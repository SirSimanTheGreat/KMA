package com.poly.CommandExecutor;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.poly.KMA.KMA;

public class Command_createMission implements CommandExecutor {

	public Command_createMission(KMA plugin) {

	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] arg) {

		if (cs instanceof Player) {
			Player p = (Player) cs;
			if (cmd.getName().equalsIgnoreCase("createmission")) {
				if (p.hasPermission("createmission")) {
					if (arg.length < 4) {
						return false;
					} else if (arg.length == 4) {
						if (!arg[3].equalsIgnoreCase("headhunter") && !arg[3].equalsIgnoreCase("stealth")
								&& !arg[3].equalsIgnoreCase("main") && !arg[3].equalsIgnoreCase("protection")) {
							return false;
						} else {
							createMission(p, arg);
						}

					}
				}

			}
		} else {
			cs.sendMessage("Dieser Command ist nur für Spieler!");
		}

		return true;
	}

	public void createMission(Player p, String[] arg) {

		File ordner = new File("plugins//KMA//missions");
		File file = new File("plugins//KMA//missions//missions.yml");

		if (!ordner.exists()) {
			ordner.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				p.sendMessage("File wurde erstellt!");
			}
		}

		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

		cfg.set(arg[0] + "-name", arg[0]);
		cfg.set(arg[0] + "cost", arg[1]);
		cfg.set(arg[0] + "reward", arg[2]);
		cfg.set(arg[0] + "typ", arg[3]);

		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		p.sendMessage("Mision erfolgreich erstellt!");
	}

}
