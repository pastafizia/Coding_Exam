/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchtheball.logic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import static java.lang.Thread.sleep;

/**
 *
 * @author kj
 */
public abstract class Bonus {
    
    protected boolean visible = false;
    protected int visibleTime = 2;
    protected int size = 0;
    protected Point pos;
    protected Thread bT;
    
    public Bonus(Point pPosition, int pSize) {
        this.pos = pPosition;
        this.size = pSize;
    }
    
    
    public abstract void draw(Graphics g);
    
    public abstract void alterBall(Ball pBall);
    
    public void setVisible(boolean v) {
        this.visible = v;
        if(this.visible) {
            if(bT != null) {
                if(bT.isAlive()) return;
                bT.start();
            }
            else {
                bT = new Thread(new BonusThread());
            }
        } else {
            if(bT != null) bT.interrupt();
        }
    }
    
    public boolean isInside(Point pPoint) {
        if(this.visible){
            Rectangle area = new Rectangle(pos, new Dimension(this.size, this.size));
            return area.contains(pPoint);
        }
        return false;
    }
    
    public boolean isRunning() {
        return bT.isAlive();
    }
    
    protected class BonusThread implements Runnable{

        @Override
        public void run() {
            int i = visibleTime;
            while(i > 0) {
                try {
                    sleep(1000);
                    i--;
                } catch (InterruptedException ex) {
                    
                }
            }
            setVisible(false);
        }
        
    }
}
