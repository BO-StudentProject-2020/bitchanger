/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
<<<<<<< HEAD
 * Entwickelt für das AID-Labor der Hochschule Bochum
=======
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
>>>>>>> master
 * 
 */

package bitchanger.preferences.writableProperty;


/** <!-- $LANGUAGE=DE -->
 * 
 * 
<<<<<<< HEAD
 * @author Tim Mühle
=======
 * @author Tim M\u00FChle
>>>>>>> master
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 *
 */
// TODO JavaDoc
public class WritableEnumProperty<E extends Enum<E>> extends WritableObjectPropertyBase<E> {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	// TODO JavaDoc
	public WritableEnumProperty(E initialValue, String tagName) {
		super(initialValue, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public WritableEnumProperty(Object bean, String name, E initialValue, String tagName) {
		super(bean, name, initialValue, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public WritableEnumProperty(Object bean, String name, String tagName) {
		super(bean, name, tagName);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
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






