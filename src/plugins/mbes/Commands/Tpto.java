package Commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;
import com.mbserver.api.Server;


public class Tpto implements CommandExecutor {
	private Server server;

    public Tpto( Server server ) {
        this.server = server;
    }
    @Override
    public void execute( String command, CommandSender sender, String[] args, String label ) {
        Player player = (Player)sender;
        Player sendplayer = server.getPlayerExact(args[0]);
        
        if (sender.hasPermission("mbes.cmds.tpto")){
        	
        		
        			
        			if (sendplayer != null){
        				
        			    Location loc = sendplayer.getLocation();
                		
                		player.teleport(loc);
                		
                		sender.sendMessage("You were teleported to " + args[0]);
                		
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
