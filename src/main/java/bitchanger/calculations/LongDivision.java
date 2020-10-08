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
public class LongDivision extends Calculation<Long> {
	
	private long divident;
	private long divisor;
	private long quotient;
	private long remainder;
	
	public LongDivision(long divident, long divisor) {
		super(divident, divisor, false);
		this.divident = divident;
		this.divisor = divisor;
	}
	
	public LongDivision(long divident, long divisor, boolean calculationWasCanceled) {
		super(divident, divisor, calculationWasCanceled);
		this.divident = divident;
		this.divisor = divisor;
	}

	@Override
	protected Long calculate(Long divident, Long divisor) {
		this.quotient = divident / divisor;
		this.remainder = divident % divisor;
		return this.quotient;
	}
	
	
	public long getDivident() {
		return divident;
	}

	public long getDivisor() {
		return divisor;
	}

	public long getQuotient() {
		return quotient;
	}

	public long getRemainder() {
		return remainder;
	}
	
	@Override
	public String toString() {
		return this.divident + " : " + this.divisor + " = " + this.quotient + " remainder " + this.remainder;
	}

	
	
}
