/**
 * Classe qui permet de générer un navire
 * 
 * @author Djaoud Mohamed Said 
 * @author Louis Raymond-Poirier
 *
 */
public class Navire {
	
	// ATTRIBUTS
	private String nomNavire;
	private ListeDynamique<Position> positions;
	
	/**
	 * Constructeur 
	 * 
	 * @param nom  nom du navire
	 * @param position_x_debut   position en x de la première case du navire
	 * @param position_y_debut   position en y de la première case de navire
	 * @param position_x_fin	 position en x de la dernière case du navire	
	 * @param position_y_fin	 position en y de la dernière case de navire
	 */
	public Navire(String nom, int position_x_debut, int position_y_debut, int position_x_fin, int position_y_fin) {
		
		this.nomNavire = nom;
		initialiserPositions(position_x_debut, position_y_debut, position_x_fin, position_y_fin);
	}
	
	/**
	 * Permet d'avoir toute les positions des cases du navire
	 * 
	 * @param position_x_debut  position en x de la première case du navire
	 * @param position_y_debut  position en y de la première case de navire
	 * @param position_x_fin    position en x de la dernière case du navire
	 * @param position_y_fin	position en y de la dernière case de navire
	 */
	public void initialiserPositions(int position_x_debut, int position_y_debut, int position_x_fin,
			int position_y_fin) {
		
		/*
		 * STRATÉGIE: on met la position du début et de la fin du navire dans une variable. Puis,
		 * on met la positon du debut dans une liste, ainsi que les autres positions du navire en augmentant
		 * la position en x ou en y de 1, de la position du debut du navire sans jamais dépasser la position de la fin.
		 */
		Position positionDebut = new Position(position_x_debut, position_y_debut);
		Position positionFin = new Position(position_x_fin, position_y_fin);
		positions = new ListeDynamique<Position>();

		for (int i = positionDebut.getX(); i <= positionFin.getX(); i++) {
			for (int y = positionDebut.getY(); y <= positionFin.getY(); y++) {
				positions.add(new Position(i, y));
			}
		}
	}
	
	/**
	 * 
	 * @return la position du navire
	 */
	public ListeDynamique<Position> getPositions() {
		return positions;
	}
	
	/**
	 * 
	 * @return le nom du navire
	 */
	public String getNom() {
		return nomNavire;
	}
	
	/**
	 * 
	 * @return la taille du navire (nombre de case qu'il prend)
	 */
	public int getTaille() {
		return positions.size();
	}
	
	/**
	 * 
	 * @return la position de la première case du navire 
	 * @throws Exception si on a pas donner un bon index 
	 */
	public Position getPositionDebut() throws Exception {
		return positions.get(0);
	}
	
	/**
	 * 
	 * @return la position de la dernière case du navire
	 * @throws Exception si on a pas donner un bon index 
	 */
	public Position getPositionFin() throws Exception {
		return positions.get(positions.size() - 1);
	}

}
