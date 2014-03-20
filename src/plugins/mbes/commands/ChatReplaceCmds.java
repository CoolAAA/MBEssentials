package plugins.mbes.commands;

import plugins.mbes.managers.ChatReplacer;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

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
						sender.sendMessage("The word '" + args[0] + "' will be replaced with the word '" + args[1] + "'");
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
						sender.sendMessage("The word '" + args[0] + "' will be replaced with the words '" + set + "'");
					}
					
				}
				
				else
					sender.sendMessage("Usage: /addword <word> <word>");
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("delword"))
		{
			if(sender.hasPermission("mbes.mod.delword") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length != 0)
				{
					rp.getMap().remove(args[0]);
					
					sender.sendMessage("The word '" + args[0] + "' has been deleted!");
				}
				
				else
					sender.sendMessage("Usage: /delword <word>");
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("clearwords"))
		{
			if(sender.hasPermission("mbes.mod.clearwords") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				rp.getMap().clear();
				sender.sendMessage("All of the words have been cleared!");
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("listwords"))
		{
			if(sender.hasPermission("mbes.mod.listwords") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(rp.getMap().isEmpty()){
					sender.sendMessage("There are no words!");
					return;
				}
				
				String[] keys = rp.getMap().keySet().toArray(new String[0]);
				sender.sendMessage(" Word  Replacement");
				sender.sendMessage("-------------------");
				for(String e : keys)
				{
					sender.sendMessage(String.format("%s    %s", e , rp.getMap().get( e )));
				}
				sender.sendMessage("End of words");
			}
			else
			{
				sender.sendMessage("You don't have permission to use this command!");
			}
		}
	}
	
}
