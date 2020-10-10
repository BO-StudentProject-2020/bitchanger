/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;

/**	<!-- $LANGUAGE=DE -->
 * Die Klasse {@code ConvertingNumbers} enth\u00E4lt Methoden zum Umwandeln von Zahlen mit verschiedenen Zahlensystemen.
 * <p>
 * Die Methoden dieser Klasse k\u00F6nnen sowohl ganze Zahlen als auch Kommazahlen mit positiven oder negativen Wert umwandeln.
 * Es werden beliebige Zahlensysteme von der Basis 2 bis zur Basis 36 unterst\u00FCtzt, was dem Zeichenvorrat 0-9 und A-Z entspricht.
 * In der String-Darstellung k\u00F6nnen Zahlen mit Ziffern, Großbuchstaben und Kleinbuchstaben an die Methoden dieser Klasse
 * \u00FCbergeben werden, allerdings enthalten die R\u00FCckgaben in der String-Darstellung aller Methoden ausschließlich Zahlen 
 * und Großbuchstaben.
 * </p>
 * <p>
 * Das Komma-Zeichen kann mit der Klasse {@code Preferences} auf Deutsch (,) oder Englisch (.) eingestellt werden.
 * Ebenso kann der Indikator f\u00FCr abgeschnittene Nachkommastellen (...) aktiviert oder deaktiviert werden.
 * </p>
 * 
 * @see Preferences
 * 
 * @author Tim M\u00FChle
 * @author Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 */
/* <!-- $LANGUAGE=EN -->
 * The {@code ConvertingNumbers} class contains methods for performing conversions of numbers with different numeral systems.
 * <p>
 * These methods can convert positive and negative integral and float-point numbers.
 * Any numeral systems from base 2 to base 36 can be used. This exactly correspond to the character storage 0-9 and A-Z.
 * As string representation, numbers with capital and lower case letters can be handed over to methods in this class. The return 
 * of these string only contains Numbers and upper case letters.
 * </p>
 * <p>
 * The comma sign can be selected in German (,) or English (.) with the class {@code Preferences}.
 * Furthermore the indicator for truncated fractional digits (...) can be activated or disabled.
 * </p>
 * 
 * @see Preferences
 * 
 * @author Tim Muehle
 * @author Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 */
public class ConvertingNumbers {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE --> Eine Konstante f\u00FCr die kleinstm\u00F6gliche Basis **/
	/*	<!-- $LANGUAGE=EN --> Constant for the lowest possible base **/
	public static final int MIN_BASE = 2;
	
	/** <!-- $LANGUAGE=DE --> Eine Konstante f\u00FCr die gr\u00F6ßtm\u00F6gliche Basis bei einem Zeichenvorrat von 0-9 und A-Z **/
	/*	<!-- $LANGUAGE=EN --> Constant for the highest possible base of 36 characters given through 0-9 and A-Z **/
	public static final int MAX_BASE = 36;
	
	/** <!-- $LANGUAGE=DE --> Indikator f\u00FCr Abgeschnittene Nachkommastellen bei der Umwandlung vom Zehnersystem in ein anderes, beliebiges Zahlensystem **/
	/*	<!-- $LANGUAGE=EN --> Indicator for the cut fractional part in case of converting from decimal system into a system of any base **/
	public static final String FRACTIONAL_PRECISION_INDICATOR = "...";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Überprüfung von potentiellen Zahlen zu einer Basis																			 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Pr\u00FCft, ob {@code value} eine Zahl zur geforderten Basis {@code base} repr\u00E4sentiert.
	 * Ist der Indikator f\u00FCr abgeschnittene Nachkommastellen aktiviert, wird dieser bei der Auswertung ignoriert.
	 * Leerzeichen k\u00F6nnen als Tausender-Trennung verwendet werden und werden von dieser Methode ebenfalls ignoriert.
	 * 
	 * @param value	zu pr\u00FCfende Zahl repr\u00E4sentiert als {@code String}
	 * @param base	geforderte Basis von {@code value} - erlaubt sind nur Werte von 2 bis 36 (einschließlich)
	 * 
	 * @return 	{@code true}, wenn {@code value} eine Zahl zur geforderten Basis {@code base} 
	 * 			repr\u00E4sentiert, andernfalls wird {@code false} zur\u00FCckgegeben
	 * 
	 * @throws IllegalArgumentException	wenn {@code basis} den Wertebereich [2, 36] verl\u00E4sst
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Checks if {@code value} represents a number to the required base {@code base}.
	 * If the indicator for truncated fractional digits is activated, it will be ignored in the evaluation.
	 * Whitespace can be used as thousands separator and will be ignored by this method.
	 * 
	 * @param value	number to be checked, represented as {@code String}
	 * @param base	requested base of {@code value} - only values between 2 and 36 are allowed
	 * 
	 * @return 	{@code true}, if {@code value} represents a number to the requested base {@code base} 
	 * 			, otherwise {@code false} will be returned.
	 * 
	 * @throws IllegalArgumentException	if {@code basis} leaves the range of value [2, 36]
	 */
	public static boolean isValueToBase(int base, String value) throws IllegalArgumentException {
		if(base < MIN_BASE || base > MAX_BASE) {
			throw new IllegalArgumentException("Out of Bounds for base = " + base + " (base must be within " + MIN_BASE + " and " + MAX_BASE + ")");
		}
		
		// Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet, Leerzeichen werden ignoriert
		char[] characters = trimToNumberString(value).toCharArray();

		char end = (char) ('0' + base - 1);	// maximale Zahlen-Ziffer im Zahlensystem zur Basis
		boolean hasComma = false;			// Hilfsvariable zum Pruefen auf mehr als ein Komma

		// Ueberpruefen, ob verbotene Zeichen enthalten sind
		for(int i = 0; i < characters.length; i++) {
			char character = characters[i];	// Jeden Character einzeln ueberpruefen
			
			if ((character < '0' || character > end)) {
				// keine oder falsche Zahlen-Ziffer zur geg. Basis -> Pruefen auf Buchstaben
				if (character < 'A' || character > ('A' + base - 11)) {
					// kein oder falscher Buchstabe zur geg. Basis -> Pruefen auf Komma
					if (character == Preferences.getPrefs().getComma()) {
						// Es handelt sich um ein Komma
						if (hasComma) {
							// mehr als ein Komma in einer Zahl nicht erlaubt
							return false;
						}
						hasComma = true;
						continue;
					} 
					// Kein Komma -> Pruefen auf Vorzeichen an der ersten Stelle
					else if (i == 0 && (character == '+' || character == '-')) { 
						// Vorzeichen an der ersten Stelle erlaubt
						continue;
					} 
					else {
						// Es handelt sich um ein anderes, verbotenes Zeichen -> keine Zahl zur gegebenen Basis
						return false;
					}
				}
			}
		}

		// Kein verbotenes Zeichen, es handelt sich um eine Zahl zur gegebenen Basis
		return true;
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Umwandeln von beliebiger Basis nach dezimal																					 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code value} zur beliebigen Basis {@code base} in eine Zahl zur Basis 10 in der String-Darstellung um.
	 * 
	 * @param base	die spezifische Basis des \u00FCbergebenen Wertes {@code value}
	 * @param value	der Zahlenwert, der umgewandelt werden soll, in der String-Darstellung
	 * 
	 * @return	Wert der Zahl im Zehnersystem in der String-Darstellung mit dem standardm\u00E4ßig eingestellten Komma
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code value} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code basis} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 * @throws NumberOverflowException		wenn der Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code value} of any base {@code base} into a number of the decimal system as string representation.
	 * 
	 * @param base	Specific base of the submitted value {@code value}
	 * @param value	The numeric value, which should be converted as string representation
	 * 
	 * @return	Value of the Number in decimal system as string representation with default set comma
	 * 
	 * @throws NullPointerException			if the parameter {@code value} is {@code null}
	 * @throws NumberFormatException		if the parameter {@code value} is not a number of base {@code base}
	 * @throws IllegalArgumentException		if {@code value} is an empty string or {@code basis} leaves the range of value [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 * @throws NumberOverflowException		if {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 */
	public static String baseToDecString(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException, NumberOverflowException {
		return baseToDecString(base, value, Preferences.getPrefs().getComma());
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code value} zur beliebigen Basis {@code base} in eine Zahl zur Basis 10 in der String-Darstellung um.
	 * 
	 * @param base	die spezifische Basis des \u00FCbergebenen Wertes {@code value}
	 * @param value	der Zahlenwert, der umgewandelt werden soll, in der String-Darstellung
	 * @param comma	das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * 
	 * @return	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code value} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code basis} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 * @throws NumberOverflowException		wenn der ganzzahlige Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code value} of any base {@code base} into a number of the decimal system as string representation.
	 * 
	 * @param base	Specific base of the submitted value {@code value}
	 * @param value	The numeric value, which should be converted as string representation
	 * @param comma	The char which is used as comma for floating point numbers
	 * 
	 * @return	Value of the Number in decimal system as string representation
	 *
	 * @throws NullPointerException			if the parameter {@code value} is {@code null}
	 * @throws NumberFormatException		if the parameter {@code value} is not a number of base {@code base}
	 * @throws IllegalArgumentException		if {@code value} is an empty string or {@code basis} leaves the range of value [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 * @throws NumberOverflowException		if integer part of {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 */
	public static String baseToDecString(int base, String value, char comma) throws NullPointerException, NumberFormatException, IllegalArgumentException, NumberOverflowException {	
		return baseToDecString(base, value, comma, null);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code value} zur beliebigen Basis {@code base} in eine Zahl zur Basis 10 in der String-Darstellung um.
	 * 
	 * @param base	die spezifische Basis des \u00FCbergebenen Wertes {@code value}
	 * @param value	der Zahlenwert, der umgewandelt werden soll, in der String-Darstellung
	 * @param comma	das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * 
	 * @return	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code value} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code basis} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 * @throws NumberOverflowException		wenn der ganzzahlige Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code value} of any base {@code base} into a number of the decimal system as string representation.
	 * 
	 * @param base	Specific base of the submitted value {@code value}
	 * @param value	The numeric value, which should be converted as string representation
	 * @param comma	The char which is used as comma for floating point numbers
	 * 
	 * @return	Value of the Number in decimal system as string representation
	 *
	 * @throws NullPointerException			if the parameter {@code value} is {@code null}
	 * @throws NumberFormatException		if the parameter {@code value} is not a number of base {@code base}
	 * @throws IllegalArgumentException		if {@code value} is an empty string or {@code basis} leaves the range of value [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 * @throws NumberOverflowException		if integer part of {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 */
	public static String baseToDecString(int base, String value, char comma, Queue<ConversionStep> calcPath) throws NullPointerException, NumberFormatException, IllegalArgumentException, NumberOverflowException {	
		// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
		checkValue(base, value);
		value = trimToNumberString(value); // Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet
		
		// Bei negativen Zahlen wird das Minuszeichen zuerst entfernt, damit die Zahl wie gewohnt bearbeitet werden kann.
		boolean isNegative = value.startsWith("-");
		
		// Wenn eine Binäreingabe mit '1' beginnt, dann ist diese Zahl eine negative Binärzahl -> Zweierkomplement 
		boolean isNegativeBin = (base == 2 && value.startsWith("1"));
		
		if(isNegative && base == 2) {
			if(calcPath != null) {
				calcPath.clear();
				calcPath.add(new ConversionStep("Keine Umwandlung m\u00F6glich, negative Bin\u00E4rzahlen m\u00FCssen im Zweierkomplement \u00FCbergeben werden!"));
			}
			throw new NumberFormatException("negative binary values must ba passed as two's complement");
		}
		else if (isNegative) {
			// Minuszeichen entfernen
			if(calcPath != null) calcPath.add(new ConversionStep("Betrag nehmen und das Vorzeichen f\u00FCr sp\u00E4ter merken", true));
			value = value.substring(1, value.length());
		}
		else if(isNegativeBin) {
			// Abfrage auf illegales Zeichen (Komma-Abfrage bei negativen Binärzahlen)
			if(value.contains(String.valueOf(comma))) {
				if(calcPath != null) {
					calcPath.clear();
					calcPath.add(new ConversionStep("Keine Umwandlung m\u00F6glich, negative Bin\u00E4rzahlen d\u00FCrfen keine Nachkommastellen haben!"));
				}
				throw new NumberFormatException("negative binary values must not have a fractional part");
			}
			
			if(calcPath != null) {
				calcPath.add(new ConversionStep("negative Bin\u00E4rzahlen werden im Zweierkomplement abgebildet => zur R\u00FCckwandlung erneut das Zweierkomplement bilden und Vorzeichen f\u00FCr sp\u00E4ter merken!", true));
				calcPath.add(new TwosComplement(Long.parseLong(value)));
			}
			
			value = complementOf(value);
			isNegative = true;
		}
		
		// Übergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		String[] separated = separateByComma(value);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		// ganzzahligen Anteil zur Basis 10 umwandeln
		if(calcPath != null) calcPath.add(new ConversionStep("Ganzzahligen Anteil mit dem Hornerschema (Zielverfahren) von Basis " + base + " zur Basis 10 umwandeln:\n"
														   + "  -> 1. Erste Ziffer (von links) mit ihrer Basis (" + base + ") multiplizieren\n"
														   + "  -> 2. N\u00E4chste Ziffer mit dem Produkt addieren\n"
														   + "  -> 3. Ergebnis erneut mit der Basis multiplizieren\n"
														   + "  -> 4. Schritte 2 und 3 wiederholen, bis die letzte Ziffer der Zahl erreicht ist", true));
		double integerPart = baseToDecIntPart(base, separated[0], calcPath);
		if(calcPath != null) calcPath.add(new ConversionStep("=> Die letzte Summe ist die umgewandelte ganze Zahl zur Basis 10: " + splitInBlocks(10, String.valueOf((long) integerPart))));
		
		
		// gebrochenen Anteil zur Basis 10 umwandeln
		double fractionalPart = 0.0;
		if(! separated[1].equals("0")) {
			// Nachkommateil vorhanden
			if(calcPath != null) calcPath.add(new ConversionStep("Gebrochenen Anteil mit dem Hornerschema (Zielverfahren) von Basis " + base + " zur Basis 10 umwandeln:\n"
															   + "  -> 1. Reihenfolge der Ziffern der umzuwandelnden Zahl inklusive der Null vor dem Komma umdrehen\n"
															   + "  -> 2. Erste Ziffer (von links) mit dem Kehrwert ihrer Basis (1/" + base + ") multiplizieren\n"
															   + "  -> 3. N\u00E4chste Ziffer mit dem Produkt addieren\n"
															   + "  -> 4. Ergebnis erneut mit dem Kehrwert der Basis multiplizieren\n"
															   + "  -> 5. Schritte 3 und 4 wiederholen, bis die letzte Ziffer der Zahl erreicht ist", true));
			fractionalPart = baseToDecFractionalPart(base, separated[1], calcPath);
			if(calcPath != null) calcPath.add(new ConversionStep("=> Die letzte Summe ist der umgewandelte gebrochene Anteil zur Basis 10: " + splitInBlocks(10, String.valueOf(fractionalPart))));
		}
		
		
		if(integerPart > (double)Long.MAX_VALUE) {
			if(calcPath != null) {
				calcPath.clear();
				calcPath.add(new ConversionStep("Keine Umwandlung m\u00F6glich, da der erlaubte Wertebereich verlassen wurde!"));
			}
			throw new NumberOverflowException("for Number " + splitInBlocks(10, String.valueOf(integerPart + fractionalPart)), "Die eingegebene Zahl liegt nicht im erlaubten Wertebereich!", Long.MAX_VALUE, -Long.MAX_VALUE);
		}
		
		
		// Bei negativen Binärzahlen ist durch die Rückumwandlung des Zweierkomplements eine Addition von + 1 nötig
		if(isNegativeBin) {
			integerPart += 1;
		}
		
		String integerStr = String.valueOf((long)(integerPart));
		
		if(fractionalPart != 0.0) {
			// Rückgabe mit Nachkommateil
			String fractionalStr = String.valueOf(fractionalPart).substring(2);
			String valueStr = integerStr + comma + fractionalStr;
			
			if(calcPath != null) calcPath.add(new ConversionStep("Beide Teile zusammenf\u00FCgen: " + splitInBlocks(10, integerStr) + " + " + splitInBlocks(10, "0." +fractionalStr) + " = " + splitInBlocks(10, valueStr), true));
			if(isNegative && calcPath != null) calcPath.add(new ConversionStep("Vorzeichen beachten!", true));
			
			return isNegative ? "-" + valueStr : valueStr;
		} else {
			// Rückgabe des ganzen Anteils
			if(isNegative && calcPath != null) calcPath.add(new ConversionStep("Vorzeichen beachten!", true));
			return isNegative ? "-" + integerStr : integerStr;
		}
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Umwandeln von dezimal zu beliebiger Basis																					 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator f\u00FCr Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase	Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * 
	 * @return 	umgewandelte Zahl zur \u00FCbergebenen Basis in der String-Darstellung mit dem standardm\u00E4ßig eingestellten Komma
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws NumberOverflowException		wenn der Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code decValue} of base 10 into a number of any base {@code newBase} as string representation.
	 * <p>
	 * If the indicator of decimal places is activated in the class {@code Preferences}
	 * the cut decimal places caused due the maximum number of decimal places will be shown as "..."
	 * </p>
	 * 
	 * @param newBase	Base of the new numeral system, which will the number {@code decValue} be converted in
	 * @param decValue	Value of the number in decimal system as string representation
	 * 
	 * @return Converted number of the submitted base as string representation with default set comma
	 * 
	 * @throws NullPointerException			If the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException		If the parameter {@code decValue} is not a number of base 10
	 * @throws IllegalArgumentException		If {@code decValue} is an empty string or {@code newBase} leaves the range of values [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws NumberOverflowException		if {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue) throws NullPointerException, NumberFormatException, NumberOverflowException {
		return decToBase(newBase, decValue, Preferences.getPrefs().getComma());
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator f\u00FCr Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase		Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma		das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * 
	 * @return	umgewandelte Zahl zur \u00FCbergebenen Basis in der String-Darstellung mit maximal 15 Nachkommastellen
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws NumberOverflowException		wenn der Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code decValue} of base 10 into a number of any base {@code newBase} as string representation.
	 * <p>
	 * If the indicator of decimal places is activated in the class {@code Preferences}
	 * the cut decimal places caused due the maximum number of decimal places will be shown as "..."
	 * </p>
	 * 
	 * @param newBase	Base of the new numeral system, which will the number {@code decValue} be converted in
	 * @param decValue	Value of the number in decimal system as string representation
	 * @param comma		the char which is used as comma for floating point numbers
	 * 
	 * @return Converted number of the submitted base as string representation with default set comma
	 * 
	 * @throws NullPointerException			If the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException		If the parameter {@code decValue} is not a number of base 10
	 * @throws IllegalArgumentException		If {@code decValue} is an empty string or {@code newBase} leaves the range of values [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws NumberOverflowException		if {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma) throws NullPointerException, NumberFormatException, IllegalArgumentException, NumberOverflowException {
		return decToBase(newBase, decValue, comma, 16);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator f\u00FCr Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase		Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma		das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * @param fractionalPrecision	maximale Anzahl der Nachkommastellen
	 * 
	 * @return	umgewandelte Zahl zur \u00FCbergebenen Basis in der String-Darstellung
	 * 
	 * @throws NullPointerException				wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException			wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException			wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws UnsupportedOperationException 	wenn {@code decValue} negativ ist und Nachkommastellen enth\u00E4lt und gleichzeitig {@code newBase} zwei ist 
	 * @throws NumberOverflowException		wenn der Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code decValue} of base 10 into a number of any base {@code newBase} as string representation.
	 * <p>
	 * If the indicator of decimal places is activated in the class {@code Preferences}
	 * the cut decimal places caused due the maximum number of decimal places will be shown as "..."
	 * </p>
	 * 
	 * @param newBase	Base of the new numeral system, which will the number {@code decValue} be converted in
	 * @param decValue	Value of the number in decimal system as String representation
	 * @param comma		the char which is used as comma for floating point numbers
	 * @param fractionalPrecision	maximum number of decimal places
	 * 
	 * @return Converted number of the submitted base as string representation with default set comma
	 * 
	 * @throws NullPointerException				If the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException			If the parameter {@code decValue} is not a number of base 10
	 * @throws IllegalArgumentException			If {@code decValue} is an empty String or {@code newBase} leaves the range of values [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws UnsupportedOperationException 	If {@code decValue} is a negative value with decimal places and {@code newBase} has the value two
	 * @throws NumberOverflowException		if {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma, int fractionalPrecision) throws NullPointerException, NumberFormatException, IllegalArgumentException, UnsupportedOperationException, NumberOverflowException {
		return decToBase(newBase, decValue, comma, fractionalPrecision, null);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator f\u00FCr Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase		Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma		das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * @param fractionalPrecision	maximale Anzahl der Nachkommastellen
	 * 
	 * @return	umgewandelte Zahl zur \u00FCbergebenen Basis in der String-Darstellung
	 * 
	 * @throws NullPointerException				wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException			wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException			wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws UnsupportedOperationException 	wenn {@code decValue} negativ ist und Nachkommastellen enth\u00E4lt und gleichzeitig {@code newBase} zwei ist 
	 * @throws NumberOverflowException		wenn der Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code decValue} of base 10 into a number of any base {@code newBase} as string representation.
	 * <p>
	 * If the indicator of decimal places is activated in the class {@code Preferences}
	 * the cut decimal places caused due the maximum number of decimal places will be shown as "..."
	 * </p>
	 * 
	 * @param newBase	Base of the new numeral system, which will the number {@code decValue} be converted in
	 * @param decValue	Value of the number in decimal system as String representation
	 * @param comma		the char which is used as comma for floating point numbers
	 * @param fractionalPrecision	maximum number of decimal places
	 * 
	 * @return Converted number of the submitted base as string representation with default set comma
	 * 
	 * @throws NullPointerException				If the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException			If the parameter {@code decValue} is not a number of base 10
	 * @throws IllegalArgumentException			If {@code decValue} is an empty String or {@code newBase} leaves the range of values [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws UnsupportedOperationException 	If {@code decValue} is a negative value with decimal places and {@code newBase} has the value two
	 * @throws NumberOverflowException		if {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma, int fractionalPrecision, Queue<ConversionStep> calcPath) throws NullPointerException, NumberFormatException, IllegalArgumentException, UnsupportedOperationException, NumberOverflowException {
		// Prüfen, ob decValue eine Zahl zur Basis 10 repräsentiert
		checkValue(10, decValue);
		decValue = trimToNumberString(decValue);
		
		if(newBase < MIN_BASE || newBase > MAX_BASE) {
			if(calcPath != null) {
				calcPath.clear();
				calcPath.add(new ConversionStep("Keine Umwandlung m\u00F6glich, da die Basis zu groß oder zu klein ist! Die minimale Basis ist " + MIN_BASE + " und die maximale Basis ist " + MAX_BASE));
			}
			
			throw new IllegalArgumentException("Out of Bounds for base = " + newBase + " (base must be within " + MIN_BASE + " and " + MAX_BASE + ")");
		}
		
		// Implementierung von negativen Zahlen
		boolean isNegative = decValue.startsWith("-");
		
		if (isNegative) {
			decValue = decValue.substring(1, decValue.length());
			if(calcPath != null) calcPath.add(new ConversionStep("Betrag nehmen und das Vorzeichen f\u00FCr sp\u00E4ter merken", true));
		}
		
		// ganzen Anteil und Nachkommateil (Basis 10) separieren und in long bzw. double umwandeln
		String[] separated = separateByComma(decValue);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		long integerPart;
		
		try {
			integerPart = Long.parseLong(separated[0]);
		} catch (NumberFormatException e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, e);
			
			if (!separated[0].equals("")) {
				if(calcPath != null) {
					calcPath.clear();
					calcPath.add(new ConversionStep("Keine Umwandlung m\u00F6glich, da der erlaubte Wertebereich verlassen wurde!"));
				}
				
				throw new NumberOverflowException("for Number " + decValue, "Die eingegebene Zahl liegt nicht im erlaubten Wertebereich!", Long.MAX_VALUE, -Long.MAX_VALUE, e);
			} else {
				throw e;
			}
		}
		
		double fractionalPart = Double.parseDouble("0." + separated[1]);
			
		StringBuilder newBaseValue = new StringBuilder();	// Variable, in der die String-Darstellung zur neuen Basis gespeichert wird
		
		// Sonderfall für Zahlensystem der Basis 2
		if (newBase == 2 && isNegative) {
			// Nachkommateil nicht erlaubt
			if(fractionalPart != 0) {
				if(calcPath != null) {
					calcPath.clear();
					calcPath.add(new ConversionStep("Keine Umwandlung m\u00F6glich, da nur ganze Zahlen als Zweierkomplement gespeichert werden k\u00F6nnen!"));
				}
				
				throw new UnsupportedOperationException("Nachkommateil bei bin\u00E4rer negativer Zahl");
			}
			
			if(calcPath != null) {
				calcPath.add(new ConversionStep("mit Quellenverfahren (Divisionsmethode) von Basis 10 zur neuen Basis 2 umwandeln:\n"
											  + "  -> 1. umzuwandelnde Dezimalzahl ganzzahlig durch neue Basis teilen und den Rest notieren\n"
											  + "  -> 2. ganzzahligen Divisionsanteil vom letzten Schritt ganzzahlig durch neue Basis teilen und den Rest notieren\n"
											  + "  -> 3. Schritt 2 so lange wiederholen, bis der ganzzahlige Divisionsanteil 0 ist", true));
				String newBaseStr = convertDecIntegerToBaseString(newBase, integerPart, calcPath);
				calcPath.add(new ConversionStep("=> Die Reste der ganzzahligen Division ergeben von unten nach oben gelesen die umgewandelte Zahl zur neuen Basis: " + splitInBlocks(newBase, newBaseStr)));
				calcPath.add(new ConversionStep("negative bin\u00E4re Zahlen werden im Zweierkomplement abgebildet => Zweierkomplement bilden", true));
				calcPath.add(new TwosComplement(integerPart));
			}
			
			// Umwandlung ins Zweierkomplement
			long negDec = (~integerPart) + 1;
			
			newBaseValue.append(Long.toBinaryString(negDec));
			
			// kürzen, sodass eine führende 1 bleibt
			int index = newBaseValue.indexOf("0") - 1;
				
			if (index < 0) {
				index = 0;
			}
				
			newBaseValue.delete(0, index);
			
			// umgewandelte Zahl in 2er Basis als String zurückgeben
			return newBaseValue.toString();
		}
		
		
		// Ganzen Anteil umrechnen
		if(calcPath != null && fractionalPart != 0) {
			calcPath.add(new ConversionStep("Zahl aufteilen in ganzzahligen und Nachkommateil", true));
		}
		
		if(calcPath != null) {
			calcPath.add(new ConversionStep("Ganzzahligen Anteil mit Quellenverfahren (Divisionsmethode) von Basis 10 zur neuen Basis " + newBase + " umwandeln:\n"
					  + "  -> 1. umzuwandelnde Dezimalzahl ganzzahlig durch neue Basis teilen und den Rest notieren\n"
					  + "  -> 2. ganzzahligen Divisionsanteil vom letzten Schritt ganzzahlig durch neue Basis teilen und den Rest notieren\n"
					  + "  -> 3. Schritt 2 so lange wiederholen, bis der ganzzahlige Divisionsanteil 0 ist", true));
		}
		
		StringBuilder integerStr = new StringBuilder(convertDecIntegerToBaseString(newBase, integerPart, calcPath));
		
		if(calcPath != null) calcPath.add(new ConversionStep("=> Die Reste der ganzzahligen Division ergeben von unten nach oben gelesen die umgewandelte ganze Zahl zur neuen Basis: " + splitInBlocks(newBase, integerStr.toString())));
		
		if(newBase == 2 && !(integerStr.charAt(0) == '0')) {
			integerStr.insert(0, '0');
			if(calcPath != null) {
				calcPath.add(new ConversionStep("Bei positiven Bin\u00E4rzahlen wird eine 0 vorangestellt, um diese nicht mit einer negativen Zahl im Zweierkomplement zu verwechseln:\n"
						+ "=> " + splitInBlocks(newBase, integerStr.toString()), true));
			}
		}
		
		newBaseValue.append(integerStr);
		
		
		// Wenn vorhanden Nachkommateil umwandeln
		if(fractionalPart != 0){
			if(calcPath != null) {
				calcPath.add(new ConversionStep("Gebrochenen Anteil umrechnen:\n"
											  + "  -> 1. umzurechnenden Dezimalbruch mit neuer Basis multiplizieren\n"
											  + "  -> 2. ganzer Anteil des Ergebnisses ist die n\u00E4chste Nachkommastelle\n"
											  + "  -> 3. gebrochenen Anteil des Ergebnisses erneut mit der neuen Basis multiplizieren\n"
											  + "  -> 4. Schritte 2 und 3 wiederholen, bis die Multiplikation eine ganze Zahl ergibt.\n"
											  + "        Oft wird dies nicht erreicht und die Berechnung wird nach ausreichend Stellen abgebrochen.\n"
											  + "        Es ergibt sich dann ein Abbruchfehler als Rest.", true));
			}
			
			String fractionalStr = convertDecFractionalToBaseString(newBase, fractionalPart, fractionalPrecision, comma, calcPath);
			newBaseValue.append(fractionalStr);
			
			if(calcPath != null) calcPath.add(new ConversionStep("=> Die ganzzahligen Produktanteile ergeben von oben nach unten gelesen die Nachkommastellen zur neuen Basis: " + splitInBlocks(newBase,  "0" + fractionalStr)));
			if(calcPath != null) calcPath.add(new ConversionStep("Beide Teile zusammenf\u00FCgen: " + splitInBlocks(newBase, integerStr.toString()) + " + " + splitInBlocks(newBase, "0" + fractionalStr) + " = " + splitInBlocks(newBase, newBaseValue.toString()), true));
		}
		
		if(isNegative) {
			if(calcPath != null) calcPath.add(new ConversionStep("Vorzeichen beachten!", true));
			newBaseValue.insert(0, '-');
		}
		
		return newBaseValue.toString();
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um
	 * und unterteilt die Zahl in Bl\u00F6cke der gew\u00FCnschten L\u00E4nge.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator f\u00FCr Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase				Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue				Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma					das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * @param blockSize				L\u00E4nge der Bl\u00F6cke, in die der String unterteilt wird
	 * 
	 * @return	umgewandelte Zahl zur \u00FCbergebenen Basis in der String-Darstellung
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verl\u00E4sst &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws NumberOverflowException		wenn der Wert von {@code value} gr\u00F6ßer oder kleiner ist als +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 * @see #decToBase(int, String, char)
	 * @see #splitInBlocks(String, int)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code decValue} of base 10 into a number of any base {@code newBase} as string representation
	 * and splits the number into blockt with given length.
	 * <p>
	 * If the indicator of decimal places is activated in the class {@code Preferences}
	 * the cut decimal places caused due the maximum number of decimal places will be shown as "..."
	 * </p>
	 * 
	 * @param newBase				Base of the new numeral system, which will the number {@code decValue} be converted in
	 * @param decValue				Value of the number in decimal system as string representation
	 * @param comma					the char which is used as comma for floating point numbers
	 * @param blockSize				length of the blocks into which the string is splitted
	 * 
	 * @return Converted number of the submitted base as string representation with default set comma
	 * 
	 * @throws NullPointerException			If the parameter {@code decValue} is {@code null}
	 * @throws NumberFormatException		If the parameter {@code decValue} is not a number of base 10
	 * @throws IllegalArgumentException		If {@code decValue} is an empty string or {@code newBase} leaves the range of values [2, 36] &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws NumberOverflowException		if {@code value} is greater or less +/- {@link Long#MAX_VALUE}
	 * 
	 * @see Preferences
	 * @see #decToBase(int, String, char)
	 * @see #splitInBlocks(String, int)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public static String decToBaseBlocks(int newBase, String decValue, char comma, int blockSize) throws NullPointerException, NumberFormatException, IllegalArgumentException, NumberOverflowException {
		String value = decToBase(newBase, decValue, comma);
		
		return splitInBlocks(value, blockSize);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Unterteilt den \u00FCbergebenen String in Bl\u00F6cke mit der gegebenen L\u00E4nge, beispielsweise
	 * zur Tausendertrennung
	 * 
	 * @param value		String, der aufgeteilt wird
	 * @param blockSize	L\u00E4nge der Bl\u00F6cke
	 * 
	 * @return	Aufgeteilter String mit Bl\u00F6cken
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Splits the String value into blocks of the given length. This is used e.g. to separate thousands.
	 * 
	 * @param value		String that is splitted
	 * @param blockSize	block's length
	 * 
	 * @return	splitted String with blocks
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public static String splitInBlocks(String value, int blockSize) {
		if(value.contains(FRACTIONAL_PRECISION_INDICATOR)) {
			return splitInBlocks(value.replace(FRACTIONAL_PRECISION_INDICATOR, ""), blockSize) + FRACTIONAL_PRECISION_INDICATOR;
		}
		
		String[] separated = separateByComma(value);
		
		StringBuilder integerPart = new StringBuilder(separated[0]);
		StringBuilder fractionalPart = new StringBuilder();
		
		insertSpace(integerPart.reverse(), blockSize);
		
		if( ! separated[1].equals("0")) {
			fractionalPart.append(separated[1]);
			insertSpace(fractionalPart, blockSize);
			fractionalPart.insert(0, Preferences.getPrefs().getComma());
		}

		return integerPart.reverse().toString() + fractionalPart.toString();
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Unterteilt den \u00FCbergebenen String in Bl\u00F6cke mit einer L\u00E4nge von 4 Zeichen f\u00FCr bin\u00E4re und hexadezimale Zahlen
	 * oder 3 Zeichen f\u00FCr alle anderen Zahlensysteme
	 * 
	 * @param base	Basis von {@code value}
	 * @param value	String, der aufgeteilt wird
	 * 
	 * @return	Aufgeteilter String mit Bl\u00F6cken
	 * 
	 * @since Bitchanger 0.1.8
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Splits the String value into blocks of the given length. This is used e.g. to separate thousands.
	 * 
	 * @param base	base of {@code value}
	 * @param value	String that is splitted
	 * 
	 * @return	splitted String with blocks
	 * 
	 * @since Bitchanger 0.1.8
	 */
	public static String splitInBlocks(int base, String value) {
		return splitInBlocks(value, base == 2 || base == 16 ? 4 : 3);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * \u00DCberpr\u00FCft, ob der \u00FCbergebene String {@code value} eine Zahl zur Basis {@code base} ist
	 * und wirft eine Exception, wenn dies nicht der Fall ist.
	 * 
	 * @param base	Basis, auf die {@code value} gepr\u00FCft wird
	 * @param value	String, der gepr\u00FCft wird
	 * 
	 * @throws NullPointerException		wenn {@code value} den Wert {@code null} hat
	 * @throws NumberFormatException	wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException wenn {@code basis} den Wertebereich [2, 36] verl\u00E4sst oder wenn {@code value} ein leerer String ist
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Checks if the submitted string {@code value} represents a number to the required base {@code base} and
	 * throws an exception, if this is not given.
	 * 
	 * @param base	Base which is {@code value} checked for
	 * @param value	String which is proofed 
	 * 
	 * @throws NullPointerException		If {@code value} is {@code null}
	 * @throws NumberFormatException	If the parameter {@code value} is not a number of base {@code base}
	 * @throws IllegalArgumentException If {@code basis} leaves the range of value [2, 36] or {@code value} is an empty string
	 */
	private static void checkValue(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException{
		if(value == null) {
			throw new NullPointerException("null");
		}
		
		if(value.equals("")) {
			throw new IllegalArgumentException("empty String");
		}
		
		if(!isValueToBase(base, value)) {	// Prüfen, ob value eine Zahl zur gegebenen Basis repr\u00E4sentiert
			throw new NumberFormatException("For input string: \"" + value + "\" - Value cannot be converted to base " + base + " number");
		}
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Umwandeln von beliebiger Basis nach dezimal																					 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// TODO JavaDoc @since Bitchanger 0.1.8
	private static String complementOf(String binValue) {
		StringBuilder sbNegativeBin = new StringBuilder(binValue);

		// Schleife für die Umkehrung des Zweierkomplements
		for (int i = 0; i < sbNegativeBin.length(); i++) {
			if (sbNegativeBin.charAt(i) == '1') {
				sbNegativeBin.setCharAt(i, '0');
			}
			else {
				sbNegativeBin.setCharAt(i, '1');
			}
		}

		return sbNegativeBin.toString();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Umwandeln von beliebiger Basis zu dezimal																					 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die \u00FCbergebene ganze Zahl {@code integerPart} zur beliebigen Basis {@code base} ins Zehnersystem um.
	 * Zur Umwandlung von einem beliebigen Zahlensystem in das Zehnersystem wird das Horner-Schema verwendet.
	 * 
	 * @param base			beliebige Basis des Zahlensystems von {@code integerPart} im Bereich [2; 36]
	 * @param integerPart	umzuwandelnde Zahl in der String-Darstellung
	 * @return				Wert der \u00FCbergebenen Zahl im Zehnersystem als {@code double}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code integerPart} of any base  {@code base} into the decimal system.
	 * For converting of any base into the decimal system, Horner-Schema will be used.
	 * 
	 * @param base			Any base of the numeral system of {@code integerPart} in the range [2; 36]
	 * @param integerPart	Number which will be converted as string representation
	 * @return				Value of the submitted number as decimal representation as{@code double}
	 */
	private static double baseToDecIntPart(int base, String integerPart, Queue<? super HornersMethod> calcSteps) {
		ArrayList<Double> coefficients = new ArrayList<>();
		
		for(char digit : integerPart.toCharArray()) {
			coefficients.add((double) valueOfDigit(digit));
		}
		
		HornersMethod horner = new HornersMethod(coefficients, base);
		if (calcSteps != null) calcSteps.add(horner);
		
		return horner.getResult();
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt den \u00FCbergebenen Nachkommateil einer Zahl zu einer beliebigen Basis ins Zehnersystem um.
	 * Zur Umwandlung von einem beliebigen Zahlensystem in das Zehnersystem wird das Horner-Schema verwendet.
	 * 
	 * @param base				beliebige Basis des Zahlensystems von {@code fractionalPart} im Bereich [2; 36]
	 * @param fractionalPart	umzuwandelnder Nachkommateil einer Zahl <b>ohne Komma</b> und als ganze Zahl in der String-Darstellung 
	 * @return					Wert des \u00FCbergebenen Nachkommaanteils im Zehnersystem als {@code double}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted decimal place of a number of any base into the decimal system.
	 * For converting of any base into the decimal system, Horner-Schema will be used.
	 * 
	 * @param base				Any base of the numeral system of {@code fractionalPart} in the range [2; 36]
	 * @param fractionalPart	Decimal places of a number which will be converted <b>without comma</b> as an integer as string representation 
	 * @return					Value of the submitted decimal place as decimal representation as{@code double}
	 */
	private static double baseToDecFractionalPart(int base, String fractionalPart, Queue<ConversionStep> calcSteps) {
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		// dafür muss die Reihenfolge der Ziffern umgekehrt und eine 0 angehangen werden
		StringBuilder sb = new StringBuilder(fractionalPart);
		sb.reverse();
		sb.append(0);
		
		if(calcSteps != null) {
			calcSteps.add(new ConversionStep("Reihenfolge der Ziffern umkehren:\n"
										   + "0" + Preferences.getPrefs().getComma() + fractionalPart + "  =>  " + insertSpace(new StringBuilder(sb.toString()), 1)));
		}
		
		ArrayList<Double> coefficients = new ArrayList<>();
		
		sb.chars()
		  .mapToDouble(digit -> { return valueOfDigit((char) digit); })
		  .forEach(coefficients::add);
		
		HornersMethod horner = new HornersMethod(coefficients, 1.0/base);
		
		if(calcSteps != null) calcSteps.add(horner);
		
		return horner.getResult();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Umwandeln von dezimal zu beliebiger Basis																					 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt einen ganzzahligen Wert vom Zehnersystem in einen Wert zu einer beliebigen Basis in der String-Darstellung um.
	 * 
	 * @param newBase		Basis des neuen Zahlensystems
	 * @param integerPart	ganzzahligen Wert im Zehnersystem
	 * @param calcSteps		Liste, in der alle einzelnen Rechenschritte gespeichert werden. {@code null}-Werte sind erlaubt und werden ignoriert
	 * 
	 * @return	umgewandelter Wert zur Basis {@code basis} in String-Darstellung
	 * 
	 * @since Bitchanger 0.1.8
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts an integer value of decimal system into a value of any base as string representation.
	 * 
	 * @param newBase		Base of the new numeral system
	 * @param integerPart	Integer value of decimal system
	 * @param calcSteps		List that is used to store every calculation step. {@code null} is allowed and will be ignored
	 * 
	 * @return	Returned value of base {@code basis} as string representation
	 * 
	 * @since Bitchanger 0.1.8
	 */
	private static String convertDecIntegerToBaseString(int newBase, long integerPart, Queue<? super LongDivision> calcSteps) {
		if(integerPart == 0L){
			return "0";
		}
		
		// String in dem das Ergebnis gespeichert wird initialisieren
		StringBuffer ergebnisGanz = new StringBuffer("");
		
		// Berechnen der Stellen vor dem Komma mit dem Quellenverfahren
		// Schleife durchlaufen, bis ganzerAnteil <= 0
		while(!(integerPart <= 0L)){
			LongDivision div = new LongDivision(integerPart, newBase);
			
			// Der Rest der ganzzahligen LongDivision ist auch gleich dem Wert der jeweiligen Stelle
			int remainder = (int) div.getRemainder();
			
			// Zaehler fuer naechsten Durchlauf aktualisieren
			integerPart = div.getQuotient();
			
			char digit = digitOfValue(remainder);
			
			div.setDigit(digit);
			
			if(calcSteps != null) {
				calcSteps.offer(div);
			}
			
			/* Das letzte Ergebnis ergibt die erste Stelle, daher muss der 
			 * vorige String hinter der aktuellen Stelle stehen */
			ergebnisGanz.insert(0, digit);
		}
		
		return ergebnisGanz.toString();
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt einen Nachkommateil vom Zehnersystem in einen Wert zu einer beliebigen Basis in der String-Darstellung um.
	 * Wenn in der Klasse {@code Preferences} der Indikator f\u00FCr Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * 
	 * @param newBase				Basis des neuen Zahlensystems
	 * @param fractionalPart		Nachkommateil im Zehnersystem
	 * @param fractionalPrecision	Maximale Anzahl von Nachkommastellen im Ergebnis
	 * @param comma					Zeichen, das als Komma vor dem Nachkommateil platziert wird
	 * 
	 * @return	umgewandelter Nachkommateil zur neuen Basis in String-Darstellung mit f\u00FChrendem Komma
	 * 
	 * @since Bitchanger 0.1.8
	 * 
	 * @see Preferences#setIndicateFractionalPrecision(boolean)
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts decimal places of decimal system into a value of any base as string representation.
	 * If the indicator of decimal places is activated in the class {@code Preferences}
	 * the cut decimal places caused due the maximum number of decimal places will be shown as "..."
	 * 
	 * @param newBase				Base of the new numeral system
	 * @param fractionalPart		Decimal places of the decimal system
	 * @param fractionalPrecision	Maximum number of decimal places in the result
	 * @param comma					Character that is placed as comma before the part of decimal places
	 * 
	 * @return	converted decimal places of the new base with leading comma as string representation
	 * 
	 * @since Bitchanger 0.1.8
	 * 
	 * @see Preferences#setIndicateFractionalPrecision(boolean)
	 */
	private static String convertDecFractionalToBaseString(int newBase, double fractionalPart, int fractionalPrecision, char comma, Queue<? super Multiplication> calcSteps) {
		// Berechnen der Stellen nach dem Komma mit Hilfe von Multiplikation mit der Basis
		
		if(fractionalPrecision <= 0) {
			// Kein Nachkommaanteil
			return "";
		}
		
		// String in dem das Ergebnis gespeichert wird
		StringBuffer fractionalResult = new StringBuffer(String.valueOf(comma));
		
		// Hilfsvariable zum Abbrechen nach x Nachkommastellen
		int zaehl = 0;
		
		while(!(fractionalPart % 1 == 0)){
			if (zaehl >= fractionalPrecision) {
				if (Preferences.getPrefs().indicateFractionalPrecision()) {
					fractionalResult.append(FRACTIONAL_PRECISION_INDICATOR);
					
					if(calcSteps != null) {
						calcSteps.offer(new Multiplication());
					}
				}
				break;
			}
			
			Multiplication mult = new Multiplication(fractionalPart, newBase);
			
			int stelle = (int) mult.getProduct();
			fractionalPart =  mult.getProduct() % 1;
			
			char ziffer = digitOfValue(stelle);
			
			mult.setDigit(ziffer);
			
			if(calcSteps != null) {
				calcSteps.offer(mult);
			}
			
			fractionalResult.append(ziffer);
			
			zaehl++;	// Hilfsvariable zum Abbrechen nach x Nachkommastellen
			
		}
		
		return fractionalResult.toString();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Berechnung von Stellenwertigkeiten																							 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Berechnet das Zeichen f\u00FCr eine Stellenwertigkeit.
	 * Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9, die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35.
	 * @param value		Wert der gesuchten Stelle zur Basis 10
	 * @return			Zeichen, das die Stellenwertigkeit des \u00FCbergebenen Wertes repr\u00E4sentiert
	 * 
	 * @see #valueOfDigit(char)
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Calculates the char for a value.
	 * The characters 0 to 9 correspond to the values 0 to 9 - The letters A to Z correspond to the values 10 to 35.
	 * @param value		Value of the position sought on decimal system
	 * @return			Character that shows the place value of the submitted value
	 * 
	 * @see #valueOfDigit(char)
	 */
	private static char digitOfValue(int value){
		// Ein Überlauf ist möglich, wenn Die Stellenwertigkeit größer als 35 ist und wird nicht abgefangen!
		if(value >= 10){
			return (char)('A' + value - 10);
		} else {
			return (char)('0' + value);
		}
	}

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Berechnet die Stellenwertigkeit, die von einem Zeichen repr\u00E4sentiert wird.
	 * Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9, die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35.
	 * @param digit		Zeichen, dessen Wertigkeit berechnet wird
	 * @return			Stellenwertigkeit, die von {@code digit} repr\u00E4sentiert wird
	 * 
	 * @see #digitOfValue(int)
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Calculates the place value of represented character.
	 * The characters 0 to 9 correspond to the values 0 to 9 - The letters A to Z correspond to the values 10 to 35.
	 * @param digit		Character whose value will be calculated 
	 * @return			Place value that is represented by {@code digit}
	 * 
	 * @see #digitOfValue(int)
	 */
	private static int valueOfDigit(char digit) {
		// Berechnung der Wertigkeit, die das Zeichen repraesentiert
		if(digit >= '0' && digit <= '9') {
			return digit - '0';
		} else {
			return digit - 'A' + 10;
		}
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Zerlegung in ganzen und Nachkommateil																						 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Separiert den Anteil vor und nach dem Komma bzw. Punkt (je nach Format) des \u00FCbergebenen String {@code value}, 
	 * der eine Zahl zu der Basis {@code base} repr\u00E4sentiert, und gibt diese beiden separierten Strings ohne f\u00FChrende 0
	 * im Nachkommateil zur\u00FCck.
	 * 
	 * @param value	Zahl, die zerlegt werden soll
	 * 
	 * @return	ganzen Anteil im Index 0 und Nachkommateil im Index 1, jeweils als ganze Zahl in der String-Darstellung
	 * 
	 * @throws NumberFormatException	Wenn es sich bei {@code value} nicht um eine Zahl in der String-Darstellung handelt, da zu viele Kommata vorhanden sind
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Separated the numbers before and after the comma / point of the submitted string {@code value}, 
	 * which represents a number of the base {@code base} and returns these separated strings without
	 * leading 0 in the decimal part.
	 * 
	 * @param value	Number to be disassembled
	 * 
	 * @return	Integer in index 0 and decimal place in index 1, both as integer as string representation
	 * 
	 * @throws NumberFormatException	If {@code value} is not a number represented as string, caused due to many commas
	 */
	private static String[] separateByComma(String value) throws NumberFormatException {
		String integerPart = "";
		String fractionalPart = "";
		
		// Separator einstellen
		String separator = getFirstComma(value);
		
		// Trennung vorhanden -> potentielle Gleitpunktzahl aufspalten
		if(separator != null) {
			// Scanner, der Ganzen- und Nachkommaanteil separiert
			Scanner sc = new Scanner(value);
			sc.useDelimiter(separator);
			
			integerPart = sc.next();
			fractionalPart = sc.next();	
			
			// Delimiter zurücksetzen und leeren, um fehlerhafte Eingaben nicht zu übergehen
			sc.reset();
			if(sc.hasNext()) {
				sc.close();
				throw new NumberFormatException("For input string: \"" + value + "\" - Cannot be converted to a number");
			}
			
			// Scanner schließen
			sc.close();
		} else {
			// Keine Trennung durch Komma oder Punkt, eventuell ganze Zahl
			integerPart = value;
			fractionalPart = "0";
		}
		
		String[] separated = {integerPart, fractionalPart};
		return separated;
	}

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * \u00FCberpr\u00FCft, ob bei dem \u00FCbergebenen String zuerst ein deutsches Komma (,) oder ein englisches Komma (.) auftaucht.
	 * 
	 * @param value	String, der auf Kommata \u00FCberpr\u00FCft wird
	 * 
	 * @return das Komma, welches zuerst in dem String auftaucht oder {@code null}, wenn kein Komma enthalten ist
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Checks if the submitted string firstly contains a German comma (,) or an English comma (.).
	 * 
	 * @param value	String, which is checked if it contains a comma
	 * 
	 * @return the comma which is on the front position or {@code null}if it does not contain a comma
	 */
	private static String getFirstComma(String value) {
		// erstes Komma in String suchen
		int firstIndexOfEnComma = value.indexOf(Comma.COMMA_EN.get());
		int firstIndexOfDeComma = value.indexOf(Comma.COMMA_DE.get());
		
		String comma = null;

		if (firstIndexOfEnComma >= 0 && firstIndexOfDeComma < 0) {
			comma = "\\.";
		} else if (firstIndexOfDeComma >= 0 && firstIndexOfEnComma < 0) {
			comma = ",";
		}
		return comma;
	}	
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Schneidet den \u00FCbergebenen String so zu, dass dieser nicht mehr den Indikator f\u00FCr abgeschnittene Nachkommastellen
	 * und keine Leerzeichen enth\u00E4lt und wandelt alle Buchstaben in Großbuchstaben um.
	 * 
	 * @param value String, der formatiert werden soll
	 * @return	zugeschnittener String
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Deletes the FRACTIONAL_PRECISION_INDICATOR and spaces and converts all letters to uppercase.
	 * Returns the formatted String.
	 * 
	 * @param value String to be formatted
	 * @return	formatted String
	 */
	private static String trimToNumberString(String value) {
		/* Es wird nur mit Großbuchstaben in Zahlensystemen mit Basis größer 10 gearbeitet, Leerzeichen und 
		 * der Indikator für abgeschnittene Nachkommastellen werden ignoriert */
		return value.replace(FRACTIONAL_PRECISION_INDICATOR, "").replace(" ", "").toUpperCase();
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * Unterteilt den \u00FCbergebenen StringBuffer in Bl\u00F6cke mit bestimmter L\u00E4nge
	 * 
	 * @param sb		StringBuffer, der unterteilt wird
	 * @param blockSize L\u00E4nge der Bl\u00F6cke
	 * 
	 * @return den ver\u00E4nderten StringBuffer, der auch \u00FCbergeben wurde
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Splits the StringBuffer sb into blocks with given length
	 * 
	 * @param sb		StringBuffer that gets split
	 * @param blockSize block's length
	 * 
	 * @return modified StringBuffer, that is same as the argument
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private static StringBuilder insertSpace(StringBuilder sb, int blockSize) {
		for(int i = blockSize; i < sb.length(); i += blockSize + 1) {
			sb.insert(i, " ");
		}
		
		return sb;
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private ConvertingNumbers() {}

}
