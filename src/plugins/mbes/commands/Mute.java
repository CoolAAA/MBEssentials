package plugins.mbes.commands;

import java.util.HashMap;

import plugins.mbes.misc.Keys;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class Mute implements CommandExecutor{

	private Server server;
	private HashMap<String,Object>data;
	public Mute(Server server,HashMap<String,Object> map) {
		this.server = server;
		data = map;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("mute"))
		{
			if(sender.hasPermission("mbes.mod.mute") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
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
						data.put(p.getLoginName() + Keys.mute_key,true);
						sender.sendMessage("The player '" + p.getDisplayName()+ "' was muted!");
					}
				}
			}
		
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("unmute"))
		{
			if(sender.hasPermission("mbes.mod.unmute") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
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
						data.remove(p.getLoginName() + Keys.mute_key);
						sender.sendMessage("The player '" + p.getDisplayName()+ "' was unmuted!");
					}
				}
			}
		
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
	}

}
