package plugins.mbes.managers;

import java.util.ArrayList;

public class FreezeManager {

	private boolean isFrozen;
	private ArrayList<String>Names;
	
	public FreezeManager() {
		isFrozen = false;
		Names = new ArrayList<String>();
	}
	
	public boolean isFrozen(String name){
		return Names.contains(name);
	}
	
	public boolean addName(String name){
		if(Names.contains(name))
			return false;
		Names.add(name);
		return true;
	}
	public boolean delName(String name){
		return Names.remove(name);
	}
	
	public boolean isFrozen(){
		return isFrozen;
	}
	
	public void setFrozen(boolean fr){
		isFrozen = fr;
	}
	
}
