package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

public class WebsiteCmds implements CommandExecutor{

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {

		if(sender instanceof Player)
			sender.sendMessage("This command can only be executed as the console!")
		
	}
	
	

}
