/*
 * Copyright (c)
 * 
 * Ersteller: Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.controller;

import java.util.ArrayList;

import bitchanger.gui.controls.AlphaNumGrid;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**	<!-- $LANGUAGE=DE -->
 * Dieser Controller gibt den Bedienelementen einer {@link AlphaNumGrid} eine Funktion
 * 
 * @author Tim
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class AlphaNumGridController extends ControllerBase {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Liste, die alle Buttons der Tastatur-Matrix enthält */
	private ArrayList<Node> buttonList;
	
	/** <!-- $LANGUAGE=DE -->	Button zum Umschalten zwischen Nummernfeld und Alphabet-Ansicht */
	private Button keyboardBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem durch die Tastatur zurück gescrollt werden kann */
	private Button previousBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem durch die Tastatur vorwärts gescrollt werden kann */
	private Button nextBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem das Vorzeichen der eingegebenen Zahl gewechselt wird */
	private Button signBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem die Zahl 0 eingegeben wird */
	private Button zeroBtn;
	
	/** <!-- $LANGUAGE=DE -->	Button, mit dem ein Komma eingegeben werden kann */
	private Button commaBtn;
	
	/** <!-- $LANGUAGE=DE -->	HBox mit den Buttons, mit denen durch die Tastatur gescrollt werden kann. */
	private HBox arrowButtons;
	
	/** <!-- $LANGUAGE=DE -->	Merker für den derzeitigen Tastaturmodus */
	private boolean isShowingKeyboard;

	
	// Konstruktor	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt einen neuen Controller, der einer {@code AlphaNumGrid} eine Funktion gibt.
	 * 
	 * @param view	{@code AlphaNumGrid}, die an diesen Controller gebunden wird
	 */
	public AlphaNumGridController(AlphaNumGrid view) {
		super(view);
		this.buttonList = view.getButtonMatrix();
		this.isShowingKeyboard = false;
	}


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		keyboardBtn = buttonMap.get(AlphaNumGrid.KEYBOARD_BTN_KEY);
		previousBtn = buttonMap.get(AlphaNumGrid.PREVIOUS_BTN_KEY);
		nextBtn = buttonMap.get(AlphaNumGrid.NEXT_BTN_KEY);
		signBtn = buttonMap.get(AlphaNumGrid.SIGN_BTN_KEY);
		zeroBtn = buttonMap.get(AlphaNumGrid.ZERO_BTN_KEY);
		commaBtn = buttonMap.get(AlphaNumGrid.COMMA_BTN_KEY);
		arrowButtons = ((AlphaNumGrid) view).getArrowButtons();;
	}

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActions() {
		setKeyboardBtnAction();
		
		setPreviousBtnAction();
		
		setNextBtnAction();
		
		setPreviousBtnDisable();
		setNextBtnDisable();
		
		setCommaBinding();
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Texte der Alpha-Buttons in der Reihenfolge von {@linkplain AlphaNumGrid#ALPHA_KEYS}.
	 * Mit jedem Button wird das Zeichen für den Text inkrementiert.
	 * 
	 * @param startLetter	Zeichen, das der Erste Alpha-Button erhält
	 */
	private void setAlphaButtons(char startLetter) {
		for(String alphaKey: AlphaNumGrid.ALPHA_KEYS) {
			if(startLetter < 'A' || startLetter > 'Z') {
				startLetter = ' ';
			}
			Button b = buttonMap.get(alphaKey);
			b.setText(String.valueOf(startLetter));
			startLetter++;
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Texte der nummerischen-Buttons in der Reihenfolge von {@linkplain AlphaNumGrid#NUM_KEYS}.
	 * Jedem Button wird die Nummer aus dem Schlüssel in {@linkplain AlphaNumGrid#NUM_KEYS} zugewiesen.
	 */
	private void setNumButtons() {
		for(String numKey: AlphaNumGrid.NUM_KEYS) {
			Button b = buttonMap.get(numKey);
			
			b.setText(String.valueOf(numKey.substring(numKey.indexOf('_') + 1)));
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt bei allen Buttons die Texte. Der erste Button erhält den übergebenen Buchstaben als Text, für jeden weiteren
	 * Button in {@link #buttonList} wird der Buchstabe inkrementiert. Diese Methode setzt nur die Zeichen 'A' bis 'Z' als
	 * Text der Buttons, wird dieser Bereich verlassen wird stattdessen ein leerer String gesetzt.
	 * 
	 * @param startLetter	Buchstabe des ersten Tastatur-Buttons
	 */
	private void setAllToKeyboard(char startLetter) {
		for(int i = 0; i < 15; i++) {
			if(startLetter < 'A' || startLetter > 'Z') {
				startLetter = ' ';
			}
			((Labeled) buttonList.get(i)).setText(String.valueOf(startLetter));
			
			startLetter++;
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Bindet den Text des Komma-Buttons an das CommaProperty.
	 * 
	 * @see Preferences#getCommaProperty()
	 */
	private void setCommaBinding() {
		Preferences.getCommaProperty().addListener(new ChangeListener<Comma>() {
			@Override
			public void changed(ObservableValue<? extends Comma> observable, Comma oldComma, Comma newComma) {
				commaBtn.setText(String.valueOf(newComma.get()));
			}
		});
	}

	/** <!-- $LANGUAGE=DE -->
	 * Setzt ChangeListener beim letzten Alpha-Button und beim letzten Nummer-Button, um zu überwachen, ob das Ende des Alphabets erreicht wurde.
	 * Dadurch wird der Button zum weiter-scrollen der Alpha-Tastatur in beiden Tastaturmodi automatisch aktiviert und deaktiviert.
	 */
	private void setNextBtnDisable() {
		// ChangeListener des letzten Alpha-Buttons zum überwachen
		buttonMap.get(AlphaNumGrid.ALPHA_KEYS[AlphaNumGrid.ALPHA_KEYS.length - 1]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!isShowingKeyboard && newValue.charAt(0) >= 'Z' || newValue.charAt(0) < 'A') {
					nextBtn.setDisable(true);
				} else if(! isShowingKeyboard) {
					nextBtn.setDisable(false);
				}
			}
		});
		
		buttonMap.get(AlphaNumGrid.NUM_KEYS[AlphaNumGrid.NUM_KEYS.length - 1]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(isShowingKeyboard && newValue.charAt(0) >= 'Z' || newValue.charAt(0) == ' ') {
					nextBtn.setDisable(true);
				} else if(isShowingKeyboard) {
					nextBtn.setDisable(false);
				}
			}
		});
	}

	/** <!-- $LANGUAGE=DE -->
	 * Setzt einen ChangeListener beim ersten Alpha-Button, um zu überwachen, ob der Anfang des Alphabets erreicht wurde.
	 * Dadurch wird der Button zum zurück-scrollen der Alpha-Tastatur in beiden Tastaturmodi automatisch aktiviert und deaktiviert.
	 */
	private void setPreviousBtnDisable() {
		buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.charAt(0) <= 'A') {
					previousBtn.setDisable(true);
				}
				else {
					previousBtn.setDisable(false);
				}
			}
		});
	}

	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Funktion zum vorwärts scrollen durch die Alpha-Tastatur beim Klick auf den Button {@link #nextBtn}.
	 */
	private void setNextBtnAction() {
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isShowingKeyboard) {
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) <= 'Z' - 15) {
						setAllToKeyboard((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) + 15));
					}
				}
				else {
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) <= 'Z' - 6) {
						setAlphaButtons((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) + 6));
					}
				}
			}
		});
	}

	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Funktion zum rückwärts scrollen durch die Alpha-Tastatur beim Klick auf den Button {@link #previousBtn}.
	 */
	private void setPreviousBtnAction() {
		previousBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isShowingKeyboard) {
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) >= 'A' + 15) {
						setAllToKeyboard((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) - 15));
					}
				}
				else {
					if(buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) >= 'A' + 6) {
						setAlphaButtons((char) (buttonMap.get(AlphaNumGrid.ALPHA_KEYS[0]).getText().charAt(0) - 6));
					}
				}
			}
		});
	}

	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Funktion zum Wechseln des Tastatur-Modus beim Klick auf den Button {@link #keyboardBtn}.
	 * Wechselt das Tastaturlayout zwischen einer Kombination aus sechs Buchstaben-Buttons mit Nummernfeld
	 * und voller Buchstabenansicht.
	 * 
	 * @see #changeToKeyboard()
	 * @see #changeToNums()
	 */
	private void setKeyboardBtnAction() {
		keyboardBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (isShowingKeyboard) {
					isShowingKeyboard = false;
					changeToNums();
				}
				else {
					isShowingKeyboard = true;
					changeToKeyboard();
				}
			}
		});
	}
	

	// Methoden		##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Wechselt das Tastaturlayout in die Alphabet-Ansicht
	 */
	private void changeToKeyboard() {
		setAllToKeyboard('A');
		
		keyboardBtn.setText("NUM");
		
		GridPane.setColumnSpan(arrowButtons, 2);
		
		zeroBtn.setVisible(false);
		GridPane.setColumnIndex(signBtn, GridPane.getColumnIndex(signBtn) + 1);
	}

	/** <!-- $LANGUAGE=DE -->
	 * Wechselt das Tastaturlayout in die Kombination aus sechs Buchstaben-Buttons und Nummernfeld
	 */
	private void changeToNums() {
		setAlphaButtons('A');
		setNumButtons();
		
		keyboardBtn.setText("KEYB");
		
		GridPane.setColumnSpan(arrowButtons, 1);
		
		zeroBtn.setVisible(true);
		GridPane.setColumnIndex(signBtn, GridPane.getColumnIndex(signBtn) - 1);
	}
	
	
	
}
