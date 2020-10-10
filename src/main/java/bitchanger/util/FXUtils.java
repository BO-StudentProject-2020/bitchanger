/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt fuer das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.util;

import java.io.File;
import java.util.Optional;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import bitchanger.calculations.NumberOverflowException;
import bitchanger.gui.controls.SVGIcon;
import bitchanger.main.BitchangerLauncher;
import bitchanger.main.BitchangerLauncher.ErrorLevel;
import bitchanger.preferences.Preferences;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.SVGPath;

/** <!-- $LANGUAGE=DE -->
 * Utility-Klasse zur allgemeinen Verarbeitung von Objekten, die zur Oberfl\u00E4che geh\u00F6ren.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.8
 *
 */
/* <!-- $LANGUAGE=EN -->
 * Utility class to handle objects whose belong to the user interface.
 * 
 * @author Tim M\u00FChle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.8
 *
 */
public class FXUtils {

	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Constrains der Nodes in einer Schlange, um diese in einer GridPane zu positionieren.
	 * <p>
	 * Die erste Node wird an die Position gesetzt, die \u00FCber die Parameter und {@code firstColumn},
	 * {@code firstRow} und {@code columnOffset} definiert ist. Die Matrix wird auf die Anzahl von 
	 * Spalten verteilt, die mit {@code columnCount} festgelegt ist und die Zeile automatisch umgebrochen.
	 * Die Constraints werden solange gesetzt, wie es Elemente in der Queue gibt.
	 * </p>
	 * <p><b>
	 * Die Nodes d\u00FCrfen auch mehr als eine Spalte in der GridPane einnehmen. Die Spalte der darauffolgenden 
	 * Node wird entsprechend inkrementiert, um keine Elemente zu \u00FCberlagern.
	 * </b></p>
	 * 
	 * @param <T>			Der Typ, den die Elemente in der Schlange zur Laufzeit haben
	 * @param firstColumn	Erste Spalte der resultierenden Tabelle
	 * @param firstRow		Erste Zeile der resultierenden Tabelle
	 * @param columnCount	Anzahl der Spalten in der Tabelle
	 * @param columnOffset	Verschiebung der ersten Spalte (wird nur in der ersten Zeile angewendet)
	 * @param controls		Elemente in der Tabelle, mit der Reihenfolge von oben links nach unten rechts (Spalten werden vor den Zeilen inkrementiert)
	 * 
	 * @see #setGridConstraints(int, int, int, int, Queue, TriConsumer)
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets the constrains of the nodes in a queue to position these in a GridPane.
	 * <p>
	 * The first nodes position is defined by the parameters and {@code firstColumn},
	 * {@code firstRow} and {@code columnOffset}. The matrix gets spread of the amount of rows.
	 * These rows are set by {@code columnCount} and get an automatic line break.
	 * The constrains are set as long as there are elemts in the queue.
	 * </p>
	 * <p><b>
	 * It is possible that the nodes take more place than just one column in the GridPane.
	 * The column of the following node gets incremented accordingly to not overlay elements.
	 * </b></p>
	 * 
	 * @param <T>			The type of the queue elements while runtime
	 * @param firstColumn	First column of the resulting table
	 * @param firstRow		First row of the resulting table
	 * @param columnCount	Number of the columns in the table
	 * @param columnOffset	Shift of the first column (only deployed in the first row)
	 * @param controls		Elements in the table with the order from upper left to lower right (columns gets incremented before rows gets incremented)
	 * 
	 * @see #setGridConstraints(int, int, int, int, Queue, TriConsumer)
	 */
	public static <T extends Node > void setGridConstraints(int firstColumn, int firstRow, int columnCount, int columnOffset, Queue<T> controls) {
		setGridConstraints(firstColumn, firstRow, columnCount, columnOffset, controls, GridPane::setConstraints);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Arbeitet Nodes in einer Schlange als Tabelle ab.
	 * <p>
	 * F\u00FCr jede Node in der Schlange wir die Spalte inkrementiert. Wird die maximale Spaltenanzahl \u00FCberschritten,
	 * wird die Zeile inkrementiert und die Spalte auf den Startwert gesetzt. Die Node wird mit den Werten der Zeile
	 * und Spalte an den TriConsumer \u00FCbergeben, um diese Daten zu verarbeiten.
	 * </p>
	 * <p><b>
	 * Die Nodes d\u00FCrfen auch mehr als eine Spalte in der GridPane einnehmen. Die Spalte der darauffolgenden 
	 * Node wird entsprechend inkrementiert, um keine Elemente zu \u00FCberlagern.
	 * </b></p>
	 * 
	 * @param <T>			Der Typ, den die Elemente in der Schlange zur Laufzeit haben
	 * @param firstColumn	Erste Spalte der resultierenden Tabelle
	 * @param firstRow		Erste Zeile der resultierenden Tabelle
	 * @param columnCount	Anzahl der Spalten in der Tabelle
	 * @param columnOffset	Verschiebung der ersten Spalte (wird nur in der ersten Zeile angewendet)
	 * @param controls		Elemente in der Tabelle, mit der Reihenfolge von oben links nach unten rechts (Spalten werden vor den Zeilen inkrementiert)
	 * @param consumer		TriConsumer, der die Daten verarbeitet
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Proceed nodes as a table in a queue.
	 * <p>
	 * For each node inside the queue the column gets incremented. If the maximum count of columns reached,
	 * the row gets incremented and the column get set on the start value.
	 * The node is committed with the values of rows and columns to the TriConsumer to proceed these data.
	 * </p>
	 * <p><b>
	 * It is possible that the nodes takes more than just one column in the GridPane.
	 * The column of the following node gets incremented accordingly to not overlay elements.
	 * </b></p>
	 * 
	 * @param <T>			The type of the queue elements while runtime
	 * @param firstColumn	First column of the resulting table
	 * @param firstRow		First row of the resulting table
	 * @param columnCount	Number of the columns in the table
	 * @param columnOffset	Shift of the first column (only deployed in the first row)
	 * @param controls		Elements in the table with the order from upper left to lower right (columns gets incremented before rows gets incremented)
	 * @param consumer		TriConsumer, that proceed the data
	 */
	public static <T extends Node > void setGridConstraints(int firstColumn, int firstRow, int columnCount, int columnOffset, Queue<T> controls, TriConsumer<Node, Integer, Integer> consumer) {
		int row = firstRow;
		int column = firstColumn + columnOffset;
		
		for(Node n: controls) {
			if(GridPane.getColumnSpan(n) == null) {
				GridPane.setColumnSpan(n, 1);
			}
			
			// Daten verarbeiten
			consumer.accept(n, column, row);
			
			// Zählvariablen inkrementieren
			for (int i = 0; i < GridPane.getColumnSpan(n); i++) {
				column++;	// so viele Spalten weiter, wie das Element einnimmt
			}
			
			// Wenn maximale Spaltenanzahl überschritten: in nächste Zeile wechseln
			if(column >= firstColumn + columnCount) {
				column = firstColumn;
				row++;
			}
		}
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die maximale Gr\u00F6\u00DFe aller Controls in einem Iterable auf den Wert {@code maxSize}.
	 * 
	 * @param nodes		Sammlung aller anzupassenden Controls, die auch in Layout-Containern liegen d\u00FCrfen
	 * @param maxSize	Maximale Gr\u00F6\u00DFe der Controls
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets the maximum size of all controls inside an Iterable to the value of {@code maxSize}.
	 * 
	 * @param nodes		Collection of all controls that can be adjusted, and also are able to lie inside a layout container
	 * @param maxSize	Maximum size of the controls
	 */
	public static void setMaxSizes(Iterable<? extends Node> nodes, double maxSize) {
		setMaxSizes(nodes, maxSize, maxSize);
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die maximale Breite aller Controls in einem Iterable auf den Wert {@code maxWidth} und 
	 * die maximale H\u00F6he auf den Wert {@code maxHeight}.
	 * 
	 * @param nodes		Sammlung aller anzupassenden Controls, die auch in Layout-Containern liegen d\u00FCrfen
	 * @param maxWidth	maximale Breite aller Controls
	 * @param maxHeight	maximale H\u00F6he aller Controls
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Sets the maximum width of all controls inside an Iterable to the value of {@code maxWidth} and 
	 * the maximum heights to the value of {@code maxHeight}.
	 * 
	 * @param nodes		Collection of all controls that can be adjusted, and also are able to lie inside a layout container
	 * @param maxWidth	maximum width of all controls
	 * @param maxHeight	maximum height of all controls
	 */
	public static void setMaxSizes(Iterable<? extends Node> nodes, double maxWidth, double maxHeight) {
		for (Node n : nodes) {
			// maximale Größe anpassen
			if (n instanceof Control) {
				((Control) n).setMaxSize(maxWidth, maxHeight);
			} 
			else if (n instanceof Parent) {
				// Controls liegen im Layout-Container
				setMaxSizes(((Parent) n).getChildrenUnmodifiable(), maxWidth, maxHeight);
			}
		}
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	

	/** <!-- $LANGUAGE=DE -->
	 * L\u00E4dt ein Icon im svg-Format aus der \u00FCbergebenen Datei und erstellt aus der Datei ein SVGPath Objekt,
	 * das dem Scenegraph in javaFX hinzugef\u00FCgt w\u00FCrden kann.
	 * 
	 * @param svgFile	Icon als svg-Datei
	 * @return			Eingelesenes Icon als SVGPath oder {@code null}, wenn die Datei nicht eingelesen werden konnte
	 */
	/* <!-- $LANGUAGE=EN -->
	 * Loads an icon in svg format from the given file and creates an SVGPath object from the file that can be added to 
	 * the Scenegraph in javaFX.
	 * 
	 * @param svgFile	Icon as an svg file
	 * @return			Loaded icon as SVGPath or {@code null} if the file could not be read correctly
	 */
	public static SVGPath loadSVG(File svgFile) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(svgFile);
			
			NodeList nodes = doc.getElementsByTagName("svg");
			
			Element svg = (Element) nodes.item(0);
			
			String width = svg.getAttribute("width");
			String height = svg.getAttribute("height");
			String fillRule = svg.getAttribute("fill-rule");
			
			String path = ((Element)svg.getElementsByTagName("path").item(0)).getAttribute("d");
			
			SVGPath svgPath = new SVGPath();
			
			svgPath.setContent(path);
			svgPath.setFillRule(fillRule.equalsIgnoreCase("evenodd") ? FillRule.EVEN_ODD : FillRule.NON_ZERO);
			
			svgPath.prefWidth(Double.parseDouble(width));
			svgPath.prefHeight(Double.parseDouble(height));
			svgPath.setScaleX(1.2);
			svgPath.setScaleY(1.2);
			
			return svgPath;
			
		} catch (Exception e) {
			BitchangerLauncher.printDebugErr(ErrorLevel.CRITICAL);
			e.printStackTrace();
			return null;
		}
	}
	
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	// TODO JavaDoc
	public static void setIconOrText(MenuItem menuItem, SVGIcon icon, String alternativeText) {
		if(icon != null && icon.hasPath()) {
			menuItem.setText("");
			menuItem.setGraphic(icon);
		}
		else {
			menuItem.setText(alternativeText);
			menuItem.setGraphic(null);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	public static void setIconOrText(MenuItem menuItem, SVGIcon icon) {
		setIconOrText(menuItem, icon, menuItem.getText());
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	public static void setIconOrText(Labeled labeled, SVGIcon icon, String alternativeText) {
		if(icon != null && icon.hasPath()) {
			labeled.setText("");
			labeled.setGraphic(icon);
		}
		else {
			labeled.setText(alternativeText);
			labeled.setGraphic(null);
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc
	public static void setIconOrText(Labeled labeled, SVGIcon icon) {
		setIconOrText(labeled, icon, labeled.getText());
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 0.1.7
	public static Optional<ButtonType> showNumberOverflowWarning(NumberOverflowException noe) {
		StringBuffer infoText = new StringBuffer();
		infoText.append("Die gr\u00F6\u00DFte erlaubte Zahl ist ");
		infoText.append(String.valueOf((long) noe.getMaxSupportetNumber()));
		infoText.append(" und die kleinste erlaubte Zahl ist ");
		infoText.append(String.valueOf((long) noe.getMinSupportetNumber()));
		
		Label infoLabel = new Label(infoText.toString());
		infoLabel.setWrapText(true);
		
		Alert dialog = new Alert(Alert.AlertType.WARNING);
		dialog.setHeaderText("\u00DCberlauf");
		dialog.setContentText(noe.getDescription());
		dialog.getDialogPane().setExpandableContent(infoLabel);
		dialog.getDialogPane().getStylesheets().add(Resources.ALERT_LAYOUT_CSS);
		dialog.getDialogPane().getStylesheets().add(Preferences.getPrefs().stylesheetProperty().get());
		dialog.setResizable(true);
		
		return dialog.showAndWait();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 0.1.7
	public static Optional<ButtonType> showDialog(AlertType dialogType, String title, String headerText, String message, ButtonType... buttonTypes) {
		Alert dialog = new Alert(dialogType, message, buttonTypes);
		if(title != null) dialog.setTitle(title);
		if(headerText != null) dialog.setHeaderText(headerText);
		
		dialog.getDialogPane().getStylesheets().add(Resources.ALERT_LAYOUT_CSS);
		dialog.getDialogPane().getStylesheets().add(Preferences.getPrefs().stylesheetProperty().get());
		dialog.setResizable(true);
		
		return dialog.showAndWait();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 0.1.7
	public static Optional<ButtonType> showDialog(AlertType dialogType, String headerText, String message, ButtonType... buttonTypes) {
		return showDialog(dialogType, null, headerText, message, buttonTypes);
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 0.1.7
	public static Optional<ButtonType> showDeactivatableDialog(AlertType dialogType, String title, String headerText, String message, BooleanProperty deactivateDialogProperty, ButtonType... buttonTypes) {
		if(deactivateDialogProperty.get()) {
			return Optional.empty();
		}
		
		Alert dialog = new Alert(dialogType, message, buttonTypes);
		
		CheckBox deactivateDialog = new CheckBox("Diese Meldung nicht mehr anzeigen");
		ButtonBar.setButtonData(deactivateDialog, ButtonData.LEFT);
		ButtonBar.setButtonUniformSize(deactivateDialog, false);
		
		deactivateDialog.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				deactivateDialogProperty.set(newValue);
			}
		});
		
		for(Node child : dialog.getDialogPane().getChildren()) {
			if(child instanceof ButtonBar) {
				((ButtonBar)child).getButtons().add(deactivateDialog);
			}
		}
		
		if(title != null) dialog.setTitle(title);
		if(headerText != null) dialog.setHeaderText(headerText);
		
		dialog.getDialogPane().getStylesheets().add(Resources.ALERT_LAYOUT_CSS);
		dialog.getDialogPane().getStylesheets().add(Preferences.getPrefs().stylesheetProperty().get());
		dialog.setResizable(true);
		
		return dialog.showAndWait();
	}

// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc @since Bitchanger 0.1.7
	public static Optional<ButtonType> showDeactivatableDialog(AlertType dialogType, String headerText, String message, BooleanProperty deactivateDialogProperty, ButtonType... buttonTypes) {
		return showDeactivatableDialog(dialogType, null, headerText, message, deactivateDialogProperty, buttonTypes);
	}

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private FXUtils() {}
	
}




