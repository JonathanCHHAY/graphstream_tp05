package graphstream;

import java.util.concurrent.CyclicBarrier;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class FabriqueGraphe {

	public static Graph generer(TypeGraph type, int args[] ) {
		

		switch ( type ) {
		
		case CYCLE: return FabriqueGraphe.createCycle(args[0]);
			
		case CHAINE: return FabriqueGraphe.createChaine(args[0]);
			
		case TORE: return FabriqueGraphe.createTore(args[0]);
			
		case GRILLE: return FabriqueGraphe.createGrille(args[0]);
			
		case ANC:
		
		case GRAPH: return FabriqueGraphe.createGraph(args[0], args[1]);
			
		default: return null;
				
		}
	}
	
	public static Graph createCycle(int nbSommets) {
		
		
		Graph graph = new SingleGraph("Cycle");
		
		graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
		 
		// Nodes already have a position.
		graph.display(false);
		
		return graph;
	}
	
	public static Graph createChaine(int sommets) {
		
		
		Graph graph = new SingleGraph("Cycle");
		
		graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
		
		return graph;
	} 
	
	public static Graph createGrille(int tailleCote) {
		
		Graph graph = new SingleGraph("grid");
		Generator gen = new GridGenerator();
		 
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<10; i++) {
		    gen.nextEvents();
		}
		gen.end();
		 
		// Nodes already have a position.
		//graph.display(false);
		
		return graph;
	}
	
	public static Graph createTore(int taille) {
		
		Graph graph = new SingleGraph("grid");
		Generator gen = new GridGenerator(false, true);
		 
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<10; i++) {
		    gen.nextEvents();
		}
		gen.end();
		 
		// Nodes already have a position.
		//graph.display(false);
		
		return graph;
	}
	
	public static Graph createGraph(int nbSommets, int degMoy) {
		
		System.out.println(TypeGraph.TORE.toString());
		
		Graph graph = new SingleGraph("Random");
		Generator gen = new RandomGenerator(2);
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<100; i++)
		    gen.nextEvents();
		gen.end();
		//graph.display();
		
		return graph;
	}
}
