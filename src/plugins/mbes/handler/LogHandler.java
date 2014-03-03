package plugins.mbes.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerDeathEvent;
import com.mbserver.api.events.PlayerPvpEvent;
import com.mbserver.api.events.PreCommandEvent;
import com.mbserver.api.game.Location;

import plugins.mbes.Config;
import plugins.mbes.misc.LogManager;

public class LogHandler implements Listener{
	
	Config config;
	LogManager logger;
	public LogHandler(Config config,LogManager logger) {
		this.config = config;
		this.logger = logger;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(config.isEnableDeathLog())
		{
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			Location loc = e.getLocation();
			String log = time + " Player '" + e.getPlayer().getDisplayName() + "' Died At X:"  + loc.getBlockX()
					+ " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ() + " World:" + loc.getWorld().getWorldName();
			try {
				logger.writeEntry(log,LogManager.DLOG);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	@EventHandler
	public void onPvP(PlayerPvpEvent e){
		if(config.isEnablePvPLog())
		{
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			Location loc = e.getLocation();
			String log = time + " Player '" + e.getAttacker().getDisplayName() + "' Attacked Player '" +  e.getVictim().getDisplayName() + "X:"  + loc.getBlockX()
					+ " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ() + " World:" + loc.getWorld().getWorldName();
			try {
				logger.writeEntry(log,LogManager.PLOG);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	@EventHandler
	public void onCmd(PreCommandEvent e){
		if(config.isEnableCommandLog())
		{
			String name = e.getSender().getName();
			String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			String args = "";
			for(String a : e.getArguments())
				args = args + a + " ";
			String log = time + " Player '" + name + "' Command:" + e.getCommand() + " Args:" + args;
			
			try {
				logger.writeEntry(log,LogManager.CLOG);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@EventHandler
        public void onBlockPlace(BlockPlaceEvent event1){
        	if(config.isEnablePlaceLog())
        	{
        		String name = event1.getSender().getName();
        		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			String log = time + " " + event1.getPlayer().getName() + " placed a block of " + event1.getMaterial());
			try {
				logger.writeEntry(log,LogManager.PLCLOG);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
        }
        @EventHandler
        public void onBlockBreak(BlockPlaceEvent event2){
        	if(config.isEnableBreakLog())
        	{
        		String name = event2.getSender().getName();
        		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			String log = time + " " + event1.getPlayer().getName() + " broke a block of " + event1.getMaterial());
			try {
				logger.writeEntry(log,LogManager.BLOG);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
        }
}
	
	
}
