package bitchanger;

import bitchanger.gui.Hauptfenster;

public class BitchangerLauncherApp {

	public static void main(String[] args) {
		// Der Lauchner startet nur die Main-Methode vom Hauptfenster
		/* Dies ist notwendig, da ohne den Aufruf mit diesem Launcher der Classpath auf die javafx Runtime
		 * ueberprueft wird und die Anwendung mit einer Exception abgebrochen wird
		 */
		Hauptfenster.main(args);
	}

}