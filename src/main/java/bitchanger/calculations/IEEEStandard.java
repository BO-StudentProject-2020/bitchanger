package bitchanger.calculations;

// TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.7
 * @version 0.1.7
 * 
 */
public enum IEEEStandard {

	// TODO JavaDoc
	IEEE_754_2008_b16(16, 5),
	
	// TODO JavaDoc
	IEEE_754_2008_b32(32, 8);
	
	
	private int bitLength;
	private int exponentLength;
	
	private IEEEStandard(int bitLength, int exponentLength) {
		this.bitLength = bitLength;
		this.exponentLength = exponentLength;
	}
	
	public int getBitLength() {
		return this.bitLength;
	}
	
	public int getExponentLength() {
		return this.exponentLength;
	}
	
}







