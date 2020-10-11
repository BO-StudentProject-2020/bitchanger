/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
<<<<<<< HEAD
 * Entwickelt für das AID-Labor der Hochschule Bochum
=======
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
>>>>>>> master
 * 
 */

package bitchanger.gui.controller;

import java.util.Optional;

import bitchanger.calculations.BitLength;
import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.NumberOverflowException;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.views.BitoperationView;
import bitchanger.gui.views.CalculatorView;
<<<<<<< HEAD
=======
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
>>>>>>> master
import bitchanger.preferences.Preferences;
import bitchanger.util.FXUtils;
import javafx.beans.binding.StringExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**	<!-- $LANGUAGE=DE -->
<<<<<<< HEAD
 * Controller, der die Funktion für eine {@linkplain CalculatorView} bereitstellt.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
=======
 * Controller, der die Funktion f\u00FCr eine {@linkplain CalculatorView} bereitstellt.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.8
>>>>>>> master
 *
 */
// TODO JavaDoc EN
public class BitoperationController extends CalculationControllerBase<BitoperationView> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
<<<<<<< HEAD
	/** <!-- $LANGUAGE=DE -->	 ComboBox für die Anzahl der Bits */
	// TODO JavaDoc EN
	private ComboBox<BitLength> bitLength;
	
	/** <!-- $LANGUAGE=DE -->	 Merker für die Anzeige der Tastaturmatrix */
=======
	/** <!-- $LANGUAGE=DE -->	 ComboBox f\u00FCr die Anzahl der Bits */
	// TODO JavaDoc EN
	private ComboBox<BitLength> bitLength;
	
	/** <!-- $LANGUAGE=DE -->	 Merker f\u00FCr die Anzeige der Tastaturmatrix */
>>>>>>> master
	// TODO JavaDoc EN
	private boolean isShowingKeyboard;
	
// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
<<<<<<< HEAD
	/**	<!-- $LANGUAGE=DE -->	Button für das logische UND */
	// TODO JavaDoc EN
	private Button andBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das logische ODER */
	// TODO JavaDoc EN
	private Button orBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das logische NICHT */
	// TODO JavaDoc EN
	private Button notBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das logische NAND */
	// TODO JavaDoc EN
	private Button nandBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das logische NOR */
	// TODO JavaDoc EN
	private Button norBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das logische Exklusiv-ODER */
	// TODO JavaDoc EN
	private Button xorBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für Linksshift */
	// TODO JavaDoc EN
	private Button shiftLeftBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für Rechtsshift */
=======
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das logische UND */
	// TODO JavaDoc EN
	private Button andBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das logische ODER */
	// TODO JavaDoc EN
	private Button orBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das logische NICHT */
	// TODO JavaDoc EN
	private Button notBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das logische NAND */
	// TODO JavaDoc EN
	private Button nandBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das logische NOR */
	// TODO JavaDoc EN
	private Button norBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das logische Exklusiv-ODER */
	// TODO JavaDoc EN
	private Button xorBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr Linksshift */
	// TODO JavaDoc EN
	private Button shiftLeftBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr Rechtsshift */
>>>>>>> master
	// TODO JavaDoc EN
	private Button shiftRightBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem ein Komma eingegeben werden kann @see {@link AlphaNumKeys#COMMA_BTN_KEY}*/
	/* <!-- $LANGUAGE=EN -->	Button that is used to enter a comma @see {@link AlphaNumKeys#COMMA_BTN_KEY} */
	private Button commaBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem die Zahl 0 eingegeben wird */
	/* <!-- $LANGUAGE=EN -->	Button that is used to enter the number 0 */
	private Button zeroBtn;

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Konstruiert einen neuen Controller für eine ConverterView und verknüpft die benötigten Attribute mit
=======
	 * Konstruiert einen neuen Controller f\u00FCr eine ConverterView und verkn\u00FCpft die ben\u00F6tigten Attribute mit
>>>>>>> master
	 * Referenzen auf die Bedienelemente aus der ConverterView.
	 * 
	 * @param view ConverterView, an die dieser Controller gebunden wird
	 * 
	 * @see #initControls()
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Construct a new controller for a ConverterView and links the necessary attributes with
	 * references to the operating elements of the ConverterView.
	 * 
	 * @param view ConverterView, where the controller is linked to
	 * 
	 * @see #initControls()
	 */
	public BitoperationController(BitoperationView view) {
		super(view);
		
		isShowingKeyboard = false;
	}
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void initControls() {
		super.initControls();
		
		if(nodeMap.get(controllable.bitLengthKey()) instanceof ComboBox) {
			bitLength = (ComboBox) nodeMap.get(controllable.bitLengthKey());
		}
		
		andBtn = this.buttonMap.get(controllable.andBtnKey());
		orBtn = this.buttonMap.get(controllable.orBtnKey());
		notBtn = this.buttonMap.get(controllable.notBtnKey());
		nandBtn = this.buttonMap.get(controllable.nandBtnKey());
		norBtn = this.buttonMap.get(controllable.norBtnKey());
		xorBtn = this.buttonMap.get(controllable.xorBtnKey());
		shiftLeftBtn = this.buttonMap.get(controllable.shiftLeftBtnKey());
		shiftRightBtn = this.buttonMap.get(controllable.shiftRightBtnKey());
		commaBtn = buttonMap.get(controllable.commaBtnKey());
		zeroBtn = buttonMap.get(AlphaNumKeys.ZERO_BTN_KEY);
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	{@inheritDoc} */
	@Override
	public void setActions() {
		super.setActions();
		
		setButtonActions();
		
		setUnfocusComboBoxBitLength();
		
		consumeKeyEvents();
		setBindingsAndListeners();
		setInitialState();
	}




//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	@Override
	protected StringExpression getResultString() {
		return this.result.logicStringProperty();
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected StringExpression getValue1String() {
		return this.value1.logicStringProperty();
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected StringExpression getValue2String() {
		return this.value2.logicStringProperty();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	@Override
	protected void parseValue(ChangeableNumber value) throws NumberOverflowException {
		try {
			StringBuffer input = new StringBuffer(textField.getText());
			
			if (Preferences.getPrefs().useUnsignedBitOperationProperty().get()) {
				if (textField.getText().contains("-")) {
					throw new NumberOverflowException("for String " + input + " - negative numbers are not allowed", 
							"Bei vorzeichenlosen Bitoperationen sind nur positive, ganze Zahlen erlaubt!",
							bitLength.getSelectionModel().getSelectedItem().maxUnsignedValue(), bitLength.getSelectionModel().getSelectedItem().minUnsignedValue());
				}
				
				input.insert(0, "0");
			}
			
			value.setValue(input.toString(), baseProperty.get());
			
		} catch (NumberFormatException | NullPointerException e) {
<<<<<<< HEAD
=======
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, e);
			
>>>>>>> master
			e.printStackTrace();
			clearBtn.fire();
			clearBtn.fire();
		} catch (IllegalArgumentException e) {
<<<<<<< HEAD
=======
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, e);
			
>>>>>>> master
			value.set(0);
		}
		
		// Prüfen, ob minmale oder maximale Grenze verlassen werden
		BitLength bitLength = Preferences.getPrefs().bitLengthProperty().get();
		long maxValue = Preferences.getPrefs().useUnsignedBitOperationProperty().get() ? bitLength.maxUnsignedValue() : bitLength.maxValue();
		long minValue = Preferences.getPrefs().useUnsignedBitOperationProperty().get() ? bitLength.minUnsignedValue() : bitLength.minValue();
		
		if(value.asDouble() > maxValue) {
<<<<<<< HEAD
			throw new NumberOverflowException("Number " + value.asDouble() + " is too large. Maximum is " + maxValue, "Die eingegebene Zahl ist zu groß!", maxValue, minValue);
=======
			throw new NumberOverflowException("Number " + value.asDouble() + " is too large. Maximum is " + maxValue, "Die eingegebene Zahl ist zu gro\u00DF!", maxValue, minValue);
>>>>>>> master
		}
		else if(value.asDouble() < minValue) {
			throw new NumberOverflowException("Number " + value.asDouble() + " is too small. Minimum is " + minValue, "Die eingegebene Zahl ist zu klein!", maxValue, minValue);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected void updateCalcLabels() {
		clearCalcLabels();
		
		operationLabel.setText(operation.getSymbol());
		
		if(operation.equals(Operation.NOT)) {
			secondValueLabel.textProperty().bind(value1.logicStringProperty());
		}
		else {
			firstValueLabel.textProperty().bind(value1.logicStringProperty());
			secondValueLabel.textProperty().bind(value2.logicStringProperty());
		}
		
		textField.setText(result.logicStringProperty().get());
		equalsLabel.setText("=");
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected void calculate() throws NumberOverflowException, Exception {
		value1.set((long)value1.asDouble());
		value2.set((long)value2.asDouble());
		
		try {
			switch(operation) {
				case AND:		result.set((long)value1.asDouble() & (long)value2.asDouble());
					break;
				case OR:		result.set((long)value1.asDouble() | (long)value2.asDouble());
					break;
				case NOT:		result.set(~(long)value1.asDouble());
					break;
				case NAND:		result.set(~((long)value1.asDouble() & (long)value2.asDouble()));
					break;
				case NOR:		result.set(~((long)value1.asDouble() | (long)value2.asDouble()));
					break;
				case XOR:		result.set((long)value1.asDouble() ^ (long)value2.asDouble());
					break;
				case SHIFT_LEFT:	result.set((long)value1.asDouble() << (long)value2.asDouble());
					break;
				case SHIFT_RIGHT:	result.set((long)value1.asDouble() >> (long)value2.asDouble());
					break;
				case UNDEFINED:		// Fall through
				default:			result.reset();
					break;
			}
		} catch (NumberOverflowException noe) {
<<<<<<< HEAD
			throw noe;
		} catch (Exception e) {
=======
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, noe);
			
			throw noe;
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL, e);
			
>>>>>>> master
			throw e;
		}
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
		setBitoperationsText(Preferences.getPrefs().showBitOperationSymbolsProperty().get());
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Actions			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Setzt die Actions für alle Buttons
=======
	 * Setzt die Actions f\u00FCr alle Buttons
>>>>>>> master
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
		setLogicActions();
		
		setKeyboardBtnAction();
		updateBitoperationSymbols();
		
		Preferences.getPrefs().useUnsignedBitOperationProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				updateSignBtnVisible(newValue);
			}
		});
		
		updateSignBtnVisible(Preferences.getPrefs().useUnsignedBitOperationProperty().get());
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setLogicActions() {
		setOperationAction(andBtn, Operation.AND, value1.logicStringProperty());
		setOperationAction(orBtn, Operation.OR, value1.logicStringProperty());
		setOperationAction(nandBtn, Operation.NAND, value1.logicStringProperty());
		setOperationAction(norBtn, Operation.NOR, value1.logicStringProperty());
		setOperationAction(xorBtn, Operation.XOR, value1.logicStringProperty());
		setOperationAction(shiftLeftBtn, Operation.SHIFT_LEFT, value1.logicStringProperty());
		setOperationAction(shiftRightBtn, Operation.SHIFT_RIGHT, value1.logicStringProperty());
		setNotOperationAction();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setNotOperationAction() {
		notBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setFirstValue(Operation.NOT, value1.logicStringProperty());
				equalsBtn.fire();
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setKeyboardBtnAction() {
		if (GridPane.getColumnSpan(buttonMap.get(controllable.commaBtnKey())) == null) {
			GridPane.setColumnSpan(buttonMap.get(controllable.commaBtnKey()), 1);
		}
		
		buttonMap.get(controllable.keyboardBtnKey()).addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (Preferences.getPrefs().useUnsignedBitOperationProperty().get()) {
					if (GridPane.getColumnSpan(commaBtn) == 1) {
						GridPane.setColumnSpan(commaBtn, 2);
						GridPane.setColumnIndex(commaBtn, GridPane.getColumnIndex(commaBtn) - 1);
					} else {
						GridPane.setColumnSpan(commaBtn, 1);
						GridPane.setColumnIndex(commaBtn, GridPane.getColumnIndex(commaBtn) + 1);
					}
				}
				
				isShowingKeyboard = !isShowingKeyboard;
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateBitoperationSymbols() {
		Preferences.getPrefs().showBitOperationSymbolsProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean showSymbols) {
				setBitoperationsText(showSymbols);
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setBitoperationsText(boolean showSymbols){
		if (showSymbols) {
			andBtn.setText("&");
			orBtn.setText("|");
			notBtn.setText("~");
			nandBtn.setText("~&");
			norBtn.setText("~|");
			xorBtn.setText("^");
		} else {
			andBtn.setText("AND");
			orBtn.setText("OR");
			notBtn.setText("NOT");
			nandBtn.setText("NAND");
			norBtn.setText("NOR");
			xorBtn.setText("XOR");
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateSignBtnVisible(boolean unsignedBitOperations) {
		if (GridPane.getColumnSpan(zeroBtn) == null) {
			GridPane.setColumnSpan(zeroBtn, 1);
		}
		
		if(unsignedBitOperations) {
			// commaBtn vergrößern, wenn Tastaturmatrix angezeigt wird
			if (GridPane.getColumnSpan(commaBtn) == 1 && isShowingKeyboard) {
				GridPane.setColumnSpan(commaBtn, 2);
				GridPane.setColumnIndex(commaBtn, GridPane.getColumnIndex(commaBtn) - 1);
			}
				
			// +/- Button ausblenden
			signBtn.setVisible(false);
			
			if (GridPane.getColumnSpan(zeroBtn) == 1) {
				// 0 Button vergrößern
				GridPane.setColumnSpan(zeroBtn, 2);
				GridPane.setColumnIndex(zeroBtn, GridPane.getColumnIndex(zeroBtn) - 1);
			}
		}
		else {
			// commaBtn verkleinern, wenn Tastaturmatrix angezeigt wird
			if (GridPane.getColumnSpan(commaBtn) == 2 && isShowingKeyboard) {
				GridPane.setColumnSpan(commaBtn, 1);
				GridPane.setColumnIndex(commaBtn, GridPane.getColumnIndex(commaBtn) + 1);
			}
				
			// +/- Button einblenden
			signBtn.setVisible(true);
			
			if (GridPane.getColumnSpan(zeroBtn) == 2) {
				// 0 Button verkleinern
				GridPane.setColumnSpan(zeroBtn, 1);
				GridPane.setColumnIndex(zeroBtn, GridPane.getColumnIndex(zeroBtn) + 1);
			}
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setUnfocusComboBoxBitLength() {
		bitLength.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textField.requestFocus();
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setBindingsAndListeners() {
		bitLength.getSelectionModel().select(Preferences.getPrefs().bitLengthProperty().get());
<<<<<<< HEAD
		Preferences.getPrefs().bitLengthProperty().bind(bitLength.getSelectionModel().selectedItemProperty());
=======
>>>>>>> master
		
		Preferences.getPrefs().bitLengthProperty().addListener(new ChangeListener<BitLength>() {
			@Override
			public void changed(ObservableValue<? extends BitLength> observable, BitLength oldValue, BitLength newValue) {
<<<<<<< HEAD
=======
				bitLength.getSelectionModel().select(newValue);
			}
		});
		
		bitLength.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BitLength>() {
			@Override
			public void changed(ObservableValue<? extends BitLength> observable, BitLength oldValue, BitLength newValue) {
				if(Preferences.getPrefs().bitLengthProperty().get().equals(newValue)) {
					// Einstellungen wurden zurückgesetzt -> keine weiteren Warnungen anzeigen
					value1.set(0);
					value2.set(0);
					result.set(0);
					operation = Operation.UNDEFINED;
					lastOperation = Operation.UNDEFINED;
					clearBtn.fire();
					clearBtn.fire();
					return;
				}
				
				Preferences.getPrefs().bitLengthProperty().set(newValue);
				
>>>>>>> master
				checkUnsignedBitLength();

				// Warnung und Löschen aller Eingaben beim verkleinern der Bitlänge
				if (!isCleared && newValue.getNumberOfBits() < oldValue.getNumberOfBits()) {
					boolean warningDeactivated = Preferences.getPrefs().bitLengthDeleteWarningDeactivatedProperty().get();
<<<<<<< HEAD
					Optional<ButtonType> warningResult = FXUtils.showDeactivatableDialog(AlertType.WARNING, "Warnung", "Achtung", "Beim Verkleinern der Bitlänge gehen alle bisherigen Eingaben verloren!", Preferences.getPrefs().bitLengthDeleteWarningDeactivatedProperty(), ButtonType.CANCEL, ButtonType.OK);
=======
					Optional<ButtonType> warningResult = FXUtils.showDeactivatableDialog(AlertType.WARNING, "Warnung", "Achtung", "Beim Verkleinern der Bitl\u00E4nge gehen alle bisherigen Eingaben verloren!", Preferences.getPrefs().bitLengthDeleteWarningDeactivatedProperty(), ButtonType.CANCEL, ButtonType.OK);
>>>>>>> master
					
					if(warningDeactivated || (warningResult.isPresent() && warningResult.get().equals(ButtonType.OK))) {
						value1.set(0);
						value2.set(0);
						result.set(0);
<<<<<<< HEAD
=======
						operation = Operation.UNDEFINED;
						lastOperation = Operation.UNDEFINED;
>>>>>>> master
						clearBtn.fire();
						clearBtn.fire();
					} else {
						bitLength.getSelectionModel().select(oldValue);
					}
				}
			}
		});
		
		Preferences.getPrefs().useUnsignedBitOperationProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				checkUnsignedBitLength();
				
				if(isShowingResult) {
					updateResult();
				}
			}
		});
<<<<<<< HEAD
=======
		
		this.baseProperty.set(Preferences.getPrefs().bitOpertionBaseProperty().get());
		this.baseProperty.bindBidirectional(Preferences.getPrefs().bitOpertionBaseProperty());
>>>>>>> master
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void checkUnsignedBitLength() {
		boolean is64Bit = Preferences.getPrefs().bitLengthProperty().get().equals(BitLength._64_BIT);
		boolean isUnsigned = Preferences.getPrefs().useUnsignedBitOperationProperty().get();
		
		if(is64Bit && isUnsigned) {
<<<<<<< HEAD
			String message = "Bei vorzeichenlosen Bitoperationen ist die Bitlänge auf maximal 63 Bit begrenzt.";
=======
			String message = "Bei vorzeichenlosen Bitoperationen ist die Bitl\u00E4nge auf maximal 63 Bit begrenzt.";
>>>>>>> master
			FXUtils.showDeactivatableDialog(AlertType.INFORMATION, "Hinweis", "Achtung", message, Preferences.getPrefs().unsignedBitLengthWarningDeactivatedProperty());
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateResult() {
		operation = lastOperation;
		
		try {
			calculate();
		} catch (NumberOverflowException noe) {
<<<<<<< HEAD
			noe.setDescription("Das Ergebnis der Berechnung verlässt den zugelassenen Zahlenbereich.");
			FXUtils.showNumberOverflowWarning(noe);
			return;
		} catch (Exception e) {
=======
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, noe);
			
			noe.setDescription("Das Ergebnis der Berechnung verl\u00E4sst den zugelassenen Zahlenbereich.");
			FXUtils.showNumberOverflowWarning(noe);
			return;
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
			
>>>>>>> master
			e.printStackTrace();
			return;
		}
		
		updateCalcLabels();
		textField.positionCaret(textField.getLength());
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	private void consumeKeyEvents() {
		addAccelerator(andBtn, new KeyCharacterCombination("&", KeyCombination.SHIFT_DOWN));
		addAccelerator(orBtn, new KeyCharacterCombination("|", KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN));
		addAccelerator(notBtn, new KeyCharacterCombination("!", KeyCombination.SHIFT_DOWN));
		addAccelerator(nandBtn, new KeyCharacterCombination("&", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN));
		addAccelerator(norBtn, new KeyCharacterCombination("|", KeyCombination.SHIFT_DOWN));
		addAccelerator(xorBtn, new KeyCodeCombination(KeyCode.CIRCUMFLEX));	// TODO "^"-Taste reagiert nicht
		addAccelerator(shiftLeftBtn, new KeyCharacterCombination("<"));
		addAccelerator(shiftRightBtn, new KeyCharacterCombination(">", KeyCombination.SHIFT_DOWN));
		
		addAccelerator(andBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F1, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F1, KeyCombination.CONTROL_DOWN));
		addAccelerator(orBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F2, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F2, KeyCombination.CONTROL_DOWN));
		addAccelerator(notBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F3, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F3, KeyCombination.CONTROL_DOWN));
		addAccelerator(nandBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F4, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F4, KeyCombination.CONTROL_DOWN));
		addAccelerator(norBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F5, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F5, KeyCombination.CONTROL_DOWN));
		addAccelerator(xorBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F6, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F6, KeyCombination.CONTROL_DOWN));
		addAccelerator(shiftLeftBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F7, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F7, KeyCombination.CONTROL_DOWN));
		addAccelerator(shiftRightBtn, (EventType<KeyEvent>)null, new KeyCodeCombination(KeyCode.F8, KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.F8, KeyCombination.CONTROL_DOWN));
	}
	
}







