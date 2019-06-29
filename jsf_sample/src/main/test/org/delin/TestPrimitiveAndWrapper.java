package org.delin;

import org.delin.reflect.IReflector;
import org.delin.reflect.impl.JReflectorImpl;

import java.lang.reflect.Field;

public class TestPrimitiveAndWrapper {
    static class A {
        int a;
    }

    public static void main(String[] args) {
        IReflector reflector = new JReflectorImpl();
        Field field = reflector.getField(A.class, "a");
        System.out.println(field.getType().equals(int.class));
        System.out.println(Integer.TYPE);
        System.out.println(field.getType());
    }
}
