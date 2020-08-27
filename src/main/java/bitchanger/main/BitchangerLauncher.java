/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.main;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bitchanger.preferences.Preferences;
import bitchanger.util.Resources;

/** <!-- $LANGUAGE=DE -->
 * Diese Klasse enthält die Main-Methode, die der Einstiegspunkt für die Applikation ist.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 * 
 */
/* <!-- $LANGUAGE=EN -->
 * This class contains the main method that is the entry point for the application.
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.6
 * 
 */
public class BitchangerLauncher {
	
	// TODO Update JavaDoc
	
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
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
		File errorFile = new File(Resources.RESOURCES_ROOT, "/log/error_" + time + ".txt");
		PrintStream errStream = openErrorStream(errorFile);
		
		if(errStream != null) {
			System.setErr(errStream);
		}

		PrimaryFXApp.launchFXApplication(args);
		
		Preferences.storeCustom();
		
		closeErrorStream(errStream);
		deleteEmptyErrorFile(errorFile);
	}

	
	private static void deleteEmptyErrorFile(File errorFile) {
		try {
			BufferedReader errIn = new BufferedReader(new FileReader(errorFile));
			
			String line;
			while((line = errIn.readLine()) != null) {
				if(!line.equals("")) {
					// Datei ist nicht leer und soll nicht gelöscht werden!
					errIn.close();
					return;
				}
			}
			
			errIn.close();
			
			errorFile.delete();
		} catch (Exception e) { /* ignore */ }
	}
	

	private static void closeErrorStream(PrintStream errStream) {
		try {
			errStream.flush();
			errStream.close();
		} catch (Exception e) { /* ignore */ }
	}

	private static PrintStream openErrorStream(File errorFile) {
		PrintStream errStream;
		
		try {
			if(!errorFile.getParentFile().exists()) {
				errorFile.getParentFile().mkdirs();
			}
			errStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(errorFile)));
		} catch (Exception e) {
			e.printStackTrace();
			errStream = null;
		}
		return errStream;
	}

}