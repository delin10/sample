package nil.ed.reflect.clazz;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenericTestClassTest {

    @Test
    public void getGenericClass() {
        GenericTestClass<Integer> genericTestClass = new ConcretGenericTestClassImpl();
        System.out.println(genericTestClass.getGenericClass()  );
    }
}