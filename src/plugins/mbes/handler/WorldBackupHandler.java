package plugins.mbes.handler;

import java.io.IOException;

import plugins.mbes.misc.WorldBackup;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.RunMode;
import com.mbserver.api.events.WorldSaveEvent;

public class WorldBackupHandler implements Listener{
	
	@EventHandler(concurrency = RunMode.THREADED)
	public void onWorldSave(WorldSaveEvent e){
		try {
			
			//So every 5 minutes (=WorldSaveEvent) there will be a backup of the World...
			//I do not think this is a very good idea
			//1. This will create noticable lagg.
			//2a. World folders are actually not so small, so it will very quickly fill de HDD/SSD.
			//2b. AeroShark's worldfolder = 500MB (imagine that being copied every 5 minutes o.O)
			//2c. 500MB*12*24= 144000MB per day = more or less 144,0 GB a day for AeroShark!!!
			//2d. And to be honest... AeroShark can only hold 100GB xD (That's why I backup it once a day (with a script)
			//3. I have no idea how long copying would take... but I guess it will hake long.
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
			// XXX Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			WorldBackup.Backup(e.getServer());
		} catch (IOException e1) {
			// XXX Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
