package plugins.mbes.managers;

import java.util.ArrayList;

import plugins.mbes.misc.MoneyAccount;
import com.mbserver.api.game.Player;

public class MoneyManager{
	private ArrayList<MoneyAccount>bank = new ArrayList<MoneyAccount>();
	
	public boolean addAccount(MoneyAccount p){
		if(bank.contains(p))
			return false;
		bank.add(p);
		return true;
	}
	
	public boolean resetAccount(MoneyAccount p){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return false;
		
		bank.set(ind,p);
		return true;
	}
	
	public boolean addMoney(MoneyAccount p,int amount){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return false;
		
		bank.set(ind,bank.get(ind).setAmount(bank.get(ind).getAmount() + amount ));
		return true;
	}
	
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
	
	public boolean setInfinite(Player p,boolean set){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return false;
		
		MoneyAccount temp = bank.get(ind);
		temp.setInf(set);
		return true;
	}
	
	public int getMoney(MoneyAccount p){
		int ind = bank.indexOf(p);
		
		if(ind == -1)
			return -1;
		
		return bank.get(ind).getAmount();
	}
	
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
