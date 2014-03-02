package plugins.mbes.misc;

import java.util.ArrayList;

import com.mbserver.api.CommandSender;

public class CmdSender implements CommandSender{
	ArrayList<String>msg = new ArrayList<String>();
	CommandSender cms; 
	
	public CmdSender(CommandSender s) {
		cms = s;
	}

	@Override
	public boolean hasPermission(String perm) {
		return cms.hasPermission(perm);
	}

	@Override
	public String getName() {
		return cms.getName();
	}

	@Override
	public void sendMessage(String message) {
		cms.sendMessage(message);
		msg.add(message);
	}
	
	public String[] getMsg(){
		String rt[] = new String[msg.size()];
		
		for(int a = 0;a < msg.size();a++)
		{
			rt[a] = msg.get(a);
		}
		return rt;
	}

}
