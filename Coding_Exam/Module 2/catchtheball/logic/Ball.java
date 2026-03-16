package catchtheball.logic;

import catchtheball.GUI.MainFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    public static int STABLE = 0;
    public static int LEFT = -1;
    public static int RIGHT = 1;
    public static int UP = -1;
    public static int DOWN = 1;
    
    private int radius;
    private int speed;
    private Point position;
    private Rectangle area;
    private int directionX = Ball.RIGHT;
    private int directionY = Ball.DOWN;
    private boolean alive = false;
    
    public Ball(Point pPosition, int pRadius, int pSpeed) {
        this.position = pPosition;
        //TODO Check pre-conditions on radius and speed
        this.radius = pRadius;
        this.speed = pSpeed;
        this.area = new Rectangle(pPosition, new Dimension(pRadius, pRadius));
        this.resetDirection();
        this.alive = true;
    }
    

    private void resetDirection() {
        Random randomDirection = new Random();
        this.directionX = randomDirection.nextBoolean() == true ? Ball.RIGHT : Ball.LEFT;
        this.directionY = randomDirection.nextBoolean() == true ? Ball.UP : Ball.DOWN;
    }
    
    public void setRandomDirection() {
        this.resetDirection();
        System.out.println(directionX);
        System.out.println(directionY);
        this.move(directionX, directionY);
    }
    
    private boolean isBallOutOfBounds() {
            if(this.directionX == Ball.RIGHT && this.position.x + this.radius >= MainFrame.WIDTH_PANEL + this.radius)
            {
                return true;
            }

            if(this.directionX == Ball.LEFT && this.position.x <= 0 - this.radius)
            {
                return true;
            }


            if(this.directionY == Ball.DOWN && this.position.y + this.radius >= MainFrame.HEIGHT_PANEL + this.radius)
            {
                return true;
            }

            if(this.directionY == Ball.UP && this.position.y <= 0 - this.radius)
            {
                return true;
            }
        return false;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public void setSpeed(int pSpeed) {
        this.speed = pSpeed;
    }
    
    public void setSize(int pSize) {
        this.radius = pSize;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getSpeed() {
        return this.speed;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public void move(int pVertical, int pHorizontal) {
        this.position.x = this.position.x + pHorizontal;
        this.position.y = this.position.y + pVertical;
        this.area = new Rectangle(this.position, new Dimension(this.radius, this.radius));
    }
    
    public void move() {
        if(!this.isBallOutOfBounds()) {
            this.position.x = this.position.x + this.directionX;
            this.position.y = this.position.y + this.directionY;
            this.area = new Rectangle(this.position, new Dimension(this.radius, this.radius));
        }
        else {
            this.alive = false;
        }
    }
    
    public boolean isInside(Point pPoint) {
        return this.area.contains(pPoint);
    }

    
    
     
}
