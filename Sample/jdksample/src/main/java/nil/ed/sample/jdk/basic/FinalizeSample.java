package nil.ed.sample.jdk.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author lidelin
 * @date 2019/08/06 17:26
 */
public class FinalizeSample {
    private static final int _1KB = 1024;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        while (true) {
            System.out.println("FinalizeSample -- " + Thread.currentThread());
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) throws Exception {
        FinalizeSample sample = new FinalizeSample();
        FinalizeClass finalizeClass = new FinalizeClass();
        sample = null;
        finalizeClass = null;
        byte[] bytes = new byte[100 * _1KB];
        TimeUnit.SECONDS.sleep(10);
    }
}

class FinalizeClass {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        while (true) {
            System.out.println("FinalizeClass -- " + Thread.currentThread());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
