/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.views.Viewable;
import bitchanger.main.PrimaryFXApp;
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
		moveToScreen = (Menu) controllable.menuItemMap.get(BasicMenuBar.WINDOW_MOVE_TO_SCREEN_ITEM_KEY);
		showFullscreen = (CheckMenuItem) controllable.menuItemMap.get(BasicMenuBar.WINDOW_SHOW_FULLSCREEN_ITEM_KEY);
		about = controllable.menuItemMap.get(BasicMenuBar.HELP_ABOUT_ITEM_KEY);
		version = controllable.menuItemMap.get(BasicMenuBar.HELP_VERSION_ITEM_KEY);
	}
	
	
	/** {@inheritDoc} */
	@Override
	public void setActions() {
		changeToViewAction(modusConverter, application.getViewable(PrimaryFXApp.CONVERTER_VIEW_KEY));
		changeToViewAction(modusIEEE, application.getViewable(PrimaryFXApp.IEEE_VIEW_KEY));
		changeToViewAction(modusCalculator, application.getViewable(PrimaryFXApp.CALCULATOR_VIEW_KEY));
		
		switchFullscreenAction();
		setScreenItems();
		listenScreenConfig();
		
		showAboutAction();
	}


	private void changeToViewAction(MenuItem source, Viewable view) {
		source.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				application.changeView(view);
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
		int num = 1;
		for(Screen screen: Screen.getScreens()) {
			MenuItem item = new MenuItem("Monitor " + num);
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
			num++;
		}
	}
	

	private void showAboutAction() {
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Stage about = new Stage(StageStyle.UNDECORATED);
				Stage about = new Stage();
				
				// TODO: Über-Dialog erstellen
//				about.setScene();
				about.setAlwaysOnTop(true);
				about.show();
			}
		});
	}


}













