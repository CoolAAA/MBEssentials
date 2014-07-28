package plugins.mbes.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mbserver.api.events.BlockBreakEvent;
import com.mbserver.api.events.BlockPlaceEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerDeathEvent;
import com.mbserver.api.events.PlayerPvpEvent;
import com.mbserver.api.events.PreCommandEvent;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Material;
import com.mbserver.api.game.Sign;

import plugins.mbes.Config;
import plugins.mbes.managers.LogManager;

@SuppressWarnings("unused") //Material will be used, so to avoid warnings in Eclipse, this will be my solution :P

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
			for(String a : e.getArguments()){
				args = args + a + " ";
			}
			if(args == ""){
				String log = "'" + name + "'" + " issued the command: '"  + e.getCommand() + "'  with no arguments";
				try {
					logger.writeLog(log,ID[2],true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else{
				String log = "'" + name + "'" + " issued the command: '"  + e.getCommand() + "'  with arguments: '" + args + "\n'";
				try {
					logger.writeLog(log,ID[2],true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}

	}

	
	@EventHandler
        public void onBlockPlace(BlockPlaceEvent event1){
        	if(config.isEnablePlaceLog())
        	{
        		String name = event1.getPlayer().getDisplayName();
        		//String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        		String log = "ERROR!";
			
        		try{
        			log = " ["+event1.getLocation().getBlockX() + "," + event1.getLocation().getBlockY() + "," + event1.getLocation().getBlockZ() + "] " +name + " placed a block of " + event1.getMaterial().getName();
        		}catch(NullPointerException err1){
				log = " ["+event1.getLocation().getBlockX() + "," + event1.getLocation().getBlockY() + "," + event1.getLocation().getBlockZ() + "] " +name + " placed a block (ID) of " + event1.getBlock().getBlockID();
        		}
			
			try {
				logger.writeLog(log,ID[3],true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(event1.getBlock().getBlockID() == 63){ //63=Sign
				Sign sign1 = (Sign) event1.getBlock().getBlockData();
				String message = sign1.getText();
				//String message = event1.getBlock().getBlockData().toString();
				String log2 = " ["+event1.getLocation().getBlockX() + "," + event1.getLocation().getBlockY() + "," + event1.getLocation().getBlockZ() + "] " +name + " placed a sign saying: " + message;
				try {
					logger.writeLog(log2,ID[3],true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
        }
        @EventHandler
        public void onBlockBreak(BlockBreakEvent event2){
        	if(config.isEnableBreakLog())
        	{
        		String name = event2.getPlayer().getDisplayName();
        		//String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        		String log = "ERROR!";
    			
        		try{
        			log = " ["+event2.getLocation().getBlockX() + "," + event2.getLocation().getBlockY() + "," + event2.getLocation().getBlockZ() + "] " +name + " broke a block of " + event2.getMaterial().getName();
        		}catch(NullPointerException err1){
				log = " ["+event2.getLocation().getBlockX() + "," + event2.getLocation().getBlockY() + "," + event2.getLocation().getBlockZ() + "] " +name + " broke a block (ID) of " + event2.getBlock().getBlockID();
        		}
        		
			try {
				logger.writeLog(log,ID[4],true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
        }

}




