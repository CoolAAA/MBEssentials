package plugins.mbes.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import plugins.mbes.managers.LogManager;



public class Logger {
	private BufferedWriter out;
	private int ID;
	
	public Logger(String folder) {
		try {
			out = new BufferedWriter(new FileWriter(folder + LogManager.getExactDate() + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedWriter getWriter(){
		return out;
	}
	
	public void setId(int id){
		ID = id;
	}
	
	public int getId() {
		return ID;
	}
	
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
