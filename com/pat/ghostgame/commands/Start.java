package com.pat.ghostgame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.ghostgame.GhostGame;
import com.pat.ghostgame.handlers.StartGame;

public class Start implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		
		
		Player player = (Player) sender;
		
		
		if (args.length != 1){
			player.sendMessage("/start (name of map)");
		}
		
		if (GhostGame.contains(args[0].toLowerCase())){
			player.sendMessage("Map does not exist");
		}
		
		if (StartGame.returnPlayerList().size() == 1){
			player.sendMessage("Not enough people to start");
		}else{
			StartGame.start(args[0].toLowerCase());
		}

		
		return false;
	}

}
