package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class Commands implements CommandExecutor{
	private Player temp;
	private Server s;
	
	public Commands(Server s) {
		this.s = s;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("kill"))
		{
			if(!sender.hasPermission("mbes.cmds.kill"))
				sender.sendMessage("You don't have permission to use the kill command!");
			
			else if(args.length == 0)
				sender.sendMessage("Syntax: /kill <playerName>");
			
			else
			{
				temp = s.getPlayer(args[0]);
				if(temp ==  null)
					sender.sendMessage("The player '" + args[0] + "' was not found!");
				else
				{
					temp.teleport(temp.getLocation().getBlockX(),-5, temp.getLocation().getBlockZ()); // teleports him under the world and dies instantly
					sender.sendMessage("Player '" + temp.getDisplayName() + "' has been killed!");
					temp = null;
				}
			}
		}
	}
	

}
