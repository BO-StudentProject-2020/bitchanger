/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.main;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bitchanger.preferences.Preferences;
import bitchanger.util.Resources;

/** <!-- $LANGUAGE=DE -->
 * Diese Klasse enth\u00E4lt die Main-Methode, die der Einstiegspunkt f\u00FCr die Applikation ist.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 * 
 */
/* <!-- $LANGUAGE=EN -->
 * This class contains the main method that is the entry point for the application.
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 * 
 */
public class BitchangerLauncher {
	
	private static boolean debug;
	
	/** <!-- $LANGUAGE=DE -->
	 * Die Main Methode pr\u00FCft zun\u00E4chst, ob das Programm aus einer IDE oder der jar-Datei ausgef\u00FChrt wird
	 * und leitet bei einer Ausf\u00FChrung aus der jar alle Fehlerausgaben in eine Datei im Programmordner um.
	 * Danach wird die PrimaryFXApp der Anwendung gestartet und wartet, bis das Anwendungsfenster geschlossen wurde.
	 * Beim Beenden werden zuerst alle Einstellungen aus {@link Preferences} gespeichert und ggf. alle Streams und Dateien 
	 * f\u00FCr die Umleitung der Fehlerausgabe geschlossen, bevor das Programm beendet wird.
	 * 
	 * <pre>
	 * 
	 * zurzeit verf\u00FCgbare Flags sind:
	 * 
	 *     -help                  shows available options
	 *     -h                     short for -help
	 *     -debug [ERROR_LEVEL]   turns debug mode on -&gt; more Exceptions are printed to error stream
	 *                            possible ERROR_LEVELs: critical, medium, low, all
	 *                            all errors higher or equal to the chosen level will be printed
	 *                            note that the default level will be low if no level was passed
	 * 
	 * </pre>
	 * 
	 * @param args	Argumente, die beim Programmstart \u00FCbergeben werden. Die Argumente werden vom Programm ignoriert.
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
		debug = false;
		parseArgs(args);
		
		// Error-Stream setzen
		boolean relocateErr;
		
		URL codeLocation = Resources.class.getProtectionDomain().getCodeSource().getLocation();
		
		if(codeLocation.toString().endsWith(".jar")) {
			// Programm wird in jar-Datei ausgeführt
			relocateErr = true;
		} else {
			// Programm wird nicht in einer jar-Datei ausgeführt (z.B. Aufruf aus IDE)
			relocateErr = false;
		}
		
		File errorFile = null;
		PrintStream errStream = null;
		
		if (relocateErr) {
			String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			errorFile = new File(Resources.RESOURCES_ROOT, "/log/error_" + time + ".txt");
			errStream = openErrorStream(errorFile);
			if (errStream != null) {
				System.setErr(errStream);
			} 
		}
		
		// Hauptfenster ausführen und aus Schließen warten
		PrimaryFXApp.launchFXApplication(args);
		
		// aktuelle Einstellungen speichern
		Preferences.storeCustom();
		
		// Error-Datei speichern oder löschen
		if (relocateErr) {
			closeErrorStream(errStream);
			deleteEmptyErrorFile(errorFile);
		}
	}
	
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine Meldung mit dem Schweregrad eines aufgetretenen Fehlers und der Angabe der Stelle, an dem die
	 * Methode aufgerufen wurde auf dem ErrorStream aus.
	 * 
	 * @param priority	Schweregrad des aufgetretenen Fehlers
	 * 
	 * @see #printDebugErr(ErrorLevel, Exception)
	 * 
	 * @since 0.1.8
	 */
	public static void printDebugErr(ErrorLevel priority) {
		printDebugErr(priority, null);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine Meldung mit dem Schweregrad des übergebenen Fehlers und der Angabe der Stelle, an dem die
	 * Methode aufgerufen wurde gefolgt von dem StackTrace der Exception auf dem ErrorStream aus.
	 * 
	 * <p><b>Eine Ausgabe auf dem ErrorStream erfolgt nur, wenn das Programm mit dem Flag -debug gestartet wurde 
	 * und der Schweregrad mindestens dem mit dem Flag angegebenen ERROR_LEVEL entspricht!</b></p>
	 * 
	 * @param priority 	Schweregrad des aufgetretenen Fehlers
	 * @param e			Aufgetretene Exception, die geloggt werden soll
	 * 
	 * @since 0.1.8
	 */
	public static void printDebugErr(ErrorLevel priority, Exception e) {
		if (!debug || ! priority.isUsed()) {
			return;
		}
		
		StackTraceElement[] calls = Thread.currentThread().getStackTrace();
		
		int i = 0;
		while(calls[i].getClassName() != BitchangerLauncher.class.getName()) {
			i++;
		}
		
		StackTraceElement caller = calls[i + 1];
		
		StringBuilder errStr = new StringBuilder();
		errStr.append("\n\n### ");
		errStr.append(priority.name());
		errStr.append(" ### ");
		errStr.append(caller.getClassName());
		errStr.append(" : ");
		errStr.append(caller.getLineNumber());
		errStr.append(" ##############################################\n");
		
		System.err.println(errStr.toString());
		
		if(e != null) e.printStackTrace();
	}
	

	/** <!-- $LANGUAGE=DE -->
	 * Wertet die \u00FCbergebenen Argumente aus.
	 * 
	 * @param args \u00FCbergebene Argumente
	 * 
	 * @since 0.1.8
	 */
	private static void parseArgs(String[] args) {
		if(args == null) {
			return;
		}
		
		for(int i = 0; i < args.length; i++) {
			String option = args[i];
			
			if(!option.startsWith("-")) {
				System.out.println("unknown option: " + option);
				System.out.println("use option -h or -help to see available options");
				
				System.exit(1);
			} else if (option.equals("-h") || option.equals("-help")) {
				System.out.println("\n"
						+ "available options are:\n"
						+ "\n"
						+ "    -help                  shows available options \n"
						+ "    -h                     short for -help \n"
						+ "    -debug [ERROR_LEVEL]   turns debug mode on -> more Exceptions are printed to error stream \n"
						+ "                           possible ERROR_LEVELs: critical, medium, low, all \n"
						+ "                           all errors higher or equal to the choosen level will be printed \n"
						+ "                           note that the default level will be low if no level was passed \n\n");
				System.exit(0);
			} else if (option.equals("-debug")) {
				debug = true;
				
				String errorLevel = "LOW";
				
				i++;
				if(i < args.length) {
					errorLevel = args[i].toUpperCase();
					if(errorLevel.startsWith("-")) {
						errorLevel = "LOW";
						i--;
					} else if (errorLevel.equalsIgnoreCase("ALL")){
						errorLevel = "IGNORE";
					}
				}
				
				try {
					ErrorLevel level = ErrorLevel.valueOf(errorLevel);
					
					for(ErrorLevel x: ErrorLevel.values()) {
						x.setUsed(x.ordinal() <= level.ordinal());
					}
				} catch (Exception e) {
					System.out.println("syntax error: " + args[i]);
					System.out.println("use option -h or -help to see available options\n");
					
					System.exit(1);
				}
			} else {
				System.out.println("syntax error: " + args[i]);
				System.out.println("use option -h or -help to see available options");
				
				System.exit(1);
			}
		}
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
		} catch (Exception e) {
			/* ignore */
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
		}
	}
	

	private static void closeErrorStream(PrintStream errStream) {
		try {
			errStream.flush();
			errStream.close();
		} catch (Exception e) {
			/* ignore */
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
		}
	}

	
	private static PrintStream openErrorStream(File errorFile) {
		PrintStream errStream;
		
		try {
			if(!errorFile.getParentFile().exists()) {
				errorFile.getParentFile().mkdirs();
			}
			errStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(errorFile)), true);
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, e);
			e.printStackTrace();
			errStream = null;
		}
		return errStream;
	}
	
	
	

	/** <!-- $LANGUAGE=DE -->
	 * Indikator f\u00FCr den Schweregrad eines aufgetretenen Fehlers.
	 * 
	 * @author Tim
	 *
	 * @since Bitchanger 0.1.8
	 */
	public static enum ErrorLevel {
		/** <!-- $LANGUAGE=DE -->	Sehr schwerwiegender Fehler, der den Programmablauf massiv st\u00F6rt und unterbricht */
		CRITICAL,
		
		/** <!-- $LANGUAGE=DE -->	Mittlerer Fehler, der den Programmablauf st\u00F6ren kann, aber nicht zu einem vollst \u00E4ndigen Abbruch/Absturz f\u00FChrt */
		MEDIUM,
		
		/** <!-- $LANGUAGE=DE -->	Schwacher Fehler, der keine bis unerhebliche Auswirkungen auf den Programmablauf hat */
		LOW,
		
		/** <!-- $LANGUAGE=DE -->	Fehler der gewollt auftritt oder keine Auswirkungen auf den Programmablauf hat und ignoriert werden kann */
		IGNORE;
		
		private boolean isUsed;
		
		
		private ErrorLevel() {
			this.isUsed = true;
		}
		
		/** <!-- $LANGUAGE=DE -->
		 * Setzt die Bedingung, ob Fehler mit diesem Schweregrad auf dem ErrorStream geloggt werden sollen.
		 * 
		 * @param use	{@code true}, wenn Fehler mit diesem Schweregrad auf dem ErrorStream geloggt werden sollen, ansonsten {@code false}
		 */
		public void setUsed(boolean use) {
			this.isUsed = use;
		}
		
		public boolean isUsed() {
			return this.isUsed;
		}
	}

}







