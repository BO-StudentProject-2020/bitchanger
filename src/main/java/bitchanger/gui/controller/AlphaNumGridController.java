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

import java.util.ArrayList;
import bitchanger.gui.controls.AlphaNumGrid;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class AlphaNumGridController extends ControllerBase {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private ArrayList<Node> buttonList;
	private Button keyboardBtn;
	private Button previousBtn;
	private Button nextBtn;
	private Button signBtn;
	private Button zeroBtn;
	private Button commaBtn;
	private HBox arrowButtons;
	private boolean isShowingKeyboard;

	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public AlphaNumGridController(AlphaNumGrid view) {
		super(view);
		this.buttonList = view.getButtonMatrix();
		this.isShowingKeyboard = false;
	}


	@Override
	protected void initControls() {
		keyboardBtn = buttonMap.get(AlphaNumGrid.KEYBOARD_BTN_KEY);
		previousBtn = buttonMap.get(AlphaNumGrid.PREVIOUS_BTN_KEY);
		nextBtn = buttonMap.get(AlphaNumGrid.NEXT_BTN_KEY);
		signBtn = buttonMap.get(AlphaNumGrid.SIGN_BTN_KEY);
		zeroBtn = buttonMap.get(AlphaNumGrid.ZERO_BTN_KEY);
		commaBtn = buttonMap.get(AlphaNumGrid.COMMA_BTN_KEY);
		arrowButtons = ((AlphaNumGrid) view).getArrowButtons();;
	}

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public void setActions() {
		setKeyboardBtnAction();
		
		setPreviousBtnAction();
		
		setNextBtnAction();
		
		setPreviousBtnDisable();
		setNextBtnDisable();
		
		setCommaBinding();
	}
	
	private void setAlphaButtons(char startLetter) {
		for(String alphaKey: AlphaNumGrid.ALPHA_KEYS) {
			if(startLetter < 'A' || startLetter > 'Z') {
				startLetter = ' ';
			}
			Button b = buttonMap.get(alphaKey);
			b.setText(String.valueOf(startLetter));
			startLetter++;
		}
	}
	
	private void setNumButtons() {
		for(String numKey: AlphaNumGrid.NUM_KEYS) {
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
	
	private void setCommaBinding() {
		Preferences.getCommaProperty().addListener(new ChangeListener<Comma>() {
			@Override
			public void changed(ObservableValue<? extends Comma> observable, Comma oldValue, Comma newComma) {
				commaBtn.setText(String.valueOf(newComma.get()));
			}
		});
	}

	private void setNextBtnDisable() {
		buttonMap.get(AlphaNumGrid.ALPHA_KEYS[AlphaNumGrid.ALPHA_KEYS.length - 1]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!isShowingKeyboard && newValue.charAt(0) >= 'Z' || newValue.charAt(0) < 'A') {
					nextBtn.setDisable(true);
				} else if(! isShowingKeyboard) {
					nextBtn.setDisable(false);
				}
			}
		});
		
		buttonMap.get(AlphaNumGrid.NUM_KEYS[AlphaNumGrid.NUM_KEYS.length - 1]).textProperty().addListener(new ChangeListener<String>() {
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
		buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).textProperty().addListener(new ChangeListener<String>() {
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
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) <= 'Z' - 15) {
						setAllToKeyboard((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) + 15));
					}
				}
				else {
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) <= 'Z' - 6) {
						setAlphaButtons((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) + 6));
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
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) >= 'A' + 15) {
						setAllToKeyboard((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) - 15));
					}
				}
				else {
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) >= 'A' + 6) {
						setAlphaButtons((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) - 6));
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
		
		zeroBtn.setVisible(false);
		GridPane.setColumnIndex(signBtn, GridPane.getColumnIndex(signBtn) + 1);
	}

	private void changeToNums() {
		setAlphaButtons('A');
		setNumButtons();
		
		keyboardBtn.setText("KEYB");
		
		GridPane.setColumnSpan(arrowButtons, 1);
		
		zeroBtn.setVisible(true);
		
		GridPane.setColumnIndex(signBtn, GridPane.getColumnIndex(signBtn) - 1);
	}
	
	
	
}
