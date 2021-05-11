package ca.sharkmenard.arkadiacore.ItemManagers;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.EnchantLore.EnchantLoreListener;

public class UpdateItemListener implements Listener {


	@EventHandler
	public void onPickUpItem(PlayerPickupItemEvent e) {
		if (!e.isCancelled())
			if (e.getItem().getItemStack() != null) {
				ItemStack item = e.getItem().getItemStack();
				
				if (realItem(item, Material.BOW, 1)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "LegendaryBow"), "LegendaryBow"));
				}else
				if (realItem(item, Material.SUGAR, 2)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "SpeedOrb"), "SpeedOrb"));
				}else
				if (realItem(item, Material.REDSTONE, 3)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "StrengthOrb"), "StrengthOrb"));
				}else
				if (realItem(item, Material.GLOWSTONE_DUST, 4)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "NoFallOrb"), "NoFallOrb"));
				}else
				if (realItem(item, Material.IRON_PICKAXE, 5)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "Hammer"), "Hammer"));
				}else
				if (realItem(item, Material.DIAMOND_HOE, 6)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "FarmHoe"), "FarmHoe"));
				}else
				if (realItem(item, Material.DIAMOND_HOE, 7)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "BecheHoe"), "BecheHoe"));
				}else
				if (realItem(item, Material.QUARTZ_BLOCK, 8)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "RandomOre"), "RandomOre"));
				}else
				if (realItem(item, Material.DIAMOND_HELMET, 9)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "MagicHelmet"), "MagicHelmet"));
				}else
				if (realItem(item, Material.DIAMOND_CHESTPLATE, 10)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "MagicBody"), "MagicBody"));
				}else
				if (realItem(item, Material.DIAMOND_LEGGINGS, 11)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "MagicLegs"), "MagicLegs"));
				}else
				if (realItem(item, Material.DIAMOND_BOOTS, 12)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "MagicBoots"), "MagicBoots"));
				}else
				if (realItem(item, Material.BLAZE_ROD, 13)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "MultiTool"), "MultiTool"));
				}else
				if (realItem(item, Material.NETHER_STAR, 14)) {
					e.getItem().setItemStack(updateItem(item, getNotSameDatas(item, "RepairStar"), "RepairStar"));
				}else
				if(isEnchantableItem(item)){
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
						EnchantLoreListener.enchantMapToLore(meta.getEnchants(), lore);
						lore.add(line);
						
						meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						meta.setLore(lore);
						item.setItemMeta(meta);
						e.getItem().setItemStack(item);
						
					}
				}
				
			}
	}
	/**
	 * Check if the item is a naturally enchantable item
	 * 
	 * @param item The Itemstack to check
	 * 
	 * @return true if item is naturally enchantable by a player
	 */
	public boolean isEnchantableItem(ItemStack item) {
		switch(item.getType()) {
		case ENCHANTED_BOOK:
		case LEATHER_HELMET :
		case LEATHER_CHESTPLATE:
		case LEATHER_LEGGINGS:
		case LEATHER_BOOTS:
		case CHAINMAIL_HELMET:
		case CHAINMAIL_CHESTPLATE:
		case CHAINMAIL_LEGGINGS:
		case CHAINMAIL_BOOTS:
		case IRON_HELMET:
		case IRON_CHESTPLATE:
		case IRON_LEGGINGS:
		case IRON_BOOTS:
		case GOLD_HELMET:
		case GOLD_CHESTPLATE:
		case GOLD_LEGGINGS:
		case GOLD_BOOTS:
		case DIAMOND_HELMET:
		case DIAMOND_CHESTPLATE:
		case DIAMOND_LEGGINGS:
		case DIAMOND_BOOTS:
		case FLINT_AND_STEEL:
		case WOOD_HOE:
		case WOOD_PICKAXE:
		case WOOD_AXE:
		case WOOD_SPADE:
		case STONE_HOE:
		case STONE_PICKAXE:
		case STONE_AXE:
		case STONE_SPADE:
		case IRON_HOE:
		case IRON_PICKAXE:
		case IRON_AXE:
		case IRON_SPADE:
		case GOLD_HOE:
		case GOLD_PICKAXE:
		case GOLD_AXE:
		case GOLD_SPADE:
		case DIAMOND_HOE:
		case DIAMOND_PICKAXE:
		case DIAMOND_AXE:
		case DIAMOND_SPADE:
		case FISHING_ROD:
		case CARROT_STICK:
		case SHEARS:
		case BOW:
		case WOOD_SWORD:
		case STONE_SWORD:
		case IRON_SWORD:
		case GOLD_SWORD:
		case DIAMOND_SWORD:
			return true;
		default:
			return false;
		}		
	}

	@EventHandler
	public void onClickInHand(PlayerInteractEvent e) {
		if (!e.isCancelled())
			if (e.getItem() != null) {
				ItemStack item = e.getItem();
				if (realItem(item, Material.BOW, 1)) {
					item = updateItem(item, getNotSameDatas(item, "LegendaryBow"), "LegendaryBow");
					if(!getNotSameDatas(item, "LegendaryBow").isEmpty())
						e.getPlayer().setItemInHand(item);
				}
				if (realItem(item, Material.SUGAR, 2)) {
					item = updateItem(item, getNotSameDatas(item, "SpeedOrb"), "SpeedOrb");
					if(!getNotSameDatas(item, "SpeedOrb").isEmpty())
						e.getPlayer().setItemInHand(item);
				}
				if (realItem(item, Material.REDSTONE, 3)) {
					item = updateItem(item, getNotSameDatas(item, "StrengthOrb"), "StrengthOrb");
					if(!getNotSameDatas(item, "StrengthOrb").isEmpty())
						e.getPlayer().setItemInHand(item);	
				}
				if (realItem(item, Material.GLOWSTONE_DUST, 4)) {
					item = updateItem(item, getNotSameDatas(item, "NoFallOrb"), "NoFallOrb");
					if(!getNotSameDatas(item, "NoFallOrb").isEmpty())
						e.getPlayer().setItemInHand(item);
				}
				if (realItem(item, Material.IRON_PICKAXE, 5)) {
					item = updateItem(item, getNotSameDatas(item, "Hammer"), "Hammer");
					if(!getNotSameDatas(item, "Hammer").isEmpty())
						e.getPlayer().setItemInHand(item);
					
				}
				if (realItem(item, Material.DIAMOND_HOE, 6)) {
					item = updateItem(item, getNotSameDatas(item, "FarmHoe"), "FarmHoe");
					if(!getNotSameDatas(item, "FarmHoe").isEmpty())
						e.getPlayer().setItemInHand(item);
				}
				if (realItem(item, Material.DIAMOND_HOE, 7)) {
					item = updateItem(item, getNotSameDatas(item, "BecheHoe"), "BecheHoe");
					if(!getNotSameDatas(item, "BecheHoe").isEmpty())
						e.getPlayer().setItemInHand(item);
				}
				if (realItem(item, Material.QUARTZ_BLOCK, 8)) {
					item = updateItem(item, getNotSameDatas(item, "RandomOre"), "RandomOre");
					if(!getNotSameDatas(item, "RandomOre").isEmpty()) {
						e.getPlayer().setItemInHand(item);
						e.setCancelled(true);
					}
				}
				if (realItem(item, Material.DIAMOND_HELMET, 9)) {
					item = updateItem(item, getNotSameDatas(item, "MagicHelmet"), "MagicHelmet");
					if(!getNotSameDatas(item, "MagicHelmet").isEmpty()) {
						e.getPlayer().setItemInHand(item);
						e.setCancelled(true);
					}
				}
				if (realItem(item, Material.DIAMOND_CHESTPLATE, 10)) {
					item = updateItem(item, getNotSameDatas(item, "MagicBody"), "MagicBody");
					if(!getNotSameDatas(item, "MagicBody").isEmpty()) {
						e.getPlayer().setItemInHand(item);
						e.setCancelled(true);
					}
				}
				if (realItem(item, Material.DIAMOND_LEGGINGS, 11)) {
					item = updateItem(item, getNotSameDatas(item, "MagicLegs"), "MagicLegs");
					if(!getNotSameDatas(item, "MagicLegs").isEmpty()) {
						e.getPlayer().setItemInHand(item);
						e.setCancelled(true);
					}
				}
				if (realItem(item, Material.DIAMOND_BOOTS, 12)) {
					item = updateItem(item, getNotSameDatas(item, "MagicBoots"), "MagicBoots");
					if(!getNotSameDatas(item, "MagicBoots").isEmpty()) {
						e.getPlayer().setItemInHand(item);
						e.setCancelled(true);
					}
				}
				if (realItem(item, Material.BLAZE_ROD, 13)) {
					item = updateItem(item, getNotSameDatas(item, "MultiTool"), "MultiTool");
					if(!getNotSameDatas(item, "MultiTool").isEmpty()) {
						e.getPlayer().setItemInHand(item);
					}
				}
				if (realItem(item, Material.NETHER_STAR, 14)) {
					item = updateItem(item, getNotSameDatas(item, "RepairStar"), "RepairStar");
					if(!getNotSameDatas(item, "RepairStar").isEmpty()) {
						e.getPlayer().setItemInHand(item);
					}
				}
			}
	}

	@EventHandler
	public void onJoining(PlayerJoinEvent e) {
		Inventory inv = e.getPlayer().getInventory();
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack item = inv.getItem(i);
			if (realItem(item, Material.BOW, 1)) {
				item = updateItem(item, getNotSameDatas(item, "LegendaryBow"), "LegendaryBow");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.SUGAR, 2)) {
				item = updateItem(item, getNotSameDatas(item, "SpeedOrb"), "SpeedOrb");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.REDSTONE, 3)) {
				item = updateItem(item, getNotSameDatas(item, "StrengthOrb"), "StrengthOrb");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.GLOWSTONE_DUST, 4)) {
				item = updateItem(item, getNotSameDatas(item, "NoFallOrb"), "NoFallOrb");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.IRON_PICKAXE, 5)) {
				item = updateItem(item, getNotSameDatas(item, "Hammer"), "Hammer");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_HOE, 6)) {
				item = updateItem(item, getNotSameDatas(item, "FarmHoe"), "FarmHoe");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_HOE, 7)) {
				item = updateItem(item, getNotSameDatas(item, "BecheHoe"), "BecheHoe");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_HOE, 7)) {
				item = updateItem(item, getNotSameDatas(item, "BecheHoe"), "BecheHoe");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.QUARTZ_BLOCK, 8)) {
				item = updateItem(item, getNotSameDatas(item, "RandomOre"), "RandomOre");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_HELMET, 9)) {
				item = updateItem(item, getNotSameDatas(item, "MagicHelmet"), "MagicHelmet");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_CHESTPLATE, 10)) {
				item = updateItem(item, getNotSameDatas(item, "MagicBody"), "MagicBody");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_LEGGINGS, 11)) {
				item = updateItem(item, getNotSameDatas(item, "MagicLegs"), "MagicLegs");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.DIAMOND_BOOTS, 12)) {
				item = updateItem(item, getNotSameDatas(item, "MagicBoots"), "MagicBoots");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.BLAZE_ROD, 13)) {
				item = updateItem(item, getNotSameDatas(item, "MultiTool"), "MultiTool");
				inv.setItem(i, item);
			}
			if (realItem(item, Material.NETHER_STAR, 14)) {
				item = updateItem(item, getNotSameDatas(item, "RepairStar"), "RepairStar");
				inv.setItem(i, item);
			}
		}
	}
	
	public boolean realItem(ItemStack item, Material type, int id) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasEnchants() && item.getType() == type
				&& item.getItemMeta().hasEnchant(Enchantment.LURE)
				&& item.getItemMeta().getEnchants().get(Enchantment.LURE) == id) {
			return true;
		}else {
			return false;
		}
		
	}

	private ItemStack updateItem(ItemStack item, ArrayList<String> wrongDatas, String configName) {
		if (wrongDatas.isEmpty())
			return item;
		ItemMeta itemM = item.getItemMeta();
		ItemStack itemConfig = ItemConfigurationLoader.getLoadedConfig().get(configName);
		ItemMeta itemConfigM = itemConfig.getItemMeta();
		for (int i = 0; i < wrongDatas.size(); i++) {
			switch (wrongDatas.get(i)) {
			case "DisplayName":
				itemM.setDisplayName(itemConfigM.getDisplayName());
				break;
			case "Enchants":
				for(Enchantment ench : itemM.getEnchants().keySet()) {
					itemM.removeEnchant(ench);
				}
				for(Enchantment ench : itemConfigM.getEnchants().keySet()) {
					itemM.addEnchant(ench, itemConfigM.getEnchantLevel(ench), true);
				}
				break;
			case "Lore":
				if(configName == "Hammer") {
					ArrayList<String> lore = new ArrayList<String>();
					for (int i1 = 0; i1 < itemConfigM.getLore().size(); i1++) {
						if (i1 != (ItemConfigurationLoader.hammerInfos().get(0))) {
								lore.add(itemConfigM.getLore().get(i1));
						} else {
							String duraC = itemConfig.getItemMeta().getLore()
									.get(ItemConfigurationLoader.hammerInfos().get(0));
							duraC = duraC.substring(ItemConfigurationLoader.hammerInfos().get(1));
							String loreDuraC = itemConfig.getItemMeta().getLore()
									.get(ItemConfigurationLoader.hammerInfos().get(0));
							loreDuraC = loreDuraC.split(duraC)[0];
							int dura = (int) ((250 - (int) item.getDurability()) * (Main.getInstance().getItemConfig().getDouble("Hammer.Durability") / 250 ));
							lore.add(loreDuraC + dura);
						}
					}
					itemM.setLore(lore);
				}else {
					itemM.setLore(itemConfigM.getLore());
				}
				break;
			case "ItemFlag":
				itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				break;
			}
		}
		item.setItemMeta(itemM);
		return item;

	}

	public ArrayList<String> getNotSameDatas(ItemStack item, String configName) {
		ArrayList<String> notSameDatas = new ArrayList<String>();
		ItemMeta itemM = item.getItemMeta();
		ItemStack itemConfig = ItemConfigurationLoader.getLoadedConfig().get(configName);
		ItemMeta itemConfigM = itemConfig.getItemMeta();
		if (itemM.hasDisplayName()) {
			if (itemM.getDisplayName() != itemConfigM.getDisplayName())
				notSameDatas.add("DisplayName");
		} else {
			notSameDatas.add("DisplayName");
		}
		if (itemM.hasEnchants()) {
			if (itemM.getEnchants().size() == itemConfigM.getEnchants().size()) {
				ArrayList<Enchantment> enchantList = new ArrayList<Enchantment>(itemConfigM.getEnchants().keySet());

				for (int i = 0; i < itemConfigM.getEnchants().size(); i++) {
					if (itemM.getEnchantLevel(enchantList.get(i)) != itemConfigM
							.getEnchantLevel(enchantList.get(i))) {
						notSameDatas.add("Enchants");
						break;
					}
				}
			} else {
				notSameDatas.add("Enchants");
			}
		} else {
			notSameDatas.add("Enchants");
		}
		if (itemM.hasLore()) {
			if (configName == "Hammer") {
				if (itemM.getLore().size() == itemConfigM.getLore().size()) {
					for (int i = 0; i < itemConfigM.getLore().size(); i++) {
						if (i != (ItemConfigurationLoader.hammerInfos().get(0))) {
							if (itemM.getLore().get(i) != itemConfigM.getLore().get(i)) {
								notSameDatas.add("Lore");
								break;
							}
						} else {
							String duras = item.getItemMeta().getLore()
									.get(ItemConfigurationLoader.hammerInfos().get(0));
							duras = duras.substring(ItemConfigurationLoader.hammerInfos().get(1));
							String loreDura = item.getItemMeta().getLore()
									.get(ItemConfigurationLoader.hammerInfos().get(0));
							loreDura = loreDura.split(duras)[0];
							String duraC = itemConfig.getItemMeta().getLore()
									.get(ItemConfigurationLoader.hammerInfos().get(0));
							duraC = duraC.substring(ItemConfigurationLoader.hammerInfos().get(1));
							String loreDuraC = itemConfig.getItemMeta().getLore()
									.get(ItemConfigurationLoader.hammerInfos().get(0));
							loreDuraC = loreDuraC.split(duraC)[0];
							if (loreDura != loreDuraC) {
								notSameDatas.add("Lore");
								break;
							}
						}
					}
				} else {
					notSameDatas.add("Lore");
				}
			} else {
				if (itemM.getLore().size() == itemConfigM.getLore().size()) {
					for (int i = 0; i < itemConfigM.getLore().size(); i++) {
						if (itemM.getLore().get(i) != itemConfigM.getLore().get(i)) {
							notSameDatas.add("Lore");
							break;
						}
					}
				} else {
					notSameDatas.add("Lore");
				}
			}
		} else {
			notSameDatas.add("Lore");
		}
		if (!itemM.hasItemFlag(ItemFlag.HIDE_ENCHANTS))
			notSameDatas.add("ItemFlag");
		return notSameDatas;
	}
}
