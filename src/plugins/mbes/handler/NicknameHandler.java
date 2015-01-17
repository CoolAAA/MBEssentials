package plugins.mbes.handler;

import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerChatEvent;
import plugins.mbes.Config;



public class NicknameHandler implements Listener {
    private Config config;

    public NicknameHandler(Config config) {
        this.config = config;
    }

    @EventHandler
    public void setMessage( PlayerChatEvent player ) {
        
    	final String message = player.getMessage();
        
        if(!this.config.getPlayerNickname(player.getPlayer()).equals("")) { // If the player has a nickname
        	
        	player.setCancelled(true);
        	ChatColor color = player.getServer().getPermissionsHandler().getColor(player.getPlayer());
        	player.getServer().broadcast(color + config.getNicknamePrefix() + this.config.getPlayerNickname(player.getPlayer()) + ": " + ChatColor.WHITE + message);
        	
        }
       
    }
    
}
