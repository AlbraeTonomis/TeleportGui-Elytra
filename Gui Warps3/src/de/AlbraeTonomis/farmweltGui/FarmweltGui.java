package de.AlbraeTonomis.farmweltGui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.AlbraeTonomis.farmweltGui.cmds.FarmweltCommand;
import de.AlbraeTonomis.farmweltGui.cmds.OneTimeElytra;
import de.AlbraeTonomis.farmweltGui.cmds.SetradiusCommand;
import de.AlbraeTonomis.farmweltGui.cmds.SetspawnCommand;


public class FarmweltGui extends JavaPlugin {
	
	private static FarmweltGui plugin;
	
	public void onEnable() {
		plugin=this; 
		
		FarmweltCommand farmweltCommand = new FarmweltCommand();
		
		getCommand("setspawn").setExecutor(new SetspawnCommand());
		getCommand("setradius").setExecutor(new SetradiusCommand());
		getCommand("farmwelt").setExecutor(farmweltCommand);
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(farmweltCommand,this);
		pluginManager.registerEvents(new OneTimeElytra(),this);
		
		
	}
	public static FarmweltGui getPlugin() {
		return plugin;
	}
}
