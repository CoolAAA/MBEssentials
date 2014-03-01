package plugins.mbes.handler;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerChatEvent;

public class MuteHandler implements Listener{
	
	@EventHandler
	public void onChat(PlayerChatEvent e){
		if(e.getPlayer().getMetaData("MBES:MUTEALL",false))
			e.setCancelled(true);
	}
	
}
