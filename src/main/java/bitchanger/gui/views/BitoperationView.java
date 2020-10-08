/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
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
 * View, die die Scene f\u00FCr die Bitoperationen mit verschiedenen Zahlensystemen enth\u00E4lt.
 * <p><b>
 * F\u00FCr diese View-Klasse wird der Controller {@link BitoperationController} registriert.
 * </b></p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
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
	
	private static final int EQUAL_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 2;
	private static final int EQUAL_BTN_ROW = 3;
	
	private static final int BACKSPACE_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 1;
	private static final int BACKSPACE_BTN_ROW = 0;
	
	private static final int CLEAR_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 2;
	private static final int CLEAR_BTN_ROW = 1;
	
	
	
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
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr die logische UND-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr die logische UND-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String andBtnKey() {
		return "logic-and-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr die logische ODER-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr die logische ODER-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String orBtnKey() {
		return "logic-or-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr die logische NICHT-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr die logische NICHT-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String notBtnKey() {
		return "logic-not-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr die logische NAND-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr die logische NAND-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String nandBtnKey() {
		return "logic-nand-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr die logische NOR-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr die logische NOR-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String norBtnKey() {
		return "logic-nor-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr die logische Exklusiv-Oder-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr die logische Exklusiv-Oder-Verkn\u00FCpfung in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String xorBtnKey() {
		return "logic-xor-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr Rechtsshift in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr Rechtsshift in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String shiftLeftBtnKey() {
		return "shift-left-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button f\u00FCr Linksshift in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button f\u00FCr Linksshift in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String shiftRightBtnKey() {
		return "shift-right-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem die ComboBox f\u00FCr die Anzahl der Bits in der Map {@code nodeMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem die ComboBox f\u00FCr die Anzahl der Bits in der Map {@code nodeMap} gespeichert wird
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
		
		FXUtils.setGridConstraints(AlphaNumKeys.COLUMN_COUNT, 1, 2, 0, logicNodes);
		FXUtils.setMaxSizes(logicNodes, Double.MAX_VALUE);
		buttonGrid.getChildren().addAll(logicNodes);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void createBitLength() {
		ComboBox<BitLength> bitLength = new ComboBox<>();
		bitLength.getItems().addAll(BitLength._8_BIT, BitLength._16_BIT, BitLength._32_BIT, BitLength._64_BIT);
		
		bitLength.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		GridPane.setColumnSpan(bitLength, 3);
		GridPane.setConstraints(bitLength, AlphaNumKeys.COLUMN_COUNT - 2, 0);
		
		this.getNodeMap().put(bitLengthKey(), bitLength);
		
		buttonGrid.getChildren().add(bitLength);
	}

}













