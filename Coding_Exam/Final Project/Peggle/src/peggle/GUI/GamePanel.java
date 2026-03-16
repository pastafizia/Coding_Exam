/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.GUI;

import java.awt.Color;
import java.awt.Graphics;
import static java.awt.MouseInfo.getPointerInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import peggle.logic.Ball;
import peggle.logic.GameMode;
import peggle.logic.Map;
import peggle.logic.Peg;
import peggle.logic.Throw;

/**
 *
 * @author kj
 */
public class GamePanel extends JPanel {
    
    private GameMode gameMode;
    private Map map;
    private Ball ball;
    private MyListener myListener;
    private Throw currentThrow;
    Thread gameThread;
    //Thread scoreThread;

    
    
    public GamePanel() {
        this.setSize(MainFrame.WIDTH_PANEL, MainFrame.HEIGHT_PANEL);
        this.setLayout(null);
        this.reset();
    }

    
    public void start() {
        if(map == null || gameMode == null) {
            MainFrame.showPanel(MainFrame.WELCOME);
            return;
        }
        map.initMap();
        gameThread.start();
        //scoreThread.start();
    }
    
    public void setGameMode(GameMode pGameMode) {
        this.gameMode = pGameMode;
    }
    
    public void setMap(Map map) {
        this.map = map;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, MainFrame.HEIGHT_PANEL, MainFrame.WIDTH_PANEL);
        g.setColor(Color.red);
        Point mousePoint = getMouseCoordinates();
        g.drawLine(MainFrame.WIDTH_PANEL/2, 0, mousePoint.x, mousePoint.y);
        //g.drawLine(0, 0, p.x, p.y);
        if(this.map.isReady()) this.map.draw(g);
        this.ball.draw(g);
        //this.currentThrow.draw(g);
        this.gameMode.draw(g);
    }
    
    private void applyGravity() {
        ball.increaseSpeed((float) 0.00, (float) 0.02999);
    }

    public void reset() {
        this.ball = new Ball();
        if(this.gameThread != null) this.gameThread.interrupt();
        //if(this.scoreThread != null) this.scoreThread.interrupt();
        this.gameThread = new Thread(new GameThread());
        //this.scoreThread = new Thread(new ScoreThread());
        this.myListener = new MyListener();
        this.addMouseListener(myListener);
        this.currentThrow = new Throw();
        this.gameMode = null;
    }
    
    private class GameThread implements Runnable {

        @Override
        public void run() {
            gameMode.gameStart();
            while(gameMode.isGameRunning()) {
                ball.resetBallStatus();
                Throw currentThrow = new Throw();
                do {
                    repaint();
                } while(!ball.isClicked());
                while(ball.isAlive() && ball.isClicked()) {
                    applyGravity();
                    repaint();
                    ball.move();
                    for(Peg peg : map.pegList) {
                        if(peg.isOverlapping(ball)) {
                            peg.collisionBehavior(ball);
                            if(!currentThrow.getHittenPegList().contains(peg)) currentThrow.setHittenPeg(peg);
                            peg.setHit(true);
                        }
                    }
                    try {
                        sleep(1000 / 240);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                for(Peg peg : map.pegList) {
                    if(peg.isHitten()) peg.setAlive(false);
                }
                gameMode.getScore(currentThrow, map.getRedPegCount());
            }
            repaint();
            gameMode.endGame();
        }
        
    }
    
//    private class ScoreThread implements Runnable {
//
//        @Override
//        public void run() {
//            repaint();
//            while (gameMode.isGameRunning()) {
//                repaint();
//                System.out.println(currentThrow.getShowTime());
//                while (currentThrow.getShowTime() < 0) {
//                    System.out.println();
//                    repaint();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                    }
//                    currentThrow.decreaseShowTime();
//                }
//            }
//            repaint();
//        }
//    }
    
    private Point getMouseCoordinates() {
        Point p = new Point(getPointerInfo().getLocation().x, getPointerInfo().getLocation().y);
        Point p2 = getLocationOnScreen();
        return new Point(p.x - p2.x, p.y - p2.y);
    }
    
    private Point getTranslation(Point p) {
        return new Point((p.x - MainFrame.WIDTH_PANEL/2), (p.y));
    }
    
    private double getTheta(Point b) {
        b = getTranslation(b);
        return atan2(b.y, b.x);
        
    }
    
    private class MyListener extends MouseAdapter {
        
       @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if(!ball.isClicked()) {

                ball.setClick(true);
                Point p = getMouseCoordinates();
                double theta = getTheta(p);
                ball.increaseSpeed(3.8999*cos(theta), 3.8999*sin(theta));
                System.out.println(p);
                System.out.println(theta);
            }
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

