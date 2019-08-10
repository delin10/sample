package nil.ed.sample.springboot.aop.service;

import nil.ed.sample.springboot.aop.common.annotation.AopAroundLog;
import org.springframework.stereotype.Service;

/**
 * @author lidelin
 * @date 2019/07/29 10:41
 */
@Service
public class TestService {
    @AopAroundLog
    public String testNormal(String a, Integer b) {
        System.out.println("test");
        return "testNormal";
    }

    @AopAroundLog
    public String testSignature(String a) {
        System.out.println("test");
        return a;
    }

    @AopAroundLog
    public String testThrows(String a) {
        System.out.println("test");
        throw new RuntimeException("exp");
    }
}
