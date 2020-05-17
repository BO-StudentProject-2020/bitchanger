package bitchanger.components;

public interface ChangeableNumber {
	
	
	
	// Methoden zum setzen des Zahlenwertes mit bestimmter Basis
	public void setHex(String hexValue);
	public void setDec(String decValue);
	public void setOct(String octValue);
	public void setBin(String binValue);
	public void setValue(String value, int baseOfValue);
	public void setByteLength(int numberOfBytes);
	
	// Methoden, um Zahl als String zu einer Basis auszulesen
	public String toHexString();	// hexadezimale Darstellung dieser Zahl als String
	public String toDecString();	// dezimale Darstellung dieser Zahl als String
	public String toOctString();	// oktale Darstellung dieser Zahl als String
	public String toBinString();	// binaere Darstellung dieser Zahl als String
	public String toBaseString(int base);	// Darstellung dieser Zahl als String zu der uebergebenen Basis base
	
}
