package nil.ed.repository;

import nil.ed.entities.TestEntity;
import nil.ed.rowmapper.TestEntityRowMapper;
import nil.ed.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class JdbcRepository  {
    private final String INSERT_SQL = "insert into my (name,ds,last_modified) values(?,?,?)";
    private final String QUERY_SQL = "select * from my";
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;

    }

    public void addObject(){
        jdbcOperations.update(INSERT_SQL,"a","dsc", Calendar.getInstance());
    }

    public List listObjects(){
        return jdbcOperations.queryForList(QUERY_SQL);
    }
}
