package boite;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.graphstream.graph.Graph;

import graphstream.TypeGraph;

public abstract class AbstractBoiteGenGraph extends JDialog {

	protected JLabel lab1, lab2;
	protected JTextField tf1, tf2;
	protected JButton btAnnuler, btValider;
	protected int[] params;
	
	public AbstractBoiteGenGraph(JFrame own) {
		// On bloque la fenêtre principale
		super (own, true);
	}
	
	public abstract void creationElem();
	public abstract void placementElem();
	public abstract void redimFen();
	
	public abstract int[] showDialog();
	public abstract void setListeners();
}
