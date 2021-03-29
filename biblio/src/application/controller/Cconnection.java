package application.controller;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.util.Map;

import java.sql.Date;

import java.util.regex.Pattern;


import javafx.collections.ObservableList;


import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.Event;
/**
 * Controller de la fenêtre de connection
 */
public class Cconnection
{

    /**
     * entrée mot de passe utilisateur
     */
    @FXML
    private JFXPasswordField mdp_inputText;

    
    /**
     * entrée email utilisateur
     */
    @FXML
    private JFXTextField email_inputText;

    
    /**
     * Bouton de connection
     */
    @FXML
    private Button b_connect;
    
    
    /**
     * fenêtre de l'app
     */
    private Stage  p_window;
    
    
    /**
     * label d'erreur à la connection
     */
    @FXML
    private Label erreur_connexion;
    
    /**
     * path de la scene en arrière plan
     */
    public String path;
    
    /**
     * liste des medias recherchés.
     */
    public ObservableList<Media> r_list = null;
    
    
    /**
     * objet BDD permet de faire des requêtes à la bdd
     */
    private Bdd_access BDD = new Bdd_access();
    // Add a public no-args constructor
    public Cconnection()
    {
    }
 
    @FXML
    private void initialize()  
    {
   
    }

    
    
    /**
     * Appel de connection avec touche Entrée
     * @author Vincent A.
     * @param event evenement
     * @throws Exception exception
     * @version 2.0
     */
    @FXML
    private void recherche2(KeyEvent event) throws Exception {
        if(event.getCode() == KeyCode.ENTER) {
            connection(event);
        }
    }
    
    /**
     * set la couleur de l'input pour montrer si l'entrée est valide
     * @author Vincent A.
     * @param event evenement
     * @version 2.0
     */
    @FXML
    private void validEmail(KeyEvent event) {
    	if(isValid(email_inputText.getText())) {
    		email_inputText.setStyle("-jfx-focus-color: #008000;");
    	}
    	else {
    		email_inputText.setStyle("-jfx-focus-color: #e61919;");
    	}

    }
    
    /**
     * check si la structure du mail est valide
     * @author Bryan C.
     * @param mail mail que l'on test
     * @version 2.0
     * @return boolean true si email valid
     */
    private static boolean isValid(String mail) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
        Pattern pat = Pattern.compile(emailRegex); 
        if (mail == null) 
        	return false; 
        return pat.matcher(mail).matches(); 
    }
    
    /**
     * Recherche l'utilisateur puis le connecte si il est trouvé, affiche un message d'erreur sinon
     * @author Bryan C.
     * @param event evenement
     * @throws Exception exception
     * @version 2.0
     */
    @FXML
    private void connection(Event event) throws Exception {
    	String[] str = { email_inputText.getText(),BDD.md5(mdp_inputText.getText())};
    	Map<String, Object[]> results = BDD.recherche(str);
    	Utilisateur user = null;
        for ( String key : results.keySet()) {
        	Object[] o = results.get(key);
        	user = new Utilisateur( key,(String)o[0],(String)o[1],(Date)o[2],(String)o[3],(String)o[4],(boolean)o[5],(int)o[6],(int)o[7],(int)o[8],(String)o[9]);
        }
    	if ( user != null ) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path));
            String lng = path.substring(0,6);
            Parent root = fxmlLoader.load();
            if ( r_list != null ) {
            	try {
            	Crecherche scene2Controller = fxmlLoader.getController();
                scene2Controller.setEmail(user.getMail());
                scene2Controller.setUser(user);
                scene2Controller.setCwindow(null);
                scene2Controller.setCconnection(null);
                scene2Controller.fromMain(r_list);
                scene2Controller.setLng(lng);
                scene2Controller.connected();
                scene2Controller.afficheAdmin(scene2Controller);
            	}
            	catch ( Exception e) {
            	}
            	}
            else {
            SuperController scene2Controller = fxmlLoader.getController();
            scene2Controller.setEmail(user.getMail());
            scene2Controller.setCwindow(null);
            scene2Controller.setCconnection(null);
            scene2Controller.setUser(user);
            scene2Controller.setLng(lng);
            scene2Controller.afficheAdmin(scene2Controller);
        	try {
        		VBoxMainController c = (VBoxMainController)scene2Controller;
        		c.connected();
        	}
        	catch( Exception e) {}
            }
        	Scene scene =  new Scene(root,1100,700);
        	p_window.setScene(scene);
        	p_window.show();
        	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        	window.close();}
    	else {
    		erreur_connexion.setText("Email ou mdp non valide!");
    	}

    }
    
    /**
     * Recois des données de la page en arrière plan
     * @author Bryan C.
     * @param s fenêtre app principale
     * @param path path du fxml de l'app principale
     * @version 2.0
     */
    public void setParent(Stage s, String path) {  p_window = s; this.path = path;}
    


    /**
     * envoi d'un mail de récup
     * @author Bryan C.
     * @param e evenement
     * @throws Exception exception
     * @version 2.0
     */
    	@FXML
       public void send( Event e) throws Exception {
    			if(!isValid(email_inputText.getText())){
    				erreur_connexion.setText("Email non valide!");
    			}
    			else {
    			  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    			  // Get a Properties object
    			     Properties props = System.getProperties();
    			     props.setProperty("mail.smtp.host", "smtp.gmail.com");
    			     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    			     props.setProperty("mail.smtp.socketFactory.fallback", "false");
    			     props.setProperty("mail.smtp.port", "465");
    			     props.setProperty("mail.smtp.socketFactory.port", "465");
    			     props.put("mail.smtp.auth", "true");
    			     props.put("mail.debug", "true");
    			     props.put("mail.store.protocol", "pop3");
    			     props.put("mail.transport.protocol", "smtp");
    			     final String username = "bibliothecareturn@gmail.com";//
    			     final String password = "rabadabougriletituban";
    			     try{
    			     Session session = Session.getDefaultInstance(props, 
    			                          new Authenticator(){
    			                             protected PasswordAuthentication getPasswordAuthentication() {
    			                                return new PasswordAuthentication(username, password);
    			                             }});

    			   // -- Create a new message --
    			     Message msg = new MimeMessage(session);

    			  // -- Set the FROM and TO fields --
    			     msg.setFrom(new InternetAddress("bibliothecareturn@gmail.com"));
    			     msg.setRecipients(Message.RecipientType.TO, 
    			                      InternetAddress.parse(email_inputText.getText(),false));
    			     msg.setSubject("Mot de passe oublié :)");
    			     String new_pwd = this.generatePassword(12);
    			     BDD.ChangePwd(BDD.md5(new_pwd), email_inputText.getText());
    			     msg.setText("Voici votre mot de passe de substitution : "+new_pwd);
    			     Transport.send(msg);
    			     System.out.println("Message sent.");
     				erreur_connexion.setText("Email envoyé!");
    			  }catch (MessagingException err){ System.out.println("Erreur d'envoi, cause: " + err);}
    			}
    	}
    			

        /**
         * crée un mot de passe aléatoire
         * @author Bryan C.
         * @param length longueur du pwd voulu
         * @throws Exception exception
         * @version 2.0
         * @return password nouveau mot de passe
         */
    	   private String generatePassword(int length) throws Exception {
    		      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    		      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    		      String specialCharacters = "!@#$";
    		      String numbers = "1234567890";
    		      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
    		      Random random = new Random();
    		      char[] password = new char[length];
    		      
    		      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
    		      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
    		      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
    		      password[3] = numbers.charAt(random.nextInt(numbers.length()));
    		   
    		      for(int i = 4; i< length ; i++) {
    		         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
    		      }
    		      return String.valueOf(password);
    		   }
    	   
           /**
            * Recois la recherche en cours 
            * @author Bryan C.
            * @param observableList liste des recherches
            * @version 2.0
            */
	public void getRecherche(ObservableList<Media> observableList) {
	r_list = observableList;
	}
	}
