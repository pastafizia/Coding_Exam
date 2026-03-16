package catchtheball.GUI;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private StatisticsPanel statisticsPanel;
    private BallPanel ballPanel;
    
    public GamePanel() {
        this.setSize(MainFrame.WIDTH_PANEL, MainFrame.HEIGHT_PANEL);
        this.setLayout(null);
        
        statisticsPanel = new StatisticsPanel();
        statisticsPanel.setLocation(20, 20);
        this.add(statisticsPanel);
        
        ballPanel = new BallPanel(statisticsPanel);
        ballPanel.setLocation(100, 20);
        this.add(ballPanel);
    }
    
    public void resetGame() {
        this.ballPanel.resetGame();
    }
}
