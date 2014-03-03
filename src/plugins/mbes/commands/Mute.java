package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class Mute implements CommandExecutor{

	private Server server;
	
	public Mute(Server server) {
		this.server = server;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("mute"))
		{
			if(sender.hasPermission("mbes.mod.mute"))
			{
				if(args.length == 0)
					sender.sendMessage("Syntax:/mute <name>");
				else
				{
					Player p = server.getPlayer(args[0]);
				
					if(p == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
				
					else
					{
						p.setMetaData("MBES:MUTEALL",true);
						sender.sendMessage("The player '" + p.getDisplayName()+ "' was muted!");
					}
				}
			}
		
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("unmute"))
		{
			if(sender.hasPermission("mbes.mod.unmute"))
			{
				if(args.length == 0)
					sender.sendMessage("Syntax:/mute <name>");
				else
				{
					Player p = server.getPlayer(args[0]);
				
					if(p == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
				
					else
					{
						p.setMetaData("MBES:MUTEALL",false);
						sender.sendMessage("The player '" + p.getDisplayName()+ "' was unmuted!");
					}
				}
			}
		
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
	}

}
