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
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.BitoperationMenuBar;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.util.FXUtils;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

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
public class BitoperationView extends CalculationViewBase {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	//TODO JavaDoc
	private static final int FIRST_KEY_BTN_ROW = 3;
	
	private static final int EQUAL_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 3;
	private static final int EQUAL_BTN_ROW = FIRST_KEY_BTN_ROW + 3;
	
	private static final int BACKSPACE_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 2;
	private static final int BACKSPACE_BTN_ROW = FIRST_KEY_BTN_ROW;
	
	private static final int CLEAR_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 3;
	private static final int CLEAR_BTN_ROW = FIRST_KEY_BTN_ROW + 1;
	
	
	
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
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

//	/** <!-- $LANGUAGE=DE -->
//	 * Erzeugt eine neue CalculatorView mit vollständigem Scenegraphen und initialisiert die Funktionen
//	 * der Bedienelemente mit einem {@link CalculatorController}.
//	 */
	// TODO JavaDoc
	public BitoperationView() {
		super(FIRST_KEY_BTN_ROW, EQUAL_BTN_COLUMN, EQUAL_BTN_ROW, BACKSPACE_BTN_COLUMN, BACKSPACE_BTN_ROW, CLEAR_BTN_COLUMN, CLEAR_BTN_ROW);
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
	 * Gibt das Schlüsselwort zurück, mit dem die ComboBox für die Anzahl der Bits in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schlüsselwort, mit dem die ComboBox für die Anzahl der Bits in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String bitLengthKey() {
		return "bit-length-combo-box";
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
		
		createBitOperators();
		createBitLength();
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
	private void moveButtons() {
		// +/- Button ausblenden und entfernen
		this.getButtonMap().get(signBtnKey()).setVisible(false);
		try { center.getChildren().remove(this.getButtonMap().get(signBtnKey())); } catch (Exception e) { /* ignore */ }
		
		// 0 Button vergrößern
		GridPane.setColumnSpan(this.getButtonMap().get(zeroBtnKey()), 2);
		GridPane.setColumnIndex(this.getButtonMap().get(zeroBtnKey()), GridPane.getColumnIndex(this.getButtonMap().get(zeroBtnKey())) - 1);
	}
	
}













