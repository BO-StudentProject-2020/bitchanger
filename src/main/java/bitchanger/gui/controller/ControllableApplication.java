package bitchanger.gui.controller;

import bitchanger.gui.views.Viewable;
import javafx.beans.property.ObjectProperty;
import javafx.stage.Stage;

/** <!-- $LANGUAGE=DE -->
 * Schnittstelle, die Methoden definiert, um eine Application mit einem Controller zu verbinden.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 * 
 * @see Controller
 */
/* <!-- $LANGUAGE=EN -->
 * Interface that defines methods to connect a Application with a controller.
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 * 
 * @see Viewable
 * @see Controller
 *
 */
public interface ControllableApplication {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	default methods																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Wechselt die Scene der primaryStage zu Scene des übergebenen Viewable
	 * 
	 * @param newView	Neue View, die dargestellt werden soll
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Changes the primaryStage's Scene to the Scene of the given Viewable
	 * 
	 * @param newView	New View to be presented
	 */
	public default void changeView(Viewable newView) {
		getCurrentViewProperty().set(newView);
		getPrimaryStage().setScene(newView.getScene());
	}

	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract methods																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Hauptfenster der Application zurück
	 * 
	 * @return Hauptfenster der Application
	 */
	/* <!-- $LANGUAGE=DE -->
	 * Returns the main application window
	 * 
	 * @return main application window
	 */
	public abstract Stage getPrimaryStage();
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die mit dem String {@code key} verknüpfte View zurück
	 * 
	 * @param key	Schlüsselwort für die View
	 * @return		View, die dem Schlüssel zugeordnet ist oder {@code null}, wenn keine passende View gefunden wird
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the view that is associated to the string {@code key}
	 * 
	 * @param key	key whose associated View is to be returned
	 * @return		View to which the specified key is mapped, or {@code null} if there is no associated View for the given key
	 */
	public abstract Viewable getViewable(String key);
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die CurrentViewProperty zurück
	 * 
	 * @return	CurrentViewProperty
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the CurrentViewProperty
	 * 
	 * @return	CurrentViewProperty
	 */
	public abstract ObjectProperty<Viewable> getCurrentViewProperty();
	
	
}
