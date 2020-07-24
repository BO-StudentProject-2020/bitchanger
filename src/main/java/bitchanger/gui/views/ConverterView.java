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

import bitchanger.gui.controller.ControllerBase;
import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.controls.AlphaNumGrid;
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

//TODO Buttons der Liste hinzufuegen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!

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
	
	// Konstanten	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
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
	private static final String[] TF_KEYS = {"hexTF", "decTF", "octTF", "binTF", "anyTF"};
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem der Löschen-Button (AC)
	 * in der Map {@code btnMap} gespeichert wird */
	private static final String CLEAR_BTN_KEY = "clearBtn";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem der Backspace-Button
	 * in der Map {@code btnMap} gespeichert wird */
	private static final String BACKSPACE_BTN_KEY = "backspaceBtn";
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter definiert, mit denen die 
	 * Buttons in der Map {@code btnMap} gespeichert werden */
	private static final String[] BTN_KEYS = {CLEAR_BTN_KEY, BACKSPACE_BTN_KEY};

	
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
	private AlphaNumGrid alphaNum;
	
	/** <!-- $LANGUAGE=DE -->
	 * Spinner zur Auswahl des beliebigen Zahlensystems in den Grenzen 2 bis 36 (beides einschließlich)
	 */
	private Spinner<Integer> baseSpinner;
	
	
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
		return PADDING_TOP + PADDING_BOTTOM + getTextFields().size() * TF_MAX_HEIGHT + (center.getRowCount() - 1) * VGAP + 
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
		return PADDING_TOP + PADDING_BOTTOM + getTextFields().size() * TF_MIN_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - FIRST_BTN_ROW) * BTN_MIN_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	/** {@inheritDoc} */
	@Override
	public double getMinWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (MAX_SPALTEN - FIRST_BTN_COLUMN) * BTN_MIN_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}
	
	
	public Spinner<Integer> getBaseSpinner(){
		// TODO ersetzen durch nodeMap	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		return this.baseSpinner;
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	protected void init() {
		center = new GridPane();
		btnTexts = new HashMap<String, Object>();
		
		btnTexts.put(CLEAR_BTN_KEY, "AC");
		btnTexts.put(BACKSPACE_BTN_KEY, "<--");
	}
	
	@Override
	protected void createScenegraph(BorderPane root) {
		createCenter();
		
		center.setAlignment(Pos.CENTER);
		center.setPadding(new Insets(PADDING_TOP, PADDING_RIGTH + FIRST_COLUMN_WITH, PADDING_BOTTOM, PADDING_LEFT));
		root.setCenter(center);
	}
	
	@Override
	protected ControllerBase createController() {
		return new ConverterController(this);
	}
	
	// GridPane Center	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createCenter() {
		// Textfelder erstellen und in die GridPane einfuegen
		createTextFields();
		
		// Buttons erstellen und in die GridPane einfuegen		
		createButtonMatrix();
		
		center.setHgap(HGAP);
		center.setVgap(VGAP);
		
		// Constraints fuer Groesse und Wachstum setzen
		setRowConstraints();
		setColumnConstraints();
	}
	
	private void setRowConstraints() {
		// RowConstraints fuer Zeilen mit Textfeldern
		for (int i = 0; i < getTextFields().size(); i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setFillHeight(true);
			rowc.setMaxHeight(TF_MAX_HEIGHT);
			rowc.setMinHeight(TF_MIN_HEIGHT);
			center.getRowConstraints().add(rowc);
		}
		
		// Zeilen zwischen Textfeldern und Buttons
		for (int i = getTextFields().size(); i < FIRST_BTN_ROW; i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setMaxHeight(WHITE_SPACE_HEIGTH);
			rowc.setMinHeight(WHITE_SPACE_HEIGTH);
			center.getRowConstraints().add(rowc);
		}
		
		// RowConstraints fuer die Buttons
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
	
	private void setColumnConstraints() {
		// ColumnConstraints fuer erste Spalte 
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setFillWidth(true);
		column1.setHalignment(HPos.CENTER);
		column1.setHgrow(Priority.NEVER);
		column1.setMaxWidth(FIRST_COLUMN_WITH);
		column1.setMinWidth(FIRST_COLUMN_WITH);
		center.getColumnConstraints().add(column1);
		
		// ColumnConstraints fuer Spalten mit Textfeldern und Buttons
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
	
	
	// TextFields and Labels	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createTextFields() {
		// Labels erstellen und der GridPane hinzufuegen
		createLabels();
		
		// Textfelder erstellen und in Liste speichern
		ArrayList<Node> textFieldList = initTextFields();
		center.getChildren().addAll(textFieldList);
	}
	
	private void createLabels() {
		String[] labelText = {"HEX", "DEC", "OCT", "BIN"};
		
		for(int i = 0; i < labelText.length; i++) {
			Label l = new Label(labelText[i]);
			GridPane.setConstraints(l, 0, i);
			
			center.getChildren().add(l);
		}
		
		// Spinner für die beliebige Basis
		baseSpinner = new BaseSpinner<Integer>();
		GridPane.setConstraints(baseSpinner, 0, labelText.length);
		center.getChildren().add(baseSpinner);
	}
	
	
	private ArrayList<Node> initTextFields() {
		ArrayList<Node> tfList = new ArrayList<>();
		
		for(int i = 0; i < TF_KEYS.length; i++) {
			String key = TF_KEYS[i];
			TextField tf = new ValueField();
			
			// Constraints fuer GridPane setzen
			GridPane.setConstraints(tf, 1, i, MAX_SPALTEN - 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
			
			// Textfeld mit Schluessel in Map speichern
			getTextFields().put(key, tf);
			
			// Textfeld der Liste fuer die GridPane hinzufuegen
			tfList.add(tf);
		}
		
		return tfList;
	}
	
	
	// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createButtonMatrix() {
		// Buttons erstellen und im Array speichern
		ArrayList<Node> buttonList = new ArrayList<Node>(23);	// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach rechts-unten in der Tabelle!)
		buttonList.addAll(createButtons());
		
		
		// Tastenfelder erstellen, die Button zum weiter bzw zurueckschalten existieren bereits
		alphaNum = new AlphaNumGrid(BTN_SPACING);
		buttonList.addAll(alphaNum.getButtonMatrix());
		getButtons().putAll(alphaNum.getButtons());
		
		
		// Constraints fuer Position in der Tabelle setzen
		setButtonConstraints(buttonList);
		
		center.getChildren().addAll(buttonList);;
	}
	
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
			// Buttons mit Schluessel in Map speichern
			getButtons().put(btnKey, b);
			
			// angelegte Buttons im Array speichern und zurueckgeben
			buttons.add(b);
		}
		
		return buttons;
	}

	private void setButtonConstraints(ArrayList<Node> buttonList) {
		// Tastenmatrix in die GridPane integrieren und die Groesse richtig anpassen
		int zeile = FIRST_BTN_ROW;
		int spalte = FIRST_BTN_COLUMN + 2;
		for(Node n: buttonList) {
			if(GridPane.getColumnSpan(n) == null) {
				GridPane.setColumnSpan(n, 1);
			}
			
			// maximale Groesse anpassen
			if (n instanceof Button) {
				((Button) n).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			}
			else if (n instanceof Pane) {
				for(Node b: ((Pane) n).getChildren()) {
					if(b instanceof Button) {
						((Button) b).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					}
				}
			}
			
			// Position in der Tabelle
			GridPane.setConstraints(n, spalte, zeile);
			
			// Zaehlvariablen inkrementieren
			for (int i = 0; i < GridPane.getColumnSpan(n); i++) {
				spalte++;	// so viele Spalten weiter, wie das Element einnimmt
			}
			
			// Wenn maximale Spaltenanzahl ueberschritten: in naechste Zeile wechseln
			if(spalte >= FIRST_BTN_COLUMN + 5) {
				spalte = FIRST_BTN_COLUMN;
				zeile++;
			}
		}
	}

}












