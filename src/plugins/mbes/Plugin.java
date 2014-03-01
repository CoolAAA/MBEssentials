package plugins.mbes;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

import plugins.mbes.commands.Commands;
import plugins.mbes.commands.ModCmds;
import plugins.mbes.commands.MoneyCmds;
import plugins.mbes.commands.Mute;
import plugins.mbes.commands.PMCmds;
import plugins.mbes.commands.Tpto;
import plugins.mbes.commands.Tphere;
import plugins.mbes.handler.AccountMaker;
import plugins.mbes.handler.MuteHandler;
import plugins.mbes.misc.MoneyManager;

@Manifest(name="MBEssentials",authors = {"TheMushyPeas","AAAA","Abiram"})
public class Plugin extends MBServerPlugin{
	Config config = new Config();
	MoneyManager bank;
	@Override
	public void onEnable() {
		this.getLogger().info("Thanks for using MBEssentials by AAAA, Abiram and TheMushypeas!");
		this.getLogger().info("Please report any bugs and glitches to the forums!");
		
        this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
         if(config.isEnableDebug())
        	this.getLogger().info("Successfully registered command: /kill");
		
		this.getPluginManager().registerCommand("sudo",new ModCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /sudo");
		
		this.getPluginManager().registerCommand("kickall",new ModCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /kickall");
		
		this.getPluginManager().registerCommand("sayas",new ModCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /sayas");
		 		//send a message using a different playername (who is online)
		
		this.getPluginManager().registerCommand("tpto",new Tpto(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /tpto");
		
		this.getPluginManager().registerCommand("tphere",new Tphere(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /tphere");
		
		this.getPluginManager().registerCommand("pm",new PMCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /pm");
		
		this.getPluginManager().registerCommand("unblock",new PMCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /unblock");
		
		this.getPluginManager().registerCommand("block",new PMCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /block");
		
		this.getPluginManager().registerCommand("blockall",new PMCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /blockall");
		
		this.getPluginManager().registerCommand("unblockall",new PMCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /unblockall");
		
		this.getPluginManager().registerCommand("mute",new Mute(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /mute");
		
		this.getPluginManager().registerEventHandler(new MuteHandler());
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered event handler: MuteHandler");
		
		 if(config.isEnableMoneySystem())
		 {
			 bank = this.getServer().getConfigurationManager().load(this,MoneyManager.class);
			 
			 this.getPluginManager().registerCommand("pay",new MoneyCmds(this.getServer(),bank));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /pay");
			 
			 this.getPluginManager().registerCommand("addmoney",new MoneyCmds(this.getServer(),bank));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /addmoney");
			 
			 this.getPluginManager().registerCommand("rmvmoney",new MoneyCmds(this.getServer(),bank));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /rmvmoney");
			 
			 this.getPluginManager().registerCommand("balance",new MoneyCmds(this.getServer(),bank));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /balance");
			
			 this.getPluginManager().registerCommand("resetmoney",new MoneyCmds(this.getServer(),bank));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /resetmoney");
			 
			this.getPluginManager().registerEventHandler(new AccountMaker(bank,config));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered event handler: AccountMaker");
		 }
		 
		this.getLogger().info("MBEssentials Startup Finished!");
	}
	@Override
	public void onDisable(){
		this.getLogger().info("Have a nice day - from the MBEssentials Team!");
	}
}
