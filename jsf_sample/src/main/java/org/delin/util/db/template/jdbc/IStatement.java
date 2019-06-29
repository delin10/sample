package org.delin.util.db.template.jdbc;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IStatement {
    int executeUpdate();

    ResultSet executeQuery(String sql);

    ResultSet executeQuery();

    PreparedStatement getPreparedStatement();

    int[] excuteBatch();

    Field[] getFields();
}
