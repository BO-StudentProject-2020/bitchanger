/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
<<<<<<< HEAD
 * Entwickelt für das AID-Labor der Hochschule Bochum
=======
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
>>>>>>> master
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayDeque;

import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.UnfocusedButton;
<<<<<<< HEAD
=======
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
>>>>>>> master
import bitchanger.util.FXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**	<!-- $LANGUAGE=DE -->
 * 
 * 
<<<<<<< HEAD
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
=======
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.8
>>>>>>> master
 * 
 */
//TODO JavaDoc
public class CalculationViewBase extends AlphaNumGridView {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
<<<<<<< HEAD
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld in der Map {@code tfMap} gespeichert wird */
=======
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit das Textfeld in der Map {@code tfMap} gespeichert wird */
>>>>>>> master
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
	
	
// protected	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	// TODO JavaDoc
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

	
// private	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	// TODO JavaDoc
	private HBox firstValBox;
	private HBox secondValBox;
	private StackPane resultLabels;
	private final int LABEL_SPACING = 10;
	private boolean isHorizontal;
	private boolean isVertical;

	
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
		
		this.paddingRigthProperty.bind(paddingLeftProperty);
	}

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem das Textfeld in der Map {@code tfMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld in der Map {@code tfMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem das Textfeld in der Map {@code tfMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem das Textfeld in der Map {@code tfMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String tfKey() {
		return TF_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem der Gleichheits-Button in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Gleichheits-Button in der Map {@code btnMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Gleichheits-Button in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Gleichheits-Button in der Map {@code btnMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String equalsBtnKey() {
		return "equals-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Hexadezimalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Hexadezimalsystem in der Map {@code btnMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr das Hexadezimalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr das Hexadezimalsystem in der Map {@code btnMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String hexBtnKey() {
		return "hex-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Dezimalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Dezimalsystem in der Map {@code btnMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr das Dezimalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr das Dezimalsystem in der Map {@code btnMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String decBtnKey() {
		return "dec-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Oktalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Oktalsystem in der Map {@code btnMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr das Oktalsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr das Oktalsystem in der Map {@code btnMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String octBtnKey() {
		return "oct-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem der Button für das Binärsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für das Binärsystem in der Map {@code btnMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr das Bin\u00E4rsystem in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr das Bin\u00E4rsystem in der Map {@code btnMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String binBtnKey() {
		return "bin-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem der BaseSpinner für das beliebige Zahlensystem in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der BaseSpinner für das beliebige Zahlensystem in der Map {@code nodeMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der BaseSpinner f\u00FCr das beliebige Zahlensystem in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der BaseSpinner f\u00FCr das beliebige Zahlensystem in der Map {@code nodeMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String baseSpinnerKey() {
		return "base-spinner";
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem das Label zur Anzeige der Basis in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label zur Anzeige der Basis in der Map {@code nodeMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem das Label zur Anzeige der Basis in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem das Label zur Anzeige der Basis in der Map {@code nodeMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String baseLabelKey() {
		return "base-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem das Label für den ersten Wert in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für den ersten Wert in der Map {@code nodeMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem das Label f\u00FCr den ersten Wert in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem das Label f\u00FCr den ersten Wert in der Map {@code nodeMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String firstValueLabelKey() {
		return "1st-val-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem das Label für den Operator in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für den Operator in der Map {@code nodeMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem das Label f\u00FCr den Operator in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem das Label f\u00FCr den Operator in der Map {@code nodeMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String operationLabelKey() {
		return "operation-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem das Label für den zweiten Wert in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für den zweiten Wert in der Map {@code nodeMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem das Label f\u00FCr den zweiten Wert in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem das Label f\u00FCr den zweiten Wert in der Map {@code nodeMap} gespeichert wird
>>>>>>> master
	 */
	// TODO JavaDoc EN
	public final String secondValueLabelKey() {
		return "2nd-val-label";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
<<<<<<< HEAD
	 * Gibt das Schlüsselwort zurück, mit dem das Label für das Gleichheitszeichen in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem das Label für das Gleichheitszeichen in der Map {@code nodeMap} gespeichert wird
=======
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem das Label f\u00FCr das Gleichheitszeichen in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem das Label f\u00FCr das Gleichheitszeichen in der Map {@code nodeMap} gespeichert wird
>>>>>>> master
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
	
	@Override
	protected void init() {
		super.init();
		
		this.isHorizontal = false;
		this.isVertical = false;
	}
	
	
	/** {@inheritDoc} */
	@Override
	protected void createScenegraph() {
		super.createScenegraph();
		
		TextField tf = this.getTextFieldMap().get(TF_KEY);
		tf.setAlignment(Pos.CENTER_RIGHT);
		
		createButtons();
		createBaseButtons();
		createLabels();
		
		addRowConstraint(center, firstTFRow - 1, ConstraintType.BUTTON_ROW);
		addRowConstraint(center, GridPane.getRowIndex(tf), ConstraintType.TEXT_FIELD_ROW);
		
		ColumnConstraints lastRow = new ColumnConstraints();
		lastRow.minWidthProperty().bind(firstColumnWidthProperty);
		lastRow.maxWidthProperty().bind(firstColumnWidthProperty);
		
		addColumnConstraint(center, center.getColumnCount() - 1, lastRow);
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
		buttonGrid.getChildren().add(equalsBtn);
		
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
		
		Label baseLabel = new Label();
		
		this.getNodeMap().put(firstValueLabelKey(), firstValueLabel);
		this.getNodeMap().put(operationLabelKey(), operationLabel);
		this.getNodeMap().put(secondValueLabelKey(), secondValueLabel);
		this.getNodeMap().put(equalsLabelKey(), equalsLabel);
		this.getNodeMap().put(baseLabelKey(), baseLabel);
		
		firstValBox = new HBox(firstValueLabel);
		secondValBox = new HBox(operationLabel, secondValueLabel, equalsLabel);
		resultLabels = new StackPane();
		resultLabels.getStyleClass().add("result-text");
		
		formatHBox(firstValBox);
		formatHBox(secondValBox);
		
		GridPane.setConstraints(resultLabels, tfColumn, firstTFRow - 1, GridPane.getColumnSpan(this.getTextFieldMap().get(TF_KEY)), 1, HPos.RIGHT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES, null);
		
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
		if(isVertical) {
			return;
		}
		
		resultLabels.getChildren().clear();
		try {
			Label equalsLabel = (Label) this.getNodeMap().get(equalsLabelKey());
			secondValBox.getChildren().remove(equalsLabel);
			GridPane.setConstraints(equalsLabel, GridPane.getColumnIndex(this.getNodeMap().get(this.baseLabelKey())), GridPane.getRowIndex(this.getNodeMap().get(this.baseLabelKey())) - 1);
			GridPane.setValignment(equalsLabel, VPos.BOTTOM);
			center.getChildren().add(equalsLabel);
<<<<<<< HEAD
		} catch (Exception e) { /* ignore */ }
=======
		} catch (Exception e) {
			/* ignore */
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
		}
>>>>>>> master
		
		VBox box = new VBox(firstValBox, secondValBox);
		box.setSpacing(this.vgapProperty.get());
		
		getTextFieldMap().get(this.tfKey()).setId("BIN-RESULT");
		
		resultLabels.getChildren().add(box);
		
		RowConstraints firstRow = createRowConstraints(ConstraintType.TEXT_FIELD_ROW);
		firstRow.minHeightProperty().bind(box.heightProperty());
		firstRow.maxHeightProperty().bind(box.heightProperty());
		addRowConstraint(center, GridPane.getRowIndex(resultLabels), firstRow);
		
		isVertical = true;
		isHorizontal = false;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public void positionValuesHorizontal() {
		if (isHorizontal) {
			return;
		}
		
		resultLabels.getChildren().clear();
		try {
			Label equalsLabel = (Label) this.getNodeMap().get(equalsLabelKey());
			center.getChildren().remove(equalsLabel);
			secondValBox.getChildren().add(equalsLabel);
<<<<<<< HEAD
		} catch (Exception e) { /* Ignore */ }
=======
		} catch (Exception e) {
			/* Ignore */
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
		}
>>>>>>> master
		
		HBox box = new HBox(firstValBox, secondValBox);
		formatHBox(box);
		
		getTextFieldMap().get(this.tfKey()).setId(null);
		
		resultLabels.getChildren().add(box);
		
		RowConstraints firstRow = createRowConstraints(ConstraintType.TEXT_FIELD_ROW);
		firstRow.minHeightProperty().bind(box.heightProperty());
		firstRow.maxHeightProperty().bind(box.heightProperty());
		addRowConstraint(center, GridPane.getRowIndex(resultLabels), firstRow);
		
		isVertical = false;
		isHorizontal = true;
	}

	
}












