/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

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
import bitchanger.gui.controls.StyleableLabel;
import bitchanger.gui.controls.TablePane;
import bitchanger.gui.controls.ValueField;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import bitchanger.util.TriConsumer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/** <!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim M\u00FChle
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
				StyleableLabel heading = new StyleableLabel("Schritt " + i + ":");
				heading.setHeading(true);
				calcPathContainer.getChildren().add(heading);
				i++;
			}
			
			if(step.hasDescription()) {
				StyleableLabel description = new StyleableLabel(step.getDescription());
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
				StyleableLabel l = new StyleableLabel(step.toString());
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
		GridPane center = new GridPane();
		
		createInputSection(center);
		createPathContainer(center);
		
		formatGrid(center);
		root.setCenter(center);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	private void createInputSection(GridPane grid) {
		StyleableLabel lBase = new StyleableLabel("Basis");
		StyleableLabel lFrom = new StyleableLabel("Von:");
		StyleableLabel lTo = new StyleableLabel("Nach:");
		
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
		
		grid.add(lBase, 1, 0);
		GridPane.setHalignment(lBase, HPos.CENTER);
		
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
		
		grid.getChildren().addAll(children);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void formatGrid(GridPane grid) {
		grid.setPadding(new Insets(AlphaNumGridView.PADDING));
		grid.setVgap(AlphaNumGridView.SPACING);
		grid.setHgap(AlphaNumGridView.SPACING);
		
		RowConstraints firstRow = new RowConstraints();
		firstRow.setFillHeight(true);
		firstRow.setVgrow(Priority.NEVER);
		grid.getRowConstraints().add(firstRow);
		
		for(int i = 1; i < 3; i++) {
			RowConstraints row = new RowConstraints(AlphaNumGridView.TF_HEIGHT, AlphaNumGridView.TF_HEIGHT, AlphaNumGridView.TF_HEIGHT);
			row.setFillHeight(true);
			row.setVgrow(Priority.NEVER);
			grid.getRowConstraints().add(row);
		}
		
		RowConstraints lastRow = new RowConstraints();
		lastRow.setFillHeight(true);
		lastRow.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().add(lastRow);
		
		for(int i = 0; i < grid.getColumnCount() - 1; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setFillWidth(true);
			column.setHgrow(Priority.NEVER);
			grid.getColumnConstraints().add(column);
		}
		
		ColumnConstraints lastColumn = new ColumnConstraints();
		lastColumn.setFillWidth(true);
		lastColumn.setHgrow(Priority.ALWAYS);
		grid.getColumnConstraints().add(lastColumn);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void createPathContainer(GridPane grid) {
		calcPathContainer = new VBox();
		calcPathContainer.setSpacing(15);
		calcPathContainer.setPadding(new Insets(0, 15, 0, 0));
		
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(calcPathContainer);
		scroll.setFitToWidth(true);
		
		StackPane scrollContainer = new StackPane(scroll);
		scrollContainer.setPadding(new Insets(30, 0, 0, 0));
		
		GridPane.setConstraints(scrollContainer, 1, 3, 2, 1, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);
		
		grid.getChildren().add(scrollContainer);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addHornersMethod(HornersMethod horner) {
		Set<Integer> digitsOverNine = new HashSet<>();

		for(Double d : horner.getCoefficients()) {
			if(d >= 10) {
				digitsOverNine.add(d.intValue());
			}
		}
		
		if (! digitsOverNine.isEmpty()) {
			// Benötigte Stellenwertigkeiten auflisten
			StringBuilder digitList = new StringBuilder("Stellenwertigkeiten beachten:");
			digitsOverNine.stream().sorted().forEach(new Consumer<Integer>() {
				@Override
				public void accept(Integer i) {
					digitList.append("\n").append((char) ('A' + i - 10)).append("  \u2259  ").append(i);
				}
			});
			calcPathContainer.getChildren().add(new StyleableLabel(digitList.toString(), true));
		}
		
		
		TablePane hornerTable = new TablePane();
		
		// TODO Liste mit Stellenwertigkeiten
		hornerTable.add(new StyleableLabel("Basis = " + horner.getBase(), true), 0, 1);
		hornerTable.add(new StyleableLabel(String.valueOf(horner.getCoefficients().get(0)), true), 1, 0);
		hornerTable.add(new StyleableLabel(String.valueOf(horner.getSums().get(0)), true), 1, 2);
		
		int columnIndex = 2;
		
		for(int i = 1; i < horner.getCoefficients().size(); i++) {
			hornerTable.add(new StyleableLabel(String.valueOf(horner.getCoefficients().get(i)), true), columnIndex, 0);
			
			VBox product = new VBox();
			product.getChildren().add(new StyleableLabel(horner.getBase() + " x " + horner.getCoefficients().get(i-1)));
			product.getChildren().add(new StyleableLabel("=  " + horner.getProducts().get(i), true));
			product.setAlignment(Pos.CENTER);
			
			hornerTable.add(product, columnIndex, 1);
			
			VBox sum = new VBox();
			sum.getChildren().add(new StyleableLabel(horner.getCoefficients().get(i) + " + " + horner.getProducts().get(i)));
			sum.getChildren().add(new StyleableLabel("=  " + horner.getSums().get(i), true));
			sum.setAlignment(Pos.CENTER);
			
			hornerTable.add(sum, columnIndex, 2);
			
			columnIndex++;
		}
		
		hornerTable.setPadding(new Insets(20, 0, 20, 10));
		
		for(Node cell : hornerTable.getChildren()) {
			GridPane.setHalignment(cell, HPos.CENTER);
			GridPane.setValignment(cell, VPos.CENTER);
		}
		
		for(Node child : hornerTable.getCellChildren()) {
			if(child instanceof Region) {
				((Region) child).setPadding(new Insets(5, 15, 5, 15));
			}
		}
		
		ScrollPane scroll = new ScrollPane(hornerTable);
		scroll.getStyleClass().add("scroll-pane-horners-method");
		scroll.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		calcPathContainer.getChildren().add(scroll);
	}

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addLongDivisions(Queue<LongDivision> divisions) {
		GridPane table = new GridPane();
		
		int rowIndex = 0;
		for(LongDivision div : divisions) {
			StyleableLabel left = new StyleableLabel(div.getDivident() + " : " + div.getDivisor());
			GridPane.setHalignment(left, HPos.RIGHT);
			table.add(left, 0, rowIndex);
			
			table.add(new StyleableLabel(" = " + div.getQuotient()), 1, rowIndex);
			table.add(new StyleableLabel("   Rest: "), 2, rowIndex);
			table.add(new StyleableLabel(String.valueOf(div.getRemainder()), true), 3, rowIndex);
			
			// Bei Rest >= 10 passenden Buchstaben hinzufügen
			if(div.getRemainder() >= 10) {
				table.add(new StyleableLabel(" => "), 4, rowIndex);
				table.add(new StyleableLabel(String.valueOf((char)('A' + div.getRemainder() - 10)), true), 5, rowIndex);
			}
			
			rowIndex++;
		}
		
		table.setPadding(new Insets(20, 50, 20, 50));
		table.setVgap(10);
		
		calcPathContainer.getChildren().add(table);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addMultiplications(Queue<Multiplication> steps) {
		GridPane table = new GridPane();
		
		Set<Integer> digitsOverNine = new HashSet<>();
		
		int rowIndex = 0;
		for(Multiplication mult : steps) {
			if(mult.calculationWasCanceled()) {
				StyleableLabel canceled = new StyleableLabel("Berechnung nach " + rowIndex + " Nachkommastellen abbrechen -> Abbruchfehler nach ausreichend Nachkommastellen nur gering");
				GridPane.setColumnSpan(canceled, table.getColumnCount());
				table.add(canceled, 0, rowIndex);
				break;
			}
			
			StyleableLabel left = new StyleableLabel(mult.getLeftFactor() + " x " + mult.getRigthFactor() + " = ");
			GridPane.setHalignment(left, HPos.RIGHT);
			table.add(left, 0, rowIndex);
			
			String product = String.valueOf(mult.getProduct());
			int commaPos = product.indexOf('.');
			StyleableLabel resultInt = new StyleableLabel(product.substring(0, commaPos), true);
			GridPane.setHalignment(resultInt, HPos.RIGHT);
			table.add(resultInt, 1, rowIndex);
			table.add(new StyleableLabel(product.substring(commaPos)), 2, rowIndex);
			
			if(mult.getProduct() >= 10) digitsOverNine.add((int) mult.getProduct());
			
			rowIndex++;
		}
		
		table.setPadding(new Insets(20, 50, 20, 50));
		table.setVgap(10);
		
		calcPathContainer.getChildren().add(table);
		
		if (! digitsOverNine.isEmpty()) {
			// Benötigte Stellenwertigkeiten auflisten
			StringBuilder digitList = new StringBuilder("Stellenwertigkeiten beachten:");
			digitsOverNine.stream().sorted().forEach(new Consumer<Integer>() {
				@Override
				public void accept(Integer i) {
					digitList.append("\n").append(i).append("  \u2259  ").append((char) ('A' + i - 10));
				}
			});
			calcPathContainer.getChildren().add(new StyleableLabel(digitList.toString(), true));
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addTwosComplement(TwosComplement step) {
		ChangeableNumber value = new SimpleChangeableNumber();
		value.set(step.getValue());
		
		GridPane table = new GridPane();
		
		int columnIndex = 2;
		for(char c : value.toBinString().toCharArray()) {
			table.add(new StyleableLabel(String.valueOf(c)), columnIndex, 0);
			table.add(new StyleableLabel(c == '0' ? "1" : c == '1' ? "0" : " "), columnIndex, 1);
			columnIndex++;
		}
		
		columnIndex--;
		
		table.add(new StyleableLabel("Invertieren: "), 0, 1);
		table.add(new StyleableLabel("Addieren: "), 0, 2);
		table.add(new StyleableLabel(" +  "), 1, 2);
		table.add(new StyleableLabel("1"), columnIndex, 2);
		table.add(new StyleableLabel(" =  "), 1, 3);
		
		value.set(step.getResult());
		
		char[] complementReverse = new StringBuilder(value.toBinString()).reverse().toString().toCharArray();
		
		for(char c : complementReverse) {
			table.add(new StyleableLabel(String.valueOf(c)), columnIndex, 3);
			columnIndex--;
		}
		
		if(columnIndex == 2) {
			// Länge anpassen
			char c = value.toBinString().charAt(0);
			table.add(new StyleableLabel(String.valueOf(c)), columnIndex, 3);
		}
		
		calcPathContainer.getChildren().add(table);
	}
	
	
}







