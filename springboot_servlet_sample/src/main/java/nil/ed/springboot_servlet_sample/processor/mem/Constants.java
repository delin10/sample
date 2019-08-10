package nil.ed.springboot_servlet_sample.processor.mem;

/**
 * @author lidelin
 * @date 2019/07/25 09:39
 */
public class Constants {
    public static int _1GB;
    public static int _1MB;
    public static int _1KB;
    public static int _1B;

    static {
        _1B = 1;
        _1KB = 1024 * _1B;
        _1MB = 1024 * _1KB;
        _1GB = 1024 * _1MB;
    }
}
