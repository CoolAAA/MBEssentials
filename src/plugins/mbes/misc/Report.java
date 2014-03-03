package plugins.mbes.misc;

import com.mbserver.api.game.Player;

public class Report {
	private String name,rp;
	private String reason;
	private int id;
	public Report(int num) {
		id = num;
	}
	public Report(Player p,Player rp,String why) {
		name = p.getDisplayName();
		this.rp = rp.getDisplayName();
		reason = why;
	}
	public String getRp() {
		return rp;
	}

	public void setRp(String rp) {
		this.rp = rp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	

	public String getName() {
		return name;
	}

	public String getReported() {
		return rp;
	}

	public String getReason() {
		return reason;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Report)obj).getId() == this.getId();
	}
	
}
