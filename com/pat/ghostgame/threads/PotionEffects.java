package com.pat.ghostgame.threads;


import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.pat.ghostgame.handlers.StartGame;
import com.pat.ghostgame.handlers.Status;


public class PotionEffects extends BukkitRunnable {

	public static int energy = 100;
	public static boolean canUse = true;

	public void run() {

		if (Status.inGame) {
			Player hunter = StartGame.hunter;
			if (hunter.isSneaking() && canUse) {
				hunter.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 6, false, false));
				hunter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 40, false, false));
				energy = energy - 2;
				if (energy <= 0){
					energy = 0;
					hunter.sendMessage("Wait 2 seconds to use SHIFT");
					canUse = false;
					Counting.energyCD = true;
				}

			} else {
				hunter.removePotionEffect(PotionEffectType.JUMP);
				hunter.removePotionEffect(PotionEffectType.SPEED);
				if (energy != 100){
				energy++;
				}

			}
		}
	}
}