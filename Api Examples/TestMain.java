package test.test;

import java.io.IOException;
import plugins.mbes.managers.LogManager;
import plugins.mbes.misc.Logger;
import plugins.mbes.MBEPlugin;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;


@Manifest(name = "Test",dependencies = "MBEssentials")
public class TestMain extends MBServerPlugin{
	
	@Override
	public void onEnable() {
		
		//Get the MBEssentials plugin reference remeber to cast to it to MBEPlugin
		MBEPlugin Mbes = (MBEPlugin) this.getPluginManager().getPlugin("MBEssentials");
		
		//Get the LogManager from the plugin
		LogManager log = Mbes.getLogManager();
		
		//Make a logger the string tells the logmanager where to create the log
		Logger myLog = new Logger("plugins\\"); 
		// You can do this Logger myLog = new Logger(new File("plugins\\mylog.txt")); it will overide the default name      
		
		//Attach logger to the logManager
		log.attachLogger(myLog);
		try {
			
			//write to the log
			log.writeLog("Im logging this",myLog,false); //Its false so it can log it using the default formatting
			// You can also do this log.writeLog("Im logging this",myLog.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
