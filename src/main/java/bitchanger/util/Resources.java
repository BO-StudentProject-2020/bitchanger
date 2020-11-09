/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */
package bitchanger.util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import bitchanger.main.BitchangerLauncher;
import bitchanger.main.PrimaryFXApp;
import bitchanger.main.BitchangerLauncher.ErrorLevel;

/** <!-- $LANGUAGE=DE -->
 * Enth\u00E4lt alle ben\u00F6tigten Ressourcen, wie Pfade zu den CSS Dateien oder den Icons
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.8
 *
 */
// TODO JavaDoc EN
public class Resources {
	
	
	/** <!-- $LANGUAGE=DE -->	Speicherort der Benutzereinstellungen beim letzten Schlie\u00DFen des Programms oder {@code null}, wenn die Datei nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File RESOURCES_ROOT;
	
	static {
		String os = System.getProperty("os.name").toUpperCase();
		String appdata;
		
		if(os.contains("WIN")) {
			// Windows: %USER%/AppData/Local/Bitchanger
			appdata = System.getenv("LocalAppData")+ File.separator + "Bitchanger";
		}
		else {
			// macOS & Linux: ~/.Bitchanger
			appdata = System.getProperty("user.home") + "/.Bitchanger";
		}
		
		File file = new File(appdata);
		
		RESOURCES_ROOT = file;
	}

// CSS	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet f\u00FCr das allgemeine Layout oder {@code null}, wenn das CSS nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final String LAYOUT_CSS = getResourceAsExternalForm("/style/bitchangerLayout.css");

	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet f\u00FCr das allgemeine Layout oder {@code null}, wenn das CSS nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final String ALERT_LAYOUT_CSS = getResourceAsExternalForm("/style/alertLayout.css");
	
	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet f\u00FCr die helle Darstellung oder {@code null}, wenn das CSS nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final String LIGHT_THEME_CSS = getResourceAsExternalForm("/style/bitchangerLightTheme.css");
	
	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet f\u00FCr die gef\u00E4rbte Darstellung oder {@code null}, wenn das CSS nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final String COLOR_THEME_CSS = getResourceAsExternalForm("/style/bitchangerColorTheme.css");
	
	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet f\u00FCr die dunkle Darstellung oder {@code null}, wenn das CSS nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final String DARK_THEME_CSS = getResourceAsExternalForm("/style/bitchangerDarkTheme.css");

	
	
// Preferences	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Speicherort der Benutzereinstellungen beim letzten Schlie\u00DFen des Programms oder {@code null}, wenn die Datei nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File CUSTOM_PREFERENCES = getResourceAsFile("/preferences/CustomSettings.prefs");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort der Standardeinstellungen des Programms oder {@code null}, wenn die Datei nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File DEFAULT_PREFERENCES = getResourceAsFile("/preferences/DefaultSettings.prefs");

	
	
// Icons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit ABC-W\u00FCrfeln, gef\u00FCllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ABC_BRICKS_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-school-8.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit ABC-W\u00FCrfeln oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ABC_BRICKS_ICON = getResourceAsFile("/graphic/svg/iconmonstr-school-9.svg");
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils in einem Kreis im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_DOWN_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-down-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils in einem Kreis im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_LEFT_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-left-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils in einem Kreis im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_RIGHT_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-right-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils in einem Kreis im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_UP_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-up-circle-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_DOWN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-down-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_LEFT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-left-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_RIGHT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-right-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ANGEL_UP_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-up-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils in einem gef\u00FCllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_DOWN_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-69.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils in einem gef\u00FCllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_LEFT_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-68.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils in einem gef\u00FCllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_RIGHT_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-67.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils in einem gef\u00FCllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_UP_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-70.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_DOWN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-65.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-64.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_RIGHT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-63.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ARROW_UP_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-66.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Logos als PNG oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File BITCHANGER_LOGO_PNG = getResourceAsFile("/graphic/Bitchanger.png");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit Rechensymbolen, gef\u00FCllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File CALCULATOR_SYMBOLS_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit Rechensymbolen oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File CALCULATOR_SYMBOLS_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Taschenrechners, gef\u00FCllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File CALCULATOR_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Taschenrechners oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File CALCULATOR_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-6.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Code-Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File CODE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-code-2.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit dem Copyright-Symbol, gef\u00FCllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File COPYRIGHT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-copyright-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit dem Copyright-Symbol oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File COPYRIGHT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-copyright-2.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gef\u00FCllten Radiergummis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ERASER_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-eraser-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Radiergummis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ERASER_ICON = getResourceAsFile("/graphic/svg/iconmonstr-eraser-2.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons zum Aktivieren des Vollbilds oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ENTER_FULLSCREEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons zum Deaktivieren des Vollbilds oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File EXIT_FULLSCREEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons zum Wechseln des Vollbilds in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File FULLSCREEN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-thin.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Idee Icons ("Gl\u00FChbirne") oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File IDEA_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Idee Icons ("Gl\u00FChbirne") oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File IDEA_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Idee Icons in einer gef\u00FCllten Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File IDEA_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-9.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Idee Icons in einer Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File IDEA_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-10.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des einfachen gef\u00FCllten Info Icons mit schlichtem 'i' oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_CIRCLE_SIMPLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons mit schlichtem 'i' oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_CIRCLE_SIMPLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Info Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-6.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons in einer gef\u00FCllten "Sprechbox" oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_SPEECH_BOX_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons in einer "Sprechbox" oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_SPEECH_BOX_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-12.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons mit schlichtem 'i' in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File INFO_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Tastatur Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Tastatur Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Icons mit kompakter Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_COMPACT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit kompakter Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_COMPACT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Icons mit \u00F6ffnender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_OPEN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit \u00F6ffnender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_OPEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-6.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Icons mit schlie\u00DFender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_CLOSE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit schlie\u00DFender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File KEYBORD_CLOSE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-8.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer "Gl\u00FChbirne" mit Draht oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File LIGHT_BULB_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer gef\u00FCllten "Gl\u00FChbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File LIGHT_BULB_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer "Gl\u00FChbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File LIGHT_BULB_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-12.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer leuchtenden gef\u00FCllten "Gl\u00FChbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File LIGHT_BULB_3_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-15.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer leuchtenden "Gl\u00FChbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File LIGHT_BULB_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-16.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Zauberstabs oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MAGIC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magic-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Zauberstabs mit Blatt oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MAGIC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magic-9.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Men\u00FC Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MENU_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Men\u00FC Icons mit drei horizontalen gef\u00FCllten Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MENU_DOTS_HORIZONTAL_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Men\u00FC Icons mit drei vertikalen gef\u00FCllten Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MENU_DOTS_VERTICAL_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Men\u00FC Icons mit drei horizontalen Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MENU_DOTS_HORIZONTAL_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-dot-horizontal-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Men\u00FC Icons mit drei vertikalen Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MENU_DOTS_VERTICAL_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-dot-vertical-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Men\u00FC Icons in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MENU_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-thin.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Monitor Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MONITOR_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-computer-3.svg");

	/** <!-- $LANGUAGE=DE -->	Speicherort des Monitor Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File MONITOR_ICON = getResourceAsFile("/graphic/svg/iconmonstr-computer-4.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Farbpalette oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PAINT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Pinsel Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PAINT_BRUSH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Pinsel Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PAINT_BRUSH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit einem Fenster und Pinsel oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PAINT_BRUSH_WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-10.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Icons eines Farbeimers mit Pinsel oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PAINT_BRUSH_BUCKET_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Farbeimers mit Pinsel oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PAINT_BRUSH_BUCKET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-12.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gef\u00FCllten Zahnrades oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PREFERENCES_GEAR_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit zwei gef\u00FCllten Zahnr\u00E4dern oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PREFERENCES_GEAR_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-10.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit drei gef\u00FCllten Zahnr\u00E4dern oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PREFERENCES_GEAR_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Zahnrades im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PREFERENCES_GEAR_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Schraubenschl\u00FCssels mit R\u00FCckg\u00E4ngig-Pfeil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PREFERENCES_WRENCH_RESET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Schraubenschl\u00FCssels oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File PREFERENCES_WRENCH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-25.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Frage Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File QUESTION_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File QUESTION_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons in einer gef\u00FCllten Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File QUESTION_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons in einer Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File QUESTION_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-6.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File QUESTION_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-question-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gef\u00FCllten Icons einer Sonne oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File SUN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Sonne oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File SUN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-2.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit zwei runden Pfeilen gegen den Uhrzeigersinn oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File SYNC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit drei runden Pfeilen gegen den Uhrzeigersinn oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File SYNC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-6.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Textfeldes mit vier Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TEXTFIELD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Textfeldes mit drei Punkten und einem Unterstrich oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TEXTFIELD_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-3.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gef\u00FCllten M\u00FClleimers oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TRASH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-27.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines M\u00FClleimers oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TRASH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-28.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gef\u00FCllten M\u00FClleimers mit 'X'-Symbol oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TRASH_X_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-29.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines M\u00FClleimers mit 'X'-Symbol oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TRASH_X_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-30.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines M\u00FClleimers in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File TRASH_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-thin.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Programmfensters oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Programmfensters in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File WINDOW_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-thin.svg");

	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit zwei Programmfenstern im d\u00FCnnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File WINDOW_THIN_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-windows-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gef\u00FCllten Quadrat oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_SQUARE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-13.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Quadrat oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_SQUARE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-14.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gef\u00FCllten Quadrat oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-9.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gef\u00FCllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_CIRCLE_1_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-10.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_CIRCLE_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gef\u00FCllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_CIRCLE_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_CIRCLE_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Kreis in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gef\u00FCllten L\u00F6schen-Symbols mit 'X' oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_ARROW_LEFT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-12.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines L\u00F6schen-Symbols mit 'X' oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File X_MARK_ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-13.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '+' oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ZOOM_IN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '-' oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ZOOM_OUT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-8.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '+' in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ZOOM_IN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-in-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '-' in d\u00FCnnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	// TODO JavaDoc EN
	public static final File ZOOM_OUT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-out-thin.svg");
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	static {
		// Aus den Standardeinstellungen darf nur gelesen werden!
		DEFAULT_PREFERENCES.setReadOnly();
		
		// Ressourcen aus Jar-Datei entpacken
		copyResources();
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Gibt eine Liste zur\u00FCck, die Speicherorte aller in dieser Klasse definierten Icons enth\u00E4lt 
	 * 
	 * @return Liste, die Speicherorte aller in dieser Klasse definierten Icons enth\u00E4lt
	 * 
	 * @since Bitchanger 0.1.7
	 */
	// TODO JavaDoc EN
	public static final LinkedList<File> iconList() {
		LinkedList<File> iconList = new LinkedList<>();
		
		for(Field field : Resources.class.getFields()) {
			try {
				if(field.get(Resources.class) instanceof File) {
					File file = (File) field.get(Resources.class);
					
					if(file.getName().contains(".svg")) {
						iconList.add(file);
					}
				}
				
			} catch(Exception e) {
				BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
				e.printStackTrace();
			}
		}
		
		return iconList;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Sucht die Ressource mit gegebenem Namen und gibt die URL als String zur\u00FCck.
	 * 
	 * @param name	Name (und Pfad) der gew\u00FCnschten Ressource
	 * @return		URL der gefundenen Ressource oder {@code null}, wenn die Ressource nicht gefunden werden konnte
	 * 
	 * @see Class#getResource(String)
	 */
	// TODO JavaDoc EN
	public static String getResourceAsExternalForm(String name) {
		String url;
		try {
			 url = Resources.class.getResource("/bitchanger_resources" + name).toExternalForm();
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.LOW, e);
			
			try {
				url = getResourceAsFile(name).toURI().toURL().toExternalForm();
			} catch (MalformedURLException e1) {
				BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL, e1);
				url = null;
			}
		}
		
		return url;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Sucht die Ressource mit gegebenem Namen und gibt diese als File zur\u00FCck.
	 * 
	 * @param name	Name (und Pfad) der gew\u00FCnschten Ressource
	 * @return		File der gew\u00FCnschten Ressource oder {@code null}, wenn die Ressource nicht gefunden werden konnte
	 * 
	 * @see Class#getResource(String)
	 */
	// TODO JavaDoc EN
	public static File getResourceAsFile(String name) {
		File file;
		
		try{
			file = new File(RESOURCES_ROOT, File.separator + "resources-" + PrimaryFXApp.VERSION + File.separator + name);
            
        } catch (Exception e){
        	BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
            e.printStackTrace();
            file = null;
        }
		
		return file;
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Kopiert alle ben\u00F6tigten Ressourcen in den systemspezifischen Ressourcen-Ordner.
	 * Der Ressourcen-Ordner hat den Pfad {@link #RESOURCES_ROOT}/resources-{@link PrimaryFXApp#VERSION}
	 */
	// TODO JavaDoc EN
	private static void copyResources() {
		try {
			URL codeLocation = Resources.class.getProtectionDomain().getCodeSource().getLocation();
			Path target = new File(RESOURCES_ROOT, "resources-" + PrimaryFXApp.VERSION).toPath();
			
			try {
				Files.createDirectories(target.getParent());
			} catch (Exception e) {
				/* ignore */
				BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			}
			
			if(codeLocation.toString().endsWith(".jar")) {
				// Programm wird in jar-Datei ausgeführt
				exportFromJar(codeLocation.toURI(), target);
			}
			else {
				// Programm wird nicht in einer jar-Datei ausgeführt
				File source = Paths.get(Resources.class.getResource("/bitchanger_resources").toURI().normalize()).toFile();
				copyRecursive(source, target.toFile(), "/bitchanger_resources");
			}
			
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
			e.printStackTrace();
		}
	}
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Extrahiert alle Ressourcen aus der Jar-Datei in den Ordner {@code target}
	 * 
	 * @param codeLocation	Pfad der ausgef\u00FChrten Jar-Datei
	 * @param target		Ordner, in den die Ressourcen extrahiert werden
	 * 
	 * @throws Exception	wenn beim Extrahieren ein Fehler auftritt
	 */
	// TODO JavaDoc EN
	private static void exportFromJar(URI codeLocation, Path target) throws Exception {
		JarFile jarfile = new JarFile(new File(codeLocation));
		ZipEntry zipfile = jarfile.getEntry("bitchanger_resources.zip");
		
		ZipInputStream zis = new ZipInputStream(jarfile.getInputStream(zipfile));
		ZipEntry entry;
		
		while((entry = zis.getNextEntry()) != null) {
			try {
				Path entryTarget = new File(target.toFile(), entry.getName().substring(entry.getName().indexOf("/"))).toPath();
				if(entry.isDirectory()) {
					entryTarget.toFile().mkdirs();
				}
				else if(Files.notExists(entryTarget)) {
					Files.copy(jarfile.getInputStream(entry), entryTarget);
				}
			} catch (Exception e) {
				BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
				e.printStackTrace();
			}
		}
		
		zis.close();
		jarfile.close();
	}

	
	/** <!-- $LANGUAGE=DE -->
	 * Kopiert alle Inhalte eines Pfades rekursiv zum Zielpfad
	 * 
	 * @param source	Quellpfad
	 * @param target	Zielpfad
	 * @param resourceName Gesamtpfad der aktuellen ausgehend vom Anfangsverzeichnis als String
	 * 
	 * @throws NullPointerException wenn einer der Parameter {@code null ist}
	 */
	// TODO JavaDoc EN
	private static void copyRecursive(File source, File target, String resourceName) throws NullPointerException {
		if(source == null) {
			throw new NullPointerException("source must not be null!");
		}
		
		if(target == null) {
			throw new NullPointerException("target must not be null!");
		}
		
		if(source.listFiles() == null) {
			return;
		}
		
		if(!target.exists()) {
			target.mkdirs();
		}
		
		
		for(File file : source.listFiles()) {
			File newTarget = new File(target, file.getName());
			
			if(file.isDirectory()) {
				newTarget.mkdir();
				copyRecursive(file, newTarget, resourceName + "/" + file.getName());
			}
			else {
				if (newTarget.exists()) {
					continue;
				}
				
				try {
					InputStream in = Resources.class.getResourceAsStream(resourceName + "/" + file.getName());
					Files.copy(in, newTarget.toPath());
				} catch (Exception e) {
					BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
					e.printStackTrace();
				}
			}
		}
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private Resources() {};
	
}
