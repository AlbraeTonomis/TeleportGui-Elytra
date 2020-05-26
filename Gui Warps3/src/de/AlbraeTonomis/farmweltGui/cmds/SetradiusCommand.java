package de.AlbraeTonomis.farmweltGui.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.AlbraeTonomis.farmweltGui.FarmweltGui;

public class SetradiusCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg3.length == 2) {
			if (arg0 instanceof Player) {
				Player player = (Player) arg0;
				if (!player.hasPermission("farmweltGui.set" + arg3[0])) {
					player.sendMessage("§cDu darfst diesen Befehl nicht verwenden");
					return false;
				}
			}
			if (arg3[0].equals("Oberwelt") || arg3[0].equals("Nether") || arg3[0].equals("Ende")) {
				FileConfiguration config = FarmweltGui.getPlugin().getConfig();
				config.set(arg3[0] + ".SpawnRadius", Integer.parseInt(arg3[1]));
				FarmweltGui.getPlugin().saveConfig();

				arg0.sendMessage("Der " + arg3[0] + " Spawnradius wurde auf §c" + arg3[1] + "§f gesetzt.");
			}
		} else {
			arg0.sendMessage("Verwende §c /setradius §a<Oberwelt|Nether|Ende><Radius>");
		}
		return false;
	}

}
