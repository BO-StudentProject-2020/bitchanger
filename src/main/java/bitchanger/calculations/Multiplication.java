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
public class Multiplication extends Calculation<Double> {
	
	private double leftFactor;
	private double rigthFactor;
	
	public Multiplication() {
		this(Double.NaN, Double.NaN, true);
	}
	
	public Multiplication(double leftFactor, double rigthFactor) {
		this(leftFactor, rigthFactor, false);
	}
	
	public Multiplication(double leftFactor, double rigthFactor, boolean calculationWasCanceled) {
		super(leftFactor, rigthFactor, calculationWasCanceled);
		this.leftFactor = leftFactor;
		this.rigthFactor = rigthFactor;
	}
	
	
	@Override
	protected Double calculate(Double leftFactor, Double rigthFactor) {
		return leftFactor * rigthFactor;
	}

	public double getLeftFactor() {
		return leftFactor;
	}

	public double getRigthFactor() {
		return rigthFactor;
	}

	public double getProduct() {
		return getResult();
	}
	
}
