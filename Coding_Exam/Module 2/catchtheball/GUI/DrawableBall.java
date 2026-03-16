package catchtheball.GUI;

import catchtheball.logic.Ball;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DrawableBall extends Ball {
    private Color color;
    
    public DrawableBall(Point pPosition, int pRadius, int pSpeed) {
        super(pPosition, pRadius, pSpeed);
        this.color = Color.black;
    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPosition().x, this.getPosition().y, this.getRadius(), this.getRadius());
    }
}
