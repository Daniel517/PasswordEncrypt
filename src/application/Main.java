package application;
	
import backend.Decryption;
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
		String enc1 = Encryption.encrypt("abc123", key);
		String enc2 = Encryption.encrypt("abc12345", key);
		String enc3 = Encryption.encrypt("abc123~!?>", key);
		System.out.println(enc1);
		System.out.println(enc2);
		System.out.println(enc3);
		System.out.println(Decryption.decrypt(enc1, key));
		System.out.println(Decryption.decrypt(enc2, key));
		System.out.println(Decryption.decrypt(enc3, key));
		System.exit(0);
	}
}
