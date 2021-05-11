package ca.sharkmenard.arkadiacore.RandomOre;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.FixedMetadataValue;

import ca.sharkmenard.arkadiacore.Main;

public class RandomOreMgr {
	
	
	private static ArrayList<Location> locs;
	private static FileConfiguration database = null;
	
	public static void loadDatabase() {
		locs = new ArrayList<Location>();
		database = Main.getInstance().getDataBase();
		ArrayList<String> dataLocsStr = new ArrayList<String>(database.getStringList("RandomOres"));
		for(int i = 0; i < dataLocsStr.size(); i++) {
			String locStr = dataLocsStr.get(i);
			String[] buffer = locStr.split(":");
			Location loc = new Location(Bukkit.getWorld(buffer[0]), Integer.parseInt(buffer[1]), Integer.parseInt(buffer[2]), Integer.parseInt(buffer[3]));
			loc.getBlock().setMetadata("RandomOre", new FixedMetadataValue(Main.getInstance(), "true"));
			locs.add(loc);
		}
	}
	
	public static void addLoc(Location loc) {
		if(database == null)
			return;
		loc.getBlock().setMetadata("RandomOre", new FixedMetadataValue(Main.getInstance(), "true"));
		locs.add(loc);
	}
	
	public static void remLoc(Location loc) {
		if(database == null)
			return;
		loc.getBlock().removeMetadata("RandomOre", Main.getInstance());
		locs.remove(loc);
	}
	
	public static void print2Database() {
		if(database == null)
			return;
		if(locs.isEmpty())
			database.set("RandomOres", new ArrayList<String>());
		ArrayList<String> locsStr = new ArrayList<String>();
		for(int i = 0; i < locs.size(); i++) {
			Location loc = locs.get(i);
			String locStr = loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ(); 
			locsStr.add(locStr);
		}
		database.set("RandomOres", locsStr);
		Main.getInstance().saveDatabase();
		database = null;
	}

}
