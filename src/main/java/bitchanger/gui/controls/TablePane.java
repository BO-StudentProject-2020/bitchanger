/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

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
public class TablePane extends GridPane {
	
	 
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constants		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	public static final String TOP_ROW_STYLE_CLASS = "top-row";
	public static final String INNER_ROW_STYLE_CLASS = "inner-row";
	public static final String BOTTOM_ROW_STYLE_CLASS = "bottom-row";
	public static final String ODD_ROW_STYLE_CLASS = "odd-row";
	public static final String EVEN_ROW_STYLE_CLASS = "even-row";
	
	public static final String LEFT_COLUMN_STYLE_CLASS = "left-column";
	public static final String INNER_COLUMN_STYLE_CLASS = "inner-column";
	public static final String RIGHT_COLUMN_STYLE_CLASS = "right-column";
	public static final String ODD_COLUMN_STYLE_CLASS = "odd-column";
	public static final String EVEN_COLUMN_STYLE_CLASS = "even-column";
	
	public static final String TOP_LEFT_CELL_STYLE_CLASS = "top-left-cell";
	public static final String TOP_RIGHT_CELL_STYLE_CLASS = "top-right-cell";
	public static final String BOTTOM_LEFT_CELL_STYLE_CLASS = "bottom-left-cell";
	public static final String BOTTOM_RIGHT_CELL_STYLE_CLASS = "bottom-right-cell";
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	private final ObservableList<Node> children;
	private final ObservableList<Node> superChildren;
	private final ArrayList<ArrayList<StackCell>> columns;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	public TablePane() {
		super();
		this.children = FXCollections.observableArrayList();
		this.superChildren = super.getChildren();
		this.columns = new ArrayList<>();
		
		observeCells();
		observeChildren();
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	private void observeCells() {
		super.getChildren().addListener(new ListChangeListener<Node>() {
			@Override
			public void onChanged(Change<? extends Node> c) {
				while (c.next()) {
					setCellStyleClasses();
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void observeChildren() {
		children.addListener(new ListChangeListener<Node>() {
			@Override
			public void onChanged(Change<? extends Node> c) {
				while (c.next()) {
					if (c.wasUpdated()) {
						// update item
					}
					else if (c.wasAdded() || c.wasRemoved()) {
						for (Node addedChild : c.getAddedSubList()) {
							Integer columnIndex = GridPane.getColumnIndex(addedChild);
							Integer rowIndex = GridPane.getRowIndex(addedChild);
							
							positionChild(addedChild, columnIndex, rowIndex);
							
							addedChild.getProperties().addListener(new MapChangeListener<Object, Object>() {
								@Override
								public void onChanged(Change<? extends Object, ? extends Object> change) {
									updateGridConstraints(addedChild);
								}
							});
						}
						
						for (Node removed : c.getRemoved()) {
							removeChild(removed);
						}
					}
				}
			}
		});
	}

	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	public ObservableList<Node> getCellChildren() {
		return this.children;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	@Override
	public void add(Node child, int columnIndex, int rowIndex) {
		GridPane.setConstraints(child, columnIndex, rowIndex);
		children.add(child);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	@Override
	public void add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan) {
		GridPane.setConstraints(child, columnIndex, rowIndex, colspan, rowspan);
		children.add(child);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	@Override
	public void addRow(int rowIndex, Node... children) {
		for (int i = 0; i < children.length; i++) {
			add(children[i], i, rowIndex);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	@Override
	public void addColumn(int columnIndex, Node... children) {
		for (int i = 0; i < children.length; i++) {
			add(children[i], columnIndex, i);
		}
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	private void setCellStyleClasses() {
		int lastColumnIndex = this.getColumnCount() - 1;
		int lastRowIndex = this.getRowCount() - 1;
		
		for(Node n : super.getChildren()) {
			Integer columnIndex = GridPane.getColumnIndex(n);
			Integer rowIndex = GridPane.getRowIndex(n);
			
			if(columnIndex == null || columnIndex < 0 || rowIndex == null || rowIndex < 0) {
				continue;
			}
			
			setStyleClasses(n, columnIndex, rowIndex, lastColumnIndex, lastRowIndex);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void setStyleClasses(Node node, int columnIndex, int rowIndex, int lastColumnIndex, int lastRowIndex) {
		Set<String> styleClasses = new HashSet<>(node.getStyleClass());
		
		
		if(columnIndex == 0) styleClasses.add(LEFT_COLUMN_STYLE_CLASS);
		else				 styleClasses.remove(LEFT_COLUMN_STYLE_CLASS);
		
		if(columnIndex != 0 && columnIndex != lastColumnIndex) styleClasses.add(INNER_COLUMN_STYLE_CLASS);
		else				 			   					   styleClasses.remove(INNER_COLUMN_STYLE_CLASS);
		
		if(columnIndex == lastColumnIndex) styleClasses.add(RIGHT_COLUMN_STYLE_CLASS);
		else				 			   styleClasses.remove(RIGHT_COLUMN_STYLE_CLASS);
		
		if(columnIndex % 2 == 0) styleClasses.add(EVEN_COLUMN_STYLE_CLASS);
		else				 styleClasses.remove(EVEN_COLUMN_STYLE_CLASS);
		
		if(columnIndex % 2 != 0) styleClasses.add(ODD_COLUMN_STYLE_CLASS);
		else				 styleClasses.remove(ODD_COLUMN_STYLE_CLASS);
		
		
		if(rowIndex == 0) styleClasses.add(TOP_ROW_STYLE_CLASS);
		else				 styleClasses.remove(TOP_ROW_STYLE_CLASS);
		
		if(rowIndex != 0 && rowIndex != lastRowIndex) styleClasses.add(INNER_ROW_STYLE_CLASS);
		else				 			   					   styleClasses.remove(INNER_ROW_STYLE_CLASS);
		
		if(rowIndex == lastRowIndex) styleClasses.add(BOTTOM_ROW_STYLE_CLASS);
		else				 			   styleClasses.remove(BOTTOM_ROW_STYLE_CLASS);
		
		if(rowIndex % 2 == 0) styleClasses.add(EVEN_ROW_STYLE_CLASS);
		else				 styleClasses.remove(EVEN_ROW_STYLE_CLASS);
		
		if(rowIndex % 2 != 0) styleClasses.add(ODD_ROW_STYLE_CLASS);
		else				 styleClasses.remove(ODD_ROW_STYLE_CLASS);
		
		if(columnIndex == 0 && rowIndex == 0) styleClasses.add(TOP_LEFT_CELL_STYLE_CLASS);
		else				 				  styleClasses.remove(TOP_LEFT_CELL_STYLE_CLASS);
		
		if(columnIndex == 0 && rowIndex == lastRowIndex) styleClasses.add(TOP_RIGHT_CELL_STYLE_CLASS);
		else				 				 			 styleClasses.remove(TOP_RIGHT_CELL_STYLE_CLASS);
		
		if(columnIndex == lastColumnIndex && rowIndex == 0) styleClasses.add(BOTTOM_LEFT_CELL_STYLE_CLASS);
		else				 								styleClasses.remove(BOTTOM_LEFT_CELL_STYLE_CLASS);
		
		if(columnIndex == lastColumnIndex && rowIndex == lastRowIndex) styleClasses.add(BOTTOM_RIGHT_CELL_STYLE_CLASS);
		else				 				  						   styleClasses.remove(BOTTOM_RIGHT_CELL_STYLE_CLASS);
		
		node.getStyleClass().clear();
		node.getStyleClass().addAll(styleClasses);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void updateGridConstraints(Node child) {
		Integer columnIndex = GridPane.getColumnIndex(child);
		Integer rowIndex = GridPane.getRowIndex(child);
		
		positionChild(child, columnIndex, rowIndex);
		
		Priority hgrow = GridPane.getHgrow(child);
		Priority vgrow = GridPane.getVgrow(child);
		
		if(hgrow != null) {
			GridPane.setHgrow(child.getParent(), hgrow);
		}
		if(vgrow != null) {
			GridPane.setVgrow(child.getParent(), vgrow);
		}
		
		
		HPos halignment = GridPane.getHalignment(child);
		VPos valignment = GridPane.getValignment(child);
		
		StackPane.setAlignment(child, convertPos(valignment, halignment));
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private Pos convertPos(VPos vpos, HPos hpos) {
		Pos pos;
		
		try {
			pos = Pos.valueOf(vpos.name() + "_" + hpos.toString());
		} catch (Exception e) {
			pos = Pos.CENTER;
		}
		
		return pos;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void positionChild(Node child, Integer columnIndex, Integer rowIndex) {
		if(columnIndex == null || columnIndex < 0 || rowIndex == null || rowIndex < 0) {
			return;
		}
		
		removeChild(child);
		
		while(columnIndex >= columns.size()) {
			addColumn();
		}
		
		ArrayList<StackCell> rows = columns.get(columnIndex);
		
		while(rowIndex >= rows.size()) {
			addRow();
		}
		
		StackCell cell = rows.get(rowIndex);
		cell.getChildren().add(child);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addRow() {
		int columnIndex = 0;
		for(ArrayList<StackCell> column : columns) {
			StackCell cell = new StackCell();

			GridPane.setConstraints(cell, columnIndex, column.size());

			column.add(cell);
			superChildren.add(cell);
			
			columnIndex++;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void addColumn() {
		ArrayList<StackCell> column = new ArrayList<>();
		columns.add(column);
		
		for (int i = 0; i < this.getRowCount(); i++) {
			StackCell cell = new StackCell();
			GridPane.setConstraints(cell, columns.size() - 1, i);

			column.add(cell);
			superChildren.add(cell);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void removeChild(Node child) {
		Parent parent = child.getParent();
		
		if(parent == null)
			return;
		
		if(parent instanceof Pane)
			((Pane)parent).getChildren().remove(child);
		
		superChildren.remove(parent);
		
		if(parent.getStyleClass().contains(RIGHT_COLUMN_STYLE_CLASS)) {
			removeLastColumn();
			
		}
		
		if(parent.getStyleClass().contains(BOTTOM_ROW_STYLE_CLASS)) {
			removeLastRow();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void removeLastColumn() {
		ArrayList<StackCell> lastColumn = columns.get(columns.size() - 1);
		boolean isEmpty = true;
		
		for(StackCell cell : lastColumn) {
			if(! cell.getChildren().isEmpty()) {
				isEmpty = false;
				break;
			}
		}
		
		if(isEmpty) {
			superChildren.removeAll(lastColumn);
			columns.remove(columns.size() - 1);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	private void removeLastRow() {
		boolean isEmpty = true;
		
		for(ArrayList<StackCell> column : columns) {
			StackCell cell = column.get(column.size() - 1);
			
			if(! cell.getChildren().isEmpty()) {
				isEmpty = false;
				break;
			}
		}
		
		if(isEmpty) {
			for(ArrayList<StackCell> column : columns) {
				StackCell cell = column.get(column.size() - 1);
				
				superChildren.remove(cell);
				column.remove(cell);
			}
		}
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	nested Classes   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	protected static class StackCell extends StackPane {
		
		public StackCell(Node... children) {
			this();
			this.getChildren().addAll(children);
		}

		public StackCell() {
			super();
			
			try { this.getStyleClass().remove("stack-pane"); } catch (Exception e) { /* ignore */ }
			this.getStyleClass().add("stack-cell");
			
			this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setFillHeight(this, true);
			GridPane.setFillWidth(this, true);
		}
	}
	
	
}
