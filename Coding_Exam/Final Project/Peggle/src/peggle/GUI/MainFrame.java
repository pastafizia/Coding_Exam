/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.GUI;

import javax.swing.JFrame;
import peggle.logic.GameModeDuel;
import peggle.logic.GameModeSolo;
import peggle.logic.Map;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;


public class MainFrame extends JFrame{
    public static final int SCALE = 2;
    public static final int WIDTH_PANEL = 760 * SCALE;
    public static final int HEIGHT_PANEL = 760 * SCALE;
    
    public static final int WELCOME = 0;
    //public static final int MAP_SELECTION = 1;
    public static final int GAME_SOLO = 1;
    public static final int GAME_DUEL = 2;
    public static final int START_GAME = 3;
    
    private static WelcomePanel welcomePanel;
    private static MapSelectionPanel mapSelectionPanel;
    private static GamePanel gamePanel;
    
    public MainFrame() {
        this.setTitle("peggle");
        this.setSize(760 * SCALE, 760 * SCALE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Image im = Toolkit.getDefaultToolkit().getImage("./pics/final.jpg");
        this.setIconImage(im);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("./pics/pennarello.png").getImage(),
                new Point(0,0),"custom cursor"));
        
        this.mapSelectionPanel = new MapSelectionPanel();
        this.add(mapSelectionPanel);
        
        this.welcomePanel = new WelcomePanel();
        this.add(welcomePanel);
        
        this.gamePanel = new GamePanel();
        this.add(gamePanel);
        
        this.showPanel(WELCOME);
    }
    
    public static void showPanel(int pGameStatus) {
        
        Map map;
        
        switch(pGameStatus) {
            case WELCOME:
                
                mapSelectionPanel.clearMapList();
                gamePanel.reset();
                gamePanel.setVisible(false);
                mapSelectionPanel.setVisible(false);
                welcomePanel.setVisible(true);
                break;
            
            case GAME_SOLO:
                
                mapSelectionPanel.clearMapList();
                gamePanel.setVisible(false);
                welcomePanel.setVisible(false);
                mapSelectionPanel.setVisible(true);
                mapSelectionPanel.start();
                gamePanel.setGameMode(new GameModeSolo());
                break;
                
            case GAME_DUEL:
                
                mapSelectionPanel.clearMapList();
                gamePanel.setVisible(false);
                welcomePanel.setVisible(false);
                mapSelectionPanel.setVisible(true);
                mapSelectionPanel.start();
                gamePanel.setGameMode(new GameModeDuel());
                break;
                
            case START_GAME:
                
                welcomePanel.setVisible(false);
                mapSelectionPanel.setVisible(false);
                
                map = mapSelectionPanel.getChosenMap();
                gamePanel.setMap(map);
                gamePanel.setVisible(true);
                gamePanel.start();
                break;
                
            default:
                break;
        }
    }
}
