package plugins.mbes.commands;

import plugins.mbes.misc.Keys;
import plugins.mbes.misc.events.PMEvent;
import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

import java.util.Arrays;

public class PMCmds implements CommandExecutor{

	private Server server;
	
	public PMCmds(Server server) {
		this.server = server;
	}

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("pm"))
		{
			if(sender.hasPermission("mbes.pm") || sender.hasPermission("mbes.*"))
			{
				if(args.length > 1)
				{
				
					Player target = server.getPlayer(args[0]);
					
					if(target == null)
					{
						sender.sendMessage("The player '" + args[0] + "' was not found!");
						return;
					}
					
					if(target.getMetaData(Keys.pm_blocked_key + sender.getName(),false) &&
							sender instanceof Player)
					{
						sender.sendMessage("The player '" + target.getDisplayName() + "' has blocked you " +
								"from sending them pm's");
						return;
					}
					
					String msg = "";
					for (String arg : Arrays.copyOfRange(args, 1, args.length))
					msg += " " + arg;
					
					PMEvent pm = new PMEvent(sender,target, msg);
					pm.setCancelled(false);
					server.getPluginManager().triggerEvent(pm);
					
					if(pm.isCancelled())
						return;
					
					target.sendMessage(String.format("Pm from '%s': %s",sender.getName(),msg));
					sender.sendMessage("Your pm has been sent to '" + target.getDisplayName() + "'");
				}
				else
					sender.sendMessage("Usage: /pm <sendTo> <message>");
			}
			else
				denied(sender);
				
		}
		
		else if(command.equals("block"))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("This command can only be exectuted as a player");
				return;
			}
			
			if(sender.hasPermission("mbes.pm") || sender.hasPermission("mbes.*"))
			{
				if(args.length > 0)
				{
					Player target = server.getPlayer(args[0]);
					
					if(target == null)
					{
						sender.sendMessage("The player '" + args[0] + "' was not found!");
						return;
					}
					
					String data = Keys.pm_blocked_key + target.getDisplayName();
					
					Player send = (Player)sender;
					
					send.setMetaData(data,true);
					
				}
				else
				{
					sender.sendMessage("Usage: /block <player>");
				}
			}
			else
				denied(sender);
		}
		
		else if(command.equals("unblockall"))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("This command can only be exectuted as a player");
				return;
			}
			
			if(sender.hasPermission("mbes.pm") || sender.hasPermission("mbes.*"))
			{
				Player send = (Player)sender;
				
				for(Player e : server.getPlayers())
				{
					send.removeMetaData(Keys.pm_blocked_key + e.getDisplayName());
				}
				send.removeMetaData(Keys.pm_blockall_key);
				sender.sendMessage("Every player online has been unblocked!");
				return;
			}
			else
				denied(sender);
		}
		
		else if(command.equals("unblock"))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("This command can only be exectuted as a player");
				return;
			}
			
			if(sender.hasPermission("mbes.pm") || sender.hasPermission("mbes.*"))
			{
				if(args.length > 0)
				{
					Player target = server.getPlayer(args[0]);
					
					if(target == null)
					{
						sender.sendMessage("The player '" + args[0] + "' was not found!");
						return;
					}
					
					
					Player send = (Player)sender;
					send.removeMetaData(Keys.pm_blocked_key + target.getDisplayName());
					sender.sendMessage("The player '" + target.getDisplayName() + "' has been unblocked");
				}
				else
				{
					sender.sendMessage("Usage: /unblock <player>");
				}
			}
			else
				denied(sender);
		}
		
		else if(command.equals("blockall"))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("This command can only be exectuted as a player");
				return;
			}
			
			if(sender.hasPermission("mbes.pm") || sender.hasPermission("mbes.*"))
			{
				Player send = (Player)sender;
				
				for(Player e : server.getPlayers())
				{
					send.setMetaData(Keys.pm_blocked_key + e.getDisplayName(),true);
					send.sendMessage(e.getDisplayName() + " has been blocked from sending you PM's");
				}
				
				send.setMetaData(Keys.pm_blockall_key,true);
			}
			else
				denied(sender);
		}
		
	}
	
	private void denied(CommandSender s){
		s.sendMessage("You don't have permission to use this command!");
	}
	
}
