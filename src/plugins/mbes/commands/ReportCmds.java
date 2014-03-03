package plugins.mbes.commands;

import plugins.mbes.managers.ReportManager;
import plugins.mbes.misc.Report;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class ReportCmds implements CommandExecutor{

	private Server server;
	private ReportManager report;
	
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
								reason = reason + args[a] + " ";
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
		
		else if(command.equals("vwreport") && args.length == 0)
		{
			if(sender.hasPermission("mbes.mod.vwreports"))
			{
				Report[] rps = report.getAll();
				
				if(rps == null)
					sender.sendMessage("There are no reports!");
				
				else
				{
					sender.sendMessage("  Reports\n-------------");
					for(Report e : rps)
					{
						sender.sendMessage( e.getId() + " " + e.getName());
					}
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("vwreport"))
		{
			
			if(sender.hasPermission("mbes.mod.vwreport"))
			{
				try{
					int vnum = Integer.parseInt(args[0]);
					
					Report rp = report.getReport(new Report(vnum));
					
					if(rp == null)
						sender.sendMessage("The report '" + vnum + "' was not found!");
					
					else
					{
						sender.sendMessage("'" + rp.getName() + "' reported " + rp.getReported() + "' for:");
						sender.sendMessage(rp.getReason());
					}
				}catch(NumberFormatException e){
					sender.sendMessage("Enter a valid number");
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("delreport"))
		{
			if(sender.hasPermission("mbes.mod.delreport"))
			{
				if(args.length == 0)
					sender.sendMessage("Syntax:/delreport <number>");
				
				else
				{
					try{
						int vnum = Integer.parseInt(args[0]);
						
						if(report.delReport(new Report(vnum)))
							sender.sendMessage("The report was deleted!");
						else
							sender.sendMessage("The report '" + vnum + "' was not found!");
					}catch(NumberFormatException e){
						sender.sendMessage("Enter a valid number!");
					}
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
	}

}
