package eg.edu.alexu.csd.oop.jdbc.cs51.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.jdbc.cs51.log.Logger;


public class SqlResultset implements ResultSet {
    private ResultSetMetaData metaData;
    private Statement statement;
    private Object[][] rows;
    private int currentRow;

    public SqlResultset(Object[][] rows, ResultSetMetaData metaData) {
        this.metaData = metaData;
        this.rows = rows;
        currentRow = 0;
    }

    public SqlResultset(SqlResultset resultSet, Statement statement) {
        this.statement = statement;
        this.metaData = resultSet.metaData;
        this.rows = resultSet.rows;
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean absolute(int row) throws SQLException {
        if (row > 0 && row < rows.length) {
            currentRow = row;
            try {
    			Logger.getInstance().info("curser set at row: "+ row);
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return true;
        } else if (row < 0 && Math.abs(row) < rows.length) {
            currentRow = rows.length - Math.abs(row) + 1;
            try {
    			Logger.getInstance().info("curser set at row: "+ currentRow);
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return true;
        } else {
            currentRow = 0;
            try {
    			Logger.getInstance().info("out of range row number, curser set at row zero");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return false;
        }

    }

    @Override
    public void afterLast() throws SQLException {
        try {
			Logger.getInstance().info("curser set at row: "+ rows.length + " - after last");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        currentRow = rows.length + 1;
    }

    @Override
    public void beforeFirst() throws SQLException {
    	try {
			Logger.getInstance().info("curser set at row zero - before first");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        currentRow = 0;
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void close() throws SQLException {
    	try {
			Logger.getInstance().info("ResultSet closed");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        this.metaData = null;
        this.rows = null;
        currentRow = 0;

    }

    @Override
    public void deleteRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            if (metaData.getColumnLabel(i).equals(columnLabel)) {
            	try {
        			Logger.getInstance().info("Found column: "+columnLabel+" at index "+i);
        		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
        			// TODO Auto-generated catch block
        		}
                return i;
            }
        }
        try {
			Logger.getInstance().warnning("couldnot find "+columnLabel);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        throw new SQLException();
    }

    @Override
    public boolean first() throws SQLException {
        if (rows.length > 0) {
            currentRow = 1;
            try {
    			Logger.getInstance().info("Got first");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return true;
        }
        try {
			Logger.getInstance().info("Empty resultset");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return false;

    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getConcurrency() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public String getCursorName() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        try {
            if (rows[currentRow - 1][columnIndex - 1] == null) {
            	try {
        			Logger.getInstance().warnning("Wrong column index");
        		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
        			// TODO Auto-generated catch block
        		}
                return 0;
            }
                try {
        			Logger.getInstance().info("Value at column index "+columnIndex+" = "+ (int) rows[currentRow - 1][columnIndex - 1]);
        		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
        			// TODO Auto-generated catch block
        		}
            return (int) rows[currentRow - 1][columnIndex - 1];
        } catch (Exception e) {
        	try {
    			Logger.getInstance().warnning("couldnot get integer. i gusess it's string");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
    			// TODO Auto-generated catch block
    		}
            throw new SQLException();
        }
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            if (metaData.getColumnLabel(i).equals(columnLabel)) {
                try {
                    if (rows[currentRow - 1][i - 1] == null) {
                    	try {
                			Logger.getInstance().warnning("Wrong column label");
                		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
                			// TODO Auto-generated catch block
                		}
                        return 0;
                    }
                        try {
                			Logger.getInstance().info("Value at column label "+columnLabel+" = "+ (int) rows[currentRow - 1][i - 1]);
                		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
                			// TODO Auto-generated catch block
                		}
                    return (int) rows[currentRow - 1][i - 1];
                } catch (Exception e) {
                    throw new SQLException();
                }
            }
        }
        try {
			Logger.getInstance().warnning("couldnot get integer. i gusess it's string");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        throw new SQLException();

    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
    	try {
			Logger.getInstance().info("metaData returned");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return metaData;
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        try {
        	try {
    			Logger.getInstance().info("Value at column index " + columnIndex + " = "+rows[currentRow - 1][columnIndex - 1]);
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return rows[currentRow - 1][columnIndex - 1];
        } catch (Exception e) {
        	try {
    			Logger.getInstance().warnning("Wrong index");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
    			// TODO Auto-generated catch block
    		}
            throw new SQLException();
        }

    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Statement getStatement() throws SQLException {
    	try {
			Logger.getInstance().info("return statement");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return statement;
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        try {
            if (rows[currentRow - 1][columnIndex - 1] == null) {
            	try {
        			Logger.getInstance().warnning("Wrong index at getString");
        		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
        			// TODO Auto-generated catch block
        		}
                return null;
            }
                try {
        			Logger.getInstance().info("Value at column index " + columnIndex + " = "+rows[currentRow - 1][columnIndex - 1]);
        		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
        			// TODO Auto-generated catch block
        		}
            return (String) rows[currentRow - 1][columnIndex - 1];
        } catch (Exception e) {
        	try {
    			Logger.getInstance().warnning("Suppose to be string but integer");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
    			// TODO Auto-generated catch block
    		}
            throw new SQLException();
        }
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            if (metaData.getColumnLabel(i).equals(columnLabel)) {
                try {
                    if (rows[currentRow - 1][i - 1] == null) {
                    	try {
                			Logger.getInstance().warnning("Wrong index at getString");
                		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
                			// TODO Auto-generated catch block
                		}
                    	return null;
                    }
                    try {
            			Logger.getInstance().info("Value at column label " + columnLabel + " = "+rows[currentRow - 1][i - 1]);
            		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            			// TODO Auto-generated catch block
            		}
                    return (String) rows[currentRow - 1][i - 1];
                } catch (Exception e) {
                	try {
            			Logger.getInstance().warnning("Suppose to be string but integer");
            		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
            			// TODO Auto-generated catch block
            		}
                    throw new SQLException();
                }
            }
        }
        try {
			Logger.getInstance().warnning("wrong column label at get string");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        throw new SQLException();
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public int getType() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public InputStream getUnicodeStream(String columnLabel)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void insertRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean isAfterLast() throws SQLException {
        if(currentRow > rows.length) {
        	try {
    			Logger.getInstance().info("after last true");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
        	return true;
        }
        try {
			Logger.getInstance().info("after last false");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        
        return false;
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        if(currentRow == 0) {
        	try {
    			Logger.getInstance().info("before first true");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
        	
        	return true;
        }
        try {
			Logger.getInstance().info("before first false");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return false;
    }

    @Override
    public boolean isClosed() throws SQLException {
        if(rows == null) {
        	
        	try {
    			Logger.getInstance().info("ResultSet closed");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
        	return true;
        }
        try {
			Logger.getInstance().warnning("ResultSet couldnot close");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return false;
    }

    @Override
    public boolean isFirst() throws SQLException {
        if(currentRow == 1) {
        	try {
    			Logger.getInstance().info("curser at first");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
        	return true;
        }
        try {
			Logger.getInstance().info("curser isnot at first");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return false;
    }

    @Override
    public boolean isLast() throws SQLException {
        if(currentRow == rows.length) {
        	try {
    			Logger.getInstance().info("curser at last");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
        	return true;
        }
        try {
			Logger.getInstance().info("curser isnot at last");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return false;
    }

    @Override
    public boolean last() throws SQLException {
        if (rows.length > 0) {
            currentRow = rows.length;
            try {
    			Logger.getInstance().info("set curser at last");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return true;
        }
        try {
			Logger.getInstance().warnning("curser couldnot set at last");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
        return false;
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean next() throws SQLException {
        if(currentRow < rows.length) {
            currentRow++;
            try {
    			Logger.getInstance().info("next index");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return true;
        } else {
            currentRow = rows.length + 1;
            try {
    			Logger.getInstance().info("has no next");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return false;
        }

    }

    @Override
    public boolean previous() throws SQLException {
        if(currentRow > 1) {
            currentRow--;
            try {
    			Logger.getInstance().info("previous index");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return true;
        } else {
            currentRow = 0;
            try {
    			Logger.getInstance().info("has no previous");
    		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
    			// TODO Auto-generated catch block
    		}
            return false;
        }
    }

    @Override
    public void refreshRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean relative(int rows) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x,
            long length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x,
            int length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x,
            long length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream,
            long length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream,
            long length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBoolean(String columnLabel, boolean x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader,
            int length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader,
            long length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateClob(String columnLabel, Reader reader)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader,
            long length) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNString(int columnIndex, String nString)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNString(String columnLabel, String nString)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateRow() throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x)
            throws SQLException {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new UnsupportedOperationException();
    }

}
