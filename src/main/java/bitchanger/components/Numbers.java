package bitchanger.components;

import java.util.Objects;

public class Numbers implements ChangeableNumbers {
	
	
	private String binWert;
	private String dezWert;
	private String hexWert;
	private String octalWert;
	private String trennzeichen;
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem hexadezimalen Wert {@code binary}
	 * 
	 * @throws 	NullPointerException	wenn das Argument {@code dezimalWert} {@code null} ist.
	 * @throws 	NumberFormatException	wenn der übergebene {@code String} keine umwandelbare Zahl ist.
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @param hex hexadezimaler Wert in der {@code String} Darstellung, mit dem diese {@code Zahl} überschriben wird
	 */
	@Override
	public void setHex(String hexValue) {
		this.setDec(ConvertingNumbers.basisToDezString(16, hexValue.toUpperCase(), this.trennzeichen));
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem Parameter {@code dezimalWert}
	 * 
	 * @throws 	NullPointerException	wenn das Argument {@code dezimalWert} {@code null} ist.
	 * @throws 	NumberFormatException	wenn der übergebene {@code String} keine umwandelbare Zahl ist.
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @param dezimalWert dezimaler Wert in der {@code String} Darstellung, mit dem diese {@code Zahl} überschriben wird
	 */
	@Override
	public void setDec(String decValue) {
		ConvertingNumbers.initDezimalString(decValue);

	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem oktalen Wert {@code binary}
	 * 
	 * @throws 	NullPointerException	wenn das Argument {@code dezimalWert} {@code null} ist.
	 * @throws 	NumberFormatException	wenn der übergebene {@code String} keine umwandelbare Zahl ist.
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @param octal oktaler Wert in der {@code String} Darstellung, mit dem diese {@code Zahl} überschriben wird
	 */
	@Override
	public void setOct(String octValue) {
		this.setDec(ConvertingNumbers.basisToDezString(8, octValue, this.trennzeichen));

	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Überschreibt alle Werte dieser {@code Zahl} mit dem binären Wert {@code binary}
	 * 
	 * @throws 	NullPointerException	wenn das Argument {@code dezimalWert} {@code null} ist.
	 * @throws 	NumberFormatException	wenn der übergebene {@code String} keine umwandelbare Zahl ist.
	 * @throws 	UnsupportedOperationException wenn das erste Zeichen ein '-' ist, 
	 * 										  da negative Zahlen nicht unterstützt werden
	 * @param binary binärer Wert in der {@code String} Darstellung, mit dem diese {@code Zahl} überschriben wird
	 */
	@Override
	public void setBin(String binValue) {
		this.setDec(ConvertingNumbers.basisToDezString(2, binValue, this.trennzeichen));

	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	@Override
	public void setValue(String value, int baseOfValue) {
		// TODO Auto-generated method stub

	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	@Override
	public void setByteLength(int numberOfBytes) {
		// TODO Auto-generated method stub

	}

// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	/**	
	 * Gibt einen {@code String} zurück, der die hexadezimale Darstellung dieser {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl zurück.
	 */
	@Override
	public String toHexString() {
		if(this.hexWert == null) {
			this.hexWert = ConvertingNumbers.dezToBasis(16, this.dezWert, this.trennzeichen);
		}
		
		return this.hexWert;
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Gibt einen {@code String} zurück, der die dezimale Darstellung dieser {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als dezimale Zahl zurück.
	 */
	@Override
	public String toDecString() {
		return this.dezWert;
	}

// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Gibt einen {@code String} zurück, der die oktale Darstellung dieser {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als oktale Zahl zurück.
	 */	
	@Override
	public String toOctString() {
		if(this.octalWert == null) {
			this.octalWert = ConvertingNumbers.dezToBasis(8, this.dezWert, this.trennzeichen);
		}
		
		return this.octalWert;
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Gibt einen {@code String} zurück, der die binäre Darstellung dieser {@code Zahl} repräsentiert
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als binäre Zahl zurück.
	 */
	@Override
	public String toBinString() {
		if(this.binWert == null) {
			this.binWert = ConvertingNumbers.dezToBasis(2, this.dezWert, this.trennzeichen);
		}
		
		return this.binWert;
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	
	/**	
	 * Gibt einen {@code String} zurück, der die Darstellung dieser {@code Zahl} zu der übergebenen Basis repräsentiert
	 * 
	 * @param basis	Ein {@code int} Wert, der die Basis darstellt, in die diese {@code Zahl} überfürht werden soll.
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl zurück.
	 */
	@Override
	public String toBaseString(int base) {
		try {
			return ConvertingNumbers.dezToBasis(base, dezWert, this.trennzeichen);
		} catch (ArrayIndexOutOfBoundsException ae) {
			return "";
		} catch (Exception e) {
			throw e;
		}
	}
	
// *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*		
	/**	
	 * Gibt einen {@code String} zurück, der die hexadezimale, dezimale,
	 * oktale und binäre Darstellung dieser {@code Zahl} enthält.
	 * 
	 * @return Die String-Darstellung dieser {@code Zahl} als hexadezimale Zahl zurück.
	 */
	@Override
	public String toString() {
		String s = String.format("Hexadezimal: %s\nDezimal: %s\nOktal: %s\nBinär: %s", this.toHexString(), this.toDecString(), 
						this.toOctString(), this.toBinString());
		return s;
	}
}