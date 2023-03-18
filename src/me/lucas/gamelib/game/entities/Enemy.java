package me.lucas.gamelib.game.entities;

import me.lucas.gamelib.Game;
import me.lucas.gamelib.utils.GameObject;

public class Enemy extends GameObject{

	private double health = 0;
	private int maxHealth = 100;
	
	public Enemy(Game game, int maxHealth) {
		super(game);
		setMaxHealth(maxHealth);
		health = maxHealth;
	}
	
	public void takeDamage(int damage) {
		health -= damage;
		if(health <= 0) {
			die();
		}
	}
	
	private void die() {
		this.destroy();
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

}
