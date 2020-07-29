package bitchanger.gui.controller;

/**	<!-- $LANGUAGE=DE -->
 * Schnittstelle, die einen Controller beschreibt.
 * Ein Controller wird mit einer View verbunden und gibt den Bedienelementen der View eine Funktion.
 * <p>
 * Die Methode {@link #setActions()} muss implementiert werden, um die Bedienelemente
 * einer View mit einer Funktion zu belegen.
 * </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */

//TODO Interface that describes/writes on a controller. Welcher Sinn von beschreiben?

/*	<!-- $LANGUAGE=EN -->
 * Interface that describes/writes on a controller.
 * The controller is connected to a view and allocates functions to the operating elements.
 * <p>
 * This method {@link #setActions()} must be implemented to set actions for the operating elements of a view.
 * </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public interface Controller {
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle für die View benötigten Funktionen.
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Sets all functions that are needed for the view.
	 */
	public abstract void setActions();

}
