/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayDeque;

import bitchanger.calculations.BitLength;
import bitchanger.gui.controller.BitoperationController;
import bitchanger.gui.controller.CalculatorController;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.BitoperationMenuBar;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.util.FXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**	<!-- $LANGUAGE=DE -->
 * View, die die Scene für die Bitoperationen mit verschiedenen Zahlensystemen enthält.
 * <p><b>
 * Für diese View-Klasse wird der Controller {@link BitoperationController} registriert.
 * </b></p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 * 
 * @see BitoperationController
 */
//TODO JavaDoc
public class BitoperationView extends AlphaNumGridView {
	
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
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(BitoperationView.class, BitoperationController.class);
	}
	
	
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
	public BitoperationView() {
		super(0, 0, 1, 1, 3, 1, null, TF_KEY);
		buildScenegraph();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	public ArrayDeque<Node> getLogicNodes(){
		ArrayDeque<Node> list = new ArrayDeque<>();
		
		list.add(this.getButtonMap().get(andBtnKey()));
		list.add(this.getButtonMap().get(nandBtnKey()));
		list.add(this.getButtonMap().get(orBtnKey()));
		list.add(this.getButtonMap().get(norBtnKey()));
		list.add(this.getButtonMap().get(notBtnKey()));
		list.add(this.getButtonMap().get(xorBtnKey()));
		list.add(this.getButtonMap().get(shiftLeftBtnKey()));
		list.add(this.getButtonMap().get(shiftRightBtnKey()));
		
		return list;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
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
	 * Gibt das Schlüsselwort zurück, mit dem der Button für die logische UND-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für die logische UND-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String andBtnKey() {
		return "logic-and-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für die logische ODER-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für die logische ODER-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String orBtnKey() {
		return "logic-or-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für die logische NICHT-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für die logische NICHT-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String notBtnKey() {
		return "logic-not-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für die logische NAND-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für die logische NAND-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String nandBtnKey() {
		return "logic-nand-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für die logische NOR-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für die logische NOR-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String norBtnKey() {
		return "logic-nor-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für die logische Exklusiv-Oder-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für die logische Exklusiv-Oder-Verknüpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String xorBtnKey() {
		return "logic-xor-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für Rechtsshift in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für Rechtsshift in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String shiftLeftBtnKey() {
		return "shift-left-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem der Button für Linksshift in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem der Button für Linksshift in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String shiftRightBtnKey() {
		return "shift-right-btn";
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
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem die ComboBox für die Anzahl der Bits in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem die ComboBox für die Anzahl der Bits in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String bitLengthKey() {
		return "bit-length-combo-box";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort zurück, mit dem die CheckBox für vorzeichenlose Bitoperationen in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem die CheckBox für vorzeichenlose Bitoperationen in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String unsignedBitoperationsKey() {
		return "unsigned-bit-operations-checkbox";
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** {@inheritDoc} */
	@Override
	public BasicMenuBar generateMenuBar(ControllableApplication controllableApp) {
		try {
			return new BitoperationMenuBar(controllableApp);
		} catch (NullPointerException e) {
			return generateMenuBar();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public BasicMenuBar generateMenuBar() {
		return new BitoperationMenuBar();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	protected void createScenegraph() {
		super.createScenegraph();
		
		GridPane.setColumnSpan(this.getTextFieldMap().get(TF_KEY), GridPane.getColumnSpan(this.getTextFieldMap().get(TF_KEY)) + 3);
		
		createBaseButtons();
		createBitOperators();
		createBitLength();
		createLabels();
		moveButtons();
		
		addColumnConstraint(6, ConstraintType.BUTTON_COLUMN);
		addColumnConstraint(7, ConstraintType.BUTTON_COLUMN);
		addColumnConstraint(8, ConstraintType.BUTTON_COLUMN);
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
	private void createBitOperators() {
		String[] texts = {"AND", "OR", "NOT", "NAND", "NOR", "XOR", "<<", ">>"};
		String[] keys = {andBtnKey(), orBtnKey(), notBtnKey(), nandBtnKey(), norBtnKey(), xorBtnKey(), shiftLeftBtnKey(), shiftRightBtnKey()};
		
		for (int i = 0; i < keys.length; i++) {
			UnfocusedButton b = new UnfocusedButton(texts[i]);
			this.getButtonMap().put(keys[i], b);
		}
		
		ArrayDeque<Node> logicNodes = getLogicNodes();
		
		FXUtils.setGridConstraints(firstKeyBtnColumn + AlphaNumKeys.COLUMN_COUNT, firstKeyBtnRow + 1, 2, 0, logicNodes);
		FXUtils.setMaxSizes(logicNodes, Double.MAX_VALUE);
		center.getChildren().addAll(logicNodes);
		
		Button equalsBtn = new UnfocusedButton("=");
		this.getButtonMap().put(equalsBtnKey(), equalsBtn);
		GridPane.setRowSpan(this.getButtonMap().get(equalsBtnKey()), 2);
		GridPane.setConstraints(equalsBtn, firstKeyBtnColumn + AlphaNumKeys.COLUMN_COUNT + 2, firstKeyBtnRow + 3);
		equalsBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		center.getChildren().add(equalsBtn);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void createBitLength() {
		ComboBox<BitLength> bitLength = new ComboBox<>();
		bitLength.getItems().addAll(BitLength._8_BIT, BitLength._16_BIT, BitLength._32_BIT, BitLength._64_BIT);
		
		bitLength.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		GridPane.setColumnSpan(bitLength, 2);
		GridPane.setConstraints(bitLength, firstKeyBtnColumn + AlphaNumKeys.COLUMN_COUNT - 1, firstKeyBtnRow);
		
		this.getNodeMap().put(bitLengthKey(), bitLength);
		
		center.getChildren().add(bitLength);
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
	
	
	private void moveButtons() {
		// Backspace Button verschieben
		GridPane.setColumnIndex(this.getButtonMap().get(backspaceBtnKey()), GridPane.getColumnIndex(this.getButtonMap().get(backspaceBtnKey())) + 3);
		
		// Clear Button verschieben
		GridPane.setRowSpan(this.getButtonMap().get(clearBtnKey()), 2);
		GridPane.setConstraints(this.getButtonMap().get(clearBtnKey()), firstKeyBtnColumn + AlphaNumKeys.COLUMN_COUNT + 2, firstKeyBtnRow + 1);
		
		// +/- Button ausblenden und entfernen
		this.getButtonMap().get(signBtnKey()).setVisible(false);
		try { center.getChildren().remove(this.getButtonMap().get(signBtnKey())); } catch (Exception e) { /* ignore */ }
		
		// 0 Button vergrößern
		GridPane.setColumnSpan(this.getButtonMap().get(zeroBtnKey()), 2);
		GridPane.setColumnIndex(this.getButtonMap().get(zeroBtnKey()), GridPane.getColumnIndex(this.getButtonMap().get(zeroBtnKey())) - 1);
	}
	
}













