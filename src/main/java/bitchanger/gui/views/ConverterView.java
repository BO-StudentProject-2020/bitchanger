/*
 * Copyright (c) 2020 - Tim Muehle und Moritz Wolter
 * 
 * Entwicklungsprojekt im Auftrag von Professorin K. Brabender und Herrn A. Koch
 * Entwickelt für das AID-Labor der Hochschule Bochum
 * 
 */

package bitchanger.gui.views;

import bitchanger.gui.controller.ControllableApplication;
import bitchanger.gui.controller.Controller;
import bitchanger.gui.controller.ConverterController;
import bitchanger.gui.controls.BaseSpinner;
import bitchanger.gui.controls.BasicMenuBar;
import bitchanger.gui.controls.ConverterMenuBar;
import bitchanger.util.FXUtils;
import bitchanger.util.IconFactory;
import bitchanger.util.Resources;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**	<!-- $LANGUAGE=DE -->
 * View, die die Scene für die Umwandlung von Zahlensystemen enthält.
 * <p><b>
 * Für diese View-Klasse wird der Controller {@link ConverterController} registriert.
 * </b></p>
 * 
 * @author Tim Mühle
 * 
 * @since Bitchanger 0.1.0
 * @version 0.1.8
 * 
 * @see ConverterController
 */
//TODO JavaDoc EN
public class ConverterView extends AlphaNumGridView {
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	public Constants   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die hexadezimale Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public static final String TF_HEX_KEY = "hexTF";
	
	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public static final String TF_DEC_KEY = "decTF";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die oktale Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public static final String TF_OCT_KEY = "octTF";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die binäre Darstellung in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public static final String TF_BIN_KEY = "binTF";

	/** <!-- $LANGUAGE=DE -->	Schlüsselwort, mit das Textfeld für die Darstellung zu einer wählbaren Basis in der Map {@code tfMap} gespeichert wird */
	// TODO JavaDoc EN
	public static final String TF_ANY_KEY = "anyTF";

	/** <!-- $LANGUAGE=DE -->	Array, das die Schlüsselwörter definiert, mit denen die Textfelder in der Map {@code tfMap} gespeichert werden */
	// TODO JavaDoc EN
	public static final String[] TF_KEYS = {TF_HEX_KEY , TF_DEC_KEY, TF_OCT_KEY, TF_BIN_KEY, TF_ANY_KEY};
	
	/** <!-- $LANGUAGE=DE -->	Array, das die Beschriftungen für die Labels vor den Textfeldern definiert */
	// TODO JavaDoc EN
	private static final String[] LABEL_TEXTS = {"HEX", "DEC", "OCT", "BIN"};
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Class initialization																										 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	static {
		// Controller-Klasse zuordnen
		Controller.register(ConverterView.class, ConverterController.class);
	}
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Instances		   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Constructors	   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##

	/** <!-- $LANGUAGE=DE -->
	 * Erzeugt eine neue ConverterView mit vollständigem Scenegraphen und initialisiert die Funktionen
	 * der Bedienelemente mit einem {@link ConverterController}.
	 */
	// TODO JavaDoc EN
	public ConverterView() {
		super(0, 0, 0, 1, 6, 1, LABEL_TEXTS, TF_KEYS);
		
		buildScenegraph();
	}
	


//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Getter and Setter																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem das Textfeld für die hexadezimale Darstellung in der Map {@code tfMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld für die hexadezimale Darstellung in der Map {@code tfMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String tfHexKey() {
		return TF_HEX_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld für die dezimale Darstellung in der Map {@code tfMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String tfDecKey() {
		return TF_DEC_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem das Textfeld für die oktale Darstellung in der Map {@code tfMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld für die oktale Darstellung in der Map {@code tfMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String tfOctKey() {
		return TF_OCT_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem das Textfeld für die binäre Darstellung in der Map {@code tfMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld für die binäre Darstellung in der Map {@code tfMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String tfBinKey() {
		return TF_BIN_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem das Textfeld für die Darstellung zu einer wählbaren Basis in der Map {@code tfMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem das Textfeld für die Darstellung zu einer wählbaren Basis in der Map {@code tfMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String tfAnyKey() {
		return TF_ANY_KEY;
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem der Spinner für die beliebige Basis in der Map {@code nodeMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem der Spinner für die beliebige Basis in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String baseSpinnerKey() {
		return "baseSpinner";
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** <!-- $LANGUAGE=DE -->
	 * Gibt das Schlüsselwort, mit dem das Label zur Anzeige des Rechenweges in der Map {@code nodeMap} gespeichert wird zurück.
	 * 
	 * @return	Schlüsselwort, mit dem das Label zur Anzeige des Rechenweges in der Map {@code nodeMap} gespeichert wird
	 */
	// TODO JavaDoc EN
	public final String calcPathHelpKey() {
		return "calc-path-btn";
	}
	
	
	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	Methods   																													 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
	
	
	/** {@inheritDoc} */
	@Override
	public BasicMenuBar generateMenuBar(ControllableApplication controllableApp) {
		try {
			return new ConverterMenuBar(controllableApp);
		} catch (NullPointerException e) {
			return generateMenuBar();
		}
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	public BasicMenuBar generateMenuBar() {
		return new ConverterMenuBar();
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

	/** {@inheritDoc} */
	@Override
	protected void createScenegraph() {
		super.createScenegraph();
		
		createAnyBase();
		createCalcPathBtn();
		
		ColumnConstraints lastRow = new ColumnConstraints();
		lastRow.minWidthProperty().bind(firstColumnWidthProperty);
		lastRow.maxWidthProperty().bind(firstColumnWidthProperty);
		
		addColumnConstraint(center, center.getColumnCount() - 1, lastRow);
		this.paddingRigthProperty.bind(paddingLeftProperty);
	}
	

	
//	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##
//  #																																 #
// 	#	private Methods   																											 #
//  #																																 #
//  ##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##	##


	/** <!-- $LANGUAGE=DE -->
	 * Fügt einen {@link BaseSpinner} zur Auswahl einer beliebigen (gültigen) Basis zur GridPane
	 * {@link AlphaNumGridView#center} hinzu und speichert diesen in der Map {@code nodeMap}.
	 */
	// TODO JavaDoc EN
	private void createAnyBase() {
		// Spinner für die beliebige Basis
		Spinner<Integer> baseSpinner = new BaseSpinner();
		getNodeMap().put(baseSpinnerKey(), baseSpinner);
		center.add(baseSpinner, 0, LABEL_TEXTS.length);
	}
	
// 	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	
	// TODO JavaDoc 0.1.8
	private void createCalcPathBtn() {
		Label calcPathBtn = new Label();
		FXUtils.setIconOrText(calcPathBtn, IconFactory.styleBindIcon(Resources.LIGHT_BULB_2_ICON, Resources.LIGHT_BULB_2_FILLED_ICON), "Rechenweg");
		
		calcPathBtn.setTooltip(new Tooltip("Hilfe zum Rechenweg anzeigen"));
		calcPathBtn.maxHeightProperty().bind(btnMaxHeigthProperty);
		calcPathBtn.minHeightProperty().bind(btnMinHeigthProperty);
		calcPathBtn.maxWidthProperty().bind(btnMaxWidthProperty);
		calcPathBtn.minWidthProperty().bind(btnMinWidthProperty);
		calcPathBtn.setPadding(new Insets(0, 5, 0, 5));
		
		getNodeMap().put(calcPathHelpKey(), calcPathBtn);
		
		TextField tfDec = getTextFieldMap().get(TF_DEC_KEY);
		GridPane.setConstraints(calcPathBtn, GridPane.getColumnIndex(tfDec) + GridPane.getColumnSpan(tfDec), GridPane.getRowIndex(tfDec));
		
		center.getChildren().add(calcPathBtn);
	}


	

}









