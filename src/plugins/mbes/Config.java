package plugins.mbes;

public class Config {
	 	private boolean enableMoneySystem;
		private boolean enablePmSystem;
	 	private boolean enableDebug;
	 	private boolean enablePlaceBlocker;
	 	private boolean enableLogs;
	 	private boolean enableDeathLog;
	 	private boolean enablePvPLog;
	 	private boolean enableKillLog;
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
		public void setEnableDeathLog(boolean enableDeathLog) {
			this.enableDeathLog = enableDeathLog;
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
		//end log disabler/enabler

	 	
}
	 	
