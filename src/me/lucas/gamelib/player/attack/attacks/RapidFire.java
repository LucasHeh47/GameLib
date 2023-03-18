package me.lucas.gamelib.player.attack.attacks;

import me.lucas.gamelib.player.Player;
import me.lucas.gamelib.player.attack.Attack;
import me.lucas.gamelib.player.attack.AttackType;

public class RapidFire extends Attack {
	public static final float baseCooldown = 0.25f;
	public static final float baseDamage = 1.0f;
	public static final AttackType type = AttackType.Fire;

	public RapidFire(Player attacker) {
		super(attacker);
		this.setType(type);
		this.setBaseDamage(baseDamage);
		this.setBaseCooldown(baseCooldown);
	}

}
