package vue;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * FenetreAgenda est la classe représentant la fenêtre mère du logiciel Agenda.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class FenetreAgenda extends JFrame {
	/**
	 * La constructeur FenetreAgenda permet de creer la fenetre mere, d'y ajouter les différentes vues, ainsi que le menu avec les mnémoniques correspondant.
	 * @param parTitre une chaine de caractère pour donner un nom à la fenetre.
	 * 
	 */
	public FenetreAgenda(String parTitre){
		super(parTitre);
		/**
		 * Le contentPane
		 */
		PanelAgenda contentPane = new PanelAgenda();
		
		JMenuBar menuBar = new JMenuBar ();
		String itemMenu[] = {"Calendrier","Évènement","Semaine","Fermer"};
		char mnemos[]={'C','V','S','R'};
		char raccourci[]={'E','N','W','Q'};
		
		/**
		 * La configuration du menu
		 */
		for(int i = 0; i< itemMenu.length;i++){
			JMenuItem item = new JMenuItem(itemMenu[i], mnemos[i]);
			item.addActionListener(contentPane);
			item.setActionCommand(itemMenu[i]);
			menuBar.add(item);
			item.setAccelerator (KeyStroke.getKeyStroke (raccourci[i], java.awt.Event.CTRL_MASK));
		}

		setJMenuBar(menuBar);
		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(570,500);setVisible(true);contentPane.formulaire.getZoneTxt().requestFocus();setLocation(250,100);
		setResizable(false);
		setBackground(new Color(201,12,1));
	}
	
	public static void main(String[] args){
		new FenetreAgenda("Agenda Personnel");
	}
	
}