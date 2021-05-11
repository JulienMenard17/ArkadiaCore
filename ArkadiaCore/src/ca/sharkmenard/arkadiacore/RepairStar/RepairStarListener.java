package ca.sharkmenard.arkadiacore.RepairStar;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class RepairStarListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLeftClickInInvToRepairItem(InventoryClickEvent e) {
		if(e.isCancelled())
			return;
		if(e.getView().getBottomInventory().getType() == InventoryType.PLAYER && e.getClickedInventory() != null && e.getClickedInventory() == e.getView().getBottomInventory()) {
			if(e.isRightClick() && !e.isShiftClick()) {
				if (e.getCursor() != null && e.getCursor().getType() == Material.NETHER_STAR && e.getCursor().hasItemMeta()
						&& e.getCursor().getItemMeta().hasDisplayName()
						&& e.getCursor().getItemMeta().hasEnchant(Enchantment.LURE)
						&& e.getCursor().getItemMeta().getEnchantLevel(Enchantment.LURE) == 14) {
					if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getDurability() != 0) {
						e.setCancelled(true);
						int amount = e.getCursor().getAmount() - 1;
						if(amount == 0) {
							e.setCursor(new ItemStack(Material.AIR));
						} else {
							ItemStack star = e.getCursor();
							star.setAmount(amount);
							e.setCursor(star);
						}
						ItemStack item = e.getCurrentItem();
						item.setDurability((short) 0);
						e.setCurrentItem(item);
					}
				}
			}
		}
		
	}

}
