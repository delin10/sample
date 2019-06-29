package org.delin.util.db.datasource.impl;

import org.delin.util.db.datasource.IDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JNDIDataSource implements IDataSource {
    private volatile DataSource dataSource;
    private String jndiName;

    public JNDIDataSource(String jndiName) {
        this.jndiName = jndiName;
        init();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void init() {
        try {
            Context context = new InitialContext();
            this.dataSource = (DataSource) context.lookup(jndiName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
