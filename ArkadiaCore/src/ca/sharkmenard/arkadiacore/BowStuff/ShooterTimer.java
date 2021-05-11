package ca.sharkmenard.arkadiacore.BowStuff;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import ca.sharkmenard.arkadiacore.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class ShooterTimer {
	
	Player p;
	BukkitTask timer;
	HashMap<Player, Integer> shooters = Main.getInstance().getShooterHandler().getShooters();

	
	public ShooterTimer(Player p) {
		this.p = p;
		launchTimer();
	}
	
	@SuppressWarnings("deprecation")
	private void launchTimer() {
		this.timer = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new BukkitRunnable() {
			
			@Override
			public void run() {
				shooters.replace(p, shooters.get(p) - 1);
				
				if(shooters.get(p) == 0) {
					shooters.remove(p);
					sendPacket(p, "§aFlèche de Nausée prête !");
					timer.cancel();
				}else
				{
					sendPacket(p, "§cCooldown : " + Main.getInstance().getShooterHandler().getShooters().get(p) + " secondes ");

				}
				
			}
		}, 20L, 20L);
	}
	
	
	  public void sendPacket(Player p, String text)
	    {
	        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"),(byte) 2);
	        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	    }


}
