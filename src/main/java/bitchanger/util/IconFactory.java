/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.util;

import java.io.File;

import bitchanger.gui.controls.SVGIcon;
import bitchanger.preferences.Preferences;
import bitchanger.preferences.Style;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.SVGPath;

/** <!-- $LANGUAGE=DE -->
 * Factory Klasse für das Erstellen von SVGIcons
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 *
 */
public class IconFactory {
	
	// TODO JavaDoc EN
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues SVGIcon, das je nach eingestelltem Style in {@link Preferences#readOnlyStyleProperty} der svg-Datei
	 * lightIconFile oder darkIconFile entspricht und sich automatisch bei Änderung des Styles anpasst.
	 * 
	 * @param lightIconFile	svg-Datei für den Style {@link Style#LIGHT}
	 * @param darkIconFile	svg-Datei für den Style {@link Style#DARK}
	 * 
	 * @return	neues SVGIcon, generiert aus den Files lightIconFile und darkIconFile oder {@code null}, wenn die Dateien nicht gelesen werden konnten
	 */
	public static SVGIcon styleBindIcon(File lightIconFile, File darkIconFile) {
		try {
			SVGIcon icon = new SVGIcon();
			SVGPath lightSVG = new SVGIcon(FXUtils.loadSVG(lightIconFile));
			SVGPath darkSVG = new SVGIcon(FXUtils.loadSVG(darkIconFile));
			
			// Listener für den Style
			Preferences.getPrefs().readOnlyStyleProperty.addListener(new ChangeListener<Style>() {
				@Override
				public void changed(ObservableValue<? extends Style> observable, Style oldValue, Style newStyle) {
					setSVGStyle(icon, lightSVG, darkSVG, newStyle);
				}
			});
			
			setSVGStyle(icon, lightSVG, darkSVG, Preferences.getPrefs().readOnlyStyleProperty.get());

			return icon;
		} 
		catch (Exception e) {
			return null;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Setzt den SVG-Pfad des SVGIcon icon auf den Pfad von lightSVG oder darkSVG, basierend auf dem Style style.
	 * 
	 * @param icon		SVGIcon, dessen SVG-Pfad neu gesetzt wird
	 * @param lightSVG	SVGPath für den Fall {@link Style#LIGHT}
	 * @param darkSVG	SVGPath für den Fall {@link Style#DARK}
	 * @param style		Style, der über den SCG-Pfad entscheidet
	 */
	private static void setSVGStyle(SVGIcon icon, SVGPath lightSVG, SVGPath darkSVG, Style style) {
		switch(style) {
			case LIGHT:
				icon.setSVG(lightSVG);
				break;
			case DARK:
				icon.setSVG(darkSVG);
				break;
			default:
				return;
		}
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private IconFactory() {}
	
}




