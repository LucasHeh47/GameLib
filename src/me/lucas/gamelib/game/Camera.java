package me.lucas.gamelib.game;

import java.awt.Rectangle;

import me.lucas.gamelib.Game;
import me.lucas.gamelib.utils.Vector2D;

public class Camera {
	
	private Vector2D location;
	private Game game;
	public int width, height;
	
	public Camera(Game game, Vector2D location, int width, int height) {
		this.game = game;
		this.setLocation(location);
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		Vector2D playerLocation = game.getPlayer().getGameLocation();
		double dx = playerLocation.getX() - (location.getX() + width / 2);
		double dy = playerLocation.getY() - (location.getY() + height / 2);
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance > 10) { // Only move camera if player is far enough away
		    double directionX = dx / distance;
		    double directionY = dy / distance;
		    double moveX = directionX * Math.min(distance / 10, 5);
		    double moveY = directionY * Math.min(distance / 10, 5);
		    setLocation(new Vector2D(location.getX() + (moveX*game.getPlayer().getSpeed().getX()), location.getY() + (moveY*game.getPlayer().getSpeed().getY())));
		}
		if(location.getX() < 0) setLocation(new Vector2D(0, getLocation().getY()));
		if(location.getY() < 0) setLocation(new Vector2D(getLocation().getX(), 0));
		if(location.getX() + width > game.gameHeight) setLocation(new Vector2D(game.gameHeight-width, getLocation().getY()));
		if(location.getY() + height > game.gameWidth) setLocation(new Vector2D(getLocation().getX(), game.gameWidth-height));


	}
	
	public Vector2D getCenter() {
		return new Vector2D(width/2, height/2);
	}

	public Vector2D getLocation() {
		return location;
	}

	public void setLocation(Vector2D location) {
		this.location = location;
	}
	
	public Rectangle getViewport() {
	    return new Rectangle((int) location.getX(), (int) location.getY(), width, height);
	}


}
