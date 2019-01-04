package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu{
	static BorderPane bp = new BorderPane();
	static Scene mainScene = new Scene(bp, 500, 200);
	private MainMenu(Stage primaryStage) {
		
	}
	public static void mainMenu(Stage primaryStage) {
		//Set properties to the scene for the main menu
		mainScene.getStylesheets().add("application/application.css");
		primaryStage.setScene(mainScene);
		mainScene.getWindow().centerOnScreen();
		primaryStage.setTitle("Main Menu");
		addOptionsToMenu(primaryStage);
	}
	public static void addOptionsToMenu(Stage primaryStage) {
		// Created two buttons for options in the main menu and placed them in an HBox
		Button encryptOption = new Button("Encrypt");
		encryptOption.getStyleClass().add("mainButton");
		Button decryptOption = new Button("Decrypt");
		decryptOption.getStyleClass().add("mainButton");
		HBox mainHBox = new HBox(encryptOption, decryptOption);
		mainHBox.setSpacing(20);
		// Created a label with text for main menu options
		Label mainLabel = new Label("Encrypt or Decrypt?");
		mainLabel.getStyleClass().add("mainLabel");
		// Created a VBox to place label above mainHBox which contains the two buttons
		// for options
		VBox mainVBox = new VBox(mainLabel, mainHBox);
		mainVBox.setSpacing(20);
		// Centered the VBox containing all elements of the main menu
		bp.setCenter(mainVBox);
		// Event handler that sends user to encrypt menu if encrypt button is clicked
		encryptOption.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				EncryptMenu.encryptMenu(primaryStage);
			}

		});
		// Event handler that sends user to decrypt menu if decrypt button is clicked
		decryptOption.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				DecryptMenu.decryptMenu(primaryStage);
			}

		});
	}
}
