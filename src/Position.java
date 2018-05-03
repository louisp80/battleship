/**
 * Classe permettant de générer et personnaliser une position (X,Y) 
 *
 *@author Djaoud Mohamed Said
 *@author Louis Raymond-Poirier
 */
public class Position {
	private int x;
	private int y;
	
	/**
	 * Constructeur de la classe Position
	 * @param x    position en x
	 * @param y	   position en y
	 */
	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);		
	}
	
	/**
	 * getter de la variable x
	 * @return retourne ce que contient la variable x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * setter de la variable x
	 * @param x    la nouvelle valeur qu'on veut donner à la variable x 
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * getter de la variable y
	 * @return retourne ce que contient la variable y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * setter de la variable y
	 * @param y    la nouvelle valeur qu'on veut donner à la variable y 
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 *@return retourne la position en Y (en lettre) , X 
	 */
	public String toString() {
		/*
		 * pour le y on le transforme en caractère et on ajoute 64 à son code ASCII pour pour qu'il nous donne une lettre
		 */
		return (char)(this.y+64) +""+ this.x;
	}
	
	/**
	 * permet de savoir si les deux positions sont les mêmes
	 * @param pos position à comparer
	 * @return  retourne true si la position est la même que la variable x et y de la classe Position et false si ce n'est pas le cas
	 */
	public boolean egale(Position pos){
		return pos.getX() == this.x && pos.getY() == this.y;
	}
}
