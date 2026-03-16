/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import peggle.GUI.MainFrame;

/**
 *
 * @author kj
 */
public class GameModeSolo extends GameMode {

    private Player player;
    
    public GameModeSolo() {
        gameRunning = false;
        player = new Player("ezPotat", 15);
    }
    
    @Override
    public void gameStart() {
        gameRunning = true;
    }
    
    @Override
    public int getScore(Throw currentThrow, int pRedCount) {
        this.player.decreaseThrowNumber();
        this.player.increaseScore(currentThrow.getScore());
        if (this.player.getThrowNumber() <= 0 || pRedCount <= 0) {
            this.gameRunning = false;
        }
        return currentThrow.getScore();
    }

    @Override
    public void endGame() {
        JOptionPane.showMessageDialog(null, this.player.getName() + " won with " + this.player.getScore() + " score!");
        MainFrame.showPanel(MainFrame.WELCOME);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.BOLD, 20*MainFrame.SCALE));
        g.drawString(("score"), MainFrame.WIDTH_PANEL/17, 25*MainFrame.SCALE);
        g.drawString(("balls"), MainFrame.WIDTH_PANEL/17, 80*MainFrame.SCALE);
        g.setFont(new Font("Helvetica", Font.BOLD, 40*MainFrame.SCALE));
        g.drawString((this.player.getScore() + ""), MainFrame.WIDTH_PANEL/17, 60*MainFrame.SCALE);
        g.drawString((this.player.getThrowNumber() + ""), MainFrame.WIDTH_PANEL/17, 120*MainFrame.SCALE);
        g.drawString((this.player.getName() + ""), MainFrame.WIDTH_PANEL*11/15, 40*MainFrame.SCALE);
    }
    
}
