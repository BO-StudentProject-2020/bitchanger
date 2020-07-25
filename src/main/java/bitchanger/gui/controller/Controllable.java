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

/** <!-- $LANGUAGE=DE -->
 * Schnittstelle, die Methoden definiert, um eine View mit einem Controller zu verbinden.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 * @see Viewable
 * @see ControllerBase
 *
 */

/* <!-- $LANGUAGE=EN -->
 * Interface that defines methods to connect a view with a controller.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 * @see Viewable
 * @see ControllerBase
 *
 */
public interface Controllable {
	
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
	 * Returns a {@code Map} with all text fields of the view. Each text field is allocated to a unique keyword,
	 * which is used to find the text field out of the map.
	 * <p>
	 * Each keyword should have a useful connection to the use of the text field!
	 * </p>
	 * 
	 * @return {@code Map}, that contains all text fields of the view
	 */
	public HashMap<String, TextField> getTextFieldMap();
	
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
	 * Returns a {@code Map} with all buttons of the view. Each button is allocated to a unique keyword,
	 * which is used to find the button out of the map.
	 * <p>
	 * Each Keyword should have a useful connection to the use of the button!
	 * </p>
	 * 
	 * @return {@code Map}, that contains all buttons of the view
	 */
	public HashMap<String, Button> getButtonMap();
	
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
	 * Returns a {@code Map} with all elements (Nodes) of the view, that are not buttons or text fields and are required in the controller.
	 * Each element is allocated to a unique keyword, which is used to find the element out of the map.
	 * <p>
	 * Each keyword should have a useful connection to the use of the elements!
	 * </p>
	 * 
	 * @return {@code Map}, which contains all elements of a view, that are not buttons or text fields and are required in the controller
	 */
	public HashMap<String, Node> getNodeMap();

}
