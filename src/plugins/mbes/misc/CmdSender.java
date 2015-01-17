package plugins.mbes.misc;

import java.util.ArrayList;

import com.mbserver.api.dynamic.Resource;
import com.mbserver.api.dynamic.UILine;
import com.mbserver.api.game.EquipmentSlot;
import com.mbserver.api.game.GameMode;
import com.mbserver.api.game.ItemStack;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Material;
import com.mbserver.api.game.Player;
import com.mbserver.api.game.World;

public class CmdSender implements Player{
	private ArrayList<String>msg;
	private Player ref;
	
	public CmdSender(Player player) {
		ref = player;
		msg = new ArrayList<String>();
	}
	
	@Override
	public boolean hasPermission(String permission) {
		return ref.hasPermission(permission);
	}

	@Override
	@Deprecated
	public String getName() {
		return ref.getName();
	}

	@Override
	public String getDisplayName() {
		return ref.getDisplayName();
	}

	@Override
	public String getLoginName() {
		return ref.getLoginName();
	}

	@Override
	public String getBanReason() {
		return ref.getBanReason();
	}

	@Override
	public boolean isBanned() {
		return ref.isBanned();
	}

	@Override
	public void setBanned(boolean banned) {
		ref.setBanned(banned);
	}

	@Override
	public void setBanReason(String reason) {
		ref.setBanReason(reason);
		
	}

	@Override
	public short getHandItem() {
		return ref.getHandItem();
	}

	@Override
	public void teleport(Location location) {
		ref.teleport(location);
	}

	@Override
	public void teleport(int x, int y, int z) {
		ref.teleport(x, y, z);
		
	}

	@Override
	public void teleport(World world, int x, int y, int z) {
		ref.teleport(world, x, y, z);
	}

	@Override
	public GameMode getGameMode() {
		return ref.getGameMode();
	}

	@Override
	public void setGameMode(GameMode gamemode) {
		ref.setGameMode(gamemode);
	}

	@Override
	public Location getLocation() {
		return ref.getLocation();
	}

	@Override
	public Location getSpawn() {
		return ref.getSpawn();
	}

	@Override
	public void setSpawn(Location spawn) {
		ref.setSpawn(spawn);
	}

	@Override
	public ItemStack[] getInventory() {
		return ref.getInventory();
	}

	@Override
	public void setItemSlot(int slot, ItemStack newItem, boolean sendPacket) {
		ref.setItemSlot(slot, newItem, sendPacket);
	}

	@Override
	public void setEquipmentSlot(EquipmentSlot slot, ItemStack newItem) {
		ref.setEquipmentSlot(slot, newItem);
	}

	@Override
	public ItemStack getEquippedItem(EquipmentSlot slot) {
		return ref.getEquippedItem(slot);
	}

	@Override
	public boolean giveItem(ItemStack stack, boolean sendPacket) {
		return ref.giveItem(stack, sendPacket);
	}

	@Override
	public boolean giveItem(short itemID, int amount, boolean sendPacket) {
		return ref.giveItem(itemID, amount, sendPacket);
	}

	@Override
	public boolean giveItem(Material material, Number amount, boolean sendPacket) {
		return ref.giveItem(material, amount, sendPacket);
	}

	@Override
	public void playSound(Resource apiSound) {
		ref.playSound(apiSound);
	}

	@Override
	public void playSound(Resource sound, float volume) {
		ref.playSound(sound, volume);
		
	}

	@Override
	public boolean isDead() {
		return ref.isDead();
	}

	@Override
	public void sendMessage(String message) {
		ref.sendMessage(message);
		
		msg.add(message);
	}

	@Override
	public void executeCommand(String command, String[] args) {
		ref.executeCommand(command, args);
	}

	@Override
	public void kick(String kickReason) {
		ref.kick(kickReason);
		
	}

	@Override
	public void setMetaData(String key, Object value)
			throws NullPointerException {
		ref.setMetaData(key, value);
		
	}

	@Override
	public void removeMetaData(String key) {
		ref.removeMetaData(key);
	}

	@Override
	public <T> T getMetaData(String key, T defaultValue)
			throws ClassCastException {
		return ref.getMetaData(key, defaultValue);
	}

	@Override
	public void drawLine(UILine line) {
		ref.drawLine(line);
		
	}

	@Override
	public void drawLine(UILine line, boolean sendPacket) {
		ref.drawLine(line, sendPacket);
		
	}

	@Override
	public void clearLines() {
		ref.clearLines();
		
	}

	@Override
	public void clearLines(boolean sendPacket) {
		ref.clearLines(sendPacket);
		
	}
	
	public String[] getMessage(){
		return msg.toArray(new String[0]);
	}
	
}
