package ca.sharkmenard.arkadiacore.ItemManagers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ca.sharkmenard.arkadiacore.Main;

public class ItemConfigurationLoader {
	
	private static boolean isLoaded = false;
	private static HashMap<String, ItemStack> itemList;
	private static ArrayList<Integer> hammerDuraInfos;
	
	
	public static void loadConfig() {
		itemList = new HashMap<String, ItemStack>();
		hammerDuraInfos = new ArrayList<Integer>();
		itemList.put("LegendaryBow", loadItem("LegendaryBow", Material.BOW, 1));
		itemList.put("SpeedOrb", loadItem("SpeedOrb", Material.SUGAR, 2));
		itemList.put("StrengthOrb", loadItem("StrengthOrb", Material.REDSTONE, 3));
		itemList.put("NoFallOrb", loadItem("NoFallOrb", Material.GLOWSTONE_DUST, 4));
		itemList.put("Hammer", loadItem("Hammer", Material.IRON_PICKAXE, 5));
		itemList.put("FarmHoe",loadItem("FarmHoe", Material.DIAMOND_HOE, 6));
		itemList.put("BecheHoe",loadItem("BecheHoe", Material.DIAMOND_HOE, 7));
		itemList.put("RandomOre", loadItem("RandomOre", Material.QUARTZ_BLOCK, 8));
		itemList.put("MagicHelmet", loadItem("MagicHelmet", Material.DIAMOND_HELMET, 9));
		itemList.put("MagicBody", loadItem("MagicBody", Material.DIAMOND_CHESTPLATE, 10));
		itemList.put("MagicLegs", loadItem("MagicLegs", Material.DIAMOND_LEGGINGS, 11));
		itemList.put("MagicBoots", loadItem("MagicBoots", Material.DIAMOND_BOOTS, 12));
		itemList.put("MultiTool", loadItem("MultiTool", Material.BLAZE_ROD, 13));
		itemList.put("RepairStar", loadItem("RepairStar", Material.NETHER_STAR, 14));
		
		
		
		isLoaded = true;
	}
	
	//Return null if not loaded
	public static HashMap<String, ItemStack> getLoadedConfig(){
		if(!isLoaded) return null; 
		return itemList;
	}
	
	@SuppressWarnings("deprecation")
	private static ItemStack loadItem(String itemName, Material mat,int customLevel) {
		FileConfiguration config = Main.getInstance().getItemConfig();
		ItemStack item = new ItemStack(mat);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(config.getString(itemName +".DisplayName").replace('&', '§'));
		ArrayList<String> listEnchant = new ArrayList<String>(config.getStringList(itemName + ".Enchantment"));
		boolean enchantAdded;
		for(int i = 0; i < listEnchant.size(); i++) {
			String[] info = listEnchant.get(i).split(":");
			enchantAdded = itemM.addEnchant(Enchantment.getById(Integer.parseInt(info[0])), Integer.parseInt(info[1]), true);
			if(!enchantAdded) {
				Main.getInstance().getServer().getLogger().severe("An Error appeared while adding an enchantement | ID: " + info[0] + " - Level: " + info[1] + " | Item: " + itemM.getDisplayName());
			}
		}
		
		itemM.addEnchant(Enchantment.LURE,customLevel , true);
		itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		ArrayList<String> listLore = new ArrayList<String>(config.getStringList(itemName + ".Lore"));
		ArrayList<String> lore = new ArrayList<String>();
		for(int i = 0; i < listLore.size(); i++) {
			if(itemName == "Hammer" && listLore.get(i).contains("DURABILITY")) {
					String duraCounter = listLore.get(i).split("DURABILITY")[0];
					hammerDuraInfos.add(i);
					hammerDuraInfos.add(duraCounter.length());
					lore.add(ChatColor.translateAlternateColorCodes('&',listLore.get(i)).replaceAll("DURABILITY", String.valueOf(config.getInt("Hammer.Durability"))));

			}else {
			
				lore.add(ChatColor.translateAlternateColorCodes('&',listLore.get(i)));
			}
		}
		if(!lore.isEmpty())
			itemM.setLore(lore);
		item.setItemMeta(itemM);
		
		return item;
	}
	
	public static ArrayList<Integer> hammerInfos() {
		if(!isLoaded) return null; 
		return hammerDuraInfos;

	}
	
	public static ArrayList<Integer> magciArmorEffects(){
		if(!isLoaded) return null; 
		ArrayList<Integer> effects = new ArrayList<Integer>();
		String buffer;
		for(int i = 0; i < Main.getInstance().getItemConfig().getStringList("MagicArmor.Effects").size(); i++ ) {
			buffer = Main.getInstance().getItemConfig().getStringList("MagicArmor.Effects").get(i);
			buffer = buffer.split(":")[0];
			effects.add(Integer.parseInt(buffer));
		}
		return effects;
	}
	public static ArrayList<Integer> magciArmorEffectsLevels(){
		if(!isLoaded) return null; 
		ArrayList<Integer> effectsLevels = new ArrayList<Integer>();
		String buffer;
		for(int i = 0; i < Main.getInstance().getItemConfig().getStringList("MagicArmor.Effects").size(); i++ ) {
			buffer = Main.getInstance().getItemConfig().getStringList("MagicArmor.Effects").get(i);
			buffer = buffer.split(":")[1];
			effectsLevels.add(Integer.parseInt(buffer));
		}
		return effectsLevels;
	}
	
	

}
