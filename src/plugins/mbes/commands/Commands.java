package plugins.mbes.commands;

import plugins.mbes.misc.Keys;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Material;
import com.mbserver.api.game.Player;

public class Commands implements CommandExecutor{
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
					p.getLocation().getWorld().setBlockWithoutUpdate(p.getLocation(),Material.BEDROCK);
					p.getLocation().getWorld().setBlockWithoutUpdate(p.getLocation().getBlockX(),p.getLocation().getBlockY() + 1,p.getLocation().getBlockZ(),Material.BEDROCK);
					
					p.setMetaData(Keys.kill_key,true);
					p.setMetaData(Keys.kill_location_key,p.getLocation());
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
						//needs alternative
						temp.teleport(temp.getLocation().getWorld(),temp.getLocation().getBlockX(),-5,temp.getLocation().getBlockZ());
						sender.sendMessage("The player '" + temp.getDisplayName() + "' has been killed!");
						
					}
				}
			}
			
		}
	}
	

}
