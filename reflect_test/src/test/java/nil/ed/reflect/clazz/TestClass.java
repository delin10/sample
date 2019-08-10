package nil.ed.reflect.clazz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lidelin
 * @date 2019/07/23 17:03
 */
public class TestClass {
    private Map<List<String>, Map<Integer, List<Integer>>> mapMap = new HashMap<>();

    public Object test(List<Integer> generic, int a, String b, Object c) {
        return c;
    }

    public <T> void test(T xx){

    }
    //泛型擦除之后签名与此一致
//    public void test(Object xx){
//
//    }
}
