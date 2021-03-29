package application;
import javafx.application.Platform;
import javafx.event.EventHandler;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
 
 
public class Main extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        try
        {
            // Load the FXML File

            Parent root = (Parent)FXMLLoader.load(getClass().getResource("fr/VBoxMain.fxml"));
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
     
    public static void main(String[] args) 
    {
        launch(args);
    }
}