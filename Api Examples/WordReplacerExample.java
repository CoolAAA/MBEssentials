package test.test;


import plugins.mbes.managers.ChatReplacer;
import plugins.mbes.MBEPlugin;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;


@Manifest(name = "Test",dependencies = "MBEssentials")
public class WordReplacerExample extends MBServerPlugin{
	
	@Override
	public void onEnable() {
		//Get the plugin from the plugins. Use the static field in MBEPlugin.
		MBEPlugin plugin = (MBEPlugin)this.getPluginManager().getPlugin(MBEPlugin.MANIFEST_NAME);
		
		//Get the chat replacer
		ChatReplacer chat = plugin.getChatReplacer();
		
		
		//Add the word to the chat replacer 
		chat.addWord("MB", "Minebuilder");
		
		//If someone in the chat says MB it will change it to Minebuilder.
		
		chat.delWord("MB");
		
		//Now if someone says MB it will not change it into minebuilder.
	}

}
