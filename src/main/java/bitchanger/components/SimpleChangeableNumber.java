package bitchanger.components;

import java.util.Objects;

public class SimpleChangeableNumber implements ChangeableNumber {

	private String binWert;
	private String dezWert;
	private String hexWert;
	private String octalWert;

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem hexadezimalen Wert
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
		this.setDec(ConvertingNumbers.basisToDezString(16, hexValue.toUpperCase(), Settings.getComma()));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem Parameter
	 * {@code dezimalWert}
	 * 
	 * @throws NullPointerException          wenn das Argument {@code dezimalWert}
	 *                                       {@code null} ist.
	 * @throws NumberFormatException         wenn der uebergebene {@code String}
	 *                                       keine umwandelbare Zahl ist.
	 * @throws UnsupportedOperationException wenn das erste Zeichen ein '-' ist, da
	 *                                       negative Zahlen nicht unterstuetzt
	 *                                       werden
	 * @param dezimalWert dezimaler Wert in der {@code String} Darstellung, mit dem
	 *                    diese {@code Zahl} ueberschriben wird
	 */
	@Override
	public void setDec(String decValue) {
		initDezimalString(decValue);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
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
		this.setDec(ConvertingNumbers.basisToDezString(8, octValue, Settings.getComma()));

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
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
		this.setDec(ConvertingNumbers.basisToDezString(2, binValue, Settings.getComma()));

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	@Override
	public void setValue(String value, int baseOfValue) {
		this.setDec(ConvertingNumbers.basisToDezString(baseOfValue, value, Settings.getComma()));
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	@Override
	public void setByteLength(int numberOfBytes) {
		// TODO Auto-generated method stub

	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**
	 * Gibt einen {@code String} zurueck, der die hexadezimale Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl
	 *         zurueck.
	 */
	@Override
	public String toHexString() {
		if (this.hexWert == null) {
			this.hexWert = ConvertingNumbers.dezToBasis(16, this.dezWert, Settings.getComma());
		}

		return this.hexWert;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
	 * Gibt einen {@code String} zurueck, der die dezimale Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als dezimale Zahl zurueck.
	 */
	@Override
	public String toDecString() {
		return this.dezWert;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
	 * Gibt einen {@code String} zurueck, der die oktale Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als oktale Zahl zurueck.
	 */
	@Override
	public String toOctString() {
		if (this.octalWert == null) {
			this.octalWert = ConvertingNumbers.dezToBasis(8, this.dezWert, Settings.getComma());
		}

		return this.octalWert;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
	 * Gibt einen {@code String} zurueck, der die binäre Darstellung dieser
	 * {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als binäre Zahl zurueck.
	 */
	@Override
	public String toBinString() {
		if (this.binWert == null) {
			this.binWert = ConvertingNumbers.dezToBasis(2, this.dezWert, Settings.getComma());
		}

		return this.binWert;
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**
	 * Gibt einen {@code String} zurueck, der die Darstellung dieser {@code Zahl} zu
	 * der uebergebenen Basis repräsentiert
	 * 
	 * @param basis Ein {@code int} Wert, der die Basis darstellt, in die diese
	 *              {@code Zahl} ueberfuerht werden soll.
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl
	 *         zurueck.
	 */
	@Override
	public String toBaseString(int base) {
		try {
			return ConvertingNumbers.dezToBasis(base, dezWert, Settings.getComma());
		} catch (ArrayIndexOutOfBoundsException ae) {
			return "";
		} catch (Exception e) {
			throw e;
		}
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	/**
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
	/**	
	 * Gibt einen {@code String} zurueck, der die Darstellung dieser {@code Zahl} zu der uebergebenen Basis repräsentiert
	 * 
	 * @param basis	Ein {@code int} Wert, der die Basis darstellt, in die diese {@code Zahl} ueberfuerht werden soll.
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl zurueck.
	 */
	public void reset() {
		this.binWert = "";
		this.dezWert = "";
		this.hexWert = "";
		this.octalWert = "";
	}

// Konstruktoren   ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/**	
		 * Erstellt eine neue {@code Zahl} mit dem dezimalen Wert 0,0
		 */
		public SimpleChangeableNumber() {
			this("0");
		}

	/**
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
	/**
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
		this.dezWert = Objects.requireNonNull(dezimalWert);
		this.hexWert = null;
		this.octalWert = null;
		this.binWert = null;
	}

// Getter und Setter  ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ##
	/**
	 * Gibt den Wert des ganzzahligen Anteils dieser {@code Zahl} als {@code double}
	 * zur Basis 10 zurueck.
	 * 
	 * @return ganzzahliger Anteil dieser {@code Zahl} zur Basis 10
	 */
	public long getGanzWert() {
		return ConvertingNumbers.parseGanzenAnteil(this.dezWert);
	}

	
}