package com.pat.ghostgame.events.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.pat.ghostgame.handlers.Status;

public class PlayerItemDrop implements Listener {
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (Status.getStatus(player) == "ingame"){
			event.setCancelled(true);
		}
	}
}
