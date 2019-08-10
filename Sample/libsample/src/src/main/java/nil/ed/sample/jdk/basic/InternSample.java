package nil.ed.sample.jdk.basic;

import java.util.Random;

/**
 * String#intern的测试
 * @author lidelin
 * @date 2019/08/06 10:43
 */
public class InternSample {
    public static void main(String[] args) {
        boolean result = false;
        String str = "abc";
        result = ("a" + "b" + "c").intern().equals("abc");
        System.out.println(result);
        System.out.println(("a" + "b" + "c") == "abc");

        System.out.println("abc".intern() == str);

//        String a = "" + 11111;
//        String b = "11111";
//        System.out.println(a == b);
//
//        String s = new String("1");
//        s.intern();
//        String s2 = "1";
//        // false
//        System.out.println(s == s2);
//
//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4);

        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);


        test();
    }

    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void test() {
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();
    }
}
