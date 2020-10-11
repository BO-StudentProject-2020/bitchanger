/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences;

/**	<!-- $LANGUAGE=DE -->
 * Aufz\u00E4hlung der Wrapper-Klasse Comma, die das Zeichen f\u00FCr ein Komma repr\u00E4sentiert.
 * <p>
 * Die Klasse Comma wird verwendet, damit in den Einstellungen kein falsches Zeichen als Komma gesetzt werden kann.
 * </p>
 * 
 * @author Tim M\u00FChle
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
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das deutsche Komma (',') repr\u00E4sentiert */
	/* <!-- $LANGUAGE=EN --> Constant that represents the German comma (',') */
	COMMA_DE(','),
	
	/** <!-- $LANGUAGE=DE --> Konstante, die das englische Komma ('.') repr\u00E4sentiert */
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
	 * Gibt das repr\u00E4sentierte Kommazeichen zur\u00FCck
	 * 
	 * @return das repr\u00E4sentierte Kommazeichen
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
