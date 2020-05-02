package bitchanger.gui;

import bitchanger.gui.elements.Representable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Hauptfenster extends Application{
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		MenuBar menubar = createMenuBar();
		
		Representable converter = new ConverterScene(menubar);
		Scene sc = converter.getScene();
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}

	private MenuBar createMenuBar() {
		MenuBar menubar = new MenuBar();
		return menubar;
	}

}
