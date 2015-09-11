package com.pat.ghostgame.events.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.pat.ghostgame.handlers.InGame;
import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;
import com.pat.ghostgame.threads.Counting;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerLogin(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (Status.getStatus(player) == "ingame") {
			if (Counting.countdown) {
				StartGame.stayLoc(player);
			}
			if (player != StartGame.hunter) {
				if (player.getLocation().distance(StartGame.hunter.getLocation()) <= 4) {
					InGame.hunterInvisSee();
				} else {
					InGame.hunterInvis();
				}
			}

		}
	}
}
