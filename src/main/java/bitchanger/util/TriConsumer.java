/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.util;

/** <!-- $LANGUAGE=DE -->
 * Repräsentiert eine Operation, die drei Übergabeparameter akzeptiert und keine Rückgabe hat.
 * 
 * @author Tim Mühle
 *
 * @param <T>	Typ des ersten Übergabeparameters
 * @param <U>	Typ des zweiten Übergabeparameters
 * @param <V>	Typ des dritten Übergabeparameters
 */
/* <!-- $LANGUAGE=EN -->
 * Represents an operation that accepts three input arguments and returns no result.
 * 
 * @author Tim Muehle
 *
 * @param <T>	the type of the first argument to the operation
 * @param <U>	the type of the second argument to the operation
 * @param <V>	the type of the third argument to the operation
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {

	/** <!-- $LANGUAGE=DE -->
	 * Führt diese Operation mit den übergebenen Argumenten aus.
	 * 
	 * @param t	erster Übergabeparameter
	 * @param u	zweiter Übergabeparameter
	 * @param v	dritter Übergabeparameter
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Performs this operation on the given arguments.
	 * 
	 * @param t	the first input argument
	 * @param u	the second input argument
	 * @param v	the third input argument
	 */
	public abstract void accept(T t, U u, V v);
	
}
