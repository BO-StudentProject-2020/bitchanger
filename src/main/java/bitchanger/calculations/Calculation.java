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
 * @since Bitchanger 0.1.8
 * @version 0.1.8
 *
 */
// TODO JavaDoc
public abstract class Calculation<T extends Number> extends ConversionStep {
	
	private T result;
	private char digit;
	private boolean calculationWasCanceled;
	
	protected Calculation(T firstValue, T secondValue, boolean calculationWasCanceled) {
		this.calculationWasCanceled = calculationWasCanceled;
		
		result = calculate(firstValue, secondValue);
		this.digit = 0;
	}
	
	protected abstract T calculate(T firstValue, T secondValue);

	public char getDigit() {
		return digit;
	}

	public void setDigit(char digit) {
		this.digit = digit;
	}

	
	public boolean calculationWasCanceled() {
		return calculationWasCanceled;
	}


	public void setCalculationWasCanceled(boolean calculationWasCanceled) {
		this.calculationWasCanceled = calculationWasCanceled;
	}
	
	public T getResult() {
		return this.result;
	}

}
