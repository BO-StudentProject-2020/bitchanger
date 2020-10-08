/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.util;

/** <!-- $LANGUAGE=DE -->
 * Repr\u00E4sentiert eine Operation, die drei \u00DCbergabeparameter akzeptiert und keine R\u00FCckgabe hat.
 * 
 * @author Tim M\u00FChle
 *
 * @param <T>	Typ des ersten \u00DCbergabeparameters
 * @param <U>	Typ des zweiten \u00DCbergabeparameters
 * @param <V>	Typ des dritten \u00DCbergabeparameters
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
	 * F\u00FChrt diese Operation mit den \u00FCbergebenen Argumenten aus.
	 * 
	 * @param t	erster \u00DCbergabeparameter
	 * @param u	zweiter \u00DCbergabeparameter
	 * @param v	dritter \u00DCbergabeparameter
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
