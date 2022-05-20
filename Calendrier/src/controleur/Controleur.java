package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Agenda;
import modele.BoutonDate;
import modele.Date;
import modele.Evenement;
import vue.PanelAffichage;
import vue.PanelCalendrier;
import vue.PanelFormulaire;
import vue.PanelMois;
/**
 * Controleur est la classe qui va synchroniser les modèles et les vues.
 * 
 * @see PanelFormulaire
 * @see PanelCalendrier
 * @see PanelAffichage
 * @see PanelMois 
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class Controleur implements ActionListener {
	private Agenda agenda;
	private PanelFormulaire panelFormulaire;
	private PanelCalendrier panelCalendrier;
	private BoutonDate boutonSelectionne;
	private PanelMois panelMois;
	private PanelAffichage panelAffichage;
	
	/**
	 * Le Constructeur Controleur permet de selectionner une date afin d'ajouter un évènement à l'agenda.
	 * @param parAgenda L'agenda, qui recueil les évènements.
	 * @param parPanelFormulaire le formulaire pour récupérer les informations utile à la création d'un évenement(titre,lieu,heures,description)
	 * @param parPanelCalendrier le calendrier 
	 * @param parPanelAffichage la table d'évenement, pour actualiser la semaine selectionné.
	 * @param parPanelMois pour les boutons du calendrier afin de pouvoir choisir la date.
	 * 
	 */
	public Controleur (Agenda parAgenda,PanelFormulaire parPanelFormulaire, PanelCalendrier parPanelCalendrier, PanelAffichage parPanelAffichage, PanelMois parPanelMois) {
		agenda = parAgenda;
		panelFormulaire = parPanelFormulaire;
		panelCalendrier = parPanelCalendrier;
		
		panelAffichage = parPanelAffichage;
		panelMois = parPanelMois;
		
		/*
		 * Le controleur se met a l'écoute du formulaire, du calendrier et du panelMois.
		 */
		panelFormulaire.enregistreEcouteur(this);
		panelCalendrier.enregistreEcouteur(this);
		panelMois.enregistreEcouteur(this);
		
	}
	Date dateSelect;
@Override
	/** 
	 * La méthode actionPerformed permet d'effectuer les changements sur la table, le calendrier ou bien le formulaire.
	 *
	 */
	public void actionPerformed(ActionEvent parEvt) {
		// TODO Auto-generated method stub
		GregorianCalendar calendar;
		
		//Concerne le bouton ajout du panel formulaire
		if(parEvt.getActionCommand().equals("+")){
			 boutonSelectionne = (BoutonDate) panelCalendrier.getPanelCal();
			 System.out.println(boutonSelectionne.getDate());
			 panelFormulaire.setAuj(dateSelect); 
			 agenda.ajout(new Evenement(dateSelect,panelFormulaire.getZoneTxt().getText(),panelFormulaire.getZoneTxt2().getText(), panelFormulaire.getHDebut(), panelFormulaire.getMDebut(), panelFormulaire.getHFin(), panelFormulaire.getMFin(), panelFormulaire.getDescrip()));
			 calendar = new GregorianCalendar(boutonSelectionne.getDate().getAnnee(),boutonSelectionne.getDate().getMois()-1,boutonSelectionne.getDate().getJour());
			 panelAffichage.setSemaine(dateSelect);
			 
			 panelFormulaire.reset();
			 panelFormulaire.getZoneTxt().requestFocus();
			JOptionPane.showMessageDialog((JButton)parEvt.getSource(), "Vous avez ajouter un évenement à l'Agenda !");
			
		}
		else if (parEvt.getSource() instanceof BoutonDate){
			dateSelect = ((BoutonDate) parEvt.getSource()).getDate();
			//System.out.println(date.toString());
			panelFormulaire.setAuj(dateSelect);
			panelAffichage.setSemaine(dateSelect);
	
			
		}
			
	}
}	

