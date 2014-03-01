package plugins.mbes.misc;

import com.mbserver.api.events.CancellableEvent;
import com.mbserver.api.exceptions.ThreadedEventModificationException;
import com.mbserver.api.game.Player;

public class PMEvent extends CancellableEvent{
	Player sender,reciever;
	String message;
	
	public PMEvent(Player from,Player to,String msg) {
		sender = from;
		reciever = to;
		message = msg;
	}
	
	
	public String getMessage() {
		return message;
	}

	public Player getSender() {
		return sender;
	}

	public Player getReciever() {
		return reciever;
	}
	
	
}
