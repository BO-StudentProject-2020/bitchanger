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
public interface Controller {
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle für die View benötigten Funktionen
	 */
	public abstract void setActions();

}
