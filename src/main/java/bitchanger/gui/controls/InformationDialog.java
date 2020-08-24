/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import bitchanger.main.PrimaryFXApp;
import bitchanger.preferences.Preferences;
import javafx.scene.control.Alert;

//TODO JavaDoc erstellen
public class InformationDialog extends Alert {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc erstellen
	public InformationDialog() {
		this(InformationType.NONE);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc erstellen
	public InformationDialog(InformationType informationType) {
		super(AlertType.INFORMATION);
		
		this.getDialogPane().getStylesheets().add(Preferences.getPrefs().readOnlyStylesheetProperty.get());
		
		switch(informationType) {
		case ABOUT:
			createAboutDialog();
			break;
		case VERSION:
			createVersionDialog();
			break;
		case NONE:
			// fall through
		default:
			// nothing to do
		}
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc erstellen
	private void createAboutDialog() {
		// TODO Über-Dialog erstellen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		this.setTitle("Über");
		
		this.setHeaderText("Über Bitchanger");
		
		this.setContentText("Bei dem Programm Bitchanger handelt es sich um einen Zahlenrechner, der mathematische Berechnungen, "
				+ "Bitoperationen und Zahlenumwandlungen von Zahlen mit der Basis 2 bis 36 durchführen kann. "
				+ "Es werden negative Zahlen, Nachkommastellen und die IEEE-Norm (16- und 32-Bit) unterstützt.\n\n"
				+ "Der Bitchanger ist als Entwicklungsprojekt im Elektrotechnik Studium an der Hochschule Bochum entstanden "
				+ "und wurde von Tim Mühle und Moritz Wolter im Auftrag von unter Aufsicht Prof. Dr. rer. nat. Katrin Brabender "
				+ "und Herrn Andreas Koch für das AID-Labor der Hochschule Bochum entwickelt.\n\n"
				+ "Der Bitchanger ist als Lehrmittel für die Hochschule Bochum gedacht und eine kommerzielle Nutzung ist untersagt.\n\n"
				+ "");
		
		this.getDialogPane().setMinWidth(700);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc erstellen
	private void createVersionDialog() {
		this.setTitle("Version");
		
		this.setHeaderText("Bitchanger Version:");
		
		StringBuffer content = new StringBuffer();
		
		content.append(PrimaryFXApp.VERSION);
		content.append("\n\n");
		content.append("Copyright (c) 2020 - Tim Mühle und Moritz Wolter");
		
		this.setContentText(content.toString());

	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	nested Classes   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	// TODO JavaDoc erstellen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	public static enum InformationType{
		
		// TODO JavaDoc erstellen
		ABOUT,
		
		// TODO JavaDoc erstellen
		VERSION,
		
		// TODO JavaDoc erstellen
		NONE;
	}

}










