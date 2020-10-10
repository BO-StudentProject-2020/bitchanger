/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.util.ArrayDeque;

import bitchanger.gui.controller.CalculatorController;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.ConverterMenuBar;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.util.FXUtils;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**	<!-- $LANGUAGE=DE -->
 * View, die die Scene f\u00FCr die Berechnungen von verschiedenen Zahlensystemen enth\u00E4lt.
 * <p><b>
 * F\u00FCr diese View-Klasse wird der Controller {@link CalculatorController} registriert.
 * </b></p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.8
 * 
 * @see CalculatorController
 */
//TODO JavaDoc
public class CalculatorView extends CalculationViewBase {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	//TODO JavaDoc
	private static final int FIRST_KEY_BTN_ROW = 3;
	
	private static final int EQUAL_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 1;
	private static final int EQUAL_BTN_ROW = 3;
	private static final int EQUAL_BTN_COLUMN_SPAN = 1;
	private static final int EQUAL_BTN_ROW_SPAN = 2;
	
	private static final int BACKSPACE_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT;
	private static final int BACKSPACE_BTN_ROW = 0;
	private static final int BACKSPACE_BTN_COLUMN_SPAN = 2;
	private static final int BACKSPACE_BTN_ROW_SPAN = 1;
	
	private static final int CLEAR_BTN_COLUMN = AlphaNumKeys.COLUMN_COUNT + 1;
	private static final int CLEAR_BTN_ROW = 1;
	private static final int CLEAR_BTN_COLUMN_SPAN = 1;
	private static final int CLEAR_BTN_ROW_SPAN = 1;
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(CalculatorView.class, CalculatorController.class);
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
	public CalculatorView() {
		super(FIRST_KEY_BTN_ROW, EQUAL_BTN_COLUMN, EQUAL_BTN_ROW, EQUAL_BTN_COLUMN_SPAN, EQUAL_BTN_ROW_SPAN, BACKSPACE_BTN_COLUMN, 
					BACKSPACE_BTN_ROW, BACKSPACE_BTN_COLUMN_SPAN, BACKSPACE_BTN_ROW_SPAN, CLEAR_BTN_COLUMN, CLEAR_BTN_ROW, 
					CLEAR_BTN_COLUMN_SPAN, CLEAR_BTN_ROW_SPAN);
		
		buildScenegraph();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button zum Dividieren in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button zum Dividieren in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String divideBtnKey() {
		return "divide-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button zum Multiplizieren in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button zum Multiplizieren in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String multiplyBtnKey() {
		return "multiply-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button zum Subtrahieren in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button zum Subtrahieren in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String subtractBtnKey() {
		return "sub-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Button zum Addieren in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Button zum Addieren in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String addBtnKey() {
		return "add-btn";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schl\u00FCsselwort zur\u00FCck, mit dem der Modulo-Button in der Map {@code btnMap} gespeichert wird
	 * 
	 * @return	Schl\u00FCsselwort, mit dem der Modulo-Button in der Map {@code btnMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String moduloBtnKey() {
		return "mod-btn";
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
			return new ConverterMenuBar(controllableApp);
		} catch (NullPointerException e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.MEDIUM, e);
			return generateMenuBar();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public BasicMenuBar generateMenuBar() {
		return new ConverterMenuBar();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	protected void createScenegraph() {
		super.createScenegraph();
		
		createArithmeticOperators();
	}


	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// TODO JavaDoc 0.1.7
	private void createArithmeticOperators() {
		createArithmeticButtons();
		
		String[] arithmeticBtnKeys = {divideBtnKey(), multiplyBtnKey(), subtractBtnKey(), addBtnKey()};
		
		ArrayDeque<Button> arithmeticButtons = new ArrayDeque<>();
		
		for (String key : arithmeticBtnKeys) {
			arithmeticButtons.add(this.getButtonMap().get(key));
		}
		
		FXUtils.setGridConstraints(AlphaNumKeys.COLUMN_COUNT, 1, 1, 0, arithmeticButtons);
		FXUtils.setMaxSizes(arithmeticButtons, Double.MAX_VALUE);
		
		buttonGrid.getChildren().addAll(arithmeticButtons);

		Button modBtn = getButtonMap().get(moduloBtnKey());
		GridPane.setConstraints(modBtn, EQUAL_BTN_COLUMN, 2);
		modBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		buttonGrid.getChildren().add(modBtn);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc 0.1.7
	private void createArithmeticButtons() {
		UnfocusedButton divBtn = new UnfocusedButton("/");
		UnfocusedButton multBtn = new UnfocusedButton("*");
		UnfocusedButton subBtn = new UnfocusedButton("-");
		UnfocusedButton addBtn = new UnfocusedButton("+");
		UnfocusedButton modBtn = new UnfocusedButton("%");
		
		this.getButtonMap().put(divideBtnKey(), divBtn);
		this.getButtonMap().put(multiplyBtnKey(), multBtn);
		this.getButtonMap().put(subtractBtnKey(), subBtn);
		this.getButtonMap().put(addBtnKey(), addBtn);
		this.getButtonMap().put(moduloBtnKey(), modBtn);
	}
	

}












