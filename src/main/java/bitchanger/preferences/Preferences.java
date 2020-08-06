package bitchanger.preferences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/** <!-- $LANGUAGE=DE -->
 * Preferences ist die globale Sammlung für alle möglichen Einstellungen, die am Bitchanger vorgenommen 
 * werden können. Die Einstellungen können aus allen anderen Klassen abgefragt oder gesetzt werden.
 * 
 * <p>
 * Zudem gibt es Methoden, mit denen alle Einstellungen dauerhaft abgespeichert und wieder geladen werden können.
 * </p>
 * 
 * @author Tim Mühle
 *
 */
public class Preferences {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Property für das Kommazeichen */
	private static ObjectProperty<Comma> commaProperty = new SimpleObjectProperty<Comma>(Comma.COMMA_DE);
	
	/** <!-- $LANGUAGE=DE -->	Property für die Anzeige von abgebrochenen Nachkommastellen */
	private static BooleanProperty indicateFractionalPrecisionProperty = new SimpleBooleanProperty(true);
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Gibt das eingestellte Kommazeichen zurück
	 * 
	 * @return eingestelltes Kommazeichen
	 */
	public static char getComma() {
		return commaProperty.getValue().get();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die commaProperty zurück
	 * 
	 * @return	commaProperty
	 */
	public static ObjectProperty<Comma> getCommaProperty() {
		return commaProperty;
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Stellt das Kommazeichen bei der commaProperty ein
	 * 
	 * @param comma	Kommazeichen
	 */
	public static void setComma(Comma comma) {
		commaProperty.setValue(comma);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt den Wert der indicateFractionalPrecisionProperty zurück
	 * 
	 * @return	{@code true}, wenn die Anzeige von abgebrochenen Nachkommastellen angeschaltet ist, sonst {@code false}
	 */
	public static boolean indicateFractionalPrecision() {
		return indicateFractionalPrecisionProperty.getValue();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Schaltet die Anzeige von abgebrochenen Nachkommastellen ein und aus
	 * 
	 * @param b	{@code true} zum Einschalten oder {@code false} zum Ausschalten
	 */
	public static void setIndicateFractionalPrecision(boolean b) {
		indicateFractionalPrecisionProperty.setValue(b);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die indicateFractionalPrecisionProperty zurück
	 * 
	 * @return indicateFractionalPrecisionProperty
	 */
	public static BooleanProperty indicateFractionalPrecisionProperty() {
		return indicateFractionalPrecisionProperty;
	}
	
	// Speichern und Laden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Lädt alle Einstellungen aus der Einstellungsdatei
	 */
	public static void load() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Lädt die Standardeinstellungen aus der Einstellungsdatei
	 */
	public static void loadDefault() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Speichert alle Einstellungen in der Einstellungsdatei
	 */
	public static void store() {
		// TODO store changed Settings in File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private Preferences() {}
	
}







