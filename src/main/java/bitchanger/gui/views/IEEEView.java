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

import bitchanger.gui.controller.Controller;
import bitchanger.gui.controller.IEEEController;
import bitchanger.gui.controls.SVGIcon;
import bitchanger.gui.controls.UnfocusedButton;
import bitchanger.util.ArrayUtils;
import bitchanger.util.FXUtils;
import bitchanger.util.Resources;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


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
public class IEEEView extends AlphaNumGridView {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Beschriftungen für die Labels vor den Textfeldern definiert */
	// TODO JavaDoc EN
	private static final String[] LABEL_TEXTS = {"DEC", "IEEE"};
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	private static final String TF_DEC_KEY = "dec-TF";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die IEEE Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	private static final String TF_IEEE_KEY = "ieee-TF";
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(ConverterView.class, IEEEController.class);
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
	public IEEEView() {
		super(0, 0, 0, 1, 6, 1, LABEL_TEXTS, ArrayUtils.arrayOf(TF_DEC_KEY, TF_IEEE_KEY));
		
		buildScenegraph();
		
		ArrayDeque<Button> buttons = new ArrayDeque<>();
		for(File file : Resources.ICON_LIST) {
			Button b = new UnfocusedButton("", FXUtils.loadSVG(file));
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			buttons.add(b);
		}
		
		FXUtils.setGridConstraints(7, 0, 9, 0, buttons);
		center.getChildren().addAll(buttons);
		
		for(int row = 0; row < center.getRowCount(); row++) {
			this.addRowConstraint(row, ConstraintType.BUTTON_ROW);
		}
		for(int column = 7; column < center.getColumnCount(); column++) {
			this.addColumnConstraint(column, ConstraintType.BUTTON_COLUMN);
		}
		
		StackPane  sp = new StackPane(new SVGIcon(Resources.ABC_BRICKS_FILLED_ICON));
		sp.getStyleClass().add("stack-pane");
		center.add(sp, 15, 11);
	}
	
	
	// TODO MenuBar -> Elemente hinzufügen
	
}












