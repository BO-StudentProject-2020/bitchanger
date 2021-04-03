/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences.writableProperty;

/** <!-- $LANGUAGE=DE -->
 * Erweiterung von {@link WritableObjectPropertyBase}, um ein Objekt von Enum zu umh\u00FCllen
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 *
 */
public class WritableEnumProperty<E extends Enum<E>> extends WritableObjectPropertyBase<E> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Enum einh\u00FCllt
	 * 
	 * @param initialValue	Startwert f\u00FCr den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	public WritableEnumProperty(E initialValue, String tagName) {
		super(initialValue, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Enum einh\u00FCllt
	 * 
	 * @param bean			Bean dieser DoubleProperty
	 * @param name			Name dieser DoubleProperty
	 * @param initialValue	Startwert f\u00FCr den umschlossenen Wert
	 * @param tagName		XML Tag-Name dieser Property
	 */
	public WritableEnumProperty(Object bean, String name, E initialValue, String tagName) {
		super(bean, name, initialValue, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Enum einh\u00FCllt
	 * 
	 * @param bean		Bean dieser DoubleProperty
	 * @param name		Name dieser DoubleProperty
	 * @param tagName	XML Tag-Name dieser Property
	 */
	public WritableEnumProperty(Object bean, String name, String tagName) {
		super(bean, name, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ObjectProperty, die ein Enum einh\u00FCllt
	 * 
	 * @param tagName	XML Tag-Name dieser Property
	 */
	public WritableEnumProperty(String tagName) {
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
		return this.get().name();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public void setData(String data) {
		this.set((E) Enum.valueOf(this.get().getClass(), data));
	}
	
}






