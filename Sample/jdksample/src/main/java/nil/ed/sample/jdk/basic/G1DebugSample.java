package nil.ed.sample.jdk.basic;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static nil.ed.sample.jdk.basic.Common.MemorySizeEnum;
import static nil.ed.sample.jdk.basic.Common.println;

/**
 * @author lidelin
 * @date 2019/08/07 10:38
 */
public class G1DebugSample {

    private static final List<Object> strongRefs = new LinkedList<>();
    private static final int objectNum = 100_0000;
    private static final Random random = new Random();
    private static final int threshold = 100000;
    /**
     * 测试碎片化问题
     */
    private static final IAllocator allocator = new SmallAllocator(strongRefs);

    public static void main(String[] args) {
        allocator.startAllocate();
    }

}

class ControlDelegate {
    public static List<Object> old = new LinkedList<>();
    private static boolean debug = false;

    public static void debug(Object msg) {
        if (debug) {
            println(msg);
        }
    }
}

interface IAllocator {
    void startAllocate();
}

abstract class AbstractAllocator implements IAllocator {
    protected List<Object> strongRefLs;

    public AbstractAllocator(List<Object> strongRefLs) {
        this.strongRefLs = strongRefLs;
    }
}

/**
 * 分配BigObject导致分区占用，碎片化内存问题
 * 分区大小2 - 32之间
 * <p>
 * -verbose:gc -XX:+PrintGCDetails -XX:+UnlockDiagnosticVMOptions -Xmx128m -Xms128m -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=20 -XX:G1HeapRegionSize=490
 */
class FragmentizationAllocator extends AbstractAllocator {

    public FragmentizationAllocator(List<Object> strongRefLs) {
        super(strongRefLs);
    }

    @Override
    public void startAllocate() {
        try {
            int count = 5;
            while (count-- > 0) {
                println("new allocate start ------------");
                IBatchAllocator bigObjectAllocator = new BigObjectAllocator(3, strongRefLs);
                IBatchAllocator midObjectAllocator = new MidObjectAllocator(1, strongRefLs);
                println("start allocate BigObject");
                bigObjectAllocator.batchAllocate();
                println("start allocate MidObject");
                midObjectAllocator.batchAllocate();
                println("success");
                TimeUnit.SECONDS.sleep(3);
                strongRefLs.clear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 分配BigObject导致分区占用，碎片化内存问题
 * 分区大小2 - 32之间
 * <p>
 * -verbose:gc -XX:+PrintGCDetails -Xmx128m -Xms128m -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=20 -XX:G1HeapRegionSize=490
 */
class SmallAllocator extends AbstractAllocator {

    public SmallAllocator(List<Object> strongRefLs) {
        super(strongRefLs);
    }

    @Override
    public void startAllocate() {
        final int count = 20;
        IntStream.range(0, 100).forEach(i -> {
            try {
                println("new allocate start ------------");
                IBatchAllocator smallObjectAllocator = new SmallObjectAllocator(count, strongRefLs);
                smallObjectAllocator.batchAllocate();
                TimeUnit.SECONDS.sleep(1);
                if (i % 5 != 0) {
                    ControlDelegate.old.addAll(CollectionRandomRemover.removeFullyRandomly(strongRefLs));
                }
                strongRefLs.clear();
                CollectionRandomRemover.removeFullyRandomly(ControlDelegate.old, 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

interface IBatchAllocator {
    void batchAllocate();
}

abstract class AbstractBatchAllocator implements IBatchAllocator {
    protected int num;
    protected List<Object> strongRefLs;

    public AbstractBatchAllocator(int num, List<Object> strongRefLs) {
        this.num = num;
        this.strongRefLs = strongRefLs;
    }
}

abstract class ObjectBatchAllocatorBase extends AbstractBatchAllocator {
    protected Supplier<Object> supplier;

    public ObjectBatchAllocatorBase(Supplier<Object> supplier, int num, List<Object> strongRefLs) {
        super(num, strongRefLs);
        this.supplier = supplier;
    }

    @Override
    public void batchAllocate() {
        IntStream.range(0, num).forEach(i -> strongRefLs.add(supplier.get()));
    }
}

class BigObjectAllocator extends ObjectBatchAllocatorBase {
    public BigObjectAllocator(int num, List<Object> strongRefLs) {
        super(BigObject::new, num, strongRefLs);
    }
}

class MidObjectAllocator extends ObjectBatchAllocatorBase {
    public MidObjectAllocator(int num, List<Object> strongRefLs) {
        super(MidObject::new, num, strongRefLs);
    }
}

class SmallObjectAllocator extends ObjectBatchAllocatorBase {
    public SmallObjectAllocator(int num, List<Object> strongRefLs) {
        super(SmallObject::new, num, strongRefLs);
    }
}

class BigObject {
    private static int count = 0;
    private byte[] buffer = new byte[MemorySizeEnum._1MB * 33];

    public BigObject() {
        ControlDelegate.debug("new BigObject is ok:" + count++);
    }
}

class MidObject {
    private static int count = 0;
    private byte[] buffer = new byte[MemorySizeEnum._1MB * 22];

    MidObject() {
        ControlDelegate.debug("new MidObject is ok:" + (count++));
    }
}

class SmallObject {
    private static int count = 0;
    private byte[] buffer = new byte[MemorySizeEnum._1KB * 5];

    SmallObject() {
        ControlDelegate.debug("new SmallObject is ok:" + (count++));
    }
}

class CollectionRandomRemover {
    public static Collection<?> randomRemove(Collection<?> collection, int num) {
        if (collection.isEmpty()) {
            return collection;
        }

        if (collection.size() <= num) {
            collection.removeAll(collection);
        }

        IntStream.range(0, num).forEach(i -> {
            Iterator<?> it = collection.iterator();
            IntStream.range(0, new Random().nextInt(collection.size()) + 1).forEach(j -> it.next());
            it.remove();
        });
        return collection;
    }

    public static Collection<?> removeFullyRandomly(Collection<?> collection) {
        Random random = new Random();
        int num = random.nextInt(collection.size() / 2 + 1);
        return randomRemove(collection, num);
    }

    public static Collection<?> removeFullyRandomly(Collection<?> collection, int limit) {
        Random random = new Random();
        int num = random.nextInt(limit);
        return randomRemove(collection, num);
    }
}