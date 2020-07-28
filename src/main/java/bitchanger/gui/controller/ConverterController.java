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
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ConverterController extends ControllerBase<ConverterView> {

	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private ChangeableNumber value;
	private BaseSpinner anyBase;
	private IntegerProperty baseProperty;

	// TextFields	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private ValueField tfHex;
	private ValueField tfDec;
	private ValueField tfBin;
	private ValueField tfOct;
	private ValueField tfAny;
	private ValueField focusedTF;

	// Buttons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private Button clearBtn;
	private Button backspcBtn;
	private Button[] alphaNumButtons;
	private Button signBtn;

	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ConverterController(ConverterView view) {
		super(view);
		this.value = new SimpleChangeableNumber();
		this.baseProperty = new SimpleIntegerProperty();
	}
	
	@Override
	protected void initControls() {
		if(nodeMap.get(ConverterView.BASE_SPINNER_KEY) instanceof BaseSpinner) {
			this.anyBase = (BaseSpinner) nodeMap.get(ConverterView.BASE_SPINNER_KEY);
		}
		
		initTextFields();
		initButtons();
	}

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
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

	
	// Actions	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	// TextFields	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void setTextFieldActions() {
		setHexVal();
		setDecVal();
		setOctVal();
		setBinVal();
		setAnyVal();

		setTFSelection();
	}
	
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
	
	private void setHexVal() {
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

	private void setDecVal() {
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

	private void setOctVal() {
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

	private void setBinVal() {
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

	private void setAnyVal() {
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
			for(Node n: anyBase.getChildren()) {
				if(isArrowButton(n)) {
					n.addEventHandler(MouseEvent.MOUSE_CLICKED, this::focusTF);
				}
			}
		});
	}
	
	private void focusTF(Event e) {
		focusedTF.requestFocus();
	}

	
	// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void setButtonActions() {
		setAlphaNumBindings();
		setClearAction();
		setBackspaceAction();
		setSignAction();
	}

	private void setInitialState() {
		tfDec.requestFocus();
		focusedTF = tfDec;
		baseProperty.set(10);
	}

	private void setSignAction() {
		signBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int caretPos = focusedTF.getCaretPosition();

				if (focusedTF.getText().startsWith("-")) {
					focusedTF.setText(focusedTF.getText().substring(1));
					caretPos--;
				} else if (focusedTF.getText().startsWith("+")) {
					focusedTF.setText("-" + focusedTF.getText().substring(1));
				} else {
					focusedTF.setText("-" + focusedTF.getText());
					caretPos++;
				}

				focusedTF.positionCaret(caretPos);
			}
		});
	}

	private void setBackspaceAction() {
		backspcBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Simulate Backspace-Key
				simulateKeyEvents(null, null, view.getScene(), "", "", KeyCode.BACK_SPACE, false, false, false, false);
			}
		});
	}
	
	private void setClearAction() {
		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				value.reset();
				setTexts(true, true, true, true, true);
			}
		});
	}

	private void setAlphaNumBindings() {
		for (Button b : alphaNumButtons) {
			((ValueButton)b).getBaseProperty().bind(baseProperty);
		}
	}

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

	private boolean isArrowButton(Node n) {
		for(String s: n.getStyleClass()) {
			if(s.contains("arrow-button")) {
				return true;
			}
		}
		
		return false;
	}

}







