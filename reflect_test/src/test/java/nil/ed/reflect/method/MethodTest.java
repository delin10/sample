package nil.ed.reflect.method;

import nil.ed.reflect.App;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/07/15 14:22
 */
public class MethodTest {
    int count = 1000000;

    /**
     * 11ms
     */
    @Test
    public void testMethodHandle() {
        Object receiver = App.class;
        MethodType mt = MethodType.methodType(int.class, new Class[]{int.class, int.class});
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            //搜寻方法
            MethodHandle methodHandle = lookup.findStatic(App.class, "test0", mt);
            IntStream.range(0,count).forEach(i->{
                try {
                    int result = (int) methodHandle.invoke(1, 1);
                } catch (Throwable throwable) {
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 2s
     */
    @Test
    public void testMethod(){
        try {
            Method method = App.class.getDeclaredMethod("test0", int.class, int.class);
            IntStream.range(0, count).forEach(i -> {
                try {
                    int result = (int) method.invoke(1, 1);
                } catch (Throwable throwable) {
                }
            });
        }catch (Exception e){

        }
    }
}
