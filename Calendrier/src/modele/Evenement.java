package modele;

import java.io.Serializable;

/**
 * Evenement est la classe repr�sentant les Evenements.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class Evenement implements Comparable <Evenement>, Serializable{
	/**
	 * chDate repr�sente la date 
	 */
	private Date chDate;
	/**
	 * chNom repr�sente le nom 
	 */
	private String chNom;
	
	/**
	 * chLieu repr�sente le lieu 
	 */
	private String chLieu;
	
	/**
	 * hDebut repr�sente l'heure du d�but de l'�v�nement 
	 */
	private String hDebut;
	/**
	 * hFin repr�sente l'heure de la fin de l'�v�nement 
	 */
	private String hFin;
	/**
	 * mDebut repr�sente les minutes du d�but de l'�v�nement 
	 */
	private String mDebut;
	/**
	 * mFin repr�sente les minutes de la fin de l'�v�nement 
	 */
	private String mFin;
	
	/**
	 * description repr�sente la description de l'�v�nement
	 */
	private String description;
	
	/**
	 * Le constructeur Evenement cr�e un evenement � l'aide de donn�e pass� en param�tre.
	 * @param parDate la date de l'�v�nement 
	 * @param parNom le nom de l'�v�nement 
	 * @param parLieu le lieu de l'�v�nement 
	 * @param parhDebut l'heure de d�but de l'�v�nement 
	 * @param parmDebut les minutes de la fin de l'�v�nement 
	 * @param parhFin l'heure de la fin de l'�v�nement 
	 * @param parmFin les minutes de la fin de l'�v�nement 
	 * @param pardescription la description de l'�v�nement  
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
	 * @return chaine de caract�re contenant la description de l'Evenement.
	 */
	public String toString(){
		return chNom+ " se situe a " +chLieu+" le "+chDate+ " de " +hDebut+ " : " + mDebut + ", � " +hFin+ " : " + mFin + " " +description;
	}

	/** 
	 * La methode getDate retourne la date
	 * @return Date, la date de l'evenement.
	 */
	public Date getDate(){
		return chDate;
	}
	
	/** 
	 * La methode setDate remplace la date de l'evenement par la date pass� en parametre.
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
	 * La methode setNom remplace le nom de l'evenement par le nom pass� en parametre.
	 * @param parNom, une chaine de caract�re le nouveau nom. 
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
	 * La methode setLieu remplace le lieu de l'evenement par le lieu pass� en parametre.
	 * @param parLieu, une chaine de caract�re le nouveau lieu.
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
	 * La methode compareTo compare 2 �venements.
	 * @param parEvt l'Evenement � comparer.
	 * @return un int, 0 si this et la Date de l'evenement sont egales, -1 si this precede l'evenement, 1 si l'evenement precede this.
	 */
	public int compareTo(Evenement parEvt){
	
		int compareDate = this.chDate.compareTo(parEvt.chDate);
		if(compareDate != 0)
			return compareDate;
		return(this.chNom + this.chLieu).compareTo(parEvt.chNom + parEvt.chLieu);		
	
	}



}//fin evenement
