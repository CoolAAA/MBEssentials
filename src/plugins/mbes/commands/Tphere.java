package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;
import com.mbserver.api.Server;

public class Tphere implements CommandExecutor {
	private Server server;

    public Tphere( Server server ) {
        this.server = server;
    }
    
    @Override
    public void execute( String command, CommandSender sender, String[] args, String label ) {
    	Player player = null;
    	
    	
    	if(args.length != 0)
    	{
    		if(!(sender instanceof Player))
    			sender.sendMessage("Cannot execute as console!");
    			
    		
    		else
    		{
    			player = (Player)sender;
        
        		Player sendplayer = server.getPlayerExact(args[0]);
        
        		if (sender.hasPermission("mbes.cmds.tphere") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.cmds.*")){
        	
        			if (sendplayer != null){
        				
        				Location loc = player.getLocation();
                		
        				sendplayer.teleport(loc);
                		
                		sender.sendMessage(args[0] + " was teleported to you");
                		
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
    		sender.sendMessage("Syntax:/tphere <playerName>");

    }
}
