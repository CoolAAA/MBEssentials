package plugins.mbes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	 	private boolean enableMoneySystem;
		private boolean enablePmSystem;
	 	private boolean enableDebug;
	 	private boolean enablePlaceBlocker;
	 	private boolean enableLogs;
	 	private boolean enableDeathLog;
	 	private boolean enablePvPLog;
	 	private boolean enableKillLog;
	 	private boolean enablePlaceLog;
	 	private boolean enableBreakLog;
	 	private boolean enableReportSystem;
	 	private String[] names = {"enableMoneySystem" ,"enablePmSystem" ,"enableDebug" ,"enablePlaceBlocker","enableLogs",
				"enableDeathLog", "enablePvPLog", "enableKillLog", "enablePlaceLog","enableBreakLog","enableReportSystem"};
	 	public boolean isEnableReport() {
			return enableReportSystem;
		}

		public void setEnableReport(boolean enableReport) {
			this.enableReportSystem = enableReport;
		}
		private boolean enableCommandLog;
		private int[] blockedBlockIDs;
	    	private boolean enableToolBlocker;
	    	private int[] blockedToolIDs;

	 	 public Config() {
	       this.setEnableMoneySystem(true);
		   this.setEnablePmSystem(true);
		   
	       this.setEnableDebug(true);
	       this.setEnablePlaceBlocker(false);
	       this.setBlockedBlockIDs(new int[0]);
	       this.setEnableToolBlocker(false);
	       this.setBlockedToolIDs(new int [0]);
	       this.setEnableLogs(true);
	       this.setEnableDeathLog(true);
	       this.setEnableCommandLog(true);
	       this.setEnableKillLog(false);
	       this.setEnablePvPLog(true);
	       this.setEnablePlaceLog(false);
	       this.setEnableBreakLog(false);
	       this.setEnableReport(true);
	 	}
	 	 
	 	public void setEnablePlaceLog(boolean enablePlaceLog) {
			this.enablePlaceLog = enablePlaceLog;
		}
		public boolean isEnableDebug() {
			return enableDebug;
		}
		public void setEnableDebug(boolean enableDebug) {
			this.enableDebug = enableDebug;
		}
		public boolean isEnableMoneySystem() {
			return this.enableMoneySystem;
		}
		public boolean isEnablePmSystem() {
			return this.enablePmSystem;
		}
		public void setEnableMoneySystem(boolean in) {
			this.enableMoneySystem=in;
		}
		public void setEnablePmSystem(boolean in2) {
			this.enablePmSystem=in2;
		}

		//Start placeblock + usetool disabler config stuff here
		//Should I use in-game adding/deleting blocks or tools ID's too? (by command)
		//I should switch to ArrayList in that case...
		public boolean isEnablePlaceBlocker() {
			return enablePlaceBlocker;
		}

		public void setEnablePlaceBlocker(boolean enablePlaceBlocker) {
			this.enablePlaceBlocker = enablePlaceBlocker;
		}

		public int[] getBlockedBlockIDs() {
			return blockedBlockIDs;
		}

		public void setBlockedBlockIDs(int[] blockedBlockIDs) {
			this.blockedBlockIDs = blockedBlockIDs;
		}

		public boolean isEnableToolBlocker() {
			return enableToolBlocker;
		}

		public void setEnableToolBlocker(boolean enableToolBlocker) {
			this.enableToolBlocker = enableToolBlocker;
		}

		public int[] getBlockedToolIDs() {
			return blockedToolIDs;
		}

		public void setBlockedToolIDs(int[] blockedToolIDs) {
			this.blockedToolIDs = blockedToolIDs;
		}
		//End placeblock + usetool disabler here


		//Begin log disbaler/enabler

		public boolean isEnableLogs() {
			return enableLogs;
		}
		public void setEnableLogs(boolean enableLogs) {
			this.enableLogs = enableLogs;
		}
		public boolean isEnableDeathLog() {
			return enableDeathLog;
		}
		public boolean isEnablePvPLog() {
			return enablePvPLog;
		}
		public void setEnablePvPLog(boolean enablePvPLog) {
			this.enablePvPLog = enablePvPLog;
		}
		public boolean isEnableKillLog() {
			return enableKillLog;
		}
		public void setEnableKillLog(boolean enableKillLog) {
			this.enableKillLog = enableKillLog;
		}
		public boolean isEnableCommandLog() {
			return enableCommandLog;
		}
		public void setEnableCommandLog(boolean enableCommandLog) {
			this.enableCommandLog = enableCommandLog;
		}
		public boolean isEnablePlaceLog() {
			return enablePlaceLog;
		}
		public void setEnableDeathLog(boolean enablePlaceLog) {
			this.enablePlaceLog = enablePlaceLog;
		}

		public boolean isEnableBreakLog() {
			return enableBreakLog;
		}
		public void setEnableBreakLog(boolean enableBreakLog) {
			this.enableBreakLog = enableBreakLog;
		}
		//end log disabler/enabler
		
		private boolean[] getValues(){
			return new boolean[] { 	 enableMoneySystem ,enablePmSystem ,enableDebug ,enablePlaceBlocker, enableLogs,
					enableDeathLog, enablePvPLog, enableKillLog, enablePlaceLog	,enableBreakLog,enableReportSystem};
		}
		public void createConfig() throws IOException{
			BufferedWriter bf = new BufferedWriter(new FileWriter("plugins//MBEssentials//config.txt"));
			boolean []val = getValues();
			for(int a = 0;a < names.length;a++)
			{
				bf.write(names[a] + ":" + String.valueOf(val[a]));
				bf.newLine();
			}
			bf.close();
		}
		
		public void setValues(boolean[] val){
			enableMoneySystem = val[0];
			enablePmSystem = val[1];
			enableDebug = val[2];
			enablePlaceBlocker = val[3];
			enableLogs = val[4];
			enableDeathLog = val[5];
			enablePvPLog = val[6];
			enableKillLog = val[7];
			enablePlaceLog = val[8];
			enableBreakLog = val[9];
			enableReportSystem = val[10];
		}
		
		public void readConfig() throws IOException{
			
				String line[] = new String[1];
				boolean[] values = getValues();
				BufferedReader bf = new BufferedReader(new FileReader("plugins//MBEssentials//config.txt"));
				
				for(int a = 0;a < values.length;a++)
				{
					line[0] = bf.readLine();
					
					if(line[0] == null)
						break;
					
					line = line[0].split(":");
					if(line.length == 1)
						continue;
					values[a] = Boolean.parseBoolean(line[1]);
				}
				
				setValues(values);
				bf.close();
				}

}

