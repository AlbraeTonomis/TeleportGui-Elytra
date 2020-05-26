package de.AlbraeTonomis.farmweltGui.Statics;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import de.AlbraeTonomis.farmweltGui.FarmweltGui;

public class Statics {

	public Location LocOberwelt;
	public int SpawnRadiusO;
	public Location LocNether;
	public int SpawnRadiusN;
	public Location LocEnde;
	public int SpawnRadiusE;

	public Statics() {
		setStatics();
	}
	public void setStatics() {
		String[] welten = { "Oberwelt", "Nether", "Ende" };
		if (FarmweltGui.getPlugin().getConfig() == null) {
			String[] dimension = { "world", "world_nether", "world_end" };
			for (int i = 0; i < 3; i++) {
				FileConfiguration config = FarmweltGui.getPlugin().getConfig();
				config.set(welten[i] + ".World", dimension[i]);
				config.set(welten[i] + ".X", 0);
				config.set(welten[i] + ".Y", 0);
				config.set(welten[i] + ".Z", 0);
				config.set(welten[i] + ".Yaw", 0);
				config.set(welten[i] + ".Pitch", 0);
				config.set(welten[i] + ".SpawnRadius",50);
				FarmweltGui.getPlugin().saveConfig();
			}
		}
		FileConfiguration config = FarmweltGui.getPlugin().getConfig();
		for (int i = 0; i < 3; i++) {

			World world = Bukkit.getWorld(config.getString(welten[i] + ".World"));
			double x = config.getDouble(welten[i] + ".X");
			double y = config.getDouble(welten[i] + ".Y");
			double z = config.getDouble(welten[i] + ".Z");
			float yaw = (float) config.getDouble(welten[i] + ".Yaw");
			float pitch = (float) config.getDouble(welten[i] + ".Pitch");
			int rad = config.getInt(welten[i] + ".SpawnRadius");

			switch (i) {
			case 0: {
				this.LocOberwelt = new Location(world, x, y, z, yaw, pitch);
				this.SpawnRadiusO = rad;
				break;

			}
			case 1: {
				this.LocNether = new Location(world, x, y, z, yaw, pitch);
				this.SpawnRadiusN = rad;
				break;

			}
			case 2: {
				this.LocEnde = new Location(world, x, y, z, yaw, pitch);
				this.SpawnRadiusE = rad;
				break;

			}
			}
		}
	}

}
