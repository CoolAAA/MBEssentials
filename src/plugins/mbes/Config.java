//TODO: CREATE CONFIG FILE

//PLANNED CONFIGURATIONS: (PUT ALL PLANNED CONFIGS HERE)
// *enable-money enables / disables the money system
// *

package a.package.name; //idk the name o.O

public class Config {
	 	private boolean enableMoneySystem;
	 	
	 	 	public Config() {
	        this.setEnableMoneySystem(false);
	 	}
	 		 	public boolean isEnableMoneySystem() {
			return this.enableMoneySystem;
		}
		public void setEnableMoneySystem(boolean in) {
			this.enableMoneySystem=in;
		}
	 	
}
	 	
