package plugins.mbes.managers;

import java.util.ArrayList;

import plugins.mbes.misc.MoneyAccount;
import com.mbserver.api.game.Player;

public class MoneyManager{
	private ArrayList<MoneyAccount>bank = new ArrayList<MoneyAccount>();
	
	/**
	 * Add a account
	 * 
	 * @param p The player to make the account for
	 * @return False if there was already an account for that person
	 * True if there was not
	 */
	public boolean addAccount(MoneyAccount p){
		if(bank.contains(p))
			return false;
		bank.add(p);
		return true;
	}
	
	
	/**
	 * Reset the players account
	 * 
	 * @param p The player to reset
	 * @return True if the account was reset False if not
	 */
	public boolean resetAccount(MoneyAccount p){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return false;
		
		bank.set(ind,p);
		return true;
	}
	
	/**
	 * Add money to a players account
	 * 
	 * @param p player to give
	 * @param amount amount to give
	 * @return if it was successful true if not false
	 */
	public boolean addMoney(MoneyAccount p,int amount){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return false;
		
		bank.set(ind,bank.get(ind).setAmount(bank.get(ind).getAmount() + amount ));
		return true;
	}
	
	/**
	 * Remove money from player
	 * @param p Player
	 * @param amount amount to remove
	 * @return
	 */
	public int removeMoney(MoneyAccount p,int amount){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return -1;
		MoneyAccount temp = bank.get(ind);
		
		bank.set(ind,temp.setAmount(temp.getAmount() - amount));
		
		if(bank.get(ind).getAmount() < 0)
			bank.set(ind,temp.setAmount(0));
		
		return amount;
	}
	
	/**Gives player infinte amount if money currently not used
	 * 
	 * @param p Player to set 
	 * @param set true or false
	 * @return if succcessful 
	 */
	public boolean setInfinite(Player p,boolean set){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return false;
		
		MoneyAccount temp = bank.get(ind);
		temp.setInf(set);
		return true;
	}
	
	/**
	 * @param p Player to see
	 * @return money in the account
	 */
	public int getMoney(MoneyAccount p){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return -1;
		
		return bank.get(ind).getAmount();
	}
	
	/**Gives money from someones account to another player
	 * @param from player to take money from
	 * @param to player to give money to
	 * @param amount amount to give
	 * @return successful or not
	 */
	public int giveMoney(MoneyAccount from,MoneyAccount to,int amount){
		int ind = bank.indexOf(from);
		
		if(ind == -1)
			return -1;
		
		MoneyAccount frm = bank.get(ind);
		
		if(amount > frm.getAmount())
			amount = frm.getAmount();
		
		bank.set(ind,frm.setAmount(frm.getAmount() - amount));
		
		addMoney(to, amount);
		
		return amount;
	}
}
