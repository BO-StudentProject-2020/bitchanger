/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import bitchanger.gui.controller.AlphaNumKeysController;
import bitchanger.gui.controller.Controllable;
import bitchanger.gui.controller.Controller;
import bitchanger.preferences.Preferences;
import bitchanger.util.FXUtils;
import bitchanger.util.IconFactory;
import bitchanger.util.Resources;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**	<!-- $LANGUAGE=DE -->
 * AlphaNumKeys stellt ein Tastaturlayout als 4x5 Matrix bereit. Am linken Rand befinden sich sechs "Alpha-Buttons", die 
 * zur Eingabe von Buchstaben bestimmt sind. Rechts daneben steht ein 3x3 Nummernfeld zur Verf\u00FCgung, um Zahlen einzugeben.
 * Die untere Zeile enth\u00E4lt einen Button zum Umschalten in die Alphabet-Ansicht, in der das Alphabet auf allen Alpha- und
 * Nummern-Buttons abgebildet wird. Zudem gibt es zwei Buttons, mit denen durch die Buchstaben der Tastatur gescrollt werden
 * kann. Daneben befindet sich ein Button zum invertieren des Vorzeichens einer Zahl, sowie ein Button f\u00FCr die Zahl Null im
 * Nummernfeld-Modus und ein Button zur Eingabe eines Kommas.
 * 
 * <p>
 * Die Funktion erh\u00E4lt die Tastaturmatrix durch einen {@link AlphaNumKeysController}.
 * </p>
 * <p>
 * Alle alphanumerischen Buttons sind Instanzen von {@link ValueButton} und alle weiteren Buttons sind Instanzen von
 * {@link UnfocusedButton}, um den Fokus nicht auf die Tastatur zu lenken und weitere Funktionen zur Verf\u00FCgung zu stellen.
 * </p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.7
 *
 * @see AlphaNumKeysController
 * @see UnfocusedButton
 * @see ValueButton
 */
//TODO JavaDoc EN
public class AlphaNumKeys implements Controllable {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort f\u00FCr den Button zum Vorzeichenwechsel */
	// TODO JavaDoc EN
	public static final String SIGN_BTN_KEY = "signBtn";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort f\u00FCr den Button, der die Null repr\u00E4sentiert */
	// TODO JavaDoc EN
	public static final String ZERO_BTN_KEY = "num_0";

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort f\u00FCr den Komma-Button */
	// TODO JavaDoc EN
	public static final String COMMA_BTN_KEY = "commaBtn";

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort f\u00FCr den Button zum Umschalten des Tastaturlayouts */
	// TODO JavaDoc EN
	public static final String KEYBOARD_BTN_KEY = "keyboardBtn";

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort f\u00FCr den Button zum Weiterscrollen durch die Tastatur */
	// TODO JavaDoc EN
	public static final String NEXT_BTN_KEY = "nextBtn";

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort f\u00FCr den Button zum R\u00FCckw\u00E4rtsscrollen durch die Tastatur */
	// TODO JavaDoc EN
	public static final String PREVIOUS_BTN_KEY = "previousBtn";

	/** <!-- $LANGUAGE=DE -->	Array, das die Schl\u00FCsselw\u00F6rter f\u00FCr die Buchstaben-Buttons definiert */
	// TODO JavaDoc EN
	public static final String[] ALPHA_KEYS = {"alpha_0", "alpha_1", "alpha_2", "alpha_3", "alpha_4", "alpha_5"};

	/** <!-- $LANGUAGE=DE -->	Array, das die Schl\u00FCsselw\u00F6rter f\u00FCr die Zahlen-Buttons definiert */
	// TODO JavaDoc EN
	public static final String[] NUM_KEYS = {"num_7", "num_8", "num_9", "num_4", "num_5", "num_6", "num_1", "num_2", "num_3"};
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die Anzahl der Zeilen dieser Tastaturmatrix enth\u00E4lt */
	// TODO JavaDoc EN
	public static final int ROW_COUNT = 4;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die Anzahl der Spalten dieser Tastaturmatrix enth\u00E4lt */
	// TODO JavaDoc EN
	public static final int COLUMN_COUNT = 5;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(AlphaNumKeys.class, AlphaNumKeysController.class);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Property f\u00FCr den Abstand der Buttons previousBtn und nextBtn in der HBox arrowButtons */
	// TODO JavaDoc EN
	private final DoubleProperty spacingProperty;

	/** <!-- $LANGUAGE=DE -->	Liste, die alle Buttons der Tastatur-Matrix enth\u00E4lt */
	// TODO JavaDoc EN
	private final ArrayList<Node> buttonList;
	
	/** <!-- $LANGUAGE=DE --> 
	 * {@code Map}, in die alle vom Controller ben\u00F6tigten Buttons mit 
	 * einem eindeutigen Schl\u00FCssel abgelegt werden */
	// TODO JavaDoc EN
	private final HashMap<String, Button> buttonMap;
	
	/** <!-- $LANGUAGE=DE -->	Button zum Umschalten zwischen Nummernfeld und Alphabet-Ansicht */
	// TODO JavaDoc EN
	private Button keyboardBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem durch die Tastatur zur\u00FCck gescrollt werden kann */
	// TODO JavaDoc EN
	private Button previousBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem durch die Tastatur vorw\u00E4rts gescrollt werden kann */
	// TODO JavaDoc EN
	private Button nextBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem ein Komma eingegeben werden kann */
	// TODO JavaDoc EN
	private Button commaBtn;
	
	/** <!-- $LANGUAGE=DE -->	HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann. */
	// TODO JavaDoc EN
	private HBox arrowButtons;
	
	/** <!-- $LANGUAGE=DE -->	Erste Zeile, in der die Buttons in einer GridPane positioniert werden */
	// TODO JavaDoc EN
	private int firstRow;
	
	/** <!-- $LANGUAGE=DE -->	Erste Spalte, in der die Buttons in einer GridPane positioniert werden */
	// TODO JavaDoc EN
	private int firstColumn;
	
	/** <!-- $LANGUAGE=DE --> 
	 * Controller, der die Funktion zu den Bedienelementen hinzuf\u00FCgt. 
	 * <b> Es ist nur einmalig erlaubt einen Controller zuzuweisen! </b> */
	// TODO JavaDoc EN
	private final Controller controller;
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE --> 
	 * Erstellt alle Buttons f\u00FCr das Tastaturlayout und setzt die Constraints f\u00FCr die Positionierung in einer GridPane.
	 * Zudem werden alle Bedienelemente durch einen {@link AlphaNumKeysController} mit der entsprechenden Funktion belegt, 
	 * um Tastatureingaben zu simulieren.
	 * <p>
	 * Wenn {@code firstRow} oder {@code firstColumn} kleiner als 0 sind, werden keine Buttons erzeugt und nur eine leere
	 * Map bereitgestellt!
	 * </p>
	 * 
	 * @param firstRow		Erste Zeile, in der die Buttons in einer GridPane positioniert werden
	 * @param firstColumn	Erste Spalte, in der die Buttons in einer GridPane positioniert werden
	 * @param spacing		Abstand der Buttons previousBtn und nextBtn in der HBox arrowButtons, sollte dem Wert von dem 
	 * 						Abstand in der GridPane entsprechen. Die {@code spacingProperty} wird mit diesem Wert initialisiert.
	 * @param scene			Scene, an die der Controller gebunden wird und die alle simulierten KeyEvents erh\u00E4lt
	 */
	// TODO JavaDoc EN
	public AlphaNumKeys(int firstRow, int firstColumn, double spacing, Scene scene) {
		this.buttonList = new ArrayList<Node>();
		this.buttonMap = new HashMap<String, Button>();
		this.firstRow = firstRow;
		this.firstColumn = firstColumn;
		this.spacingProperty = new SimpleDoubleProperty(spacing);
		
		if (firstRow >= 0 && firstColumn >= 0) {
			createButtons();
			
			this.controller = Controller.ofArg(this, scene);
			this.controller.setActions();
		}
		else {
			this.controller = null;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE --> 
	 * Erstellt alle Buttons f\u00FCr das Tastaturlayout und setzt die Constraints f\u00FCr die Positionierung in einer GridPane.
	 * Zudem werden alle Bedienelemente durch einen {@link AlphaNumKeysController} mit der entsprechenden Funktion belegt, 
	 * um Tastatureingaben zu simulieren.
	 * <p>
	 * Wenn {@code firstRow} oder {@code firstColumn} kleiner als 0 sind, werden keine Buttons erzeugt und nur eine leere
	 * Map bereitgestellt!
	 * </p>
	 * 
	 * @param firstRow			Erste Zeile, in der die Buttons in einer GridPane positioniert werden
	 * @param firstColumn		Erste Spalte, in der die Buttons in einer GridPane positioniert werden
	 * @param spacingProperty	Property, an die die {@code spacingProperty} gebunden wird
	 * @param scene				Scene, an die der Controller gebunden wird und die alle simulierten KeyEvents erh\u00E4lt
	 */
	// TODO JavaDoc EN
	public AlphaNumKeys(int firstRow, int firstColumn, ObservableValue<Number> spacingProperty, Scene scene) {
		this(firstRow, firstColumn, spacingProperty.getValue().doubleValue(), scene);
		
		this.spacingProperty.bind(spacingProperty);
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	public Map<String, TextField> getTextFieldMap() {
		return Controllable.EMPTY_TEXTFIELD_MAP;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public Map<String, Button> getButtonMap() {
		return this.buttonMap;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public Map<String, Node> getNodeMap() {
		return Controllable.EMPTY_NODE_MAP;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt die HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann, zur\u00FCck
	 * 
	 * @return HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann
	 */
	// TODO JavaDoc EN
	public HBox getArrowButtons() {
		return this.arrowButtons;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Liste mit allen Nodes der Tastatur-Matrix zur\u00FCck. Alle Elemente sind in der Reihenfolge
	 * angeordnet, wie sie Zeilenweise in der Tabelle positioniert sind.
	 * 
	 * @return Liste mit allen Nodes der Tastatur-Matrix
	 */
	// TODO JavaDoc EN
	public ArrayList<Node> getButtonMatrix() {
		return buttonList;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt die Property f\u00FCr den Abstand der Buttons previousBtn und nextBtn in der HBox arrowButtons zur\u00FCck.
	 * 
	 * @return	Property f\u00FCr den Abstand der Buttons previousBtn und nextBtn in der HBox arrowButtons
	 */
	// TODO JavaDoc EN
	public DoubleProperty spacingProperty() {
		return spacingProperty;
	};
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * F\u00FCgt den n\u00E4chsten Button zur Liste {@link #buttonList} und zur Map {@link #buttonMap} hinzu
	 * und setzt die Constraints zur Positionierung in einer GridPane.
	 * 
	 * @param button	Node, die hinzugef\u00FCgt und positioniert wird
	 * @param column	Spalte in der die Node positioniert wird
	 * @param row		Zeile in der die Node positioniert wird
	 * @param key		Schl\u00FCsselwort, mit dem der Button in der Map abgelegt wird
	 */
	// TODO JavaDoc EN
	private void addButton(Node button, int column, int row, String key) {
		addButton(button, column, row);
		
		if(button instanceof Button) {
			buttonMap.put(key, (Button) button);
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * F\u00FCgt den n\u00E4chsten Button zur Liste {@link #buttonList} hinzu
	 * und setzt die Constraints zur Positionierung in einer GridPane.
	 * 
	 * @param button	Node, die hinzugef\u00FCgt und positioniert wird
	 * @param column	Spalte in der die Node positioniert wird
	 * @param row		Zeile in der die Node positioniert wird
	 */
	// TODO JavaDoc EN
	private void addButton(Node button, int column, int row) {
		GridPane.setConstraints(button, column, row);
		buttonList.add(button);
	}
	
	
// Buttons erstellen	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle Buttons des Tastaturlayouts und setzt alle Constraints zur Positionierung in einer GridPane.
	 * 
	 * @see #createMainMatrix()
	 * @see #createControlButtons()
	 */
	// TODO JavaDoc EN
	private void createButtons() {
		// Buttons fuer den Tastaturbereich erstellen
		createMainMatrix();
		
		// Buttons in der letzten Zeile erstellen
		createControlButtons();
		
		Button signBtn = new UnfocusedButton("\u00B1");
		Button num0 = new ValueButton("0");
		commaBtn = new UnfocusedButton(String.valueOf(Preferences.getPrefs().getComma()));
		
		Button[] lastRow = {signBtn, num0, commaBtn};
		String[] lastRowKeys = {SIGN_BTN_KEY, ZERO_BTN_KEY, COMMA_BTN_KEY};
		
		for (int column = 0; column < lastRow.length; column++) {
			addButton(lastRow[column], column + firstColumn + 2, firstRow + 3, lastRowKeys[column]);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt die Matrix mit den sechs Buchstaben- und neun Zahlen-Buttons.
	 * Alle Buttons werden in der Map {@link #buttonMap} gespeichert und die
	 * Constraints zur Positionierung in einer GridPane gesetzt.
	 */
	// TODO JavaDoc EN
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
		
		FXUtils.setGridConstraints(firstColumn, firstRow, 5, 0, alphaNumButtons, this::addButton);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt die Steuer-Buttons in der untersten Zeile des Tastaturlayouts, f\u00FCgt diese der Map {@link #buttonMap}
	 * hinzu und setzt die Constraint zur Positionierung in einer GridPane.
	 */
	// TODO JavaDoc EN
	private void createControlButtons() {
		keyboardBtn = new UnfocusedButton("ABC");
		FXUtils.setIconOrText(keyboardBtn, IconFactory.styleBindIcon(Resources.KEYBORD_OPEN_ICON, Resources.KEYBORD_OPEN_FILLED_ICON));
		addButton(keyboardBtn, firstColumn, firstRow + 3, KEYBOARD_BTN_KEY);		
		
		previousBtn = new UnfocusedButton();
		nextBtn = new UnfocusedButton();
		
		FXUtils.setIconOrText(previousBtn, IconFactory.ofSVGFile(Resources.ARROW_LEFT_ICON), "<");
		FXUtils.setIconOrText(nextBtn, IconFactory.ofSVGFile(Resources.ARROW_RIGHT_ICON), ">");
		
		buttonMap.put(PREVIOUS_BTN_KEY, previousBtn);
		buttonMap.put(NEXT_BTN_KEY, nextBtn);
		
		previousBtn.setDisable(true);
		
		arrowButtons = new HBox();
		arrowButtons.getChildren().addAll(previousBtn, nextBtn);
		addButton(arrowButtons, firstColumn + 1, firstRow + 3);
		HBox.setHgrow(previousBtn, Priority.ALWAYS);
		HBox.setHgrow(nextBtn, Priority.ALWAYS);
		
		arrowButtons.spacingProperty().bindBidirectional(this.spacingProperty);
	}
	
}








