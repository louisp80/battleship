import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


/**
 * 
 * Cette classe fait office de classe main et s'occupe de cr�er l'interface
 * 
 * @author Louis Raymond-Poirier
 * @author Djaoud Mohamed Said
 * @version 2.0
 *
 */
public class NewInterface extends JFrame {
	
	private Statistiques stats = new Statistiques();
	
	private static final long serialVersionUID = 1L;
	private static int choixDifficulte;

	private JPanel contentPane;
	private JButton boutonClique;
	private JTextArea zoneTexte;
	private JScrollPane conteneurTexte;
	private JPanel historiqueJoueur;
	JPanel historiqueAdversaire;

	private boolean secondClic = false;
	private boolean finPlacerNavires = false;
	private Position premiereCasePos;
	private Position deuxiemeCasePos;

	private JoueurHumain joueur;
	private JoueurArtificiel joueurIA;
	private boolean partieCommence = false;

	private String[] nomsNavires = { "Porte-avion", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur" };
	private String[] lesAutresBoutonsTxt = { "Annuler", "Aide", "Recommencer" };
	private JButton[] lesBoutonsBateau;
	private JButton[] lesAutresBoutons;

	private int TAILLE_PORTE_AVION = 5;
	private int TAILLE_CROISEUR = 4;
	private int TAILLE_CONTRE_TORPILLEUR = 3;
	private int TAILLE_SOUS_MARIN = 3;
	private int TAILLE_TORPILLEUR = 2;
	private int NOMBRE_DE_NAVIRE = 5;
	private int LONGUEUR_DE_LA_GRILLE = 11;
	private int LARGEUR_DE_LA_GRILLE = 11;
	private int HISTORIQUE_COUPS = 6;

	/**
	 * lance l'application
	 * 
	 * @param args
	 */
	/*
	 * STRAT�GIE: appelle le sous-programme qui cr�e l'interface dans un invokeLater
	 */
	public static void main(String[] args) {

		choixDeLaDifficulte();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewInterface frame = new NewInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Cr�e l'interface 
	 */
	/*
	 * STRAT�GIE: Cr�ation et positionnement des diff�rents conteneurs, boutons, etc.
	 * On g�re les �v�nements associ�s � chaque bouton.
	 */
	public NewInterface() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 635, 765);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Bataille navale");

		JPanel panelDeGauche = new JPanel();
		panelDeGauche.setBounds(10, 10, 150, 600);
		contentPane.add(panelDeGauche);
		panelDeGauche.setLayout(null);

		JPanel panelBoutons = new JPanel();
		panelBoutons.setBounds(0, 20, 150, 245);
		panelBoutons.setLayout(new GridLayout(0, 1));
		panelDeGauche.add(panelBoutons);

		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(0, 1, 0, 0));
		panelCentral.setBounds(180, 10, 290, 600);
		contentPane.add(panelCentral);

		JPanel panelCinqDerniersEvenements = new JPanel();
		panelCinqDerniersEvenements.setBounds(480, 10, 150, 600);
		panelCinqDerniersEvenements.setLayout(new GridLayout(0,1));
		contentPane.add(panelCinqDerniersEvenements);
		
		
		
		historiqueAdversaire= new JPanel();
		historiqueAdversaire.setLayout(new GridLayout(6,1));
		JLabel titreA = new JLabel("5 derniers coups");
		historiqueAdversaire.add(titreA);
		
		historiqueJoueur= new JPanel();
		historiqueJoueur.setLayout(new GridLayout(6,1));
		JLabel titreJ = new JLabel("5 derniers coups");
		historiqueJoueur.add(titreJ);
		
		panelCinqDerniersEvenements.add(historiqueAdversaire);
		panelCinqDerniersEvenements.add(historiqueJoueur);

		zoneTexte = new JTextArea(5, 20);
		conteneurTexte = new JScrollPane(zoneTexte);
		conteneurTexte.setBounds(10, 620, 600, 96);
		zoneTexte.setEditable(false);
		contentPane.add(conteneurTexte);

		// Cr�er les deux grilles
		JPanel grilleAdversaire = new JPanel();
		JLabel labelAdv = new JLabel("Adversaire");
		JPanel grille_joueur = new JPanel();
		JLabel labelJoueur = new JLabel("Joueur");
		panelCentral.add(grilleAdversaire);
		panelCentral.add(grille_joueur);


		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		lesBoutonsBateau = new JButton[nomsNavires.length];
		lesAutresBoutons = new JButton[lesAutresBoutonsTxt.length];

		joueur = new JoueurHumain(creerInterfaceJoueur(labelJoueur, grille_joueur));
		joueurIA = new JoueurArtificiel(creerInterfaceJoueur(labelAdv, grilleAdversaire));
		joueurIA.setDifficulte(choixDifficulte);

		for (int i = 0; i < nomsNavires.length; i++) {

			lesBoutonsBateau[i] = new JButton(nomsNavires[i]);
			lesBoutonsBateau[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {

					boutonClique = (JButton) event.getSource();
					imprimer("Poser " + boutonClique.getText());

					desactiverBoutons();

				}
			});

			panelBoutons.add(lesBoutonsBateau[i]);

		}

		for (int i = 0; i < lesAutresBoutonsTxt.length; i++) {

			lesAutresBoutons[i] = new JButton(lesAutresBoutonsTxt[i]);

			lesAutresBoutons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {

					boutonClique = (JButton) event.getSource();

					if (boutonClique.getText().equals("Recommencer")) {
						recommencer();
					} else if (boutonClique.getText().equals("Annuler")) {
						annuler();
					}else if(boutonClique.getText().equals("Aide")){
						aide();						
					}

					boutonClique = null;
					activerBoutons();

				}
			});

			panelBoutons.add(lesAutresBoutons[i]);

		}
	}
	/**
	 * Cr�e une grille de jeu
	 * 
	 * @param label
	 * @param leJoueur
	 * @return laGrille
	 */
	/*
	 * STRAT�GIE: a l'aide de boucles for imbriqu�es, remplir le tableau 2D de CaseGrilles, 
	 * qui �tendent la classe JButton. Pour la premi�re rang�e et la premi�re colonne d'une grille,
	 * �crire les coordonn�es associ�es
	 */
	public CaseGrille[][] creerInterfaceJoueur(JLabel label, JPanel leJoueur) {

		leJoueur.setLayout(new BorderLayout());

		CaseGrille[][] laGrille = new CaseGrille[LONGUEUR_DE_LA_GRILLE][LARGEUR_DE_LA_GRILLE];
		JPanel conteneurGrille = new JPanel();
		conteneurGrille.setLayout(new GridLayout(LONGUEUR_DE_LA_GRILLE, LARGEUR_DE_LA_GRILLE));

		for (int ligne = 0; ligne < LONGUEUR_DE_LA_GRILLE; ligne++) {

			for (int colonne = 0; colonne < LARGEUR_DE_LA_GRILLE; colonne++) {

				laGrille[ligne][colonne] = new CaseGrille(ligne, colonne);
				CaseGrille laCase = laGrille[ligne][colonne];
				laCase.setBorder(new LineBorder(Color.GRAY, 1));
				conteneurGrille.add(laCase);

				laCase.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {

						if (!partieCommence) {
							JScrollBar vertical = conteneurTexte.getVerticalScrollBar();
							vertical.setValue(vertical.getMaximum());

							desactiverGrillePourUnDesDeuxJoueur(joueurIA, joueur);
							placerNaviresJoueur(laCase, boutonClique, laGrille);
						} else {

							Position laPosition = laCase.getPosition();
							tirer(laPosition);
						}
					}
				});

				if (colonne == 0) {

					laCase.setBackground(Color.LIGHT_GRAY);
					laCase.setEnabled(false);

					if (ligne != 0) {

						int chiffreLigne = 48 + ligne;
						laCase.setText(Character.toString((char) chiffreLigne));

						if (ligne == 10) {

							laCase.setText("10");

						}
					}

				} else if (ligne == 0) {

					laCase.setBackground(Color.LIGHT_GRAY);
					int lettreColonne = 64 + colonne;
					laCase.setEnabled(false);
					;
					laCase.setText(Character.toString((char) lettreColonne));

				} else {
					laCase.setBackground(Color.WHITE);

				}
			}
		}
		leJoueur.add(label, BorderLayout.NORTH);
		leJoueur.add(conteneurGrille);
		return laGrille;
	}
	
	
	/**
	 * Le joueur place ses navires sr sa grille. Il recoit:
	 * @param caseClique: La case  choisie
	 * @param boutonClique: Quel bouton/ateau a �t� choisi
	 * @param laGrille: la grille du  joueur
	 */
	/*
	 * STRAT�GIE: Calcul le nombre de cases entre les deux cases cliqu�es. D�termine ensuite si ce nombre 
	 * correspond a nombre de cases n�c�ssit�es par le type de bateau choisi.
	 * Si le nombre ne correspond pas ou si le bateau n'est pas en ligne droite, imprimer message.
	 * Appeller le rafraichissement de la grille
	 */
	public void placerNaviresJoueur(CaseGrille caseClique, JButton boutonClique, CaseGrille[][] laGrille) {
		if (boutonClique == null) {
			imprimer(caseClique.getPosition().toString());
		} else {
			String nomBouton = boutonClique.getText();
			if (!secondClic) {
				premiereCasePos = caseClique.getPosition();
				secondClic = true;
			} else {
				// Trouve le nombre de cases s�l�ctionn�s par le joueur (peut
				// choisir dans les les 4 directions)
				// De droite � gauche, de gauche � droite, de haut vers bas, de
				// bas vers haut.
				deuxiemeCasePos = caseClique.getPosition();

				int nombreCases = 0;
				int tailleNavire = 0;

				int departX = premiereCasePos.getX();
				int finX = deuxiemeCasePos.getX();

				// v�rifie la direction
				if (departX > finX) {
					departX = deuxiemeCasePos.getX();
					finX = premiereCasePos.getX();
				}

				int departY = premiereCasePos.getY();
				int finY = deuxiemeCasePos.getY();

				// v�rifie la direction
				if (departY > finY) {
					departY = deuxiemeCasePos.getY();
					finY = premiereCasePos.getY();
				}

				// calcul du nombre de cases
				if (departX == finX || departY == finY) {
					for (int i = departX; i <= finX; i++) {
						for (int y = departY; y <= finY; y++) {
							nombreCases++;
						}
					}
				}

				tailleNavire = quelEstLeNavire(nomBouton);

				if (tailleNavire == nombreCases) {

					Navire leNavire = new Navire(nomBouton, departX, departY, finX, finY);

					try {
						ajouterNavire(joueur, leNavire);

					} catch (Exception e) {

						imprimer(e.getMessage());

					}

					activerBoutons();

				} else {

					imprimer("Un " + nomBouton + " doit faire " + tailleNavire
							+ " cases et doit �tre en horizontale ou verticale !");

				}

				// verifier que les cases ne sont pas prises
				refreshGrille(joueur);
				secondClic = false;
			}
		}

	}
	/**
	 * Fonction qui g�re le placement de navires pour l'intelligence artificielle
	 */
	/*
	 * STRAT�GIE: Pour chaque navire, on g�n�re une position x et y al�atoire, puis on d�cide al�atoirement si
	 * le navire sera vertical ou horizontal. On v�rifie ensuitesi le navire peut �tre contenu dans la grille
	 * selon sa position et si oui, l'ajouter.
	 */
	public void placerNaviresIA() {

		finPlacerNavires = true;

		int tailleNavire = 0;
		int posX_debut;
		int posX_fin;
		int posY_debut;
		int posY_fin;
		boolean vertical;
		boolean navireEstPlace;

		for (String nomNavire : nomsNavires) {

			navireEstPlace = false;

			tailleNavire = quelEstLeNavire(nomNavire);

			while (!navireEstPlace) {

				vertical = Math.random() < 0.5;

				if (vertical) {

					posX_debut = (int) ((Math.random() * 10) + 1); // 8 + 1 max
					posX_fin = posX_debut;

					posY_debut = (int) ((Math.random() * (10 - (tailleNavire - 1))) + 1);
					posY_fin = posY_debut + (tailleNavire - 1);

				} else {

					posX_debut = (int) ((Math.random() * (10 - (tailleNavire - 1))) + 1);
					posX_fin = posX_debut + (tailleNavire - 1);

					posY_debut = (int) ((Math.random() * 10) + 1);
					posY_fin = posY_debut;

				}

				Navire leNavire = new Navire(nomNavire, posX_debut, posY_debut, posX_fin, posY_fin);

				try {

					ajouterNavire(joueurIA, leNavire);
					navireEstPlace = true;

				} catch (Exception e) {

					System.out.println(e.getMessage());

				}

			}

		}

		imprimer("Navires de l'ennemi plac�s");

	}
	/**
	 * Fonction qui demande la difficult� de l'IA
	 */
	/*
	 * STRAT�GIE:  Afficher une fenetre qui offre l'option facile et l'option normal
	 * et enregistrer la r�ponse
	 */
	public static void choixDeLaDifficulte() {

		Object[] nomBoutton = { "Facile", "Normale" };

		choixDifficulte = JOptionPane.showOptionDialog(null, "� quelle difficult�e voulez-vous jouer ?",
				"Niveau de difficult�", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, nomBoutton,
				nomBoutton[0]);
	}
	/**
	 * Fonction qui ajoute un navire sur la grille pour le joueur
	 * 
	 * @param leJoueur: le joueur qui ajoute un navire sur sa grille
	 * @param leNavire: le navire � ajouter
	 * @throws Exception
	 */
	/*
	 * STRAT�GIE: D'embl�e on v�rifie si les positions du navires sont superpos�es � celles
	 * d'un navire d�j� sur la grille. Si oui, afficher un message
	 * On v�rifie ensuite si le navire est contenu dans la grille. Lorsque tous les navires sont plac�s
	 * on appelle placerNavireIAS
	 */
	public void ajouterNavire(AJoueur leJoueur, Navire leNavire) throws Exception {

		if (leNavire != null) {

			// Pour chaque position du navire
			for (int i = 0; i < leNavire.getPositions().size(); i++) {

				Position posBateau = leNavire.getPositions().get(i);

				// Pour chaque navire du joueur
				for (int j = 0; j < leJoueur.getListeNavires().size(); j++) {

					// Pour chaque position d'un navire du joueur
					for (int k = 0; k < leJoueur.getListeNavires().get(j).getPositions().size(); k++) {

						// les positions occup�es
						Position posPleines = leJoueur.getListeNavires().get(j).getPositions().get(k);

						if (posBateau.toString().equals(posPleines.toString())) {
							throw new Exception("Vous ne pouvez pas poser vos navires les uns par dessus les autres");
						}
					}
				}
			}

			if (leNavire.getPositionFin().getX() > 10 || leNavire.getPositionFin().getY() > 10) {

				throw new Exception(leNavire.getPositions() + " - pas dans la grille");

			}

			if (!leJoueur.navireExiste(leNavire.getNom())) {

				leJoueur.placerNavire(leNavire);
				imprimer("Vous posez un " + leNavire.getNom() + " de la case " + leNavire.getPositionDebut() + " � "
						+ leNavire.getPositionFin());

			} else {

				imprimer("Ce navire est d�j� sur la grille");

			}

			// Si tous les navires sont plac�s, d�but de la partie
			if (leJoueur.getListeNavires().size() == NOMBRE_DE_NAVIRE) {

				if (!finPlacerNavires) {

					placerNaviresIA();
					desactiverGrillePourUnDesDeuxJoueur(joueur, joueurIA);
					partieCommence = true;
					imprimer("Debut jeu");
				}

			}
		}

	}
	/**
	 * Fonction qui g�re le tir du joueur sur l'IA
	 * @param laPosition: la position choisie
	 */
	/*
	 * STRAT�GIE: appeller la fonction tir du joueur puis rafraichir les grilles. V�rifier si l'un ou l'autre des joueurs a perdu.
	 * Si oui, arr�ter la partie et afficher un message.
	 */
	public void tirer(Position laPosition){
		
		try {
			joueur.setPositionTir(laPosition);

			if (!joueur.dejaTire(laPosition)) {
				
				joueur.tir(joueurIA);
				stats.nbCoups++;
				
				if(joueurIA.aPerdu()){
					
					finMatch();
					
				}else{
					
					joueurIA.tir(joueur);	
					
					if(joueur.aPerdu()){
						
						finMatch();
						
					}
				}
				
				refreshGrilles();
				
			} else {
				JOptionPane.showMessageDialog(null, "Vous avez d�j� tir� sur cette case !");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * fonction qui affiche la fin de la partie
	 */
	/*
	 * STRATEGIE: ajouter les valeurs aux statistiques. Afficher les sttistiques et les messages pertinents.
	 */
	public void finMatch(){
		
		stats.nbMatchs++;
		stats.coupsMoyens();
		
		try{
			
			if(joueurIA.aPerdu()){
				stats.nbVictoires++;
				imprimer("F�licitation vous avez gagn�!");

			}else if(joueur.aPerdu()){
				imprimer("Vous avez �t� d�fait par l'intelligence artificielle");
			}
			
		}catch(Exception e){
			System.out.println(e);
		}

		imprimer("Nb de coups : " + stats.nbCoups);
		imprimer("Nb de matchs : " + stats.nbMatchs);
		imprimer("Nb de victoires : " + stats.nbVictoires);
		imprimer("Nb de coups moyens : " + stats.nbCoupsMoyens);
		
		partieCommence = false;
		
		

		
	}
	/**
	 * Fonction qui sugg�re une case au joueur
	 */
	/*
	 * STRAT�GIE: Appeller la fonction aide du joueur qui retourne une position. Imprimer la position
	 */
	public void aide(){
		try{
			
			Position pos = joueur.aide(joueurIA);
			if(pos != null)
				imprimer("Nous vous sugg�rons la position : "+ pos);
			else
				imprimer("Nous n'avons aucune suggestion � vous proposer.");
			
		}catch(Exception e){
			System.out.println(e);	
		}		
	}
	/**
	 * Fonction qui retire un bateau mal positionn� ou qui annulele dernier coup du joueur
	 */
	/*
	 * STRAT�GIE: En d�but de partie, retirer le dernier navire ajout�. Une fois la partie commenc�e,
	 * appeller la fonction retireTir de chaque joueur puis rafraichir les grilles.
	 */
	public void annuler() {
		if (partieCommence) {
			joueur.retirerTir(joueurIA);
			joueurIA.retirerTir(joueur);
			refreshGrilles();
		} else if(finPlacerNavires){
			
		}else{
			joueur.retirerNavire();
			refreshGrille(joueur);
		}

	}
	/**
	 * Fonction pour recommencer la partie
	 */
	/*
	 * STRAT�GIE: Appeller les fonctions recommencer de chaue joueur. Rafraichir les grilles.
	 */
	public void recommencer() {

		joueur.recommencer();
		joueurIA.recommencer();
		refreshGrilles();
		partieCommence = false;
		finPlacerNavires = false;
		activerBoutons();
		choixDeLaDifficulte();
		joueurIA.setDifficulte(choixDifficulte);
		imprimer("Partie recommenc�e");
		desactiverGrillePourUnDesDeuxJoueur(joueurIA, joueur);
	}

	/**
	 * Fonction qui active les boutons de navires qui ne sont pas d�j� sur la grille
	 */
	/*
	 * STRAT�GIE: V�rifier si le navire es sur la grille. Si non, activer le bouton associ�.
	 */
	public void activerBoutons() {

		for (JButton bouton : lesBoutonsBateau) {

			if (!joueur.navireExiste(bouton.getText())) {
				bouton.setEnabled(true);
			}
		}
		boutonClique = null;

	}

	/**
	 * Fonction qui d�sactive les boutons de navires.
	 */
	/*
	 * STRAT�GIE: D�sactiver chaque bouton.
	 */
	public void desactiverBoutons() {

		for (JButton bouton : lesBoutonsBateau) {

			bouton.setEnabled(false);

		}

	}
	/**
	 * fonction pour empecherdecliquer sur la grille de l'Adversaire
	 * 
	 * @param joueurGrilleDesactiver
	 * @param joueurGrilleActiver
	 */
	/*
	 * STRAT�GIE: d�sactiver la grille de l'Adversaire et activer la grille du joueur
	 */
	public void desactiverGrillePourUnDesDeuxJoueur(AJoueur joueurGrilleDesactiver, AJoueur joueurGrilleActiver) {

		for (int ligne = 1; ligne < LONGUEUR_DE_LA_GRILLE; ligne++) {

			for (int colonne = 1; colonne < LARGEUR_DE_LA_GRILLE; colonne++) {
				joueurGrilleDesactiver.grille[ligne][colonne].setEnabled(false);
				joueurGrilleActiver.grille[ligne][colonne].setEnabled(true);
			}
		}
	}

	/**
	 * Fonction qui rafraichi les deux grilles
	 * 
	 */
	/*
	 * STRAT�GIE: appeller refreshGrille pour chaque joueur
	 */
	public void refreshGrilles(){
		refreshGrille(joueur);
		refreshGrille(joueurIA);
	}
	/**
	 * Fonction pour rafraichier la grille. Afficher les bateaux plac�s, les cases tir�es, 
	 * les cases touch�es
	 * @param leJoueur
	 */
	/*
	 * STRAT�GIE: D'embl�e, mettre toutes les cases en blanc. Ensuite, parcourir toutes les positions
	 * de tous les bateaux plac�s. MEttre ces postions en bleu.
	 * Parcourir les positions tir�es. Si la position est touch�e, mettre en rouge. Sinon, mettre un "X"
	 */
	public void refreshGrille(AJoueur leJoueur) {
		
		boolean setRouge = false;
		ListeDynamique<Navire> lesNavires = leJoueur.getListeNavires();
		ListeDynamique<Position> positionsTouchees = leJoueur.getListeTouchees();
		CaseGrille[][] laGrille = leJoueur.getGrille();
		AJoueur adversaire;
		
		if(leJoueur==joueur){
			adversaire = joueurIA;
		}else{
			adversaire = joueur;
		}
		
		ListeDynamique<Position> positionsTirees = adversaire.getListeTirees();
		
		
		
		//Toutes les cases deviennent blanches
		for (int ligne = 1; ligne < LONGUEUR_DE_LA_GRILLE; ligne++) {
			for (int colonne = 1; colonne < LARGEUR_DE_LA_GRILLE; colonne++) {
				laGrille[ligne][colonne].setBackground(Color.WHITE);
				laGrille[ligne][colonne].setText("");
			}
		}
		
		if(leJoueur == joueur){
			//Les positions contenant un bateau deviennent bleues
			for (int i = 0; i < lesNavires.size(); i++) {			

				try {

					ListeDynamique<Position> positionsNavire = lesNavires.get(i).getPositions();

					for (int j = 0; j < positionsNavire.size(); j++) {

						Position pos = positionsNavire.get(j);
						laGrille[pos.getX()][pos.getY()].setBackground(Color.BLUE);

					}

				} catch (Exception e) {

					System.out.println(e);

				}
			}
		}
		//Les positions tir�es dans le vide: �crire "x"
		for (int i = 0; i < positionsTirees.size(); i++) {			
			
			try {
				
				setRouge = false;
				Position posX = positionsTirees.get(i);
				
				//Les positions de bateau touch� deviennent rouges
				for (int j = 0; j < positionsTouchees.size(); j++) {			
						
					Position posR = positionsTouchees.get(j);
					
					if(posX.egale(posR)){
						
						setRouge = true;
						
					}	
				}
				if(setRouge){
					laGrille[posX.getX()][posX.getY()].setBackground(Color.RED);
				}else{
					laGrille[posX.getX()][posX.getY()].setText("X");
				}
				
				

			} catch (Exception e) {

				System.out.println(e);

			}
		}
		/////////////5 derniers coups//////////////
		cinqDerniersCoups(leJoueur);
		
		
		
		
		
	}
	/**
	 * Fonction qui affiche les cinq derniers coups
	 * @param leJoueur
	 */
	/*
	 * STRAT�GIE: Effacer l'historique dans le JPanel. Ensuite, parcourir la liste des positions
	 * tir�es. Pour chacune d'elle, v�rifier si elle a touch�. �crire la position dans un nouveau JLabel
	 * suivi de "Touche" ou "Coule" selon le cas. Ajouter le JLabel au JPanel.
	 */
	public void cinqDerniersCoups(AJoueur leJoueur){
		
		JPanel lePanel;
		
		if(leJoueur == joueurIA){
			lePanel = historiqueJoueur;
			
		}else{
			lePanel = historiqueAdversaire;
		}
		
		while(lePanel.getComponentCount()>1){
			lePanel.remove(1);
			lePanel.repaint();
		}
		for(int i = 1; i<HISTORIQUE_COUPS; i++){
			
			if(leJoueur.getListeTirees().size()>=i){
				
				try{
					
					Position laPos = leJoueur.getListeTirees().get(leJoueur.getListeTirees().size()-i);
					String leTexte = laPos.toString() + "  -  " + toucheCoule(laPos, leJoueur);
					JLabel unCoup = new JLabel(leTexte);
					lePanel.add(unCoup);
				
					
				}catch(Exception e){
					System.out.println(e);
				}
				
			
			}
			
		}
	}
	/**
	 * fonction qui v�rifie si la position a touch� un bateau ou si elle a rat�
	 * 
	 * @param laPos
	 * @param leJoueur
	 * @return
	 */
	/*
	 * STRAT�GIE: Recoit en param�tre une position tir�e � v�rifier. Parcourir la liste des positions
	 * touch�es et les comparer � la position tir�e. Retourner un string selon la situation approppri�e
	 */
	public String toucheCoule(Position laPos, AJoueur leJoueur){
		
		AJoueur adversaire;
		
		if(leJoueur==joueur){
			adversaire = joueurIA;
		}else{
			adversaire = joueur;
		}
		String leTexte = "Coule";
		ListeDynamique<Position> laListe = adversaire.getListeTouchees();
		
		for(int i = 0; i<laListe.size(); i++){
			try{
				if(laPos.egale(laListe.get(i))){
					leTexte = "Touche";
				}
				
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		return leTexte;
		
	}

	/**
	 * Fonction qui imprime une r�troaction dans la zone de texte au bas de l'interface
	 * 
	 * @param leTexte
	 */
	/*
	 * STRAT�GIE: Ajouter le texte � la zone de texte. Inscrire un retour � la ligne
	 */
	public void imprimer(String leTexte) {
		zoneTexte.append(leTexte + "\n");
	}
	/**
	 * Fonction qui d�termine le nombre de cases associ�es au bateau.
	 * @param nomNavire
	 * @return int
	 */
	/*
	 * STRAT�GIE: Selon le nomdu navire, d�terminer le nombre de case qu'il doit avoir �
	 * l'aide d'un switch/case. Retourner cette valeur.
	 */
	public int quelEstLeNavire(String nomNavire) {

		int tailleNavire = 0;

		switch (nomNavire) {
		case "Porte-avion":
			tailleNavire = TAILLE_PORTE_AVION;
			break;
		case "Croiseur":
			tailleNavire = TAILLE_CROISEUR;
			break;
		case "Contre-torpilleur":
			tailleNavire = TAILLE_CONTRE_TORPILLEUR;
			break;
		case "Sous-marin":
			tailleNavire = TAILLE_SOUS_MARIN;
			break;
		case "Torpilleur":
			tailleNavire = TAILLE_TORPILLEUR;
			break;
		}

		return tailleNavire;
	}
}
