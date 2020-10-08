package bitchanger.calculations;

// TODO JavaDoc
/**	<!-- $LANGUAGE=DE -->
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.6
 * @version 0.1.6
 * 
 */
public enum IEEEStandard {

	// TODO JavaDoc
	IEEE_754_2008_b16(16),
	
	// TODO JavaDoc
	IEEE_754_2008_b32(32);

	private int bitLength;

	private IEEEStandard(int bitLength) {
		this.bitLength = bitLength;
	}
	
	public int getBitLength() {
		return bitLength;
	}
}


