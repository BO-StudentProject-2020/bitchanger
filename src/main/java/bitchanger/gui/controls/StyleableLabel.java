/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import javafx.scene.Node;
import javafx.scene.control.Label;

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
public class StyleableLabel extends Label {

	public static final String STYLE_CLASS = "styleable-label";
	public static final String BOLD_STYLE_CLASS = "styleable-label-bold";
	public static final String HEADING_STYLE_CLASS = "styleable-label-heading";
	
	private boolean isBold;
	private boolean isHeading;
	
	public StyleableLabel() {
		this("");
	}

	public StyleableLabel(String text) {
		this(text, false);
	}
	
	public StyleableLabel(String text, boolean isBold) {
		super(text);
		this.getStyleClass().add(STYLE_CLASS);
		this.setWrapText(true);
		
//		this.setText(text);
		this.setBold(isBold);
		this.isHeading = false;
	}

	public StyleableLabel(String text, Node graphic) {
		this(text, graphic, false);
	}
	
	public StyleableLabel(String text, Node graphic, boolean isBold) {
		this(text, isBold);
		this.setGraphic(graphic);
	}
	
	public void setBold(boolean b) {
		if(b) {
			this.getStyleClass().add(BOLD_STYLE_CLASS);
			this.isBold = b;
		} else {
			this.isBold = b;
			try {
				this.getStyleClass().remove(BOLD_STYLE_CLASS);
			} catch (Exception e) {
				/* ignore */
				BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			}
		}
	}
	
	public boolean isBold() {
		return this.isBold;
	}
	
	public void setHeading(boolean b) {
		if(b) {
			this.getStyleClass().add(HEADING_STYLE_CLASS);
			this.isHeading = b;
		} else {
			this.isHeading = b;
			try {
				this.getStyleClass().remove(HEADING_STYLE_CLASS);
			} catch (Exception e) {
				/* ignore */
				BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			}
		}
	}
	
	public boolean isHeading() {
		return this.isHeading;
	}

}
