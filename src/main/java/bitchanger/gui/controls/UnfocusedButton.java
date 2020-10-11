/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

/**	<!-- $LANGUAGE=DE -->
 * Button, der beim Klick nicht den Fokus erh\u00E4lt und als Grundform ein Rechteck ohne abgerundete Ecken besitzt.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * Button that does not get focus by clicking and has a rectangle without rounded corners as basic form.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class UnfocusedButton extends Button{
	
	// TODO JavaDoc
	public static final double DEFAULT_GRAPHIC_SCALE_FACTOR = 0.03;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	// TODO JavaDoc
	private final DoubleProperty graphicScaleProperty;
	
	// TODO JavaDoc
	private final DoubleProperty graphicScaleFactorProperty;

	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem leeren String als Beschriftung
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Creates an UnfocusedButton with an empty string as label
	 */
	public UnfocusedButton() {
		this("");
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO: JavaDoc
	public UnfocusedButton(Node graphic) {
		this("", graphic);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem spezifischen String und einem Icon als Beschriftung
	 *
	 * @param text		Text f\u00FCr die Beschriftung dieses Buttons
	 * @param graphic	Icon f\u00FCr die Beschriftung dieses Buttons
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Creates an UnfocusedButton with a specific string and an icon as label
	 *
	 * @param text		Text for the label of this button
	 * @param graphic	Icon for the label of this button
	 */
	public UnfocusedButton(String text, Node graphic) {
		super(text, graphic);
		
		this.graphicScaleProperty = new SimpleDoubleProperty(Math.min(this.getHeight(), this.getWidth()));
		this.graphicScaleFactorProperty = new SimpleDoubleProperty(DEFAULT_GRAPHIC_SCALE_FACTOR);
		
		if(graphic != null) {
			graphic.scaleXProperty().bind(graphicScaleProperty);
			graphic.scaleYProperty().bind(graphicScaleProperty);
		}
		
		initShape();
		observeGraphicScaling();
		
		getStyleClass().add("unfocused-button");
		
		// TODO Effect?
//		setEffect(new Lighting());
//		setEffect(new InnerShadow());
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Erstellt einen UnfocusedButton mit einem spezifischen String als Beschriftung
	 * 
	 * @param text	Text f\u00FCr die Beschriftung dieses Buttons
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Creates an UnfocusedButton with a specific string as label
	 * 
	 * @param text	Text for the label of this button
	 */
	public UnfocusedButton(String text) {
		this(text, null);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/**	<!-- $LANGUAGE=DE -->
	 * Schaltet den Fokus f\u00FCr diesen Button aus und setzt als Grundform dieses Buttons ein Rechteck ohne abgerundete Ecken ein.
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Disables the focus for this button and sets rectangle without rounded corners as basic form for this button.
	 */
	private void initShape() {
		this.setFocusTraversable(false);
		
		Rectangle shape = new Rectangle(50, 50);
		shape.setArcHeight(0);
		shape.setArcWidth(0);
		this.setShape(shape);
		setScaleShape(true);
		
		this.setPadding(new Insets(2));
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void observeGraphicScaling() {
		this.graphicProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldGraphic, Node newGraphic) {
				if (newGraphic != null) {
					getGraphic().scaleXProperty().bind(graphicScaleProperty);
					getGraphic().scaleYProperty().bind(graphicScaleProperty);
				}
			}
		});

		this.heightProperty().addListener(this::bindScaleProperty);
		this.widthProperty().addListener(this::bindScaleProperty);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	private void bindScaleProperty(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		if (getHeight() < getWidth()) {
			this.graphicScaleProperty.bind(heightProperty().multiply(graphicScaleFactorProperty));
		} else {
			this.graphicScaleProperty.bind(widthProperty().multiply(graphicScaleFactorProperty));
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public void setGraphicScaleFactor(Double factor) {
		graphicScaleFactorProperty.set(factor);
	}

}














