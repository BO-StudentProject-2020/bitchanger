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
import java.util.Arrays;
import java.util.stream.Stream;

import bitchanger.gui.controls.AlphaNumKeys;
import bitchanger.preferences.Comma;
import bitchanger.preferences.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**	<!-- $LANGUAGE=DE -->
 * Dieser Controller gibt den Bedienelementen einer Instanz von {@link AlphaNumKeys} eine Funktion und bindet die Simulation der
 * Tastatur an die gekapselte Scene.
 * <p>
 * Die Hauptfunktion ist die Simulation einer Tastatur mit den alphanumerischen Buttons. Beim Klick auf einer dieser
 * Buttons werden die benötigten KeyEvents ausgelöst und an die gebundene Scene weitergeleitet.
 * Die zur weiteren zur Verfügung gestellten Funktionen sind das Umschalten zwischen den Tastaturmodi, scrollen durch die Tastatur, 
 * Aktualisierung des Komma-Buttons bei Änderung der CommaProperty aus {@link Preferences}.
 * </p>
 * <p><b>
 * Die Funktion des +/- Buttons zum Vorzeichenwechsel bleibt unbelegt, da weitere Bedienelemente benötigt werden. Die Funktion dieses
 * Buttons muss in einem anderen Controller implementiert werden.
 * </b></p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.4
 *
 */
public class AlphaNumKeysController extends ControllerBase<AlphaNumKeys> {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->	Scene, an die dieser Controller gebunden wird und die alle simulierten KeyEvents erhält */
	private Scene scene;
	
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
	 * Erzeugt einen neuen Controller, der einer {@code AlphaNumKeys} eine Funktion gibt.
	 * 
	 * @param keys	{@code AlphaNumKeys}, die an diesen Controller gebunden wird
	 * @param scene {@code Scene}, an die dieser Controller gebunden wird, um alle KeyEvents zum Simulieren einer Tastatur weiterzugeben
	 */
	public AlphaNumKeysController(AlphaNumKeys keys, Scene scene) {
		super(keys);
		this.scene = scene;
		this.buttonList = keys.getButtonMatrix();
		this.isShowingKeyboard = false;
	}


	/** {@inheritDoc} */
	@Override
	protected void initControls() {
		keyboardBtn = buttonMap.get(AlphaNumKeys.KEYBOARD_BTN_KEY);
		previousBtn = buttonMap.get(AlphaNumKeys.PREVIOUS_BTN_KEY);
		nextBtn = buttonMap.get(AlphaNumKeys.NEXT_BTN_KEY);
		signBtn = buttonMap.get(AlphaNumKeys.SIGN_BTN_KEY);
		zeroBtn = buttonMap.get(AlphaNumKeys.ZERO_BTN_KEY);
		commaBtn = buttonMap.get(AlphaNumKeys.COMMA_BTN_KEY);
		arrowButtons = controllable.getArrowButtons();
	}

	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Actions zum Umschalten zwischen den Tastaturmodi, zum scrollen durch die Tastatur, zur Aktualisierung des Komma-Buttons
	 * und simuliert die Tastatureingaben für die alphanumerischen Buttons.
	 * <p><b>
	 * Die Funktion des +/- Buttons zum Vorzeichenwechsel bleibt unbelegt, da weitere Bedienelemente benötigt werden. Die Funktion dieses
	 * Buttons muss in einem anderen Controller implementiert werden.
	 * </b></p>
	 */
	@Override
	public void setActions() {
		setKeyboardBtnAction();
		
		setPreviousBtnAction();
		setNextBtnAction();
		
		setPreviousBtnDisable();
		setNextBtnDisable();
		
		setCommaBinding();
		
		setSimulateKeyEvents();
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
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
		setAlphaButtonTexts('A');
		setNumButtonTexts();
		
		keyboardBtn.setText("KEYB");
		
		GridPane.setColumnSpan(arrowButtons, 1);
		
		zeroBtn.setVisible(true);
		GridPane.setColumnIndex(signBtn, GridPane.getColumnIndex(signBtn) - 1);
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Texte der Alpha-Buttons in der Reihenfolge von {@linkplain AlphaNumKeys#ALPHA_KEYS}.
	 * Mit jedem Button wird das Zeichen für den Text inkrementiert.
	 * 
	 * @param startLetter	Zeichen, das der Erste Alpha-Button erhält
	 */
	private void setAlphaButtonTexts(char startLetter) {
		for(String alphaKey: AlphaNumKeys.ALPHA_KEYS) {
			if(startLetter < 'A' || startLetter > 'Z') {
				startLetter = ' ';
			}
			Button b = buttonMap.get(alphaKey);
			b.setText(String.valueOf(startLetter));
			startLetter++;
		}
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Texte der nummerischen-Buttons in der Reihenfolge von {@linkplain AlphaNumKeys#NUM_KEYS}.
	 * Jedem Button wird die Nummer aus dem Schlüssel in {@linkplain AlphaNumKeys#NUM_KEYS} zugewiesen.
	 */
	private void setNumButtonTexts() {
		for(String numKey: AlphaNumKeys.NUM_KEYS) {
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
	
	
	// Bindings	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Bindet den Text des Komma-Buttons an das CommaProperty.
	 * 
	 * @see Preferences#getCommaProperty()
	 */
	private void setCommaBinding() {
		Preferences.getPrefs().commaProperty.addListener(new ChangeListener<Comma>() {
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
		buttonMap.get(AlphaNumKeys.ALPHA_KEYS[AlphaNumKeys.ALPHA_KEYS.length - 1]).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!isShowingKeyboard && newValue.charAt(0) >= 'Z' || newValue.charAt(0) < 'A') {
					nextBtn.setDisable(true);
				} else if(! isShowingKeyboard) {
					nextBtn.setDisable(false);
				}
			}
		});
		
		buttonMap.get(AlphaNumKeys.NUM_KEYS[AlphaNumKeys.NUM_KEYS.length - 1]).textProperty().addListener(new ChangeListener<String>() {
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
		buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).textProperty().addListener(new ChangeListener<String>() {
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

	
	// Actions	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Funktion zum vorwärts scrollen durch die Alpha-Tastatur beim Klick auf den Button {@link #nextBtn}.
	 */
	private void setNextBtnAction() {
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isShowingKeyboard) {
					if(buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) <= 'Z' - 15) {
						setAllToKeyboard((char) (buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) + 15));
					}
				}
				else {
					if(buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) <= 'Z' - 6) {
						setAlphaButtonTexts((char) (buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) + 6));
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
					if(buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) >= 'A' + 15) {
						setAllToKeyboard((char) (buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) - 15));
					}
				}
				else {
					if(buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) >= 'A' + 6) {
						setAlphaButtonTexts((char) (buttonMap.get(AlphaNumKeys.ALPHA_KEYS[0]).getText().charAt(0) - 6));
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Actions aller alphanumerischen Buttons und des Komma-Buttons, um mit diesen Buttons eine Tastatur zu simulieren.
	 * 
	 * @see #setSimulateKeyOnAction(Button)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private void setSimulateKeyEvents() {
		// OnActions für die alphanumerischen Buttons setzen, die in ALPHA_KEYS und NUM_KEYS definiert sind
		Stream.concat(Arrays.stream(AlphaNumKeys.ALPHA_KEYS), Arrays.stream(AlphaNumKeys.NUM_KEYS))
				.map(buttonMap::get).forEach(this::setSimulateKeyOnAction);
		
		// OnAction für den Button zur Simulation der "0"
		setSimulateKeyOnAction(zeroBtn);
		
		// OnAction für den Komma-Button
		commaBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				KeyCode commaKeyCode = KeyCode.COMMA;
				if(Preferences.getPrefs().commaProperty.get().equals(Comma.COMMA_EN)) {
					commaKeyCode = KeyCode.PERIOD;
				}
				
				simulateKey(commaBtn, commaKeyCode);
			}
		});
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Fügt einem Button die Funktion hinzu, dass dieser beim Klick einen Druck einer Taste auf der Tastatur simuliert.
	 * Die simulierte Taste wird durch den Text des Buttons festgelegt.
	 * 
	 * @param b Button, dessen Action gesetzt wird
	 * 
	 * @see #simulateKey(Button, KeyCode)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private void setSimulateKeyOnAction(Button b) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				simulateKey(b, KeyCode.getKeyCode(b.getText().toUpperCase()));				
			}
		});
	}
	
	/** <!-- $LANGUAGE=DE -->
	 * Simuliert den Druck der Taste auf einer Tastatur mit dem spezifischen {@code keycode} und feuert nacheinander die KeyEvents 
	 * {@link KeyEvent#KEY_PRESSED}, {@link KeyEvent#KEY_TYPED} und {@link KeyEvent#KEY_RELEASED}. Die Events werden an die Scene
	 * weitergeleitet, an die dieser Controller gebunden ist.
	 * 
	 * @param b			Quelle für die gefeuerten Events
	 * @param keycode	KeyCode der simulierten Taste
	 * 
	 * @see #simulateKeyEvents(Button, Node, Scene, String, String, KeyCode)
	 * 
	 * @since Bitchanger 0.1.4
	 */
	private void simulateKey(Button b, KeyCode keycode) {
		simulateKeyEvents(b, null, scene, b.getText(), "", keycode);
	}

	
	
	
	
}
