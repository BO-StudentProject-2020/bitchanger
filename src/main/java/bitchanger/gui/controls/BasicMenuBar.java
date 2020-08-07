/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.HashMap;
import java.util.Map;

import bitchanger.gui.controller.BasicMenuController;
import bitchanger.gui.controller.Controllable;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**	<!-- $LANGUAGE=DE -->
 * Basis Menüleiste, die die allgemein benötigten Menüpunkte enthält.
 * Über die MenuItems, die in Optionen -> Komma einstellen vorhanden sind, wird
 * entsprechend das {@link Preferences#commaProperty} geändert.
 * Weitere Funktionen können über Controller hinzugefügt werden.
 * 
 * @author Tim Mühle
 *
 * @since Bitchanger 0.4.0
 * @version 0.1.4
 * 
 * @see BasicMenuController
 */
public class BasicMenuBar extends MenuBar implements Controllable {
	
	public static final String MODUS_MENU_KEY = "modus-menu";
	public static final String OPTIONS_MENU_KEY = "options-menu";
	public static final String WINDOW_MENU_KEY = "window-menu";
	public static final String HELP_MENU_KEY = "help-menu";
	public static final String MODUS_CONVERTER_ITEM_KEY = "modus-converter-item";
	public static final String MODUS_IEEE_ITEM_KEY = "modus-ieee-item";
	public static final String MODUS_CALCULATOR_ITEM_KEY = "modus-calculator-item";
	
	
	public final HashMap<String, MenuItem> menuItemMap;
	
	public BasicMenuBar() {
		super();
		
		this.menuItemMap = new HashMap<>();
		
		createMenuModus();
		createMenuOptions();
		createMenuWindow();
		createMenuAbout();
	}

	private void createMenuModus() {
		Menu modus = new Menu("Modus");
		addMenu(modus, MODUS_MENU_KEY);
		
		MenuItem viewConverter = new MenuItem("Umrechner");
		MenuItem viewIEEE = new MenuItem("IEEE");
		MenuItem viewCalculator = new MenuItem("Berechnungen");
		
		menuItemMap.put(MODUS_CONVERTER_ITEM_KEY, viewConverter);
		menuItemMap.put(MODUS_IEEE_ITEM_KEY, viewIEEE);
		menuItemMap.put(MODUS_CALCULATOR_ITEM_KEY, viewCalculator);
		
		modus.getItems().addAll(viewConverter, viewIEEE, viewCalculator);
	}
	
	private void createMenuOptions() {
		Menu options = new Menu("Optionen");
		addMenu(options, OPTIONS_MENU_KEY);
		
		Menu chooseComma = new Menu("Komma wählen");
		MenuItem chooseCommaDE = new MenuItem("deutsch");
		MenuItem chooseCommaEN = new MenuItem("englisch");
		
		setChooseCommaAction(chooseCommaDE, Comma.COMMA_DE);
		setChooseCommaAction(chooseCommaEN, Comma.COMMA_EN);
		
		chooseComma.getItems().addAll(chooseCommaDE, chooseCommaEN);
		
		options.getItems().addAll(chooseComma);
	}
	
	private void setChooseCommaAction(MenuItem chooseComma, Comma comma) {
		chooseComma.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().commaProperty.setValue(comma);
			}
		});
	}

	private void createMenuWindow() {
		Menu window = new Menu("Fenster");
		addMenu(window, MODUS_MENU_KEY);
		
		Menu moveToScreen = new Menu("Auf Monitor bewegen");
		CheckMenuItem showFullscreen = new CheckMenuItem("Vollbild");
		
		
		window.getItems().addAll(moveToScreen, showFullscreen);
	}
	
	private void createMenuAbout() {
		Menu help = new Menu("Hilfe");
		addMenu(help, HELP_MENU_KEY);
		
		MenuItem about = new MenuItem("Über");
		MenuItem version = new MenuItem("Version");
		
		help.getItems().addAll(about, version);
	}

	private void addMenu(Menu m, String key) {
		this.getMenus().add(m);
		menuItemMap.put(key, m);
	}

	@Override
	public Map<String, TextField> getTextFieldMap() {
		return Controllable.EMPTY_TEXTFIELD_MAP;
	}

	@Override
	public Map<String, Button> getButtonMap() {
		return Controllable.EMPTY_BUTTON_MAP;
	}

	@Override
	public Map<String, Node> getNodeMap() {
		return Controllable.EMPTY_NODE_MAP;
	}
	
}
