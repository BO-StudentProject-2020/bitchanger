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

package bitchanger.gui.controller;

import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.BitoperationMenuBar;
import bitchanger.preferences.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;

//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
<<<<<<< HEAD
 * @author Tim Mühle
=======
 * @author Tim M\u00FChle
>>>>>>> master
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 * 
 */
public class BitoperationMenuController extends BasicMenuController {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc
	protected MenuItem bitOperationSymbols;
	
	// TODO JavaDoc
	protected CheckMenuItem unsignedBitOperations;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	public BitoperationMenuController(BasicMenuBar controllable, ControllableApplication app) {
		super(controllable, app);
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		super.initControls();
		
		this.bitOperationSymbols = this.controllable.getMenuItemMap().get(BitoperationMenuBar.OPTIONS_BIT_OPERATION_SYMBOLS_KEY);
		
		if (this.controllable.getMenuItemMap().get(BitoperationMenuBar.OPTIONS_UNSIGNED_BIT_OPERATIONS_KEY) instanceof CheckMenuItem) {
			this.unsignedBitOperations = (CheckMenuItem) this.controllable.getMenuItemMap().get(BitoperationMenuBar.OPTIONS_UNSIGNED_BIT_OPERATIONS_KEY);
		}
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	public void setActions() {
		super.setActions();
		
		//  Menu Options
		setBitOperationSymbolsAction();
		
		this.unsignedBitOperations.setSelected(Preferences.getPrefs().useUnsignedBitOperationProperty().get());
		Preferences.getPrefs().useUnsignedBitOperationProperty().bindBidirectional(unsignedBitOperations.selectedProperty());
	}



//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// TODO JavaDoc
	private void setBitOperationSymbolsAction() {
		bitOperationSymbols.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Preferences.getPrefs().showBitOperationSymbolsProperty().set(!Preferences.getPrefs().showBitOperationSymbolsProperty().get());
			}
		});
	}
	
}








