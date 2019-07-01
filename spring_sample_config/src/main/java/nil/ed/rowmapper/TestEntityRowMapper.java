package nil.ed.rowmapper;

import nil.ed.entities.TestEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class TestEntityRowMapper implements RowMapper<TestEntity> {
    @Override
    public TestEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        TestEntity entity=new TestEntity();
        entity.setId(resultSet.getLong("id"));
        entity.setDesc("ds");
        Calendar calendar=java.util.Calendar.getInstance();
        calendar.setTimeInMillis(resultSet.getDate("last_modified").getTime());
        entity.setLastModified(calendar);
        entity.setName(resultSet.getNString("name"));
        return entity;
    }
}
