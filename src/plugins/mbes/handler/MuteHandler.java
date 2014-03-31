package plugins.mbes.handler;

import java.util.HashMap;

import plugins.mbes.misc.Keys;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerChatEvent;

public class MuteHandler implements Listener{
	
	HashMap<String,Object>data;
	
	public MuteHandler(HashMap<String,Object> map) {
		data = map;
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e){
		if(data.containsKey(e.getPlayer().getLoginName() + Keys.mute_key))
		{
			e.getPlayer().sendMessage("You have been muted from the chat");
			e.setCancelled(true);
		}
	}
	
}
