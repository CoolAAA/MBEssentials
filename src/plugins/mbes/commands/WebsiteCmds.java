package plugins.mbes.commands;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import plugins.mbes.MBEPlugin;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public class WebsiteCmds implements CommandExecutor{

	private URI website;

	public WebsiteCmds() {
		try {
			website = new URI("http://mbessentials.bl.ee/"); 
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {

		if(sender instanceof Player)
			sender.sendMessage(MBEPlugin.tag + "This command can only be executed as the console!");
		
		else
		{
			sender.sendMessage(MBEPlugin.tag + "Opening the MbEssentials Website...");
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(website);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			else
				sender.sendMessage(MBEPlugin.tag + "Could not open browser! This may be because your desktop is not supported!");
			
		}
		
	}
	
	

}
