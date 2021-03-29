	package application.controller;
	import com.jfoenix.controls.JFXTextField;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;

import java.util.regex.Pattern; 
	import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Controller de la page gestion utilisateur
 */
	public class Cgestion_users extends SuperController{

		
		 /**
	     * entrée pour recherche utilisateur
	     */
	    @FXML
	    private TextField inputText_gest;

	    /**
	     * tableau qui affiche les resultats utilisateur
	     */
	    @FXML
	    private TableView<Utilisateur> table;
	    
	    /**
	     * Colonne prenom
	     */
	    @FXML
	    private TableColumn<Utilisateur, String> prenom_gest_C;

	    /**
	     * Colonne nom
	     */
	    @FXML
	    private TableColumn<Utilisateur, String> nom_gest_C;

	    /**
	     * Colonne date naissance
	     */
	    @FXML
	    private TableColumn<Utilisateur, Date> age_gest_C;

	    /**
	     * Colonne tel
	     */
	    @FXML
	    private TableColumn<Utilisateur, String> num_gest_C;

	    /**
	     * Colonne email
	     */
	    @FXML
	    private TableColumn<Utilisateur, String> email_gest_C;

	    /**
	     * Colonne adresse
	     */
	    @FXML
	    private TableColumn<Utilisateur, String> adresse_gest_C;
	    
	    //partie rendu
	    
	    /**
	     * Emprunt courrant
	     */
	    private Emprunt CurrentEmprunt=null;
	    
	    /**
	     * tableau des emprunts
	     */
	    @FXML
	    private TableView<Emprunt> table_r;
	    
	    /**
	     * bouton pour rendre un emprunt
	     */
	    @FXML
	    private Button bouton_rendu_r;
	    
	    /**
	     * Colonne mail
	     */
	    @FXML
	    private TableColumn<Emprunt,String> mail_r;
	    
	    /**
	     * Colonne oeuvre
	     */
	    @FXML
	    private TableColumn<Emprunt,String> oeuvre_r;
	    
	    /**
	     * Colonne auteur
	     */
	    @FXML
	    private TableColumn<Emprunt,String> auteur_r;
	    
	    /**
	     * Colonne genre
	     */
	    @FXML
	    private TableColumn<Emprunt,String> genre_r;
	    
	    /**
	     * Colonne date d'emprunt 
	     */
	    @FXML
	    private TableColumn<Emprunt,String> date_empr_r;
	    
	    /**
	     * Colonne date de retour
	     */
	    @FXML
	    private TableColumn<Emprunt,String> date_retour_r;
	    
	    /**
	     * Bouton pour actualiser les forfaits
	     */
	    @FXML
	    private Button Maj_abo;
	    //partie utilisateur
	    
	    /**
	     * tab pour utilisateur
	     */
	    @FXML
	    private Tab input_name_U;

	    /**
	     * entrée nom
	     */
	    @FXML
	    private JFXTextField input_nom_U;

	    /**
	     * entrée prenom
	     */
	    @FXML
	    private JFXTextField input_prenom_U;

	    
	    /**
	     * entrée date de naissance
	     */
	    @FXML
	    private JFXDatePicker input_age_U;

	    /**
	     * entrée adresse
	     */
	    @FXML
	    private JFXTextField input_adresse_U;

	    /**
	     * entrée tel
	     */
	    @FXML
	    private JFXTextField input_phone_U;

	    /**
	     * entrée mdp
	     */
	    @FXML
	    private JFXPasswordField input_mdp_U;

	    /**
	     * entrée email
	     */
	    @FXML
	    private JFXTextField input_email_U;
	    
	    /**
	     * entrée admin
	     */
	    @FXML
	    private JFXRadioButton input_admin_U;
	    
	    
	    //partie ajout_livre
	    
	    /**
	     * entrée nom
	     */
	    @FXML
	    private TextField input_nom_L;
	    
	    /**
	     * entrée auteur
	     */
	    @FXML
	    private TextField input_auteur_L;

	    /**
	     * entrée genre
	     */
	    @FXML
	    private TextField input_genre_L;
	    
	    /**
	     * entrée nb dispo
	     */
	    @FXML
	    private TextField input_nb_L;
	    
	    /**
	     * entrée maison edition
	     */
	    @FXML
	    private TextField input_maison_L;
	    
	    // dvd 
	    
	    /**
	     * entrée nom
	     */
	    @FXML
	    private JFXTextField input_nom_DVD;
	    
	    /**
	     * entrée realisateur
	     */
	    @FXML
	    private JFXTextField input_real_DVD;
	    
	    /**
	     * entrée genre
	     */
	    @FXML
	    private JFXTextField input_genre_DVD;
	    
	    /**
	     * nb dispo
	     */
	    @FXML
	    private JFXTextField input_nb_DVD;
	    
	    /**
	     * label ajout livre
	     */
	    @FXML
	    private Label p1;
	    
	    /**
	     * label ajout dvd
	     */
	    @FXML
	    private Label p2;
	    
	    /**
	     * label actualisation
	     */
	    @FXML
	    private Label lActua;    
	    
	    /**
	     * label rendu
	     */
	    @FXML
	    private Label rendu;    
	    
	    
	    
	    /**
	     * Initialisation de la page gestion users : init des tableaux et du css
	     * @author Bryan C. Vincent A.
	     * @version 2.0
	     */
	    @FXML
	    private void initialize()
	    {
	    	//Table Gestion utilisateur
	    	table.setEditable(true);
	    	
	    	if (getLng().equals("../fr/")) {
	        	table.setPlaceholder(new Label(" Aucun contenu dans la table des utilisateurs"));
	        } else {
	        		table.setPlaceholder(new Label(" No contents in the user table"));
	        }     	
	        age_gest_C.setCellValueFactory(new PropertyValueFactory<>("age"));
	    	age_gest_C.setCellValueFactory(new PropertyValueFactory<Utilisateur, Date>("date"));
	        age_gest_C.setCellFactory(new Callback<TableColumn<Utilisateur, Date>, TableCell<Utilisateur, Date>>() {
	            @Override
	            public TableCell<Utilisateur, Date> call(TableColumn<Utilisateur, Date> p) {
	                DatePickerCell<Utilisateur, Date> datePick = new DatePickerCell<Utilisateur, Date>(table.getItems());
	                return datePick;
	            }
	        });
	        prenom_gest_C.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            prenom_gest_C.setCellFactory(TextFieldTableCell.forTableColumn());
            prenom_gest_C.setOnEditCommit(e -> {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue());
            });
            nom_gest_C.setCellValueFactory(new PropertyValueFactory<>("nom"));
            nom_gest_C.setCellFactory(TextFieldTableCell.forTableColumn());
            nom_gest_C.setOnEditCommit(e -> {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
            });
	    	num_gest_C.setCellValueFactory(new PropertyValueFactory<>("tel"));
	    	num_gest_C.setCellFactory(TextFieldTableCell.forTableColumn());
	    	num_gest_C.setOnEditCommit(e -> {
	    		e.getTableView().getItems().get(e.getTablePosition().getRow()).setTel(e.getNewValue());
	    	});
	    	email_gest_C.setCellValueFactory(new PropertyValueFactory<>("mail"));
	    	email_gest_C.setCellFactory(TextFieldTableCell.forTableColumn());
	    	email_gest_C.setOnEditCommit(e -> {
	    		e.getTableView().getItems().get(e.getTablePosition().getRow()).setMail(e.getNewValue());
	    	});
	    	adresse_gest_C.setCellValueFactory(new PropertyValueFactory<>("adr"));
	    	adresse_gest_C.setCellFactory(TextFieldTableCell.forTableColumn());
	    	adresse_gest_C.setOnEditCommit(e -> {
	    		e.getTableView().getItems().get(e.getTablePosition().getRow()).setAdr(e.getNewValue());
	    	});
	    	ObservableList<Utilisateur> observableList = FXCollections.observableArrayList();
	    	table.setItems(observableList);
	    	
	    	//Table gestion rendu
	    	if (table_r!=null ) {
	    	if (getLng().equals("../fr/")) {
		        table.setPlaceholder(new Label(" Aucun contenu dans la table de réservation"));
		    } else {
		        table.setPlaceholder(new Label(" No contents in the reservation table"));
		    } 
	    	mail_r.setCellValueFactory(new PropertyValueFactory<>("mail"));
	    	oeuvre_r.setCellValueFactory(new PropertyValueFactory<>("titre"));
	    	auteur_r.setCellValueFactory(new PropertyValueFactory<>("auteur"));
	    	genre_r.setCellValueFactory(new PropertyValueFactory<>("genre"));
	    	date_empr_r.setCellValueFactory(new PropertyValueFactory<>("date_emprunt"));
	    	date_retour_r.setCellValueFactory(new PropertyValueFactory<>("date_rendu"));
	    	table_r.setOnMouseClicked((MouseEvent event) -> {
	    	    if (event.getClickCount() == 1) {
	                int index = table_r.getSelectionModel().getSelectedIndex();
	                Emprunt emprunt = table_r.getItems().get(index);
	                System.out.println("youpi");
	                CurrentEmprunt = emprunt;
	    	    }
	    	});
	    	ObservableList<Emprunt> observableList_r = FXCollections.observableArrayList();
	    	table_r.setItems(observableList_r);
	    	}
	    	main.getStylesheets().add(getClass().getResource(getLng()+getCss()).toExternalForm());
	    }
	    

	    /**
	     * Creation utilisateur
	     * @author Bryan C. Vincent A.
	     * @param event evenement
	     * @version 2.0
	     * @throws Exception exception
	     */
	    @FXML
	    private void create_U(Event event) throws Exception {
	    	String[] s= { input_nom_U.getText(), input_prenom_U.getText(), input_adresse_U.getText(),input_phone_U.getText(), BDD.md5(input_mdp_U.getText()),input_email_U.getText()};
		    boolean q = input_admin_U.isSelected();
	    	int result=BDD.ajout_utilisateur(s,input_age_U.getValue(),q); //result servira a afficher des labels ou autre si necessaire
	    	

            
	    }

	    /**
	     * modification Utilisateur
	     * @author Bryan C.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    void modif_U(Event event) {
	    	for ( Utilisateur user : table.getItems() ) {
	    		if (user.getOld_mail() != null) {
	    			BDD.UpdateUtilisateur(user, user.getOld_mail());
	    			user.setOld_mail(null);
	    		}
	    		else {
	    			BDD.UpdateUtilisateur(user, user.getMail());
	    		}
	    	}
	    }
	    
	    @FXML
	    void recherche2_U(KeyEvent event) {

	    }

	    /**
	     * Recherche Utilisateur
	     * @author Bryan C.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    void recherche_U(Event event) {
	    	String[] s = {inputText_gest.getText()};
	    	Map<String, Object[]> results = BDD.recherche(s);
	    	ObservableList<Utilisateur> observableList = FXCollections.observableArrayList();
	    	table.setItems(observableList);
	        for ( String key : results.keySet()) {
	        	Object[] o = results.get(key);
	        	Utilisateur user = new Utilisateur( key,(String)o[0],(String)o[1],(Date)o[2],(String)o[3],(String)o[4],(boolean)o[5],(int)o[6],(int)o[7],(int)o[8],(String)o[9]);
	        	table.getItems().add(user);
	        }

	    }
	    
	    /**
	     * Creation de livre
	     * @author Vincent A.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    void create_L(Event event) {
		    String[] s= {input_nom_L.getText(),input_auteur_L.getText(),input_genre_L.getText(),input_maison_L.getText(),input_nb_L.getText()};
	    	try {
		    int result=BDD.ajout_livre(s); //result servira a afficher des labels ou autre si necessaire
		    p1.setText("Livre ajouté");
	    	}catch (Exception e){
	    		p1.setText("Valeur erronée");
	    	}
	    }
	    
	    /**
	     * actualisation de forfait
	     * @author Vincent A.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    void maj_abo(Event event) {
	    	BDD.MAJ_abonnement();
	    	lActua.setText("Actualisation faite");
	    }
	    
	    /**
	     * Creation de dvd
	     * @author Vincent A.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    void create_DVD(Event event)  {
	    	String[] s= {input_nom_DVD.getText(),input_real_DVD.getText(),input_nb_DVD.getText()};
	    	try {
	    	int result=BDD.ajout_DVD(s);
	    	p2.setText("DVD créer");
	    	}catch (Exception e) {
	    		p2.setText("Valeur erronée");
	    	}

	    }

	    /**
	     * Remove utilisateur
	     * @author Vincent A.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    void remove_U(Event event) {
	    	for ( Utilisateur user : table.getItems() ) {
	    			BDD.DeleteUtilisateur(user.getMail());
	    		}
	    }
	    
	    /**
	     * set la color de l'input si le format de l'input est correct
	     * @author Bryan C.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML 
	    private void validEmail (KeyEvent event) {
	    	if(isValid(input_email_U.getText())) {
	    		input_email_U.setStyle("-jfx-focus-color: #008000;");
	    	}
	    	else {
	    		input_email_U.setStyle("-jfx-focus-color: #e61919;");
	    	}
	    }
	    
	    /**
	     * set la color de l'input si le format de l'input est correct
	     * @author Bryan C.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    private void validTel (KeyEvent event ) {
	    	if(isValidTel(input_phone_U.getText())) {
	    		input_phone_U.setStyle("-jfx-focus-color: #008000;");
	    	}
	    	else {
	    		input_phone_U.setStyle("-jfx-focus-color: #e61919;");
	    	}
	    }
	    
	    /**
	     * limite le nombre de carac de l'entrée
	     * @author Bryan C.
	     * @param event evenement
	     * @version 2.0
	     */
	    @FXML
	    private void limit(KeyEvent event) {
	    	TextField tf = (TextField)event.getSource();
	    	if(isValidName(tf.getText())) {
	    		tf.setStyle("-jfx-focus-color: #008000;");
	    	}
	    	else {
	    		tf.setStyle("-jfx-focus-color: #e61919;");
	    	}
	    	if ( tf.getText().length() > 20 ) {
	    		String s = tf.getText().substring(0, 20);
                tf.setText(s);
	    	}
	    }
	    
	    /**
	     * check si la structure du mail est valide
	     * @author Bryan C.
	     * @param email mail que l'on test
	     * @version 2.0
	     * @return boolean true si email valid
	     */
	    private static boolean isValid(String email) {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                    "[a-zA-Z0-9_+&*-]+)*@" + 
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                    "A-Z]{1,7}$"; 
                      
	        Pattern pat = Pattern.compile(emailRegex); 
	        if (email == null) 
	        	return false; 
	        return pat.matcher(email).matches(); 
	    }
	    
	    /**
	     * check si la structure du nom est valide
	     * @author Bryan C.
	     * @param nom nom utilisateur
	     * @version 2.0
	     * @return boolean true si nom valid
	     */
	    private static boolean isValidName(String nom) {
	        String emailRegex = "^[a-zA-Z ,.'-]{0,}$"; 
                      
	        Pattern pat = Pattern.compile(emailRegex); 
	        if (nom == null) 
	        	return false; 
	        return pat.matcher(nom).matches(); 
	    }
	    
	    /**
	     * check si la structure du tel est valide
	     * @author Bryan C.
	     * @param nom tel que l'on test
	     * @version 2.0
	     * @return boolean true si tel valid
	     */
	    private static boolean isValidTel(String nom) {
	        String emailRegex = "^(0)\\d{8}$"; 
                      
	        Pattern pat = Pattern.compile(emailRegex); 
	        if (nom == null) 
	        	return false; 
	        return pat.matcher(nom).matches(); 
	    }
	    
	    /**
	     * charge le tableau des réservations
	     * @author Vincent A. Sofiane B.
	     * @param e evenement
	     * @version 2.0
	     */
	    @FXML
	    public void charg_rendu(Event e) {
	    	e.consume();
	    	System.out.println("ok");
	    	ObservableList<Emprunt> observableList = FXCollections.observableArrayList();
	        if (table_r!=null) {table_r.setItems(observableList); }
	    	Map<String, Object[]> results = BDD.rechercheReservation2();
	    	for ( String key : results.keySet()) {
	        	Object[] s = results.get(key);
	        	Emprunt m = new Emprunt(s[3].toString(),s[2].toString(),key,(String)s[0],(String)s[1],(String)s[4],(int)s[5]);
	        	observableList.add(m);
	    	}
	    	 if (table_r!=null) {table_r.setItems(observableList);}

	    }
	    
	    /**
	     * Supprime la réservation sélectionnée
	     * @author Vincent A. Sofiane B.
	     * @param e evenement
	     * @version 2.0
	     */
	    @FXML
	    private void supp_resa(Event e) {
	        
        
        		if (CurrentEmprunt!=null) {
        			System.out.println("test");
        	    	int id_reservation =CurrentEmprunt.getId_reservation();
        	    	BDD.supp_resa(id_reservation);
        	    	charg_rendu(e);
        	    	rendu.setText("Rendu fait");
        	    }
        	
        	
        }
	    
	    /**
	     * Cellule DatePicker
	     * @author Bryan C.
	     * @version 2.0
	     */
	    public class DatePickerCell<S, T> extends TableCell<Utilisateur, Date> {

	        private DatePicker datePicker;
	        private ObservableList<Utilisateur> birthdayData;

	        public DatePickerCell(ObservableList<Utilisateur> listBirthdays) {

	            super();
	           
	            this.birthdayData = listBirthdays;

	            if (datePicker == null) {
	                createDatePicker();
	            }
	            setGraphic(datePicker);
	            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

	            Platform.runLater(new Runnable() {
	                @Override
	                public void run() {
	                    datePicker.requestFocus();
	                }
	            });
	        }

	        @Override
	        public void updateItem(Date item, boolean empty) {

	            super.updateItem(item, empty);
	            SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");

	            if (null == this.datePicker) {
	                System.out.println("datePicker is NULL");
	            }

	            if (empty) {
	                setText(null);
	                setGraphic(null);
	            } else {

	                if (isEditing()) {
	                    setContentDisplay(ContentDisplay.TEXT_ONLY);

	                } else {
	                    setDatepikerDate(smp.format(item));
	                    setText(smp.format(item));
	                    setGraphic(this.datePicker);
	                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	                }
	            }
	        }
	        

	        private void setDatepikerDate(String dateAsStr) {

	            LocalDate ld = null;
	            int jour, mois, annee;
	            System.out.println(dateAsStr);
	            jour = mois = annee = 0;
	            try {
	                annee = Integer.parseInt(dateAsStr.substring(0, 4));
	                mois = Integer.parseInt(dateAsStr.substring(5, 7));
	                jour = Integer.parseInt(dateAsStr.substring(8, dateAsStr.length()));
	            } catch (NumberFormatException e) {
	                System.out.println("setDatepikerDate / unexpected error " + e);
	            }

	            ld = LocalDate.of(annee, mois, jour);
	            datePicker.setValue(ld);
	        }

	        private void createDatePicker() {
	            this.datePicker = new JFXDatePicker();
	            datePicker.setPromptText("jj/mm/aaaa");
	            datePicker.setEditable(true);

	            datePicker.setOnAction(new EventHandler() {
	                public void handle(Event t) {
	                    LocalDate date = datePicker.getValue();
	                    int index = getIndex();

	                    SimpleDateFormat smp = new SimpleDateFormat("yyyy-mm-dd");
	                    Calendar cal = Calendar.getInstance();
	                    cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
	                    cal.set(Calendar.MONTH, date.getMonthValue() - 1);
	                    cal.set(Calendar.YEAR, date.getYear());

	                    setText(smp.format(cal.getTime()));
	                    commitEdit(new java.sql.Date(cal.getTime().getTime()));
	                    
	                    if (null != getBirthdayData()) {
	                        getBirthdayData().get(index).setDate( new java.sql.Date(cal.getTime().getTime()));
	                    }
	                }
	            });

	            setAlignment(Pos.CENTER);
	        }

	        @Override
	        public void startEdit() {
	            super.startEdit();
	        }

	        @Override
	        public void cancelEdit() {
	            super.cancelEdit();
	            setContentDisplay(ContentDisplay.TEXT_ONLY);
	        }
	       
	        public ObservableList<Utilisateur> getBirthdayData() {
	            return birthdayData;
	        }

	        public void setBirthdayData(ObservableList<Utilisateur> birthdayData) {
	            this.birthdayData = birthdayData;
	        }

	        public DatePicker getDatePicker() {
	            return datePicker;
	        }

	        public void setDatePicker(DatePicker datePicker) {
	            this.datePicker = datePicker;
	        }

	    }
	}