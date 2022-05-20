package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Date;

public class PanelCalendrier extends JPanel implements ActionListener{
	
	//************************* NAVIGATION *************************//
	/**
	 * Tableau contenant les chaines de caract�re necessaire � la navigation du calendrier.
	 */
	private String[] nomBouton= {"<<","<",">",">>"};
	
	/**
	 * Tableau contenant les diff�rents boutons necessaire � la navigation du calendrier. 
	 */
	private JButton[] tabBouton = new JButton[nomBouton.length];
	
	/**
	 * Pour le calendrier nous privil�gions le gestionnaire de tyoe CardLayout pour afficher un seul mois � la fois.
	 */
	private CardLayout gestionaireCarte = new CardLayout();
	
	/**
	 * Tableau contenant les noms des mois dans l'ordre.
	 */
	private String[] MOIS={"", "Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
	
	/**
	 * Le PanelMois cr�er les diff�rents boutons des diff�rents jours du mois et g�re la couleur de ses boutons.
	 * @see PanelMois
	 */
	private PanelMois panelCal;
	
	/**
	 * Le panelCentre est le panel qui contient les boutons du calendrier ainsi que les boutons de navigations.
	 */
	private JPanel panelCentre = new JPanel();
	
	/**
	 * Date indiquant le mois Courant
	 */
	private Date moisCourant = new Date();
	
	/**
	 * Le Label correspondant au mois courant.
	 */
	private JLabel labelSud = new JLabel(MOIS[moisCourant.getMois()], JLabel.CENTER);
	
	/**
	 * Le panelSud contient le labelSud ainsi que les boutons de navigations.
	 */
	private JPanel panelSud;
	
	/**
	 * Tableau contenant les diff�rents PanelsMois.
	 * @see PanelMois
	 */
	private PanelMois[] panelsMois= new PanelMois[13];
	
	/**
	 * La variable qui permet de se d�placer dans les mois.
	 */
	private int deplacement = moisCourant.getMois();
	
	/**
	 * La constructeur PanelCalendrier cr�e le calendrier avec les diff�rents boutons des jours des mois. Ainsi que les boutons pour pouvoir naviguer � travers les mois.
	 */
	public PanelCalendrier() {
		
		setLayout(new BorderLayout());
	//************************* bouton naviguer *************************//		
		panelSud = new JPanel();
		
		add(panelSud, BorderLayout.SOUTH);
		panelSud.setLayout(new GridBagLayout());
		
		/**
		 * Aligner les boutons de navigations ainsi que le label du mois
		 */
		GridBagConstraints contrainte = new GridBagConstraints();
		
		for(int i=0; i< tabBouton.length;i++) {
			tabBouton[i]= new JButton(nomBouton[i]);
			tabBouton[i].addActionListener(this);
			contrainte.gridx=i;contrainte.insets = new Insets(55,0,10,0);
			panelSud.add(tabBouton[i], contrainte);
		}
		contrainte.gridx=4;contrainte.insets = new Insets(55,6,10,0);
		panelSud.add(labelSud, contrainte);
		
		
	//************************* calendrier *************************//
		
		JPanel panelNord = new JPanel();
		add(panelNord, BorderLayout.NORTH);
		panelNord.setLayout(new GridBagLayout());
		
		add(panelCentre);
		panelCentre.setLayout(gestionaireCarte);
		/**
		 * Creer les diff�rents PanelMois � l'aide d'une boucle.
		 */
		for(int i=0; i < 13;i++){
			panelsMois[i]=new PanelMois(i);
		}
		
		panelCal = panelsMois[moisCourant.getMois()];
		panelCentre.add(panelCal);
		
		gestionaireCarte.show(panelCentre, MOIS[moisCourant.getMois()]);
		
	}
	
	
	/**
	 * Le actionPerformed g�re les actions a effectu� lorsque l'on clique sur les boutons de navigations 
	 */
	public void actionPerformed(ActionEvent parEvt) {
		
//***************************** Bouton premier **************************************//	
		/**
		 * Concerne le Bouton pour revenir au premier mois.
		 */
		if(parEvt.getSource()== tabBouton[0]){
			gestionaireCarte.first(panelCentre);
			deplacement = 1;
			labelSud.setText(MOIS[deplacement]);
			panelCentre.remove(panelCal);
			panelCal =  panelsMois[1];
			panelCentre.add(panelCal);
			
		}
		
//***************************** Bouton precedent **************************************//		
		/**
		 * Concerne le Bouton pour revenir au mois pr�c�dent.
		 */
		if(parEvt.getSource()== tabBouton[1]){
			gestionaireCarte.previous(panelCentre);
			deplacement-=1;
			if(deplacement<1){
				deplacement = MOIS.length-1;
				gestionaireCarte.show(panelCentre, MOIS[deplacement]);
			}
				
			if(deplacement == 0){
				deplacement = 1;
				gestionaireCarte.show(panelCentre, MOIS[deplacement]);
			}
			panelCentre.remove(panelCal);
			panelCal =  panelsMois[deplacement];
			panelCentre.add(panelCal);
			labelSud.setText(MOIS[deplacement]);
		}
		
//***************************** Bouton suivant **************************************//	
		
		/**
		 * Concerne le Bouton pour revenir au mois suivant.
		 */
		if(parEvt.getSource()== tabBouton[2]){
			gestionaireCarte.next(panelCentre);
			deplacement+=1;
			if(deplacement>MOIS.length-1 || deplacement == 0){
				deplacement = 1;
				gestionaireCarte.show(panelCentre, MOIS[deplacement]);
			}
			panelCentre.remove(panelCal);
			panelCal =  panelsMois[deplacement];
			panelCentre.add(panelCal);
			
			labelSud.setText(MOIS[deplacement]);
		}
		
		
//***************************** Bouton dernier  **************************************//			
		/**
		 * Concerne le Bouton pour revenir au dernier mois.
		 */
		if(parEvt.getSource()== tabBouton[3]){
			gestionaireCarte.last(panelCentre);
			deplacement = MOIS.length-1;
			labelSud.setText(MOIS[deplacement]);
			panelCentre.remove(panelCal);
			panelCal =  panelsMois[deplacement];
			panelCentre.add(panelCal);
		}
		
	}
	
	/**
	 * La m�thode enregistreEcouteur �coute le panelCalendrier si changement il y a.
	 * @param controleur est le controleur qui doit �couter.
	 */
	public void enregistreEcouteur(Controleur controleur){
		for(JButton bouton : tabBouton){
			bouton.addActionListener(controleur);
		}
		for(PanelMois panel : panelsMois){
			panel.enregistreEcouteur(controleur);
		}
	}
	
	/**
	 * La m�thode getPanelCal permet de r�cup�rer le bouton selectionn� du PanelMois.
	 * @return JButton, le bouton selectionn�.
	 */
	public JButton getPanelCal(){
		return panelCal.getBoutonSelectionne();
	}
	
	/**
	 * La m�thode getPanelMois retourne le PanelMois relatif au d�placement effectuer gr�ce aux bouton de navigation.
	 * @return panelMois 
	 */
	public PanelMois getPanelMois(){
		return panelsMois[deplacement];
	}
	
		
}
	
