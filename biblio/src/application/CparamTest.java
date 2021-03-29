package application;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@ExtendWith(ApplicationExtension.class)
public class CparamTest {


    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    
    Parent root = null;
    @Start
    public void start(Stage primaryStage) {
        try
        {
            // Load the FXML File

            root = (Parent)FXMLLoader.load(getClass().getResource("fr/VBoxParametre.fxml"));
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
    public void when_button_is_clicked_text_changes(FxRobot robot) {
        // when:
    	robot.sleep(2000);
        robot.clickOn("#background");
        robot.sleep(500);
        robot.clickOn("#en");
        robot.sleep(500);
        robot.clickOn(820,680);
        robot.sleep(500);
        robot.clickOn(840,680);
        robot.sleep(500);
        robot.clickOn("#fr");
        robot.sleep(400);
        robot.clickOn(850,680);
        robot.sleep(500);



    }
}