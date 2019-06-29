package org.delin.util.db.template.jdbc;

import org.delin.reflect.IReflector;
import org.delin.reflect.impl.JReflectorImpl;
import org.delin.util.ParamsUtils;
import org.delin.util.db.datasource.IDataSource;
import org.delin.util.db.template.IDBTemplate;
import org.delin.util.db.template.OrmTemplate;
import org.delin.util.db.template.convertor.IConverter;
import org.delin.util.db.template.jpa.JpaTemplateImpl;
import org.delin.util.lambda.RuntimeExceptionExec;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class JDBCTemplateImpl implements IDBTemplate {
    private IDataSource dataSource;
    private IReflector reflector;
    private Map<Class<?>, Map<String, IConverter>> converters;
    private OrmTemplate ormTemplate;

    public JDBCTemplateImpl() {
        reflector = new JReflectorImpl();
    }

    public JDBCTemplateImpl(IDataSource dataSource) {
        this.dataSource = dataSource;
        reflector = new JReflectorImpl();
        converters = new HashMap<>();
    }

    public List<String> getColumns(ResultSetWrapper rs) throws SQLException {
        List<String> cols = new ArrayList<>();
        ResultSetMetaData rsm = rs.getMetaData();
        int count = rsm.getColumnCount();
        for (int i = 1; i <= count; ++i) {
            cols.add(rsm.getColumnName(i).toLowerCase());
        }
        return cols;
    }

    @Override
    public ResultSet executeQuery(String sql) {
        return (ResultSet) RuntimeExceptionExec.exec(() -> {
            Connection connection = dataSource.getConnection();
            StatementWrapper statement = StatementWrapper.parse(connection, connection.createStatement(), null);
            return statement.executeQuery(sql);
        });
    }

    @Override
    public ResultSet executeUniqueQuery(Map<String, Object> ids, String table) {
        return (ResultSet) RuntimeExceptionExec.exec(() -> {
            StatementWrapper statement = prepareSelectIdsStatement(ids, table);
            return ResultSetWrapper.parse(statement.executeQuery(), statement);
        });
    }

    @Override
    public List executeForResult(String sql) {
        return null;
    }

    @Override
    public void save(Object obj, String table) {
        try (StatementWrapper statement = prepareInsertStatement(obj, obj.getClass(), table);) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> addBatchAndReturnFail(List<T> ls, Class<?> clazz, String table) {
        int[] sucs = (int[]) RuntimeExceptionExec.exec(() -> {
            StatementWrapper statementWrapper = prepareInsertStatement(null, clazz, table);
            for (Object obj : ls) {
                setPrepareValue(statementWrapper.getPreparedStatement(), statementWrapper.getFields(), obj, true);
            }
            return statementWrapper.excuteBatch();
        });
        return ls;
    }

    @Override
    public void update(Map<String, Object> updates, Map<String, Object> ids, String table) {
        RuntimeExceptionExec.exec(() -> {
            StatementWrapper statement = prepareUpdateStatement(updates, ids, table);
            statement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(Map<String, Object> ids, String table) {
        RuntimeExceptionExec.exec(() -> {
            StatementWrapper statement = prepareDeleteIdsStatement(ids, table);
            return statement.executeUpdate();
        });
    }

    @Override
    public void addConverter(Class<?> clazz, String field, IConverter converter) {
        Map<String, IConverter> clazzConverters = converters.get(clazz);
        if (clazzConverters == null) {
            clazzConverters = new HashMap<>();
            converters.put(clazz, clazzConverters);
        }
        if (reflector.getField(clazz, field) != null) {
            clazzConverters.put(field, converter);
        }
    }

    private StatementWrapper prepareInsertStatement(Object obj, Class<?> clazz, String table) throws Exception {
        Field[] fields = reflector.getAllField(clazz);
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(table);
        sql.append(" (");
        String values = Arrays.stream(fields).map(field -> {
            sql.append(field.getName());
            sql.append(",");
            return "?";
        }).collect(Collectors.joining(",", "(", ")"));
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        sql.append(" values ");
        sql.append(values);
        System.out.println(sql);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        if (obj != null) {
            setPrepareValue(preparedStatement, fields, obj, false);
        }
        return StatementWrapper.parse(connection, preparedStatement, fields);
    }

    private StatementWrapper prepareUpdateStatement(Map<String, Object> updates, Map<String, Object> ids, String table) throws Exception {
        StringBuilder sql = new StringBuilder("update ");
        sql.append(table);
        sql.append(" set ");
        String values = updates.keySet().stream().map(key -> key + "=?").collect(Collectors.joining(","));
        sql.append(values);
        String where = ids.keySet().stream().map(key -> key + "=?").collect(Collectors.joining(" and "));
        sql.append(" where ");
        sql.append(where);
        System.out.println(sql);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        int next = setPrepareValue(preparedStatement, updates, 1);
        setPrepareValue(preparedStatement, ids, next);
        return StatementWrapper.parse(connection, preparedStatement, null);
    }

    private StatementWrapper prepareSelectIdsStatement(Map<String, Object> ids, String table) throws Exception {
        StringBuilder builder = new StringBuilder("select * from ");
        builder.append(table);
        builder.append(" where ");
        builder.append(ids.keySet().stream().map(id -> id + "=" + "?").collect(Collectors.joining(" and ")));
        System.out.println(builder);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());

        setPrepareValue(preparedStatement, ids, 1);
        return StatementWrapper.parse(connection, preparedStatement, null);
    }

    private StatementWrapper prepareDeleteIdsStatement(Map<String, Object> ids, String table) throws Exception {
        StringBuilder builder = new StringBuilder("delete from ");
        builder.append(table);
        builder.append(" where ");
        builder.append(ids.keySet().stream().map(id -> id + "=" + "?").collect(Collectors.joining(" and ")));
        System.out.println(builder);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());

        setPrepareValue(preparedStatement, ids, 1);
        return StatementWrapper.parse(connection, preparedStatement, null);
    }

    public void setPrepareValue(PreparedStatement statement, Field[] fields, Object obj, boolean batch) throws SQLException {
        Class<?> clazz = obj.getClass();
        for (int i = 0; i < fields.length; ++i) {
            Object value = reflector.getValue(fields[i], obj);
            System.out.println(value.getClass());
            //转换数据
            value = convertData(clazz, fields[i].getName(), value, false);
            if (value instanceof String) {
                statement.setString(i + 1, value.toString());
            } else if (value instanceof Date) {
                Date date = (Date) value;
                statement.setDate(i + 1, new java.sql.Date(date.getTime()));
            } else if (value instanceof Float) {
                statement.setFloat(i + 1, (float) value);
            } else if (value.getClass().isEnum()) {
                statement.setString(i + 1, ((Enum) value).name());
            } else if (value.getClass().isPrimitive()) {
                System.out.println(value.getClass().equals(float.class));
                if (value.getClass().equals(float.class)) {
                    statement.setFloat(i + 1, (float) value);
                }
            }
        }
        if (batch) {
            statement.addBatch();
        }
    }

    public Object convertData(Class<?> clazz, String field, Object value, boolean fromDataBase) {
        Map<String, IConverter> converterMap = converters.get(clazz);
        if (value != null && converterMap != null) {
            IConverter converter = converterMap.get(field);
            if (converter != null) {
                if (!fromDataBase) {
                    value = converter.convertDataFromObject(value);
                } else {
                    value = converter.convertDataFromDatabase(value);
                }
            }
        }
        return value;
    }

    public int setPrepareValue(PreparedStatement statement, Map<String, Object> params, int next) throws SQLException {
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        int pos = next;
        System.out.println(params);
        while (iterator.hasNext()) {
            statement.setString(pos++, iterator.next().getValue().toString());
        }
        return pos;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new JDBCTemplateImpl().prepareSelectIdsStatement(ParamsUtils.putAll(ParamsUtils.params("a", "b"), ParamsUtils.params("a", "b")), "table"));
    }

    static class A {
        String a;
        int b;
        int c;
    }
}
