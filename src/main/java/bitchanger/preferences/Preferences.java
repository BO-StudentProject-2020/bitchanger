package bitchanger.preferences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Preferences {
	
	// Attribute	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	private static ObjectProperty<Comma> commaProperty = new SimpleObjectProperty<Comma>(Comma.COMMA_DE);
	private static BooleanProperty indicateFractionalPrecision = new SimpleBooleanProperty(true);
	
	
	// Getter und Setter	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public static char getComma() {
		return commaProperty.getValue().get();
	}
	
	public static ObjectProperty<Comma> getCommaProperty() {
		return commaProperty;
	}
	
	public static void setComma(Comma comma) {
		commaProperty.setValue(comma);
	}
	
	public static boolean indicateFractionalPrecision() {
		return indicateFractionalPrecision.getValue();
	}
	
	public static void setIndicateFractionalPrecision(boolean b) {
		indicateFractionalPrecision.setValue(b);
	}
	
	public static BooleanProperty indicateFractionalPrecisionProperty() {
		return indicateFractionalPrecision;
	}
	
	// Methoden		##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	public static void load() {
		// TODO load Settings from File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	public static void store() {
		// TODO store changed Settings in File	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!	!!
	}
	
	
	// Innere Klassen	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
}
