package modele;
import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Date est la classe représentant une date.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */ 
public class Date implements Comparable <Date>, Serializable {
  /**
  * jour représente le jour
  */
  private int jour;
  /**
   * mois représente le mois
   */
  private int mois;
  /**
   * annee représente l'annee
   */
  private int annee;
  /**
   * jourSemaine représente les jourSemaines.
   */
  private int jourSemaine;
  
  /**
   * Le constructeur Date sans paramètre défini la date du jour courant.
   */
  public Date ()   { 
	  GregorianCalendar dateAuj = new GregorianCalendar ();
	  annee = dateAuj.get (Calendar.YEAR);
	  mois = dateAuj.get (Calendar.MONTH)+1; // janvier = 0, fevrier = 1...
	  jour = dateAuj.get (Calendar.DAY_OF_MONTH);
	  jourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
  }
  /**
   * Le constructeur Date construit une date a partir du jour, du mois et de l'année donné en paramètre.
   * @param parJour un entier pour le jour
   * @param parMois un entier pour le mois
   * @param parAnnee un entier pour l'année
   */
  public Date (int parJour, int parMois, int parAnnee)   {   
	jour = parJour;
	mois = parMois;
	annee = parAnnee; 
	GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
	jourSemaine = date.get (Calendar.DAY_OF_WEEK);				
  }
  
  /**
   * La methode estValide indique par un boolean si la date est valide.
   * @return boolean 
   */
  public boolean estValide(){
		if(annee < 1583)return false;
		if(mois < 1 || mois > 12)return false;
		if(jour < 1 || jour > dernierJourDuMois(mois, annee))return false;
		return true;
	}
  /**
   * La methode compareTo compare 2 Dates.
   * @param parDate une Date a comparer.
   * @return 0 si this et parDate sont egales, -1 si this precede parDate, 1 si parDate precede this.
   */
  public int compareTo (Date parDate) {
    if (annee < parDate.annee)
		return -1;
	if (annee > parDate.annee)
		return 1;
	// les annees sont =
	if (mois < parDate.mois)
		return -1;
	if (mois > parDate.mois)
		return 1;
	// les mois sont =
	if (jour < parDate.jour)
		return -1;
	if (jour > parDate.jour)
		return 1;
	return 0;	
  }
 /**
  * La methode dateDuLendemain retourne la date du lendemain.
  * @return Date
  */
  public Date dateDuLendemain ()   {	
    if (jour < dernierJourDuMois(mois,annee))
		     return  new Date (jour+1,mois,annee);
		else if (mois < 12)
				return new Date (1,mois+1,annee);
			 else return new Date (1,1,annee+1);	
  }  
  /**
   * La methode dateDeLaVeille retourne la date de la veille.
   * @return Date
   */
  public Date dateDeLaVeille () {    
	if (jour > 1)
			return  new Date (jour-1,mois,annee);
	else if (mois > 1)
			   return new Date (Date.dernierJourDuMois(mois-1, annee),mois-1,annee);
		 else return  new Date (31,12,annee-1);
  }	 
  /**
   * La methode dernierJourDuMois retourne le dernier jour d'un mois passé en paramètre, tout en respectant les années bissextiles.
   * @param parMois un entier qui désigne le mois.
   * @param parAnnee un entier qui désigne l'année.
   * @see estBissextile
   * @return un entier, le dernier jour du mois.
   */
  public static int dernierJourDuMois (int parMois, int parAnnee) {
		switch (parMois) {
			 case 2 : if (estBissextile (parAnnee))  return 29 ; else return 28 ;  
			 case 4 : 	 case 6 : 	 case 9 : 	 case 11 : return 30 ;
			 default : return 31 ;
			}  // switch
  } 
  /**
   * La methode estBissextile indique si une année passé en paramètre est bissextiles ou non.
   * @param parAnnee un entier qui représente l'année.
   * @return boolean
   */
  private static boolean estBissextile(int parAnnee) {
	  return parAnnee % 4 == 0 && (parAnnee % 100 != 0 || parAnnee % 400 == 0);
  }
  /**
   * La méthode toString retourne une chaine de caractère contenant la date en toute lettre. ex: Samedi 1 Decembre. 
   * @return une chaine de caractère : la date.
   */
  public String toString () {
    String chaine = new String();
    switch (jourSemaine) {
		 case 1: chaine = "Dimanche"; break;
		 case 2: chaine = "Lundi"; break;
		 case 3: chaine = "Mardi"; break;
		 case 4: chaine = "Mercredi"; break;
		 case 5: chaine = "Jeudi"; break;
		 case 6: chaine = "Vendredi"; break;
		 case 7: chaine = "Samedi"; break;
		}
	chaine += " " + jour + " ";
	switch (mois) {
		 case 1: chaine += "Janvier"; break;
		 case 2: chaine += "Fevrier"; break;
		 case 3: chaine += "Mars"; break;
		 case 4: chaine += "Avril"; break;
		 case 5: chaine += "Mai"; break;
		 case 6: chaine += "Juin"; break;
		 case 7: chaine += "Juillet"; break;
		 case 8: chaine += "Aout"; break;
		 case 9: chaine += "Septembre"; break;
		 case 10: chaine += "Octobre"; break;
		 case 11: chaine += "Novembre"; break;
		 case 12: chaine += "Decembre"; break;
		}	
	return chaine;
  } 
  /**
   * La méthode toStringSem retourne une chaine de caractère contenant le jours de la semaine en abréviation ainsi que le numéro du jour.
   * @see ModeleTable
   * @return une chaine de caractère : la date en abréviation. 
   */
  public String toStringSem () {
	    String chaine = new String();
	    switch (jourSemaine) {
			 case 1: chaine = "Dim"; break;
			 case 2: chaine = "Lun"; break;
			 case 3: chaine = "Mar"; break;
			 case 4: chaine = "Mer"; break;
			 case 5: chaine = "Jeu"; break;
			 case 6: chaine = "Ven"; break;
			 case 7: chaine = "Sam"; break;
			}
		chaine += " " + jour + " ";
		
		return chaine;
	  }  

  /**
   * La methode getAnnee retourne l'année.
   * @return un entier l'année.
   */
  public int getAnnee() { 
	  return annee;
  }
  
  /**
   * La methode getJour retourne le jour.
   * @return un entier le jour.
   */
  public int getJour() { 
	  return jour;
  }
  
  /**
   * La methode getMois retourne le mois.
   * @return un entier le mois.
   */
  public int getMois() { 
	  return mois;
  }
  
  /**
   * La methode getJourSemaine retourne le jour de la semaine.
   * @return un entier le jour de la semaine.
   */
  public int getJourSemaine () {
	  return jourSemaine;
  }
  
  /**
   * La methode isToday indique si la date(this) est la date courante.
   * @return boolean
   */
  public boolean isToday() {
	  return new Date().compareTo(this) == 0;
  }
  
  /**
   * La methode date1erJourSem retourne le 1er jour de la semaine d'une date(this).
   * @return Date = ler jour de la semaine.
   */
  public Date date1erJourSem(){
	  int jSemaine = this.getJourSemaine();
	  Date parDate = this;
	  while(jSemaine !=2){
		  if(jSemaine >= 2)  {
			  jSemaine--; 
			  parDate= parDate.dateDeLaVeille();
		  }
		  else if(jSemaine == 1){
			  for(int i=0; i<6;i++)
				  parDate= parDate.dateDeLaVeille();
			  jSemaine=2;
		  }
		 
	  }
			  
	  return parDate;
  }
  
}  // class Date