package application.controller;
import java.util.Map;

import javafx.collections.FXCollections;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.collections.ObservableList;

/**
 * Controller de la page recherche
 */
public class Crecherche extends SuperController
{
	
    /**
     * entrée Utilisateur
     */
    @FXML
    private TextField inputText;
 
    /**
     * statut de connection
     */
    @FXML
    private Label connected;
    
    /**
     * Bouton pour remove un media
     */
    @FXML Button button_delete;
    
    /**
     * Bouton pour réserver un media
     */
    @FXML Button Reserver;
    
    /**
     * Bouton pour ajouter un exemplaire d'un media
     */
    @FXML Button Ajouter_exempl;
    
    /**
     * Bouton pour supprimer un exemplaire d'un media
     */
    @FXML Button Supp_exempl;
    
    /**
     * Zone admin
     */
    @FXML
    public Pane biblioPane;
    
    /**
     * tableau où les résultats de recherche sont affiché
     */
    @FXML
    public TableView<Media> table;
    
    /**
     * Colonne nom
     */
    @FXML
    private TableColumn<Media,String> n_nom;

    /**
     * Colonne type
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
     * Colonne disponibilité
     */
    @FXML
    private TableColumn<Media,String> n_dispo;
    
    /**
     * Colonne maison edition
     */
    @FXML
    private TableColumn<Media,String> n_edition;
    
    /**
     * label nombre dans le panier
     */
    @FXML
    private Label number;
    
    /**
     * media sélectionné
     */
    private Media CurrentMedia = null;
    
  
    // Add a public no-args constructor
    public Crecherche()
    {
    }

    
    /**
     * Initialisation de la page recherche : init du tableau et du css
     * @author Bryan C.
     * @version 2.0
     */
    @FXML
    private void initialize()
    {
    	n_nom.setCellValueFactory(new PropertyValueFactory<>("titre"));
    	n_media.setCellValueFactory(new PropertyValueFactory<>("type"));
    	n_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    	n_auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
    	n_edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
    	n_dispo.setCellValueFactory(new PropertyValueFactory<>("id_stock"));
    	ObservableList<Media> observableList = FXCollections.observableArrayList();
    	table.setItems(observableList);
    	table.setOnMouseClicked((MouseEvent event) -> {
    	    if (event.getClickCount() == 1) {
                int index = table.getSelectionModel().getSelectedIndex();
                Media media = table.getItems().get(index);
                CurrentMedia = media;
    	    }
    	});
    	main.getStylesheets().add(getClass().getResource(getLng()+getCss()).toExternalForm());
    	
    }
    
    /**
     * fonction qui appelle la fonction de recherche quand on appuie sur la touche Entrée 
     * @author Vincent A.
     * @version 2.0
     * @param event evenement
     */
    @FXML
    void recherche2(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            this.recherche(event);
        }
        
    }
    
    /**
     * fonction qui recherche des médias et nous envoie sur la page de recherche
     * @author Bryan C.
     * @version 2.0
     * @param event evenement
     */
    @FXML
    private void recherche(Event event)
    {
    	event.consume();
    	ObservableList<Media> observableList = FXCollections.observableArrayList();
    	table.setItems(observableList);
        Map<String, Object[]> results = BDD.rechercheMedia(inputText.getText());
        for ( String key : results.keySet()) {
        	Object[] s = results.get(key);
        	Media m = new Media( (String)s[0],(String)s[4],key,(String)s[1], (String)s[2],(int)s[3],(String)s[5]);
        	table.getItems().add(m);
        }
        if ( getCconnection() != null ) {
        	getCconnection().getRecherche(observableList);
        }
    }
    
    
    
    /**
     * fonction qui réserve un media et l'ajoute au panier utilisateur
     * @author Bryan C.
     * @version 2.0
     * @param event evenement
     */
    @FXML
    private void ajouter_panier(Event event) {
    	event.consume();
    	if (CurrentMedia != null && getUser() != null && !getListe().contains(CurrentMedia)) {
    	getListe().add(CurrentMedia);
    	System.out.println(getListe());
    	Media c = CurrentMedia;
    	Object[] o = { getUser().getMail(),c.getId(),c.getType() };
    	String res = BDD.reserver(o);
    	System.out.println(res);
    	if ( res.equals("22011")) {
    		// afficher qu'il n y a plus de ce media dispo
    	}
    	else if ( res.equals("22012")) {
    		// l'utilisateur ne peut plus réserver ce type de média
    	}
    	else {
    	getReservation().add(res);
    	if ( getPwindow() != null ) {
    		getPwindow().liste = getListe();
    		getPwindow().Reservation = getReservation();
    	}
    	number.setText(Integer.toString(getReservation().size()));
    	recherche(event);
    	}
    	}
    }
    
    /**
     * fonction qui ouvre la fenêtre panier
     * @author Bryan C.
     * @version 2.0
     * @param event evenement
     * @throws Exception exception
     */
    @FXML
    private void open_panier(Event event) throws Exception {
    	event.consume();
    	if(getPwindow() == null ) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(getLng()+"VboxPanier.fxml"));
            Parent root = fxmlLoader.load();
            CPanier controller_scene = fxmlLoader.getController();
            controller_scene.fromMain(getListe());
            setCPanier(controller_scene);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            controller_scene.setParent(window,getLng()+"VBoxRecherche2.fxml",getUser());
            Scene scene = new Scene(root, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
      		    @Override
      		    public void handle(WindowEvent e)  {
      	            FXMLLoader fxmlLoader = new FXMLLoader();
      	            fxmlLoader.setLocation(getClass().getResource(getLng()+getPwindow().path));
      	            try {
      	            Parent root = fxmlLoader.load();
                    SuperController controller_scene = fxmlLoader.getController();
                    controller_scene.setReservation(getReservation());
                    controller_scene.setListe(getListe());
                    controller_scene.setPWindow(null);
                    controller_scene.setUser(getUser());
                    controller_scene.setEmail(getEmail());
                    controller_scene.setLng(getLng());
                    try {
                    	Crecherche c = (Crecherche)controller_scene;
                    	c.fromMain(null);
                    }
                    catch (Exception err){                   	
                    }
                    controller_scene.setWindow((Stage) ((Node)event.getSource()).getScene().getWindow(), controller_scene);
                    controller_scene.afficheAdmin(controller_scene);
                  	try {
                		VBoxMainController c = (VBoxMainController)controller_scene;
                		c.connected();
                	}
                	catch( Exception err) {}
                  	try {
                		Crecherche c = (Crecherche)controller_scene;
                		c.connected();
                	}
                	catch( Exception err) {}
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
                setPWindow(controller_scene);
                stage.show();
        	}
        	else if (getPwindow() == null){
        		changeScene(getLng()+"VboxRecherche2.fxml", event);
        	}
        	}
    
    /**
     * Remove de la media
     * @author Vincent A.
     * @version 2.0
     * @param event evenement
     */
    @FXML
    private void remove_media(Event event) {
        BDD.DeleteMedia(CurrentMedia.getId(), CurrentMedia.getEdition());
        recherche(event);
        event.consume();
    }
    
    /**
     * Recois les infos de la recherche de la page précédente
     * @author Bryan C.
     * @version 2.0
     * @param observableList liste des medias
     */
    public void fromMain(ObservableList<Media> observableList ) {
    	table.setItems(observableList);
    	number.setText(Integer.toString(getReservation().size()));
    }
    
    /**
     * Recois les infos de la recherche et les mets dans l'entrée
     * @author Bryan C.
     * @version 2.0
     * @param s contenu de la recherche
     */
    public void setText(String s) {
    	inputText.setText(s);
    }

    /**
     * actualise le statut de connection
     * @author Bryan C.
     * @version 2.0
     */
    public void connected() {
    	if ( getUser() != null ) {
    	connected.setText(connected.getText()+" "+getUser().getPrenom());
    	}
    	else {
    	connected.setText(connected.getText()+" Annonyme");
    	}
    }
    
    /**
     * Ajoute un exemplaire sur le media sélectionné
     * @author Vincent A.
     * @version 2.0
     * @param e evenement
     */
    @FXML
    private void ajout_exemplaire(Event e) {
    	
    	e.consume();
    	if (CurrentMedia!=null) {
    	String type=CurrentMedia.getType();
    	int id_stock=CurrentMedia.getId_stock();
    	
    	BDD.Add_exemplaire(type,id_stock,CurrentMedia.getId());
    	recherche(e);
    	}
    	//faut mettre à jour la page ensuite
    }
    
    /**
     * Suppression  d'un exemplaire sur le media sélectionné
     * @author Vincent A.
     * @version 2.0
     * @param e evenement
     */
    @FXML
    private void supp_exemplaire(Event e) {
    	e.consume();
    	if (CurrentMedia!=null) {
    		if (CurrentMedia.getId_stock()>0) {
    		String type=CurrentMedia.getType();
        	int id_stock=CurrentMedia.getId_stock();
        	BDD.supp_Exemplaire(type,id_stock,CurrentMedia.getId());
        	recherche(e);
    		}
    	}
    }
}

  