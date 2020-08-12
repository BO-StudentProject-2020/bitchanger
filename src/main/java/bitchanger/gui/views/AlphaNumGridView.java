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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.gui.controls.ValueField;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/** <!-- $LANGUAGE=DE -->
 * View, die die Basis Scene für eine Tabelle aus Textfeldern mit vorangestellten Labels und einer
 * darunterliegenden Tastaturmatrix aus {@link AlphaNumKeys} erstellt.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 *
 * @see ConverterController
 */
public class AlphaNumGridView extends ViewBase<BorderPane> {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// public	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Array, das die Schlüsselwörter definiert, mit denen die Textfelder in der Map {@code tfMap} gespeichert werden
	 */
	public final String[] tfKeys;

	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem der Löschen-Button (AC) in der Map {@code btnMap} gespeichert wird
	 */
	public final String clearBtnKey;

	/** <!-- $LANGUAGE=DE -->
	 * Schlüsselwort, mit dem der Backspace-Button in der Map {@code btnMap} gespeichert wird
	 */
	public final String backspaceBtnKey;
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum Vorzeichenwechsel */
	public final String signBtnKey = AlphaNumKeys.SIGN_BTN_KEY;
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button, der die Null repräsentiert */
	public final String zeroBtnKey = AlphaNumKeys.ZERO_BTN_KEY;

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Komma-Button */
	public final String commaBtnKey = AlphaNumKeys.COMMA_BTN_KEY;

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum Umschalten des Tastaturlayouts */
	public final String keyboardBtnKey = AlphaNumKeys.KEYBOARD_BTN_KEY;

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum Weiterscrollen durch die Tastatur */
	public final String nextBtnKey = AlphaNumKeys.NEXT_BTN_KEY;

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort für den Button zum Rückwärtsscrollen durch die Tastatur */
	public final String previousBtnKey = AlphaNumKeys.PREVIOUS_BTN_KEY;

	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter für die Buchstaben-Buttons definiert */
	public final String[] alphaKeys = AlphaNumKeys.ALPHA_KEYS;

	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter für die Zahlen-Buttons definiert */
	public final String[] numKeys = AlphaNumKeys.NUM_KEYS;
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Beschriftungen für die Labels vor den Textfeldern definiert */
	public final String[] labelTexts;
	
	
// protected	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Property für die maximale Höhe der Textfelder in dieser View */
	protected final DoubleProperty tfMaxHeightProperty;

	/** <!-- $LANGUAGE=DE --> Property für die minimale Höhe der Textfelder in dieser View */
	protected final DoubleProperty tfMinHeightProperty;

	/** <!-- $LANGUAGE=DE --> Property für die maximale Höhe der Buttons in dieser View */
	protected final DoubleProperty btnMaxHeigthProperty;

	/** <!-- $LANGUAGE=DE --> Property für die minimale Höhe der Buttons in dieser View */
	protected final DoubleProperty btnMinHeigthProperty;

	/** <!-- $LANGUAGE=DE --> Property für die maximale Breite der Buttons in dieser View */
	protected final DoubleProperty btnMaxWidthProperty;

	/** <!-- $LANGUAGE=DE --> Property für die minimale Breite der Buttons in dieser View */
	protected final DoubleProperty btnMinWidthProperty;

	/** <!-- $LANGUAGE=DE --> Property für die Höhe des Abstands zwischen den Buttons und Textfeldern  */
	protected final DoubleProperty whiteSpaceHeigthProperty;

	/** <!-- $LANGUAGE=DE --> Property für die Breite der ersten Spalte mit den Labels enthält. Wird benötigt, um symmetrisch Weißraum auf der rechten Seite hinzuzufügen. */
	protected final DoubleProperty firstColumnWidthProperty;

	/** <!-- $LANGUAGE=DE --> Property für den Abstand am oberen Rand der GridPane im Center */
	protected final DoubleProperty paddingTopProperty;

	/** <!-- $LANGUAGE=DE --> Property für den Abstand am rechten Rand der GridPane im Center */
	protected final DoubleProperty paddingRigthProperty;

	/** <!-- $LANGUAGE=DE --> Property für den Abstand am unteren Rand der GridPane im Center */
	protected final DoubleProperty paddingBottomProperty;

	/** <!-- $LANGUAGE=DE --> Property für den Abstand am linken Rand der GridPane im Center */
	protected final DoubleProperty paddingLeftProperty;

	/** <!-- $LANGUAGE=DE --> Property für den Abstand der Buttons in der GridPane */
	protected final DoubleProperty btnSpacingProperty;

	/** <!-- $LANGUAGE=DE --> Property für den horizontalen Abstand der GridPane im Center */
	protected final DoubleProperty hgapProperty;

	/** <!-- $LANGUAGE=DE --> Property für den vertikalen Abstand der GridPane im Center */
	protected final DoubleProperty vgapProperty;

	/** <!-- $LANGUAGE=DE --> Konstante, die die erste Zeile der GridPane definiert, in der die Tastatur-Buttons positioniert werden */
	protected final int firstKeyBtnRow;

	/** <!-- $LANGUAGE=DE --> Konstante, die die erste Spalte der GridPane definiert, in der die Tastatur-Buttons positioniert werden */
	protected final int firstKeyBtnColumn;

	/** <!-- $LANGUAGE=DE --> Konstante, die die Anzahl der Spalten in der GridPane definiert */
	protected final int maxColumns;

	/** <!-- $LANGUAGE=DE --> Tabelle im Center von {@code root}.
	 * In den oberen Zeilen werden in der Spalte 0 die Labels mit aus dem Array {@link #labelTexts}
	 * und in der Spalte 1 der Textfelder, die in {@link #tfKeys} definiert sind positioniert.
	 * Im unteren Bereich wird ab Zeile {@link #firstKeyBtnRow} und ab Spalte {@link #firstKeyBtnColumn}
	 * die Tastaturmatrix aus {@link AlphaNumKeys} eingefügt.
	 */
	protected final GridPane center;

	/** <!-- $LANGUAGE=DE --> Buttons, die als alpha-numerische Tastatur dienen, die für verschiedene Zahlensysteme ausgelegt ist. */
	protected final AlphaNumKeys alphaNum;
	
	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Map, in der die angezeigten Texte oder Icons für die Buttons definiert werden. */
	private final HashMap<String, Object> btnTexts;
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen.
	 * Die Labels in der Spalte 0 und die Textfelder in der Spalte 1 werden aus den Arrays
	 * {@code labelTexts} und {@code tfKeys} generiert. Nach den Textfeldern wird automatisch 
	 * eine Zeile frei gelassen, bevor die erste Zeile mit den Buttons beginnt. Die Buttons
	 * beginnen in der Spalte 1.
	 * 
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 */
	public AlphaNumGridView(String[] labelTexts, String... tfKeys) {
		this(Math.max(labelTexts.length, tfKeys.length) + 1, 1, labelTexts, tfKeys);
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen.
	 * Die Labels in der Spalte 0 und die Textfelder in der Spalte 1 werden aus den Arrays
	 * {@code labelTexts} und {@code tfKeys} generiert.
	 * 
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 */
	public AlphaNumGridView(int firstKeyBtnRow, int firstKeyBtnColumn, String[] labelTexts, String... tfKeys) {
		this(labelTexts, tfKeys, 40, Double.MAX_VALUE, 50, 40, 80, 20, 20, 20, 20, 6, firstKeyBtnRow,
				firstKeyBtnColumn);
	}

	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen.
	 * Die Labels in der Spalte 0 und die Textfelder in der Spalte 1 werden aus den Arrays
	 * {@code labelTexts} und {@code tfKeys} generiert.
	 * 
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 * @param tfHeight			Höhe, die für alle Textfelder in den Properties {@link #tfMaxHeightProperty} und {@link #tfMinHeightProperty} eingestellt wird
	 * @param btnMaxSize		Größe, die für alle Buttons in den Properties {@link #btnMaxHeigthProperty} und {@link #btnMaxWidthProperty} eingestellt wird
	 * @param btnMinSize		Größe, die für alle Buttons in den Properties {@link #btnMinHeigthProperty} und {@link #btnMinWidthProperty} eingestellt wird
	 * @param whiteSpaceHeigth	Höhe, die für die Property {@link #whiteSpaceHeigthProperty} eingestellt wird
	 * @param firstColumnWidth	Breite, die für die Property {@link #firstColumnWidthProperty} eingestellt wird
	 * @param paddingTop		Größe, die für die Property {@link #paddingTopProperty} eingestellt wird
	 * @param paddingRigth		Größe, die für die Property {@link #paddingRigthProperty} eingestellt wird
	 * @param paddingBottom		Größe, die für die Property {@link #paddingBottomProperty} eingestellt wird
	 * @param paddingLeft		Größe, die für die Property {@link #paddingLeftProperty} eingestellt wird
	 * @param spacing			Größe, die für die Properties {@link #btnSpacingProperty}, {@link #hgapProperty} und {@link #vgapProperty} eingestellt wird
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
	 */
	public AlphaNumGridView(String[] labelTexts, String[] tfKeys, double tfHeight, double btnMaxSize, double btnMinSize,
			double whiteSpaceHeigth, double firstColumnWidth, double paddingTop, double paddingRigth,
			double paddingBottom, double paddingLeft, double spacing, int firstKeyBtnRow, int firstKeyBtnColumn) {

		this(labelTexts, tfKeys, tfHeight, tfHeight, btnMaxSize, btnMinSize, btnMaxSize, btnMinSize, whiteSpaceHeigth,
				firstColumnWidth, paddingTop, paddingRigth, paddingBottom, paddingLeft, spacing, spacing, spacing,
				firstKeyBtnRow, firstKeyBtnColumn);
	}

	
	/**<!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen.
	 * Die Labels in der Spalte 0 und die Textfelder in der Spalte 1 werden aus den Arrays
	 * {@code labelTexts} und {@code tfKeys} generiert.
	 * 
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 * @param tfMaxHeight		Höhe, die für alle Textfelder in der Property {@link #tfMaxHeightProperty} eingestellt wird
	 * @param tfMinHeight		Höhe, die für alle Textfelder in der Property {@link #tfMinHeightProperty} eingestellt wird
	 * @param btnMaxHeigth		Höhe, die für alle Buttons in der Property {@link #btnMaxHeigthProperty} eingestellt wird
	 * @param btnMinHeigth		Höhe, die für alle Buttons in der Property {@link #btnMinHeigthProperty} eingestellt wird
	 * @param btnMaxWidth		Breite, die für alle Buttons in der Property {@link #btnMaxWidthProperty} eingestellt wird
	 * @param btnMinWidth		Breite, die für alle Buttons in der Property {@link #btnMinWidthProperty} eingestellt wird
	 * @param whiteSpaceHeigth	Höhe, die für die Property {@link #whiteSpaceHeigthProperty} eingestellt wird
	 * @param firstColumnWidth	Breite, die für die Property {@link #firstColumnWidthProperty} eingestellt wird
	 * @param paddingTop		Größe, die für die Property {@link #paddingTopProperty} eingestellt wird
	 * @param paddingRigth		Größe, die für die Property {@link #paddingRigthProperty} eingestellt wird
	 * @param paddingBottom		Größe, die für die Property {@link #paddingBottomProperty} eingestellt wird
	 * @param paddingLeft		Größe, die für die Property {@link #paddingLeftProperty} eingestellt wird
	 * @param btnSpacing		Größe, die für die Property {@link #btnSpacingProperty} eingestellt wird
	 * @param hgap				Größe, die für die Property {@link #hgapProperty} eingestellt wird
	 * @param vgap				Größe, die für die Property {@link #vgapProperty} eingestellt wird
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
	 */
	public AlphaNumGridView(String[] labelTexts, String[] tfKeys, double tfMaxHeight, double tfMinHeight,
			double btnMaxHeigth, double btnMinHeigth, double btnMaxWidth, double btnMinWidth, double whiteSpaceHeigth,
			double firstColumnWidth, double paddingTop, double paddingRigth, double paddingBottom, double paddingLeft,
			double btnSpacing, double hgap, double vgap, int firstKeyBtnRow, int firstKeyBtnColumn) {

		super(new BorderPane(), false);

		this.maxColumns = 6;
		this.tfMaxHeightProperty = new SimpleDoubleProperty(tfMaxHeight);
		this.tfMinHeightProperty = new SimpleDoubleProperty(tfMinHeight);
		this.btnMaxHeigthProperty = new SimpleDoubleProperty(btnMaxHeigth);
		this.btnMinHeigthProperty = new SimpleDoubleProperty(btnMinHeigth);
		this.btnMaxWidthProperty = new SimpleDoubleProperty(btnMaxWidth);
		this.btnMinWidthProperty = new SimpleDoubleProperty(btnMinWidth);
		this.whiteSpaceHeigthProperty = new SimpleDoubleProperty(whiteSpaceHeigth);
		this.firstColumnWidthProperty = new SimpleDoubleProperty(firstColumnWidth);
		this.paddingTopProperty = new SimpleDoubleProperty(paddingTop);
		this.paddingRigthProperty = new SimpleDoubleProperty(paddingRigth);
		this.paddingBottomProperty = new SimpleDoubleProperty(paddingBottom);
		this.paddingLeftProperty = new SimpleDoubleProperty(paddingLeft);
		this.btnSpacingProperty = new SimpleDoubleProperty(btnSpacing);
		this.hgapProperty = new SimpleDoubleProperty(hgap);
		this.vgapProperty = new SimpleDoubleProperty(vgap);

		this.firstKeyBtnRow = firstKeyBtnRow;
		this.firstKeyBtnColumn = firstKeyBtnColumn;

		if (tfKeys != null) {
			this.tfKeys = tfKeys;
		} else {
			this.tfKeys = new String[0];
		}
		
		this.clearBtnKey = "clearBtn";
		this.backspaceBtnKey = "backspaceBtn";

		if (labelTexts != null) {
			this.labelTexts = labelTexts;
		} else {
			this.labelTexts = new String[0];
		}
		
		this.btnTexts = new HashMap<String, Object>();
		this.btnTexts.put(clearBtnKey, "AC");
		this.btnTexts.put(backspaceBtnKey, "<--");

		this.center = new GridPane();
		this.alphaNum = new AlphaNumKeys(firstKeyBtnRow + 1, firstKeyBtnColumn, btnSpacingProperty, this.scene);
	}
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** {@inheritDoc} */
	@Override
	public double getMaxHeigth() {
		return paddingTopProperty.get() + paddingBottomProperty.get()
				+ getTextFieldMap().size() * tfMaxHeightProperty.get() + (center.getRowCount() - 1) * vgapProperty.get()
				+ (center.getRowCount() - firstKeyBtnRow) * btnMaxHeigthProperty.get() + whiteSpaceHeigthProperty.get();
	}

	/** {@inheritDoc} */
	@Override
	public double getMaxWidth() {
		return paddingLeftProperty.get() + paddingRigthProperty.get() + firstColumnWidthProperty.get()
				+ (maxColumns - firstKeyBtnColumn) * btnMaxWidthProperty.get() + (maxColumns - 1) * hgapProperty.get();
	}

	/** {@inheritDoc} */
	@Override
	public double getMinHeigth() {
		return paddingTopProperty.get() + paddingBottomProperty.get()
				+ getTextFieldMap().size() * tfMinHeightProperty.get() + (center.getRowCount() - 1) * vgapProperty.get()
				+ (center.getRowCount() - firstKeyBtnRow) * btnMinHeigthProperty.get() + whiteSpaceHeigthProperty.get();
	}

	/** {@inheritDoc} */
	@Override
	public double getMinWidth() {
		return paddingLeftProperty.get() + paddingRigthProperty.get() + firstColumnWidthProperty.get()
				+ (maxColumns - firstKeyBtnColumn) * btnMinWidthProperty.get() + (maxColumns - 1) * hgapProperty.get();
	}

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt den Scenegraphen und fügt diesen dem Wurzelknoten hinzu.
	 * 
	 * @param root Wurzelknoten des Scenegraphen
	 */
	@Override
	protected void createScenegraph() {
		// Label erstellen und in die GridPane einfügen
		createLabels();

		// Textfelder erstellen und in die GridPane einfügen
		createTextFields();

		// Buttons erstellen und in die GridPane einfügen
		createButtonMatrix();

		// Constraints für Größe und Wachstum setzen
		setRowConstraints();
		setColumnConstraints();

		// GridPane formatieren
		center.hgapProperty().bindBidirectional(hgapProperty);
		center.vgapProperty().bindBidirectional(vgapProperty);

		updatePadding(null, null, null);

		center.setAlignment(Pos.CENTER);

		root.setCenter(center);

		// Properties binden
		bindProperties();
	}

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
// GridPane Center	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<
	/**
	 * <!-- $LANGUAGE=DE --> Setzt alle {@code RowConstraints} für center.
	 * <p>
	 * Es werden die Höhe für Zeilen mit Textfeldern, Buttons und Whitespace
	 * eingestellt. Zudem werden die Anordnung und das Größenwachstum für die Zeilen
	 * der GridPane eingestellt.
	 * </p>
	 */
	private void setRowConstraints() {
		// RowConstraints für Zeilen mit Textfeldern
		for (int i = 0; i < getTextFieldMap().size(); i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setFillHeight(true);
			rowc.maxHeightProperty().bindBidirectional(tfMaxHeightProperty);
			rowc.minHeightProperty().bindBidirectional(tfMinHeightProperty);
			center.getRowConstraints().add(rowc);
		}

		// Zeilen zwischen Textfeldern und Buttons
		for (int i = getTextFieldMap().size(); i < firstKeyBtnRow; i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.maxHeightProperty().bindBidirectional(whiteSpaceHeigthProperty);
			rowc.minHeightProperty().bindBidirectional(whiteSpaceHeigthProperty);
			center.getRowConstraints().add(rowc);
		}

		// RowConstraints für die Buttons
		for (int i = firstKeyBtnRow; i < center.getRowCount(); i++) {
			RowConstraints rowc = new RowConstraints();
			rowc.setFillHeight(true);
			rowc.maxHeightProperty().bindBidirectional(btnMaxHeigthProperty);
			rowc.minHeightProperty().bindBidirectional(btnMinHeigthProperty);
			rowc.setValignment(VPos.CENTER);
			rowc.setVgrow(Priority.ALWAYS);
			center.getRowConstraints().add(rowc);
		}
	}

	/**
	 * <!-- $LANGUAGE=DE --> Setzt alle {@code ColumnConstraints} für center.
	 * <p>
	 * Es werden Breite der Spalten, sowie Anordnung und das Größenwachstum für alle
	 * Spalten der GridPane eingestellt.
	 * </p>
	 */
	private void setColumnConstraints() {
		// ColumnConstraints für erste Spalte
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setFillWidth(true);
		column1.setHalignment(HPos.CENTER);
		column1.setHgrow(Priority.NEVER);
		column1.maxWidthProperty().bindBidirectional(firstColumnWidthProperty);
		column1.minWidthProperty().bindBidirectional(firstColumnWidthProperty);
		center.getColumnConstraints().add(column1);

		// ColumnConstraints für Spalten mit Textfeldern und Buttons
		for (int i = 1; i < center.getColumnCount(); i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setFillWidth(true);
			column.setHalignment(HPos.CENTER);
			column.setHgrow(Priority.ALWAYS);
			column.maxWidthProperty().bindBidirectional(btnMaxWidthProperty);
			column.minWidthProperty().bindBidirectional(btnMinWidthProperty);
			center.getColumnConstraints().add(column);
		}
	}

	
// TextFields	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	/**<!-- $LANGUAGE=DE -->
	 * Erstellt alle Textfelder, für die es einen Schlüssel im Array TF_KEYS gibt. Jedes Textfeld wird der GridPane hinzugefügt und die
	 * Constraints entsprechend gesetzt. Alle Textfelder werden mit den Schlüsselwörtern aus TF_KEYS in der Textfeld-Map gespeichert.
	 */
	private void createTextFields() {
		for (int i = 0; i < tfKeys.length; i++) {
			TextField tf = new ValueField();

			// Constraints für GridPane setzen
			GridPane.setConstraints(tf, 1, i, maxColumns - 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER,
					Priority.ALWAYS);

			// Textfeld der GridPane hinzufügen
			center.getChildren().add(tf);

			// Textfeld mit Schlüssel in Map speichern
			getTextFieldMap().put(tfKeys[i], tf);
		}
	}

	
// Labels	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	/**
	 * <!-- $LANGUAGE=DE --> Erstellt alle Label und den Spinner in der ersten
	 * Spalte der GridPane. Alle Label und der Spinner werden in der GridPane
	 * positioniert. Der Spinner wird mit dem Schlüssel aus
	 * {@value #BASE_SPINNER_KEY} in der nodeMap gespeichert.
	 */
	private void createLabels() {
		ArrayDeque<Node> labels = new ArrayDeque<>();

		for (int i = 0; i < labelTexts.length; i++) {
			labels.add(new Label(labelTexts[i]));
		}

		FXUtils.setGridConstraints(0, 0, 1, 0, labels);
		center.getChildren().addAll(labels);
	}

	
// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	/**
	 * <!-- $LANGUAGE=DE --> Erstellt alle benötigten Buttons und positioniert diese
	 * in der GridPane.
	 * 
	 * @see #createButtons()
	 * @see AlphaNumKeys
	 * @see FXUtils#setMaxSizes(Iterable, double)
	 * @see FXUtils#setGridConstraints(int, int, int, int, java.util.Queue)
	 */
	private void createButtonMatrix() {
		// Buttons erstellen und im Array speichern
		// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach
		// rechts-unten in der Tabelle!)
		ArrayDeque<Node> buttonList = new ArrayDeque<>();
		buttonList.addAll(createButtons());

		// Constraints für Position in der Tabelle setzen und in die Tabelle legen
		FXUtils.setGridConstraints(firstKeyBtnColumn, firstKeyBtnRow, 5, 2, buttonList, center::add);

		// Tastaturfeld erstellen und in die Tabelle legen
		center.getChildren().addAll(alphaNum.getButtonMatrix());

		getButtonMap().putAll(alphaNum.getButtonMap());

		// Maximale Größe der Buttons setzen
		FXUtils.setMaxSizes(buttonList, Double.MAX_VALUE);
		FXUtils.setMaxSizes(alphaNum.getButtonMatrix(), Double.MAX_VALUE);
	}

	/**
	 * <!-- $LANGUAGE=DE --> Erzeugt die Buttons, die im Array BTN_KEYS definiert
	 * sind und gibt diese in einer ArrayList zurück. Die Buttons werden in der
	 * Reihenfolge in die ArrayList eingefügt, in der sie in BTN_KEYS definiert
	 * sind. Alle Buttons werden in der Map für Buttons abgelegt.
	 * <p>
	 * <b> Alle Buttons werden als Instanz von {@link UnfocusedButton} angelegt.
	 * </b>
	 * </p>
	 * 
	 * @return Liste der erstellten Buttons mit der Reihenfolge von
	 *         {@link #BTN_KEYS}
	 */
	private ArrayList<Button> createButtons() {
		// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach
		// rechts-unten in der Tabelle!)
		ArrayList<Button> buttons = new ArrayList<Button>(4);

		for (String btnKey : ArrayUtils.arrayOf(clearBtnKey, backspaceBtnKey)) {
			Button b = new UnfocusedButton();
			if (this.btnTexts.get(btnKey) instanceof String) {
				b.setText((String) this.btnTexts.get(btnKey));
			} else if (this.btnTexts.get(btnKey) instanceof Node) {
				b.setGraphic((Node) this.btnTexts.get(btnKey));
			}

			if (btnKey.equals(backspaceBtnKey)) {
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


// Properties	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	private void bindProperties() {
		getButtonMap().forEach(new BiConsumer<String, Button>() {
			@Override
			public void accept(String key, Button b) {
				if (!key.equals(AlphaNumKeys.NEXT_BTN_KEY) && !key.equals(AlphaNumKeys.PREVIOUS_BTN_KEY)) {
					b.maxHeightProperty().bindBidirectional(btnMaxHeigthProperty);
					b.minHeightProperty().bindBidirectional(btnMinHeigthProperty);
					b.maxWidthProperty().bindBidirectional(btnMaxWidthProperty);
					b.minWidthProperty().bindBidirectional(btnMinWidthProperty);
				}
			}
		});

		getTextFieldMap().forEach(new BiConsumer<String, TextField>() {
			@Override
			public void accept(String key, TextField tf) {
				tf.maxHeightProperty().bindBidirectional(tfMaxHeightProperty);
				tf.minHeightProperty().bindBidirectional(tfMinHeightProperty);
			}
		});

		paddingTopProperty.addListener(this::updatePadding);
		paddingRigthProperty.addListener(this::updatePadding);
		paddingBottomProperty.addListener(this::updatePadding);
		paddingLeftProperty.addListener(this::updatePadding);
	}

	private void updatePadding(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		center.setPadding(new Insets(paddingTopProperty.get(), paddingRigthProperty.get() + firstColumnWidthProperty.get(),
						paddingBottomProperty.get(), paddingLeftProperty.get()));
	}

}
