package nil.ed.reflect.method;

import nil.ed.reflect.clazz.TestClass;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lidelin
 * @date 2019/07/23 17:04
 */
public class ParameterTest {
    @Test
    public void test() throws Exception {
//        Method method = TestClass.class.getDeclaredMethod("test", List.class, int.class, String.class, Object.class);
//
//        Parameter[] parameter = method.getParameters();
//        Arrays.stream(parameter).map(Parameter::getDeclaringExecutable).map(Executable::toGenericString).forEach(System.out::println);
//        Type[] types = ((ParameterizedType)parameter[0].getParameterizedType()).getActualTypeArguments();
//        Arrays.stream(types).forEach(System.out::println);
//
//        Type mapMapType = TestClass.class.getDeclaredField("mapMap").getGenericType();
//        //ParameterizedType 不能带有泛型参数
//        ParameterizedType parameterizedType = (ParameterizedType)mapMapType;
//        Arrays.stream(parameterizedType.getActualTypeArguments()).map(a -> a.getTypeName()).forEach(System.out::println);

          //泛型方法抛出异常
          //需要类型具体化才能使用ParameterizedType
          //java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl cannot be cast to java.lang.reflect.ParameterizedType
//        Method method1 = TestClass.class.getDeclaredMethod("test", Object.class);
//
//        Parameter[] parameter1 = method1.getParameters();
//        Type[] types1 = ((ParameterizedType)parameter1[0].getParameterizedType()).getActualTypeArguments();
//        Arrays.stream(types1).forEach(System.out::println);
    }
}
