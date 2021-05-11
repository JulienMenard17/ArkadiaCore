package ca.sharkmenard.arkadiacore.EnchantLore;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ca.sharkmenard.arkadiacore.Main;

public class EnchantLoreListener implements Listener {
	
	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		ItemMeta meta = e.getItem().getItemMeta();
		String line = "§7§m-------------------";
		ArrayList<String> lore;
		if(meta.hasLore()) {
			lore = new ArrayList<String>(meta.getLore());
		}
		lore = new ArrayList<String>();
		int start = lore.indexOf(line);
		if(start == -1) {
			lore.add(line);
		}else {
			for(int l = start; l < lore.size(); l++) {
				lore.remove(l);
			}
		}
		enchantMapToLore(e.getEnchantsToAdd(), lore);
		lore.add(line);
		
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setLore(lore);
		e.getItem().setItemMeta(meta);
	}
	
	@EventHandler
	public void onAnvil(InventoryClickEvent e) {
		if(e.isCancelled())
			return;
		if(e.getView().getTopInventory().getType().equals(InventoryType.ANVIL) && e.getClickedInventory() != null && e.getView().getTopInventory() instanceof AnvilInventory) {
			AnvilInventory inv = (AnvilInventory) e.getView().getTopInventory();
			Bukkit.getScheduler().runTaskLater(Main.getInstance(),new Runnable() {
				
				@Override
				public void run() {
					if(isItemNotNull(inv.getItem(2))) {
						if((e.getClickedInventory().equals(e.getView().getTopInventory()) && e.getSlot() != 2) || e.getClickedInventory().equals(e.getView().getBottomInventory())) {
							ItemStack result = inv.getItem(2);
							ItemMeta meta = result.getItemMeta();
							String line = "§7§m-------------------";
							ArrayList<String> lore;
							if(meta.hasLore()) {
								lore = new ArrayList<String>(meta.getLore());
							}
							lore = new ArrayList<String>();
							int start = lore.indexOf(line);
							if(start == -1) {
								lore.add(line);
							}else {
								for(int l = start; l < lore.size(); l++) {
									lore.remove(l);
								}
							}
							enchantMapToLore(meta.getEnchants(), lore);
							lore.add(line);
							
							meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							meta.setLore(lore);
							result.setItemMeta(meta);
							inv.setItem(2, result);
						}
					}					
				}
			} , 0);
		
		}
	}
	
	/*
	@EventHandler
	public void onPickUpItem(PlayerPickupItemEvent e) {
		if (!e.isCancelled())
			if (e.getItem().getItemStack() != null) {
				ItemStack item = e.getItem().getItemStack();
				if(e.getItem().getItemStack().hasItemMeta() && e.getItem().getItemStack().getItemMeta().hasEnchants()) {
					ItemMeta meta = e.getItem().getItemStack().getItemMeta();
					String line = "§7§m-------------------";
					ArrayList<String> lore;
					if(meta.hasLore()) {
						lore = new ArrayList<String>(meta.getLore());
					}
					lore = new ArrayList<String>();
					int start = lore.indexOf(line);
					if(start == -1) {
						lore.add(line);
					}else {
						for(int l = start; l < lore.size(); l++) {
							lore.remove(l);
						}
					}
					enchantMapToLore(meta.getEnchants(), lore);
					lore.add(line);
					
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setLore(lore);
					item.setItemMeta(meta);
					e.getItem().setItemStack(item);
					
				}
			}
	}
	*/
	
	private boolean isItemNotNull(ItemStack item) {
		if(item != null && item.getType() != null && item.getType() != Material.AIR) {
			return true;
		}
		return false;
	}
	
	
	public static void enchantMapToLore(Map<Enchantment,Integer> map, ArrayList<String> lore) {
		ArrayList<Enchantment> enchants = new ArrayList<Enchantment>(map.keySet());
		if(enchants.contains(Enchantment.PROTECTION_ENVIRONMENTAL)) {
			lore.add("§7⦁ §bProtection : §3" + map.get(Enchantment.PROTECTION_ENVIRONMENTAL));
		}
		if(enchants.contains(Enchantment.PROTECTION_FIRE)) {
			lore.add("§7⦁ §bFire Protection : §3" + map.get(Enchantment.PROTECTION_FIRE));
		}
		if(enchants.contains(Enchantment.PROTECTION_FALL)) {
			lore.add("§7⦁ §bFeather Falling : §3" + map.get(Enchantment.PROTECTION_FALL));
		}
		if(enchants.contains(Enchantment.PROTECTION_EXPLOSIONS)) {
			lore.add("§7⦁ §bBlast Protection : §3" + map.get(Enchantment.PROTECTION_EXPLOSIONS));
		}
		if(enchants.contains(Enchantment.PROTECTION_PROJECTILE)) {
			lore.add("§7⦁ §bProjectile Protection : §3" + map.get(Enchantment.PROTECTION_PROJECTILE));
		}
		if(enchants.contains(Enchantment.OXYGEN)) {
			lore.add("§7⦁ §bRespiration : §3" + map.get(Enchantment.OXYGEN));
		}
		if(enchants.contains(Enchantment.WATER_WORKER)) {
			lore.add("§7⦁ §bAqua Affinity : §3" + map.get(Enchantment.WATER_WORKER));
		}
		if(enchants.contains(Enchantment.THORNS)) {
			lore.add("§7⦁ §bThorns : §3" + map.get(Enchantment.THORNS));
		}
		if(enchants.contains(Enchantment.DEPTH_STRIDER)) {
			lore.add("§7⦁ §bDepth Strider : §3" + map.get(Enchantment.DEPTH_STRIDER));
		}
		if(enchants.contains(Enchantment.DAMAGE_ALL)) {
			lore.add("§7⦁ §bSharpness : §3" + map.get(Enchantment.DAMAGE_ALL));
		}
		if(enchants.contains(Enchantment.DAMAGE_UNDEAD)) {
			lore.add("§7⦁ §bSmite : §3" + map.get(Enchantment.DAMAGE_UNDEAD));
		}
		if(enchants.contains(Enchantment.DAMAGE_ARTHROPODS)) {
			lore.add("§7⦁ §bBane of Arthropods : §3" + map.get(Enchantment.DAMAGE_ARTHROPODS));
		}
		if(enchants.contains(Enchantment.KNOCKBACK)) {
			lore.add("§7⦁ §bKnockback : §3" + map.get(Enchantment.KNOCKBACK));
		}
		if(enchants.contains(Enchantment.FIRE_ASPECT)) {
			lore.add("§7⦁ §bFire Aspect : §3" + map.get(Enchantment.FIRE_ASPECT));
		}
		if(enchants.contains(Enchantment.LOOT_BONUS_MOBS)) {
			lore.add("§7⦁ §bLooting : §3" + map.get(Enchantment.LOOT_BONUS_MOBS));
		}
		if(enchants.contains(Enchantment.DIG_SPEED)) {
			lore.add("§7⦁ §bEfficiency : §3" + map.get(Enchantment.DIG_SPEED));
		}
		if(enchants.contains(Enchantment.SILK_TOUCH)) {
			lore.add("§7⦁ §bSilk Touch : §3" + map.get(Enchantment.SILK_TOUCH));
		}
		if(enchants.contains(Enchantment.DURABILITY)) {
			lore.add("§7⦁ §bUnbreaking : §3" + map.get(Enchantment.DURABILITY));
		}
		if(enchants.contains(Enchantment.LOOT_BONUS_BLOCKS)) {
			lore.add("§7⦁ §bFortune : §3" + map.get(Enchantment.LOOT_BONUS_BLOCKS));
		}
		if(enchants.contains(Enchantment.ARROW_DAMAGE)) {
			lore.add("§7⦁ §bPower : §3" + map.get(Enchantment.ARROW_DAMAGE));
		}
		if(enchants.contains(Enchantment.ARROW_KNOCKBACK)) {
			lore.add("§7⦁ §bPunch : §3" + map.get(Enchantment.ARROW_KNOCKBACK));
		}
		if(enchants.contains(Enchantment.ARROW_FIRE)) {
			lore.add("§7⦁ §bFlame : §3" + map.get(Enchantment.ARROW_FIRE));
		}
		if(enchants.contains(Enchantment.ARROW_INFINITE)) {
			lore.add("§7⦁ §bInfinity : §3" + map.get(Enchantment.ARROW_INFINITE));
		}
		if(enchants.contains(Enchantment.LUCK)) {
			lore.add("§7⦁ §bLuck of the Sea : §3" + map.get(Enchantment.LUCK));
		}
		if(enchants.contains(Enchantment.LURE)) {
			lore.add("§7⦁ §bLure : §3" + map.get(Enchantment.LURE));
		}
		
		/* Copy Paste
		 if(enchants.contains(Enchantment.)) {
			lore.add("§6 " + map.get(Enchantment.) );
		}
		 */
	}

}
