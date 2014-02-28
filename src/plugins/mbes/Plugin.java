package plugins.mbes;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import plugins.mbes.commands.Tpto;



@Manifest(name="MBEssentials",authors = {"TheMushyPeas","AAAA"})
public class Plugin extends MBServerPlugin{
	@Override
	public void onEnable() {
		this.getLogger().info("Thanks for using MBEssentials by AAAA and TheMushypeas!");
		this.getLogger().info("Please report any bugs and glitches to the forums!");
        this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
		this.getLogger().info("Successfully registered command: /kill");
		this.getPluginManager().registerCommand("sudo",new ModCmds(this.getServer()));
		this.getLogger().info("Successfully registered command: /sudo");
		this.getPluginManager().registerCommand("kickall",new ModCmds(this.getServer()));
		this.getLogger().info("Successfully registered command: /kickall");
		this.getPluginManager().registerCommand("tpto",new Tpto(this.getServer()));
		this.getLogger().info("Successfully registered command: /tpto");
		
		this.getLogger().info("MBEssentials Startup Finished!");
	}
	@Override
	public void onDisable(){
		this.getLogger().info("Have a nice day - from the MBEssentials Team!");
	}
}
