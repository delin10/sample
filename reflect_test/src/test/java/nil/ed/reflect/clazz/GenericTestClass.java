package nil.ed.reflect.clazz;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author lidelin
 * @date 2019/07/24 11:31
 */
public class GenericTestClass<T> {
    T field;
    public String getGenericClass(){
        Type superrClazz = this.getClass().getGenericSuperclass();
        // 无法转换
        // 泛型具体化后的泛型类可以转换
        ParameterizedType parameterizedType = (ParameterizedType)superrClazz;
        return ((ParameterizedType) superrClazz).getActualTypeArguments()[0].getTypeName();
    }

    public T getField() {
        return field;// Field field:Ljava/lang/Object;
    }
}
