package bitchanger.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ExtendedButton extends Button{

	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public ExtendedButton() {
		super();
		initShape();
	}

	public ExtendedButton(String text, Node graphic) {
		super(text, graphic);
		initShape();
	}

	public ExtendedButton(String text) {
		super(text);
		initShape();
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private void initShape() {
		// TODO REMOVE LATER (Nur zu Testzwecken!!!)	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
		this.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
	
	/*	Ellipse shape = new Ellipse(500, 500);
		this.setShape(shape);
	*/	
		
		this.setFocusTraversable(false);
		
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		this.setShape(shape);
		setScaleShape(true);
		
		this.setPadding(new Insets(2));
		
	/*	// Rundung an Groesse binden (auskommentieren, um Rundung zu loeschen)
		shape.arcHeightProperty().bind(widthProperty().divide(4));
		shape.arcWidthProperty().bind(heightProperty().divide(4));
	*/
	}
	

}
