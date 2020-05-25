package bitchanger.components;

public class Settings {
	
	private static Comma comma = Comma.COMMA_DE;
	
	public static String getComma() {
		return String.valueOf(comma.get());
	}
	
	public static void setComma(Comma comma) {
		Settings.comma = comma;
	}
	
	
	public enum Comma{
		
		COMMA_DE(','), COMMA_EN('.');
		
		private char comma;
		
		private Comma(char comma) {
			this.comma = comma;
		}
		
		public char get() {
			return this.comma;
		}
	}
	
}
