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

/**
 * Diese Klasse enthält die Main-Methode, die der Einstiegspunkt für die Applikation ist.
 * 
 * @author Tim Mühle
 * 
 * @since 0.1.0
 * @version 0.1.0
 * 
 */
public class BitchangerLauncherApp {

	/**
	 * Die Main Methode liest zuerst alle gespeicherten Einstellungen für die Klasse {@code Preferences} ein.
	 * Danach wird das Hauptfenster der Anwendung gestartet und gewartet, bis dieses geschlossen wurde.
	 * 
	 * @param args	Argumente, die beim Programmstart übergeben werden. Die Argumente werden vom Programm ignoriert.
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