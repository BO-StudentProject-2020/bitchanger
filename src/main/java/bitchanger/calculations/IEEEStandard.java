/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

// TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 1.0.2
 * 
 */
public enum IEEEStandard {

	// TODO JavaDoc
	IEEE_754_2008_b16(16, 5),
	
	// TODO JavaDoc
	IEEE_754_2008_b32(32, 8);
	
	
	private final int bitLength;
	private final int expLength;
	private final int expOffset;
	
	private IEEEStandard(int bitLength, int expLength) {
		this.bitLength = bitLength;
		this.expLength = expLength;
		this.expOffset = (1 << (expLength - 1)) - 1;
	}
	
	public int getBitLength() {
		return this.bitLength;
	}
	
	public int getExpLength() {
		return this.expLength;
	}
	
	// @since Bitchanger 1.0.2
	public long maxExp() {
		return (long) (Math.pow(2, expLength) - 1);
	}
	
	// @since Bitchanger 1.0.0
	public int getMantLength() {
		return bitLength - expLength - 1;
	}
	
	// @since Bitchanger 1.0.0
	public int getExpOffset() {
		return this.expOffset;
	}
}







