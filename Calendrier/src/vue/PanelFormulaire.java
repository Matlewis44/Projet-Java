package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import modele.Agenda;
import modele.Date;
import modele.Evenement;

/**
 * PanelFormulaire est la classe repr�sentant le formulaire du logiciel Agenda.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class PanelFormulaire extends JPanel{
	
	/**
	 * Le bouton Ajout pour ajouter un �v�nement � l'agenda.
	 */
	private JButton bAdd= new JButton("+");
	
	/**
	 * La date qui repr�sente la Date du jour courant dans un premier temps, et ensuite la date du bouton selectionn�.
	 */
	private Date dateAuj;
	
	/**
	 * Pour selectionner les heures du d�but de l'�venement.
	 */
	private JComboBox heure;
	
	/**
	 * Pour selectionner les heures de la fin de l'�venement.
	 */
	private JComboBox heure2;
	
	/**
	 * Pour selectionner les minutes du d�but de l'�venement.
	 */
	private JComboBox minute;
	
	/**
	 * Pour selectionner les minutes de la fin de l'�venement.
	 */
	private JComboBox minute2;
	
	/**
	 * Tableau contenant les chaines de caractere de toute les heures.
	 */
	private String [] h = new String[25];
	
	/**
	 * Tableau contenant les chaines de caractere de toute les minutes.
	 */
	private String [] m = new String[61];

	/**
	 * Emplacement pour pouvoir �crire le titre de l'�v�nement.
	 */
	private  JTextField zoneTxt = new JTextField(13);
	/**
	 * Emplacement pour pouvoir �crire le lieu de l'�v�nement.
	 */
	private JTextField zoneTxt2 = new JTextField(13);
	/**
	 * Emplacement pour pouvoir �crire la description de l'�v�nement.
	 */
	private JTextArea zoneTxtDescription = new JTextArea(10,13);
	
	/**
	 * La date du jour courant.
	 */
	private GregorianCalendar date = new GregorianCalendar();
	private int jour = date.get(date.DAY_OF_WEEK);
	private int numJour = date.get(date.DAY_OF_MONTH);
	private int mois = date.get(date.MONTH)+1;
	private int annee = date.get(date.YEAR);
	
	/**
	 * Label Indiquant la date du jour courant, selectionn�.
	 */
	private JLabel auj;
	
	
	/**
	 * La constructeur PanelFormulaire permet de creer le formulaire.
	 */
	public PanelFormulaire(){
		setLayout(new GridBagLayout());
		
		/**
		 * Tableau contenant les jours de la semaine dans l'ordre.
		 */
		String[] JOUR_SEMAINE={"", "Dimanche", "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
		
		/**
		 * Tableau contenant les noms des mois dans l'ordre.
		 */
		String[] MOIS={"", "Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
		
		dateAuj = new Date(numJour, mois, annee);
		auj = new JLabel(JOUR_SEMAINE[jour]+" "+numJour+" "+MOIS[mois]+" "+annee);
		/**
		 * Label Indiquant le champ titre
		 */
		JLabel titre = new JLabel("Titre");
		/**
		 * Label Indiquant le champ lieu
		 */
		JLabel lieu = new JLabel("Lieu");
		/**
		 * Label Indiquant le champ description
		 */
		JLabel description = new JLabel("Description");
		
		/**
		 * Le mnemonique du titre
		 */
		titre.setDisplayedMnemonic('T');
		/**
		 * Le mnemonique du lieu
		 */
		lieu.setDisplayedMnemonic('L');
		/**
		 * Le mnemonique de la description
		 */
		description.setDisplayedMnemonic('E');
		
		titre.setLabelFor(zoneTxt);
		lieu.setLabelFor(zoneTxt2);
		description.setLabelFor(zoneTxtDescription);
		
		JLabel debut = new JLabel("D�but");
		JLabel separation = new JLabel(":");
		JLabel separation2 = new JLabel(":");
		JLabel fin = new JLabel("Fin");
		
		int compteur = 0;
		/**
		 * Boucle pour cr�er toute les heures
		 */
		for(int i = 0; i <= 2 ; i++){
			for(int j = 0; j <= 9 ; j++){
				if(i == 2 && j == 4)
					break;
				compteur++;
				h[compteur] = Integer.toString(i) + Integer.toString(j);
			}
		}
		
		int compteur2 = 0;
		
		/**
		 * Boucle pour cr�er toute les minutes
		 */
		for(int i = 0; i <= 5 ; i++){
			for(int j = 0; j <= 9 ; j++){
				compteur2++;
				m[compteur2] = Integer.toString(i) + Integer.toString(j);
			}
		}
		
		
		heure = new JComboBox(h);
		heure2 = new JComboBox(h);
		minute = new JComboBox(m);
		minute2 = new JComboBox(m);
		
		/**
		 * Le mnemonique de la combo box debut des heures
		 */
		debut.setDisplayedMnemonic('D');
		debut.setLabelFor(heure);
		
		/**
		 * Le mnemonique de la combo box debut des minutes
		 */
		separation.setDisplayedMnemonic(';');
		separation.setLabelFor(minute);
		
		/**
		 * Le mnemonique de la combo box fin des minutes
		 */
		separation2.setDisplayedMnemonic(':');
		separation2.setLabelFor(minute2);
		
		/**
		 * Le mnemonique de la combo box fin des heures
		 */
		fin.setDisplayedMnemonic('F');
		fin.setLabelFor(heure2);
		
		
		StringTokenizer heureMin= new StringTokenizer(date.getTime().toString());
		String horloge = new String();
		String horloge2 = new String();
		for(int i = 0; i<=3;i++)
			horloge = heureMin.nextToken().toString();
		
		StringTokenizer heureMin2= new StringTokenizer(horloge,":");
		
		horloge2 = heureMin2.nextToken().toString();
		heure.setSelectedIndex(Integer.parseInt(horloge2)+1);
		
		horloge2 = heureMin2.nextToken().toString();
		minute.setSelectedIndex(Integer.parseInt(horloge2)+1);
		
		/**
		 * Contrainte pour placer a un endroit pr�cis les diff�rents labels, et JTextFields.
		 */
		GridBagConstraints contrainte = new GridBagConstraints();
		
		contrainte.gridx = 0;contrainte.gridy = 0;contrainte.gridwidth = 2;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(auj, contrainte);
	
		contrainte.gridx = 3;contrainte.gridy = 0;contrainte.gridwidth = 1;
		contrainte.insets = new Insets(10,60,10,10);
		add(bAdd, contrainte);
		//bAdd.addActionListener(this);
		bAdd.setActionCommand("+");
		
		contrainte.gridx = 0;contrainte.gridy = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(titre, contrainte);
		
		contrainte.gridx = 1;contrainte.gridy = 1;contrainte.gridwidth = 3;
		contrainte.fill = GridBagConstraints.HORIZONTAL;contrainte.insets = new Insets(10,10,10,10);
		add(zoneTxt, contrainte);
		
		contrainte.gridx = 0;contrainte.gridy = 2;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(lieu, contrainte);
		
		contrainte.gridx = 0;contrainte.gridy = 3;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(debut, contrainte);
		
		contrainte.gridx = 2;contrainte.gridy = 3;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(separation, contrainte);
		
		contrainte.gridx = 2;contrainte.gridy = 4;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(separation2, contrainte);
		
		contrainte.gridx = 1;contrainte.gridy = 3;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(heure, contrainte);
		
		contrainte.gridx = 3;contrainte.gridy = 3;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,60);
		add(minute, contrainte);
		
		contrainte.gridx = 1;contrainte.gridy = 4;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(heure2, contrainte);
		
		contrainte.gridx = 3;contrainte.gridy = 4;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,60);
		add(minute2, contrainte);
		
		contrainte.gridx = 0;contrainte.gridy = 4;contrainte.gridwidth = 1;
		contrainte.fill = GridBagConstraints.BOTH;contrainte.insets = new Insets(10,10,10,10);
		add(fin, contrainte);
		
		contrainte.gridx = 1;contrainte.gridy = 2;contrainte.gridwidth = 3;
		contrainte.fill = GridBagConstraints.HORIZONTAL;contrainte.insets = new Insets(10,10,10,10);
		add(zoneTxt2, contrainte);
		
		contrainte.gridx = 0;contrainte.gridy = 5;contrainte.gridwidth = 1;
		contrainte.insets = new Insets(10,10,10,10);
		contrainte.anchor = GridBagConstraints.NORTHWEST;
		add(description, contrainte);
		
		contrainte.gridx = 1;contrainte.gridy = 5;contrainte.gridwidth = 3;
		contrainte.fill = GridBagConstraints.HORIZONTAL;contrainte.insets = new Insets(10,10,10,10);
		zoneTxtDescription.setLineWrap(true);
		add(zoneTxtDescription, contrainte);
		
		
	}
	
	/**
	 * La m�thode reset, efface toute �criture mise dans les JTextField et JTextArea et met le focus sur le titre apres avoir ajouter un �v�nement.
	 */
	public void reset(){
		zoneTxt.setText("");
		zoneTxt2.setText("");
		zoneTxtDescription.setText("");
		StringTokenizer heureMin= new StringTokenizer(date.getTime().toString());
		String horloge = new String();
		String horloge2 = new String();
		for(int i = 0; i<=3;i++)
			horloge = heureMin.nextToken().toString();
		
		StringTokenizer heureMin2= new StringTokenizer(horloge,":");
		
		horloge2 = heureMin2.nextToken().toString();
		heure.setSelectedIndex(Integer.parseInt(horloge2)+1);
		
		horloge2 = heureMin2.nextToken().toString();
		minute.setSelectedIndex(Integer.parseInt(horloge2)+1);
		
		horloge2 = heureMin2.nextToken().toString();
		heure2.setSelectedIndex(0);
		minute2.setSelectedIndex(0);
		zoneTxt.requestFocus();
	}
	/**
	 * La m�thode getZoneTxt retourne le JTextfield du titre. Peut Etre utile pour r�cup�rer ce qui est �crit dedans gr�ce getText().
	 * @return JTextField
	 */
	public JTextField getZoneTxt(){
		return zoneTxt;
	}
	
	/**
	 * La m�thode getZoneTxt2 retourne le JTextfield du lieu. Peut Etre utile pour r�cup�rer ce qui est �crit dedans gr�ce getText().
	 * @return JTextField
	 */
	public JTextField getZoneTxt2(){
		return zoneTxt2;
	}
	/**
	 * La m�thode getHDebut retourne la chaine de caractere de l'heure du d�but.
	 * @return String
	 */
	public String getHDebut(){
		return h[heure.getSelectedIndex()];
	}
	
	/**
	 * La m�thode getMDebut retourne la chaine de caractere des minutes du d�but.
	 * @return String
	 */
	public String getMDebut(){
		return m[minute.getSelectedIndex()];
	}
	
	/**
	 * La m�thode getHFin retourne la chaine de caractere de l'heure de la fin.
	 * @return String
	 */
	public String getHFin(){
		return h[heure2.getSelectedIndex()];
	}
	
	/**
	 * La m�thode getMFin retourne la chaine de caractere des minutes de la fin.
	 * @return String
	 */
	public String getMFin(){
		return m[minute2.getSelectedIndex()];
	}
	
	/**
	 * La m�thode getDescrip retourne la chaine de caractere contenu dans le JTextArea.
	 * @return String
	 */
	public String getDescrip(){
		return zoneTxtDescription.getText();
	}
	
	/**
	 * La m�thode getBAdd retourne le bouton ajout.
	 * @return JButton.
	 */
	public JButton getBAdd(){
		return bAdd;
	}
	
	/**
	 * La m�thode setDate modifie la date afficher (JLabel)par la date pass� en parametre.
	 * @param parAuj la Date � remplacer.
	 */
	public void setAuj(Date parAuj){
		auj.setText(parAuj.toString());				//pour avoir le label date a cote du bouton +
	}
	
	/**
	 * La m�thode getDateAuj retourne la date afficher peut etre la date du jour courant si on n'a pas selectionne d'autre date.
	 * @return Date la date afficher.
	 */
	public Date getDateAuj(){
		return dateAuj;				
	}
	/**
	 * La m�thode enregistreEcouteur repr�sente le controleur qui �coute le formulaire, (s'il y a un �venement � ajouter).
	 * @param controleur le controleur qui �coute.
	 */
	public void enregistreEcouteur(Controleur controleur){
		bAdd.addActionListener(controleur);
	}
	
		
	

		
	
	
	
	
}