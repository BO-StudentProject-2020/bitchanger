/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import bitchanger.main.PrimaryFXApp;
import bitchanger.preferences.Preferences;
import bitchanger.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

//TODO JavaDoc erstellen
/** <!-- $LANGUAGE=DE -->
 * 
 * @author Tim M\u00FChle
 *
 * @since Bitchanger 0.1.4
 * @version 1.0.1
 * 
 */
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
		
		this.getDialogPane().getStylesheets().add(Resources.ALERT_LAYOUT_CSS);
		this.getDialogPane().getStylesheets().add(Preferences.getPrefs().stylesheetProperty().get());
		
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
	

	// TODO JavaDoc erstellen @version 1.0.1
	private void createAboutDialog() {
		this.setTitle("\u00DCber");
		
		this.setHeaderText("\u00DCber Bitchanger");
		
		Label infoL = new Label("Bei dem Programm Bitchanger handelt es sich um einen Zahlenrechner, der mathematische Berechnungen, "
				+ "Bitoperationen und Zahlenumwandlungen von Zahlen mit der Basis 2 bis 36 durchf\u00FChren kann. "
				+ "Es werden negative Zahlen, Nachkommastellen und die IEEE-Norm (16- und 32-Bit) unterst\u00FCtzt.\n\n"
				+ "Der Bitchanger ist als Entwicklungsprojekt an der Hochschule Bochum entstanden "
				+ "und wurde von Tim M\u00FChle und Moritz Wolter im Auftrag von Prof. Dr. rer. nat. Katrin Brabender "
				+ "und Herrn Andreas Koch f\u00FCr das AID-Labor der Hochschule Bochum entwickelt.\n\n"
				+ "Der Bitchanger ist unter der MIT Lizenz ver\u00F6ffentlicht. Der Quellcode und die Dokumentation "
				+ "stehen unter der folgenden Seite zur Ver\u00FCgung:"
				+ "");
		infoL.setWrapText(true);
		infoL.setTextAlignment(TextAlignment.JUSTIFY);
		
		Hyperlink link = new Hyperlink("https://github.com/BO-StudentProject-2020/bitchanger");
		link.setFocusTraversable(false);
//		link.setUnderline(true);
		link.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				PrimaryFXApp.getInstanceHostServices().showDocument("https://github.com/BO-StudentProject-2020/bitchanger");
			}
		});
		
		VBox content = new VBox(infoL, link);
		content.setSpacing(5);
		
		this.getDialogPane().setContent(content);
		this.getDialogPane().setMinWidth(700);
		this.getDialogPane().setPrefWidth(700);
		this.setResizable(true);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc erstellen
	private void createVersionDialog() {
		this.setTitle("Bitchanger Info");
		this.setHeaderText("Version: " + PrimaryFXApp.VERSION);
		this.setContentText("Copyright \u00A9 2020 - Tim M\u00FChle und Moritz Wolter");
		
		this.getDialogPane().setMinWidth(400);
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










