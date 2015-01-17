package plugins.mbes.misc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mbserver.api.PluginManager;
import plugins.mbes.Config;

public class Updater {

	public static void step1() {
		
		File cln = new File("plugins/MbEssentials/version.dat");
		if(cln.exists()) {
			try {
				Downloader.downloadFile("http://mbessentials.bl.ee/update/whatsnew.txt", "plugins/MbEssentials/Data/wn.dat");
			} catch (IOException e) {
					e.printStackTrace();
			}
			cln.delete();
		}
		
	}
	
	public static void step2(Config config, java.util.logging.Logger logger) {
		
		File f = new File("plugins/MbEssentials/Data/wn.dat");
		
		if(f.exists()) {
			
			if(config.isEnableChangeLog()){
				
				logger.info("What's new in this version:");
				logger.info("");
				
				try{
					
					  FileInputStream fstream = new FileInputStream("plugins/MbEssentials/Data/wn.dat");
					  DataInputStream in = new DataInputStream(fstream);
					  BufferedReader br = new BufferedReader(new InputStreamReader(in));
					  String strLine;
					  
					  while ((strLine = br.readLine()) != null)   {
						  
						  logger.info("");
						  logger.info(strLine);
						  logger.info("");
						  Thread.sleep(1000);
					  }
					  
					  in.close();
					  
				}
				
				catch (Exception e){
					
					  e.printStackTrace();
					  
				}
			    
			}
			
			boolean success = f.delete();
			    
			if (!success) {
				
			    logger.info("Changelog deletion failed - please delete it from: plugins/MbEssentials/Data/wn.dat");
			    
			}
			
		}
		
	}
	
	public static void step3(String pUrl, String vUrl, String wnUrl, String[] paths, float version, java.util.logging.Logger logger, PluginManager pm) {
		
		logger.info("Checking for an update to MbEssentials...");
		
		try {
			
			if(Downloader.checkUpdate(pUrl, vUrl, wnUrl, paths[0],paths[1],paths[2],version)){
				
				logger.info("MbEssentials was successfully updated  to the latest version!");
				Thread.sleep(2000);
				logger.warning("In order to apply the new MbEssentials update, you must restart the server");
				Thread.sleep(2000);
				logger.warning("When you start it up again, the latest version of MbEssentials will be installed!");
				Thread.sleep(2000);
					
			} else {
				
				logger.info("You are already running the latest version of MbEssentials!");
				
			}
			
		} catch (IOException e1) {
			
			try {
				
				File lFile = new File("plugins/MbEssentials/Download-Error-" + new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date()));
				lFile.createNewFile();
				
				PrintWriter err = new PrintWriter(lFile);
				e1.printStackTrace(err);
				
				logger.warning("Could not update MbEssentials. An error log was created at:" + lFile.getPath());
				
			} catch (FileNotFoundException e2) {
				
				e2.printStackTrace();
				logger.warning("Please report this error to the Space Walrus forums, and we will try and help you!");
				
			} catch (IOException e2) {
				
				e2.printStackTrace();
				logger.warning("Please report this error to the Space Walrus forums, and we will try and help you!");
				
			}
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		
	}
	
	public static void step4(java.util.logging.Logger logger) {
		
		if(new File("plugins/MbEssentials/Data/MbEssentials.jar").isFile()){
			
		    FileChannel sourceChannel = null;
		    FileChannel destChannel = null;
		    
		    File source = new File("plugins/MbEssentials/Data/MbEssentials.jar");
		    File dest = new File("plugins/MbEssentials.jar");
		    
		    dest.delete();
		    
		    try {
		    	
		        sourceChannel = new FileInputStream(source).getChannel();
		        destChannel = new FileOutputStream(dest).getChannel();
		        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		        
		    } catch (IOException e) {
		    	 
		    	e.printStackTrace();
				
			} finally{
				
		        try {
		        	
					sourceChannel.close();
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
					
				}
		        
		        try {
		        	
					destChannel.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
		    }
		

		    source.delete();
		    logger.warning("All MbEssentials Update Files Were Downloaded And Installed Successfully");
		    
		}
		
	}
	
}
