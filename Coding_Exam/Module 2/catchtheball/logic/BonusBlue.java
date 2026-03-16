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
public class BonusBlue extends Bonus{
    

    public BonusBlue(Point pPosition, int pSize) {
        super(pPosition, pSize);
    }

    @Override
    public void draw(Graphics g) {
        if(!visible) return;
        g.setColor(Color.blue);
        g.fillRect(pos.x, pos.y, this.size, this.size);
    }

    @Override
    public void alterBall(Ball pBall) {
        pBall.setSize(pBall.getRadius()*2);
    }
    
}
