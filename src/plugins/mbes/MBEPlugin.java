package plugins.mbes;

import java.io.IOException;
import java.util.HashMap;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.dynamic.ChatColor;

import plugins.mbes.commands.ChatReplaceCmds;
import plugins.mbes.commands.Commands;
import plugins.mbes.commands.DelNickname;
import plugins.mbes.commands.Freeze;
import plugins.mbes.commands.MbesVer;
import plugins.mbes.commands.ModCmds;
import plugins.mbes.commands.MoneyCmds;
import plugins.mbes.commands.Mute;
import plugins.mbes.commands.NameDB;
import plugins.mbes.commands.PMCmds;
import plugins.mbes.commands.ReportCmds;
import plugins.mbes.commands.Tpto;
import plugins.mbes.commands.Tphere;
import plugins.mbes.commands.Coords;
import plugins.mbes.commands.UpdateCmds;
import plugins.mbes.commands.WebsiteCmds;
import plugins.mbes.commands.Nickname;
import plugins.mbes.handler.AccountMaker;
import plugins.mbes.handler.ChatReplacerHandler;
import plugins.mbes.handler.FreezeHandler;
import plugins.mbes.handler.LogHandler;
import plugins.mbes.handler.MuteHandler;
import plugins.mbes.handler.NameHandler;
import plugins.mbes.handler.PMBlockHandler;
import plugins.mbes.handler.WorldBackupHandler;
import plugins.mbes.handler.NicknameHandler;
import plugins.mbes.handler.UpdateNotifyHandler;
import plugins.mbes.managers.ChatReplacer;
import plugins.mbes.managers.FreezeManager;
import plugins.mbes.managers.LogManager;
import plugins.mbes.managers.MoneyManager;
import plugins.mbes.managers.NameDataBase;
import plugins.mbes.managers.ReportManager;
import plugins.mbes.misc.CreateFiles;
import plugins.mbes.misc.Logger;
import plugins.mbes.misc.Updater;
import plugins.mbes.misc.WorldBackup;

@Manifest(name="MbEssentials",authors = {"TheMushyPeas","AAAA","Abiram"},config = Config.class)

public class MBEPlugin extends MBServerPlugin{
	
	
	// Variable decleration
	
	private HashMap<String,Object>data;
	private NameDataBase playerNameDB;
	private final float version = (float) 1.22;
	private FreezeManager FreezeMan;
	public final static String MANIFEST_NAME = "MbEssentials";
	private final String vUrl = "http://mbessentials.bl.ee/update/version.txt";
	private final String pUrl = "http://mbessentials.bl.ee/update/MbEssentials.jar";
	private final String wnUrl = "http://mbessentials.bl.ee/update/whatsnew.txt";
	public boolean enableUpdater = true;
	private final String[] paths = {"plugins/MbEssentials/Data/MbEssentials.jar","plugins/MbEssentials/Data/ver.dat","plugins/MbEssentials/Data/wn.dat"};
	private LogManager logm;
	private ChatReplacer chatrp;
	private MoneyManager bank;
	private Config config;
	private ReportManager report;
	private Logger breakLog,placeLog,deathLog,PvPLog,cmdLog;
	public static String tag = ChatColor.GREEN + "[" + ChatColor.MAGENTA + "MBES" + ChatColor.GREEN + "] " + ChatColor.WHITE;
	private UpdateNotifyHandler notify;
	
	// Constructor
	
	public MBEPlugin() {
		
		playerNameDB = new NameDataBase();
		FreezeMan = new FreezeManager();
		data = new HashMap<String,Object>();
		config = new Config();
		report = new ReportManager();
		notify = new UpdateNotifyHandler(version, paths);
		
	}
	
	@Override
	public void onLoad() {
		
		// Create files / directorys
		
		CreateFiles.create(enableUpdater);
		
	}
	
	@Override
	public void onEnable() {
		
		config = this.getConfig();
		this.saveConfig();
		
		// Perform updater stuff
		
		if(enableUpdater == true){
			
			Updater.step1();
			Updater.step2(config, this.getLogger());
			this.getLogger().info("You are currently running MbEssentials version: " + version);
			Updater.step3(pUrl, vUrl, wnUrl, paths, version, this.getLogger(), this.getPluginManager());
			
		}

		else{
			
			this.getLogger().info("It appears that you are using a SW hosted minebuilder server!");
			this.getLogger().info("All updates to this plugin are therefore handled by SW.");
			
		}
		
		chatrp = this.getServer().getConfigurationManager().load(this,ChatReplacer.class);
		if(chatrp == null)
			chatrp = new ChatReplacer();
		
		playerNameDB = this.getServer().getConfigurationManager().load(this,NameDataBase.class);
		if(playerNameDB == null)
			playerNameDB = new NameDataBase();
		
		// Register commands and handlers
		
        this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
		this.getPluginManager().registerCommand("sudo",new ModCmds(this.getServer()));
		this.getPluginManager().registerCommand("kickall",new ModCmds(this.getServer()));
		this.getPluginManager().registerCommand("sayas",new ModCmds(this.getServer()));
		this.getPluginManager().registerCommand("tpto",new Tpto(this.getServer()));
		this.getPluginManager().registerCommand("tphere",new Tphere(this.getServer()));
		this.getPluginManager().registerCommand("mbesver",new MbesVer(version));	 
		this.getPluginManager().registerCommand("coords",new Coords(this.getServer()));
		this.getPluginManager().registerCommand("nick",new Nickname(config));
		this.getPluginManager().registerCommand("delnick",new DelNickname(config));

		if(config.isEnablePmSystem())
		{
		
			this.getPluginManager().registerCommand("pm",new PMCmds(this.getServer()));		
			this.getPluginManager().registerCommand("unblock",new PMCmds(this.getServer()));
			this.getPluginManager().registerCommand("block",new PMCmds(this.getServer()));
			this.getPluginManager().registerCommand("blockall",new PMCmds(this.getServer()));
			this.getPluginManager().registerCommand("unblockall",new PMCmds(this.getServer()));
		
		}
		
		this.getPluginManager().registerCommand("mute",new Mute(this.getServer(),data));
		this.getPluginManager().registerCommand("unmute",new Mute(this.getServer(),data));
		this.getPluginManager().registerEventHandler(new MuteHandler(data));
		
		if(config.isEnableMoneySystem())
		{
			 
			bank = this.getServer().getConfigurationManager().load(this,MoneyManager.class);
			 
			if(bank == null)
				bank = new MoneyManager();
			 
			this.getPluginManager().registerCommand("pay",new MoneyCmds(this.getServer(),bank));
			this.getPluginManager().registerCommand("addmoney",new MoneyCmds(this.getServer(),bank));
			this.getPluginManager().registerCommand("rmvmoney",new MoneyCmds(this.getServer(),bank));
		    this.getPluginManager().registerCommand("balance",new MoneyCmds(this.getServer(),bank));
		    this.getPluginManager().registerCommand("resetmoney",new MoneyCmds(this.getServer(),bank));
			this.getPluginManager().registerEventHandler(new AccountMaker(bank));
			 
		 }
		 
		 if(config.isEnableLogs())
		 {
			 logm = new LogManager();
			 
			 if(config.isEnableBreakLog())
			 {
				 breakLog = new Logger("logs/MBE_Logs/Break_Logs/");
				 logm.attachLogger(breakLog);
			 }
			 
			 if(config.isEnableCommandLog())
			 {
				 cmdLog = new Logger("logs/MBE_Logs/Command_Logs/");
				 logm.attachLogger(cmdLog);
			 }
			 
			 if(config.isEnableDeathLog())
			 {
				 deathLog = new Logger("logs/MBE_Logs/Death_Logs/");
				 logm.attachLogger(deathLog);
			 }
			 
			 if(config.isEnablePlaceLog())
			 {
				 placeLog = new Logger("logs/MBE_Logs/Place_Logs/");
				 logm.attachLogger(placeLog);
			 }
			 
			 if(config.isEnablePvPLog())
			 {
				 PvPLog = new Logger("logs/MBE_Logs/PvP_Logs/");
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
			 this.getPluginManager().registerCommand("vwreport",new ReportCmds(this.getServer(),report));
			 this.getPluginManager().registerCommand("delreport",new ReportCmds(this.getServer(),report));
			 
		 }
		 
		 this.getPluginManager().registerCommand("addword",new ChatReplaceCmds(chatrp));
		 this.getPluginManager().registerCommand("delword",new ChatReplaceCmds(chatrp));
		 this.getPluginManager().registerCommand("clearwords",new ChatReplaceCmds(chatrp));
		 this.getPluginManager().registerEventHandler(new ChatReplacerHandler(chatrp));
		 
	     if(enableUpdater == true){ //These commands are only needed for manually hosted servers
	    	 
			 this.getPluginManager().registerCommand("forceupdate", new UpdateCmds(pUrl, vUrl,this.getServer(), version, paths, pUrl));
			 this.getPluginManager().registerCommand("checkupdate", new UpdateCmds(pUrl, vUrl,this.getServer(), version, paths, pUrl));
			 this.getPluginManager().registerEventHandler(this.notify);
			 
		 }
		 
		 this.getPluginManager().registerCommand("freeze",new String[] {"frz"},new Freeze(this.getServer(),FreezeMan));
		 this.getPluginManager().registerCommand("unfreeze",new String[] {"unfrz"},new Freeze(this.getServer(),FreezeMan));
		 this.getPluginManager().registerCommand("website",new String[] {"web","mbe"},new WebsiteCmds());
		 this.getPluginManager().registerEventHandler(new FreezeHandler(FreezeMan));
		 this.getPluginManager().registerEventHandler(new NicknameHandler(config));
		 this.getPluginManager().registerEventHandler(new PMBlockHandler());
		 this.getPluginManager().registerEventHandler(new NameHandler(playerNameDB));
		 this.getPluginManager().registerCommand("fullnames", new String[] {"fullnames","fn","names"},new NameDB(playerNameDB));
		 
		 if(config.isEnableWorldBackupSave())
		 {
			 
		 	this.getPluginManager().registerEventHandler(new WorldBackupHandler(config));

		 }
		 
		this.getLogger().info("MBEssentials Startup Finished!");
		
		if(config.isEnableWorldBackupStart()) {
			
			this.getLogger().info("Starting world backup");
		
			try {
				
				WorldBackup.Backup(this.getServer());
				this.getLogger().info("Finished backing up worlds!");
				
			} catch (IOException e) {
				
				this.getLogger().info("An error occured while trying to backup worlds!");
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	@Override
	public void onDisable(){
		
		this.saveConfig();
		this.notify.stopDominatingTheWorldBoi(); // This was causing issues... Temporary fix :)
		
		if(logm != null) {
			
			if(config.isEnableLogs()) {
				
				try {
						
					logm.closeAll();
						
				} catch (IOException e) {
						
					e.printStackTrace();
						
				this.getLogger().warning("Please report this error to the Space Walrus forums, and we will try and help you!");
				
				}
					
			}
		
			
		}
		
		if(bank != null) {
			
			this.getServer().getConfigurationManager().save(this,bank);
			
		}
		
		this.getServer().getConfigurationManager().save(this,chatrp);
		this.getServer().getConfigurationManager().save(this,report);
		this.getServer().getConfigurationManager().save(this,playerNameDB);
		
		this.getLogger().info("Successfully saved all MBEssentials data");
			
		this.getLogger().info("Have a nice day - from the MBEssentials Team!");
		
		if(enableUpdater == true){
			
			Updater.step4(this.getLogger());
			
		}
		
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


