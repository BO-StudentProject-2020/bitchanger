package bitchanger.gui.controller;

import bitchanger.components.ChangeableNumber;
import bitchanger.components.ConvertingNumbers;
import bitchanger.components.SimpleChangeableNumber;
import bitchanger.gui.elements.BaseSpinner;
import bitchanger.gui.elements.ValueButton;
import bitchanger.gui.elements.ValueField;
import bitchanger.gui.views.Controllable;
import bitchanger.gui.views.ConverterView;
import bitchanger.preferences.Preferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ConverterController extends ControllerBase {

	// Attribute
	private ChangeableNumber value;
	private BaseSpinner<Integer> anyBase;
	private IntegerProperty baseProperty;

	// TextFields
	private ValueField tfHex;
	private ValueField tfDec;
	private ValueField tfBin;
	private ValueField tfOct;
	private ValueField tfAny;
	private ValueField focusedTF;

	// Buttons
	private Button clearBtn;
	private Button backspcBtn;
	private Button[] alphaNumButtons;
	private Button signBtn;
	private Button commaBtn;

	public ConverterController(Controllable view) {
		super(view);
		this.value = new SimpleChangeableNumber();
		this.baseProperty = new SimpleIntegerProperty();
		this.anyBase = (BaseSpinner<Integer>) ((ConverterView) view).getBaseSpinner();
	}

	@Override
	public void setControlls() {
		initTextFields();
		initButtons();

		setTextFieldActions();
		setSpinnerActions();
		setButtonActions();
		setInitialState();
	}

	private void setSpinnerActions() {
		anyBase.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newBase) {
				if (newBase >= ConvertingNumbers.MIN_BASE && newBase <= ConvertingNumbers.MAX_BASE) {
					setTexts(false, false, false, false, true);
				}
			}
		});
		

		anyBase.getEditor().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				focusedTF.requestFocus();
			}
		});
		
		// Focus nach Klick auf Button abgeben
		anyBase.skinProperty().addListener(e -> {
			for(Node n: anyBase.getChildren()) {
				if(isArrowButton(n)) {
					n.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							focusedTF.requestFocus();
						}
					});
				}
			}
		});
	}
	
	private boolean isArrowButton(Node n) {
		for(String s: n.getStyleClass()) {
			if(s.contains("arrow-button")) {
				return true;
			}
		}
		
		return false;
	}

	private void setInitialState() {
		tfDec.requestFocus();
		focusedTF = tfDec;
		baseProperty.set(10);
	}

	private void setButtonActions() {
//		setButtonSelection(allButtons);
		setAlphaNumActions();
		setClearAction();
		setBackspaceAction();
		setCommaAction();
		setSignAction();
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

	private void setCommaAction() {
		commaBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (focusedTF.getLength() <= 0) {
					focusedTF.setText("0");
					focusedTF.positionCaret(1);
				}
				
				int caretPos = focusedTF.getCaretPosition();
				StringBuffer sb = new StringBuffer(focusedTF.getText());
				sb.insert(caretPos, Preferences.getComma());
				focusedTF.setText(sb.toString());
				focusedTF.positionCaret(caretPos + 1);
			}
		});
	}

	private void setBackspaceAction() {
		backspcBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Simulate Backspace-Key
				KeyEvent pressed = new KeyEvent(backspcBtn, focusedTF, KeyEvent.KEY_PRESSED, "", "", KeyCode.BACK_SPACE, false, false, false, false);
				focusedTF.fireEvent(pressed);
				
				KeyEvent typed = new KeyEvent(backspcBtn, focusedTF, KeyEvent.KEY_TYPED, "", "", KeyCode.BACK_SPACE, false, false, false, false);
				focusedTF.fireEvent(typed);
				
				KeyEvent released = new KeyEvent(backspcBtn, focusedTF, KeyEvent.KEY_RELEASED, "", "", KeyCode.BACK_SPACE, false, false, false, false);
				focusedTF.fireEvent(released);
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

	private void setAlphaNumActions() {
		for (Button b : alphaNumButtons) {
			setAlphaNumOnAction(b);
			setAlphaNumBaseBinding(b);
		}
	}

	private void setAlphaNumBaseBinding(Button b) {
		((ValueButton)b).getBaseProperty().bind(baseProperty);
	}

	private void setAlphaNumOnAction(Button b) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (focusedTF.getText() == null) {
					focusedTF.setText(b.getText());
					focusedTF.positionCaret(focusedTF.getLength());
				} else {
					int caretPos = focusedTF.getCaretPosition();
					StringBuffer sb = new StringBuffer(focusedTF.getText());
					sb.insert(caretPos, b.getText());
					focusedTF.setText(sb.toString());
					focusedTF.positionCaret(caretPos + 1);
				}
			}
		});
	}

/*	private void setButtonSelection(List<Node> btnList) {
		for (Node n : btnList) {
			if (n instanceof Pane) {
				setButtonSelection(((Pane) n).getChildren());
			} else {
				setBtnFocusedProperty(n);
			}
		}
	}

	private void setBtnFocusedProperty(Node button) {
		// Alternativ: -> ExtendedButton -> setFocusTraversable(false);
		button.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean focused) {
				if (focused) {
					focusedTF.requestFocus();
				}
			}
		});
	}
*/
	private void initButtons() {
		this.clearBtn = this.buttonMap.get("clearBtn");
		this.backspcBtn = this.buttonMap.get("backspaceBtn");
		this.signBtn = this.buttonMap.get("signBtn");
		this.commaBtn = this.buttonMap.get("commaBtn");

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

	private void setTextFieldActions() {
		setHexVal();
		setDecVal();
		setOctVal();
		setBinVal();
		setAnyVal();

		setTFSelection();
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

}
