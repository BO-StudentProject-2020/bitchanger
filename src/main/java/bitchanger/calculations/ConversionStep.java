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
public class ConversionStep {
	
	private String description;
	private boolean isDescription;
	private boolean isNewStep;
	
	public ConversionStep() {
		this(null);
	}
	
	public ConversionStep(String description) {
		this(description, false);
	}
	
	public ConversionStep(String description, boolean isNewStep) {
		this.description = description;
		
		Class<?> superClass = ConversionStep.class.getSuperclass();
		this.isDescription = this.getClass().getSuperclass().equals(superClass);
		
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
		return this.isDescription;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}
