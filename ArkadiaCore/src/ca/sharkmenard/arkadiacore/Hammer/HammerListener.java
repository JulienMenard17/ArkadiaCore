package ca.sharkmenard.arkadiacore.Hammer;

import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ca.sharkmenard.arkadiacore.Main;
import ca.sharkmenard.arkadiacore.ItemManagers.ItemConfigurationLoader;
import ca.sharkmenard.arkadiacore.RandomOre.RandomOreMgr;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;




public class HammerListener
  implements Listener
{
  private ArrayList<Material> hammerblock = new ArrayList<>();
  
  public HammerListener() {
    this.hammerblock.add(Material.OBSIDIAN);
    this.hammerblock.add(Material.BEDROCK);
    this.hammerblock.add(Material.AIR);
  }
  
  @EventHandler
  public void onBreak(BlockBreakEvent e) {
    if (e.isCancelled())
      return; 
    if (realItem(e.getPlayer().getItemInHand()).booleanValue()) {
      Player player = e.getPlayer();
      ItemStack item = player.getItemInHand();
      if (!item.getItemMeta().hasEnchant(Enchantment.LURE) || item.getType() != Material.IRON_PICKAXE)
        return; 
      Block b = e.getBlock();
      if (this.hammerblock.contains(b.getType()))
        return;
      String facing = getPlayerFacing(player);
      if (facing.equals("UP") || facing.equals("DOWN")) {
      	updateDurability(item, player);
        for (Block bp : getHammerBlock(b, "UP")) {
        	possibleBreak(bp, item, player);
        }
        try {
        	int dura = Integer.parseInt(item.getItemMeta().getLore().get(ItemConfigurationLoader.hammerInfos().get(0)).substring(ItemConfigurationLoader.hammerInfos().get(1)));
        	if(dura >= 0) {
        		sendPacket(player, ChatColor.BLUE + "Durabilité: " + dura);
        	}else {
        		sendPacket(player, ChatColor.BLUE + "Durabilité: " + 0);
        		item.setType(Material.AIR);

        	}
        } catch (NumberFormatException ex) {
        	ex.printStackTrace();
		}
        return;
      } 
      if (facing.equals("NORTH") || facing.equals("SOUTH")) {
      	updateDurability(item, player);
        for (Block bp : getHammerBlock(b, "NORTH")) {
        	possibleBreak(bp, item, player);
        }
        try {
        	int dura = Integer.parseInt(item.getItemMeta().getLore().get(ItemConfigurationLoader.hammerInfos().get(0)).substring(ItemConfigurationLoader.hammerInfos().get(1)));
        	if(dura >= 0) {
        		sendPacket(player, ChatColor.BLUE + "Durabilité: " + dura);
        	}else {
        		sendPacket(player, ChatColor.BLUE + "Durabilité: " + 0);
        		item.setType(Material.AIR);
        	}
        } catch (NumberFormatException ex) {
        	ex.printStackTrace();
		}
        return;
      } 
      if (facing.equals("EAST") || facing.equals("WEST")) {
      	updateDurability(item, player);
        for (Block bp : getHammerBlock(b, "EAST")) {
        	possibleBreak(bp, item, player);
        }
        try {
        	int dura = Integer.parseInt(item.getItemMeta().getLore().get(ItemConfigurationLoader.hammerInfos().get(0)).substring(ItemConfigurationLoader.hammerInfos().get(1)));
        	if(dura >= 0) {
        		sendPacket(player, ChatColor.BLUE + "Durabilité: " + dura);
        	}else {
        		sendPacket(player, ChatColor.BLUE + "Durabilité: " + 0);
        		item.setType(Material.AIR);
        	}
        } catch (NumberFormatException ex) {
        	ex.printStackTrace();
		}
        return;
      } 
    } 
  }
  
  public List<Block> getHammerBlock(Block block, String type) {
    List<Block> lblock = new ArrayList<>();
    if (type.equals("UP") || type.equals("DOWN")) {
      lblock.add(block.getRelative(BlockFace.NORTH));
      lblock.add(block.getRelative(BlockFace.SOUTH));
      lblock.add(block.getRelative(BlockFace.EAST));
      lblock.add(block.getRelative(BlockFace.WEST));
      lblock.add(block.getRelative(BlockFace.NORTH_EAST));
      lblock.add(block.getRelative(BlockFace.NORTH_WEST));
      lblock.add(block.getRelative(BlockFace.SOUTH_EAST));
      lblock.add(block.getRelative(BlockFace.SOUTH_WEST));
      return lblock;
    } 
    if (type.equals("SOUTH") || type.equals("NORTH")) {
      lblock.add(block.getRelative(BlockFace.EAST));
      lblock.add(block.getRelative(BlockFace.WEST));
      lblock.add(block.getRelative(BlockFace.UP));
      lblock.add(block.getRelative(BlockFace.DOWN));
      lblock.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
      lblock.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
      lblock.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
      lblock.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
      return lblock;
    } 
    if (type.equals("EAST") || type.equals("WEST")) {
      lblock.add(block.getRelative(BlockFace.NORTH));
      lblock.add(block.getRelative(BlockFace.SOUTH));
      lblock.add(block.getRelative(BlockFace.UP));
      lblock.add(block.getRelative(BlockFace.DOWN));
      lblock.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
      lblock.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
      lblock.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
      lblock.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
      return lblock;
    } 
    return lblock;
  }



  
  public void possibleBreak(Block block, ItemStack item, Player player) {
	  String duras = item.getItemMeta().getLore().get(ItemConfigurationLoader.hammerInfos().get(0));
	  duras = duras.substring(ItemConfigurationLoader.hammerInfos().get(1));
	  if(Main.getInstance().canBuild(player, block) && Integer.parseInt(duras) > 0) {
		  if (ASkyBlockAPI.getInstance().locationIsOnIsland(player, block.getLocation()) || player.isOp() || player.hasPermission("core.override")) {
			  if (!this.hammerblock.contains(block.getType())) {
				  if(!block.hasMetadata("RandomOre")) {
					  block.breakNaturally(item);
					  if(block.getType().equals(Material.NETHER_WARTS)) {
						  int random = (int) (Math.random() * 3);
						  block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.NETHER_STALK, random + 2));
					  }
				  } else {
					  	block.setType(Material.AIR);
						RandomOreMgr.remLoc(block.getLocation());
						ItemStack item1 = null;
						int random = (int)(Math.random() *8);
						switch(random) {
						case 0:
							item1 = new ItemStack(Material.COAL, 4);
							break;
						case 1:
							item1 = new ItemStack(Material.INK_SACK, 8 ,(short) 4);
							break;
						case 2:
							item1 = new ItemStack(Material.REDSTONE, 6);
							break;
						case 3:
							item1 = new ItemStack(Material.DIAMOND);
							break;
						case 4:
							item1 = new ItemStack(Material.IRON_INGOT);
							break;
						case 5:
							item1 = new ItemStack(Material.EMERALD);
							break;
						case 6:
							item1 = new ItemStack(Material.QUARTZ);
							break;
						case 7:
							item1 = new ItemStack(Material.GOLD_INGOT);
							break;
						default:
							item1 = new ItemStack(Material.COAL, 4);
							break;
						}
						
						block.getWorld().dropItem(block.getLocation(), item1);
					}
				  updateDurability(item, player);
				  return;
			  } 
		  }
	  }
  }
  
  public void updateDurability(ItemStack item, Player p) {
	  ItemStack hammer = item;
	  ItemMeta meta = hammer.getItemMeta();
	  try {

		  String duras = item.getItemMeta().getLore().get(ItemConfigurationLoader.hammerInfos().get(0));
		  duras = duras.substring(ItemConfigurationLoader.hammerInfos().get(1));
		  String loreDura = item.getItemMeta().getLore().get(ItemConfigurationLoader.hammerInfos().get(0));
		  loreDura = loreDura.split(duras)[0];
      	int dura = Integer.parseInt(duras);
		  dura--;
		  ArrayList<String> lore = new ArrayList<String>(meta.getLore());
		  lore.set(ItemConfigurationLoader.hammerInfos().get(0), loreDura + String.valueOf(dura));
		  meta.setLore(lore);
		  item.setItemMeta(meta);
		  int count = (int) ((double) (Main.getInstance().getItemConfig().getInt("Hammer.Durability") - dura) / (Double) ((Main.getInstance().getItemConfig().getDouble("Hammer.Durability")) / 250.0D));
		  item.setDurability((short) count);
	  }catch (NumberFormatException e) {
		  e.printStackTrace();
	  }
  }


  public void sendPacket(Player p, String text)
  {
      PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"),(byte) 2);
      ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
  }



  
  public Boolean realItem(ItemStack itemstack) {
    if (itemstack == null)
      return Boolean.valueOf(false); 
    if (!itemstack.hasItemMeta())
      return Boolean.valueOf(false); 
    if (!itemstack.getItemMeta().hasDisplayName())
      return Boolean.valueOf(false); 
    return Boolean.valueOf(true);
  }
  
  public String getPlayerFacing(Player player) {
    Float pitch = Float.valueOf(player.getLocation().getPitch());
    Float y = Float.valueOf(player.getLocation().getYaw());
    if (pitch.floatValue() >= 50.0F)
      return "DOWN"; 
    if (pitch.floatValue() <= -50.0F)
      return "UP"; 
    String dir = null;
    if (y.floatValue() < 0.0F)
      y = Float.valueOf(y.floatValue() + 360.0F); 
    y = Float.valueOf(y.floatValue() % 360.0F);
    int i = (int)((y.floatValue() + 8.0F) / 22.5D);
    if (i == 0) {
      dir = "SOUTH";
    } else if (i == 2) {
      dir = "WEST";
    } else if (i == 3) {
      dir = "WEST";
    } else if (i == 4) {
      dir = "WEST";
    } else if (i == 5) {
      dir = "WEST";
    } else if (i == 6) {
      dir = "NORTH";
    } else if (i == 7) {
      dir = "NORTH";
    } else if (i == 8) {
      dir = "NORTH";
    } else if (i == 9) {
      dir = "NORTH";
    } else if (i == 10) {
      dir = "EAST";
    } else if (i == 11) {
      dir = "EAST";
    } else if (i == 12) {
      dir = "EAST";
    } else if (i == 13) {
      dir = "EAST";
    } else if (i == 1) {
      dir = "SOUTH";
    } else if (i == 14) {
      dir = "SOUTH";
    } else if (i == 15) {
      dir = "SOUTH";
    } else {
      dir = "SOUTH";
    } 
    return dir;
  }
}