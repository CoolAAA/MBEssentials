package plugins.mbes.commands;

import plugins.mbes.misc.MoneyManager;
import plugins.mbes.misc.MoneyAccount;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class MoneyCmds implements CommandExecutor{
	
	Server s;
	MoneyManager bank;
	
	public MoneyCmds(Server s,MoneyManager bank) {
		this.bank = bank;
		this.s = s;
	}

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("pay"))
		{
			if(sender.hasPermission("mbes.money"))
			{
				if(args.length < 2)
					sender.sendMessage("Syntax:/pay <payTo> <amount>");
				
				else
				{
					Player to = s.getPlayer(args[0]);
					
					if(to == null)
						sender.sendMessage("Player '" + args[0] + "' was not found!");
					
					else
					{
						
						
						if(sender instanceof Player)
						{
							try{
								int amount = Integer.parseInt(args[1]);
								
								amount = bank.giveMoney(new MoneyAccount((Player)sender),new MoneyAccount(to),amount);
								sender.sendMessage("$" + amount + " was given to '" + to.getDisplayName() + "' from your account!");
							}catch(NumberFormatException e){
								sender.sendMessage("Enter a valid amount of money!");
							}
						}
						else
						{
							try{
								int amount = Integer.valueOf(args[1]);
								
								bank.addMoney(new MoneyAccount(to), amount);
								sender.sendMessage("$" + amount + " was given to '" + to.getDisplayName() + "'");
							}catch(NumberFormatException e){
								sender.sendMessage("Enter a valid amount of money!");
							}
						}
					}
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("balance"))
		{
			if(sender.hasPermission("mbes.money"))
			{
				if(sender instanceof Player)
				{
					int amount = bank.getMoney(new MoneyAccount((Player)sender));
					
					if(amount == -1)
						sender.sendMessage("You don't have a bank account!");
					
					else
						sender.sendMessage("Balance:" + amount);
				}
				else
					sender.sendMessage("You have to be a player to use this command!");
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("addmoney"))
		{
			if(sender.hasPermission("mbes.mod.addmoney"))
			{
				if(args.length < 2)
					sender.sendMessage("Syntax:/addmoney <giveTo> <amount>");
				
				else
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
					
					else
					{
						try{
							int amount = Integer.parseInt(args[1]);
							bank.addMoney(new MoneyAccount(temp), amount);
							sender.sendMessage("$" + amount + " was given to '" + temp.getDisplayName() + "'");
						}catch(NumberFormatException e){
							sender.sendMessage("Please enter a valid amount!");
						}
					}
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		else if(command.equals("rmvmoney"))
		{
			if(sender.hasPermission("mbes.mod.rmvmoney"))
			{
				if(args.length < 2)
					sender.sendMessage("Syntax:/rmvmoney <playerName> <amount>");
				
				else
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
					
					else
					{
						try{
							int amount = Integer.parseInt(args[1]);
							amount = bank.removeMoney(new MoneyAccount(temp), amount);
							sender.sendMessage("$" + amount + " was removed from '" + temp.getDisplayName() + "' account!");
						}catch(NumberFormatException e){
							sender.sendMessage("Please enter a valid amount!");
						}
					}
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
		
		else if(command.equals("resetmoney"))
		{
			if(sender.hasPermission("mbes.mod.resetmoney"))
			{
				if(args.length == 0)
					sender.sendMessage("Syntax:/resetmoney <playerName>");
				
				else
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage("The player '" + args[0] + "' was not found!");
					
					else
					{
							bank.resetAccount(new MoneyAccount(temp));
							sender.sendMessage(temp.getDisplayName() + "' account was reset!");
						
					}
				}
			}
			else
				sender.sendMessage("You don't have permission to use this command!");
		}
	}
}
