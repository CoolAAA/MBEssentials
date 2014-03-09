package plugins.mbes.managers;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import plugins.mbes.misc.Logger;

public class LogManager {
	private ArrayList<Logger>loggers = new ArrayList<Logger>();
	
	public void attachLogger(Logger log){
		log.setId(loggers.size());
		loggers.add(log);
	}
	
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
	
	public boolean writeLog(String write,int ID) throws IOException{
		
		if(loggers.size() - 1 < ID)
			return false;
		
		Logger temp = loggers.get(ID);
		
		temp.getWriter().write(String.format("[%s] %s",LogManager.getDate(),write));
		temp.getWriter().newLine();
		temp.getWriter().newLine();
		return true;
	}
	
	public static String getExactDate(){
		return new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date());
	}
	
	public static String getDate(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	public void closeAll() throws IOException{
		for(int a = 0; a < loggers.size();a++)
		{
			loggers.get(a).close();
		}
	}
}
