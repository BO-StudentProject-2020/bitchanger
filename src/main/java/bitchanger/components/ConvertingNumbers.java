/*
 * Copyright (c)
 * 
 * Ersteller: Tim Mühle & Moritz Wolter
 * 
 */

package bitchanger.components;


/**
 * Diese Klasse stellt Methoden zum Umwandeln von Zahlen mit verschiedenen Zahlensystemen bereit.
 * This class contains methods to convert numbers into different numeral systems.
 * @author Tim & Moritz
 *
 */

public class ConvertingNumbers {
	
	
	
	/**
	 * wandelt die übergebene Zahl zur spezifischen Basis in der String-Darstellung 
	 * in eine Zahl zur Basis 10 und gibt diese als {@code double} zurück
	 * @param basis	die spezifische Basis des übergebenen wertes
	 * @param value	der Zahlenwert, der umgewandelt werden soll übergeben in der String-Darstellung
	 * @return	Darstellung des Wertes im Zehnersystem als {@code double} Zahl
	 * @throws 	NullPointerException	wenn der Parameter {@code wert} null ist.
	 * @throws 	NumberFormatException	wenn der Parameter {@code wert} keinen umwandelbaren ganzzahligen Anteil hat.
	 * @throws 	UnsupportedOperationException	wenn das erste Zeichen des Parameters {@code zahl} ein '-' ist, 
	 * 											da negative Zahlen hier nicht erlaubt sind
	 */
	public static double basisToDez(int basis, String value) throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		// Übergebene Zahl in ganzen Anteil und Nachkommastellen trennen
		value = value.toUpperCase();
		String ganzerWert = Zahl.separiereGanzenAnteil(value);
		String nachKomma = Zahl.separiereNachkomma(value);
		
		// Strings die ganzen und Nachkommateil zu der übergebenen Basis 
		// repräsentieren in double Zahlen zur Basis 10 umwandeln
		double ganzDez = basisToDezGanz(basis, ganzerWert);
		double nachDez = basisToDezNachKomma(basis, nachKomma);
		
		// Vor- und Nachkommateil addieren und Ergebnis zurückgeben
		return ganzDez + nachDez;
	}
	


}
