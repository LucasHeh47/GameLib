package me.lucas.gamelib.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.lucas.gamelib.Game;

public class GameObject {

	protected Color color;
	protected Vector2D location;
	protected Vector2D gameLocation;
	protected Vector2D size;
	protected Vector2D speed;
	protected Vector2D maxSpeed;
	
	protected Game game;
	
	public GameObject(Game game) {
		this.game = game;
		if(location == null && gameLocation != null) location = gameLocation;
		Game.activeGameObjects.add(this);
	}
	
	public void render(Graphics g) {
	    g.setColor(color);
	    Vector2D screenPos = getGameLocation().subtract(game.getCamera().getLocation());
	    g.fillRect(screenPos.getXint(), screenPos.getYint(), size.getXint(), size.getYint());
	}

	
	public List<GameObject> getColliders() {
		List<GameObject> list = new ArrayList<GameObject>();
		for(GameObject obj : Game.activeGameObjects) {
			if(obj == this) continue;
			if(obj.location.distance(this.getLocation()).getX() <= this.getSize().getX() ||
					obj.location.distance(this.getLocation()).getY() <= this.getSize().getY() ) list.add(obj);
		}
		return list;
	}
	
	public void destroy() {
		Game.activeGameObjects.remove(this);
	}
	
	public Vector2D getRenderPosition() {
	    Vector2D cameraPosition = game.getCamera().getLocation();
	    return new Vector2D(location.getX() - cameraPosition.getX(), location.getY() - cameraPosition.getY());
	}
	
	public Vector2D getLocation() {
		return location;
	}
	public GameObject setLocation(Vector2D location) {
		this.location = location;
		return this;
	}
	public Vector2D getGameLocation() {
		return gameLocation;
	}
	public GameObject setGameLocation(Vector2D location) {
		this.gameLocation = location;
		return this;
	}
	public Vector2D getSize() {
		return size;
	}
	public GameObject setSize(Vector2D size) {
		this.size = size;
		return this;
	}
	public Vector2D getSpeed() {
		return speed;
	}
	public GameObject setSpeed(Vector2D speed) {
		this.speed = speed;
		return this;
	}
	public Vector2D getMaxSpeed() {
		return maxSpeed;
	}
	public GameObject setMaxSpeed(Vector2D maxSpeed) {
		this.maxSpeed = maxSpeed;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public GameObject setColor(Color color) {
		this.color = color;
		return this;
	}
	
}
