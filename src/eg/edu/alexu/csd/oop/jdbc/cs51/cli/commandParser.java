package eg.edu.alexu.csd.oop.jdbc.cs51.cli;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.jdbc.cs51.parsers.FunctionChooserParser;
import eg.edu.alexu.csd.oop.jdbc.cs51.sql.SqlConnection;
import eg.edu.alexu.csd.oop.jdbc.cs51.sql.SqlDriver;

public class commandParser {
    private static final String CLI_NAME = "jdbc";
    private static final String CONNECT_COMMAND = CLI_NAME + " +connect +(.+)";
    private static final String EXECUTE_COMMAND = CLI_NAME + " +execute +(.+)";
    private static final String CLOSE_COMMAND = CLI_NAME + " +close *";
    private Driver sqlDriver;
    private Connection connection;
    private Statement statement;
    
    public commandParser(Driver sqlDriver) {
        this.sqlDriver = sqlDriver;
    }
    
    public void excute(String command) {
        command = command.toLowerCase();
        Pattern p1 = Pattern.compile(CONNECT_COMMAND);
        Pattern p2 = Pattern.compile(CLOSE_COMMAND);
        Pattern p3 = Pattern.compile(EXECUTE_COMMAND);
        Matcher m1 = p1.matcher(command);
        Matcher m2 = p2.matcher(command);
        Matcher m3 = p3.matcher(command);
        if(m1.matches()) {
            String path = m1.group(1);
            Properties info = new Properties();
            File dbDir = new File(path);
            info.put("path", dbDir.getAbsoluteFile());
            try {
                connection = sqlDriver.connect("jdbc:xmldb://localhost", info);
                statement = connection.createStatement();
                System.out.println("connected!");
            } catch (SQLException e) {
                System.out.println("invalid PATH.");
            }
        } else if (m2.matches()) {
            if(connection != null) {
                try {
                    connection.close();
                    connection = null;
                    System.out.println("closed!");
                } catch (SQLException e) {
                }
            } else {
                System.out.println("no connection found.");
            }
            if(statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (SQLException e) {
                }
            }
        } else if (m3.matches()) {
            String query = m3.group(1);
            if(connection != null && statement != null) {
                FunctionChooserParser f = new FunctionChooserParser();
                int q = 0;
                try {
                     q = f.getOutput(query);
                } catch (SQLException e) {
                }
                
                switch(q) {
                case 1:
                    executeStructure(query);
                    break;
                case 2:
                    executeUpdate(query);
                    break;
                case 3:
                    executeSelect(query);
                    break;
                default:
                    System.out.println("wrong query");
                    break;
                }
            } else {
                System.out.println("no connection found");
            }
        } else {
            System.out.println("couldn't find command : " + command);
        }
    }
    
    private void executeStructure(String query) {
        try {
            statement.execute(query);
            System.out.println("Done!");
        } catch (SQLException e) {
            System.out.println("Wrong Query");
        }
    }
    private void executeUpdate(String query) {
        try {
            System.out.println("Updated : " + statement.executeUpdate(query));
        } catch (SQLException e) {
            System.out.println("Wrong Query");
        }
    }
    private void executeSelect(String query) {
        try {
            ResultSet rs = statement.executeQuery(query);
            System.out.println("results: ");
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                System.out.print("Row: ");
                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getObject(i) + "  ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Wrong Query");
        }
    }
}
