package application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.Driver;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.controller.Emprunt;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@ExtendWith(ApplicationExtension.class)
class CompteTest{

	Parent root = null;

	@Start
	public void start(Stage primaryStage) {
		try {
			// Load the FXML File

			root = (Parent) FXMLLoader.load(getClass().getResource("fr/VBoxMain.fxml"));
			// Create the Scene
			Scene scene = new Scene(root, 1100, 700);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void connexion_compte(FxRobot robot) {
		robot.clickOn("#compte");
		robot.clickOn("#email_conn");
		robot.write("wandehousse@gmail.com");
		robot.clickOn("#mdp_conn");
		robot.write("Nancy5400");
		robot.clickOn("#connection");
		Assertions.assertThat(robot.lookup("#connect_as").queryLabeled()).hasText("connecté en tant que : Ester");
		robot.clickOn("#compte");
		robot.clickOn(710,266);
		robot.sleep(3000);
		robot.clickOn(710,310);
		robot.sleep(4000);
		robot.clickOn(710,370);
		robot.sleep(4000);
		robot.clickOn(710,480);
		robot.sleep(4000);
		robot.clickOn(710,540);
		robot.clickOn("#input_name_Co");
		robot.doubleClickOn("#input_name_Co");
		robot.write("Test1");
		Assertions.assertThat(robot.lookup("#input_name_Co").queryTextInputControl()).hasText("Test1");
		robot.press(KeyCode.ENTER);
		robot.sleep(1000);
		robot.clickOn("#input_prenom_Co");
		robot.doubleClickOn("#input_prenom_Co");
		robot.write("Test2");
		robot.press(KeyCode.ENTER);
		Assertions.assertThat(robot.lookup("#input_prenom_Co").queryTextInputControl()).hasText("Test2");
		robot.sleep(1000);
		robot.clickOn("#input_tel_Co");
		robot.doubleClickOn("#input_tel_Co");
		robot.write("0513594278");
		robot.press(KeyCode.ENTER);
		Assertions.assertThat(robot.lookup("#input_tel_Co").queryTextInputControl()).hasText("0513594278");
		robot.sleep(1000);
		robot.clickOn("#input_email_Co");
		robot.doubleClickOn("#input_email_Co");
		robot.write("test@gmail.com");
		robot.press(KeyCode.ENTER);
		Assertions.assertThat(robot.lookup("#input_email_Co").queryTextInputControl()).hasText("test@gmail.com");
		robot.sleep(1000);
		robot.clickOn("#input_adresse_Co");
		robot.doubleClickOn("#input_adresse_Co");
		robot.write("rue du test 5");
		robot.press(KeyCode.ENTER);
		Assertions.assertThat(robot.lookup("#input_adresse_Co").queryTextInputControl()).hasText("rue du test 5");
		robot.sleep(1000);
		robot.clickOn("#new_mdp");
		robot.write("nouvea_mot_de_passe");
		robot.clickOn("#confirm_mdp_new");
		robot.write("nouveau_mot_de_passe");
		robot.sleep(8000);
		robot.clickOn("#save_data");
		
		robot.doubleClickOn("#new_mdp");
		robot.write("Nancy5400");
		robot.doubleClickOn("#confirm_mdp_new");
		robot.write("Nancy5400");
		robot.sleep(8000);
		robot.clickOn("#save_data");
		
	}
	



}
