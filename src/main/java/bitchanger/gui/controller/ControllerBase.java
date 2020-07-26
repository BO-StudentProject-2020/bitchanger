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
 * <p>
 * Jedem Controller kann nur eine einzige View zugewiesen werden. Umgekehrt ist es möglich eine View mit mehreren Controllern zu verbinden.
 * </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public abstract class ControllerBase implements Controller {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	{@code Map}, die alle Textfelder der gekapselten View enthält */
	protected HashMap<String, TextField> textFieldMap;
	
	/** <!-- $LANGUAGE=DE -->	{@code Map}, die alle Buttons der gekapselten View enthält */
	protected HashMap<String, Button> buttonMap;
	
	/** <!-- $LANGUAGE=DE -->
	 * {@code Map}, die alle Nodes der gekapselten View enthält, die eine Funktion erhalten 
	 * und weder eine Instanz von Button noch von Textfeld sind */
	protected HashMap<String, Node> nodeMap;
	
	/** <!-- $LANGUAGE=DE -->	gekapselten View, der durch diesen Controller eine Funktion gegeben wird */
	protected final Controllable view;
	
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Kapselt die übergebene View und initialisiert die Maps mit den Bedienelementen mit Referenzen
	 * auf die zugehörigen Maps der View.
	 * <p><b>
	 * Nach der Initialisierung der allgemeinen Attribute wird die Methode {@link #initControls()} aufgerufen.
	 * </b></p>
	 * 
	 * @param view	View, die mit diesem Controller eine Funktion erhält
	 */
	protected ControllerBase(Controllable view) {
		this.textFieldMap = view.getTextFieldMap();
		this.buttonMap = view.getButtonMap();
		this.nodeMap = view.getNodeMap();
		this.view = view;
		
		initControls();
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Initialisiert alle benötigten Bedienelemente mit Referenzen aus den zur Verfügung stehenden Maps.
	 */
	protected abstract void initControls();

}
