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
	_8_BIT("8-Bit", 8), _16_BIT("16-Bit", 16), _32_BIT("32-Bit", 32), _64_BIT("64-Bit", 64), UNKNOWN("Unbekannt", -1);

	private String description;
	private int numberOfBits;

	private BitLength(String description, int numberOfBits) {
		this.description = description;
		this.numberOfBits = numberOfBits;
	}

	@Override
	public String toString() {
		return description;
	}
	
	public int getNumberOfBits() {
		return this.numberOfBits;
	}
}
