package graphstream;

public enum TypeGraph {

	CYCLE,
	CHAINE,
	TORE,
	GRILLE,
	ANC,
	GRAPH;

	@Override
	public String toString() {	
		
		// On implémente le fait de pouvoir convertir l'énum en texte
		switch( this ) {
		
		case CYCLE:
			return "Cycle";
			
		case CHAINE:
			return "Chaine";
			
		case TORE:
			return "Tore";
			
		case GRILLE:
			return "Grille carrée";
			
		case ANC:
			return "Arbre n-aire complet ";
			
		case GRAPH:
			return "Graphe aléatoire";
			
		default:
			return "";
		}
		
		
	}
}

