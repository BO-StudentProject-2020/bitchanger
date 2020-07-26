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

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**	<!-- $LANGUAGE=DE -->
 * Basis für einen Controller, der einer View eine Funktion gibt. Die wichtigsten Attribute, die benötigt werden um Zugriff
 * auf die Bedienelemente der View zu erhalten, werden im Konstruktor initialisiert und sind in allen Subklassen sichtbar.
 * <p>
 * Subklassen müssen die Methoden {@link #initControls()} und {@link #setActions()} implementieren, um die Bedienelemente
 * mit einer Funktion zu belegen.
 * </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public abstract class ControllerBase {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	protected HashMap<String, TextField> textFieldMap;
	protected HashMap<String, Button> buttonMap;
	protected HashMap<String, Node> nodeMap;
	protected Controllable view;
	
	protected ControllerBase(Controllable view) {
		this.textFieldMap = view.getTextFieldMap();
		this.buttonMap = view.getButtonMap();
		this.nodeMap = view.getNodeMap();
		this.view = view;
		
		initControls();
	}
	
	protected abstract void initControls();

	public abstract void setActions();
	
}
