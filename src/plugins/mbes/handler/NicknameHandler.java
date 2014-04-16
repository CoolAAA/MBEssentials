package plugins.mbes.handler;

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
    	String sender = name.getDisplayName();
        String message = player.getMessage();
        String nickname = this.config.getPlayerNickname(name);
        
        if (nickname == null){
        player.overrideFormat(sender + ": " + message);
        }
        else {
        	 
        player.overrideFormat(config.getNicknamePrefix() + nickname + ": " + message);
        }
        
        }		
	}
    
