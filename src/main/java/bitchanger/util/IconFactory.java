/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
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
 * Factory Klasse f\u00FCr das Erstellen von SVGIcons
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.6
 *
 */
public class IconFactory {
	
	// TODO JavaDoc EN
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt ein neues SVGIcon, das je nach eingestelltem Style in {@link Preferences#styleProperty()} der svg-Datei
	 * lightIconFile oder darkIconFile entspricht und sich automatisch bei \u00C4nderung des Styles anpasst.
	 * 
	 * @param lightIconFile	svg-Datei f\u00FCr den Style {@link Style#LIGHT}
	 * @param darkIconFile	svg-Datei f\u00FCr den Style {@link Style#DARK}
	 * 
	 * @return	neues SVGIcon, generiert aus den Files lightIconFile und darkIconFile oder {@code null}, wenn die Dateien nicht gelesen werden konnten
	 */
	public static SVGIcon styleBindIcon(File lightIconFile, File darkIconFile) {
		try {
			SVGIcon icon = new SVGIcon();
			SVGPath lightSVG = new SVGIcon(FXUtils.loadSVG(lightIconFile));
			SVGPath darkSVG = new SVGIcon(FXUtils.loadSVG(darkIconFile));
			
			// Listener für den Style
			Preferences.getPrefs().styleProperty().addListener(new ChangeListener<Style>() {
				@Override
				public void changed(ObservableValue<? extends Style> observable, Style oldValue, Style newStyle) {
					setSVGStyle(icon, lightSVG, darkSVG, newStyle);
				}
			});
			
			setSVGStyle(icon, lightSVG, darkSVG, Preferences.getPrefs().styleProperty().get());

			return icon;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Setzt den SVG-Pfad des SVGIcon icon auf den Pfad von lightSVG oder darkSVG, basierend auf dem Style style.
	 * 
	 * @param icon		SVGIcon, dessen SVG-Pfad neu gesetzt wird
	 * @param lightSVG	SVGPath f\u00FCr den Fall {@link Style#LIGHT}
	 * @param darkSVG	SVGPath f\u00FCr den Fall {@link Style#DARK}
	 * @param style		Style, der \u00FCber den SCG-Pfad entscheidet
	 */
	private static void setSVGStyle(SVGIcon icon, SVGPath lightSVG, SVGPath darkSVG, Style style) {
		switch(style) {
			case LIGHT: /* fall through */
			case COLOR:
				icon.setSVG(lightSVG);
				break;
			case DARK:
				icon.setSVG(darkSVG);
				break;
			default:
				return;
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	public static SVGIcon ofSVGFile(File file) {
		return file == null ? null : new SVGIcon(file);
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




