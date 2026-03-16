/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import peggle.GUI.MainFrame;
/**
 *
 * @author kj
 */
public class RandomMapGenerator {
    
    private static final int POGONUMBER = 50;
    
    public static void main(String[] args) {
        
        Map outputMap = new Map();
        Random random = new Random();
        int randomX;
        int randomY;
        int i = 0;
        
        do {
            randomX = random.nextInt(MainFrame.WIDTH_PANEL - 50*MainFrame.SCALE) + 25*MainFrame.SCALE;
            randomY = random.nextInt(MainFrame.HEIGHT_PANEL - 150*MainFrame.SCALE) + 100*MainFrame.SCALE;
            if(random.nextBoolean()) {
                PegCircle circle = new PegCircle(new Point(randomX, randomY));
                if(distanceCheck(circle, outputMap.pegList)) {
                    outputMap.pegList.add(circle);
                    i++;
                }
            }
            else {
                PegRectangle rect = new PegRectangle(new Point(randomX, randomY));
                if(distanceCheck(rect, outputMap.pegList)) {
                    outputMap.pegList.add(rect);
                    i++;
                }
            }
            
        } while (i < RandomMapGenerator.POGONUMBER);
        
        try {
            FileOutputStream fos = new FileOutputStream("./maps/MINI_50.pgl");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(outputMap);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RandomMapGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RandomMapGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private static boolean distanceCheck(Peg pPeg, ArrayList<Peg> pPegList) {
        boolean overlaps = false;
        for(Peg peg : pPegList) {
            
            if(peg.area.intersects(pPeg.area)) return false;
            
        }
        return true;
    }
    
}
