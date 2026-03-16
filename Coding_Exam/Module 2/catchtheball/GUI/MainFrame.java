package catchtheball.GUI;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    public static final int WELCOME = 1;
    public static final int GAME = 2;
    
    public static final int WIDTH_PANEL = 500;
    public static final int HEIGHT_PANEL = 478;
    
    private static WelcomePanel welcomePanel;
    private static GamePanel gamePanel;
    
    public MainFrame() {
        this.setTitle("Catch the ball...");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        welcomePanel = new WelcomePanel();
        this.getContentPane().add(welcomePanel);
        
        gamePanel = new GamePanel();
        this.getContentPane().add(gamePanel);
        
        MainFrame.showPanel(MainFrame.WELCOME);
    }
    
    public static void showPanel(int pGameStatus) {
        switch (pGameStatus) {
            case WELCOME:
                welcomePanel.setVisible(true);
                gamePanel.setVisible(false);
                break;
            case GAME:
                gamePanel.resetGame();
                welcomePanel.setVisible(false);
                gamePanel.setVisible(true);
                break;
        }
    }
}
