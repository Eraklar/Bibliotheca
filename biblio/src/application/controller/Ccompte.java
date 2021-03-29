package application.controller;

import java.util.Map;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Date;

import java.time.LocalDate;


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.control.Label;

import javafx.event.Event;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * Controller de la page Mon compte
 */
public class Ccompte extends SuperController
{
	
    /**
     * label de deco
     */
    @FXML
    private Label deco;
    
    /**
     * label de nom
     */
    @FXML
    private Label l_nom_Co;
    
    /**
     * label de prenom
     */
    @FXML
    private Label l_prenom_Co;
    
    /**
     * Tableau des emprunts
     */
    @FXML
    public TableView<Emprunt> table;
    
    /**
     * Colonne oeuvre
     */
    @FXML
    private TableColumn<Emprunt,String> oeuvre_compte_T;

    /**
     * Colonne auteur
     */
    @FXML
    private TableColumn<Emprunt,String> auteur_compte_T;
    
    /**
     * Colonne type d'emprunt
     */
    @FXML
    private TableColumn<Emprunt,String> type_compte_T;
    
    /**
     * Colonne emprunt
     */
    @FXML
    private TableColumn<Emprunt,String> emprunt_compte_T;
    
    /**
     * Colonne date de retour
     */
    @FXML
    private TableColumn<Emprunt,String> retour_compte_T;
    
    /**
     * label tel
     */
    @FXML
    private Label l_telephone_Co;
    
    /**
     * label date de naissance
     */
    @FXML
    private Label l_date_Co;
    
    /**
     * label email
     */
    @FXML
    private Label l_email_Co;
    
    /**
     * label adresse
     */
    @FXML
    private Label l_adresse_Co;
    
    /**
     * Entrée nom
     */
    @FXML
    private JFXTextField input_name_Co;
    
    /**
     * Entrée prenom
     */
    @FXML
    private JFXTextField input_prenom_Co;
    
    /**
     * Entrée tel
     */
    @FXML
    private JFXTextField input_tel_Co;
    
    /**
     * Entrée date de naissance
     */
    @FXML
    private JFXDatePicker input_birth_Co;
    
    /**
     * Entrée email
     */
    @FXML
    private JFXTextField input_email_Co;
    
    /**
     * Entrée adresse
     */
    @FXML
    private JFXTextField input_adresse_Co;
    
    @FXML
    private JFXPasswordField input_mdp_Co;
    
    @FXML
    private JFXPasswordField input_confirm_mdp;
    
    // Add a public no-args constructor
    public Ccompte()
    {
    }
 
    /**
     * Initialisation de la page compte : init du tableau et du css
     * @author Bryan C. Erwan D.
     * @version 2.0
     */
    @FXML
    private void initialize()  
    {
    	if (getLng().equals("../fr/")) {
            table.setPlaceholder(new Label(" Aucun contenu dans la table de réservation"));
            } else {
                table.setPlaceholder(new Label(" No contents in the reservation table"));
            }
    	oeuvre_compte_T.setCellValueFactory(new PropertyValueFactory<>("titre"));
    	auteur_compte_T.setCellValueFactory(new PropertyValueFactory<>("auteur"));
    	type_compte_T.setCellValueFactory(new PropertyValueFactory<>("type"));
    	emprunt_compte_T.setCellValueFactory(new PropertyValueFactory<>("date_emprunt"));
    	retour_compte_T.setCellValueFactory(new PropertyValueFactory<>("date_rendu"));
    	ObservableList<Emprunt> observableList = FXCollections.observableArrayList();
    	table.setItems(observableList);
    	main.getStylesheets().add(getClass().getResource(getLng()+getCss()).toExternalForm());
    }
    
    /**
     * Deconnection de l'utilisateur
     * @author Erwan D.
     * @version 2.0
     */
    @FXML
    private void deconnection() {
    	setEmail(null);
    }
    
    
    /**
     * transition input en label et inversement
     * @author Erwan D.
     * @param label objet label
     * @param jfx objet jfxtextfiel
     * @version 2.0
     */
    private void hide(Label label, JFXTextField jfx) {
    	if((label.isVisible()) == true) {
    		label.setVisible(false);
    		jfx.setVisible(true); 
    	}else{
    		label.setVisible(true);
    		jfx.setVisible(false); 
    	}
    }
    
    
    /**
     * transition datepicker et label
     * @author Erwan D.
     * @param label objet label
     * @param d objet jfxdatepicker
     * @version 2.0
     */
    private void hide_date(Label label, JFXDatePicker d) {
    	if((label.isVisible()) == true) {
    		label.setVisible(false);
    		d.setVisible(true); 
    	}else{
    		label.setVisible(true);
    		d.setVisible(false); 
    	}
    }
    
    /**
     * transition input to label en appuyant sur entrée et sauvegarde des modifs
     * @author Erwan D.
     * @throws Exception exception
     * @param e evenement
     * @version 2.0
     */
    @FXML
    private void hide_JFX(KeyEvent e) throws Exception {
    	if(e.getCode() == KeyCode.ENTER) {
    		String text = ((JFXTextField)e.getSource()).getId();
        	switch(text){
        		case "input_name_Co":
        			getUser().setNom(((JFXTextField)e.getSource()).getText());
        			BDD.UpdateUtilisateur(getUser(), getUser().getMail());
        			hide(l_nom_Co,input_name_Co);
        			info();
        			break;
        		case "input_prenom_Co":
        			getUser().setPrenom(((JFXTextField)e.getSource()).getText());
        			BDD.UpdateUtilisateur(getUser(), getUser().getMail());
        			hide(l_prenom_Co,input_prenom_Co);
        			info();
        			break;
        		case "input_tel_Co":
        			getUser().setTel(((JFXTextField)e.getSource()).getText());
        			BDD.UpdateUtilisateur(getUser(), getUser().getMail());
        			hide(l_telephone_Co,input_tel_Co);
        			info();
        			break;
        		case "input_email_Co":
                    getUser().setMail(((JFXTextField)e.getSource()).getText());
                    if (getUser().getOld_mail() != null) {
                        BDD.UpdateUtilisateur(getUser(), getUser().getOld_mail());
                        getUser().setOld_mail(null);
                    }
                    else {
                        BDD.UpdateUtilisateur(getUser(), getUser().getMail());
                    }
                    hide(l_email_Co,input_email_Co);
                    info();
                    break;
        		case "input_adresse_Co":
        			getUser().setAdr(((JFXTextField)e.getSource()).getText());
        			BDD.UpdateUtilisateur(getUser(), getUser().getMail());
        			hide(l_adresse_Co,input_adresse_Co);
        			info();
        			break;
        	}
        }
    	}
    	
    
    
    /**
     * application de la fonction hide en fonction du label
     * @author Erwan D.
     * @param e evenement
     * @version 2.0
     */
    @FXML
    private void change(Event e) {
    	String text = ((Label)e.getSource()).getId();
    	switch(text){
    		case "l_nom_Co":
    			hide(l_nom_Co,input_name_Co);
    			break;
    		case "l_prenom_Co":
    			hide(l_prenom_Co,input_prenom_Co);
    			break;
    		case "l_telephone_Co":
    			hide(l_telephone_Co,input_tel_Co);
    			break;
    		case "l_date_Co":
    			hide_date(l_date_Co,input_birth_Co);
    			break;
    		case "l_email_Co":
    			hide(l_email_Co,input_email_Co);
    			break;
    		case "l_adresse_Co":
    			hide(l_adresse_Co,input_adresse_Co);
    			break;
    	}
    }
    
    /**
     * affiche info utilisateur
     * @author Bryan C. Erwan D. Vincent A.
     * @version 2.0
     */
    @FXML
    public void info() {
    l_nom_Co.setText(getUser().getNom());
	l_prenom_Co.setText(getUser().getPrenom());
	l_telephone_Co.setText(getUser().getTel());
	String date = (getUser().getAge()).toString();
	l_date_Co.setText(date);
	l_email_Co.setText(getUser().getMail());
	l_adresse_Co.setText(getUser().getAdr());
	input_name_Co.setText(getUser().getNom());
	input_prenom_Co.setText(getUser().getPrenom());
	input_tel_Co.setText(getUser().getTel());
	input_email_Co.setText(getUser().getMail());
	input_adresse_Co.setText(getUser().getAdr());
	
	ObservableList<Emprunt> observableList = FXCollections.observableArrayList();
    table.setItems(observableList); 
	Map<String, Object[]> results = BDD.rechercheReservation(getUser().getMail());
   
    for ( String key : results.keySet()) {
    	System.out.println(key);
    	
    	Object[] s = results.get(key);
    	
    	Emprunt m = new Emprunt(s[3].toString(),s[2].toString(),key,(String)s[0],(String)s[1],(String)s[4],(int)s[5]);
    	
    	observableList.add(m);
    	
    	
    	
    }
    table.setItems(observableList);
    
    }


    
    public String md5 ( String pwd ) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(pwd.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    
    
    /**
     * affiche enregistrer
     * @author Erwan D. 
     * @version 2.0
     */
	@FXML
	private void enregistrer(Event e) throws Exception {
		String mdp = input_mdp_Co.getText();
		String mdp2 = input_confirm_mdp.getText();
		if((mdp.equals(mdp2)) && (!(mdp.equals(""))) && (!(mdp.equals("")))){
			getUser().setMdp(md5(mdp));
			BDD.UpdateUtilisateur(getUser(), getUser().getMail()); 
			input_mdp_Co.setText("");
			input_confirm_mdp.setText("");
			input_mdp_Co.setStyle("-jfx-focus-color: red; -jfx-unfocus-color: black;");
			input_confirm_mdp.setStyle("-jfx-focus-color: red; -jfx-unfocus-color: black;");
		}else{
			input_mdp_Co.setStyle("-jfx-focus-color: red; -jfx-unfocus-color: red;");
			input_confirm_mdp.setStyle("-jfx-focus-color: red; -jfx-unfocus-color: red;");
		}
	}
}
