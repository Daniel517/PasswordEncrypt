package gui;

import java.util.Optional;

import backend.Decryption;
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

public class DecryptMenu {
	//BorderPane to be used as main layout for scene
	static BorderPane bp = new BorderPane();
	//Scene for decryption menu
	static Scene decryptScene = new Scene(bp, 800, 800);
	//GridPane used to display decrypted text
	static GridPane gp = new GridPane();
	//Counter to keep track of next open vertical slot of gridpane
	static int counter = 0;
	//Key
	static String key = "RanDom3892GenEraTed191830Key";
	private DecryptMenu() {
		
	}
	public static void decryptMenu(Stage primaryStage) {
		//Set up properties for scene
		decryptScene.getStylesheets().add("application/application.css");
		primaryStage.setScene(decryptScene);
		decryptScene.getWindow().centerOnScreen();
		primaryStage.setTitle("Decryption Menu");
		//Calls method to get key from user
		getKey();
		//Calls method to set up I/O bar of menu
		setUpInputSection(primaryStage);
		//setUpOutputDisplay();
	}
	private static void getKey() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Key Input Menu");
		dialog.setHeaderText("Enter key: (Default used if left empty)");
		dialog.setContentText("Key: ");
		Optional<String> keyIn = dialog.showAndWait();
		if(keyIn.isPresent()) {
			key = keyIn.get();
		}
	}
	private static void setUpInputSection(Stage primaryStage) {
		//TextField for user input which will be decrypted
		TextField inputField = new TextField();
		inputField.setPromptText("Enter password to decrypt...");
		inputField.setPrefWidth(350);
		inputField.getStyleClass().add("inputLabel");
		//Button to allow the user to decrypt input
		Button decryptButton = new Button("Decrypt");
		decryptButton.getStyleClass().add("inputButton");
		//Button allows user to change encryption key
		Button changeKeyButton = new Button("Change Key");
		changeKeyButton.getStyleClass().add("inputButton");
		//Back button which will allow the user to go back to the main menu
		Button backButton = new Button("Back");
		backButton.getStyleClass().add("inputButton");
		//HBox containing all three elements of the I/O bar
		HBox inputSection = new HBox(inputField, decryptButton, backButton);
		inputSection.setSpacing(30);
		//Sets the HBox to the top of the BorderPane
		bp.setTop(inputSection);
		//Button allows user to encrypt entire file
		Button fileButton = new Button("Decrypt File");
		fileButton.getStyleClass().add("inputButton");
		HBox fileButtonBox = new HBox(fileButton);
		bp.setBottom(fileButtonBox);
		//If decrypt button is pressed, decrypts the input and add it to the display
		decryptButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				addDecryptedText(Decryption.decrypt(inputField.getText(), key));
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
				fileDecryptOption();
			}
			
		});
		//If ENTER key is pressed, decrypts input and adds it to the display
		decryptScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					addDecryptedText(Decryption.decrypt(inputField.getText(), key));
					inputField.clear();
				}
			}
			
		});
	}
	private static void addDecryptedText(String decryptedText) {
		//Creates a label of the new decrypted input
		Label decText = new Label(decryptedText);
		decText.getStyleClass().add("cipheredLabel");
		//Adds the decrypted text to the gridpane
		gp.add(decText, 0, counter);
		//Increases the counter by one in order to keep the next available slot updated
		counter++;
		//Adds the gridpane with all ecrypted text to the center 
		//of the borderpane in order to be displayed to the user
		bp.setCenter(gp);
	}
	/**
	 * Decrypts an entire file based on the current key
	 */
	private static void fileDecryptOption() {
		FileChooser fc = new FileChooser();
		Decryption.decryptFile(fc.showOpenDialog(null), key);
	}
}
