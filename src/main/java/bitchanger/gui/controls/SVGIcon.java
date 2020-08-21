/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import java.io.File;

import bitchanger.util.FXUtils;
import javafx.scene.shape.SVGPath;


// TODO JavaDoc
public class SVGIcon extends SVGPath {

	// TODO JavaDoc
	public SVGIcon() {
		this(new SVGPath());
	}

	// TODO JavaDoc
	public SVGIcon(File svgFile) {
		this(FXUtils.loadSVG(svgFile));
	}
	
	// TODO JavaDoc
	public SVGIcon(SVGPath svgPath) {
		super();
		this.getStyleClass().add("svg-icon");
		
		setSVG(svgPath);
	}
	
	

	// TODO JavaDoc
	public void setSVG(SVGPath svgPath) {
		if(svgPath == null) {
			this.setContent("");
			return;
		}
		
		this.setFill(svgPath.getFill());
		this.setFillRule(svgPath.getFillRule());
		this.setContent(svgPath.getContent());;
	}
	
	
	
	
}
