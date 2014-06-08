package plugins.mbes.handler;


import java.io.IOException;
import plugins.mbes.misc.Downloader;
import com.mbserver.api.Server;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.RunMode;
import com.mbserver.api.events.ServerStartedEvent;


public class UpdateNotifyHandler implements Listener{
	
	
	private float version;
	private String vUrl;
	private String paths[];
	
	public UpdateNotifyHandler(float version, String paths[], String vUrl) {
		this.version = version;
		this.paths = paths;
		this.vUrl = vUrl;
	}
	
	@EventHandler(concurrency = RunMode.THREADED)
	public void onPonyDomination(ServerStartedEvent e){
		Server s = e.getServer();
		while (true) {
			try {
				Thread.sleep(180000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			try {
				if(Downloader.checkUpdateNoDownload("http://mbessentials.bl.ee/update/version.txt", paths[1], version)){
					s.getLogger().warning("There is an update avialable for MbEssentials!");
					s.getLogger().warning("It is highly recommended that you restart you server to get the update!");
					s.getLogger().warning("Updating takes less than a minute, and is very easy!");
					
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			{
				//Do nothing, MbEssentials is up to date!
			}

		}
		
		
	}
		
}
