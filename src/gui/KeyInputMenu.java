package gui;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KeyInputMenu {
	static BorderPane bp = new BorderPane();
	static Stage secondStage;
	static Scene keyScene;
	private KeyInputMenu() {
		
	}
	public static String getKey() {
		secondStage = new Stage();
		keyScene = new Scene(bp, 400, 400);
		keyScene.getStylesheets().add("application/application.css");
		secondStage.setScene(keyScene);
		keyScene.getWindow().centerOnScreen();
		secondStage.show();
		String key = inputField();
		return key;
	}
	private static String inputField() {
		Label keyLabel = new Label("Enter key:");
		TextField keyField = new TextField();
		VBox keyMenu = new VBox(keyLabel, keyField);
		keyMenu.setSpacing(30);
		bp.setCenter(keyMenu);
		StringBuilder key = new StringBuilder();
		keyScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					key.append(keyField.getText());
					secondStage.close();
				}
				
			}
			
		});
		return key.toString();
	}
}
