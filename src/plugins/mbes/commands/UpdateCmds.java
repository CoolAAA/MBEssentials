package plugins.mbes.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import plugins.mbes.misc.Downloader;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;

public class UpdateCmds implements CommandExecutor{
	
	private String pUrl,vUrl,wnUrl;
	private String paths[];
	private Server s;
	private float version;
	
	public UpdateCmds(String pUrl,String vUrl,Server server,float version, String paths[], String wnUrl) {
		this.pUrl = pUrl;
		this.s = server;
		this.vUrl = vUrl;
		this.version = version;
		this.paths = paths;
		this.wnUrl = wnUrl;
	}

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("forceupdate"))
		{
			if(sender.hasPermission("mbes.cmds.update"))
			{
				
				Thread th = new Thread(){
					public void run() {
						try {
							Downloader.downloadFile(pUrl,paths[0]);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				
				th.run();
				
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("checkupdate"))
		{
			if(sender.hasPermission("mbes.cmds.update") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.cmds.*"))
			{
				
				sender.sendMessage("You have started an MbEssentials update check!");
				s.getLogger().info("Checking for an update to MBEssentials!");
				
				Thread th = new Thread(){
					public void run() {
						try {
							Downloader.checkUpdate(pUrl,vUrl,wnUrl,paths[0],paths[1],paths[2],version);
						} catch (IOException e1) {
							try {
								File lFile = new File("plugins\\MBEssentials\\Download-err-" + new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date()));
								lFile.createNewFile();
								PrintWriter err = new PrintWriter(lFile);
								e1.printStackTrace(err);
								s.getLogger().warning("Could not update MBEssentials. An error log was created at:" + lFile.getPath());
							} catch (FileNotFoundException e2) {
								e2.printStackTrace();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
						}
					}
				};
				
				th.run();
			}
			
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
	}

}
