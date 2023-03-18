package me.lucas.gamelib.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.lucas.gamelib.Game;
import me.lucas.gamelib.game.Map;
import me.lucas.gamelib.utils.GameObject;
import me.lucas.gamelib.utils.Vector2D;

public class Enemy extends GameObject{

	private double health = 0;
	private int maxHealth = 100;
	
	private boolean aggrovated = false;
	
	private int viewRange;
	
	private int[][] map;
	
	public Enemy(Game game, int maxHealth, int viewRange) {
		super(game);
		this.setViewRange(viewRange);
		setMaxHealth(maxHealth);
		health = maxHealth;
		map = game.getMap().getMap();
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		if(checkInDistanceToPlayer()) {
			setColor(Color.green);
			double dx = game.getPlayer().getGameLocation().getX() - getGameLocation().getX();
			double dy = game.getPlayer().getGameLocation().getY() - getGameLocation().getY();
			double distance = Math.sqrt(dx * dx + dy * dy);
			double directionX = dx / distance;
			double directionY = dy / distance;
			double x = getGameLocation().getX() + (directionX * getSpeed().getX());
			double y = getGameLocation().getY() + (directionY * getSpeed().getY());
			Vector2D newLocX = new Vector2D(x, getGameLocation().getY());
			Vector2D newLocY = new Vector2D(getGameLocation().getX(), y);
			Map map = game.getMap();
			// Check X
			Vector2D distancev = newLocX.distance(new Vector2D(game.getPlayer().getGameLocation().getX(), game.getPlayer().getGameLocation().getY()));
			if (!(distancev.getX() < 5) && !(distancev.getY() < 5)) {
			    if (!map.doesCollide(new Vector2D(newLocX.getX(), getGameLocation().getY()), getSize())) {
			        setGameLocation(newLocX);
			        newLocY.setX(newLocX.getX());
			    }
			}

			// Check y
			Vector2D distancev2 = newLocY.distance(new Vector2D(game.getPlayer().getGameLocation().getX(), game.getPlayer().getGameLocation().getY()));
			if (!(distancev2.getX() < 5) && !(distancev2.getY() < 5)) {
			    if (!map.doesCollide(new Vector2D(getGameLocation().getX(), newLocY.getY()), getSize())) {
			        setGameLocation(newLocY);
			    }
			}
		} else setColor(Color.red);
	}
	
	private boolean checkInDistanceToPlayer() {
		return getGameLocation().distance(game.getPlayer().getGameLocation()).getX() <= this.getViewRange() &&
				getGameLocation().distance(game.getPlayer().getGameLocation()).getY() <= this.getViewRange();
	}


	private double getDistance(int x1, int y1, int x2, int y2) {
	    int dx = x2 - x1;
	    int dy = y2 - y1;
	    return Math.sqrt(dx * dx + dy * dy);
	}
	
	public List<Point> getPathToPlayer(Point playerPosition) {
	    int[][] dist = new int[map.length][map[0].length];
	    Point[][] next = new Point[map.length][map[0].length];

	    // Initialize the distance matrix
	    for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[i].length; j++) {
	            if (map[i][j] > 0) {
	                dist[i][j] = Integer.MAX_VALUE;
	            } else {
	                dist[i][j] = 0;
	            }
	            next[i][j] = null;
	        }
	    }

	    // Run the Warshall algorithm
	    for (int k = 0; k < map.length; k++) {
	        for (int i = 0; i < map.length; i++) {
	            for (int j = 0; j < map[i].length; j++) {
	                if (dist[i][j] > dist[i][k] + dist[k][j]) {
	                    dist[i][j] = dist[i][k] + dist[k][j];
	                    next[i][j] = next[i][k];
	                }
	            }
	        }
	    }

	    // Reconstruct the shortest path
	    List<Point> path = new ArrayList<>();
	    Point current = new Point(getGameLocation().getXint(), getGameLocation().getYint());
	    while (current != null) {
	        path.add(current);
	        current = next[current.x][current.y];
	    }

	    // Reverse the path so that it starts at the enemy position and ends at the player position
	    Collections.reverse(path);

	    return path;
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

	public int getViewRange() {
		return viewRange;
	}

	public void setViewRange(int viewRange) {
		this.viewRange = viewRange;
	}

	public boolean isAggrovated() {
		return aggrovated;
	}

	public void setAggrovated(boolean aggrovated) {
		this.aggrovated = aggrovated;
	}

}
