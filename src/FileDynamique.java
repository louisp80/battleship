/**
 * 
 * @author Djaoud Mohamed Said
 * @author Louis Raymond-Poirier
 *
 * @param <T>
 *            c'est une variable qui permet de prendre n'importe quel type de
 *            variable que l'utilisateur a rentré (template)
 */
public class FileDynamique<T> {

	// ATTRIBUTS
	private Maillon<T> tete;
	private int taille;

	/**
	 * Constructeur
	 */
	public FileDynamique() {
		this.taille = 0;
		this.tete = null;
	}

	/**
	 * @return retourne si la file est vide
	 */
	public boolean estVide() {
		return this.tete == null;
	}

	/**
	 * enfile la valeur dans la file
	 * 
	 * @param valeur
	 *            la valeur à enfiler
	 */
	public void enfile(T valeur) {
		/*
		 * STRATÉGIE: si la file est vide alors la tete aura la valeur du paramétre, sinon on cherche dans tout les maillons
		 * jusqu'à trouver celui que son prochain égale à null. Puis, on prend ce maillon et on dit que son prochain c'est la 
		 * valeur prise en paramétre
		 */
		taille++;
		if (tete == null) {
			tete = new Maillon<T>(valeur);
			return;
		}
		Maillon<T> cursor = tete;
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		cursor.setNext(new Maillon<T>(valeur));
	}

	/**
	 *
	 * @return retourne le premier élément mis en file 
	 */
	public T defile() {
		/*
		 * STRATÉGIE: Si le prochain élément la tête n'est pas null alors 
		 * la tête est égale à son prochain et on retorune la valeur initiale de la tête
		 * dans une autre variable. Si le prochain élément de la tête est null alors on retourne
		 * la valeur de la tête dans une variable et on met la tête à null.
		 */
		
		Maillon<T> maillonDeRetour = this.tete;
		
		if (this.tete.getNext() != null)
			this.tete = this.tete.getNext();
		else
			this.tete = null;
		
		taille--;
		return maillonDeRetour.getValeur();
	}
	
	/**
	 * 
	 * @return retourne la taille de la file 
	 */
	public int size() {

		return taille;
	}

	/**
	 * avoir la valeur à l'indice désiré
	 */
	public T get(int index) {
		/*
		 * STRATÉGIE: on cherche dans tout les maillons,en commencent par la tête, jusqu'à trouver celui que sont prochain égale à null.
		 * Puis, on retourne la valeur de ce maillon là 
		 */
		Maillon<T> cursor = tete;
		for (int i = 0; i < index; i++)
			if (cursor.getNext() != null)
				cursor = cursor.getNext();
		return cursor.getValeur();
	}

}
