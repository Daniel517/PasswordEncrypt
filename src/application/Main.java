package application;
	
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
		launch(args);
//		String copy = "HEÆ LLO";
//		String[] pass = copy.split("Æ ");
//		System.out.println(pass[0]);
//		System.out.println(pass[1]);
//		System.exit(0);
	}
}
