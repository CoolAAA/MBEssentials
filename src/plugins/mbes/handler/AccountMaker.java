package plugins.mbes.handler;

import plugins.mbes.Config;
import plugins.mbes.misc.MoneyAccount;
import plugins.mbes.managers.MoneyManager;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerLoginEvent;

public class AccountMaker implements Listener{
	
	private MoneyManager bank;
	private Config config;
	
	public AccountMaker(MoneyManager m,Config config) {
		this.bank = m;
		this.config = config;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		boolean made = bank.addAccount(new MoneyAccount(e.getPlayer()));
		
		if(config.isEnableDebug())
		{
			if(made)
				e.getServer().getLogger().info("Account succesfully made for '" + e.getPlayer().getDisplayName() + "'");
			else
				e.getServer().getLogger().info("Account unsuccesfully made for '" + e.getPlayer().getDisplayName() + "'");
		}
	}
}
