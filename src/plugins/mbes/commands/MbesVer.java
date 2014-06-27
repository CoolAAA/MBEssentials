package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

public class MbesVer implements CommandExecutor {
	private float version;

	public MbesVer( float version ) {
		this.version = version;
	}

	public void execute( String command, CommandSender sender, String[] args, String label ) {
		
		sender.sendMessage("Current MbEssentials Version:");
		sender.sendMessage(Float.toString(version));
		
	}
	
}
