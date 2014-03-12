package plugins.mbes.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import plugins.mbes.managers.LogManager;



public class Logger {
	private BufferedWriter out;
	private int ID;
	
	
	
	/**
	 * @param folder The folder in which the log will be stored
	 *Example new Logger("logs\\MyLogFolder");
	 */
	public Logger(String folder) {
		try {
			out = new BufferedWriter(new FileWriter(folder + LogManager.getExactDate() + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * You should not use this method
	 */
	public BufferedWriter getWriter(){
		return out;
	}
	
	/**
	 * You should not use this method
	 * When you attach this logger to a LogManager it will set the ID
	 */
	public void setId(int id){
		ID = id;
	}
	
	
	/**
	 * @return The ID of the logger. Used when logging
	 */
	public int getId() {
		return ID;
	}
	
	/**
	 * You should not use this method
	 */
	public void close() throws IOException{
		out.close();
	}
	
	 @Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Logger) || obj == null) return false;
		
		if(((Logger)obj).getId() == this.getId())
			return true;
		
		return false;
	}
}
