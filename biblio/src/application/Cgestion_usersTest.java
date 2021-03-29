package application;

//import java.sql.Date;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@ExtendWith(ApplicationExtension.class)
public class Cgestion_usersTest {


	/**
	 * Will be called with {@code @Before} semantics, i. e. before each test method.
	 */

	Parent root = null;
	@Start
	public void start(Stage primaryStage) {
		try
		{
			// Load the FXML File

			root = (Parent)FXMLLoader.load(getClass().getResource("fr/VBoxGestionU.fxml"));
			// Create the Scene
			Scene scene = new Scene(root,1100,700);
			// Add the StyleSheet to the Scene
			scene.getStylesheets().add(getClass().getResource("fr/application.css").toExternalForm());
			// Set the Title to the Stage
			primaryStage.setTitle("Bibliotheca");
			primaryStage.setMinHeight(700);
			primaryStage.setMinWidth(1100);
			primaryStage.getIcons().add(new Image("application/img/logo.png"));
			// Add the Scene to the Stage
			primaryStage.setScene(scene);
			primaryStage.show();

			// Show the Stage
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}


	@Test
	public void when_button_is_clicked_user(FxRobot robot) {
		// when:
		robot.sleep(1000);
		robot.write("sosoben56@gmail.com");
		robot.sleep(400);
		robot.clickOn("#recherche");
		robot.sleep(400);   
		//robot.clickOn(400,230);
		//robot.sleep(400);
		robot.clickOn("#supp_u");
		robot.sleep(400);
		robot.clickOn("#recherche");
		robot.sleep(400);
		robot.clickOn("#nom");
		robot.write("Ben Massaoud");
		robot.sleep(400);        
		robot.clickOn("#prenom");
		robot.write("Sofiane");
		robot.sleep(400);        
		robot.clickOn("#age");
		robot.write("17/05/2000");  
		robot.sleep(400);        
		robot.clickOn("#tel");
		robot.write("0680750353");
		robot.sleep(400);
		robot.clickOn("#adr");
		robot.write("Inguiniel");
		robot.sleep(400);        
		robot.clickOn("#mail");
		robot.write("sosoben56@gmail.com");
		robot.sleep(400);        
		robot.clickOn("#mdp");
		robot.write("toto");
		robot.sleep(400);        
		robot.clickOn("#admin");
		robot.sleep(400);        
		robot.clickOn("#button_U");
		robot.sleep(400);
		robot.clickOn("#barre_recherche");


		robot.clickOn("#recherche");
		robot.sleep(400);
		robot.clickOn(500,240);
		robot.clickOn(500,240);
		robot.write("Toto");
		robot.sleep(400);
		robot.press(KeyCode.ENTER);
		robot.sleep(400);
		robot.clickOn("#mod_u");
		robot.clickOn("#barre_recherche");
		robot.clickOn("#recherche");
		robot.sleep(400);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = null;
		try {
			parsed = format.parse("2000-05-17");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sql = new java.sql.Date(parsed.getTime());
		Assertions.assertThat(robot.lookup("#table").queryTableView()).containsRow("Sofiane", "Toto","680750353","sosoben56@gmail.com","Inguiniel",sql);

	}
	@Test
	public void when_button_is_clicked_livre(FxRobot robot) {
		robot.sleep(400);
		robot.clickOn(500,520);
		robot.sleep(400);
		robot.clickOn("#nlivre");
		robot.write("Le Fleuve");
		robot.sleep(400);        
		robot.clickOn("#alivre");
		robot.write("Moi");
		robot.sleep(400);        
		robot.clickOn("#lnombre");
		robot.write("1");  
		robot.sleep(400);        
		robot.clickOn("#glivre");
		robot.write("Roman");
		robot.sleep(400);
		robot.clickOn("#mlivre");
		robot.write("Livre de Poche");
		robot.sleep(400);   
		robot.clickOn("#ajout_livre");
		robot.sleep(1000);   
		Assertions.assertThat(robot.lookup("#p1").queryLabeled()).hasText("Livre ajouté");

	}

	@Test
	public void when_button_is_clicked_dvd(FxRobot robot) {
		robot.sleep(400);
		robot.clickOn(560,520);
		robot.sleep(400);
		robot.clickOn("#ndvd");
		robot.write("so");
		robot.sleep(400);        
		robot.clickOn("#rdvd");
		robot.write("Moi");
		robot.sleep(400);        
		robot.clickOn("#gdvd");
		robot.write("fiction");  
		robot.sleep(400);        
		robot.clickOn("#nbdvd");
		robot.write("1");
		robot.sleep(400);   
		robot.clickOn("#creerdvd");
		robot.sleep(1000);   

		Assertions.assertThat(robot.lookup("#p2").queryLabeled()).hasText("DVD créer");

	}

	@Test
	public void when_button_is_clicked_act(FxRobot robot) {

		robot.sleep(1000);
		robot.clickOn("#act");
		robot.sleep(1000);

		Assertions.assertThat(robot.lookup("#lActua").queryLabeled()).hasText("Actualisation faite");

	}

	@Test
	public void when_button_is_clicked_rendu(FxRobot robot) {

		robot.sleep(400);
		robot.clickOn(660,520);
		robot.sleep(400);
		robot.clickOn(660,570);
		robot.clickOn(660,570);
		robot.sleep(400);
		robot.clickOn("#retour");
		robot.sleep(1000);
		Assertions.assertThat(robot.lookup("#rendu").queryLabeled()).hasText("Rendu fait");

	}


}
