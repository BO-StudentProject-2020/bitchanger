/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.ConvertingNumbers;
import bitchanger.calculations.NumberOverflowException;
import bitchanger.calculations.SimpleChangeableNumber;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.ValueButton;
import bitchanger.gui.controls.ValueField;
import bitchanger.gui.views.CalculationViewBase;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**	<!-- $LANGUAGE=DE -->
 * Controller, der einige Grundfunktionen f\u00FCr eine {@linkplain CalculationViewBase} bereitstellt.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.8
 *
 */
// TODO JavaDoc EN
public abstract class CalculationControllerBase<T extends CalculationViewBase> extends ControllerBase<T> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
// protected	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/**	<!-- $LANGUAGE=DE -->	Ergebnis der letzten Berechnung */
	/*	<!-- $LANGUAGE=EN -->	Last result */
	protected final ChangeableNumber value1;
	
	/**	<!-- $LANGUAGE=DE -->	Ergebnis der letzten Berechnung */
	/*	<!-- $LANGUAGE=EN -->	Last result */
	protected final ChangeableNumber value2;
	
	/**	<!-- $LANGUAGE=DE -->	Ergebnis der letzten Berechnung */
	/*	<!-- $LANGUAGE=EN -->	Last result */
	protected final ChangeableNumber result;
	
	/** <!-- $LANGUAGE=DE -->	Merker f\u00FCr die Anzeige eines Rechenergebnisses im Textfeld */
	// TODO JavaDoc EN
	protected boolean isShowingResult;
	
	/** <!-- $LANGUAGE=DE --> 	Merker f\u00FCr das l\u00F6schen von {@link #lastOperation} beim zweiten Klick auf den Button {@link #clearBtn} */
	// TODO JavaDoc EN
	protected boolean isCleared;
	
	/** <!-- $LANGUAGE=DE -->	Rechenoperation, die ausgef\u00FChrt werden soll */
	// TODO: JavaDoc EN
	protected Operation operation;
	
	/** <!-- $LANGUAGE=DE -->	Rechenoperation, die zuletzt ausgef\u00FChrt wurde */
	// TODO: JavaDoc EN
	protected Operation lastOperation;
	
	/**	<!-- $LANGUAGE=DE -->	Property zum Einstellen der Basis des aktuell fokussierten Textfelds */
	/*	<!-- $LANGUAGE=EN -->	Property to adjust the base of the currently focused text field */
	protected final IntegerProperty baseProperty;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die Eingabe */
	/*	<!-- $LANGUAGE=EN -->	Textfield for input */
	protected final ValueField textField;
	
	/** <!-- $LANGUAGE=DE -->	Label f\u00FCr den ersten Wert */
	// TODO JavaDoc EN
	protected final Label firstValueLabel;

	/** <!-- $LANGUAGE=DE -->	Label f\u00FCr die Rechenoperation */
	// TODO JavaDoc EN
	protected final Label operationLabel;

	/** <!-- $LANGUAGE=DE -->	Label f\u00FCr den zweiten Wert */
	// TODO JavaDoc EN
	protected final Label secondValueLabel;

	/** <!-- $LANGUAGE=DE -->	Label f\u00FCr das Gleichheitszeichen */
	// TODO JavaDoc EN
	protected final Label equalsLabel;

	/**	<!-- $LANGUAGE=DE -->	Button zum Berechnen */
	// TODO JavaDoc EN
	protected final Button equalsBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button zum Berechnen */
	// TODO JavaDoc EN
	protected final Button signBtn;
	
	/**	<!-- $LANGUAGE=DE --> 	Button zum L\u00F6schen und Zur\u00FCcksetzen */
	/*	<!-- $LANGUAGE=EN --> 	Button to clear the values */
	protected Button clearBtn;
	
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/**	<!-- $LANGUAGE=DE -->	Spinner f\u00FCr die ausw\u00E4hlbare, beliebige Basis */
	/*	<!-- $LANGUAGE=EN -->	Spinner for the eligible base */
	private BaseSpinner anyBase;
	
	/** <!-- $LANGUAGE=DE -->	Label f\u00FCr die Basis des Ergebnisses */
	// TODO JavaDoc EN
	private Label baseLabel;
	
	/**	<!-- $LANGUAGE=DE -->	Button, der die Backspace-Taste auf der Tastatur simuliert */
	/*	<!-- $LANGUAGE=DE -->	Button that simulate the backspace key */
	private Button backspcBtn;
	
	/**	<!-- $LANGUAGE=DE -->	alphanumerische Buttons zur Simulation einer Tastatur */
	/*	<!-- $LANGUAGE=EN -->	alphanumeric keys to simulate a keyboard */
	private Button[] alphaNumButtons;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das Hexadezimalsystem */
	// TODO JavaDoc EN
	private Button hexBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das Dezimalsystem */
	// TODO JavaDoc EN
	private Button decBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das Oktalsystem */
	// TODO JavaDoc EN
	private Button octBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button f\u00FCr das Bin\u00E4rsystem */
	// TODO JavaDoc EN
	private Button binBtn;
	
	
	
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
	public CalculationControllerBase(T view) {
		super(view);
		
		this.equalsBtn = this.buttonMap.get(controllable.equalsBtnKey());
		this.signBtn = this.buttonMap.get(controllable.signBtnKey());
		this.textField = (ValueField) this.textFieldMap.get(controllable.tfKey());
		this.firstValueLabel = (Label) this.nodeMap.get(controllable.firstValueLabelKey());
		this.operationLabel = (Label) this.nodeMap.get(controllable.operationLabelKey());
		this.secondValueLabel = (Label) this.nodeMap.get(controllable.secondValueLabelKey());
		this.equalsLabel = (Label) this.nodeMap.get(controllable.equalsLabelKey());
		
		this.value1 = new SimpleChangeableNumber();
		this.value2 = new SimpleChangeableNumber();
		this.result = new SimpleChangeableNumber();
		this.operation = Operation.UNDEFINED;
		this.lastOperation = Operation.UNDEFINED;
		this.baseProperty = new SimpleIntegerProperty(10);
		this.isShowingResult = false;
		this.isCleared = false;
		
		this.value1.baseProperty().bind(baseProperty);
		this.value2.baseProperty().bind(baseProperty);
		this.result.baseProperty().bind(baseProperty);
	}
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		if(nodeMap.get(controllable.baseSpinnerKey()) instanceof BaseSpinner) {
			this.anyBase = (BaseSpinner) nodeMap.get(controllable.baseSpinnerKey());
		}
		
		baseLabel = (Label) this.nodeMap.get(controllable.baseLabelKey());
		
		initButtons();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// TODO JavaDoc
	protected abstract StringExpression getResultString();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	protected abstract StringExpression getValue1String();

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	protected abstract StringExpression getValue2String();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	protected abstract void parseValue(ChangeableNumber value) throws NumberOverflowException;

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	protected abstract void updateCalcLabels();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	protected abstract void calculate() throws NumberOverflowException, Exception;
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	{@inheritDoc} */
	@Override
	public void setActions() {
		setSpinnerActions();
		setButtonActions();
		
		consumeKeyEvents();
		setInitialState();
		
		textField.getBaseProperty().bind(anyBase.valueProperty());
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Sucht die ben\u00F6tigten Referenzen zu den Buttons aus der buttonMap und speichert diese in den Attributen
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Searches the necessary references to the buttons of the buttonMap and stores these in the attributes
	 */
	private void initButtons() {
		this.clearBtn = this.buttonMap.get(controllable.clearBtnKey());
		this.backspcBtn = this.buttonMap.get(controllable.backspaceBtnKey());

		alphaNumButtons = new Button[16];
		for (int i = 0; i < 6; i++) {
			alphaNumButtons[i] = this.buttonMap.get("alpha_" + i);
		}

		for (int i = 0; i < 10; i++) {
			alphaNumButtons[i + 6] = this.buttonMap.get("num_" + i);
		}
		
		hexBtn = this.buttonMap.get(controllable.hexBtnKey());
		decBtn = this.buttonMap.get(controllable.decBtnKey());
		octBtn = this.buttonMap.get(controllable.octBtnKey());
		binBtn = this.buttonMap.get(controllable.binBtnKey());
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Pr\u00FCft, ob die \u00FCbergebene Node einer der Pfeil-Buttons zum Scrollen in einem Spinner ist.
	 * Dies ist der Fall, wenn die StyleClass den String "arrow-button" enth\u00E4lt.
	 * 
	 * @param n	Testkandidat f\u00FCr einen Pfeil-Button
	 * @return	{@code true}, wenn die StyleClass von n den String "arrow-button" enth\u00E4lt, {@code false} andernfalls
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Checks if the committed node is one of the button, which is used to scroll the spinner.
	 * If the StyleClass contains the string "arrow-button", the button is used to scroll the spinner.
	 * 
	 * @param n	Test for an arrow-button
	 * @return	{@code true}, if the StyleClass of n contains the string arrow-button", if not {@code false}
	 */
	private boolean isArrowButton(Node n) {
		for(String s: n.getStyleClass()) {
			if(s.contains("arrow-button")) {
				return true;
			}
		}
		
		return false;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Attribute auf den Ausgangszustand.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the attributes to the initial condition.
	 */
	private void setInitialState() {
		textField.requestFocus();
		
		octBtn.fire();
		decBtn.fire();
		
		this.operation = Operation.UNDEFINED;
		this.lastOperation = Operation.UNDEFINED;
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Actions			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	// TODO JavaDoc
	protected void clearCalcLabels() {
		firstValueLabel.textProperty().unbind();
		operationLabel.textProperty().unbind();
		secondValueLabel.textProperty().unbind();
		equalsLabel.textProperty().unbind();
		
		firstValueLabel.setText("");
		operationLabel.setText("");
		secondValueLabel.setText("");
		equalsLabel.setText("");
	}


	
// Spinner	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert die Basis, wenn sich die valueProperty von {@link #anyBase} \u00E4ndert.
	 * Sorgt außerdem daf\u00FCr, dass anyBase den Fokus nach der Eingabe einer Basis im Editor oder mit den 
	 * Inkrement- und Dekrement-Buttons wieder an {@link #textField} abgibt.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Updates the base, if the valueProperty of {@link #anyBase} changes.
	 * Furthermore makes sure that anyBase is focused on {@link #textField} after an input of a base or using the increment and decrement buttons.
	 */
	private void setSpinnerActions() {
		anyBase.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newBase) {
				if (newBase >= ConvertingNumbers.MIN_BASE && newBase <= ConvertingNumbers.MAX_BASE) {
					baseProperty.set(newBase);
				}
			}
		});
		

		anyBase.getEditor().setOnAction(this::focusTF);
		
		// Focus nach Klick auf Button abgeben
		anyBase.skinProperty().addListener(e -> {
			anyBase.getChildrenUnmodifiable().stream().filter(this::isArrowButton).forEach(node -> {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, this::focusTF);
			});
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Fokussiert das Textfeld f\u00FCr die Eingabe.
	 * Diese Methode wird als Referenz f\u00FCr einen EventHandler verwendet.
	 * 
	 * @param e	Event, das den EventHanler ausl\u00F6st
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Focuses the input text field.
	 * This method is used as reference for an EventHandler.
	 * 
	 * @param e	Event that causes an EventHandler
	 */
	private void focusTF(Event e) {
		textField.requestFocus();
	}

	
// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Actions f\u00FCr alle Buttons
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
		
		setEqualsAction();
		setBaseActions();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

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

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setClearAction() {
		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textField.clear();
				
				if(isShowingResult && operation.isUndefined()) {
					StringExpression firstValText;
					if(lastOperation.equals(Operation.NOT)) {
						firstValText = new SimpleStringProperty(lastOperation.getSymbol() + " ");
						firstValText = firstValText.concat(getValue1String());
					} else {
						firstValText = getValue1String().concat(" " + lastOperation.getSymbol() + " ");
						firstValText = firstValText.concat(getValue2String());
					}

					firstValueLabel.textProperty().bind(firstValText);
					operationLabel.setText("=");
					secondValueLabel.textProperty().bind(getResultString());
					equalsLabel.setText("");
				} 
				else {
					equalsLabel.setText("");
					clearCalcLabels();
					operation = Operation.UNDEFINED;
					lastOperation = Operation.UNDEFINED;
					isCleared = true;
				}
				
				isShowingResult = false;
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * L\u00E4sst den Backspace-Button die Backspace-Taste auf der Tastatur simulieren.
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setSignAction() {
		signBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int caretPos = textField.getCaretPosition();
				
				if (textField.getBase() == 2) {
					ChangeableNumber num = new SimpleChangeableNumber();
					num.setBin(textField.getText());
					num.set(- num.asDouble());
					textField.setText(num.toBinString());
				}
				else {
					if (textField.getText().startsWith("-")) {
						textField.setText(textField.getText().substring(1));
						caretPos--;
					} else if (textField.getText().startsWith("+")) {
						textField.setText("-" + textField.getText().substring(1));
					} else {
						textField.setText("-" + textField.getText());
						caretPos++;
					} 
				}
				
				textField.positionCaret(caretPos);
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	protected void setOperationAction(Button b, Operation o, ObservableValue<String> firstValueText) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isCleared = false;
				
				if (!operation.isUndefined() && firstValueText.getValue().equals("")) {
					operation = o;
					operationLabel.setText(o.getSymbol());
				} else {
					setFirstValue(o, firstValueText);
				}
			}
		});
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	protected void setFirstValue(Operation o, ObservableValue<String> firstValueText) {
		if (!operation.isUndefined()) {
			equalsBtn.fire();
		}
		
		try {
			parseValue(value1);
		} catch (NumberOverflowException noe) {
			FXUtils.showNumberOverflowWarning(noe);
			return;
		}
		
		textField.clear();
		clearCalcLabels();
		firstValueLabel.textProperty().bind(firstValueText);
		operationLabel.setText(o.getSymbol());
		
		operation = o;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setEqualsAction() {
		equalsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(operation.isUndefined()) {
					if(lastOperation.isUndefined()) {
						return;
					}
					
					// letzte Operation wiederholen
					operation = lastOperation;
					try {
						value1.set(result.asDouble());
					} catch (Exception e) {
						return;
					}
				} else {
					try {
						parseValue(value2);
					} catch (NumberOverflowException noe) {
						FXUtils.showNumberOverflowWarning(noe);
						return;
					} catch (Exception e) {
						return;
					}
				}
				
				try {
					calculate();
				} catch (NumberOverflowException noe) {
					noe.setDescription("Das Ergebnis der Berechnung verl\u00E4sst den zugelassenen Zahlenbereich.");
					FXUtils.showNumberOverflowWarning(noe);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				
				updateCalcLabels();
				textField.positionCaret(textField.getLength());
				isShowingResult = true;

				lastOperation = operation;
				operation = Operation.UNDEFINED;
				
				updateCalcLabelPos(baseProperty.get());
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setBaseActions() {
		setBaseBtnAction(hexBtn, 16);
		setBaseBtnAction(decBtn, 10);
		setBaseBtnAction(octBtn, 8);
		setBaseBtnAction(binBtn, 2);
		
		baseProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldBase, Number newBase) {
				updateBase(newBase);
				updateCalcLabelPos(newBase.intValue());
				updateTextField(oldBase, newBase);
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	private void setBaseBtnAction(Button baseBtn, int base) {
		baseBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				anyBase.getValueFactory().setValue(base);
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateBase(Number newBase) {
		for(Button b : ArrayUtils.arrayOf(hexBtn, decBtn, octBtn, binBtn)) {
			b.setId("UNSELECTED_BASE_BUTTON");
		}
		
		switch(newBase.intValue()) {
			case 16:
				baseLabel.setText("hex");
				hexBtn.setId("SELECTED_BASE_BUTTON");
				break;
			case 10:
				baseLabel.setText("dec");
				decBtn.setId("SELECTED_BASE_BUTTON");
				break;
			case 8:
				baseLabel.setText("oct");
				octBtn.setId("SELECTED_BASE_BUTTON");
				break;
			case 2:
				baseLabel.setText("bin");
				binBtn.setId("SELECTED_BASE_BUTTON");
				break;
			default:
				baseLabel.setText(String.valueOf(newBase.intValue()));
				break;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateCalcLabelPos(int base) {
		if(base == 2) {
			controllable.positionValuesVertical();
		} else {
			controllable.positionValuesHorizontal();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateTextField(Number oldBase, Number newBase) {
		ChangeableNumber num = new SimpleChangeableNumber();
		num.baseProperty().set(newBase.intValue());
		
		try {
			num.setValue(textField.getText(), oldBase.intValue());
		} catch (NumberOverflowException noe) {
			num.reset();
			noe.setDescription("Die eingegebene Zahl lag außerhalb des erlaubten Wertebereiches und wurde zur\u00FCckgesetzt.");
			FXUtils.showNumberOverflowWarning(noe);
		} catch (Exception e) {
			num.reset();
		}
		
		if (isShowingResult && lastOperation.isLogic()) {
			textField.setText(num.logicStringProperty().get());
		}
		else {
			textField.setText(num.stringProperty().get());
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void consumeKeyEvents() {
		addAccelerator(equalsBtn, KeyEvent.KEY_TYPED, new KeyCodeCombination(KeyCode.ENTER));
		addAccelerator(clearBtn, KeyEvent.KEY_TYPED, new KeyCodeCombination(KeyCode.F12));
		addAccelerator(clearBtn, KeyEvent.KEY_TYPED, new KeyCodeCombination(KeyCode.ESCAPE));
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	nested Classes   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	static protected enum Operation {
		// TODO JavaDoc
		ADD("+"),
		SUBSTRACT("-"),
		DIVIDE("/"),
		MULTIPLY("*"),
		MODULO("%"),
		AND("&"),
		OR("|"),
		NOT("~"),
		NAND("~&"),
		NOR("~|"),
		XOR("^"),
		SHIFT_LEFT("<<"),
		SHIFT_RIGHT(">>"),
		UNDEFINED(" ");
		
		private String symbol;
		
		private Operation(String symbol) {
			this.symbol = symbol;
		}
		
		public boolean isLogic() {
			return this.equals(AND) || this.equals(OR) || this.equals(NOT) || this.equals(NAND) || this.equals(NOR) || this.equals(XOR) || this.equals(SHIFT_LEFT) || this.equals(SHIFT_RIGHT);
		}

		public String getSymbol() {
			return symbol;
		}
		
		public boolean isUndefined() {
			return this.equals(UNDEFINED);
		}
		
	}
	
}







