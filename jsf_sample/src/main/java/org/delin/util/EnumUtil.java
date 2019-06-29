package org.delin.util;

import org.delin.reflect.IReflector;
import org.delin.reflect.impl.JReflectorImpl;

public class EnumUtil {
    private static IReflector reflector = new JReflectorImpl();

    public static Enum<?> get(String name, Class... enums) {
        for (Class clazz : enums) {
            try {
                return Enum.valueOf(clazz, name);
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return null;
    }
}
