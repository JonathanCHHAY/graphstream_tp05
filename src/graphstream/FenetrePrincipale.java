package graphstream;

import java.util.ArrayList;

import javax.lang.model.element.TypeElement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class FenetrePrincipale extends JFrame {

	public static void main(String[] args) {
		
		Graph graph = new SingleGraph("Tutorial 1");
		
		FenetrePrincipale fen = new FenetrePrincipale();
		fen.setVisible(true);
		
		System.out.println(TypeGraph.TORE.toString());
		
	}
	
	public FenetrePrincipale() {

		// Titre fenêtre et fermeture la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        
	}
	
	public void init() {
        
		this.add(new PanneauChoix());
        // Réglage taille fenêtre
        this.setSize(800, 500);
		
	}
	
	public class PanneauChoix extends JPanel {
		
		JComboBox<TypeGraph> choixGraphe;
		JButton generer;
		
		public PanneauChoix() {
			
			generer = new JButton("Générer");
			choixGraphe = new JComboBox();
			
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.CYCLE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.CHAINE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.TORE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.GRILLE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.ANC);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.GRAPH);
			
			this.add(generer);
			this.add(choixGraphe);
			
		}
	}
}
