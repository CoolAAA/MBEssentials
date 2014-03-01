package plugins.mbes.misc;

import com.mbserver.api.game.Player;

public class MoneyAccount {
	
	Player player;
	int amount;
	String name;
	boolean inf; 
	
	public Player getPlayer() {
		return player;
	}
	
	public MoneyAccount(final Player player) {
		this.player = player;
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
		if(((Player)obj).getDisplayName().equals(this.getName()))
			return true;
		
		return false;
	}
	 
}
