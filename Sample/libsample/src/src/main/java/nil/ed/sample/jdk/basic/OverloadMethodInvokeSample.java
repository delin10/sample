package nil.ed.sample.jdk.basic;

/**
 * 在继承体系下重载方法的选择
 * 1、如果重载方法方法参数中包含继承体系下参数，比如method(SuperClass)、method(SubClass)，如果此时使用SubClass的子类对象传入，选择的方法为？
 * 2、如果同时继承类和接口，该接口具有与类相同的函数签名的默认方法，选择调用哪一个？
 * 3、在（2）的条件下如果重载的方法中存在一个泛型，Java怎么选择？
 * 4、
 *
 * @author lidelin
 * @date 2019/08/10 09:23
 */
public class OverloadMethodInvokeSample {
    private InvokeDelegate delegate = new InvokeDelegate();

    public static void main(String[] args) {
        Scene1.show();
    }
}

/**
 * 场景1
 */
class Scene1 {
    public static void show() {
        InvokeSubA invokeSubA = new InvokeSubA();
        InvokeDelegate.invoke(invokeSubA);
    }

    static class InvokeDelegate {
        public static void invoke(BaseInvoke obj) {
            System.out.println("select invoke(BaseInvoke)");
            ((BaseInvoke) obj).invoke();
        }

        public static void invoke(Invoke obj) {
            System.out.println("select invoke(Invoke)");
            ((BaseInvoke) obj).invoke();
        }

        public static void invoke(InvokeSuper obj) {
            System.out.println("select invoke(InvokeSuper)");
            ((BaseInvoke) obj).invoke();
        }
    }
}

/**
 * Invoke代理
 */
class InvokeDelegate<T> {
    /**
     * 泛型证明擦除
     *
     * @param obj
     */
    public void invoke(T obj) {
        System.out.println("select invoke(Object)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(BaseInvoke obj) {
        System.out.println("select invoke(BaseInvoke)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(Invoke obj) {
        System.out.println("select invoke(Invoke)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(DisturbInvoke obj) {
        System.out.println("select invoke(DisturbInvoke)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(InvokeSuper obj) {
        System.out.println("select invoke(InvokeSuper)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(InvokeSubA obj) {
        System.out.println("select invoke(InvokeSubA)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(InvokeSubB obj) {
        System.out.println("select invoke(InvokeSubB)");
        ((BaseInvoke) obj).invoke();
    }

    public void invoke(InvokeSubC obj) {
        System.out.println("select invoke(InvokeSubC)");
        ((BaseInvoke) obj).invoke();
    }
}

/**
 * 基础Invoke接口
 */
interface BaseInvoke {
    void invoke();
}

/**
 * 实现默认方法的接口
 */
interface Invoke extends BaseInvoke {
    @Override
    default void invoke() {
        System.out.println("invoke is interface Invoke default method");
    }
}

/**
 * 实现默认方法的干扰接口
 */
interface DisturbInvoke extends BaseInvoke {
    @Override
    default void invoke() {
        System.out.println("invoke is interface DisturbInvoke default method");
    }
}

interface DisturbInvokeSubInterface extends DisturbInvoke {
    @Override
    default void invoke() {
        System.out.println("invoke is interface DisturbInvokeSubInterface default method");
    }
}

/**
 * Invoke基类
 */
class InvokeSuper implements Invoke {

    @Override
    public void invoke() {
        System.out.println("invoke is InvokeSuper invoke method");
    }
}

class InvokeSubA extends InvokeSuper {
    @Override
    public void invoke() {
        System.out.println("invoke is InvokeSubA invoke method");
    }
}

/**
 * 正序实现的接口
 */
class InvokeSubB extends InvokeSuper implements DisturbInvoke {
    @Override
    public void invoke() {
        System.out.println("invoke is InvokeSubB invoke method");
    }
}

/**
 * 反序实现的接口
 */
class InvokeSubC extends InvokeSuper implements DisturbInvoke, Invoke {
    @Override
    public void invoke() {
        System.out.println("invoke is InvokeSubC invoke method");
    }
}
