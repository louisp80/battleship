/**
 * 
 * @author Djaoud Mohamed Said
 * @author Louis Raymond-Poirier
 *
 * @param <T>
 *            c'est une variable qui permet de prendre n'importe quel type de
 *            variable que l'utilisateur a rentr� (template)
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
	 *            la valeur � enfiler
	 */
	public void enfile(T valeur) {
		/*
		 * STRAT�GIE: si la file est vide alors la tete aura la valeur du param�tre, sinon on cherche dans tout les maillons
		 * jusqu'� trouver celui que son prochain �gale � null. Puis, on prend ce maillon et on dit que son prochain c'est la 
		 * valeur prise en param�tre
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
	 * @return retourne le premier �l�ment mis en file 
	 */
	public T defile() {
		/*
		 * STRAT�GIE: Si le prochain �l�ment la t�te n'est pas null alors 
		 * la t�te est �gale � son prochain et on retorune la valeur initiale de la t�te
		 * dans une autre variable. Si le prochain �l�ment de la t�te est null alors on retourne
		 * la valeur de la t�te dans une variable et on met la t�te � null.
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
	 * avoir la valeur � l'indice d�sir�
	 */
	public T get(int index) {
		/*
		 * STRAT�GIE: on cherche dans tout les maillons,en commencent par la t�te, jusqu'� trouver celui que sont prochain �gale � null.
		 * Puis, on retourne la valeur de ce maillon l� 
		 */
		Maillon<T> cursor = tete;
		for (int i = 0; i < index; i++)
			if (cursor.getNext() != null)
				cursor = cursor.getNext();
		return cursor.getValeur();
	}

}
