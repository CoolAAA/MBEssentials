package plugins.mbes.commands;

import plugins.mbes.managers.NameDataBase;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

public class NameDB implements CommandExecutor{

	private NameDataBase db;
	
	public NameDB(NameDataBase base) {
		db = base;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.fullnames") || sender.hasPermission("mbes.mod.*"))
		{
			if(args.length > 0)
			{
				String[] data = db.getNames(args[0]);
				
				if(data == null)
					sender.sendMessage("No players were found!");
				
				else
					for(String e : data)
						sender.sendMessage(e);
			}
			else
			{
				
				String[] data = db.getNames();
				if(data == null)
					sender.sendMessage("No players were found");
				
				else
					for(String e : data)
						sender.sendMessage(e);
			}
		}
		else
		{
			sender.sendMessage("You don't have permission to use this command!");
		}
		
	}

}
