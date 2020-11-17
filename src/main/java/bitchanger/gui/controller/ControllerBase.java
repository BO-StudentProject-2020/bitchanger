/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.util.Map;

import bitchanger.gui.views.Viewable;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**	<!-- $LANGUAGE=DE -->
 * Basis f\u00FCr einen Controller, der einer Instanz von Controllable (z.B. eine View) eine Funktion gibt. Die wichtigsten Attribute, 
 * die ben\u00F6tigt werden um Zugriff auf die Bedienelemente des Controllables zu erhalten, werden im Konstruktor initialisiert und sind in 
 * allen Subklassen sichtbar.
 * <p>
 * Subklassen m\u00FCssen die Methoden {@link #initControls()} und {@link #setActions()} implementieren, um die Bedienelemente
 * mit einer Funktion zu belegen.
 * </p>
 * <p>
 * Jedem Controller kann nur ein einziges Controllable zugewiesen werden. Umgekehrt ist es m\u00F6glich ein Controllable mit mehreren
 * Controllern f\u00FCr verschiedene Funktionen zu verbinden.
 * </p>
 * 
 * @param <T>	Typ des Controllable (wird ben\u00F6tigt, um auf weitere Methoden zugreifen zu k\u00F6nnen, die nicht in 
 * 				{@link Controllable} definiert sind)
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.7
 *
 */
//TODO JavaDoc EN
public abstract class ControllerBase<T extends Controllable> implements Controller {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->	{@code Map}, die alle Textfelder des gekapselten Controllables enth\u00E4lt */
	// TODO JavaDoc EN
	protected Map<String, TextField> textFieldMap;
	
	/** <!-- $LANGUAGE=DE -->	{@code Map}, die alle Buttons des gekapselten Controllables enth\u00E4lt */
	// TODO JavaDoc EN
	protected Map<String, Button> buttonMap;
	
	/** <!-- $LANGUAGE=DE -->
	 * {@code Map}, die alle Nodes des gekapselten Controllables enth\u00E4lt, die eine Funktion erhalten 
	 * und weder eine Instanz von Button noch von Textfeld sind */
	// TODO JavaDoc EN
	protected Map<String, Node> nodeMap;
	
	/** <!-- $LANGUAGE=DE -->	gekapseltes Controllable, dem durch diesen Controller eine Funktion gegeben wird */
	// TODO JavaDoc EN
	protected final T controllable;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/** <!-- $LANGUAGE=DE -->
	 * Kapselt das \u00FCbergebene Controllable und initialisiert die Maps mit den Bedienelementen mit Referenzen
	 * auf die zugeh\u00F6rigen Maps des Controllables.
	 * <p><b>
	 * Nach der Initialisierung der allgemeinen Attribute wird die Methode {@link #initControls()} aufgerufen.
	 * </b></p>
	 * 
	 * @param controllable	Controllable, das mit diesem Controller eine Funktion erh\u00E4lt
	 */
	// TODO JavaDoc EN
	protected ControllerBase(T controllable) {
		this.textFieldMap = controllable.getTextFieldMap();
		this.buttonMap = controllable.getButtonMap();
		this.nodeMap = controllable.getNodeMap();
		this.controllable = controllable;
		
		initControls();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/** <!-- $LANGUAGE=DE -->
	 * Initialisiert alle ben\u00F6tigten Bedienelemente mit Referenzen aus den zur Verf\u00FCgung stehenden Maps.
	 */
	// TODO JavaDoc EN
	protected abstract void initControls();
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/**  <!-- $LANGUAGE=DE -->
	 * Simuliert den Druck der Taste auf einer Tastatur mit dem spezifischen {@code keycode} und feuert nacheinander die KeyEvents 
	 * {@link KeyEvent#KEY_PRESSED}, {@link KeyEvent#KEY_TYPED} und {@link KeyEvent#KEY_RELEASED} an den gew\u00E4hlten Empf\u00E4nger 
	 * {@code target}. Wenn {@code target} den Wert {@code null} hat, werden die Events an die \u00FCbergebene Scene {@code scene} 
	 * weitergeleitet. Sind sowohl {@code target} als auch {@code scene} {@code null}, werden die Events an die Scene von 
	 * {@linkplain #controllable} weitergeleitet, sofern {@linkplain #controllable} die Schnittstelle {@link Viewable} implementiert.
	 * <p><b>
	 * Wenn die Parameter {@code target} und {@code scene} beide den Wert {@code null} haben und {@linkplain #controllable} nicht die Schnittstelle
	 * {@link Viewable} implementiert, werden keine Events gefeuert und die Methode hat keine weiteren Auswirkungen.
	 * Insbesondere wird auch keine Exception geworfen!
	 * </b></p>
	 * 
	 * @param source		Quelle des Events, darf {@code null} sein
	 * @param target		Ziel des Events, darf {@code null} sein, wenn das Attribut {@linkplain #controllable} eine Instanz von {@link Viewable} ist
	 * @param scene			Scene, die die KeyEvents konsumiert, wenn {@code target} den Wert {@code null} hat. Darf {@code null} sein
	 * @param character		Zeichen oder Zeichenkette, die mit dem Event verbunden wird
	 * @param text			String, der den KeyCode beschreibt
	 * @param keycode		KeyCode, der die Taste repr\u00E4sentiert
	 * @param shiftDown		true, wenn {@code "Shift"} gedr\u00FCckt ist
	 * @param controlDown	true, wenn {@code "Strg"} gedr\u00FCckt ist
	 * @param altDown		true, wenn {@code "Alt"} gedr\u00FCckt ist
	 * @param metaDown		true, wenn die {@code "Meta-"}Taste gedr\u00FCckt wurde (Command-Taste auf macOS bzw. Windows-Taste auf Windows)
	 * 
	 * @since Bitchanger 0.1.4
	 * 
	 * @see KeyEvent#KeyEvent(Object, javafx.event.EventTarget, javafx.event.EventType, String, String, KeyCode, boolean, boolean, boolean, boolean)
	 */
	// TODO JavaDoc EN @Moritz: DIE BESCHREIBUNG FÜR DIE PARAMETER KANNST DU AUS DEM DOC VOM KONSTRUKTOR VON KeyEvent KOPIEREN! (Du kannst ja auf die Verlinkung klicken ;) )
	protected void simulateKeyEvents(Button source, Node target, Scene scene, String character, String text, KeyCode keycode, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown) {
		KeyEvent pressed = new KeyEvent(source, target, KeyEvent.KEY_PRESSED, character, text, keycode, shiftDown, controlDown, altDown, metaDown);
		KeyEvent typed = new KeyEvent(source, target, KeyEvent.KEY_TYPED, character, text, keycode, shiftDown, controlDown, altDown, metaDown);
		KeyEvent released = new KeyEvent(source, target, KeyEvent.KEY_RELEASED, character, text, keycode, shiftDown, controlDown, altDown, metaDown);
		
		if(scene == null && controllable instanceof Viewable) {
			scene = ((Viewable) controllable).getScene();
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**  <!-- $LANGUAGE=DE -->
	 * Verh\u00E4lt sich wie {@link #simulateKeyEvents(Button, Node, Scene, String, String, KeyCode, boolean, boolean, boolean, boolean)},
	 * bis auf dass die Parameter shiftDown, controlDown, altDown und metaDown alle den Wert {@code false} haben.
	 *  
	 * @param source		Quelle des Events, darf {@code null} sein
	 * @param target		Ziel des Events, darf {@code null} sein, wenn das Attribut {@linkplain #controllable} eine Instanz von {@link Viewable} ist
	 * @param scene			Scene, die die KeyEvents konsumiert, wenn {@code target} den Wert {@code null} hat. Darf {@code null} sein
	 * @param character		Zeichen oder Zeichenkette, die mit dem Event verbunden wird
	 * @param text			String, der den KeyCode beschreibt
	 * @param keycode		KeyCode, der die Taste repr\u00E4sentiert
	 * 
	 * @see #simulateKeyEvents(Button, Node, Scene, String, String, KeyCode, boolean, boolean, boolean, boolean)
	 */
	// TODO JavaDoc EN
	protected void simulateKeyEvents(Button source, Node target, Scene scene, String character, String text, KeyCode keycode) {
		simulateKeyEvents(source, target, scene, character, text, keycode, false, false, false, false);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc -> Shortcuts @since Bitchanger 0.1.7
	protected void addAccelerator(Scene scene, Runnable runnable, EventType<KeyEvent> trigger, KeyCombination... keyCombinations) {
		for(KeyCombination kc : keyCombinations) {
			// TODO unterscheiden nach Funktionstasten und normalen Tasten
			scene.getAccelerators().put(kc, runnable);
			
			if(trigger == null) continue;
			
			scene.addEventHandler(trigger, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if(kc.match(event)) {
						runnable.run();
					}
				}
			});
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc -> Shortcuts @since Bitchanger 0.1.7
	protected void addAccelerator(Button button, EventType<KeyEvent> trigger, KeyCombination... keyCombinations) {
		addAccelerator(button.getScene(), button::fire, trigger, keyCombinations);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc -> Shortcuts @since Bitchanger 0.1.7
	protected void addAccelerator(Button button, KeyCombination... keyCombinations) {
		addAccelerator(button.getScene(), button::fire, KeyEvent.ANY, keyCombinations);
	}

}










