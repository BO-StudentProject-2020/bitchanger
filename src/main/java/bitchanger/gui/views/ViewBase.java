/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayList;
import java.util.HashMap;

import bitchanger.gui.controller.ControllerBase;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public abstract class ViewBase implements Viewable, Controllable {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	protected Scene scene;
	private HashMap<String, TextField> tfMap;	// Hiermit koennen die entsprechenden TFs direkt gesucht werden -> hilfreich fuer Actions!
	private HashMap<String, Button> btnMap;
	protected ArrayList<Node> buttonList;
	protected ControllerBase controller;
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ViewBase(MenuBar menu) {
		this.tfMap = new HashMap<String, TextField>();
		this.btnMap = new HashMap<String, Button>();
	}


	protected abstract void init();


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
	
//	@Override
//	public ArrayList<Node> getButtonList() {
//		return buttonList;
//	}
	
	protected void setController(ControllerBase controller) {
		this.controller = controller;
		controller.setControlls();
	}

	protected abstract void createRoot(BorderPane root);
	
}
