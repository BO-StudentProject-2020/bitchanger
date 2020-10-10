/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

import java.util.Objects;

import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Preferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**	<!-- $LANGUAGE=DE -->
 * Die Klasse SimpleChangeableNumber bietet eine vollst\u00E4ndige Implementierung von {@link ChangeableNumber}.
 * <p>
 * Jede Instanz dieser Klasse schließt einen Wert ein, der aus beliebigen Zahlensystemen gesetzt und in
 * verschiedene Zahlensysteme umgewandelt werden kann. Die String-Darstellungen in den verschiedenen
 * Zahlensystemen enthalten <b>keine Pr\u00E4fixe</b>, die auf die Basis hinweisen.
 * </p>
 * <p>
 * Die String Darstellungen der Zahlensysteme werden f\u00FCr bessere Lesbarkeit in Bl\u00F6cke unterteilt.
 * Beim Hexadezimal- und Bin\u00E4rsystem sind die Bl\u00F6cke vier Zeichen lang, in allen anderen Zahlensystemen
 * haben die Bl\u00F6cke eine L\u00E4nge von drei Zeichen.
 * </p>
 * 
 * @see ChangeableNumber
 * 
 * @author Tim M\u00FChle
 * @author Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * The class SimpleChangeableNumber offers a complete implementation of {@link ChangeableNumber}.
 * <p>
 * Each instance of this class wraps a value, that gets set from different numeral systems and can get
 * converted into any numeral system. The strings in these numeral systems <b>do not contain any prefixes</b>
 * that refer to the base.
 * </p>
 * <p>
 * The string representations of the number systems are splitted into blocks for better readability.
 * The blocks are four characters long in the hexadecimal and binary systems, in all other number systems
 * the blocks are three characters long.
 * </p>
 * 
 * @see ChangeableNumber
 * 
 * @author Tim Muehle
 * @author Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 *
 */
public class SimpleChangeableNumber implements ChangeableNumber {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der bin\u00E4ren String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as binary String representation */
	private String binValue;
	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der dezimalen String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as decimal String representation */
	private String decValue;
	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der hexadezimalen String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as hexadecimal String representation */
	private String hexValue;
	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der oktalen String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as octal String representation */
	private String octalValue;
	
	// TODO JavaDoc
	private final StringProperty stringProperty;
	
	// TODO JavaDoc
	private final StringProperty logicStringProperty;
	
	// TODO JavaDoc
	private final IntegerProperty baseProperty;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue Instanz mit dem eingeschlossenen Wert 0
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Generates a new instance with 0 as wrapped value
	 */
	public SimpleChangeableNumber() {
		this("0");
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue Instanz, die den \u00FCbergebenen dezimal-Wert repr\u00E4sentiert
	 * 
	 * @param decValue	Wert, der von diesem Objekt eingeschlossen werden soll
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist
	 * 
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Generates a new instance that represents the committed decimal value
	 * 
	 * @param decValue	Value that should be wrapped by this object
	 * 
	 * @throws NullPointerException			if the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException		if the parameter {@code decValue} is not a number of the decimal system
	 * @throws IllegalArgumentException		if {@code decValue} is an empty string
	 * 
	 */
	public SimpleChangeableNumber(String decValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.stringProperty = new SimpleStringProperty();
		this.logicStringProperty = new SimpleStringProperty();
		this.baseProperty = new SimpleIntegerProperty(10);
		
		setListeners();
		
		this.initDecimal(decValue);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc 0.1.7
	private void setListeners() {
		this.baseProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldBase, Number newBase) {
				updateStringProperty();
				updateLogicStringProperty();
			}
		});
		
		Preferences.getPrefs().bitLengthProperty().addListener(new ChangeListener<BitLength>() {
			@Override
			public void changed(ObservableValue<? extends BitLength> observable, BitLength oldValue, BitLength newValue) {
				updateLogicStringProperty();
			}
		});
		
		Preferences.getPrefs().useUnsignedBitOperationProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				updateLogicStringProperty();
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt den eingeschlossenen Wert dieser {@code SimpleChangeableNumber} auf den \u00FCbergebenen dezimal-Wert.
	 * Die String-Darstellungen f\u00FCr das Hexadezimal-, Oktal- und Bin\u00E4rsystem werden berechnet und in den Attributen gespeichert.
	 * 
	 * @param decValue	neuer Wert, den diese {@code SimpleChangeableNumber} repr\u00E4sentiert
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist
	 * 
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets the wrapped value of this {@code SimpleChangeableNumber} to the committed decimal value.
	 * The string representations for the hexadecimal-, octal- an binary-system get calculated and stored in the attributes.
	 * 
	 * @param decValue	new value that gets represented by {@code SimpleChangeableNumber}
	 * 
	 * @throws NullPointerException			if the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException		if the parameter {@code decValue} is not a number of the decimal system
	 * @throws IllegalArgumentException		if {@code decValue} is an empty string
	 * 
	 */
	private void initDecimal(String decValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.decValue = ConvertingNumbers.splitInBlocks(Objects.requireNonNull(decValue), 3);
		this.hexValue = ConvertingNumbers.decToBaseBlocks(16, this.decValue, Preferences.getPrefs().getComma(), 4);
		this.octalValue = ConvertingNumbers.decToBaseBlocks(8, this.decValue, Preferences.getPrefs().getComma(), 3);
		
		try {
			this.binValue = ConvertingNumbers.decToBaseBlocks(2, this.decValue, Preferences.getPrefs().getComma(), 4);
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			
			this.binValue = "";
		}
		
		updateStringProperty();
		updateLogicStringProperty();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	// TODO JavaDoc 0.1.7
	private void updateStringProperty() {
		try {
			if(baseProperty.get() == 10) {
				stringProperty.set(toDecString());
			}
			else {
				stringProperty.set(toBaseString(baseProperty.get()));
			}
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			
			stringProperty.set("");
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	// TODO JavaDoc 0.1.7
	private void updateLogicStringProperty() {
		long val;
		
		if(Preferences.getPrefs().useUnsignedBitOperationProperty().get()) {
			if (Preferences.getPrefs().bitLengthProperty().get().equals(BitLength._64_BIT) && Preferences.getPrefs().useUnsignedBitOperationProperty().get()) {
				// bei unsigned maximal 63 Bit möglich!
				val = (long) this.asDouble() & 0x7FFFFFFFFFFFFFFFl;
			} else {
				val = (long) this.asDouble() & Preferences.getPrefs().bitLengthProperty().get().getBitmask();
			}
		} else {
			switch(Preferences.getPrefs().bitLengthProperty().get()) {
				case _8_BIT:	val = (byte)this.asDouble();
					break;
				case _16_BIT:	val = (short)this.asDouble();
					break;
				case _32_BIT:	val = (int)this.asDouble();
					break;
				case _64_BIT:	// fall through
				case UNKNOWN:	// fall through
				default:		val = (long)this.asDouble();
					break;
			}
		}
		
		if (baseProperty.get() == 10) {
			this.logicStringProperty.set(ConvertingNumbers.decToBaseBlocks(10, String.valueOf(val), Preferences.getPrefs().getComma(), 3));
		} 
		else if (baseProperty.get() == 2) {
			StringBuffer binText = new StringBuffer();
			binText.append(ConvertingNumbers.decToBase(baseProperty.get(), String.valueOf(val)));
			
			while(binText.length() < Preferences.getPrefs().bitLengthProperty().get().getNumberOfBits()) {
				binText.insert(0, binText.charAt(0));
			}
			
			// zu lange Strings kürzen (können wegen dem Vorzeichen zu lang sein)
			binText.reverse();
			binText.setLength(Preferences.getPrefs().bitLengthProperty().get().getNumberOfBits());
			binText.reverse();
			
			this.logicStringProperty.set(ConvertingNumbers.splitInBlocks(binText.toString(), 4));
		}
		else {
			this.logicStringProperty.set(ConvertingNumbers.decToBaseBlocks(baseProperty.get(), String.valueOf(val), Preferences.getPrefs().getComma(), 4));
		}
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** {@inheritDoc} */
	@Override
	public void set(double decValue) throws NullPointerException, NumberFormatException, IllegalArgumentException, NumberOverflowException {
		if(decValue > Long.MAX_VALUE || decValue < -Long.MAX_VALUE) {
			throw new NumberOverflowException("for Number " + decValue, 
					"Die eingegebene Zahl liegt nicht im erlaubten Wertebereich!", Long.MAX_VALUE, -Long.MAX_VALUE);
		}
		
		// Unterteilen in ganzen und gebrochenen Anteil wegen Exponenten-Darstellung von double bei großen Zahlen
		String integerPart = String.valueOf((long)decValue);
		String fractionalPart = String.valueOf(decValue % 1.0);
		fractionalPart = fractionalPart.substring(fractionalPart.indexOf("."));
		
		this.setDec(integerPart + fractionalPart);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public void setHex(String hexValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.setValue(hexValue, 16);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public void setDec(String decValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		// Aufruf von basisToDezString noetig, damit die Exceptions bei falscher Eingabe geworfen werden
		initDecimal(decValue);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public void setOct(String octValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.setValue(octValue, 8);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public void setBin(String binValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.setValue(binValue, 2);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public void setValue(String value, int baseOfValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.initDecimal(ConvertingNumbers.baseToDecString(baseOfValue, value, Preferences.getPrefs().getComma()));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	
	/** {@inheritDoc} */
	@Override
	public void setIEEE(String ieee, IEEEStandard standard) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		// TODO IEEE Implementieren
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	
	/** {@inheritDoc} */
	@Override
	public void reset() {
		this.binValue = "";
		this.decValue = "";
		this.hexValue = "";
		this.octalValue = "";
		this.stringProperty.set("");
	}


	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	toString methods																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** {@inheritDoc} */
	@Override
	public String toHexString() {
		return this.hexValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public String toDecString() {
		return this.decValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public String toOctString() {
		return this.octalValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public String toBinString() {
		return this.binValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** {@inheritDoc} */
	@Override
	public String toBaseString(int base) throws IllegalArgumentException {
		if(base == 10) {
			// Ungenauigkeiten beim Umwandeln ins Zehnersystem umgehen
			return toDecString();
		}
		
		try {
			int blockSize = (base == 2 || base == 16) ? 4 : 3; 
			return ConvertingNumbers.decToBaseBlocks(base, decValue, Preferences.getPrefs().getComma(), blockSize);
		} catch (IllegalArgumentException illArg) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, illArg);
			
			// Auf falsche Basis prüfen
			if(base < ConvertingNumbers.MIN_BASE || base > ConvertingNumbers.MAX_BASE) {
				throw illArg;
			}
			
			// decValue ist ein leerer String (reset-Methode wurde aufgerufen)
			return "";
		} catch (Exception e) {
			// Sollte nicht auftreten
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL, e);
			
			throw e;
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt einen {@code String} zur\u00FCck, der die hexadezimale, dezimale, oktale und
	 * bin\u00E4re Darstellung des eingeschlossenen Wertes enth\u00E4lt.
	 * 
	 * @return	String bestehend aus der hexadezimalen, dezimalen, oktalen und bin\u00E4ren String-Darstellung des eingeschlossenen
	 * 			Wertes, hintereinander aufgelistet
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns a {@code String} that includes the hexadecimal, decimal, octal and binary
	 * presentation of the wrapped value.
	 * 
	 * @return	String that consists of the hexadecimal, decimal, octal and binary String representation of the wrapped value.
	 * These Strings are cataloged consecutively.
	 */
	@Override
	public String toString() {
		String s = String.format("Hex: %s\tDec: %s\tOct: %s\tBin: %s", this.toHexString(),
				this.toDecString(), this.toOctString(), this.toBinString());
		return s;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	
	/** {@inheritDoc} */
	@Override
	public String toIEEEString(IEEEStandard standard) {
		// TODO IEEE Implementieren
		return null;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		

	/** {@inheritDoc} */
	@Override
	public ReadOnlyStringProperty stringProperty() {
		return this.stringProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		

	/** {@inheritDoc} */
	@Override
	public ReadOnlyStringProperty logicStringProperty() {
		return this.logicStringProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		

	/** {@inheritDoc} */
	@Override
	public IntegerProperty baseProperty() {
		return this.baseProperty;
	}
	
	

	
}