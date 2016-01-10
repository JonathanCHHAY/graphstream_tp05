package graphstream;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.lang.model.element.TypeElement;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;

import boite.AbstractBoiteGenGraph;
import boite.BoiteGenCycle;

public class FenetrePrincipale extends JFrame {

	public static void main(String[] args) {
		
		FenetrePrincipale fen = new FenetrePrincipale();
		fen.setVisible(true);
		
		/*
		Generator gen = new MyFullGenerator();
		 Graph graph = new SingleGraph("graph");
		 
		 gen.addSink(graph);
		 
		 gen.begin();
		 for(int i = 0; i < 6; i++)
		    gen.nextEvents();
		 gen.end();
		 
		 graph.display();
		 */
		 
		int[] params = {3,2};
		Graph g = FabriqueGraphe.generer(TypeGraph.TORE, params );
		//g.display();
		
		//System.out.println(TypeGraph.TORE.toString());
		
	}
	
	private JPanel panoGraph;
	private PanneauChoix panoChoix;
	View view;
	
	public FenetrePrincipale() {

		panoGraph = new JPanel();
		
		// Titre fenêtre et fermeture la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creerPanneauGraph(TypeGraph.GRAPH);
        creerInterfaces();
        placeElem();
        redimFen();
        setListeners();
        
	}
	
	public void creerPanneauGraph(TypeGraph typeGraph) {
		

		int[] params = {1, 0};
		Graph graph = FabriqueGraphe.generer(typeGraph, params);
		
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);
		viewer.enableAutoLayout(); // Active les animations
		// ...
		view = viewer.addDefaultView(false);   // false indicates "no JFrame".
		//graph.display();
		// ...
	}
	
	public void creerInterfaces() {
		
		panoChoix = new PanneauChoix();
	}
	
	public void placeElem() {
		
		panoGraph.add(view);
		view.setLocation(0, 0);
		view.setPreferredSize(new Dimension(500,500));
		
		panoGraph.add(panoChoix);
		panoChoix.setLocation(panoGraph.WIDTH, 0);
		
		//this.setSize(800, 500);
	}
	
	public void redimFen() {
		
		this.setContentPane(panoGraph);        
		this.pack();
	}
	
	public void setListeners() {
		
		panoChoix.generer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panoChoix);
				TypeGraph graph = (TypeGraph) ( ( (DefaultComboBoxModel) panoChoix.choixGraphe.getModel() ).getSelectedItem() );
				AbstractBoiteGenGraph boite = new BoiteGenCycle(topFrame);
				int params[] = boite.showDialog();
				
				//System.out.println(params[0]);
				//System.out.println(params[1]);
				//System.out.println(graph.toString());
			}
		});
	}
	

	public void update() {
		
		/*
		Graph graph = FabriqueGraphe.generer(typeGraph, params);
		panoGraph.removeAll();
		panoGraph.add(, constraints);
		*/
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
