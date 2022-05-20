package vue;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controleur.Controleur;
import modele.Agenda;
import modele.LectureEcriture;


/**
 * PanelAgenda est la classe représentant le panel fils de la fenetre mère du logiciel Agenda qui contient les différentes vues.
 * @author 33675
 *
 */
public class PanelAgenda extends JPanel implements ActionListener{
	
	/**
	 * Le formulaire pour creer un évènement.
	 * @see PanelFormulaire
	 */
	public PanelFormulaire formulaire = new PanelFormulaire();
	
	/**
	 * L'Agenda qui contient les évènement creer par le formulaire
	 * @see Agenda
	 */
	private Agenda unAgenda;
	
	/**
	 * La disposition des objets sur la fenetre est de type CardLayout.
	 * @see CardLayout
	 */
	private CardLayout gestionnaireCartes;
	
	/**
	 * La constructeur PanelAgenda permet d'ajouter le formulaire, la Jtable, ainsi que le calendrier au Panel fils. Mais permet aussi d'ecrire une sauvegarde. 
	 */
	public PanelAgenda(){
		/**
		 * Permet de sauvegarder l'agenda ou bien de creer une sauvegarde s'il n'en n'existe pas déjà une.
		 */
		File ficAgenda = new File("AgendaSauvegarde"+ File.separator + "Agenda2020.ser");
		if(ficAgenda.length() == 0){
			System.out.println("le fichier n'existe pas : "+ficAgenda.length());
			unAgenda = new Agenda(1000, ficAgenda);
			LectureEcriture.ecriture(ficAgenda, unAgenda);
		}
		else{
			unAgenda =  (Agenda) LectureEcriture.lecture(ficAgenda);
			System.out.println(unAgenda);
		}
		
		/**
		 *  PanelAffichage creer une JTable avec ses caractéristiques et son fonctionnement.
		 *  @see PanelAffichage
		 */
		PanelAffichage panelAffichage = new PanelAffichage(unAgenda);
		gestionnaireCartes = new CardLayout();
		setLayout(gestionnaireCartes);
		
		/**
		 * Le PanelCalendrier est le calendrier de l'agenda.
		 * @see PanelCalendrier
		 */
		PanelCalendrier calendrier = new PanelCalendrier();
		
		/**
		 * Le controleur synchoronise les modèle et la vue.
		 */
		Controleur controleur = new Controleur(unAgenda, formulaire, calendrier, panelAffichage,calendrier.getPanelMois());
		
		JPanel cal = new JPanel();
		add(cal);
		
		cal.setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridy=5;
		cal.add(calendrier);
		add(formulaire,"formulaire");
		add(panelAffichage);
		
	}
	/**
	 * Ici le actionPerformed s'occupe de définir les actions a effectuer lorsque l'on clique sur les différents items de Menu.
	 * @see FenetreAgenda
	 * @param evt de type ActionEvent.
	 */
	public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        if (actionCommand.equals("Calendrier")) {
        	 gestionnaireCartes.first(this);
        }
        if (actionCommand.equals("Évènement")) {
        	gestionnaireCartes.show(this,"formulaire");
        	formulaire.getZoneTxt().requestFocus();
        }
        if (actionCommand.equals("Semaine")) {
       	 	gestionnaireCartes.last(this);
        }
        if (actionCommand.equals("Fermer")) {
        	int saisi = JOptionPane.showConfirmDialog (
        			this,
        			"Êtes-vous sûr de votre choix ?",
        			"Au revoir !!",
        			JOptionPane.OK_CANCEL_OPTION,
        			JOptionPane.QUESTION_MESSAGE
        			);
        			switch (saisi) {
        				case JOptionPane.CLOSED_OPTION:
        					System.out.println ("Valeur retournée : " +saisi);break;
        				case JOptionPane.OK_OPTION:
        					System.out.println ("Valeur retournée : " +saisi);System.exit(0);break;
        				case JOptionPane.CANCEL_OPTION:
        					System.out.println ("Valeur retournée : " +saisi); break;
        			} 
        	
        }
       
    }        
    
	
}