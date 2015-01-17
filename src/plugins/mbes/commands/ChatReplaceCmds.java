package plugins.mbes.commands;

import plugins.mbes.MBEPlugin;
import plugins.mbes.managers.ChatReplacer;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.dynamic.ChatColor;

public class ChatReplaceCmds implements CommandExecutor{

	ChatReplacer rp;
	
	public ChatReplaceCmds(ChatReplacer e) {
		rp = e;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("addword"))
		{
			if(sender.hasPermission("mbes.mod.addword") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length > 1)
				{
					if(args.length == 2)
					{
						rp.getMap().put(args[0],args[1]);
						sender.sendMessage(MBEPlugin.tag + "The word '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' will be replaced with the word '" + ChatColor.RED + args[1] + ChatColor.WHITE + "'");
					}
					
					else
					{
						String set = "";
						
						for(int a = 1; a < args.length;a++)
						{
							if(a + 1 > args.length)
								set = set.concat(args[a]);
							
							else
								set = set.concat(args[a] + " ");
						}
						
						rp.getMap().put(args[0],set);
						sender.sendMessage(MBEPlugin.tag + "The word '" +  ChatColor.RED + args[0] + ChatColor.WHITE + "' will be replaced with the words '" +  ChatColor.RED + set + ChatColor.WHITE + "'");
					}
					
				}
				
				else
					sender.sendMessage(MBEPlugin.tag + "Usage: " + ChatColor.RED + "/addword " + ChatColor.GREEN + "<word> <word>");
			}
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("delword"))
		{
			if(sender.hasPermission("mbes.mod.delword") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length != 0)
				{
					rp.getMap().remove(args[0]);
					
					sender.sendMessage(MBEPlugin.tag + "The word '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' has been deleted!");
				}
				
				else
					sender.sendMessage(MBEPlugin.tag + "Usage: " + ChatColor.RED + "/delword " + ChatColor.GREEN + "<word>");
			}
			
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("clearwords"))
		{
			if(sender.hasPermission("mbes.mod.clearwords") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				rp.getMap().clear();
				sender.sendMessage(MBEPlugin.tag + "All of the words have been cleared!");
			}
			
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("listwords"))
		{
			if(sender.hasPermission("mbes.mod.listwords") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(rp.getMap().isEmpty()){
					sender.sendMessage(MBEPlugin.tag + "There are no words!");
					return;
				}
				
				String[] keys = rp.getMap().keySet().toArray(new String[0]);
				sender.sendMessage(MBEPlugin.tag + " Word  Replacement");
				sender.sendMessage(MBEPlugin.tag + "-------------------");
				for(String e : keys)
				{
					sender.sendMessage(MBEPlugin.tag + String.format("%s    %s", e , rp.getMap().get( e )));
				}
				sender.sendMessage(MBEPlugin.tag + "End of words");
			}
			else
			{
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
			}
		}
	}
	
}
