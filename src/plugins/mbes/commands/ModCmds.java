package plugins.mbes.commands;

import plugins.mbes.misc.CmdSender;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class ModCmds implements CommandExecutor{

	private boolean isPlayer = false;
	private Player p;
	private Server s;
	
	public  ModCmds(Server s) {
		this.s = s;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(sender instanceof Player)
		{
			isPlayer = true;
			p = (Player)sender;
		}
		
		if(command.equals("kickall"))
		{
		
			if(sender.hasPermission("mbes.mod.kickall") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				String reason;
				if(args.length == 0)
					reason = "You Have Been Kicked Off The Server!";
				else
					reason = args[0];
				for(Player tmp : s.getPlayers())
				{
					if(isPlayer)
						if(tmp.getLoginName().equals(p.getLoginName()))
							continue;
					
					tmp.kick(reason);
					
					sender.sendMessage("Player '" + tmp.getDisplayName() + "' has been kicked!");
				}
				sender.sendMessage("All players have been kicked!");
				
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("sudo"))
		{
			if(sender.hasPermission("mbes.mod.sudo") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length < 2) {
					sender.sendMessage("Syntax:/sudo <playerName> <command> [args]");
					return;
				}
				
				Player temp = s.getPlayer(args[0]);
				
				if(temp != null)
				{
					if(args.length > 2)
					{
						String[] arg = new String[args.length - 2];
						int t = 0;
						while(t < arg.length)
						{
							arg[t] = args[t + 2]; 
							t++;
						}
						CmdSender sudo = new CmdSender(temp);
						sudo.executeCommand(args[1], arg);
						sender.sendMessage("Returned:");
						
						for(String e : sudo.getMessage())
						{
							sender.sendMessage(e);
						}
					}
					
					else
					{
						CmdSender sudo = new CmdSender(temp);
						sudo.executeCommand(args[1],new String[] {""});
						for(String e : sudo.getMessage())
						{
							sender.sendMessage(e);
						}
					}
				}
				else
					sender.sendMessage("The Player '" + args[0] + "' was not found!");
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("sayas"))
		{
			if(sender.hasPermission("mbes.mod.sayas") || sender.hasPermission("mbes.mod.*") || sender.hasPermission("mbes.*"))
			{
				if(args.length < 2)
					sender.sendMessage("Usage: /sayas <player> <message>");
				
				else
				{
					Player target = s.getPlayer(args[0]);
					
					if(target == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
					
					
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
	}

}
