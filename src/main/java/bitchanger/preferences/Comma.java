package bitchanger.preferences;

public enum Comma {
	
	COMMA_DE(','), COMMA_EN('.');
	
	private char comma;
	
	private Comma(char comma) {
		this.comma = comma;
	}
	
	public char get() {
		return this.comma;
	}
}
