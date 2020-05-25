package bitchanger.gui.elements;

import javafx.scene.control.TextField;

public class ValueField extends TextField {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private int base;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ValueField() {
		super();
	}

	public ValueField(String text) {
		super(text);
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public void setBase(int base) {
		this.base = base;
	}
	
	public int getBase() {
		return this.base;
	}
	
	
}
