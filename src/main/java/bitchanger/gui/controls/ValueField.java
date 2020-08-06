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
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**	<!-- $LANGUAGE=DE -->
 * TextFeld, in dem eine Zahl zu einem bestimmten Zahlensystem eingegeben werden kann.
 * Es können nur Zeichen eingegeben werden, die in dem eingestellten Zahlensystem erlaubt sind.
 * Als einzige Ausnahme ist auch die Eingabe von Leerzeichen (beispielsweise zur Tausendertrennung) möglich.
 * Die aktuelle Basis wird über die baseProperty eingestellt. 
 * Die Grundform ist ein Rechteck ohne abgerundete Ecken.
 * 
 * <p>
 * Als Zusatzfunktion wird die Eingabe in Blöcke aufgeteilt, wenn es sich bei dem eingestellten Zahlensystem
 * um das Binärsystem handelt.
 * </p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ValueField extends TextField {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Property für die Basis des Zahlensystems */
	private IntegerProperty baseProperty;
	
	/** <!-- $LANGUAGE=DE -->	Hilfsvariable für die letzte bekannte Position im Textfeld */
	private int lastCaretPosition;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit leerem Text und der Basis 10
	 */
	public ValueField() {
		this(10);
	}

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit dem übergebenen Text als Inhalt und der Basis 10
	 */
	public ValueField(String text) {
		this(text, 10);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit leerem Text und der übergebenen Basis
	 */
	public ValueField(int base) {
		this("", base);
	}

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues ValueField mit dem übergebenen Text als Inhalt und der übergebenen Basis
	 */
	public ValueField(String text, int base) {
		super(text);
		this.baseProperty = new SimpleIntegerProperty(base);
		init();
	}
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt den Wert der {@link #baseProperty}
	 * 
	 * @param base	neue Basis
	 */
	public void setBase(int base) {
		this.baseProperty.setValue(base);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt den Wert der {@link #baseProperty} zurück
	 * 
	 * @return	Wert der {@link #baseProperty}
	 */
	public int getBase() {
		return this.baseProperty.get();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die {@link #baseProperty} dieses ValueFields zurück
	 * 
	 * @return	{@link #baseProperty} dieses ValueFields
	 */
	public IntegerProperty getBaseProperty() {
		return this.baseProperty;
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Überprüft, ob es eine Textauswahl in diesem ValueField gibt
	 * 
	 * @return {@code true}, wenn eine Textstelle ausgewählt wurde, sonst {@code false}
	 */
	private boolean hasSelection() {
		return getSelection().getLength() > 0;
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Initialisiert die Attribute, setzt Listener und passt die Form des ValueFields an.
	 * 
	 * @see #setListener()
	 */
	private void init() {
		this.baseProperty = new SimpleIntegerProperty();
		this.lastCaretPosition = -1;
		
		this.textProperty().addListener(this::checkText);
		this.caretPositionProperty().addListener(this::storeCaretPosition);
		
		// Bei neuem Focus den Curser an die letzte bekannte Position setzen (behebt fehlerhafte Auswahl, wenn TF aus dem Spinner ausgewaehlt wird)
		this.focusedProperty().addListener(this::resetCaretPosition);
		
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		
		this.setShape(shape);
		this.setScaleShape(true);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Prüft, ob der eingegebene Text zur Basis passt und setzt die Eingabe zurück, wenn ein verbotenes 
	 * Zeichen eingegeben wurde. Kann als Methoden-Referenz für einen ChangeListener eingesetzt werden.
	 * 
	 * @param observable	{@code ObservableValue}, dessen Wert sich ändert
	 * @param oldValue		neuer Wert
	 * @param newValue		alter Wert
	 */
	private void checkText(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		// Eingabe falscher Zeichen unterbinden
		if(! ConvertingNumbers.isValueToBase(getBase(), newValue)) {
			int caretPos = getCaretPosition();
			setText(oldValue);
			positionCaret(caretPos-1);
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Speichert die letzte bekannte CaretPosition, um diese zurücksetzen zu können. 
	 * Kann als Methoden-Referenz für einen ChangeListener eingesetzt werden.
	 * 
	 * @param observable	{@code ObservableValue}, dessen Wert sich ändert
	 * @param oldValue		neuer Wert
	 * @param newValue		alter Wert
	 */
	public void storeCaretPosition(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		if(!hasSelection() && lastCaretPosition >= 0) {
			// letzte Curserposition speichern, fuer den Fall, dass das TF den Focus verliert und wieder ausgewaehlt wird
			lastCaretPosition = oldValue.intValue();	
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die CaretPosition auf den letzten bekannten Wert zurück. 
	 * Kann als Methoden-Referenz für einen ChangeListener eingesetzt werden.
	 * 
	 * @param observable	{@code ObservableValue}, dessen Wert sich ändert
	 * @param oldValue		neuer Wert
	 * @param newValue		alter Wert
	 */
	public void resetCaretPosition(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
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
	
	
}
