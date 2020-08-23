/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import java.io.File;
import java.util.ArrayDeque;

import bitchanger.gui.controller.IEEEController;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.util.FXUtils;
import bitchanger.util.IconFactory;
import bitchanger.util.Resources;
import javafx.scene.control.Button;


/**	<!-- $LANGUAGE=DE -->
 * View, die die Scene für die Umwandlung zwischen dezimal Zahlen und der IEEE-Norm enthält.
 * <p><b>
 * Für diese View-Klasse wird der Controller {@link IEEEController} registriert.
 * </b></p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 * 
 * @see IEEEController
 */
//TODO JavaDoc EN
public class CalculatorView extends AlphaNumGridView {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
//	/** <!-- $LANGUAGE=DE -->	Array, das die Beschriftungen für die Labels vor den Textfeldern definiert */
//	// TODO JavaDoc EN
//	private static final String[] LABEL_TEXTS = {"DEC", "IEEE"};
//	
//	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird */
//	// TODO JavaDoc EN
//	private static final String TF_DEC_KEY = "dec-TF";
//	
//	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die IEEE Darstellung in der Map {@code tfMap} gespeichert wird */
//	// TODO JavaDoc EN
//	private static final String TF_IEEE_KEY = "ieee-TF";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
//		Controller.register(ConverterView.class, IEEEController.class);
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
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public final String tfDecKey = "dec-TF";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die IEEE Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public final String tfIEEEKey = "ieee-TF";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue IEEEView mit vollständigem Scenegraphen und initialisiert die Funktionen
	 * der Bedienelemente mit einem {@link IEEEController}.
	 */
	// TODO JavaDoc EN
	public CalculatorView() {
		super(0, 0, 0, 1, 6, 1, null);
		
		buildScenegraph();
		
		center.getChildren().clear();
		
		ArrayDeque<Button> buttons = new ArrayDeque<>();
		for(File file : Resources.ICON_LIST) {
			Button b = new UnfocusedButton("", IconFactory.ofSVGFile(file));
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			buttons.add(b);
		}
		
		FXUtils.setGridConstraints(0, 0, 9, 0, buttons);
		center.getChildren().addAll(buttons);
		
		for(int row = 0; row < center.getRowCount(); row++) {
			this.addRowConstraint(row, ConstraintType.BUTTON_ROW);
		}
		for(int column = 0; column < center.getColumnCount(); column++) {
			this.addColumnConstraint(column, ConstraintType.BUTTON_COLUMN);
		}
	}
	
	
}












