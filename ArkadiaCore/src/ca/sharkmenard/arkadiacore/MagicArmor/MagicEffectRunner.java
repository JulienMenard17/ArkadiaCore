package ca.sharkmenard.arkadiacore.MagicArmor;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.ItemManagers.ItemConfigurationLoader;
import ca.sharkmenard.arkadiacore.ItemManagers.UpdateItemListener;

public class MagicEffectRunner {
	
	public MagicEffectRunner() {
		
		Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(isRightItem(p.getEquipment())) {
						ArrayList<Integer> effects = ItemConfigurationLoader.magciArmorEffects();
						ArrayList<Integer> effectsLevels = ItemConfigurationLoader.magciArmorEffectsLevels();
						for(int i =0; i < effects.size(); i++) {
							if(effects.get(i) == 16)
								p.addPotionEffect(new PotionEffect(PotionEffectType.getById(effects.get(i)), 300, effectsLevels.get(i)), true);
							else
								p.addPotionEffect(new PotionEffect(PotionEffectType.getById(effects.get(i)), 200, effectsLevels.get(i)), true);

						}
					}

				}
				
			}
		}, 20L, 60L);
		
	}
	
	private boolean isRightItem(EntityEquipment playerGear) {
		boolean isRight = true;
		isRight = new UpdateItemListener().realItem(playerGear.getHelmet(), Material.DIAMOND_HELMET, 9 );
		if(!isRight)
			return false;
		isRight = new UpdateItemListener().realItem(playerGear.getChestplate(), Material.DIAMOND_CHESTPLATE, 10 );
		if(!isRight)
			return false;
		isRight = new UpdateItemListener().realItem(playerGear.getLeggings(), Material.DIAMOND_LEGGINGS, 11 );
		if(!isRight)
			return false;
		isRight = new UpdateItemListener().realItem(playerGear.getBoots(), Material.DIAMOND_BOOTS, 12 );
		if(!isRight)
			return false;
		
		return true;
	}
}
