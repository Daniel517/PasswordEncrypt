package gui;

import java.util.Optional;

import backend.Encryption;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * GUI for encryption 
 * 
 * @author danielramirez
 *
 */
public class EncryptMenu {
	/**
	 * BorderPane to be used as main layout for scene
	 */
	static BorderPane bp = new BorderPane();
	/**
	 * Scene for encyption menu
	 */
	static Scene encryptScene = new Scene(bp, 800, 800);
	/**
	 * GridPane used to display encrpyted text
	 */
	static GridPane gp = new GridPane();
	/**
	 * Counter to keep track of next open vertical slot of gridpane
	 */
	static int counter = 0;
	/**
	 * Key used for encryption, default set
	 */
	static String key = "RanDom3892GenEraTed191830Key";
	/**
	 * Private constructor
	 */
	private EncryptMenu() {
		
	}
	/**
	 * Sets up the GUI for encryption
	 * 
	 * @param primaryStage Primary Stage
	 */
	public static void encryptMenu(Stage primaryStage) {
		//Set up properties for scene
		encryptScene.getStylesheets().add("application/application.css");
		primaryStage.setScene(encryptScene);
		encryptScene.getWindow().centerOnScreen();
		primaryStage.setTitle("Encryption Menu");
		//Calls method to ask user for key
		getKey();
		//Calls method to set up I/O bar of menu
		setUpInputSection(primaryStage);
		//setUpOutputDisplay();
	}
	/**
	 * Gets key from user to be used for encryption
	 */
	private static void getKey() {
		TextInputDialog keyDialog = new TextInputDialog();
		keyDialog.setTitle("Key Input Menu");
		keyDialog.setHeaderText("Enter key: (Default used if left empty)");
		keyDialog.setContentText("Key: ");
		Optional<String> keyIn = keyDialog.showAndWait();
		if(keyIn.isPresent() && keyIn.get() != "") {
			key = keyIn.get();
		}
	}
	/**
	 * Sets up all objects in the GUI including button, textfields, etc.
	 * 
	 * @param primaryStage Primary Stage
	 */
	private static void setUpInputSection(Stage primaryStage) {
		System.out.println(key);
		//TextField for user input which will be encrypted
		TextField inputField = new TextField();
		inputField.setPromptText("Enter password to encrypt...");
		inputField.setPrefWidth(350);
		inputField.getStyleClass().add("inputLabel");
		//Button to allow the user to encrypt input
		Button encryptButton = new Button("Encrypt");
		encryptButton.getStyleClass().add("inputButton");
		//Button allows user to change encryption key
		Button changeKeyButton = new Button("Change Key");
		changeKeyButton.getStyleClass().add("inputButton");
		//Back button which will allow the user to go back to the main menu
		Button backButton = new Button("Back");
		backButton.getStyleClass().add("inputButton");
		//HBox containing all three elements of the I/O bar
		HBox inputSection = new HBox(inputField, encryptButton, changeKeyButton, backButton);
		inputSection.setSpacing(30);
		//Sets the HBox to the top of the BorderPane
		bp.setTop(inputSection);
		//Button allows user to encrypt entire file
		Button fileButton = new Button("Encrypt File");
		fileButton.getStyleClass().add("inputButton");
		HBox fileButtonBox = new HBox(fileButton);
		bp.setBottom(fileButtonBox);
		//If encrypt button is pressed, encrypts the input and add it to the display
		encryptButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				addEncryptedText(Encryption.encrypt(inputField.getText(), key));
				inputField.clear();
			}
			
		});
		//If pressed allows user to update key
		changeKeyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				getKey();
			}
			
		});
		//If back button is pressed, takes user to the main menu
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				MainMenu.mainMenu(primaryStage);
			}
			
		});
		fileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				fileEncryptOption();
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
	/**
	 * Adds encrypted text into gridpane which is displyed to the user.
	 * 
	 * @param encryptedText Encrypted text to be displayed
	 */
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
	/**
	 * Encrypts an entire file based on the current key
	 */
	private static void fileEncryptOption() {
		FileChooser fc = new FileChooser();
		String regex = getRegex() + " ";
		Encryption.encryptFile(fc.showOpenDialog(null), key, regex);
	}
	private static String getRegex() {
		TextInputDialog regexDialog = new TextInputDialog();
		regexDialog.setTitle("Regex Input Menu");
		regexDialog.setHeaderText("Enter splitting special character: (Anything before splitting character will not encrypted)"
				+ "\nShould be of ascii value higher than 127 and a space should be placed after special character:");
		regexDialog.setContentText("Character: ");
		Optional<String> regexIn = regexDialog.showAndWait();
		if(regexIn.isPresent()) {
			return regexIn.get();
		}
		else {
			return "";
		}
	}
}
