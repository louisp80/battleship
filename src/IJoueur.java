/**
 * Interface qui contient les m�thodes tir et recoitTir
 * 
 * @author Djaoud Mohamed Said 
 * @author Louis Raymond-Poirier
 */
public interface IJoueur {
	
	/**
	 * m�thode qui permet � un des deux joueurs de tirer
	 * @param cible   Le joueur sur qui tirer.
	 */
	void tir(IJoueur cible);
	
	/**
	 * m�thode qui permet de savoir si la position tir�e a touch� ou non un navire
	 * @param position  la position � v�rifier, pour savoir si elle a touch� un navire ou non
	 * @return retourne true si le tir a touch� un bateau et false s'il n'a pas touch� un navire
	 */
	boolean recoitTir(Position position);
}
