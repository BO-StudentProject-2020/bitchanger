/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences.writableProperty;

import bitchanger.preferences.XMLWritable;
import javafx.beans.property.SimpleDoubleProperty;

/** <!-- $LANGUAGE=DE -->
 * Erweiterung von {@link SimpleDoubleProperty} als {@link XMLWritable}
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 *
 */
// TODO JavaDoc EN
public class WritableDoubleProperty extends SimpleDoubleProperty implements XMLWritable {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->	XML Tag-Name dieses Objektes */
	/* <!-- $LANGUAGE=EN -->	XML Tag-Name for this object */
	private String tagName;
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue DoubleProperty
	 * 
	 * @param tagName	XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableDoubleProperty(String tagName) {
		super();
		this.tagName = tagName;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue DoubleProperty
	 * 
	 * @param initialValue	Startwert für den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableDoubleProperty(double initialValue, String tagName) {
		super(initialValue);
		this.tagName = tagName;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue DoubleProperty
	 * 
	 * @param bean			Bean dieser DoubleProperty
	 * @param name			Name dieser DoubleProperty
	 * @param initialValue	Startwert für den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableDoubleProperty(Object bean, String name, double initialValue, String tagName) {
		super(bean, name, initialValue);
		this.tagName = tagName;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue DoubleProperty
	 * 
	 * @param bean		Bean dieser DoubleProperty
	 * @param name		Name dieser DoubleProperty
	 * @param tagName	XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableDoubleProperty(Object bean, String name, String tagName) {
		super(bean, name);
		this.tagName = tagName;
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	public String getTagName() {
		return this.tagName;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	
	/** {@inheritDoc} */
	@Override
	public String getData() {
		return String.valueOf(this.get());
	}
	

	/** {@inheritDoc} */
	@Override
	public void setData(String data) {
		this.set(Double.parseDouble(data));
	}
	
}






