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
public class PegRectangle extends Peg {
    
    public PegRectangle(Point pPos) {
        HEIGHT = 30 * MainFrame.SCALE;
        WIDTH = 60 * MainFrame.SCALE;
        this.setPos(pPos);
    }
    
    
    @Override
    public boolean isOverlapping(Ball pBall) {
        if(!this.alive) return false;
        if(!this.area.intersects(pBall.getArea())) return false;
        
        if(this.hitCounter >= this.MAX_HITS) this.setAlive(false);
        if(this.area.contains(pBall.getPos())) return true;
        return false;
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
        g.fillRect((this.pos.x - this.WIDTH/2), (this.pos.y - this.HEIGHT/2), this.WIDTH, this.HEIGHT);
        g.setColor(Color.GREEN);
        g.drawRect((this.pos.x - this.WIDTH/2 - 1), (this.pos.y - this.HEIGHT/2 - 1), this.WIDTH + 1, this.HEIGHT + 1);
    }
    
    @Override
    public void collisionBehavior(Ball ball) {
        double bounceBonus = 0.93777;
        
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

}
