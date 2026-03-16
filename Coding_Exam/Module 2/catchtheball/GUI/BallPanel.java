package catchtheball.GUI;

import catchtheball.logic.Bonus;
import catchtheball.logic.BonusBlue;
import catchtheball.logic.BonusRed;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class BallPanel extends JPanel {

    private static final int START_SPEED = 50;
    private static final int SPEED_INCREMENT = 15;
    private static final int BALL_DIAMETER = 20;
    private static final int BONUS_SIDE = 15;
    
    private DrawableBall ball;
    private Bonus bonus;
    private StatisticsPanel statisticsPanel;
    private boolean gameStarted;
    

    public BallPanel(StatisticsPanel pStatisticsPanel) {
        this.setSize(380, 438);
        Random randomStart = new Random();
        this.ball = new DrawableBall(new Point(randomStart.nextInt(this.getBounds().width - BallPanel.BALL_DIAMETER), randomStart.nextInt(this.getBounds().height - BallPanel.BALL_DIAMETER)), BallPanel.BALL_DIAMETER, BallPanel.START_SPEED);
        
        this.statisticsPanel = pStatisticsPanel;
        this.gameStarted = false;

        MyListener myListener = new MyListener();
        this.addMouseListener(myListener);
    }
    
    public void resetGame() {
        Random randomStart = new Random();
        this.ball = new DrawableBall(new Point(randomStart.nextInt(this.getBounds().width - BallPanel.BALL_DIAMETER), randomStart.nextInt(this.getBounds().height - BallPanel.BALL_DIAMETER)), BallPanel.BALL_DIAMETER, BallPanel.START_SPEED);
        this.statisticsPanel.resetStatistics();
        this.gameStarted = false;
        this.bonus = null;
    }

    private void newBonus(boolean redOrBlue, Point bonusPoint) {
        if(redOrBlue) {
            bonus = new BonusRed(bonusPoint, BallPanel.BONUS_SIDE);
        }
        else {
            bonus = new BonusBlue(bonusPoint, BallPanel.BONUS_SIDE);
        }
        bonus.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 380, 438);

        g.setColor(Color.lightGray);
        g.drawRect(0, 0, 379, 437);

        this.ball.draw(g);
        if(bonus != null) this.bonus.draw(g);
    }

    private class MyListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!gameStarted) {
                statisticsPanel.startTime();
                gameStarted = true;
                Thread threadBall = new Thread(new BallThread());
                threadBall.start();
                if(bonus != null) bonus.setVisible(false);
            } else {
                if (ball.isInside(e.getPoint())) {
                    statisticsPanel.raisePoints();
                    ball.setRandomDirection();
                    ball.setSpeed(ball.getSpeed() + BallPanel.SPEED_INCREMENT);
                    
                }
                if (bonus != null) {
                    if(bonus.isInside(e.getPoint())) {
                        bonus.setVisible(false);
                        bonus.alterBall(ball);
                    }
                }
            }
        }
    }
    
    private class BallThread implements Runnable {

        public void run() {
            while (gameStarted) {
                if(!statisticsPanel.isTimeOut()) {
                    if(!ball.isAlive()) statisticsPanel.stopGame();
                    ball.move();
                    repaint();
                    try {
                        double frac = (1.0 / ball.getSpeed()) * 1000;
                        Thread.sleep((int) frac);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StatisticsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Random randomBonus = new Random();
                    if (randomBonus.nextInt(5000) < 15) {
                        Point bp = new Point((randomBonus.nextInt(getBounds().width - BallPanel.BONUS_SIDE)), (randomBonus.nextInt(getBounds().height - BallPanel.BONUS_SIDE)));
                        if(bonus == null) {
                            newBonus(randomBonus.nextBoolean(), bp);
                            bonus.setVisible(true);
                        }
                        else {
                            if (!bonus.isRunning()) newBonus(randomBonus.nextBoolean(), bp);
                        }
                    }
                }
            }
        }
    }
}
