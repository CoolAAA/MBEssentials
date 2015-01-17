package plugins.mbes.commands;

import plugins.mbes.Config;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public class DelNickname implements CommandExecutor {
	private Config config;

    public DelNickname(Config config) {
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
    	this.config.setPlayerNickname(player, null);
    	sender.sendMessage("Your nickname has been cleared!");
	}
}
		    



	
