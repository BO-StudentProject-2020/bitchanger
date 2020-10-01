/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import bitchanger.gui.controller.Controllable;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controls.BasicMenuBar;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;

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
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.7
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
 * @version 0.1.7
 * 
 * @see Controllable
 * @see Controller
 *
 */
public interface Viewable extends Controllable {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	default BasicMenuBar generateMenuBar() {
		return new BasicMenuBar();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	default BasicMenuBar generateMenuBar(ControllableApplication controllableApp) {
		try {
			return new BasicMenuBar(controllableApp);
		} catch (NullPointerException e) {
			return generateMenuBar();
		}
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
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
	public abstract Scene getScene();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die Property für maximale Höhe der View zurück
	 * 
	 * @return Property für die maximale Höhe der View
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Property for the maximum height of the view
	 * 
	 * @return Property for maximum height of the view
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public abstract DoubleProperty maxHeigthProperty();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die Property für maximale Breite der View zurück
	 * 
	 * @return Property für die maximale Breite der View
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Property for the maximum width of the view
	 * 
	 * @return Property for maximum width of the view
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public abstract DoubleProperty maxWidthProperty();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die Property für minimale Höhe der View zurück
	 * 
	 * @return Property für die minimale Höhe der View
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Property for the minimum height of the view
	 * 
	 * @return Property for minimum height of the view
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public abstract DoubleProperty minHeigthProperty();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die Property für minimale Breite der View zurück
	 * 
	 * @return Property für die minimale Breite der View
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Property for the minimum width of the view
	 * 
	 * @return Property for minimum width of the view
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public abstract DoubleProperty minWidthProperty();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die übergebene MenuBar in den Scenegraph der View. Eine eventuell vorhandene MenuBar
	 * wird zuvor aus dem Scenegraph entfernt und ersetzt. Ist das Argument {@code null} wird nur
	 * versucht die bereits vorhandene MenuBar aus dem Scenegraphen zu entfernen.
	 * 
	 * @param <T>		Typ der MenuBar zur Laufzeit
	 * @param menubar	MenuBar, die von dieser View verwendet werden soll. Darf {@code null} sein, um die aktuelle MenuBar zu entfernen
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Adds the given MenuBar to this View's scenegraph. Any existing MenuBar is removed from the 
	 * scenegraph and replaced. If the argument is {@code null} the only attempt is to remove the 
	 * already existing MenuBar from the scenegraph.
	 * 
	 * @param <T>		runtime type of the given MenuBar
	 * @param menubar	MenuBar to be used by this View, may be {@code null} to remove the current MenuBar
	 */
	public abstract <T extends MenuBar> void setMenuBar(T menubar);
	
}
