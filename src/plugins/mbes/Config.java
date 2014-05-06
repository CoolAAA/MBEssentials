package plugins.mbes;

import java.util.HashMap;

import com.mbserver.api.game.Player;


public class Config {
	
	   	public static Object configObj;
		private HashMap<String, String> nickname;
		private boolean firstUse;
		private boolean enableMoneySystem;
		private boolean enablePmSystem;
	 	private boolean enableDebug;
	 	private boolean enablePlaceBlocker;
	 	private boolean enableItemBlocker;
	 	private boolean enableLogs;
	 	private boolean enableDeathLog;
	 	private boolean enablePvPLog;
	 	private boolean enableKillLog;
	 	private boolean enablePlaceLog;
	 	private boolean enableBreakLog;
	 	private boolean enableCommandLog;
	 	private boolean enableReportSystem;
	 	private boolean enableWorldBackupStart;
	 	private boolean enableWorldBackupSave;
		private int[] blockedBlockIDs;
	    	private int[] blockedToolIDs;
	    	private int amountOfBackupsPerDay;
	    	private String nicknamePrefix;
	    
	 	public Config() {
	 		
	 	   this.nickname = new HashMap<String, String>();
	       	   this.setEnableMoneySystem(true);
		   this.setEnablePmSystem(true);
	 	   this.setEnableDebug(true);
	       	   this.setEnablePlaceBlocker(false);
	           this.setBlockedBlockIDs(new int[0]);
	           this.setEnableItemBlocker(false);
	       	   this.setBlockedToolIDs(new int [0]);
	           this.setEnableLogs(true);
	           this.setEnableDeathLog(true);
	           this.setEnableCommandLog(true);
	           this.setEnableKillLog(false);
	           this.setEnablePvPLog(true);
	           this.setEnablePlaceLog(false);
	           this.setEnableBreakLog(false);
	           this.setEnableReport(true);
	           this.setEnableWorldBackupSave(false);
	           this.setEnableWorldBackupStart(true);
	           this.setEnableItemBlocker(true);
	           this.setFirstUse(true);
	           this.setAmountOfBackupsPerDay(2);
	           this.setNicknamePrefix("~");
	           
	 	}
	 	
	    	public boolean isFirstUse() {
			return firstUse;
		}

		public void setFirstUse(boolean firstUse) {
			this.firstUse = firstUse;
		}
		public boolean isEnableReport() {
			return enableReportSystem;
		}

		public void setEnableReport(boolean enableReport) {
			this.enableReportSystem = enableReport;
		}
		

	 	public boolean isEnableItemBlocker() {
			return enableItemBlocker;
		}

		public void setEnableItemBlocker(boolean enableItemBlocker) {
			this.enableItemBlocker = enableItemBlocker;
		}

		public boolean isEnableWorldBackupStart() {
			return enableWorldBackupStart;
		}

		public void setEnableWorldBackupStart(boolean enableWorldBackupStart) {
			this.enableWorldBackupStart = enableWorldBackupStart;
		}

		public boolean isEnableWorldBackupSave() {
			return enableWorldBackupSave;
		}

		public void setEnableWorldBackupSave(boolean enableWorldBackupSave) {
			this.enableWorldBackupSave = enableWorldBackupSave;
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

		public int[] getBlockedToolIDs() {
			return blockedToolIDs;
		}

		public void setBlockedToolIDs(int[] blockedToolIDs) {
			this.blockedToolIDs = blockedToolIDs;
		}

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

		public int getAmountOfBackupsPerDay() {
			return amountOfBackupsPerDay;
		}
		public void setAmountOfBackupsPerDay(int amountOfBackupsPerDay) {
			this.amountOfBackupsPerDay = amountOfBackupsPerDay;
		}
		
		public void setPlayerNickname(Player player, String nickname) {
		      this.nickname.put(player.getLoginName(), nickname);
		}

		public String getPlayerNickname(Player player) {
		      return this.nickname.get(player.getLoginName());
		}
		public void setNicknamePrefix(String nickprefix) {
			this.nicknamePrefix = nickprefix;
		}
		public String getNicknamePrefix() {
			return nicknamePrefix;
		}
		

}

