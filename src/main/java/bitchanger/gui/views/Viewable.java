/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import javafx.scene.Scene;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.0
 *
 */
public interface Viewable {
	
	public Scene getScene();
	public double getMaxHeigth();
	public double getMaxWidth();
	public double getMinHeigth();
	public double getMinWidth();
	
}
