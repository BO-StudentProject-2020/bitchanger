/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import bitchanger.calculations.ConvertingNumbers;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**	<!-- $LANGUAGE=DE -->
 * TextFeld, in dem eine Zahl zu einem bestimmten Zahlensystem eingegeben werden kann.
 * Es k\u00F6nnen nur Zeichen eingegeben werden, die in dem eingestellten Zahlensystem erlaubt sind.
 * Als einzige Ausnahme ist auch die Eingabe von Leerzeichen (beispielsweise zur Tausendertrennung) m\u00F6glich.
 * Die aktuelle Basis wird \u00FCber die baseProperty eingestellt. 
 * Die Grundform ist ein Rechteck ohne abgerundete Ecken.
 * 
 * <p>
 * Als Zusatzfunktion wird die Eingabe in Bl\u00F6cke aufgeteilt, wenn es sich bei dem eingestellten Zahlensystem
 * um das Bin\u00E4rsystem handelt.
 * </p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.6
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * Text field in which a number of a certain numeral system can be entered.
 * Only characters that are available in this selected numeral system can be entered.
 * The only exception is the space, that can be used as thousands separator for example.
 * The current base gets set by the baseProperty. 
 * The basic form is a rectangle without rounded corners.
 * 
 * <p>
 * As aditional function the input is split into blocks, if the chosen numeral system is the binary system.
 * </p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 *
 */
public class ValueField extends TextField {


//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr die Basis des Zahlensystems */
	/* <!-- $LANGUAGE=EN -->	Property for the base of the numeral system */
	private final IntegerProperty baseProperty;
	
	/** <!-- $LANGUAGE=DE -->	Hilfsvariable f\u00FCr die letzte bekannte Position im Textfeld */
	/* <!-- $LANGUAGE=EN -->	Auxiliary variable for the last known position in the text field */
	private int lastCaretPosition;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit leerem Text und der Basis 10
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates a new ValueField with empty text and the base 10
	 */
	public ValueField() {
		this(10);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit dem \u00FCbergebenen Text als Inhalt und der Basis 10
	 * 
	 * @param text	Textinhalt f\u00FCr dieses Textfeld
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates a new ValueField that contains the committed text and the base 10
	 * 
	 * @param text	Text for this text field
	 */
	public ValueField(String text) {
		this(text, 10);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit leerem Text und der \u00FCbergebenen Basis
	 * 
	 * @param base	Wert f\u00FCr die baseProperty
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates a new ValueFiled with an empty text and the committed base
	 * 
	 * @param base	Value for baseProperty
	 */
	public ValueField(int base) {
		this("", base);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit dem \u00FCbergebenen Text als Inhalt und der \u00FCbergebenen Basis
	 * 
	 * @param text	Textinhalt f\u00FCr dieses Textfeld
	 * @param base	Wert f\u00FCr die baseProperty
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Creates a new ValueField that contains the committed text and the committed base
	 * 
	 * @param text	Text for this text field
	 * @param base	Value for baseProperty
	 */
	public ValueField(String text, int base) {
		super(text);
		
		this.baseProperty = new SimpleIntegerProperty(base);
		this.lastCaretPosition = -1;
		
		this.textProperty().addListener(this::checkText);
		this.caretPositionProperty().addListener(this::storeCaretPosition);
		
		// Bei neuem Focus den Curser an die letzte bekannte Position setzen (behebt fehlerhafte Auswahl, wenn TF aus dem Spinner ausgewaehlt wird)
		this.focusedProperty().addListener(this::focuse);
		
		observeCommaProperty();
		
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		
		this.setShape(shape);
		this.setScaleShape(true);
		
		this.getStyleClass().add("value-field");
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** <!-- $LANGUAGE=DE -->
	 * Setzt den Wert der {@link #baseProperty}
	 * 
	 * @param base	neue Basis
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets the value of {@link #baseProperty}
	 * 
	 * @param base	new base
	 */
	public void setBase(int base) {
		this.baseProperty.setValue(base);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt den Wert der {@link #baseProperty} zur\u00FCck
	 * 
	 * @return	Wert der {@link #baseProperty}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the value of {@link #baseProperty}
	 * 
	 * @return	Value of {@link #baseProperty}
	 */
	public int getBase() {
		return this.baseProperty.get();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt die {@link #baseProperty} dieses ValueFields zur\u00FCck
	 * 
	 * @return	{@link #baseProperty} dieses ValueFields
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Returns the {@link #baseProperty} of this ValueField
	 * 
	 * @return	{@link #baseProperty} of this ValueFields
	 */
	public IntegerProperty getBaseProperty() {
		return this.baseProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * \u00DCberpr\u00FCft, ob es eine Textauswahl in diesem ValueField gibt
	 * 
	 * @return {@code true}, wenn eine Textstelle ausgew\u00E4hlt wurde, sonst {@code false}
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Checks if there is a text selection in this ValueField
	 * 
	 * @return {@code true}, if a text part is selected, otherwise {@code false}
	 */
	private boolean hasSelection() {
		return getSelection().getLength() > 0;
	}
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Pr\u00FCft, ob der eingegebene Text zur Basis passt und setzt die Eingabe zur\u00FCck, wenn ein verbotenes 
	 * Zeichen eingegeben wurde. Kann als Methoden-Referenz f\u00FCr einen ChangeListener eingesetzt werden.
	 * 
	 * @param observable	{@code ObservableValue}, dessen Wert sich \u00E4ndert
	 * @param oldValue		alter Wert
	 * @param newValue		neuer Wert
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Checks if the entered text agree to the base and resets the input if there was entered an illegal character.
	 * Can be used as method reference for a ChangeListener.
	 * 
	 * @param observable	{@code ObservableValue}, which value is changing
	 * @param oldValue		old value
	 * @param newValue		new value
	 */
	private void checkText(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		// Eingabe falscher Zeichen unterbinden (Unendlichzeichen \u221E zulassen)
		if(! ConvertingNumbers.isValueToBase(getBase(), newValue) && ! newValue.contains("\u221E")) {
			int caretPos = getCaretPosition();
			setText(oldValue);
			positionCaret(caretPos-1);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Speichert die letzte bekannte CaretPosition, um diese zur\u00FCcksetzen zu k\u00F6nnen. 
	 * Kann als Methoden-Referenz f\u00FCr einen ChangeListener eingesetzt werden.
	 * 
	 * @param observable	{@code ObservableValue}, dessen Wert sich \u00E4ndert
	 * @param oldValue		alter Wert
	 * @param newValue		neuer Wert
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Stores the last known CaretPosition to reset it. 
	 * Can be used as method reference for a ChangeListener.
	 * 
	 * @param observable	{@code ObservableValue}, which value is changing
	 * @param oldValue		old value
	 * @param newValue		new value
	 */
	public void storeCaretPosition(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		if(!hasSelection() && lastCaretPosition >= 0) {
			// letzte Curserposition speichern, fuer den Fall, dass das TF den Focus verliert und wieder ausgewaehlt wird
			lastCaretPosition = oldValue.intValue();	
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Entfernt den Indikator f\u00FCr abgeschnittene Nachkommastellen und setzt die CaretPosition auf den 
	 * letzten bekannten Wert zur\u00FCck. 
	 * Kann als Methoden-Referenz f\u00FCr einen ChangeListener eingesetzt werden.
	 * 
	 * @param observable	{@code ObservableValue}, dessen Wert sich \u00E4ndert
	 * @param oldValue		alter Wert
	 * @param isFocused		neuer Wert
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Removes the indicator for the cut decimal places and resets the CaretPosition to the last known value. 
	 * Can be used as method reference for a ChangeListener.
	 * 
	 * @param observable	{@code ObservableValue}, which value is changing
	 * @param oldValue		old value
	 * @param isFocused		new value ?!?!
	 */
	private void focuse(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isFocused) {
		if (isFocused) {
			// Indikator für abgeschnittene Nachkommastellen entfernen
			setText(getText().replace(ConvertingNumbers.FRACTIONAL_PRECISION_INDICATOR, ""));
			
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * \u00DCberwacht die CommaProperty aus {@link Preferences} und passt das Komma der Zahl in diesem Textfeld
	 * bei \u00C4nderung an
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Monitors the CommaProperty from {@link Preferences} and adjusts the comma if the number is changing
	 */
	private void observeCommaProperty() {
		Preferences.getPrefs().commaProperty().addListener(new ChangeListener<Comma>() {
			@Override
			public void changed(ObservableValue<? extends Comma> observable, Comma oldValue, Comma newValue) {
				if(oldValue.equals(newValue))
					return;
				
				boolean removedIndicator = false;
				String text = getText();
				
				if(getText().endsWith(ConvertingNumbers.FRACTIONAL_PRECISION_INDICATOR)) {
					text = text.replace(ConvertingNumbers.FRACTIONAL_PRECISION_INDICATOR, "");
					removedIndicator = true;
				}
				
				setText(text.replace(oldValue.get(), newValue.get()));
				
				if(removedIndicator) {
					appendText(ConvertingNumbers.FRACTIONAL_PRECISION_INDICATOR);
				}
				
				positionCaret(lastCaretPosition);
			}
		});
	}
	
}
















