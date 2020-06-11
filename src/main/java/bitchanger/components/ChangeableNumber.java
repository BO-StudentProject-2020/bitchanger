/*
 * Copyright (c)
 * 
 * Ersteller: Tim Mühle & Moritz Wolter
 * 
 */

package bitchanger.components;

public interface ChangeableNumber {
	
	// Methoden zum setzen des Zahlenwertes mit bestimmter Basis
	public void setHex(String hexValue) throws NullPointerException, NumberFormatException;
	public void setDec(String decValue) throws NullPointerException, NumberFormatException;
	public void setOct(String octValue) throws NullPointerException, NumberFormatException;
	public void setBin(String binValue) throws NullPointerException, NumberFormatException;
	public void setValue(String value, int baseOfValue) throws NullPointerException, NumberFormatException;
	
	public void setByteLength(int numberOfBytes);
	public void reset();
	
	// Methoden, um Zahl als String zu einer Basis auszulesen
	public String toHexString();	// hexadezimale Darstellung dieser Zahl als String
	public String toDecString();	// dezimale Darstellung dieser Zahl als String
	public String toOctString();	// oktale Darstellung dieser Zahl als String
	public String toBinString();	// binaere Darstellung dieser Zahl als String
	public String toBaseString(int base) throws IllegalArgumentException;	// Darstellung dieser Zahl als String zu der uebergebenen Basis base
	
}
