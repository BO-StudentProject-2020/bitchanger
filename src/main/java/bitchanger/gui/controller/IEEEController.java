/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.util.NoSuchElementException;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.ConvertingNumbers;
import bitchanger.calculations.IEEEStandard;
import bitchanger.calculations.NumberOverflowException;
import bitchanger.calculations.SimpleChangeableNumber;
import bitchanger.gui.controls.ValueButton;
import bitchanger.gui.controls.ValueField;
import bitchanger.gui.views.IEEEView;
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Preferences;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.4
 * @version 1.0.2
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
	/** <!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die dezimale Darstellung */
	// TODO JavaDoc EN
	private ValueField tfDec;

	/** <!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die IEEE Darstellung */
	// TODO JavaDoc EN
	private ValueField tfIEEE;
	
	
// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/**	<!-- $LANGUAGE=DE -->
	 * Button zum L\u00F6schen und Zur\u00FCcksetzen von {@link #value}
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
	
	/**	<!-- $LANGUAGE=DE -->	Button zur Eingabe von NaN */
	/*	<!-- $LANGUAGE=EN -->	Button to input NaN */
	private Button nanBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button zur Eingabe des Unendlichzeichens */
	/*	<!-- $LANGUAGE=EN -->	Button to input infinity */
	private Button infinityBtn;


	
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
		this.nanBtn = this.buttonMap.get(controllable.nanBtnKey());
		this.infinityBtn = this.buttonMap.get(controllable.infinityBtnKey());

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
		
		addAccelerator(clearBtn, KeyEvent.KEY_TYPED, new KeyCodeCombination(KeyCode.F12));
		addAccelerator(clearBtn, KeyEvent.KEY_TYPED, new KeyCodeCombination(KeyCode.ESCAPE));
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Attribute auf den Ausgangszustand.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the attributes to the initial condition.
	 * 
	 * @since Bitchanger 0.1.7
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
	 * Setzt Listener f\u00FCr die Textfelder, um die Eingabe direkt in alle anderen Zahlensysteme umzuwandeln.
	 * Zudem wird das Attribut {@link #focusedTF} bei Auswahl eines Textfeldes aktualisiert und {@link #baseProperty}
	 * mit der Basis des Textfelds verbunden.
	 * 
	 * @see #setDecValListener()
	 * @see #setIEEEValListener()
	 * @see #setTFSelection()
	 * @see #updateIEEEStandard()
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets listener for the text fields, to convert the input in any numeral system immediately.
	 * Furthermore the attribute {@link #focusedTF} gets updates by selecting another text field
	 * and {@link #baseProperty} gets connected to the base of this text field.
	 * 
	 * @see #setDecValListener()
	 * @see #setIEEEValListener()
	 * @see #setTFSelection()
	 * @see #updateIEEEStandard()
	 * 
	 * @since Bitchanger 0.1.7
	 */
	private void setTextFieldActions() {
		setDecValListener();
		setIEEEValListener();
		setIEEEOnAction();
		clearIEEE();

		setTFSelection();
		updateIEEEStandard();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener f\u00FCr {@link #tfDec}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @since Bitchanger 0.1.7
	 * @version 1.0.2
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the listener for {@link #tfDec}, to convert the input and update the other text fields immediately.
	 * 
	 * @since Bitchanger 0.1.7
	 * @version 1.0.2
	 */
	private void setDecValListener() {
		tfDec.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfDec.isFocused()) {
					try {
						value.setDec(newValue);
					} catch (NumberOverflowException noe) {
						BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, noe);
						FXUtils.showNumberOverflowWarning(noe);
						value.reset();
					} catch (Exception e) {
						value.reset();
						if (! (e instanceof NoSuchElementException)) { // NoSuchElementException tritt auf, wenn Nachkommateil fehlt (wegen Scanner)
							BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, e);
							e.printStackTrace();
						} else {
							BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
						}
					}
					
					try {
						tfIEEE.setTextUnchecked(value.toIEEEString(Preferences.getPrefs().ieeeStandardProperty().get()));
					} catch (ArithmeticException e) {
						tfIEEE.setText("");
						FXUtils.showDialog(AlertType.ERROR, "Fehler", "Fehler", e.getMessage(), ButtonType.CLOSE);
					}
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener f\u00FCr {@link #tfIEEE}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the listener for {@link #tfIEEE}, to convert the input and update the other text fields immediately.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	private void setIEEEValListener() {
		tfIEEE.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfIEEE.getText().replace(" ", "").length() > Preferences.getPrefs().ieeeStandardProperty().get().getBitLength()) {
					// Warnung bei zu vielen Zeichen
					FXUtils.showDialog(AlertType.WARNING, "Warnung", "Eingabefehler", "Die eingegebene Zahl ist zu lang. Es werden 16-Bit und 32-Bit IEEE-Zahlen unterst\u00FCtzt. Die gew\u00FCnschte Norm kann im Men\u00FC Optionen ausgew\u00E4hlt werden.", ButtonType.OK);
				}
				else if (tfIEEE.isFocused() && newValue.replace(" ", "").length() == Preferences.getPrefs().ieeeStandardProperty().get().getBitLength()) {
					updateIEEEValue(newValue);
				}
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since 0.1.8
	private void updateIEEEValue(String newValue) {
		try {
			value.setIEEE(newValue, Preferences.getPrefs().ieeeStandardProperty().get());
		} catch (NumberOverflowException noe) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, noe);
			FXUtils.showNumberOverflowWarning(noe);
			value.reset();
		} catch (Exception e) {
			value.reset();
			if (! (e instanceof NoSuchElementException)) { // NoSuchElementException tritt auf, wenn Nachkommateil fehlt (wegen Scanner)
				BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM);
				e.printStackTrace();
			} else {
				BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			}
		}
		
		tfDec.setText(value.toDecString());
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since 0.1.8
	private void setIEEEOnAction() {
		tfIEEE.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tfIEEE.getText().replace(" ", "").length() > Preferences.getPrefs().ieeeStandardProperty().get().getBitLength()) {
					// Warnung bei zu vielen Zeichen
					FXUtils.showDialog(AlertType.WARNING, "Warnung", "Eingabefehler", "Die eingegebene Zahl ist zu lang. Es werden 16-Bit und 32-Bit IEEE-Zahlen unterst\u00FCtzt. Die gew\u00FCnschte Norm kann im Men\u00FC Optionen ausgew\u00E4hlt werden.", ButtonType.OK);
					return;
				}
				
				StringBuilder value = new StringBuilder();
				value.append(tfIEEE.getText().replace(" ", ""));
				
				while(value.length() < Preferences.getPrefs().ieeeStandardProperty().get().getBitLength()) {
					// Mit Nullen auf benötigte Länge auffüllen
					value.append("0");
				}
				
				updateIEEEValue(value.toString());
				
				IEEEStandard ieee = Preferences.getPrefs().ieeeStandardProperty().get();
				
				value.insert(ieee.getExpLength() + 1, " ");
				value.insert(1, " ");
				
				tfIEEE.setText(value.toString());
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since 1.0.0
	private void clearIEEE() {
		tfIEEE.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isFocused) {
				if (isFocused && !ConvertingNumbers.isValueToBase(2, tfIEEE.getText())) {
					// Textfelder bei NaN zurücksetzen
					tfIEEE.clear();
					tfDec.clear();
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert das Attribut {@link #focusedTF} bei Auswahl eines Textfeldes durch einen Mausklick und 
	 * verbindet {@link #baseProperty} mit der baseProperty des Textfelds.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Updates the attribute {@link #focusedTF} by selecting a text filed with the cursor 
	 * and connects {@link #baseProperty} with the baseProperty of the text field.
	 * 
	 * @since Bitchanger 0.1.7
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

	// TODO JavaDoc @since Bitchanger 0.1.7 @version 1.0.2
	private void updateIEEEStandard() {
		Preferences.getPrefs().ieeeStandardProperty().addListener(new ChangeListener<IEEEStandard>() {
			@Override
			public void changed(ObservableValue<? extends IEEEStandard> observable, IEEEStandard oldValue, IEEEStandard newValue) {
				tfIEEE.clear();
				tfDec.clear();
			}
		});
	}
	
	
	
// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Actions f\u00FCr alle Buttons
	 * 
	 * @see #setAlphaNumBindings()
	 * @see #setClearAction()
	 * @see #setBackspaceAction()
	 * @see #setSignAction()
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets actions for all buttons
	 * 
	 * @see #setAlphaNumBindings()
	 * @see #setClearAction()
	 * @see #setBackspaceAction()
	 * @see #setSignAction()
	 * 
	 * @since Bitchanger 0.1.7
	 */
	private void setButtonActions() {
		setAlphaNumBindings();
		setClearAction();
		setBackspaceAction();
		setSignAction();
		setNaNAction();
		setInfinityAction();
		disableButtons();
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Bindet die baseProperty aller alphanumerischen Buttons an das Attribut {@link #baseProperty}, um die Buttons
	 * bei einem Wechsel der Basis automatisch aus- oder einzublenden.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Links the baseProperty of all alphanumeric buttons to the attribute {@link #baseProperty},
	 * to show or hide the buttons automatically if the base is changing.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	private void setAlphaNumBindings() {
		for (Button b : alphaNumButtons) {
			((ValueButton)b).getBaseProperty().bind(baseProperty);
		}
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt {@link #value} bei Klick auf den Clear-Button zur\u00FCck und aktualisiert alle Textfelder.
	 * 
	 * @see ChangeableNumber#reset()
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Resets {@link #value} by clicking on clear button and updates all text fields.
	 * 
	 * @see ChangeableNumber#reset()
	 * 
	 * @since Bitchanger 0.1.7
	 */
	private void setClearAction() {
		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				value.reset();
				tfDec.clear();
				tfIEEE.clear();
			}
		});
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * L\u00E4sst den Backspace-Button die Backspace-Taste auf der Tastatur simulieren.
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Simulates the backspace button an the keyboard.
	 * 
	 * @since Bitchanger 0.1.7
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
	 * 
	 * @since Bitchanger 0.1.7
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Turns the sign of {@link #value} by clicking onto the sign button and updates all text fields.
	 * 
	 * @since Bitchanger 0.1.7
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

//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 1.0.0
	private void setNaNAction() {
		nanBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tfDec.setText(ChangeableNumber.NaN);
			}
		});
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 1.0.0
	private void setInfinityAction() {
		infinityBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tfDec.setText(ChangeableNumber.POSITIVE_INFINITY);
			}
		});
	}
	
//	* 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 1.0.0
	private void disableButtons() {
		tfDec.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean tfDecFocused) {
				nanBtn.setDisable(!tfDecFocused);
				infinityBtn.setDisable(!tfDecFocused);
			}
		});
	}
	
}









