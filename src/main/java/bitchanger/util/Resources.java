package bitchanger.util;

public class Resources {
	
	// TODO formatieren + JavaDoc	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!

	public static final String KEYBORD_ICON = getResource("/graphic/keyboard-icon.png");
	
	public static final String LIGHT_CSS = getResource("/style/bitchangerLight.css");
	
	public static final String DARK_CSS = getResource("/style/bitchangerDark.css");
	
	public static final String DEFAULT_PREFERENCES = getResource("/preferences/DefaultPreferences.xml");
	
	
	public static String getResource(String name) {
		String url;
		try {
			 url = Resources.class.getResource(name).toExternalForm();
		} catch (Exception e) {
			url = null;
		}
		
		return url;
	}
	
	private Resources() {};
	
}
