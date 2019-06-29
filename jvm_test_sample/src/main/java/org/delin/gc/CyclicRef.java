package org.delin.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class CyclicRef {
    static final int _1mb=1024*1024;
    static class A {
        B b;

        public A() {
        }

        A(B bb) {
            this.b = bb;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("A 被回收");
        }
    }

    static class B {
        A a;

        public B() {
        }

        B(A aa) {
            this.a = aa;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("B 被回收");
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        ReferenceQueue<A> refQ=new ReferenceQueue<>();
        PhantomReference<A> ref=new PhantomReference<>(a,refQ);
        a.b = b;
        b.a = a;
        alloc("0");
        a = null;
        alloc("1");
        b.a = null;
        alloc("2");
        System.out.println(refQ.poll());
        //Full GC这里A被回收
        alloc("3");
        try {
            System.out.println(refQ.remove());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void alloc(final String id){
        Thread t1=new Thread(){
            @Override
            public void run() {
                System.out.println(id+" alloc \r\n\r\n");
                byte[] bs=new byte[2*_1mb];
            }
        };
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
