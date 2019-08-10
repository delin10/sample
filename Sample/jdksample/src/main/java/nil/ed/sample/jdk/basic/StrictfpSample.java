package nil.ed.sample.jdk.basic;

import java.math.BigDecimal;

/**
 * @author lidelin
 * @date 2019/08/06 11:55
 */
public class StrictfpSample {
    /**
     * 测试结果：Strictfp可确保您在每个平台上获得与浮点计算完全相同的结果
     * @param args
     */
    public strictfp static void main(String[] args) {
        System.out.println(1.7 - 1.6);

        BigDecimal decimal = new BigDecimal("1.7");
        BigDecimal decimal2 = new BigDecimal("1.6");
        System.out.println(decimal.subtract(decimal2));
    }
}
