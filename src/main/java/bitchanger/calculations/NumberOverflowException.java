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





