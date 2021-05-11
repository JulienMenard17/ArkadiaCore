package ca.sharkmenard.arkadiacore.AutoMessage;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import ca.sharkmenard.arkadiacore.Main;

public class DeathMessageListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
	    EntityDamageEvent lastDamageEvent = player.getLastDamageCause();
	    if (lastDamageEvent instanceof EntityDamageByEntityEvent) {
	      
	    	EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent)lastDamageEvent;
	    	if(damageEvent.getDamager() instanceof Player) {
	    		e.setDeathMessage(Main.getInstance().getMessages().getString("death-message").replace('&', '§').replaceAll("%player%", ((Player) e.getEntity()).getDisplayName()).replaceAll("%killer%", ((Player) damageEvent.getDamager()).getDisplayName()));
	    	}else
	    	if(damageEvent.getDamager() instanceof Arrow) {
	    		Arrow a = (Arrow) damageEvent.getDamager();
	    		if(a.getShooter() instanceof Player) {
	    			e.setDeathMessage(Main.getInstance().getMessages().getString("death-message").replace('&', '§').replaceAll("%player%", ((Player) e.getEntity()).getDisplayName()).replaceAll("%killer%", ((Player) a.getShooter()).getDisplayName()));
	    		}
	    	}
	    }
	
	}
	
	@EventHandler
	public void onVoidDamage(EntityDamageEvent e) {
		if(e.isCancelled())
			return;
		if(e.getEntity() instanceof Player && e.getCause() == DamageCause.VOID) {
			e.setCancelled(true);
			Player p = (Player) e.getEntity();
			p.performCommand(Main.getInstance().getMessages().getString("teleported-cmd"));
			p.sendMessage(Main.getInstance().getMessages().getString("player-teleported").replace('&', '§'));
		}
	}
}
