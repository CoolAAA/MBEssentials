package plugins.mbes.misc;

import java.io.File;

public class CreateFiles {

	public static void create(boolean enableUpdater) {
		
		String[] fileNames = {"plugins/MbEssentials","logs/MBE_Logs","logs/MBE_Logs/Command_Logs","logs/MBE_Logs/Death_Logs"
				,"logs/MBE_Logs/PvP_Logs","logs/MBE_Logs/Place_Logs","logs/MBE_Logs/Break_Logs",};
		
		File file;
		
		for(String e : fileNames)
		{
			file = new File(e);
			
			if(!file.exists())
				file.mkdir();
		}
		
		if(enableUpdater == true){
			if(!new File("plugins/MbEssentials/Data").isDirectory()){
				new File("plugins/MbEssentials/Data").mkdirs();
			}
		}
		
		else{
			new File("plugins/MbEssentials/Data").delete();
		}
		
	}
	
}
