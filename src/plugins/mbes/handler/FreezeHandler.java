package plugins.mbes.handler;

import plugins.mbes.managers.FreezeManager;

import com.mbserver.api.events.BlockEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerItemPickupEvent;
import com.mbserver.api.events.PlayerMoveEvent;
import com.mbserver.api.events.PlayerPvpEvent;
import com.mbserver.api.events.BlockInteractEvent;
import com.mbserver.api.events.PlayerTeleportEvent;

public class FreezeHandler implements Listener{
	
	private FreezeManager man;
	
	public FreezeHandler(FreezeManager e) {
		man = e;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(e instanceof PlayerTeleportEvent)
			return;
	}
	
	@EventHandler
	public void onBlock(BlockEvent e){
		if(man.isFrozen())
			if(man.isFrozen(e.getPlayer().getLoginName()))
				e.setCancelled(true);
	}
	
	@EventHandler
	public void onPvP(PlayerPvpEvent e){
		if(man.isFrozen())
			if(man.isFrozen(e.getAttacker().getLoginName()))
				e.setCancelled(true);
	}
	
	@EventHandler
	public void onPickup(PlayerItemPickupEvent e){
		if(man.isFrozen())
			if(man.isFrozen(e.getPlayer().getLoginName()))
				e.setCancelled(true);
	}
	
	
	@EventHandler
	public void onInter(BlockInteractEvent e){
		if(man.isFrozen())
			if(man.isFrozen(e.getPlayer().getLoginName()))
				e.setCancelled(true);
	}

}
