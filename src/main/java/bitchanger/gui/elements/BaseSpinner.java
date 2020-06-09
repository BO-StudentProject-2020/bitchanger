package bitchanger.gui.elements;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;

public class BaseSpinner<T> extends Spinner<T>{

	public BaseSpinner() {
		super(2, 36, 10);
		this.setEditable(true);
		setActions();
	}

	private void setActions() {
		this.getEditor().addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (hasValidValue()) {
							commitValue();
							getEditor().positionCaret(getEditor().getLength());
						}
					}
				});
			}
		});
	}
	
//	public void printNode(Node n) {
//		if(n instanceof Parent) {
//			for(Node x : ((Parent) n).getChildrenUnmodifiable()) {
//				printNode(x);
//			}
//		}
//		System.out.println(n.getClass());
//		System.out.println(n);
//	}
	
	@Override
	public ObservableList<Node> getChildren() {
		// TODO Auto-generated method stub
		return super.getChildren();
	}

	private boolean hasValidValue() {
		int min = ((SpinnerValueFactory.IntegerSpinnerValueFactory) this.getValueFactory()).getMin();
		int max = ((SpinnerValueFactory.IntegerSpinnerValueFactory) this.getValueFactory()).getMax();
		int wert;
		
		try {
			wert = Integer.parseInt(this.getEditor().getText());
		} catch (Exception e) {
			return false;
		}
		
		if(wert < min || wert > max) {
			return false;
		} else {
			return true;
		}
	}

}
