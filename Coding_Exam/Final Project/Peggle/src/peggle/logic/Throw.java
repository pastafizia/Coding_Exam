/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import peggle.GUI.MainFrame;

/**
 *
 * @author kj
 */
public class Throw {
    
    private int showTime = 2;
//    private int totalScore = 0;
    private int score = 0;

   
//    private boolean endedThrow = false;
    private boolean hittenRedPeg = false;
    
    private ArrayList<Peg> hittenPeg;
    
    public Throw() {
        this.hittenPeg = new ArrayList<>();
    }
    
    public void setScore(int pScore) {
        this.score = pScore;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setHittenPeg(Peg p) {
        this.hittenPeg.add(p);
        this.showTime = 2;
        if(p.isRedPeg()) {
            this.hittenRedPeg = true;
            this.score += Peg.RED_POGO_SCORE;
//            this.totalScore += 1000;
        }
        else {
//            this.totalScore += 100;
            this.score += Peg.BLUE_POGO_SCORE;
        }
    }
    
    public ArrayList<Peg> getHittenPegList() {
        return this.hittenPeg;
    }
    
    public int getShowTime() {
        return showTime;
    }
    
    public void decreaseShowTime() {
        if(this.showTime > 0) this.showTime--;
    }
    
    public boolean isRedPegHit() {
        return this.hittenRedPeg;
    }
//    public void showTotalScore() {
//        this.endedThrow = true;
//        this.showTime = 2;
//    }
    
    public void draw(Graphics g) {
        if(this.showTime > 0) {
            
//            if(endedThrow) {
//                g.setColor(Color.black);
//                g.setFont(new Font("Helvetica", Font.BOLD, 20*MainFrame.SCALE));
//                g.drawString((this.totalScore + ""), MainFrame.WIDTH_PANEL/2, MainFrame.HEIGHT_PANEL/4);
//            }
//            else {
                g.setColor(Color.black);
                g.setFont(new Font("Helvetica", Font.BOLD, 20*MainFrame.SCALE));
                g.drawString((this.score + ""), MainFrame.WIDTH_PANEL/2, MainFrame.HEIGHT_PANEL/4);
//            }
        }
        
    }
    
    
}
