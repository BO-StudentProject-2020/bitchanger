package bitchanger.gui.views;

import java.util.ArrayList;
import java.util.HashMap;
import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.elements.AlphaNumGrid;
import bitchanger.gui.elements.BaseSpinner;
import bitchanger.gui.elements.UnfocusedButton;
import bitchanger.gui.elements.ValueField;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class ConverterView extends ViewBase{
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private final int TF_MAX_HEIGHT = 40;
	private final int TF_MIN_HEIGHT = 40;
	private final int BTN_MAX_HEIGTH = 120;
	private final int BTN_MIN_HEIGTH = 85;
	private final int BTN_MAX_WIDTH = BTN_MAX_HEIGTH;
	private final int BTN_MIN_WIDTH = BTN_MIN_HEIGTH;
	private final int WHITE_SPACE_HEIGTH = 40;
	private final int FIRST_COLUMN_WITH = 80;
	private final int PADDING_TOP = 10;
	private final int PADDING_RIGTH = 20;
	private final int PADDING_BOTTOM = 20;
	private final int PADDING_LEFT = 20;
	private final int MAX_SPALTEN = 6;
	private final int BTN_SPACING = 6;
	private final int HGAP = BTN_SPACING;
	private final int VGAP = BTN_SPACING;
	private final int FIRST_BTN_ROW = 6;
	private final int FIRST_BTN_COLUMN = 1;
	private final String[] TF_KEYS = {"hexTF", "decTF", "octTF", "binTF", "anyTF"};
	private final String CLEAR_BTN_KEY = "clearBtn";
	private final String BACKSPACE_BTN_KEY = "backspaceBtn";
	private final String[] BTN_KEYS = {CLEAR_BTN_KEY, BACKSPACE_BTN_KEY};
	
	private GridPane center;
	private HashMap<String, Object> btnTexts;
	private AlphaNumGrid alphaNum;
	private Spinner<Integer> baseSpinner;
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ConverterView(MenuBar menu) {
		super(menu);
		
		init();
		
		BorderPane root = new BorderPane();
		createRoot(root);
		
		if(menu != null) {
			root.setTop(menu);
		}
		
		this.scene = new Scene(root);
		
		setController(new ConverterController(this));
		
		// Tests
//		center.setGridLinesVisible(true);
	}
	
	public ConverterView() {
		this(null);
	}


	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public double getMaxHeigth() {
		return PADDING_TOP + PADDING_BOTTOM + getTextFields().size() * TF_MAX_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - FIRST_BTN_ROW) * BTN_MAX_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	@Override
	public double getMaxWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (this.MAX_SPALTEN - this.FIRST_BTN_COLUMN) * this.BTN_MAX_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}

	@Override
	public double getMinHeigth() {
		return PADDING_TOP + PADDING_BOTTOM + getTextFields().size() * TF_MIN_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - FIRST_BTN_ROW) * BTN_MIN_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	@Override
	public double getMinWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (this.MAX_SPALTEN - this.FIRST_BTN_COLUMN) * this.BTN_MIN_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}
	
	public Spinner<Integer> getBaseSpinner(){
		return this.baseSpinner;
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	protected void init() {
		center = new GridPane();
		btnTexts = new HashMap<String, Object>();
		
		btnTexts.put("clearBtn", "AC");
		btnTexts.put("backspaceBtn", "<--");
	}
	
	@Override
	protected void createRoot(BorderPane root) {
		createCenter();
		
		center.setAlignment(Pos.CENTER);
		center.setPadding(new Insets(PADDING_TOP, PADDING_RIGTH + FIRST_COLUMN_WITH, PADDING_BOTTOM, PADDING_LEFT));
		root.setCenter(center);
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
		for (int i = getTextFields().size(); i < this.FIRST_BTN_ROW; i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setMaxHeight(WHITE_SPACE_HEIGTH);
			rowc.setMinHeight(WHITE_SPACE_HEIGTH);
			center.getRowConstraints().add(rowc);
		}
		
		// RowConstraints fuer die Buttons
		for (int i = this.FIRST_BTN_ROW; i < center.getRowCount(); i++) {
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
		buttonList = new ArrayList<Node>(23);	// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach rechts-unten in der Tabelle!)
		buttonList.addAll(createButtons());
		
		
		// Tastenfelder erstellen, die Button zum weiter bzw zurueckschalten existieren bereits
		alphaNum = new AlphaNumGrid(BTN_SPACING);
		buttonList.addAll(alphaNum.getButtonMatrix());
		getButtons().putAll(alphaNum.getButtonMap());
		
		
		// Constraints fuer Position in der Tabelle setzen
		setButtonConstraints();
		
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

	private void setButtonConstraints() {
		// Tastenmatrix in die GridPane integrieren und die Groesse richtig anpassen
		int zeile = this.FIRST_BTN_ROW;
		int spalte = this.FIRST_BTN_COLUMN + 2;
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
			if(spalte >= this.FIRST_BTN_COLUMN + 5) {
				spalte = FIRST_BTN_COLUMN;
				zeile++;
			}
		}
	}
	
}












