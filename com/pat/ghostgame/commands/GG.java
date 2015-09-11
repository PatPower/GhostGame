package com.pat.ghostgame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;
import com.pat.ghostgame.utils.SendMessage;

public class GG implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}

		Player player = (Player) sender;

		if (Status.getStatus(player) == "ingame" || Status.getStatus(player) == "lobby" || StartGame.isInList(player)) {
		player.sendMessage("You are already in a game");
			return false;
		}

		if (args.length == 0) {
			sender.sendMessage("/gg join");
			return false;
		} 


		if(args[0].equalsIgnoreCase("join")) {
			StartGame.addToList(player);
			player.sendMessage("You are in the lobby");
			Status.setStatus(player, "lobby");
			SendMessage.broadcast(player.getName() + " has joined the lobby");
			return true;
		}
		
		
		
		return false;
	}
}
