package nil.ed.sample.jdk.basic;

/**
 * switch的字节码实现
 * @author lidelin
 * @date 2019/08/06 10:12
 */
public class SwitchSample {
    public static void main(String[] args) {
        int a = 0;
        switch (a) {
            case 0:
                println(0);
            case 1:
                println(1);
                break;
            case 2:
                println(2);
            default:
                println(Integer.MAX_VALUE);
        }
    }

    public static void println(int a) {
        System.out.println(a);
    }
}
