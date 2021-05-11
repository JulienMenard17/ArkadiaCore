package ca.sharkmenard.arkadiacore.OrbStuff;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.sharkmenard.arkadiacore.Main;

public class OrbRunner {
	
	
	
	public OrbRunner() {
		
		Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().containsAtLeast(OrbCommand.getSpeedOrb(), 1)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1), true);
					}
					if(p.getInventory().containsAtLeast(OrbCommand.getStrengthOrb(), 1)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1), true);
					}

				}
				
			}
		}, 20L, 60L);
		
	}

}
