/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.net.MalformedURLException;
import java.util.function.Consumer;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.ConvertingNumbers;
import bitchanger.calculations.NumberOverflowException;
import bitchanger.calculations.SimpleChangeableNumber;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.ValueButton;
import bitchanger.gui.controls.ValueField;
import bitchanger.gui.views.CalcPathView;
import bitchanger.gui.views.ConverterView;
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Preferences;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import bitchanger.util.Resources;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**	<!-- $LANGUAGE=DE -->
 * Controller, der die Funktion f\u00FCr eine {@linkplain ConverterView} bereitstellt.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * Controller that provides the function for a {@linkplain ConverterView}.
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 *
 */
public class ConverterController extends ControllerBase<ConverterView> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE -->	Zahl, die in die verschiedenen Zahlensysteme umgewandelt wird */
	/*	<!-- $LANGUAGE=EN -->	Number which gets converted into any numeral system */
	private ChangeableNumber value;
	
	/**	<!-- $LANGUAGE=DE -->	Spinner f\u00FCr die ausw\u00E4hlbare, beliebige Basis */
	/*	<!-- $LANGUAGE=EN -->	Spinner for the eligible base */
	private BaseSpinner anyBase;
	
	/**	<!-- $LANGUAGE=DE -->	Property zum Einstellen der Basis des aktuell fokussierten Textfelds */
	/*	<!-- $LANGUAGE=EN -->	Property to adjust the base of the currently focused text field */
	private IntegerProperty baseProperty;

	
// TextFields	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die hexadezimale Darstellung */
	/*	<!-- $LANGUAGE=EN -->	Text field for the hexadecimal representation */
	private ValueField tfHex;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die dezimale Darstellung */
	/*	<!-- $LANGUAGE=EN -->	Text field for the decimal representation */
	private ValueField tfDec;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die bin\u00E4re Darstellung */
	/*	<!-- $LANGUAGE=EN -->	Text field for the binary representation */
	private ValueField tfBin;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die oktale Darstellung */
	/*	<!-- $LANGUAGE=EN -->	Text field for the octal representation */
	private ValueField tfOct;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld f\u00FCr die Darstellung zu einer w\u00E4hlbaren Basis */
	/*	<!-- $LANGUAGE=EN -->	Text field for the representation of a number of any base*/
	private ValueField tfAny;
	
	/**	<!-- $LANGUAGE=DE -->	Hilfsvariable mit Referenz auf das aktuell oder zuletzt fokussierte Textfeld */
	/*	<!-- $LANGUAGE=EN -->	Auxiliary variable with reference to the current or last focused text field */
	private ValueField focusedTF;

	
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
	
	/**	<!-- $LANGUAGE=DE -->	Button, mit dem ein neues Fenster ge\u00F6ffnet wird, das den Rechenweg anzeigt */
	/*	<!-- $LANGUAGE=EN -->	Button that action is to show a new Window for explaining the calculation path */
	private Label calcPathHelp;

	
	
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
	public ConverterController(ConverterView view) {
		super(view);
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
		if(nodeMap.get(controllable.baseSpinnerKey()) instanceof BaseSpinner) {
			this.anyBase = (BaseSpinner) nodeMap.get(controllable.baseSpinnerKey());
		}
		
		initTextFields();
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
		updateIndicateFractionalPrecision();
		
		addAccelerator(clearBtn, KeyEvent.KEY_TYPED, new KeyCodeCombination(KeyCode.F12));
		addAccelerator(clearBtn, KeyEvent.KEY_PRESSED, new KeyCodeCombination(KeyCode.ESCAPE));
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
		this.signBtn = this.buttonMap.get(controllable.signBtnKey());

		alphaNumButtons = new Button[16];
		for (int i = 0; i < 6; i++) {
			alphaNumButtons[i] = this.buttonMap.get("alpha_" + i);
		}

		for (int i = 0; i < 10; i++) {
			alphaNumButtons[i + 6] = this.buttonMap.get("num_" + i);
		}
		
		try {
			calcPathHelp = (Label) this.nodeMap.get(controllable.calcPathHelpKey());
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL, e);
			e.printStackTrace();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Sucht die ben\u00F6tigten Referenzen zu den Textfeldern aus der textFieldMap, speichert diese in den Attributen und setzt die Basis der einzelnen Textfelder.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Searches the necessary references to the text fields of the buttonMap, stores these in the attributes and sets the base of each text field.
	 */
	private void initTextFields() {
		tfHex = (ValueField) this.textFieldMap.get(controllable.tfHexKey());
		tfDec = (ValueField) this.textFieldMap.get(controllable.tfDecKey());
		tfOct = (ValueField) this.textFieldMap.get(controllable.tfOctKey());
		tfBin = (ValueField) this.textFieldMap.get(controllable.tfBinKey());
		tfAny = (ValueField) this.textFieldMap.get(controllable.tfAnyKey());
		
		tfHex.setBase(16);
		tfDec.setBase(10);
		tfOct.setBase(8);
		tfBin.setBase(2);
		tfAny.getBaseProperty().bind(anyBase.valueProperty());
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
		setValueListener(tfHex, value::setHex, false, true, true, true, true);
		setValueListener(tfDec, value::setDec, true, false, true, true, true);
		setValueListener(tfOct, value::setOct, true, true, false, true, true);
		setValueListener(tfBin, value::setBin, true, true, true, false, true);
		setAnyValListener();

		setTFSelection();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert die Texte der gew\u00E4hlten Textfelder mit dem aktuellen Wert von {@link #value} in der
	 * zum Textfeld geh\u00F6renden Darstellung.
	 * 
	 * @param setHex	true, wenn der Text von {@link #tfHex} gesetzt werden soll
	 * @param setDec	true, wenn der Text von {@link #tfDec} gesetzt werden soll
	 * @param setOct	true, wenn der Text von {@link #tfOct} gesetzt werden soll
	 * @param setBin	true, wenn der Text von {@link #tfBin} gesetzt werden soll
	 * @param setAny	true, wenn der Text von {@link #tfAny} gesetzt werden soll
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Refreshes the texts of the chosen text fields to the new value of {@link #value}.
	 * This new value is always shown in the base which refers to the text field.
	 * 
	 * @param setHex	true, if the text of {@link #tfHex} should be set
	 * @param setDec	true, if the text of {@link #tfDec} should be set
	 * @param setOct	true, if the text of {@link #tfOct} should be set
	 * @param setBin	true, if the text of {@link #tfBin} should be set
	 * @param setAny	true, if the text of {@link #tfAny} should be set
	 */
	private void setTexts(boolean setHex, boolean setDec, boolean setOct, boolean setBin, boolean setAny) {
		if (setBin)
			tfBin.setText(value.toBinString());
		
		if (setOct)
			tfOct.setText(value.toOctString());
		
		if (setDec)
			tfDec.setText(value.toDecString());
		
		if (setHex)
			tfHex.setText(value.toHexString());
		
		if (setAny && anyBase.getValue() != null) {
			tfAny.setText(value.toBaseString(anyBase.getValue()));
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener f\u00FCr das Textfeld {@code #tf}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @param tf		Textfeld, das einen Listener f\u00FCr das {@code textProperty} erh\u00E4lt
	 * @param valueConsumer	Methode, die die Eingabe verarbeitet
	 * @param setHex	true, wenn der Text von {@link #tfHex} gesetzt werden soll
	 * @param setDec	true, wenn der Text von {@link #tfDec} gesetzt werden soll
	 * @param setOct	true, wenn der Text von {@link #tfOct} gesetzt werden soll
	 * @param setBin	true, wenn der Text von {@link #tfBin} gesetzt werden soll
	 * @param setAny	true, wenn der Text von {@link #tfAny} gesetzt werden soll
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the listener for {@link #tfHex}, to convert the input and update the other text fields immediately.
	 * 
	 * @param tf		text field to that a new Listener for the {@code textProperty} is added
	 * @param valueConsumer	method to consume the input
	 * @param setHex	true, if the text of {@link #tfHex} should be set
	 * @param setDec	true, if the text of {@link #tfDec} should be set
	 * @param setOct	true, if the text of {@link #tfOct} should be set
	 * @param setBin	true, if the text of {@link #tfBin} should be set
	 * @param setAny	true, if the text of {@link #tfAny} should be set
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	private void setValueListener(ValueField tf, Consumer<String> valueConsumer, boolean setHex, boolean setDec, boolean setOct, boolean setBin, boolean setAny) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tf.isFocused()) {
					try {
						valueConsumer.accept(newValue);
					} catch (NumberOverflowException noe) {
						BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, noe);
						value.reset();
						FXUtils.showNumberOverflowWarning(noe);
					} catch (Exception e) {
						BitchangerLauncher.printDebugErr(ErrorLevel.LOW, e);
						value.reset();
					}
					setTexts(setHex, setDec, setOct, setBin, setAny);
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener f\u00FCr {@link #tfAny}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Sets the listener for {@link #tfAny}, to convert the input and update the other text fields immediately.
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	private void setAnyValListener() {
		tfAny.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfAny.isFocused()) {
					try {
						value.setValue(newValue, anyBase.getValue());
					} catch (Exception e) {
						BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM);
						
						value.reset();
						e.printStackTrace();
					}
					setTexts(true, true, true, true, false);
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
		for (ValueField tf : ArrayUtils.arrayOf(tfHex, tfDec, tfOct, tfBin, tfAny)) {
			tf.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					focusedTF = tf;
					baseProperty.bind(tf.getBaseProperty());
					
					// Hilfelabel für Rechenweg neben neuem Textfeld positionieren
					GridPane.setRowIndex(calcPathHelp, GridPane.getRowIndex(focusedTF));
				}
			});
		}

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void updateIndicateFractionalPrecision() {
		Preferences.getPrefs().indicateFractionalPrecisionProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					value.setDec(value.toDecString());
				} catch (Exception e) {
					BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM);
					
					value.reset();
					e.printStackTrace();
				}
				
				setTexts(tfHex != focusedTF, tfDec != focusedTF, tfOct != focusedTF, tfBin != focusedTF, tfAny != focusedTF);
			}
		});
	}
	

	
// Spinner	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert die Basis von {@link #tfAny}, wenn sich die valueProperty von {@link #anyBase} \u00E4ndert.
	 * Sorgt au\u00DFerdem daf\u00FCr, dass anyBase den Fokus nach der Eingabe einer Basis im Editor oder mit den 
	 * Inkrement- und Dekrement-Buttons wieder an {@link #focusedTF} abgibt.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Updates the base of {@link #tfAny}, if the valueProperty of {@link #anyBase} changes.
	 * Furthermore makes sure that anyBase is focused on {@link #focusedTF} after an input of a base or using the increment and decrement buttons.
	 */
	private void setSpinnerActions() {
		anyBase.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newBase) {
				if (newBase >= ConvertingNumbers.MIN_BASE && newBase <= ConvertingNumbers.MAX_BASE) {
					setTexts(false, false, false, false, true);
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
	 * Fokussiert das Textfeld {@link #focusedTF}.
	 * Diese Methode wird als Referenz f\u00FCr einen EventHandler verwendet.
	 * 
	 * @param e	Event, das den EventHanler ausl\u00F6st
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Focuses the text field {@link #focusedTF}.
	 * This method is used as reference for an EventHandler.
	 * 
	 * @param e	Event that causes an EventHandler
	 */
	private void focusTF(Event e) {
		focusedTF.requestFocus();
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
		
		setCalcPathHelpAction();
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

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt {@link #value} bei Klick auf den Clear-Button zur\u00FCck und aktualisiert alle Textfelder.
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
				setTexts(true, true, true, true, true);
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
					ChangeableNumber num = new SimpleChangeableNumber();
					num.setBin(focusedTF.getText());
					num.set(- num.asDouble());
					focusedTF.setText(num.toBinString());
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

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since 0.1.8
	private void setCalcPathHelpAction() {
		calcPathHelp.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Neues Fenster mit CalcPathView anzeigen
				CalcPathView cpv = new CalcPathView(focusedTF.getText(), baseProperty.get(),
						baseProperty.get() == 10 ? 2 : 10);
				
				cpv.getScene().getStylesheets().add(Resources.LAYOUT_CSS);
				cpv.getScene().getStylesheets().add(Preferences.getPrefs().stylesheetProperty().get());
				
				Preferences.getPrefs().stylesheetProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldStylesheet, String newStylesheet) {
						try {
							cpv.getScene().getStylesheets().remove(oldStylesheet);
						} catch(Exception e) {
							/* ignore */
							BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
						}
						cpv.getScene().getStylesheets().add(newStylesheet);
					}
				});
				
				Stage stage = new Stage(StageStyle.DECORATED);
				stage.setTitle("Bitchanger - Rechenweg f\u00FCr Umwandlungen");
				try {
					stage.getIcons().add(new Image(Resources.BITCHANGER_LOGO_PNG.toURI().toURL().toExternalForm()));
				} catch (MalformedURLException e) { 
					/* ignore */
					BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
				}
				
				stage.setScene(cpv.getScene());
				stage.setHeight(700);
				stage.setWidth(1000);
				
				// Fenster verkleinern, falls größer als Bildschirm
				stage.setOnShown(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						if(stage.getHeight() > Screen.getPrimary().getVisualBounds().getHeight()) {
							stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
							stage.centerOnScreen();
						}
						
						if(stage.getWidth() > Screen.getPrimary().getVisualBounds().getWidth()) {
							stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
							stage.centerOnScreen();
						}
					}
				});
				
				stage.show();
				
				// Fenster automatisch schließen
				controllable.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						stage.close();
					}
				});
			}
		});
	}


}







