/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

/** <!-- $LANGUAGE=DE -->
 * 
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.8
 * @version 0.1.8
 *
 */
// TODO JavaDoc
public class TwosComplement extends Calculation<Long> {
	
	private long value;
	
	public TwosComplement(Long value) {
		super(value, 0L, false);
		this.value = value;
	}

	@Override
	protected Long calculate(Long value, Long unused) {
		return (~value) + 1;
	}
	
	public long getValue() {
		return this.value;
	}
}






