package application.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.Node;

/**
 * Contenu commun et partage des informations hérité des controllers de l'app principale
 */
public class SuperController {
	
    
    /**
     *permet d'avoir le path du fxml courant
     */
    @FXML
    private URL location;
    
    /**
     *repertoire des pages pour une langue donnée
     */
    private String lng = "../fr/";
    
    /**
     *nom du fichier css a utilisé / 2 existants : Dardmode et application
     */
    private String css = getCss();
    //
    
    
    @FXML
    private ResourceBundle resources;
    
    /**
     *permet de gérer l'animation du bouton faisant disparaitre / apparaitre la barre de navigation
     */
    private boolean animation = false;
    
    // bouton d'accès au Popup paramètres
    /**
     *Bouton d'accès à la page paramètre
     */
    @FXML
    private Button b_param;
    
    /**
     *Bouton d'accès à la page mon compte ou Connection
     */
    @FXML
    private Button b_compte;

    /**
     *Bouton Hamburger
     */   
    @FXML
    private Button b_display;
    
    /**
     *Bouton d'acès à la page de gestion utilisateursn
     */
    @FXML
    private Button b_gestion;
    
    /**
     * email de l'utilisateur connecté
     */
    private String email = null;

    /**
     *GridPane principal présent dans chaque scene héritant de SuperController
     */
	@FXML
	public GridPane main;
	
    /**
     *Parti 1 la barre de navigation 
     */
    @FXML
    private Pane panneau1;
    
    /**
     *Parti 2 la barre de navigation 
     */
    @FXML
    private Pane panneau2;
    
    /**
     *Parti 3 la barre de navigation 
     */
    @FXML
    private Pane panneau3;
    
    /**
     *liste des media mit dans le pannier 
     */
    private ObservableList<Media> liste = FXCollections.observableArrayList();
    
    /**
     *liste res réservations effectués en ajoutant au panier mais pas finalisé
     */
    private ArrayList<String> Reservation = new ArrayList<String>();
    
    /**
     *Controller de la page panier 
     */
    private CPanier panier =null;
    
    /**
     *Modèle utilisateur connecté 
     */
    private Utilisateur user = null;
    

    /**
     *Controller de la page connection 
     */
    private  Cconnection c_window = null;
    
    /**
     *Controller de la page panier 
     */
    private CPanier p_window = null;
    
    /**
     *Controller de la page connection
     */
    private Cconnection Cconnect = null;
    
    /**
     *Objet BDD permettant de faire des requêtes à la bdd 
     */
    public Bdd_access BDD;
    
    
    /**
     * @author Bryan C.
     * @version 2.0
     * @see Bdd_access
     */
    public SuperController()
    {
    	BDD = new Bdd_access();
    }
 
    @FXML
    private void initialize()
    {	
    }
    

    
    /**
     * Affiche ou cache la barre de navigation
     * @param event evenement
     * @throws Exception exception
     * @author Bryan C. Erwan D.
     * @version 2.0
     */
    @FXML
    private void display(Event event) throws Exception {
        TranslateTransition openNav=new TranslateTransition(new Duration(350), panneau1);
        openNav.setToX(0);
        TranslateTransition closeNav=new TranslateTransition(new Duration(350), panneau1);
        TranslateTransition openNav2=new TranslateTransition(new Duration(350), panneau2);
        openNav2.setToX(0);
        TranslateTransition closeNav2=new TranslateTransition(new Duration(350), panneau2);
        TranslateTransition openNav3=new TranslateTransition(new Duration(350), panneau3);
        openNav3.setToX(0);
        TranslateTransition closeNav3=new TranslateTransition(new Duration(350), panneau3);
        RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), b_display);
        rotation.setCycleCount(1);
            if(panneau1.getTranslateX()!=0){

                openNav.play();
                openNav2.play();
                openNav3.play();
            	if ( ! animation ) {
            		animation = true;
            		System.out.println(b_display.getRotate());
            		rotation.setByAngle(-b_display.getRotate());
            		rotation.play();
            	}

            }else{

                closeNav.setToX(-(panneau1.getWidth()));
                closeNav2.setToX(-(panneau2.getWidth()));
                closeNav3.setToX(-(panneau3.getWidth()));
                closeNav.play();
                closeNav2.play();
                closeNav3.play();
            	if ( ! animation ) {
            		animation = true;
            		System.out.println(b_display.getRotate());
            		rotation.setByAngle(b_display.getRotate()+90);
            		rotation.play();
            	}
        }
          	animation = false;
    }
    
    
    /**
     * Getter reservation
     * @return Reservation liste des reservation faites par l'utilisateur
     * {@link SuperController#Reservation}
     * @author Bryan C.
     * @version 2.0	
     */
    public ArrayList<String> getReservation ( ) {
    	return Reservation;
    }
    
    /**
     * Getter Css, lecture du fichier texte où est stocké le path du css
     * @return css path du css
     * {@link SuperController#css}
     * @author Bryan C.
     * @version 2.0	
     */
    public String getCss() {
    	String data = "";
        try {
        	FileReader myObj = new FileReader("currentCSS.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    	return data;
    }
    
    /**
     * Setter css : écrit le nouveau path du css dans le fichier texte
     * @param css nom du path css
     * @author Bryan C.
     * @version 2.0	
     */
    public void setCss( String css ) {
        try {
            FileWriter myWriter = new FileWriter("currentCSS.txt");
            myWriter.write(css);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    	this.css = css;
    }
    
    /**
     * Setter reservation
     * @param m liste des ids reservations
     * @author Bryan C.
     * @version 2.0	
     */
    public void setReservation( ArrayList<String> m) {
    	Reservation = m;
    }
    /**
     * Setter PWindow
     * @param c controller de la fenêtre panier
     * @author Bryan C.
     * @version 2.0	
     */
    
    public void setPWindow(CPanier c) {
    	p_window = c;
    }
    /**
     * Getter Pwindow
     * @return {@link SuperController#p_window}
     * @author Bryan C.
     * @version 2.0	
     */
    
    public CPanier getPwindow() {
    	return p_window;
    }
    
    /**
     * Setter Liste
     * @param observableList liste des medias dans le panier
     * @author Bryan C.
     * @version 2.0	
     */
    public void setListe( ObservableList<Media> observableList ) {
    	liste = observableList;
    }
    
    /**
     * Getter Liste
     * @return {@link SuperController#liste}
     * @author Bryan C.
     * @version 2.0	
     */
    public ObservableList<Media> getListe() {
    	return liste;
    }
    
    /**
     * Setter Cwindow
     * @param b controller de la page de connection
     * @author Bryan C.
     * @version 2.0	
     */
    public void setCwindow(Cconnection b) {
    	c_window = b;
    }
    
    
    /**
     * Setter Email
     * @param s l'email
     * @author Bryan C.
     * @version 2.0	
     */
    public void setEmail(String s) {
    	email = s;
    }
    
    /**
     * Getter Email
     * @return {@link SuperController#email}
     * @author Bryan C.
     * @version 2.0	
     */
    public String getEmail() {
    	return email;
    }
    
    /**
     * Getter Cwindow
     * @return {@link SuperController#c_window}
     * @author Bryan C.
     * @version 2.0	
     */
    public Cconnection getCwindow() {
    	return c_window;
    }
    
    /**
     * Setter User
     * @param user Modèle utilisateur
     * @author Bryan C.
     * @version 2.0	
     */
    public void setUser(Utilisateur user) {
    	this.user = user;
    }
    
    /**
     * Setter User
     * @param p controller CPanier
     * @author Bryan C.
     * @version 2.0	
     */
    public void setCPanier(CPanier p) {
    	panier = p;
    }
    
    /**
     * Getter User
     * @return {@link SuperController#user}
     * @author Bryan C.
     * @version 2.0	
     */
    public Utilisateur getUser() {
    	return user;
    }
    
    /**
     * Getter User
     * @return {@link SuperController#user}
     * @author Bryan C.
     * @version 2.0	
     */
    public URL getLocation() {
    	return location;
    }
    
    /**
     * Getter Cconnection
     * @return {@link SuperController#Cconnect}
     * @author Bryan C.
     * @version 2.0	
     */
    public Cconnection getCconnection() {
    	return Cconnect;
    }
    
    /**
     * Setter Cconnection
     * @param c controller de page connection
     * @author Bryan C.
     * @version 2.0	
     */
    public void setCconnection(Cconnection c) {
    	Cconnect = c;
    }
    
    /**
     * Setter Lng
     * @param lng path des pages dans la langue sélectionné
     * @author Bryan C.
     * @version 2.0	
     */
    public void setLng( String lng ) {
    	this.lng = lng;
    }
    
    /**
     * Getter Lng
     * @return {@link SuperController#lng}
     * @author Bryan C.
     * @version 2.0	
     */
    public String getLng() {
    	return lng;
    }
    
    /**
     * goto_compte permet d'aller à la page compte ou la fenêtre de connection
     * @author Bryan C.
     * @param event evenement
     * @throws Exception exception
     * @version 2.0	
     */
    @FXML
    private void goto_compte(Event event) throws Exception {
    	event.consume();
    	if ( email == null && c_window == null ) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(lng+"VBoxConnection.fxml"));
            /* 
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Parent root = fxmlLoader.load();
            Cconnection scene2Controller = fxmlLoader.getController();
            setCconnection(scene2Controller);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            String fileName = lng+location.getFile().substring( location.getFile().lastIndexOf('/')+1, location.getFile().length() );
            scene2Controller.setParent(window,fileName);
            try {
            	Crecherche c = (Crecherche)this;
            	scene2Controller.getRecherche(c.table.getItems());
            }
            catch ( Exception e ) {}
            System.out.println(fileName);
            Scene scene = new Scene(root, 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Connection");
            stage.setScene(scene);
       	  stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
  		    @Override
  		    public void handle(WindowEvent e)  {
  	            FXMLLoader fxmlLoader = new FXMLLoader();
  	            fxmlLoader.setLocation(getClass().getResource(c_window.path));
  	            try {
  	            Parent root = fxmlLoader.load();
                  SuperController scene2Controller = fxmlLoader.getController();
                  scene2Controller.setCwindow(null);
                  scene2Controller.setLng(getLng());
              	try {
            		VBoxMainController c = (VBoxMainController)scene2Controller;
            		c.connected();
            	}
            	catch( Exception err) {}
              	try {
              		Crecherche c = (Crecherche)scene2Controller;
              		c.fromMain(getCconnection().r_list);
              	}
              	catch( Exception err) {}
              	scene2Controller.setCconnection(null);
              	Scene scene =  new Scene(root,1100,700);
              	window.setScene(scene);
              	window.show();
              	stage.close();
  		    }
	            catch (Exception err){
	            	System.out.println(err);
	            }
  		  }
  });
       	setCwindow(scene2Controller);
            stage.show();
    	}
    	else if (c_window == null ){
    		changeScene(lng+"VboxCompte.fxml", event);
    	}
    	}
    	
    
    /**
     * goto_acceuil permet d'aller à la page principale
     * @author Bryan C.
     * @param event evenement
     * @version 2.0
     * @throws Exception exception
     */
    @FXML
    private void goto_acceuil(Event event) throws Exception {
    	event.consume();
    	changeScene(lng+"VboxMain.fxml", event);
    	}
    
    /**
     * goto_par permet d'aller à la page paramètre
     * @author Bryan C.
     * @version 2.0
     * @param event evenement
     * @throws Exception exception
     */
    @FXML
    private void goto_par(Event event) throws Exception {
    	event.consume();
    	changeScene(lng+"VBoxParametre.fxml", event);
    	}
    
    /**
     * goto_gusers permet d'aller à la page gestion utilisateurs
     * @author Bryan C.
     * @version 2.0	
     * @param event evenement
     * @throws Exception exception
     */
    @FXML
    private void goto_gusers(Event event) throws Exception {
    	event.consume();
    	changeScene(lng+"VBoxGestionU.fxml", event);
    	}
    
    /**
     * changeScene changement de scene
     * @author Bryan C.
     * @version 2.0
     * @param event evenement
     * @param path path de la page fxml
     * @throws Exception exception
     */
    public void changeScene(String path,Event event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        Parent root = fxmlLoader.load();
        SuperController scene2Controller = fxmlLoader.getController();
    	scene2Controller.setCss(css);
    	scene2Controller.setEmail(email);
    	scene2Controller.setCconnection(Cconnect);
    	scene2Controller.setUser(user);
    	scene2Controller.setWindow(((Node)event.getTarget()).getScene().getWindow(),scene2Controller);
    	scene2Controller.setReservation(Reservation);
    	scene2Controller.setLng(lng);
    	scene2Controller.afficheAdmin(scene2Controller);
    	try {
    		VBoxMainController c = (VBoxMainController)scene2Controller;
    		c.connected();
    	}
    	catch( Exception e) {}
    	try {
    		Ccompte c =	(Ccompte)scene2Controller;
    		c.info();
    	}
    	catch( Exception e) {}
    	System.out.println(css);
    	Scene scene =  new Scene(root,1100,700);
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	if ( c_window != null ) {
    		c_window.setParent(window, path);
    	}
    	if ( p_window != null ) {
    		p_window.setParent(window, path,getUser());
    	}
    	scene2Controller.setCwindow(c_window);
    	scene2Controller.setPWindow(p_window);
    	window.setScene(scene);
    	window.show();
    }
    
    /**
     * setWindow créer un listener qui écoute si la fenêtre va se fermer pour pouvoir annuler les réservations si elles n'ont pas étaient confirmées dans le panier
     * @author Bryan C.
     * @param w fenêtre de la prochaine page
     * @param scene2Controller controller de la prochaine page
     * @version 2.0	
     */
    public void setWindow( Window w, SuperController scene2Controller ) {
    	try {
   	  	w.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent e)  {
		    	System.out.println(scene2Controller.getListe());
		    	System.out.println(scene2Controller.getReservation());
		    	if ( !getListe().isEmpty() ) {
		    		System.out.println("yeah motherfucker");
		    		BDD.remove(Reservation);
		  }
		    }
});
    	}
    	catch ( Exception err ) {
    		System.out.println(err);
    	}
    }
    
    /**
     * afficheAdmin permet d'afficher le contenu dispo uniquement pour un administrateur
     * @param control controller de la scene chargé
     * @author Bryan C.
     * @version 2.0	
     */
    public void afficheAdmin(SuperController control) {
    	try {
    	if  ( getUser().getAdmin() ) {
    	b_gestion.setVisible(true);
    	}
    }
    	catch(Exception e) {}

    try {
    	if  ( getUser().getAdmin() ) {
    		Crecherche c = (Crecherche)control;
    		c.biblioPane.setVisible(true);
    	}
    }
    catch(Exception e) {}
}
}

