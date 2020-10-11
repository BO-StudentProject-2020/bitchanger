/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
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
import bitchanger.util.FXUtils;
import bitchanger.util.IconFactory;
import bitchanger.util.Resources;
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
 * Basis Men\u00FCleiste, die die allgemein ben\u00F6tigten Men\u00FCpunkte enth\u00E4lt.
 * \u00DCber die MenuItems, die in Optionen -&gt; Komma einstellen vorhanden sind, wird
 * entsprechend das {@link Preferences#commaProperty} ge\u00E4ndert.
 * Weitere Funktionen k\u00F6nnen \u00FCber Controller hinzugef\u00FCgt werden.
 * 
 * @author Tim M\u00FChle
 *
 * @since Bitchanger 0.1.4
 * @version 0.1.7
 * 
 * @see BasicMenuController
 */
/*	<!-- $LANGUAGE=EN -->
 * Basic MenuBar containing the MenuItems that are generally required.
 * The {@link Preferences#commaProperty} is changed accordingly via the MenuItems, 
 * which are available in Options -&gt; Set Comma.
 * Further functions can be added via Controllers.
 * 
 * @author Tim M\u00FChle
 *
 * @since Bitchanger 0.1.4
 * @version 0.1.7
 * 
 * @see BasicMenuController
 */
public class BasicMenuBar extends MenuBar implements Controllable {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das Menu "Modus" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the Menu "Modus" is associated in the Map {@link #menuItemMap} */
	public static final String MODUS_MENU_KEY = "modus-menu";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das Menu "Optionen" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the Menu "Optionen" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_MENU_KEY = "options-menu";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das Menu "Fenster" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the Menu "Fenster" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_MENU_KEY = "view-menu";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das Menu "Hilfe" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the Menu "Hilfe" is associated in the Map {@link #menuItemMap} */
	public static final String HELP_MENU_KEY = "help-menu";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Umrechner" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Umrechner" is associated in the Map {@link #menuItemMap} */
	public static final String MODUS_CONVERTER_ITEM_KEY = "modus-converter-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "IEEE" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "IEEE" is associated in the Map {@link #menuItemMap} */
	public static final String MODUS_IEEE_ITEM_KEY = "modus-ieee-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Berechnungen" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Berechnungen" is associated in the Map {@link #menuItemMap} */
	public static final String MODUS_CALCULATOR_ITEM_KEY = "modus-calculator-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Bitoperationen" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Bitoperationen" is associated in the Map {@link #menuItemMap} */
	public static final String MODUS_BITOPERATIONS_ITEM_KEY = "modus-bitoperations-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das Menu "Stil" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the Menu "Stil" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_STYLE_MENU_KEY = "view-style-menu";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Hell" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Hell" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_STYLE_LIGHT_ITEM_KEY = "view-style-ligth-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Hell" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Hell" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_STYLE_COLOR_ITEM_KEY = "view-style-color-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Dunkel" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Dunkel" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_STYLE_DARK_ITEM_KEY = "view-style-dark-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Auf Monitor bewegen" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Auf Monitor bewegen" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_MOVE_TO_SCREEN_ITEM_KEY = "view-movetoscreen-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das CheckMenuItem "Vollbild" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the CheckMenuItem "Vollbild" is associated in the Map {@link #menuItemMap} */
	public static final String VIEW_SHOW_FULLSCREEN_ITEM_KEY = "view-showfullscreen-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "\u00DCber" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "\u00DCber" is associated in the Map {@link #menuItemMap} */
	public static final String HELP_ABOUT_ITEM_KEY = "help-about-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Version" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Version" is associated in the Map {@link #menuItemMap} */
	public static final String HELP_VERSION_ITEM_KEY = "help-version-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Einstellungen zur\u00FCcksetzen" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Einstellungen zur\u00FCcksetzen" is associated in the Map {@link #menuItemMap} */
	public static final String HELP_RESET_PREFS_ITEM_KEY = "help-reset-preferences-item";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(BasicMenuBar.class, BasicMenuController.class);
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
	

	/** <!-- $LANGUAGE=DE -->	{@code Map}, in der alle vom Controller ben\u00F6tigten MenuItems mit einem eindeutigen Schl\u00FCssel abgelegt werden */
	/* <!-- $LANGUAGE=EN -->	{@code Map} in which all MenuItems required by the controller are stored with a unique key */
	protected final HashMap<String, MenuItem> menuItemMap;

	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue BasicMenuBar mit allen MenuItems. Die einzige bereitgestellte Funktion ist
	 * die \u00C4nderung des CommaProperty in {@link Preferences#getPrefs()} zu englisch oder deutsch.
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Constructs a new BasicMenuBar with all MenuItems. The only function provided is changing the
	 * CommaProperty in {@link Preferences#getPrefs()} to English or German.
	 */
	public BasicMenuBar() {
		super();
		
		this.menuItemMap = new HashMap<>();
		this.minHeightProperty().bind(this.heightProperty());
		
		createMenuModus();
		createMenuOptions();
		createMenuView();
		createMenuHelp();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue BasicMenuBar mit allen MenuItems, die bereits mit den Basisfunktionen belegt sind.
	 * 
	 * @param controllableApp	Anwendungsklasse, mit der die Basisfunktionen verkn\u00FCpft werden
	 * 
	 * @throws NullPointerException	wenn der Parameter {@code controllableApp} null ist
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Constructs a new BasicMenuBar with all MenuItems that are already assigned the basic functions.
	 * 
	 * @param controllableApp	Application class with that the basic functions are associated
	 * 
	 * @throws NullPointerException	if the argument {@code controllableApp} is null
	 */
	public BasicMenuBar(ControllableApplication controllableApp) throws NullPointerException {
		this();
		Objects.requireNonNull(controllableApp);
		Controller controller = new BasicMenuController(this, controllableApp);
		controller.setActions();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** {@inheritDoc} */
	@Override
	public Map<String, TextField> getTextFieldMap() {
		return Controllable.EMPTY_TEXTFIELD_MAP;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public Map<String, Button> getButtonMap() {
		return Controllable.EMPTY_BUTTON_MAP;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public Map<String, Node> getNodeMap() {
		return Controllable.EMPTY_NODE_MAP;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt die {@code Map} zur\u00FCck, in der alle vom Controller ben\u00F6tigten MenuItems mit einem eindeutigen Schl\u00FCssel abgelegt werden
	 * 
	 * @return	{@code Map}, in der alle vom Controller ben\u00F6tigten MenuItems mit einem eindeutigen Schl\u00FCssel abgelegt werden
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the {@code Map} in which all MenuItems required by the controller are stored with a unique key
	 * 
	 * @return	{@code Map} in which all MenuItems required by the controller are stored with a unique key
	 */
	public final HashMap<String, MenuItem> getMenuItemMap(){
		return this.menuItemMap;
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Action des \u00FCbergebenen MenuItems, sodass das spezifische Comma in den Einstellungen
	 * gesetzt wird.
	 * 
	 * @param chooseComma	MenuItem, das die Action erh\u00E4lt
	 * @param comma			spezifisches Comma, das gesetzt wird
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets the action of the given MenuItem so that the specific Comma is set in the Preferences.
	 * 
	 * @param chooseComma	MenuItem receiving the action
	 * @param comma			specific Comma that is set in the Preferences
	 */
	private void setChooseCommaAction(MenuItem chooseComma, Comma comma) {
		chooseComma.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().commaProperty().setValue(comma);
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Modus" mit den Elementen "Umrechner", "IEEE" und "Berechnungen"
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates the Menu "Modus" with its Items "Umrechner", "IEEE" and "Berechnungen"
	 */
	private void createMenuModus() {
		Menu modus = new Menu("Modus");
		FXUtils.setIconOrText(modus, IconFactory.ofSVGFile(Resources.MENU_ICON));
		addMenu(modus, MODUS_MENU_KEY);
		
		MenuItem viewConverter = new MenuItem("Umrechner", IconFactory.ofSVGFile(Resources.SYNC_2_ICON));
		MenuItem viewIEEE = new MenuItem("IEEE", IconFactory.ofSVGFile(Resources.TEXTFIELD_ICON));
		MenuItem viewCalculator = new MenuItem("Berechnungen", IconFactory.styleBindIcon(Resources.CALCULATOR_SYMBOLS_ICON, Resources.CALCULATOR_SYMBOLS_FILLED_ICON));
		MenuItem viewBitoperations = new MenuItem("Bitoperationen", IconFactory.ofSVGFile(Resources.CODE_ICON));
		
		menuItemMap.put(MODUS_CONVERTER_ITEM_KEY, viewConverter);
		menuItemMap.put(MODUS_IEEE_ITEM_KEY, viewIEEE);
		menuItemMap.put(MODUS_CALCULATOR_ITEM_KEY, viewCalculator);
		menuItemMap.put(MODUS_BITOPERATIONS_ITEM_KEY, viewBitoperations);
		
		modus.getItems().addAll(viewConverter, viewIEEE, viewCalculator, viewBitoperations);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Optionen" mit den Elementen "Komma w\u00E4hlen" und den Unterelementen 
	 * "deutsch" und "englisch"
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates the Menu "Optionen" with its Items "Komma w\u00E4hlen" and the sub-elements
	 * "deutsch" and "englisch"
	 */
	private void createMenuOptions() {
		Menu options = new Menu("Optionen", IconFactory.ofSVGFile(Resources.PREFERENCES_GEAR_3_ICON));
		addMenu(options, OPTIONS_MENU_KEY);
		
		Menu chooseComma = new Menu("Komma w\u00E4hlen");
		MenuItem chooseCommaDE = new MenuItem("deutsch");
		MenuItem chooseCommaEN = new MenuItem("englisch");
		
		setChooseCommaAction(chooseCommaDE, Comma.COMMA_DE);
		setChooseCommaAction(chooseCommaEN, Comma.COMMA_EN);
		
		chooseComma.getItems().addAll(chooseCommaDE, chooseCommaEN);
		
		options.getItems().addAll(chooseComma);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Ansicht" mit den Elementen "Stil", "Auf Monitor bewegen" und "Vollbild"
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates the Menu "Ansicht" with its Items "Stil", "Auf Monitor bewegen" and "Vollbild"
	 */
	private void createMenuView() {
		Menu viewMenu = new Menu("Ansicht", IconFactory.ofSVGFile(Resources.WINDOW_THIN_2_ICON));
		addMenu(viewMenu, MODUS_MENU_KEY);
		
		Menu styleMenu = new Menu("Stil", IconFactory.ofSVGFile(Resources.PAINT_BRUSH_WINDOW_ICON));
		MenuItem styleLight = new MenuItem("Hell", IconFactory.ofSVGFile(Resources.SUN_ICON));
		MenuItem styleColor = new MenuItem("Color", IconFactory.ofSVGFile(Resources.PAINT_ICON));
		MenuItem styleDark = new MenuItem("Dunkel", IconFactory.ofSVGFile(Resources.SUN_FILLED_ICON));
		
		styleMenu.getItems().addAll(styleLight, styleColor, styleDark);
		
		menuItemMap.put(VIEW_STYLE_MENU_KEY, styleMenu);
		menuItemMap.put(VIEW_STYLE_LIGHT_ITEM_KEY, styleLight);
		menuItemMap.put(VIEW_STYLE_COLOR_ITEM_KEY, styleColor);
		menuItemMap.put(VIEW_STYLE_DARK_ITEM_KEY, styleDark);
		
		Menu moveToScreen = new Menu("Auf Monitor bewegen", IconFactory.styleBindIcon(Resources.MONITOR_ICON, Resources.MONITOR_FILLED_ICON));
		CheckMenuItem showFullscreen = new CheckMenuItem("Vollbild");
		
		menuItemMap.put(VIEW_MOVE_TO_SCREEN_ITEM_KEY, moveToScreen);
		menuItemMap.put(VIEW_SHOW_FULLSCREEN_ITEM_KEY, showFullscreen);
		
		viewMenu.getItems().addAll(styleMenu, moveToScreen, showFullscreen);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt das Menu "Hilfe" mit den Elementen "\u00DCber" und "Version"
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates the Menu "Hilfe" with its Items "\u00DCber" and "Version"
	 */
	private void createMenuHelp() {
		Menu help = new Menu("Hilfe", IconFactory.styleBindIcon(Resources.QUESTION_CIRCLE_ICON, Resources.QUESTION_CIRCLE_FILLED_ICON));
		addMenu(help, HELP_MENU_KEY);
		
		MenuItem about = new MenuItem("\u00DCber", IconFactory.styleBindIcon(Resources.QUESTION_SPEECH_BUBBLE_ICON, Resources.QUESTION_SPEECH_BUBBLE_FILLED_ICON));
		MenuItem version = new MenuItem("Version", IconFactory.styleBindIcon(Resources.INFO_CIRCLE_ICON, Resources.INFO_CIRCLE_FILLED_ICON));
		MenuItem resetPreferences = new MenuItem("Einstellungen zur\u00FCcksetzen", IconFactory.ofSVGFile(Resources.PREFERENCES_WRENCH_RESET_ICON));
		
		menuItemMap.put(HELP_ABOUT_ITEM_KEY, about);
		menuItemMap.put(HELP_VERSION_ITEM_KEY, version);
		menuItemMap.put(HELP_RESET_PREFS_ITEM_KEY, resetPreferences);
		
		help.getItems().addAll(about, version, resetPreferences);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * F\u00FCgt das \u00FCbergebene Menu zu dieser MenuBar hinzu und speichert dieses mit dem Schl\u00FCssel
	 * {@code key} in der Map {@link #menuItemMap}
	 * 
	 * @param m		Menu, das hinzugef\u00FCgt und an die n\u00E4chste Stelle gesetzt wird
	 * @param key	Schl\u00FCsselwort, mit dem {@code m} in der Map {@link #menuItemMap} abgelegt wird
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Adds the given Menu to this MenuBar and puts it in the Map {@link #menuItemMap}
	 * with the given key
	 * 
	 * @param m		Menu that is added
	 * @param key	key with which the Menu m is to be associated in the Map {@link #menuItemMap}
	 */
	private void addMenu(Menu m, String key) {
		this.getMenus().add(m);
		menuItemMap.put(key, m);
	}

	
}










