package plugins.mbes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import plugins.mbes.commands.ChatReplaceCmds;
import plugins.mbes.commands.Commands;
import plugins.mbes.commands.ModCmds;
import plugins.mbes.commands.MoneyCmds;
import plugins.mbes.commands.Mute;
import plugins.mbes.commands.PMCmds;
import plugins.mbes.commands.ReportCmds;
import plugins.mbes.commands.Tpto;
import plugins.mbes.commands.Tphere;
import plugins.mbes.commands.Coords;
import plugins.mbes.commands.UpdateCmds;
import plugins.mbes.handler.AccountMaker;
import plugins.mbes.handler.ChatReplacerHandler;
import plugins.mbes.handler.LogHandler;
import plugins.mbes.handler.MuteHandler;
import plugins.mbes.managers.ChatReplacer;
import plugins.mbes.managers.LogManager;
import plugins.mbes.managers.MoneyManager;
import plugins.mbes.managers.ReportManager;
import plugins.mbes.misc.Downloader;
import plugins.mbes.misc.Logger;

@Manifest(name="MBEssentials",authors = {"TheMushyPeas","AAAA","Abiram"},config = Config.class)

public class MBEPlugin extends MBServerPlugin{
	private final int version = 1;
	private final String vUrl = "https://github.com/CoolAAA/MBEssentials/releases/download/v1.0a/version.txt";
	private final String pUrl = "https://github.com/CoolAAA/MBEssentials/releases/download/v1.0a/MBEssentials.jar";
	private LogManager logm;
	private ChatReplacer chatrp;
	private MoneyManager bank;
	private Config config = new Config();
	private ReportManager report = new ReportManager();
	private Logger breakLog,placeLog,deathLog,PvPLog,cmdLog;
	
	@Override
	public void onLoad() {
		String[] fileNames = {"plugin//MBEssentials","logs//MBE_Logs","logs//MBE_Logs//Command_Logs","logs//MBE_Logs//Death_Logs"
				,"logs//MBE_Logs//PvP_Logs","logs//MBE_Logs//Place_Logs","logs//MBE_Logs//Break_Logs"};
		
		File file;
		
		for(String e : fileNames)
		{
			file = new File(e);
			
			if(!file.exists())
				file.mkdir();
		}
		
		
		
	}
	
	@Override
	public void onEnable() {
		
		this.getLogger().info("Checking for an update to MBEssentials!");
		
		try { 
			if(Downloader.checkUpdate(new String[] {vUrl,pUrl},new String[] {"plugins\\MBEssentials\\version.txt","plugins\\MBEssentials.jar"}, version))
				this.getLogger().info("Successfully updated MBEssentials retart server to use newest version!");
		} catch (IOException e1) {
			try {
				File lFile = new File("plugins\\MBEssentials\\Download-err-" + new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date()));
				lFile.createNewFile();
				PrintWriter err = new PrintWriter(lFile);
				e1.printStackTrace(err);
				this.getLogger().warning("Could not update MBEssentials error log created at:" + lFile.getPath());
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
		this.getLogger().info("Thanks for using MBEssentials by AAAA, Abiram and TheMushypeas!");
		this.getLogger().info("Please report any bugs and glitches to the forums!");
		
		config = this.getConfig();
		
		chatrp = this.getServer().getConfigurationManager().load(this,ChatReplacer.class);
		if(chatrp == null)
			chatrp = new ChatReplacer();
		
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
			 
		this.getPluginManager().registerCommand("coords",new Coords(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /Coords");
		
		if(config.isEnablePmSystem())
		{
		
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
		
		}
		
		this.getPluginManager().registerCommand("mute",new Mute(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /mute");
		
		this.getPluginManager().registerEventHandler(new MuteHandler());
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered event handler: MuteHandler");
		
		 if(config.isEnableMoneySystem())
		 {
			 bank = this.getServer().getConfigurationManager().load(this,MoneyManager.class);
			 
			 if(bank == null)
				 bank = new MoneyManager();
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
		 
		 if(config.isEnableLogs())
		 {
			 logm = new LogManager();
			 
			 
			 if(config.isEnableBreakLog())
			 {
				 breakLog = new Logger("logs//MBE_Logs//Break_Logs//");
				 logm.attachLogger(breakLog);
			 }
			 
			 if(config.isEnableCommandLog())
			 {
				 cmdLog = new Logger("logs//MBE_Logs//Command_Logs//");
				 logm.attachLogger(cmdLog);
			 }
			 
			 if(config.isEnableDeathLog())
			 {
				 deathLog = new Logger("logs//MBE_Logs//Death_Logs//");
				 logm.attachLogger(deathLog);
			 }
			 
			 if(config.isEnablePlaceLog())
			 {
				 placeLog = new Logger("logs//MBE_Logs//Place_Logs//");
				 logm.attachLogger(placeLog);
			 }
			 
			 if(config.isEnablePvPLog())
			 {
				 PvPLog = new Logger("logs//MBE_Logs//PvP_Logs//");
				 logm.attachLogger(PvPLog);
			 }
			 
			 int[] lgs = new int[5];
			 
			 if(deathLog != null)
				 lgs[0] = deathLog.getId();
			 
			 if(PvPLog != null)
				 lgs[1] = PvPLog.getId();
			 
			 if(cmdLog != null)
				 lgs[2] = cmdLog.getId();
			 
			 if(placeLog != null)
				 lgs[3] = placeLog.getId();
			 
			 if(breakLog != null)
				 lgs[4] = breakLog.getId();
			 
			 this.getPluginManager().registerEventHandler(new LogHandler(config,logm,lgs));
			 
		 }
		 
		 if(config.isEnableReport())
		 {
			 report = this.getServer().getConfigurationManager().load(this,ReportManager.class);
			 
			 if(report == null)
				 report = new ReportManager();
			 
			 this.getPluginManager().registerCommand("report",new ReportCmds(this.getServer(),report));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /report");
			 
			 this.getPluginManager().registerCommand("vwreport",new ReportCmds(this.getServer(),report));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /vwreport");
			 
			 this.getPluginManager().registerCommand("delreport",new ReportCmds(this.getServer(),report));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /delreport");
		 }
		 
		 this.getPluginManager().registerCommand("addword",new ChatReplaceCmds(chatrp));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /addword");
		 
		 this.getPluginManager().registerCommand("delword",new ChatReplaceCmds(chatrp));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registerd command: /delword");
		 
		 this.getPluginManager().registerCommand("clearwords",new ChatReplaceCmds(chatrp));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registerd command: /clearwords");
		 
		 this.getPluginManager().registerEventHandler(new ChatReplacerHandler(chatrp));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered handler: ChatReplacerHandler");
		 
		 this.getPluginManager().registerCommand("forceupdate", new UpdateCmds(pUrl, vUrl,this.getServer(), version));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered: /forceupdate");
		 
		 this.getPluginManager().registerCommand("checkupdate", new UpdateCmds(pUrl, vUrl,this.getServer(), version));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered: /checkupdateupdate");
		 
		this.getLogger().info("MBEssentials Startup Finished!");
	}
	
	@Override
	public void onDisable(){
		
		this.saveConfig();
		
		if(logm != null)
			if(config.isEnableDebug())
				if(config.isEnableLogs())
					try {
						logm.closeAll();
					} catch (IOException e) {
						e.printStackTrace();
					}
		
				this.getLogger().info("Successfully closed logger");
				
				if(bank != null)
					this.getServer().getConfigurationManager().save(this,bank);
				
				this.getServer().getConfigurationManager().save(this,chatrp);
				
			this.getLogger().info("Successfully saved config and bank!");
			
		this.getLogger().info("Have a nice day - from the MBEssentials Team!");
	}
	
	public MoneyManager getMoneyManager(){
		return bank;
	}
	
	public LogManager getLogManager(){
		return logm;
	}
	
	public ChatReplacer getChatReplacer(){
		return chatrp;
	}
}


