package plugins.mbes.handler;

import plugins.mbes.misc.MoneyAccount;
import plugins.mbes.managers.MoneyManager;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerLoginEvent;

public class AccountMaker implements Listener{
	
	private MoneyManager bank;
	
	public AccountMaker(MoneyManager m) {
		this.bank = m;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		
		@SuppressWarnings("unused") // shut up eclipse
		boolean made = bank.addAccount(new MoneyAccount(e.getPlayer()));

	}
}
