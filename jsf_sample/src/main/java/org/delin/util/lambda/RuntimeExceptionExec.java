package org.delin.util.lambda;

public class RuntimeExceptionExec {
    public static Object exec(ExceptionRunnable runnable) {
        try {
            return runnable.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
