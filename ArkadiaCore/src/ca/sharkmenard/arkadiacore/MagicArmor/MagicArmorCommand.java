package ca.sharkmenard.arkadiacore.MagicArmor;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.ItemManagers.ItemConfigurationLoader;

public class MagicArmorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String command = "givemagicarmor";
		if (label.equalsIgnoreCase(command) || label.equalsIgnoreCase("givema")) {
			if(sender.hasPermission("core.give")) {
				//ItemStack theItem = getHammer();
				boolean isNotStackableItem = true;
				int amount = 1;
				if(args.length >= 1) {
					try {
						amount = Integer.parseInt(args[0]);
					} catch (NumberFormatException e) {
						sender.sendMessage("§cLa quantité indiqué n'est pas un nombre valide!");
						sender.sendMessage("§7La quantité a donc été mis à 1");
					}
				}
				if(args.length <= 1) {
					if (sender instanceof Player) {
						Player player = (Player)sender;
						if(args.length == 0) {
							HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(getMagicArmor());
							Main.invFullToDrop(invFull, player);
							player.sendMessage("§7Vous venez de recevoir " + amount + "x " + "§dMagic Armor");
							return true;
						}else if(args.length == 1) {
							ItemStack item = new ItemStack(Material.AIR);
							item.setAmount(amount);
							if(isNotStackableItem) {
								for(int i = 0; i < amount;i++) {
									HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(getMagicArmor());
									Main.invFullToDrop(invFull, player);
								}
								player.sendMessage("§7Vous venez de recevoir " + amount + "x " + "§dMagic Armor");
							}else {
								HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(item);
								Main.invFullToDrop(invFull, player);
								player.sendMessage("§7Vous venez de recevoir " + amount + "x " + "§dMagic Armor");
							}
							return true;
						}
					
					}else {
						sender.sendMessage("§cVous devez être un joueur pour effectuer cette commande!");
						sender.sendMessage("§7Essayer plutôt /" + command + " [Quantité] [NomDuJoueur]");
					}
				}else {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().equals(args[1])) {
							ItemStack item = new ItemStack(Material.AIR);
							item.setAmount(amount);
							if(isNotStackableItem) {
								for(int i = 0; i < amount;i++) {
									HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(getMagicArmor());
									Main.invFullToDrop(invFull, p);
								}
								p.sendMessage("§7Vous venez de recevoir " + amount + "x " + "§dMagic Armor");
							}else {
								HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(item);
								Main.invFullToDrop(invFull, p);
								p.sendMessage("§7Vous venez de recevoir " + amount + "x " + "§dMagic Armor");
							}
							return true;
						}
					}
					sender.sendMessage("§cCe joueur n'est pas connecté !");
				}
				
				

			}else {
				sender.sendMessage("§cVous n'avez pas la permission d'effectuer cette commande!");
			}
			
			return true;
		} 

		return false;
	}
	
	
	private ItemStack[] getMagicArmor() {
		ItemStack[] list = {ItemConfigurationLoader.getLoadedConfig().get("MagicHelmet"),ItemConfigurationLoader.getLoadedConfig().get("MagicBody"),
				ItemConfigurationLoader.getLoadedConfig().get("MagicLegs"),ItemConfigurationLoader.getLoadedConfig().get("MagicBoots")};
		return list;
	}

}
