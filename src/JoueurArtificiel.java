import javax.swing.JOptionPane;

/**
 * 
 * Cette classe gère les fonctionnalités spécifiques au joueur artificiel
 * 
 * @author Louis Raymond-Poirier
 * @author Djaoud Mohamed Said
 * @version 2.0
 *
 */
public class JoueurArtificiel extends AJoueur {

	private boolean modeChasse = true;
	private Position posTiree;
	private int difficulte;

	public JoueurArtificiel(CaseGrille[][] grille) {
		setGrille(grille);
	}
	/*
	 * STRATÉGIE: Lorsqu'il n'y a plus de tirs potentiels, set au mode chasse(tirs aléatoires). Pour tirer,
	 * appeler recoitTir(Position) de l'adversaire. Si la liste des positions potentielles compte au moins
	 * 1 élément, défiler et tirer.
	 */
	@Override
	public void tir(IJoueur cible) {

		try {

			if (fileDesProchainsTirs.estVide()) {
				modeChasse = true;
			}

			if (modeChasse) {
				posTiree = tirRandom();
				listePositionsTirees.add(posTiree);

				if (cible.recoitTir(posTiree)) {
					if (difficulte == JOptionPane.NO_OPTION) {
						modeChasse = false;
						this.modeCible(posTiree);						
					}
				}
			} else {
				posTiree = fileDesProchainsTirs.defile();
				listePositionsTirees.add(posTiree);				
				if (cible.recoitTir(posTiree)) {
					this.modeCible(posTiree);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Fonction qui génère une position aléatoire
	 * 
	 * @return Position
	 * @throws Exception
	 */
	/*
	 * STRATÉGIE: Générer une position en X et en Y aléatoire puis vérifier si cette position a déjà été tirée.
	 * Si oui, générer nouvelle position. Retourner la position
	 */
	public Position tirRandom() throws Exception {
		Position positionRandom = null;

		do {
			int positionX = (int) ((Math.random() * 10) + 1);
			int positionY = (int) ((Math.random() * 10) + 1);

			positionRandom = new Position(positionX, positionY);
		} while (dejaTire(positionRandom));

		return positionRandom;
	}
	/**
	 * Fonction qui dit au AI à quelle difficulté jouer
	 * 
	 * @param difficulte
	 */
	/*
	 * STRATÉGIE: set la difficulté
	 */
	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

}
