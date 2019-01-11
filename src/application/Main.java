package application;
	
import backend.Encryption;
import gui.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MainMenu.mainMenu(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		String key = "abc";
		System.out.println(Encryption.encrypt("abc123~>Z", key));
		System.out.println(Encryption.encrypt("abc123", key));
		System.out.println(Encryption.encrypt("abc123", key));
		System.exit(0);
	}
}
