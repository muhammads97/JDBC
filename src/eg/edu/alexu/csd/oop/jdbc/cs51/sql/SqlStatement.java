package eg.edu.alexu.csd.oop.jdbc.cs51.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.jdbc.cs51.log.Logger;
import eg.edu.alexu.csd.oop.jdbc.cs51.parsers.ChooserParser;
import eg.edu.alexu.csd.oop.jdbc.cs51.parsers.FunctionChooserParser;

public class SqlStatement implements Statement {
	private Connection connection;
	private Queue<String> Batch;
	private int queryTimeOut;
	private Database database;
	private ChooserParser functionChooserParser;
	private ExecutorService executorService;

	public SqlStatement(Connection connection, Database database) {
		this.connection = connection;
		Batch = new LinkedList<String>();
		queryTimeOut = 1;
		this.database = database;
		functionChooserParser = new FunctionChooserParser();
		executorService=Executors.newSingleThreadExecutor();
		
	}
	
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void addBatch(String sql) throws SQLException {
		sql.trim().toLowerCase();
		Batch.add(sql);
		try {
			Logger.getInstance().info("added query : \"" + sql + "\" to batch");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void cancel() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clearBatch() throws SQLException {
		Batch.clear();
		try {
			Logger.getInstance().info("batch cleared");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}

	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void close() throws SQLException {
		// connection.close();
		executorService = null;
		Batch.clear();
		Batch = null;
		functionChooserParser = null;
		try {
			Logger.getInstance().info("Statement Closed");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}

	}

	@Override
	public void closeOnCompletion() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean execute(String sql) throws SQLException {
		sql.trim().toLowerCase();
		Object result=false;
		Future<Boolean>future=executorService.submit(new task(result,sql,functionChooserParser,database));
//		int parseReturn = functionChooserParser.getOutput(sql);
//
//		if (parseReturn == 1) { 
//			return (boolean)database.executeStructureQuery(sql);
//			
//		} else if (parseReturn == 2) {
//			database.executeUpdateQuery(sql);
//			return true;
//		} else if (parseReturn == 3) {
//			ResultSet rs =database.executeQuery(sql);
//			if(rs==null) {
//				return false;
//			}
//			return true;
//		} else {
//			throw new SQLException();
//		}
		try {
			result=future.get(queryTimeOut,TimeUnit.SECONDS);
			try {
				Logger.getInstance().info("query \"" + sql + "\" excuted");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
			}
			return (boolean)result;
		}catch(TimeoutException e) {
			try {
				Logger.getInstance().info("query \"" + sql + "\" want executed due to time limit exceeded");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
				// TODO Auto-generated catch block
			}
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			try {
				Logger.getInstance().info("query \"" + sql + "\" wasnt excuted due to inturruption");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
				// TODO Auto-generated catch block
			}
			throw new SQLException();
		} catch (ExecutionException e) {
			try {
				Logger.getInstance().info("query \"" + sql + "\" wasnt excuted due to wrong query");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
				// TODO Auto-generated catch block
			}
			throw new SQLException();
		}

	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int[] executeBatch() throws SQLException {
		int[] returnValues = new int[Batch.size()];
		int i = 0;
		while (!Batch.isEmpty()) {
			String Query = Batch.poll();
			Query.trim().toLowerCase();
			int parseReturn = functionChooserParser.getOutput(Query);
			if (parseReturn == 0) {
				throw new SQLException();
			} else if (parseReturn == 1) {
				boolean queryExecuted = (boolean) execute(Query);
				if (queryExecuted) {
					returnValues[i] = 1;
				} else {
					returnValues[i] = 0;
				}

			} else if (parseReturn == 2) {
				returnValues[i] = executeUpdate(Query);

			} else if (parseReturn == 3) {
				executeQuery(Query);
				returnValues[i] = 0;

			}
			i++;
		}
		try {
			Logger.getInstance().info("batch executed");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		return returnValues;

	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		sql = sql.trim().toLowerCase();
		try {
			Logger.getInstance().info("query \""+ sql + "\" executed");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		return database.executeQuery(sql);

	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		sql.trim().toLowerCase();
		int result = (int) database.executeUpdateQuery(sql);
		try {
			Logger.getInstance().info("query update \""+ sql + "\" executed");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		return result;
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Logger.getInstance().info("connection returned");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		return connection;

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
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getMaxRows() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean getMoreResults() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getQueryTimeout() throws SQLException {
		try {
			Logger.getInstance().info("query time out returned");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		return queryTimeOut;

	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getResultSetType() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getUpdateCount() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isPoolable() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setCursorName(String name) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
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
	public void setMaxFieldSize(int max) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		try {
			Logger.getInstance().info("query time out set to " + seconds + " seconds");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		queryTimeOut = seconds;

	}

}
 class task implements Callable<Boolean>{
	 private Object object;
	private  String query;
	private ChooserParser functionChooserParser;
	private Database database;
	 public  task(Object object,String query,ChooserParser functionChooserParser ,Database database) {
		// TODO Auto-generated constructor stub
		 this.object=object;
		 this.query=query;
		 this.functionChooserParser=functionChooserParser;
		 this.database=database;
	}

	@Override
	public Boolean call() throws Exception {
		query=query.trim().toLowerCase();
		int parseReturn = functionChooserParser.getOutput(query);

		if (parseReturn == 1) {
			object=(boolean)database.executeStructureQuery(query);
			
		} else if (parseReturn == 2) {
			database.executeUpdateQuery(query);
			object=true;
		} else if (parseReturn == 3) {
			ResultSet rs =database.executeQuery(query);
			if(rs==null) {
			object=false;
			}else {
				object= true;
			}
			
		} else {
			throw new SQLException();
		}
		return (boolean)object;
	}
	
	
	
	
	
	
	
}
