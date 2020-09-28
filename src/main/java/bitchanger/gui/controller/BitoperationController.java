/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.calculations.BitLength;
import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.NumberOverflowException;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.views.BitoperationView;
import bitchanger.gui.views.CalculatorView;
import bitchanger.preferences.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

/**	<!-- $LANGUAGE=DE -->
 * Controller, der die Funktion für eine {@linkplain CalculatorView} bereitstellt.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 *
 */
// TODO JavaDoc EN
public class BitoperationController extends CalculationControllerBase<BitoperationView> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->	 ComboBox für die Anzahl der Bits */
	// TODO JavaDoc EN
	private ComboBox<BitLength> bitLength;
	
	/** <!-- $LANGUAGE=DE -->	 Merker für die Anzeige der Tastaturmatrix */
	// TODO JavaDoc EN
	private boolean isShowingKeyboard;
	
// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
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
	 * Konstruiert einen neuen Controller für eine ConverterView und verknüpft die benötigten Attribute mit
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
		
		bitLength.getSelectionModel().select(Preferences.getPrefs().bitLengthProperty().get());
		Preferences.getPrefs().bitLengthProperty().bind(bitLength.getSelectionModel().selectedItemProperty());
		
		setInitialState();
	}


	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	// TODO JavaDoc
	@Override
	protected void parseValue(ChangeableNumber value) throws NumberOverflowException {
		try {
			StringBuffer input = new StringBuffer(textField.getText());
			
			if (Preferences.getPrefs().useUnsignedBitOperationProperty().get()) {
				input.insert(0, "0");
			}
			
			value.setValue(input.toString(), baseProperty.get());
			
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			clearBtn.fire();
			clearBtn.fire();
		} catch (IllegalArgumentException e) {
			value.set(0);
		}
		
		// Prüfen, ob minmale oder maximale Grenze verlassen werden
		BitLength bitLength = Preferences.getPrefs().bitLengthProperty().get();
		long maxValue = Preferences.getPrefs().useUnsignedBitOperationProperty().get() ? bitLength.maxUnsignedValue() : bitLength.maxValue();
		long minValue = Preferences.getPrefs().useUnsignedBitOperationProperty().get() ? bitLength.minUnsignedValue() : bitLength.minValue();
		
		if(value.asDouble() > maxValue) {
			throw new NumberOverflowException("Number " + value.asDouble() + " is too large. Maximum is " + maxValue, "Die eingegebene Zahl ist zu groß!", maxValue, minValue);
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
		
		textField.positionCaret(textField.getLength());
		isShowingResult = true;
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
			throw noe;
		} catch (Exception e) {
			throw e;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	@Override
	protected void updateCalcLabelPos(int base) {
		if(base == 2 && !lastOperation.equals(Operation.NOT)) {
			controllable.positionValuesVertical();
		} else {
			controllable.positionValuesHorizontal();
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
		if(unsignedBitOperations) {
			// commaBtn vergrößern, wenn Tastaturmatrix angezeigt wird
			if (GridPane.getColumnSpan(commaBtn) == 1 && isShowingKeyboard) {
				GridPane.setColumnSpan(commaBtn, 2);
				GridPane.setColumnIndex(commaBtn, GridPane.getColumnIndex(commaBtn) - 1);
			}
				
			// +/- Button ausblenden
			signBtn.setVisible(false);
			
			// 0 Button vergrößern
			GridPane.setColumnSpan(zeroBtn, 2);
			GridPane.setColumnIndex(zeroBtn, GridPane.getColumnIndex(zeroBtn) - 1);
		}
		else {
			// commaBtn verkleinern, wenn Tastaturmatrix angezeigt wird
			if (GridPane.getColumnSpan(commaBtn) == 2 && isShowingKeyboard) {
				GridPane.setColumnSpan(commaBtn, 1);
				GridPane.setColumnIndex(commaBtn, GridPane.getColumnIndex(commaBtn) + 1);
			}
				
			// +/- Button einblenden
			signBtn.setVisible(true);
			
			// 0 Button verkleinern
			GridPane.setColumnSpan(zeroBtn, 1);
			GridPane.setColumnIndex(zeroBtn, GridPane.getColumnIndex(zeroBtn) + 1);
		}
	}
	
	
}







