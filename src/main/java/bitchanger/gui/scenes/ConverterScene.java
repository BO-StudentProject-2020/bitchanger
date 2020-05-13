package bitchanger.gui.scenes;

import java.util.ArrayList;
import java.util.HashMap;
import bitchanger.gui.elements.AlphaNumGrid;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class ConverterScene implements Viewable, Controllable{
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private final int startZeileButtons = 6;
	private final int startSpalteButtons = 1;
	private final int TF_MAX_HEIGHT = 40;
	private final int TF_MIN_HEIGHT = 40;
	private final int BTN_MAX_HEIGTH = 80;
	private final int BTN_MIN_HEIGTH = 30;
	private final int BTN_MAX_WIDTH = 80;
	private final int BTN_MIN_WIDTH = 30;
	private final int WHITE_SPACE_HEIGTH = 50;
	private final int FIRST_COLUMN_WITH = 30;
	private final int PADDING_TOP = 10;
	private final int PADDING_RIGTH = 20;
	private final int PADDING_BOTTOM = 20;
	private final int PADDING_LEFT = 20;
	private final int MAX_SPALTEN = 6;
	private final int HGAP = 5;
	private final int VGAP = 5;
	private final String[] TF_KEYS = {"hexTF", "decTF", "octTF", "binTF", "anyTF"};
	private final GridPane center;
	
	private Scene scene;
	private Button prevButton, nextButton, clearButton, backspaceButton;
	private HashMap<String, TextField> tfMap;	// Hiermit koennen die entsprechenden TFs direkt gesucht werden -> hilfreich fuer Actions!
	private HashMap<String, Button> btnMap;
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ConverterScene(MenuBar menu) {
		BorderPane root = new BorderPane();
		root.setTop(menu);
		
		center = new GridPane();
		createCenter();
		
//		center.setGridLinesVisible(true);
		center.setAlignment(Pos.CENTER);
		center.setPadding(new Insets(PADDING_TOP, PADDING_RIGTH + FIRST_COLUMN_WITH, PADDING_BOTTOM, PADDING_LEFT));
		root.setCenter(center);
		this.scene = new Scene(root);
	}


	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	@Override
	public double getMaxHeigth() {
		return PADDING_TOP + PADDING_BOTTOM + tfMap.size() * TF_MAX_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - startZeileButtons) * BTN_MAX_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	@Override
	public double getMaxWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (this.MAX_SPALTEN - this.startSpalteButtons) * this.BTN_MAX_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}

	@Override
	public double getMinHeigth() {
		return PADDING_TOP + PADDING_BOTTOM + tfMap.size() * TF_MIN_HEIGHT + (center.getRowCount() - 1) * VGAP + 
				(center.getRowCount() - startZeileButtons) * BTN_MIN_HEIGTH + WHITE_SPACE_HEIGTH;
	}

	@Override
	public double getMinWidth() {
		return PADDING_LEFT + PADDING_RIGTH + FIRST_COLUMN_WITH + (this.MAX_SPALTEN - this.startSpalteButtons) * this.BTN_MIN_WIDTH
				+ (MAX_SPALTEN - 1) * HGAP;
	}
	
	@Override
	public HashMap<String, TextField> getTextFields() {
		return this.tfMap;
	}

	@Override
	public HashMap<String, Button> getButtons() {
		// TODO Buttons der Liste hinzufuegen	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		return this.btnMap;
	}	
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	// GridPane Center	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createCenter() {
		// Textfelder erstellen und in die GridPane einfuegen
		createTextFields();
		
		// Leerzeile fuer Abstand einfuegen
		
		
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
		for (int i = 0; i < tfMap.size(); i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setFillHeight(true);
			rowc.setMaxHeight(TF_MAX_HEIGHT);
			rowc.setMinHeight(TF_MIN_HEIGHT);
			center.getRowConstraints().add(rowc);
		}
		
		// Zeilen zwischen Textfeldern und Buttons
		for (int i = tfMap.size(); i < this.startZeileButtons; i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setMaxHeight(WHITE_SPACE_HEIGTH);
			rowc.setMinHeight(WHITE_SPACE_HEIGTH);
			center.getRowConstraints().add(rowc);
		}
		
		// RowConstraints fuer die Buttons
		for (int i = this.startZeileButtons; i < center.getRowCount(); i++) {
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
		
	}
	
	private ArrayList<Node> initTextFields() {
		ArrayList<Node> tfList = new ArrayList<>();
		tfMap = new HashMap<>();
		
		for(int i = 0; i < TF_KEYS.length; i++) {
			String key = TF_KEYS[i];
			TextField tf = new TextField();
			
			// Constraints fuer GridPane setzen
			GridPane.setConstraints(tf, 1, i, MAX_SPALTEN - 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
			
			// Textfeld mit Schluessel in Map speichern
			tfMap.put(key, tf);
			
			// Textfeld der Liste fuer die GridPane hinzufuegen
			tfList.add(tf);
		}
		
		return tfList;
	}
	
	
	// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	private void createButtonMatrix() {
		// Buttons erstellen und im Array speichern
		ArrayList<Button> buttonList = new ArrayList<Button>(23);
		buttonList.addAll(createButtons());
		
		// Tastenfelder erstellen, die Button zum weiter bzw zurueckschalten existieren bereits
		AlphaNumGrid alphaNum = new AlphaNumGrid(prevButton, nextButton);
		buttonList.addAll(alphaNum.getButtonMatrix());
		
		// Constraints fuer Position in der Tabelle setzen
		setButtonConstraints(buttonList);
		
		center.getChildren().addAll(buttonList);;
	}
	
	private ArrayList<Button> createButtons() {
		ArrayList<Button> buttons = new ArrayList<Button>(4);
		prevButton = new Button("<");
		nextButton = new Button(">");
		clearButton = new Button("AC");
		backspaceButton = new Button("<--");
		
		// BackspaceButton muss auf zwei Spalten verteilt werden!
		GridPane.setColumnSpan(backspaceButton, 2);
		
		// angelegte Buttons im Array speichern und zurueckgeben
		buttons.add(prevButton);
		buttons.add(nextButton);
		buttons.add(clearButton);
		buttons.add(backspaceButton);
		
		return buttons;
	}

	private void setButtonConstraints(ArrayList<Button> btnList) {
		// Tastenmatrix in die GridPane integrieren und die Groesse richtig anpassen
		int zeile = this.startZeileButtons;
		int spalte = this.startSpalteButtons;
		for(Node n: btnList) {
			if(GridPane.getColumnSpan(n) == null) {
				GridPane.setColumnSpan(n, 1);
			}
			
			// maximale Groesse anpassen
			((Button)n).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			// Position in der Tabelle
			GridPane.setConstraints(n, spalte, zeile);
			
			// Zaehlvariablen inkrementieren
			for (int i = 0; i < GridPane.getColumnSpan(n); i++) {
				spalte++;	// so viele Spalten weiter, wie das Element einnimmt
			}
			
			// Wenn maximale Spaltenanzahl ueberschritten: in naechste Zeile wechseln
			if(spalte >= this.startSpalteButtons + 5) {
				spalte = startSpalteButtons;
				zeile++;
			}
		}
	}
	
}












