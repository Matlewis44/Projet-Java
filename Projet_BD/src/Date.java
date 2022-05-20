import java.util.GregorianCalendar;
import java.util.Calendar;
 
public class Date implements Comparable <Date> {
  private int jour;
  private int mois;
  private int annee;
  private int jourSemaine ;
  
   
  public Date ()   { 
	  GregorianCalendar dateAuj = new GregorianCalendar ();
	  annee = dateAuj.get (Calendar.YEAR);
	  mois = dateAuj.get (Calendar.MONTH)+1; // janvier = 0, fevrier = 1...
	  jour = dateAuj.get (Calendar.DAY_OF_MONTH);
	  jourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
	  if (jourSemaine == 1) {
		  jourSemaine = 6;
	  }
	  else {
		  jourSemaine = jourSemaine-2;
	  }
  }
  
  public Date (int parJour, int parMois, int parAnnee)   {   
	jour = parJour;
	mois = parMois;
	annee = parAnnee; 
	GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
	jourSemaine = date.get (Calendar.DAY_OF_WEEK);				
  }
  
 
   
  /**
   * retourne 0 si this et parDate sont égales, 
   * -1 si this précède parDate,
   *  1 si parDate précède this
   */
  public int compareTo (Date parDate) {
    if (annee < parDate.annee)
		return -1;
	if (annee > parDate.annee)
		return 1;
	// les années sont =
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
 
  public Date dateDuLendemain ()   {	
    if (jour < dernierJourDuMois(mois,annee))
		     return  new Date (jour+1,mois,annee);
		else if (mois < 12)
				return new Date (1,mois+1,annee);
			 else return new Date (1,1,annee+1);	
  }  
  
  public Date dateDeLaVeille () {    
	if (jour > 1)
			return  new Date (jour-1,mois,annee);
	else if (mois > 1)
			   return new Date (Date.dernierJourDuMois(mois-1, annee),mois-1,annee);
		 else return  new Date (31,12,annee-1);
  }	 
  
  public static int dernierJourDuMois (int parMois, int parAnnee) {
		switch (parMois) {
			 case 2 : if (estBissextile (parAnnee))  return 29 ; else return 28 ;  
			 case 4 : 	 case 6 : 	 case 9 : 	 case 11 : return 30 ;
			 default : return 31 ;
			}  // switch
	  } 
	  
  private static boolean estBissextile(int parAnnee) {
			return parAnnee % 4 == 0 && (parAnnee % 100 != 0 || parAnnee % 400 == 0);
	  }
    
  public String toString () {
    String chaine = new String();
    switch (jour) {
	 case 1: chaine += "01"; break;
	 case 2: chaine += "02"; break;
	 case 3: chaine += "03"; break;
	 case 4: chaine += "04"; break;
	 case 5: chaine += "05"; break;
	 case 6: chaine += "06"; break;
	 case 7: chaine += "07"; break;
	 case 8: chaine += "08"; break;
	 case 9: chaine += "09"; break;
	 case 10: chaine += "10"; break;
	 case 11: chaine += "11"; break;
	 case 12: chaine += "12"; break;
	 case 13: chaine += "13"; break;
	 case 14: chaine += "14"; break;
	 case 15: chaine += "15"; break;
	 case 16: chaine += "16"; break;
	 case 17: chaine += "17"; break;
	 case 18: chaine += "18"; break;
	 case 19: chaine += "19"; break;
	 case 20: chaine += "20"; break;
	 case 21: chaine += "21"; break;
	 case 22: chaine += "22"; break;
	 case 23: chaine += "23"; break;
	 case 24: chaine += "24"; break;
	 case 25: chaine += "25"; break;
	 case 26: chaine += "26"; break;
	 case 27: chaine += "27"; break;
	 case 28: chaine += "28"; break;
	 case 29: chaine += "29"; break;
	 case 30: chaine += "30"; break;
	 case 31: chaine += "31"; break;
	 
	 	}
	chaine += "/" ;
	switch (mois) {
		 case 1: chaine += "01"; break;
		 case 2: chaine += "02"; break;
		 case 3: chaine += "03"; break;
		 case 4: chaine += "04"; break;
		 case 5: chaine += "05"; break;
		 case 6: chaine += "06"; break;
		 case 7: chaine += "07"; break;
		 case 8: chaine += "08"; break;
		 case 9: chaine += "09"; break;
		 case 10: chaine += "10"; break;
		 case 11: chaine += "11"; break;
		 case 12: chaine += "12"; break;
		}
	chaine += "/"+annee;
	return chaine;
  }  


public int getAnnee() { 
	return annee;
}

public int getJour() { 
	return jour;
}

public int getMois() { 
	return mois;
}

public int getJourSemaine () {
	return jourSemaine;
}

public boolean isToday() {
	return new Date().compareTo(this) == 0;
}
}  // class Date