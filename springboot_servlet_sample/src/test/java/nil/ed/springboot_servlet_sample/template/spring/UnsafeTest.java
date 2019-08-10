package nil.ed.springboot_servlet_sample.template.spring;

import nil.ed.springboot_servlet_sample.util.TestUtil;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.text.Format;
import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/08/04 16:58
 */
public class UnsafeTest {
    static class DirectObject{
        private int a;
        private Object inst;

        public DirectObject(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        static {
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                Unsafe unsafe = (Unsafe) field.get(null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testUnsafe() throws Exception {
        final int count = 100000;
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        DirectObject directObject = (DirectObject) unsafe.allocateInstance(DirectObject.class);
        System.out.println(directObject.getA());
        long timeUnsafe = TestUtil.test(() -> {
            IntStream.range(0, count).forEach(i -> {
                try {
                    Object obj = unsafe.allocateInstance(Object.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                }
            });
        });

        long timeNormal = TestUtil.test(() -> {
            IntStream.range(0, count).forEach(i -> {
                try {
                    Object obj = new Object();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            });
        });

        System.out.println("1 : " + timeUnsafe);
        System.out.println("2 : " + timeNormal);
    }
}
