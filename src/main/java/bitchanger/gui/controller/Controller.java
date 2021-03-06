/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;

/**	<!-- $LANGUAGE=DE -->
 * Schnittstelle, die einen Controller beschreibt.
 * Ein Controller wird mit einer View oder einem Control verbunden und gibt den Bedienelementen der View eine Funktion.
 * <p>
 * Die Methode {@link #setActions()} muss implementiert werden, um die Bedienelemente
 * einer View mit einer Funktion zu belegen.
 * </p>
 * <p><b>
 * Die Factory-Methode {@link #of(Controllable)} kann Controller f\u00FCr ein bestimmtes Controllable
 * erzeugen, wenn der Controller mit der Methode {@link #register(Class, Class)} angemeldet und
 * dem Controllable zugeordnet wurde.
 * </b></p>
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
/*	<!-- $LANGUAGE=EN -->
 * Interface for defining a controller.
 * The controller is connected to a View or a Control to provide functions.
 * <p>
 * This method {@link #setActions()} must be implemented to set actions for the operating elements of a view.
 * </p>
 * <p><b>
 * The factory method {@link #of (controllable)} can construct Controllers for a specific Controllable if the 
 * Controller was registered with the method {@link #register(Class, Class)} and assigned to the Controllable.
 * </b></p>
 * 
 * @author Tim Muehle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public interface Controller {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constants		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Map, die die f\u00FCr ein Controllable registrierten Controller speichert und diese verkn\u00FCpft
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Map that saves the Controllers registered for a Controllable and associates them
	 */
	public static final HashMap<Class<? extends Controllable>, Class<? extends Controller>> REGISTERED_CONTROLLERS = new HashMap<>();
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	static Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Registriert eine Controller-Klasse f\u00FCr die Factory-Methode {@link #of(Controllable)} 
	 * und ordnet diese einer Controllable-Klasse zu.
	 * 
	 * @param controllable	Controllable-Klasse, der die Controller-Klasse zugeordnet wird
	 * @param controller	Controller-Klasse, die registriert wird
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Registers a Controller class for the factory method {@link #of(Controllable)} 
	 * and assigns it to a Controllable class.
	 * 
	 * @param controllable	Controllable class to which the Controller class is assigned
	 * @param controller	Controller class that is registered
	 */
	public static void register(Class<? extends Controllable> controllable, Class<? extends Controller> controller) {
		REGISTERED_CONTROLLERS.put(controllable, controller);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Factory-Methode, die einen neuen Controller f\u00FCr das \u00FCbergebene Controllable erzeugt und zur\u00FCckgibt.
	 * <p><b>
	 * Der registrierte Controller muss einen \u00F6ffentlichen Konstruktor haben, der als einzigen Parameter
	 * das Controllable \u00FCbergeben bekommt.
	 * </b></p>
	 * <p><b>
	 * Es kann nur ein passender Controller zur\u00FCckgegeben werden, wenn ein Passendes Wertepaar in
	 * der Map {@link #REGISTERED_CONTROLLERS} vorhanden ist. Ein Controllable kann einen Controller
	 * mit der Methode {@link #register(Class, Class)} zugeordnet bekommen.
	 * </b></p>
	 * 
	 * @param c	Controllable, das durch den neuen Controller eine Funktion erhalten soll
	 * @return	neuer Controller, der an das Controllable gebunden ist oder {@code null}, wenn kein Controller registriert wurde
	 * 
	 * @see #register(Class, Class)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Factory method that creates and returns a new Controller for the given Controllable.
	 * <p><b>
	 * Note that the registered Controller must have a public Constructor that takes the
	 * Controllable as the only parameter.
	 * </b></p>
	 * <p><b>
	 * A suitable Controller can only be returned if a suitable value pair is available in the Map 
	 * {@link #REGISTERED_CONTROLLERS}. A Controllable can be assigned a Controller with the method
	 * {@link #register(Class, Class)}.
	 * </b></p>
	 * 
	 * @param c	Controllable, which should be given a function by the new Controller
	 * @return	new Controller that is bound to the Controllable or {@code null} if no Controller has been registered
	 * 
	 * @see #register(Class, Class)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public static Controller of(Controllable c) {
		return ofArg(c);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Factory-Methode, die einen neuen Controller f\u00FCr das \u00FCbergebene Controllable erzeugt und zur\u00FCckgibt.
	 * <p><b>
	 * Der registrierte Controller muss einen \u00F6ffentlichen Konstruktor haben, der als ersten Parameter
	 * das Controllable \u00FCbergeben bekommt und alle weiteren \u00FCbergebenen Objekte ebenfalls in der selben
	 * Reihenfolge erh\u00E4lt, in der diese \u00FCbergeben wurden.
	 * </b></p>
	 * <p><b>
	 * Es kann nur ein passender Controller zur\u00FCckgegeben werden, wenn ein Passendes Wertepaar in
	 * der Map {@link #REGISTERED_CONTROLLERS} vorhanden ist. Ein Controllable kann einen Controller
	 * mit der Methode {@link #register(Class, Class)} zugeordnet bekommen.
	 * </b></p>
	 * 
	 * @param c	Controllable, das durch den neuen Controller eine Funktion erhalten soll
	 * @param args Parameter in der Reihenfolge, wie diese vom Konstruktor ben\u00F6tigt werden
	 * 
	 * @return	neuer Controller, der an das Controllable gebunden ist oder {@code null}, wenn kein Controller registriert wurde
	 * 
	 * @see #register(Class, Class)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Factory method that creates and returns a new Controller for the given Controllable.
	 * <p><b>
	 * Note that the registered Controller must have a public constructor that takes the Controllable as the first 
	 * argument and also receives all other transferred Objects in the same order as given.
	 * </b></p>
	 * <p><b>
	 * A suitable Controller can only be returned if a suitable value pair is available in the Map 
	 * {@link #REGISTERED_CONTROLLERS}. A Controllable can be assigned a Controller with the method
	 * {@link #register(Class, Class)}.
	 * </b></p>
	 * 
	 * @param c	Controllable, which should be given a function by the new Controller
	 * @param args Arguments in the order they are required by the constructor
	 * 
	 * @return	new Controller that is bound to the Controllable or {@code null} if no Controller has been registered
	 * 
	 * @see #register(Class, Class)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	public static Controller ofArg(Controllable c, Object... args) {
		Class<? extends Controller> controllerClass = REGISTERED_CONTROLLERS.get(c.getClass());
		
		if(controllerClass == null) {
			return null;
		}
		
		Class<?>[] argClasses = new Class<?>[args.length + 1];
		argClasses[0] = c.getClass();
		
		for(int i = 0; i < args.length; i++) {
			argClasses[i+1] = args[i].getClass();
		}
		
		Object[] constructorArgs = Stream.concat(Stream.of(c), Arrays.stream(args)).toArray();
		
		try {
			Constructor<? extends Controller> constructor = controllerClass.getConstructor(argClasses);
			return constructor.newInstance(constructorArgs);
		} 
		catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.IGNORE, e);
			return getConstructorForArgs(controllerClass, argClasses, constructorArgs);
		}
	}
	
	
	// TODO JavaDoc
	private static Controller getConstructorForArgs(Class<? extends Controller> controllerClass, Class<?>[] argClasses, Object[] constructorArgs) {
		try {
			Constructor<?>[] constructors = controllerClass.getConstructors();

			_CONTRUCTOR_LOOP:
			for (Constructor<?> constructor : constructors) {
				if (constructor.getParameterTypes().length != argClasses.length) {
					continue;
				}

				for (int i = 0; i < argClasses.length; i++) {
					if (!constructor.getParameterTypes()[i].isAssignableFrom(argClasses[i])) {
						continue _CONTRUCTOR_LOOP;
					}
				}

				return (Controller) constructor.newInstance(constructorArgs);
			}
			
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
			e.printStackTrace();
		}
		
		return null;
	}
	
	

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt alle f\u00FCr die View ben\u00F6tigten Funktionen.
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets all functions that are needed for the view.
	 */
	public abstract void setActions();

}






