package com.pat.ghostgame.handlers;




import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InGame {
	
	public static Player hunter;

	public static void start(){
		hunter = StartGame.hunter;
		hunterInvis();
		
		
	}

	public static void hunterInvis(){
		hunter.removePotionEffect(PotionEffectType.INVISIBILITY);
		hunter.removePotionEffect(PotionEffectType.NIGHT_VISION);
		hunter.removePotionEffect(PotionEffectType.WATER_BREATHING);
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 10, false, false));
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999999, 1, false, false));
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9999999, 10, false, false));
		
	}
	public static void hunterInvisSee(){
		hunter.removePotionEffect(PotionEffectType.INVISIBILITY);
		hunter.removePotionEffect(PotionEffectType.NIGHT_VISION);
		hunter.removePotionEffect(PotionEffectType.WATER_BREATHING);
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 10, false, true));
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999999, 1, false, true));
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9999999, 10, false, true));
		
	}
}
