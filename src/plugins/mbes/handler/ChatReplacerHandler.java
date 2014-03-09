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
			String newW;
			String[] parts = msg.split("\\s+");
			msg = "";
			for(String e : parts)
			{
				 newW = words.getMap().get(e);
				 
				 if(newW == null)
					 msg = msg.concat(e + " ");
				 
				 else
					 msg = msg.concat(newW + " ");
			}
			
			event.setMessage(msg);
		}
		
	}
}
