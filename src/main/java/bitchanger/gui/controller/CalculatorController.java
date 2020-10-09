/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.NumberOverflowException;
import bitchanger.gui.views.CalculatorView;
import bitchanger.preferences.Preferences;
import javafx.beans.binding.StringExpression;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**	<!-- $LANGUAGE=DE -->
 * Controller, der die Funktion f\u00FCr eine {@linkplain CalculatorView} bereitstellt.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.8
 *
 */
// TODO JavaDoc EN
public class CalculatorController extends CalculationControllerBase<CalculatorView> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE -->	Button zum Dividieren */
	// TODO JavaDoc EN
	private Button divideBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button zum Multiplizieren */
	// TODO JavaDoc EN
	private Button multiplyBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button zum Subtrahieren */
	// TODO JavaDoc EN
	private Button subBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button zum Addieren */
	// TODO JavaDoc EN
	private Button addBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr die Modulo-Operation */
	// TODO JavaDoc EN
	private Button moduloBtn;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Konstruiert einen neuen Controller f\u00FCr eine ConverterView und verkn\u00FCpft die ben\u00F6tigten Attribute mit
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
	public CalculatorController(CalculatorView view) {
		super(view);
	}
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		super.initControls();
		
		divideBtn = this.buttonMap.get(controllable.divideBtnKey());
		multiplyBtn = this.buttonMap.get(controllable.multiplyBtnKey());
		subBtn = this.buttonMap.get(controllable.subtractBtnKey());
		addBtn = this.buttonMap.get(controllable.addBtnKey());
		moduloBtn = this.buttonMap.get(controllable.moduloBtnKey());
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
		
		setArithmeticActions();
		consumeKeyEvents();
		
		this.baseProperty.set(Preferences.getPrefs().calculationBaseProperty().get());
		this.baseProperty.bindBidirectional(Preferences.getPrefs().calculationBaseProperty());
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	
	// TODO JavaDoc
	@Override
	protected StringExpression getResultString() {
		return this.result.stringProperty();
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected StringExpression getValue1String() {
		return this.value1.stringProperty();
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected StringExpression getValue2String() {
		return this.value2.stringProperty();
	}

//	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	@Override
	protected void parseValue(ChangeableNumber value) throws NumberOverflowException {
		try {
			value.setValue(textField.getText(), baseProperty.get());
		} catch (NumberOverflowException noe) {
			throw noe;
		} catch (IllegalArgumentException e) {
			value.set(0);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected void updateCalcLabels() {
		clearCalcLabels();
		
		operationLabel.setText(operation.getSymbol());
		
		firstValueLabel.textProperty().bind(value1.stringProperty());
		secondValueLabel.textProperty().bind(value2.stringProperty());
		textField.setText(result.toBaseString(baseProperty.get()));
		
		equalsLabel.setText("=");
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	@Override
	protected void calculate() throws NumberOverflowException, Exception {
		try {
			switch(operation) {
				case ADD:		result.set(value1.asDouble() + value2.asDouble());
					break;
				case SUBSTRACT:	result.set(value1.asDouble() - value2.asDouble());
					break;
				case MULTIPLY:	result.set(value1.asDouble() * value2.asDouble());
					break;
				case DIVIDE:	result.set(value1.asDouble() / value2.asDouble());
					break;
				case MODULO:	result.set(value1.asDouble() % value2.asDouble());
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
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Actions			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	// TODO JavaDoc
	private void setArithmeticActions() {
		setOperationAction(addBtn, Operation.ADD, value1.stringProperty());
		setOperationAction(subBtn, Operation.SUBSTRACT, value1.stringProperty());
		setOperationAction(multiplyBtn, Operation.MULTIPLY, value1.stringProperty());
		setOperationAction(divideBtn, Operation.DIVIDE, value1.stringProperty());
		setOperationAction(moduloBtn, Operation.MODULO, value1.stringProperty());
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	private void consumeKeyEvents() {
		addAccelerator(addBtn, new KeyCodeCombination(KeyCode.ADD), new KeyCodeCombination(KeyCode.PLUS));
		addAccelerator(subBtn, new KeyCodeCombination(KeyCode.SUBTRACT), new KeyCodeCombination(KeyCode.MINUS));
		addAccelerator(multiplyBtn, new KeyCodeCombination(KeyCode.MULTIPLY), new KeyCodeCombination(KeyCode.STAR));
		addAccelerator(divideBtn, new KeyCodeCombination(KeyCode.DIVIDE), new KeyCodeCombination(KeyCode.SLASH));
		addAccelerator(moduloBtn, new KeyCharacterCombination("%", KeyCombination.SHIFT_DOWN), new KeyCodeCombination(KeyCode.DIVIDE, KeyCombination.SHIFT_DOWN));
	}

}







