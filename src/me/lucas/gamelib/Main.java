package me.lucas.gamelib;

import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);
        
        Game game = new Game(frame);
        frame.setSize(game.WIDTH, game.HEIGHT);
        frame.add(game);
        
        frame.setVisible(true);
        if(!frame.isVisible()) System.exit(0);
    }
}
