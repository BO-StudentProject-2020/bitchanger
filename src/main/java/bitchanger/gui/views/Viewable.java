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

/*	<!-- $LANGUAGE=EN -->
 * The interface {@code Viewable} defines the base for a view.
 * <p>
 * A view is representable in a JavaFX window (javafx.stage.Stage). The view encapsulate a {@code Scene},
 * which can be represented in a stage. 
 * </p>
 * <p> <b> 
 * The scene graph is constructed by a view, the view only sets the layout not the functions.
 * </b> </p>
 * <p> <b>
 * A controller can be allocated to each view, to set functions to the operating elements in the scene graph.
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
	
	/*	<!-- $LANGUAGE=EN -->
	 * Returns the represented {@code Scene}
	 * 
	 * @return from the view represented {@code Scene}
	 * 
	 */
	public Scene getScene();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die maximale Höhe der View an
	 * 
	 * @return maximale Höhe der View
	 * 
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Indicates the maximum height of the view
	 * 
	 * @return maximum height of the view
	 * 
	 */
	public double getMaxHeigth();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die maximale Breite der View an
	 * 
	 * @return maximale Breite der View
	 * 
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Indicates the maximum width of the view
	 * 
	 * @return maximum width of the view
	 * 
	 */
	public double getMaxWidth();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die minimale Höhe der View an
	 * 
	 * @return minimale Höhe der View
	 * 
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Indicates the minimum height of the view
	 * 
	 * @return minimum height of the view
	 * 
	 */
	public double getMinHeigth();
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die minimale Breite der View an
	 * 
	 * @return minimale Breite der View
	 * 
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Indicates the minimum width of the view
	 * 
	 * @return minimum width of the view
	 * 
	 */
	public double getMinWidth();
	
}
