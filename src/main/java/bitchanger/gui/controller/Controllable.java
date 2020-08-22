/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.util.Collections;
import java.util.Map;

import bitchanger.gui.views.Viewable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** <!-- $LANGUAGE=DE -->
 * Schnittstelle, die Methoden definiert, um eine View mit einem Controller zu verbinden.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 * @see Viewable
 * @see Controller
 *
 */
/* <!-- $LANGUAGE=EN -->
 * Interface that defines methods to connect a controllable with a controller.
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 * @see Viewable
 * @see Controller
 *
 */
public interface Controllable {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public CONSTANTS   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Leere und unveränderliche Map, die als Platzhalter verwendet werden kann, wenn ein Controllable keine 
	 * Textfelder an seinen Controller übergeben muss
	 * 
	 * @see Collections#emptyMap()
	 */
	/* <!-- $LANGUAGE=EN -->
	 * empty and immutable map that can be used as placeholder if any Controllable does not have to pass textfields to its Controller
	 * 
	 * @see Collections#emptyMap()
	 */
	public static final Map<String, TextField> EMPTY_TEXTFIELD_MAP = Collections.emptyMap();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Leere und unveränderliche Map, die als Platzhalter verwendet werden kann, wenn ein Controllable keine 
	 * Buttons an seinen Controller übergeben muss
	 * 
	 * @see Collections#emptyMap()
	 */
	/* <!-- $LANGUAGE=EN -->
	 * empty and immutable map that can be used as placeholder if any Controllable does not have to pass buttons to its Controller
	 * 
	 * @see Collections#emptyMap()
	 */
	public static final Map<String, Button> EMPTY_BUTTON_MAP = Collections.emptyMap();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Leere und unveränderliche Map, die als Platzhalter verwendet werden kann, wenn ein Controllable keine 
	 * Nodes an seinen Controller übergeben muss
	 * 
	 * @see Collections#emptyMap()
	 */
	/* <!-- $LANGUAGE=EN -->
	 * empty and immutable map that can be used as placeholder if any Controllable does not have to pass nodes to its Controller
	 * 
	 * @see Collections#emptyMap()
	 */
	public static final Map<String, Node> EMPTY_NODE_MAP = Collections.emptyMap();
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine {@code Map} mit allen Textfelder der View zurück. Jedes Textfeld ist einem eindeutigen Schlüsselwort zugeordnet,
	 * mit dem es aus der Map herausgesucht werden kann.
	 * <p>
	 * Jedes Schlüsselwort sollte einen Sinnvollen Bezug zum Einsatz des Textfeldes haben!
	 * </p>
	 * 
	 * @return {@code Map}, die alle Textfelder einer View enthält
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns a {@code Map} with all text fields of the controllable. Each text field is allocated to a unique keyword,
	 * which is used to find the text field out of the map.
	 * <p>
	 * Each keyword should have a useful connection to the use of the text field!
	 * </p>
	 * 
	 * @return {@code Map}, that contains all text fields of the controllable
	 */
	public abstract Map<String, TextField> getTextFieldMap();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine {@code Map} mit allen Buttons der View zurück. Jeder Button ist einem eindeutigen Schlüsselwort zugeordnet,
	 * mit dem er aus der Map herausgesucht werden kann.
	 * <p>
	 * Jedes Schlüsselwort sollte einen Sinnvollen Bezug zum Einsatz des Buttons haben!
	 * </p>
	 * 
	 * @return {@code Map}, die alle Buttons einer View enthält
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns a {@code Map} with all buttons of the controllable. Each button is allocated to a unique keyword,
	 * which is used to find the button out of the map.
	 * <p>
	 * Each Keyword should have a useful connection to the use of the button!
	 * </p>
	 * 
	 * @return {@code Map}, that contains all buttons of the controllable
	 */
	public abstract Map<String, Button> getButtonMap();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine {@code Map} mit allen Elemente (Nodes) der View zurück, die keine Buttons oder Textfelder sind und im Controller benötigt werden.
	 * Jedes Element ist einem eindeutigen Schlüsselwort zugeordnet, mit dem es aus der Map herausgesucht werden kann.
	 * <p>
	 * Jedes Schlüsselwort sollte einen Sinnvollen Bezug zum Einsatz des Elements haben!
	 * </p>
	 * 
	 * @return {@code Map}, die alle Elemente einer View enthält, die keine Buttons oder Textfelder sind und im Controller benötigt werden
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns a {@code Map} with all elements (Nodes) of the controllable, that are not buttons or text fields and are required in the controller.
	 * Each element is allocated to a unique keyword, which is used to find the element out of the map.
	 * <p>
	 * Each keyword should have a useful connection to the use of the elements!
	 * </p>
	 * 
	 * @return {@code Map}, which contains all elements of a controllable, that are not buttons or text fields and are required in the controller
	 */
	public abstract Map<String, Node> getNodeMap();

}
