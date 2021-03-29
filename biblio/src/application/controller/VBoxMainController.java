package application.controller;


import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Map;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.Event;


/**
 * Controller de la page principale
 */
public class VBoxMainController extends SuperController
{
	
    /**
     * entrée Utilisateur
     */
    @FXML
    private TextField inputText;

    /**
     * label connecté en tant que :
     */
    @FXML
    private Label connected;
    
    /**
     * Bouton de recherche
     */
    @FXML
    private Button button;
    
    /**
     * La Chouette :D
     */
    @FXML
    private ImageView loginAnimationImageView;
 
    // Add a public no-args constructor
    public VBoxMainController()
    {
    }
 
    
    /**
     * Initialisation de la page pour set le style et la chouette :D
     * @author Bryan C.
     * @version 2.0
     * @throws Exception exception
     */
    @FXML
    private void initialize() throws Exception
    {
    	try {
    	main.getStylesheets().add(getClass().getResource(getLng()+getCss()).toExternalForm());}
    	finally{
    		
    	}

    	new Thread(()  -> {
    		try {
    	    animation();
    		}
    		catch( Exception e) {
    			System.out.println(e);
    		}
    	}).start();
    	
    	connected.setText("connecté en tant que : Anonyme");
        }
    

    /**
     * Animation de la chouette 
     * @author Bryan C.
     * @version 2.0
     * @throws Exception exception
     */
    @FXML
    private void animation() throws Exception {
    	int i = 0;
    	loginAnimationImageView.setRotate(loginAnimationImageView.getRotate() + 20);
    	while ( true ) {
    		if ( i%2 != 0 ) {
    	loginAnimationImageView.setRotate(loginAnimationImageView.getRotate() + 40);
    		}
    		else {
    	    	loginAnimationImageView.setRotate(loginAnimationImageView.getRotate() - 40);
    		}
    	Thread.sleep(500);
    	i++;
    	}
    }
    
    /**
     * fonction qui appelle la fonction de recherche quand on appuie sur la touche Entrée 
     * @author Vincent A.
     * @version 2.0
     * @param event evenement
     * @throws Exception exception
     */
    @FXML
    void recherche2(KeyEvent event) throws Exception {
        if(event.getCode() == KeyCode.ENTER) {
            this.recherche(event);
        }
    }
    
    /**
     * fonction qui recherche des médias et nous envoie sur la page de recherche
     * @author Bryan C.
     * @version 2.0
     * @param event evenement
     * @throws Exception exception
     */
    @FXML
    private void recherche(Event event) throws Exception
    {
    	event.consume();
    	ObservableList<Media> observableList = FXCollections.observableArrayList();

        Map<String, Object[]> results = BDD.rechercheMedia(inputText.getText());
        for ( String key : results.keySet()) {
        	Object[] s = results.get(key);
        	Media m = new Media( (String)s[0],(String)s[4],key,(String)s[1], (String)s[2],(int)s[3],(String)s[5]);
        	observableList.add(m);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(getLng()+"VBoxRecherche2.fxml"));
        Parent root = fxmlLoader.load();
        Crecherche scene2Controller = fxmlLoader.getController();
        scene2Controller.setCss(getCss());
        scene2Controller.setReservation(getReservation());
        scene2Controller.fromMain(observableList);
        scene2Controller.setEmail(getEmail());
        scene2Controller.setCwindow(getCwindow());
        scene2Controller.setCconnection(getCconnection());
        scene2Controller.setUser(getUser());
        scene2Controller.afficheAdmin(scene2Controller);     
        scene2Controller.setText(inputText.getText());
        scene2Controller.setWindow(((Node)event.getTarget()).getScene().getWindow(),scene2Controller);
        scene2Controller.setLng(getLng());
        scene2Controller.connected();
    	Scene scene =  new Scene(root,1100,700);
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	if ( getCwindow()  != null ) {
    		getCwindow().setParent(window,getLng()+"VBoxRecherche2.fxml");
    		getCwindow().getRecherche(observableList);
    	}
    	if ( getPwindow() != null ) {
    		getPwindow().setParent(window, getLng()+"VBoxRecherche2.fxml",getUser());
    	}
    	window.setScene(scene);
    	window.show();
    }
    
    
    /**
     * fonction qui affiche notre statut de connection
     * @author Bryan C.
     * @version 2.0
     */
    public void connected() {
    	if ( getUser() != null ) {
    	connected.setText("connecté en tant que : "+getUser().getPrenom());
    	}
    }
}
    
