package modele;
import java.util.*;

import modele.Date;
import modele.Evenement;
/**
 * AgendaCollection est la classe représentant l'agenda sous forme de collection : ArrayList, TreeSet, HashMap.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class AgendaCollection {
	/**
	 * listeEvenement est une ArrayList qui contient les évènements
	 */
	private ArrayList <Evenement> listeEvenement;
	/**
	 * listeEvenement est un TreeSet qui contient les évènements trié.
	 */
	private TreeSet <Evenement> arbreEvenement;
	
	/**
	 * hashMapEvenement est un HashMap qui contient les évènements trié.
	 */
	private HashMap <Integer, TreeSet <Evenement>> hashMapEvenement;
	
	/**
	 * Le constructeur AgendaCollection se charge d'instancier une ArrayList, un TreeSet, et une HashMap.
	 * Elle ne prend pas de parametre.
	 * 
	 */
	public AgendaCollection() {
		listeEvenement = new ArrayList <Evenement> ();
		arbreEvenement = new TreeSet <Evenement> ();
		hashMapEvenement = new HashMap <Integer,TreeSet<Evenement>>(20);
	}
	
	/**
	 * La methode "ajout", ajoute l'évènement passé en paramètre dans une ArrayList, dans un TreeSet, et dans une HashMap.
	 * @param parEvt est l'évènement que l'on veut ajouter.
	 */
	public void ajout (Evenement parEvt) {
		listeEvenement.add(parEvt);
		arbreEvenement.add(parEvt);
		Date date = parEvt.getDate();
		GregorianCalendar calendar = new GregorianCalendar(date.getAnnee(),date.getMois()-1,date.getJour());
		int numeroSemaine = calendar.get(Calendar.WEEK_OF_YEAR);
			if(hashMapEvenement.containsKey(numeroSemaine)){
				hashMapEvenement.get(numeroSemaine).add(parEvt);
			}
			else {
				TreeSet <Evenement> arbreEvt = new TreeSet <Evenement>();
				arbreEvt .add(parEvt);
				hashMapEvenement.put(numeroSemaine, arbreEvt);
			}
	}

	/**
	 * methode toString() 
	 * @return une chaine de caractère, qui indique la taille de la HashMap ainsi que les évènements de la Hashmap.
	 */
	public String toString(){
		return hashMapEvenement.size()+". "+hashMapEvenement+"\n";
	}
	
	
	/**
	 * La methode "nbEvenement" indique le nombre de fois qu'il y a la chaine de caratere "exposer" dans un Evenement.
	 * @param parEvt un Evenement
	 * @return int un entier.
	 */
	public int nbEvenement (Evenement parEvt) {	
		int compteur = 0;
		String exposer = "exposer";
		for(int i=0; i < Evenement.getNbEvtInstancier();i++) {
			if(parEvt.getNom().compareTo(exposer)== 0)
				compteur++;
		}
		
		return compteur;
	}
	
	/**
	 * La methode nbEvenementATelleDate indique le nombre d'evenement se passant a une date donnée en paramètre.
	 * @param parDate une Date
	 * @return int un entier, qui est le nombre d'evenement.
	 * 
	 */
	public int nbEvenementATelleDate (Date parDate) {
		
		Iterator <Evenement> iterateur = arbreEvenement.iterator();
		int compteur = 0;
		while(iterateur.hasNext()) {
			Evenement evtCourant = iterateur.next();
			compteur++;
		}
		
		return compteur;
	}
	

	
	
}