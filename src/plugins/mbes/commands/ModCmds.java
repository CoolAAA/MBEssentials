package plugins.mbes.commands;

import plugins.mbes.misc.CmdSender;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class ModCmds implements CommandExecutor{

	boolean isPlayer = false;
	Player p;
	Server s;
	
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
		
			if(sender.hasPermission("mbes.mod.kickall"))
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
		
		if(command.equals("sudo"))
		{
			if(sender.hasPermission("mbes.mod.sudo"))
			{
				if(args.length < 2)
					sender.sendMessage("Syntax:/sudo <playerName> <command> [args]");
				
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
						CmdSender Cmds = new CmdSender((CommandSender)temp);
						s.executeCommand(Cmds,args[1],arg);
						sender.sendMessage("Returned:");
						
						for(String e : Cmds.getMsg())
						{
							sender.sendMessage(e);
						}
					}
					
					else
					{
						CmdSender Cmds = new CmdSender((CommandSender)temp);
						s.executeCommand(Cmds,args[1]);
						sender.sendMessage("Returned:\n" + Cmds.getMsg());
					}
				}
				else
					sender.sendMessage("The Player '" + args[0] + "' was not found!");
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
	}

}
