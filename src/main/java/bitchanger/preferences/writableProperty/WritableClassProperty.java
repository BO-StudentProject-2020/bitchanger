/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences.writableProperty;

import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;

/** <!-- $LANGUAGE=DE -->
 * Erweiterung von {@link WritableObjectPropertyBase}, um ein Class-Objekt zu umh\u00FCllen
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.8
 *
 */
// TODO JavaDoc EN
public class WritableClassProperty<T> extends WritableObjectPropertyBase<Class<? extends T>> {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Class-Object einh\u00FCllt
	 * 
	 * @param initialValue	Startwert f\u00FCr den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableClassProperty(Class<? extends T> initialValue, String tagName) {
		super(initialValue, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Class-Object einh\u00FCllt
	 * 
	 * @param bean			Bean dieser DoubleProperty
	 * @param name			Name dieser DoubleProperty
	 * @param initialValue	Startwert f\u00FCr den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableClassProperty(Object bean, String name, Class<? extends T> initialValue, String tagName) {
		super(bean, name, initialValue, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Class-Object einh\u00FCllt
	 * 
	 * @param bean		Bean dieser DoubleProperty
	 * @param name		Name dieser DoubleProperty
	 * @param tagName	XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableClassProperty(Object bean, String name, String tagName) {
		super(bean, name, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Class-Object einh\u00FCllt
	 * 
	 * @param tagName	XML Tag-Name dieser Property
	 */
	// TODO JavaDoc EN
	public WritableClassProperty(String tagName) {
		super(tagName);
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** {@inheritDoc} */
	@Override
	public String getData() {
		return this.get().getName();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public void setData(String data) {
		Class<? extends T> cl;
		
		try {
			cl = (Class<? extends T>) Class.forName(data);
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
			e.printStackTrace();
			return;
		}
		
		this.set(cl);
	}

}







