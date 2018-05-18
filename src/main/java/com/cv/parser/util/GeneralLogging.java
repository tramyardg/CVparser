package com.cv.parser.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GeneralLogging {
	
	/**
	 * Setup the Logger to folder  log\logding.txt
	 * @throws IOException
	 */
	  static public void setup() throws IOException {

		    // Get the global logger to configure it
		    logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		    logger.setLevel(Level.INFO);
		    fileTxt = new FileHandler("log\\Logging.txt", true);
		    // Create txt Formatter
		    formatterTxt = new SimpleFormatter();
		    fileTxt.setFormatter(formatterTxt);
		    logger.addHandler(fileTxt);
		    
		    logger.info("START Logging");
	  }
	
	  /**
	   * Get the logger so it can be accessible in all modules
	   * @return
	   */
	  static public Logger getLogger() {
		  return logger;
	  }
	
	  private static Logger logger;
	  static private FileHandler fileTxt;
	  static private SimpleFormatter formatterTxt;	
	

}
