package plugins.mbes.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mbserver.api.events.BlockPlaceEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerDeathEvent;
import com.mbserver.api.events.PlayerPvpEvent;
import com.mbserver.api.events.PreCommandEvent;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Material;
import com.mbserver.api.game.Sign;
import java.lang.reflect.InvocationTargetException;

import plugins.mbes.Config;
import plugins.mbes.managers.LogManager;

public class LogHandler implements Listener{

	private Config config;
	private LogManager logger;
	private int[] ID = new int[5];
	
	public LogHandler(Config config,LogManager logger,int[] ID) {
		this.config = config;
		this.logger = logger;
		this.ID = ID;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(config.isEnableDeathLog())
		{
			Location loc = e.getLocation();
			String log = "Player '" + e.getPlayer().getDisplayName() + "' Died At X:"  + loc.getBlockX()
					+ " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ() + " World:" + loc.getWorld().getWorldName();
			try {
				logger.writeLog(log,ID[0],true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	@EventHandler
	public void onPvP(PlayerPvpEvent e){
		if(config.isEnablePvPLog())
		{
			Location loc = e.getLocation();
			String log = "Player '" + e.getAttacker().getDisplayName() + "' Attacked Player '" +  e.getVictim().getDisplayName() + "X:"  + loc.getBlockX()
					+ " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ() + " World:" + loc.getWorld().getWorldName();
			try {
				logger.writeLog(log,ID[1],true);
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
			String args = "";
			for(String a : e.getArguments())
				args = args + a + " ";
			String log = name + " issued the command: '"  + e.getCommand() + "  With Arguments: " + args;
			String logspace = "";
			try {
				logger.writeLog(log,ID[2],true);
				logger.writeLog(logspace,ID[2],true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	
	@EventHandler
        public void onBlockPlace(BlockPlaceEvent event1){
        	if(config.isEnablePlaceLog())
        	{
        		String name = event1.getPlayer().getDisplayName();
        		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        		String log;
        		try{log = name + " placed a block of " + event1.getMaterial().getName();}
    			catch(InvocationTargetException e){
			log = name + " placed a block of (ID) " +event1.getBlock().getBlockID();
    			}
			try {
				logger.writeLog(log,ID[3],true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(event1.getMaterial() == Material.SIGN){
				Sign lol = (Sign) event1.getBlock().getBlockData();
				String message = lol.getText();
				String log2 = time + " " + name + " placed a sign saying: " + message;
				try {
					logger.writeLog(log2,ID[3],true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
        }
        @EventHandler
        public void onBlockBreak(BlockPlaceEvent event2){
        	if(config.isEnableBreakLog())
        	{
        		String name = event2.getPlayer().getDisplayName();
        		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
			String log;
        		try{log = name + " placed a block of " + event2.getMaterial().getName();}
    			catch(InvocationTargetException e){
			log = name + " placed a block of (ID) " +event2.getBlock().getBlockID();
    			}
			try {
				logger.writeLog(log,ID[4],true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
        }

}




