/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.util.ArrayDeque;

import bitchanger.calculations.ConversionStep;
import bitchanger.calculations.ConvertingNumbers;
import bitchanger.gui.views.CalcPathView;
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Preferences;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/** <!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.8
 * @version 0.1.8
 *
 */
// TODO JavaDoc
public class CalcPathViewController extends ControllerBase<CalcPathView> {

	
	private final ReadOnlyIntegerProperty fromBaseProperty;
	private final ReadOnlyIntegerProperty toBaseProperty;
	private TextField tfInput;
	private TextField tfOutput;
	
	
	public CalcPathViewController(CalcPathView controllable) {
		super(controllable);
		this.fromBaseProperty = controllable.fromBaseProperty();
		this.toBaseProperty = controllable.toBaseProperty();
	}

	@Override
	protected void initControls() {
		this.tfInput = textFieldMap.get(controllable.tfInputKey());
		this.tfOutput = textFieldMap.get(controllable.tfOutputKey());
	}
	
	
	
	@Override
	public void setActions() {
		setInputAction();
		setBaseListener();
	}

	private void setInputAction() {
		tfInput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateValue();
			}
		});
	}

	private void updateValue() {
		String output = "";
		ArrayDeque<ConversionStep> calcPath = new ArrayDeque<>();
		
		try {
			if(fromBaseProperty.get() == 10) {
				output = ConvertingNumbers.decToBase(toBaseProperty.get(), tfInput.getText(), Preferences.getPrefs().getComma(), 16, calcPath);
			} else if(toBaseProperty.get() == 10){
				output = ConvertingNumbers.baseToDecString(fromBaseProperty.get(), tfInput.getText(), Preferences.getPrefs().getComma(), calcPath);
			} else {
				calcPath.add(new ConversionStep("Umrechnung in Zahl zur Basis 10", true));
				output = ConvertingNumbers.baseToDecString(fromBaseProperty.get(), tfInput.getText(), Preferences.getPrefs().getComma(), calcPath);
				
				calcPath.add(new ConversionStep("Zahl zur Basis 10 ist:  " + output, true));
				calcPath.add(new ConversionStep("Umrechnung von Basis 10 zur Basis " + toBaseProperty.get(), true));
				output = ConvertingNumbers.decToBase(toBaseProperty.get(), output, Preferences.getPrefs().getComma(), 16, calcPath);
			}
			
			output = ConvertingNumbers.splitInBlocks(toBaseProperty.get(), output);
			calcPath.add(new ConversionStep("Das Ergebnis ist:  " + output, true));
		}
		catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			
			output = "";
		}
		
		tfOutput.setText(output);
		controllable.setCalcPath(calcPath);
	}
	
	private void setBaseListener() {
		fromBaseProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateValue();
			}
		});
		
		toBaseProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateValue();
			}
		});
	}


}
