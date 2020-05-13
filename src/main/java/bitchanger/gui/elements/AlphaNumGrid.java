package bitchanger.gui.elements;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AlphaNumGrid {
	
	
	/**
	 * Creates a keyboard layout as new GridPane with external buttons to shift the six letters on the left and an included button to
	 * switch between the layouts (first layout: six buttons in two rows for letters on the left and a 3x4 matrix on the right that includes
	 * numbers '0' to '9', a button for sign and a button for the comma
	 * @param previous	Button that causes the letters to be shifted further
	 * @param next		Button that causes the letters to be shifted back
	 */
	public AlphaNumGrid(Button previous, Button next) {
		super();
		
		createButtons();
	}
	
	/**
	 * Creates a keyboard layout as a new GridPane with included buttons to shift the six letters on the left
	 */
	public AlphaNumGrid() {
		super();
		
		createButtons();
	}

	private void createButtons() {
		// TODO IMPLEMENT: Buttons hinzufuegen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		
		
	}
	
	public ArrayList<Button> getButtonMatrix() {
		ArrayList<Button> list = new ArrayList<Button>();
		
		// TODO MUSS NOCH ENTSPRECHEND IMPLEMENTIERT WERDEN !!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		for(int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 4; j++) {
				
				if (!(i == 3 && j == 1)) {
					Button b = new Button(i + ":" + j);
					GridPane.setConstraints(b, j, i);
					if (i == 3 && j == 0) {
						GridPane.setColumnSpan(b, 2);
					}
					
					list.add(b);
				}
			}
		}
		
		return list;
	}
	
	
	
}
