package gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EncryptMenu {
	static BorderPane bp = new BorderPane();
	private EncryptMenu() {
		
	}
	public static void encryptMenu(Stage primaryStage) {
		Scene encryptScene = new Scene(bp, 800, 800);
		primaryStage.setScene(encryptScene);
		encryptScene.getWindow().centerOnScreen();
		primaryStage.setTitle("Encryption Menu");
	}
}
