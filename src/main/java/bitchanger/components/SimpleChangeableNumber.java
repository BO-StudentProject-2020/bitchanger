package bitchanger.components;

import java.util.Objects;

import bitchanger.preferences.Preferences;

public class SimpleChangeableNumber implements ChangeableNumber {

	
	// Attribute
	private String binValue;
	private String decValue;
	private String hexValue;
	private String octalValue;

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * �berschreibt alle Werte dieser {@code Zahl} mit dem hexadezimalen Wert
	 * {@code binary}
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der uebergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen ein '-' ist, da
	 *                                       negative Zahlen nicht unterstuetzt
	 *                                       werden
	 * @param hex hexadezimaler Wert in der {@code String} Darstellung, mit dem
	 *            diese {@code Zahl} ueberschriben wird
	 */
	@Override
	public void setHex(String hexValue) {
		this.initDezimalString(ConvertingNumbers.baseToDecString(16, hexValue.toUpperCase(), Preferences.getComma()));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * �berschreibt alle Werte dieser {@code Zahl} mit dem Parameter
	 * {@code dezimalWert}
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der �bergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen ein '-' ist, da
	 *                                       negative Zahlen nicht unterst�tzt
	 *                                       werden
	 * @param dezimalWert dezimaler Wert in der {@code String} Darstellung, mit dem
	 *                    diese {@code Zahl} �berschriben wird
	 */
	@Override
	public void setDec(String decValue) {
		// Aufruf von basisToDezString noetig, damit die Exceptions bei falscher Eingabe geworfen werden
		initDezimalString(decValue);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem oktalen Wert
	 * {@code binary}
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der uebergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen ein '-' ist, da
	 *                                       negative Zahlen nicht unterstuetzt
	 *                                       werden
	 * @param octal oktaler Wert in der {@code String} Darstellung, mit dem diese
	 *              {@code Zahl} ueberschriben wird
	 */
	@Override
	public void setOct(String octValue) {
		this.initDezimalString(ConvertingNumbers.baseToDecString(8, octValue, Preferences.getComma()));

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem binären Wert
	 * {@code binary}
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der uebergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen ein '-' ist, da
	 *                                       negative Zahlen nicht unterstuetzt
	 *                                       werden
	 * @param binary binärer Wert in der {@code String} Darstellung, mit dem diese
	 *               {@code Zahl} ueberschriben wird
	 */
	@Override
	public void setBin(String binValue) {
		this.initDezimalString(ConvertingNumbers.baseToDecString(2, binValue, Preferences.getComma()));

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	@Override
	public void setValue(String value, int baseOfValue) {
		this.initDezimalString(ConvertingNumbers.baseToDecString(baseOfValue, value, Preferences.getComma()));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	@Override
	public void setByteLength(int numberOfBytes) {
		// TODO Auto-generated method stub

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/*
	 * Gibt einen {@code String} zurueck, der die hexadezimale Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl
	 *         zurueck.
	 */
	@Override
	public String toHexString() {
		return this.hexValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * Gibt einen {@code String} zurueck, der die dezimale Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als dezimale Zahl zurueck.
	 */
	@Override
	public String toDecString() {
		return this.decValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * Gibt einen {@code String} zurueck, der die oktale Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als oktale Zahl zurueck.
	 */
	@Override
	public String toOctString() {
		return this.octalValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * Gibt einen {@code String} zurueck, der die binäre Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als binäre Zahl zurueck.
	 */
	@Override
	public String toBinString() {
		return this.binValue;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/*
	 * Gibt einen {@code String} zurueck, der die Darstellung dieser {@code Zahl} zu
	 * der uebergebenen Basis repräsentiert
	 * 
	 * @param basis Ein {@code int} Wert, der die Basis darstellt, in die diese
	 *              {@code Zahl} ueberfuerht werden soll.
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl
	 *         zurueck.
	 */
	@Override
	public String toBaseString(int base) throws IllegalArgumentException {
		if(base == 10) {
			// Ungenauigkeiten beim Umwandeln ins Zehnersystem umgehen
			return toDecString();
		}
		
		try {
			return ConvertingNumbers.decToBase(base, decValue, Preferences.getComma());
		} catch (IllegalArgumentException illArg) {
			// Auf falsche Basis pr�fen
			if(base < ConvertingNumbers.MIN_BASE || base > ConvertingNumbers.MAX_BASE) {
				throw illArg;
			}
			
			// decValue ist ein leerer String (reset Methode wurde aufgerufen)
			return "";
		} catch (Exception e) {
			// Sollte nicht auftreten
			throw e;
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	/*
	 * Gibt einen {@code String} zurueck, der die hexadezimale, dezimale, oktale und
	 * binäre Darstellung dieser {@code Zahl} enthält.
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl
	 *         zurueck.
	 */
	@Override
	public String toString() {
		String s = String.format("Hexadezimal: %s\nDezimal: %s\nOktal: %s\nBinär: %s", this.toHexString(),
				this.toDecString(), this.toOctString(), this.toBinString());
		return s;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	/*
	 * Gibt einen {@code String} zurueck, der die Darstellung dieser {@code Zahl} zu der uebergebenen Basis repräsentiert
	 * 
	 * @param basis	Ein {@code int} Wert, der die Basis darstellt, in die diese {@code Zahl} ueberfuerht werden soll.
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl zurueck.
	 */
	public void reset() {
		this.binValue = "";
		this.decValue = "";
		this.hexValue = "";
		this.octalValue = "";
	}

// Konstruktoren   ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/*
		 * Erstellt eine neue {@code Zahl} mit dem dezimalen Wert 0,0
		 */
		public SimpleChangeableNumber() {
			this("0");
		}

	/*
	 * Erstellt eine neue {@code Zahl} mit dem spezifischen dezimalen Wert.
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der uebergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen des Parameters
	 *                                       {@code zahl} ein '-' ist, da negative
	 *                                       Zahlen hier nicht erlaubt sind
	 * @param dezimalWert dezimaler Wert in der {@code String} Darstellung, mit dem
	 *                    diese {@code Zahl} initialisiert wird
	 */
	public SimpleChangeableNumber(String dezimalWert)
			throws NullPointerException, NumberFormatException, UnsupportedOperationException {
		this.initDezimalString(dezimalWert);
	}

// Initialisierung	  ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/*
	 * Initialisiet diese {@code Zahl} mit neuem dezimalem Wert aus dem uebergebenem
	 * {@code double} Wert. Die Darstellungen anderer Zahlensysteme werden mit
	 * {@code null} initialisiert.
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der uebergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen des Parameters
	 *                                       {@code zahl} ein '-' ist, da negative
	 *                                       Zahlen hier nicht erlaubt sind
	 * @param dezimalWert dezimaler Wert in der {@code String} Darstellung, mit dem
	 *                    diese {@code Zahl} ueberschrieben wird
	 */
	private void initDezimalString(String dezimalWert) throws NullPointerException {
		this.decValue = Objects.requireNonNull(dezimalWert);
		this.hexValue = ConvertingNumbers.decToBase(16, this.decValue, Preferences.getComma());
		this.octalValue = ConvertingNumbers.decToBase(8, this.decValue, Preferences.getComma());
		this.binValue = ConvertingNumbers.decToBase(2, this.decValue, Preferences.getComma());
	}

// Getter und Setter  ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	

	
}