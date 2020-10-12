/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.main;

import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.views.BitoperationView;
import bitchanger.gui.views.CalculatorView;
import bitchanger.gui.views.ConverterView;
import bitchanger.gui.views.IEEEView;
import bitchanger.gui.views.Viewable;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Preferences;
import bitchanger.util.ArrayUtils;
import bitchanger.util.Resources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/** <!-- $LANGUAGE=DE -->
 * Hauptfenster der Applikation mit javaFX
 * <p>
 * Das Fenster bietet verschiedene Szenen zum Rechnen und Umwandeln von verschiedenen Zahlensystemen.
 * \u00DCber eine Men\u00FCleiste kann zwischen den Szenen gewechselt werden, sowie die Programm-Einstellungen ge\u00F6ffnet werden.
 * </p>
 * 
 * @author Tim M\u00FChle
 *
 * @since Bitchanger 0.1.0
 * @version 0.1.8
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
 * @version 0.1.9
 * 
 * @see ConverterView
 * @see ConverterController
 */
public class PrimaryFXApp extends Application implements ControllableApplication {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constants		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem \u00FCber {@link #getViewable(String)} auf die ConverterView zugegriffen werden kann */
	// TODO JavaDoc EN
	public static final String CONVERTER_VIEW_KEY = "converter-view";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem \u00FCber {@link #getViewable(String)} auf die IEEEView zugegriffen werden kann */
	// TODO JavaDoc EN
	public static final String IEEE_VIEW_KEY = "ieee-view";

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem \u00FCber {@link #getViewable(String)} auf die CalculatorView zugegriffen werden kann */
	// TODO JavaDoc EN
	public static final String CALCULATOR_VIEW_KEY = "calculator-view";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem \u00FCber {@link #getViewable(String)} auf die CalculatorView zugegriffen werden kann */
	// TODO JavaDoc EN
	public static final String BITOPERATIONS_VIEW_KEY = "bitoperations-view";
	
	/** <!-- $LANGUAGE=DE -->	Aktuelle Version des Bitchangers */
	// TODO JavaDoc EN
	public static final String VERSION = "0.1.9";

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	static Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Startet die Anwendung und \u00F6ffnet das Applikationsfenster.
	 * 
	 * @param args	Argumente, die beim Programmstart \u00FCbergeben werden. Die Argumente werden an die Methode {@code launch} weitergegeben und ansonsten ignoriert.
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

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Property f\u00FCr die aktuell im Fenster dargestellte View */
	/* <!-- $LANGUAGE=EN --> Property of the currently displayed View */
	private final ObjectProperty<Viewable> currentViewProperty;
	
	/** <!-- $LANGUAGE=DE --> View f\u00FCr die Umwandlung von Zahlensystemen */
	/* <!-- $LANGUAGE=EN --> View for converting of numeral systems */
	private Viewable converterView;
	
	/** <!-- $LANGUAGE=DE --> View f\u00FCr die Umwandlung mit der IEEE-Norm */
	/* <!-- $LANGUAGE=EN --> View for conversion with the IEEE standard */
	private Viewable ieeeView;
	
	/** <!-- $LANGUAGE=DE --> View f\u00FCr das Rechnen mit Zahlensystemen */
	/* <!-- $LANGUAGE=EN --> View for calculating with number systems */
	private Viewable calculatorView;
	
	/** <!-- $LANGUAGE=DE --> View f\u00FCr Bitoperationen mit Zahlensystemen */
	/* <!-- $LANGUAGE=EN --> View for bitwise operations with number systems */
	private Viewable bitoperationsView;
	
	/** <!-- $LANGUAGE=DE --> Hauptfenster der Anwendung */
	/* <!-- $LANGUAGE=EN --> Main application window */
	private Stage primaryStage;
	
	/** <!-- $LANGUAGE=DE --> H\u00F6he des Fensterrahmens */
	/* <!-- $LANGUAGE=EN --> Height of the window frame */
	private double emptyStageHeigth;
	
	/** <!-- $LANGUAGE=DE --> Breite des Fensterrahmens */
	/* <!-- $LANGUAGE=EN --> Width of the window frame */
	private double emptyStageWidth;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->	Erzeugt eine neue PrimaryFXApp f\u00FCr den Bitchanger */
	/* <!-- $LANGUAGE=EN -->	Construct a new PrimaryFXApp for the Bitchanger */
	public PrimaryFXApp() {
		super();
		this.currentViewProperty = new SimpleObjectProperty<Viewable>();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** {@inheritDoc} */
	@Override
	public Stage getPrimaryStage() {
		return primaryStage;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

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
		case BITOPERATIONS_VIEW_KEY:
			return bitoperationsView;
		default:
			return null;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public ObjectProperty<Viewable> getCurrentViewProperty() {
		return this.currentViewProperty;
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// Start	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Diese Methode erstellt den Inhalt f\u00FCr das PrimaryFXApp und \u00F6ffnet dieses.
	 * <p>
	 * - Es wird eine Men\u00FCbar erstellt, die in jeder Szene die Steuerung des Fensters erlaubt.
	 * </p>
	 * <p>
	 * - Die verschiedenen Views f\u00FCr die unterschiedlichen Oberfl\u00E4chen werden erzeugt.
	 * </p>
	 * <p>
	 * - Die zuletzt ge\u00F6ffnete Szene wird geladen.
	 * </p>
	 * <p>
	 * - Das in den Einstellungen ausgew\u00E4hlte Stylesheet wird geladen.
	 * </p>
	 * <p>
	 * - Das Fenster wird formatiert und ge\u00F6ffnet.
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
		primaryStage.setFullScreenExitHint("Dr\u00FCcken Sie F11, um den Vollbildmodus zu beenden.");
		
		computeStageFrameSize();

		this.converterView = new ConverterView();
		this.ieeeView = new IEEEView();
		this.calculatorView = new CalculatorView();
		this.bitoperationsView = new BitoperationView();
		
		adjustViews(converterView, ieeeView, calculatorView, bitoperationsView);

		for(Viewable view : ArrayUtils.arrayOf(converterView, ieeeView, calculatorView, bitoperationsView)) {
			if(Preferences.getPrefs().viewClassProperty().get().equals(view.getClass())) {
				changeView(view);
			}
		}
		
		updateViewClassProperty();
		
		
		primaryStage.setTitle("Bitchanger " + VERSION);
		primaryStage.getIcons().add(new Image(Resources.BITCHANGER_LOGO_PNG.toURI().toURL().toExternalForm()));
		
		// Fenstergroesse an Scene anpassen und Maximale / Minimale Groesse einstellen (berechnet aus groesse der Scene und dem zusaetzlichen Fensterrahmen)
		observeStageOnShowing();
		observeScene();
		
		primaryStage.show();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc 0.1.7
	private void computeStageFrameSize() {
		Stage s = new Stage(StageStyle.DECORATED);
		
		Region r = new Region();
		r.setMaxSize(0, 0);
		r.setPrefSize(0, 0);
		
		Scene sc = new Scene(r, 1, 1);
		
		s.setScene(sc);
		s.setOpacity(0);
		s.setMaxHeight(0);
		s.setMaxWidth(0);
		
		s.setOnShown(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				emptyStageHeigth = s.getHeight();
				emptyStageWidth = s.getWidth();
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						s.close();
					}
				});
			}
		});
		
		s.show();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateViewClassProperty() {
		this.currentViewProperty.addListener(new ChangeListener<Viewable>() {
			@Override
			public void changed(ObservableValue<? extends Viewable> observable, Viewable oldValue, Viewable newView) {
				Preferences.getPrefs().viewClassProperty().set(newView.getClass());
			}
		});
	}
	
	
// Layout	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Passt die minimale und die maximale Gr\u00F6\u00DFe des Fensters an die Gr\u00F6\u00DFe der aktuellen Szene ({@code currentView}) an
	 * 
	 * @see Viewable#getMinHeigth()
	 * @see Viewable#getMaxHeigth()
	 * @see Viewable#getMinWidth()
	 * @see Viewable#getMaxWidth()
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Adjusts the minimum and maximum size of the window to the size of the current scene ({@code currentView}).
	 * 
	 * @see Viewable#getMinHeigth()
	 * @see Viewable#getMaxHeigth()
	 * @see Viewable#getMinWidth()
	 * @see Viewable#getMaxWidth()
	 */
	private void setStageSize() {
		// Fenstergroesse an Scene anpassen und Maximale / Minimale Groesse einstellen
		// (berechnet aus groesse der Scene und dem zusaetzlichen Fensterrahmen)
		primaryStage.minHeightProperty().bind(currentViewProperty.get().minHeigthProperty().add(emptyStageHeigth));
		primaryStage.maxHeightProperty().bind(currentViewProperty.get().maxHeigthProperty().add(emptyStageHeigth));
		primaryStage.minWidthProperty().bind(currentViewProperty.get().minWidthProperty().add(emptyStageWidth));
		primaryStage.maxWidthProperty().bind(currentViewProperty.get().maxWidthProperty().add(emptyStageWidth));
		
		primaryStage.sizeToScene();
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void observeScene() {
		primaryStage.sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) {
				setStageSize();
			}
		});
	}



// Views	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	// TODO JavaDoc
	private void adjustViews(Viewable... views) {
		for(Viewable view : views) {
			BasicMenuBar menubar = view.generateMenuBar(this);
			view.setMenuBar(menubar);
			
			Preferences.getPrefs().stylesheetProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldStylesheet, String newStylesheet) {
					try {
						view.getScene().getStylesheets().remove(oldStylesheet);
					} catch (Exception e) {
						// ignore
						BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
					}
					
					view.getScene().getStylesheets().add(newStylesheet);
				}
			});
			
			view.getScene().getStylesheets().add(Resources.LAYOUT_CSS);
			view.getScene().getStylesheets().add(Preferences.getPrefs().stylesheetProperty().get());
		}
	}




}






