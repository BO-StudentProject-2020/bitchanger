package bitchanger.gui.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AlphaNumGrid {
	
	private ArrayList<Button> buttonList;
	private HashMap<String, Button> buttonMap;
	private CommaSymbol comma;
	private final String KEYBOARD_BTN = "keyboard";
	private final String SIGN_BTN = "sign";
	private final String NUM_0_BTN = "num_0";
	private final String COMMA_BTN = "comma";
	
	
	/**
	 * Creates a keyboard layout as new GridPane with external buttons to shift the six letters on the left and an included button to
	 * switch between the layouts (first layout: six buttons in two rows for letters on the left and a 3x4 matrix on the right that includes
	 * numbers '0' to '9', a button for sign and a button for the comma
	 * @param previous	Button that causes the letters to be shifted further
	 * @param next		Button that causes the letters to be shifted back
	 */
	public AlphaNumGrid(Button previous, Button next) {
		this();
		/*buttonList = new ArrayList<Button>();
		buttonMap = new HashMap<String, Button>();
		
		createButtons();*/
	}
	
	/**
	 * Creates a keyboard layout as a new GridPane with included buttons to shift the six letters on the left
	 */
	public AlphaNumGrid() {
		buttonList = new ArrayList<Button>();
		buttonMap = new HashMap<String, Button>();
		this.comma = CommaSymbol.COMMA_DE;
		createButtons();
	}

	private void createButtons() {
		// TODO IMPLEMENT: Buttons hinzufuegen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		// TODO MUSS NOCH ENTSPRECHEND IMPLEMENTIERT WERDEN !!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		Queue<Button> alphaButtons = new LinkedList<Button>();
		
		// 6 Buttons fuer Buchstaben anlegen
		for (int i = 0; i < 6; i++) {
			Button b = new Button(String.valueOf((char)('A' + i)));
			alphaButtons.add(b);
			buttonMap.put("alpha_" + i, b);
		}
		
		// 9 Buttons fuer Zahlen anlegen
		String[] numBtnText = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
		Queue<Button> numButtons = new LinkedList<Button>();
		for (int i = 0; i < numBtnText.length; i++) {
			Button b = new Button(numBtnText[i]);
			numButtons.add(b);
			buttonMap.put("num_" + i, b);
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
		
		Button keyboardBtn = new Button("Keyboard");
		GridPane.setColumnSpan(keyboardBtn, 2);
		setNextButton(keyboardBtn, 0, 3, KEYBOARD_BTN);
		
		Button signBtn = new Button("+/-");
		Button num0 = new Button("0");
		Button commaBtn = new Button(comma.get());
		
		Button[] lastRow = {signBtn, num0, commaBtn};
		String[] lastRowKeys = {SIGN_BTN, NUM_0_BTN, COMMA_BTN};
		for (int column = 0; column < lastRow.length; column++) {
			setNextButton(lastRow[column], column + 2, 3, lastRowKeys[column]);
		}
		
	/*	for(int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 4; j++) {
				
				if (!(i == 3 && j == 1)) {
					Button b = new Button(i + ":" + j);
					
					if (i == 3 && j == 0) {
						GridPane.setColumnSpan(b, 2);
					}
					
					buttonList.add(b);
				}
			}
		}
	*/
	}
	
	private void setNextButton(Button button, int column, int row, String key) {
		setNextButton(button, column, row);
		buttonMap.put(key, button);
	}

	private void setNextButton(Button button, int column, int row) {
		GridPane.setConstraints(button, column, row);
		buttonList.add(button);
	}

	public ArrayList<Button> getButtonMatrix() {
		return buttonList;
	}
	
	public HashMap<String, Button> getButtonMap(){
		return buttonMap;
	}
	
	public void setButtonTexts(String[] texts) {
		if(texts.length != buttonList.size()) {
			throw new IllegalArgumentException("Legth of argument >texts< must be equal to number of buttons in this matrix!");
		}
		
		for(int i = 0; i < texts.length; i++) {
			buttonList.get(i).setText(texts[i]);
		}
	}
	
	private enum CommaSymbol {
		COMMA_DE(","), COMMA_EN(".");
		private String comma;
		
		private CommaSymbol(String comma) {
			this.comma = comma;
		}
		
		protected String get() {
			return this.comma;
		}
	}
	
	
}
