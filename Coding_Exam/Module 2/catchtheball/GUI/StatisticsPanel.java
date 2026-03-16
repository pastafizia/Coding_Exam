package catchtheball.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StatisticsPanel extends JPanel {
        
    public static int MAX_TIME = 30;
    
    private int points;
    private int time;
    private boolean gameIsGoing = false;
    
    private Thread timerThread;
    
    public StatisticsPanel() {
        this.setSize(80, 438);
        this.gameIsGoing = true;
        this.points = 0;
        this.time = StatisticsPanel.MAX_TIME;
    }
    
    public void startTime() {
        timerThread = new Thread(new TimerThread());            
        timerThread.start();
    }
    
    public boolean isTimeOut() {
        return (this.time <= 0);
    }
    
    public void raisePoints() {
        this.points++;
        this.repaint();
    }
    
    public void resetStatistics() {
        this.points = 0;
        this.time = StatisticsPanel.MAX_TIME;
        this.gameIsGoing = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 80, 438);
        
        g.setColor(Color.lightGray);
        g.drawRect(0, 0, 80, 437);
        
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        
        g.drawString("Points", 10, 50);
        g.drawString("" + this.points, 10, 80);

        g.drawString("Time", 10, 160);
        g.drawString("" + this.time, 10, 190);
    }
    
    public void stopGame() {
        this.gameIsGoing = false;
    }
    
    private class TimerThread implements Runnable {

        @Override
        public void run() {
            while (time != 0 && gameIsGoing) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StatisticsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                time--;
                repaint();
            }
            JOptionPane.showMessageDialog(null, "Hai totalizzato " + points + " punti!");
            MainFrame.showPanel(MainFrame.WELCOME);
                
        }
        
    }
}
