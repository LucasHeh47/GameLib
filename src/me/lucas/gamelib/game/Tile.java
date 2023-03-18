package me.lucas.gamelib.game;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
    private Image asset;
    private Color color;
    private int id;

    public Tile(String assetFile, int id) {
       setAsset(assetFile);
       this.id = id;
    }

    public Image getAsset() {
        return asset;
    }
   

    public void setAsset(String filePath) {
        try {
            String fullPath = System.getProperty("user.dir") + "/src/me/lucas/gamelib/game/assets/" + filePath;
            asset = ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
