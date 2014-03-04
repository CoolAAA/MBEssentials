package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;
import com.mbserver.api.Server;

public class Coords implements CommandExecutor {
	private Server server;

    public Coords( Server server ) {
        this.server = server;
    }
    
    @Override
    public void execute( String command, CommandSender sender, String[] args, String label ) {
 
    	if(args.length != 0)
    	{
    		if(!(sender instanceof Player))
    			sender.sendMessage("Cannot execute as console!");
    			
    		
    		else
    		{
    			Player sendplayer = server.getPlayerExact(args[0]);
        
        		if (sender.hasPermission("mbes.cmds.coords")){
        	
        			if (sendplayer != null){
        				
        				Location loc = sendplayer.getLocation();
        				sender.sendMessage("The coordinates of " + args[0] + " are X:" + loc.getBlockX() + " Y: " + loc.getBlockY() + " Z: " + loc.getBlockZ());
                		
        				}
                	
                	else{
                			sender.sendMessage("The specified player could not be found");
                		}
        	}

        	else {
        	sender.sendMessage("You don't have permission to use this command!");
        	}
    	}
        
    	}
    	
    	else
    		sender.sendMessage("Syntax: /coords <playerName>");
    }
}
