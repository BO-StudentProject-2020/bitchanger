package bitchanger.util;

import java.io.File;

public class Resources {
	
	// TODO formatieren + JavaDoc	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!

	public static final String KEYBORD_ICON = getResourceAsExternalForm("/graphic/keyboard-icon.png");
	
	public static final String LAYOUT_CSS = getResourceAsExternalForm("/style/bitchangerLayout.css");

	public static final String LIGHT_CSS = getResourceAsExternalForm("/style/bitchangerLightTheme.css");
	
	public static final String DARK_CSS = getResourceAsExternalForm("/style/bitchangerDarkTheme.css");
	
	public static final File CUSTOM_PREFERENCES = getResourceAsFile("/preferences/CustomSettings.prefs");
	
	public static final File DEFAULT_PREFERENCES = getResourceAsFile("/preferences/DefaultSettings.prefs");
	
	static {
		DEFAULT_PREFERENCES.setReadOnly();
	}
	
	
	public static String getResourceAsExternalForm(String name) {
		String url;
		try {
			 url = Resources.class.getResource(name).toExternalForm();
		} catch (Exception e) {
			url = null;
		}
		
		return url;
	}
	
	public static File getResourceAsFile(String name) {
		File file;
		
		try {
			String url = Resources.class.getResource(name).getFile();
			file = new File(url);
		} catch (Exception e) {
			file = null;
		}
		
		return file;
	}
	
	private Resources() {};
	
}
