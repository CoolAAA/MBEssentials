package plugins.mbes;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

@Manifest(name="MBEssentials",authors = {"TheMushyPeas","AAAA"})
public class Plugin extends MBServerPlugin{
	@Override
	public void onEnable() {
		this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
	}
}
