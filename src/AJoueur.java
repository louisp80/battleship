/**
 * 
 * Cette classe abstraite implémente IJoueur et est étendue par JoueurArtificiel et JoueurHumain. Elle gère les fonctionnalités qui
 * sont communes à ces deux classes.
 * 
 * @author Louis Raymond-Poirier
 * @author Djaoud Mohamed Said
 * @version 2.0
 *
 */
public abstract class AJoueur implements IJoueur {
	
	protected CaseGrille[][] grille;
	private ListeDynamique<Navire> listeNavires = new ListeDynamique<Navire>();
	protected ListeDynamique<Position> listePositionsTirees = new ListeDynamique<Position>();
	protected ListeDynamique<Position> listePositionsTouchees = new ListeDynamique<Position>();
	protected FileDynamique<Position> fileDesProchainsTirs = new FileDynamique<Position>();
	
	private int NB_TOUCHES_VICTOIRE = 17;
	
	/**
	 * fonction qui recoit une position et qui enfile les positions autour de celle-ci.
	 * 
	 * @param positionNavireTouche
	 * @throws Exception
	 */
	/*
	 * STRATÉGIE: On recoit une position qui est une case touchée. On enfile toutes les positions autour entant que positions potentielles. 
	 * On vérifie si la position potentielle est déjà dans la file ou si elle a déjà été tirée
	 */
	public void modeCible(Position positionNavireTouche) throws Exception {

		Position nord, sud, est, ouest;
		sud = new Position(positionNavireTouche.getX() + 1, positionNavireTouche.getY());
		nord = new Position(positionNavireTouche.getX() - 1, positionNavireTouche.getY());
		est = new Position(positionNavireTouche.getX(), positionNavireTouche.getY() + 1);
		ouest = new Position(positionNavireTouche.getX(), positionNavireTouche.getY() - 1);

		if (estValide(nord) && !estEnFile(nord) && !dejaTire(nord))
			fileDesProchainsTirs.enfile(nord);

		if (estValide(sud) && !estEnFile(sud) && !dejaTire(sud))
			fileDesProchainsTirs.enfile(sud);

		if (estValide(est) && !estEnFile(est) && !dejaTire(est))
			fileDesProchainsTirs.enfile(est);

		if (estValide(ouest) && !estEnFile(ouest) && !dejaTire(ouest))
			fileDesProchainsTirs.enfile(ouest);

	}
	/**
	 * Fonction qui retourne si le tir reçu a touché un navire
	 * 
	 * @return boolean
	 */
	/*
	 * STRATÉGIE: On recoit une position tirée par l'adversaire. On vérifie à l'aide de contains(Position) si cette position
	 * est occupé par un navire. Si oui, ajouter cette position à la liste de positions touchées.
	 */
	@Override
	public boolean recoitTir(Position position) {
		
		boolean estTouche = false;
		
		try {
			if (this.contains(position) != null) {
				listePositionsTouchees.add(position);
				estTouche =  true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return estTouche;
	}
	/**
	 * Fonction qui retourne si la position reçue est valide
	 * 
	 * @param pos
	 * @return boolean
	 */
	/*
	 * STRATÉGIE: On retourne directement si la position est à l'intérieure de la grille, en vérifiant si l'indice est inférieure à ses bornes
	 */
	private boolean estValide(Position pos) {
		return pos.getX() > 0 && pos.getX() < 11 && pos.getY() > 0 && pos.getY() < 11;
	}
	/**
	 * 
	 * fonction qui retourne si la position reçue est déjà dans la file
	 * 
	 * @param pos
	 * @return boolean
	 */
	/*
	 * STRATÉGIE: on parcours la file des prochains tirs et on vérifie pour chaque valeur si elle correspond à la position reçue. 
	 */
	private boolean estEnFile(Position pos) {
		
		boolean enfilee = false;
		
		for (int i = 0; i < fileDesProchainsTirs.size(); i++) {
			if (fileDesProchainsTirs.get(i).egale(pos)) {				
				enfilee = true;
			}
		}
		return enfilee;
	}
	/**
	 * Fonction qui retourne si la position reçue a déjà été tirée
	 * 
	 * @param pos
	 * @return boolean
	 * @throws Exception
	 */
	/*
	 * STRATÉGIE: On parcours la liste des positions tirées et on compare chaque valeur à la position reçue
	 */
	protected boolean dejaTire(Position pos) throws Exception {
		
		boolean tire = false;
		
		for (int i = 0; i < listePositionsTirees.size(); i++) {
			if (listePositionsTirees.get(i).egale(pos)) {
				tire = true;
			}
		}
		return tire;
	}
	/**
	 * Fonction qui ajoute le navire dans la liste des navires placés
	 * 
	 * @param navire
	 */
	/*
	 * STRATÉGIE: On appelle navireExiste(Navire) afin de savoir si le navire est déjà dans la liste
	 */
	public void placerNavire(Navire navire) {

		if (!navireExiste(navire.getNom())) {

			listeNavires.add(navire);
		}

	}
	/**
	 * Fonction pour retirer le dernier navire de la liste
	 */
	/*
	 * STRATÉGIE: Si la liste des navires n'est pas vide, retirer le dernier navire de la liste
	 */
	public void retirerNavire() {
		if (listeNavires.size() > 0) {
			try {
				listeNavires.remove(listeNavires.size() - 1);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	/**
	 * Fonction qui permet d'annuler le dernier tir
	 * 
	 * @param cible: le joueur adverse
	 */
	/*
	 * STRATÉGIE: Si la liste des positions tirées n'est pas vide, on retire le dernier tir de la liste.
	 * On vérifie ensuite si le tir avait touché afin de l'enlever de la liste des tirs touchés selon le cas.
	 */
	public void retirerTir(AJoueur cible) {

		if (listePositionsTirees.size() > 0) {
			try {

				Position tir = listePositionsTirees.remove(listePositionsTirees.size() - 1);
				ListeDynamique<Position> laListe = cible.getListeTouchees();

				if (laListe.size() > 0) {
					if (tir.egale(laListe.get(laListe.size() - 1))) {

						laListe.remove(laListe.size() - 1);

					}
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	/**
	 * Fonction pour réinitialiser un joueur
	 * 
	 */
	/*
	 * STRATÉGIE: Pour chaque liste pertinente, vider la liste à l'aide d'une boucle while.
	 */
	public void recommencer() {
		while (listeNavires.size() > 0) {
			try {
				while (!listePositionsTirees.estVide())
					listePositionsTirees.remove(0);

				while (!fileDesProchainsTirs.estVide())
					fileDesProchainsTirs.defile();

				while (!listePositionsTouchees.estVide())
					listePositionsTouchees.remove(0);

				while (!listeNavires.estVide())
					listeNavires.remove(0);

			} catch (Exception e) {

				System.out.println(e);
			}

		}

	}
	/**
	 * 
	 * Fonction qui retourne la grille associée au bon joueur
	 * 
	 * @return CaseGrile[][]
	 */
	/*
	 * STRATÉGIE: retourne la grille
	 */
	public CaseGrille[][] getGrille() {
		return grille;
	}
	/**
	 * Grille pour associer une grille à un joueur
	 * 
	 * @param grille: grille à associer au joueur
	 */
	/*
	 * STRATÉGIE: associer la grille à la variable grille du AJoueur
	 */
	public void setGrille(CaseGrille[][] grille) {
		this.grille = grille;
	}
	/**
	 * 
	 * Fonction pour vérifier si un navire a été placé par un joueur
	 * 
	 * @param nomNavire
	 * @return boolean
	 */
	/*
	 * STRATÉGIE: On reçoit le nom d'un navire. On navigue ensuite la liste des navires placés et on compare les noms des navires
	 */
	public boolean navireExiste(String nomNavire) {

		boolean doesExist = false;

		for (int i = 0; i < listeNavires.size(); i++) {

			try {

				if (listeNavires.get(i).getNom() == nomNavire) {

					doesExist = true;

				}

			} catch (Exception e) {

				System.out.println(e);
			}

		}

		return doesExist;

	}
	/**
	 * Fonction qui retourne si un joueur a perdu
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	/*
	 * STRATÉGIE: On compare le nombre de positions dans la liste des positions touchées au nombre nécessaire (17) à la victoire. 
	 * Ce nombre est une addition des cases de tous les navires ( 5 + 4 + 3 + 3 + 2 = 17)
	 */
	public boolean aPerdu() throws Exception {

		boolean joueurAPerdu = false;

		if (listePositionsTouchees.size() == NB_TOUCHES_VICTOIRE)
			joueurAPerdu = true;

		return joueurAPerdu;
	}
	/**
	 * retourne si une position reçue fait contact avec l'un des navires. Si oui, retourner la position.
	 * 
	 * @param pos
	 * @return Position
	 * @throws Exception
	 */
	/*
	 * STRATÉGIE: retourne si une position reçue fait contact avec l'un des navires. Si oui, retourner la position.
	 */
	public Position contains(Position pos) throws Exception {
		
		Position laPosition = null;
		
		for (int i = 0; i < listeNavires.size(); i++) {
			Navire navire = listeNavires.get(i);
			ListeDynamique<Position> positions = navire.getPositions();
			for (int j = 0; j < positions.size(); j++)
				if (positions.get(j).egale(pos))
					laPosition =  positions.get(j);
		}
		return laPosition;

	}
	/**
	 * Les 3 prochaines fonctions retournent la liste demandée.
	 * 
	 * @return ListeDynamique<>
	 */
	/*
	 * STRATÉGIE: retourner la liste
	 */
	public ListeDynamique<Navire> getListeNavires() {

		return listeNavires;
	}
	public ListeDynamique<Position> getListeTirees() {

		return listePositionsTirees;
	}
	public ListeDynamique<Position> getListeTouchees() {

		return listePositionsTouchees;
	}

}
