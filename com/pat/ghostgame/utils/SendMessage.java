package com.pat.ghostgame.utils;

import org.bukkit.Bukkit;

public class SendMessage {
	
	public static void broadcast(String m){
		String message = ("say " + m);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
	}

}
