package me.lucas.gamelib;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
    
    // Declare any variables or objects you need to handle input
	private Game game;
    
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean shiftPressed = false;
    
    private boolean leftMouseDown = false;
    
    public float mouseX, mouseY;
    
    public InputHandler(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
        	shiftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
        	shiftPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) {
    		leftMouseDown = true;
    	}
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) {
    		leftMouseDown = false;
    	}
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter event
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit event
    }
    

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isShiftPressed() {
		return shiftPressed;
	}

	public boolean isLeftMouseDown() {
		return leftMouseDown;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}
    
}
