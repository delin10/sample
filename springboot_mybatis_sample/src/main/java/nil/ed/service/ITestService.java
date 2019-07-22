package nil.ed.service;

import nil.ed.entity.TestEntity;

import java.util.List;

public interface ITestService {
    public List<TestEntity> listTests(int id, String content);
}
