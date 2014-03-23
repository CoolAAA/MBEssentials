package plugins.mbes.managers;

import java.util.ArrayList;

import plugins.mbes.misc.MoneyAccount;
import com.mbserver.api.game.Player;

public class MoneyManager{
	private ArrayList<MoneyAccount>bank = new ArrayList<MoneyAccount>();
	/**
	 * Return if not enough money in the account
	 */
	public static final int NOT_ENOUGH = -1;
	/**
	 * Returned if the account was not found
	 */
	public static final int NOT_FOUND = -2;
	
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
	 * @return True if the account was reset False if not found
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
	 * @return 0 if successful
	 */
	public int addMoney(MoneyAccount p,int amount){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return MoneyManager.NOT_FOUND;
		
		bank.set(ind,bank.get(ind).setAmount(bank.get(ind).getAmount() + amount ));
		return 0;
	}
	
	/**
	 * Remove money from player
	 * @param account The players money account
	 * @param amount amount to remove
	 * @return 0 if successful or the static fields in MoneyManager class
	 */
	public int removeMoney(MoneyAccount account,int amount){
		int ind = bank.indexOf(account);
		
		if(ind == -1)
			return MoneyManager.NOT_FOUND;
		MoneyAccount temp = bank.get(ind);
		
		int preAmount = this.getMoney(account);
		bank.set(ind,temp.setAmount(temp.getAmount() - amount));
		
		if(bank.get(ind).getAmount() < 0){
			bank.set(ind,temp.setAmount(preAmount));
			return MoneyManager.NOT_ENOUGH;
		}
		
		return 0;
	}
	
	/**Gives player infinte amount of money
	 * 
	 * @param p moneyAccount to set 
	 * @param set true or false
	 * @return True if successful False if moneyaccount was not found
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
			return MoneyManager.NOT_FOUND;
		
		return bank.get(ind).getAmount();
	}
	
	/**Gives money from someones account to another player
	 * 
	 * @param from player to take money from
	 * @param to player to give money to
	 * @param amount amount to give
	 * @return successful or not
	 */
	public int giveMoney(MoneyAccount from,MoneyAccount to,int amount){
		int ind = bank.indexOf(from);
		
		if(ind == -1)
			return MoneyManager.NOT_FOUND;
		
		MoneyAccount frm = bank.get(ind);
		
		if(amount > frm.getAmount())
			return MoneyManager.NOT_ENOUGH;
		
		bank.set(ind,frm.setAmount(frm.getAmount() - amount));
		
		return this.addMoney(to, amount);
		
	}
}
