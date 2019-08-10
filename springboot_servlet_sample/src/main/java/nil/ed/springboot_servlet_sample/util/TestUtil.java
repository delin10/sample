package nil.ed.springboot_servlet_sample.util;

import java.time.Instant;

/**
 * @author lidelin
 * @date 2019/08/04 17:02
 */
public class TestUtil {
    public static long test(Runnable runnable) {
        long start = Instant.now().getNano();
        runnable.run();
        return Instant.now().getNano() - start;
    }
}
