package me.lucas.gamelib.game.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.plaf.basic.BasicGraphicsUtils;

import me.lucas.gamelib.Game;
import me.lucas.gamelib.utils.Vector2D;

public class MiniMap {

	private Game game;
	
	private Vector2D location;
	private Vector2D size;
	
	public MiniMap(Game game, Vector2D location, Vector2D size) {
		this.game = game;
		this.location = location;
		this.size = size;
	}
	
	public void update(Graphics g) {
		render(g);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(location.getXint(), 
				location.getYint(), 
				size.getXint(), 
				size.getYint());
	}
	
	
	public Vector2D getSize() {
		return size;
	}

	public void setSize(Vector2D size) {
		this.size = size;
	}

	public Vector2D getLocation() {
		return location;
	}

	public void setLocation(Vector2D location) {
		this.location = location;
	}

}
