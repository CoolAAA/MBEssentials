package plugins.mbes;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import plugins.mbes.commands.ChatReplaceCmds;
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
import plugins.mbes.handler.KillHandler;
import plugins.mbes.handler.LogHandler;
import plugins.mbes.handler.MuteHandler;
import plugins.mbes.handler.NameHandler;
import plugins.mbes.handler.PMBlockHandler;
import plugins.mbes.handler.WorldBackupHandler;
import plugins.mbes.handler.NicknameHandler;
import plugins.mbes.handler.UpdateNotifyHandler;
import plugins.mbes.handler.UpdateHandler;
import plugins.mbes.managers.ChatReplacer;
import plugins.mbes.managers.FreezeManager;
import plugins.mbes.managers.LogManager;
import plugins.mbes.managers.MoneyManager;
import plugins.mbes.managers.NameDataBase;
import plugins.mbes.managers.ReportManager;
import plugins.mbes.misc.Downloader;
import plugins.mbes.misc.Logger;
import plugins.mbes.misc.WorldBackup;

@Manifest(name="MbEssentials",authors = {"TheMushyPeas","AAAA","Abiram"},config = Config.class)

public class MBEPlugin extends MBServerPlugin{
	
	
		
	private HashMap<String,Object>data;
	private NameDataBase playerNameDB;
	private final float version = (float) 1.15;
	private FreezeManager FreezeMan;
	public final static String MANIFEST_NAME = "MbEssentials";
	private final String vUrl = "http://mbessentials.bl.ee/test/version.txt";
	private final String pUrl = "http://mbessentials.bl.ee/test/MbEssentials.jar";
	private final String wnUrl = "http://mbessentials.bl.ee/update/whatsnew.txt";
	
	//////////////////////////////////////////////
	public boolean enableUpdater = true; // Only set this to "false" if the plugin is being compiled for the SW hosted servers - e.g your name is RENS
	////////////////////////////////////////////// This option can NOT be specified in the config, as it is a nessecary part of the plugin.
	
	private final String[] paths = {"plugins/MbEssentials/Data/MbEssentials.jar","plugins/MbEssentials/Data/ver.dat","plugins/MbEssentials/Data/wn.dat"};
	private LogManager logm;
	private ChatReplacer chatrp;
	private MoneyManager bank;
	private Config config;
	private ReportManager report;
	private Logger breakLog,placeLog,deathLog,PvPLog,cmdLog;
	
	public MBEPlugin() {
		playerNameDB = new NameDataBase();
		FreezeMan = new FreezeManager();
		data = new HashMap<String,Object>();
		config = new Config();
		report = new ReportManager();
	}
	
	@Override
	public void onLoad() {
		String[] fileNames = {"plugins/MbEssentials","logs/MBE_Logs","logs/MBE_Logs/Command_Logs","logs/MBE_Logs/Death_Logs"
				,"logs/MBE_Logs/PvP_Logs","logs/MBE_Logs/Place_Logs","logs/MBE_Logs/Break_Logs",};
		
		File file;
		
		for(String e : fileNames)
		{
			file = new File(e);
			
			if(!file.exists())
				file.mkdir();
		}
		
		if(enableUpdater == true){
			if(!new File("plugins/MbEssentials/Data").isDirectory()){
				new File("plugins/MbEssentials/Data").mkdirs();
			}
		}
		else{
			new File("plugins/MbEssentials/Data").delete();
		}
		
	}
	
	@Override
	public void onEnable() {
		
		if(enableUpdater == true){
			File cln = new File("plugins/MbEssentials/version.dat");
			if(cln.exists()) {
				try {
					Downloader.downloadFile("http://mbessentials.bl.ee/update/whatsnew.txt", "plugins/MbEssentials/Data/wn.dat");
				} catch (IOException e) {
						e.printStackTrace();
				}
				cln.delete();
			}
		}
		if(enableUpdater == true){
			File f = new File("plugins/MbEssentials/Data/wn.dat");
			
			if(f.exists()) {
				this.getLogger().info("What's new in this version:");
				this.getLogger().info("");
				try{
					  FileInputStream fstream = new FileInputStream("plugins/MbEssentials/Data/wn.dat");
					  DataInputStream in = new DataInputStream(fstream);
					  BufferedReader br = new BufferedReader(new InputStreamReader(in));
					  String strLine;
					  while ((strLine = br.readLine()) != null)   {
						  this.getLogger().info("");
						  this.getLogger().info(strLine);
						  this.getLogger().info("");
						  Thread.sleep(5000);
					  }
					  in.close();
				}
				
				catch (Exception e){
					
					  e.printStackTrace();
				}
			    
			    boolean success = f.delete();
			    
			    if (!success) {
			    	
			    	this.getLogger().info("IMPORTANT: YOU MUST DELETE THE FILE LOCATED HERE IMMEDIATELY:");
			    	this.getLogger().info("plugins/MbEssentials/Data/wn.dat");
			    	this.getLogger().info("THIS IS ONLY BECAUSE OF UPDATER ISSUES - THIS WILL NEVER HAVE TO BE DONE IN THE FUTURE");
			    	
			    	try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			    	
			    }
		    	
			}
		}
		
		this.getLogger().info("You are currently running MbEssentials version: " + version);
		if(enableUpdater == true){
			this.getLogger().info("Checking for an update to MbEssentials...");
			
			try { 
				if(Downloader.checkUpdate(pUrl, vUrl, wnUrl, paths[0],paths[1],paths[2],version)){
					this.getLogger().info("MbEssentials was successfully updated  to the latest version!");
					Thread.sleep(4000);
					this.getLogger().warning("In order to apply the new MbEssentials update, the server must restart.");
					Thread.sleep(4000);
					this.getLogger().warning("Your server will now shut down in order to apply the update.");
					Thread.sleep(4000);
					this.getLogger().warning("When you start it up again, the latest version of MbEssentials will be installed!");
					Thread.sleep(4000);
					
					this.getPluginManager().registerEventHandler(new UpdateHandler());
						
				}else{
					this.getLogger().info("You are already running the latest version of MbEssentials!");
				}
			} catch (IOException e1) {
				try {
					File lFile = new File("plugins/MbEssentials/Download-Error-" + new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date()));
					lFile.createNewFile();
					PrintWriter err = new PrintWriter(lFile);
					e1.printStackTrace(err);
					this.getLogger().warning("Could not update MbEssentials. An error log was created at:" + lFile.getPath());
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
					this.getLogger().warning("Please report this error to the MbEssentials forums, and we will try and help you!");
				} catch (IOException e2) {
					e2.printStackTrace();
					this.getLogger().warning("Please report this error to the MbEssentials forums, and we will try and help you!");
				}
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		}
		else{
			this.getLogger().info("It appears that you are using a SW hosted minebuilder server!");
			this.getLogger().info("All updates to this plugin are therefore handled by SW.");
		}
		
		this.getLogger().info("Thanks for using MBEssentials by AAAA, Abiram and TheMushyPeas!");
		this.getLogger().info("Please report any bugs and glitches to the forums!");
		this.getLogger().info("Don't forget to check out our website as well for lots of help and instructions: mbessentials.bl.ee");
		config = this.getConfig();
		this.saveConfig();
		chatrp = this.getServer().getConfigurationManager().load(this,ChatReplacer.class);
		if(chatrp == null)
			chatrp = new ChatReplacer();
		

		playerNameDB = this.getServer().getConfigurationManager().load(this,NameDataBase.class);
		
		if(playerNameDB == null)
			playerNameDB = new NameDataBase();
		
        //this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
         	// if(config.isEnableDebug())
        		//this.getLogger().info("Successfully registered command: /kill");

		
		this.getPluginManager().registerCommand("sudo",new ModCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /sudo");
		
		this.getPluginManager().registerCommand("kickall",new ModCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /kickall");
		
		this.getPluginManager().registerCommand("sayas",new ModCmds(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /sayas");
			 
		this.getPluginManager().registerCommand("tpto",new Tpto(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /tpto");
		
		this.getPluginManager().registerCommand("tphere",new Tphere(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /tphere");
		 
		 this.getPluginManager().registerCommand("mbesver",new MbesVer(version));
			 
		this.getPluginManager().registerCommand("coords",new Coords(this.getServer()));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /Coords");
		 
		 this.getPluginManager().registerCommand("nick",new Nickname(config));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /nick");
		 
		 this.getPluginManager().registerCommand("delnick",new DelNickname(config));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /delnick");
		
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
		
		this.getPluginManager().registerCommand("mute",new Mute(this.getServer(),data));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /mute");
		
		this.getPluginManager().registerCommand("unmute",new Mute(this.getServer(),data));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /unmute");
		
		this.getPluginManager().registerEventHandler(new MuteHandler(data));
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
			 this.getLogger().info("Successfully registered command: /delword");
		 
		 this.getPluginManager().registerCommand("clearwords",new ChatReplaceCmds(chatrp));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /clearwords");
		 
		 this.getPluginManager().registerEventHandler(new ChatReplacerHandler(chatrp));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered handler: ChatReplacerHandler");
		 
		 if(enableUpdater == true){ //These commands are only needed for manually hosted servers
			 this.getPluginManager().registerCommand("forceupdate", new UpdateCmds(pUrl, vUrl,this.getServer(), version, paths, pUrl));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /forceupdate");
			 
			 this.getPluginManager().registerCommand("checkupdate", new UpdateCmds(pUrl, vUrl,this.getServer(), version, paths, pUrl));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered command: /checkupdate");
		 }
		 
		 this.getPluginManager().registerCommand("freeze",new String[] {"frz"},new Freeze(this.getServer(),FreezeMan));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /freeze");
		 
		 this.getPluginManager().registerCommand("unfreeze",new String[] {"unfrz"},new Freeze(this.getServer(),FreezeMan));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /unfreeze");
		 
		 this.getPluginManager().registerCommand("website",new String[] {"web","mbe"},new WebsiteCmds());
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered command: /unfreeze");
		 
		 this.getPluginManager().registerEventHandler(new FreezeHandler(FreezeMan));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered handler: FreezeHandler");
		 
		 this.getPluginManager().registerEventHandler(new KillHandler());
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered handler: KillHandler");
		 
		 this.getPluginManager().registerEventHandler(new NicknameHandler(config));
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered handler: NicknameHandler");
		 
		 this.getPluginManager().registerEventHandler(new PMBlockHandler());
		 if(config.isEnableDebug())
			 this.getLogger().info("Successfully registered handler: PMBlockHandler");
		 
		 if(enableUpdater == true){ //These commands are only needed for manually hosted servers
			 this.getPluginManager().registerEventHandler(new UpdateNotifyHandler(version, paths, pUrl));
			 if(config.isEnableDebug())
				 this.getLogger().info("Successfully registered handler: UpdateNotifyHandler");
		 }
		 
		 this.getPluginManager().registerEventHandler(new NameHandler(playerNameDB));
		 this.getPluginManager().registerCommand("fullnames", new String[] {"fullnames","fn","names"},new NameDB(playerNameDB));
		 if(config.isEnableWorldBackupSave())
		 {
		 	this.getPluginManager().registerEventHandler(new WorldBackupHandler(config));
		 	
		 	if(config.isEnableDebug())
		 		this.getLogger().info("Successfully registered handler: WorldBackupSave");
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
		
		if(logm != null)
			if(config.isEnableDebug())
				if(config.isEnableLogs())
					try {
						logm.closeAll();
					} catch (IOException e) {
						e.printStackTrace();
						this.getLogger().warning("Please report this error to the MbEssentials forums, and we will try and help you!");
					}
		
				this.getLogger().info("Successfully closed logger");
				
				if(bank != null)
					this.getServer().getConfigurationManager().save(this,bank);
				
				this.getServer().getConfigurationManager().save(this,chatrp);
				this.getServer().getConfigurationManager().save(this,report);
				this.getServer().getConfigurationManager().save(this,playerNameDB);
			this.getLogger().info("Successfully saved config and bank!");
			this.getLogger().info("Successfully saved report manager and NameDataBase!");
			
		this.getLogger().info("Have a nice day - from the MBEssentials Team!");
		this.getLogger().info("Your feedback is welcome - post it on our forums!");
		
		if(enableUpdater == true){
			if(new File("plugins/MbEssentials/Data/MbEssentials.jar").isFile()){
				
			    FileChannel sourceChannel = null;
			    FileChannel destChannel = null;
			    File source = new File("plugins/MbEssentials/Data/MbEssentials.jar");
			    File dest = new File("plugins/MbEssentials.jar");
			    dest.delete();
			    try {
			    	
			        sourceChannel = new FileInputStream(source).getChannel();
			        destChannel = new FileOutputStream(dest).getChannel();
			        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
			       } catch (IOException e) {
					e.printStackTrace();
				}finally{
			           try {
						sourceChannel.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			           try {
						destChannel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			       }
			
	
			source.delete();
			this.getLogger().warning("All MbEssentials Update Files Were Downloaded And Installed Successfully");
		}
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


