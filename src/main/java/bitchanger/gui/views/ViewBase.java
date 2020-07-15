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

import java.util.HashMap;

import bitchanger.gui.controller.ControllerBase;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**	<!-- $LANGUAGE=DE -->
 * 
 * Basisklasse für alle View-Klassen, die das Interface {@code Viewable} implementieren und mit einem
 * Controller eine Funktion erhalten.
 * <p>
 * Die grundlegenden Methoden der Schnittstellen {@code Viewable} und {@code Controllable} sind bereits implementiert.
 * Die dazugehörigen Attribute sind protected geschützt, damit diese direkt in Unterklassen verwendet werden können.
 * </p>
 * <p>
 * Es werden abstrakte Methoden definiert, die zur Erstellung des Scenegraphens benötigt werden.
 * </p>
 * 
 * @param <T>	Typ des Wurzelknotens im Scenegraphen
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public abstract class ViewBase<T extends Parent> implements Viewable, Controllable {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE --> Szene, die von der View repräsentiert wird */
	protected Scene scene;
	
	/** <!-- $LANGUAGE=DE --> Wurzelknoten im Scenegraph */
	protected T root;
	
	/** <!-- $LANGUAGE=DE -->
	 * {@code Map}, in die alle vom Controller benötigten Textfelder der View 
	 * mit einem eindeutigen Schlüssel abgelegt werden */
	private HashMap<String, TextField> tfMap;	// Hiermit koennen die entsprechenden TFs direkt gesucht werden -> hilfreich fuer Actions!
	
	/** <!-- $LANGUAGE=DE --> 
	 * {@code Map}, in die alle vom Controller benötigten Buttons der View mit 
	 * einem eindeutigen Schlüssel abgelegt werden */
	private HashMap<String, Button> btnMap;
	
	/** <!-- $LANGUAGE=DE --> Controller, der die Funktion zu den Bedienelementen hinzufügt */
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
	
}
