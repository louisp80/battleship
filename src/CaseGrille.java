import javax.swing.JButton;

/**
 * Classe qui permet de créer une case de la grille 
 * 
 * @author Djaoud Mohamed Said 
 * @author Louis Raymond-Poirier
 */

@SuppressWarnings("serial")
public class CaseGrille extends JButton{
	
	private Position position;
	
	/**
	 * Constructeur
	 * @param posX  la position x de la case 
	 * @param posY  la position y de la case  
	 */
	public CaseGrille(int posX, int posY){
		position = new Position(posX, posY);
	}
	
	/**
	 * 
	 * @return la position x de la grille 
	 */
	public int getPosX(){
		return position.getX();
	}
	
	/**
	 * 
	 * @return la position y de la grille 
	 */
	public int getPosY(){
		return position.getY();
	}
	
	/**
	 * 
	 * @return la position de la grille (x,y)
	 */
	public Position getPosition(){
		return position;
	}
}