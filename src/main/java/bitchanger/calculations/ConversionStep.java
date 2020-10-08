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
public class ConversionStep {
	
	private String description;
	private boolean isNewStep;
	
	public ConversionStep() {
		this(null);
	}
	
	public ConversionStep(String description) {
		this(description, false);
	}
	
	public ConversionStep(String description, boolean isNewStep) {
		this.description = description;
		this.isNewStep = isNewStep;
	}
	
	public boolean isNewStep() {
		return isNewStep;
	}

	public void setNewStep(boolean isNewStep) {
		this.isNewStep = isNewStep;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDescription() {
		Class<?> superClass = ConversionStep.class.getSuperclass();
		return this.getClass().getSuperclass().equals(superClass);
	}
	
	public boolean hasDescription() {
		return this.description == null ? false : ! this.description.equals("");
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}
