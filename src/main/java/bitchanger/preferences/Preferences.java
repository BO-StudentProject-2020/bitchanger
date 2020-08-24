/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bitchanger.util.Resources;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

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
 * @version 0.1.5
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
 * @version 0.1.5
 */
public class Preferences {
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die alle aktuellen Einstellungen enthält */
	/* <!-- $LANGUAGE=EN -->	Constant that contains all the current settings */
	private static Preferences prefs = new Preferences(Resources.CUSTOM_PREFERENCES);
	
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Speichert die aktuellen Einstellungen in der Datei {@link Resources#CUSTOM_PREFERENCES}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Stores the current settings in the File {@link Resources#CUSTOM_PREFERENCES}
	 */
	public static void storeCustom() {
		prefs.store(Resources.CUSTOM_PREFERENCES);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Lädt die letzten Einstellungen aus der Datei {@link Resources#CUSTOM_PREFERENCES}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads the last settings from the file {@link Resources#CUSTOM_PREFERENCES}
	 */
	public static void loadCustom() {
		try {
			prefs.load(Resources.customPreferencesStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Lädt die Standardeinstellungen aus der Datei {@link Resources#DEFAULT_PREFERENCES}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads the default settings from the file {@link Resources#DEFAULT_PREFERENCES}
	 */
	public static void loadDefault() {
		try {
			prefs.load(Resources.defaultPreferencesStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	/** <!-- $LANGUAGE=DE -->	ReadOnlyProperty für das gewählte Stylesheet */
	/* <!-- $LANGUAGE=EN -->	ReadOnlyProperty for the selected Stylesheet */
	public final ReadOnlyObjectProperty<Style> readOnlyStyleProperty;
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Property für das gewählte Stylesheet */
	/* <!-- $LANGUAGE=EN -->	Property for the selected Stylesheet */
	private final StringProperty stylesheetProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property für den gewählten Style des Stylesheets */
	/* <!-- $LANGUAGE=EN -->	Property for the selected Style of the Stylesheet */
	private final ObjectProperty<Style> styleProperty;
	
	
	
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
		this(Resources.DEFAULT_PREFERENCES);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt neue Preferences mit den Einstellungen, die in der übergebenen Datei gespeichert sind oder den Standardeinstellungen,
	 * wenn die übergebene Datei nicht gefunden oder geladen werden konnte.
	 * 
	 * @param file	Datei mit den Einstellungen, die geladen werden sollen, im XML-Format
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates new Preferences with the settings that are saved in the given File or the default settings,
	 * if the given File could not be found or loaded.
	 * 
	 * @param file	File with the settings to be loaded in XML format
	 */
	private Preferences(File file) {
		this.commaProperty = new SimpleObjectProperty<>();
		this.indicateFractionalPrecisionProperty = new SimpleBooleanProperty();
		
		this.stylesheetProperty = new SimpleStringProperty();
		this.readOnlyStylesheetProperty = new SimpleStringProperty();
		((StringPropertyBase) this.readOnlyStylesheetProperty).bind(stylesheetProperty);
		
		this.styleProperty = new SimpleObjectProperty<>();
		this.readOnlyStyleProperty = new SimpleObjectProperty<>();
		((ObjectProperty<Style>) this.readOnlyStyleProperty).bind(styleProperty);
		
		
		try {
			this.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
			this.commaProperty.set(Comma.COMMA_DE);
			this.indicateFractionalPrecisionProperty.set(true);
			this.stylesheetProperty.set("");
			this.styleProperty.set(Style.UNKNOWN);
		}
	}	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt neue Preferences mit den Einstellungen, die in der übergebenen Datei gespeichert sind oder den Standardeinstellungen,
	 * wenn die übergebene Datei nicht gefunden oder geladen werden konnte.
	 * 
	 * @param in	Stream mit den Einstellungen, die geladen werden sollen, im XML-Format
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates new Preferences with the settings that are saved in the given File or the default settings,
	 * if the given File could not be found or loaded.
	 * 
	 * @param in	Stream with the settings to be loaded in XML format
	 */
	private Preferences(InputStream in) {
		this.commaProperty = new SimpleObjectProperty<>();
		this.indicateFractionalPrecisionProperty = new SimpleBooleanProperty();
		
		this.stylesheetProperty = new SimpleStringProperty();
		this.readOnlyStylesheetProperty = new SimpleStringProperty();
		((StringPropertyBase) this.readOnlyStylesheetProperty).bind(stylesheetProperty);
		
		this.styleProperty = new SimpleObjectProperty<>();
		this.readOnlyStyleProperty = new SimpleObjectProperty<>();
		((ObjectProperty<Style>) this.readOnlyStyleProperty).bind(styleProperty);
		
		
		try {
			this.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			this.commaProperty.set(Comma.COMMA_DE);
			this.indicateFractionalPrecisionProperty.set(true);
			this.stylesheetProperty.set("");
			this.styleProperty.set(Style.UNKNOWN);
		}
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

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
			String url = Resources.getResourceAsExternalForm(path);
			this.stylesheetProperty.set(url);
			this.styleProperty.set(Style.UNKNOWN);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Speichert den Speicherort des Stylesheets mit dem vorgegebenen Style in der {@link #stylesheetProperty}.
	 * 
	 * @param style	Stil, den das Stylesheet haben soll
	 * 
	 * @return		{@code true}, wenn der Style bekannt ist und das Stylesheet gesetzt wurde, sonst {@code false}
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=DE -->
	 * Saves the location of the Stylesheet with the given Style in the {@link #stylesheetProperty}.
	 * 
	 * @param style	Style of the Stylesheet to be set
	 * 
	 * @return		{@code true} if Style is not UNKNOWN if the Stylesheet was set, otherwise {@code false}
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public boolean setStylesheet(Style style) {
		switch(style) {
		case LIGHT:
			this.stylesheetProperty.set(Resources.LIGHT_CSS);
			break;
		case DARK:
			this.stylesheetProperty.set(Resources.DARK_CSS);
			break;
		default:
			return false;
		}
		
		this.styleProperty.set(style);
		return true;
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	other methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Lädt alle Einstellungen aus der übergebenen XML-Datei
	 * 
	 * @param in	Stream, aus dem die Einstellungen gelesen werden sollen
	 * 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads all settings from the given XML file
	 * 
	 * @param in	Stream from which the settings are read
	 * 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private void load(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(in);
		
		NodeList nodes = doc.getElementsByTagName("preferences");
		
		Element prefs = (Element) nodes.item(0);
		
		String comma = prefs.getElementsByTagName("comma").item(0).getTextContent();
		this.commaProperty.set(Comma.valueOf(comma));
		
		String indicateFractionalPrecision = prefs.getElementsByTagName("indicateFractionalPrecision").item(0).getTextContent();
		this.indicateFractionalPrecisionProperty.set(Boolean.valueOf(indicateFractionalPrecision));
		
		String style = prefs.getElementsByTagName("style").item(0).getTextContent();
		this.setStylesheet(Style.valueOf(style));
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Speichert alle Einstellungen in der übergebenen Datei im XML-Format
	 * 
	 * @param file	Datei, in die die Einstellungen geschrieben werden.
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Stores all preferences into the given file as XML document
	 * 
	 * @param file	File in which the preferences are written.
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private void store(File file) {
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			// XML-Root
			Element xmlRoot = doc.createElement("bitchanger");
			doc.appendChild(xmlRoot);
			
			// XML-Baum erstellen
			createXMLTree(doc, xmlRoot);
			
			// in Datei Schreiben
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(file);
			
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Fügt alle Einstellungen dieser Preferences zu dem XML-Baum des Element xmlRoot hinzu
	 * 
	 * @param doc		XML-Dokument, in dem xmlRoot gespeichert ist
	 * @param xmlRoot	Wurzelelement des XML-Baumes, der erweitert wird
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Adds all settings of these Preferences to the XML tree of the Element xmlRoot
	 * 
	 * @param doc		XML document in which xmlRoot is stored
	 * @param xmlRoot	root element of the XML tree that is being expanded
	 */
	private void createXMLTree(Document doc, Element xmlRoot) {
		// Preferences
		Element prefs = doc.createElement("preferences");
		xmlRoot.appendChild(prefs);

		// commaProperty
		Element commaPropertyTag = doc.createElement("comma");
		commaPropertyTag.appendChild(doc.createTextNode(this.commaProperty.get().name()));
		prefs.appendChild(commaPropertyTag);

		// indicateFractionalPrecisionProperty
		Element indicateFractionalPrecisionPropertyTag = doc.createElement("indicateFractionalPrecision");
		indicateFractionalPrecisionPropertyTag
				.appendChild(doc.createTextNode(String.valueOf(this.indicateFractionalPrecisionProperty.get())));
		prefs.appendChild(indicateFractionalPrecisionPropertyTag);

		// styleProperty
		Element stylePropertyTag = doc.createElement("style");
		stylePropertyTag.appendChild(doc.createTextNode(this.styleProperty.get().name()));
		prefs.appendChild(stylePropertyTag);
	}

	
}







