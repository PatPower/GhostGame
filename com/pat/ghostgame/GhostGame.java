package com.pat.ghostgame;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.pat.ghostgame.commands.GG;
import com.pat.ghostgame.commands.GetStatus;
import com.pat.ghostgame.commands.Info;
import com.pat.ghostgame.commands.SetArena;
import com.pat.ghostgame.commands.Start;
import com.pat.ghostgame.commands.Test;
import com.pat.ghostgame.config.MyConfig;
import com.pat.ghostgame.config.MyConfigManager;
import com.pat.ghostgame.events.blocks.BlockBreak;
import com.pat.ghostgame.events.blocks.BlockPlace;
import com.pat.ghostgame.events.players.PlayerDeath;
import com.pat.ghostgame.events.players.PlayerItemDrop;
import com.pat.ghostgame.events.players.PlayerJoin;
import com.pat.ghostgame.events.players.PlayerMove;
import com.pat.ghostgame.events.players.PlayerQuit;
import com.pat.ghostgame.threads.Counting;
import com.pat.ghostgame.threads.FasterCounting;
import com.pat.ghostgame.threads.PotionEffects;

public class GhostGame extends JavaPlugin {

	public static MyConfigManager manager;
	public static MyConfig arenas;

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		manager = new MyConfigManager(this);
		arenas = manager.getNewConfig("arenas.yml");
		arenas.saveConfig();
		registerCommands();
		registerEvents();
		startThreads();

		logger.info(pdfFile.getName() + " has been enabled. Version. " + pdfFile.getVersion() + ".");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		// arenas.saveConfig();
		logger.info(pdfFile.getName() + " has been disabled. Version. " + pdfFile.getVersion() + ".");
	}
	// configs

	public static void setS(Player player, String name) {
		arenas.set(name + ".survivor" + ".x", player.getLocation().getX());
		arenas.set(name + ".survivor" + ".y", player.getLocation().getY());
		arenas.set(name + ".survivor" + ".z", player.getLocation().getZ());
		arenas.set(name + ".survivor" + ".yaw", player.getLocation().getYaw());
		arenas.set(name + ".survivor" + ".pitch", player.getLocation().getPitch());
		arenas.saveConfig();
	}

	public static void setH(Player player, String name) {
		arenas.set(name + ".hunter" + ".world", player.getWorld().getName());
		arenas.set(name + ".hunter" + ".x", player.getLocation().getX());
		arenas.set(name + ".hunter" + ".y", player.getLocation().getY());
		arenas.set(name + ".hunter" + ".z", player.getLocation().getZ());
		arenas.set(name + ".hunter" + ".yaw", player.getLocation().getYaw());
		arenas.set(name + ".hunter" + ".pitch", player.getLocation().getPitch());
		arenas.saveConfig();
	}

	public static Location getH(String name) {
		Location location = new Location(Bukkit.getWorld("world"), arenas.getDouble(name + ".hunter" + ".x"),
				arenas.getDouble(name + ".hunter" + ".y"), arenas.getDouble(name + ".hunter" + ".z"),
				(float) arenas.getDouble(name + ".hunter" + ".yaw"),
				(float) arenas.getDouble(name + ".hunter" + ".pitch"));
		return location;
	}
	
	public static boolean contains(String string){
		return arenas.contains(string);
	}
	
	public static void remove(String string){
		arenas.removeKey(string);
		arenas.saveConfig();
	}

	public static Location getS(String name) {
		Location location = new Location(Bukkit.getWorld("world"), arenas.getDouble(name + ".survivor" + ".x"),
				arenas.getDouble(name + ".survivor" + ".y"), arenas.getDouble(name + ".hunter" + ".z"),
				(float) arenas.getDouble(name + ".survivor" + ".yaw"),
				(float) arenas.getDouble(name + ".survivor" + ".pitch"));
		return location;
	}

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerItemDrop(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);

	}

	@SuppressWarnings("deprecation")
	public void startThreads() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Counting(), 20, 20);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new FasterCounting(), 5, 5);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new PotionEffects(), 1, 1);
	}

	public void registerCommands() {
		getCommand("gg").setExecutor(new GG());
		getCommand("start").setExecutor(new Start());
		getCommand("test").setExecutor(new Test());
		getCommand("intel").setExecutor(new Info());
		getCommand("getstatus").setExecutor(new GetStatus());
		getCommand("setarena").setExecutor(new SetArena());
	}

}
