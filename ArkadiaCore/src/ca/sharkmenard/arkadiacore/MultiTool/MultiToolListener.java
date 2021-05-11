package ca.sharkmenard.arkadiacore.MultiTool;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.RandomOre.RandomOreMgr;

public class MultiToolListener implements Listener {

	private ArrayList<Material> multiToolBlocks;

	private ArrayList<Material> getWrongBlocks() {
		multiToolBlocks = new ArrayList<Material>();
		this.multiToolBlocks.add(Material.OBSIDIAN);
		this.multiToolBlocks.add(Material.BEDROCK);
		this.multiToolBlocks.add(Material.BARRIER);
		this.multiToolBlocks.add(Material.COMMAND);
		this.multiToolBlocks.add(Material.AIR);
		return multiToolBlocks;
	}

	@EventHandler
	public void onClickToBreak(PlayerInteractEvent e) {
		if (e.isCancelled())
			return;
		if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.hasBlock() && ((Main.getInstance().canBuild(e.getPlayer(), e.getClickedBlock())  && Main.getInstance().canBuild(e.getPlayer(), e.getClickedBlock())) || e.getPlayer().isOp() || e.getPlayer().hasPermission("core.override"))) {
			if (e.getItem() != null && e.getItem().getType() == Material.BLAZE_ROD && e.getItem().hasItemMeta()
					&& e.getItem().getItemMeta().hasDisplayName()
					&& e.getItem().getItemMeta().hasEnchant(Enchantment.LURE)
					&& e.getItem().getItemMeta().getEnchantLevel(Enchantment.LURE) == 13) {
				if (!getWrongBlocks().contains(e.getClickedBlock().getType())) {
					if(!e.getClickedBlock().hasMetadata("RandomOre")) {
						e.getClickedBlock().breakNaturally();
						if(e.getClickedBlock().getType().equals(Material.NETHER_WARTS)) {
							int random = (int) (Math.random() * 3);
							e.getClickedBlock().getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.NETHER_STALK, random + 2));
						}
					} else{
							e.getClickedBlock().setType(Material.AIR);
							RandomOreMgr.remLoc(e.getClickedBlock().getLocation());
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
							
							e.getClickedBlock().getWorld().dropItem(e.getClickedBlock().getLocation(), item);
						}
				}
			}
		}
	}
}
