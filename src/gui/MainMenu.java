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
	private MainMenu(Stage primaryStage) {
		mainMenu(primaryStage);
	}
	public static void mainMenu(Stage primaryStage) {
		//Create new Scene for the main menu and added .css file to it
		Scene mainScene = new Scene(bp, 500, 200);
		mainScene.getStylesheets().add("application/application.css");
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Main Menu");
		//Created two buttons for options in the main menu and placed them in an HBox
		Button encryptButton = new Button("Encrypt");
		encryptButton.getStyleClass().add("mainButton");
		Button decryptButton = new Button("Decrypt");
		decryptButton.getStyleClass().add("mainButton");
		HBox mainHBox = new HBox(encryptButton, decryptButton);
		mainHBox.setSpacing(20);
		//Created a label with text for main menu options
		Label mainLabel = new Label("Encrypt or Decrypt?");
		mainLabel.getStyleClass().add("mainLabel");
		//Created a VBox to place label above mainHBox which contains the two buttons for options
		VBox mainVBox = new VBox(mainLabel , mainHBox);
		mainVBox.setSpacing(20);
		//Centered the VBox containing all elements of the main menu
		bp.setCenter(mainVBox);
		//Event handler that sends user to encrypt menu if encrypt button is clicked
		encryptButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				EncryptMenu.encryptMenu(primaryStage);
			}
			
		});
		//Event handler that sends user to decrypt menu if decrypt button is clicked
		decryptButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				//decryptMenu(primaryStage);
			}
			
		});
	}
}
