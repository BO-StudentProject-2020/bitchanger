package bitchanger.gui.elements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ValueButton extends RoundButton{

	//TODO Properties zum Ausblenden des Buttons bei unzulässigem Zeichen 	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	
	public ValueButton() {
		super();
		initBindings();
	}

	public ValueButton(String text, Node graphic) {
		super(text, graphic);
		initBindings();
	}

	public ValueButton(String text) {
		super(text);
		initBindings();
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private void initBindings() {
		
		
		textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals(" ")) {
					setDisable(true);
				}
				else {
					setDisable(false);
				}
			}
		});
	}

}
