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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** <!-- $LANGUAGE=DE -->
 * Schnittstelle, die Methoden definiert, um eine View mit einem Controller zu verbinden
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
	public HashMap<String, TextField> getTextFields();
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine {@code Map} mit allen Buttons der View zurück. Jeder Button ist einem eindeutigen Schlüsselwort zugeordnet,
	 * mit dem er aus der Map herausgesucht werden kann.
	 * <p>
	 * Jedes Schlüsselwort sollte einen Sinnvollen Bezug zum Einsatz des Buttons haben!
	 * </p>
	 * 
	 * @return {@code Map}, die alle Buttons einer View enthält
	 */
	public HashMap<String, Button> getButtons();
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine {@code Map} mit allen Elemente (Nodes) der View zurück, die keine Buttons oder Textfelder sind und im Controller benötigt werden.
	 * Jedes Element ist einem eindeutigen Schlüsselwort zugeordnet, mit dem es aus der Map herausgesucht werden kann.
	 * <p>
	 * Jedes Schlüsselwort sollte einen Sinnvollen Bezug zum Einsatz des Elements haben!
	 * </p>
	 * 
	 * @return {@code Map}, die alle Elemente einer View enthält, die keine Buttons oder Textfelder sind und im Controller benötigt werden
	 */
	public HashMap<String, Node> getNodes();

}
