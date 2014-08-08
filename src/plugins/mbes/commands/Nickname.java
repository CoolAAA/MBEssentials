package plugins.mbes.commands;

import plugins.mbes.Config;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public class Nickname implements CommandExecutor {
	private Config config;

    public Nickname(Config config) {
        this.config = config;
    }
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage("This command can only be executed by players!");
			return;
		}
		
		Player player = null;
    	player = (Player)sender; 
    	
    	if(sender.hasPermission("mbes.nickname") || sender.hasPermission("mbes.*")){
    	
		if (args.length == 0){
			
			String nicktemp = this.config.getPlayerNickname(player);
			if (nicktemp == null){
				sender.sendMessage("You do not currently have a nickname");
			}
			else {
			sender.sendMessage("Your current nickname is: " + nicktemp );
			}
			
		}
		 
		else{
			
			this.config.setPlayerNickname(player, args[0]);
			sender.sendMessage("Your new nickname is: " + this.config.getPlayerNickname(player));
					
			
			}
    	}
    	else{
    		
    		sender.sendMessage("You do not have permission to use /nick");
    		
    	}
}
}
		    



	
