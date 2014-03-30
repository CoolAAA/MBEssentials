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
