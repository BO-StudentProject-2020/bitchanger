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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**	<!-- $LANGUAGE=DE -->
 * 
 * Basisklasse für alle View-Klassen, die das Interface {@code Viewable} implementieren und mit einem
 * Controller eine Funktion erhalten.
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public abstract class ViewBase<T extends Parent> implements Viewable, Controllable {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	protected Scene scene;
	protected T root;
	private HashMap<String, TextField> tfMap;	// Hiermit koennen die entsprechenden TFs direkt gesucht werden -> hilfreich fuer Actions!
	private HashMap<String, Button> btnMap;
	protected ArrayList<Node> buttonList;
	protected ControllerBase controller;
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ViewBase(T root) {
		this.root = root;
		this.tfMap = new HashMap<String, TextField>();
		this.btnMap = new HashMap<String, Button>();
		init();
		
		this.scene = new Scene(this.root);
		createRoot(this.root);
		
		this.controller = createController();
		
		if(controller != null) {
			controller.setControlls();
		}
	}

	protected void init() {
		// nothing to do
	}
	
	protected abstract void createRoot(T root);
	
	protected abstract ControllerBase createController();


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
	
}
