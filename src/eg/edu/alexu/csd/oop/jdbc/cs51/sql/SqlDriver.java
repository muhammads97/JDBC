package eg.edu.alexu.csd.oop.jdbc.cs51.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.logging.Logger;

public class SqlDriver implements Driver {

	private Properties info;

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return true;

	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if (url == null) {
			throw new UnsupportedOperationException();
		}
		File file = (File) info.get("path");
		if (file.exists()) {
			return new SqlConnection(file.getAbsolutePath());
		} else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return null;

	}

	/*-----------------------------Unused functions--------------------------------------------*/

	@Override
	public int getMajorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean jdbcCompliant() {
		throw new UnsupportedOperationException();
	}

}
