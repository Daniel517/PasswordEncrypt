package gui;

import backend.Encryption;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EncryptMenu {
	//BorderPane to be used as main layout for scene
	static BorderPane bp = new BorderPane();
	//Scene for encyption menu
	static Scene encryptScene = new Scene(bp, 800, 800);
	//GridPane used to display encrpyted text
	static GridPane gp = new GridPane();
	//Counter to keep track of next open vertical slot of gridpane
	static int counter = 0;
	static String key;
	private EncryptMenu() {
		
	}
	public static void encryptMenu(Stage primaryStage) {
		//Set up properties for scene
		encryptScene.getStylesheets().add("application/application.css");
		primaryStage.setScene(encryptScene);
		encryptScene.getWindow().centerOnScreen();
		primaryStage.setTitle("Encryption Menu");
		key = KeyInputMenu.getKey();
		System.out.println(key + " 1 2 3");
		//Calls method to set up I/O bar of menu
		setUpInputSection(primaryStage);
		//setUpOutputDisplay();
	}
	private static void setUpInputSection(Stage primaryStage) {
		//TextField for user input which will be encrypted
		TextField inputField = new TextField();
		inputField.setPromptText("Enter password to encrypt...");
		inputField.setPrefWidth(350);
		inputField.getStyleClass().add("inputLabel");
		//Button to allow the user to encrypt input
		Button encryptButton = new Button("Encrypt");
		encryptButton.getStyleClass().add("inputButton");
		//Back button which will allow the user to go back to the main menu
		Button backButton = new Button("Back");
		backButton.getStyleClass().add("inputButton");
		//HBox containing all three elements of the I/O bar
		HBox inputSection = new HBox(inputField, encryptButton, backButton);
		inputSection.setSpacing(30);
		//Sets the HBox to the top of the BorderPane
		bp.setTop(inputSection);
		//If encrypt button is pressed, encrypts the input and add it to the display
		encryptButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				addEncryptedText(Encryption.encrypt(inputField.getText(), "abcdAB!"));
				inputField.clear();
			}
			
		});
		//If back button is pressed, takes user to the main menu
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				MainMenu.mainMenu(primaryStage);
			}
			
		});
		//If ENTER key is pressed, encrypts input and adds it to the display
		encryptScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					addEncryptedText(Encryption.encrypt(inputField.getText(), key));
					inputField.clear();
				}
			}
			
		});
	}
	private static void addEncryptedText(String encryptedText) {
		//Creates a label of the new encrypted input
		Label encText = new Label(encryptedText);
		encText.getStyleClass().add("cipheredLabel");
		//Adds the encrypted text to the gridpane
		gp.add(encText, 0, counter);
		//Increases the counter by one in order to keep the next available slot updated
		counter++;
		//Adds the gridpane with all ecrypted text to the center 
		//of the borderpane in order to be displayed to the user
		bp.setCenter(gp);
	}
}
