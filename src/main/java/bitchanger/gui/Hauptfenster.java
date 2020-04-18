package bitchanger.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Hauptfenster extends Application{
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Scene sc = new Scene(root);
		primaryStage.setScene(sc);
		primaryStage.show();
	}

}
