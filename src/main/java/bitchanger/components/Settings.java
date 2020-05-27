package bitchanger.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Settings {
	
	public static void load() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	public static void store() {
		// TODO store changed Settings in File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	private static ObjectProperty<Comma> commaProperty = new SimpleObjectProperty<Settings.Comma>(Comma.COMMA_DE);
	
	public static char getComma() {
		return commaProperty.getValue().get();
	}
	
	public static ObjectProperty<Comma> getCommaProperty() {
		return commaProperty;
	}
	
	public static void setComma(Comma comma) {
		commaProperty.setValue(comma);
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
