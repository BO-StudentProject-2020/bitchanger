/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
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
 * UnfocusedButton mit einer zus\u00E4tzlichen Property f\u00FCr die Einstellung einer Basis.
 * Wenn der Wert, den dieser Button repr\u00E4sentiert, nicht zu der eingestellten Basis passt,
 * deaktiviert sich dieser Button selbst. \u00C4ndert sich die Basis wieder so, dass der Wert
 * wieder g\u00FCltig ist, wird dieser Button automatisch aktiviert.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * UnfocusedButton with an extra Property to adjust the base.
 * If the value, that gets represented by this button, is not available for the chosen base,
 * this button disables itself. If the base is changing and the value agrees to it,
 * this button gets activated automatically.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ValueButton extends UnfocusedButton{

	 
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constants		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->	Property, \u00FCber das die Basis des aktuellen Zahlensystems eingestellt wird */
	/* <!-- $LANGUAGE=EN -->	Property that is used to adjust the base of the current numeral system */
	private final IntegerProperty baseProperty;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen ValueButton mit einem leeren String als Beschriftung
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Creates a ValueButton with an empty string as caption
	 */
	public ValueButton() {
		this("");
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen ValueButton mit einem spezifischen String und einem Icon als Beschriftung
	 *
	 * @param text		Text f\u00FCr die Beschriftung dieses Buttons
	 * @param graphic	Icon f\u00FCr die Beschriftung dieses Buttons
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Creates a ValueButton with a specific string and an icon as caption
	 *
	 * @param text		Text for the caption if this button
	 * @param graphic	Icon for the caption if this button
	 */
	public ValueButton(String text, Node graphic) {
		super(text, graphic);
		
		this.baseProperty = new SimpleIntegerProperty();
		
		init();
		
		getStyleClass().add("value-button");
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen ValueButton mit einem spezifischen String als Beschriftung
	 *
	 * @param text		Text f\u00FCr die Beschriftung dieses Buttons
	  */
	/*	<!-- $LANGUAGE=EN -->
	 * Creates a ValueButton with a specific string as caption
	 *
	 * @param text		Text for the caption of this button
	  */
	public ValueButton(String text) {
		this(text, null);
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Wert f\u00FCr die Basis
	 * 
	 * @param base neue Basis
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the value of the base
	 * 
	 * @param base new base
	 */
	public void setBase(int base) {
		this.baseProperty.set(base);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Gibt den Wert der {@link #baseProperty} zur\u00FCck
	 * 
	 * @return baseProperty dieses ValueButtons
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Returns the value of the {@link #baseProperty}
	 * 
	 * @return baseProperty of this ValueButton
	 */
	public IntegerProperty getBaseProperty() {
		return this.baseProperty;
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt Listener zum Aktivieren und Deaktivieren dieses Buttons
	 * durch die eingestellte Basis
	 * 
	 * @see #setTextListener()
	 * @see #setBaseListener()
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets listener to activate and disable this button
	 * by the selected base
	 * 
	 * @see #setTextListener()
	 * @see #setBaseListener()
	 */
	private void init() {
		setTextListener();
		setBaseListener();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * \u00DCberpr\u00FCft die textProperty und aktiviert bzw. deaktiviert diesen Button, wenn der neue Text
	 * zur baseProperty passt bzw. nicht passt
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Checks the textProperty and activates or disables this button if the new text
	 * agrees or disagrees to the baseProperty
	 */
	private void setTextListener() {
		textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals("") || !ConvertingNumbers.isValueToBase(baseProperty.get(), getText())) {
					setDisable(true);
				}
				else {
					setDisable(false);
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * \u00DCberpr\u00FCft die baseProperty und aktiviert bzw. deaktiviert diesen Button, wenn der Text
	 * zum neuen Wert der baseProperty passt bzw. nicht passt
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Checks the baseProperty and activates or disables this button if the text
	 * agrees or disagrees to the new value of the baseProperty
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












