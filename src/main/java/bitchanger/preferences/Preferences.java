package bitchanger.preferences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/** <!-- $LANGUAGE=DE -->
 * Preferences ist die globale Sammlung für alle möglichen Einstellungen, die am Bitchanger vorgenommen 
 * werden können. Die Einstellungen können über die Methode {@link #getPrefs()} aus allen anderen Klassen
 * abgefragt und geändert werden.
 * 
 * <p>
 * Zudem gibt es Methoden, mit denen alle Einstellungen dauerhaft abgespeichert und wieder geladen werden können.
 * </p>
 * 
 * @author Tim Mühle
 *
 */

/* <!-- $LANGUAGE=EN -->
 * Preferences is the global collection for all possible settings that can be selected for the bitchanger.
 * These settings can be requested and changed from all classes by using the method {@link #getPrefs()}.
 * 
 * <p>
 * Furthermore there are methods to store all settings permanently and load these at a later moment.
 * </p>
 * 
 * @author Tim Mühle
 *
 */
public class Preferences {
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die alle aktuellen Einstellungen enthält */
	/* <!-- $LANGUAGE=EN -->	Constant that contains all the current settings */
	private static Preferences prefs = new Preferences();
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die aktuellen Einstellungen zurück
	 * 
	 * @return	aktuelle Einstellungen
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Returns the current settings
	 * 
	 * @return	current settings
	 */
	public static Preferences getPrefs() {
		return prefs;
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Property für das Kommazeichen */
	/* <!-- $LANGUAGE=EN -->	Property for comma character */
	public final ObjectProperty<Comma> commaProperty = new SimpleObjectProperty<Comma>(Comma.COMMA_DE);
	
	/** <!-- $LANGUAGE=DE -->	Property für die Anzeige von abgebrochenen Nachkommastellen */
	/* <!-- $LANGUAGE=EN -->	Property for displaying aborted decimal places */
	public final BooleanProperty indicateFractionalPrecisionProperty = new SimpleBooleanProperty(true);
	
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist in keiner anderen Klasse instanziierbar **/
	/* <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class in any other class **/
	private Preferences() {
		load();	// Letzte Einstellungen aus Datei laden
	}
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Gibt das eingestellte Kommazeichen zurück
	 * 
	 * @return eingestelltes Kommazeichen
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Returns the selected character for comma
	 * 
	 * @return selected character for comma
	 */
	public char getComma() {
		return commaProperty.getValue().get();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt den Wert der indicateFractionalPrecisionProperty zurück
	 * 
	 * @return	{@code true}, wenn die Anzeige von abgebrochenen Nachkommastellen angeschaltet ist, sonst {@code false}
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Returns the value of indicateFractionalPrecisionProperty
	 * 
	 * @return	{@code true}, if the view of cancelled fractional parts is selected, if not {@code false}
	 */
	public boolean indicateFractionalPrecision() {
		return indicateFractionalPrecisionProperty.getValue();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Schaltet die Anzeige von abgebrochenen Nachkommastellen ein und aus
	 * 
	 * @param b	{@code true} zum Einschalten oder {@code false} zum Ausschalten
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Turns the view of cancelled fractional parts on and off
	 * 
	 * @param b	{@code true} to turn on or {@code false} to turn off
	 */
	public void setIndicateFractionalPrecision(boolean b) {
		indicateFractionalPrecisionProperty.setValue(b);
	}
	
	
	// Speichern und Laden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Lädt alle Einstellungen aus der Einstellungsdatei
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Loads all settings from the settings data
	 */
	public void load() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Lädt die Standardeinstellungen aus der Einstellungsdatei
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Loads the default settings from the settings data
	 */
	public void loadDefault() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Speichert alle Einstellungen in der Einstellungsdatei
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Stores all settings into the settings data
	 */
	public void store() {
		// TODO store changed Settings in File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}

	
}







