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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.animation.FadeTransition;


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
import javafx.util.Duration;
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
    private Button Jcj, JcIA, JcIALoose, IAvsIA, exit;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label nbcJ1, nbcJ2, scrJ1, scrJ2, scrMaxJ1, scrMaxJ2, toPrint;
    
    @FXML
    private GridPane grilleJ1, grilleJ2;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        newGame();
    }

    /**
     * methode appellé quand le bouton JcJ est cliqué
     * reinitialise la grille et l'affichage et change le listener de l'anchorpane
     */
    @FXML
    private void gameJcJ() {
        
        rootPane.setOnKeyReleased(this::movement);
        newGame();
    }
    
     /**
     * methode appellé quand le bouton JcIA aléatoire est cliqué
     * reinitialise la grille et l'affichage et change le listener de l'anchorpane
     */
    @FXML
    private void gameJcIA() {      
        rootPane.setOnKeyReleased(this::movementAgainstIARandom);

        newGame();
    }
    
    /**
     * methode appellé quand le bouton JcIA perdante est cliqué
     * reinitialise la grille et l'affichage et change le listener de l'anchorpane
     */
    @FXML
    private void gameJcIALoose() {    
        
        rootPane.setOnKeyReleased(this::movementAgainstIALooser);

        newGame();
    }
    
    /**
     * methode appellé quand le bouton IAvsIA  est cliqué
     * reinitialise la grille et l'affichage et change le listener de l'anchorpane
     */
    @FXML
    private void gameIAvsIA() {
        
        rootPane.setOnKeyReleased(this::movementIAvsIALooser);

        newGame();
        toPrint("appuyer sur une touche", 100);
    }

       /**
        * methode qui gere les mouvements lors d'un JcJ
        * analyse la touche préssée, applique une direction en fonction de la touche 
        * et appelle makeMovement pour "effectuer" le mouvement
        * @param e l'event représantant la touche pressée
        */
    @FXML
    private void movement(KeyEvent e) {
        
        KeyCode code = e.getCode();
        //if(turn){//J1 tour
        // un switch aurait été mieux mais ça ne semble pas marcher avec les keycode
            if(code == KeyCode.Q){             
                directionJ1 = Parametres.GAUCHE;
                makeMovement(true);
            } 
            if(code == KeyCode.S){
                
                directionJ1 = Parametres.BAS;
                makeMovement(true);
            }
            if(code == KeyCode.Z){
               
                directionJ1 = Parametres.HAUT;
                makeMovement(true);
            }else if(code == KeyCode.D){
                
                
                directionJ1 = Parametres.DROITE;
                makeMovement(true);
            }
        //J2 tour
            if(code == KeyCode.K){                
                directionJ2 = Parametres.GAUCHE;
                makeMovement(false);
            } 
            if(code == KeyCode.O){
               
                directionJ2 = Parametres.HAUT;
                makeMovement(false);
            } 
            if(code == KeyCode.L){
                
                directionJ2 = Parametres.BAS;
                makeMovement(false);
            } 
            if(code == KeyCode.M){
                
                directionJ2 = Parametres.DROITE;
                makeMovement(false);
            }
        
        
    }
    
    /**
     * methode qui gere les mouvements lors d'un JcIA aléatoire
     * analyse la touche préssée, applique une direction en fonction de la touche 
     * et appelle makeMovement pour "effectuer" le mouvement
     * Appel la fonction static de IaRandom pour générer une direction aléatoire
     * @param e l'event représantant la touche pressée
     */
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
    
      /**
     * methode qui gere les mouvements lors d'un JcIA perdante
     * analyse la touche préssée, applique une direction en fonction de la touche 
     * et appelle makeMovement pour "effectuer" le mouvement
     * Appel la fonction static de IALooser pour générer une direction 
     * @param e l'event représantant la touche pressée
     */
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
    
    /**
     * methode qui gere les mouvements des deux IA lors d'un match entre les IAs
     * appel les deux fonctions des IA rerspectives afin de générer deux mouvements
     * et appelle makeMovement pour "effectuer" le mouvement
     * chaque fois qu'une touche du clavier est préssé une IA effectue un mouvement
     * @param e l'event représantant la touche pressée
     */
    @FXML
    private void movementIAvsIALooser(KeyEvent e) {
        toPrint("appuyer sur une touche", 10000000);
        int movementChoosed = IaRandom.movechoice();
            
            
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
    
    
    /**
     * méthode effectuant et affichant le mouvement sauvegardé dans directionJ1 et directionJ2
     * la grille est mise à jour avant de mettre à jour l'affichage
     * afficherGrilleJ1/J2 met à jour l'affichage
     * @param J1turn un boolean permettant de savoir si le mouvement à effectué par le J1 ou le J2
     */
    private void makeMovement(boolean J1turn){
       
        if(J1turn){
            boolean bLanceur = G1.lanceurDeplacerCases(directionJ1);
                    
            if (bLanceur) {
                boolean b = G1.nouvelleCase();
                if (!b) {
                    toPrint("J1 perd", 1000);
                    finDePartie();
                     
                }
                if(G1.getValeurMax() >= Parametres.OBJECTIF){
                    toPrint("J1 gagne", 1000);
                    finDePartie();
                }      
            }
            updateScore(true);
            afficherGrilleJ1();
                
        }else{//J2 tour
            boolean bLanceur = G2.lanceurDeplacerCases(directionJ2);
            if (bLanceur) {
                boolean b = G2.nouvelleCase();
                if (!b){
                    toPrint("J2 perd", 1000);
                    finDePartie();
                     
                }
                if(G2.getValeurMax() >= Parametres.OBJECTIF){
                    toPrint("J2 gagne", 1000);
                    finDePartie();
                }    
                }
            updateScore(false);
            afficherGrilleJ2();  
        }
        
        turn = !turn;
   
    }
    
    private void finDePartie(){
        rootPane.setOnKeyReleased(this::gameEnded);
        
        
    }
    
    @FXML
    private void gameEnded(KeyEvent e){
        toPrint("partie terminé", 1000);
    }
    
    /**
     * reset la gridpane puis parcours le model de la grille
     * créer un label et un pane ou met à jour le label 
     * en fonction du contenu du model grille
     * fonctionne pour la grille J1.
     */
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
    
       /**
     * reset la gridpane puis parcours le model de la grille
     * créer un label et un pane ou met à jour le label 
     * en fonction du contenu du model grille
     * fonctionne pour la grille J2
     * aurait pu etre fusionner avec afficherGrilleJ1 si plus de temps ...
     */
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
    
        /**
         * reinitialise les labels de la grille à ""
         * et rend invisible les pane de background
         * fonctionne pour J1
         */
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
    
          /**
         * reinitialise les labels de la grille à ""
         * et rend invisible les pane de background
         * fonctionne pour J2
         * Aurait pu etre fusionné avec clearGridpaneLabelsJ1 si plus de temps...
         */
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
    
    /**
     * renvoie un node d'une gridPane
     * @param gridPane la gridPane sur laquelle la recherche est faite
     * @param col la colonne de l'élément recherché
     * @param row la ligne de l'élément recherché
     * @return l'élément recherché en Node ou null si rien n'est trouvé
     */
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
       
    for (Node node : gridPane.getChildren()) {
        
        if ( (GridPane.getColumnIndex(node) == col) && (GridPane.getRowIndex(node) == row)) {
            return node;
        }
    }
    return null;
    }
    
    /**
     * met à jour les différents score
     */
private void updateScore(boolean J1turn){
        if(J1turn){
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


/**
 * inialise la grille et l'affichage commun d'une nouvelle partie, quelle que soit le mode de jeu
 */
private void newGame(){
        toPrint("New game", 3000);

        
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
        

        afficherGrilleJ1();
        
        G2 = new Grille();
        b = G2.nouvelleCase();
        b = G2.nouvelleCase();
        

        afficherGrilleJ2();
        }

/**
 * quitte l'application
 */
@FXML
private void exit(){
    System.exit(0);
}

/**
 * dans un label situé en haut à droite afficher en fade in fade out du texte
 * @param text le texte à afficher
 * @param time le temps avant de lancer le fade out du texte
 */
private void toPrint(String text, int time){
    toPrint.setText(text);
    FadeTransition ft = new FadeTransition(Duration.millis(3000), toPrint);
    ft.setFromValue(0);
    ft.setToValue(1);
    ft.play();

    Timer timer = new Timer();

        TimerTask task = new TimerTask(){
		public void run(){

			 FadeTransition ft = new FadeTransition(Duration.millis(3000), toPrint);
                          ft.setFromValue(1);
                          ft.setToValue(0);
                          ft.play();
		}
	};
		
        timer.schedule(task, time);
    
}

}