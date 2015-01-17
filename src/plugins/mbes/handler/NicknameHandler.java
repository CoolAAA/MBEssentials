package plugins.mbes.handler;

import com.mbserver.api.Server;
import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerChatEvent;
import com.mbserver.api.events.EventPriority;

import plugins.mbes.Config;

import com.mbserver.api.game.Player;


public class NicknameHandler implements Listener {
    private Config config;

    public NicknameHandler(Config config) {
        this.config = config;
    }

	// player = the event :| WHY?!\
	// Supports usage for other plugins as well
    @EventHandler(priority = EventPriority.LOWEST)
    public void setMessage( PlayerChatEvent player ) {
        final String message = player.getMessage();
        final String alterTag = "MBES_ALTER";
        if(!message.contains(alterTag)){
            player.setCancelled(true);	
            
            final Player name = player.getPlayer();
    	    final String nickname = this.config.getPlayerNickname(name);
    	    final Server server = player.getServer();
    	    
    	    if (nickname == null){
            //Do nothing
            player.setCancelled(false);
            //^ this should fix it :)
            return;
            }
            
            ChatColor color = player.getServer().getPermissionsHandler().getColor(name);
    	    //player.setColor(color);
            //player.setMessage(color + config.getNicknamePrefix() + nickname + ": " + ChatColor.WHITE + message);
        
            PlayerChatEvent newEvent = new PlayerChatEvent(name, message + alterTag, ChatColor.WHITE);
	    server.getPluginManager().triggerEvent(newEvent);
	    if(newEvent.isCancelled()){
		//boo :(
	    }else{
		server.broadcast(color + config.getNicknamePrefix() + nickname + ": " + ChatColor.WHITE + message.replace(alterTag,""));
	    }
        
        
        }
    }		
}
