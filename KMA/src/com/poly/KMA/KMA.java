package com.poly.KMA;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import com.poly.CommandExecutor.Command_createMission;

import poly.com.listeners.Listener_onPlayerJoin;

public class KMA extends JavaPlugin {

	public void onEnable() {

		File ordner = new File("plugins//KMA//configs//");
		if (!ordner.exists()) {
			ordner.mkdir();
		}

		Map<String, PlayerStats> players = new HashMap<String, PlayerStats>();		//HashMap für Spieler und deren Stats, Spieler Name ist der Unique Key

		getServer().getPluginManager().registerEvents(new Listener_onPlayerJoin(this, players), this);

		getCommand("createmission").setExecutor(new Command_createMission(this));

		getLogger().info("KMA loaded sucessfully!");
	}

	public void onDisable() {
		getLogger().info("KMA sucessfully disabled!");
	}

}

/*
 * TODO: - PlayerStats: Bei den Set Methoden muss noch zurück in die CFG gespeichert werden!!!
 */