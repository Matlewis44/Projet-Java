package modele;

import java.util.Collection;
import javax.swing.table.DefaultTableModel;

/**
 * ModeleTable est la classe définit comment est construit la table(nombre de colonne, ligne etc...).
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class ModeleTable extends DefaultTableModel{
	
	/**
	 * Le constructeur ModeleTable permet d'ajouter une valeur dans la JTable 
	 * ainsi que de donner un nom aux colonnes, le nombre de colonnes et de lignes de cette JTable.
	 * @param parAuj une Date 
	 * @param parAgenda l'Agenda
	 */
	public ModeleTable(Date parAuj, Agenda parAgenda){
		this.setColumnCount(7);
		this.setRowCount(16);
		
		/*
		  Mettre les jours de la semaine dans les colonnes de la JTable
		 */
		String semaine[]= new String [7];
		Date jour = parAuj.date1erJourSem();
		for(int i = 0; i < semaine.length; i++){
			semaine[i]=jour.toStringSem();
			jour = jour.dateDuLendemain();
		}
		setColumnIdentifiers(semaine);
		
		Collection<Evenement> evtSemaine = parAgenda.getEvenementsSemaine(parAuj);
		System.out.println("parAgenda = "+parAgenda);
		System.out.println("evtSemaine = "+evtSemaine);
		
		if(evtSemaine!=null) {
			for(Evenement evt : parAgenda.getEvenementsSemaine(parAuj)){
				ajoutEvenement(evt);
			}
		}
		
		
		
	}
	/**
	 * La méthode ajoutEvenement ajoute un évenement a la table.
	 * @param evt un evenement a ajouter a la table
	 */
	public void ajoutEvenement(Evenement evt){
		int indiceColonne = evt.getDate().getJourSemaine();
		int indiceLigne = 0;
		if(indiceColonne-2 == -1)
			indiceColonne=(indiceColonne-2)+7;
		else
			indiceColonne=indiceColonne-2;
		while(indiceLigne < getRowCount() && getValueAt(indiceLigne,indiceColonne) != null){
			indiceLigne++;
		}
		
		setValueAt(evt,indiceLigne,indiceColonne);
	}
	/**
	 * La methode getColumnClass permet de récuperer le type d'une colonne.
	 * @param indiceColonne un entier qui est l'indice de la colonne.
	 * @return Class qui est la Class de la colonne. 
	 */
	public Class getColumnClass (int indiceColonne) {
		return Evenement.class;
	}
	
	
	
	
}
