package org.delin.util.db.datasource;


import java.sql.Connection;
import java.sql.SQLException;

public interface IDataSource {
    Connection getConnection() throws SQLException;
}
