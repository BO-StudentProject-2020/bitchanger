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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

/**	<!-- $LANGUAGE=DE -->
 * UnfocusedButton mit einer zusätzlichen Property für die Einstellung einer Basis.
 * Wenn der Wert, den dieser Button repräsentiert, nicht zu der eingestellten Basis passt,
 * deaktiviert sich dieser Button selbst. Ändert sich die Basis wieder so, dass der Wert
 * wieder gültig ist, wird dieser Button automatisch aktiviert.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ValueButton extends UnfocusedButton{

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Property, über das die Basis des aktuellen Zahlensystems eingestellt wird */
	private IntegerProperty baseProperty;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen ValueButton mit einem leeren String als Beschriftung
	 */
	public ValueButton() {
		super();
		init();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen ValueButton mit einem spezifischen String und einem Icon als Beschriftung
	 *
	 * @param text		Text für die Beschriftung dieses Buttons
	 * @param graphic	Icon für die Beschriftung dieses Buttons
	 */
	public ValueButton(String text, Node graphic) {
		super(text, graphic);
		init();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen ValueButton mit einem spezifischen String als Beschriftung
	*
	 * @param text		Text für die Beschriftung dieses Buttons
	  */
	public ValueButton(String text) {
		super(text);
		init();
	}
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Wert für die Basis
	 * 
	 * @param base neue Basis
	 */
	public void setBase(int base) {
		this.baseProperty.set(base);
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt den Wert das {@link #baseProperty} zurück
	 * 
	 * @return baseProperty dieses ValueButtons
	 */
	public IntegerProperty getBaseProperty() {
		return this.baseProperty;
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Initialisiert die {@link #baseProperty} und setzt Listener zum Aktivieren und Deaktivieren dieses Buttons
	 * durch die eingestellte Basis
	 * 
	 * @see #setTextListener()
	 * @see #setBaseListener()
	 */
	private void init() {
		this.baseProperty = new SimpleIntegerProperty();
		
		setTextListener();
		setBaseListener();
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Überprüft die textProperty und aktiviert bzw. deaktiviert diesen Button, wenn der neue Text
	 * zur baseProperty passt bzw. nicht passt
	 */
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
	
	/**	<!-- $LANGUAGE=DE -->
	 * Überprüft die baseProperty und aktiviert bzw. deaktiviert diesen Button, wenn der Text
	 * zum neuen Wert der baseProperty passt bzw. nicht passt
	 */
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
	
	

}
