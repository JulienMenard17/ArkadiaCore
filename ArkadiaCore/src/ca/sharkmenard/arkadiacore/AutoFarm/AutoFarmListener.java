package ca.sharkmenard.arkadiacore.AutoFarm;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

import ca.sharkmenard.arkadiacore.Main;

public class AutoFarmListener implements Listener {

	@EventHandler
	public void onGrowth(BlockGrowEvent e) {
		if(Main.getInstance().isAutoFarm()) {
			if(e.getNewState().getBlock().getType().equals(Material.AIR))
				Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						switch(e.getNewState().getBlock().getType()) {
						case MELON_BLOCK:
						case SUGAR_CANE_BLOCK:
						case PUMPKIN:
							e.getNewState().getBlock().breakNaturally();
						default:
							break;
						}
						

					}
				});
			}
		}
	
}
