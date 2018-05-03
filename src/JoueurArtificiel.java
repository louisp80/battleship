import javax.swing.JOptionPane;

/**
 * 
 * Cette classe g�re les fonctionnalit�s sp�cifiques au joueur artificiel
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
	 * STRAT�GIE: Lorsqu'il n'y a plus de tirs potentiels, set au mode chasse(tirs al�atoires). Pour tirer,
	 * appeler recoitTir(Position) de l'adversaire. Si la liste des positions potentielles compte au moins
	 * 1 �l�ment, d�filer et tirer.
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
	 * Fonction qui g�n�re une position al�atoire
	 * 
	 * @return Position
	 * @throws Exception
	 */
	/*
	 * STRAT�GIE: G�n�rer une position en X et en Y al�atoire puis v�rifier si cette position a d�j� �t� tir�e.
	 * Si oui, g�n�rer nouvelle position. Retourner la position
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
	 * Fonction qui dit au AI � quelle difficult� jouer
	 * 
	 * @param difficulte
	 */
	/*
	 * STRAT�GIE: set la difficult�
	 */
	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

}
