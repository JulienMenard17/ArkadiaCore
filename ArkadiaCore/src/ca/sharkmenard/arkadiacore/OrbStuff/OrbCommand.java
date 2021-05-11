package ca.sharkmenard.arkadiacore.OrbStuff;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.ItemManagers.ItemConfigurationLoader;

public class OrbCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String command = "giveorb";
		if (label.equalsIgnoreCase(command)) {
			if(sender.hasPermission("core.give")) {
				ItemStack theItem;
				boolean isNotStackableItem = false;
				int amount = 1;
				if(args.length >= 1) {
					switch (args[0]) {
						case "speed":
							theItem = getSpeedOrb();
							if(args.length >= 2) {
								try {
									amount = Integer.parseInt(args[1]);
								} catch (NumberFormatException e) {
									sender.sendMessage("§cLa quantité indiqué n'est pas un nombre valide!");
									sender.sendMessage("§7La quantité a donc été mis à 1");
								}
							}
							if(args.length <= 2) {
								if (sender instanceof Player) {
									Player player = (Player)sender;
									if(args.length == 1) {
										HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
										Main.invFullToDrop(invFull, player);
										player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										return true;
									}else if(args.length == 2) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, player);
											}
											player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(item);
											Main.invFullToDrop(invFull, player);
											player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								
								}else {
									sender.sendMessage("§cVous devez être un joueur pour effectuer cette commande!");
									sender.sendMessage("§7Essayer plutôt /" + command+ " speed"  + " [Quantité] [NomDuJoueur]");
								}
							}else {
								for(Player p : Bukkit.getOnlinePlayers()) {
									if(p.getName().equals(args[2])) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, p);
											}
											p.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(item);
											Main.invFullToDrop(invFull, p);
											p.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								}
								sender.sendMessage("§cCe joueur n'est pas connecté !");
							}
							break;
						case "force":
							theItem = getStrengthOrb();
							if(args.length >= 2) {
								try {
									amount = Integer.parseInt(args[1]);
								} catch (NumberFormatException e) {
									sender.sendMessage("§cLa quantité indiqué n'est pas un nombre valide!");
									sender.sendMessage("§7La quantité a donc été mis à 1");
								}
							}
							if(args.length <= 2) {
								if (sender instanceof Player) {
									Player player = (Player)sender;
									if(args.length == 1) {
										HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
										Main.invFullToDrop(invFull, player);
										player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										return true;
									}else if(args.length == 2) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, player);
											}
											player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(item);
											Main.invFullToDrop(invFull, player);
											player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								
								}else {
									sender.sendMessage("§cVous devez être un joueur pour effectuer cette commande!");
									sender.sendMessage("§7Essayer plutôt /" + command + " force" +" [Quantité] [NomDuJoueur]");
								}
							}else {
								for(Player p : Bukkit.getOnlinePlayers()) {
									if(p.getName().equals(args[2])) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, p);
											}
											p.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(item);
											Main.invFullToDrop(invFull, p);
											p.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								}
								sender.sendMessage("§cCe joueur n'est pas connecté !");
							}
							break;
						case "nofall":
							theItem = getNoFallOrb();
							if(args.length >= 2) {
								try {
									amount = Integer.parseInt(args[1]);
								} catch (NumberFormatException e) {
									sender.sendMessage("§cLa quantité indiqué n'est pas un nombre valide!");
									sender.sendMessage("§7La quantité a donc été mis à 1");
								}
							}
							if(args.length <= 2) {
								if (sender instanceof Player) {
									Player player = (Player)sender;
									if(args.length == 1) {
										HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
										Main.invFullToDrop(invFull, player);
										player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										return true;
									}else if(args.length == 2) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, player);
											}
											player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(item);
											Main.invFullToDrop(invFull, player);
											player.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								
								}else {
									sender.sendMessage("§cVous devez être un joueur pour effectuer cette commande!");
									sender.sendMessage("§7Essayer plutôt /" + command + " nofall" +" [Quantité] [NomDuJoueur]");
								}
							}else {
								for(Player p : Bukkit.getOnlinePlayers()) {
									if(p.getName().equals(args[2])) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, p);
											}
											p.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(item);
											Main.invFullToDrop(invFull, p);
											p.sendMessage("§7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								}
								sender.sendMessage("§cCe joueur n'est pas connecté !");
							}
							break;
						default:
							sender.sendMessage("§7Right Usage: /giveorb [Type] {Amount} {Player}");
							break;

					}
				}else {
					sender.sendMessage("§7Right Usage: /giveorb [Type] {Amount} {Player}");

				}

			}else {
				sender.sendMessage("§cVous n'avez pas la permission d'effectuer cette commande!");
			}
			
			return true;
		} 

		return false;	
	}
	
	
	public static ItemStack getSpeedOrb() {
		return ItemConfigurationLoader.getLoadedConfig().get("SpeedOrb");
	}
	
	
	public static ItemStack getStrengthOrb() {
		return ItemConfigurationLoader.getLoadedConfig().get("StrengthOrb");
	}
	
	public static ItemStack getNoFallOrb() {
		return ItemConfigurationLoader.getLoadedConfig().get("NoFallOrb");
	}

}

