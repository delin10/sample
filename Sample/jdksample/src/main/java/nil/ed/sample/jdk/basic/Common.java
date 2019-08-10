package nil.ed.sample.jdk.basic;

public class Common {
    public static class MemorySizeEnum {
        public static final int _1B = 1;
        public static final int _1KB = 1024 * _1B;
        public static final int _1MB = 1024 * _1KB;
        public static final int _1GB = 1024 * _1MB;
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }
}
