package plugins.mbes.commands;

import plugins.mbes.Config;
import plugins.mbes.MBEPlugin;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.dynamic.ChatColor;
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
			sender.sendMessage(MBEPlugin.tag + "This command can only be executed by players!");
			return;
		}
		
		Player player = null;
    	player = (Player)sender; 
    	
    	if(sender.hasPermission("mbes.nickname") || sender.hasPermission("mbes.*")){
    	
		if (args.length == 0){
			
			String nicktemp = this.config.getPlayerNickname(player);
			if (nicktemp == null){
				sender.sendMessage(MBEPlugin.tag + "You do not currently have a nickname");
			}
			else {
			sender.sendMessage(MBEPlugin.tag + "Your current nickname is: " + ChatColor.RED + nicktemp );
			}
			
		}
		 
		else{
			
			this.config.setPlayerNickname(player, args[0]);
			sender.sendMessage(MBEPlugin.tag + "Your new nickname is: " + ChatColor.RED + this.config.getPlayerNickname(player));
					
			
			}
    	}
    	else{
    		
    		sender.sendMessage(MBEPlugin.tag + "You do not have permission to use /nick");
    		
    	}
}
}
		    



	
