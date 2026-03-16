/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import peggle.GUI.MainFrame;

/**
 *
 * @author kj
 */
public class Map implements Serializable {
    
    private int startingRedPeg = 25;
    public ArrayList<Peg> pegList;
    private boolean ready = false;
   
    
    public Map() {
        pegList = new ArrayList<>();
    }
  
    public Map(int pStartingRedPeg) {
        this.startingRedPeg = pStartingRedPeg;
        pegList = new ArrayList<>();
    }
    
    public int getRedPegCount() {
        int redCount = 0;
        for(Peg peg : pegList) {
            if(peg.isRedPeg() && peg.alive) redCount++;
        }
        return redCount;
    }
    
    public int getStartingRedPeg() {
        return startingRedPeg;
    }
    
    public void draw(Graphics g) {
        for(Peg peg : pegList) {
            peg.draw(g);
        }
    }
    
    private void setRedPeg() {
        int redCount = 1;
        Random random = new Random();
        do {
            for(Peg peg : pegList) {
                if(redCount >= this.startingRedPeg) this.ready = true; 
                if (random.nextInt(100*pegList.size()) < pegList.size()) {
                    peg.setRedPeg();
                    redCount++;
                }
            }
        } while (redCount <= this.startingRedPeg); 
    }
    
    private void deleteOutOfBoundPeg() {
        ArrayList<Peg> deadPeg = new ArrayList<>();
        for(Peg peg : pegList) {
            if(peg.pos.x >= MainFrame.WIDTH_PANEL || peg.pos.x <= 0 || peg.pos.y >= MainFrame.HEIGHT_PANEL || peg.pos.y <= 0)
                deadPeg.add(peg);
        }
        pegList.removeAll(deadPeg);
    }
    
    public void initMap() {
        deleteOutOfBoundPeg();
        setRedPeg();
        
        this.ready = true;
    }
    
    
    public boolean isReady() {
        return this.ready;
    }
}
