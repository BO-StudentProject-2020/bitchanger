/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controls;

import bitchanger.calculations.ConvertingNumbers;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;

/**	<!-- $LANGUAGE=DE -->
 * Spinner, mit dem die Basis für ein Zahlensystem ausgewählt werden kann.
 * 
 * <p>
 * Diese Klasse erweitert die Funktion der Klasse Spinner&lt;T&gt; so, dass der Benutzer
 * Eingaben im Editor des Spinners machen darf und der im Editor eingegebenen Wert 
 * automatisch während der Eingabe aktualisiert wird, wenn es sich um einen gültigen 
 * Wert handelt.
 * </p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.2
 * @version 0.1.4
 *
 */

/*	<!-- $LANGUAGE=EN -->
 * Spinner for choosing the base of a numeral system.
 * 
 * <p>
 * This class extends the function of the class Spinner&lt;T&gt;,so that the user
 * is able to give input into the editor of the spinner and that this input is updated automatically if it is a valid value.
 * </p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.2
 * @version 0.1.4
 *
 */
public class BaseSpinner extends Spinner<Integer>{

	/**	<!-- $LANGUAGE=DE -->
	 * Erzeugt einen BaseSpinner, bei dem die minimale und maximale einstellbare Basis durch die Konstanten 
	 * {@link ConvertingNumbers#MIN_BASE} und {@link ConvertingNumbers#MAX_BASE} gegeben sind. Der Standardwert
	 * wird mit 10 initialisiert.
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Produces a BaseSpinner whose maximum and minimum adjustable base is given through the constant 
	 * {@link ConvertingNumbers#MIN_BASE} and {@link ConvertingNumbers#MAX_BASE}.
	 * The default value is initialized as a 10.
	 */
	public BaseSpinner() {
		super(ConvertingNumbers.MIN_BASE, ConvertingNumbers.MAX_BASE, 10);
		this.setEditable(true);
		setActions();
	}

	/**	<!-- $LANGUAGE=DE -->
	 * Setzt einen EventHandler, der bei Eingabe im Editor automatisch den Wert überprüft
	 * und, wenn möglich, mit der Methode {@link #commitValue()} aktualisiert.
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Sets an EventHandler that controls the input into the editor automatically and
	 * updates it with the method {@link #commitValue()} if possible.
	 */
	private void setActions() {
		this.getEditor().addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (hasValidValue()) {
							commitValue();
							getEditor().positionCaret(getEditor().getLength());
						}
					}
				});
			}
		});
	}
	
	/**	<!-- $LANGUAGE=DE -->
	 * Prüft, ob der Text im Editor ein gültiger Wert ist und mit Methode {@link #commitValue()}
	 * aktualisiert werden kann.
	 * 
	 * @return	{@code true} wenn der Text im Editor ein gültiger Wert ist, sonst {@code false}
	 */
	
	/*	<!-- $LANGUAGE=EN -->
	 * Checks if the text inside the editor has a valid value an can be updated with the method {@link #commitValue()}.
	 * 
	 * @return	{@code true} if the text inside the editor is a valid value, if not {@code false}
	 */
	private boolean hasValidValue() {
		int min = ((SpinnerValueFactory.IntegerSpinnerValueFactory) this.getValueFactory()).getMin();
		int max = ((SpinnerValueFactory.IntegerSpinnerValueFactory) this.getValueFactory()).getMax();
		int wert;
		
		try {
			wert = Integer.parseInt(this.getEditor().getText());
		} catch (Exception e) {
			return false;
		}
		
		if(wert < min || wert > max) {
			return false;
		} else {
			return true;
		}
	}

}
