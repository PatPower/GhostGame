package com.pat.ghostgame.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.pat.ghostgame.GhostGame;
import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;
import com.pat.ghostgame.utils.SendMessage;


@SuppressWarnings("unused")
public class Test implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
//		
//		player.removePotionEffect(PotionEffectType.INVISIBILITY);
//		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
//		player.removePotionEffect(PotionEffectType.WATER_BREATHING);
//		
//		player.getInventory().clear();
//		player.getInventory().setBoots(null);
//		player.getInventory().setHelmet(null);
//		player.getInventory().setChestplate(null);
//		player.getInventory().setLeggings(null);
//		player.teleport(new Location(Bukkit.getWorld("world"), -128, 67, 503,90,0));
//		Status.setStatus(player, "none");
//		Status.inGame = false;
//		StartGame.addToList(player);
//		StartGame.removeSurvivor(player);
//		StartGame.numOfSurv = 0;
//		SendMessage.broadcast("GAME ENDED");
//
//		Status.endGame();
		player.teleport(GhostGame.getH(args[0].toLowerCase()));
		
		return false;
	}

}
