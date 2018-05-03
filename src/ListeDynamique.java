/**
 * Classe qui permet de g�n�rer un liste dynamique qui peut ajouter un �l�ment, retirer un �l�ment,
 * avoir la valeur de l'�l�ment � une position d�sir�e, savoir si la liste est vide ou non et quelle est sa taille  
 * 
 * @author Djaoud Mohamed Said
 * @author Louis Raymond-Poirier
 *
 * @param <T> c'est une variable qui permet de prendre n'importe quel type de variable que l'utilisateur a rentr� (template)
 */
public class ListeDynamique<T> {

	private Maillon<T> head;
	private int size = 0;

	/**
	 * Enleve un maillon de la liste
	 * 
	 * @param indice  
	 * @return la valeur enlev�e
	 * @throws Exception  si l'indice n'existe pas 
	 */
	public T remove(int indice) throws Exception {

		/*
		 * STRATEGIE: On d�roule partir de la tete, jusqu'a indice -1 Si on a
		 * par ex, 1 5 10 15 20 25 dans la liste est qu'on cherche l'indice 3
		 * (le 15), alors on avance jusqu'au 10, puis, on met le setNext du 10
		 * au 20. Le maillon 10 est donc enleve.
		 * 
		 * Dans le cas particulier de l'indice 0, on n'a pas besoin de d�rouler.
		 */

		if (indice >= size || indice < 0) {
			throw new Exception("Indice n'existe pas");
		}

		Maillon<T> tmp = this.head;
		int count = 0;
		this.size--;

		while (indice - 1 > 0 && count != indice - 1 && tmp.getNext() != null) {
			tmp = tmp.getNext();
			count++;
		}
		
		T val = null;
		
		if (count == indice - 1) {
			val = tmp.getNext().getValeur();
			tmp.setNext(tmp.getNext().getNext());
		} else if (indice == 0) {
			val = tmp.getValeur();
			this.head = this.head.getNext();
		}
		return val;

	}

	/**
	 * Recupere la valeur � l'indice
	 * 
	 * @param indice
	 * @return la valeur � l'indice
	 */
	public T get(int indice) throws Exception {

		/**
		 * Strategie: On d�roule jusqu'� indice.
		 */

		if (indice >= size || indice < 0) {
			throw new Exception("Indice n'existe pas");
		}

		Maillon<T> tmp = this.head;
		int count = 0;
		while (count != indice && tmp.getNext() != null) {
			tmp = tmp.getNext();
			count++;
		}

		return tmp.getValeur();
	}

	/**
	 * Ajoute un elements
	 * 
	 * @param value la valeur qu'on veut ajouter
	 */
	public void add(T value) {

		/**
		 * Strat�gie: Si la liste est vide alors le premier �l�ment va �tre la t�te sinon,
		 * on d�roule jusqu'� la fin de la liste puis on ajoute le nouvel element
		 */

		if (this.estVide()) {
			
			this.head = new Maillon<T>(value);

		} else {

			Maillon<T> tmp = this.head;
			while (tmp.getNext() != null) {
				tmp = tmp.getNext();
			}
			tmp.setNext(new Maillon<T>(value));
		}

		this.size++;
	}

	/**
	 * estVide
	 * 
	 * @return retourne true si la liste est vide et false si elle ne l'est pas 
	 */
	public boolean estVide() {
		return this.size == 0;
	}

	/**
	 * Affiche ce qu'il y a dans la liste
	 */
	public String toString() {

		String string = "";
		Maillon<T> tmp = this.head;

		while (tmp.getNext() != null) {
			string += tmp.getValeur() + ";";
			tmp = tmp.getNext();
		}

		string += tmp.getValeur() + ";";

		return string;

	}
	
	/**
	 * 
	 * @return retourne la taille de la liste
	 */
	public int size() {

		return size;
	}
	
}
