/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 * 
 */
// TODO JavaDoc
public enum BitLength {
	// TODO JavaDoc
	_8_BIT("8-Bit", 8, 0xFF), _16_BIT("16-Bit", 16, 0xFFFF), _32_BIT("32-Bit", 32, 0xFFFFFFFF), UNKNOWN("Unbekannt", -1, 0x0);

	private String description;
	private int numberOfBits;
	private long bitmask;

	private BitLength(String description, int numberOfBits, long bitmask) {
		this.description = description;
		this.numberOfBits = numberOfBits;
		this.bitmask = bitmask;
	}

	@Override
	public String toString() {
		return description;
	}
	
	public int getNumberOfBits() {
		return this.numberOfBits;
	}
	
	public long getBitmask() {
		return this.bitmask;
	}
	
}




