package application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@ExtendWith(ApplicationExtension.class)
public class MainTest {


    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    
    Parent root = null;
    @Start
    public void start(Stage primaryStage) {
        try
        {
            // Load the FXML File

            root = (Parent)FXMLLoader.load(getClass().getResource("fr/VBoxMain.fxml"));
            // Create the Scene
            Scene scene = new Scene(root,1100,700);
            // Add the StyleSheet to the Scene
            scene.getStylesheets().add(getClass().getResource("fr/application.css").toExternalForm());
            // Set the Title to the Stage
            primaryStage.setTitle("Bibliotheca");
            primaryStage.setMinHeight(700);
            primaryStage.setMinWidth(1100);
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
    public void when_button_is_clicked_page(FxRobot robot) {
    	robot.sleep(500);
        robot.clickOn("#display");
        robot.sleep(400);
        robot.clickOn("#display");
        robot.sleep(400);
        robot.clickOn("#param");
        robot.sleep(400);
        
        robot.clickOn("#user");
        robot.sleep(400);
        
        //connexion fail
        robot.clickOn("#mail");
        robot.write("Sofiane56240@gmail.com");
        robot.clickOn("#mdp");
        robot.write("toto1");
        robot.clickOn("#connection");
        Assertions.assertThat(robot.lookup("#erreur_connexion").queryLabeled()).hasText("Email ou mdp non valide!");
        
        robot.sleep(400);
        robot.doubleClickOn("#mdp");
        robot.write("toto");
        robot.clickOn("#connection");
        
        
        robot.clickOn("#livre");
        robot.sleep(400);
        robot.clickOn("#barre_recherche");
    	robot.sleep(400);
        robot.write("Le Fleau");
        robot.sleep(400);
        robot.clickOn("#button");
        robot.sleep(400);
		Assertions.assertThat(robot.lookup("#table").queryTableView()).containsRow("Le Fléau", "livre","Roman","King","Livre de Poche",1);
		robot.sleep(400);
		
		robot.doubleClickOn(400,160);
		robot.clickOn("#add");
		robot.sleep(700);
		robot.doubleClickOn(400,160);
		robot.clickOn("#supEx");
		robot.sleep(700);
		robot.doubleClickOn(400,180);
		robot.clickOn("#supMedia");
		robot.sleep(400);
		robot.doubleClickOn(400,160);
		robot.clickOn("#ajoute_panier");
		robot.sleep(400);
		robot.clickOn("#caddie");
		robot.sleep(400);
		robot.clickOn("#final");
		robot.sleep(400);

		
		
		
		

    }
}