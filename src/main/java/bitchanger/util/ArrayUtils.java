/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.util;

import java.util.ArrayDeque;
import java.util.Queue;

/** <!-- $LANGUAGE=DE -->
 * Klasse, die Werkzeug-Methoden für Arrays enthält.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.7
 *
 */
public class ArrayUtils {

	/** <!-- $LANGUAGE=DE -->
	 * Wandelt eine Aufzählung von Objekten in ein Array um und gibt dieses zurück.
	 * 
	 * @param <T>		Typ der Objekte und des zurückgegebenen Arrays
	 * @param values	Objekte, aus denen das Array gebildet wird
	 * @return			Array aus den übergebenen Objekten
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Converts the given Objects to an Array
	 * 
	 * @param <T>		Type of Objects and the returned Array
	 * @param values	Objects to for building an Array
	 * @return			Array from the given Objects
	 */
	@SafeVarargs
	public static <T> T[] arrayOf(T... values) {
		return values;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc @since 0.1.7
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
