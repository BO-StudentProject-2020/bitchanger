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
 * @version 0.1.4
 * 
 * @see Preferences#getCommaProperty()
 * @see Preferences#getComma()
 * @see Preferences#setComma(Comma)
 *
 */

/*	<!-- $LANGUAGE=EN -->
 * Enumeration of the wrapper class, that represents the character for a comma.
 * <p>
 * This class is used, so that no wrong character can be set as comma.
 * </p>
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.3
 * @version 0.1.4
 * 
 * @see Preferences#getCommaProperty()
 * @see Preferences#getComma()
 * @see Preferences#setComma(Comma)
 *
 */
public enum Comma {
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das deutsche Komma (',') repräsentiert */
	/* <!-- $LANGUAGE=EN --> Constant that represents the German comma (',') */
	COMMA_DE(','),
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das englische Komma ('.') repräsentiert */
	/* <!-- $LANGUAGE=EN --> Constant that represents the English comma ('.') */
	COMMA_EN('.');
	
	/**	<!-- $LANGUAGE=DE -->
	 * 
	 * Eingeschlossenes Komma-Zeichen
	 * 
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * 
	 * Wrapped comma sign
	 * 
	 */
	private char comma;
	
	/**	<!-- $LANGUAGE=DE -->
	 * Kommazeichen im Konstruktor initialisieren
	 * 
	 * @param comma Kommazeichen
	 * 
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Initialization of the comma sign in the constructor
	 * 
	 * @param comma comma sign
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
	
	/*	<!-- $LANGUAGE=EN -->
	 * Returns the represented comma sign
	 * 
	 * @return the represented comma sign
	 * 
	 */
	public char get() {
		return this.comma;
	}
}
