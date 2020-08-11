package bitchanger.gui.controls;

import bitchanger.main.PrimaryFXApp;
import bitchanger.preferences.Preferences;
import javafx.scene.control.Alert;

public class InformationDialog extends Alert {
	
	public static enum InformationType{
		
		ABOUT,
		VERSION,
		NONE;
	}

	public InformationDialog() {
		this(InformationType.NONE);
	}
	
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

	private void createAboutDialog() {
		// TODO Über-Dialog erstellen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		this.setTitle("Über");
		
		this.setHeaderText("Über Bitchanger");
		
		this.setContentText("Lorem ipsum dolor sit amet, consetetur "
				+ "sadipscing elitr, sed diam nonumy eirmod tempor invidunt "
				+ "ut labore et dolore magna aliquyam erat, sed diam voluptua. "
				+ "At vero eos et accusam et justo duo dolores et ea rebum. Stet "
				+ "clita kasd gubergren, no sea takimata sanctus est Lorem ipsum "
				+ "dolor sit amet. Lorem ipsum dolor sit amet, consetetur "
				+ "sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut "
				+ "labore et dolore magna aliquyam erat, sed diam voluptua. At "
				+ "vero eos et accusam et justo duo dolores et ea rebum. Stet "
				+ "clita kasd gubergren, no sea takimata sanctus est Lorem ipsum "
				+ "dolor sit amet.");
		
	}

	private void createVersionDialog() {
		this.setTitle("Version");
		
		this.setHeaderText("Bitchanger Version:");
		
		StringBuffer content = new StringBuffer();
		
		content.append(PrimaryFXApp.VERSION);
		content.append("\n\n");
		content.append("All rights reserved (c)");
		
		this.setContentText(content.toString());

	}

}
