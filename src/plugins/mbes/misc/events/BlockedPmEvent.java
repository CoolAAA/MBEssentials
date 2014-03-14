package plugins.mbes.misc.events;

import com.mbserver.api.events.CancellableEvent;
import com.mbserver.api.game.Player;

public class BlockedPmEvent extends CancellableEvent{
	Player sender,reciever;
	
	public BlockedPmEvent(Player from,Player to) {
		reciever = to;
		sender = from;
	}

	public Player getSender() {
		return sender;
	}

	public Player getReciever() {
		return reciever;
	}

}
