package me.lucas.gamelib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import me.lucas.gamelib.game.Camera;
import me.lucas.gamelib.game.Map;
import me.lucas.gamelib.game.Maps;
import me.lucas.gamelib.game.entities.Enemy;
import me.lucas.gamelib.game.entities.enemies.BasicEnemy;
import me.lucas.gamelib.game.ui.MiniMap;
import me.lucas.gamelib.player.Player;
import me.lucas.gamelib.player.PlayerControlType;
import me.lucas.gamelib.utils.GameObject;
import me.lucas.gamelib.utils.Vector2D;

public class Game extends JPanel implements ActionListener, Runnable {
	
	private static final long serialVersionUID = 1L;

	public static List<GameObject> activeGameObjects;
    
    private Timer timer;
    
    private Thread thread;
    private boolean running;
    
    private Maps mapHandler;
    private Map map;
    
    public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	private Player player;
	private Camera camera;
	private MiniMap miniMap;
    
	private BufferedImage buffer;
	
    private InputHandler inputHandler;
    
    public InputHandler getInputHandler() {
		return inputHandler;
	}
	public int WIDTH, HEIGHT;
    public int gameWidth, gameHeight;
    
    public long seed = 34332;
    
    public Game(JFrame frame) {
    	Game.activeGameObjects = new ArrayList<GameObject>();
    	inputHandler = new InputHandler(this);
    	frame.addKeyListener(inputHandler);
        frame.addMouseListener(inputHandler);
        frame.addMouseMotionListener(inputHandler);
        HEIGHT = 760;
        WIDTH = 1280;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Vector2D playerSpawn = new Vector2D(1, 1);
        
        camera = new Camera(this, playerSpawn.subtract(new Vector2D(WIDTH/2, HEIGHT/2)), WIDTH, HEIGHT);
        mapHandler = new Maps();
        map = new Map(mapHandler.createRandomMap(seed, 500, 500)
        		, 
        		64, // Tile size - Original 64
        		this);
        
        player = new Player(this, playerSpawn);
        player.setColor(Color.BLUE)
        	.setGameLocationByTile(new Vector2D(10, 2))
        	.setSize(new Vector2D(30, 30))
        	.setSpeed(new Vector2D(6, 6))
        	.setMaxSpeed(new Vector2D(7.5, 7.5));
        

        Enemy enemy = new BasicEnemy(this, 100, 512);
        enemy.setColor(Color.RED)
        	.setGameLocationByTile(new Vector2D(2, 2))
        	.setSize(new Vector2D(20, 20))
        	.setSpeed(new Vector2D(1, 1))
        	.setMaxSpeed(new Vector2D(7.5, 7.5));
        
        
        gameWidth = map.getMap().length*map.getTileSize();
        gameHeight = map.getMap()[0].length*map.getTileSize();
        
        
        miniMap = new MiniMap(this, 
        		new Vector2D(WIDTH-(WIDTH*0.2)-25, 10),
        		new Vector2D(WIDTH*0.2, WIDTH*0.2));
        
        timer = new Timer(16, this);
        timer.start();
        start();
    } 
    public void start() {
	    if (thread == null) {
	        thread = new Thread(this);
	        running = true;
	        thread.start();
	    } else {
	    	System.exit(0);
	    }
    }
    
    public void stop() {
        if (thread != null) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread = null;
        }
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        double targetFPS = 60.0;
        double nsPerFrame = 1000000000.0 / targetFPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            repaint();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    public void update() {
        if(player.controlType == PlayerControlType.WASD) player.wasdMovement(inputHandler);
        if(inputHandler.isLeftMouseDown()) player.attack();

        // Create graphics object from buffer
        Graphics2D g2d = (Graphics2D) buffer.getGraphics();

//        // Clear the buffer <---- This is for color testing not tile images
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(0, 0, gameWidth, gameHeight);

        // Draw everything to the buffer
        map.render(g2d);
        for(GameObject obj : Game.activeGameObjects) {
        	// For sprites
//          Vector2D renderPosition = obj.getRenderPosition();
//          g.drawImage(obj.getImage(), (int) renderPosition.getX(), (int) renderPosition.getY(), null);
            obj.update(g2d);
        }

        // Dispose of the graphics object
        camera.update();
        miniMap.update(g2d);
        g2d.dispose();
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, null);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
        repaint();
	}
	public Maps getMaps() {
		return mapHandler;
	}
	public void setMaps(Maps maps) {
		this.mapHandler = maps;
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public MiniMap getMiniMap() {
		return miniMap;
	}
	public void setMiniMap(MiniMap miniMap) {
		this.miniMap = miniMap;
	}
	public Timer getTimer() {
		return timer;
	}
	public boolean isRunning() {
		return running;
	}
	public Maps getMapHandler() {
		return mapHandler;
	}
}
