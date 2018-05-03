/**
 * Interface qui contient les méthodes tir et recoitTir
 * 
 * @author Djaoud Mohamed Said 
 * @author Louis Raymond-Poirier
 */
public interface IJoueur {
	
	/**
	 * méthode qui permet à un des deux joueurs de tirer
	 * @param cible   Le joueur sur qui tirer.
	 */
	void tir(IJoueur cible);
	
	/**
	 * méthode qui permet de savoir si la position tirée a touché ou non un navire
	 * @param position  la position à vérifier, pour savoir si elle a touché un navire ou non
	 * @return retourne true si le tir a touché un bateau et false s'il n'a pas touché un navire
	 */
	boolean recoitTir(Position position);
}
