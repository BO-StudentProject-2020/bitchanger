package bitchanger.gui.elements;

import bitchanger.components.ConvertingNumbers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class ValueField extends TextField {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private int base;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ValueField() {
		this(10);
		
	}

	public ValueField(String text) {
		this(text, 10);
		
	}
	
	public ValueField(int base) {
		super();
		this.base = base;
		init();
	}

	public ValueField(String text, int base) {
		super(text);
		this.base = base;
		init();
	}
	
	
	private void init() {
		this.setAlignment(Pos.CENTER_LEFT);
		setListener();
	}
	

	private void setListener() {
	/*	this.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				boolean isAlphabetic = Character.isAlphabetic(event.getCharacter().charAt(0));
				boolean isDigit = Character.isDigit(event.getCharacter().charAt(0));
				boolean isCharacterToBase = ConvertingNumbers.isValueToBase(event.getCharacter(), base);
				if(!isCharacterToBase && (isAlphabetic || isDigit)) {
					deletePreviousChar();
					System.out.println(event.getCharacter());
				}
			}
		});
	*/	
		this.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(! ConvertingNumbers.isValueToBase(newValue, base)) {
					int caretPos = getCaretPosition();
					setText(oldValue);
					positionCaret(caretPos-1);
				}
			}
		});
	}

	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public void setBase(int base) {
		this.base = base;
	}
	
	public int getBase() {
		return this.base;
	}
	
	
}
