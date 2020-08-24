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

import java.util.Scanner;
import bitchanger.preferences.Preferences;
import bitchanger.preferences.Comma;

/**	<!-- $LANGUAGE=DE -->
 * Die Klasse {@code ConvertingNumbers} enthält Methoden zum Umwandeln von Zahlen mit verschiedenen Zahlensystemen.
 * <p>
 * Die Methoden dieser Klasse können sowohl ganze Zahlen als auch Kommazahlen mit positiven oder negativen Wert umwandeln.
 * Es werden beliebige Zahlensysteme von der Basis 2 bis zur Basis 36 unterstützt, was dem Zeichenvorrat 0-9 und A-Z entspricht.
 * In der String-Darstellung können Zahlen mit Ziffern, Großbuchstaben und Kleinbuchstaben an die Methoden dieser Klasse
 * übergeben werden, allerdings enthalten die Rückgaben in der String-Darstellung aller Methoden ausschließlich Zahlen 
 * und Großbuchstaben.
 * </p>
 * <p>
 * Das Komma-Zeichen kann mit der Klasse {@code Preferences} auf Deutsch (,) oder Englisch (.) eingestellt werden.
 * Ebenso kann der Indikator für abgeschnittene Nachkommastellen (...) aktiviert oder deaktiviert werden.
 * </p>
 * 
 * @see Preferences
 * 
 * @author Tim Mühle
 * @author Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
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
 * @version 0.1.4
 */

public class ConvertingNumbers {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE --> Eine Konstante für die kleinstmögliche Basis **/
	/*	<!-- $LANGUAGE=EN --> Constant for the lowest possible base **/
	public static final int MIN_BASE = 2;
	
	/** <!-- $LANGUAGE=DE --> Eine Konstante für die größtmögliche Basis bei einem Zeichenvorrat von 0-9 und A-Z **/
	/*	<!-- $LANGUAGE=EN --> Constant for the highest possible base of 36 characters given through 0-9 and A-Z **/
	public static final int MAX_BASE = 36;
	
	/** <!-- $LANGUAGE=DE --> Indikator für Abgeschnittene Nachkommastellen bei der Umwandlung vom Zehnersystem in ein anderes, beliebiges Zahlensystem **/
	/*	<!-- $LANGUAGE=EN --> Indicator for the cut fractional part in case of converting from decimal system into a system of any base **/
	public static final String FRACTIONAL_PRECISION_INDICATOR = "...";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// 	Überprüfung von potentiellen Zahlen zu einer Basis	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/** <!-- $LANGUAGE=DE -->
	 * Prüft, ob {@code value} eine Zahl zur geforderten Basis {@code base} repräsentiert.
	 * Ist der Indikator für abgeschnittene Nachkommastellen aktiviert, wird dieser bei der Auswertung ignoriert.
	 * Leerzeichen können als Tausender-Trennung verwendet werden und werden von dieser Methode ebenfalls ignoriert.
	 * 
	 * @param value	zu prüfende Zahl repräsentiert als {@code String}
	 * @param base	geforderte Basis von {@code value} - erlaubt sind nur Werte von 2 bis 36 (einschließlich)
	 * 
	 * @return 	{@code true}, wenn {@code value} eine Zahl zur geforderten Basis {@code base} 
	 * 			repräsentiert, andernfalls wird {@code false} zurückgegeben
	 * 
	 * @throws IllegalArgumentException	wenn {@code basis} den Wertebereich [2, 36] verlässt
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
	
	
	
// 	Umwandeln von beliebiger Basis nach dezimal	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code value} zur beliebigen Basis {@code base}  in eine Zahl zur Basis 10 als {@code double} um.
	 * 
	 * @param base	die spezifische Basis des übergebenen Wertes {@code value}
	 * @param value	der Zahlenwert, der umgewandelt werden soll, in der String-Darstellung
	 * 
	 * @return	Wert der Zahl im Zehnersystem als {@code double}
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code value} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code basis} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code value} of any base {@code base} into a number of the base 10 in {@code double}.
	 * 
	 * @param base	Specific base of the submitted value {@code value}
	 * @param value	The numerical value, which should be converted as String representation
	 * 
	 * @return Value of the number in decimal system as {@code double}
	 * 
	 * @throws NullPointerException			If the parameter {@code value} is {@code null}
	 * @throws NumberFormatException		If the parameter {@code value} is not a number of base {@code base}
	 * @throws IllegalArgumentException		If {@code value} is an empty String or {@code basis} leaves the range of value [2, 36]  &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
	 */
	// &#160; = geschütztes Leerzeichen
	public static double baseToDec(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
		checkValue(base, value);
		value = trimToNumberString(value);	// Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet
		
		
		// Bei negativen Zahlen wird das Minuszeichen zuerst entfernt, damit die Zahl wie gewohnt bearbeitet werden kann.
		boolean isNegative = value.startsWith("-");
		
		// Wenn eine Binäreingabe mit '1' beginnt, dann ist diese Zahl eine negative Binärzahl -> Zweierkomplement 
		boolean isNegativeBin = (base == 2 && value.startsWith("1"));
		
		// Abfrage auf illegales Zeichen (Minus-Zeichen) im Binärfeld
		boolean illegalCharMinus = (base == 2 && value.contains("-"));
		
		// TODO Kann nach Verbot von Minus Zeichen in JAVAFX hier entfernt werden!! Wenn nicht dann Exception hinzufügen -> Auch JavaDoc!
		if(illegalCharMinus) {
			value = "";
		}
		
		if(isNegative) {
			
			StringBuffer sbNegative = new StringBuffer(value);
			
			sbNegative.deleteCharAt(0);
			
			value = sbNegative.toString();
		}
		
		if(isNegativeBin) {
			
			// Abfrage auf illegales Zeichen (COMMA-Abfrage bei negativen Binärzahlen) im Binärfeld
			// TODO Comma aus Preferences hinzufügen, wie - es wird eine CharSequence benötigt? Außerdem Exception hinzufügen!
			boolean illegalCommaBin = value.contains(String.valueOf(Preferences.getPrefs().getComma()));
			
			if(illegalCommaBin) {
				value = "";
			}
			
			StringBuffer sbNegativeBin = new StringBuffer(value);
			
			// Schleife für die Umkehrung des Zweierkomplements
			for (int i = 0; i < sbNegativeBin.length(); i++) {
				
				if(sbNegativeBin.charAt(i) == '1') {
					
					sbNegativeBin.setCharAt(i, '0');
					
				}else {
					
					sbNegativeBin.setCharAt(i, '1');
					
				}
				
			}
			
			value = sbNegativeBin.toString();
			isNegative = true;
		}
		
		// Uebergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		String[] separated = separateByComma(value);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		// Strings, die Ganzen- und Nachkommateil zu der uebergebenen Basis repraesentieren, in double Zahlen zur Basis 10 umwandeln
		double integerPart = baseToDecIntPart(base, separated[0]);
		double fractionalPart = baseToDecFractionalPart(base, separated[1]);
		
		// Bei negativen Binärzahlen ist durch die Rückumwandlung des Zweierkomplements eine Subtraktion von -1 nötig
		if(isNegativeBin) {
			
			integerPart = integerPart+1;
		}
		
		// Vor- und Nachkommateil addieren und Ergebnis zurueckgeben
		if((integerPart + fractionalPart) >= Double.MAX_VALUE) {
			throw new UnsupportedOperationException("for value \"" + value + "\" - value must be within the double value range");
		}
		if(isNegative  ||  isNegativeBin) {
			
			return -integerPart-fractionalPart;
			
		}else
		
		return integerPart + fractionalPart;
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code value} zur beliebigen Basis {@code base} in eine Zahl zur Basis 10 in der String-Darstellung um.
	 * 
	 * @param base	die spezifische Basis des übergebenen Wertes {@code value}
	 * @param value	der Zahlenwert, der umgewandelt werden soll, in der String-Darstellung
	 * 
	 * @return	Wert der Zahl im Zehnersystem in der String-Darstellung mit dem standardmäßig eingestellten Komma
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code value} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code basis} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
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
	 */
	public static String baseToDecString(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		return baseToDecString(base, value, Preferences.getPrefs().getComma());
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code value} zur beliebigen Basis {@code base} in eine Zahl zur Basis 10 in der String-Darstellung um.
	 * 
	 * @param base	die spezifische Basis des übergebenen Wertes {@code value}
	 * @param value	der Zahlenwert, der umgewandelt werden soll, in der String-Darstellung
	 * @param comma	das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * 
	 * @return	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code value} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code basis} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int base, String value)}
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
	 */
	public static String baseToDecString(int base, String value, char comma) throws NullPointerException, NumberFormatException, IllegalArgumentException {	
		// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
		checkValue(base, value);
		value = trimToNumberString(value); // Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet
		
		
		// Bei negativen Zahlen wird das Minuszeichen zuerst entfernt, damit die Zahl wie gewohnt bearbeitet werden kann.
		boolean isNegative = value.startsWith("-");
		
		// Wenn eine Binäreingabe mit '1' beginnt, dann ist diese Zahl eine negative Binärzahl -> Zweierkomplement 
		boolean isNegativeBin = (base == 2 && value.startsWith("1"));
		
		// Abfrage auf illegales Zeichen (Minus-Zeichen) im Binärfeld
		boolean illegalCharMinus = (base == 2 && value.contains("-"));
		
		// TODO Kann nach Verbot von Minus Zeichen in JAVAFX hier entfernt werden!! Wenn nicht dann Exception hinzufügen -> Auch JavaDoc!
		if(illegalCharMinus) {
			value = "";
		}
		
		if (isNegative) {
			StringBuffer sb = new StringBuffer(value);

			sb.deleteCharAt(0);

			value = sb.toString();
		}
		
		if(isNegativeBin) {
			
			// Abfrage auf illegales Zeichen (COMMA-Abfrage bei negativen Binärzahlen) im Binärfeld
			// TODO Comma aus Preferences hinzufügen, wie - es wird eine CharSequence benötigt? Außerdem Exception hinzufügen!
			boolean illegalCommaBin = value.contains(String.valueOf(Preferences.getPrefs().getComma()));
			
			if(illegalCommaBin) {
				value = "";
			}
			
			StringBuffer sbNegativeBin = new StringBuffer(value);
			
			// Schleife für die Umkehrung des Zweierkomplements
			for (int i = 0; i < sbNegativeBin.length(); i++) {
				
				if(sbNegativeBin.charAt(i) == '1') {
					
					sbNegativeBin.setCharAt(i, '0');
					
				}else {
					
					sbNegativeBin.setCharAt(i, '1');
					
				}
				
			}
			
			value = sbNegativeBin.toString();
			isNegative = true;
		}
		
		// Uebergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		String[] separated = separateByComma(value);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		// Strings die ganzen und Nachkommateil zu der uebergebenen Basis 
		// repraesentieren in double Zahlen zur Basis 10 umwandeln
		double integerPart = baseToDecIntPart(base, separated[0]);
		double fractionalPart = baseToDecFractionalPart(base, separated[1]);
		
		// Bei negativen Binärzahlen ist durch die Rückumwandlung des Zweierkomplements eine Subtraktion von -1 nötig
		if(isNegativeBin) {
					
			integerPart = integerPart+1;
		}
		
		if(fractionalPart != 0.0) {
			// Ja -> Rueckgabe mit Nachkommateil
			// Abfrage ob es sich um eine negative oder positive Zahl handelt, danach richtet sich die Rückgabe
			return isNegative ? (String.valueOf((long)(-integerPart)) + comma + String.valueOf(fractionalPart).substring(2))
					: (String.valueOf((long)integerPart) + comma + String.valueOf(fractionalPart).substring(2));
		} else {
			// Nein -> Rueckgabe des ganzen Anteils
			// Abfrage ob es sich um eine negative oder positive Zahl handelt, danach richtet sich die Rückgabe
			return isNegative ? String.valueOf(-(long)integerPart) : String.valueOf((long)integerPart);
		}
	}

	

// 	Umwandeln von dezimal zu beliebiger Basis	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator für Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase	Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * 
	 * @return 	umgewandelte Zahl zur übergebenen Basis in der String-Darstellung mit dem standardmäßig eingestellten Komma
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
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
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue) throws NullPointerException, NumberFormatException {
		return decToBase(newBase, decValue, Preferences.getPrefs().getComma());
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator für Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase		Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma		das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * 
	 * @return	umgewandelte Zahl zur übergebenen Basis in der String-Darstellung mit maximal 15 Nachkommastellen
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
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
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		return decToBase(newBase, decValue, comma, 16);
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator für Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase		Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma		das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * @param fractionalPrecision	maximale Anzahl der Nachkommastellen
	 * 
	 * @return	umgewandelte Zahl zur übergebenen Basis in der String-Darstellung
	 * 
	 * @throws NullPointerException				wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException			wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException			wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * @throws UnsupportedOperationException 	wenn {@code decValue} negativ ist und Nachkommastellen enthält und gleichzeitig {@code newBase} zwei ist 
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
	 * @throws UnsupportedOperationException 	
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma, int fractionalPrecision) throws NullPointerException, NumberFormatException, IllegalArgumentException, UnsupportedOperationException {
		// Prüfen, ob decValue eine Zahl zur Basis 10 repräsentiert
		checkValue(10, decValue);
		decValue = trimToNumberString(decValue);
		
		if(newBase < MIN_BASE || newBase > MAX_BASE) {
			throw new IllegalArgumentException("Out of Bounds for base = " + newBase + " (base must be within " + MIN_BASE + " and " + MAX_BASE + ")");
		}
	
		// Implementierung von negativen Zahlen
		boolean isNegative = decValue.startsWith("-");
		
		if (isNegative) {
			StringBuffer sb = new StringBuffer (decValue);
			
			sb.deleteCharAt(0);
		
			decValue = sb.toString();
		}
			
		// ganzen Anteil und Nachkommateil (Basis 10) separieren und in long bzw. double umwandeln
		String[] separated = separateByComma(decValue);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
			
		long integerPart = Long.parseLong(separated[0]);
		double fractionalPart = Double.parseDouble("0." + separated[1]);
			
		StringBuffer newBaseValue = new StringBuffer();	// Variable, in der die String-Darstellung zur neuen Basis gespeichert wird
			
			
		// Sonderfall für Zahlensystem der Basis 2
			
		if (newBase == 2 && isNegative) {

			//Umwandlung ins Zweierkomplement
			long negDec = (~integerPart)+1;
				
			String negBinString = Long.toBinaryString(negDec);
			
			newBaseValue.append(negBinString);
				
			int index = newBaseValue.indexOf("0")-1;
				
			if (index < 0) {
				index = 0;
			}
				
			newBaseValue.delete(0, index);

				
			// Wenn Nachkommateil vorhanden, dann binär leeren String ausgeben
			if(fractionalPart != 0) {
				throw new UnsupportedOperationException("Nachkommateil bei binärer negativer Zahl");
			}
				
			// umgewandelte Zahl in 2er Basis als String zurückgeben
			return newBaseValue.toString();
		
		} else {
			
			// Ganzen Anteil umrechnen
			newBaseValue.append(convertDecIntegerToBaseString(newBase, integerPart));
				
			// Wenn vorhanden Nachkommateil umwandeln
			if(fractionalPart != 0){
				String newBaseFractionalPart = convertDecFractionalToBaseString(newBase, fractionalPart, fractionalPrecision, comma);
					
				newBaseValue.append(newBaseFractionalPart);
			}
				
			if(isNegative) {	
				newBaseValue.insert(0, '-');
			}
			
			if(newBase == 2) {
				newBaseValue.insert(0, '0');
			}
				
			// umgewandelte Zahl in der neuen Basis als String zurückgeben
			return newBaseValue.toString();
		}
	
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um
	 * und unterteilt die Zahl in Blöcke der gewünschten Länge.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator für Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase				Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue				Wert der Zahl im Zehnersystem in der String-Darstellung
	 * @param comma					das Zeichen, welches als Komma in Gleitpunktzahlen verwendet wird
	 * @param blockSize				Länge der Blöcke, in die der String unterteilt wird
	 * 
	 * @return	umgewandelte Zahl zur übergebenen Basis in der String-Darstellung
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code decValue} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
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
	 * 
	 * @see Preferences
	 * @see #decToBase(int, String, char)
	 * @see #splitInBlocks(String, int)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public static String decToBaseBlocks(int newBase, String decValue, char comma, int blockSize) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		String value = decToBase(newBase, decValue, comma);
		
		if(value.contains(FRACTIONAL_PRECISION_INDICATOR)) {
			return splitInBlocks(value.replace(FRACTIONAL_PRECISION_INDICATOR, ""), blockSize) + FRACTIONAL_PRECISION_INDICATOR;
		}
		
		return splitInBlocks(value, blockSize);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Unterteilt den übergebenen String in Blöcke mit der gegebenen Länge, beispielsweise
	 * zur Tausendertrennung
	 * 
	 * @param value		String, der aufgeteilt wird
	 * @param blockSize	Länge der Blöcke
	 * 
	 * @return	Aufgeteilter String mit Blöcken
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
		String[] separated = separateByComma(value);
		
		StringBuffer integerPart = new StringBuffer(separated[0]);
		StringBuffer fractionalPart = new StringBuffer();
		
		insertSpace(integerPart.reverse(), blockSize);
		
		if( ! separated[1].equals("0")) {
			fractionalPart.append(separated[1]);
			insertSpace(fractionalPart, blockSize);
			fractionalPart.insert(0, Preferences.getPrefs().getComma());
		}


		return integerPart.reverse().toString() + fractionalPart.toString();
	}
	
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Überprüft, ob der übergebene String {@code value} eine Zahl zur Basis {@code base} ist
	 * und wirft eine Exception, wenn dies nicht der Fall ist.
	 * 
	 * @param base	Basis, auf die {@code value} geprüft wird
	 * @param value	String, der geprüft wird
	 * 
	 * @throws NullPointerException		wenn {@code value} den Wert {@code null} hat
	 * @throws NumberFormatException	wenn der Parameter {@code value} keine Zahl zur Basis {@code base} ist
	 * @throws IllegalArgumentException wenn {@code basis} den Wertebereich [2, 36] verlässt oder wenn {@code value} ein leerer String ist
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
		
		if(!isValueToBase(base, value)) {	// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
			throw new NumberFormatException("For input string: \"" + value + "\" - Value cannot be converted to base " + base + " number");
		}
	}
	
	
	
// 	Umwandeln von beliebiger Basis nach dezimal	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt die übergebene ganze Zahl {@code integerPart} zur beliebigen Basis {@code base} ins Zehnersystem um.
	 * Zur Umwandlung von einem beliebigen Zahlensystem in das Zehnersystem wird das Horner-Schema verwendet.
	 * 
	 * @param base			beliebige Basis des Zahlensystems von {@code integerPart} im Bereich [2; 36]
	 * @param integerPart	umzuwandelnde Zahl in der String-Darstellung
	 * @return				Wert der übergebenen Zahl im Zehnersystem als {@code double}
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted number {@code integerPart} of any base  {@code base} into the decimal system.
	 * For converting of any base into the decimal system, Horner-Schema will be used.
	 * 
	 * @param base			Any base of the numeral system of {@code integerPart} in the range [2; 36]
	 * @param integerPart	Number which will be converted as string representation
	 * @return				Value of the submitted number as decimal representation as{@code double}
	 */
	private static double baseToDecIntPart(int base, String integerPart) {
		char[] digits = integerPart.toCharArray();
		double sum = 0;
		
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		for(int i = 0; i < digits.length; i++) {
			sum *= base;
			sum += valueOfDigit(digits[i]);
		}
		
		return sum;
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt den übergebenen Nachkommateil einer Zahl zu einer beliebigen Basis ins Zehnersystem um.
	 * Zur Umwandlung von einem beliebigen Zahlensystem in das Zehnersystem wird das Horner-Schema verwendet.
	 * 
	 * @param base				beliebige Basis des Zahlensystems von {@code fractionalPart} im Bereich [2; 36]
	 * @param fractionalPart	umzuwandelnder Nachkommateil einer Zahl <b>ohne Komma</b> und als ganze Zahl in der String-Darstellung 
	 * @return					Wert des übergebenen Nachkommaanteils im Zehnersystem als {@code double}
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Converts the submitted decimal place of a number of any base into the decimal system.
	 * For converting of any base into the decimal system, Horner-Schema will be used.
	 * 
	 * @param base				Any base of the numeral system of {@code fractionalPart} in the range [2; 36]
	 * @param fractionalPart	Decimal places of a number which will be converted <b>without comma</b> as an integer as string representation 
	 * @return					Value of the submitted decimal place as decimal representation as{@code double}
	 */
	private static double baseToDecFractionalPart(int base, String fractionalPart) {
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		// dafür muss die Reihenfolge der Ziffern umgekehrt und eine 0 angehangen werden
		StringBuffer sb = new StringBuffer(fractionalPart);
		sb.reverse();
		sb.append(0);
		
		double sum = 0;
		
		for(int i = 0; i < sb.length(); i++) {
			sum *= 1.0/base;
			sum += valueOfDigit(sb.charAt(i));
		}
		
		return sum;
	}
	
	
	
// 	Umwandeln von dezimal zu beliebiger Basis	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt einen ganzzahligen Wert vom Zehnersystem in einen Wert zu einer beliebigen Basis in der String-Darstellung um.
	 * 
	 * @param newBase		Basis des neuen Zahlensystems
	 * @param integerPart	ganzzahligen Wert im Zehnersystem
	 * 
	 * @return	umgewandelter Wert zur Basis {@code basis} in String-Darstellung
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * Converts an integer value of decimal system into a value of any base as string representation.
	 * 
	 * @param newBase		Base of the new numeral system
	 * @param integerPart	Integer value of decimal system
	 * 
	 * @return	Returned value of base {@code basis} as string representation
	 */
	private static String convertDecIntegerToBaseString(int newBase, long integerPart) {
		if(integerPart == 0L){
			return "0";
		}
		
		// String in dem das Ergebnis gespeichert wird initialisieren
		String ergebnisGanz = "";
		
		// Berechnen der Stellen vor dem Komma mit dem Quellenverfahren
		// Schleife durchlaufen, bis ganzerAnteil <= 0
		while(!(integerPart <= 0L)){	
			// Der Rest der ganzzahligen Division ist auch gleich dem Wert der jeweiligen Stelle
			int rest = (int) (integerPart % newBase);
			
			// Zaehler fuer naechsten Durchlauf aktualisieren
			integerPart = integerPart / (long)newBase;
			
			char digit = digitOfValue(rest);
			
			/* Das letzte Ergebnis ergibt die erste Stelle, daher muss der 
			 * vorige String hinter der aktuellen Stelle stehen */
			ergebnisGanz = digit + ergebnisGanz;
		}
		
		return ergebnisGanz;
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt einen Nachkommateil vom Zehnersystem in einen Wert zu einer beliebigen Basis in der String-Darstellung um.
	 * Wenn in der Klasse {@code Preferences} der Indikator für Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * 
	 * @param newBase				Basis des neuen Zahlensystems
	 * @param fractionalPart		Nachkommateil im Zehnersystem
	 * @param fractionalPrecision	Maximale Anzahl von Nachkommastellen im Ergebnis
	 * @param comma					Zeichen, das als Komma vor dem Nachkommateil platziert wird
	 * 
	 * @return	umgewandelter Nachkommateil zur neuen Basis in String-Darstellung mit führendem Komma
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
	 * @see Preferences#setIndicateFractionalPrecision(boolean)
	 */
	private static String convertDecFractionalToBaseString(int newBase, double fractionalPart, int fractionalPrecision, char comma) {
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
				}
				break;
			}
			
			double helper = fractionalPart * newBase;
			int stelle = (int) helper;
			fractionalPart = helper % 1;
			
			char ziffer = digitOfValue(stelle);
			
			fractionalResult.append(ziffer);
			
			zaehl++;	// Hilfsvariable zum Abbrechen nach x Nachkommastellen
			
		}
		
		return fractionalResult.toString();
	}
	
	
	
// 	Berechnung von Stellenwertigkeiten	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Berechnet das Zeichen für eine Stellenwertigkeit.
	 * Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9, die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35.
	 * @param value		Wert der gesuchten Stelle zur Basis 10
	 * @return			Zeichen, das die Stellenwertigkeit des übergebenen Wertes repräsentiert
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

	
	/** <!-- $LANGUAGE=DE -->
	 * Berechnet die Stellenwertigkeit, die von einem Zeichen repräsentiert wird.
	 * Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9, die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35.
	 * @param digit		Zeichen, dessen Wertigkeit berechnet wird
	 * @return			Stellenwertigkeit, die von {@code digit} repräsentiert wird
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
	
	
	
// 	Zerlegung in ganzen und Nachkommateil	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Separiert den Anteil vor und nach dem Komma bzw. Punkt (je nach Format) des übergebenen String {@code value}, 
	 * der eine Zahl zu der Basis {@code base} repräsentiert, und gibt diese beiden separierten Strings ohne führende 0
	 * im Nachkommateil zurück.
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

	
	/** <!-- $LANGUAGE=DE -->
	 * überprüft, ob bei dem übergebenen String zuerst ein deutsches Komma (,) oder ein englisches Komma (.) auftaucht.
	 * 
	 * @param value	String, der auf Kommata überprüft wird
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Schneidet den übergebenen String so zu, dass dieser nicht mehr den Indikator für abgeschnittene Nachkommastellen
	 * und keine Leerzeichen enthält und wandelt alle Buchstaben in Großbuchstaben um.
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Unterteilt den übergebenen StringBuffer in Blöcke mit bestimmter Länge
	 * 
	 * @param sb		StringBuffer, der unterteilt wird
	 * @param blockSize Länge der Blöcke
	 * 
	 * @return den veränderten StringBuffer, der auch übergeben wurde
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
	private static StringBuffer insertSpace(StringBuffer sb, int blockSize) {
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
