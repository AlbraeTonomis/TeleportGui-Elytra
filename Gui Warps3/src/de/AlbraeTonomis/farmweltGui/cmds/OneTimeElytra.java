package de.AlbraeTonomis.farmweltGui.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.AlbraeTonomis.farmweltGui.FarmweltGui;
import de.AlbraeTonomis.farmweltGui.Statics.Statics;

public class OneTimeElytra implements Listener {
	Statics st = new Statics();
	int taskID;
	int taskID2;
	int taskID3;
	int taskID4;
	int taskID5;

	public OneTimeElytra() {
		taskID = 0;
		// taskID2=0;
		taskID3 = 0;
		taskID4 = 0;
		taskID5 = 0;
	}

//	@EventHandler
	public void elytra(Player p) {
		// if (event.get) {
		// Player p = event.getPlayer();
		System.out.println("Event ausgeloest");
		if (p.getWorld().getName().equals(st.LocOberwelt.getWorld().getName()))
			System.out.println("name richtig");

		// Vergleiche für max 5 Sekunden ob die Person im Zielradius ist
		taskID3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(FarmweltGui.getPlugin(), new Runnable() {
			int i = 5;

			@Override
			public void run() {

				if (p.getWorld().getNearbyEntities(st.LocOberwelt, st.SpawnRadiusO, st.SpawnRadiusO, st.SpawnRadiusO)
						.contains(p)) {
					System.out.println("bin im zielradius");
					taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(FarmweltGui.getPlugin(), new Runnable() {
						@Override
						public void run() {
							// Gebe die Einmal-Elytra wenn der Chestplate slot leer ist

							if (p.getInventory().getChestplate() == null) {

								ItemStack item = new ItemStack(Material.ELYTRA);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName("§cEinmal-Elytra");
								meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
								meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
								meta.setUnbreakable(true);
								item.setItemMeta(meta);
								p.getInventory().setChestplate(item);
								System.out.println("Elytra plaziert");
								Bukkit.getScheduler().cancelTask(taskID);
							} else if (p.getWorld()
									.getNearbyEntities(st.LocOberwelt, st.SpawnRadiusO, st.SpawnRadiusO,
											st.SpawnRadiusO)
									.contains(p) && p.getInventory().getChestplate() != null
									&& !p.getInventory().getChestplate().getItemMeta().getDisplayName()
											.equals("§cEinmal-Elytra"))
								p.sendTitle("§cVorsicht du trägst keine Einmal-Elytra", "", 10, 60, 10);
							else {
								Bukkit.getScheduler().cancelTask(taskID);
								return;
							}
						}
					}, 0, 80);

					Bukkit.getScheduler().cancelTask(taskID3);
				}

				else if (i < 0)
					i--;
				else
					Bukkit.getScheduler().cancelTask(taskID3);
			}
		}, 0, 20);
	}

	@EventHandler
	public void elytraCheck(EntityToggleGlideEvent event) {
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			Player p = (Player) event.getEntity();
			if (!p.getWorld().getNearbyEntities(st.LocOberwelt, st.SpawnRadiusO, st.SpawnRadiusO, st.SpawnRadiusO)
					.contains(p)) {
				System.out.println("Ist ein Spieler");
				if (p.isGliding()) {
					System.out.println("fliegt nicht mehr");
					if (p.getInventory().getChestplate().hasItemMeta()) {
						System.out.println("hat meta");
						if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equals("§cEinmal-Elytra")) {
							p.getInventory().setChestplate(null);
							System.out.println("gelöscht");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onClickOnItem(InventoryClickEvent event) {

		if (event.getWhoClicked() instanceof Player) {
			Player p = (Player) event.getWhoClicked();
			if (event.getCurrentItem() != null)
				if (event.getCurrentItem().hasItemMeta()) {
					if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cEinmal-Elytra")) {
						Entity e = (Entity) p;
						if (e.isOnGround() && !p.getWorld()
								.getNearbyEntities(st.LocOberwelt, st.SpawnRadiusO, st.SpawnRadiusO, st.SpawnRadiusO)
								.contains(p)) {
							event.setCurrentItem(null);
							System.out.println("Elytra entfernt");
						} else
							p.closeInventory();
					}
				}

		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		Entity e = (Entity) p;
		if (p.getWorld() == st.LocOberwelt.getWorld()) {
			if (e.isOnGround() && !p.getWorld()
					.getNearbyEntities(st.LocOberwelt, st.SpawnRadiusO, st.SpawnRadiusO, st.SpawnRadiusO).contains(p)) {
				if (p.getInventory().getChestplate() != null)
					if (p.getInventory().getChestplate().hasItemMeta()) {
						if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equals("§cEinmal-Elytra"))
							p.getInventory().setChestplate(null);

					}

			}
		} else {
			if (p.getInventory().getChestplate() != null)
				if (p.getInventory().getChestplate().hasItemMeta()) {
					if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equals("§cEinmal-Elytra"))
						p.getInventory().setChestplate(null);
				}
		}
	}

// Soll beim PlayerJoin schauen, ob der Spieler eine Einmal Elytra trägt oder noch eine Braucht
	/*
	 * @EventHandler public void onJoin(PlayerJoinEvent event) { if
	 * (event.getPlayer() instanceof Player) { Player p = event.getPlayer(); if
	 * (p.getInventory().getChestplate() != null) { if
	 * (p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(
	 * "§cEinmal-Elytra")) {
	 * 
	 * elytraCheck(p); return; }
	 * 
	 * } elytra(p); } }
	 */
}