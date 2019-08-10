package nil.ed.sample.springboot.aop.service;

import nil.ed.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TestServiceTest extends AbstractServiceTest {
    @Autowired
    private TestService service;

    @Test
    public void testNormal() {
        printAsJsonString(service.testNormal("testNormal", 1));
    }

    @Test
    public void testSignature() {
        service.testSignature("testSignature");
    }

    @Test
    public void testThrows() {
        service.testThrows("testNormal");
    }
}