/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayList;
import java.util.HashMap;

import bitchanger.gui.controller.Controller;
import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.gui.controls.ValueField;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;


/**	<!-- $LANGUAGE=DE -->
 * View, die die Scene für die Umwandlung von Zahlensystemen enthält.
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class ConverterView extends ViewBase<BorderPane> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die maximale Höhe der Textfelder in dieser View definiert */
	private static final int TF_MAX_HEIGHT = 40;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die minimale Höhe der Textfelder in dieser View definiert */
	private static final int TF_MIN_HEIGHT = TF_MAX_HEIGHT;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die maximale Höhe der Buttons in dieser View definiert */
	private static final int BTN_MAX_HEIGTH = 120;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die minimale Höhe der Buttons in dieser View definiert  */
	private static final int BTN_MIN_HEIGTH = 85;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die maximale Breite der Buttons in dieser View definiert */
	private static final int BTN_MAX_WIDTH = BTN_MAX_HEIGTH;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die minimale Breite der Buttons in dieser View definiert */
	private static final int BTN_MIN_WIDTH = BTN_MIN_HEIGTH;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die Höhe des Abstands zwischen den Buttons und Textfeldern definiert */
	private static final int WHITE_SPACE_HEIGTH = 40;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die Breite der ersten Spalte mit den Labels enthält. 
	 * Wird benötigt, um symmetrisch Weißraum auf der rechten Seite hinzuzufügen. */
	private static final int FIRST_COLUMN_WITH = 80;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den Abstand am oberen Rand der GridPane im Center zu definiert */
	private static final int PADDING_TOP = 10;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den Abstand am rechten Rand der GridPane im Center zu definiert */
	private static final int PADDING_RIGTH = 20;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den Abstand am unteren Rand der GridPane im Center zu definiert */
	private static final int PADDING_BOTTOM = 20;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den Abstand am linken Rand der GridPane im Center zu definiert */
	private static final int PADDING_LEFT = 20;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die Anzahl der Spalten in der GridPane definiert */
	private static final int MAX_SPALTEN = 6;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den Abstand der Buttons in der GridPane definiert */
	private static final int BTN_SPACING = 6;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den horizontalen Abstand der GridPane im Center definiert */
	private static final int HGAP = BTN_SPACING;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die den vertikalen Abstand der GridPane im Center definiert */
	private static final int VGAP = BTN_SPACING;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die erste Zeile der GridPane definiert, 
	 * in der die Tastatur-Buttons positioniert werden */
	private static final int FIRST_BTN_ROW = 6;
	
	/** <!-- $LANGUAGE=DE -->	Konstante, die die erste Spalte der GridPane definiert, 
	 * in der die Tastatur-Buttons positioniert werden */
	private static final int FIRST_BTN_COLUMN = 1;
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter definiert, mit denen die 
	 * Textfelder in der Map {@code tfMap} gespeichert werden */
	public static final String[] TF_KEYS = {"hexTF", "decTF", "octTF", "binTF", "anyTF"};
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem der Löschen-Button (AC)
	 * in der Map {@code btnMap} gespeichert wird */
	public static final String CLEAR_BTN_KEY = "clearBtn";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem der Backspace-Button
	 * in der Map {@code btnMap} gespeichert wird */
	public static final String BACKSPACE_BTN_KEY = "backspaceBtn";
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter definiert, mit denen die 
	 * Buttons in der Map {@code btnMap} gespeichert werden */
	public static final String[] BTN_KEYS = {CLEAR_BTN_KEY, BACKSPACE_BTN_KEY};
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem der Spinner für die beliebige Basis
	 * in der Map {@code nodeMap} gespeichert wird */
	public static final String BASE_SPINNER_KEY = "baseSpinner";

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Tabelle im Center von {@code root}.
	 * In dieser Tabelle werden die Textfelder und Buttons für die Zahlenumwandlung positioniert.
	 */
	private GridPane center;
	
	/** <!-- $LANGUAGE=DE -->	Map, in der die angezeigten Texte oder Icons für die Buttons definiert werden. */
	private HashMap<String, Object> btnTexts;
	
	/** <!-- $LANGUAGE=DE -->
	 * Buttons, die als alpha-numerische Tastatur dienen, die für verschiedene Zahlensysteme ausgelegt ist.
	 */
	private AlphaNumKeys alphaNum;
	
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen und positioniert die 
	 * Menüleiste im Top der BorderPane, die der Wurzelknoten des Scenegraphen ist.
	 * 
	 * @param menu	Menübar, die oben in der Scene angeheftet wird
	 */
	public ConverterView(MenuBar menu) {
		super(new BorderPane());
		
		if(menu != null) {
			root.setTop(menu);
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen.
	 */
	public ConverterView() {
		this(null);
	}


	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** {@inheritDoc} */
	@Override
	public double getMaxHeigth() {
		return PADDING_TOP + PADDING_BOTTOM + getTextFieldMap().size() * TF_MAX_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - FIRST_BTN_ROW) * BTN_MAX_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	/** {@inheritDoc} */
	@Override
	public double getMaxWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (MAX_SPALTEN - FIRST_BTN_COLUMN) * BTN_MAX_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}

	/** {@inheritDoc} */
	@Override
	public double getMinHeigth() {
		return PADDING_TOP + PADDING_BOTTOM + getTextFieldMap().size() * TF_MIN_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - FIRST_BTN_ROW) * BTN_MIN_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	/** {@inheritDoc} */
	@Override
	public double getMinWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (MAX_SPALTEN - FIRST_BTN_COLUMN) * BTN_MIN_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}
	

	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Initialisiert die Attribute {@link #center} und {@link #btnTexts} und füllt {@link #btnTexts}
	 * mit den Beschriftungen für die Buttons {@link #CLEAR_BTN_KEY} und {@link #BACKSPACE_BTN_KEY}.
	 */
	@Override
	protected void init() {
		center = new GridPane();
		btnTexts = new HashMap<String, Object>();
		
		btnTexts.put(CLEAR_BTN_KEY, "AC");
		btnTexts.put(BACKSPACE_BTN_KEY, "<--");
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt den Scenegraphen und fügt diesen dem Wurzelknoten hinzu.
	 * 
	 * @param root Wurzelknoten des Scenegraphen
	 */
	@Override
	protected void createScenegraph(BorderPane root) {
		createCenter();
		
		center.setAlignment(Pos.CENTER);
		center.setPadding(new Insets(PADDING_TOP, PADDING_RIGTH + FIRST_COLUMN_WITH, PADDING_BOTTOM, PADDING_LEFT));
		root.setCenter(center);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt einen neuen {@link ConverterController}, der mit dieser View verbunden ist.
	 * 
	 * @return neue Instanz von {@link ConverterController}, die mit dieser View verbunden ist
	 */
	@Override
	protected Controller createController() {
		return new ConverterController(this);
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// GridPane Center	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt die Kinder im Scenegraphen und setzt das Layout in der GridPane {@link #center}
	 * 
	 * @see #createTextFields()
	 * @see #createButtonMatrix()
	 * @see #setRowConstraints()
	 * @see #setColumnConstraints()
	 */
	private void createCenter() {
		// Label erstellen und in die GridPane einfügen
		createLabels();
		
		// Textfelder erstellen und in die GridPane einfügen
		createTextFields();
		
		// Buttons erstellen und in die GridPane einfügen		
		createButtonMatrix();
		
		center.setHgap(HGAP);
		center.setVgap(VGAP);
		
		// Constraints für Größe und Wachstum setzen
		setRowConstraints();
		setColumnConstraints();
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle {@code RowConstraints} für center.
	 * <p>
	 * Es werden die Höhe für Zeilen mit Textfeldern, Buttons und Whitespace eingestellt.
	 * Zudem werden die Anordnung und das Größenwachstum für die Zeilen der GridPane eingestellt.
	 * </p>
	 */
	private void setRowConstraints() {
		// RowConstraints für Zeilen mit Textfeldern
		for (int i = 0; i < getTextFieldMap().size(); i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setFillHeight(true);
			rowc.setMaxHeight(TF_MAX_HEIGHT);
			rowc.setMinHeight(TF_MIN_HEIGHT);
			center.getRowConstraints().add(rowc);
		}
		
		// Zeilen zwischen Textfeldern und Buttons
		for (int i = getTextFieldMap().size(); i < FIRST_BTN_ROW; i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setMaxHeight(WHITE_SPACE_HEIGTH);
			rowc.setMinHeight(WHITE_SPACE_HEIGTH);
			center.getRowConstraints().add(rowc);
		}
		
		// RowConstraints für die Buttons
		for (int i = FIRST_BTN_ROW; i < center.getRowCount(); i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setFillHeight(true);
			rowc.setMaxHeight(BTN_MAX_HEIGTH);
			rowc.setMinHeight(BTN_MIN_HEIGTH);
			rowc.setValignment(VPos.CENTER);
			rowc.setVgrow(Priority.ALWAYS);
			center.getRowConstraints().add(rowc);
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle {@code ColumnConstraints} für center.
	 * <p>
	 * Es werden Breite der Spalten, sowie Anordnung und das Größenwachstum
	 * für alle Spalten der GridPane eingestellt.
	 * </p>
	 */
	private void setColumnConstraints() {
		// ColumnConstraints für erste Spalte 
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setFillWidth(true);
		column1.setHalignment(HPos.CENTER);
		column1.setHgrow(Priority.NEVER);
		column1.setMaxWidth(FIRST_COLUMN_WITH);
		column1.setMinWidth(FIRST_COLUMN_WITH);
		center.getColumnConstraints().add(column1);
		
		// ColumnConstraints für Spalten mit Textfeldern und Buttons
		for (int i = 1; i < center.getColumnCount(); i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setFillWidth(true);
			column.setHalignment(HPos.CENTER);
			column.setHgrow(Priority.ALWAYS);
			column.setMaxWidth(BTN_MAX_WIDTH);
			column.setMinWidth(BTN_MIN_WIDTH);
			center.getColumnConstraints().add(column);
		}
	}
	

	// TextFields	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle Textfelder, für die es einen Schlüssel im Array TF_KEYS gibt.
	 * Jedes Textfeld wird der GridPane hinzugefügt und die Constraints entsprechend gesetzt.
	 * Alle Textfelder werden mit den Schlüsselwörtern aus TF_KEYS in der Textfeld-Map gespeichert.
	 */
	private void createTextFields() {
		for(int i = 0; i < TF_KEYS.length; i++) {
			TextField tf = new ValueField();
			
			// Constraints für GridPane setzen
			GridPane.setConstraints(tf, 1, i, MAX_SPALTEN - 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
			
			// Textfeld der GridPane hinzufügen
			center.getChildren().add(tf);
			
			// Textfeld mit Schlüssel in Map speichern
			getTextFieldMap().put(TF_KEYS[i], tf);
		}
	}
	
	
	// Labels	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle Label und den Spinner in der ersten Spalte der GridPane.
	 * Alle Label und der Spinner werden in der GridPane positioniert. Der Spinner wird mit dem Schlüssel aus {@value #BASE_SPINNER_KEY} in der nodeMap gespeichert.
	 */
	private void createLabels() {
		String[] labelText = {"HEX", "DEC", "OCT", "BIN"};
		
		for(int i = 0; i < labelText.length; i++) {
			Label l = new Label(labelText[i]);
			GridPane.setConstraints(l, 0, i);
			
			center.getChildren().add(l);
		}
		
		// Spinner für die beliebige Basis
		Spinner<Integer> baseSpinner = new BaseSpinner();
		
		getNodeMap().put(BASE_SPINNER_KEY, baseSpinner);
		
		GridPane.setConstraints(baseSpinner, 0, labelText.length);
		center.getChildren().add(baseSpinner);
	}
	
	
	// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle benötigten Buttons und positioniert diese in der GridPane.
	 * 
	 * @see #createButtons()
	 * @see AlphaNumKeys
	 * @see #setButtonConstraints(ArrayList)
	 */
	private void createButtonMatrix() {
		// Buttons erstellen und im Array speichern
		// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach rechts-unten in der Tabelle!)
		ArrayList<Node> buttonList = new ArrayList<Node>(23);
		buttonList.addAll(createButtons());
		
		// Tastaturfeld erstellen
		alphaNum = new AlphaNumKeys(BTN_SPACING, this.scene);
		buttonList.addAll(alphaNum.getButtonMatrix());
		
		getButtonMap().putAll(alphaNum.getButtonMap());
		
		// Constraints für Position in der Tabelle setzen
		setButtonConstraints(buttonList);
		
		center.getChildren().addAll(buttonList);;
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt die Buttons, die im Array BTN_KEYS definiert sind und gibt diese in einer ArrayList zurück.
	 * Die Buttons werden in der Reihenfolge in die ArrayList eingefügt, in der sie in BTN_KEYS definiert sind.
	 * Alle Buttons werden in der Map für Buttons abgelegt.
	 * <p><b>
	 * Alle Buttons werden als Instanz von {@link UnfocusedButton} angelegt.
	 * </b></p>
	 * 
	 * @return	Liste der erstellten Buttons mit der Reihenfolge von {@link #BTN_KEYS}
	 */
	private ArrayList<Button> createButtons() {
		// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach rechts-unten in der Tabelle!)
		ArrayList<Button> buttons = new ArrayList<Button>(4);
		
		for(String btnKey : BTN_KEYS) {
			Button b = new UnfocusedButton();
			if(this.btnTexts.get(btnKey) instanceof String) {
				b.setText((String) this.btnTexts.get(btnKey));
			}
			else if(this.btnTexts.get(btnKey) instanceof Node) {
				b.setGraphic((Node) this.btnTexts.get(btnKey));
			}
			
			if (btnKey.equals(BACKSPACE_BTN_KEY)) {
				// BackspaceButton muss auf zwei Spalten verteilt werden!
				GridPane.setColumnSpan(b, 2);
			}
			
			// Buttons mit Schlüssel in Map speichern
			getButtonMap().put(btnKey, b);
			
			// angelegte Buttons im Array speichern und zurückgeben
			buttons.add(b);
		}
		
		return buttons;
	}

	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Constrains der Buttons, um diese in der GridPane richtig zu positionieren.
	 * <p>
	 * Der erste Button wird an die Position gesetzt, die über die Konstanten FIRST_BTN_ROW und
	 * FIRST_BTN_COLUMN definiert ist. Die Matrix wird auf fünf Spalten verteilt und die Zeile
	 * automatisch umgebrochen.
	 * </p>
	 * <p>
	 * Die maximale Größe wird bei allen Buttons eingestellt, damit sich sich die Größe dem Fenster anpassen kann.
	 * </p>
	 * 
	 * @param buttonList	Liste mit Buttons in der richtigen Reihenfolge.
	 */
	private void setButtonConstraints(ArrayList<Node> buttonList) {
		// Button-Matrix in die GridPane integrieren und die Größe richtig anpassen
		int zeile = FIRST_BTN_ROW;
		int spalte = FIRST_BTN_COLUMN + 2;	// in der ersten Zeile bleiben die ersten Felder frei
		for(Node n: buttonList) {
			if(GridPane.getColumnSpan(n) == null) {
				GridPane.setColumnSpan(n, 1);
			}
			
			// maximale Größe anpassen
			if (n instanceof Button) {
				((Button) n).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			}
			else if (n instanceof Pane) {
				// Buttons liegen im Layout-Container
				for(Node b: ((Pane) n).getChildren()) {
					if(b instanceof Button) {
						((Button) b).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					}
				}
			}
			
			// Position in der Tabelle
			GridPane.setConstraints(n, spalte, zeile);
			
			// Zählvariablen inkrementieren
			for (int i = 0; i < GridPane.getColumnSpan(n); i++) {
				spalte++;	// so viele Spalten weiter, wie das Element einnimmt
			}
			
			// Wenn maximale Spaltenanzahl überschritten: in nächste Zeile wechseln
			if(spalte >= FIRST_BTN_COLUMN + 5) {
				spalte = FIRST_BTN_COLUMN;
				zeile++;
			}
		}
	}

}












