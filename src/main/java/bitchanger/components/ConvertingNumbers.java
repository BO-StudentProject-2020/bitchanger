/*
 * Copyright (c)
 * 
 * Ersteller: Tim Mühle & Moritz Wolter
 * 
 */

package bitchanger.components;

import zahlenRechner.Zahl;

/**
 * This class contains methods to convert numbers into different numeral systems.
 * 
 * @author Tim & Moritz
 *
 */

public class ConvertingNumbers {
	
	
	
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
		String ganzerWert = Zahl.separiereGanzenAnteil(value);
		String nachKomma = Zahl.separiereNachkomma(value);
		
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
		return basisToDezString(base, value, Zahl.SEP_ENG);
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
	public static String basisToDezString(int base, String value, String separator) throws NullPointerException, NumberFormatException, UnsupportedOperationException {	
		// Übergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		value = value.toUpperCase();
		String ganzerWert = Zahl.separiereGanzenAnteil(base,value);
		String nachKomma = Zahl.separiereNachkomma(base,value);
		
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
		return dezToBasis(base, dezValue, Zahl.SEP_ENG);
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
	public static String dezToBasis(int base, String dezValue, String separator) throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		// ganzen Anteil (Basis 10) separieren und in long umwandeln
		long ganzerWert = Long.parseLong(Zahl.separiereGanzenAnteil(dezValue));
		
		// Nachkommateil (Basis 10) separieren und in double umwandeln
		double nachKomma = Double.parseDouble("0."+Zahl.separiereNachkomma(dezValue));
		
		// in Zahl zur übergebenen Basis umwandeln und als String zurückgeben
		return dezToBasis(base, ganzerWert, nachKomma, separator);
	}
	



}
