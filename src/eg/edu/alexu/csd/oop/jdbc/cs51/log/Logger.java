package eg.edu.alexu.csd.oop.jdbc.cs51.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.server.LoaderHandler;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

public class Logger {

	private static Logger log;
	private java.util.logging.Logger logger;
	private Properties preferences ;
	private FileInputStream configFile;
	private FileHandler handler;
	private Formatter f;

	private Logger() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		logger = java.util.logging.Logger.getLogger(Logger.class.getName());
		logger.setLevel(Level.ALL);
		preferences = new Properties();
		 configFile = new FileInputStream("mylogging.properties");
        preferences.load(configFile);
        LogManager.getLogManager().readConfiguration(configFile);
         handler = new FileHandler("default.log", true);
         logger.addHandler(handler);
          f =(Formatter) Class.forName(preferences.getProperty("java.util.logging.FileHandler.formatter")).newInstance();
         handler.setFormatter(f);
	}

	public static Logger getInstance() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (log == null) {
			return new Logger();
		}
		return log;

	}
	
	
	public void warnning(String msg) {
		if(preferences.get("Log").equals("true"))
		logger.log(Level.WARNING, msg);
		
	}
	
	public void info(String msg) {
		if(preferences.get("Log").equals("true"))
		logger.log(Level.INFO, msg);
		
	}

}
