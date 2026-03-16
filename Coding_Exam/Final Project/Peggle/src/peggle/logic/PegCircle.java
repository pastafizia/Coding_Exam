/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import peggle.GUI.MainFrame;

/**
 *
 * @author kj
 */
public class PegCircle extends Peg {
    
    public static final int RADIUS = 30 * MainFrame.SCALE;
    
    public PegCircle(Point pPos) {
        HEIGHT = 30 * MainFrame.SCALE;
        WIDTH = 30 * MainFrame.SCALE;
        this.setPos(pPos);
    }
    
    @Override
    public boolean isOverlapping(Ball pBall) {
        if(!this.alive) return false;
        
        int dX = pBall.getPos().x - this.pos.x;
        int dY = pBall.getPos().y - this.pos.y;
        if(this.hitCounter >= this.MAX_HITS) this.setAlive(false);
        return dX * dX + dY * dY <= (Ball.RADIUS/2 + PegCircle.RADIUS/2) * (Ball.RADIUS/2 + PegCircle.RADIUS/2);
    }
    
    @Override
    public void collisionBehavior(Ball ball) {
        double bounceBonus = 0.97777;
        if(ball.getPos().y >= this.area.y) {
            ball.increaseSpeed(0, -(ball.getYVelocity()*2)*bounceBonus);
        }
        
        if(ball.getPos().y <= this.area.y) {
            ball.increaseSpeed(0, -(ball.getYVelocity()*2)*bounceBonus);
        }
        
        if(ball.getPos().x <= this.area.x) {
            if(ball.getXVelocity() > 0) ball.increaseSpeed(-(ball.getXVelocity()*2)*bounceBonus, 0);
        }
        
        if(ball.getPos().x >= this.area.x)
        {
            if(ball.getXVelocity() < 0) ball.increaseSpeed(-(ball.getXVelocity()*2)*bounceBonus, 0);
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if(!this.alive) return;
            
        g.setColor(Color.blue);
        if(this.isHitten()) g.setColor(Color.cyan);
        if(this.isRedPeg()) {
            if(this.isHitten()) g.setColor(Color.orange);
            else g.setColor(Color.red);
        }
        g.fillOval((this.pos.x - PegCircle.RADIUS/2), (this.pos.y - PegCircle.RADIUS/2), PegCircle.RADIUS, PegCircle.RADIUS);
        g.setColor(Color.GREEN);
        g.drawOval((this.pos.x - PegCircle.RADIUS/2 - 1), (this.pos.y - PegCircle.RADIUS/2 - 1), PegCircle.RADIUS + 1, PegCircle.RADIUS + 1);
    }
}
