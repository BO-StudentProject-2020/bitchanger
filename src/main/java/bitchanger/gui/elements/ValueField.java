package bitchanger.gui.elements;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class ValueField extends TextField {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private int base;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ValueField() {
		super();
		init();
	}

	public ValueField(String text) {
		super(text);
		init();
	}
	
	
	private void init() {
		this.setAlignment(Pos.CENTER_LEFT);
	}
	

	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public void setBase(int base) {
		this.base = base;
	}
	
	public int getBase() {
		return this.base;
	}
	
	
}
