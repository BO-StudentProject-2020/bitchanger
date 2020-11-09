/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.preferences;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**	<!-- $LANGUAGE=DE -->
 * Schnittstelle, die Methoden definiert, um ein Objekt als XML-Tag darzustellen
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 *
 */
// TODO JavaDoc EN
public interface XMLWritable {

//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	default Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Erzeugt ein XML-Tag f\u00FCr dieses Objekt aus den Methoden {@link #getTagName()} und {@link #getData()} und
	 * gibt diesen als {@link Element} zur\u00FCck.
	 * 
	 * @param xmlDoc {@link Document} f\u00FCr das ein XML-Tag erstellt wird
	 * @return {@link Element} mit dem Tag-Namen {@link #getTagName()}, das den String {@link #getData()} als Textknoten enth\u00E4lt
	 */
	// TODO JavaDoc EN
	default Element getXMLTag(Document xmlDoc) {
		Element tag = xmlDoc.createElement(this.getTagName());
		tag.appendChild(xmlDoc.createTextNode(this.getData()));
		return tag;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**	<!-- $LANGUAGE=DE -->
	 * Sucht ein {@link Element} mit dem Tag-Namen {@link #getTagName()} im \u00FCbergebenen {@code parentTag}
	 * und \u00FCbergibt im Anschluss die gefundenen Daten an {@link #setData(String)}. Es wird nur der erste
	 * gefundene Knoten mit passendem Tag-Namen beachtet.
	 * 
	 * @param parentTag	XML-Tag, in dem nach einem passenden Knoten gesucht wird
	 */
	// TODO JavaDoc EN
	default void setFromXMLTag(Element parentTag) {
		String data = parentTag.getElementsByTagName(this.getTagName()).item(0).getTextContent();
		this.setData(data);
	}
	
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	abstract Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt den Namen f\u00FCr den XML-Tag dieses Objektes zur\u00FCck
	 * 
	 * @return Name f\u00FCr den XML-Tag dieses Objektes
	 */
	// TODO JavaDoc EN
	public abstract String getTagName();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt den Namen f\u00FCr den XML-Tag dieses Objektes
	 * 
	 * @param tagName	Name f\u00FCr den XML-Tag dieses Objektes
	 */
	// TODO JavaDoc EN
	public abstract void setTagName(String tagName);
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**	<!-- $LANGUAGE=DE -->
	 * Gibt die gekapselten Daten dieses Objektes als String zur\u00FCck
	 * 
	 * @return gekapselten Daten dieses Objektes als String
	 */
	// TODO JavaDoc EN
	public abstract String getData();
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	/**	<!-- $LANGUAGE=DE -->
	 * Stellt alle Daten dieses Objektes aus dem String wieder her, der
	 * von der Methode {@link #getData()} erzeugt wurde
	 * 
	 * @param data	zu setzende Daten dieses Objektes als String
	 */
	// TODO JavaDoc EN
	public abstract void setData(String data);
	
	
	
}







