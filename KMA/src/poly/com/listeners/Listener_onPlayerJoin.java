package poly.com.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.ChatColor;

import com.poly.KMA.KMA;
import com.poly.KMA.PlayerStats;

public class Listener_onPlayerJoin implements Listener {

	protected KMA plugin;
	protected Map<String, PlayerStats> players;	//Klassenvariable

	public Listener_onPlayerJoin(KMA plugin, Map<String, PlayerStats> map2) {
		this.plugin = plugin;
		this.players = map2;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		loadPlayer(p);
		setScoreBoard(p);
	}

	private void setScoreBoard(Player p) {
		
		Scoreboard scb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objv = scb.registerNewObjective("test", "dummy");
		File file = new File("plugins//KMA//configs//" + p.getName() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int lvl = cfg.getInt("lvl");
		int money = cfg.getInt("money");
		int respect = cfg.getInt("respect");
		objv.setDisplaySlot(DisplaySlot.SIDEBAR);
		objv.setDisplayName(ChatColor.GOLD + "Stats");
		
		Score two = objv.getScore(ChatColor.GREEN + "Level: ");
		two.setScore(lvl);
		Score one = objv.getScore(ChatColor.GREEN + "Geld: ");
		one.setScore(money);
		Score zero = objv.getScore(ChatColor.GREEN + "Respekt: ");
		zero.setScore(respect);
		
		
		p.setScoreboard(scb);
	}

	public void loadPlayer(Player p) {

		File file = new File("plugins//KMA//configs//" + p.getName() + ".yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {

			}

			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

			cfg.set("money", 10);
			cfg.set("lvl", 1);
			cfg.set("respect", 0);

			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

			PlayerStats ps = new PlayerStats(p);		//laden der Stats in ein Objekt

			ps.setMoney(cfg.getInt("money"));		
			ps.setRespect(cfg.getInt("respect"));
			ps.setLvl(cfg.getInt("lvl"));

			players.put(p.getName(), ps);					//Speichern des Objekts mit dem Key in eine Map

			ps = players.get(p.getName());					//p.getName ist der Uniqe Key der Map darin ingetragen ist das StatObjekt.
			
			p.sendMessage(
					"Dein Lvl: " + ps.getLvl() + " Dein Respect: " + ps.getRespect() + " Dein Geld: " + ps.getMoney());

		}
	}

}
