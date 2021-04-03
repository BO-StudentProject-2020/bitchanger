/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences.writableProperty;

import bitchanger.preferences.XMLWritable;
import javafx.beans.property.SimpleObjectProperty;

/** <!-- $LANGUAGE=DE -->
 * Erweiterung von {@link SimpleObjectProperty} als {@link XMLWritable}
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 *
 */
public abstract class WritableObjectPropertyBase<T> extends SimpleObjectProperty<T> implements XMLWritable {
	
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
	 * Erzeugt eine neue ObjectProperty
	 * 
	 * @param tagName	XML Tag-Name dieser Property
	 */
	public WritableObjectPropertyBase(String tagName) {
		super();
		this.tagName = tagName;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty
	 * 
	 * @param bean			Bean dieser ObjectProperty
	 * @param name			Name dieser ObjectProperty
	 * @param initialValue	Startwert f\u00FCr den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	public WritableObjectPropertyBase(Object bean, String name, T initialValue, String tagName) {
		super(bean, name, initialValue);
		this.tagName = tagName;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty
	 * 
	 * @param bean		Bean dieser ObjectProperty
	 * @param name		Name dieser ObjectProperty
	 * @param tagName	XML Tag-Name dieser Property
	 */
	public WritableObjectPropertyBase(Object bean, String name, String tagName) {
		super(bean, name);
		this.tagName = tagName;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty
	 * 
	 * @param initialValue	Startwert f\u00FCr den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	public WritableObjectPropertyBase(T initialValue, String tagName) {
		super(initialValue);
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
	
}






