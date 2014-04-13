package plugins.mbes.handler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import plugins.mbes.Config;
import plugins.mbes.misc.WorldBackup;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.RunMode;
import com.mbserver.api.events.WorldSaveEvent;

public class WorldBackupHandler implements Listener{
	private Config config;
	public WorldBackupHandler(Config config){
		this.config=config;
		
	}
	
	
	@EventHandler(concurrency = RunMode.THREADED)
	public void onWorldSave(WorldSaveEvent e){
			int freq = config.getAmountOfBackupsPerDay();
			if (freq<1){freq=1;} //set minimum
			int interval = 86200 / freq; //will round off
			int aInterval=interval;
			Date time1= new Date();
			Date currentTime= new Date();
			Date currentTimePlusFiveMins= new Date();
			currentTimePlusFiveMins.setTime((currentTime.getTime()+5000));
			
		for(int x=1;x<(freq+1);x++){
			
			int h=(aInterval-(aInterval%3600))/3600;
			int m=((aInterval-(h*3600))-((aInterval-(h*3600))%60))/60;
			int s=aInterval-(h*2400)-(m*60);
			String timestring = String.format("%02d:%02d:%02d", h,m,s);
			
			try {
			time1 = new SimpleDateFormat("HH:mm:ss").parse(timestring);	
			} catch (ParseException e2) {	
			e2.printStackTrace();
			}
			aInterval=aInterval+interval;	
			if(time1.after(currentTime)&&time1.before(currentTimePlusFiveMins)){
		////	
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
		////
		}
		}
		
		
		
	}
}
