package nil.ed.reflect.method;

import nil.ed.reflect.clazz.SubClazz;
import nil.ed.reflect.clazz.SuperClazz;
import org.junit.Test;

/**
 * @author lidelin
 * @date 2019/07/31 17:24
 */
public class BridgeMethodTest {
    @Test
    public void test() {
        SubClazz subClazz = null;
        SuperClazz<String> superClazz = subClazz = new SubClazz();
        superClazz.method("xxxx");
    }
}
