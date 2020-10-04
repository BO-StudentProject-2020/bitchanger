/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayDeque;
import java.util.Queue;

import bitchanger.calculations.ChangeableNumber;
import bitchanger.calculations.ConversionStep;
import bitchanger.calculations.ConvertingNumbers;
import bitchanger.calculations.HornersMethod;
import bitchanger.calculations.LongDivision;
import bitchanger.calculations.Multiplication;
import bitchanger.calculations.SimpleChangeableNumber;
import bitchanger.calculations.TwosComplement;
import bitchanger.gui.controller.CalcPathViewController;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controls.ValueField;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import bitchanger.util.TriConsumer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/** <!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.8
 * @version 0.1.8
 *
 */
// TODO JavaDoc
public class CalcPathView extends ViewBase<BorderPane> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(CalcPathView.class, CalcPathViewController.class);
	}
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	private final IntegerProperty fromBaseProperty;
	private final IntegerProperty toBaseProperty;
	private VBox calcPathContainer;
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	public CalcPathView() {
		this("", 10, 2);
	}
	
	public CalcPathView(String value, int fromBase, int toBase) {
		super(new BorderPane(), false);
		
		this.fromBaseProperty = new SimpleIntegerProperty(fromBase);
		this.toBaseProperty = new SimpleIntegerProperty(toBase);
		
		this.buildScenegraph();
		this.getTextFieldMap().get(tfInputKey()).setText(value);
	}
	
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	public ReadOnlyIntegerProperty fromBaseProperty() {
		return this.fromBaseProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	public ReadOnlyIntegerProperty toBaseProperty() {
		return this.toBaseProperty;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	public String tfInputKey() {
		return "tfFrom";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	public String tfOutputKey() {
		return "tfTo";
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	public void setCalcPath(Queue<ConversionStep> calcPath) {
		calcPathContainer.getChildren().clear();
		
		int i = 1;
		
		while(calcPath.peek() != null) {
			ConversionStep step = calcPath.poll();
			
			if(step.isNewStep()) {
				if(i > 1) calcPathContainer.getChildren().add(new Separator());
				calcPathContainer.getChildren().add(new Label("Schritt " + i + ":"));
				i++;
			}
			
			if(step.hasDescription()) {
				Label description = new Label(step.getDescription());
				description.setWrapText(true);
				calcPathContainer.getChildren().add(description);
			}
			
			if(step.isDescription()) 
				continue;
			
			if(step instanceof LongDivision) {
				ArrayDeque<LongDivision> divisions = new ArrayDeque<>();
				divisions.add((LongDivision) step);
				
				while(calcPath.peek() instanceof LongDivision){
					divisions.add((LongDivision) calcPath.poll());
				}
				
				addLongDivisions(divisions);
			}
			else if(step instanceof Multiplication) {
				ArrayDeque<Multiplication> multiplications = new ArrayDeque<>();
				multiplications.add((Multiplication) step);
				
				while(calcPath.peek() instanceof Multiplication){
					multiplications.add((Multiplication) calcPath.poll());
				}
				
				addMultiplications(multiplications);
			}
			else if(step instanceof TwosComplement) {
				addTwosComplement((TwosComplement) step);
			}
			else if(step instanceof HornersMethod) {
				addHornersMethod((HornersMethod)step);
			}
			else {
				Label l = new Label(step.toString());
				l.setWrapText(true);
				calcPathContainer.getChildren().add(l);
			}
		}
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	@Override
	protected void createScenegraph() {
		createTop();
		createCenter();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	private void createTop() {
		Label lBase = new Label("Basis");
		Label lFrom = new Label("Von:");
		Label lTo = new Label("Nach:");
		
		ComboBox<Integer> fromBase = new ComboBox<>();
		ComboBox<Integer> toBase = new ComboBox<>();
		
		for(int i = ConvertingNumbers.MIN_BASE; i <= ConvertingNumbers.MAX_BASE; i++) {
			fromBase.getItems().add(i);
			toBase.getItems().add(i);
		}
		
		fromBase.getSelectionModel().select(Integer.valueOf(fromBaseProperty.get()));
		toBase.getSelectionModel().select(Integer.valueOf(toBaseProperty.get()));
		
		fromBaseProperty.bind(fromBase.getSelectionModel().selectedItemProperty());
		toBaseProperty.bind(toBase.getSelectionModel().selectedItemProperty());
		
		ValueField tfFrom = new ValueField();
		ValueField tfTo = new ValueField();
		
		getTextFieldMap().put(tfInputKey(), tfFrom);
		getTextFieldMap().put(tfOutputKey(), tfTo);
		
		tfFrom.getBaseProperty().bind(fromBaseProperty);
		tfTo.getBaseProperty().bind(toBaseProperty);
		tfTo.setEditable(false);
		
		GridPane top = new GridPane();
		
		top.add(lBase, 1, 0);
		
		Queue<Node> children = ArrayUtils.queueOf(lFrom, fromBase, tfFrom, lTo, toBase, tfTo);
		FXUtils.setGridConstraints(0, 1, 3, 0, children, new TriConsumer<Node, Integer, Integer>() {
			@Override
			public void accept(Node node, Integer columnIndex, Integer rowIndex) {
				GridPane.setConstraints(node, columnIndex, rowIndex);
				if (node instanceof Region) {
					((Region) node).setMinHeight(AlphaNumGridView.TF_HEIGHT);
					((Region) node).setMaxHeight(AlphaNumGridView.TF_HEIGHT);
				}
			}
		});
		
		top.getChildren().addAll(children);
		
		formatTop(top);
		
		root.setTop(top);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void formatTop(GridPane top) {
		top.setPadding(new Insets(AlphaNumGridView.PADDING));
		top.setVgap(AlphaNumGridView.SPACING);
		top.setHgap(AlphaNumGridView.SPACING);
		
		for(int i = 0; i < top.getRowCount(); i++) {
			RowConstraints row = new RowConstraints(AlphaNumGridView.TF_HEIGHT, AlphaNumGridView.TF_HEIGHT, AlphaNumGridView.TF_HEIGHT);
			row.setFillHeight(true);
			row.setVgrow(Priority.NEVER);
			top.getRowConstraints().add(row);
		}
		
		for(int i = 0; i < top.getColumnCount() - 1; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setFillWidth(true);
			column.setHgrow(Priority.NEVER);
			top.getColumnConstraints().add(column);
		}
		
		ColumnConstraints lastColumn = new ColumnConstraints();
		lastColumn.setFillWidth(true);
		lastColumn.setHgrow(Priority.ALWAYS);
		top.getColumnConstraints().add(lastColumn);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void createCenter() {
		calcPathContainer = new VBox();
		calcPathContainer.setSpacing(15);
		
		ScrollPane center = new ScrollPane();
		center.setPadding(new Insets(AlphaNumGridView.PADDING));
		center.setContent(calcPathContainer);
		
		root.setCenter(center);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addHornersMethod(HornersMethod horner) {
		GridPane hornerTable = new GridPane();
		
		// TODO Labels im StackPane unterbringen, um Linien anzeigen zu können
		hornerTable.add(new Label("Basis = " + horner.getBase()), 0, 1);
		hornerTable.add(new Label(String.valueOf(horner.getCoefficients().get(0))), 1, 0);
		hornerTable.add(new Label(String.valueOf(horner.getSums().get(0))), 1, 2);
		
		int columnIndex = 2;
		
		for(int i = 1; i < horner.getCoefficients().size(); i++) {
			hornerTable.add(new Label(String.valueOf(horner.getCoefficients().get(i))), columnIndex, 0);
			hornerTable.add(new Label(horner.getBase() + "x" + horner.getCoefficients().get(i-1) + " = " + horner.getProducts().get(i)), columnIndex, 1);
			hornerTable.add(new Label(horner.getCoefficients().get(i) + "+" + horner.getProducts().get(i) + " = " + horner.getSums().get(i)), columnIndex, 2);
			columnIndex++;
		}
		
		hornerTable.setPadding(new Insets(20, 50, 20, 50));
		hornerTable.setHgap(10);
		hornerTable.setVgap(10);
		
		calcPathContainer.getChildren().add(hornerTable);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addLongDivisions(Queue<LongDivision> divisions) {
		GridPane table = new GridPane();
		
		int rowIndex = 0;
		for(LongDivision div : divisions) {
			Label left = new Label(div.getDivident() + " : " + div.getDivisor());
			GridPane.setHalignment(left, HPos.RIGHT);
			table.add(left, 0, rowIndex);
			
			table.add(new Label(" = " + div.getQuotient()), 1, rowIndex);
			table.add(new Label(" Rest = " + div.getRemainder()), 2, rowIndex);
			
			rowIndex++;
		}
		
		table.setPadding(new Insets(20, 50, 20, 50));
		table.setVgap(10);
		
		calcPathContainer.getChildren().add(table);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addMultiplications(Queue<Multiplication> steps) {
		GridPane table = new GridPane();
		
		int rowIndex = 0;
		for(Multiplication mult : steps) {
			Label left = new Label(mult.getLeftFactor() + " x " + mult.getRigthFactor());
			GridPane.setHalignment(left, HPos.RIGHT);
			table.add(left, 0, rowIndex);
			
			table.add(new Label(" = " + mult.getProduct()), 1, rowIndex);
			
			rowIndex++;
		}
		
		table.setPadding(new Insets(20, 50, 20, 50));
		table.setVgap(10);
		
		calcPathContainer.getChildren().add(table);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addTwosComplement(TwosComplement step) {
		ChangeableNumber value = new SimpleChangeableNumber();
		value.set(step.getValue());
		
		GridPane table = new GridPane();
		
		int columnIndex = 2;
		for(char c : value.toBinString().toCharArray()) {
			table.add(new Label(String.valueOf(c)), columnIndex, 0);
			table.add(new Label(c == '0' ? "1" : "0"), columnIndex, 1);
			columnIndex++;
		}
		
		columnIndex--;
		
		table.add(new Label("Invertieren: "), 0, 1);
		table.add(new Label("Addieren: "), 0, 2);
		table.add(new Label("+"), 1, 2);
		table.add(new Label("1"), columnIndex, 2);
		
		value.set(step.getResult());
		
		char[] complementReverse = new StringBuilder(value.toBinString()).reverse().toString().toCharArray();
		
		for(char c : complementReverse) {
			table.add(new Label(String.valueOf(c)), columnIndex, 3);
			columnIndex--;
		}
		
		calcPathContainer.getChildren().add(table);
	}
	
	
}







