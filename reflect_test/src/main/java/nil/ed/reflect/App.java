package nil.ed.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Throwable{
        int count = 10000000;
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

        Method method=App.class.getDeclaredMethod("test0", int.class, int.class);
        IntStream.range(0,count).forEach(i->{
            try {
                int result = (int) method.invoke(1, 1);
            } catch (Throwable throwable) {
            }
        });

    }

    public static int test0(int a, int b) {
        return a + b;
    }

    public int test1(int a, int b) {
        return a + b;
    }
}
