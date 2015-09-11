package com.pat.ghostgame.events.blocks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.pat.ghostgame.handlers.Status;

public class BlockPlace implements Listener{

	@EventHandler
	public void onPlayerBlockBreak(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(!(Status.getStatus(player) == "ingame")){
			return;
		}
		event.setCancelled(true);
	}
}
