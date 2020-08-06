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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**	<!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class AlphaNumKeys implements Controllable {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum Vorzeichenwechsel */
	public static final String SIGN_BTN_KEY = "signBtn";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button, der die Null repräsentiert */
	public static final String ZERO_BTN_KEY = "num_0";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Komma-Button */
	public static final String COMMA_BTN_KEY = "commaBtn";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum umschalten des Tastaturlayouts */
	public static final String KEYBOARD_BTN_KEY = "keyboardBtn";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum weiterscrollen durch die Tastatur */
	public static final String NEXT_BTN_KEY = "nextBtn";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum rückwärtsscrollen durch die Tastatur */
	public static final String PREVIOUS_BTN_KEY = "previousBtn";

	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter für die Buchstaben-Buttons definiert */
	public static final String[] ALPHA_KEYS = {"alpha_0", "alpha_1", "alpha_2", "alpha_3", "alpha_4", "alpha_5"};

	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter für die Zahlen-Buttons definiert */
	public static final String[] NUM_KEYS = {"num_7", "num_8", "num_9", "num_4", "num_5", "num_6", "num_1", "num_2", "num_3"};
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Liste, die alle Buttons der Tastatur-Matrix enthält */
	private ArrayList<Node> buttonList;
	
	/** <!-- $LANGUAGE=DE --> 
	 * {@code Map}, in die alle vom Controller benötigten Buttons mit 
	 * einem eindeutigen Schlüssel abgelegt werden */
	private HashMap<String, Button> buttonMap;
	
	/** <!-- $LANGUAGE=DE -->	Button zum Umschalten zwischen Nummernfeld und Alphabet-Ansicht */
	private Button keyboardBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem durch die Tastatur zurück gescrollt werden kann */
	private Button previousBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem durch die Tastatur vorwärts gescrollt werden kann */
	private Button nextBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem ein Komma eingegeben werden kann */
	private Button commaBtn;
	
	/** <!-- $LANGUAGE=DE -->	HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann. */
	private HBox arrowButtons;
	
	/** <!-- $LANGUAGE=DE -->	Erste Zeile, in der die Buttons in einer GridPane positioniert werden */
	private int firstRow;
	
	/** <!-- $LANGUAGE=DE -->	Erste Spalte, in der die Buttons in einer GridPane positioniert werden */
	private int firstColumn;
	
	/** <!-- $LANGUAGE=DE -->	Abstand der Buttons previousBtn und nextBtn in der HBox arrowButtons */
	private double spacing;
	
	/** <!-- $LANGUAGE=DE --> 
	 * Controller, der die Funktion zu den Bedienelementen hinzufügt. 
	 * <b> Es ist nur einmalig erlaubt einen Controller zuzuweisen! </b> */
	private final Controller controller;
	
	
	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE --> 
	 * Erstellt alle Buttons für das Tastaturlayout und setzt die Constraints für die Positionierung in einer GridPane.
	 * Zudem werden alle Bedienelemente durch einen {@link AlphaNumKeysController} mit der entsprechenden Funktion belegt, 
	 * um Tastatureingaben zu simulieren.
	 * 
	 * @param firstRow		Erste Zeile, in der die Buttons in einer GridPane positioniert werden
	 * @param firstColumn	Erste Spalte, in der die Buttons in einer GridPane positioniert werden
	 * @param spacing		Abstand der Buttons previousBtn und nextBtn in der HBox arrowButtons, sollte dem Wert von dem Abstand in der GridPane entsprechen
	 * @param scene			Scene, an die der Controller gebunden wird und die alle simulierten KeyEvents erhält
	 */
	public AlphaNumKeys(int firstRow, int firstColumn, double spacing, Scene scene) {
		this.buttonList = new ArrayList<Node>();
		this.buttonMap = new HashMap<String, Button>();
		this.firstRow = firstRow;
		this.firstColumn = firstColumn;
		this.spacing = spacing;
		
		createButtons();
		
		this.controller = new AlphaNumKeysController(this, scene);
		this.controller.setActions();
	}
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** {@inheritDoc} */
	@Override
	public HashMap<String, TextField> getTextFieldMap() {
		return new HashMap<String, TextField>(0);
	}
	
	/** {@inheritDoc} */
	@Override
	public HashMap<String, Button> getButtonMap() {
		return this.buttonMap;
	}
	
	/** {@inheritDoc} */
	@Override
	public HashMap<String, Node> getNodeMap() {
		return new HashMap<String, Node>(0);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann, zurück
	 * 
	 * @return HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann
	 */
	public HBox getArrowButtons() {
		return this.arrowButtons;
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Liste mit allen Nodes der Tastatur-Matrix zurück. Alle Elemente sind in der Reihenfolge
	 * angeordnet, wie sie Zeilenweise in der Tabelle positioniert sind.
	 * 
	 * @return Liste mit allen Nodes der Tastatur-Matrix
	 */
	public ArrayList<Node> getButtonMatrix() {
		return buttonList;
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Fügt den nächsten Button zur Liste {@link #buttonList} und zur Map {@link #buttonMap} hinzu
	 * und setzt die Constraints zur Positionierung in einer GridPane.
	 * 
	 * @param button	Node, die hinzugefügt und positioniert wird
	 * @param column	Spalte in der die Node positioniert wird
	 * @param row		Zeile in der die Node positioniert wird
	 * @param key		Schlüsselwort, mit dem der Button in der Map abgelegt wird
	 */
	private void setNextButton(Node button, int column, int row, String key) {
		setNextButton(button, column, row);
		
		if(button instanceof Button) {
			buttonMap.put(key, (Button) button);
		}
	}

	/** <!-- $LANGUAGE=DE -->
	 * Fügt den nächsten Button zur Liste {@link #buttonList} hinzu
	 * und setzt die Constraints zur Positionierung in einer GridPane.
	 * 
	 * @param button	Node, die hinzugefügt und positioniert wird
	 * @param column	Spalte in der die Node positioniert wird
	 * @param row		Zeile in der die Node positioniert wird
	 */
	private void setNextButton(Node button, int column, int row) {
		GridPane.setConstraints(button, column, row);
		buttonList.add(button);
	}
	
// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
// Buttons erstellen	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle Buttons des Tastaturlayouts und setzt alle Constraints zur Positionierung in einer GridPane.
	 * 
	 * @see #createMainMatrix()
	 * @see #createControlButtons()
	 */
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
			setNextButton(lastRow[column], column + firstColumn + 2, firstRow + 3, lastRowKeys[column]);
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt die Matrix mit den sechs Buchstaben- und neun Zahlen-Buttons.
	 * Alle Buttons werden in der Map {@link #buttonMap} gespeichert und die
	 * Constraints zur Positionierung in einer GridPane gesetzt.
	 */
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
		
		Queue<Button> alphaNumButtons = new LinkedList<Button>();
		
		for(int i = 0; i < 3; i++) {
			alphaNumButtons.add(alphaButtons.poll());
			alphaNumButtons.add(alphaButtons.poll());
			alphaNumButtons.add(numButtons.poll());
			alphaNumButtons.add(numButtons.poll());
			alphaNumButtons.add(numButtons.poll());
		}
		
		FXUtils.setGridConstraints(firstColumn, firstRow, 5, 0, alphaNumButtons, this::setNextButton);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt die Steuer-Buttons in der untersten Zeile des Tastaturlayouts, fügt diese der Map {@link #buttonMap}
	 * hinzu und setzt die Constraint zur Positionierung in einer GridPane.
	 */
	private void createControlButtons() {
		keyboardBtn = new UnfocusedButton("KEYB");
		setNextButton(keyboardBtn, firstColumn, firstRow + 3, KEYBOARD_BTN_KEY);		
		
		previousBtn = new UnfocusedButton("<");
		nextBtn = new UnfocusedButton(">");
		
		buttonMap.put(PREVIOUS_BTN_KEY, previousBtn);
		buttonMap.put(NEXT_BTN_KEY, nextBtn);
		
		previousBtn.setDisable(true);
		
		arrowButtons = new HBox();
		arrowButtons.getChildren().addAll(previousBtn, nextBtn);
		setNextButton(arrowButtons, firstColumn + 1, firstRow + 3);
		HBox.setHgrow(previousBtn, Priority.ALWAYS);
		HBox.setHgrow(nextBtn, Priority.ALWAYS);
		
		arrowButtons.setSpacing(this.spacing);
	}
	
}


