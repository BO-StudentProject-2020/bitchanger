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
import java.util.Objects;
import bitchanger.gui.controller.BasicMenuController;
import bitchanger.gui.controller.Controllable;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
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
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das Menu "Modus" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String MODUS_MENU_KEY = "modus-menu";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das Menu "Optionen" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String OPTIONS_MENU_KEY = "options-menu";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das Menu "Fenster" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String WINDOW_MENU_KEY = "window-menu";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das Menu "Hilfe" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String HELP_MENU_KEY = "help-menu";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das MenuItem "Umrechner" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String MODUS_CONVERTER_ITEM_KEY = "modus-converter-item";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das MenuItem "IEEE" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String MODUS_IEEE_ITEM_KEY = "modus-ieee-item";
	
	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem das MenuItem "Berechnungen" in der Map {@link #menuItemMap} abgelegt ist
	 */
	public static final String MODUS_CALCULATOR_ITEM_KEY = "modus-calculator-item";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * {@code Map}, in die alle vom Controller benötigten MenuItems 
	 * mit einem eindeutigen Schlüssel abgelegt werden */
	public final HashMap<String, MenuItem> menuItemMap;

	
// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue funktionslose BasicMenuBar mit allen MenuItems.
	 */
	public BasicMenuBar() {
		super();
		
		this.menuItemMap = new HashMap<>();
		
		createMenuModus();
		createMenuOptions();
		createMenuWindow();
		createMenuHelp();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue BasicMenuBar mit allen MenuItems, die bereits mit den Basisfunktionen belegt sind.
	 * 
	 * @param controllableApp	Anwendungsklasse, mit der die Basisfunktionen verknüft werden
	 * 
	 * @throws NullPointerException	wenn der Parameter {@code controllableApp} null ist
	 */
	public BasicMenuBar(ControllableApplication controllableApp) throws NullPointerException {
		this();
		Objects.requireNonNull(controllableApp);
		Controller controller = new BasicMenuController(this, controllableApp);
		controller.setActions();
	}
	
	
// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** {@inheritDoc} */
	@Override
	public Map<String, TextField> getTextFieldMap() {
		return Controllable.EMPTY_TEXTFIELD_MAP;
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, Button> getButtonMap() {
		return Controllable.EMPTY_BUTTON_MAP;
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, Node> getNodeMap() {
		return Controllable.EMPTY_NODE_MAP;
	}

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Action des übergebenen MenuItems, sodass das spezifische Comma in den Einstellungen
	 * gesetzt wird.
	 * 
	 * @param chooseComma	MenuItem, das die Action erhält
	 * @param comma			spezifisches Comma, das gesetzt wird
	 */
	private void setChooseCommaAction(MenuItem chooseComma, Comma comma) {
		chooseComma.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().commaProperty.setValue(comma);
			}
		});
	}
	
	
// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Modus" mit den Elementen "Umrechner", "IEEE" und "Berechnungen"
	 */
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Optionen" mit den Elementen "Komma wählen" und den Unterelementen 
	 * "deutsch" und "englisch"
	 */
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

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Fenster" mit den Elementen "Auf Monitor bewegen" und "Vollbild"
	 */
	private void createMenuWindow() {
		Menu window = new Menu("Fenster");
		addMenu(window, MODUS_MENU_KEY);
		
		Menu moveToScreen = new Menu("Auf Monitor bewegen");
		CheckMenuItem showFullscreen = new CheckMenuItem("Vollbild");
		
		
		window.getItems().addAll(moveToScreen, showFullscreen);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Hilfe" mit den Elementen "Über" und "Version"
	 */
	private void createMenuHelp() {
		Menu help = new Menu("Hilfe");
		addMenu(help, HELP_MENU_KEY);
		
		MenuItem about = new MenuItem("Über");
		MenuItem version = new MenuItem("Version");
		
		help.getItems().addAll(about, version);
	}

	/** <!-- $LANGUAGE=DE -->
	 * Fügt das übergebene Menu zu dieser MenuBar hinzu und speichert dieses mit dem Schlüssel
	 * {@code key} in der Map {@link #menuItemMap}
	 * 
	 * @param m		Menu, das hinzugefügt und an die nächste Stelle gesetzt wird
	 * @param key	Schlüsselwort, mit dem {@code m} in der Map {@link #menuItemMap} abgelegt wird
	 */
	private void addMenu(Menu m, String key) {
		this.getMenus().add(m);
		menuItemMap.put(key, m);
	}

	
}
