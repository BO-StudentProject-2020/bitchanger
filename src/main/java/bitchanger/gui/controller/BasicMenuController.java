/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.InformationDialog;
import bitchanger.gui.controls.InformationDialog.InformationType;
import bitchanger.gui.views.Viewable;
import bitchanger.main.PrimaryFXApp;
import bitchanger.preferences.Preferences;
import bitchanger.preferences.Style;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Screen;
import javafx.stage.Stage;

//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.6
 * 
 */
public class BasicMenuController extends ControllerBase<BasicMenuBar> {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc
	protected MenuItem modusConverter;

	// TODO JavaDoc
	protected MenuItem modusIEEE;

	// TODO JavaDoc
	protected MenuItem modusCalculator;

	// TODO JavaDoc
	protected Menu options;

	// TODO JavaDoc
	protected Menu styleMenu;

	// TODO JavaDoc
	protected MenuItem styleLight;

	// TODO JavaDoc
	protected MenuItem styleDark;

	// TODO JavaDoc
	protected Menu moveToScreen;

	// TODO JavaDoc
	protected CheckMenuItem showFullscreen;

	// TODO JavaDoc
	protected MenuItem about;

	// TODO JavaDoc
	protected MenuItem version;

	// TODO JavaDoc
	protected MenuItem resetPreferences;

	// TODO JavaDoc
	private ControllableApplication application;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc
	public BasicMenuController(BasicMenuBar controllable, ControllableApplication application) {
		super(controllable);
		
		this.application = application;
	}

	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		this.modusConverter = this.controllable.getMenuItemMap().get(BasicMenuBar.MODUS_CONVERTER_ITEM_KEY);
		this.modusIEEE = this.controllable.getMenuItemMap().get(BasicMenuBar.MODUS_IEEE_ITEM_KEY);
		this.modusCalculator = this.controllable.getMenuItemMap().get(BasicMenuBar.MODUS_CALCULATOR_ITEM_KEY);
		this.options = (Menu) this.controllable.getMenuItemMap().get(BasicMenuBar.OPTIONS_MENU_KEY);
		this.styleMenu = (Menu) this.controllable.getMenuItemMap().get(BasicMenuBar.VIEW_STYLE_MENU_KEY);
		this.styleLight = this.controllable.getMenuItemMap().get(BasicMenuBar.VIEW_STYLE_LIGHT_ITEM_KEY);
		this.styleDark = this.controllable.getMenuItemMap().get(BasicMenuBar.VIEW_STYLE_DARK_ITEM_KEY);
		this.moveToScreen = (Menu) this.controllable.getMenuItemMap().get(BasicMenuBar.VIEW_MOVE_TO_SCREEN_ITEM_KEY);
		this.showFullscreen = (CheckMenuItem) this.controllable.getMenuItemMap().get(BasicMenuBar.VIEW_SHOW_FULLSCREEN_ITEM_KEY);
		this.about = this.controllable.getMenuItemMap().get(BasicMenuBar.HELP_ABOUT_ITEM_KEY);
		this.version = this.controllable.getMenuItemMap().get(BasicMenuBar.HELP_VERSION_ITEM_KEY);
		this.resetPreferences = this.controllable.getMenuItemMap().get(BasicMenuBar.HELP_RESET_PREFS_ITEM_KEY);
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	public void setActions() {
		// Menu Modus
		changeToViewAction(modusConverter, application.getViewable(PrimaryFXApp.CONVERTER_VIEW_KEY));
		changeToViewAction(modusIEEE, application.getViewable(PrimaryFXApp.IEEE_VIEW_KEY));
		changeToViewAction(modusCalculator, application.getViewable(PrimaryFXApp.CALCULATOR_VIEW_KEY));
		
		// Menu View
		changeStyleAction();
		switchFullscreenAction();
		setScreenItems();
		listenScreenConfig();
		
		// Menu Help
		showAboutAction();
		showVersionAction();
		resetPreferencesAction();
	}


	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Actions																														 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	// TODO JavaDoc
	private void changeToViewAction(MenuItem source, Viewable view) {
		source.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				application.changeView(view);
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void changeStyleAction() {
		styleLight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().setStylesheet(Style.LIGHT);
			}
		});
		
		styleDark.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().setStylesheet(Style.DARK);
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void switchFullscreenAction() {
		showFullscreen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				application.getPrimaryStage().setFullScreen(!application.getPrimaryStage().isFullScreen());
			}
		});
		
		// Vollbild überwachen und Selection setzen
		application.getPrimaryStage().fullScreenProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isFullscreen) {
				if(isFullscreen) {
					showFullscreen.setSelected(true);
				}
				else {
					showFullscreen.setSelected(false);
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void showAboutAction() {
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				InformationDialog dialog = new InformationDialog(InformationType.ABOUT);
				dialog.showAndWait();
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void showVersionAction() {
		version.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				InformationDialog dialog = new InformationDialog(InformationType.VERSION);
				dialog.showAndWait();
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void resetPreferencesAction() {
		this.resetPreferences.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.loadDefault();
			}
		});
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Bindings and Listeners																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	// TODO JavaDoc
	private void listenScreenConfig() {
		// Menü bei Änderung der Bildschirmkonfiguration anpassen
		Screen.getScreens().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				setScreenItems();
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	private void setScreenItems() {
		moveToScreen.getItems().clear();

		// Bildschirme auslesen
		int screenNum = 1;
		for (Screen screen : Screen.getScreens()) {
			MenuItem item = new MenuItem("Monitor " + screenNum);
			item.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// mittig auf Bildschirm positionieren
					Stage primaryStage = application.getPrimaryStage();

					if (primaryStage.isFullScreen()) {
						primaryStage.setX(screen.getBounds().getMinX() + (screen.getBounds().getWidth() / 2)
								- primaryStage.getWidth() / 2);
						primaryStage.setY(screen.getBounds().getMinY() + (screen.getBounds().getHeight() / 2)
								- primaryStage.getHeight() / 2);
					} else {
						primaryStage.setX(screen.getVisualBounds().getMinX() + (screen.getVisualBounds().getWidth() / 2)
								- primaryStage.getWidth() / 2);
						primaryStage.setY(screen.getVisualBounds().getMinY()
								+ (screen.getVisualBounds().getHeight() / 2) - primaryStage.getHeight() / 2);
					}
				}
			});

			moveToScreen.getItems().add(item);
			screenNum++;
		}
	}
}













