package nil.ed.mapper;

import nil.ed.entity.TestEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Consumer;

@Repository
public interface TestMapper {
	List<TestEntity> listTests(long id, @Param("part") String content);
}