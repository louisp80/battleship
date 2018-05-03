/**
 * Classe qui permet de générer un liste dynamique qui peut ajouter un élément, retirer un élément,
 * avoir la valeur de l'élément à une position désirée, savoir si la liste est vide ou non et quelle est sa taille  
 * 
 * @author Djaoud Mohamed Said
 * @author Louis Raymond-Poirier
 *
 * @param <T> c'est une variable qui permet de prendre n'importe quel type de variable que l'utilisateur a rentré (template)
 */
public class ListeDynamique<T> {

	private Maillon<T> head;
	private int size = 0;

	/**
	 * Enleve un maillon de la liste
	 * 
	 * @param indice  
	 * @return la valeur enlevée
	 * @throws Exception  si l'indice n'existe pas 
	 */
	public T remove(int indice) throws Exception {

		/*
		 * STRATEGIE: On déroule partir de la tete, jusqu'a indice -1 Si on a
		 * par ex, 1 5 10 15 20 25 dans la liste est qu'on cherche l'indice 3
		 * (le 15), alors on avance jusqu'au 10, puis, on met le setNext du 10
		 * au 20. Le maillon 10 est donc enleve.
		 * 
		 * Dans le cas particulier de l'indice 0, on n'a pas besoin de dérouler.
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
	 * Recupere la valeur à l'indice
	 * 
	 * @param indice
	 * @return la valeur à l'indice
	 */
	public T get(int indice) throws Exception {

		/**
		 * Strategie: On déroule jusqu'à indice.
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
		 * Stratégie: Si la liste est vide alors le premier élément va être la tête sinon,
		 * on déroule jusqu'à la fin de la liste puis on ajoute le nouvel element
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
