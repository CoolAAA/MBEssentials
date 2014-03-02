package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class Commands implements CommandExecutor{
	boolean isPlayer = false;
	Player p,temp;
	Server s;
	
	public Commands(Server s) {
		this.s = s;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(sender instanceof Player) // Check if its a player or console
		{
			isPlayer = true;
			p = (Player)sender;
		}
		
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
					temp.teleport(temp.getLocation().getBlockX(),1, temp.getLocation().getBlockZ());
					//Stuck method does NOT always work!!!
					//My idea: make a 3 by 3 AIR-box at 10000,0,10000 to 10002,2,10002 (in onEnable()
					//         save the CURRENT gamemode
					//	   teleport player to 10001,1,10001 so he will FALL out of the world
					//	   make him Survival (so he will fall)
					//	   restore gamemode
					sender.sendMessage("Player '" + temp.getDisplayName() + "' has been killed!");
					temp = null;
				}
			}
		}
	}
	

}
