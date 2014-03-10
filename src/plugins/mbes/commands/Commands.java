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
			if(args.length == 0)
			{
				if(sender.hasPermission("mbes.cmds.kill") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.cmds.*"))
				{
					Player p = (Player)sender;
					
					p.teleport(p.getLocation().getWorld(),p.getLocation().getBlockX(),-5,p.getLocation().getBlockZ());
				}
				
				else
				{
					sender.sendMessage("You don't have permission to use this command!");
				}
			}
			
			else
			{
				if(sender.hasPermission("mbes.cmds.kill") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
					else
					{
						temp.teleport(temp.getLocation().getWorld(),temp.getLocation().getBlockX(),-5,temp.getLocation().getBlockZ());
						sender.sendMessage("The player '" + temp.getDisplayName() + "' has been killed!");
					}
				}
			}
			
		}
	}
	

}
