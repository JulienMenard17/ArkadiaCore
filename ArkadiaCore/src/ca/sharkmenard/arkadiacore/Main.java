package ca.sharkmenard.arkadiacore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Server.Spigot;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.SpigotConfig;
import org.spigotmc.SpigotWorldConfig;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import ca.sharkmenard.arkadiacore.AutoFarm.AutoFarmListener;
import ca.sharkmenard.arkadiacore.AutoMessage.AutoMessageRunner;
import ca.sharkmenard.arkadiacore.AutoMessage.DeathMessageListener;
import ca.sharkmenard.arkadiacore.BowStuff.BowCommand;
import ca.sharkmenard.arkadiacore.BowStuff.BowListener;
import ca.sharkmenard.arkadiacore.BowStuff.ShootersHandler;
import ca.sharkmenard.arkadiacore.EnchantLore.EnchantLoreListener;
import ca.sharkmenard.arkadiacore.Hammer.HammerCommand;
import ca.sharkmenard.arkadiacore.Hammer.HammerListener;
import ca.sharkmenard.arkadiacore.HoeStuff.HoeCommand;
import ca.sharkmenard.arkadiacore.HoeStuff.HoeListener;
import ca.sharkmenard.arkadiacore.ItemManagers.ItemConfigurationLoader;
import ca.sharkmenard.arkadiacore.ItemManagers.UpdateItemListener;
import ca.sharkmenard.arkadiacore.MagicArmor.MagicArmorCommand;
import ca.sharkmenard.arkadiacore.MagicArmor.MagicEffectRunner;
import ca.sharkmenard.arkadiacore.MultiTool.MultiToolCommand;
import ca.sharkmenard.arkadiacore.MultiTool.MultiToolListener;
import ca.sharkmenard.arkadiacore.OrbStuff.OrbCommand;
import ca.sharkmenard.arkadiacore.OrbStuff.OrbListener;
import ca.sharkmenard.arkadiacore.OrbStuff.OrbRunner;
import ca.sharkmenard.arkadiacore.RandomOre.RandomOreCommand;
import ca.sharkmenard.arkadiacore.RandomOre.RandomOreListener;
import ca.sharkmenard.arkadiacore.RandomOre.RandomOreMgr;
import ca.sharkmenard.arkadiacore.RepairStar.RepairStarCommand;
import ca.sharkmenard.arkadiacore.RepairStar.RepairStarListener;

public class Main extends JavaPlugin {
	
	private static Main main;
	
	WorldGuardPlugin worldGuardPlugin;
	private File itemConfigFile;
	private FileConfiguration itemConfig;
	private File databaseFile;
	private FileConfiguration database;
	private File messagesFile;
	private FileConfiguration messages;
	private ShootersHandler shooterHandler;
	private OrbRunner orbRunner;
	@SuppressWarnings("unused")
	private MagicEffectRunner MagicEffectRunner;
	private boolean isAutoFarm;
	
	@Override
	public void onEnable() {
		isAutoFarm = false;
		main = this;
		worldGuardPlugin = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		createItemConfig();
		createDatabase();
		createMessages();
		ItemConfigurationLoader.loadConfig();
		RandomOreMgr.loadDatabase();
		shooterHandler = new ShootersHandler();

		getCommand("givebow").setExecutor(new BowCommand());
		getCommand("givehammer").setExecutor(new HammerCommand());
		getCommand("giveorb").setExecutor(new OrbCommand());
		getCommand("givehoe").setExecutor(new HoeCommand());
		getCommand("giverandomore").setExecutor(new RandomOreCommand());
		getCommand("givemagicarmor").setExecutor(new MagicArmorCommand());
		getCommand("givemultitool").setExecutor(new MultiToolCommand());
		getCommand("giverepairstar").setExecutor(new RepairStarCommand());
		getCommand("core").setExecutor(new CoreCommand());
		Bukkit.getPluginManager().registerEvents(new UpdateItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new BowListener(), this);
		Bukkit.getPluginManager().registerEvents(new HammerListener(), this);
		Bukkit.getPluginManager().registerEvents(new OrbListener(), this);
		Bukkit.getPluginManager().registerEvents(new HoeListener(), this);
		Bukkit.getPluginManager().registerEvents(new RandomOreListener(), this);
		Bukkit.getPluginManager().registerEvents(new MultiToolListener(), this);
		Bukkit.getPluginManager().registerEvents(new RepairStarListener(), this);
		Bukkit.getPluginManager().registerEvents(new AutoFarmListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathMessageListener(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantLoreListener(), this);
		orbRunner = new OrbRunner();
		MagicEffectRunner = new MagicEffectRunner();
		AutoMessageRunner.startRunner();
	}
	
	@Override
	public void onDisable() {
		RandomOreMgr.print2Database();
	}
		public static Main getInstance() {
		return main;
	}
	
	
	public ShootersHandler getShooterHandler() {
		return shooterHandler;
	}


	public OrbRunner getOrbRunner() {
		return orbRunner;
	}
	public void reload() {
	createItemConfig();
	ItemConfigurationLoader.loadConfig();
	RandomOreMgr.print2Database();
	createDatabase();
	RandomOreMgr.loadDatabase();
	createMessages();
	AutoMessageRunner.updateMessages();
	}
	
	public FileConfiguration getItemConfig() {
		return this.itemConfig;
	}
	
	
	private void createItemConfig() {
		itemConfigFile = new File(getDataFolder(), "itemConfig.yml");
		if(!itemConfigFile.exists()) {
			itemConfigFile.getParentFile().mkdirs();
			saveResource("itemConfig.yml", false);
		}
		
		itemConfig = new YamlConfiguration();
		try {
			itemConfig.load(itemConfigFile);
		} catch (IOException | InvalidConfigurationException e ) {
			e.printStackTrace();
		}
	}
	
	public void saveItemConfig() {
	    try {
	    	itemConfig.save(itemConfigFile);
	        } catch (IOException e) {
	            this.getLogger().warning("Unable to save " + "itemConfig.yml"); // shouldn't really happen, but save throws the exception
	        }
	}
	public FileConfiguration getDataBase() {
		return this.database;
	}
	
	
	private void createDatabase() {
		databaseFile = new File(getDataFolder(), "database.yml");
		if(!databaseFile.exists()) {
			databaseFile.getParentFile().mkdirs();
			saveResource("database.yml", false);
		}
		
		database = new YamlConfiguration();
		try {
			database.load(databaseFile);
		} catch (IOException | InvalidConfigurationException e ) {
			e.printStackTrace();
		}
	}
	
	public void saveDatabase() {
	    try {
	    	database.save(databaseFile);
	        } catch (IOException e) {
	            this.getLogger().warning("Unable to save " + "database.yml"); // shouldn't really happen, but save throws the exception
	        }
	}
	
	
	public FileConfiguration getMessages() {
		return this.messages;
	}
	
	private void createMessages() {
		messagesFile = new File(getDataFolder(), "messages.yml");
		if(!messagesFile.exists()) {
			messagesFile.getParentFile().mkdirs();
			saveResource("messages.yml", false);
		}
		
		messages = new YamlConfiguration();
		try {
			messages.load(messagesFile);
		} catch (IOException | InvalidConfigurationException e ) {
			e.printStackTrace();
		}
	}
	
	public void saveMessages() {
	    try {
	    	messages.save(messagesFile);
	        } catch (IOException e) {
	            this.getLogger().warning("Unable to save " + "messages.yml"); // shouldn't really happen, but save throws the exception
	        }
	}
	
	public static void invFullToDrop(HashMap<Integer, ItemStack> invFull, Player player) {
		for(int i = 0; i < invFull.values().size(); i++) {
			  player.getWorld().dropItem(player.getLocation(), new ArrayList<ItemStack>(invFull.values()).get(i));
		  }
	}
	
	public boolean canBuild(Player p, Block block) {
		return worldGuardPlugin.canBuild(p, block);
	}

	public boolean isAutoFarm() {
		return isAutoFarm;
	}

	public void setAutoFarm(boolean isAutoFarm) {
		this.isAutoFarm = isAutoFarm;
	}

}
