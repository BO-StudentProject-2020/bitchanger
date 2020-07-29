/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
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
import bitchanger.gui.views.ConverterView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

/**	<!-- $LANGUAGE=DE -->
 * Controller, der die Funktion für eine {@linkplain ConverterView} bereitstellt.
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ConverterController extends ControllerBase<ConverterView> {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->	Zahl, die in die verschiedenen Zahlensysteme umgewandelt wird */
	private ChangeableNumber value;
	
	/**	<!-- $LANGUAGE=DE -->	Spinner für die auswählbare, beliebige Basis */
	private BaseSpinner anyBase;
	
	/**	<!-- $LANGUAGE=DE -->	Property zum Einstellen der Basis des aktuell fokussierten Textfelds */
	private IntegerProperty baseProperty;

	// TextFields	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->	Textfeld für die hexadezimale Darstellung */
	private ValueField tfHex;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld für die dezimale Darstellung */
	private ValueField tfDec;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld für die binäre Darstellung */
	private ValueField tfBin;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld für die oktale Darstellung */
	private ValueField tfOct;
	
	/**	<!-- $LANGUAGE=DE -->	Textfeld für die Darstellung zu einer wählbaren Basis */
	private ValueField tfAny;
	
	/**	<!-- $LANGUAGE=DE -->	Hilfsvariable mit Referenz auf das aktuell oder zuletzt fokussierte Textfeld */
	private ValueField focusedTF;

	// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Button zum Löschen und Zurücksetzen von {@link #value}
	 * 
	 * @see ChangeableNumber#reset()
	 */
	private Button clearBtn;
	
	/**	<!-- $LANGUAGE=DE -->	Button, der die Backspace-Taste auf der Tastatur simuliert */
	private Button backspcBtn;
	
	/**	<!-- $LANGUAGE=DE -->	alphanumerische Buttons zur Simulation einer Tastatur */
	private Button[] alphaNumButtons;
	
	/**	<!-- $LANGUAGE=DE -->	Button, mit dem das Vorzeichen der Zahl gewechselt werden kann */
	private Button signBtn;

	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Konstruiert einen neuen Controller für eine ConverterView und verknüpft die benötigten Attribute mit
	 * Referenzen auf die Bedienelemente aus der ConverterView.
	 * 
	 * @param view ConverterView, an die dieser Controller gebunden wird
	 * 
	 * @see #initControls()
	 */
	public ConverterController(ConverterView view) {
		super(view);
		this.value = new SimpleChangeableNumber();
		this.baseProperty = new SimpleIntegerProperty();
	}
	
	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		if(nodeMap.get(ConverterView.BASE_SPINNER_KEY) instanceof BaseSpinner) {
			this.anyBase = (BaseSpinner) nodeMap.get(ConverterView.BASE_SPINNER_KEY);
		}
		
		initTextFields();
		initButtons();
	}

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
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

	
	// Methods	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Sucht die benötigten Referenzen zu den Buttons aus der buttonMap und speichert diese in den Attributen
	 */
	private void initButtons() {
		this.clearBtn = this.buttonMap.get("clearBtn");
		this.backspcBtn = this.buttonMap.get("backspaceBtn");
		this.signBtn = this.buttonMap.get("signBtn");

		alphaNumButtons = new Button[16];
		for (int i = 0; i < 6; i++) {
			alphaNumButtons[i] = this.buttonMap.get("alpha_" + i);
		}

		for (int i = 0; i < 10; i++) {
			alphaNumButtons[i + 6] = this.buttonMap.get("num_" + i);
		}
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Sucht die benötigten Referenzen zu den Textfeldern aus der textFieldMap, speichert diese in den Attributen und setzt die Basis der einzelnen Textfelder.
	 */
	private void initTextFields() {
		tfHex = (ValueField) this.textFieldMap.get("hexTF");
		tfDec = (ValueField) this.textFieldMap.get("decTF");
		tfOct = (ValueField) this.textFieldMap.get("octTF");
		tfBin = (ValueField) this.textFieldMap.get("binTF");
		tfAny = (ValueField) this.textFieldMap.get("anyTF");
		
		tfHex.setBase(16);
		tfDec.setBase(10);
		tfOct.setBase(8);
		tfBin.setBase(2);
		tfAny.getBaseProperty().bind(anyBase.valueProperty());
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Prüft, ob die übergebene Node einer der Pfeil-Buttons zum Scrollen in einem Spinner ist.
	 * Dies ist der Fall, wenn die StyleClass den String "arrow-button" enthält
	 * 
	 * @param n	Testkandidat für einen Pfeil-Button
	 * @return	{@code true}, wenn die StyleClass von n den String "arrow-button" enthält, {@code false} andernfalls
	 */
	private boolean isArrowButton(Node n) {
		for(String s: n.getStyleClass()) {
			if(s.contains("arrow-button")) {
				return true;
			}
		}
		
		return false;
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt die Attribute auf den Ausgangszustand.
	 */
	private void setInitialState() {
		tfDec.requestFocus();
		focusedTF = tfDec;
		baseProperty.set(10);
	}
	
	// Actions	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
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
	private void setTextFieldActions() {
		setHexValListener();
		setDecValListener();
		setOctValListener();
		setBinValListener();
		setAnyValListener();

		setTFSelection();
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert die Texte der gewählten Textfelder mit dem aktuellen Wert von {@link #value} in der
	 * zum Textfeld gehörenden Darstellung.
	 * 
	 * @param setHex	true, wenn der Text von {@link #tfHex} gesetzt werden soll
	 * @param setDec	true, wenn der Text von {@link #tfDec} gesetzt werden soll
	 * @param setOct	true, wenn der Text von {@link #tfOct} gesetzt werden soll
	 * @param setBin	true, wenn der Text von {@link #tfBin} gesetzt werden soll
	 * @param setAny	true, wenn der Text von {@link #tfAny} gesetzt werden soll
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
	
	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfHex}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	private void setHexValListener() {
		tfHex.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfHex.isFocused()) {
					try {
						value.setHex(newValue);
					} catch (Exception e) {
						value.reset();
					}
					setTexts(false, true, true, true, true);
				}
			}
		});
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfDec}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
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
					}
					setTexts(true, false, true, true, true);
				}
			}
		});
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfOct}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	private void setOctValListener() {
		tfOct.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfOct.isFocused()) {
					try {
						value.setOct(newValue);
					} catch (Exception e) {
						value.reset();
					}
					setTexts(true, true, false, true, true);
				}
			}
		});
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfBin}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
	 * 
	 * @see #setTexts(boolean, boolean, boolean, boolean, boolean)
	 */
	private void setBinValListener() {
		tfBin.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (tfBin.isFocused()) {
					try {
						value.setBin(newValue);
					} catch (Exception e) {
						value.reset();
					}
					setTexts(true, true, true, false, true);
				}
			}
		});
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Listener für {@link #tfAny}, um die Eingabe direkt umzuwandeln und die anderen Textfelder zu aktualisieren.
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
						value.reset();
					}
					setTexts(true, true, true, true, false);
				}
			}
		});
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert das Attribut {@link #focusedTF} bei Auswahl eines Textfeldes durch einen Mausklick und 
	 * verbindet {@link #baseProperty} mit der baseProperty des Textfelds.
	 */
	private void setTFSelection() {
		ValueField[] textfields = { tfHex, tfDec, tfOct, tfBin, tfAny };

		for (ValueField tf : textfields) {
			tf.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					focusedTF = tf;
					baseProperty.bind(tf.getBaseProperty());
				}
			});
		}

	}

	
	// Spinner	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**	<!-- $LANGUAGE=DE -->
	 * Aktualisiert die Basis von {@link #tfAny}, wenn sich die valueProperty von {@link #anyBase} ändert.
	 * Sorgt außerdem dafür, dass anyBase den Focus nach der Eingabe einer Basis im Editor oder mit den 
	 * Inkrement- und Dekrement-Buttons wieder an {@link #focusedTF} abgibt.
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
			anyBase.getChildren().stream().filter(this::isArrowButton).forEach(node -> {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, this::focusTF);
			});
		});
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Fokussiert das Textfeld {@link #focusedTF}.
	 * Diese Methode wird als Referenz für einen EventHandler verwendet.
	 * 
	 * @param e	Event, das den EventHanler auslöst
	 */
	private void focusTF(Event e) {
		focusedTF.requestFocus();
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
	private void setButtonActions() {
		setAlphaNumBindings();
		setClearAction();
		setBackspaceAction();
		setSignAction();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Bindet die baseProperty aller alphanumerischen Buttons an das Attribut {@link #baseProperty}, um die Buttons
	 * bei einem Wechsel der Basis automatisch aus- oder einzublenden.
	 */
	private void setAlphaNumBindings() {
		for (Button b : alphaNumButtons) {
			((ValueButton)b).getBaseProperty().bind(baseProperty);
		}
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setz {@link #value} bei Klick auf den Clear-Button zurück und aktualisiert alle Textfelder.
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
	
	/**	<!-- $LANGUAGE=DE -->
	 * Lässt den Backspace-Button die Backspace-Taste auf der Tastatur simulieren.
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
	
	/**	<!-- $LANGUAGE=DE -->
	 * Kehrt das Vorzeichen von {@link #value} beim Klick auf den Vorzeichen-Button um und aktualisiert alle Textfelder.
	 */
	private void setSignAction() {
		signBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int caretPos = focusedTF.getCaretPosition();
				
// TODO Anpassung an Zweierkomplement im Binärsystem. IDEE: Nur dezimales Feld prüfen und setzen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
				
				if (focusedTF.getText().startsWith("-")) {
					focusedTF.setText(focusedTF.getText().substring(1));
					caretPos--;
				} 
				else if (focusedTF.getText().startsWith("+")) {
					focusedTF.setText("-" + focusedTF.getText().substring(1));
				} 
				else {
					focusedTF.setText("-" + focusedTF.getText());
					caretPos++;
				}

				focusedTF.positionCaret(caretPos);
			}
		});
	}

}







