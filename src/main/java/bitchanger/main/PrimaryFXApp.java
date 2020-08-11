/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.main;

import bitchanger.gui.controller.BasicMenuController;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.views.ConverterView;
import bitchanger.gui.views.IEEEView;
import bitchanger.gui.views.Viewable;
import bitchanger.preferences.Preferences;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** <!-- $LANGUAGE=DE -->
 * Hauptfenster der Applikation mit javaFX
 * <p>
 * Das Fenster bietet verschiedene Szenen zum Rechnen und Umwandeln von verschiedenen Zahlensystemen.
 * Über eine Menüleiste kann zwischen den Szenen gewechselt werden, sowie die Programm-Einstellungen geöffnet werden.
 * </p>
 * 
 * @author Tim Mühle
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
 * @author Tim Muehle
 *
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 * @see ConverterView
 * @see ConverterController
 */
public class PrimaryFXApp extends Application implements ControllableApplication {
	
// Konstanten	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem über {@link #getViewable(String)} auf die ConverterView zugegriffen werden kann 
	 */
	public static final String CONVERTER_VIEW_KEY = "converter-view";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem über {@link #getViewable(String)} auf die IEEEView zugegriffen werden kann 
	 */
	public static final String IEEE_VIEW_KEY = "ieee-view";

	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem über {@link #getViewable(String)} auf die CalculatorView zugegriffen werden kann 
	 */
	public static final String CALCULATOR_VIEW_KEY = "calculator-view";
	
	/** <!-- $LANGUAGE=DE -->
	 * Aktuelle Version des Bitchangers
	 */
	public static final String VERSION = "0.1.4";

	
	
// Launch-Methode	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

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
	public static void launchFXApplication(String[] args) {
		launch(args);
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE --> Property für die aktuell im Fenster dargestellte View */
	/* <!-- $LANGUAGE=EN --> Property of the currently displayed View */
	public final ObjectProperty<Viewable> currentViewProperty;
	
	/** <!-- $LANGUAGE=DE --> View für die Umwandlung von Zahlensystemen */
	/* <!-- $LANGUAGE=EN --> View for converting of numeral systems */
	private Viewable converterView;
	
	/** <!-- $LANGUAGE=DE --> View für die Umwandlung mit der IEEE-Norm */
	/* <!-- $LANGUAGE=EN --> View for conversion with the IEEE standard */
	private Viewable ieeeView;
	
	/** <!-- $LANGUAGE=DE --> View für das Rechnen mit Zahlensystemen */
	/* <!-- $LANGUAGE=EN --> View for calculating with number systems */
	private Viewable calculatorView;
	
	/** <!-- $LANGUAGE=DE --> Hauptfenster der Anwendung */
	/* <!-- $LANGUAGE=EN --> Main application window */
	private Stage primaryStage;
	
	
// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue PrimaryFXApp für den Bitchanger
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Construct a new PrimaryFXApp for the Bitchanger
	 */
	public PrimaryFXApp() {
		super();
		this.currentViewProperty = new SimpleObjectProperty<>();
	}

	
// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** {@inheritDoc} */
	@Override
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	
	/** {@inheritDoc} */
	@Override
	public Viewable getViewable(String key) {
		switch(key) {
		case CONVERTER_VIEW_KEY:
			return converterView;
		case IEEE_VIEW_KEY:
			return ieeeView;
		case CALCULATOR_VIEW_KEY:
			return calculatorView;
		default:
			return null;
		}
	}
	
	
	/** {@inheritDoc} */
	@Override
	public ObjectProperty<Viewable> getCurrentViewProperty() {
		return this.currentViewProperty;
	}

	
// Start	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Diese Methode erstellt den Inhalt für das PrimaryFXApp und öffnet dieses.
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
	 * <b> Diese Methode wird erst beendet, wenn das PrimaryFXApp geschlossen wurde </b>
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
		this.primaryStage = primaryStage;

		this.converterView = new ConverterView();
		this.ieeeView = new IEEEView();
		this.calculatorView = new IEEEView();
		
		adjustViews(converterView, ieeeView, calculatorView);
		
		currentViewProperty.set(converterView);
		
		changeView(converterView);
		primaryStage.setTitle("Bitchanger " + VERSION);
		
		// Fenstergroesse an Scene anpassen und Maximale / Minimale Groesse einstellen (berechnet aus groesse der Scene und dem zusaetzlichen Fensterrahmen)
		observeStageOnShowing();
		observeScene();
		
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
	private void setStageSize() {
		// Fenstergroesse an Scene anpassen und Maximale / Minimale Groesse einstellen
		// (berechnet aus groesse der Scene und dem zusaetzlichen Fensterrahmen)
		primaryStage.sizeToScene();
		primaryStage.setMinHeight(primaryStage.getHeight());
		primaryStage.setMinWidth(primaryStage.getWidth());
//		primaryStage.setMaxHeight(currentView.getMaxHeigth() + primaryStage.getHeight() - currentView.getMinHeigth());
//		primaryStage.setMaxWidth(currentView.getMaxWidth() + primaryStage.getWidth() - currentView.getMinWidth());
	}
	
	
	private void observeStageOnShowing() {
		primaryStage.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isShowing) {
				if (isShowing) {
					setStageSize();
				}
			}
		});
	}
	
	
	private void observeScene() {
		primaryStage.sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) {
				setStageSize();
			}
		});
	}



// Views	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	private void adjustViews(Viewable... views) {
		for(Viewable view : views) {
			BasicMenuBar menubar = new BasicMenuBar();
			Controller cntr = new BasicMenuController(menubar, this);
			cntr.setActions();
			
			view.setMenuBar(menubar);
			
			Preferences.getPrefs().readOnlyStylesheetProperty.addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldStylesheet, String newStylesheet) {
					try {
						view.getScene().getStylesheets().remove(oldStylesheet);
					} catch (Exception e) {
						// ignore
					}
					
					view.getScene().getStylesheets().add(newStylesheet);
				}
			});
			
			view.getScene().getStylesheets().add(Preferences.getPrefs().readOnlyStylesheetProperty.get());
		}
	}




}






