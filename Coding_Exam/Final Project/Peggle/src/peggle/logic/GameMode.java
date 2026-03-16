/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Graphics;

/**
 *
 * @author kj
 */
public abstract class GameMode {
    
    protected boolean gameRunning = false;
    
    
    public boolean isGameRunning() {
        return gameRunning;
    }

    public abstract int getScore(Throw currentThrow, int pRedCount);

    public abstract void endGame();
    
    public abstract void draw(Graphics g);
    
    public abstract void gameStart();
}
