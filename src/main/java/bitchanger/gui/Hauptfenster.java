package bitchanger.gui;

import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.views.ConverterView;
import bitchanger.gui.views.Viewable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

/* <!-- $LANGUAGE=EN -->
 * Main window of the application with javaFX
 * <p>
 * The window contains several scenes for calculations and converting of several numeral systems.
 * These scenes can be changed and program settings can be opened via the menu bar.
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
public class Hauptfenster extends Application {
	
// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE --> aktuell im Fenster dargestellte View */
	/* <!-- $LANGUAGE=EN --> View currently displayed in the window */
	private Viewable currentView;
	
	// mögliche Views mit Szenen für die verschiedenen Bedien-Oberflächen
	/** <!-- $LANGUAGE=DE --> View für die Umwandlung von Zahlensystemen */
	/* <!-- $LANGUAGE=EN --> View for converting of numeral systems */
	private Viewable converterView;
	
	
	
// Main-Methode	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Startet die Anwendung und öffnet das Applikationsfenster.
	 * 
	 * @param args	Argumente, die beim Programmstart übergeben werden. Die Argumente werden an die Methode {@code launch} weitergegeben und ansonsten ignoriert.
	 * 
	 * @see Application#launch(String...)
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Starts the applications and opens the application window.
	 * 
	 * @param args	Arguments that are committed at the beginning of the application. These arguments are committed to the method {@code launch} and otherwise they will be ignored.
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
	 * - Die verschiedenen Views für die unterschiedlichen Oberflächen werden erzeugt.
	 * </p>
	 * <p>
	 * - Die zuletzt geöffnete Szene wird geladen.
	 * </p>
	 * <p>
	 * - Das in den Einstellungen ausgewählte Stylesheet wird geladen.
	 * </p>
	 * <p>
	 * - Das Fenster wird formatiert und geöffnet.
	 * </p>
	 * 
	 * <b> Diese Methode wird erst beendet, wenn das Hauptfenster geschlossen wurde </b>
	 * 
	 * @see ConverterView
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * This method creates the content for the main window and opens it.
	 * <p>
	 * - A menu bar is created, which allows to control the main window in every scene.
	 * </p>
	 * <p>
	 * - Different views for the various surfaces are created.
	 * </p>
	 * <p>
	 * - The latest scene is loaded.
	 * </p>
	 * <p>
	 * - The in settings selected stylesheet is loaded.
	 * </p>
	 * <p>
	 * - The window is formatted and opened.
	 * </p>
	 * 
	 * <b> This method will end after the main window is closed. </b>
	 * 
	 * @see ConverterView
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {		
		MenuBar menubar = createMenuBar();
		
		converterView = new ConverterView(menubar);
		currentView = converterView;
		
		// TODO Testzeile entfernen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		currentView.getScene().getStylesheets().setAll("Layout.css");
		
		primaryStage.setScene(currentView.getScene());
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
	
	/* <!-- $LANGUAGE=EN -->
	 * Adjusts the minimum and maximum size of the window to the size of the current scene ({@code currentView}).
	 * 
	 * @param primaryStage	window which size can be adjusted
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
	
	/* <!-- $LANGUAGE=EN -->
	 * Creates a menu bar, that allows to select between different scenes, shows informations about this application
	 * and includes settings.
	 * <p>
	 * Each required element is added to a new {@code MenuBar}.
	 * Every action is set and does not need to be adjusted.
	 * </p>
	 * 
	 * @return	a new {@code MenuBar} with the required content
	 * 
	 */
	private MenuBar createMenuBar() {
		MenuBar menubar = new MenuBar();
		
		// TODO MENUEBAR IMPLEMENTIEREN	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		
		return menubar;
	}

}






