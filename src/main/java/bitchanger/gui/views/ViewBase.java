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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**	<!-- $LANGUAGE=DE -->
 * 
 * Basis-Klasse für alle View-Klassen, die das Interface {@code Viewable} implementieren und mit einem
 * Controller eine Funktion erhalten.
 * <p>
 * Die grundlegenden Methoden der Schnittstellen {@code Viewable} und {@code Controllable} sind bereits implementiert.
 * Die dazugehörigen Attribute sind mit protected geschützt, damit diese direkt in Unterklassen verwendet werden können.
 * </p>
 * <p>
 * Es werden abstrakte Methoden definiert, die zur Erstellung des Scenegraphens benötigt werden.
 * </p>
 * 
 * @param <T> Typ des Wurzelknotens im Scenegraphen
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
	
	/** <!-- $LANGUAGE=DE --> 
	 * {@code Map}, in die alle vom Controller benötigten Elemente der View mit einem 
	 * eindeutigen Schlüssel abgelegt werden, die keine Buttons oder Textfelder sind */
	private HashMap<String, Node> nodeMap;
	
	/** <!-- $LANGUAGE=DE --> Controller, der die Funktion zu den Bedienelementen hinzufügt */
	protected ControllerBase controller;
	
	
	
// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue View, die eine neue {@code Scene} mit dem übergebenen Wurzelknoten {@code root} kapselt.
	 * <p>
	 * Nach der Initialisierung der Attribute werden nacheinander die Methoden {@link #init()}, {@link #createScenegraph(Parent)} 
	 * und {@link #createController()} aufgerufen, um den Scenegraphen zu konstruieren und im Anschluss mit Hilfe eines Controllers
	 * die Funktion der Bedienelemente hinzuzufügen.
	 * </p>
	 * 
	 * @param root	Wurzelknoten des Scenegraphen
	 * 
	 * @see #init()
	 * @see #createScenegraph(Parent)
	 * @see #createController()
	 */
	public ViewBase(T root) {
		this.root = root;
		this.tfMap = new HashMap<String, TextField>();
		this.btnMap = new HashMap<String, Button>();
		this.nodeMap = new HashMap<String, Node>();
		this.scene = new Scene(this.root);
		
		init();
		createScenegraph(this.root);
		
		initController();
		
		if(controller != null) {
			controller.setControlls();
		}
	}

	
	
// Methoden		##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Initialisierungsmethode. 
	 * <b> Diese Methode wird als erstes vom Konstruktor aufgerufen, nachdem die Attribute initialisiert wurden. </b>
	 * <p>
	 * Unterklassen können diese Methode überschreiben, um Attribute vor der Konstruktion des Scenegraphen zu initialisieren.
	 * </p>
	 * <p>
	 * Die Implementierung der Methode in dieser Klasse hat keine Anweisungen.
	 * </p>
	 */
	protected void init() {
		// nothing to do
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Methode, die den Scenegraphen konstruiert. <b> Diese Methode wird aufgerufen, nachdem die init-Methode beendet wurde. </b>
	 * <p>
	 * In dieser Methode müssen alle Bedien- und Oberflächenelemente zum Scenegraphen hinzugefügt werden. Alle Elemente, die 
	 * auch im Controller benötigt werden, müssen zu der entsprechenden Map hinzugefügt werden: 
	 * {@link #btnMap}, {@link #tfMap}, {@link #nodeMap}
	 * </p>
	 * 
	 * @param root Wurzelknoten des Scenegraphen
	 */
	protected abstract void createScenegraph(T root);
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Methode, die einen neuen Controller für die View erzeugt und im Attribut {@link #controller} speichert.
	 * <b> Diese Methode wird vom Konstruktor aufgerufen, nachdem der Scenegraph konstruiert wurde. </b>
	 * 
	 * @return ein <b>neuer</b> Controller für diese View
	 * 
	 */
	protected abstract void initController();


// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** {@inheritDoc} */
	@Override
	public Scene getScene() {
		return scene;
	}

	/** {@inheritDoc} */
	@Override
	public HashMap<String, TextField> getTextFieldMap() {
		return tfMap;
	}

	/** {@inheritDoc} */
	@Override
	public HashMap<String, Button> getButtonMap() {
		return btnMap;
	}
	
	/** {@inheritDoc} */
	@Override
	public HashMap<String, Node> getNodeMap() {
		return nodeMap;
	}
	
}
