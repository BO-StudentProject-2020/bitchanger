package bitchanger.preferences;

/**	<!-- $LANGUAGE=DE -->
 * Aufzählung der Wrapper-Klasse Comma, die das Zeichen für ein Komma repräsentiert.
 * <p>
 * Die Klasse Comma wird verwendet, damit in den Einstellungen kein falsches Zeichen als Komma gesetzt werden kann.
 * </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.3
 * @version 0.1.3
 * 
 * @see Preferences#getCommaProperty()
 * @see Preferences#getComma()
 * @see Preferences#setComma(Comma)
 *
 */
public enum Comma {
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das deutsche Komma (',') repräsentiert */
	COMMA_DE(','),
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das englische Komma ('.') repräsentiert */
	COMMA_EN('.');
	
	/**	<!-- $LANGUAGE=DE -->
	 * 
	 * Eingeschlossenes Komma-Zeichen
	 * 
	 */
	private char comma;
	
	/**	<!-- $LANGUAGE=DE -->
	 * Kommazeichen im Konstruktor initialisieren
	 * 
	 * @param comma Kommazeichen
	 * 
	 */
	private Comma(char comma) {
		this.comma = comma;
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt das repräsentierte Komma-Zeichen zurück
	 * 
	 * @return das repräsentierte Komma-Zeichen zurück
	 * 
	 */
	public char get() {
		return this.comma;
	}
}
