package plugins.mbes.handler;

import plugins.mbes.misc.Keys;

import com.mbserver.api.Server;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PostPlayerLoginEvent;
import com.mbserver.api.events.RunMode;
import com.mbserver.api.game.Player;

public class PMBlockHandler implements Listener{
	
	@EventHandler(concurrency = RunMode.THREADED)
	public void onLogin(PostPlayerLoginEvent e){
		Server s = e.getServer();
		
		for(Player player : s.getPlayers())
		{
			if(player.getMetaData(Keys.pm_blockall_key,false))
			{
				player.setMetaData(Keys.pm_blocked_key + e.getPlayer().getDisplayName(),true);
				player.sendMessage(e.getPlayer().getDisplayName() + " has been blocked from sending you PM's");
			}
		}
	}
}
