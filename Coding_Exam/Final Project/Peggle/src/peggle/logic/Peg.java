/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author kj
 */
public abstract class Peg implements Serializable {
    
    public static final int RED_POGO_SCORE = 1000;
    public static final int BLUE_POGO_SCORE = 100;
    
    protected int HEIGHT = 0;
    protected int WIDTH = 0;
    protected int rotation = 0;
    protected Point pos = new Point(0, 0);
    protected Rectangle area;
    protected int hitCounter = 0;
    protected int MAX_HITS = 4;
    protected boolean hit = false;
    protected boolean alive = true;

   
    protected boolean redPeg = false;
    
    public Peg() {
        this.area = new Rectangle((this.pos.x - this.WIDTH/2), (this.pos.y - this.HEIGHT/2), this.WIDTH, this.HEIGHT);
    }
    
    public boolean isAlive() {
        return this.alive;
    }
     
    public void setHit(boolean hit) {
        this.hit = hit;
        hitCounter++;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }
    public boolean isRedPeg() {
        return redPeg;
    }
    
    public void setRedPeg() {
        redPeg = true;
    }
    
    public Point getPos() {
        return pos;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int pRotation) {
        this.rotation = pRotation;
    }
    
    public void setPos(Point pPos) {
        this.pos = pPos;
        this.area = new Rectangle((this.pos.x - this.WIDTH/2), (this.pos.y - this.HEIGHT/2), this.WIDTH, this.HEIGHT);
    }
    
    public boolean isHitten() {
        return hit;
    }
    
    public abstract void draw(Graphics g);
    
    public abstract boolean isOverlapping(Ball pBall);
    
    public abstract void collisionBehavior(Ball pBall);
    
}
