import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelDiapo extends JPanel implements ActionListener {
	
	private JPanel panelCentre = new JPanel();
	private JPanel panelSud = new JPanel ();
	private JPanel panelEtu = new JPanel ();
	private JPanel panelBib = new JPanel ();
	

	PanelEtu panelEtudiant;
	PanelBibli panelBibliothecaire;
	
	/*********** Pour la barre de MENU ******************/
	CardLayout gestionnaireCartes;
	
	
		

	final int NB_BOUTONS= 2;
	
	// etiquette nom interface
	JLabel etiquette2;
	
	// Prï¿½paration des tableaux nï¿½cessaires ï¿½ lï¿½interface
	JButton [] boutons = new JButton [NB_BOUTONS];
		
	// Les intitulï¿½s portï¿½s par les boutons
	String [] intitulesDesBoutons={"Bibliothécaire","Etudiant"};
	
	CardLayout gestionnaireDeCartes = new CardLayout (5,5);
	
	public PanelDiapo () {
		
		this.setLayout (new BorderLayout (20,20)) ;
		panelSud.setOpaque (true); 
		add (panelSud, BorderLayout.SOUTH);
		
		
		// CardLayout
		panelCentre.setLayout(gestionnaireDeCartes);
		panelCentre.setOpaque (true); 
		add (panelCentre, BorderLayout.CENTER);
		
		// Boutons
		boutons[0] = new JButton(intitulesDesBoutons[0]);
		boutons[1] = new JButton(intitulesDesBoutons[1]);
		boutons[0].addActionListener (this);
		boutons[1].addActionListener (this);
		panelSud.add(boutons[0],intitulesDesBoutons[0]);
		panelSud.add(boutons[1],intitulesDesBoutons[1]);
		
		// Les 2 panels dans panelCentre
		panelEtudiant = new PanelEtu();
		panelCentre.add(panelEtudiant, "panelEtudiant");
		
		panelBibliothecaire = new PanelBibli();
		panelCentre.add(panelBibliothecaire, "panelBibliothecaire");
		
		

		
	}

	
	@Override
	public void actionPerformed(ActionEvent parEvt) {
		if (parEvt.getSource()==boutons [1]) {
			gestionnaireDeCartes.first (panelCentre);
		}
		
		if (parEvt.getSource()==boutons [0]) {
			gestionnaireDeCartes.next (panelCentre);
		}
        String actionCommand = parEvt.getActionCommand();
        
        if (actionCommand.equals("Fermer")) {
        	int saisi = JOptionPane.showConfirmDialog (
        			this,
        			"Voulez-vous vraiment quitter ?",
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