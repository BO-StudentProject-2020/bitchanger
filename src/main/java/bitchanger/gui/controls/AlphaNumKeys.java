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

import bitchanger.gui.controller.AlphaNumKeysController;
import bitchanger.gui.controller.Controllable;
import bitchanger.gui.controller.Controller;
import bitchanger.preferences.Preferences;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
public class AlphaNumKeys implements Controllable {
	
	// TODO Constraints anpassen und auslagern -> setNextButton() 	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	public static final String SIGN_BTN_KEY = "signBtn";
	public static final String ZERO_BTN_KEY = "num_0";
	public static final String COMMA_BTN_KEY = "commaBtn";
	public static final String KEYBOARD_BTN_KEY = "keyboardBtn";
	public static final String NEXT_BTN_KEY = "nextBtn";
	public static final String PREVIOUS_BTN_KEY = "previousBtn";
	public static final String[] ALPHA_KEYS = {"alpha_0", "alpha_1", "alpha_2", "alpha_3", "alpha_4", "alpha_5"};
	public static final String[] NUM_KEYS = {"num_7", "num_8", "num_9", "num_4", "num_5", "num_6", "num_1", "num_2", "num_3"};
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private ArrayList<Node> buttonList;
	private HashMap<String, Button> buttonMap;
	private Button keyboardBtn;
	private Button previousBtn;
	private Button nextBtn;
	private Button commaBtn;
	private HBox arrowButtons;
	private double spacing;
	private Controller controller;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/*
	 * Creates a keyboard layout as a new GridPane with included buttons to shift the six letters on the left
	 */
	public AlphaNumKeys(double spacing, Scene scene) {
		this.buttonList = new ArrayList<Node>();
		this.buttonMap = new HashMap<String, Button>();
		this.spacing = spacing;
		
		createButtons();
		
		this.controller = new AlphaNumKeysController(this, scene);
		this.controller.setActions();
	}
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public HashMap<String, TextField> getTextFieldMap() {
		return null;
	}
	
	@Override
	public HashMap<String, Button> getButtonMap() {
		return this.buttonMap;
	}
	
	@Override
	public HashMap<String, Node> getNodeMap() {
		return null;
	}
	
	public HBox getArrowButtons() {
		return this.arrowButtons;
	}
	
	public ArrayList<Node> getButtonMatrix() {
		return buttonList;
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private void setNextButton(Node button, int column, int row, String key) {
		setNextButton(button, column, row);
		if(button instanceof Button) {
			buttonMap.put(key, (Button) button);
		}
	}

	private void setNextButton(Node button, int column, int row) {
//		GridPane.setConstraints(button, column, row);
		buttonList.add(button);
	}
	
// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
// Buttons erstellen	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createButtons() {
		// Buttons fuer den Tastaturbereich erstellen
		createMainMatrix();
		
		// Buttons in der letzten Zeile erstellen
		createControlButtons();
		
		Button signBtn = new UnfocusedButton("\u00B1");
		Button num0 = new ValueButton("0");
		commaBtn = new UnfocusedButton(String.valueOf(Preferences.getComma()));
		
		Button[] lastRow = {signBtn, num0, commaBtn};
		String[] lastRowKeys = {SIGN_BTN_KEY, ZERO_BTN_KEY, COMMA_BTN_KEY};
		for (int column = 0; column < lastRow.length; column++) {
			setNextButton(lastRow[column], column + 2, 3, lastRowKeys[column]);
		}
	}
	
	private void createControlButtons() {
		keyboardBtn = new UnfocusedButton("KEYB");
		setNextButton(keyboardBtn, 0, 3, KEYBOARD_BTN_KEY);		
		
		previousBtn = new UnfocusedButton("<");
		nextBtn = new UnfocusedButton(">");
		
		buttonMap.put(PREVIOUS_BTN_KEY, previousBtn);
		buttonMap.put(NEXT_BTN_KEY, nextBtn);
		
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

		for (int i = 0; i < 6; i++) {
			Button b = new ValueButton(String.valueOf((char)('A' + i)));
			alphaButtons.add(b);
			buttonMap.put(ALPHA_KEYS[i], b);
		}
		
		// 9 Buttons fuer Zahlen anlegen
		Queue<Button> numButtons = new LinkedList<Button>();
		String[] numBtnText = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};

		for (int i = 0; i < numBtnText.length; i++) {
			Button b = new ValueButton(numBtnText[i]);
			numButtons.add(b);
			buttonMap.put(NUM_KEYS[i], b);
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

}


