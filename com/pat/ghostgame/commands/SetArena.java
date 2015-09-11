package com.pat.ghostgame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.ghostgame.GhostGame;

public class SetArena implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("setS")) {
				GhostGame.setS(player, args[1].toLowerCase());
				player.sendMessage("Arena Survivor spawn set");
			} else if (args[0].equalsIgnoreCase("setH")) {
				GhostGame.setH(player, args[1].toLowerCase());
				player.sendMessage("Arena Hunter spawn set");
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (GhostGame.contains(args[1].toLowerCase())) {
					GhostGame.remove(args[1].toLowerCase());
					player.sendMessage("Arena removed");
				}else{
					player.sendMessage(args[1] + " does not exist");
				}
			}
		}
		return false;
	}
}
