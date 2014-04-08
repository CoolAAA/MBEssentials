package plugins.mbes.handler;

import plugins.mbes.managers.NameDataBase;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerLoginEvent;

public class NameHandler implements Listener{
	private NameDataBase db;
	
	public NameHandler(NameDataBase base) {
		db = base;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		db.setName(e.getPlayer().getDisplayName(),e.getPlayer().getLoginName());
	}
	
}
