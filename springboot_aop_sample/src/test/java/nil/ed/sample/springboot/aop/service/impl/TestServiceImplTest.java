package nil.ed.sample.springboot.aop.service.impl;

import nil.ed.AbstractServiceTest;
import nil.ed.sample.springboot.aop.entity.TestEntity;
import nil.ed.sample.springboot.aop.service.ITestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestServiceImplTest extends AbstractServiceTest {
    @Autowired
    private ITestService service;

    @Test
    public void listTests() {
        service.listTests();
    }

    @Test
    public void insert() {
        TestEntity entity = new TestEntity();
        entity.setId(100000000);
        service.insert(entity);

        Field[] fields = TestServiceImpl.class.getDeclaredFields();
        Method[] methods = TestServiceImpl.class.getDeclaredMethods();
        Arrays.stream(fields).forEach(System.out::println);
        Arrays.stream(methods).forEach(System.out::println);
    }
}