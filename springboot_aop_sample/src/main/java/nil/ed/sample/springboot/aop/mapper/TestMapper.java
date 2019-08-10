package nil.ed.sample.springboot.aop.mapper;

import nil.ed.sample.springboot.aop.entity.TestEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {
    List<TestEntity> listTests();

    void insert(TestEntity entity);
}