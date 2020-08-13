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
import java.util.HashMap;
import java.util.function.BiConsumer;

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
 * View, die die Basis Scene für eine Tabelle aus einer Spalte mit Labels, einer Spalte mit Textfeldern 
 * und einer Tastaturmatrix aus {@link AlphaNumKeys} erstellt.
 * <p>
 * Die Position der Labels und Textfelder werden über die Attribute {@link #labelColumn}, {@link #firstLabelRow},
 * {@link #tfColumn} und {@link #firstTFRow} festgelegt. Die Anzahl der Labels und Textfelder wird über die Arrays
 * {@link #labelTexts} und {@link #tfKeys} festgelegt. Alle Labels werden in der definierten Spalte untereinander 
 * in die Zeilen der GridPane {@link #center} positioniert. Analog gilt das selbe für die Textfelder.
 * <b> Alle Textfelder sind eine Instanz von {@link ValueField}. </b>
 * </p>
 * <p>
 * Zudem wird eine funktionsfähige Tastaturmatrix aus {@link AlphaNumKeys} in der Tabelle eingefügt. Die Position
 * des ersten Buttons dieser Matrix ist durch die Attribut {@link #firstKeyBtnColumn} und {@link #firstKeyBtnRow} + 1
 * definiert. Hat das Attribut {@link #useClearAndBackBtn} zum Zeitpunkt des Aufrufes der Methode {@link #createScenegraph()}
 * den Wert {@code true} werden in der Zeile oberhalb der Tastaturmatrix die Buttons {@link #clearBtnKey} und 
 * {@link #backspaceBtnKey} positioniert. Unabhängig davon werden diese beiden Buttons in jedem Fall vollständig initialisiert 
 * und zur Map {@link #getButtonMap()} hinzugefügt und können somit auch manuell in der GridPane positioniert werden.
 * <b> Alle Buttons werden als Instanz von {@link UnfocusedButton} angelegt. </b>
 * </p>
 * <p><b>
 * Die Textfelder wachsen in vertikale Richtung mit den Spalten der GridPane, die Buttons der Tastaturmatrix
 * wachsen sowohl mit den Spalten als auch mit den Zeilen der GridPane.
 * </b></p>
 * <p><b>
 * Wenn das Attribut {@link #setTFColumnSpan} zum Zeitpunkt des Aufrufes der Methode {@link #createScenegraph()} den Wert {@code true} hat,
 * werden die Textfelder über die Anzahl der Spalten verteilt, die die Tastaturmatrix einnimmt!
 * </b></p>
 * <p>
 * Der Scenegraph dieser View muss erst von einer Subklasse mit der Methode {@link #createScenegraph()} erstellt werden,
 * bevor diese View dargestellt werden kann.
 * </p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 *
 */
public class AlphaNumGridView extends ViewBase<BorderPane> {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
// public	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Array, das die Schlüsselwörter definiert, mit denen die Textfelder in der Map {@code tfMap} gespeichert werden */
	public final String[] tfKeys;

	/** <!-- $LANGUAGE=DE --> Schlüsselwort, mit dem der Löschen-Button (AC) in der Map {@code btnMap} gespeichert wird */
	public final String clearBtnKey;

	/** <!-- $LANGUAGE=DE --> Schlüsselwort, mit dem der Backspace-Button in der Map {@code btnMap} gespeichert wird */
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

	/** <!-- $LANGUAGE=DE -->
	 * {@code true}, wenn die Textfelder über die Anzahl der Spalten von der Tastatur verteilt werden sollen.
	 * Subklassen können dieses Attribut vor dem Aufruf der Methode {@link #createScenegraph()} auf {@code false}
	 * setzen, um die Textfelder nur in eine einzige Spalte zu legen.
	 */
	protected boolean setTFColumnSpan;
	
	/** <!-- $LANGUAGE=DE -->
	 * {@code true}, wenn die Buttons {@link #clearBtnKey} und {@link #backspaceBtnKey} in der Zeile
	 * {@link #firstKeyBtnRow} über der Tastaturmatrix in der GridPane positioniert werden sollen.
	 * Subklassen können dieses Attribut vor dem Aufruf der Methode {@link #createScenegraph()} auf {@code false}
	 * setzen, um die Buttons nicht in der GridPane zu positionieren.
	 * <p><b>
	 * Unabhängig von diesem Attribut werden die beiden Buttons vollständig initialisiert und in der Map
	 * {@link #getButtonMap()} eingefügt!
	 * </b></p>
	 */
	protected boolean useClearAndBackBtn;
	
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

	/** <!-- $LANGUAGE=DE --> Property für die Höhe der Zeilen zwischen den Textfeldern und Buttons */
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

	/** <!-- $LANGUAGE=DE --> Konstante, die die erste Zeile der GridPane definiert, in der die Textfelder positioniert werden */
	protected final int firstTFRow;
	
	/** <!-- $LANGUAGE=DE --> Konstante, die die Spalte der GridPane definiert, in der die Textfelder positioniert werden */
	protected final int tfColumn;
	
	/** <!-- $LANGUAGE=DE --> Konstante, die die erste Zeile der GridPane definiert, in der die Labels positioniert werden */
	protected final int firstLabelRow;
	
	/** <!-- $LANGUAGE=DE --> Konstante, die die Spalte der GridPane definiert, in der die Labels positioniert werden */
	protected final int labelColumn;
	
	/** <!-- $LANGUAGE=DE --> Konstante, die die erste Zeile der GridPane definiert, in der die Tastatur-Buttons positioniert werden */
	protected final int firstKeyBtnRow;

	/** <!-- $LANGUAGE=DE --> Konstante, die die erste Spalte der GridPane definiert, in der die Tastatur-Buttons positioniert werden */
	protected final int firstKeyBtnColumn;

	/** <!-- $LANGUAGE=DE --> Tabelle im Center von {@code root}, in der alle Controls positioniert werden */
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
	 * Erzeugt eine neue AlphaNumGridView, die keine Labels und keine Textfelder enthält.
	 * Der Scenegraph wird nicht automatisch erstellt.
	 * 
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
	 */
	public AlphaNumGridView(int firstKeyBtnRow, int firstKeyBtnColumn) {
		this(-1, -1, -1, -1, firstKeyBtnRow, firstKeyBtnColumn, null);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue AlphaNumGridView, die ihre Labels in der Spalte 0 ab Zeile 0 und ihre Textfelder in der Spalte 1 
	 * ab Zeile 0 positioniert. Der Scenegraph wird nicht automatisch erstellt.
	 * Die Labels in der Spalte 0 und die Textfelder in der Spalte 1 werden aus den Arrays {@code labelTexts} und 
	 * {@code tfKeys} generiert. Nach den Textfeldern wird automatisch eine Zeile frei gelassen, bevor die erste Zeile 
	 * mit den Buttons beginnt. Die Buttons beginnen in der Spalte 1.
	 * 
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 */
	public AlphaNumGridView(String[] labelTexts, String... tfKeys) {
		this(0, 0, 0, 1, Math.max(labelTexts.length, tfKeys.length) + 1, 1, labelTexts, tfKeys);
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue AlphaNumGridView, die ihre Labels, Textfelder und die Tastaturmatrix nach den übergebenen Parametern
	 * positioniert. Der Scenegraph wird nicht automatisch erstellt.
	 * Die Labels und die Textfelder werden aus den Arrays {@code labelTexts} und {@code tfKeys} generiert. 
	 * 
	 * @param firstLabelRow		erste Zeile in der GridPane, in der die Labels positioniert werden
	 * @param labelColumn		Spalte in der GridPane, in der die Labels positioniert werden
	 * @param firstTFRow		erste Zeile in der GridPane, in der die Textfelder positioniert werden
	 * @param tfColumn			Spalte in der GridPane, in der die Textfelder positioniert werden
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 */
	public AlphaNumGridView(int firstLabelRow, int labelColumn, int firstTFRow, int tfColumn, int firstKeyBtnRow, 
			int firstKeyBtnColumn, String[] labelTexts, String... tfKeys) {
		
		this(firstLabelRow, labelColumn, firstTFRow, tfColumn, firstKeyBtnRow, firstKeyBtnColumn, labelTexts, 
				tfKeys, 40, Double.MAX_VALUE, 50, 40, 80, 20, 6);
	}

	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue AlphaNumGridView, die ihre Labels, Textfelder und die Tastaturmatrix nach den übergebenen Parametern
	 * positioniert. Der Scenegraph wird nicht automatisch erstellt.
	 * Die Labels und die Textfelder werden aus den Arrays {@code labelTexts} und {@code tfKeys} generiert.
	 * 
	 * @param firstLabelRow		erste Zeile in der GridPane, in der die Labels positioniert werden
	 * @param labelColumn		Spalte in der GridPane, in der die Labels positioniert werden
	 * @param firstTFRow		erste Zeile in der GridPane, in der die Textfelder positioniert werden
	 * @param tfColumn			Spalte in der GridPane, in der die Textfelder positioniert werden
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param labelTexts		Beschriftungen für die Labels vor den Textfeldern
	 * @param tfKeys			Schlüsselwörter, mit denen die generierten Textfelder in der Map {@code tfMap} gespeichert werden
	 * @param tfHeight			Höhe, die für alle Textfelder in den Properties {@link #tfMaxHeightProperty} und {@link #tfMinHeightProperty} eingestellt wird
	 * @param btnMaxSize		Größe, die für alle Buttons in den Properties {@link #btnMaxHeigthProperty} und {@link #btnMaxWidthProperty} eingestellt wird
	 * @param btnMinSize		Größe, die für alle Buttons in den Properties {@link #btnMinHeigthProperty} und {@link #btnMinWidthProperty} eingestellt wird
	 * @param whiteSpaceHeigth	Höhe, die für die Property {@link #whiteSpaceHeigthProperty} eingestellt wird
	 * @param firstColumnWidth	Breite, die für die Property {@link #firstColumnWidthProperty} eingestellt wird
	 * @param paddingTopRigthBottomLeft		Größe, die für die Properties {@link #paddingTopProperty}, {@link #paddingRigthProperty}, {@link #paddingBottomProperty} und {@link #paddingLeftProperty} eingestellt wird
	 * @param spacing			Größe, die für die Properties {@link #btnSpacingProperty}, {@link #hgapProperty} und {@link #vgapProperty} eingestellt wird
	 */
	public AlphaNumGridView(int firstLabelRow, int labelColumn, int firstTFRow, int tfColumn, int firstKeyBtnRow, 
			int firstKeyBtnColumn, String[] labelTexts, String[] tfKeys, double tfHeight, double btnMaxSize, double btnMinSize,
			double whiteSpaceHeigth, double firstColumnWidth, double paddingTopRigthBottomLeft, double spacing) {

		this(firstLabelRow, labelColumn, firstTFRow, tfColumn, firstKeyBtnRow, firstKeyBtnColumn, labelTexts, tfKeys, tfHeight, 
				tfHeight, btnMaxSize, btnMinSize, btnMaxSize, btnMinSize, whiteSpaceHeigth, firstColumnWidth, paddingTopRigthBottomLeft, 
				paddingTopRigthBottomLeft, paddingTopRigthBottomLeft, paddingTopRigthBottomLeft, spacing, spacing, spacing);
	}

	
	/**<!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue AlphaNumGridView, die ihre Labels, Textfelder und die Tastaturmatrix nach den übergebenen Parametern
	 * positioniert. Der Scenegraph wird nicht automatisch erstellt.
	 * Die Labels und die Textfelder werden aus den Arrays {@code labelTexts} und {@code tfKeys} generiert. 
	 * 
	 * @param firstLabelRow		erste Zeile in der GridPane, in der die Labels positioniert werden
	 * @param labelColumn		Spalte in der GridPane, in der die Labels positioniert werden
	 * @param firstTFRow		erste Zeile in der GridPane, in der die Textfelder positioniert werden
	 * @param tfColumn			Spalte in der GridPane, in der die Textfelder positioniert werden
	 * @param firstKeyBtnRow	erste Zeile in der GridPane, in der die Tastatur-Buttons positioniert werden
	 * @param firstKeyBtnColumn	erste Spalte in der GridPane, in der die Tastatur-Buttons positioniert werden
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
	 */
	public AlphaNumGridView(int firstLabelRow, int labelColumn, int firstTFRow, int tfColumn, int firstKeyBtnRow, int firstKeyBtnColumn,
			String[] labelTexts, String[] tfKeys, double tfMaxHeight, double tfMinHeight, double btnMaxHeigth, double btnMinHeigth, 
			double btnMaxWidth, double btnMinWidth, double whiteSpaceHeigth, double firstColumnWidth, double paddingTop,
			double paddingRigth, double paddingBottom, double paddingLeft, double btnSpacing, double hgap, double vgap) {

		super(new BorderPane(), false);

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

		this.firstTFRow = (firstTFRow >= 0 && tfColumn >= 0) ? firstTFRow : -1;
		this.tfColumn = (firstTFRow >= 0 && tfColumn >= 0) ? tfColumn : -1;
		this.firstLabelRow = (firstLabelRow >= 0 && labelColumn >= 0) ? firstLabelRow : -1;
		this.labelColumn = (firstLabelRow >= 0 && labelColumn >= 0) ? labelColumn : -1;
		this.firstKeyBtnRow = (firstKeyBtnRow >= 0 && firstKeyBtnColumn >= 0) ? firstKeyBtnRow : -1;
		this.firstKeyBtnColumn = (firstKeyBtnRow >= 0 && firstKeyBtnColumn >= 0) ? firstKeyBtnColumn : -1;
		
		this.setTFColumnSpan = true;
		this.useClearAndBackBtn = true;

		boolean createTextFields = (tfKeys != null) && (tfColumn >= 0) && (firstTFRow >= 0);
		this.tfKeys = createTextFields ? tfKeys : new String[0];

		boolean createLabels = (labelTexts != null) && (labelColumn >= 0) && (firstLabelRow >= 0);
		this.labelTexts = createLabels ? labelTexts : new String[0];
		
		this.clearBtnKey = "clearBtn";
		this.backspaceBtnKey = "backspaceBtn";

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
				+ AlphaNumKeys.COLUMN_COUNT * btnMaxWidthProperty.get() + (center.getColumnCount() - 1) * hgapProperty.get();
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
				+ AlphaNumKeys.COLUMN_COUNT * btnMinWidthProperty.get() + (center.getColumnCount() - 1) * hgapProperty.get();
	}

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt den Scenegraphen und fügt diesen dem Wurzelknoten hinzu.
	 * Subklassen können diese Methode überschreiben, um nach der Erstellung des Scenegraphen in dieser
	 * Klasse weitere Bedienelemente hinzuzufügen.
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
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle {@code RowConstraints} für center.
	 * <p>
	 * Es werden die Höhe für Zeilen mit Textfeldern, Buttons und Whitespace eingestellt. Zudem werden die Anordnung 
	 * und das Größenwachstum für die Zeilen der GridPane eingestellt.
	 * </p>
	 */
	private void setRowConstraints() {
		// RowConstraints für Zeilen mit Textfeldern
		for (int i = firstTFRow; i < firstTFRow + getTextFieldMap().size(); i++) {
			addRowConstraint(i, ConstraintType.TEXT_FIELD_ROW);
		}

		// Zeilen zwischen Textfeldern und Buttons
		int firstWhitespaceRow = Math.min(firstTFRow + getTextFieldMap().size(), firstKeyBtnRow + AlphaNumKeys.ROW_COUNT);
		int whitespaceEnd = Math.max(firstTFRow, firstKeyBtnRow);
		
		for (int i = firstWhitespaceRow; i < whitespaceEnd; i++) {
			addRowConstraint(i, ConstraintType.WHITESPACE_ROW);
		}

		// RowConstraints für die Buttons
		int btnStartRow = useClearAndBackBtn ? firstKeyBtnRow : firstKeyBtnRow + 1;
		int btnEndRow = useClearAndBackBtn ? btnStartRow + AlphaNumKeys.ROW_COUNT + 1 : btnStartRow + AlphaNumKeys.ROW_COUNT;
		
		for (int i = btnStartRow; i < btnEndRow; i++) {
			addRowConstraint(i, ConstraintType.BUTTON_ROW);
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Erstellt neue RowConstraints für die Zeile mit dem Index {@code rowIndex}, die an den ConstraintType angepasst sind.
	 * Der ConstraintType bestimmt, ob sich in der Zeile Textfelder, Buttons oder Weißraum befindet, um Valignment und Vgrow
	 * anzupassen und die Properties maxHeightProperty und minHeightProperty mit den passenden Properties dieser AlphaNumGridView
	 * bidirektional zu verbinden.
	 * 
	 * @param rowIndex	Index der Zeile, für die neue RowConstraints hinzugefügt werden
	 * @param type		Typ der Zeile
	 */
	protected void addRowConstraint(int rowIndex, ConstraintType type) {
		if(rowIndex < 0) {
			return;
		}
		
		RowConstraints rowc = new RowConstraints();
		rowc.setFillHeight(true);
		
		switch(type) {
		case TEXT_FIELD_ROW:
			rowc.maxHeightProperty().bindBidirectional(tfMaxHeightProperty);
			rowc.minHeightProperty().bindBidirectional(tfMinHeightProperty);
			break;
		case BUTTON_ROW:
			rowc.maxHeightProperty().bindBidirectional(btnMaxHeigthProperty);
			rowc.minHeightProperty().bindBidirectional(btnMinHeigthProperty);
			rowc.setValignment(VPos.CENTER);
			rowc.setVgrow(Priority.ALWAYS);
			break;
		case WHITESPACE_ROW:
			rowc.maxHeightProperty().bindBidirectional(whiteSpaceHeigthProperty);
			rowc.minHeightProperty().bindBidirectional(whiteSpaceHeigthProperty);
			break;
		case FIRST_COLUMN:	/* fall through	*/
		case BUTTON_COLUMN:	/* fall through	*/
		default:
			return;
		}
		
		// Constraints auffüllen, wenn vorherige Constraints fehlen
		while(rowIndex > center.getRowConstraints().size()) {
			center.getRowConstraints().add(new RowConstraints());
		}
		
		// Alte Constraints entfernen, um nicht alle Constraints zu verschieben
		try {
			center.getRowConstraints().remove(rowIndex);
		} catch (Exception e) { /* ignore */ }
		
		// Constraints hinzufügen
		center.getRowConstraints().add(rowIndex, rowc);
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle {@code ColumnConstraints} für center.
	 * <p>
	 * Es werden Breite der Spalten, sowie Anordnung und das Größenwachstum für alle Spalten der GridPane eingestellt.
	 * </p>
	 * <p><b>
	 * Verbindet die Properties {@code maxWidthProperty} und {@code minWidthProperty} der ColumnConstraints bidirektional 
	 * mit der Property {@link #firstColumnWidthProperty} für die erste Spalte und mit den Properties {@link #btnMaxWidthProperty}
	 * und {@link #btnMinWidthProperty} für alle weitere Spalten.
	 * </b></p>
	 */
	private void setColumnConstraints() {
		// ColumnConstraints für erste Spalte
		addColumnConstraint(0, ConstraintType.FIRST_COLUMN);

		// ColumnConstraints für Spalten mit Buttons
		for (int i = firstKeyBtnColumn; i < firstKeyBtnColumn + AlphaNumKeys.COLUMN_COUNT; i++) {
			addColumnConstraint(i, ConstraintType.BUTTON_COLUMN);
		}
	}

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt neue ColumnConstraints für die Spalte mit dem Index {@code columnIndex}, die an den ConstraintType angepasst sind.
	 * Der ConstraintType bestimmt, ob es sich um die erste Spalte oder um eine Spalte mit Buttons handelt, um Hgrow
	 * anzupassen und die Properties maxWidthProperty und minWidthProperty mit den passenden Properties dieser AlphaNumGridView
	 * bidirektional zu verbinden.
	 * 
	 * @param columnIndex	Index der Spalte, für die neue RowConstraints hinzugefügt werden
	 * @param type			Typ der Spalte
	 */
	protected void addColumnConstraint(int columnIndex, ConstraintType type) {
		if(columnIndex < 0) {
			return;
		}
		
		ColumnConstraints column = new ColumnConstraints();
		column.setFillWidth(true);
		column.setHalignment(HPos.CENTER);
		
		switch(type) {
		case FIRST_COLUMN:
			column.setHgrow(Priority.NEVER);
			column.maxWidthProperty().bindBidirectional(firstColumnWidthProperty);
			column.minWidthProperty().bindBidirectional(firstColumnWidthProperty);
			break;
		case BUTTON_COLUMN:
			column.setHgrow(Priority.ALWAYS);
			column.maxWidthProperty().bindBidirectional(btnMaxWidthProperty);
			column.minWidthProperty().bindBidirectional(btnMinWidthProperty);
			break;
		case TEXT_FIELD_ROW:	/* fall through	*/
		case BUTTON_ROW:		/* fall through	*/
		case WHITESPACE_ROW:	/* fall through	*/
		default:
			return;
		}
		
		// Constraints auffüllen, wenn vorherige Constraints fehlen
		while(columnIndex > center.getColumnConstraints().size()) {
			center.getColumnConstraints().add(new ColumnConstraints());
		}
		
		// Alte Constraints entfernen, um nicht alle Constraints zu verschieben
		try {
			center.getColumnConstraints().remove(columnIndex);
		} catch (Exception e) { /* ignore */ }
		
		// Constraints hinzufügen
		center.getColumnConstraints().add(columnIndex, column);
	}
	
	
// TextFields	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	/**<!-- $LANGUAGE=DE -->
	 * Erstellt alle Textfelder, für die es einen Schlüssel im Array {@link #tfKeys} gibt. Jedes Textfeld wird 
	 * als Instanz von {@link ValueField} der GridPane hinzugefügt und die Constraints entsprechend gesetzt. 
	 * Alle Textfelder werden mit den Schlüsselwörtern aus {@link #tfKeys} in der Textfeld-Map gespeichert.
	 */
	private void createTextFields() {
		for (int row = firstTFRow; row < firstTFRow + tfKeys.length; row++) {
			TextField tf = new ValueField();

			// Constraints für GridPane setzen
			int columnSpan = setTFColumnSpan ? AlphaNumKeys.COLUMN_COUNT : 1;
			GridPane.setConstraints(tf, tfColumn, row, columnSpan, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);

			// Textfeld der GridPane hinzufügen
			center.getChildren().add(tf);

			// Textfeld mit Schlüssel in Map speichern
			getTextFieldMap().put(tfKeys[row], tf);
		}
	}

	
// Labels	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle Labels aus den Strings im Array {@link #labelTexts} und positioniert diese in der GridPane.
	 */
	private void createLabels() {
		ArrayDeque<Node> labels = new ArrayDeque<>();

		for (int i = firstLabelRow; i < firstLabelRow + labelTexts.length; i++) {
			labels.add(new Label(labelTexts[i]));
		}

		FXUtils.setGridConstraints(labelColumn, firstLabelRow, 1, 0, labels);
		center.getChildren().addAll(labels);
	}

	
// Buttons	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<	<<

	/** <!-- $LANGUAGE=DE -->
	 * Erstellt alle benötigten Buttons und positioniert diese in der GridPane.
	 * 
	 * @see #createButtons()
	 * @see AlphaNumKeys
	 */
	private void createButtonMatrix() {
		// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach rechts-unten in der Tabelle!)
		ArrayDeque<Button> buttonList = createButtons();

		if (useClearAndBackBtn) {
			// Constraints für Position in der Tabelle setzen und in die Tabelle legen
			FXUtils.setGridConstraints(firstKeyBtnColumn, firstKeyBtnRow, AlphaNumKeys.COLUMN_COUNT, 2, buttonList, center::add);
		}
		
		// Tastaturfeld in die Tabelle legen (Constraints werden bereits in AlphaNumKeys gesetzt!)
		center.getChildren().addAll(alphaNum.getButtonMatrix());

		getButtonMap().putAll(alphaNum.getButtonMap());

		// Maximale Größe der Buttons setzen
		FXUtils.setMaxSizes(buttonList, Double.MAX_VALUE);
		FXUtils.setMaxSizes(alphaNum.getButtonMatrix(), Double.MAX_VALUE);
	}

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt die Buttons {@link #clearBtnKey} und {@link #backspaceBtnKey} und gibt diese in einer ArrayList zurück.
	 * Alle Buttons werden auch in der Map für Buttons abgelegt.
	 * <p><b>
	 * Alle Buttons werden als Instanz von {@link UnfocusedButton} angelegt.
	 * </b></p>
	 * 
	 * @return Liste der erstellten Buttons
	 */
	private ArrayDeque<Button> createButtons() {
		// Liste mit allen Buttons in richtiger Reihenfolge (oben-links nach rechts-unten in der Tabelle!)
		ArrayDeque<Button> buttons = new ArrayDeque<>(2);

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

	/** <!-- $LANGUAGE=DE -->
	 * Bindet die Properties dieser AlphaNumGridView bidirektional an korrespondierende Properties der enthaltenen
	 * Objekte. Setzt zudem auch Listener, um die Paddings der GridPane bei Änderung der Padding-Properties anzupassen.
	 * 
	 * @see #updatePadding(ObservableValue, Number, Number)
	 */
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

	/** <!-- $LANGUAGE=DE -->
	 * Methode, die als Referenz für einen ChangeListener eingesetzt werden kann, um die Paddings der GridPane zu aktualisieren.
	 * 
	 * @param observable	{@code ObservableValue}, der sich ändert
	 * @param oldValue		alter Wert
	 * @param newValue		neuer Wert
	 */
	private void updatePadding(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		center.setPadding(new Insets(paddingTopProperty.get(), paddingRigthProperty.get() + firstColumnWidthProperty.get(),
						paddingBottomProperty.get(), paddingLeftProperty.get()));
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	nested Classes   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Aufzählung von möglichen Typen für Constraints einer GridPane
	 * 
	 * @author Tim
	 *
	 * @since Bitchanger 0.1.4
	 * @version 0.1.4
	 */
	protected enum ConstraintType{
		/** <!-- $LANGUAGE=DE -->	Konstante für eine Zeile, die Textfelder enthält */
		TEXT_FIELD_ROW,
		
		/** <!-- $LANGUAGE=DE -->	Konstante für eine Zeile, die Buttons enthält */
		BUTTON_ROW,
		
		/** <!-- $LANGUAGE=DE -->	Konstante für eine Zeile, die Weißraum enthält */
		WHITESPACE_ROW,
		
		/** <!-- $LANGUAGE=DE -->	Konstante für die erste Spalte in einer GridPane */
		FIRST_COLUMN,
		
		/** <!-- $LANGUAGE=DE -->	Konstante für eine Spalte, die Buttons enthält */
		BUTTON_COLUMN;
	}
	
	
}


