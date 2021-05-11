package ca.sharkmenard.arkadiacore.RandomOre;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.ItemManagers.UpdateItemListener;

public class RandomOreListener implements Listener {

	@EventHandler
	 public void placeRO(BlockPlaceEvent e) {
		if(e.isCancelled())
			return;
		if(new UpdateItemListener().realItem(e.getItemInHand(), Material.QUARTZ_BLOCK, 8)) {
				RandomOreMgr.addLoc(e.getBlock().getLocation());
		}
		
	 }
	
	@EventHandler
	 public void placeRO(BlockBreakEvent e) {
		if(e.isCancelled())
			return;
		if(e.getBlock().hasMetadata("RandomOre")) {
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			RandomOreMgr.remLoc(e.getBlock().getLocation());
			ItemStack item = null;
			int random = (int)(Math.random() *8);
			switch(random) {
			case 0:
				item = new ItemStack(Material.COAL, 4);
				break;
			case 1:
				item = new ItemStack(Material.INK_SACK, 8 ,(short) 4);
				break;
			case 2:
				item = new ItemStack(Material.REDSTONE, 6);
				break;
			case 3:
				item = new ItemStack(Material.DIAMOND);
				break;
			case 4:
				item = new ItemStack(Material.IRON_INGOT);
				break;
			case 5:
				item = new ItemStack(Material.EMERALD);
				break;
			case 6:
				item = new ItemStack(Material.QUARTZ);
				break;
			case 7:
				item = new ItemStack(Material.GOLD_INGOT);
				break;
			default:
				item = new ItemStack(Material.COAL, 4);
				break;
			}
			
			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), item);
		}
		
	 }
	
}
