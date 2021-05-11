package ca.sharkmenard.arkadiacore.HoeStuff;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.ItemManagers.ItemConfigurationLoader;

public class HoeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String command = "givehoe";
		if (label.equalsIgnoreCase(command)) {
			if(sender.hasPermission("core.give")) {
				ItemStack theItem;
				boolean isNotStackableItem = true;
				int amount = 1;
				if(args.length >= 1) {
					switch (args[0]) {
						case "beche":
							theItem = getBecheHoe();
							if(args.length >= 2) {
								try {
									amount = Integer.parseInt(args[1]);
								} catch (NumberFormatException e) {
									sender.sendMessage("�cLa quantit� indiqu� n'est pas un nombre valide!");
									sender.sendMessage("�7La quantit� a donc �t� mis � 1");
								}
							}
							if(args.length <= 2) {
								if (sender instanceof Player) {
									Player player = (Player)sender;
									if(args.length == 1) {
										HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
										Main.invFullToDrop(invFull, player);
										player.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										return true;
									}else if(args.length == 2) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, player);
											}
											player.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(item);
											Main.invFullToDrop(invFull, player);
											player.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								
								}else {
									sender.sendMessage("�cVous devez �tre un joueur pour effectuer cette commande!");
									sender.sendMessage("�7Essayer plut�t /" + command+ " beche"  + " [Quantit�] [NomDuJoueur]");
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
											p.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(item);
											Main.invFullToDrop(invFull, p);
											p.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								}
								sender.sendMessage("�cCe joueur n'est pas connect� !");
							}
							break;
						case "farm":
							theItem = getFarmHoe();
							if(args.length >= 2) {
								try {
									amount = Integer.parseInt(args[1]);
								} catch (NumberFormatException e) {
									sender.sendMessage("�cLa quantit� indiqu� n'est pas un nombre valide!");
									sender.sendMessage("�7La quantit� a donc �t� mis � 1");
								}
							}
							if(args.length <= 2) {
								if (sender instanceof Player) {
									Player player = (Player)sender;
									if(args.length == 1) {
										HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
										Main.invFullToDrop(invFull, player);
										player.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										return true;
									}else if(args.length == 2) {
										ItemStack item = new ItemStack(theItem);
										item.setAmount(amount);
										if(isNotStackableItem) {
											for(int i = 0; i < amount;i++) {
												HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(new ItemStack[] { theItem});
												Main.invFullToDrop(invFull, player);
											}
											player.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(item);
											Main.invFullToDrop(invFull, player);
											player.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								
								}else {
									sender.sendMessage("�cVous devez �tre un joueur pour effectuer cette commande!");
									sender.sendMessage("�7Essayer plut�t /" + command + " farm" +" [Quantit�] [NomDuJoueur]");
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
											p.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}else {
											HashMap<Integer, ItemStack> invFull = p.getInventory().addItem(item);
											Main.invFullToDrop(invFull, p);
											p.sendMessage("�7Vous venez de recevoir " + amount + "x " + theItem.getItemMeta().getDisplayName());
										}
										return true;
									}
								}
								sender.sendMessage("�cCe joueur n'est pas connect� !");
							}
							break;
						default:
							sender.sendMessage("�7Right Usage: /givehoe [Type] {Amount} {Player}");
							break;

					}
				}else {
					sender.sendMessage("�7Right Usage: /givehoe [Type] {Amount} {Player}");

				}

			}else {
				sender.sendMessage("�cVous n'avez pas la permission d'effectuer cette commande!");
			}
			
			return true;
		} 

		return false;	
	}
	
	
	public ItemStack getFarmHoe() {
		return ItemConfigurationLoader.getLoadedConfig().get("FarmHoe");
	}
	
	public ItemStack getBecheHoe() {
		return ItemConfigurationLoader.getLoadedConfig().get("BecheHoe");
	}

}
