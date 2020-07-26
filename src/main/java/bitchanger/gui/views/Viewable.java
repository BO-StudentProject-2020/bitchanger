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

import bitchanger.gui.controller.Controllable;
import bitchanger.gui.controller.Controller;
import javafx.scene.Scene;

/**	<!-- $LANGUAGE=DE -->
 * Die Schnittstelle {@code Viewable} definiert die Basis für eine View.
 * <p>
 * Eine View ist in einem JavaFX-Fenster (javafx.stage.Stage) darstellbar. Dazu kapselt die View eine {@code Scene}, die
 * in einer Stage präsentiert werden kann. 
 * </p>
 * <p> <b> 
 * Der Scenegraph wird von der View konstruiert, dabei wird in der View nur das
 * Layout und keine Funktion festgelegt. 
 * </b> </p>
 * <p> <b>
 * Jeder View kann ein Controller zugeordnet werden, um den Bedienelementen im Scenegraph eine Funktion zu geben.
 * </b> </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.0
 * 
 * @see Controllable
 * @see Controller
 *
 */
public interface Viewable {
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die repräsentierte {@code Scene} zurück
	 * 
	 * @return von der View repräsentierte {@code Scene}
	 * 
	 */
	public Scene getScene();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die maximale Höhe der View an
	 * 
	 * @return maximale Höhe der View
	 * 
	 */
	public double getMaxHeigth();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die maximale Breite der View an
	 * 
	 * @return maximale Breite der View
	 * 
	 */
	public double getMaxWidth();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die minimale Höhe der View an
	 * 
	 * @return minimale Höhe der View
	 * 
	 */
	public double getMinHeigth();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die minimale Breite der View an
	 * 
	 * @return minimale Breite der View
	 * 
	 */
	public double getMinWidth();
	
}
