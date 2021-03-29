package application.controller;

import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Controller de la page param�tre
 */
public class Cparam extends SuperController {


	

    @FXML
    private Pagination start;
    
    @FXML
    private JFXRadioButton night;

    
    /**
     * Initialisation de la page parametre : init de la pagination et du css
     * @author Sofiane B.
     * @version 2.0
     */
    @FXML
    void initialize()
    {
    	start.setPageFactory((pageIndex) -> {
            Label label1 = new Label("");
            Label label2 = new Label("");
            
            if (getLng().equals("../fr/")) {
            switch(pageIndex) {
            case 0 :
            label1 = new Label("	Quelles sont les diff�rents forfaits disponible ?");
            label1.setFont(new Font("Book Antiqua", 20));
            label2.setFont(new Font("Book Antiqua", 16));

            label2 = new Label("\n\n\n 		Les forfaits sont instaur� en fonction de l'�ge et l'anciennet� de nos clients :\r\n\n"
            		+ "		- Pour les enfants 5 livres maximum sans possibilit� de r�server de DVD\r\n\n"
            		+ "		- Pour la premi�re ann�e d�abonnement, un adulte peut r�server 2 DVD et 4 livres\r\n\n"
            		+ "		- Pour la deuxi�me ann�e d�abonnement, un adulte peut r�server 3 DVD et 5 livres \r\n\n"
            		+ "		- A partir de la troisi�me ann�e d�abonnement, un adulte peut prendre 5 DVD et 7 livres.");
            break;
            
            case 1 :
            label1= new Label("	Comment faire un retour ?");
            label1.setFont(new Font("Book Antiqua", 20));
            label2.setFont(new Font("Book Antiqua", 16));

            label2 = new Label("\n\n\n 		Il vous suffit de vous rendre � l'acceuil de votre biblioth�que pr�f�r�, de d�poser le m�dia que vous voulez \r\n\n"
            		+ "		retouner � votre biblioth�caire, elle se chargera de faire ce retour au plus vite afin que vous  \r\n\n"
            		+ " 		puissez � nouveau en prendre un.");

            break;
            
            case 2 :
            label1= new Label("	Comment fait on pour s'abonner ?");
            label1.setFont(new Font("Book Antiqua", 20));
            label2.setFont(new Font("Book Antiqua", 16));

            label2 = new Label("\n\n\n 		Il vous suffit de vous rendre � l'acceuil de votre biblioth�que pr�f�r�, vous y retrouverer \r\n\n"
            		+ "		votre biblioth�caire, elle se chargera de faire votre inscription avec vous, vous  \r\n\n"
            		+ " 		pourrez ensuite r�server les m�dias de votre choix.");

            break;
            
            case 3 :
            label1= new Label("	Pourquoi devez vous nous donner 20 pour cette application ?");
            label1.setFont(new Font("Book Antiqua", 20));
            label2.setFont(new Font("Book Antiqua", 16));

            label2 = new Label("\n\n\n 		Nous avons r�ussi � mettre en place une application java fonctionnelle pouvant �tre utilis�e au sein de r�elles\r\n\n"
            		+ "		biblioth�ques, le tout avec une interface plut�t plaisante et epur�e.\r\n\n"
            		+ " 		J'esp�re que celle ci vous plaira!!");

            break;
            }
            }
            else {
            	switch(pageIndex) {
                case 0 :
                label1 = new Label("	What are the different packages available?");
                label1.setFont(new Font("Book Antiqua", 20));
                label2.setFont(new Font("Book Antiqua", 16));

                label2 = new Label("\n\n\n 		The packages are set up according to the age and seniority of our customers :\r\n\n"
                		+ "		- For children 5 books maximum without the possibility of reserving a DVD.\r\n\n"
                		+ "		- For the first year of subscription, an adult can reserve 2 DVDs and 4 books.\r\n\n"
                		+ "		- For the second year of subscription, an adult can reserve 3 DVDs and 5 books. \r\n\n"
                		+ "		- From the third year of subscription, an adult can take 5 DVDs and 7 books.");
                break;
                
                case 1 :
                label1= new Label("	How to return ?");
                label1.setFont(new Font("Book Antiqua", 20));
                label2.setFont(new Font("Book Antiqua", 16));

                label2 = new Label("\n\n\n 		All you have to do is go to the reception desk of your favourite library and drop off the media you want \r\n\n"
                		+ "		 back to your librarian, she will arrange for this return as soon as possible so that you can  \r\n\n"
                		+ " 		may you take one again.");

                break;
                
                case 2 :
                label1= new Label("	How to subscribe ?");
                label1.setFont(new Font("Book Antiqua", 20));
                label2.setFont(new Font("Book Antiqua", 16));

                label2 = new Label("\n\n\n 		All you have to do is go to the reception desk of your favourite library and you will find there \r\n\n"
                		+ "		your librarian, she will take care of registering you with you, you  \r\n\n"
                		+ " 		will then be able to book the media of your choice.");

                break;
                
                case 3 :
                label1= new Label("	Why you have to give us 20 for this application ?");
                label1.setFont(new Font("Book Antiqua", 20));
                label2.setFont(new Font("Book Antiqua", 16));

                label2 = new Label("\n\n\n 		We have succeeded in setting up a functional java application that can be used in real-life situations\r\n\n"
                		+ "		libraries, all with a rather pleasant and refined interface.\r\n\n"
                		+ " 		I hope you like this one!!");

                break;
            	
            	
            	}
            }
            return new Pane(label1, label2);
            
        });
    	System.out.println(getCss());
    	main.getStylesheets().add(getClass().getResource(getLng()+getCss()).toExternalForm());
    	if (getCss().equals("Darkmode.css")){
    		night.setSelected(true);
    	}
    }


    /**
     * Change la langue en fran�ais
     * @author Bryan
     * @throws Exception exception
     * @param event evenement
     * @version 2.0
     */
    @FXML
    void changeFR(MouseEvent event) throws Exception {
    	setLng("../fr/");
    	changeScene(getLng()+"VBoxParametre.fxml",event);
    }
    
    /**
     * Change la langue en anglais
     * @author Bryan
     * @throws Exception exception
     * @param event evenement
     * @version 2.0
     */
    @FXML
    void changeEN(MouseEvent event) throws Exception {
    	setLng("../en/");
    	changeScene(getLng()+"VBoxParametre.fxml",event);
    }

    /**
     * Change la style en mode nuit ou mode jours
     * @author Bryan
     * @throws Exception exception
     * @param event evenement
     * @version 2.0
     */
    @FXML
    void changeStyle(MouseEvent event) throws Exception {
    	main.getStylesheets().remove(getClass().getResource(getLng()+getCss()).toExternalForm());
    	if ( night.isSelected()) {
    		setCss("Darkmode.css");
    	}
    	else {
    		setCss("application.css");
    	}
    	main.getStylesheets().add(getClass().getResource(getLng()+getCss()).toExternalForm());
    	changeScene(getLng()+"VBoxParametre.fxml",event);
    }
   
}
