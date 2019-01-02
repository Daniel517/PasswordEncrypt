package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends BorderPane {
	public GUI(Stage primaryStage) {
		mainMenu(primaryStage);
	}
	public void mainMenu(Stage primaryStage) {
		Scene mainScene = new Scene(this, 500, 200);
		mainScene.getStylesheets().add("application/application.css");
		primaryStage.setScene(mainScene);
		Button encryptButton = new Button("Encrypt");
		encryptButton.getStyleClass().add("mainButton");
		Button decryptButton = new Button("Decrypt");
		decryptButton.getStyleClass().add("mainButton");
		HBox mainHBox = new HBox(encryptButton, decryptButton);
		Label mainLabel = new Label("Encrypt or Decrypt?");
		mainLabel.getStyleClass().add("mainLabel");
		VBox mainVBox = new VBox(mainLabel , mainHBox);
		setCenter(mainVBox);
	}
}
