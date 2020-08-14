/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import bitchanger.gui.controller.Controller;
import bitchanger.gui.controller.IEEEController;
import bitchanger.util.ArrayUtils;


/**	<!-- $LANGUAGE=DE -->
 * View, die die Scene für die Umwandlung zwischen dezimal Zahlen und der IEEE-Norm enthält.
 * <p><b>
 * Für diese View-Klasse wird der Controller {@link IEEEController} registriert.
 * </b></p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 * 
 * @see IEEEController
 */
public class IEEEView extends AlphaNumGridView {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Beschriftungen für die Labels vor den Textfeldern definiert */
	private static final String[] LABEL_TEXTS = {"DEC", "IEEE"};
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird */
	private static final String TF_DEC_KEY = "dec-TF";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die IEEE Darstellung in der Map {@code tfMap} gespeichert wird */
	private static final String TF_IEEE_KEY = "ieee-TF";
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller Klasse zuordnen
		Controller.register(ConverterView.class, IEEEController.class);
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Fields			   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird */
	public final String tfDecKey = "dec-TF";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die IEEE Darstellung in der Map {@code tfMap} gespeichert wird */
	public final String tfIEEEKey = "ieee-TF";
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue IEEEView mit vollständigem Scenegraphen und initialisiert die Funktionen
	 * der Bedienelemente mit einem {@link IEEEController}.
	 */
	public IEEEView() {
		super(0, 0, 0, 1, 6, 1, LABEL_TEXTS, ArrayUtils.arrayOf(TF_DEC_KEY, TF_IEEE_KEY));
		
		buildScenegraph();
	}
}












