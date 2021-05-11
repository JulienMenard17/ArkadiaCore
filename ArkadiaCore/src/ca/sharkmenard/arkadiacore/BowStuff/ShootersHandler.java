package ca.sharkmenard.arkadiacore.BowStuff;

import java.util.HashMap;

import org.bukkit.entity.Player;

import ca.sharkmenard.arkadiacore.Main;

public class ShootersHandler {
	
	HashMap<Player, Integer> shooters;
	
	public ShootersHandler() {
		shooters = new HashMap<Player, Integer>();
	}
	
	
	public void startShooterTimer(Player p) {
		if(!shooters.keySet().contains(p)) {
			shooters.put(p, Main.getInstance().getItemConfig().getInt("LegendaryBow.Cooldown"));
			new ShooterTimer(p);
		}
	}
		
	
	
	public HashMap<Player, Integer> getShooters() {
		return shooters;
	}

}
