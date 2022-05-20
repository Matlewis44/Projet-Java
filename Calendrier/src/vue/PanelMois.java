package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.BoutonDate;
import modele.CalendrierDuMois;
import modele.Date;

/**
 * PanelMois est la classe repr�sentant les boutons des jours des mois du calendrier.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class PanelMois extends JPanel implements ActionListener{
	
	/**
	 * Pour creer la date du jour courant par d�faut s'il on ne selectionne pas de bouton.
	 */
	private GregorianCalendar date = new GregorianCalendar();
	
	/**
	 * le num�ro du jour
	 */
	private int numJour = date.get(date.DAY_OF_MONTH);
	/**
	 * le mois
	 */
	private int mois = date.get(date.MONTH)+1;
	
	/**
	 * l'annee
	 */
	private int annee = date.get(date.YEAR);
	
	/**
	 * boutonSelectionne repr�sente le bouton s�lectionn�
	 */
	private BoutonDate boutonSelectionne= new BoutonDate(new Date(numJour,mois,annee));
	/**
	 * Arraylist contenant tout les boutons de tout les mois.
	 */
	private ArrayList <BoutonDate> listeBoutons;
	
	/**
	 * La constructeur PanelMois permet de creer les boutons des mois, tout en choisissant les couleurs des boutons et de determiner comment les afficher.
	 * @param parNumDuMois un entier, le num�ro du mois.
	 */
	public PanelMois(int parNumDuMois){
		String[] jour_semaine = {"Lun","Mar", "Mer","Jeu","Ven","Sam","Dim"};
		Date today;
		today =new Date();
		Collection <Date> datesDuMois = new CalendrierDuMois(parNumDuMois,today.getAnnee()).getDates();
		
		setLayout(new GridLayout(datesDuMois.size()%7,7,8,8));
		/**
		 * Les label indiquant les jours de la semaine situ� au dessus des boutons.
		 */
		for(int i=0;i<7;i++){
			JLabel labelJour = new JLabel(jour_semaine[i], JLabel.CENTER);
			add(labelJour);
		}
		
		listeBoutons = new ArrayList <BoutonDate>();
		BoutonDate boutonJour;
		Iterator <Date> iterateur = datesDuMois.iterator();
		/**
		 * ajout des boutons au JPanel
		 */
		while(iterateur.hasNext()){
			Date date = iterateur.next();
			boutonJour = new BoutonDate(Integer.toString(date.getJour()));
		
			boutonJour.addActionListener(this);
			listeBoutons.add(boutonJour);
			this.add(boutonJour);
			boutonJour.setDate(date);
			
			/**
			 * choix des couleurs des boutons
			 */
			if(parNumDuMois==date.getMois())
				boutonJour.setBackground(new Color(220,220,220));
			else
				boutonJour.setBackground(new Color(150,150,150));
			if(date.isToday())
				boutonJour.setBackground(new Color(20,100,250));
			//System.out.println(calendrier.getMois(parNumDuMois));
			//System.out.println(test);
		}
	}
	/**
	 * Le actionPerformed va enregistrer les actions a effectuer lors d'un clique que un bouton. Ici changer la couleur du bouton, et changer la date selectionn�.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		// TODO Auto-generated method stub

		if(parEvt.getSource() instanceof BoutonDate){
			//System.out.println("ta appuyer");
			if(boutonSelectionne != parEvt.getSource() && !(boutonSelectionne.getDate().isToday())){
				boutonSelectionne.setBackground(new Color(220,220,220));
			}
			boutonSelectionne = (BoutonDate) parEvt.getSource();
			
			if(!boutonSelectionne.getDate().isToday()){
				boutonSelectionne.setBackground(new Color(12,255,65));
			}
			
			Date dateSelectionne = boutonSelectionne.getDate();
		
			
		}
	}
	/**
	 * La m�thode getBoutonSelectionne retourne le JButton selectionn�
	 * @return JButton le bouton selectionn�
	 */
	public JButton getBoutonSelectionne(){
		return boutonSelectionne;
	}
	
	/**
	 * La m�thode enregistreEcouteur �coute le panelMois si changement il y a.
	 * @param controleur est le controleur qui doit �couter.
	 */
	public void enregistreEcouteur(Controleur controleur) {
		// TODO Auto-generated method stub
		for(BoutonDate bouton : listeBoutons){
			bouton.addActionListener(controleur);
		}
		
	}

	
	
}