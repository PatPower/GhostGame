package com.pat.ghostgame.events.players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;
import com.pat.ghostgame.utils.SendMessage;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerDeathEvent event) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "remove item 9999");
		Player player = event.getEntity().getPlayer();
		if (Status.getStatus(player) == "ingame") {
			event.getDrops().clear();
			if (player == StartGame.hunter) {
				SendMessage.broadcast("Survivors WIN!");
				Status.endGame();
			} else {
				StartGame.removeSurvivor(player);
			}
		}
	}

}