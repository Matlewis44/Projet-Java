package modele;

import java.io.Serializable;

/**
 * Evenement est la classe représentant les Evenements.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class Evenement implements Comparable <Evenement>, Serializable{
	/**
	 * chDate représente la date 
	 */
	private Date chDate;
	/**
	 * chNom représente le nom 
	 */
	private String chNom;
	
	/**
	 * chLieu représente le lieu 
	 */
	private String chLieu;
	
	/**
	 * hDebut représente l'heure du début de l'évènement 
	 */
	private String hDebut;
	/**
	 * hFin représente l'heure de la fin de l'évènement 
	 */
	private String hFin;
	/**
	 * mDebut représente les minutes du début de l'évènement 
	 */
	private String mDebut;
	/**
	 * mFin représente les minutes de la fin de l'évènement 
	 */
	private String mFin;
	
	/**
	 * description représente la description de l'évènement
	 */
	private String description;
	
	/**
	 * Le constructeur Evenement crée un evenement à l'aide de donnée passé en paramètre.
	 * @param parDate la date de l'évènement 
	 * @param parNom le nom de l'évènement 
	 * @param parLieu le lieu de l'évènement 
	 * @param parhDebut l'heure de début de l'évènement 
	 * @param parmDebut les minutes de la fin de l'évènement 
	 * @param parhFin l'heure de la fin de l'évènement 
	 * @param parmFin les minutes de la fin de l'évènement 
	 * @param pardescription la description de l'évènement  
	 */
	public Evenement(Date parDate, String parNom, String parLieu, String parhDebut, String parmDebut, String parhFin, String parmFin, String pardescription){
		chDate = parDate;
		chNom = parNom;
		chLieu = parLieu;
		hDebut = parhDebut;
		hFin = parhFin;
		mDebut = parmDebut;
		mFin = parmFin;
		description = pardescription;
		chNbEvtInstancier++;
	}

	/** 
	 * @return chaine de caractère contenant la description de l'Evenement.
	 */
	public String toString(){
		return chNom+ " se situe a " +chLieu+" le "+chDate+ " de " +hDebut+ " : " + mDebut + ", à " +hFin+ " : " + mFin + " " +description;
	}

	/** 
	 * La methode getDate retourne la date
	 * @return Date, la date de l'evenement.
	 */
	public Date getDate(){
		return chDate;
	}
	
	/** 
	 * La methode setDate remplace la date de l'evenement par la date passé en parametre.
	 * @param parDate, la nouvelle date. 
	 */
	public void setDate(Date parDate){
		if(parDate.estValide())
			chDate = parDate;
	}
	
	/** 
	 * La methode getNom retourne le nom.
	 * @return String, le nom de l'evenement.
	 */
	public String getNom(){
		return chNom;
	}
	
	/**
	 * La methode setNom remplace le nom de l'evenement par le nom passé en parametre.
	 * @param parNom, une chaine de caractère le nouveau nom. 
	 */
	public void setNom(String parNom){
		chNom = parNom;
	}
	
	/** 
	 * La methode getLieu retourne le lieu.
	 * @return String, le lieu de l'evenement.
	 */
	public String getLieu(){
		return chLieu;
	}
	
	/**
	 * La methode setLieu remplace le lieu de l'evenement par le lieu passé en parametre.
	 * @param parLieu, une chaine de caractère le nouveau lieu.
	 */
	public void setLieu(String parLieu){
			chLieu = parLieu;
	}

	/**
	 * nombre d'evenement instancier.
	 */
	private static int chNbEvtInstancier;// initialiser par defaut a 0
	
	/*
	 * La methode getNbEvtInstancier retourne le nombre d'evenement instancier.
	 * @return int, le nombre d'evenement instancier.
	 */
	public static int getNbEvtInstancier(){
		return chNbEvtInstancier;
	}

	/* lire les evenements
     */

	/*public Evenement lireEvenement(){
		chDate = Date.lireDate();
		chNom = Clavier.lireString();
		chLieu = Clavier.lireString();

		return new Evenement(chDate, chNom, chLieu);
		
	}*/
	
	/**
	 * La methode compareTo compare 2 évenements.
	 * @param parEvt l'Evenement à comparer.
	 * @return un int, 0 si this et la Date de l'evenement sont egales, -1 si this precede l'evenement, 1 si l'evenement precede this.
	 */
	public int compareTo(Evenement parEvt){
	
		int compareDate = this.chDate.compareTo(parEvt.chDate);
		if(compareDate != 0)
			return compareDate;
		return(this.chNom + this.chLieu).compareTo(parEvt.chNom + parEvt.chLieu);		
	
	}



}//fin evenement
