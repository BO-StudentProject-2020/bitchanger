package bitchanger.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class Resources {
	
	// TODO formatieren + JavaDoc	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!

	public static final String LAYOUT_CSS = getResourceAsExternalForm("/style/bitchangerLayout.css");

	public static final String LIGHT_CSS = getResourceAsExternalForm("/style/bitchangerLightTheme.css");
	
	public static final String DARK_CSS = getResourceAsExternalForm("/style/bitchangerDarkTheme.css");
	
	public static final File CUSTOM_PREFERENCES = getResourceAsFile("/preferences/CustomSettings.prefs");
	
	public static final File DEFAULT_PREFERENCES = getResourceAsFile("/preferences/DefaultSettings.prefs");

	
	public static final File ABC_BRICKS_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-school-8.svg");
	public static final File ABC_BRICKS_ICON = getResourceAsFile("/graphic/svg/iconmonstr-school-9.svg");
	
	public static final File ANGEL_DOWN_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-down-circle-thin.svg");
	public static final File ANGEL_LEFT_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-left-circle-thin.svg");
	public static final File ANGEL_RIGHT_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-right-circle-thin.svg");
	public static final File ANGEL_UP_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-up-circle-thin.svg");
	
	public static final File ANGEL_DOWN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-down-thin.svg");
	public static final File ANGEL_LEFT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-left-thin.svg");
	public static final File ANGEL_RIGHT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-right-thin.svg");
	public static final File ANGEL_UP_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-angel-up-thin.svg");
	
	public static final File ARROW_DOWN_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-69.svg");
	public static final File ARROW_LEFT_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-68.svg");
	public static final File ARROW_RIGHT_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-67.svg");
	public static final File ARROW_UP_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-70.svg");
	
	public static final File ARROW_DOWN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-65.svg");
	public static final File ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-64.svg");
	public static final File ARROW_RIGHT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-63.svg");
	public static final File ARROW_UP_ICON = getResourceAsFile("/graphic/svg/iconmonstr-arrow-66.svg");
	
	public static final File CALCULATOR_SYMBOLS_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-1.svg");
	public static final File CALCULATOR_SYMBOLS_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-2.svg");
	public static final File CALCULATOR_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-5.svg");
	public static final File CALCULATOR_ICON = getResourceAsFile("/graphic/svg/iconmonstr-calculator-6.svg");
	
	public static final File COPYRIGHT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-copyright-1.svg");
	public static final File COPYRIGHT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-copyright-2.svg");
	
	public static final File ERASER_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-eraser-1.svg");
	public static final File ERASER_ICON = getResourceAsFile("/graphic/svg/iconmonstr-eraser-2.svg");
	
	public static final File ENTER_FULLSCREEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-2.svg");
	public static final File EXIT_FULLSCREEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-3.svg");
	public static final File FULLSCREEN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-fullscreen-thin.svg");

	public static final File IDEA_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-1.svg");
	public static final File IDEA_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-2.svg");
	public static final File IDEA_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-9.svg");
	public static final File IDEA_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-idea-10.svg");
	
	public static final File INFO_CIRCLE_SIMPLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-1.svg");
	public static final File INFO_CIRCLE_SIMPLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-2.svg");
	public static final File INFO_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-5.svg");
	public static final File INFO_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-6.svg");
	public static final File INFO_SPEECH_BOX_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-11.svg");
	public static final File INFO_SPEECH_BOX_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-12.svg");
	public static final File INFO_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-info-thin.svg");
	
	public static final File KEYBORD_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-1.svg");
	public static final File KEYBORD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-2.svg");
	public static final File KEYBORD_COMPACT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-3.svg");
	public static final File KEYBORD_COMPACT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-4.svg");
	public static final File KEYBORD_OPEN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-5.svg");
	public static final File KEYBORD_OPEN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-6.svg");
	public static final File KEYBORD_CLOSE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-7.svg");
	public static final File KEYBORD_CLOSE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-keyboard-8.svg");
	
	public static final File LIGHT_BULB_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-5.svg");
	public static final File LIGHT_BULB_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-11.svg");
	public static final File LIGHT_BULB_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-12.svg");
	public static final File LIGHT_BULB_3_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-15.svg");
	public static final File LIGHT_BULB_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-light-bulb-16.svg");

	public static final File MAGIC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magic-1.svg");
	public static final File MAGIC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magic-9.svg");

	public static final File MENU_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-2.svg");
	public static final File MENU_DOTS_HORIZONTAL_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-7.svg");
	public static final File MENU_DOTS_VERTICAL_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-11.svg");
	public static final File MENU_DOTS_HORIZONTAL_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-dot-horizontal-thin.svg");
	public static final File MENU_DOTS_VERTICAL_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-dot-vertical-thin.svg");
	public static final File MENU_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-menu-thin.svg");

	public static final File PAINT_BRUSH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-3.svg");
	public static final File PAINT_BRUSH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-4.svg");
	public static final File PAINT_BRUSH_WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-10.svg");
	public static final File PAINT_BRUSH_BUCKET_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-11.svg");
	public static final File PAINT_BRUSH_BUCKET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-12.svg");
	
	public static final File PREFERENCES_GEAR_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-1.svg");
	public static final File PREFERENCES_GEAR_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-10.svg");
	public static final File PREFERENCES_GEAR_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-11.svg");
	public static final File PREFERENCES_GEAR_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-thin.svg");
	public static final File PREFERENCES_WRENCH_RESET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-11.svg");
	public static final File PREFERENCES_WRENCH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-25.svg");
	
	public static final File QUESTION_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-2.svg");
	public static final File QUESTION_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-3.svg");
	public static final File QUESTION_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-5.svg");
	public static final File QUESTION_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-6.svg");
	public static final File QUESTION_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-question-thin.svg");
	
	public static final File SUN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-1.svg");
	public static final File SUN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-2.svg");
	
	public static final File SYNC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-3.svg");
	public static final File SYNC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-6.svg");
	
	public static final File TEXTFIELD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-1.svg");
	public static final File TEXTFIELD_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-3.svg");

	public static final File TRASH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-27.svg");
	public static final File TRASH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-28.svg");
	public static final File TRASH_X_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-29.svg");
	public static final File TRASH_X_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-30.svg");
	public static final File TRASH_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-thin.svg");

	public static final File WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-4.svg");
	public static final File WINDOW_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-thin.svg");
	public static final File WINDOW_THIN_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-windows-thin.svg");
	
	public static final File X_MARK_SQUARE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-13.svg");
	public static final File X_MARK_SQUARE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-14.svg");
	public static final File X_MARK_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-2.svg");
	public static final File X_MARK_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-9.svg");
	public static final File X_MARK_CIRCLE_1_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-10.svg");
	public static final File X_MARK_CIRCLE_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-11.svg");
	public static final File X_MARK_CIRCLE_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-4.svg");
	public static final File X_MARK_CIRCLE_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-5.svg");
	public static final File X_MARK_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-circle-thin.svg");
	public static final File X_MARK_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-thin.svg");
	public static final File X_MARK_ARROW_LEFT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-12.svg");
	public static final File X_MARK_ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-13.svg");

	public static final File ZOOM_IN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-7.svg");
	public static final File ZOOM_OUT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-8.svg");
	public static final File ZOOM_IN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-in-thin.svg");
	public static final File ZOOM_OUT_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-out-thin.svg");
	
	public static final LinkedList<File> ICON_LIST = new LinkedList<>();
	
	static {
		DEFAULT_PREFERENCES.setReadOnly();
		
		for(Field field : Resources.class.getFields()) {
			try {
				if(field.get(new File("")) instanceof File && field.get(new File("")) != CUSTOM_PREFERENCES && field.get(new File("")) != DEFAULT_PREFERENCES) {
					ICON_LIST.add((File) field.get(new File("")));
					System.out.println(field.get(new File("")));
				}
				
				System.out.print(field.get(new File("")) + " -> ");
				System.out.println(field.get(new File("")) instanceof File);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		ICON_LIST.addAll(Arrays.asList(ABC_BRICKS_FILLED_ICON, ABC_BRICKS_ICON));
		ICON_LIST.addAll(Arrays.asList(ANGEL_DOWN_CIRCLE_THIN_ICON, ANGEL_LEFT_CIRCLE_THIN_ICON, ANGEL_RIGHT_CIRCLE_THIN_ICON, ANGEL_UP_CIRCLE_THIN_ICON));
		
		ANGEL_DOWN_THIN_ICON, ANGEL_LEFT_THIN_ICON, ANGEL_RIGHT_THIN_ICON, ANGEL_UP_THIN_ICON
		
		ARROW_DOWN_CIRCLE_FILLED_ICON, ARROW_LEFT_CIRCLE_FILLED_ICON, ARROW_RIGHT_CIRCLE_FILLED_ICON, ARROW_UP_CIRCLE_FILLED_ICON
		
		ARROW_DOWN_ICON, ARROW_LEFT_ICON, ARROW_RIGHT_ICON, ARROW_UP_ICON
		
		CALCULATOR_SYMBOLS_FILLED_ICON, CALCULATOR_SYMBOLS_ICON, CALCULATOR_FILLED_ICON, CALCULATOR_ICON
		
		COPYRIGHT_FILLED_ICON, COPYRIGHT_ICON
		
		ERASER_FILLED_ICON, ERASER_ICON
		
		ENTER_FULLSCREEN_ICON, EXIT_FULLSCREEN_ICON, FULLSCREEN_THIN_ICON

		IDEA_FILLED_ICON, IDEA_ICON, IDEA_SPEECH_BUBBLE_FILLED_ICON, IDEA_SPEECH_BUBBLE_ICON
		
		INFO_CIRCLE_SIMPLE_FILLED_ICON, INFO_CIRCLE_SIMPLE_ICON, INFO_CIRCLE_FILLED_ICON, INFO_CIRCLE_ICON, INFO_SPEECH_BOX_FILLED_ICON, INFO_SPEECH_BOX_ICON, INFO_CIRCLE_THIN_ICON
		
		KEYBORD_FILLED_ICON, KEYBORD_ICON, KEYBORD_COMPACT_FILLED_ICON, KEYBORD_COMPACT_ICON, KEYBORD_OPEN_FILLED_ICON, KEYBORD_OPEN_ICON, KEYBORD_CLOSE_FILLED_ICON, KEYBORD_CLOSE_ICON
		
		LIGHT_BULB_1_ICON, LIGHT_BULB_2_FILLED_ICON, LIGHT_BULB_2_ICON, LIGHT_BULB_3_FILLED_ICON, LIGHT_BULB_3_ICON

		MAGIC_1_ICON, MAGIC_2_ICON

		MENU_ICON, MENU_DOTS_HORIZONTAL_FILLED_ICON, MENU_DOTS_VERTICAL_FILLED_ICON, MENU_DOTS_HORIZONTAL_ICON, MENU_DOTS_VERTICAL_ICON, MENU_THIN_ICON

		public static final File PAINT_BRUSH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-3.svg");
		public static final File PAINT_BRUSH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-4.svg");
		public static final File PAINT_BRUSH_WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paintbrush-10.svg");
		public static final File PAINT_BRUSH_BUCKET_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-11.svg");
		public static final File PAINT_BRUSH_BUCKET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-paint-bucket-12.svg");
		
		public static final File PREFERENCES_GEAR_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-1.svg");
		public static final File PREFERENCES_GEAR_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-10.svg");
		public static final File PREFERENCES_GEAR_3_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-11.svg");
		public static final File PREFERENCES_GEAR_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-gear-thin.svg");
		public static final File PREFERENCES_WRENCH_RESET_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-11.svg");
		public static final File PREFERENCES_WRENCH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-wrench-25.svg");
		
		public static final File QUESTION_CIRCLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-2.svg");
		public static final File QUESTION_CIRCLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-3.svg");
		public static final File QUESTION_SPEECH_BUBBLE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-5.svg");
		public static final File QUESTION_SPEECH_BUBBLE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-help-6.svg");
		public static final File QUESTION_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-question-thin.svg");
		
		public static final File SUN_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-1.svg");
		public static final File SUN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-weather-2.svg");
		
		public static final File SYNC_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-3.svg");
		public static final File SYNC_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-synchronization-6.svg");
		
		public static final File TEXTFIELD_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-1.svg");
		public static final File TEXTFIELD_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-password-3.svg");

		public static final File TRASH_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-27.svg");
		public static final File TRASH_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-28.svg");
		public static final File TRASH_X_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-29.svg");
		public static final File TRASH_X_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-30.svg");
		public static final File TRASH_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-trash-can-thin.svg");

		public static final File WINDOW_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-4.svg");
		public static final File WINDOW_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-window-thin.svg");
		public static final File WINDOW_THIN_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-windows-thin.svg");
		
		public static final File X_MARK_SQUARE_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-13.svg");
		public static final File X_MARK_SQUARE_ICON = getResourceAsFile("/graphic/svg/iconmonstr-checkbox-14.svg");
		public static final File X_MARK_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-2.svg");
		public static final File X_MARK_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-9.svg");
		public static final File X_MARK_CIRCLE_1_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-10.svg");
		public static final File X_MARK_CIRCLE_1_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-11.svg");
		public static final File X_MARK_CIRCLE_2_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-4.svg");
		public static final File X_MARK_CIRCLE_2_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-5.svg");
		public static final File X_MARK_CIRCLE_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-circle-thin.svg");
		public static final File X_MARK_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-thin.svg");
		public static final File X_MARK_ARROW_LEFT_FILLED_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-12.svg");
		public static final File X_MARK_ARROW_LEFT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-x-mark-13.svg");

		public static final File ZOOM_IN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-7.svg");
		public static final File ZOOM_OUT_ICON = getResourceAsFile("/graphic/svg/iconmonstr-magnifier-8.svg");
		public static final File ZOOM_IN_THIN_ICON = getResourceAsFile("/graphic/svg/iconmonstr-zoom-in-thin.svg");
		public static final File ZOOM_OUT_THIN_ICON));
		
		*/
		
	}
	
	
	public static String getResourceAsExternalForm(String name) {
		String url;
		try {
			 url = Resources.class.getResource(name).toExternalForm();
			 System.out.println("Resource " + name + " initialized");
		} catch (Exception e) {
			url = null;
			System.out.println("Resource " + name + " not found");
		}
		
		return url;
	}
	
	public static File getResourceAsFile(String name) {
		File file;
		
		try {
			String url = Resources.class.getResource(name).getFile();
			file = new File(url);
			System.out.println("Resource " + name + " initialized");
		} catch (Exception e) {
			file = null;
			System.out.println("Resource " + name + " not found");
		}
		
		return file;
	}
	
	private Resources() {};
	
}
