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
	
	// TODO Update JavaDoc
	
	/** <!-- $LANGUAGE=DE -->
	 * Die Main Methode startet die PrimaryFXApp der Anwendung und wartet, bis das Anwendungsfenster geschlossen wurde.
	 * Danach werden zuerst alle Einstellungen aus {@link Preferences} gespeichert, bevor das Programm beendet wird.
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
	
	
	
	// TODO JavaDoc since 0.1.8
	public static void printDebugErr(ErrorLevel priority) {
		printDebugErr(priority, null);
	}
	
	// TODO JavaDoc since 0.1.8
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
	

	// TODO JavaDoc since 0.1.8
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

	// TODO JavaDoc
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
	

	// TODO JavaDoc
	private static void closeErrorStream(PrintStream errStream) {
		try {
			errStream.flush();
			errStream.close();
		} catch (Exception e) {
			/* ignore */
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
		}
	}

	
	// TODO JavaDoc
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
	
	
	

	// TODO JavaDoc since 0.1.8
	public static enum ErrorLevel {
		CRITICAL,
		MEDIUM,
		LOW,
		IGNORE;
		
		private boolean isUsed;
		
		
		private ErrorLevel() {
			this.isUsed = true;
		}
		
		public void setUsed(boolean use) {
			this.isUsed = use;
		}
		
		public boolean isUsed() {
			return this.isUsed;
		}
	}

}







