/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.Objects;

import bitchanger.gui.controller.CalculatorMenuController;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 * 
 */
public class CalculatorMenuBar extends BasicMenuBar {

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem das MenuItem "Bitoperationen ein-/ausblenden" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Bitoperationen ein-/ausblenden" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_SHOW_BIT_OPERATIONS_KEY = "options-show-bit-operations-item";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit dem das MenuItem "Bitoperationen Symbole" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Bitoperationen Symbole" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_BIT_OPERATION_SYMBOLS_KEY = "options-bit-operation-symbols-item";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(CalculatorMenuBar.class, CalculatorMenuController.class);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances   																												 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	public CalculatorMenuBar() {
		super();

		addItems();
	}
	
	
	// TODO JavaDoc
	public CalculatorMenuBar(ControllableApplication controllableApp) throws NullPointerException {
		this();
		Objects.requireNonNull(controllableApp);
		
		Controller controller = new CalculatorMenuController(this, controllableApp);
		controller.setActions();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	private void addItems() {
		MenuItem showBitOps = new MenuItem("Bitoperationen ein-/ausblenden");
		MenuItem bitOpsSymbols = new MenuItem("Bitoperationen Symbole");
		
		this.menuItemMap.put(OPTIONS_SHOW_BIT_OPERATIONS_KEY, showBitOps);
		this.menuItemMap.put(OPTIONS_BIT_OPERATION_SYMBOLS_KEY, bitOpsSymbols);
		
		((Menu) this.menuItemMap.get(BasicMenuBar.OPTIONS_MENU_KEY)).getItems().add(showBitOps);
		((Menu) this.menuItemMap.get(BasicMenuBar.OPTIONS_MENU_KEY)).getItems().add(bitOpsSymbols);
	}

}










