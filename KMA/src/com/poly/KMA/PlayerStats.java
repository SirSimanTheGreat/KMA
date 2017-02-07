package com.poly.KMA;

import org.bukkit.entity.Player;

public class PlayerStats {

	private int money, respect, lvl;

	public PlayerStats(Player p) {

	}

	// GETTER-SETTER

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getRespect() {
		return respect;
	}

	public void setRespect(int respect) {
		this.respect = respect;

	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

}
