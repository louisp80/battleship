/**
 * 
 * Cette classe gère les fonctionnalités spécifiques au joueur humain
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
	 * STRATÉGIE: Ajouter la position à la liste de positions tirées. Appeler recevoirTir de l'adversaire
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
	 * Fonction pour set la position à tirer
	 * 
	 * @param pos
	 */
	/*
	 * STRATÉGIE: Reçoit une position à tirer. Setter cette position à la variable positionTir
	 */
	public void setPositionTir(Position pos) {
		this.positionTir = pos;
	}
	/**
	 * Fonction qui suggère une position à tirer au joueur humain
	 * 
	 * @param cible
	 * @return Position
	 * @throws Exception
	 */
	/*
	 * STRATÉGIE: Créer une file de positions potentielles basée sur les positions touchées. Si la file n'est pas vide, retourner 
	 * la première position enfilée
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

