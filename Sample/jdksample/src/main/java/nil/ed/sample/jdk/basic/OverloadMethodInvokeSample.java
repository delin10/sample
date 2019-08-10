package nil.ed.sample.jdk.basic;

/**
 * 在继承体系下重载方法的选择
 * 1、如果重载方法方法参数中包含继承体系下参数，比如method(SuperClass)、method(SubClass)，如果此时使用SubClass的子类对象传入，选择的方法为？
 * --- 选择最具体的方法
 * 2、如果同时继承类和接口，该接口具有与类相同的函数签名的默认方法，选择调用哪一个？（即Java版的菱形继承）
 * 3、在（1）的条件下如果重载的方法中存在一个泛型，Java怎么选择？
 * 4、如果实现的接口具有相同签名的默认方法，将会怎么调用？
 *
 * @author lidelin
 * @date 2019/08/10 09:23
 */
public class OverloadMethodInvokeSample {
    public static void main(String[] args) {
        Scene3.show();
    }
}

/**
 * 场景1
 * 结果分析：
 * 优先调用最具体即子类的接口
 */
class Scene1 {
    public static void show() {
        SubClass subClass = new SubClass();
        InvokeDelegate.invoke(subClass);

        SuperClass superClass = subClass;
        InvokeDelegate.invoke(superClass);
    }

    static class SuperClass {

    }

    static class SubClass extends SuperClass {

    }

    static class InvokeDelegate {
        public static void invoke(SuperClass obj) {
            System.out.println("select invoke(SuperClass)");
        }

        public static void invoke(SubClass obj) {
            System.out.println("select invoke(SubClass)");
        }

        public static void invoke(Object obj) {
            System.out.println("select invoke(Object)");
        }
    }
}

/**
 * 场景2
 * 结果：优先调用父类的方法
 */
class Scene2 {
    public static void show() {
        BaseInvoke invoke = new DiamondInheritClass();
        invoke.invoke();
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
            System.out.println("invoke is interface Invoke invoke default method");
        }
    }

    static class InvokePeerClass implements BaseInvoke {

        @Override
        public void invoke() {
            System.out.println("invoke is Class InvokePeerClass invoke method");
        }
    }

    /**
     * 菱形继承子类
     */
    static class DiamondInheritClass extends InvokePeerClass implements Invoke {

    }
}

/**
 * 场景三：
 * 带泛型的在被泛型擦除之后为Object，优先级低
 */
class Scene3 {
    public static void show() {
        SubClass subClass = new SubClass();
        InvokeDelegate.invoke(subClass);

        SuperClass superClass = subClass;
        InvokeDelegate.invoke(superClass);
    }

    static class SuperClass {

    }

    static class SubClass extends SuperClass {

    }

    static class InvokeDelegate {
        public static void invoke(SuperClass obj) {
            System.out.println("select invoke(SuperClass)");
        }

        public static <T> void invoke(T obj) {
            System.out.println("select invoke(Object)");
        }
    }
}

/**
 * 场景四：
 * 不支持接口默认方法菱形继承
 */
class Scene4 {
    interface A {
        default void invoke(Object obj) {
            System.out.println("A");
        }
    }

    interface B {
        default void invoke(Object obj) {
            System.out.println("B");
        }
    }

    /**
     * Error:(157, 5) java: 类 nil.ed.sample.jdk.basic.Scene4.C从类型 nil.ed.sample.jdk.basic.Scene4.A 和 nil.ed.sample.jdk.basic.Scene4.B 中继承了invoke(java.lang.Object) 的不相关默认值
     */
//    class C implements A, B {
//    }
}

