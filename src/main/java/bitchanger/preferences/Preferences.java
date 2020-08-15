/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import bitchanger.util.Resources;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
 * @since Bitchanger 0.1.0
 * @version 0.1.4
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
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 */
public class Preferences {
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die alle aktuellen Einstellungen enthält */
	/* <!-- $LANGUAGE=EN -->	Constant that contains all the current settings */
	private static Preferences prefs = new Preferences();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die aktuellen Einstellungen zurück
	 * 
	 * @return	aktuelle Einstellungen
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the current settings
	 * 
	 * @return	current settings
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public static Preferences getPrefs() {
		return prefs;
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// public	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Property für das Kommazeichen */
	/* <!-- $LANGUAGE=EN -->	Property for comma character */
	public final ObjectProperty<Comma> commaProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property für die Anzeige von abgebrochenen Nachkommastellen */
	/* <!-- $LANGUAGE=EN -->	Property for displaying aborted decimal places */
	public final BooleanProperty indicateFractionalPrecisionProperty;
	
	/** <!-- $LANGUAGE=DE -->	ReadOnlyProperty für das gewählte Stylesheet */
	/* <!-- $LANGUAGE=EN -->	ReadOnlyProperty for the selected Stylesheet */
	public final ReadOnlyStringProperty readOnlyStylesheetProperty;
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Property für das gewählte Stylesheet */
	/* <!-- $LANGUAGE=EN -->	Property for the selected Stylesheet */
	private final StringProperty stylesheetProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property für den gewählten Style des Stylesheets */
	/* <!-- $LANGUAGE=EN -->	Property for the selected Style of the Stylesheet */
	private final StringProperty styleProperty;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt neue Preferences mit den Basiswerten. Diese Klasse ist in keiner anderen Klasse instanziierbar
	 * 
	 * @since Bitchanger 0.1.4
	 **/
	/* <!-- $LANGUAGE=EN -->
	 * Creates new preferences with the base values. Do not let anyone instantiate this class in any other class
	 * 
	 * @since Bitchanger 0.1.4
	 **/
	private Preferences() {
		this.commaProperty = new SimpleObjectProperty<Comma>(Comma.COMMA_DE);
		this.indicateFractionalPrecisionProperty = new SimpleBooleanProperty(true);
		this.stylesheetProperty = new SimpleStringProperty();
		this.readOnlyStylesheetProperty = stylesheetProperty;
		this.styleProperty = new SimpleStringProperty();
		
		setStylesheet(Style.LIGTH);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt das eingestellte Kommazeichen zurück
	 * 
	 * @return eingestelltes Kommazeichen
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the selected character for comma
	 * 
	 * @return selected character for comma
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public char getComma() {
		return commaProperty.getValue().get();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt den Wert der indicateFractionalPrecisionProperty zurück
	 * 
	 * @return	{@code true}, wenn die Anzeige von abgebrochenen Nachkommastellen angeschaltet ist, sonst {@code false}
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the value of indicateFractionalPrecisionProperty
	 * 
	 * @return	{@code true}, if the view of cancelled fractional parts is selected, if not {@code false}
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public boolean indicateFractionalPrecision() {
		return indicateFractionalPrecisionProperty.getValue();
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Schaltet die Anzeige von abgebrochenen Nachkommastellen ein und aus
	 * 
	 * @param b	{@code true} zum Einschalten oder {@code false} zum Ausschalten
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Turns the view of cancelled fractional parts on and off
	 * 
	 * @param b	{@code true} to turn on or {@code false} to turn off
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public void setIndicateFractionalPrecision(boolean b) {
		indicateFractionalPrecisionProperty.setValue(b);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Sucht die Ressource mit dem übergebenen Pfad und speichert den Speicherort der Resource in der {@link #stylesheetProperty}.
	 * 
	 * @param path	Pfad zum gewünschten Stylesheet
	 * 
	 * @return	{@code true}, wenn die Ressource gefunden und in der Property gespeichert wurde, sonst {@code false}
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=DE -->
	 * Searches for the resource with the given path and saves the location of the resource in the {@link #stylesheetProperty}.
	 * 
	 * @param path	Path to the Stylesheet
	 * 
	 * @return	{@code true} if the resource was found and saved in the property, {@code false} otherwise
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public boolean setStylesheet(String path) {
		try {
			String url = Resources.getResource(path);
			this.stylesheetProperty.set(url);
			this.styleProperty.set(url);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Speichert den Speicherort des Stylesheets mit dem vorgegebenen Style in der {@link #stylesheetProperty}.
	 * 
	 * @param style	Stil, den das Stylesheet haben soll
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=DE -->
	 * Saves the location of the Stylesheet with the given Style in the {@link #stylesheetProperty}.
	 * 
	 * @param style	Style of the Stylesheet to be set
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public void setStylesheet(Style style) {
		switch(style) {
		case LIGTH:
			this.stylesheetProperty.set(Resources.LIGHT_CSS);
			break;
		case DARK:
			this.stylesheetProperty.set(Resources.DARK_CSS);
			break;
		default:
			return;
		}
		
		this.styleProperty.set(style.name());
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	other methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Lädt alle Einstellungen aus der Einstellungsdatei
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads all settings from the settings data
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public void load() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Lädt die Standardeinstellungen aus der Einstellungsdatei
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads the default settings from the settings data
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public void loadDefault() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Speichert alle Einstellungen in der Einstellungsdatei
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Stores all preferences into the settings data
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public void store() {
		// TODO store changed Settings in File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			// XML-Root
			Element xmlRoot = doc.createElement("bitchanger");
			doc.appendChild(xmlRoot);
			
			// Preferences
			Element prefs = doc.createElement("preferences");
			xmlRoot.appendChild(prefs);
			
			// commaProperty
			Element commaPropertyTag = doc.createElement("comma");
			commaPropertyTag.appendChild(doc.createTextNode(this.commaProperty.get().name()));
			prefs.appendChild(commaPropertyTag);
			
			// indicateFractionalPrecisionProperty
			Element indicateFractionalPrecisionPropertyTag = doc.createElement("indicateFractionalPrecision");
			indicateFractionalPrecisionPropertyTag.appendChild(doc.createTextNode(String.valueOf(this.indicateFractionalPrecisionProperty.get())));
			prefs.appendChild(indicateFractionalPrecisionPropertyTag);
			
			// styleProperty
			Element stylePropertyTag = doc.createElement("style");
			stylePropertyTag.appendChild(doc.createTextNode(this.styleProperty.get()));
			prefs.appendChild(stylePropertyTag);
			
			// in Datei Schreiben
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			
			File file = new File(Resources.DEFAULT_PREFERENCES);
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}







