package plugins.mbes.handler;

import plugins.mbes.misc.Keys;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.EventPriority;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerDeathEvent;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.World;

public class KillHandler implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e){
		if(e.getPlayer().getMetaData(Keys.kill_key,false))
		{
			Location loc = (Location) e.getPlayer().getMetaData(Keys.kill_location_key,null);
			
			if(loc == null)
				return;
			
			World world = e.getLocation().getWorld();
			
			world.destroy(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
			world.destroy(loc.getBlockX(),loc.getBlockY() + 1,loc.getBlockZ());
			
			e.getPlayer().removeMetaData(Keys.kill_key);
			e.getPlayer().removeMetaData(Keys.kill_location_key);
		}
	}

}
