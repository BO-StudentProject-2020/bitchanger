package bitchanger.gui;

import java.util.ArrayList;
import java.util.HashMap;

import bitchanger.gui.elements.AlphaNumGrid;
import bitchanger.gui.elements.Representable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ConverterScene implements Representable{
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private final int startZeileButtons = 5;
	private final int startSpalteButtons = 1;
	private final int MAX_SPALTEN = 6;
	private final String[] TF_KEYS = {"hexTF", "decTF", "octTF", "binTF", "anyTF"};
	
	private Scene scene;
	private Button prevButton, nextButton, clearButton, backspaceButton;
	private HashMap<String, TextField> tfMap;	// Hiermit koennen die entsprechenden TFs direkt gesucht werden -> hilfreich fuer Actions!
	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ConverterScene(MenuBar menu) {
		BorderPane root = new BorderPane();
		root.setTop(menu);
		
		GridPane center = new GridPane();
		
		center.setGridLinesVisible(true);
		// Textfelder erstellen und in die GridPane einfuegen
		createTextFields(center);
		
		// Buttons erstellen und in die GridPane einfuegen		
		createButtonMatrix(center);
		
		root.setCenter(center);
		this.scene = new Scene(root);
	}
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private void createTextFields(GridPane center) {
		// Labels erstellen und der GridPane hinzufuegen
		createLabels(center);
		
		// Textfelder erstellen und in Liste speichern
		ArrayList<Node> textFieldList = initTextFields();
		center.getChildren().addAll(textFieldList);
		
	}
	
	private ArrayList<Node> initTextFields() {
		ArrayList<Node> list = new ArrayList<>();
		tfMap = new HashMap<>();
		
		for(int i = 0; i < TF_KEYS.length; i++) {
			String key = TF_KEYS[i];
			TextField tf = new TextField();
			
			// Constraints fuer GridPane setzen
			GridPane.setConstraints(tf, 1, i, MAX_SPALTEN - 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
			
			// Textfeld mit Schluessel in Map speichern
			tfMap.put(key, tf);
			
			// Textfeld der Liste fuer die GridPane hinzufuegen
			list.add(tf);
		}
		
		return list;
	}


	private void createLabels(GridPane center) {
		String[] labelText = {"HEX", "DEC", "OCT", "BIN"};
		
		for(int i = 0; i < labelText.length; i++) {
			Label l = new Label(labelText[i]);
			GridPane.setConstraints(l, 0, i);
			
			center.getChildren().add(l);
		}
		
	}


	private void createButtonMatrix(GridPane grid) {
		// Buttons erstellen und im Array speichern
		ArrayList<Button> buttonList = new ArrayList<Button>(23);
		buttonList.addAll(createButtons());
		
		// Tastenfelder erstellen, die Button zum weiter bzw zurueckschalten existieren bereits
		AlphaNumGrid alphaNum = new AlphaNumGrid(prevButton, nextButton);
		buttonList.addAll(alphaNum.getButtonMatrix());
		
		// Constraints fuer Position in der Tabelle setzen
		setButtonConstraints(buttonList);
		
		grid.getChildren().addAll(buttonList);;
	}

	private void setButtonConstraints(ArrayList<Button> list) {
		// Tastenmatrix in die GridPane integrieren und die Groesse richtig anpassen
		int zeile = this.startZeileButtons;
		int spalte = this.startSpalteButtons;
		for(Node n: list) {
			if(GridPane.getColumnSpan(n) == null) {
				GridPane.setColumnSpan(n, 1);
			}
			
			// Groesse automatisch anpassen
			((Button)n).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setHgrow(n, Priority.ALWAYS);
			GridPane.setVgrow(n, Priority.ALWAYS);
			
			GridPane.setFillHeight(n, true);
			GridPane.setFillWidth(n, true);
			
			GridPane.setMargin(n, new Insets(5));
			
			// Position im Feld
			GridPane.setHalignment(n, HPos.CENTER);
			GridPane.setValignment(n, VPos.CENTER);
			
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
	
	
}












