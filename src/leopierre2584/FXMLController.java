/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopierre2584;



import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static leopierre2584.Parametres.TAILLE;

/**
 * FXML Controller class
 *
 * @author Lyeeth genre :p
 */
public class FXMLController implements Initializable {

    private boolean turn = true; // true == J1's turn
    
    private int gameType; // 0 = JcJ
    
    private Grille G1, G2 ;
    
    // optionnel, on devrait pouvoir les passer en parametre de methode
    int directionJ1, directionJ2;
    
    @FXML
    private Button Jcj, JcIA, JcIALoose, IAvsIA;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label nbcJ1, nbcJ2, scrJ1, scrJ2, scrMaxJ1, scrMaxJ2;
    
    @FXML
    private GridPane grilleJ1, grilleJ2;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize");
        newGame();
    }

    @FXML
    private void gameJcJ() {
        rootPane.setOnKeyReleased(this::movement);
        newGame();
    }
    
    @FXML
    private void gameJcIA() {      
        rootPane.setOnKeyReleased(this::movementAgainstIARandom);

        newGame();
    }
    
    @FXML
    private void gameJcIALoose() {      
        rootPane.setOnKeyReleased(this::movementAgainstIALooser);

        newGame();
    }
    
        @FXML
    private void gameIAvsIA() {      
        rootPane.setOnKeyReleased(this::movementIAvsIALooser);

        newGame();
    }

    @FXML
    private void movement(KeyEvent e) {
        KeyCode code = e.getCode();
        if(turn){//J1 tour

        // un switch aurait été mieux mais ça ne semble pas marcher avec les keycode
            if(code == KeyCode.Q){
                
                directionJ1 = Parametres.GAUCHE;
                makeMovement(true);
            }else if(code == KeyCode.S){
                
                directionJ1 = Parametres.BAS;
                makeMovement(true);
            }else if(code == KeyCode.Z){
               
                directionJ1 = Parametres.HAUT;
                makeMovement(true);
            }else if(code == KeyCode.D){
                
                
                directionJ1 = Parametres.DROITE;
                makeMovement(true);
            }

        //J2 tour
        }else {
            if(code == KeyCode.K){
                
                directionJ2 = Parametres.GAUCHE;
                makeMovement(false);
            }else if(code == KeyCode.O){
               
                directionJ2 = Parametres.HAUT;
                makeMovement(false);
            }else if(code == KeyCode.L){
                
                directionJ2 = Parametres.BAS;
                makeMovement(false);
            }else if(code == KeyCode.M){
                
                directionJ2 = Parametres.DROITE;
                makeMovement(false);
            }
        }
        
    }
    
    @FXML
    private void movementAgainstIARandom(KeyEvent e) {
        KeyCode code = e.getCode();        
        // un switch aurait été mieux mais ça ne semble pas marcher avec les keycodes
            if(code == KeyCode.Q){
                
                directionJ1 = Parametres.GAUCHE;
                makeMovement(true);
            }else if(code == KeyCode.S){
                
                directionJ1 = Parametres.BAS;
                makeMovement(true);
            }else if(code == KeyCode.Z){
               
                directionJ1 = Parametres.HAUT;
                makeMovement(true);
            }else if(code == KeyCode.D){
                
                
                directionJ1 = Parametres.DROITE;
                makeMovement(true);
            }
        //IA tour
            int movementChoosed = IaRandom.movechoice();
            /*try{ 
            TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException ex) 
            {       
                System.out.println(ex);
            }*/
            
            switch(movementChoosed){
                case 1:
                    directionJ2 = Parametres.GAUCHE;
                    makeMovement(false);
                    break;
                case 2:
                    directionJ2 = Parametres.DROITE;
                    makeMovement(false);
                    break;
                case 3:
                    directionJ2 = Parametres.HAUT;
                    makeMovement(false);
                    break;
                    
                case 4:
                    directionJ2 = Parametres.BAS;
                    makeMovement(false);
                    break;
            }
        
    }
    
    @FXML
    private void movementAgainstIALooser(KeyEvent e) {
        KeyCode code = e.getCode();        
        // un switch aurait été mieux mais ça ne semble pas marcher avec les keycodes
            if(code == KeyCode.Q){
                
                directionJ1 = Parametres.GAUCHE;
                makeMovement(true);
            }else if(code == KeyCode.S){
                
                directionJ1 = Parametres.BAS;
                makeMovement(true);
            }else if(code == KeyCode.Z){
               
                directionJ1 = Parametres.HAUT;
                makeMovement(true);
            }else if(code == KeyCode.D){
                
                
                directionJ1 = Parametres.DROITE;
                makeMovement(true);
            }
        //IA tour
            int movementChoosed = IALooser.movechoice(G2);
            /*try{ 
            TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException ex) 
            {       
                System.out.println(ex);
            }*/
            
            switch(movementChoosed){
                case 1:
                    directionJ2 = Parametres.GAUCHE;
                    makeMovement(false);
                    break;
                case 2:
                    directionJ2 = Parametres.DROITE;
                    makeMovement(false);
                    break;
                case 3:
                    directionJ2 = Parametres.HAUT;
                    makeMovement(false);
                    break;
                    
                case 4:
                    directionJ2 = Parametres.BAS;
                    makeMovement(false);
                    break;
            }
        
    }
    
    
    @FXML
    private void movementIAvsIALooser(KeyEvent e) {
        int movementChoosed = IaRandom.movechoice();
            /*try{ 
            TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException ex) 
            {       
                System.out.println(ex);
            }*/
            
            switch(movementChoosed){
                case 1:
                    directionJ2 = Parametres.GAUCHE;
                    makeMovement(false);
                    break;
                case 2:
                    directionJ2 = Parametres.DROITE;
                    makeMovement(false);
                    break;
                case 3:
                    directionJ2 = Parametres.HAUT;
                    makeMovement(false);
                    break;
                    
                case 4:
                    directionJ2 = Parametres.BAS;
                    makeMovement(false);
                    break;
            }
        //IA tour
            movementChoosed = IALooser.movechoice(G2);
            /*try{ 
            TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException ex) 
            {       
                System.out.println(ex);
            }*/
            
            switch(movementChoosed){
                case 1:
                    directionJ2 = Parametres.GAUCHE;
                    makeMovement(false);
                    break;
                case 2:
                    directionJ2 = Parametres.DROITE;
                    makeMovement(false);
                    break;
                case 3:
                    directionJ2 = Parametres.HAUT;
                    makeMovement(false);
                    break;
                    
                case 4:
                    directionJ2 = Parametres.BAS;
                    makeMovement(false);
                    break;
            }
        
    }
    
    
    // ici on met à jour le modele (i.e les grilles)
    private void makeMovement(boolean J1turn){
            
        if(J1turn){
            boolean bLanceur = G1.lanceurDeplacerCases(directionJ1);
                    
            if (bLanceur) {
                boolean b = G1.nouvelleCase();
                if (!b) 
                    G1.gameOver();
            }
            afficherGrilleJ1();
                
        }else{//J2 tour
            boolean bLanceur = G2.lanceurDeplacerCases(directionJ2);
            if (bLanceur) {
                boolean b = G2.nouvelleCase();
                if (!b) 
                    G2.gameOver();
                }
                afficherGrilleJ2();  
        }
        updateScore();
        turn = !turn;
   
    }
    
    private void afficherGrilleJ1(){
        HashSet<Case> modeleGrille = G1.getGrille();
        clearGridpaneLabelsJ1();
        
        
        for (Case c : modeleGrille) {           
            Node n = this.getNodeFromGridPane(grilleJ1, c.getX(), c.getY());
            if(n == null){
                
                //Label l = new Label( Integer.toString(c.getValeur()) );
                Pane p = new Pane();
                p.setStyle("-fx-background-color: #ff8080");
                Label l = new Label();
                l.setContentDisplay(ContentDisplay.CENTER);
                l.setText(Integer.toString(c.getValeur()));
                p.getChildren().add(l);
                grilleJ1.add(p, c.getX(), c.getY());
                
            }else if (n instanceof Pane){
                
                Pane pane = (Pane)n;
                pane.setStyle("-fx-background-color: #ff8080");
                for(Node tmp : pane.getChildren()) {
                    Label lab = (Label) tmp;
                    lab.setText(Integer.toString(c.getValeur()));
                }
                        
                
            }
                
           
            
        }  
    }
    
        private void afficherGrilleJ2(){
        HashSet<Case> modeleGrille = G2.getGrille();
        clearGridpaneLabelsJ2();
        
        int i =0;
        for (Case c : modeleGrille) {           
            Node n = this.getNodeFromGridPane(grilleJ2, c.getX(), c.getY());
            if(n == null){
                
                //Label l = new Label( Integer.toString(c.getValeur()) );
                Pane p = new Pane();
                p.setStyle("-fx-background-color: #ff8080");
                Label l = new Label();
                l.setContentDisplay(ContentDisplay.CENTER);
                l.setText(Integer.toString(c.getValeur()));
                p.getChildren().add(l);
                grilleJ2.add(p, c.getX(), c.getY());
                
            }else if (n instanceof Pane){
                
                Pane pane = (Pane)n;
                pane.setStyle("-fx-background-color: #ff8080");
                for(Node tmp : pane.getChildren()) {
                    Label lab = (Label) tmp;
                    lab.setText(Integer.toString(c.getValeur()));
                }
                        
                
            }
                
           
            
        }  
    }
    
    // reinitialise tous les lables de la grille
    private void clearGridpaneLabelsJ1(){
        for(Node p : grilleJ1.getChildren()){
            if (p instanceof Pane){
                Pane pane = (Pane)p;
                pane.setStyle("-fx-background-color: transparent");
                for(Node tmp : pane.getChildren()) {
                    Label lab = (Label) tmp;
                    lab.setText("");
                }

            }
        }
    }
    
        // reinitialise tous les lables de la grille
    private void clearGridpaneLabelsJ2(){
        for(Node p : grilleJ2.getChildren()){
           if (p instanceof Pane){
                Pane pane = (Pane)p;
                pane.setStyle("-fx-background-color: transparent");
                for(Node tmp : pane.getChildren()) {
                    Label lab = (Label) tmp;
                    lab.setText("");
                }

            }
        }
    }
    
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
       
    for (Node node : gridPane.getChildren()) {
        
        if ( (GridPane.getColumnIndex(node) == col) && (GridPane.getRowIndex(node) == row)) {
            return node;
        }
    }
    return null;
    }
    
    
private void updateScore(){
        if(turn){
            int nbc = Integer.parseInt(nbcJ1.getText())+1;
            nbcJ1.setText(Integer.toString(nbc));
            
            scrJ1.setText(Integer.toString(G1.getScore() ));
            scrMaxJ1.setText(Integer.toString(G1.getValeurMax()));
            
        }else{
            int nbc = Integer.parseInt(nbcJ2.getText())+1;
            nbcJ2.setText(Integer.toString(nbc));
            
            scrJ2.setText(Integer.toString(G2.getScore() ));
            scrMaxJ2.setText(Integer.toString(G2.getValeurMax()));
        }
    }



private void newGame(){
        System.out.println("New Game");
        
        turn = true;
        
        nbcJ1.setText("0");
        nbcJ2.setText("0");
        scrJ1.setText("0");
        scrJ2.setText("0");
        scrMaxJ1.setText("0");
        scrMaxJ2.setText("0");
                
        
        
        G1 = new Grille();
        boolean b = G1.nouvelleCase();
        b = G1.nouvelleCase();
        
        System.out.println(G1);
        afficherGrilleJ1();
        
        G2 = new Grille();
        b = G2.nouvelleCase();
        b = G2.nouvelleCase();
        
        System.out.println(G2);
        afficherGrilleJ2();
        }

}