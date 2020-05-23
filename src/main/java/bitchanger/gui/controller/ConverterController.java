package bitchanger.gui.controller;

import java.util.List;
import bitchanger.components.ChangeableNumbers;
import bitchanger.components.Numbers;
import bitchanger.gui.scenes.Controllable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ConverterController extends ControllerBase {
	
	// Attribute
	private ChangeableNumbers value;
	private Spinner<Integer> anyBase;
	
	// TextFields
	private TextField tfHex;
	private TextField tfDec;
	private TextField tfBin;
	private TextField tfOct;
	private TextField tfAny;
	private TextField focusedTF;
	
	// Buttons
	private Button clearBtn;
	private Button backspcBtn;
	private Button[] alphaNumButtons;
	private Button signBtn;
	private Button commaBtn;
	

	public ConverterController(Controllable view) {
		super(view);
		this.value = new Numbers();
	}
	
	@Override
	public void setControlls() {
		initTextFields();
		initButtons();
		
		setTextFieldActions();
		setButtonActions();
	}

	private void setButtonActions() {
		setButtonSelection(allButtons);
		setAlphaNumActions();
	}

	private void setAlphaNumActions() {
		for(Button b: alphaNumButtons) {
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (focusedTF.getText() == null) {
						focusedTF.setText(b.getText());
					}
					else {
						focusedTF.setText(focusedTF.getText() + b.getText());
					}
					focusedTF.positionCaret(focusedTF.getLength());
				}
			});
		}
	}

	private void setButtonSelection(List<Node> btnList) {
		for(Node n: btnList) {
			if (n instanceof Pane) {
				setButtonSelection(((Pane) n).getChildren());
			}
			else {
				setBtnFocusedProperty(n);
			}
		}
	}
	
	private void setBtnFocusedProperty(Node button) {
		button.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean focused) {
				if(focused) {
					focusedTF.requestFocus();
				}
			}
		});
	}

	private void initButtons() {
		this.clearBtn = this.buttonMap.get("clearBtn");
		this.backspcBtn = this.buttonMap.get("backspaceBtn");
		this.signBtn = this.buttonMap.get("signBtn");
		this.commaBtn = this.buttonMap.get("commaBtn");
		
		alphaNumButtons = new Button[16];
		for(int i = 0; i < 6; i++) {
			alphaNumButtons[i] = this.buttonMap.get("alpha_" + i);
		}
		
		for(int i = 0; i < 10; i++) {
			alphaNumButtons[i + 6] = this.buttonMap.get("num_" + i);
		}
	}

	private void initTextFields() {
		tfHex = this.textFieldMap.get("hexTF");
		tfDec = this.textFieldMap.get("decTF");
		tfOct = this.textFieldMap.get("octTF");
		tfBin = this.textFieldMap.get("binTF");
		tfAny = this.textFieldMap.get("anyTF");
		
		tfDec.requestFocus();
		focusedTF = tfDec;
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
		TextField[] textfields = {tfHex, tfDec, tfOct, tfBin, tfAny};
		
		for(TextField tf: textfields) {
			tf.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					focusedTF = tf;
				}
			});
		}
		
	}

	private void setHexVal() {
		tfHex.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(tfHex.isFocused()) {
					try {
						value.setHex(newValue);
					} catch (Exception e) {
						e.printStackTrace();
						value.reset();
					}
					setTexts(false,true,true,true,true);
				}
			}
		});
	}
	
	private void setDecVal() {
		tfDec.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(tfDec.isFocused()) {
					try {
						value.setDec(newValue);
					} catch (Exception e) {
						e.printStackTrace();
						value.reset();
					}
					setTexts(true,false,true,true,true);
				}
			}
		});
	}
	
	private void setOctVal() {
		tfOct.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(tfOct.isFocused()) {
					try {
						value.setOct(newValue);
					} catch (Exception e) {
						e.printStackTrace();
						value.reset();
					}
					setTexts(true,true,false,true,true);
				}
			}
		});
	}
	
	private void setBinVal() {
		tfBin.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(tfBin.isFocused()) {
					try {
						value.setBin(newValue);
					} catch (Exception e) {
						e.printStackTrace();
						value.reset();
					}
					setTexts(true,true,true,false,true);
				}
			}
		});
	}
	
	private void setAnyVal() {
		tfAny.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(tfAny.isFocused()) {
					try {
						value.setValue(newValue, anyBase.getValue());
					} catch (Exception e) {
						e.printStackTrace();
						value.reset();
					}
					setTexts(true,true,true,true,false);
				}
			}
		});
	}

	private void setTexts(boolean setHex, boolean setDec, boolean setOct, boolean setBin, boolean setAny) {
		if(setBin)
			tfBin.setText(value.toBinString());
		if(setOct)
			tfOct.setText(value.toOctString());
		if(setDec)
			tfDec.setText(value.toDecString());
		if(setHex)
			tfHex.setText(value.toHexString());
		if(setAny && anyBase.getValue() != null) {
			tfAny.setText(value.toBaseString(anyBase.getValue()));
		}
	}
	
	
	
	
}

















