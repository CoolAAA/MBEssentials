package plugins.mbes;

public class Config {
	 	private boolean enableMoneySystem;
	 	private boolean enableDebug;
	 	private boolean enablePlaceBlocker;
		private int[] blockedBlockIDs;
	    	private boolean enableToolBlocker;
	    	private int[] blockedToolIDs;
	 	
	 	 public Config() {
	       this.setEnableMoneySystem(true); //why true? :o
	       this.setEnableDebug(true);
	       this.setEnablePlaceBlocker(false);
	        this.setBlockedBlockIDs(new int[0]);
	        this.setEnableToolBlocker(false);
	        this.setBlockedToolIDs(new int [0]);
	       
	       
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
		public void setEnableMoneySystem(boolean in) {
			this.enableMoneySystem=in;
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
	 	
}
	 	
