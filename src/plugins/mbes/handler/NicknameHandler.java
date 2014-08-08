package plugins.mbes.handler;

import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerChatEvent;

import plugins.mbes.Config;

import com.mbserver.api.game.Player;


public class NicknameHandler implements Listener {
    private Config config;

    public NicknameHandler(Config config) {
        this.config = config;
    }

    @EventHandler
    public void setMessage( PlayerChatEvent player ) {
        
    	Player name = player.getPlayer();
    	String nickname = this.config.getPlayerNickname(name);
    	if (nickname == null){
            //Do nothing
        }
    	else {
            String message = player.getMessage();
            ChatColor color = player.getServer().getPermissionsHandler().getColor(name);
    		player.setColor(color);
        	player.overrideFormat(color + config.getNicknamePrefix() + nickname + ": " + ChatColor.WHITE + message);
        	
        }
    	
        }		
	}
    
