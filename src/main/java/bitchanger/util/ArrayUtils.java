/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.util;

import java.util.ArrayDeque;
import java.util.Queue;

/** <!-- $LANGUAGE=DE -->
 * Klasse, die Werkzeug-Methoden f\u00FCr Arrays enth\u00E4lt.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.7
 *
 */
public class ArrayUtils {

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt eine Aufz\u00E4hlung von Objekten in ein Array um und gibt dieses zur\u00FCck.
	 * 
	 * @param <T>		Typ der Objekte und des zur\u00FCckgegebenen Arrays
	 * @param values	Objekte, aus denen das Array gebildet wird
	 * @return			Array aus den \u00FCbergebenen Objekten
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the given Objects to an Array
	 * 
	 * @param <T>		Type of Objects and the returned Array
	 * @param values	Objects for building an Array
	 * @return			Array from the given Objects
	 */
	@SafeVarargs
	public static <T> T[] arrayOf(T... values) {
		return values;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Wandelt eine Aufz\u00E4hlung von Objekten in eine Queue um und gibt diese zur\u00FCck.
	 * 
	 * @param <T>		Typ der Objekte und der zur\u00FCckgegebenen Queue
	 * @param values	Objekte, aus denen die Queue gebildet wird
	 * @return			Queue aus den \u00FCbergebenen Objekten
	 * 
	 * @since 0.1.7
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the given Objects to a Queue
	 * 
	 * @param <T>		Type of Objects and the returned Queue
	 * @param values	Objects for building a Queue
	 * @return			Queue from the given Objects
	 * 
	 * @since 0.1.7
	 */
	@SafeVarargs
	public static <T> Queue<T> queueOf(T... values) {
		ArrayDeque<T> queue = new ArrayDeque<>();
		
		for(T t : values) {
			queue.add(t);
		}
		
		return queue;
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private ArrayUtils() {}
	
}
