package com.pat.ghostgame.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.ghostgame.handlers.Status;

public class GetStatus implements CommandExecutor {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		player.sendMessage(Status.getStatus(Bukkit.getPlayer(args[0])));
		return false;
	}
}
