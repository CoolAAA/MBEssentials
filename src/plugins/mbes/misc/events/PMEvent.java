package plugins.mbes.misc.events;

import com.mbserver.api.CommandSender;
import com.mbserver.api.events.CancellableEvent;
import com.mbserver.api.game.Player;

public class PMEvent extends CancellableEvent{
	CommandSender sender;
	Player reciever;
	String message;
	
	public PMEvent(CommandSender from,Player to,String msg) {
		sender = from;
		reciever = to;
		message = msg;
	}
	
	
	public String getMessage() {
		return message;
	}

	public CommandSender getSender() {
		return sender;
	}

	public Player getReciever() {
		return reciever;
	}
	
	
}
