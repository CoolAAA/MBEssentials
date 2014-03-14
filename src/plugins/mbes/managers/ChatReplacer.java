package plugins.mbes.managers;

import java.util.HashMap;
import java.util.Map;

public class ChatReplacer {
	private Map<String,String>words = new HashMap<String, String>();
	
	public Map<String,String> getMap(){
		return words;
	}
	 
	/**
	 * Add a word to replace in the global chat
	 * 
	 * @param rp - Word to replace
	 * @param tr - Word/Words to replace with
	 */
	public void addWord(String rp,String tr){
		words.put(rp,tr);
	}
	
	/**
	 * Remove a word from the Chat word replacer
	 * 
	 * @param word - The word to remove from the wordReplacer
	 * @return true if the word was removed False if the word was not found
	 */
	public boolean delWord(String word){
		if(words.remove(word) == null)
			return false;
		return true;
	}
	
}
