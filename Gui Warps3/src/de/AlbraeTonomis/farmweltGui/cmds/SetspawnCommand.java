package de.AlbraeTonomis.farmweltGui.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.AlbraeTonomis.farmweltGui.FarmweltGui;


public class SetspawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0,  Command arg1,  String arg2,
			String[] arg3) {
		if(arg0 instanceof Player) {
			Player player=(Player) arg0;
			
				if (arg3.length ==1){
					if(player.hasPermission("farmweltGui.set"+arg3[0])) {
					if(arg3[0].equals("Oberwelt")||arg3[0].equals("Nether")||arg3[0].equals("Ende")) {
					FileConfiguration config= FarmweltGui.getPlugin().getConfig();
					config.set(arg3[0]+".World", player.getWorld().getName());
					config.set(arg3[0]+".X", player.getLocation().getX());
					config.set(arg3[0]+".Y", player.getLocation().getY());
					config.set(arg3[0]+".Z", player.getLocation().getZ());
					config.set(arg3[0]+".Yaw", player.getLocation().getYaw());
					config.set(arg3[0]+".Pitch", player.getLocation().getPitch());
					FarmweltGui.getPlugin().saveConfig();
					player.sendMessage("Der "+ arg3[0]+" Spawn wurde verlegt");
					}
					} else player.sendMessage("§cDu darfst diesen Befehl nicht verwenden");
				}
				else player.sendMessage("§cVerwende /setspawn [Oberwelt|Nether|Ende]");
					
				
			
				
				
			
		}
		else {
			arg0.sendMessage("Nur Spieler können diesen Command verwenden");
		}
		return false;
	}

}

