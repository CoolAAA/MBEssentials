package plugins.mbes;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

@Manifest(name="MBEssentials",authors = {"TheMushyPeas","AAAA"})
public class Plugin extends MBServerPlugin{
	@Override
	public void onEnable() {
		this.getlogger().info("Thanks for using MBEssentials by AAAA and TheMushypeas!");
                this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
		this.getPluginManager().registerCommand("sudo",new ModCmds(this.getServer()));
		this.getPluginManager().registerCommand("kickall",new ModCmds(this.getServer()));
	}
}
