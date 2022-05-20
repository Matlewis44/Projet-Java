package modele;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import modele.Date;
/**
 * Agenda est la classe qui permet de creer un agenda, � partir d'�venement.
 * 
 * @see Evenement 
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class Agenda implements Serializable {
	
	/**
	 * Le Tableau qui contient les �v�nements
	 */
	private Evenement[] tableauEvenements;
	
	/**
	 * TAILLE_EVENEMENTS est le nombre d'�v�nement possible
	 */
	private final int TAILLE_EVENEMENTS;
	
	/**
	 * nbrEvenementInstancier est le nombre d'�v�nement instancier.
	 */
	private int nbrEvenementInstancier;
	
	/**
	 * hashMapEvenement est la Hashmap qui contient les �v�nements
	 */
	private HashMap <Integer, ArrayList <Evenement>> hashMapEvenement;
	
	/**
	 * fichierRecu est l'agenda sauvegarder.
	 */
	private File fichierRecu;
	
	/**
	 * Le Constructeur Agenda permet de selectionner une date afin d'ajouter un �venement � l'agenda, il instancie �galement la HashMap.
	 * @param parTAILLE_EVENEMENTS un entier qui d�finit la taille de l'agenda.
	 * @param parFichier un File pour r�cup�rer l'agenda sauvegarder.
	 * 
	 */
	public Agenda(int parTAILLE_EVENEMENTS, File parFichier){
		tableauEvenements = new Evenement[parTAILLE_EVENEMENTS];
		TAILLE_EVENEMENTS = parTAILLE_EVENEMENTS;
		fichierRecu = parFichier;
		hashMapEvenement = new  HashMap <>();
	}

	/** 
	 * La m�thode ajout ajoute un �v�nement re�u en param�tre au tableau des Evenements.
	 * @param event de type Evenement d�signe l'�venement � ajouter.
	 * @return boolean , true si le tableau n'est pas plein sinon elle retourne false
	 */

	public boolean ajout(Evenement event){
		if(nbrEvenementInstancier == TAILLE_EVENEMENTS)
			return false;
		tableauEvenements[nbrEvenementInstancier]=event;
		nbrEvenementInstancier++;
		Date date = event.getDate();
		GregorianCalendar calendar = new GregorianCalendar(date.getAnnee(),
												date.getMois()-1, date.getJour());
		int numeroDeSemaine = calendar.get(Calendar.WEEK_OF_YEAR);
		
	 	if (hashMapEvenement.containsKey(numeroDeSemaine)) { 		
	 		hashMapEvenement.get(numeroDeSemaine).add (event);	
			 }
		else {
			 ArrayList <Evenement> liste = new ArrayList <Evenement> ();
			 liste.add (event);
			 hashMapEvenement.put (numeroDeSemaine, liste);		 
		}
	 	LectureEcriture.ecriture(fichierRecu, this);
		return true;
	}
	/**
	 * La methode getEvenement donne la position de l'�venement dans le tableau des �venements. 
	 * @param event l'Evenement souhait�.
	 * @return int , qui repr�sente la position de l'�venement  est sup�rieur � 0, si l'�venement donn� en param�tre est dans le tableau des �venements, -1 sinon. 
	 */
	public int getEvenement(Evenement event){
		for(int i = 0; i < nbrEvenementInstancier; i++){
			if(event.compareTo(tableauEvenements[i])==0)
				return i;
		}
		return -1;
	}
	/**
	 * @return une chaine de caractere indiquant la taille du tableau des �venements, le nombre de d'�venements ainsi que la description de ces �venements.
	 */
	public String toString(){
		String chaine = "Taille : "+ TAILLE_EVENEMENTS + "  Nombre d'evt ds le tab " + nbrEvenementInstancier + "\n";
		for(int i=0; i<nbrEvenementInstancier;i++){
			chaine += tableauEvenements[i]+" "+"\n";
		}
		return chaine;
	}
	
	
	/**
	 * La methode rechercheMinimum cherche l'�venement qui se passe le plus t�t dans le temps.
	 * @param parDebut un entier indiquant le d�but ou l'on doit effectuer la recherche.
	 * @param parFin un entier indiquant la fin ou l'on doit effectuer la recherche.
	 * @return un entier qui est l'indice de l'�venement qui se passe le plus t�t dans le temps.
	 */
	public int rechercheMinimum(int parDebut, int parFin){
		int indiceMin = parDebut;
		for(int i= parDebut ; i < parFin; i++){
			if(tableauEvenements[i].compareTo(tableauEvenements[i+1])==-1)
				indiceMin = getEvenement(tableauEvenements[i]);
		}
		return indiceMin;
	}
	/**
	 * La methode getTaille retourne un entier qui est le nombre total d'evenement que l'on peut mettre dans le tableau.
	 * @return int 
	 */
	public int getTaille(){
		return TAILLE_EVENEMENTS;
	}
	/**
	 * La m�thode triEvenement tri les �venements dans le tableau, elle ne retourne rien.
	 * @see rechercheMinimum
	 */
	public void triEvenement(){
		Evenement temp;
		Evenement minEvent;
		for(int i = 0; i<= nbrEvenementInstancier-1 ; i++){
			minEvent = tableauEvenements[rechercheMinimum(i,nbrEvenementInstancier-1)];
			temp = tableauEvenements[i];
			tableauEvenements[i] = temp;
			tableauEvenements[rechercheMinimum(0,nbrEvenementInstancier-1)] = temp;
		}
	}
	/**
	 * La methode getEvenementsSemaine retourne une Collection d'Evenement qui se passe durant la semaine de la date donner en parametre.
	 * @param date est la date souhait�
	 * @return l'�v�nement qui se passe la semaine souhait�.
	 */
	public Collection <Evenement> getEvenementsSemaine (Date date){
		GregorianCalendar calendar = new GregorianCalendar(date.getAnnee(),
				 date.getMois()-1, date.getJour());
		int numSemaine = calendar.get(Calendar.WEEK_OF_YEAR);
		return hashMapEvenement.get(numSemaine);
	}
	
}