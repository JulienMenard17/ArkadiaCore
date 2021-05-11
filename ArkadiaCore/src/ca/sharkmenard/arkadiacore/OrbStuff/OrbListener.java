package ca.sharkmenard.arkadiacore.OrbStuff;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class OrbListener implements Listener {
	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL)
			if(p.getInventory().containsAtLeast(OrbCommand.getNoFallOrb(), 1)) {
				e.setCancelled(true);
			}
		}
		
	}
	@EventHandler
	public void onPlaceStrengthOrb(BlockPlaceEvent e) {
		if(e.isCancelled())
			return;
		if(e.getItemInHand() != null && e.getItemInHand().getType() == Material.REDSTONE && e.getItemInHand().hasItemMeta()
						&& e.getItemInHand().getItemMeta().hasDisplayName()
						&& e.getItemInHand().getItemMeta().hasEnchant(Enchantment.LURE)
						&& e.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LURE) == 3) {
			e.setCancelled(true);
		}
	}

}
