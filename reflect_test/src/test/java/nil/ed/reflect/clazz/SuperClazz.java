package nil.ed.reflect.clazz;

import java.io.IOException;

/**
 * @author lidelin
 * @date 2019/07/31 17:22
 */
public abstract class SuperClazz<T> {
    public abstract T method(T param);

    public abstract Object method1(String param)throws IOException;
}
