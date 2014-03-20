package plugins.mbes.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;
import com.mbserver.api.Server;

public class Coords implements CommandExecutor {
	private Server server;

	public Coords( Server server ) {
		this.server = server;
	}

	@Override
	public void execute( String command, CommandSender sender, String[] args, String label ) {
		if ( !sender.hasPermission("mbes.cmds.coords") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.cmds.*")) {
			sender.sendMessage("You do not have permission to use /" + label);
			return;
		}

		Player target = null;
		if ( args.length == 0 && sender instanceof Player )
			target = (Player) sender;
		else {
			if ( args.length == 1 ) {
				target = this.server.getPlayer(args[0]);
				
				if (target == null) {
					sender.sendMessage("Could not find the player: " + args[0] + ".");
					return;
				}
			
		}else {
				sender.sendMessage("Usage: /coords <playerName>");
				return;
			}

		Location location = target.getLocation();
		sender.sendMessage(String.format("The coordinates of %s are X: %d, Y: %d, Z: %d in world %s."
			,target.getDisplayName(),
			location.getBlockX(),
			location.getBlockY(),
			location.getBlockZ(),
			location.getWorld().getWorldName()
			));
		}
	}
}
