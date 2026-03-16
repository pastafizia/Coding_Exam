/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peggle.logic;

/**
 *
 * @author kj
 */
public class Player {
    
    private String playerName;
    private int score = 0;
    private int throwNumber = 0;
    
    public Player(String pPlayerName, int initialThrowNumber) {
        this.playerName = pPlayerName;
        this.throwNumber = initialThrowNumber;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public String getName() {
        return playerName;
    }
    
    public void increaseScore(int pScore) {
        this.score += pScore;
    }
    
    public int getThrowNumber() {
        return this.throwNumber;
    }
    
    public void decreaseThrowNumber() {
        if(this.throwNumber > 0) this.throwNumber--;
    }
}
