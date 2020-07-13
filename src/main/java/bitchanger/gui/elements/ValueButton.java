package bitchanger.gui.elements;

import bitchanger.components.ConvertingNumbers;
import bitchanger.preferences.Preferences;
import bitchanger.preferences.Comma;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public class ValueButton extends ExtendedButton{

	//TODO Properties zum Ausblenden des Buttons bei unzulässigem Zeichen 	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	private IntegerProperty baseProperty;
	
	public ValueButton() {
		super();
		init();
	}

	public ValueButton(String text, Node graphic) {
		super(text, graphic);
		init();
	}

	public ValueButton(String text) {
		super(text);
		init();
	}
	
	
	public void setBase(int base) {
		this.baseProperty.set(base);
	}
	
	public IntegerProperty getBaseProperty() {
		return this.baseProperty;
	}
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private void init() {
		this.baseProperty = new SimpleIntegerProperty();
		
		setTextListener();
		setBaseListener();
		
		
		
		Preferences.getCommaProperty().addListener(new ChangeListener<Comma>() {
			@Override
			public void changed(ObservableValue<? extends Comma> observable, Comma oldValue, Comma newValue) {
				
			}
		});
	}

	private void setBaseListener() {
		baseProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (ConvertingNumbers.isValueToBase(newValue.intValue(), getText())) {
					setDisable(false);
				} else {
					setDisable(true);
				}
			}
		});
	}
	
	private void setTextListener() {
		textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals(" ") || !ConvertingNumbers.isValueToBase(baseProperty.get(), getText())) {
					setDisable(true);
				}
				else {
					setDisable(false);
				}
			}
		});
	}

}
