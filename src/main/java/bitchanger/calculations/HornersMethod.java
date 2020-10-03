/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.calculations;

import java.util.ArrayList;
import java.util.List;

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
public class HornersMethod extends ConversionStep {
	
	private List<Double> coefficients;
	private double base;
	private List<Double> products;
	private List<Double> sums;
	
	public HornersMethod(List<Double> coefficients, double base) {
		this.coefficients = coefficients;
		this.base = base;
		this.products = new ArrayList<Double>(coefficients.size());
		this.sums = new ArrayList<Double>(coefficients.size());
		
		calculate();
	}

	private void calculate() {
		products.add(Double.NaN);
		sums.add(coefficients.get(0));
		
		for(int i = 1; i < coefficients.size(); i++) {
			double product = sums.get(i - 1) * base;
			double sum = coefficients.get(i) + product;
			
			products.add(product);
			sums.add(sum);
		}
	}

	public List<Double> getCoefficients() {
		return coefficients;
	}

	public double getBase() {
		return base;
	}

	public List<Double> getProducts() {
		return products;
	}

	public List<Double> getSums() {
		return sums;
	}
	
	public double getResult() {
		return sums.get(sums.size() - 1);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(double c : coefficients) {
			sb.append("\t|\t").append(c);
		}
		
		int length =  sb.length() + ((int) sb.chars().filter(c -> { return c == '\t'; }).count() + 1) * 5 + 3;
		
		sb.append("\n");
		
		for(int i = 0; i < length; i++) {
			sb.append("-");
		}
		
		sb.append("\n").append(base);
		
		for(Double p : products) {
			sb.append("\t|\t");
			
			if(p.isNaN())
				continue;
			
			sb.append(p);
		}
		
		sb.append("\n");
		
		for(int i = 0; i < length; i++) {
			sb.append("-");
		}
		
		sb.append("\n");
		
		for(double s : sums) {
			sb.append("\t|\t").append(s);
		}
		
		return sb.toString();
	}
	
}
