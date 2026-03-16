/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import peggle.GUI.MainFrame;

/**
 *
 * @author kj
 */
public class Ball {
    
    public static final int RADIUS = 12 * MainFrame.SCALE;
    private Point defaultPos;
    private Point pos;
    private Velocity velocity;
    private DoubleCoordinate doubleCoordinate;
    private Rectangle area;
    private boolean clicked = false;
    private boolean alive = false;
    
    
    public Ball() {
        this.defaultPos = new Point(MainFrame.WIDTH_PANEL/2, 0);
        this.pos = this.defaultPos;
        this.area = new Rectangle((this.pos.x - Ball.RADIUS/2), (this.pos.y - Ball.RADIUS/2), Ball.RADIUS, Ball.RADIUS);
        this.alive = true;
        this.velocity = new Velocity();
        this.doubleCoordinate = new DoubleCoordinate();
        this.doubleCoordinate.x = pos.x;
        this.doubleCoordinate.y = pos.y;
    }

    public double getXVelocity() {
        return velocity.vx;
    }
    
    public double getYVelocity() {
        return velocity.vy;
    }
    
    public boolean isAlive() {
        return this.alive;
    }

    public Rectangle getArea() {
        return area;
    }
    
    public static int getRadius() {
        return RADIUS;
    }
    
    public Point getDefaultPos() {
        return defaultPos;
    }

//    public void setDefaultPos(Point pDefaultPos) {
//        this.defaultPos = pDefaultPos;
//    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pPos) {
        this.pos = pPos;
        this.area = new Rectangle((this.pos.x - Ball.RADIUS/2), (this.pos.y - Ball.RADIUS/2), Ball.RADIUS, Ball.RADIUS);
    }
    
    
    public void draw(Graphics g) {
        if(!this.alive) return;
        
        g.setColor(Color.DARK_GRAY);
        g.fillOval((this.pos.x - Ball.RADIUS/2), (this.pos.y - Ball.RADIUS/2), Ball.RADIUS, Ball.RADIUS);
        g.setColor(Color.CYAN);
        g.drawOval((this.pos.x - Ball.RADIUS/2) - 1, (this.pos.y - Ball.RADIUS/2) - 1, Ball.RADIUS + 1, Ball.RADIUS + 1);
        g.setColor(Color.white);
        //g.drawRect(this.area.x, this.area.y, this.area.width, this.area.height);
    }
    
//    public void setVisible(boolean pVisible) {
//        this.alive = pVisible;
//    }
    
    public void resetBallStatus() {
        this.pos = new Point(defaultPos);
        this.doubleCoordinate.x = pos.x;
        this.doubleCoordinate.y = pos.y;
        this.velocity.vx = 0;
        this.velocity.vy = 0;
        this.alive = true;
        this.clicked = false;
    }
    
    private class DoubleCoordinate {
        public double x = 0;
        public double y = 0;
    }
    
    private class Velocity {
        public double vx = 0;
        public double vy = 0;
        
        public Velocity() {
            this.vx = 0;
            this.vy = 0;
        }
        
        public Velocity(double pVx, double pVy) {
            this.vx = pVx;
            this.vy = pVy;
        }
        
        public void increaseSpeed(double pVx, double pVy) {
            this.vx += pVx;
            this.vy += pVy;
        }
        public void increaseSpeed(Velocity pVelocity) {
            this.increaseSpeed(pVelocity.vx, pVelocity.vy);
        }
    }
    
    public void increaseSpeed(double pVx, double pVy) {
        this.velocity.increaseSpeed(pVx, pVy);
    }
    
    public boolean isClicked() {
        return clicked;
    }
    
    public void move() {
        double decX = 0;
        double decY = 0;
        decX = this.doubleCoordinate.x - (int) doubleCoordinate.x;
        decY = this.doubleCoordinate.y - (int) doubleCoordinate.y;
        
        this.doubleCoordinate.x = this.pos.x;
        this.doubleCoordinate.y = this.pos.y;
        this.doubleCoordinate.x += decX;
        this.doubleCoordinate.y += decY;
        
        this.doubleCoordinate.x += this.velocity.vx * MainFrame.SCALE;
        this.doubleCoordinate.y += this.velocity.vy * MainFrame.SCALE;
        
        this.setPos(new Point((int)doubleCoordinate.x, (int)doubleCoordinate.y));
        if(this.velocity.vx > 0 && this.pos.x > (MainFrame.WIDTH_PANEL - Ball.RADIUS)) this.velocity.vx = -this.velocity.vx;
        if(this.velocity.vx < 0 && this.pos.x < (0 - Ball.RADIUS)) this.velocity.vx = -this.velocity.vx;
        if(this.velocity.vy < 0 && this.pos.y < (0 - Ball.RADIUS)) this.velocity.vy = -this.velocity.vy;
        if(this.velocity.vy > 0 && this.pos.y > (MainFrame.HEIGHT_PANEL - Ball.RADIUS)) this.alive = false;
    }
    
    public void setClick(boolean b) {
        this.clicked = b;
    }
}
