package bitchanger.util;

import java.io.File;

import bitchanger.gui.controls.SVGIcon;
import bitchanger.preferences.Preferences;
import bitchanger.preferences.Style;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.SVGPath;

//TODO JavaDoc
public class IconFactory {
	
	// TODO JavaDoc
	public static SVGIcon styleBindIcon(File lightIconFile, File darkIconFile) {
		try {
			SVGIcon icon = new SVGIcon();
			SVGPath lightSVG = new SVGIcon(FXUtils.loadSVG(lightIconFile));
			SVGPath darkSVG = new SVGIcon(FXUtils.loadSVG(darkIconFile));
			
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
	
	// TODO JavaDoc
	private static void setSVGStyle(SVGIcon icon, SVGPath lightSVG, SVGPath darkSVG, Style style) {
		switch(style) {
			case LIGHT:
				icon.setSVG(lightSVG);
				icon.setId("LIGHT_ICON");
				break;
			case DARK:
				icon.setSVG(darkSVG);
				icon.setId("DARK_ICON");
				break;
			default:
				return;
		}
	}

}
