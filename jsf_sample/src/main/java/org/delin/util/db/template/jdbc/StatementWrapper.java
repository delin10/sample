package org.delin.util.db.template.jdbc;

import org.delin.util.lambda.RuntimeExceptionExec;

import java.io.Closeable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementWrapper extends AbstractStatamentAdapter implements Closeable, IStatement {
    private Statement statement;
    private Connection connection;
    private Field[] fields;

    public StatementWrapper(Connection connection, Statement statement, Field[] fields) {
        this.statement = statement;
        this.connection = connection;
        this.fields = fields;
    }

    public static StatementWrapper parse(Connection connection, Statement statement, Field[] fields) {
        return new StatementWrapper(connection, statement, fields);
    }


    @Override
    public void close() {
        try {
            statement.close();
        } catch (Exception e) {
        }
    }

    @Override
    public int executeUpdate() {
        try {
            return ((PreparedStatement) statement).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) {
        return (ResultSet) RuntimeExceptionExec.exec(() -> {
            return statement.executeQuery(sql);
        });

    }

    @Override
    public ResultSet executeQuery() {
        return (ResultSet) RuntimeExceptionExec.exec(() -> {
            return getPreparedStatement().executeQuery();
        });
    }

    @Override
    public int[] excuteBatch() {
        try {
            return (int[]) RuntimeExceptionExec.exec(() -> {
                int[] res = getPreparedStatement().executeBatch();
                return res;
            });
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public PreparedStatement getPreparedStatement() {
        return (PreparedStatement) statement;
    }

    public Field[] getFields() {
        return fields;
    }
}
