
public class Statistiques {
	
	public int nbVictoires;
	public int nbMatchs;
	public int nbCoups;
	public int nbCoupsMoyens;
	public int nbCoupsTot;
	
	public Statistiques(){
		
		this.nbVictoires = 0;
		this.nbMatchs = 0;
		this.nbCoups = 0;
		this.nbCoupsTot = 0;
		this.nbCoupsMoyens = 0;
		
	}
	public void coupsMoyens(){
		
		nbCoupsTot += nbCoups;
		nbCoupsMoyens = nbCoupsTot/nbMatchs;
		
		
	}
	


}
