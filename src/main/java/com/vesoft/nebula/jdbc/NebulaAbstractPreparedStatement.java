/* Copyright (c) 2020 vesoft inc. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License.
 */

package com.vesoft.nebula.jdbc;

import com.vesoft.nebula.jdbc.impl.NebulaConnection;
import com.vesoft.nebula.jdbc.impl.NebulaStatement;
import com.vesoft.nebula.jdbc.utils.ExceptionBuilder;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NebulaAbstractPreparedStatement extends NebulaStatement implements PreparedStatement{

    protected String                  rawNGQL;
    protected String                  nGql;
    protected HashMap<Object, Object> parameters;
    protected int                     parametersNumber;

    public NebulaAbstractPreparedStatement(NebulaConnection connection, String rawNGQL) {
        super(connection);
        this.rawNGQL = rawNGQL;
        this.parametersNumber = namedParameterCount(rawNGQL);
        this.parameters = new HashMap<>(this.parametersNumber);
    }

    protected void checkParamsNumber(int parameterIndex) throws SQLException {
        if (parameterIndex > this.parametersNumber) {
            throw new SQLException("ParameterIndex does not correspond to a parameter marker in the nGQL statement.");
        }
    }

    protected int namedParameterCount(String rawNGQL) {
        int max = 0;
        String regex = "\\?(?=[^\"]*(?:\"[^\"]*\"[^\"]*)*$)";
        Matcher matcher = Pattern.compile(regex).matcher(rawNGQL);
        while (matcher.find()) {
            max++;
        }
        return max;
    }

    protected void insertParameter(int parameterIndex, Object obj) throws SQLException {
        this.checkClosed();
        this.checkParamsNumber(parameterIndex);
        this.parameters.put(parameterIndex, obj);
    }


    @Override
    public ResultSetMetaData getMetaData() throws SQLException {

        if (this.currentResultSet != null) {
            return currentResultSet.getMetaData();
        }
        return null;
    }

    @Override
    public void clearParameters() throws SQLException {
        this.checkClosed();
        this.parameters.clear();
    }

    public HashMap<Object, Object> getParameters() {
        return parameters;
    }

    public int getParametersNumber() {
        return parametersNumber;
    }

    @Override public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLException("Method execute(String, int) cannot be called on PreparedStatement.");
    }

    @Override public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLException("Method execute(String, int[]) cannot be called on PreparedStatement.");
    }

    @Override public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new SQLException("Method execute(String, String[]) cannot be called on PreparedStatement.");
    }

    @Override public ResultSet executeQuery(String sql) throws SQLException {
        throw new SQLException("Method executeQuery(String) cannot be called on PreparedStatement.");
    }

    @Override public int executeUpdate(String sql) throws SQLException {
        throw new SQLException("Method executeUpdate(String) cannot be called on PreparedStatement.");
    }

    @Override public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLException("Method executeUpdate(String, int) cannot be called on PreparedStatement.");
    }

    @Override public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLException("Method executeUpdate(String, int[]) cannot be called on PreparedStatement.");
    }

    @Override public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new SQLException("Method executeUpdate(String, String[]) cannot be called on PreparedStatement.");
    }

    /**
     * -----------------------Not implement yet-------------------------
     *
     * */

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void addBatch() throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        throw  ExceptionBuilder.buildUnsupportedOperationException();
    }
}
