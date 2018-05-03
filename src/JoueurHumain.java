/**
 * 
 * Cette classe g�re les fonctionnalit�s sp�cifiques au joueur humain
 * 
 * @author Louis Raymond-Poirier
 * @author Djaoud Mohamed Said
 * @version 2.0
 *
 */
public class JoueurHumain extends AJoueur {
	private Position positionTir;

	public JoueurHumain(CaseGrille[][] grille) {
		this.setGrille(grille);
	}

	/*
	 * STRAT�GIE: Ajouter la position � la liste de positions tir�es. Appeler recevoirTir de l'adversaire
	 */
	@Override
	public void tir(IJoueur cible) {
		
		try {
			listePositionsTirees.add(positionTir);			
			cible.recoitTir(positionTir);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Fonction pour set la position � tirer
	 * 
	 * @param pos
	 */
	/*
	 * STRAT�GIE: Re�oit une position � tirer. Setter cette position � la variable positionTir
	 */
	public void setPositionTir(Position pos) {
		this.positionTir = pos;
	}
	/**
	 * Fonction qui sugg�re une position � tirer au joueur humain
	 * 
	 * @param cible
	 * @return Position
	 * @throws Exception
	 */
	/*
	 * STRAT�GIE: Cr�er une file de positions potentielles bas�e sur les positions touch�es. Si la file n'est pas vide, retourner 
	 * la premi�re position enfil�e
	 */
	public Position aide(AJoueur cible) throws Exception{
		
		Position laPos = null;
		fileDesProchainsTirs = new FileDynamique<Position>();
		
		for(int i = 0 ; i < cible.getListeTouchees().size() ;i++){
			this.modeCible(cible.getListeTouchees().get(i));
			if(fileDesProchainsTirs.size() > 0)
				laPos = fileDesProchainsTirs.defile();
		}
		return laPos;
	}

}

