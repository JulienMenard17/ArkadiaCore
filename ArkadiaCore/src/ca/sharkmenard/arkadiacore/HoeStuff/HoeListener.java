package ca.sharkmenard.arkadiacore.HoeStuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ca.sharkmenard.arkadiacore.Main;

public class HoeListener implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getItem() != null && e.getItem().getType() == Material.DIAMOND_HOE && e.getItem().hasItemMeta()
				&& e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().hasEnchant(Enchantment.LURE)) {
			

			if (e.hasBlock() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player p = e.getPlayer();
				// FarmHoe ID 6
				if (e.getItem().getItemMeta().getEnchantLevel(Enchantment.LURE) == 6) {
					ArrayList<Block> blockList = new ArrayList<Block>(getHoeBlock(e.getClickedBlock()));
					for(int i = 0; i < blockList.size(); i++) {
						possibleUpdate(blockList.get(i), e.getItem(), p, "Farm");
					}
				}

				// BecheHoe ID 7
				if (e.getItem().getItemMeta().getEnchantLevel(Enchantment.LURE) == 7) {
					ArrayList<Block> blockList = new ArrayList<Block>(getHoeBlock(e.getClickedBlock()));
					for(int i = 0; i < blockList.size(); i++) {
						possibleUpdate(blockList.get(i), e.getItem(), p, "Beche");
						
					}
				}

			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void possibleUpdate(Block block, ItemStack item, Player player, String hoeType) {
		  if(Main.getInstance().canBuild(player, block)) {
			  if (ASkyBlockAPI.getInstance().locationIsOnIsland(player, block.getLocation())  || player.isOp() || player.hasPermission("core.override")) {
				  if(hoeType == "Farm") {
					  if ((getFarmHoeBlock().contains(block.getType()) && block.getData() == 7) || (block.getType() == Material.NETHER_WARTS && block.getData() == 3)) {
						  ArrayList<ItemStack> drops = (ArrayList<ItemStack>) block.getDrops(item);
						  block.setData((byte) 0);
						  if(block.getType().equals(Material.NETHER_WARTS)) {
							  	int random = (int) (Math.random() * 3);
							  	drops.add(new ItemStack(Material.NETHER_STALK, random + 2));
						  }
						  for(ItemStack it : drops) {
							  HashMap<Integer, ItemStack> invFull = player.getInventory().addItem(it);
							  for(int i = 0; i < invFull.values().size(); i++) {
								  block.getLocation().getWorld().dropItem(block.getLocation(), new ArrayList<ItemStack>(invFull.values()).get(i));
							  }

						  }

						  return;
					  } 
				} else {
					if (getBecheHoeBlock().contains(block.getType())
							|| getBecheHoeBlockToBeche().contains(block.getType())) {
						if (block.getLocation().add(0, 1, 0).getBlock().getType() != (Material.AIR)) {
							return;
						}
						if (getBecheHoeBlockToBeche().contains(block.getType())) {
							block.setType(Material.SOIL);
						}

						boolean found = false;
						Material matFound = null;
						if (block.getType() == Material.SOIL) {
							for (int i = 0; i < getBecheHoeItemSoil().size(); i++) {
								if (player.getInventory().contains(getBecheHoeItemSoil().get(i))) {
									found = true;
									matFound = getBecheHoeItemSoil().get(i);
									break;
								}
							}
							if (found) {
								int index = player.getInventory().first(matFound);
								ItemStack itemReduce = player.getInventory().getItem(index);
								if (itemReduce.getAmount() == 1) {
									itemReduce.setType(Material.AIR);
								} else {
									itemReduce.setAmount(player.getInventory().getItem(index).getAmount() - 1);

								}
								player.getInventory().setItem(index, itemReduce);
								// Nether wart sur de la terre et seeds sur de soul sand
								switch (matFound) {
								case SEEDS:
									block.getLocation().add(0, 1, 0).getBlock().setType(Material.CROPS);
									break;
								case CARROT_ITEM:
									block.getLocation().add(0, 1, 0).getBlock().setType(Material.CARROT);
									break;
								case POTATO_ITEM:
									block.getLocation().add(0, 1, 0).getBlock().setType(Material.POTATO);
									break;
								default:
									break;
								}
							}
						}
						if (block.getType() == Material.SOUL_SAND) {
							for (int i = 0; i < getBecheHoeItemSoulSand().size(); i++) {
								if (player.getInventory().contains(getBecheHoeItemSoulSand().get(i))) {
									found = true;
									matFound = getBecheHoeItemSoulSand().get(i);
									break;
								}
							}
							if (found) {
								int index = player.getInventory().first(matFound);
								ItemStack itemReduce = player.getInventory().getItem(index);
								if (itemReduce.getAmount() == 1) {
									itemReduce.setType(Material.AIR);
								} else {
									itemReduce.setAmount(player.getInventory().getItem(index).getAmount() - 1);

								}
								player.getInventory().setItem(index, itemReduce);
								switch (matFound) {
								case NETHER_STALK:
									block.getLocation().add(0, 1, 0).getBlock().setType(Material.NETHER_WARTS);
									break;
								default:
									break;
								}
							}
						}

						return;
					}
				}
			}
		}
	  }
	  
	  private ArrayList<Material> getFarmHoeBlock(){
		  ArrayList<Material> blocks = new ArrayList<Material>();
		  blocks.add(Material.CROPS);
		  blocks.add(Material.CARROT);
		  blocks.add(Material.POTATO);
		  return blocks;
	  }
	  
	  private ArrayList<Material> getBecheHoeBlock(){
		  ArrayList<Material> blocks = new ArrayList<Material>();
		  blocks.add(Material.SOUL_SAND);
		  blocks.add(Material.SOIL);
		  return blocks;
	  }
	  
	  private ArrayList<Material> getBecheHoeBlockToBeche(){
		  ArrayList<Material> blocks = new ArrayList<Material>();
		  blocks.add(Material.GRASS);
		  blocks.add(Material.DIRT);
		  return blocks;
	  }
	  
	  private ArrayList<Material> getBecheHoeItemSoil(){
		  ArrayList<Material> items = new ArrayList<Material>();
		  items.add(Material.SEEDS);
		  items.add(Material.CARROT_ITEM);
		  items.add(Material.POTATO_ITEM);

		  return items;
	  }
	  
	  private ArrayList<Material> getBecheHoeItemSoulSand(){
		  ArrayList<Material> items = new ArrayList<Material>();
		  items.add(Material.NETHER_STALK);
		  return items;
	  }
	  
	  
	
	

	private List<Block> getHoeBlock(Block block) {
		List<Block> lblock = new ArrayList<>();
		lblock.add(block);
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

}
