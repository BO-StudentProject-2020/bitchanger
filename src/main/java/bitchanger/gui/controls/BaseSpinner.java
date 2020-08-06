/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
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
 * Diese Klasse erweitert die Funktion der Klasse Spinner<T> so, dass der Benutzer
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
public class BaseSpinner extends Spinner<Integer>{

	/**	<!-- $LANGUAGE=DE -->
	 * Erzeugt einen BaseSpinner, bei dem die minimale und maximale einstellbare Basis durch die Konstanten 
	 * {@link ConvertingNumbers#MIN_BASE} und  {@link ConvertingNumbers#MAX_BASE} gegeben sind. Der Standardwert
	 * wird mit 10 initialisiert.
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
