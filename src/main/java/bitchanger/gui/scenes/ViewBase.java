package bitchanger.gui.scenes;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public abstract class ViewBase implements Viewable, Controllable {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	protected Scene scene;
	private HashMap<String, TextField> tfMap;	// Hiermit koennen die entsprechenden TFs direkt gesucht werden -> hilfreich fuer Actions!
	private HashMap<String, Button> btnMap;
	
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ViewBase() {
		this.tfMap = new HashMap<String, TextField>();
		this.btnMap = new HashMap<String, Button>();
		
		init();
	}


	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public HashMap<String, TextField> getTextFields() {
		return tfMap;
	}

	@Override
	public HashMap<String, Button> getButtons() {
		return btnMap;
	}

	protected abstract void createRoot(BorderPane root);
	
	protected void init() {
		// Can be overwritten to initialize attributes
	}
	
}
