/**
 * Classe permetant de générer un maillon qui peut contenir une valeur et faire un lien vers le prochain maillon  
 * 
 * @author Djaoud Mohamed Said 
 * @author Louis Raymond-Poirier
 *
 * @param <T>  c'est une variable qui permet de prendre n'importe quel type de variable que l'utilisateur a rentré (template)
 */
public class Maillon<T> {

	// ATTRIBUTS
	private T valeur;
	private Maillon<T> next;

	/**
	 * Constructeur
	 *
	 * @param value  constructeur de la classe maillon prend en parametre une valeur
	 */
	public Maillon(T value) {
		this.valeur = value;
	}

	/**
	 * getter de la variable valeur
	 * @return la valeur
	 */
	public T getValeur() {
		return valeur;
	}

	/**
	 *setter de la variable valeur 
	 * @param valeur qu'on met dans valeur
	 */
	public void setValeur(T valeur) {
		this.valeur = valeur;
	}

	/**
	 * getter de la variable next
	 * @return next 
	 */
	public Maillon<T> getNext() {
		return next;
	}

	/**
	 *setter de la variable next 
	 * @param next qu'on met dans next 
	 */
	public void setNext(Maillon<T> next) {
		this.next = next;
	}

}
