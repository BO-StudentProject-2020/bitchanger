/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayDeque;

import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.util.FXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**	<!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 * 
 */
//TODO JavaDoc
public class CalculationViewBase extends AlphaNumGridView {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	private static final String TF_KEY = "textField";
	
	
	
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
	
	
	// TODO JavaDoc
	private Label equals2Label;
	private HBox firstValBox;
	private HBox secondValBox;
	private StackPane resultLabels;
	private final int LABEL_SPACING = 10;

	protected final int equalBtnColumn;

	protected final int equalBtnRow;

	protected final int equalBtnColumnSpan;

	protected final int equalBtnRowSpan;

	protected final int backspaceBtnColumn;

	protected final int backspaceBtnRow;

	protected final int backspaceBtnColumnSpan;

	protected final int backspaceBtnRowSpan;

	protected final int clearBtnColumn;

	protected final int clearBtnRow;

	protected final int clearBtnColumnSpan;

	protected final int clearBtnRowSpan;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

//	/** <!-- $LANGUAGE=DE -->
//	 * Erzeugt eine neue CalculatorView mit vollständigem Scenegraphen und initialisiert die Funktionen
//	 * der Bedienelemente mit einem {@link CalculatorController}.
//	 */
	// TODO JavaDoc
	public CalculationViewBase() {
		this(6);
	}
	
	// TODO JavaDoc
	public CalculationViewBase(int firstKeyBtnRow) {
		this(firstKeyBtnRow, 1 + AlphaNumKeys.COLUMN_COUNT, firstKeyBtnRow + 3, 
			AlphaNumKeys.COLUMN_COUNT, firstKeyBtnRow, 1 + AlphaNumKeys.COLUMN_COUNT, firstKeyBtnRow + 1);
	}
	
	public CalculationViewBase(int firstKeyBtnRow, int equalBtnColumn, int equalBtnRow, 
				int backspaceBtnColumn, int backspaceBtnRow, int clearBtnColumn, int clearBtnRow) {
		this(firstKeyBtnRow, equalBtnColumn, equalBtnRow, 1, 2, backspaceBtnColumn, backspaceBtnRow, 2, 1, clearBtnColumn, clearBtnRow, 1, 2);
	}
	
	public CalculationViewBase(int firstKeyBtnRow, int equalBtnColumn, int equalBtnRow, int equalBtnColumnSpan, int equalBtnRowSpan, 
				int backspaceBtnColumn, int backspaceBtnRow, int backspaceBtnColumnSpan, int backspaceBtnRowSpan, int clearBtnColumn, 
				int clearBtnRow, int clearBtnColumnSpan, int clearBtnRowSpan) {
		super(0, 0, 1, 1, firstKeyBtnRow, 1, null, TF_KEY);
		
		this.equalBtnColumn = equalBtnColumn;
		this.equalBtnRow = equalBtnRow;
		this.equalBtnColumnSpan = equalBtnColumnSpan;
		this.equalBtnRowSpan = equalBtnRowSpan;
		this.backspaceBtnColumn = backspaceBtnColumn;
		this.backspaceBtnRow = backspaceBtnRow;
		this.backspaceBtnColumnSpan = backspaceBtnColumnSpan;
		this.backspaceBtnRowSpan = backspaceBtnRowSpan;
		this.clearBtnColumn = clearBtnColumn;
		this.clearBtnRow = clearBtnRow;
		this.clearBtnColumnSpan = clearBtnColumnSpan;
		this.clearBtnRowSpan = clearBtnRowSpan;
	}

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem das Textfeld in der Map {@code tfMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld in der Map {@code tfMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String tfKey() {
		return TF_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Gleichheits-Button in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Gleichheits-Button in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String equalsBtnKey() {
		return "equals-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Hexadezimalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Hexadezimalsystem in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String hexBtnKey() {
		return "hex-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Dezimalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Dezimalsystem in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String decBtnKey() {
		return "dec-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Oktalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Oktalsystem in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String octBtnKey() {
		return "oct-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Binärsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Binärsystem in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String binBtnKey() {
		return "bin-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der BaseSpinner für das beliebige Zahlensystem in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der BaseSpinner für das beliebige Zahlensystem in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String baseSpinnerKey() {
		return "base-spinner";
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem das Label zur Anzeige der Basis in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label zur Anzeige der Basis in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String baseLabelKey() {
		return "base-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem das Label für den ersten Wert in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für den ersten Wert in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String firstValueLabelKey() {
		return "1st-val-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem das Label für den Operator in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für den Operator in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String operationLabelKey() {
		return "operation-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem das Label für den zweiten Wert in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für den zweiten Wert in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String secondValueLabelKey() {
		return "2nd-val-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem das Label für das Gleichheitszeichen in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für das Gleichheitszeichen in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String equalsLabelKey() {
		return "equals-label";
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** {@inheritDoc} */
	@Override
	protected void createScenegraph() {
		super.createScenegraph();
		
		GridPane.setColumnSpan(this.getTextFieldMap().get(TF_KEY), equalBtnColumn + equalBtnColumnSpan - GridPane.getColumnIndex(this.getTextFieldMap().get(TF_KEY)));
		
		createButtons();
		createBaseButtons();
		createLabels();
		
		addColumnConstraint(6, ConstraintType.BUTTON_COLUMN);
		addColumnConstraint(7, ConstraintType.BUTTON_COLUMN);
		addRowConstraint(firstTFRow - 1, ConstraintType.BUTTON_ROW);
		addRowConstraint(firstTFRow + 1, ConstraintType.TEXT_FIELD_ROW);
		addRowConstraint(firstKeyBtnRow - 1, ConstraintType.BUTTON_ROW);
	}


	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// TODO JavaDoc
	private void createButtons() {
		// Gleich-Button
		Button equalsBtn = new UnfocusedButton("=");
		equalsBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		GridPane.setConstraints(equalsBtn, equalBtnColumn, equalBtnRow);
		GridPane.setColumnSpan(equalsBtn, equalBtnColumnSpan);
		GridPane.setRowSpan(equalsBtn, equalBtnRowSpan);
		
		this.getButtonMap().put(equalsBtnKey(), equalsBtn);
		center.getChildren().add(equalsBtn);
		
		// Backspace Button
		Button backspaceBtn = getButtonMap().get(backspaceBtnKey());
		GridPane.setConstraints(backspaceBtn, backspaceBtnColumn, backspaceBtnRow);
		GridPane.setColumnSpan(backspaceBtn, backspaceBtnColumnSpan);
		GridPane.setRowSpan(backspaceBtn, backspaceBtnRowSpan);
		
		// Clear Button
		Button clearBtn = getButtonMap().get(clearBtnKey());
		GridPane.setConstraints(clearBtn, clearBtnColumn, clearBtnRow);
		GridPane.setColumnSpan(clearBtn, clearBtnColumnSpan);
		GridPane.setRowSpan(clearBtn, clearBtnRowSpan);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void createBaseButtons() {
		ArrayDeque<Node> baseNodes = new ArrayDeque<>();
		
		String[] texts = {"HEX", "DEC", "OCT", "BIN"};
		String[] keys = {hexBtnKey(), decBtnKey(), octBtnKey(), binBtnKey()};
		
		for (int i = 0; i < keys.length; i++) {
			UnfocusedButton b = new UnfocusedButton(texts[i]);
			this.getButtonMap().put(keys[i], b);
			baseNodes.add(b);
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		
		BaseSpinner anyBase = new BaseSpinner();
		anyBase.setMaxHeight(Double.MAX_VALUE);
		this.getNodeMap().put(baseSpinnerKey(), anyBase);
		baseNodes.add(anyBase);
		
		FXUtils.setGridConstraints(0, firstKeyBtnRow, 1, 0, baseNodes);
		center.getChildren().addAll(baseNodes);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void createLabels() {
		Label firstValueLabel = new Label();
		Label operationLabel = new Label();
		Label secondValueLabel = new Label();
		Label equalsLabel = new Label();
		equals2Label = new Label();
		
		equals2Label.textProperty().bind(equalsLabel.textProperty());
		equals2Label.setVisible(false);
		
		Label baseLabel = new Label();
		
		this.getNodeMap().put(firstValueLabelKey(), firstValueLabel);
		this.getNodeMap().put(operationLabelKey(), operationLabel);
		this.getNodeMap().put(secondValueLabelKey(), secondValueLabel);
		this.getNodeMap().put(equalsLabelKey(), equalsLabel);
		this.getNodeMap().put(baseLabelKey(), baseLabel);
		
		firstValBox = new HBox(firstValueLabel);
		secondValBox = new HBox(operationLabel, secondValueLabel, equalsLabel);
		resultLabels = new StackPane();
		
		formatHBox(firstValBox);
		formatHBox(secondValBox);
		
		GridPane.setConstraints(resultLabels, tfColumn, firstTFRow - 1, GridPane.getColumnSpan(this.getTextFieldMap().get(TF_KEY)), 1, HPos.RIGHT, VPos.CENTER, Priority.NEVER, Priority.NEVER, null);
		
		GridPane.setConstraints(baseLabel, GridPane.getColumnIndex(this.getTextFieldMap().get(TF_KEY)) + GridPane.getColumnSpan(this.getTextFieldMap().get(TF_KEY)), firstTFRow);
		GridPane.setFillHeight(baseLabel, false);
		GridPane.setValignment(baseLabel, VPos.BOTTOM);
		
		baseLabel.setId("base-label");
		
		center.getChildren().addAll(resultLabels, baseLabel);
		positionValuesVertical();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void formatHBox(HBox box) {
		box.setAlignment(Pos.CENTER_RIGHT);
		box.setSpacing(LABEL_SPACING);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public void positionValuesVertical() {
		resultLabels.getChildren().clear();
		try {
			firstValBox.getChildren().add(equals2Label);
		} catch (Exception e) { /* ignore */ }
		
		VBox box = new VBox(firstValBox, secondValBox);
		box.setSpacing(this.vgapProperty.get());
		
		resultLabels.getChildren().add(box);
		addRowConstraint(firstTFRow - 1, createRowConstraintsFromNode(resultLabels));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public void positionValuesHorizontal() {
		resultLabels.getChildren().clear();
		try {
			firstValBox.getChildren().remove(equals2Label);
		} catch (Exception e) { /* Ignore */ }
		
		HBox box = new HBox(firstValBox, secondValBox);
		formatHBox(box);
		
		resultLabels.getChildren().add(box);
		addRowConstraint(GridPane.getRowIndex(resultLabels), createRowConstraintsFromNode(resultLabels));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private RowConstraints createRowConstraintsFromNode(Region n) {
		RowConstraints rowc = new RowConstraints();
		rowc.minHeightProperty().bind(n.minHeightProperty());
		rowc.maxHeightProperty().bind(n.maxHeightProperty());
		rowc.prefHeightProperty().bind(n.prefHeightProperty());

		return rowc;
	}
	
	
}












