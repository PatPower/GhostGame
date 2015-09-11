package com.pat.ghostgame.threads;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.pat.ghostgame.handlers.InGame;
import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;
import com.pat.ghostgame.utils.SendMessage;

public class Counting extends BukkitRunnable {

	public static boolean countdown;
	public static boolean energyCD;
	private static int cd = 2;
	private static int timer = 5;

	public void run() {
		int rand = (int) (Math.random() * 20);
		if (countdown) {
			SendMessage.broadcast("Game starting in " + timer);
			timer--;
			if (timer == 0) {
				InGame.start();
				timer = 5;
				Status.inGame = true;
				countdown = false;
			}
		}
		if (energyCD) {
			cd--;
			if (cd == 0) {
				PotionEffects.canUse = true;
				StartGame.hunter.sendMessage("You can use SHIFT now");
				cd = 2;
				energyCD = false;
			}
		}
		if (rand == 4) {
			if (Status.inGame) {
				//StartGame.hunter.getWorld().playEffect(StartGame.hunter.getLocation(), Effect.GHAST_SHRIEK,
						//Material.LAVA);
			}
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setFoodLevel(20);
		}
		//SendMessage.broadcast(Status.getStatus(StartGame.hunter));
	}

	public static void startCountdown() {
		countdown = true;
	}

	public static void stopCountdown() {
		countdown = false;
	}
}