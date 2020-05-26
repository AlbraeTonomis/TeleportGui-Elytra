package de.AlbraeTonomis.farmweltGui.cmds;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.plugin.Plugin;

//import de.AlbraeTonomis.farmweltGui.FarmweltGui;
import de.AlbraeTonomis.farmweltGui.Statics.Statics;


//import org.jetbrains.annotations.NotNull;

public class FarmweltCommand implements CommandExecutor, Listener{
	
	//private Plugin pl = FarmweltGui.getPlugin();
	private Statics st= new Statics();
	private OneTimeElytra el= new OneTimeElytra();
		
		
	

	@Override
	public boolean onCommand(/*@NotNull*/ CommandSender arg0, /*@NotNull*/ Command arg1, /*@NotNull*/ String arg2,
			/*@NotNull*/ String[] arg3) {
		//st.setStatics();
		if(arg0 instanceof Player) {
			Player player=(Player) arg0;
			if(player.hasPermission("farmweltGui.farmwelt")) {
				if (arg3.length ==0){
					Inventory inv =Bukkit.createInventory(null, 9*3, "Farmwelten");
					
					for(int i=0;i<9*3;i++) {
						
					ItemStack item= new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
					ItemMeta itemMeta0= item.getItemMeta();
					itemMeta0.setDisplayName(null);
					item.setItemMeta(itemMeta0);
					inv.setItem(i,item);
					}
				
					
					ItemStack item1= new ItemStack(Material.GRASS_BLOCK);
					ItemMeta itemMeta= item1.getItemMeta();
					itemMeta.setDisplayName("§3Oberwelt");
					itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1,false);
					itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					item1.setItemMeta(itemMeta);
					inv.setItem(11,item1);
					
					ItemStack item2= new ItemStack(Material.NETHERRACK);
					ItemMeta itemMeta2= item2.getItemMeta();
					itemMeta2.setDisplayName("§4Nether");
					itemMeta2.addEnchant(Enchantment.ARROW_DAMAGE, 1,false);
					itemMeta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					item2.setItemMeta(itemMeta2);
					inv.setItem(13,item2);
					
					ItemStack item3= new ItemStack(Material.END_STONE);
					ItemMeta itemMeta3= item3.getItemMeta();
					itemMeta3.setDisplayName("§eEnde");
					itemMeta3.addEnchant(Enchantment.ARROW_DAMAGE, 1,false);
					itemMeta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					item3.setItemMeta(itemMeta3);
					inv.setItem(15,item3);
					
					
					player.openInventory(inv);
					player.sendMessage("§aFarmwelten");
				
				}
				else player.sendMessage("§cVerwende /farmwelt");
					
				
			} else player.sendMessage("§cDu darfst diesen Befehl nicht verwenden");
				
				
			
		}
		else {
			arg0.sendMessage("Nur Spieler können diesen Command verwenden");
		}
		return false;
	}
	@EventHandler
	public void onClickOnItem(InventoryClickEvent event) {
		
		if (event.getWhoClicked() instanceof Player) {
			
			Player p = (Player) event.getWhoClicked();
			
			if (event.getView().getTitle().equals("Farmwelten")){
				
				event.setCancelled(true);
				
				try {
					
					if (event.getCurrentItem().getType() != Material.WHITE_STAINED_GLASS_PANE && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta()) {
						
						ItemStack invstack = event.getCurrentItem();
						
						
							if (invstack.getType() == Material.GRASS_BLOCK) {
								if(p.hasPermission("farmweltGui.teleportOberwelt")) {
								p.teleport(st.LocOberwelt);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 7, 1);
								el.elytra(p);
								}
								else p.sendMessage("Diese Farmwelt ist zurzeit geschossen");
						}
							if (invstack.getType() == Material.NETHERRACK) {
								if(p.hasPermission("farmweltGui.teleportNether")) {
								p.teleport(st.LocNether);
								p.playSound(p.getLocation(), Sound.BLOCK_PORTAL_TRIGGER, 7, 1);
								}
								else p.sendMessage("Diese Farmwelt ist zurzeit geschossen");
						}
							if (invstack.getType() == Material.END_STONE) {
								if(p.hasPermission("farmweltGui.teleportEnde")) {
								p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 7, 1);
								p.teleport(st.LocEnde);
								p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 7, 1);
								}
								else p.sendMessage("Diese Farmwelt ist zurzeit geschossen");
								
						}
						p.closeInventory();
				
					}
					
				} catch (Exception e) {
					
				}
				
			}
		}
		
	}
}	