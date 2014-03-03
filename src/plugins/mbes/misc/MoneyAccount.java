package plugins.mbes.misc;

import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class MoneyAccount {
	
	int amount;
	String name;
	boolean inf; 
	
	public Player getPlayer(Server s) {
		return s.getPlayerExact(name);
	}
	
	public MoneyAccount(final Player player) {
		this.setName(player.getDisplayName());
		inf = false;
		amount = 0;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public MoneyAccount setAmount(int amount) {
		this.amount = amount;
		return this;
	}
	
	public String getName() {    
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public boolean isInf() {
		return inf;
	}
	
	public void setInf(boolean inf) {
		this.inf = inf;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(((MoneyAccount)obj).getName().equals(this.getName()))
			return true;
		
		return false;
	}
	 
}
