package com.pat.ghostgame.events.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (player == StartGame.hunter){
			Status.endGame();
		}
		if (Status.getStatus(player) == "ingame") {
			Status.setStatus(player, null);
			StartGame.removeSurvivor(player);
		} else if (Status.getStatus(player) == "lobby") {
			StartGame.removePlayerList(player);
		}

	}
}
