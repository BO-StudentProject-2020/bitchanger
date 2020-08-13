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

public class BasicMenuController extends ControllerBase<BasicMenuBar> {
	
	protected MenuItem modusConverter;
	protected MenuItem modusIEEE;
	protected MenuItem modusCalculator;
	protected Menu options;
	protected Menu styleMenu;
	protected MenuItem styleLight;
	protected MenuItem styleDark;
	protected Menu moveToScreen;
	protected CheckMenuItem showFullscreen;
	protected MenuItem about;
	protected MenuItem version;
	private ControllableApplication application;
	
	public BasicMenuController(BasicMenuBar controllable, ControllableApplication application) {
		super(controllable);
		
		this.application = application;
	}

	
	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		modusConverter = controllable.menuItemMap.get(BasicMenuBar.MODUS_CONVERTER_ITEM_KEY);
		modusIEEE = controllable.menuItemMap.get(BasicMenuBar.MODUS_IEEE_ITEM_KEY);
		modusCalculator = controllable.menuItemMap.get(BasicMenuBar.MODUS_CALCULATOR_ITEM_KEY);
		options = (Menu) controllable.menuItemMap.get(BasicMenuBar.OPTIONS_MENU_KEY);
		styleMenu = (Menu) controllable.menuItemMap.get(BasicMenuBar.VIEW_STYLE_MENU_KEY);
		styleLight = controllable.menuItemMap.get(BasicMenuBar.VIEW_STYLE_LIGHT_ITEM_KEY);
		styleDark = controllable.menuItemMap.get(BasicMenuBar.VIEW_STYLE_DARK_ITEM_KEY);
		moveToScreen = (Menu) controllable.menuItemMap.get(BasicMenuBar.VIEW_MOVE_TO_SCREEN_ITEM_KEY);
		showFullscreen = (CheckMenuItem) controllable.menuItemMap.get(BasicMenuBar.VIEW_SHOW_FULLSCREEN_ITEM_KEY);
		about = controllable.menuItemMap.get(BasicMenuBar.HELP_ABOUT_ITEM_KEY);
		version = controllable.menuItemMap.get(BasicMenuBar.HELP_VERSION_ITEM_KEY);
	}
	
	
	/** {@inheritDoc} */
	@Override
	public void setActions() {
		changeToViewAction(modusConverter, application.getViewable(PrimaryFXApp.CONVERTER_VIEW_KEY));
		changeToViewAction(modusIEEE, application.getViewable(PrimaryFXApp.IEEE_VIEW_KEY));
		changeToViewAction(modusCalculator, application.getViewable(PrimaryFXApp.CALCULATOR_VIEW_KEY));
		
		changeStyleAction();
		switchFullscreenAction();
		setScreenItems();
		listenScreenConfig();
		
		showAboutAction();
		showVersionAction();
	}


	private void changeToViewAction(MenuItem source, Viewable view) {
		source.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				application.changeView(view);
			}
		});
	}
	
	
	private void changeStyleAction() {
		styleLight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().setStylesheet(Style.LIGTH);
			}
		});
		
		styleDark.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().setStylesheet(Style.DARK);
			}
		});
	}

	
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
	
	private void listenScreenConfig() {
		// Menü bei Änderung der Bildschirmkonfiguration anpassen
		Screen.getScreens().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable observable) {
				setScreenItems();		
			}
		});
	}

	private void setScreenItems() {
		moveToScreen.getItems().clear();
		
		// Bildschirme auslesen
		int screenNum = 1;
		for(Screen screen: Screen.getScreens()) {
			MenuItem item = new MenuItem("Monitor " + screenNum);
			item.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// mittig auf Bildschirm positionieren
					Stage primaryStage = application.getPrimaryStage();
					
					if(primaryStage.isFullScreen()) {
						primaryStage.setX(screen.getBounds().getMinX()+(screen.getBounds().getWidth()/2)-primaryStage.getWidth()/2);
						primaryStage.setY(screen.getBounds().getMinY()+(screen.getBounds().getHeight()/2)-primaryStage.getHeight()/2);
					} else {
						primaryStage.setX(screen.getVisualBounds().getMinX()+(screen.getVisualBounds().getWidth()/2)-primaryStage.getWidth()/2);
						primaryStage.setY(screen.getVisualBounds().getMinY()+(screen.getVisualBounds().getHeight()/2)-primaryStage.getHeight()/2);
					}					
				}
			});
				
			moveToScreen.getItems().add(item);
			screenNum++;
		}
	}
	

	private void showAboutAction() {
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				InformationDialog dialog = new InformationDialog(InformationType.ABOUT);
				dialog.showAndWait();
			}
		});
	}
	
	private void showVersionAction() {
		version.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				InformationDialog dialog = new InformationDialog(InformationType.VERSION);
				dialog.showAndWait();
			}
		});
	}


}













