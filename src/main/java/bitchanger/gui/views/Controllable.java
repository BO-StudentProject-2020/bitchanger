package bitchanger.gui.views;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** <!-- $LANGUAGE=DE -->
 * Schnittstelle, die Methoden definiert, um eine View mit einem Controller zu verbinden
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.0
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
	

}
