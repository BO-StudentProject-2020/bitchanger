/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences;

/**	<!-- $LANGUAGE=DE -->
 * Aufzählung der Wrapper-Klasse Comma, die das Zeichen für ein Komma repräsentiert.
 * <p>
 * Die Klasse Comma wird verwendet, damit in den Einstellungen kein falsches Zeichen als Komma gesetzt werden kann.
 * </p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.3
 * @version 0.1.3
 * 
 * @see Preferences#getPrefs()
 * @see Preferences#commaProperty
 * @see Preferences#getComma()
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * Enumeration of the wrapper class, that represents the character for a comma.
 * <p>
 * This class is used, so that no wrong character can be set as comma.
 * </p>
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.3
 * @version 0.1.3
 * 
 * @see Preferences#getPrefs()
 * @see Preferences#commaProperty
 * @see Preferences#getComma()
 *
 */
public enum Comma {
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das deutsche Komma (',') repräsentiert */
	/* <!-- $LANGUAGE=EN --> Constant that represents the German comma (',') */
	COMMA_DE(','),
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das englische Komma ('.') repräsentiert */
	/* <!-- $LANGUAGE=EN --> Constant that represents the English comma ('.') */
	COMMA_EN('.');
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE --> Eingeschlossenes Kommazeichen */
	/*	<!-- $LANGUAGE=EN --> Wrapped comma sign */
	private char comma;
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Kommazeichen im Konstruktor initialisieren
	 * 
	 * @param comma Kommazeichen
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Initialization of the comma sign in the constructor
	 * 
	 * @param comma sign representing the comma
	 */
	private Comma(char comma) {
		this.comma = comma;
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/**	<!-- $LANGUAGE=DE -->
	 * Gibt das repräsentierte Kommazeichen zurück
	 * 
	 * @return das repräsentierte Kommazeichen
	 */
	/*	<!-- $LANGUAGE=EN -->
	 * Returns the represented comma sign
	 * 
	 * @return the represented comma sign
	 */
	public char get() {
		return this.comma;
	}
	
}
