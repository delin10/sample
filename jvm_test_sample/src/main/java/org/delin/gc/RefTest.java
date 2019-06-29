package org.delin.gc;

import java.lang.ref.*;

public class RefTest {
    static {
        System.out.println("RefTest.class init");
        System.gc();
    }

    static final int _mb = 1024 * 1024;

    static class A {
        int v;

        static {
            System.out.println("init A(Class)");
        }

        {
            System.out.println("init A!");
        }

        public A() {
            v = 1;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(A.this + "---A finalize() is called!!");
        }
    }

    static class  BB{
        int v;

        static {
            System.out.println("init A(Class)");
        }

        {
            System.out.println("init A!");
        }

        public BB() {
            v = 1;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            while (true) {
            }
        }
    }
    public static void testSoftRef() {
        A a = new A();
        System.gc();
        final ReferenceQueue<A> refQ = new ReferenceQueue<>();
        final SoftReference<A> ref = new SoftReference<>(a, refQ);
        //System.out.println("call enqueue:" + ref.enqueue());
        //当它所关心的引用要被回收了 进入队列
        System.out.println("Before a=null, refQ.poll():" + refQ.poll());
        a = null;
        System.gc();
        System.out.println("After a=null, refQ.poll():" + refQ.poll());
        //由JVM内部调用，当GC要堆此对象进行GC时
        //ref.enqueue();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.gc();
                //从未遇到过 Reference<A>报错
                Reference<? extends A> reff = refQ.poll();
                System.out.println("After OOM1 call refQ.poll():" + reff + ",value=" + (reff != null ? reff.get() : "null"));
                System.gc();
                System.out.println("After OOM2 call ref.get:" + reff.get());
            }
        });
        // -Xms1m -Xmx1m OOM
        byte[] bs = new byte[10 * _mb];
        for (int i = 0; i < bs.length; ++i) {
            bs[i] = 0;
        }
        System.out.println("byte数组的长度:" + bs.length);

    }

    public static void testWeakRef() {
        final ReferenceQueue<A> refQ = new ReferenceQueue<>();
        WeakReference<A> ref = new WeakReference<>(new A(), refQ);
        System.out.println("调用GC前:" + refQ.poll());
        System.gc();
        //enqueue是另一个低优先级的线程进行的
        Thread.yield();
        System.out.println("yield:" + refQ.poll());
    }

    public static void testPhantomRef() {
        //OOM对象真正被回收
//        new Thread(){
//            @Override
//            public void run() {
//                byte[] bs=new byte[2*_mb];
//            }
//        }.start();
        final ReferenceQueue<A> refQ = new ReferenceQueue<>();
        A a=new A();
        PhantomReference<A> ref = new PhantomReference<>(a, refQ);
        //WeakReference必须存在强引用 否则a不被判定为存在弱引用的对象
        WeakReference<A> reff=new WeakReference<>(a,refQ);
        a=null;
        System.gc();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.gc();
        //The get method of a reference always returns null
        //重载了finalize方法之后 阻塞
        // 原因：需要等待finalizer线程执行完finalize方法之后，才能堆对象进行回收，但是由于Finalizer本身优先级较低，所以导致
        // 用户线程在执行过程中 Finalizer是完全没有机会执行的 所以一直处于等待状态
        // 但是org.delin.gc.RefTest$A@1f18626---A finalize() is called!!输出结果存在
        // finalize被调用 对象只是被标记为finalized
        // system.gc() JVM不一定执行GC
        //然而 PhantomReference入队的时间为对象被回收时 但是对象由于上述原因导致的finalize方法的无法调用完成
        //对象迟迟无法回收，所以造成上述问题
        try {
            //必须用户线程先睡眠 再GC
            Thread.sleep(100);
            System.gc();
            System.out.println(refQ.remove());
            System.out.println(refQ.remove());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test finalize()
     */
    static SaveClass save;

    static class SaveClass {
        public SaveClass() {
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize() is called, this is saved!");
            save = this;
        }
    }

    public static void testFinalize() {
        SaveClass sv = new SaveClass();
        sv = null;
        System.gc();
        System.out.println("After First System.gc():" + save);
        save = null;
        System.gc();
        // 结果是null 因为finalize方法只调用一次
        System.out.println("After second System.gc():" + save);
    }

    public static void main(String[] args) {
        testPhantomRef();
    }
}
