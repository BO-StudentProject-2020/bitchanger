/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
<<<<<<< HEAD
 * Entwickelt für das AID-Labor der Hochschule Bochum
=======
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
>>>>>>> master
 * 
 */

package bitchanger.calculations;


/** <!-- $LANGUAGE=DE -->
 * 
 * 
<<<<<<< HEAD
 * @author Tim Mühle
=======
 * @author Tim M\u00FChle
>>>>>>> master
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 *
 */
// TODO JavaDoc
public class NumberOverflowException extends UnsupportedOperationException {

	private static final long serialVersionUID = 4742919788542711863L;
	
	private String description;
	private long maxSupportetNumber;
	private long minSupportetNumber;

	public NumberOverflowException(String message, String description, long maxSupportetNumber, long minSupportetNumber, Throwable cause) {
		super(message, cause);
		this.description = description;
		this.maxSupportetNumber = maxSupportetNumber;
		this.minSupportetNumber = minSupportetNumber;
	}

	public NumberOverflowException(String message, String description, long maxSupportetNumber, long minSupportetNumber) {
		super(message);
		this.description = description;
		this.maxSupportetNumber = maxSupportetNumber;
		this.minSupportetNumber = minSupportetNumber;
	}

	public long getMaxSupportetNumber() {
		return this.maxSupportetNumber;
	}

	public long getMinSupportetNumber() {
		return this.minSupportetNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}





