/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;
import peggle.GUI.MainFrame;

/**
 *
 * @author kj
 */
public class GameModeDuel extends GameMode {

    //private boolean draw = false;
    private Player playerTrue;
    private Player playerFalse;
    
    private boolean currentPlayer;
    
    public GameModeDuel() {
        gameRunning = false;
        playerTrue = new Player("cheeki", 10);
        playerFalse = new Player("breeki", 10);
        Random coinFlip = new Random();
        currentPlayer = coinFlip.nextBoolean();
    }
    
    public Player getCurrentPlayer() {
        if(this.currentPlayer == true) {
            return this.playerTrue;
        }
        return this.playerFalse;
    }
    
    private Player getTrueFalsePlayer(boolean b) {
        if(b) return playerTrue;
        return playerFalse;
    }
    
    public void switchPlayers() {
        if(this.getTrueFalsePlayer(!this.currentPlayer).getThrowNumber() > 0) this.currentPlayer = !this.currentPlayer;
    }
    
    public Player getWinnerPlayer() {
        if(this.playerTrue.getScore() == this.playerFalse.getScore()) return null;
        if(this.playerTrue.getScore() > this.playerFalse.getScore()) return this.playerTrue;
        return this.playerFalse;
    }
    
    @Override
    public void gameStart() {
        gameRunning = true;
    }

    @Override
    public int getScore(Throw currentThrow, int pRedCount) {
        this.getCurrentPlayer().decreaseThrowNumber();
        
        if(currentThrow.isRedPegHit()) {
            this.getCurrentPlayer().increaseScore(currentThrow.getScore());
        }
        else {
            double negative25PerCent = 0.0;
            negative25PerCent = - (this.getCurrentPlayer().getScore() * 25 / 100);
            currentThrow.setScore((int) negative25PerCent);
            this.getCurrentPlayer().increaseScore((int) negative25PerCent);
        }
            
        if ((this.playerTrue.getThrowNumber() <= 0 && this.playerFalse.getThrowNumber() <= 0) || pRedCount <= 0) {
            this.gameRunning = false;
        } 
        else {
            this.switchPlayers();
        }
        
        return currentThrow.getScore();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.BOLD, 20*MainFrame.SCALE));
        g.drawString(("score"), MainFrame.WIDTH_PANEL/17, 25*MainFrame.SCALE);
        g.drawString(("balls"), MainFrame.WIDTH_PANEL/17, 80*MainFrame.SCALE);
        g.setFont(new Font("Helvetica", Font.BOLD, 40*MainFrame.SCALE));
        g.drawString((this.getCurrentPlayer().getScore() + ""), MainFrame.WIDTH_PANEL/17, 60*MainFrame.SCALE);
        g.drawString((this.getCurrentPlayer().getThrowNumber() + ""), MainFrame.WIDTH_PANEL/17, 120*MainFrame.SCALE);
        g.drawString((this.getCurrentPlayer().getName() + ""), MainFrame.WIDTH_PANEL*11/15, 40*MainFrame.SCALE);
    }
    
    @Override
    public void endGame() {
        if(this.getWinnerPlayer() == null) {
            JOptionPane.showMessageDialog(null, " DRAW ");
        }
        else {
            JOptionPane.showMessageDialog(null, this.getWinnerPlayer().getName() + " won with " + this.getWinnerPlayer().getScore() + " score!");
        }
        MainFrame.showPanel(MainFrame.WELCOME);
    }

}
