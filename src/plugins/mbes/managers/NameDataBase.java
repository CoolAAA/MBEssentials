package plugins.mbes.managers;

import java.util.HashMap;

public class NameDataBase {
	private static final String FORMAT = "%s  -  %s";
	private HashMap<String,String>data;
	
	public NameDataBase() {
		data = new HashMap<String,String>();
	}
	
	public void setName(String display,String login)
	{
		data.put(display,login);
	}
	
	public String[] getNames(){
		if(data.isEmpty())
			return null;
		
		String[] values = new String[data.size()];
		int count = 0;
		String[] keys = data.keySet().toArray(new String[0]);
		for(;count < data.size();count++)
		{
			String val = data.get(keys[count]);
			values[count] = String.format(NameDataBase.FORMAT,keys[count],val);
		}
		return values;
	}
	
	public String[] getNames(String pre)
	{
		String[] keys = data.keySet().toArray(new String[0]);
		int keycount = 0;
		
		for(String e : keys)
		{
			if(e.startsWith(pre))
				keycount++;
		}
		
		String[] values = new String[keycount];
		
		int count = 0;
		for(;count < data.size();count++)
		{
			if(keys[count].startsWith(pre))
			{
				String val = data.get(keys[count]);
				values[count] = String.format(NameDataBase.FORMAT,keys[count],val);
			}
		}
		return values;
	}
}
