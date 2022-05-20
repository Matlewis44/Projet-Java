package modele;

import java.util.Collection;
import java.util.TreeSet;
	
/**
 * CalendrierDuMois est la classe représentant toute les dates d'un mois.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */	
	
public class CalendrierDuMois {
	/**
	 * dates est la collection qui contient les dates du mois
	 */
	public Collection <Date> dates;
	/**
	 * mois2 représente le mois
	 */
	public int mois2;
	/**
	 * Tableau contenant la chaine de caractère des mois.
	 */
	private String[] MOIS={"", "Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
	/**
	 * La constructeur CalendrierDuMois permet de creer toute les dates d'un mois d'une année donner en paramètre.
	 * @param mois un entier représentant un mois.
	 * @param annee un entier représentant une annee
	 */	
	public CalendrierDuMois (int mois, int annee) {
		dates = new TreeSet <Date> ();
		Date date = new Date (1, mois, annee);
		int indiceJour = date.getJourSemaine() == 1 ? 6 : date.getJourSemaine()-2;
		for (int indice = indiceJour ; indice>=0 ; indice--) {
			dates.add(date);
			date = date.dateDeLaVeille();
		}
		date = new Date (2,mois,annee);
		indiceJour = indiceJour+1 % 7 ;
		while (date.getMois () == mois) {
			while(indiceJour<7) {
				dates.add(date);
				date = date.dateDuLendemain();
				indiceJour++ ;
			}
			indiceJour=0;
		}
		mois2 = mois;
	}
	/**
	 * La methode getDates retourne les dates de la collection Date.
	 * @return Collection date, une collection de date.
	 */
	public Collection <Date> getDates() {
		return dates;
	}
	/**
	 * @return String, une chaine de caractère : le mois, le nombre de dates, ainsi que ces dates.
	 */
	public String toString(){
		return MOIS[mois2]+ " " + getDates().size() +" dates: "+ dates;
	}
	/**
	 * La méthode getMois retourne le mois demandé par un entier en paramètre.
	 * @param num un entier pour le numéro du mois (1 = Janvier, 12 = Décembre)
	 * @return String, une chaine de caractère : le mois demandé.
	 */
	public String getMois(int num){
		return MOIS[num];
	}
	
	
}