/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopierre2584;

/**
 *
 * @author daudi
 */
public abstract class Joueur {
    
    
    private int score = 0;
    private int direction;
    private Grille grille; 

    public Joueur() {
        
    }
    
    public abstract boolean move();
    
    public abstract boolean setMove();

    
    /*
    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        return result;
    }
    */
}
