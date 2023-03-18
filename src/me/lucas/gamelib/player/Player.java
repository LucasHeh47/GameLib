package me.lucas.gamelib.player;

import java.util.ArrayList;

import me.lucas.gamelib.Game;
import me.lucas.gamelib.InputHandler;
import me.lucas.gamelib.game.Camera;
import me.lucas.gamelib.game.Map;
import me.lucas.gamelib.player.attack.Attack;
import me.lucas.gamelib.utils.GameObject;
import me.lucas.gamelib.utils.Vector2D;

public class Player extends GameObject{

	private Game game;
	
	private double energy = 0;
	private int maxEnergy = 100;
	private double health = 0;
	private int maxHealth = 100;
	
	private boolean isMoving;
	private float moveToX;
	private float moveToY;
	
	private double energyUsage = 2;

	private boolean chargingEnergy = false;
	private boolean chargingHealth = false;
	
	public PlayerControlType controlType;
	
	private int mouseX, mouseY;
	
	private double energyRegenRate = 0.5;
	private int healthRegenRate = 1;
	
	private Attack[] attacks;
	
	private InputHandler inputHandler;
	
	private Camera camera;
	
	public Player(Game game, Vector2D spawnLoc) {
		super(game);
		this.game = game;
		this.camera = game.getCamera();
		this.gameLocation = spawnLoc;
		this.setAttacks(new Attack[5]);
		moveToX = spawnLoc.getXint();
		moveToY = spawnLoc.getYint();
		controlType = PlayerControlType.MOUSE;
		isMoving = true;
		health = maxHealth;
		energy = maxEnergy;
		inputHandler = game.getInputHandler();
		setLocation(camera.getCenter());
	}
	
	public void update() {
//    	System.out.println(energy);
		mouseX = (int) (inputHandler.mouseX + game.getCamera().getLocation().getX());
		mouseY = (int) (inputHandler.mouseY + game.getCamera().getLocation().getY());
		if(inputHandler.isLeftMouseDown()) {
			moveToX = mouseX - 10;
			moveToY = mouseY - 25;
		}
		if(isMoving) {
			double dx = moveToX - getGameLocation().getX();
			double dy = moveToY - getGameLocation().getY();
			double distance = Math.sqrt(dx * dx + dy * dy);
			double directionX = dx / distance;
			double directionY = dy / distance;
			double x = getGameLocation().getX() + (directionX * getSpeed().getX());
			double y = getGameLocation().getY() + (directionY * getSpeed().getY());
			Vector2D newLocX = new Vector2D(x, getGameLocation().getY());
			Vector2D newLocY = new Vector2D(getGameLocation().getX(), y);
			Map map = game.getMap();
			// Check X
			Vector2D distancev = newLocX.distance(new Vector2D(moveToX, moveToY));
			if (!(distancev.getX() < 5) && !(distancev.getY() < 5)) {
			    if (!map.doesCollide(new Vector2D(newLocX.getX(), getGameLocation().getY()), getSize())) {
			        setGameLocation(newLocX);
			        newLocY.setX(newLocX.getX());
			    }
			}

			// Check y
			Vector2D distancev2 = newLocY.distance(new Vector2D(moveToX, moveToY));
			if (!(distancev2.getX() < 5) && !(distancev2.getY() < 5)) {
			    if (!map.doesCollide(new Vector2D(getGameLocation().getX(), newLocY.getY()), getSize())) {
			        setGameLocation(newLocY);
			    }
			}


		}
		chargeHealth();
		chargeEnergy();
	}
	
	public void wasdMovement(InputHandler inputHandler) {
		int deltaX = 0;
        int deltaY = 0;

        double speedX = inputHandler.isShiftPressed() ? getMaxSpeed().getX() : getSpeed().getX();
        double speedY = inputHandler.isShiftPressed() ? getMaxSpeed().getY() : getSpeed().getY();

        // Check if the W key is pressed and move the player up
        if (inputHandler.isUpPressed()) {
            deltaY -= speedY;
        }

        // Check if the S key is pressed and move the player down
        if (inputHandler.isDownPressed()) {
            deltaY += speedY;
        }

        // Check if the A key is pressed and move the player left
        if (inputHandler.isLeftPressed()) {
            deltaX -= speedX;
        }

        // Check if the D key is pressed and move the player right
        if (inputHandler.isRightPressed()) {
            deltaX += speedX;
        }
        Vector2D delta = new Vector2D(deltaX, deltaY);
        delta = delta.normalize();
        if(inputHandler.isShiftPressed() && energy > 0) {
        	delta = delta.multiply(getMaxSpeed());
        	chargingEnergy = false;
        	energy -= energyUsage;
        } else {
        	delta = delta.multiply(getSpeed());
        	chargingEnergy = true;
        }
        Vector2D newLoc = new Vector2D(getLocation().getX() + delta.getX(), getLocation().getY() + delta.getY());
        
        setLocation(newLoc);
	}

	
	public void takeDamage(int damage) {
		health -= damage;
		if(health <= 0) {
			die();
		}
	}
	
	public void die() {
		
	}
	
	public void attack() {
		
	}
	
	private void chargeEnergy() {
		if(chargingEnergy) {
			if(energy + energyRegenRate > maxEnergy) {
				energy = maxEnergy;
				return;
			}
			energy += energyRegenRate;
		}
	}
	
	private void chargeHealth() {
		if(chargingHealth) {
			if(health + healthRegenRate > maxHealth) {
				health = maxHealth;
				return;
			}
			health += healthRegenRate;
		}
	}
	
	@Override
	public GameObject setLocation(Vector2D location) {
		super.setLocation(location);
		return this;
	}
	@Override
	public GameObject setSize(Vector2D size) {
		super.setSize(size);
		return this;
	}
	@Override
	public GameObject setSpeed(Vector2D speed) {
		super.setSpeed(speed);
		return this;
	}
	@Override
	public GameObject setMaxSpeed(Vector2D maxSpeed) {
		super.setMaxSpeed(maxSpeed);
		return this;
	}
	public Vector2D getMaxSpeed() {
		return maxSpeed;
	}
	public Vector2D getSpeed() {
		return speed;
	}
	public Vector2D getSize() {
		return size;
	}
	public Vector2D getLocation() {
		return location;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Attack[] getAttacks() {
		return attacks;
	}

	public void setAttacks(Attack[] attacks) {
		this.attacks = attacks;
	}

	public void setAttack(int attack, Attack newAttack) {
		this.attacks[attack-1] = newAttack;
	}
	
}
