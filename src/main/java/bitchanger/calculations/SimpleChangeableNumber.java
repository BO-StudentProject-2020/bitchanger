/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

import java.util.Objects;

import bitchanger.preferences.Preferences;

/**	<!-- $LANGUAGE=DE -->
 * Die Klasse SimpleChangeableNumber bietet eine vollständige Implementierung von {@link ChangeableNumber}.
 * <p>
 * Jede Instanz dieser Klasse schließt einen Wert ein, der aus beliebigen Zahlensystemen gesetzt und in
 * verschiedene Zahlensysteme umgewandelt werden kann. Die String-Darstellungen in den verschiedenen
 * Zahlensystemen enthalten <b>keine Präfixe</b>, die auf die Basis hinweisen.
 * </p>
 * <p>
 * Die String Darstellungen der Zahlensysteme werden für bessere Lesbarkeit in Blöcke unterteilt.
 * Beim Hexadezimal- und Binärsystem sind die Blöcke vier Zeichen lang, in allen anderen Zahlensystemen
 * haben die Blöcke eine Länge von drei Zeichen.
 * </p>
 * 
 * @see ChangeableNumber
 * 
 * @author Tim Mühle
 * @author Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */

/*	<!-- $LANGUAGE=EN -->
 * The class SimpleChangeableNumber offers a complete implementation of {@link ChangeableNumber}.
 * <p>
 * Each instance of this class wraps a value, that gets set from different numeral systems and can get
 * converted into any numeral system. The strings in these numeral systems <b>do not contain any prefixes<b>
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
 * @version 0.1.4
 *
 */
public class SimpleChangeableNumber implements ChangeableNumber {
	
// Attribute	## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der binären String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as binary string representation */
	private String binValue;
	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der dezimalen String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as decimal string representation */
	private String decValue;
	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der hexadezimalen String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as hexadecimal string representation */
	private String hexValue;
	
	/** <!-- $LANGUAGE=DE --> eingeschlossener Wert in der oktalen String-Darstellung */
	/* <!-- $LANGUAGE=EN --> wrapped value as octal string representation */
	private String octalValue;
	
	
	
// Konstruktoren   ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue Instanz mit dem eingeschlossenen Wert 0
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Generates a new instance with 0 as wrapped value
	 */
	public SimpleChangeableNumber() {
		this("0");
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue Instanz, die den übergebenen dezimal-Wert repräsentiert
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
		this.initDecimal(decValue);
	}

	
	
// Initialisierung	  ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt den eingeschlossenen Wert dieser {@code SimpleChangeableNumber} auf den übergebenen dezimal-Wert.
	 * Die String-Darstellungen für das Hexadezimal-, Oktal- und Binärsystem werden berechnet und in den Attributen gespeichert.
	 * 
	 * @param decValue	neuer Wert, den diese {@code SimpleChangeableNumber} repräsentiert
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
		this.binValue = ConvertingNumbers.decToBaseBlocks(2, this.decValue, Preferences.getPrefs().getComma(), 4);
	}

	
	
// Getter und Setter  ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHex(String hexValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.setValue(hexValue, 16);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDec(String decValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		// Aufruf von basisToDezString noetig, damit die Exceptions bei falscher Eingabe geworfen werden
		initDecimal(decValue);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setOct(String octValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.setValue(octValue, 8);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBin(String binValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.setValue(binValue, 2);

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(String value, int baseOfValue) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		this.initDecimal(ConvertingNumbers.baseToDecString(baseOfValue, value, Preferences.getPrefs().getComma()));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		this.binValue = "";
		this.decValue = "";
		this.hexValue = "";
		this.octalValue = "";
	}


	
// toString - Methoden	 ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toHexString() {
		return this.hexValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toDecString() {
		return this.decValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toOctString() {
		return this.octalValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toBinString() {
		return this.binValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * {@inheritDoc}
	 */
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
			// Auf falsche Basis prüfen
			if(base < ConvertingNumbers.MIN_BASE || base > ConvertingNumbers.MAX_BASE) {
				throw illArg;
			}
			
			// decValue ist ein leerer String (reset-Methode wurde aufgerufen)
			return "";
		} catch (Exception e) {
			// Sollte nicht auftreten
			throw e;
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt einen {@code String} zurück, der die hexadezimale, dezimale, oktale und
	 * binäre Darstellung des eingeschlossenen Wertes enthält.
	 * 
	 * @return	String bestehend aus der hexadezimalen, dezimalen, oktalen und binären String-Darstellung des eingeschlossenen
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
	
	

	
}