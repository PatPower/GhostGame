package com.pat.ghostgame.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.pat.ghostgame.GhostGame;
import com.pat.ghostgame.threads.Counting;
import com.pat.ghostgame.utils.SendMessage;

public class StartGame {

	// set spawn coords - done

	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static ArrayList<Player> playerList2 = new ArrayList<Player>();
	private static HashMap<Player, Location> spawn = new HashMap<Player, Location>();
	public static Player hunter;
	private static ArrayList<Player> survivorList = new ArrayList<Player>();
	public static int numOfSurv = 0;
	static Location pSpawn;
	static Location tSpawn;

	public static boolean start(String string) {
		setSurvivorSpawn(string);
		setHunterSpawn(string);
		int rand = (int) (Math.random() * playerList.size());
		hunter = playerList.get(rand);
		Status.setStatus(playerList.get(rand), "ingame");
		tSpawn(hunter);
		hunter.setFoodLevel(20);
		hunter.setGameMode(GameMode.SURVIVAL);
		playerList.remove(rand);
		while (!(playerList.size() <= 0)) {
			rand = (int) (Math.random() * playerList.size());
			Player player = playerList.get(rand);
			Status.setStatus(player, "ingame");
			player.setFoodLevel(20);
			survivorList.add(player);
			pSpawn(player, numOfSurv);
			numOfSurv++;
			player.setGameMode(GameMode.SURVIVAL);
			playerList.remove(rand);

		}
		setupHunter(hunter);
		teleport(hunter);
		for (Player player : survivorList) {
			setupPlayer(player);
			teleport(player);
		}
		Counting.startCountdown();
		return false;
	}

	
	private static void setSurvivorSpawn(String string){
		pSpawn = GhostGame.getS(string);
	}
	private static void setHunterSpawn(String string){
		tSpawn = GhostGame.getH(string);
	}
	
	// setup players

	public static void setupPlayer(Player player) {
		fullHP(player);
		setupArmorPlayer(player);
		setupInvPlayer(player);
	}
	
	public static void clearSurvivor(){
		survivorList.clear();
	}
	
	public static void clearPlayerList2(){
		playerList2.clear();
	}
	
	public static void clearPlayerList(){
		playerList.clear();
	}

	public static ArrayList<Player> returnPlayerList(){
		return playerList;
	}
	
	public static ArrayList<Player> returnPlayerList2(){
		return playerList2;
	}
	
	public static ArrayList<Player> returnSurvivorList(){
		return survivorList;
	}
	
	public static void removeSurvivor(Player player) {
		survivorList.remove(player);
		SendMessage.broadcast(" " + survivorList.size());
		if (survivorList.size() == 0) {
			SendMessage.broadcast("Hunters WIN!");
			Status.endGame();
		}
	}

	public static void setupHunter(Player player) {
		fullHP(player);
		setupInvHunter(player);
	}

	// functions
	public static boolean isInList(Player player) {
		return playerList.contains(player);
	}

	public static int playerLength() {
		return playerList.size();
	}

	public static void addToList(Player player) {
		playerList.add(player);
		playerList2.add(player);
	}

	public static void removePlayerList(Player player) {
		playerList.remove(player);
		playerList2.remove(player);
	}

	// returns players

	public static Player returnHunter() {
		return hunter;
	}


	// player spawns
	public static void pSpawn(Player player, int num) {
		spawn.put(player, pSpawn);
	}

	public static void tSpawn(Player player) {
		spawn.put(player, tSpawn);
	}

	// other stuff

	public static void fullHP(Player player) {
		player.setHealth(20);
	}

	@SuppressWarnings("deprecation")
	static Potion hpPot = new Potion(PotionType.REGEN, 1, true);
	static ItemStack potions = hpPot.toItemStack(1);

	public static void setupArmorPlayer(Player player) {
		if (numOfSurv >= 2) {
			player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
			player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		} else {
			player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		}
	}

	public static void setupInvPlayer(Player player) {
		ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack arrows = new ItemStack(Material.ARROW, 3);
		player.getInventory().addItem(potions);
		player.getInventory().addItem(ironSword);
		player.getInventory().addItem(bow);
		player.getInventory().addItem(arrows);
	}

	public static void setupInvHunter(Player player) {
		ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
		diamondSword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		player.getInventory().addItem(potions);
		player.getInventory().addItem(diamondSword);
	}

	public static void teleport(Player player) {
		player.teleport(spawn.get(player));
	}

	public static void stayLoc(Player player) {
		if (player.getLocation().distance(spawn.get(player)) >= 0.1) {
			player.teleport(spawn.get(player));
		}
	}

}
