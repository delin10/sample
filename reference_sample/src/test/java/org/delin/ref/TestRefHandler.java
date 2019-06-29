package org.delin.ref;

import org.junit.Test;

public class TestRefHandler {
    class A {
        private String id;

        public A(String id) {
            this.id = id;
        }

//        @Override
//        protected void finalize() throws Throwable {
//            super.finalize();
//            System.out.println(id + " finalize..");
//        }
    }

    @Test
    public void test()

    {
        RefManager manager = new RefManager();
        RefHandler handler = new RefHandler(manager);
        Thread t = new Thread(handler);
        t.start();
        Object a = new A("a");
        manager.registerSoftReference(a, ref -> {
            System.out.println("a gc");
        });
        Object b = new A("b");
        manager.registerWeakReference(a, ref -> {
            System.out.println("b gc");
        });
        Object c = new A("c");
        manager.registerPhantomReference(a, ref -> {
            System.out.println("c gc");
        });
        a = null;
        b = null;
        c = null;
        try {
            Thread.sleep(100);
            System.gc();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
