package bitchanger.gui;

import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.views.ConverterView;
import bitchanger.gui.views.Viewable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

/** <!-- $LANGUAGE=DE -->
 * Hauptfenster der Applikation mit javaFX
 * <p>
 * Das Fenster bietet verschiedene Szenen zum Rechnen und Umwandeln von verschiedenen Zahlensystemen.
 * Über eine Menüleiste kann zwischen den Szenen gewechselt werden, sowie die Programm-Einstellungen geöffnet werden.
 * </p>
 * 
 * @author Tim
 *
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 * @see ConverterView
 * @see ConverterController
 */
public class Hauptfenster extends Application{
	
// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE --> aktuell im Fenster dargestellte Szene */
	private Scene currentScene;
	
	/** <!-- $LANGUAGE=DE --> aktuelle View */
	private Viewable currentView;
	
	// mögliche Views mit Szenen für die verschiedenen Bedien-Oberflächen
	/** <!-- $LANGUAGE=DE --> View für die Umwandlung von Zahlensystemen */
	private Viewable converterView;
	
	
	
// Main-Methode	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Startet die Anwendung und öffnet das Applikationsfenster
	 * 
	 * @param args	Argumente, die beim Programmstart übergeben werden. Die Argumente werden an die Methode {@code launch} weitergegeben und ansonsten ignoriert.
	 * 
	 * @see Application#launch(String...)
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
// Start	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Diese Methode erstellt den Inhalt für das Hauptfenster und öffnet dieses.
	 * <p>
	 * - Es wird eine Menübar erstellt, die in jeder Szene die Steuerung des Fensters erlaubt.
	 * </p>
	 * <p>
	 * - Die verschiedenen Views für die unterschiedlichen Oberflächen werden erzeugt
	 * </p>
	 * <p>
	 * - Die zuletzt geöffnete Szene wird geladen
	 * </p>
	 * <p>
	 * - Das in den Einstellungen ausgewählte Stylesheet wird geladen
	 * </p>
	 * <p>
	 * - Das Fenster wird formatiert und geöffnet
	 * </p>
	 * 
	 * <b> Diese Methode wird erst beendet, wenn das Hauptfenster geschlossen wurde </b>
	 * 
	 * @see #createMenuBar()
	 * @see ConverterView
	 * @see #setStageSize(Stage)
	 * 
	 */
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
		setStageSize(primaryStage);
		
		primaryStage.show();
	}

	
	
// Layout	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Passt die minimale und die maximale Größe des Fensters an die Größe der aktuellen Szene ({@code currentView}) an
	 * 
	 * @param primaryStage	Fenster, dessen Größe eingestellt werden soll
	 * 
	 * @see Viewable#getMinHeigth()
	 * @see Viewable#getMaxHeigth()
	 * @see Viewable#getMinWidth()
	 * @see Viewable#getMaxWidth()
	 */
	private void setStageSize(Stage primaryStage) {
		// Fenstergroesse an Scene anpassen und Maximale / Minimale Groesse einstellen
		// (berechnet aus groesse der Scene und dem zusaetzlichen Fensterrahmen)
		primaryStage.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isShowing) {
				if (isShowing) {
					primaryStage.sizeToScene();
					primaryStage.setMinHeight(primaryStage.getHeight());
					primaryStage.setMinWidth(primaryStage.getWidth());
//					primaryStage.setMaxHeight(currentView.getMaxHeigth() + primaryStage.getHeight() - currentView.getMinHeigth());
//					primaryStage.setMaxWidth(currentView.getMaxWidth() + primaryStage.getWidth() - currentView.getMinWidth());
				}
			}
		});
	}



// Menü	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt eine Menübar, die die das Auswählen der verschiedenen Szenen erlaubt, Programm Einstellungen bietet und
	 * Informationen über die Applikation enthält.
	 * <p>
	 * Alle benötigten Elemente werden einer neuen {@code MenuBar} hinzugefügt.
	 * Alle Actions werden gesetzt und müssen nicht mehr angepasst werden.
	 * </p>
	 * 
	 * @return	eine neue {@code MenuBar} mit dem benötigten Inhalt
	 * 
	 */
	private MenuBar createMenuBar() {
		MenuBar menubar = new MenuBar();
		
		// TODO MENUEBAR IMPLEMENTIEREN	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		
		return menubar;
	}

}






