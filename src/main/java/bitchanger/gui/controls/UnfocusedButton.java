/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**	<!-- $LANGUAGE=DE -->
 * Button, der beim Klick nicht den Fokus erhält und als Grundform ein Rechteck ohne abgerundete Ecken besitzt.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */

/*	<!-- $LANGUAGE=EN -->
 * Button that does not get focus by clicking and has a rectangle without rounded corners as basic form.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class UnfocusedButton extends Button{

	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem leeren String als Beschriftung
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Creates an UnfocusedButton with an empty string as label
	 */
	public UnfocusedButton() {
		super();
		initShape();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem spezifischen String und einem Icon als Beschriftung
	 *
	 * @param text		Text für die Beschriftung dieses Buttons
	 * @param graphic	Icon für die Beschriftung dieses Buttons
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Creates an UnfocusedButton with a specific string and an icon as label
	 *
	 * @param text		Text for the label of this button
	 * @param graphic	Icon for the label of this button
	 */
	public UnfocusedButton(String text, Node graphic) {
		super(text, graphic);
		initShape();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem spezifischen String als Beschriftung
	 * 
	 * @param text	Text für die Beschriftung dieses Buttons
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Creates an UnfocusedButton with a specific string as label
	 * 
	 * @param text	Text for the label of this button
	 */
	public UnfocusedButton(String text) {
		super(text);
		initShape();
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Schaltet den Fokus für diesen Button aus und setzt als Grundform dieses Buttons ein Rechteck ohne abgerundete Ecken ein.
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Disables the focus for this button and sets rectangle without rounded corners as basic form for this button.
	 */
	private void initShape() {
		// TODO REMOVE LATER (Nur zu Testzwecken!!!)	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		this.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
	
		this.setFocusTraversable(false);
		
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		this.setShape(shape);
		setScaleShape(true);
		
		this.setPadding(new Insets(2));
	}
	

}
