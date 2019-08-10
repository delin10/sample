package nil.ed.sample.springboot.aop.service.impl;


import nil.ed.sample.springboot.aop.common.annotation.AopAroundLog;
import nil.ed.sample.springboot.aop.entity.TestEntity;
import nil.ed.sample.springboot.aop.mapper.TestMapper;
import nil.ed.sample.springboot.aop.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {

    @Resource
    private TestMapper testMapper;

    @Override
    @AopAroundLog
    @Transactional(rollbackFor = Exception.class)
    public List<TestEntity> listTests() {
        List<TestEntity> ls = testMapper.listTests();
        System.out.println(ls);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(TestEntity entity) {
        testMapper.insert(entity);
        entity.setId(entity.getId() + 1);
        testMapper.insert(entity);
        throw new RuntimeException();
    }
}
