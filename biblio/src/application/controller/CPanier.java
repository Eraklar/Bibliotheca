package application.controller;



import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.event.Event;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * Controller de la fenêtre panier
 */
public class CPanier{

	/**
	 * fenêtre de l'app principale
	 */
	@FXML
    private Stage  p_window;
    
	/**
	 * path de la scene actuelle dans l'app principale
	 */
	@FXML
    public String path;
	
	/**
	 * liste des medias en cours de réservation
	 */
    public ObservableList<Media> liste = FXCollections.observableArrayList();
    
    /**
     * liste des id reservations
     */
    public ArrayList<String> Reservation = new ArrayList<String>();
	
    /**
     * Utilisateur connecté
     */
	public Utilisateur user;
	
	/**
	 * Tableau des réservations
	 */
	@FXML
    private TableView<Media> table;
    
	/**
	 * Colonne nom
	 */
    @FXML
    private TableColumn<Media,String> n_nom;

	/**
	 * Colonne media
	 */
    @FXML
    private TableColumn<Media,String> n_media;
    
	/**
	 * Colonne auteur
	 */
    @FXML
    private TableColumn<Media,String> n_auteur;
    
	/**
	 * Colonne genre
	 */
    @FXML
    private TableColumn<Media,String> n_genre;
    
	/**
	 * Colonne edition
	 */
    @FXML
    private TableColumn<Media,String> n_edition;

	
    /**
     * Change les données clés
     * @author Bryan C.
     * @param s fenetre de l'app principale
     * @param path path de la scene changée
     * @param user utilisateur
     * @version 2.0
     */
	public void setParent(Stage s, String path, Utilisateur user){ 
		p_window = s;
		this.path = path;
		this.user = user;
	}
	
    /**
     * Initialisation de la fenetre panier : init du tableau et du css
     * @author Bryan C.
     * @version 2.0
     */
	@FXML
	private void initialize(){
		
		table.setPlaceholder(new Label("Nique tes morts sur la Cannebiere"));
    	n_nom.setCellValueFactory(new PropertyValueFactory<>("titre"));
    	n_media.setCellValueFactory(new PropertyValueFactory<>("id_stock"));
    	n_auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
    	n_genre.setCellValueFactory(new PropertyValueFactory<>("id"));
    	n_edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
    	ObservableList<Media> observableList = FXCollections.observableArrayList();
    	table.setItems(observableList);
    }
	
	
	   /**
     * recois les information de la page de recherche
     * @author Bryan C.
     * @param observableList liste des medias en cours de réservation
     * @version 2.0
     */
    public void fromMain(ObservableList<Media> observableList ) {
    	table.setItems(observableList);
    }
    
    /**
     * Finalise la réservation
     * @author Bryan C.
     * @param event evenement
     * @throws Exception exception
     * @version 2.0
     */
    @FXML
    private void finaliser( Event event) throws Exception {
    	event.consume();
        FXMLLoader fxmlLoader = new FXMLLoader();
        String lng = path.substring(0,6);
        fxmlLoader.setLocation(getClass().getResource(path));
        Parent root = fxmlLoader.load();
        SuperController scene2Controller = fxmlLoader.getController();
        scene2Controller.setEmail(user.getMail());
        scene2Controller.setUser(user);
        scene2Controller.setCPanier(null);
        scene2Controller.setLng(lng);
        scene2Controller.afficheAdmin(scene2Controller);
      	try {
    		VBoxMainController c = (VBoxMainController)scene2Controller;
    		c.connected();
    	}
    	catch( Exception err) {}
      	try {
    		Crecherche c = (Crecherche)scene2Controller;
    		c.connected();
    	}
    	catch( Exception err) {}
    	Scene scene =  new Scene(root,1100,700);
    	p_window.setScene(scene);
    	p_window.show();
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.close();
    }
}
