/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.main;

import java.io.File;
import java.io.PrintStream;

import bitchanger.preferences.Preferences;

/** <!-- $LANGUAGE=DE -->
 * Diese Klasse enthält die Main-Methode, die der Einstiegspunkt für die Applikation ist.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.5
 * 
 */
/* <!-- $LANGUAGE=EN -->
 * This class contains the main method that is the entry point for the application.
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.5
 * 
 */
public class BitchangerLauncher {
	
	/** <!-- $LANGUAGE=DE -->
	 * Die Main Methode startet die PrimaryFXApp der Anwendung und wartet, bis das Anwendungsfenster geschlossen wurde.
	 * Danach werden zuerst alle Einstellungen aus {@link Preferences} gespeichert, bevor das Programm beendet wird.
	 * 
	 * @param args	Argumente, die beim Programmstart übergeben werden. Die Argumente werden vom Programm ignoriert.
	 * 
	 * @see Preferences
	 * @see PrimaryFXApp#launchFXApplication(String[])
	 * 
	 */
	/* <!-- $LANGUAGE=EN -->
	 * The main method launches the application's main window and waits until this main window was closed.
	 * All settings from {@link Preferences} are saved before the program is terminated.
	 * 
	 * @param args	Arguments that are committed at the beginning of the program. The program ignores these arguments.
	 * 
	 * @see Preferences
	 * @see PrimaryFXApp#launchFXApplication(String[])
	 * 
	 */
	public static void main(String[] args) {
		// Der Lauchner startet nur die Main-Methode vom PrimaryFXApp
		/* Dies ist notwendig, da ohne den Aufruf mit diesem Launcher der Classpath auf die javafx Runtime
		 * ueberprueft wird und die Anwendung mit einer Exception abgebrochen wird
		 */
		try {
			System.setErr(new PrintStream(new File("bitchanger_error.txt")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrimaryFXApp.launchFXApplication(args);
		
		Preferences.storeCustom();
	}

}