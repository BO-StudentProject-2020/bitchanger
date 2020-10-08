/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.Objects;

import bitchanger.gui.controller.BitoperationMenuController;
import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 * 
 */
public class BitoperationMenuBar extends BasicMenuBar {

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Bitoperationen Symbole" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Bitoperationen Symbole" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_BIT_OPERATION_SYMBOLS_KEY = "options-bit-operation-symbols-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das MenuItem "Bitoperationen Symbole" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the MenuItem "Bitoperationen Symbole" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_UNSIGNED_BIT_OPERATIONS_KEY = "options-unsigned-bit-operations-item";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(BitoperationMenuBar.class, BitoperationMenuController.class);
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
	public BitoperationMenuBar() {
		super();

		addItems();
	}
	
	
	// TODO JavaDoc
	public BitoperationMenuBar(ControllableApplication controllableApp) throws NullPointerException {
		this();
		Objects.requireNonNull(controllableApp);
		
		Controller controller = new BitoperationMenuController(this, controllableApp);
		controller.setActions();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	private void addItems() {
		MenuItem bitOpsSymbols = new MenuItem("Bitoperationen Symbole");
		CheckMenuItem unsignesBitOps = new CheckMenuItem("vorzeichenlose Bitoperationen");
		
		this.menuItemMap.put(OPTIONS_BIT_OPERATION_SYMBOLS_KEY, bitOpsSymbols);
		this.menuItemMap.put(OPTIONS_UNSIGNED_BIT_OPERATIONS_KEY, unsignesBitOps);
		
		((Menu) this.menuItemMap.get(BasicMenuBar.OPTIONS_MENU_KEY)).getItems().add(bitOpsSymbols);
		((Menu) this.menuItemMap.get(BasicMenuBar.OPTIONS_MENU_KEY)).getItems().add(unsignesBitOps);
	}

}










