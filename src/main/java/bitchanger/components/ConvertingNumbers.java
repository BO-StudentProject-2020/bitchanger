/*
 * Copyright (c)
 * 
 * Ersteller: Tim Mühle & Moritz Wolter
 * 
 */

package bitchanger.components;

import java.util.Scanner;

/**
 * This class {@code ConvertingNumbers} contains methods to convert numbers into different numeral systems.
 * 
 * Furthermore, this class {@code ConvertingNumbers} contains methods, which convert a positive floating-point number 
 * into different numeral systems with any base.
 * 
 * @author Tim & Moritz
 *
 */

public class ConvertingNumbers {
	
	
	public static boolean isValueToBase(String value, int base) {

		char[] characters = value.toUpperCase().toCharArray();

		// maximale Zahlen-Ziffer im Zahlensystem zur Basis
		char end = (char) ('0' + base - 1);
		boolean hasComma = false;

		// Ueberpruefen, ob verbotene Zeichen enthalten sind
		for(int i = 0; i < characters.length; i++) {
			char character = characters[i];
			
			if ((character < '0' || character > end)) {
				// keine oder falsche Zahlen-Ziffer
				if (character < 'A' || character > ('A' + base - 11)) {
					// kein oder falscher Buchstabe zur geg. Basis
					if (character == Settings.getComma()) {
						// Es handelt sich um ein Komma
						if (hasComma) {
							// mehr als ein Komma in einer Zahl nicht erlaubt
							return false;
						}
						hasComma = true;
						continue;
					} else if (i == 0 && (character == '+' || character == '-')) {
						// Vorzeichen an der ersten Stelle erlaubt
						continue;
					} else {
						// Es handelt sich um ein anderes, verbotenes Zeichen -> keine Zahl zur
						// gegebenen Basis
						return false;
					}
				}
			}
		}

		// Kein verbotenes Zeichen, es handelt sich um eine Zahl zur gegebenen Basis
		return true;
	}
	
	
	/**
	 * wandelt die übergebene Zahl zur spezifischen Basis in der String-Darstellung 
	 * in eine Zahl zur Basis 10 und gibt diese als {@code double} zurück
	 * @param base	die spezifische Basis des übergebenen wertes
	 * @param value	der Zahlenwert, der umgewandelt werden soll übergeben in der String-Darstellung
	 * @return	Darstellung des Wertes im Zehnersystem als {@code double} Zahl
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code zahl} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 */
	public static double basisToDez(int base, String value) throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		// Übergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		value = value.toUpperCase();
		String ganzerWert = separiereGanzenAnteil(value);
		String nachKomma = separiereNachkomma(value);
		
		// Strings die ganzen und Nachkommateil zu der übergebenen Basis 
		// repräsentieren in double Zahlen zur Basis 10 umwandeln
		double ganzDez = basisToDezGanz(base, ganzerWert);
		double nachDez = basisToDezNachKomma(base, nachKomma);
		
		// Vor- und Nachkommateil addieren und Ergebnis zurückgeben
		return ganzDez + nachDez;
	}
	
	/**
	 * wandelt die übergebene Zahl zur spezifischen Basis in der String-Darstellung 
	 * in eine Zahl zur Basis 10 und gibt diese als in der String-Darstellung zurück
	 * @param base	die spezifische Basis des übergebenen wertes
	 * @param value	der Zahlenwert, der umgewandelt werden soll, übergeben in der String-Darstellung
	 * @return	Darstellung des Wertes im Zehnersystem als String-Darstellung
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code wert} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 */
	public static String basisToDezString(int base, String value) throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		return basisToDezString(base, value, Settings.getComma());
	}
	
	/**
	 * wandelt die übergebene Zahl zur spezifischen Basis in der String-Darstellung 
	 * in eine Zahl zur Basis 10 und gibt diese als in der String-Darstellung zurück
	 * @param base	die spezifische Basis des übergebenen wertes
	 * @param value	der Zahlenwert, der umgewandelt werden soll, übergeben in der String-Darstellung
	 * @param separator der spezifische Separator, mit dem der ganze und der Nachkommateil getrennt werden
	 * @return	Darstellung des Wertes im Zehnersystem als String-Darstellung
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code wert} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 */
	public static String basisToDezString(int base, String value, char separator) throws NullPointerException, NumberFormatException, UnsupportedOperationException {	
		// Übergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		value = value.toUpperCase();
		String ganzerWert = separiereGanzenAnteil(base,value);
		String nachKomma = separiereNachkomma(base,value);
		
		// Strings die ganzen und Nachkommateil zu der übergebenen Basis 
		// repräsentieren in double Zahlen zur Basis 10 umwandeln
		double ganzDez = basisToDezGanz(base, ganzerWert);
		double nachDez = basisToDezNachKomma(base, nachKomma);
		
		// Überprüfen ob es Nachkommastellen gibt
		if(nachDez != 0.0) {
			// Ja -> Rückgabe mit Nachkommateil
			return String.valueOf((long)ganzDez) + separator + String.valueOf(nachDez).substring(2);
		} else {
			// Nein -> Rückgabe des ganzen Anteils
			return String.valueOf((long)ganzDez);
		}
		
	}


	/**
	 * wandelt den übergebenen Wert vom Zehnersystem in einen Wert zur gewünschten Basis um
	 * @param base	neue Basis, in die umgewandelt werden soll
	 * @param dezValue der Wert, der umgewandelt werden soll in der String Darstellung
	 * @return umgewandelte Zahl zur neuen Basis in der String-Darstellung
	 * @throws 	NullPointerException	wenn der Parameter {@code dezWert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code C} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code dezWert} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 */
	public static String dezToBasis(int base, String dezValue) throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		return dezToBasis(base, dezValue, Settings.getComma());
	}
	
	/**
	 * wandelt den übergebenen Wert vom Zehnersystem in einen Wert zur gewünschten Basis um
	 * @param base	neue Basis, in die umgewandelt werden soll
	 * @param dezValue der Wert, der umgewandelt werden soll in der String Darstellung
	 * @param separator der spezifische Separator, mit dem der ganze und der Nachkommateil getrennt werden
	 * @return umgewandelte Zahl zur neuen Basis in der String-Darstellung
	 * @throws 	NullPointerException	wenn der Parameter {@code dezWert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code C} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code dezWert} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 */
	public static String dezToBasis(int base, String dezValue, char separator) throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		// ganzen Anteil (Basis 10) separieren und in long umwandeln
		long ganzerWert = Long.parseLong(separiereGanzenAnteil(dezValue));
		
		// Nachkommateil (Basis 10) separieren und in double umwandeln
		double nachKomma = Double.parseDouble("0."+separiereNachkomma(dezValue));
		
		// in Zahl zur übergebenen Basis umwandeln und als String zurückgeben
		return dezToBasis(base, ganzerWert, nachKomma, separator);
	}


	/**
	 * wandelt den übergebenen Wert vom Zehnersystem in einen Wert zur gewünschten Basis um
	 * @param base neue Basis, in die umgewandelt werden soll
	 * @param integer ganzer Anteil im Zehnersystem der Zahl, die umgewandelt werden soll
	 * @param decimalPlace Nachkommateil im Zehnersystem der Zahl, die umgewandelt werden soll
	 * @return umgewandelte Zahl zur neuen Basis in der String-Darstellung
	 * @throws UnsupportedOperationException	wenn einer der Parameters {@code ganzerAnteil} oder {@code nachKomma} 
	 * 											kleiner als 0 sind, da negative Zahlen hier nicht erlaubt sind
	 */
	public static String dezToBasis(int base, long integer, double decimalPlace) throws UnsupportedOperationException{
		return dezToBasis(base, integer, decimalPlace, Settings.getComma());
	}
	
	/**
	 * wandelt den übergebenen Wert vom Zehnersystem in einen Wert zur gewünschten Basis um
	 * @param base neue Basis, in die umgewandelt werden soll
	 * @param integer ganzer Anteil im Zehnersystem der Zahl, die umgewandelt werden soll
	 * @param decimalPlace Nachkommateil im Zehnersystem der Zahl, die umgewandelt werden soll
	 * @param separator der spezifische Separator, mit dem der ganze und der Nachkommateil getrennt werden
	 * @return umgewandelte Zahl zur neuen Basis in der String-Darstellung
	 * @throws UnsupportedOperationException	wenn einer der Parameters {@code ganzerAnteil} oder {@code nachKomma} 
	 * 											kleiner als 0 sind, da negative Zahlen hier nicht erlaubt sind
	 */
	public static String dezToBasis(int base, long integer, double decimalPlace, char separator) throws UnsupportedOperationException{
		String neueBasis = "";
		
		// überprüfen ob alle Zahlen positiv sind
		// negative Zahlen werden derzeit nicht unterstützt!
		if(integer < 0 || decimalPlace < 0) {
			throw new UnsupportedOperationException("Negative values are not supported");
		}
		
		// Ganzen Anteil umrechnen
		if(integer == 0L){
			neueBasis = "0";
		} else {
			neueBasis = wandelVonDezimalGanzIt(base, integer);
		}
		
		// Wenn vorhanden Nachkommateil umwandeln
		if(decimalPlace != 0){
			// Nachkommaanteil umwandeln und hinzufuegen
			neueBasis += wandelVonDezimalNachkommaIt(base, decimalPlace, 50, separator);
		}
		
		// umgewandelte Zahl in der neuen Basis als String zurückgeben
		return neueBasis;
	}

	private static double basisToDezGanz(int basis, String ganzerWert) {
		char[] ziffern = ganzerWert.toCharArray();
		double summe = 0;
		
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		for(int i = 0; i < ziffern.length; i++) {
			summe *= basis;
			summe += berechneZiffer(ziffern[i]);
		}
		
		return summe;
	}
	
	private static double basisToDezNachKomma(int base, String decimalPlace) {
		// umwandeln von beliebiger Basis zum Zehnersystem mit dem Horner-Schema
		// dafür muss die Reihenfolge der Ziffern umgekehrt und eine 0 angehangen werden
		StringBuffer sb = new StringBuffer(decimalPlace);
		sb.reverse();
		sb.append(0);
		
		double summe = 0;
		
		for(int i = 0; i < sb.length(); i++) {
			summe *= 1.0/base;
			summe += berechneZiffer(sb.charAt(i));
		}
		
		return summe;
	}
	
	private static char berechneZiffer(int stelle){
		// gibt die Ziffer zur Stellenwertigkeit der übergebenen Stelle zurück
		// Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9
		// Die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35
		// Ein Überlauf ist möglich, wenn Die Stellenwertigkeit größer als 35 ist und wird nicht abgefangen!
		if(stelle >= 10){
			return (char)('A' + stelle - 10);
		} else {
			return (char)('0' + stelle);
		}
	}
	
	private static int berechneZiffer(char zeichen) {
		// Berechnung der Wertigkeit, die das Zeichen repräsentiert
		// Die Ziffern 0 bis 9 entsprechen der Wertigkeit 0 bis 9
		// Die Buchstaben A bis Z entsprechen der Wertigkeit 10 bis 35
		if(zeichen >= '0' && zeichen <= '9') {
			return zeichen - '0';
		} else {
			return zeichen - 'A' + 10;
		}
	}
	
	private static String wandelVonDezimalGanzIt(int basis, long ganzerAnteil) {
		// String in dem das Ergebnis gespeichert wird initialisieren
		String ergebnisGanz = "";
		
		// Berechnen der Stellen vor dem Komma mit dem Quellenverfahren
		// Schleife durchlaufen, bis ganzerAnteil <= 0
		while(!(ganzerAnteil <= 0L)){	
			// Der Rest der ganzzahligen Division ist auch gleich dem Wert der jeweiligen Stelle
			int rest = (int) (ganzerAnteil % basis);
			
			// Zaehler fuer naechsten Durchlauf aktualisieren
			ganzerAnteil = ganzerAnteil / (long)basis;
			
			char ziffer = berechneZiffer(rest);
			
			/* Das letzte Ergebnis ergibt die erste Stelle, daher muss der 
			 * vorige String hinter der aktuellen Stelle stehen */
			ergebnisGanz = ziffer + ergebnisGanz;
			
		}
		
		return ergebnisGanz;
	}
		
	private static String wandelVonDezimalNachkommaIt(int basis, double nachKomma, int anzahlStellenMax, char separator) {
		// Berechnen der Stellen nach dem Komma mit Hilfe von Multiplikation mit der Basis
		
		if(anzahlStellenMax <= 0) {
			// Kein Nachkommaanteil
			return "";
		}
		
		// String in dem das Ergebnis gespeichert wird
		String ergebnisNachkomma = String.valueOf(separator);
		
		// Hilfsvariabel zum Abbrechen nach X Nachkommastellen
		int zaehl = 0;
		
		while(!(nachKomma % 1 == 0 || zaehl >= anzahlStellenMax)){
			zaehl++;	// Hilfsvariabel zum Abbrechen nach X Nachkommastellen
			double hilf = nachKomma * basis;
			int stelle = (int) hilf;
			nachKomma = hilf % 1;
			
			char ziffer = berechneZiffer(stelle);
			
			ergebnisNachkomma = ergebnisNachkomma + ziffer;
		}
		
		return ergebnisNachkomma;
	}
	
// Klassenmethoden  ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/**	
	 * Gibt einen {@code long} Wert zurück, der den positiven ganzzahligen Anteil des übergebenen {@code String} mit der Basis 10 repräsentiert.
	 * 
	 * @throws 	NullPointerException	wenn der Parameter {@code zahl} null ist.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code zahl} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 * @throws 	NumberFormatException	wenn der Parameter {@code zahl} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @param zahl die Dezimalzahl, deren ganzer Anteil ermittelt werden soll
	 * @return ganzzahligen Anteil von {@code zahl} als {@code long}
	 */
	public static long parseGanzenAnteil(String zahl) throws NullPointerException, UnsupportedOperationException, NumberFormatException{
		// Ganzen Anteil separieren und auf verbotene Zeichen überprüfen
		String ganz = separiereGanzenAnteil(zahl);
		
		// In long umwandeln und diesen zurückgeben
		return Long.parseLong(ganz);
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Gibt einen {@code double} Wert zurück, der den Nachkommanteil des übergebenen {@code String} mit der Basis 10 repräsentiert.
	 * 
	 * @throws 	NullPointerException	wenn der Parameter {@code zahl} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code zahl} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code zahl} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 * @param zahl die Dezimalzahl, deren ganzer Anteil ermittelt werden soll
	 * @return ganzzahligen Anteil von {@code zahl} als {@code long}
	 */
	public static double parseNachkomma(String zahl) throws NullPointerException, UnsupportedOperationException, NumberFormatException{
		// Nachkommaanteil separieren und auf verbotene Zeichen überprüfen
		String nachKomma = separiereNachkomma(zahl);
		
		// In long umwandeln und diesen zurückgeben
		return Double.parseDouble(nachKomma);
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Gibt einen {@code String} zurück, der den positiven ganzzahligen Anteil des übergebenen {@code String} mit der Basis 10 repräsentiert.
	 * 
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code zahl} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @param wert die Dezimalzahl, deren ganzer Anteil ermittelt werden soll, in der {@code String} Darstellung
	 * @return ganzzahligen Anteil von {@code wert} als {@code String} Darstellung
	 */
	public static String separiereGanzenAnteil(String wert) throws NullPointerException, UnsupportedOperationException, NumberFormatException{
		return separiereGanzenAnteil(10,wert);
	}

// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Gibt einen {@code String} zurück, der den ganzen Anteil des übergebenen {@code String} zu der geforderten Basis 10 repräsentiert.
	 * 
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code zahl} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @param basis die Basis der übergebenen Zahl
	 * @param wert die Zahl, deren ganzer Anteil ermittelt werden soll, in der {@code String} Darstellung
	 * @return ganzzahligen Anteil von {@code wert} als {@code String} Darstellung
	 */
	public static String separiereGanzenAnteil(int basis, String wert) throws NullPointerException, UnsupportedOperationException, NumberFormatException{
		// Wenn kein String übergeben wurde wird eine NullPointerException geworfen
		if(wert == null) {
			throw new NullPointerException("null");
		}
		
		// Nachkommaanteil separieren und in einem char-Array speichern
		// Es sind das deutsche als auch das englische Zahlenformat und auch Zahlen ohne Nachkommateil zugelassen
		String[] getrennt = trenneKomma(wert);
		
		// Überprüfen, ob verbotene Zeichen enthalten sind
		if(!istGanzeZahl(basis,getrennt[0])) {
			// Es handelt sich um keine Zahl zur gegebenen Basis, da ein verbotenes Zeichen aufgetaucht ist
			// Es wird eine NumberformatException erzeugt
			throw new NumberFormatException("For input string: \"" + getrennt[0] + "\" Verbotenes Zeichen enthalten!");
		}
		
		// Es handelt sich wirklich Zahl zur gefragte Basis, diese wird zurückgegeben
		return getrennt[0];
	}
		
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Gibt einen {@code String} zurück, der den Nachkommaanteil des übergebenen {@code String} mit der Basis 10 repräsentiert.
	 * 
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @param wert die Dezimalahl, deren Nachkommaanteil ermittelt werden soll, in der {@code String} Darstellung
	 * @return Nachkommaanteil von {@code wert} als {@code String} Darstellung
	 */
	public static String separiereNachkomma(String wert) throws NullPointerException, NumberFormatException, UnsupportedOperationException{
		return separiereNachkomma(10,wert);
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Gibt einen {@code String} zurück, der den Nachkommaanteil ohne führende 0 und ohne Komma oder Punkt 
	 * des übergebenen {@code String} zu der geforderten Basis repräsentiert.
	 * 
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @param basis die Basis der übergebenen Zahl
	 * @param wert die Zahl, deren Nachkommaanteil ermittelt werden soll, in der {@code String} Darstellung
	 * @return Nachkommaanteil von {@code wert} als {@code String} Darstellung
	 */
	public static String separiereNachkomma(int basis, String wert) throws NullPointerException, NumberFormatException, UnsupportedOperationException{
		// Wenn kein String übergeben wurde wird eine NullPointerException geworfen
		if(wert == null) {
			throw new NullPointerException("null");
		}
		
		// Nachkommaanteil separieren und in einem char-Array speichern
		// Es sind das deutsche als auch das englische Zahlenformat und auch Zahlen ohne Nachkommateil zugelassen
		String[] getrennt = trenneKomma(wert);
		
		// Prüfen ob alle Zeichen erlaubte Zeichen zu der Basis sind
		if(!istGanzeZahl(basis,getrennt[1])) {
			// Es handelt sich um keine Zahl zur gegebenen Basis, da ein verbotenes Zeichen aufgetaucht ist
			// Es wird eine NumberformatException erzeugt
			throw new NumberFormatException("For input string: \"" + getrennt[1] + "\" Verbotenes Zeichen enthalten!");
		}
		
		// Es handelt sich wirklich um eine Zahl zur gefrageten basis, diese wird zurückgegeben
		return getrennt[1];
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Separiert den Anteil vor und nach dem Komma bzw. Punkt (jenach Format) des übergebenen {@code String}, 
	 * der eine Zahl zu der Basis 10 repräsentiert, und gibt diese beiden separierten Strings ohne führende 0
	 * im Nachkommateil zurück.
	 * 
	 * @param wert die Dezimalahl, die getrennt werden soll in der {@code String} Darstellung
	 * @return seperierte Anteile von {@code wert} als {@code String[]}, dabei ist der String im Index 0 der
	 * ganze Anteil und der String im Index 1 der Nachkommaanteil.
	 */	
	private static String[] trenneKomma(String wert) {
		String ganz = "";
		String nach = "";
		
		// Trennung in String suchen
		int punkt = wert.indexOf('.');
		int komma = wert.indexOf(',');
		
		// Separator einstellen
		String separator = null;
		if(punkt >= 0 && komma < 0) {
			separator = "\\.";
		} else if (komma >= 0 && punkt < 0) {
			separator = ",";
		}
		
		// Trennung vorhanden -> potentielle Gleitpunktzahl aufspalten
		if(separator != null) {
			// Scanner, der Ganzen- und Nachkommaanteil seperiert
			Scanner sc = new Scanner(wert);
			sc.useDelimiter(separator);
			
			ganz = sc.next();
			nach = sc.next();	
			
			// Delimiter zurücksetzten und leeren, um fehlerhafte Eingaben nicht zu vertuschen
			sc.reset();
			while(sc.hasNext()) {
				nach += sc.next();
			}
			
			// Scanner schliessen
			sc.close();
		} else {
			// Keine Trennung durch Komma oder Punkt, eventuell ganze Zahl
			ganz = wert;
			nach = "0";
		}
		
		String[] ergebnis = {ganz,nach};
		return ergebnis;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**
	 * Überprüft ob es sich bei dem übergebenen {@code String} um eine ganze positive 
	 * Zahl zur entsprechenden Basis handelt 
	 * @param basis	die Basis der übergebenen Zahl
	 * @param zahl	die potentielle Zahl, die überprüft werden soll in der {@code String} Darstellung
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @return	{@code true} wenn es sich wirklich um eine positive Zahl zu der entsprechenden Basis handelt,
	 * 			{@code false} sonst
	 */
	private static boolean istGanzeZahl(int basis, String zahl) throws UnsupportedOperationException{
		char[] ziffern = zahl.toUpperCase().toCharArray();
		
		// negative Zahlen sind nicht erlaubt!
		if(ziffern[0] == '-') {
			throw new UnsupportedOperationException("Negative values are not supported");
		}
		
		// maximale Zahl im Zahlensystem zur Basis
		char end = (char) ('0' + basis - 1);
		
		// Überprüfen, ob verbotene Zeichen enthalten sind
		for(char x:ziffern) {
			if((x < '0' || x > end)) {
				if(x < 'A' || x > ('A' + basis - 11))
				// Es handelt sich um keine Zahl zur gegebenen Basis, da ein verbotenes Zeichen aufgetaucht ist
				return false;
			}
		}
		// Kein verbotenes Zeichen, es handelt sich um eine Zahl
		return true;
	}
}
