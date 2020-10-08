/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.util.Objects;

import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controller.ConverterMenuController;
import bitchanger.gui.controller.IEEEMenuController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;

//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 * 
 */
public class IEEEMenuBar extends BasicMenuBar {

	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das CheckMenuItem "16-Bit" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the CheckMenuItem "16-Bit" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_IEEE_16_ITEM_KEY = "options-ieee-16-item";
	
	/** <!-- $LANGUAGE=DE -->	Schl\u00FCsselwort, mit dem das CheckMenuItem "32-Bit" in der Map {@link #menuItemMap} abgelegt ist */
	/* <!-- $LANGUAGE=EN -->	Key with which the CheckMenuItem "32-Bit" is associated in the Map {@link #menuItemMap} */
	public static final String OPTIONS_IEEE_32_ITEM_KEY = "options-ieee-32-item";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(IEEEMenuBar.class, ConverterMenuController.class);
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
	public IEEEMenuBar() {
		super();

		addItems();
	}
	
	
	// TODO JavaDoc
	public IEEEMenuBar(ControllableApplication controllableApp) throws NullPointerException {
		this();
		Objects.requireNonNull(controllableApp);
		
		Controller controller = new IEEEMenuController(this, controllableApp);
		controller.setActions();
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	private void addItems() {
		Menu ieeeMenu = new Menu("IEEE Standard");
		CheckMenuItem ieee16 = new CheckMenuItem("16-Bit");
		CheckMenuItem ieee32 = new CheckMenuItem("32-Bit");
		
		ieeeMenu.getItems().addAll(ieee16, ieee32);
		
		this.menuItemMap.put(OPTIONS_IEEE_16_ITEM_KEY, ieee16);
		this.menuItemMap.put(OPTIONS_IEEE_32_ITEM_KEY, ieee32);
		
		((Menu) this.menuItemMap.get(BasicMenuBar.OPTIONS_MENU_KEY)).getItems().add(ieeeMenu);
	}

}










