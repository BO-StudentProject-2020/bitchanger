/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.util.NoSuchElementException;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.IEEEStandard;
import bitchanger.calculations.SimpleChangeableNumber;
import bitchanger.gui.controls.ValueButton;
import bitchanger.gui.controls.ValueField;
import bitchanger.gui.views.IEEEView;
import bitchanger.preferences.Preferences;
import bitchanger.util.ArrayUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.6
 * 
 */
public class IEEEController extends ControllerBase<IEEEView> {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE -->	Zahl, die in die verschiedenen Zahlensysteme umgewandelt wird */
	/*	<!-- $LANGUAGE=EN -->	Number which gets converted into any numeral system */
	private ChangeableNumber value;
	
	/**	<!-- $LANGUAGE=DE -->	Property zum Einstellen der Basis des aktuell fokussierten Textfelds */
	/*	<!-- $LANGUAGE=EN -->	Property to adjust the base of the currently focused text field */
	private IntegerProperty baseProperty;
	
	/**	<!-- $LANGUAGE=DE -->	Hilfsvariable mit Referenz auf das aktuell oder zuletzt fokussierte Textfeld */
	/*	<!-- $LANGUAGE=EN -->	Auxiliary variable with reference to the current or last focused text field */
	private ValueField focusedTF;
	
	
// TextFields	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Textfeld für die dezimale Darstellung */
	// TODO JavaDoc EN
	private ValueField tfDec;

	/** <!-- $LANGUAGE=DE -->	Textfeld für die IEEE Darstellung */
	// TODO JavaDoc EN
	private ValueField tfIEEE;
	
	
// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/**	<!-- $LANGUAGE=DE -->
	 * Button zum Löschen und Zurücksetzen von {@link #value}
	 * 
	 * @see ChangeableNumber#reset()
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Button to delete or remove {@link #value}
	 * 
	 * @see ChangeableNumber#reset()
	 */
	private Button clearBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button, der die Backspace-Taste auf der Tastatur simuliert */
	/*	<!-- $LANGUAGE=DE -->	Button that simulate the backspace key */
	private Button backspcBtn;
	
	/**	<!-- $LANGUAGE=DE -->	alphanumerische Buttons zur Simulation einer Tastatur */
	/*	<!-- $LANGUAGE=EN -->	alphanumeric keys to simulate a keyboard */
	private Button[] alphaNumButtons;
	
	/**	<!-- $LANGUAGE=DE -->	Button, mit dem das Vorzeichen der Zahl gewechselt werden kann */
	/*	<!-- $LANGUAGE=EN -->	Button that action is to change the sign of the number */
	private Button signBtn;


	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
 
	// TODO JavaDoc
	public IEEEController(IEEEView controllable) {
		super(controllable);
		this.value = new SimpleChangeableNumber();
		this.baseProperty = new SimpleIntegerProperty();
	}

	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		// Textfields
		this.tfDec = (ValueField) textFieldMap.get(controllable.tfDecKey());
		this.tfIEEE = (ValueField) textFieldMap.get(controllable.tfIEEEKey());
		
		this.tfDec.setBase(10);
		this.tfIEEE.setBase(2);
		
		// Buttons
		this.clearBtn = this.buttonMap.get(controllable.clearBtnKey());
		this.backspcBtn = this.buttonMap.get(controllable.backspaceBtnKey());
		this.signBtn = this.buttonMap.get(controllable.signBtnKey());

		alphaNumButtons = new Button[16];
		for (int i = 0; i < 6; i++) {
			alphaNumButtons[i] = this.buttonMap.get("alpha_" + i);
		}

		for (int i = 0; i < 10; i++) {
			alphaNumButtons[i + 6] = this.buttonMap.get("num_" + i);
		}
	}

	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	public void setActions() {
		setTextFieldActions();
		setButtonActions();
		setInitialState();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Attribute auf den Ausgangszustand.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the attributes to the initial condition.
	 */
	private void setInitialState() {
		tfDec.requestFocus();
		focusedTF = tfDec;
		baseProperty.set(10);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Actions			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
// TextFields	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt Listener für die Textfelder, um die Eingabe direkt in alle anderen Zahlensysteme umzuwandeln.
	 * Zudem wird das Attribut {@link #focusedTF} bei Auswahl eines Textfeldes aktualisiert und {@link #baseProperty}
	 * mit der Basis des Textfelds verbunden.
	 * 
	 * @see #setHexValListener()
	 * @see #setDecValListener()
	 * @see #setOctValListener()
	 * @see #setBinValListener()
	 * @see #setAnyValListener()
	 * @see #setTFSelection()
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets listener for the text fields, to convert the input in any numeral system immediately.
	 * Furthermore the attribute {@link #focusedTF} gets updates by selecting another text field
	 * and {@link #baseProperty} gets connected to the base of this text field.
	 * 
	 * @see #setHexValListener()
	 * @see #setDecValListener()
	 * @see #setOctValListener()
	 * @see #setBinValListener()
	 * @see #setAnyValListener()
	 * @see #setTFSelection()
	 */
	private void setTextFieldActions() {
		setDecValListener();
		setIEEEValListener();

		setTFSelection();
		updateIEEEStandard();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfDec}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the listener for {@link #tfDec}, to convert the input and update the other text fields immediately.
	 */
	private void setDecValListener() {
		tfDec.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfDec.isFocused()) {
					try {
						value.setDec(newValue);
					} catch (Exception e) {
						value.reset();
						if (! (e instanceof NoSuchElementException)) { // NoSuchElementException tritt auf, wenn Nachkommateil fehlt (wegen Scanner)
							e.printStackTrace();
						}
					}
					
					tfIEEE.setText(value.toIEEEString(Preferences.getPrefs().ieeeStandardProperty().get()));
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfIEEE}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the listener for {@link #tfIEEE}, to convert the input and update the other text fields immediately.
	 */
	private void setIEEEValListener() {
		tfIEEE.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfIEEE.isFocused()) {
					try {
						value.setIEEE(newValue, Preferences.getPrefs().ieeeStandardProperty().get());
					} catch (Exception e) {
						value.reset();
						if (! (e instanceof NoSuchElementException)) { // NoSuchElementException tritt auf, wenn Nachkommateil fehlt (wegen Scanner)
							e.printStackTrace();
						}
					}
					
					tfDec.setText(value.toDecString());
				}
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert das Attribut {@link #focusedTF} bei Auswahl eines Textfeldes durch einen Mausklick und 
	 * verbindet {@link #baseProperty} mit der baseProperty des Textfelds.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Updates the attribute {@link #focusedTF} by selecting a text filed with the cursor 
	 * and connects {@link #baseProperty} with the baseProperty of the text field.
	 */
	private void setTFSelection() {
		for (ValueField tf : ArrayUtils.arrayOf(tfDec, tfIEEE)) {
			tf.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					focusedTF = tf;
					baseProperty.bind(tf.getBaseProperty());
				}
			});
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateIEEEStandard() {
		Preferences.getPrefs().ieeeStandardProperty().addListener(new ChangeListener<IEEEStandard>() {
			@Override
			public void changed(ObservableValue<? extends IEEEStandard> observable, IEEEStandard oldValue, IEEEStandard newValue) {
				tfIEEE.setText(value.toIEEEString(newValue));
			}
		});
	}
	
	
	
// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Actions für alle Buttons
	 * 
	 * @see #setAlphaNumBindings()
	 * @see #setClearAction()
	 * @see #setBackspaceAction()
	 * @see #setSignAction()
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets actions for all buttons
	 * 
	 * @see #setAlphaNumBindings()
	 * @see #setClearAction()
	 * @see #setBackspaceAction()
	 * @see #setSignAction()
	 */
	private void setButtonActions() {
		setAlphaNumBindings();
		setClearAction();
		setBackspaceAction();
		setSignAction();
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Bindet die baseProperty aller alphanumerischen Buttons an das Attribut {@link #baseProperty}, um die Buttons
	 * bei einem Wechsel der Basis automatisch aus- oder einzublenden.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Links the baseProperty of all alphanumeric buttons to the attribute {@link #baseProperty},
	 * to show or hide the buttons automatically if the base is changing.
	 */
	private void setAlphaNumBindings() {
		for (Button b : alphaNumButtons) {
			((ValueButton)b).getBaseProperty().bind(baseProperty);
		}
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt {@link #value} bei Klick auf den Clear-Button zurück und aktualisiert alle Textfelder.
	 * 
	 * @see ChangeableNumber#reset()
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Resets {@link #value} by clicking on clear button and updates all text fields.
	 * 
	 * @see ChangeableNumber#reset()
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	private void setClearAction() {
		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				value.reset();
				tfDec.setText(value.toDecString());
				tfIEEE.setText(value.toIEEEString(Preferences.getPrefs().ieeeStandardProperty().get()));
			}
		});
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Lässt den Backspace-Button die Backspace-Taste auf der Tastatur simulieren.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Simulates the backspace button an the keyboard.
	 */
	private void setBackspaceAction() {
		backspcBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Simulate Backspace-Key
				simulateKeyEvents(null, null, controllable.getScene(), "", "", KeyCode.BACK_SPACE, false, false, false, false);
			}
		});
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Kehrt das Vorzeichen von {@link #value} beim Klick auf den Vorzeichen-Button um und aktualisiert alle Textfelder.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Turns the sign of {@link #value} by clicking onto the sign button and updates all text fields.
	 */
	private void setSignAction() {
		signBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int caretPos = focusedTF.getCaretPosition();
				
				if (focusedTF.getBase() == 2) {
					ChangeableNumber num = new SimpleChangeableNumber(tfDec.getText());
					num.set(- num.asDouble());
					focusedTF.setText(num.toIEEEString(Preferences.getPrefs().ieeeStandardProperty().get()));
				}
				else {
					if (focusedTF.getText().startsWith("-")) {
						focusedTF.setText(focusedTF.getText().substring(1));
						caretPos--;
					} else if (focusedTF.getText().startsWith("+")) {
						focusedTF.setText("-" + focusedTF.getText().substring(1));
					} else {
						focusedTF.setText("-" + focusedTF.getText());
						caretPos++;
					} 
				}
				
				focusedTF.positionCaret(caretPos);
			}
		});
	}

	
	
}









