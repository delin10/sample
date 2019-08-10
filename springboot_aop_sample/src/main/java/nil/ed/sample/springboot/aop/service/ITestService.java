package nil.ed.sample.springboot.aop.service;

import nil.ed.sample.springboot.aop.entity.TestEntity;
import org.aspectj.weaver.ast.Test;

import java.util.List;

public interface ITestService {
    List<TestEntity> listTests();

    void insert(TestEntity entity);
}
