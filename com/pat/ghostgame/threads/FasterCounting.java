package com.pat.ghostgame.threads;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;

public class FasterCounting extends BukkitRunnable {

	private static String bars = "";
	private static boolean showOnce = true;

	public void run() {

		if (Status.inGame == true) {
			if (PotionEffects.energy != 100) {
				showOnce = false;
				for (int i = 0; i < PotionEffects.energy / 10; i++) {
					bars = bars + "▒";
				}
				if (PotionEffects.energy <= 10) {
					StartGame.hunter.sendMessage(ChatColor.RED + bars + " [" + PotionEffects.energy + "%]");
				} else if (11 <= PotionEffects.energy && PotionEffects.energy <= 50) {
					StartGame.hunter.sendMessage(ChatColor.YELLOW + bars + " [" + PotionEffects.energy + "%]");
				} else if (51 <= PotionEffects.energy && PotionEffects.energy <= 99) {
					StartGame.hunter.sendMessage(ChatColor.GREEN + bars + " [" + PotionEffects.energy + "%]");

				} else {
					for (int i = 0; i < PotionEffects.energy / 10; i++) {
						bars = bars + "▒";
					}
					StartGame.hunter.sendMessage(bars + " [" + PotionEffects.energy + "%]");
					StartGame.hunter.sendMessage(ChatColor.DARK_GREEN + bars + " [" + PotionEffects.energy + "%]");

				}
			}else if (showOnce == false) {
				for (int i = 0; i < PotionEffects.energy / 10; i++) {
					bars = bars + "▒";
				}
				StartGame.hunter.sendMessage(ChatColor.DARK_GREEN + bars + " [" + PotionEffects.energy + "%]");
				showOnce = true;

			}

			bars = "";
		}
	}
}
