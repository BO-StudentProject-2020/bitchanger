package bitchanger.components;

public class Settings {
	
	private static Comma comma;
	
	public static String getComma() {
		return String.valueOf(comma.getComma());
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
		
		public char getComma() {
			return this.comma;
		}
	}
	
	
	public static String getComma() {
		
		return comma;
	}
}
