/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchtheball.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author kj
 */
public class BonusRed extends Bonus{
    
    private static final int SPEED_INCREMENT = -50;

    public BonusRed(Point pPosition, int pSize) {
        super(pPosition, pSize);
    }

    @Override
    public void draw(Graphics g) {
        if(!visible) return;
        g.setColor(Color.red);
        g.fillRect(pos.x, pos.y, this.size, this.size);
    }

    @Override
    public void alterBall(Ball pBall) {
        pBall.setSpeed(pBall.getSpeed() + BonusRed.SPEED_INCREMENT);
    }
    
}
