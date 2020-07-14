package bitchanger.gui.controller;

import java.util.HashMap;
import bitchanger.gui.views.Controllable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class ControllerBase {

	protected HashMap<String, TextField> textFieldMap;
	protected HashMap<String, Button> buttonMap;
//	protected ArrayList<Node> allButtons;
	
	protected ControllerBase(Controllable view) {
		this.textFieldMap = view.getTextFields();
		this.buttonMap = view.getButtons();
//		this.allButtons = view.getButtonList();
		
//		this.allButtons = new ArrayList<Node>();
//		this.allButtons.addAll(this.buttonMap.values());
	}
	
	public abstract void setControlls();
	
}
