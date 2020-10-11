/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import bitchanger.calculations.IEEEStandard;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.IEEEMenuBar;
import bitchanger.preferences.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;

//TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 * 
 */
public class IEEEMenuController extends BasicMenuController {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc
	protected CheckMenuItem ieee16;
	protected CheckMenuItem ieee32;
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	public IEEEMenuController(BasicMenuBar controllable, ControllableApplication app) {
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
		
		this.ieee16 = (CheckMenuItem) this.controllable.getMenuItemMap().get(IEEEMenuBar.OPTIONS_IEEE_16_ITEM_KEY);
		this.ieee32 = (CheckMenuItem) this.controllable.getMenuItemMap().get(IEEEMenuBar.OPTIONS_IEEE_32_ITEM_KEY);
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
		setIEEEItemAction(ieee16, IEEEStandard.IEEE_754_2008_b16);
		setIEEEItemAction(ieee32, IEEEStandard.IEEE_754_2008_b32);
		
		ieee16.setSelected(Preferences.getPrefs().ieeeStandardProperty().get().equals(IEEEStandard.IEEE_754_2008_b16));
		ieee32.setSelected(Preferences.getPrefs().ieeeStandardProperty().get().equals(IEEEStandard.IEEE_754_2008_b32));
		updateIEEEStandard();
	}


	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	private void setIEEEItemAction(CheckMenuItem ieeeItem, IEEEStandard ieeeStandard) {
		ieeeItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(ieeeItem.isSelected()) {
					Preferences.getPrefs().ieeeStandardProperty().set(ieeeStandard);
				}
			}
		});
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	private void updateIEEEStandard() {
		Preferences.getPrefs().ieeeStandardProperty().addListener(new ChangeListener<IEEEStandard>() {
			@Override
			public void changed(ObservableValue<? extends IEEEStandard> observable, IEEEStandard oldValue,
					IEEEStandard newStandard) {
				ieee16.setSelected(newStandard.equals(IEEEStandard.IEEE_754_2008_b16));
				ieee32.setSelected(newStandard.equals(IEEEStandard.IEEE_754_2008_b32));
			}
		});
	}
	
	
}








