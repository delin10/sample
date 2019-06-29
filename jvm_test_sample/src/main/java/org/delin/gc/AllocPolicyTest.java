package org.delin.gc;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class AllocPolicyTest {
    /**
     * 普通对象优先在Eden分配
     */
    public static void testEdenAlloc() {
        byte[] bs0 = new byte[1 * Constants._1mb];
        byte[] bs1 = new byte[1 * Constants._1mb];
        byte[] bs2 = new byte[1 * Constants._1mb];
        byte[] bs4 = new byte[1 * Constants._1mb];
    }

    /**
     * 大对象优先直接在老年代分配
     */
    public static void testBigObject() {
        byte[] bs0 = new byte[8 * Constants._1mb];
    }

    /**
     * 对象年龄
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void testObjectAge() {
        //256
        byte[] bs0 = new byte[Constants._1mb / 4];
        byte[] bs01 = new byte[Constants._1mb / 4];
        System.out.println("bs1 alloc");
        //4096 4352
        byte[] bs1 = new byte[8 * Constants._1mb];
        System.out.println("bs2 alloc");
        //2048 6400
        byte[] bs2 = new byte[3 * Constants._1mb];
        System.out.println("bs3 alloc");
        bs1 = null;
        System.out.println("bs5 alloc");
        byte[] bs5 = new byte[8 * Constants._1mb];
    }

    public static void main(String[] args) {
    }
}
