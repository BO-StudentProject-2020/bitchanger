/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;


/** <!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 *
 */
// TODO JavaDoc
public class NumberOverflowException extends UnsupportedOperationException {

	private static final long serialVersionUID = 4742919788542711863L;

	public NumberOverflowException() {
		super();
	}

	public NumberOverflowException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumberOverflowException(String message) {
		super(message);
	}

	public NumberOverflowException(Throwable cause) {
		super(cause);
	}
	
	
}





