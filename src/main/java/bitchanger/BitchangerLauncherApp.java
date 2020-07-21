/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger;

import bitchanger.gui.Hauptfenster;
import bitchanger.preferences.Preferences;

/** <!-- $LANGUAGE=DE -->
 * Diese Klasse enthält die Main-Methode, die der Einstiegspunkt für die Applikation ist.
 * 
 * @author Tim Mühle
 * 
 * @since 0.1.0
 * @version 0.1.0
 * 
 */

/* <!-- $LANGUAGE=EN -->
 * This class contains the main method that is the entry point for the application.
 * 
 * @author Tim Mühle
 * 
 * @since 0.1.0
 * @version 0.1.0
 * 
 */
public class BitchangerLauncherApp {

	/** <!-- $LANGUAGE=DE -->
	 * Die Main Methode liest zuerst alle gespeicherten Einstellungen für die Klasse {@code Preferences} ein.
	 * Danach wird das Hauptfenster der Anwendung gestartet und gewartet, bis dieses geschlossen wurde.
	 * 
	 * @param args	Argumente, die beim Programmstart übergeben werden. Die Argumente werden vom Programm ignoriert.
	 * 
	 * @see Preferences
	 * @see Hauptfenster#main(String[])
	 * 
	 */
	
	/* <!-- $LANGUAGE=EN -->
	 * The main method first reads in all stored settings for the class {@code Preferences}.
	 * Then the main window of the application will be started and waits until this main window is closed.
	 * 
	 * @param args	Arguments that are committed at the beginning of the program. The program ignores these arguments.
	 * 
	 * @see Preferences
	 * @see Hauptfenster#main(String[])
	 * 
	 */
	public static void main(String[] args) {
		// Der Lauchner startet nur die Main-Methode vom Hauptfenster
		/* Dies ist notwendig, da ohne den Aufruf mit diesem Launcher der Classpath auf die javafx Runtime
		 * ueberprueft wird und die Anwendung mit einer Exception abgebrochen wird
		 */
		Hauptfenster.main(args);
	}

}