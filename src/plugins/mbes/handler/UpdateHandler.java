package plugins.mbes.handler;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.ServerStartedEvent;

public class UpdateHandler implements Listener {
	@EventHandler
	public void onStart(ServerStartedEvent e){
		e.getServer().shutdown();
	}

}
