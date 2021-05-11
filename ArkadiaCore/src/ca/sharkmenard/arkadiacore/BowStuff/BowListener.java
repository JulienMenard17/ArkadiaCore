package ca.sharkmenard.arkadiacore.BowStuff;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.sharkmenard.arkadiacore.Main;


public class BowListener implements Listener {
	
	
	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if (e.getEntityType().equals(EntityType.PLAYER))
			if (e.getBow() != null && e.getBow().hasItemMeta() && e.getBow().getItemMeta().hasDisplayName()
					&& e.getBow().getItemMeta().hasEnchant(Enchantment.LURE)) {

				if (!Main.getInstance().getShooterHandler().getShooters().containsKey((Player) e.getEntity())) {
					Arrow arrow = (Arrow) e.getProjectile();
					arrow.setFireTicks(1200);
					arrow.setMetadata("nauseeArrow", new FixedMetadataValue((Plugin) Main.getInstance(), "true"));
				}

				Main.getInstance().getShooterHandler().startShooterTimer((Player) e.getEntity());

			}

	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager();
			if (arrow.hasMetadata("nauseeArrow")) {
				arrow.setFireTicks(0);
				if (e.getEntity() instanceof Player) {
					Player p = (Player) e.getEntity();

					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (Main.getInstance().getItemConfig().getInt("LegendaryBow.EffectDuration") * 20), 1), true);
				}
			}
		}

	}

}
