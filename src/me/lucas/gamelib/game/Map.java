package me.lucas.gamelib.game;

import java.awt.Graphics;

import me.lucas.gamelib.Game;
import me.lucas.gamelib.utils.GameObject;
import me.lucas.gamelib.utils.Vector2D;

public class Map {
	
	private Game game;
	
	private int[][] map;
	
	private int tileSize;
	
	public Map(int[][] map, int tileSize, Game game) {
		this.game = game;
		this.map = map;
		this.tileSize = tileSize;
	}
	
	public void render(Graphics g) {
	    int cameraX = game.getCamera().getLocation().getXint();
	    int cameraY = game.getCamera().getLocation().getYint();
	    int screenWidth = game.WIDTH;
	    int screenHeight = game.HEIGHT;

	    int firstCol = cameraX / tileSize;
	    int lastCol = (cameraX + screenWidth) / tileSize;
	    int firstRow = cameraY / tileSize;
	    int lastRow = (cameraY + screenHeight) / tileSize;

	    for (int i = firstRow; i <= lastRow; i++) { // iterate over visible rows
	        for (int j = firstCol; j <= lastCol; j++) { // iterate over visible columns
	            if (i < 0 || j < 0 || i >= map.length || j >= map[i].length) {
	                continue; // tile is outside the map bounds
	            }
	            int x = j * tileSize - cameraX;
	            int y = i * tileSize - cameraY;
	            g.drawImage(game.getMaps().tiles.get(map[i][j]).getAsset(), x, y, tileSize, tileSize, null);
	        }
	    }
	}

	
	public boolean doesCollide(Vector2D loc) {
		int tile = this.getMap()
	    		[(int)loc.getY()/this.getTileSize()]
	    		[(int)loc.getX()/this.getTileSize()];
	    if(tile <= 0) return false;
	    return true;
	}
	
	public boolean doesCollide(GameObject obj) {
	    int tileSize = getTileSize();
	    int leftTile = (int) (obj.getLocation().getX() / tileSize);
	    int topTile = (int) (obj.getLocation().getY() / tileSize);
	    int rightTile = (int) ((obj.getLocation().getX() + obj.getSize().getX()) / tileSize);
	    int bottomTile = (int) ((obj.getLocation().getY() + obj.getSize().getY()) / tileSize);

	    boolean collision = false;
	    if (getMap()[topTile][leftTile] > 0 ||
	        getMap()[bottomTile][leftTile] > 0 ||
	        getMap()[topTile][rightTile] > 0 ||
	        getMap()[bottomTile][rightTile] > 0) {
	        collision = true;
	    }
	    return collision;
	}
	
	public boolean doesCollide(Vector2D loc, Vector2D size) {
	    int tileSize = getTileSize();
	    int leftTile = (int) (loc.getX() / tileSize);
	    int topTile = (int) (loc.getY() / tileSize);
	    int rightTile = (int) ((loc.getX() + size.getX()) / tileSize);
	    int bottomTile = (int) ((loc.getY() + size.getY()) / tileSize);

	    boolean collision = false;
	    if (getMap()[topTile][leftTile] > 0 ||
	        getMap()[bottomTile][leftTile] > 0 ||
	        getMap()[topTile][rightTile] > 0 ||
	        getMap()[bottomTile][rightTile] > 0) {
	        collision = true;
	    }
	    return collision;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

}
