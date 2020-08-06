/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import bitchanger.calculations.ConvertingNumbers;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ValueField extends TextField {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private IntegerProperty baseProperty;
	private int lastCaretPosition;
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ValueField() {
		this(10);
	}

	public ValueField(String text) {
		this(text, 10);
	}
	
	public ValueField(int base) {
		this("", base);
	}

	public ValueField(String text, int base) {
		super(text);
		this.baseProperty = new SimpleIntegerProperty(base);
		init();
	}
	
	
	private void init() {
		this.setAlignment(Pos.CENTER_LEFT);
		this.baseProperty = new SimpleIntegerProperty();
		this.lastCaretPosition = -1;
		setListener();
		
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		
		this.setShape(shape);
		this.setScaleShape(true);
	}
	

	private void setListener() {
		this.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(! ConvertingNumbers.isValueToBase(getBase(), newValue)) {
					int caretPos = getCaretPosition();
					setText(oldValue);
					positionCaret(caretPos-1);
				}
			}
		});
		
		this.caretPositionProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(!hasSelection() && lastCaretPosition >= 0) {
					lastCaretPosition = oldValue.intValue();	// letzte Curserposition speichern, fuer den Fall, dass das TF den Focus verliert und wieder ausgewaehlt wird
				}
			}
		});
		
		// Bei neuem Focus den Curser an die letzte bekannte Position setzen (behebt fehlerhafte Auswahl, wenn TF aus dem Spinner ausgewaehlt wird)
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					if (lastCaretPosition < 0) {
						lastCaretPosition = getText().length();
					}
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (hasSelection()) {
								deselect();
								positionCaret(lastCaretPosition);
							}
						}
					});
					
				}
			}
		});
	}

	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public void setBase(int base) {
		this.baseProperty.setValue(base);
	}
	
	public int getBase() {
		return this.baseProperty.get();
	}
	
	public IntegerProperty getBaseProperty() {
		return this.baseProperty;
	}
	
	private boolean hasSelection() {
		return getSelection().getLength() > 0;
	}
}
