package plugins.mbes.commands;

import plugins.mbes.MBEPlugin;
import plugins.mbes.managers.MoneyManager;
import plugins.mbes.misc.MoneyAccount;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.game.Player;

public class MoneyCmds implements CommandExecutor{
	
	private Server s;
	private MoneyManager bank;
	
	public MoneyCmds(Server s,MoneyManager bank) {
		this.bank = bank;
		this.s = s;
	}

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("pay"))
		{
			if(sender.hasPermission("mbes.money") || sender.hasPermission("mbes.*"))
			{
				if(args.length < 2)
					sender.sendMessage(MBEPlugin.tag + "Syntax: " + ChatColor.RED + "/pay " + ChatColor.GREEN + "<payTo> <amount>");
				
				else
				{
					Player to = s.getPlayer(args[0]);
					
					if(to == null)
						sender.sendMessage(MBEPlugin.tag + "Player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					
					else
					{
						
						
						if(sender instanceof Player)
						{
							try{
								int amount = Integer.parseInt(args[1]);
								
								amount = bank.giveMoney(new MoneyAccount((Player)sender),new MoneyAccount(to),amount);
								sender.sendMessage(MBEPlugin.tag + ChatColor.RED + "$" + amount + ChatColor.WHITE + " was given to '" + ChatColor.RED + to.getDisplayName() + ChatColor.WHITE + "' from your account!");
							}catch(NumberFormatException e){
								sender.sendMessage(MBEPlugin.tag + "Enter a valid amount of money!");
							}
						}
						else
						{
							try{
								int amount = Integer.valueOf(args[1]);
								
								bank.addMoney(new MoneyAccount(to), amount);
								sender.sendMessage(MBEPlugin.tag + ChatColor.RED + "$" + amount + ChatColor.WHITE + " was given to '" + ChatColor.RED + to.getDisplayName() + ChatColor.WHITE + "'");
							}catch(NumberFormatException e){
								sender.sendMessage(MBEPlugin.tag + "Enter a valid amount of money!");
							}
						}
					}
				}
			}
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("balance"))
		{
			if(sender.hasPermission("mbes.money") || sender.hasPermission("mbes.*"))
			{
				if(sender instanceof Player)
				{
					int amount = bank.getMoney(new MoneyAccount((Player)sender));
					
					if(amount == -1)
						sender.sendMessage(MBEPlugin.tag + "You don't have a bank account!");
					
					else
						sender.sendMessage(MBEPlugin.tag + "Balance: " + ChatColor.RED + "$" + amount);
				}
				else
					sender.sendMessage(MBEPlugin.tag + "You have to be a player to use this command!");
			}
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("addmoney"))
		{
			if(sender.hasPermission("mbes.mod.addmoney") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length < 2)
					sender.sendMessage(MBEPlugin.tag + "Syntax: " + ChatColor.RED + "/addmoney " + ChatColor.GREEN + "<giveTo> <amount>");
				
				else
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					
					else
					{
						try{
							int amount = Integer.parseInt(args[1]);
							bank.addMoney(new MoneyAccount(temp), amount);
							sender.sendMessage(MBEPlugin.tag + ChatColor.RED + "$" + amount + ChatColor.WHITE + " was given to '" + ChatColor.RED + temp.getDisplayName() + ChatColor.WHITE + "'");
						}catch(NumberFormatException e){
							sender.sendMessage(MBEPlugin.tag + "Please enter a valid amount!");
						}
					}
				}
			}
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		else if(command.equals("rmvmoney"))
		{
			if(sender.hasPermission("mbes.mod.rmvmoney") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length < 2)
					sender.sendMessage(MBEPlugin.tag + "Syntax: " + ChatColor.RED + "/rmvmoney " + ChatColor.GREEN + "<playerName> <amount>");
				
				else
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					
					else
					{
						try{
							int amount = Integer.parseInt(args[1]);
							amount = bank.removeMoney(new MoneyAccount(temp), amount);
							sender.sendMessage(MBEPlugin.tag + ChatColor.RED + "$" + amount + ChatColor.WHITE + " was removed from '" + ChatColor.RED + temp.getDisplayName() + ChatColor.WHITE + "'s account");
						}catch(NumberFormatException e){
							sender.sendMessage(MBEPlugin.tag + "Please enter a valid amount!");
						}
					}
				}
			}
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("resetmoney"))
		{
			if(sender.hasPermission("mbes.mod.resetmoney") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length == 0)
					sender.sendMessage(MBEPlugin.tag + "Syntax: " + ChatColor.RED + "/resetmoney " + ChatColor.GREEN + "<playerName>");
				
				else
				{
					Player temp = s.getPlayer(args[0]);
					
					if(temp == null)
						sender.sendMessage(MBEPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					
					else
					{
							bank.resetAccount(new MoneyAccount(temp));
							sender.sendMessage(MBEPlugin.tag + ChatColor.RED + temp.getDisplayName() + ChatColor.WHITE + "' account was reset!");
						
					}
				}
			}
			else
				sender.sendMessage(MBEPlugin.tag + "You don't have permission to use this command!");
		}
	}
}
