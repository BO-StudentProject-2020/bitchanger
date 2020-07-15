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
import bitchanger.gui.views.Controllable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public abstract class ControllerBase {

	protected HashMap<String, TextField> textFieldMap;
	protected HashMap<String, Button> buttonMap;
//	protected ArrayList<Node> allButtons;
	
	protected ControllerBase(Controllable view) {
		this.textFieldMap = view.getTextFields();
		this.buttonMap = view.getButtons();
//		this.allButtons = view.getButtonList();
		
//		this.allButtons = new ArrayList<Node>();
//		this.allButtons.addAll(this.buttonMap.values());
	}
	
	public abstract void setControlls();
	
}
