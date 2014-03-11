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
	
	private String url,vUrl;
	private Server s;
	private int version;
	public UpdateCmds(String url,String vUrl,Server server,int version) {
		this.url = url;
		this.s = server;
		this.vUrl = vUrl;
		this.version = version;
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
							Downloader.downloadFile(url,"plugins\\MBEssentials.jar");
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
				s.getLogger().info("Checking for an update to MBEssentials!");
				
				Thread th = new Thread(){
					public void run() {
						try {
							Downloader.checkUpdate(new String[] {vUrl,url},new String[] {"plugins\\MBEssentials\\version.txt","plugins\\MBEssentials.jar"}, version);
						} catch (IOException e1) {
							try {
								File lFile = new File("plugins\\MBEssentials\\Download-err-" + new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date()));
								lFile.createNewFile();
								PrintWriter err = new PrintWriter(lFile);
								e1.printStackTrace(err);
								s.getLogger().warning("Could not update MBEssentials error log created at:" + lFile.getPath());
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
