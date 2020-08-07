package bitchanger.gui.controls;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class BasicMenuBar extends MenuBar {
	
	public static final String MODUS_KEY = "modus-menu";
	public static final String OPTIONS_KEY = "options-menu";
	public static final String WINDOW_KEY = "window-menu";
	public static final String HELP_KEY = "help-menu";
	
	public final HashMap<String, Menu> menuMap;
	

	public BasicMenuBar() {
		super();
		
		menuMap = new HashMap<>();
		
		createMenuModus();
		createMenuOptions();
		createMenuWindow();
		createMenuAbout();
		
	}

	private void createMenuModus() {
		Menu modus = new Menu("Modus");
		addMenu(modus, MODUS_KEY);
		
		MenuItem viewConverter = new MenuItem("Umrechner");
		MenuItem viewIEEE = new MenuItem("IEEE");
		MenuItem viewCalculator = new MenuItem("Berechnungen");
		
		viewConverter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		modus.getItems().addAll(viewConverter, viewIEEE, viewCalculator);
	}
	
	private void createMenuOptions() {
		Menu options = new Menu("Optionen");
		addMenu(options, OPTIONS_KEY);
		
		Menu chooseComma = new Menu("Komma wählen");
		MenuItem chooseCommaDE = new MenuItem("deutsch");
		MenuItem chooseCommaEN = new MenuItem("englisch");
		
		chooseComma.getItems().addAll(chooseCommaDE, chooseCommaEN);
		
		options.getItems().addAll(chooseComma);
	}
	
	private void createMenuWindow() {
		Menu window = new Menu("Fenster");
		addMenu(window, MODUS_KEY);
		
		MenuItem newWindow = new MenuItem("Neues Fenster");
		Menu moveToScreen = new Menu("Auf Monitor bewegen");
		CheckMenuItem showFullscreen = new CheckMenuItem("Vollbild");
		
		window.getItems().addAll(newWindow, moveToScreen, showFullscreen);
	}
	
	private void createMenuAbout() {
		Menu help = new Menu("Hilfe");
		addMenu(help, HELP_KEY);
		
		MenuItem about = new MenuItem("Über");
		MenuItem version = new MenuItem("Version");
		
		help.getItems().addAll(about, version);
	}

	private void addMenu(Menu m, String key) {
		this.getMenus().add(m);
		menuMap.put(key, m);
	}
	
}
