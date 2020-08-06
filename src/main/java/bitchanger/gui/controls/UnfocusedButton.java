/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
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
public class UnfocusedButton extends Button{

	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem leeren String als Beschriftung
	 */
	public UnfocusedButton() {
		super();
		initShape();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem spezifischen String und einem Icon als Beschriftung
	 */
	public UnfocusedButton(String text, Node graphic) {
		super(text, graphic);
		initShape();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem spezifischen String als Beschriftung
	 */
	public UnfocusedButton(String text) {
		super(text);
		initShape();
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**	<!-- $LANGUAGE=DE -->
	 * Schaltet den Fokus für diesen Button aus und setzt als Grundform dieses Buttons ein Rechteck ohne abgerundete Ecken ein.
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
