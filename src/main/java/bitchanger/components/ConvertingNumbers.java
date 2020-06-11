/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 */

package bitchanger.components;

import java.util.Scanner;
import bitchanger.preferences.Preferences;
import bitchanger.preferences.Comma;

/**
 * Die Klasse {@code ConvertingNumbers} enthält Methoden zum Umwandeln von Zahlen mit verschiedenen Zahlensystemen.
 * <p>
 * Die Methoden diese Klasse können sowohl ganze Zahlen als auch Kommazahlen mit positiven oder negativen Wert umwandeln.
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
 * @author Tim Mühle, Moritz Wolter
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 */

/*
 * The class {@code ConvertingNumbers} contains methods for performing conversions of numbers with different numeral systems.
 * 
 * 
 * @author Tim Muehle, Moritz Wolter
 *
 */

public class ConvertingNumbers {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	Eine Konstante für die kleinstmögliche Basis **/
	public static final int MIN_BASE = 2;
	
	/** Eine Konstante für die größtmögliche Basis bei einem Zeichenvorrat von 0-9 und A-Z **/
	public static final int MAX_BASE = 36;
	
	/** Indikator für Abgeschnittene Nachkommastellen bei der Umwandlung vom Zehnersystem in ein anderes, beliebiges Zahlensystem **/
	public static final String FRACTIONAL_PRECISION_INDICATOR = "...";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// 	Überprüfung von potentiellen Zahlen zu einer Basis	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**
	 * Prüft, ob {@code value} eine Zahl zur geforderten Basis {@code base} repräsentiert.
	 * Ist der Indikator für abgeschnittene Nachkommastellen aktiviert, wird dieser bei der Auswertung ignoriert.
	 * 
	 * @param value	zu prüfende Zahl repräsentiert als {@code String}
	 * @param base	geforderte Basis von {@code value} - erlaubt sind nur Werte von 2 bis 36 (einschließlich)
	 * 
	 * @return 	{@code true}, wenn {@code value} eine Zahl zur geforderten Basis {@code base} 
	 * 			repräsentiert, andernfalls wird {@code false} zurückgegeben
	 * 
	 * @throws IllegalArgumentException	wenn {@code basis} den Wertebereich [2, 36] verlässt
	 */
	public static boolean isValueToBase(int base, String value) throws IllegalArgumentException {
		if(base < MIN_BASE || base > MAX_BASE) {
			throw new IllegalArgumentException("Out of Bounds for base = " + base + " (base must be within " + MIN_BASE + " and " + MAX_BASE + ")");
		}
		
		if (Preferences.indicateFractionalPrecision() && value.endsWith(FRACTIONAL_PRECISION_INDICATOR)) {
			value = value.replace(FRACTIONAL_PRECISION_INDICATOR, "");	// Indikator für abgeschnittene Nachkommastellen ignorieren
		}

		char[] characters = value.toUpperCase().toCharArray();	// Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet

		char end = (char) ('0' + base - 1);	// maximale Zahlen-Ziffer im Zahlensystem zur Basis
		boolean hasComma = false;			// Hilfsvariable zum Pruefen auf mehr als ein Komma

		// Ueberpruefen, ob verbotene Zeichen enthalten sind
		for(int i = 0; i < characters.length; i++) {
			char character = characters[i];	// Jeden Character einzeln ueberpruefen
			
			if ((character < '0' || character > end)) {
				// keine oder falsche Zahlen-Ziffer zur geg. Basis -> Pruefen auf Buchstaben
				if (character < 'A' || character > ('A' + base - 11)) {
					// kein oder falscher Buchstabe zur geg. Basis -> Pruefen auf Komma
					if (character == Preferences.getComma()) {
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
	
	/**
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
	 */																						// &#160; = geschütztes Leerzeichen
	public static double baseToDec(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
		checkValue(base, value);
		
		// Uebergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		value = value.toUpperCase();	// Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet
		
		String[] separated = separateByComma(base, value);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		// Strings, die Ganzen- und Nachkommateil zu der uebergebenen Basis repraesentieren, in double Zahlen zur Basis 10 umwandeln
		double ganzDez = baseToDecIntPart(base, separated[0]);
		double nachDez = baseToDecFractionalPart(base, separated[1]);
		
		// Vor- und Nachkommateil addieren und Ergebnis zurueckgeben
		if((ganzDez + nachDez) >= Double.MAX_VALUE) {
			throw new UnsupportedOperationException("for value \"" + value + "\" - value must be within the double value range");
		}
		
		return ganzDez + nachDez;
	}
	
	
	/**
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
	public static String baseToDecString(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		return baseToDecString(base, value, Preferences.getComma());
	}
	
	
	/**
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
	public static String baseToDecString(int base, String value, char comma) throws NullPointerException, NumberFormatException, IllegalArgumentException {	
		// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
		checkValue(base, value);
		
		// Uebergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		value = value.toUpperCase();	// Es wird nur mit Großbuchstaben in Zahlensystemen größer 10 gearbeitet
		String[] separated = separateByComma(base, value);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		// Strings die ganzen und Nachkommateil zu der uebergebenen Basis 
		// repraesentieren in double Zahlen zur Basis 10 umwandeln
		double ganzDez = baseToDecIntPart(base, separated[0]);
		double nachDez = baseToDecFractionalPart(base, separated[1]);
		
		// Ueberpruefen ob es Nachkommastellen gibt
		if(nachDez != 0.0) {
			// Ja -> Rueckgabe mit Nachkommateil
			
			return String.valueOf((long)ganzDez) + comma + String.valueOf(nachDez).substring(2);
		} else {
			// Nein -> Rueckgabe des ganzen Anteils
			return String.valueOf((long)ganzDez);
		}
		
	}

	

// 	Umwandeln von dezimal zu beliebiger Basis	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**
	 * Wandelt die übergebene Zahl {@code decValue} zur Basis 10 in eine Zahl zur beliebigen Basis {@code newBase} in der String-Darstellung um.
	 * <p>
	 * Wenn in der Klasse {@code Preferences} der Indikator für Nachkommastellen aktiviert ist, werden die wegen der 
	 * maximalen Anzahl von Nachkommastellen abgeschnittenen Nachkommastellen durch "..." angedeutet.
	 * </p>
	 * 
	 * @param newBase		Basis des neuen Zahlensystems, in das die Zahl {@code decValue} umgewandelt werden soll
	 * @param decValue	Wert der Zahl im Zehnersystem in der String-Darstellung
	 * 
	 * @return 	umgewandelte Zahl zur übergebenen Basis in der String-Darstellung mit dem standardmäßig eingestellten Komma
	 * 
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue) throws NullPointerException, NumberFormatException {
		return decToBase(newBase, decValue, Preferences.getComma());
	}
	
	
	/**
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
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		return decToBase(newBase, decValue, comma, 15);
	}
	
	
	/**
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
	 * @throws NullPointerException			wenn der Parameter {@code decValue} {@code null} ist
	 * @throws NumberFormatException		wenn der Parameter {@code decValue} keine Zahl zur Basis 10 ist
	 * @throws IllegalArgumentException		wenn {@code value} ein leerer String ist oder wenn {@code newBase} den Wertebereich [2, 36] verlässt &#160; - &#160; <b>see</b> {@link isValueToBase(int, String)}
	 * 
	 * @see Preferences
	 */
	public static String decToBase(int newBase, String decValue, char comma, int fractionalPrecision) throws NullPointerException, NumberFormatException, IllegalArgumentException {
		// Prüfen, ob decValue eine Zahl zur Basis 10 repräsentiert
		checkValue(10, decValue);
		
		if(newBase < MIN_BASE || newBase > MAX_BASE) {
			throw new IllegalArgumentException("Out of Bounds for base = " + newBase + " (base must be within " + MIN_BASE + " and " + MAX_BASE + ")");
		}
		
		// ganzen Anteil und Nachkommateil (Basis 10) separieren und in long bzw. double umwandeln
		String[] separated = separateByComma(10, decValue);	// Index 0 => Ganzer Anteil, Index 1 => Nachkommaanteil
		
		long integerPart = Long.parseLong(separated[0]);
		double fractionalPart = Double.parseDouble("0." + separated[1]);
		
		StringBuffer newBaseValue = new StringBuffer();	// Variable, in der die String-Darstellung zur neuen Basis gespeichert wird
		
		// ueberpruefen ob alle Zahlen positiv sind
		// negative Zahlen werden derzeit nicht unterstuetzt!
		if(integerPart < 0 || fractionalPart < 0) {
			throw new UnsupportedOperationException("Negative values are not supported");
		}
		// TODO Negative Werte implementieren 	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		
		// Ganzen Anteil umrechnen
		newBaseValue.append(convertDecIntegerToBaseString(newBase, integerPart));
		
		
		// Wenn vorhanden Nachkommateil umwandeln
		if(fractionalPart != 0){
			String newBaseFractionalPart = convertDecFractionalToBaseString(newBase, fractionalPart, fractionalPrecision, comma);
			
			newBaseValue.append(newBaseFractionalPart);
		}
		
		// umgewandelte Zahl in der neuen Basis als String zurueckgeben
		return newBaseValue.toString();
	}
	
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/**
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
	private static void checkValue(int base, String value) throws NullPointerException, NumberFormatException, IllegalArgumentException{
		if(value == null) {
			throw new NullPointerException("null");
		}
		
		if(value.equals("")) {
			throw new IllegalArgumentException("for empty String");
		}
		
		if(!isValueToBase(base, value)) {	// Prüfen, ob value eine Zahl zur gegebenen Basis repräsentiert
			throw new NumberFormatException("For input string: \"" + value + "\" - Value cannot be converted to base " + base + " number");
		}
	}
	
	
	
// 	Umwandeln von beliebiger Basis nach dezimal	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**
	 * Wandelt die übergebene ganze Zahl {@code integerPart} zur beliebigen Basis {@code base} ins Zehnersystem um.
	 * Zur Umwandlung von einem beliebigen Zahlensystem in das Zehnersystem wird das Horner-Schema verwendet.
	 * 
	 * @param base			beliebige Basis des Zahlensystems von {@code integerPart} im Bereich [2; 36]
	 * @param integerPart	umzuwandelnde Zahl in der String-Darstellung
	 * @return				Wert der übergebenen Zahl im Zehnersystem als {@code double}
	 */
	private static double baseToDecIntPart(int base, String integerPart) {
		char[] ziffern = integerPart.toCharArray();
		double summe = 0;
		
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		for(int i = 0; i < ziffern.length; i++) {
			summe *= base;
			summe += valueOfDigit(ziffern[i]);
		}
		
		return summe;
	}
	
	
	/**
	 * Wandelt den übergebenen Nachkommateil einer Zahl zu einer beliebigen Basis ins Zehnersystem um.
	 * Zur Umwandlung von einem beliebigen Zahlensystem in das Zehnersystem wird das Horner-Schema verwendet.
	 * 
	 * @param base				beliebige Basis des Zahlensystems von {@code fractionalPart} im Bereich [2; 36]
	 * @param fractionalPart	umzuwandelnder Nachkommateil einer Zahl <b>ohne Komma</b> und als ganze Zahl in der String-Darstellung 
	 * @return					Wert des übergebenen Nachkommaanteils im Zehnersystem als {@code double}
	 */
	private static double baseToDecFractionalPart(int base, String fractionalPart) {
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		// dafuer muss die Reihenfolge der Ziffern umgekehrt und eine 0 angehangen werden
		StringBuffer sb = new StringBuffer(fractionalPart);
		sb.reverse();
		sb.append(0);
		
		double summe = 0;
		
		for(int i = 0; i < sb.length(); i++) {
			summe *= 1.0/base;
			summe += valueOfDigit(sb.charAt(i));
		}
		
		return summe;
	}
	
	
	
// 	Umwandeln von dezimal zu beliebiger Basis	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	
	/**
	 * Wandelt einen ganzzahligen Wert vom Zehnersystem in einen Wert zu einer beliebigen Basis in der String-Darstellung um.
	 * 
	 * @param newBase		Basis des neuen Zahlensystems
	 * @param integerPart	ganzzahligen Wert im Zehnersystem
	 * 
	 * @return	umgewandelter Wert zur Basis {@code basis} in String-Darstellung
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
			
			char ziffer = digitOfValue(rest);
			
			/* Das letzte Ergebnis ergibt die erste Stelle, daher muss der 
			 * vorige String hinter der aktuellen Stelle stehen */
			ergebnisGanz = ziffer + ergebnisGanz;
		}
		
		return ergebnisGanz;
	}
	
	
	/**
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
	private static String convertDecFractionalToBaseString(int newBase, double fractionalPart, int fractionalPrecision, char comma) {
		// Berechnen der Stellen nach dem Komma mit Hilfe von Multiplikation mit der Basis
		
		if(fractionalPrecision <= 0) {
			// Kein Nachkommaanteil
			return "";
		}
		
		// String in dem das Ergebnis gespeichert wird
		StringBuffer ergebnisNachkomma = new StringBuffer(String.valueOf(comma));
		
		// Hilfsvariable zum Abbrechen nach x Nachkommastellen
		int zaehl = 0;
		
		while(!(fractionalPart % 1 == 0)){
			if (zaehl >= fractionalPrecision) {
				if (Preferences.indicateFractionalPrecision()) {
					ergebnisNachkomma.append(FRACTIONAL_PRECISION_INDICATOR);
				}
				break;
			}
			
			double hilf = fractionalPart * newBase;
			int stelle = (int) hilf;
			fractionalPart = hilf % 1;
			
			char ziffer = digitOfValue(stelle);
			
			ergebnisNachkomma.append(ziffer);
			
			zaehl++;	// Hilfsvariable zum Abbrechen nach x Nachkommastellen
			
		}
		
		return ergebnisNachkomma.toString();
	}
	
	
	
// 	Berechnung von Stellenwertigkeiten	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**
	 * Berechnet das Zeichen für eine Stellenwertigkeit.
	 * Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9, die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35.
	 * @param value		Wert der gesuchten Stelle zur Basis 10
	 * @return			Zeichen, das die Stellenwertigkeit des übergebenen Wertes repräsentiert
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

	
	/**
	 * Berechnet die Stellenwertigkeit, die von einem Zeichen repräsentiert wird.
	 * Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9, die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35.
	 * @param digit		Zeichen, dessen Wertigkeit berechnet wird
	 * @return			Stellenwertigkeit, die von {@code digit} repräsentiert wird
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
	
	/**
	 * Separiert den Anteil vor und nach dem Komma bzw. Punkt (je nach Format) des übergebenen String {@code value}, 
	 * der eine Zahl zu der Basis {@code base} repräsentiert, und gibt diese beiden separierten Strings ohne führende 0
	 * im Nachkommateil zurück.
	 * 
	 * @param base	Basis des Zahlensystems von {@code value}
	 * @param value	Zahl, die zerlegt werden soll
	 * 
	 * @return	ganzen Anteil im Index 0 und Nachkommateil im Index 1, jeweils als ganze Zahl in der String-Darstellung
	 * 
	 * @throws NumberFormatException	Wenn es sich bei {@code value} nicht um eine Zahl in der String-Darstellung handelt, da zu viele Kommata vorhanden sind
	 */
	private static String[] separateByComma(int base, String value) throws NumberFormatException {
		String ganz = "";
		String nach = "";
		
		// Separator einstellen
		String separator = getFirstComma(value);
		
		// Trennung vorhanden -> potentielle Gleitpunktzahl aufspalten
		if(separator != null) {
			// Scanner, der Ganzen- und Nachkommaanteil separiert
			Scanner sc = new Scanner(value);
			sc.useDelimiter(separator);
			
			ganz = sc.next();
			nach = sc.next();	
			
			// Delimiter zurücksetzten und leeren, um fehlerhafte Eingaben nicht zu übergehen
			sc.reset();
			if(sc.hasNext()) {
				sc.close();
				throw new NumberFormatException("For input string: \"" + value + "\" - Value cannot be converted to base " + base + " number");
			}
			
			// Scanner schließen
			sc.close();
		} else {
			// Keine Trennung durch Komma oder Punkt, eventuell ganze Zahl
			ganz = value;
			nach = "0";
		}
		
		String[] separated = {ganz, nach};
		return separated;
	}

	
	/**
	 * Überprüft, ob bei dem übergebenen String zuerst ein deutsches Komma (,) oder ein englisches Komma (.) auftaucht.
	 * 
	 * @param value	String, der auf Kommata überprüft wird
	 * 
	 * @return das Komma, welches zuerst in dem String auftaucht oder {@code null}, wenn kein Komma enthalten ist
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
	
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** Diese Klasse ist nicht instanziierbar **/
	private ConvertingNumbers() {}

}
