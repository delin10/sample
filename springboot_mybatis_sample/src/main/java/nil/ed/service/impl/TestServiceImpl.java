package nil.ed.service.impl;

import nil.ed.entity.TestEntity;
import nil.ed.mapper.TestMapper;
import nil.ed.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestEntity> listTests(int id, String content) {
        return testMapper.listTests(id, content);
    }
}
