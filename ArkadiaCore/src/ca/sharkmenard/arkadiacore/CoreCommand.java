package ca.sharkmenard.arkadiacore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoreCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("core")) {
			if(sender.isOp() || sender.hasPermission("core.*")) {
				if(args.length == 0) {
					sender.sendMessage("§7Commandes:");
					sender.sendMessage("§7/core reload");
					sender.sendMessage("§7/core autofarm");
					sender.sendMessage("§7/core autofarm [on/off]");
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
					Main.getInstance().reload();
					sender.sendMessage("§aArkadiaCore's Configs Reloaded");
				}
				if(args.length >= 1 && args[0].equalsIgnoreCase("autofarm")) {
					if(args.length == 2) {
						switch(args[1]) {
						case "on":
							Main.getInstance().setAutoFarm(true);
							sender.sendMessage("§aAutoFarm = On");
							break;
						case "off":
							Main.getInstance().setAutoFarm(false);
							sender.sendMessage("§cAutoFarm = Off");
							break;
						default:
							sender.sendMessage("§7Cmd: /core autofarm [on/off]");
							break;
						}
					}else if(args.length == 1) {
						if(Main.getInstance().isAutoFarm()) {
							sender.sendMessage("§aAutoFarm = On");
						}else {
							sender.sendMessage("§cAutoFarm = Off");
						}
					}else {
						sender.sendMessage("§7Cmd: /core autofarm [on/off]");
					}
				}
			}
		}
		return false;
	}

}
