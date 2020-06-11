package bitchanger.gui;

import bitchanger.gui.views.ConverterView;
import bitchanger.gui.views.Viewable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Hauptfenster extends Application{
	
	private Scene currentScene;
	private Viewable currentView;
	private Viewable converterView;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		MenuBar menubar = createMenuBar();
		
		converterView = new ConverterView(menubar);
		currentView = converterView;
		currentScene = converterView.getScene();
		
		// TODO Testzeile entfernen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		currentScene.getStylesheets().setAll("Layout.css");
		
		primaryStage.setScene(currentScene);
		primaryStage.setTitle("Bitchanger 0.1.4");
		
		// Fenstergroesse an Scene anpassen und Maximale / Minimale Groesse einstellen (berechnet aus groesse der Scene und dem zusaetzlichen Fensterrahmen)
		primaryStage.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isShowing) {
				if(isShowing) {
					primaryStage.sizeToScene();
					primaryStage.setMinHeight(primaryStage.getHeight());
					primaryStage.setMinWidth(primaryStage.getWidth());
					primaryStage.setMaxHeight(currentView.getMaxHeigth() + primaryStage.getHeight() - currentView.getMinHeigth());
					primaryStage.setMaxWidth(currentView.getMaxWidth() + primaryStage.getWidth() - currentView.getMinWidth());
					
				}
			}
		});

		primaryStage.show();
	}

	private MenuBar createMenuBar() {
		MenuBar menubar = new MenuBar();
		
		// TODO MENUEBAR IMPLEMENTIEREN	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		
		return menubar;
	}

}
