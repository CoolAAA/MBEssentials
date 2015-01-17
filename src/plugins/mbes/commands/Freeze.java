package plugins.mbes.commands;

import plugins.mbes.MBEPlugin;
import plugins.mbes.managers.FreezeManager;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.game.Player;

public class Freeze implements CommandExecutor{

	private Server s;
	private FreezeManager e;
	
	public Freeze(Server server,FreezeManager man) {
		s = server;
		e = man;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("freeze"))
		{
			if(sender.hasPermission("mbes.mod.freeze") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length > 0)
				{
					Player target = s.getPlayer(args[0]);
					
					if(target == null)
						sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					
					else
					{
						if(e.isFrozen(target.getLoginName()))
							sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + target.getDisplayName() + ChatColor.WHITE + "' is already frozen!");
						
						else{
							e.addName(target.getLoginName());
							sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + target.getDisplayName() + ChatColor.WHITE + "' is now frozen!");
						}
					}
					
				}
				
				else
					sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
			}
		}
		
		else if(command.equals("unfreeze"))
		{
			if(sender.hasPermission("mbes.mod.unfreeze") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length > 0)
				{
					Player target = s.getPlayer(args[0]);
					
					if(target == null)
						sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					
					else
					{
						if(!e.isFrozen(target.getLoginName()))
							sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + target.getDisplayName() + ChatColor.WHITE + "' is not frozen!");
						
						else{
							e.delName(target.getLoginName());
							sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + target.getDisplayName() + ChatColor.WHITE + "' has been unfrozen!");
						}
					}
					
				}
				
				else
					sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
			}
		}
	}

}
