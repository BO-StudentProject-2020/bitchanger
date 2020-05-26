package bitchanger.gui.elements;

import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class BaseSpinner<T> extends Spinner<T>{

	public BaseSpinner() {
		super(2, 36, 10);
		this.setEditable(true);
		setActions();
	}

	private void setActions() {
		this.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				commitEditorText();
			}
		});
	}
	
	private void commitEditorText() {
	    if (!this.isEditable()) 
	    	return;
	    if (wertebereichVerlassen()) 
	    	return;
	    
	    String text = this.getEditor().getText();
	    SpinnerValueFactory<T> valueFactory = this.getValueFactory();
	    
	    if (valueFactory != null) {
	        StringConverter<T> converter = valueFactory.getConverter();
	        if (converter != null) {
	            T value = converter.fromString(text);
	            valueFactory.setValue(value);
	        }
	    }
	    
	    this.getEditor().positionCaret(this.getEditor().getLength());
	}
	
	private boolean wertebereichVerlassen() {
		int min = ((SpinnerValueFactory.IntegerSpinnerValueFactory) this.getValueFactory()).getMin();
		int max = ((SpinnerValueFactory.IntegerSpinnerValueFactory) this.getValueFactory()).getMax();
		int wert;
		try {
			wert = Integer.parseInt(this.getEditor().getText());
		} catch (Exception e) {
			return true;
		}
		
		if(wert < min || wert > max) {
			return true;
		} else {
			return false;
		}
	}

}
