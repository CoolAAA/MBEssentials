package plugins.mbes.commands;

import plugins.mbes.managers.ReportManager;
import plugins.mbes.misc.Report;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class ReportCmds implements CommandExecutor{

	Server server;
	ReportManager report;
	
	public ReportCmds(Server s,ReportManager rp) {
		server = s;
		report = rp;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("report"))
		{
			if(sender instanceof Player)
			{
				if(sender.hasPermission("mbes.cmds.report"))
				{
					if(args.length > 1)
					{	
						Player rp = server.getPlayer(args[0]);
						
						if(rp != null)
						{
							String reason = "";
						
							for(int a = 1; a < args.length;a++)
							{
								reason = reason + args[a];
							}
							
							report.newReport(new Report((Player)sender,rp,reason));
							
						}
						
						else
							sender.sendMessage("The player '" + args[0] + "' was not found!");
					}
					else
						sender.sendMessage("Syntax:/report <player> <reason>");
				}
				
				else
					sender.sendMessage("You don't have permission to use this command!");
			}
			
			else
				sender.sendMessage("This command can only be executed as a player!");
		}
		
		else if(command.equals("viewreport"))
		
	}

}
