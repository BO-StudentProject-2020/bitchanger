/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */
package bitchanger.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;

/** <!-- $LANGUAGE=DE -->
 * Enthält alle benötigten Ressourcen, wie Pfade zu den CSS Dateien oder den Icons
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 *
 */
public class Resources {
	
	// TODO JavaDoc EN

// CSS	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet für das allgemeine Layout oder {@code null}, wenn das CSS nicht gefunden wurde */
	public static final String LAYOUT_CSS = getResourceAsExternalForm("/style/bitchangerLayout.css");

	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet für die helle Darstellung oder {@code null}, wenn das CSS nicht gefunden wurde */
	public static final String LIGHT_CSS = getResourceAsExternalForm("/style/bitchangerLightTheme.css");
	
	/** <!-- $LANGUAGE=DE -->	Pfad zum Stylesheet für die dunkle Darstellung oder {@code null}, wenn das CSS nicht gefunden wurde */
	public static final String DARK_CSS = getResourceAsExternalForm("/style/bitchangerDarkTheme.css");

	
	
// Preferences	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Speicherort der Benutzereinstellungen beim letzten Schließen des Programms oder {@code null}, wenn die Datei nicht gefunden wurde */
	public static final File CUSTOM_PREFERENCES = getResourceAsFile("/preferences/CustomSettings.prefs");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort der Standardeinstellungen des Programms oder {@code null}, wenn die Datei nicht gefunden wurde */
	public static final File DEFAULT_PREFERENCES = getResourceAsFile("/preferences/DefaultSettings.prefs");

	
	
// Icons	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit ABC-Würfeln, gefüllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ABC_BRICKS_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-school-8.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit ABC-Würfeln oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ABC_BRICKS_ICON = getResourceAsFile("/graphic/svg/iconmonstr-school-9.svg");
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils in einem Kreis im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_DOWN_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-down-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils in einem Kreis im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_LEFT_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-left-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils in einem Kreis im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_RIGHT_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-right-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils in einem Kreis im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_UP_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-up-circle-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_DOWN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-down-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_LEFT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-left-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_RIGHT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-right-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ANGEL_UP_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-up-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils in einem gefüllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_DOWN_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-69.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils in einem gefüllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_LEFT_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-68.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils in einem gefüllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_RIGHT_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-67.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils in einem gefüllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_UP_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-70.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach unten zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_DOWN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-65.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach links zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-64.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach rechts zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_RIGHT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-63.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines nach oben zeigenden Pfeils oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ARROW_UP_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-66.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit Rechensymbolen, gefüllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File CALCULATOR_SYMBOLS_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit Rechensymbolen oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File CALCULATOR_SYMBOLS_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Taschenrechners, gefüllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File CALCULATOR_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Taschenrechners oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File CALCULATOR_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-6.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit dem Copyright-Symbol, gefüllt oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File COPYRIGHT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-copyright-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit dem Copyright-Symbol oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File COPYRIGHT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-copyright-2.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gefüllten Radiergummis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ERASER_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-eraser-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Radiergummis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ERASER_ICON = getResourceAsFile("/graphic/svg/iconmonstr-eraser-2.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons zum Aktivieren des Vollbilds oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ENTER_FULLSCREEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons zum Deaktivieren des Vollbilds oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File EXIT_FULLSCREEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons zum Wechseln des Vollbilds in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File FULLSCREEN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-thin.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Idee Icons ("Glühbirne") oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File IDEA_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Idee Icons ("Glühbirne") oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File IDEA_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Idee Icons in einer gefüllten Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File IDEA_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-9.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Idee Icons in einer Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File IDEA_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-10.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des einfachen gefüllten Info Icons mit schlichtem 'i' oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_CIRCLE_SIMPLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons mit schlichtem 'i' oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_CIRCLE_SIMPLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Info Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-6.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons in einer gefüllten "Sprechbox" oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_SPEECH_BOX_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons in einer "Sprechbox" oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_SPEECH_BOX_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-12.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Info Icons mit schlichtem 'i' in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File INFO_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Tastatur Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Tastatur Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Icons mit kompakter Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_COMPACT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit kompakter Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_COMPACT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Icons mit öffnender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_OPEN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit öffnender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_OPEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-6.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Icons mit schließender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_CLOSE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit schließender Tastatur oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File KEYBORD_CLOSE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-8.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer "Glühbirne" mit Draht oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File LIGHT_BULB_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer gefüllten "Glühbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File LIGHT_BULB_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer "Glühbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File LIGHT_BULB_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-12.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer leuchtenden gefüllten "Glühbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File LIGHT_BULB_3_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-15.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer leuchtenden "Glühbirne" oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File LIGHT_BULB_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-16.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Zauberstabs oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MAGIC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magic-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Zauberstabs mit Blatt oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MAGIC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magic-9.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Menü Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MENU_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Menü Icons mit drei horizontalen gefüllten Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MENU_DOTS_HORIZONTAL_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Menü Icons mit drei vertikalen gefüllten Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MENU_DOTS_VERTICAL_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Menü Icons mit drei horizontalen Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MENU_DOTS_HORIZONTAL_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-dot-horizontal-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Menü Icons mit drei vertikalen Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MENU_DOTS_VERTICAL_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-dot-vertical-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Menü Icons in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File MENU_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-thin.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Pinsel Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PAINT_BRUSH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Pinsel Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PAINT_BRUSH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit einem Fenster und Pinsel oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PAINT_BRUSH_WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-10.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Icons eines Farbeimers mit Pinsel oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PAINT_BRUSH_BUCKET_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Farbeimers mit Pinsel oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PAINT_BRUSH_BUCKET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-12.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gefüllten Zahnrades oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PREFERENCES_GEAR_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit zwei gefüllten Zahnrädern oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PREFERENCES_GEAR_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-10.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit drei gefüllten Zahnrädern oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PREFERENCES_GEAR_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Zahnrades im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PREFERENCES_GEAR_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Schraubenschlüssels mit Rückgängig-Pfeil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PREFERENCES_WRENCH_RESET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Schraubenschlüssels oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File PREFERENCES_WRENCH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-25.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Frage Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File QUESTION_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File QUESTION_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons in einer gefüllten Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File QUESTION_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons in einer Sprechblase oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File QUESTION_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-6.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Frage Icons in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File QUESTION_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-question-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des gefüllten Icons einer Sonne oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File SUN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Sonne oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File SUN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-2.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit zwei runden Pfeilen gegen den Uhrzeigersinn oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File SYNC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-3.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit drei runden Pfeilen gegen den Uhrzeigersinn oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File SYNC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-6.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Textfeldes mit vier Punkten oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TEXTFIELD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-1.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Textfeldes mit drei Punkten und einem Unterstrich oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TEXTFIELD_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-3.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gefüllten Mülleimers oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TRASH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-27.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Mülleimers oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TRASH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-28.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gefüllten Mülleimers mit 'X'-Symbol oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TRASH_X_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-29.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Mülleimers mit 'X'-Symbol oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TRASH_X_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-30.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Mülleimers in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File TRASH_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-thin.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Programmfensters oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Programmfensters in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File WINDOW_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-thin.svg");

	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons mit zwei Programmfenstern im dünnen Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File WINDOW_THIN_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-windows-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gefüllten Quadrat oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_SQUARE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-13.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Quadrat oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_SQUARE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-14.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-2.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gefüllten Quadrat oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-9.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gefüllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_CIRCLE_1_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-10.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_CIRCLE_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-11.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem gefüllten Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_CIRCLE_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-4.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Kreis oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_CIRCLE_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-5.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in einem Kreis in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-circle-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines 'X'-Symbols in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines gefüllten Löschen-Symbols mit 'X' oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_ARROW_LEFT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-12.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons eines Löschen-Symbols mit 'X' oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File X_MARK_ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-13.svg");

	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '+' oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ZOOM_IN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-7.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '-' oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ZOOM_OUT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-8.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '+' in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ZOOM_IN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-in-thin.svg");
	
	/** <!-- $LANGUAGE=DE -->	Speicherort des Icons einer Lupe mit '-' in dünnem Stil oder {@code null}, wenn das Icon nicht gefunden wurde */
	public static final File ZOOM_OUT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-out-thin.svg");
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->	Liste, die Speicherorte aller in dieser Klasse definierten Icons enthält */
	public static final LinkedList<File> ICON_LIST = new LinkedList<>();
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	initializing	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	static {
		// Aus den Standardeinstellungen darf nur gelesen werden!
		DEFAULT_PREFERENCES.setReadOnly();
		
		// Speicherorte der Icons in Liste speichern
		for(Field field : Resources.class.getFields()) {
			try {
				if(field.get(new File("")) instanceof File && field.get(new File("")) != CUSTOM_PREFERENCES && field.get(new File("")) != DEFAULT_PREFERENCES) {
					ICON_LIST.add((File) field.get(new File("")));
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Sucht die Ressource mit gegebenem Namen und gibt die URL als String zurück.
	 * 
	 * @param name	Name (und Pfad) der gewünschten Ressource
	 * @return		URL der gefundenen Ressource oder {@code null}, wenn die Ressource nicht gefunden werden konnte
	 * 
	 * @see Class#getResource(String)
	 */
	public static String getResourceAsExternalForm(String name) {
		String url;
		try {
			 url = Resources.class.getResource(name).toExternalForm();
		} catch (Exception e) {
			e.printStackTrace();
			url = null;
		}
		
		return url;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Sucht die Ressource mit gegebenem Namen und gibt diese als File zurück.
	 * 
	 * @param name	Name (und Pfad) der gewünschten Ressource
	 * @return		File der gewünschten Ressource oder {@code null}, wenn die Ressource nicht gefunden werden konnte
	 * 
	 * @see Class#getResource(String)
	 */
	public static File getResourceAsFile(String name) {
		File file;
		
		try {
			String url = Resources.class.getResource(name).getFile();
			file = new File(url);
		} catch (Exception e) {
			e.printStackTrace();
			file = null;
		}
		
		return file;
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
