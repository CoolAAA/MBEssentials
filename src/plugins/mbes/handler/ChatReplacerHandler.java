package plugins.mbes.handler;

import plugins.mbes.managers.ChatReplacer;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerChatEvent;

public class ChatReplacerHandler implements Listener {
	ChatReplacer words;
	
	public ChatReplacerHandler(ChatReplacer rp) {
		words = rp;
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent event){
		
		if(!words.getMap().isEmpty())
		{
			String msg = event.getMessage();
			String[] Rp = words.getMap().keySet().toArray(new String[0]);
			
			for(int a = 0; a < words.getMap().size(); a++)
			{
				if(!event.getPlayer().hasPermission("mbes.words." + Rp[a]) || !event.getPlayer().hasPermission(("mbes.words." + Rp[a]).toLowerCase()))
					msg.replaceAll("(?i)" + Rp[a],words.getMap().get(Rp[a]));
			}
			
			event.setMessage(msg);
		}
		
	}
}
