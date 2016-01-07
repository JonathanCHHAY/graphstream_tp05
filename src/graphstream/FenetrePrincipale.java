package graphstream;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.lang.model.element.TypeElement;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;

public class FenetrePrincipale extends JFrame {

	public static void main(String[] args) {
		
		FenetrePrincipale fen = new FenetrePrincipale();
		fen.setVisible(true);
		
		int[] params = {3,2};
		//Graph g = FabriqueGraphe.generer(TypeGraph.TORE, params );
		//g.display();
		
		//System.out.println(TypeGraph.TORE.toString());
		
	}
	
	private JPanel pano;
	
	public FenetrePrincipale() {

		pano = new JPanel();
		
		// Titre fenêtre et fermeture la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        
	}
	
	public void init() {
        
		
		int[] params = {1, 0};
		Graph graph = FabriqueGraphe.generer(TypeGraph.TORE, params);
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);
		viewer.enableAutoLayout(); // Active les animations
		// ...
		View view = viewer.addDefaultView(false);   // false indicates "no JFrame".
		//graph.display();
		// ...
		pano.add(view);
		view.setLocation(0, 0);
		view.setPreferredSize(new Dimension(500,500));
		
		PanneauChoix panoChoix = new PanneauChoix();
		pano.add(panoChoix);
		panoChoix.setLocation(view.WIDTH, 0);
		
		
		this.setContentPane(pano);
        // Réglage taille fenêtre
        
		this.pack();
		//this.setSize(800, 500);
		
	}
	
	public class PanneauChoix extends JPanel {
		
		JComboBox<TypeGraph> choixGraphe;
		JButton generer;
		
		public PanneauChoix() {
			
			setBorder( BorderFactory.createTitledBorder( "Choix du graphe" ) );
			generer = new JButton("Générer");
			choixGraphe = new JComboBox();
			
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.CYCLE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.CHAINE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.TORE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.GRILLE);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.ANC);
			( (DefaultComboBoxModel) choixGraphe.getModel() ).addElement(TypeGraph.GRAPH);
			

			this.add(choixGraphe);
			this.add(generer);
			
		}
	}
}
