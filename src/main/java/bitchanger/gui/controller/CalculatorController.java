/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.ConvertingNumbers;
import bitchanger.calculations.SimpleChangeableNumber;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.ValueButton;
import bitchanger.gui.controls.ValueField;
import bitchanger.gui.views.CalculatorView;
import bitchanger.preferences.Preferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
public class CalculatorController extends ControllerBase<CalculatorView> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE -->	Ergebnis der letzten Berechnung */
	/*	<!-- $LANGUAGE=EN -->	Last result */
	private final ChangeableNumber value1;
	
	/**	<!-- $LANGUAGE=DE -->	Ergebnis der letzten Berechnung */
	/*	<!-- $LANGUAGE=EN -->	Last result */
	private final ChangeableNumber value2;
	
	/**	<!-- $LANGUAGE=DE -->	Ergebnis der letzten Berechnung */
	/*	<!-- $LANGUAGE=EN -->	Last result */
	private final ChangeableNumber result;
	
	/** <!-- $LANGUAGE=DE -->	Rechenoperation, die ausgeführt werden soll */
	// TODO: JavaDoc EN
	private Operation operation;
	
	/**	<!-- $LANGUAGE=DE -->	Spinner für die auswählbare, beliebige Basis */
	/*	<!-- $LANGUAGE=EN -->	Spinner for the eligible base */
	private BaseSpinner anyBase;
	
	/**	<!-- $LANGUAGE=DE -->	Property zum Einstellen der Basis des aktuell fokussierten Textfelds */
	/*	<!-- $LANGUAGE=EN -->	Property to adjust the base of the currently focused text field */
	private final IntegerProperty baseProperty;

	/**	<!-- $LANGUAGE=DE -->	Textfeld für die Eingabe */
	/*	<!-- $LANGUAGE=EN -->	Textfield for input */
	private ValueField textField;
	
	/** <!-- $LANGUAGE=DE -->	 Label für das Ergebnis */
	// TODO JavaDoc EN
	private Label resultLabel;
	
	/** <!-- $LANGUAGE=DE -->	Label für die Basis des Ergebnisses */
	// TODO JavaDoc EN
	private Label baseLabel;
	
	/** <!-- $LANGUAGE=DE -->	 ComboBox für die Anzahl der Bits */
	// TODO JavaDoc EN
	private ComboBox<String> bitLength;
	
	
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
	
	/**	<!-- $LANGUAGE=DE -->	Button für das Hexadezimalsystem */
	// TODO JavaDoc EN
	private Button hexBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das Dezimalsystem */
	// TODO JavaDoc EN
	private Button decBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das Oktalsystem */
	// TODO JavaDoc EN
	private Button octBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button für das Binärsystem */
	// TODO JavaDoc EN
	private Button binBtn;
	
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
	
	/**	<!-- $LANGUAGE=DE -->	Button für die Modulo-Operation */
	// TODO JavaDoc EN
	private Button moduloBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button zum Berechnen */
	// TODO JavaDoc EN
	private Button equalsBtn;
	
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
	public CalculatorController(CalculatorView view) {
		super(view);
		this.value1 = new SimpleChangeableNumber();
		this.value2 = new SimpleChangeableNumber();
		this.result = new SimpleChangeableNumber();
		this.operation = Operation.UNDEFINED;
		this.baseProperty = new SimpleIntegerProperty();
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
		if(nodeMap.get(controllable.baseSpinnerKey()) instanceof BaseSpinner) {
			this.anyBase = (BaseSpinner) nodeMap.get(controllable.baseSpinnerKey());
		}
		
		if(nodeMap.get(controllable.bitLengthKey()) instanceof ComboBox) {
			bitLength = (ComboBox) nodeMap.get(controllable.bitLengthKey());
		}
		
		initTextFieldsAndLabels();
		initButtons();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	{@inheritDoc} */
	@Override
	public void setActions() {
		setTextFieldActions();
		setSpinnerActions();
		setButtonActions();
		setInitialState();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Sucht die benötigten Referenzen zu den Buttons aus der buttonMap und speichert diese in den Attributen
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Searches the necessary references to the buttons of the buttonMap and stores these in the attributes
	 */
	private void initButtons() {
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
		
		hexBtn = this.buttonMap.get(controllable.hexBtnKey());
		decBtn = this.buttonMap.get(controllable.decBtnKey());
		octBtn = this.buttonMap.get(controllable.octBtnKey());
		binBtn = this.buttonMap.get(controllable.binBtnKey());
		
		divideBtn = this.buttonMap.get(controllable.divideBtnKey());
		multiplyBtn = this.buttonMap.get(controllable.multiplyBtnKey());
		subBtn = this.buttonMap.get(controllable.subtractBtnKey());
		addBtn = this.buttonMap.get(controllable.addBtnKey());
		moduloBtn = this.buttonMap.get(controllable.moduloBtnKey());
		equalsBtn = this.buttonMap.get(controllable.equalsBtnKey());
		
		andBtn = this.buttonMap.get(controllable.andBtnKey());
		orBtn = this.buttonMap.get(controllable.orBtnKey());
		notBtn = this.buttonMap.get(controllable.notBtnKey());
		nandBtn = this.buttonMap.get(controllable.nandBtnKey());
		norBtn = this.buttonMap.get(controllable.norBtnKey());
		xorBtn = this.buttonMap.get(controllable.xorBtnKey());
		shiftLeftBtn = this.buttonMap.get(controllable.shiftLeftBtnKey());
		shiftRightBtn = this.buttonMap.get(controllable.shiftRightBtnKey());
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Sucht die benötigten Referenzen zu den Textfeldern aus der textFieldMap, speichert diese in den Attributen und setzt die Basis der einzelnen Textfelder.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Searches the necessary references to the text fields of the buttonMap, stores these in the attributes and sets the base of each text field.
	 */
	private void initTextFieldsAndLabels() {
		textField = (ValueField) this.textFieldMap.get(controllable.tfKey());
		textField.getBaseProperty().bind(anyBase.valueProperty());
		
		resultLabel = (Label) this.nodeMap.get(controllable.resultLabelKey());
		baseLabel = (Label) this.nodeMap.get(controllable.baseLabelKey());
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Prüft, ob die übergebene Node einer der Pfeil-Buttons zum Scrollen in einem Spinner ist.
	 * Dies ist der Fall, wenn die StyleClass den String "arrow-button" enthält.
	 * 
	 * @param n	Testkandidat für einen Pfeil-Button
	 * @return	{@code true}, wenn die StyleClass von n den String "arrow-button" enthält, {@code false} andernfalls
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
		baseProperty.set(10);
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Actions			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
// TextFields	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	
	// TODO JavaDoc
	private void setTextFieldActions() {
		
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	


	
// Spinner	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert die Basis, wenn sich die valueProperty von {@link #anyBase} ändert.
	 * Sorgt außerdem dafür, dass anyBase den Fokus nach der Eingabe einer Basis im Editor oder mit den 
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
					// TODO anpassen!
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
	 * Fokussiert das Textfeld für die Eingabe.
	 * Diese Methode wird als Referenz für einen EventHandler verwendet.
	 * 
	 * @param e	Event, das den EventHanler auslöst
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
		
		setArithmeticActions();
		setEqualsAction();
		consumeKeyEvents();
		
		updateBitoperationSymbols();
		showBitoperationsListener();
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
				value1.set(0);
				value2.set(0);
				result.reset();
				resultLabel.setText("");
				textField.clear();
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setArithmeticActions() {
		setOperationAction(addBtn, Operation.ADD);
		setOperationAction(subBtn, Operation.SUBSTRACT);
		setOperationAction(multiplyBtn, Operation.MULTIPLY);
		setOperationAction(divideBtn, Operation.DIVIDE);
		setOperationAction(moduloBtn, Operation.MODULO);
		setOperationAction(andBtn, Operation.AND);
		setOperationAction(orBtn, Operation.OR);
		setOperationAction(notBtn, Operation.NOT);
		setOperationAction(nandBtn, Operation.NAND);
		setOperationAction(norBtn, Operation.NOR);
		setOperationAction(xorBtn, Operation.XOR);
		setOperationAction(shiftLeftBtn, Operation.SHIFT_LEFT);
		setOperationAction(shiftRightBtn, Operation.SHIFT_RIGHT);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public void setOperationAction(Button b, Operation o) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (operation.isUndefined()) {
					parseValue(value1);
					textField.clear();
					resultLabel.setText(value1.toBaseString(baseProperty.get()) + " " + o.getSymbol() + " ");
					operation = o;
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void parseValue(ChangeableNumber value) {
		try {
			value.setValue(textField.getText(), baseProperty.get());
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			value.set(0);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void setEqualsAction() {
		equalsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(operation.isUndefined()) {
					return;
				}
				
				parseValue(value2);
				
				if(operation.isLogic()) {	// logische Verkn�pfung nur mit ganzen Zahlen m�glich
					value1.set((long)value1.asDouble());
					value2.set((long)value2.asDouble());
				}
				
				calculate();
				
				StringBuffer resultText = new StringBuffer();
				resultText.append(value1.toBaseString(baseProperty.get()))
					.append(" ")
					.append(operation.getSymbol())
					.append(" ")
					.append(value2.toBaseString(baseProperty.get()))
					.append(" =");
				
				resultLabel.setText(resultText.toString());
				textField.setText(result.toBaseString(baseProperty.get()));
				textField.positionCaret(textField.getLength());
				
				operation = Operation.UNDEFINED;
			}

			
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	private void calculate() {
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
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	private void consumeKeyEvents() {
		controllable.getScene().addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case DIVIDE:
				case SLASH:
					divideBtn.fire();
					break;
				case MULTIPLY:
				case STAR:
					multiplyBtn.fire();
					break;
				case ADD:
				case PLUS:
					addBtn.fire();
					break;
				case SUBTRACT:
				case MINUS:
					subBtn.fire();
					break;
				case ENTER:
					equalsBtn.fire();
					break;
				default:
					System.out.println(event.getCode());
				}
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
	private void showBitoperationsListener() {
		for(Node n : controllable.getLogicNodes()) {
			n.visibleProperty().bind(Preferences.getPrefs().showBitOperationsProperty());
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateBitoperationSymbols() {
		Preferences.getPrefs().showBitOperationSymbolsProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean showSymbols) {
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
		});
		
	}
	
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	nested Classes   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	private enum Operation {
		// TODO JavaDoc
		ADD("+", OperationType.ARITHMETIC),
		SUBSTRACT("-", OperationType.ARITHMETIC),
		DIVIDE("/", OperationType.ARITHMETIC),
		MULTIPLY("*", OperationType.ARITHMETIC),
		MODULO("%", OperationType.ARITHMETIC),
		AND("&", OperationType.LOGIC),
		OR("|", OperationType.LOGIC),
		NOT("~", OperationType.LOGIC),
		NAND("~&", OperationType.LOGIC),
		NOR("~|", OperationType.LOGIC),
		XOR("^", OperationType.LOGIC),
		SHIFT_LEFT("<<", OperationType.LOGIC),
		SHIFT_RIGHT(">>", OperationType.LOGIC),
		UNDEFINED(" ", OperationType.OTHER);
		
		private String symbol;
		private OperationType type;
		
		private Operation(String symbol, OperationType type) {
			this.symbol = symbol;
			this.type = type;
		}
		
		public String getSymbol() {
			return symbol;
		}
		
		public boolean isUndefined() {
			return this.equals(UNDEFINED);
		}
		
		public boolean isLogic() {
			return this.type.equals(OperationType.LOGIC);
		}
	}
	
	
	private enum OperationType {
		// TODO JavaDoc
		ARITHMETIC,
		LOGIC,
		OTHER;
	}
	
	
}







