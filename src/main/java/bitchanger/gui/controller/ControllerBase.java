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

import bitchanger.gui.views.Viewable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
public abstract class ControllerBase<T extends Controllable> implements Controller {

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
	protected final T view;
	
	
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
	protected ControllerBase(T view) {
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
	
	
	/**  <!-- $LANGUAGE=DE -->
	 * Simuliert den Druck der Taste auf einer Tastatur mit dem spezifischen {@code keycode} und feuert nacheinander die KeyEvents 
	 * {@link KeyEvent#KEY_PRESSED}, {@link KeyEvent#KEY_TYPED} und {@link KeyEvent#KEY_RELEASED} an den gewählten Empfänger 
	 * {@code target}. Wenn {@code target} den Wert {@code null} hat, werden die Events an die übergebene Scene {@code scene} 
	 * weitergeleitet. Sind sowohl {@code target} als auch {@code scene} {@code null}, werden die Events an die Scene von 
	 * {@linkplain #view} weitergeleitet, sofern {@linkplain #view} die Schnittstelle {@link Viewable} implementiert.
	 * <p><b>
	 * Wenn die Parameter {@code target} und {@code scene} beide den Wert {@code null} haben und {@linkplain #view} nicht die Schnittstelle
	 * {@link Viewable} implementiert, werden keine Events gefeuert und die Methode hat keine weiteren Auswirkungen.
	 * Insbesondere wird auch keine Exception geworfen!
	 * </b></p>
	 * 
	 * @param source		Quelle des Events, darf {@code null} sein
	 * @param target		Ziel des Events, darf {@code null} sein, wenn das Attribut {@linkplain #view} eine Instanz von {@link Viewable} ist
	 * @param scene			Scene, die die KeyEvents konsumiert, wenn {@code target} den Wert {@code null} hat. Darf {@code null sein
	 * @param character		Zeichen oder Zeichenkette, die mit dem Event verbunden wird
	 * @param text			String, der den KeyCode beschreibt
	 * @param keycode		KeyCode, der die Taste repräsentiert
	 * @param shiftDown		true, wenn {@code "Shift"} gedrückt ist
	 * @param controlDown	true, wenn {@code "Strg"} gedrückt ist
	 * @param altDown		true, wenn {@code "Alt"} gedrückt ist
	 * @param metaDown		true, wenn die {@code "Meta-"}Taste gedrückt wurde (Command-Taste auf macOS bzw. Windows-Taste auf Windows)
	 * 
	 * @since Bitchanger 0.1.4
	 * 
	 * @see KeyEvent#KeyEvent(Object, javafx.event.EventTarget, javafx.event.EventType, String, String, KeyCode, boolean, boolean, boolean, boolean)
	 */
	/* <!-- $LANGUAGE=EN -->
	 * 
	 * @Moritz: DIE BESCHREIBUNG FÜR DIE PARAMETER KANNST DU AUS DEM DOC VOM KONSTRUKTOR VON KeyEvent KOPIEREN! (Du kannst ja auf die Verlinkung klicken ;) )
	 * 
	 */
	protected void simulateKeyEvents(Button source, Node target, Scene scene, String character, String text, KeyCode keycode, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown) {
		KeyEvent pressed = new KeyEvent(source, target, KeyEvent.KEY_PRESSED, character, text, keycode, shiftDown, controlDown, altDown, metaDown);
		KeyEvent typed = new KeyEvent(source, target, KeyEvent.KEY_TYPED, character, text, keycode, shiftDown, controlDown, altDown, metaDown);
		KeyEvent released = new KeyEvent(source, target, KeyEvent.KEY_RELEASED, character, text, keycode, shiftDown, controlDown, altDown, metaDown);
		
		if(scene == null && view instanceof Viewable) {
			scene = ((Viewable) view).getScene();
		}
		
		if (target != null){
			target.fireEvent(pressed);
			target.fireEvent(typed);
			target.fireEvent(released);
		}
		else if(scene != null) {
			scene.processKeyEvent(pressed);
			scene.processKeyEvent(typed);
			scene.processKeyEvent(released);
		}
		
	}
	
	/**  <!-- $LANGUAGE=DE -->
	 * Verhält sich wie {@link #simulateKeyEvents(Button, Node, Scene, String, String, KeyCode, boolean, boolean, boolean, boolean)},
	 * bis auf dass die Parameter shiftDown, controlDown, altDown und metaDown alle den Wert {@code false} haben.
	 * 
	 * @see #simulateKeyEvents(Button, Node, Scene, String, String, KeyCode, boolean, boolean, boolean, boolean)
	 */
	protected void simulateKeyEvents(Button source, Node target, Scene scene, String character, String text, KeyCode keycode) {
		simulateKeyEvents(source, target, scene, character, text, keycode, false, false, false, false);
	}

}
