package nil.ed.ssl;

public class ParamChecker {
    public static void checkNullAndThrows(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }
}
