package catchtheball.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {
    
    private JButton buttonGame;
    private JButton buttonExit;
    
    public WelcomePanel() {
        this.setSize(MainFrame.WIDTH_PANEL, MainFrame.HEIGHT_PANEL);
        this.setLayout(null);
        
        MyListener listener = new MyListener();
        
        buttonGame = new JButton("Gioca");
        buttonGame.setBounds(200, 250, 100, 30);
        buttonGame.addMouseListener(listener);
        this.add(buttonGame);
        
        buttonExit = new JButton("Esci");
        buttonExit.setBounds(200, 300, 100, 30);
        buttonExit.addMouseListener(listener);
        this.add(buttonExit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, MainFrame.WIDTH_PANEL, MainFrame.HEIGHT_PANEL);
        
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 40));
        g.drawString("CATCH THE BALL", 70, 200);
        
    }
    
    private class MyListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton btn = (JButton) e.getSource();
            
            if (btn.equals(buttonExit))
                System.exit(0);
            
            if (btn.equals(buttonGame))
                MainFrame.showPanel(MainFrame.GAME);
        }
        
    }
}
