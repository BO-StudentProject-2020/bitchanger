package bitchanger.util;

import java.util.Queue;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;

/** <!-- $LANGUAGE=DE -->
 * Utility-Klasse zur allgemeinen Verarbeitung von Objekten, die zur Oberfläche gehören.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 *
 */

/* <!-- $LANGUAGE=EN -->
 * Utility class to handle objects whose belong to the user interface.
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.4
 * @version 0.1.4
 *
 */
public class FXUtils {

	/** <!-- $LANGUAGE=DE -->
	 * Setzt die Constrains der Nodes in einer Schlange, um diese in einer GridPane zu positionieren.
	 * <p>
	 * Die erste Node wird an die Position gesetzt, die über die Parameter und {@code firstColumn},
	 * {@code firstRow} und {@code columnOffset} definiert ist. Die Matrix wird auf die Anzahl von 
	 * Spalten verteilt, die mit {@code columnCount} festgelegt ist und die Zeile automatisch umgebrochen.
	 * Die Constraints werden solange gesetzt, wie es Elemente in der Queue gibt.
	 * </p>
	 * <p><b>
	 * Die Nodes dürfen auch mehr als eine Spalte in der GridPane einnehmen. Die Spalte der darauffolgenden 
	 * Node wird entsprechend inkrementiert, um keine Elemente zu überlagern.
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Arbeitet Nodes in einer Schlange als Tabelle ab.
	 * <p>
	 * Für jede Node in der Schlange wir die Spalte inkrementiert. Wird die maximale Spaltenanzahl überschritten,
	 * wird die Zeile inkrementiert und die Spalte auf den Startwert gesetzt. Die Node wird mit den Werten der Zeile
	 * und Spalte an den TriConsumer übergeben, um diese Daten zu verarbeiten.
	 * </p>
	 * <p><b>
	 * Die Nodes dürfen auch mehr als eine Spalte in der GridPane einnehmen. Die Spalte der darauffolgenden 
	 * Node wird entsprechend inkrementiert, um keine Elemente zu überlagern.
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die maximale Größe aller Controls in einem Iterable auf den Wert {@code maxSize}.
	 * 
	 * @param nodes		Sammlung aller anzupassenden Controls, die auch in Layout-Containern liegen dürfen
	 * @param maxSize	Maximale Größe der Controls
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
	
	/** <!-- $LANGUAGE=DE -->
	 * Setzt die maximale Breite aller Controls in einem Iterable auf den Wert {@code maxWidth} und 
	 * die maximale Höhe auf den Wert {@code maxHeight}.
	 * 
	 * @param nodes		Sammlung aller anzupassenden Controls, die auch in Layout-Containern liegen dürfen
	 * @param maxWidth	maximale Breite aller Controls
	 * @param maxHeight	maximale Höhe aller Controls
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

	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE --> Diese Klasse ist nicht instanziierbar **/
	/*  <!-- $LANGUAGE=EN --> Do not let anyone instantiate this class **/
	private FXUtils() {}
	
}
