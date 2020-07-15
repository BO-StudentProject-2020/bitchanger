/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import bitchanger.gui.views.Controllable;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class AlphaNumGrid implements Controllable {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private ArrayList<Node> buttonList;
	private HashMap<String, Button> buttonMap;
	private final String SIGN_BTN = "signBtn";
	private final String NUM_0_BTN = "num_0";
	private final String COMMA_BTN = "commaBtn";
	private Button keyboardBtn;
	private Button previousBtn;
	private Button nextBtn;
	private Button commaBtn;
	private HBox arrowButtons;
	private double spacing;
	private boolean isShowingKeyboard;
	private String[] alphaKeys;
	private String[] numKeys;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/*
	 * Creates a keyboard layout as a new GridPane with included buttons to shift the six letters on the left
	 */
	public AlphaNumGrid(double spacing) {
		this.buttonList = new ArrayList<Node>();
		this.buttonMap = new HashMap<String, Button>();
		
		this.spacing = spacing;
		this.isShowingKeyboard = false;
		
		createButtons();
		setActions();
	}
	

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public HashMap<String, TextField> getTextFields() {
		return null;
	}
	
	@Override
	public HashMap<String, Button> getButtons() {
		return this.buttonMap;
	}
	
	private void setNextButton(Node button, int column, int row, String key) {
		setNextButton(button, column, row);
		if(button instanceof Button) {
			buttonMap.put(key, (Button) button);
		}
		
	}

	private void setNextButton(Node button, int column, int row) {
		GridPane.setConstraints(button, column, row);
		buttonList.add(button);
	}

	public ArrayList<Node> getButtonMatrix() {
		return buttonList;
	}
	
	public HashMap<String, Button> getButtonMap(){
		return buttonMap;
	}
	
	private void setAlphaButtons(char startLetter) {
		for(String alphaKey: alphaKeys) {
			if(startLetter < 'A' || startLetter > 'Z') {
				startLetter = ' ';
			}
			Button b = buttonMap.get(alphaKey);
			b.setText(String.valueOf(startLetter));
			startLetter++;
		}
	}
	
	private void setNumButtons() {
		for(String numKey: numKeys) {
			Button b = buttonMap.get(numKey);
			
			b.setText(String.valueOf(numKey.substring(numKey.indexOf('_') + 1)));
		}
	}
	
	private void setAllToKeyboard(char startLetter) {
		for(int i = 0; i < 15; i++) {
			if(startLetter < 'A' || startLetter > 'Z') {
				startLetter = ' ';
			}
			((Labeled) buttonList.get(i)).setText(String.valueOf(startLetter));
			
			startLetter++;
		}
	}
	
	
// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
// Buttons erstellen	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createButtons() {
		// Buttons fuer den Tastaturbereich erstellen
		createMainMatrix();
		
		// Buttons in der letzten Zeile erstellen
		createControllButtons();
		
		Button signBtn = new UnfocusedButton("\u00B1");
		Button num0 = new ValueButton("0");
		commaBtn = new UnfocusedButton(String.valueOf(Preferences.getComma()));
		
		Button[] lastRow = {signBtn, num0, commaBtn};
		String[] lastRowKeys = {SIGN_BTN, NUM_0_BTN, COMMA_BTN};
		for (int column = 0; column < lastRow.length; column++) {
			setNextButton(lastRow[column], column + 2, 3, lastRowKeys[column]);
		}
	}
	
	private void createControllButtons() {
		keyboardBtn = new UnfocusedButton("KEYB");
		setNextButton(keyboardBtn, 0, 3);		
		
		previousBtn = new UnfocusedButton("<");
		nextBtn = new UnfocusedButton(">");
		previousBtn.setDisable(true);
		
		arrowButtons = new HBox();
		arrowButtons.getChildren().addAll(previousBtn, nextBtn);
		setNextButton(arrowButtons, 1, 3);
		HBox.setHgrow(previousBtn, Priority.ALWAYS);
		HBox.setHgrow(nextBtn, Priority.ALWAYS);
		
		arrowButtons.setSpacing(this.spacing);
	}

	private void createMainMatrix() {
		// 6 Buttons fuer Buchstaben anlegen
		Queue<Button> alphaButtons = new LinkedList<Button>();
		alphaKeys = new String[6];
		for (int i = 0; i < 6; i++) {
			Button b = new ValueButton(String.valueOf((char)('A' + i)));
			alphaButtons.add(b);
			alphaKeys[i] = "alpha_" + i;
			buttonMap.put(alphaKeys[i], b);
		}
		
		// 9 Buttons fuer Zahlen anlegen
		Queue<Button> numButtons = new LinkedList<Button>();
		String[] numBtnText = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
		numKeys = new String[9];
		for (int i = 0; i < numBtnText.length; i++) {
			Button b = new ValueButton(numBtnText[i]);
			numButtons.add(b);
			numKeys[i] = "num_" + numBtnText[i];
			buttonMap.put(numKeys[i], b);
		}
		
		for(int row = 0; row <= 2; row++) {
			for(int column = 0; column <= 4; column++) {
				Button nextBtn = null;
				if(column < 2) {
					nextBtn = alphaButtons.poll();
				}
				else if (column <= 4) {
					nextBtn = numButtons.poll();
				}
				
				setNextButton(nextBtn, column, row);
			}
		}
	}
	
	
// Actions	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void setActions() {
		setKeyboardBtnAction();
		
		setPreviousBtnAction();
		
		setNextBtnAction();
		
		setPreviousBtnDisable();
		setNextBtnDisable();
		
		setCommaBinding();
	}
	
	private void setCommaBinding() {
		Preferences.getCommaProperty().addListener(new ChangeListener<Comma>() {
			@Override
			public void changed(ObservableValue<? extends Comma> observable, Comma oldValue, Comma newComma) {
				commaBtn.setText(String.valueOf(newComma.get()));
			}
		});
	}

	private void setNextBtnDisable() {
		buttonMap.get(alphaKeys[alphaKeys.length - 1]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!isShowingKeyboard && newValue.charAt(0) >= 'Z' || newValue.charAt(0) < 'A') {
					nextBtn.setDisable(true);
				} else if(! isShowingKeyboard) {
					nextBtn.setDisable(false);
				}
			}
		});
		
		buttonMap.get(numKeys[numKeys.length - 1]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(isShowingKeyboard && newValue.charAt(0) >= 'Z' || newValue.charAt(0) == ' ') {
					nextBtn.setDisable(true);
				} else if(isShowingKeyboard) {
					nextBtn.setDisable(false);
				}
			}
		});
	}

	private void setPreviousBtnDisable() {
		buttonMap.get(alphaKeys[0]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.charAt(0) <= 'A') {
					previousBtn.setDisable(true);
				}
				else {
					previousBtn.setDisable(false);
				}
			}
		});
	}

	private void setNextBtnAction() {
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isShowingKeyboard) {
					if(buttonMap.get(alphaKeys[0]).getText().charAt(0) <= 'Z' - 15) {
						setAllToKeyboard((char) (buttonMap.get(alphaKeys[0]).getText().charAt(0) + 15));
					}
				}
				else {
					if(buttonMap.get(alphaKeys[0]).getText().charAt(0) <= 'Z' - 6) {
						setAlphaButtons((char) (buttonMap.get(alphaKeys[0]).getText().charAt(0) + 6));
					}
				}
			}
		});
	}

	private void setPreviousBtnAction() {
		previousBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isShowingKeyboard) {
					if(buttonMap.get(alphaKeys[0]).getText().charAt(0) >= 'A' + 15) {
						setAllToKeyboard((char) (buttonMap.get(alphaKeys[0]).getText().charAt(0) - 15));
					}
				}
				else {
					if(buttonMap.get(alphaKeys[0]).getText().charAt(0) >= 'A' + 6) {
						setAlphaButtons((char) (buttonMap.get(alphaKeys[0]).getText().charAt(0) - 6));
					}
				}
			}
		});
	}

	private void setKeyboardBtnAction() {
		keyboardBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (isShowingKeyboard) {
					isShowingKeyboard = false;
					changeToNums();
				}
				else {
					isShowingKeyboard = true;
					changeToKeyboard();
				}
			}
		});
	}

	private void changeToKeyboard() {
		setAllToKeyboard('A');
		
		keyboardBtn.setText("NUM");
		
		GridPane.setColumnSpan(arrowButtons, 2);
		
		this.buttonMap.get(NUM_0_BTN).setVisible(false);
		GridPane.setColumnIndex(this.buttonMap.get(SIGN_BTN), GridPane.getColumnIndex(this.buttonMap.get(SIGN_BTN)) + 1);
	}

	private void changeToNums() {
		setAlphaButtons('A');
		setNumButtons();
		
		keyboardBtn.setText("KEYB");
		
		GridPane.setColumnSpan(arrowButtons, 1);
		
		this.buttonMap.get(NUM_0_BTN).setVisible(true);
		
		GridPane.setColumnIndex(this.buttonMap.get(SIGN_BTN), GridPane.getColumnIndex(this.buttonMap.get(SIGN_BTN)) - 1);
	}

}
