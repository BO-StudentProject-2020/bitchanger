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

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;

/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.2
 * @version 0.1.4
 *
 */
public class BaseSpinner<T> extends Spinner<T>{

	public BaseSpinner() {
		super(2, 36, 10);
		this.setEditable(true);
		setActions();
	}

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
		
	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

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
