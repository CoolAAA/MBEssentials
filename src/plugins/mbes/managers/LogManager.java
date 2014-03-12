package plugins.mbes.managers;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import plugins.mbes.misc.Logger;

/**
 * Class to make logs and to log to them.
 *
 */
public class LogManager {
	private ArrayList<Logger>loggers = new ArrayList<Logger>();
	
	
	
	/**
	 * @param log - The loggger you are going to attach.
	 */
	public void attachLogger(Logger log){
		log.setId(loggers.size());
		loggers.add(log);
	}
	
	
	
	/**
	 * Writes a string to a log
	 * When you log something the log will make a new line 
	 * the date is also logged when you log something
	 * 
	 * @param write - The string to write to the log
	 * @param log - the logger to use
	 * @return true if the logging was successful false if the logger was not found
	 * @throws IOException - if a IO error occurs
	 */
	public boolean writeLog(String write,Logger log) throws IOException{
		int ind = loggers.indexOf(log);
		
		if(ind == -1)
			return false;
		
		Logger temp = loggers.get(ind);
		
		temp.getWriter().write(String.format("[%s] %s",LogManager.getDate(),write));
		temp.getWriter().newLine();
		temp.getWriter().flush();
		
		return true;
	}
	
	
	
	/**
	 * Writes a string to a log
	 * When you log something the log will make a new line 
	 * The date is also logged when you log something
	 * 
	 * @param write - String to write
	 * @param ID - the ID of the logger you can use Logger.getId() to get the loggers id.
	 * @return true if the logging was successful false if a logger with that id was not found
	 * @throws IOException  an IO error occurs
	 */
	public boolean writeLog(String write,int ID) throws IOException{
		
		if(loggers.size() - 1 < ID)
			return false;
		
		Logger temp = loggers.get(ID);
		
		temp.getWriter().write(String.format("[%s] %s",LogManager.getDate(),write));
		temp.getWriter().newLine();
		temp.getWriter().newLine();
		return true;
	}
	
	
	/**
	 * @return The date in this format - Day_Month_year_Hour_minute_second
	 */
	public static String getExactDate(){
		return new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date());
	}
	
	
	
	/**
	 * @return The date in this format - Hour:minute:second
	 */
	public static String getDate(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	
	/**
	 * This method should not be used as it will close all loggers!
	 * @throws IOException
	 */
	public void closeAll() throws IOException{
		for(int a = 0; a < loggers.size();a++)
		{
			loggers.get(a).close();
		}
	}
}
