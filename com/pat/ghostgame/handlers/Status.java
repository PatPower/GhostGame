package com.pat.ghostgame.handlers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.pat.ghostgame.utils.SendMessage;

public class Status {

	private static HashMap<UUID, String> status = new HashMap<UUID, String>();
	public static boolean inGame = false;

	public static void addPlayer(Player player) {
		UUID name = player.getUniqueId();
		if (status.containsKey(name)) {
			String message = "say Welcome Back! " + player.getName();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
			return;
		} else {
			status.put(name, null);
			String message = "say Welcome to the server " + player.getName();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
		}

	}

	// no use unless for testing
	public static void removePlayer(Player player) {
		UUID name = player.getUniqueId();
		status.remove(name);
	}

	public static String getStatus(Player player) {
		UUID name = player.getUniqueId();
		return status.get(name);
	}

	public static int length() {
		return status.size();
	}

	// public static Player findTarget(Player player) {
	// int team = playing.get(player);
	//
	// for (Player p : Bukkit.getOnlinePlayers()) {
	// if (playing.containsValue(team) && p != player) {
	// return p;
	// }
	// }
	// return null;
	// }

	public static void endGame() {
		for (Player player : StartGame.returnPlayerList2()) {
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			player.removePotionEffect(PotionEffectType.WATER_BREATHING);
			player.getInventory().clear();
			player.getInventory().setBoots(null);
			player.getInventory().setHelmet(null);
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.teleport(new Location(Bukkit.getWorld("world"), -128, 67, 503, 90, 0));
			setStatus(player, "none");
		}
		StartGame.clearPlayerList();
		StartGame.clearPlayerList2();
		StartGame.clearSurvivor();
		StartGame.numOfSurv = 0;
		inGame = false;
		SendMessage.broadcast("GAME ENDED");
	}

	public static void setStatus(Player player, String string) {
		UUID name = player.getUniqueId();
		status.put(name, string);
	}

}