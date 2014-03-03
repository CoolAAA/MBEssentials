package plugins.mbes.managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import plugins.mbes.Config;

public class LogManager {
	private String seperator;
	private String date;
	private Config config;
	public static final int KLOG = 0,DLOG = 1,PLOG = 2,CLOG = 3,PLCLOG = 4, BLOG = 5;


	private String[] dirs = {"Kill_Logs","Death_Logs","PvP Logs","Command_Logs", "Place_Logs", "Break_Logs"};
	private BufferedWriter[] logWriter = new BufferedWriter[5];
	
	
	public LogManager(Config config) throws IOException {
		this.config = config;
		if(System.getProperty("os.name").toLowerCase().contains("windows"))
			seperator = "\r\n";
		else
			seperator = "\n";

		File mbes = new File("logs\\MBE_Logs");
		if(!mbes.exists())
			mbes.mkdir();
			for(int a = 0;a < dirs.length;a++)
			{
				mbes = new File("logs\\MBE_Logs\\" + dirs[a]);
				if(!mbes.exists())
					mbes.mkdir();
			}

		date  = new SimpleDateFormat("dd_MMM_yy_HH_mm").format(new Date());
		date = date + ".txt";
		if(config.isEnableKillLog())
			logWriter[0] = new BufferedWriter(new FileWriter("logs\\MBE_Logs\\" + dirs[0] + "\\" + date));
		if(config.isEnableDeathLog())
			logWriter[1] = new BufferedWriter(new FileWriter("logs\\MBE_Logs\\" + dirs[1] + "\\" + date));
		if(config.isEnablePvPLog())
			logWriter[2] = new BufferedWriter(new FileWriter("logs\\MBE_Logs\\" + dirs[2] + "\\" + date));
		if(config.isEnableCommandLog())
			logWriter[3] = new BufferedWriter(new FileWriter("logs\\MBE_Logs\\" + dirs[3] + "\\" + date));
		if(config.isEnablePlaceLog())
			logWriter[4] = new BufferedWriter(new FileWriter("logs\\MBE_Logs\\" + dirs[4] + "\\" + date));
		if(config.isEnableBreakLog())
			logWriter[5] = new BufferedWriter(new FileWriter("logs\\MBE_Logs\\" + dirs[5] + "\\" + date));
	}

	public void writeEntry(String what,final int log) throws IOException{
		logWriter[log].write(what + seperator + seperator);
		logWriter[log].flush();
	}

	public void close(){

			try {
				if(config.isEnableKillLog())
					logWriter[0].close();

				if(config.isEnableDeathLog())
					logWriter[1].close();

				if(config.isEnablePvPLog())
					logWriter[2].close();

				if(config.isEnableCommandLog())
					logWriter[3].close();


				if(config.isEnablePlaceLog())
					logWriter[4].close();

				if(config.isEnableBreakLog())
					logWriter[5].close();

					
				if(config.isEnablePlaceLog())
					logWriter[4].close();
					
				if(config.isEnableBreakLog())
					logWriter[5].close();
				
			} catch (IOException e) {

			}
	}

}
