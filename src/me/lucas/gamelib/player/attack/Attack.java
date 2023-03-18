package me.lucas.gamelib.player.attack;

import me.lucas.gamelib.player.Player;

public abstract class Attack {
	
	private AttackType type;
	private float baseDamage;
	private float baseCooldown;
	
	public Player attacker;
	
	public Attack(Player attacker) {
		this.attacker = attacker;
	}

	public float getBaseDamage() {
		return baseDamage;
	}

	protected void setBaseDamage(float baseDamage) {
		this.baseDamage = baseDamage;
	}

	public float getBaseCooldown() {
		return baseCooldown;
	}

	protected void setBaseCooldown(float baseCooldown) {
		this.baseCooldown = baseCooldown;
	}

	public AttackType getType() {
		return type;
	}

	protected void setType(AttackType type) {
		this.type = type;
	}

}
