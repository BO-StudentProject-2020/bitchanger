package bitchanger.gui.elements;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class RoundButton extends Button{

	// Konstruktoren	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public RoundButton() {
		super();
		initShape();
	}

	public RoundButton(String text, Node graphic) {
		super(text, graphic);
		initShape();
	}

	public RoundButton(String text) {
		super(text);
		initShape();
	}
	
	
	// Methoden	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private void initShape() {
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		this.setShape(shape);
		setScaleShape(true);
		
		// Rundung an Groesse binden (auskommentieren, um Rundung zu loeschen)
		shape.arcHeightProperty().bind(widthProperty().divide(4));
		shape.arcWidthProperty().bind(heightProperty().divide(4));
	
	}
	

}
