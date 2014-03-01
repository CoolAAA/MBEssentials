package plugins.mbes;

public class Config {
	 	private boolean enableMoneySystem;
	 	private boolean enableDebug;
	 	
	 	 public Config() {
	       this.setEnableMoneySystem(true);
	       this.setEnableDebug(true);
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
	 	
}
	 	
