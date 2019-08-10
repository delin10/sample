package nil.ed.springboot_servlet_sample.processor.mem.buffer;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;
import nil.ed.springboot_servlet_sample.processor.mem.Constants;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/07/25 13:11
 */
public class DirectByteBufferPoolImpl extends AbstractByteBufferPool implements Iterable<PageNode> {
    private static final int FIXED_PAGE_SIZE = 4 * Constants._1KB;
    private static final int MIN_PAGE_COUNT = 1;
    private int initialPageCount;
    /**
     * 双向循环链表头、尾结点
     */
    private PageNode head, tail;
    /**
     * 当前可用的页结点，非最后一个
     */
    private PageNode currentNode;
    /**
     * 池中页的总数
     */
    private int pageCount;
    /**
     * 已使用的页面
     */
    private int usedPage;
    /**
     * 池已使用的大小
     */
    private int byteCount;
    /**
     *
     */
    private int modCount;

    public DirectByteBufferPoolImpl(int initialSize) {
        super(new DirectPageNodeFactoryImpl());
        if (initialSize <= 0) {
            throw new IllegalArgumentException("invalid param: initialSize <= 0 ");
        }
        this.initialPageCount = (int) Math.ceil(1.0 * initialSize / FIXED_PAGE_SIZE);
        allocate(MIN_PAGE_COUNT);
    }

    @Override
    public void put(byte[] arr, int src, int len) {
        //System.out.println("pool start put...");
        int putSize = len, start = src;
        byte[] rest = arr;
        byteCount += len;
        while (true) {
            rest = currentNode.put(rest, start, putSize, true);
            //System.out.println("rest.length = " + rest.length);
            putSize = rest.length;
            if (putSize > 0) {
                start = 0;
                nextNode();
            } else {
                break;
            }
        }
    }

    @Override
    public void put(byte b) {
        if (currentNode.isFull()) {
            allocate(1);
            currentNode = currentNode.getNext();
        }
        currentNode.put(b);
        afterPut(currentNode);
    }

    @Override
    public byte get() {
        return 0;
    }

    @Override
    public int getPageCount() {
        return this.pageCount;
    }

    @Override
    public int getByteCount() {
        return this.byteCount;
    }

    /**
     * 删除最大数量为max的空闲页面
     *
     * @param max
     */
    public void purge(int max) {
        if (tail == null || tail == head) {
            return;
        }

        PageNode p = tail;
        int count = Math.min(pageCount, max);

        while (count > 0 && p.isFull() && p != head) {
            relink(p.getPrev(), head);
            p = p.getPrev();
        }
    }

    @Override
    public Spliterator<PageNode> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<PageNode> iterator() {
        return new DirectBufferPoolIterator();
    }

    @Override
    public Iterator<Byte> byteIterator() {
        return new ByteIterator();
    }

    @Override
    public String toString() {
        return String.format("[pageCount = %d, byteCount = %d, usedPage = %s]", pageCount, byteCount, usedPage);
    }

    @Override
    protected void addLast(PageNode node) {
        if (head == null) {
            currentNode = tail = head = node;
        }

        tail.setNext(node);
        node.setPrev(tail);
        node.setNext(head);
        head.setPrev(node);
        tail = node;
        ++pageCount;
        ++modCount;
    }

    @Override
    protected PageNode createPageNode() {
        return factory.createPageNode(FIXED_PAGE_SIZE);
    }

    /**
     * 定位索引为i叶节点
     * V
     * 1 -> 2 -> 3 -> 4
     *
     * @param pageNo 页号
     * @return
     * @throws IllegalArgumentException pageNo不合法抛出异常
     */
    protected PageNode indexNode(int pageNo) {
        if (pageNo >= pageCount) {
            throw new IllegalArgumentException("invalid pageNo");
        }

        PageNode n = null;
        //  偏左，从左到右搜索
        if (pageNo < pageCount / 2) {
            int count = pageNo;
            n = head;
            while (n != null && count-- > 0) {
                n = n.getNext();
            }
        } else {
            // 偏右，从右往左搜索
            int count = pageCount - pageNo - 1;
            n = tail;
            while (n != null && count-- > 0) {
                n = n.getPrev();
            }
        }
        return n;
    }

    /**
     * put回调
     *
     * @param pageNode
     */
    protected void afterPut(PageNode pageNode) {
    }

    private PageNode nextNode() {
        usedPage++;
        if (currentNode == tail) {
            allocate(1);
        }
        return currentNode = currentNode.getNext();
    }

    private void relink(PageNode cur, PageNode newNext) {
        cur.setNext(newNext);
        newNext.setPrev(cur);
    }

    class DirectBufferPoolIterator implements Iterator<PageNode> {
        private boolean end = false;
        private PageNode cursor;

        DirectBufferPoolIterator() {
            cursor = head;
        }

        @Override
        public boolean hasNext() {
            return !end || cursor != null;
        }

        @Override
        public PageNode next() {
            if (end || Objects.isNull(cursor)) {
                throw new NoSuchElementException();
            }
            //System.out.println(String.format("head=%s, tail=%s, cursor=%s", head, tail, cursor));
            PageNode node = cursor;
            if (cursor == tail) {
                end = true;
                cursor = null;
            } else {
                cursor = cursor.getNext();
            }
            return node;
        }
    }

    class ByteIterator implements Iterator<Byte> {
        private Iterator<PageNode> pageNodeIterator;
        private PageNode currentNode;
        private int offset;

        public ByteIterator() {
            this.pageNodeIterator = iterator();
            currentNode = pageNodeIterator.next();
        }

        @Override
        public boolean hasNext() {
            return offset < currentNode.getUsed() || pageNodeIterator.hasNext();
        }

        @Override
        public Byte next() {
            if (offset == currentNode.getUsed()) {
                if (pageNodeIterator.hasNext()) {
                    currentNode = pageNodeIterator.next();
                    offset = 0;
                } else {
                    throw new NoSuchElementException();
                }
            }
            return currentNode.get(offset++);
        }
    }
}
