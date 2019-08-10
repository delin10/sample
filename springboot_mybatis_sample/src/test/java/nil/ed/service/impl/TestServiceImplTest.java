package nil.ed.service.impl;

import nil.ed.AbstractServiceTest;
import nil.ed.entity.TestEntity;
import nil.ed.service.ITestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TestServiceImplTest extends AbstractServiceTest {
    @Autowired
    private ITestService testService;

    @Test
    public void listTests() {
        testService.listTests(1, "");
    }
}