/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences;

import java.io.File;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import bitchanger.calculations.BitLength;
import bitchanger.calculations.IEEEStandard;
import bitchanger.gui.views.ConverterView;
import bitchanger.gui.views.Viewable;
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.writableProperty.WritableBooleanProperty;
import bitchanger.preferences.writableProperty.WritableClassProperty;
import bitchanger.preferences.writableProperty.WritableEnumProperty;
import bitchanger.preferences.writableProperty.WritableIntegerProperty;
import bitchanger.util.Resources;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

/** <!-- $LANGUAGE=DE -->
 * Preferences ist die globale Sammlung f\u00FCr alle m\u00F6glichen Einstellungen, die am Bitchanger vorgenommen 
 * werden k\u00F6nnen. Die Einstellungen k\u00F6nnen \u00FCber die Methode {@link #getPrefs()} aus allen anderen Klassen
 * abgefragt und ge\u00E4ndert werden.
 * 
 * <p>
 * Zudem gibt es Methoden, mit denen alle Einstellungen dauerhaft abgespeichert und wieder geladen werden k\u00F6nnen.
 * </p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 */
/* <!-- $LANGUAGE=EN -->
 * Preferences is the global collection for all possible settings that can be selected for the bitchanger.
 * These settings can be requested and changed from all classes by using the method {@link #getPrefs()}.
 * 
 * <p>
 * Furthermore there are methods to store all settings permanently and load these at a later moment.
 * </p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 */
public class Preferences {
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die alle aktuellen Einstellungen enth\u00E4lt */
	/* <!-- $LANGUAGE=EN -->	Constant that contains all the current settings */
	private static Preferences prefs = new Preferences(Resources.CUSTOM_PREFERENCES);
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die aktuellen Einstellungen zur\u00FCck
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
	 * L\u00E4dt die letzten Einstellungen aus der Datei {@link Resources#CUSTOM_PREFERENCES}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads the last settings from the file {@link Resources#CUSTOM_PREFERENCES}
	 */
	public static void loadCustom() {
		prefs.load(Resources.CUSTOM_PREFERENCES);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * L\u00E4dt die Standardeinstellungen aus der Datei {@link Resources#DEFAULT_PREFERENCES}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads the default settings from the file {@link Resources#DEFAULT_PREFERENCES}
	 */
	public static void loadDefault() {
		prefs.load(Resources.DEFAULT_PREFERENCES);
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
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr das Kommazeichen */
	/* <!-- $LANGUAGE=EN -->	Property for comma character */
	private final WritableEnumProperty<Comma> commaProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die Anzeige von abgebrochenen Nachkommastellen */
	/* <!-- $LANGUAGE=EN -->	Property for displaying aborted decimal places */
	private final WritableBooleanProperty indicateFractionalPrecisionProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr das gew\u00E4hlte Stylesheet */
	/* <!-- $LANGUAGE=EN -->	Property for the selected Stylesheet */
	private final SimpleStringProperty stylesheetProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr den gew\u00E4hlten Style des Stylesheets */
	/* <!-- $LANGUAGE=EN -->	Property for the selected Style of the Stylesheet */
	private final WritableEnumProperty<Style> styleProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die zuletzt angezeigte View */
	/* <!-- $LANGUAGE=EN -->	Property for the last shown View */
	private final WritableClassProperty<Viewable> viewClassProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die gew\u00E4hlte IEEE-Norm */
	/* <!-- $LANGUAGE=EN -->	Property for the selected IEEE standard */
	private final WritableEnumProperty<IEEEStandard> ieeeStandardProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die Anzeige der Symbole der Bitoperationen in der CalculatorView */
	/* <!-- $LANGUAGE=EN -->	Property for showing symbols of the logical bit operations in CalculatorView */
	private final WritableBooleanProperty showBitOperationSymbolsProperty;

	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die gew\u00E4hlte Bitl\u00E4nge */
	/* <!-- $LANGUAGE=EN -->	Property for the selected number of Bits */
	private final WritableEnumProperty<BitLength> bitLengthProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr vorzeichenlose Bitoperationen in der CalculatorView */
	/* <!-- $LANGUAGE=EN -->	Property for unsigned logical bit operations in CalculatorView */
	private final WritableBooleanProperty useUnsignedBitOperationProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr das Deaktivieren der Warnmeldung f\u00FCr vorzeichenlose Bitoperationen mit 64-Bit */
	/* <!-- $LANGUAGE=EN -->	Property for deactivating the warning alert that appears when bitLengthProperty is set to 64-Bit and useUnsignedBitOperationProperty is set to true */
	private final WritableBooleanProperty unsignedBitLengthWarningDeactivatedProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr das Deaktivieren der Warnmeldung beim Verkleinern der Bitl\u00E4nge */
	/* <!-- $LANGUAGE=EN -->	Property for deactivating the warning alert that appears when bitLengthProperty is set to a lower bit length */
	private final WritableBooleanProperty bitLengthDeleteWarningDeactivatedProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die eingestellte Basis in den Bitoperationen */
	/* <!-- $LANGUAGE=EN -->	Property for the selected base in BitoperationView */
	private final WritableIntegerProperty bitOpertionBaseProperty;
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die eingestellte Basis in den Berechnungen */
	/* <!-- $LANGUAGE=EN -->	Property for the selected base in CalculatorView */
	private final WritableIntegerProperty calculationBaseProperty;
	
	
	
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
	 * Erstellt neue Preferences mit den Einstellungen, die in der \u00FCbergebenen Datei gespeichert sind oder den Standardeinstellungen,
	 * wenn die \u00FCbergebene Datei nicht gefunden oder geladen werden konnte.
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
		this.commaProperty 									= new WritableEnumProperty<>(Comma.COMMA_DE, "comma");
		this.indicateFractionalPrecisionProperty 			= new WritableBooleanProperty(true, "indicateFractionalPrecision");
		this.stylesheetProperty 							= new SimpleStringProperty("");
		this.styleProperty 									= new WritableEnumProperty<>(Style.UNKNOWN, "style");
		this.viewClassProperty 								= new WritableClassProperty<>(ConverterView.class, "viewClass");
		this.ieeeStandardProperty 							= new WritableEnumProperty<>(IEEEStandard.IEEE_754_2008_b32, "IEEE_Standard");
		this.showBitOperationSymbolsProperty 				= new WritableBooleanProperty(false, "showBitOperationSymbols");
		this.bitLengthProperty 								= new WritableEnumProperty<>(BitLength._8_BIT, "bitLength");
		this.useUnsignedBitOperationProperty 				= new WritableBooleanProperty(true, "unsignedBitoperations");
		this.unsignedBitLengthWarningDeactivatedProperty	= new WritableBooleanProperty(false, "deactivateUnsignedBitLenthWarning");
		this.bitLengthDeleteWarningDeactivatedProperty 		= new WritableBooleanProperty(false, "deactivateBitLengthDeleteWarning");
		this.bitOpertionBaseProperty 						= new WritableIntegerProperty(10, "bitOperationBase");
		this.calculationBaseProperty 						= new WritableIntegerProperty(10, "calculationBase");
		
		try {
			this.load(file);
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM);
			e.printStackTrace();
		}
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr das Kommazeichen zur\u00FCck
	 * 
	 * @return	Property f\u00FCr das Kommazeichen
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the Property for comma character
	 * 
	 * @return Property for comma character
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public ObjectProperty<Comma> commaProperty(){
		return this.commaProperty;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die Anzeige von abgebrochenen Nachkommastellen zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die Anzeige von abgebrochenen Nachkommastellen
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the Property for displaying aborted decimal places
	 * 
	 * @return Property for displaying aborted decimal places
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public BooleanProperty indicateFractionalPrecisionProperty() {
		return this.indicateFractionalPrecisionProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die ReadOnlyProperty f\u00FCr das gew\u00E4hlte Stylesheet zur\u00FCck
	 * 
	 * @return	ReadOnlyProperty f\u00FCr das gew\u00E4hlte Stylesheet
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the ReadOnlyProperty for the selected Stylesheet
	 * 
	 * @return ReadOnlyProperty for the selected Stylesheet
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public ReadOnlyStringProperty stylesheetProperty() {
		return this.stylesheetProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die ReadOnlyProperty f\u00FCr das gew\u00E4hlte Stylesheet zur\u00FCck
	 * 
	 * @return	ReadOnlyProperty f\u00FCr das gew\u00E4hlte Stylesheet
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the ReadOnlyProperty for the selected Stylesheet
	 * 
	 * @return ReadOnlyProperty for the selected Stylesheet
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public ReadOnlyObjectProperty<Style> styleProperty() {
		return this.styleProperty;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die zuletzt angezeigte View zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die zuletzt angezeigte View
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the Property for the last shown View
	 * 
	 * @return Property for the last shown View
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public WritableClassProperty<Viewable> viewClassProperty() {
		return this.viewClassProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die gew\u00E4hlte IEEE-Norm zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die gew\u00E4hlte IEEE-Norm
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the Property for the selected IEEE standard
	 * 
	 * @return Property for the selected IEEE standard
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public ObjectProperty<IEEEStandard> ieeeStandardProperty() {
		return this.ieeeStandardProperty;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die Anzeige der Symbole der Bitoperationen in der CalculatorView zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die Anzeige der Symbole der Bitoperationen in der CalculatorView
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public BooleanProperty showBitOperationSymbolsProperty() {
		return this.showBitOperationSymbolsProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die Anzeige der Symbole der Bitoperationen in der BitoperationView zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die Anzeige der Symbole der Bitoperationen in der BitoperationView
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public ObjectProperty<BitLength> bitLengthProperty() {
		return this.bitLengthProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr vorzeichenlose Bitoperationen in der BitoperationView zur\u00FCck
	 * 
	 * @return	Property f\u00FCr vorzeichenlose Bitoperationen in der BitoperationView
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public BooleanProperty useUnsignedBitOperationProperty() {
		return this.useUnsignedBitOperationProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr das Deaktivieren der Warnmeldung f\u00FCr vorzeichenlose Bitoperationen mit 64-Bit zur\u00FCck
	 * 
	 * @return	Property f\u00FCr das Deaktivieren der Warnmeldung f\u00FCr vorzeichenlose Bitoperationen mit 64-Bit
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public BooleanProperty unsignedBitLengthWarningDeactivatedProperty() {
		return this.unsignedBitLengthWarningDeactivatedProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr das Deaktivieren der Warnmeldung beim Verkleinern der Bitl\u00E4nge zur\u00FCck
	 * 
	 * @return	Property f\u00FCr das Deaktivieren der Warnmeldung beim Verkleinern der Bitl\u00E4nge
	 * 
	 * @since Bitchanger 0.1.7
	 */
	public BooleanProperty bitLengthDeleteWarningDeactivatedProperty() {
		return this.bitLengthDeleteWarningDeactivatedProperty;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die eingestellte Basis in den Bitoperationen zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die eingestellte Basis in den Bitoperationen
	 * 
	 * @since Bitchanger 0.1.8
	 */
	public IntegerProperty bitOpertionBaseProperty() {
		return this.bitOpertionBaseProperty;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr die eingestellte Basis in den Berechnungen zur\u00FCck
	 * 
	 * @return	Property f\u00FCr die eingestellte Basis in den Berechnungen
	 * 
	 * @since Bitchanger 0.1.8
	 */
	public IntegerProperty calculationBaseProperty() {
		return this.calculationBaseProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
		
	/** <!-- $LANGUAGE=DE -->
	 * Gibt das eingestellte Kommazeichen zur\u00FCck
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
	 * Gibt den Wert der indicateFractionalPrecisionProperty zur\u00FCck
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
	 * Sucht die Ressource mit dem \u00FCbergebenen Pfad und speichert den Speicherort der Resource in der {@link #stylesheetProperty}.
	 * 
	 * @param path	Pfad zum gew\u00FCnschten Stylesheet
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
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
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
			this.stylesheetProperty.set(Resources.LIGHT_THEME_CSS);
			break;
		case COLOR:
			this.stylesheetProperty.set(Resources.COLOR_THEME_CSS);
			break;
		case DARK:
			this.stylesheetProperty.set(Resources.DARK_THEME_CSS);
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
	 * L\u00E4dt alle Einstellungen aus der \u00FCbergebenen XML-Datei
	 * 
	 * @param file	Datei, aus der die Einstellungen geladen werden sollen
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads all settings from the given XML file
	 * 
	 * @param file	File from which the settings are loaded
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private void load(File file) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(file);
			
			NodeList nodes = doc.getElementsByTagName("preferences");
			
			Element preferencesTag = (Element) nodes.item(0);
			
			// Alle Attribute einlesen, die eine Instanz von XMLWritable sind
			for(Field f : this.getClass().getDeclaredFields()) {
				if(XMLWritable.class.isAssignableFrom(f.getType())) {
					try {
						XMLWritable property = (XMLWritable) f.get(this);
						property.setFromXMLTag(preferencesTag);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			this.setStylesheet(styleProperty.get());
			
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM);
			e.printStackTrace();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Speichert alle Einstellungen in der \u00FCbergebenen Datei im XML-Format
	 * 
	 * @param file	Datei, in der die Einstellungen gespeichert werden. Der eventuelle Inhalt der Datei wird \u00FCberschrieben!
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Stores all preferences into the given file as XML document
	 * 
	 * @param file	File in which the preferences are stored. Any content of the file will be overwritten!
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
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
			e.printStackTrace();
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * F\u00FCgt alle Einstellungen dieser Preferences zu dem XML-Baum des Element xmlRoot hinzu
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
		// Preferences Tag
		Element prefTag = doc.createElement("preferences");
		xmlRoot.appendChild(prefTag);
		
		// Alle Attribute zum XML-Baum hinzufügen, die eine Instanz von XMLWritable sind
		for(Field f : this.getClass().getDeclaredFields()) {
			if(XMLWritable.class.isAssignableFrom(f.getType())) {
				try {
					XMLWritable property = (XMLWritable) f.get(this);
					Element propertyTag = property.getXMLTag(doc);
					prefTag.appendChild(propertyTag);
				} catch (Exception e) {
					BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
					e.printStackTrace();
				}
			}
		}
	}

	
}







