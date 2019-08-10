package nil.ed.reflect.clazz;

/**
 * @author lidelin
 * @date 2019/07/31 17:23
 */
public class SubClazz extends SuperClazz<String> implements SuperInterface {
    /**
     *  public java.lang.Object method(java.lang.Object);
     *     descriptor: (Ljava/lang/Object;)Ljava/lang/Object;
     *     flags: ACC_PUBLIC, ACC_BRIDGE, ACC_SYNTHETIC
     *
     *  public java.lang.String method(java.lang.String);
     *     descriptor: (Ljava/lang/String;)Ljava/lang/String;
     *     flags: ACC_PUBLIC
     * @param param
     * @return
     */
    @Override
    public String method(String param) {
        return param;
    }

    @Override
    public Object method1(String param) {
        return null;
    }

    @Override
    public void method() {

    }
}
