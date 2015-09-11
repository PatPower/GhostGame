package com.pat.ghostgame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.ghostgame.handlers.StartGame;


public class Info implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		for (Player player1 : StartGame.returnSurvivorList()) {
			player.sendMessage(player1.getName());
		}
		player.sendMessage("==");
		for (Player player1 : StartGame.returnPlayerList()) {
			player.sendMessage(player1.getName());
		}
		
		player.sendMessage("==");
		for (Player player1 : StartGame.returnPlayerList2()) {
			player.sendMessage(player1.getName());
		}

		return false;

	}

}
