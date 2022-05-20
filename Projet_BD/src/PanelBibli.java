
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



/**
 * Interface pour le bibliothï¿½caire
 * @author aniss
 *
 */
public class PanelBibli extends JPanel implements ActionListener {
	private JPanel panelNord = new JPanel (); // indique l'interface sur lequel on se trouve
	private JPanel panelBibWest = new JPanel (); // Contient la partie recherche de livre
	private JPanel panelBibEast = new JPanel (); // Contient la partie gestion des etudiants
	private JPanel panelSouthEast = new JPanel (); // Contient le panelBibEast
	private JPanel panelNorthWest = new JPanel (); // Contient le panelBibWest
	JPanel panelEast = new JPanel(); // Contient les tables
	private JLabel labelBiblio = new JLabel("Bibliothéaire", JLabel.LEFT); // label qui indique la fenetre sur lequel on se trouve
	
	// Les champs textfield du panelBibEast
	JTextField champIdEtu = new JTextField (10);
	JTextField champNomEtu = new JTextField (10);
	JTextField champPrenomEtu = new JTextField (10);
	JTextField champAdresseMail = new JTextField(10);
	JTextField champMdp = new JTextField(10);

	
	// Les champs textfield du panelBibWest
	static JTextField champTitre = new JTextField (10);
	static JTextField champAuteur = new JTextField (10);
	
	JButton boutonTableLivre = new JButton("Afficher la table des livres");
	JButton boutonTableEtudiant = new JButton("Afficher la table des étudiants");
	
	JButton boutonCreer = new JButton("Crér");
	JButton boutonSupprimer = new JButton("Supprimer");
	JButton boutonModifier = new JButton("Modifier");
	
	/********* Les diffï¿½rents boutons du panelBibWest *******/
	JButton boutonPreter = new JButton("Prêter");
	JButton boutonRecup = new JButton("Récuperer");
	JButton boutonAjouter = new JButton("Ajouter");
	
	// Pour la table avec livre
	int nbLignes = 100;
	JTable tableLivre;
	String[] columnNames = {"Livre", "Auteur", "Etat", "Par qui ?"};
	
	// Pour la table avec livre
	JTable tableEtu;
	String[] columnNames2 = {"Nom", "Prenom", "Numéro étudiant", "mail", "nbr livres réservés"};

	// Gestionnaire de cartes pour les tables
	CardLayout gestionnaireDeCartes = new CardLayout (5,5);
	
	// Prï¿½paration des tableaux nï¿½cessaires ï¿½ lï¿½interface
	JButton [] boutons = {boutonTableLivre, boutonTableEtudiant} ;
	private Statement stmt;
	private Connection conn;

	
	
		/**
		 * Le constructeur sans paramï¿½tre
		 */
		public PanelBibli() {
			
			this.setLayout (new BorderLayout (20,20)) ;
			panelNord.add(labelBiblio);
			panelNord.setOpaque (true); 
			panelBibEast.setOpaque (true); 
			panelBibWest.setOpaque (true); 
			this.setOpaque (true); 
	
			this.add (panelNord, BorderLayout.NORTH);
			this.add (panelSouthEast, BorderLayout.SOUTH);
			this.add (panelNorthWest, BorderLayout.WEST);
	
	
			/**** ORGANISATION PANELBIBEAST *********/
			panelSouthEast.setLayout(new BorderLayout(20,20)); // ainsi on pourra placer cette partie au SOUTH WEST
			
			// Le gestionnaire de rï¿½partition
			panelBibEast.setLayout (new GridBagLayout ());	
			GridBagConstraints contraintes = new GridBagConstraints ();
			contraintes.insets = new Insets (6,6,6,6); 
			contraintes.anchor = GridBagConstraints.WEST;
			
			// Les labels pour les champs + interface sur laquelle on se trouve
			JLabel id_etudiant = new JLabel("id_etudiant", JLabel.LEFT);
			JLabel nom_etudiant = new JLabel("nom_etudiant", JLabel.LEFT);
			JLabel prenom_etudiant = new JLabel("prenom_etudiant", JLabel.LEFT);
			JLabel adr_etudiant = new JLabel("adresse_mail", JLabel.LEFT);
			JLabel mdp_etudiant = new JLabel("mot de passe", JLabel.LEFT);
	
			
			
			// AJOUT LABEL ID_ET ET LE CHAMP CORRESPONDANT
			contraintes.gridy=0; contraintes.gridwidth = 1;	contraintes.gridx=0;
			panelBibEast.add(id_etudiant, contraintes);
			contraintes.gridx++; contraintes.gridwidth =4; 
			contraintes.fill = GridBagConstraints.HORIZONTAL;		
			panelBibEast.add(champIdEtu, contraintes);
			contraintes.fill = GridBagConstraints.NONE;
	
			
			// AJOUT LABEL NOM_ET ET LE CHAMP CORRESPONDANT
			contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
			panelBibEast.add(nom_etudiant, contraintes);
			contraintes.gridx++; contraintes.gridwidth =4; 
			contraintes.fill = GridBagConstraints.HORIZONTAL;		
			panelBibEast.add(champNomEtu, contraintes);
			contraintes.fill = GridBagConstraints.NONE;
	
			
			// AJOUT LABEL PRENOM_ET ET LE CHAMP CORRESPONDANT
			contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
			panelBibEast.add(prenom_etudiant, contraintes);
			contraintes.gridx++; contraintes.gridwidth =4; 
			contraintes.fill = GridBagConstraints.HORIZONTAL;		
			panelBibEast.add(champPrenomEtu, contraintes);
			contraintes.fill = GridBagConstraints.NONE;
			
			// AJOUT LABEL adr_etudiant ET LE CHAMP CORRESPONDANT
			contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
			panelBibEast.add(adr_etudiant, contraintes);
			contraintes.gridx++; contraintes.gridwidth =4; 
			contraintes.fill = GridBagConstraints.HORIZONTAL;		
			panelBibEast.add(champAdresseMail, contraintes);
			contraintes.fill = GridBagConstraints.NONE;
			
			// AJOUT LABEL mdp_etudiant ET LE CHAMP CORRESPONDANT
			contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
			panelBibEast.add(mdp_etudiant, contraintes);
			contraintes.gridx++; contraintes.gridwidth =4; 
			contraintes.fill = GridBagConstraints.HORIZONTAL;		
			panelBibEast.add(champMdp, contraintes);
			contraintes.fill = GridBagConstraints.NONE;
	
			
			// AJOUT SUR PANEL EAST DES BOUTONS
			contraintes.gridx=0; contraintes.gridwidth =1; contraintes.gridy++; 
			panelBibEast.add(boutonCreer, contraintes );
			contraintes.gridx++; contraintes.gridwidth =1; contraintes.gridy++;
			contraintes.fill = GridBagConstraints.HORIZONTAL;		
			panelBibEast.add(boutonSupprimer, contraintes);
			contraintes.fill = GridBagConstraints.NONE;
			contraintes.gridx++; contraintes.gridwidth =1;  contraintes.gridy--;
			panelBibEast.add(boutonModifier, contraintes);
			
			// Ajout du panel au panel "mere" (en l'occurence panelSouthEast)
			panelSouthEast.add(panelBibEast, BorderLayout.WEST);
			
			/***************************************************************************************/
			
			/***** ORGANISATION DU PANELBIBWEST ****************************************************/
			
			panelNorthWest.setLayout(new BorderLayout(20,20)); // ainsi on pourra placer cette partie au SOUTH WEST
	
			// Le gestionnaire de rï¿½partition
			panelBibWest.setLayout (new GridBagLayout ());	
			GridBagConstraints contraintes2 = new GridBagConstraints ();
			contraintes2.insets = new Insets (6,6,6,6); 
			contraintes2.anchor = GridBagConstraints.WEST;
			
			// Les labels pour les champs
			JLabel labelTitre = new JLabel("titre", JLabel.LEFT);
			JLabel labelAuteur = new JLabel("auteur", JLabel.LEFT);
			
			
			// AJOUT LABEL TITRE ET LE CHAMP CORRESPONDANT
			contraintes2.gridy=0; contraintes2.gridwidth = 1;	contraintes2.gridx=0;
			panelBibWest.add(labelTitre, contraintes2);
			contraintes2.gridx++; contraintes2.gridwidth =4; 
			contraintes2.fill = GridBagConstraints.HORIZONTAL;		
			panelBibWest.add(champTitre, contraintes2);
			contraintes2.fill = GridBagConstraints.NONE;
			
			// AJOUT LABEL AUTEUR ET LE CHAMP CORRESPONDANT
			contraintes2.gridy++; contraintes2.gridwidth = 1;	contraintes2.gridx=0;
			panelBibWest.add(labelAuteur, contraintes2);
			contraintes2.gridx++; contraintes2.gridwidth =4; 
			contraintes2.fill = GridBagConstraints.HORIZONTAL;		
			panelBibWest.add(champAuteur, contraintes2);
			contraintes2.fill = GridBagConstraints.NONE;
	
	
		
			// ACtionListener sur les boutons
			boutonPreter.addActionListener(this);
			boutonRecup.addActionListener(this);
			boutonAjouter.addActionListener(this);
			boutonCreer.addActionListener(this);
			boutonSupprimer.addActionListener(this);
			boutonModifier.addActionListener(this);
			
			// AJOUT SUR PANEL WEST
			contraintes2.gridx=0; contraintes2.gridwidth =1; contraintes2.gridy++; 
			panelBibWest.add(boutonPreter, contraintes2 );
			contraintes2.gridx++; contraintes2.gridwidth =1; contraintes2.gridy++;
			contraintes2.fill = GridBagConstraints.HORIZONTAL;		
			panelBibWest.add(boutonRecup, contraintes2);
			contraintes2.fill = GridBagConstraints.NONE;
			contraintes2.gridx++; contraintes2.gridwidth =1;  contraintes2.gridy--;
			panelBibWest.add(boutonAjouter, contraintes2);
			
			// Ajout du panel au panel "mere" (en l'occurence panelNorthWest)
			panelNorthWest.add(panelBibWest, BorderLayout.NORTH);
			
			/***************************************************************************************/
			
			/*** GESTION DES BOUTONS POUR LES TABLES(ETU) ***********************************************/
			boutonTableEtudiant.setFont (new Font ("Helvetica", Font.BOLD,10));
			boutonTableEtudiant.setBackground(new Color (255,200,0));
			boutonTableEtudiant.addActionListener(this);
			contraintes.gridx--; contraintes.gridwidth =1;  contraintes.gridy=5;
			panelBibEast.add(boutonTableEtudiant, contraintes);
			
			/*** GESTION DES BOUTONS POUR LES TABLES(ETU) ***********************************************/
			boutonTableLivre.setFont (new Font ("Helvetica", Font.BOLD,10));
			boutonTableLivre.setBackground(new Color (255,200,0));
			boutonTableLivre.addActionListener(this);
			contraintes2.gridx--; contraintes2.gridwidth =1;  contraintes2.gridy=5;
			panelBibWest.add(boutonTableLivre, contraintes2);
			
			/***************************************************************************************/
	
			/***** ORGANISATION DU PANELEAST CONTENANT LES TABLES ****************************************************/
			// CardLayout
			panelEast.setLayout(gestionnaireDeCartes);
			panelEast.setOpaque (true); 
			add (panelEast, BorderLayout.EAST);
			
			// GESTION TABLE LIVRE
			tableLivre = new JTable(new Object[nbLignes][columnNames.length], columnNames);		
			tableLivre.setPreferredScrollableViewportSize(new Dimension(700,500));
			tableLivre.setFillsViewportHeight(true);
			
			// Pour agrandir la largeur des cellules
			for(int i=0; i<4; i++) {
				TableColumn col = tableLivre.getColumnModel().getColumn(i);
				col.setPreferredWidth(150);
			}
	
			// Pour agrandir la hauteur des cellules
			tableLivre.setRowHeight(30);
			        
			// On met le tableau dans un JScrollPAne
			JScrollPane scrollPane = new JScrollPane(tableLivre);
			//JScrollBar vjsp = scrollPane.getVerticalScrollBar();
			//JScrollBar hjsp = scrollPane.getHorizontalScrollBar();
			panelEast.add(scrollPane, BorderLayout.SOUTH);
			
			
			
			// GESTION TABLE ETU
			tableEtu = new JTable(new Object[nbLignes][columnNames2.length], columnNames2);		
			tableEtu.setPreferredScrollableViewportSize(new Dimension(1000,500));
			tableEtu.setFillsViewportHeight(true);
					
			// Pour agrandir la largeur des cellules
			for(int i=0; i<4; i++) {
				TableColumn col = tableEtu.getColumnModel().getColumn(i);
				col.setPreferredWidth(150);
			}
	
			// Pour agrandir la hauteur des cellules
			tableEtu.setRowHeight(30);
					        
			// On met le tableau dans un JScrollPAne
			JScrollPane scrollPane2 = new JScrollPane(tableEtu);
			panelEast.add(scrollPane2, BorderLayout.CENTER);
			JScrollBar vertical = scrollPane.getVerticalScrollBar();
			JScrollBar Horizontal = scrollPane.getHorizontalScrollBar();
		}//Fin du constructeur
		
		
		
				
	
	/**
	 * Permet de gï¿½nï¿½rer les ï¿½vï¿½nements produit par les boutons
	 * @param e Correspond ï¿½ un clic de bouton
	 */
	
	
	
	/**
	 * Permet de rï¿½cuperer le nom du livre
	 * @return un String correspondant au nom du livre
	 */
	public static String getChampTitre() {
		return champTitre.getText();
	}
	
	/**
	 * Permet de rï¿½cuperer l'auteur du livre
	 * @return un String correspondant ï¿½ l'auteur du livre
	 */
	public static String getChampAuteur() {
		return champAuteur.getText();
	}
	
	/**
	 * Permet de rï¿½cuperer l'ID de l'etudiant
	 * @return un String correspondant ï¿½ l'ID
	 */
	public String getChampIdEtu() {
		return champIdEtu.getText();
	}

	/**
	 * Permet de rï¿½cuperer le nom de l'ï¿½tudiant
	 * @return un String correspondant au nom de l'ï¿½tudiant
	 */
	public String getNomEtu() {
		return champNomEtu.getText();
	}
	
	/**
	 * Permet de rï¿½cuperer le prenom de l'ï¿½tudiant
	 * @return un String correspondant au prenom de l'ï¿½tudiant
	 */
	public String getPrenomEtu() {
		return champPrenomEtu.getText();
	}
	
	/**
	 * Permet de rï¿½cuperer le mot de passe du compte de l'etudiant
	 * @return un String correspondant au mot de passe
	 */
	public String getMdp() {
		return champMdp.getText();
	}
	
	/**
	 * Permet de rï¿½cuperer l'adresse mail de l'ï¿½tudiant
	 * @return un String correspondant l'adresse mail de l'ï¿½tudiant
	 */
	public String getAdresseMail() {
		return champAdresseMail.getText();
	}
	/**
	 * MÃ©thode permettant la crÃ©ation d'un compte Ã©tudiant
	 * @throws SQLException 
	 */
	public void createEtu() throws SQLException {
		
		/********** Connexion au serveur SQL ******************/
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
	
		Statement stmt = conn.createStatement ();
		
		String Nom = getNomEtu();
		String Prenom = getPrenomEtu();
		String AdrMail = getAdresseMail();
		String Mdp = getMdp();
		
		
		String query = "insert into ETUDIANT(NOM,PRENOM,EMAIL,MDP) VALUES ('"+Nom+"','"+Prenom+"','"+AdrMail+"','"+Mdp+"')";
		stmt.executeUpdate(query);
		JOptionPane.showInputDialog (
    			this,
    			"Compte crée !",
    			JOptionPane.OK_CANCEL_OPTION
    			);
	}
	
	/**
	 * MÃ©thode permettant la suppression d'un compte Ã©tudiant
	 * @throws SQLException 
	 */
	public void deleteEtu() throws SQLException {
		
		/********** Connexion au serveur SQL ******************/
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
	
		Statement stmt = conn.createStatement ();
		
		
		String ID = getChampIdEtu();
		
		String query = "delete from ETUDIANT where id_et = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, ID);
	    ps.executeUpdate();	
	    JOptionPane.showInputDialog (
    			this,
    			"Compte supprimé !",
    			JOptionPane.OK_CANCEL_OPTION
    			);
	}
	
	/**
	 * MÃ©thode permettant la mise Ã  jour/modification d'un compte Ã©tudiant
	 * @throws SQLException 
	 */
	public void updateEtu() throws SQLException {
		
		/********** Connexion au serveur SQL ******************/
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
		
		String ID = getChampIdEtu();
		String Nom = getNomEtu();
		String Prenom = getPrenomEtu();
		String AdrMail = getAdresseMail();
		String Mdp = getMdp();
	    String query = "UPDATE ETUDIANT SET nom = ?, prenom = ?, email = ?,mdp = ?"
	            + " WHERE id_et = ?";
		PreparedStatement ps = conn.prepareStatement(query);
	    ps.setString(1, Nom);
	    ps.setString(2, Prenom);
	    ps.setString(3, AdrMail);
	    ps.setString(4, Mdp);
	    ps.setString(5, ID);
	    ps.executeUpdate();
	    JOptionPane.showInputDialog (
    			this,
    			"Compte modifié !",
    			JOptionPane.OK_CANCEL_OPTION
    			);
	}
	
	/*
	 * on vérifie d'abord que le livre est reservé
	 *	ensuite on vérifie si la reservation est faite par celui qui fait l'emprunt
	 *	si c'est le cas on le laisse emprunter
	 *	sinon ça affiche que c'est pas lui qui l'a reservé en premier
	 *	et si il n'est pas reservé il doit le reserver
	 *	on supprime sa reservation une fois que le livre est emprunté

	 */	
	public void empruntLivre() throws SQLException{
		
		/********** Connexion au serveur SQL ******************/
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
	
		Statement stmt = conn.createStatement ();
		
		String ID = getChampIdEtu();
		String ID_EX = getChampAuteur();
		String TITRE_LIVRE = getChampTitre();
		String result = null;
		String result2 = null;
		
		int IDI_EX = Integer.parseInt(ID_EX);
		int IDI = Integer.parseInt(ID);
		
		
		
		String verif = "Select count(*) from RESERV where ID_LIVRE = ( Select ID_LIVRE from EXEMPLAIRE where ID_EX = ? and ID_LIVRE = (Select ID_LIVRE from LIVRE where Titre = ?))";
		String verifidet = "Select ID_ET from RESERV where ID_LIVRE = ( Select ID_LIVRE from EXEMPLAIRE where ID_EX = ? and ID_LIVRE = (Select ID_LIVRE from LIVRE where Titre = ?))";
		
		
		System.out.println(verifidet);
		
		
		PreparedStatement ps = conn.prepareStatement(verif);
	    ps.setInt(1, IDI_EX);
	    ps.setString(2, TITRE_LIVRE);
	    System.out.println(ps.toString());
		ResultSet res = ps.executeQuery();
		
		PreparedStatement ps2 = conn.prepareStatement(verifidet);
	    ps2.setInt(1, IDI_EX);
	    ps2.setString(2, TITRE_LIVRE);
		ResultSet res2 = ps2.executeQuery();
		
		System.out.println(res2);
		
		while(res.next()) {
			result = res.getString("count(*)");
		}
		
		
		while(res2.next()) {
			result2 = res2.getString("ID_ET");
		}
		
		int resulti = Integer.parseInt(result);
		int resulti2 = Integer.parseInt(result2);
		
		if(resulti != 0) {
			if(resulti2 == IDI) {
				Date DATEJ = new Date();
				Date DATEF = new Date();
				int i;
				
				for(i=0 ; i<15 ; i++) {
				DATEF = DATEF.dateDuLendemain(); 
				}
				
				String dateDebut = DATEJ.toString();
				String dateFin = DATEF.toString();
				
				String query = "insert into EMPRUNT VALUES ("+IDI+","+IDI_EX+",to_date('"+dateDebut+"',DD/MM/YYYY),to_date('"+dateFin+"',DD/MM/YYYY))";
				stmt.executeUpdate(query);
				String delreserv = "delete from RESERV where id_et = ? and ID_LIVRE = (Select ID_LIVRE from EXEMPLAIRE where ID_EX = ?) ";
				PreparedStatement ps3 = conn.prepareStatement(delreserv);
				ps3.setInt(1, IDI);
				ps3.setInt(2, IDI_EX);
				ps3.executeQuery();
				JOptionPane.showInputDialog (
	        			this,
	        			"Le livre peut être prêté !",
	        			JOptionPane.OK_CANCEL_OPTION
	        			);
			}
			else {
		JOptionPane.showInputDialog (
    			this,
    			"Le livre est déjà  reservé par quelqu'un d'autre, emprunt impossible !",
    			JOptionPane.OK_CANCEL_OPTION
    			);
		
			}
		}
		else {
			JOptionPane.showInputDialog (
	    			this,
	    			"Le livre n'est pas reservé, reserve le avant !",
	    			JOptionPane.OK_CANCEL_OPTION
	    			);
		}
	}
		
	
	public void recupLivre() throws SQLException {
		/********** Connexion au serveur SQL ******************/
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
	
		Statement stmt = conn.createStatement ();
		
		String ID = getChampIdEtu();
		String ID_EX = getChampIdEtu();
		int IDI_EX = Integer.parseInt(ID_EX);
		int IDI = Integer.parseInt(ID);
		
		String delemprunt = "delete from EMPRUNT where ID_ET = ? and ID_EX = ? ";
		PreparedStatement ps = conn.prepareStatement(delemprunt);
		ps.setInt(1, IDI);
		ps.setInt(2, IDI_EX);
		stmt.executeUpdate(delemprunt);
		JOptionPane.showMessageDialog(null, "L'emprunt à  été supprimé, il ne reste plus qu'à  aller ranger le livre !", "Suppr d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	/**
	 * MÃ©thode permettant l'ajout d'un livre dans la base de donnÃ©es
	 * @throws SQLException 
	 */
	public void addLivre() throws SQLException {
		/********** Connexion au serveur SQL ******************/
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
	    
	      Statement stmt = conn.createStatement();
	      
		String auteur = getChampAuteur();
		String titre =  getChampTitre();					
		
		// Traitement
		String query = "insert into LIVRE(Auteur,Titre) VALUES ('"+auteur+"','"+titre+"')";
		stmt.executeUpdate(query);
		JOptionPane.showMessageDialog(null, "Livre ajouté !", "Ajout d'un livre", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Cette méthode permet de rechercher et sélectionner le livre
	 * taper par l'étudiant grâce à la récupération de l'auteur et du titre
	 * @throws SQLException
	 */
	public static void rechercheLivre(ResultSet res)throws SQLException{
		/********** Connexion au serveur SQL ******************/
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
		/*Statement stmt = conn.createStatement ();
		String Auteur = getChampAuteur();
		String Livre =  getChampTitre();					
		
		String query ="Select distinct auteur,titre from LIVRE where auteur = '"+Auteur+"', titre = '"+Livre+"')" ;
		stmt.executeUpdate(query);
		res = stmt.executeQuery(query);
		if(res != null) {
			 ResultSet resultat = stmt.executeQuery("select auteur, titre from livre");
			 rechercheLivre(resultat);
        }*/
		
		int i;
		 ResultSetMetaData rsetMeta = res.getMetaData();// lecture des métaData
		 int Nbre = rsetMeta.getColumnCount(); // le nombre de colonne
		 for ( i =1; i <= Nbre; i++) { // Afficher les entêtes de colonnes
		 if ( i > 1 ) System.out.println(", ");
		 	System.out.println(" le nom de la colonne est : " + rsetMeta.getColumnLabel(i));
		 }
		 while (res.next()) {	
			 for ( i =1; i <= Nbre; i++){
				 if ( i > 1 ) System.out.println(", ");
				 System.out.println(res.getString(i));
			 }
			 System.out.println("");
		 }
	} 
	
	/**
	 * Afficher les livres empruntés depuis plus de 15 jours 
	 * et les noms et adresse mails des emprunteurs 
	 * @throws SQLException
	 */
/*	public void AfficheLivre() throws SQLException{
        /********** Connexion au serveur SQL ******************
        //Chargement du driver JDBC Oracle
        
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        // Connexion
    
        Connection conn =
          DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
                       "ahassan", "azerty");
        //Traitement
        String query = "SELECT * FROM Livre";
        
          Statement stm = conn.createStatement();
          ResultSet res = stm.executeQuery(query);
          int i = 0;
          while (res.next()) {
              String Livre = res.getString("Titre");
              String Auteur = res.getString("Auteur");
              System.out.println(Livre + " "+ Auteur+ "\n");
              columnNames[i] = Livre;
              columnNames[i] = Auteur ;
               i++;
          }
                
          
          String verifidate = "Select DATE_RES from RESERV where DATE_RES + 15 > (SELECT SYSDATE FROM DUAL);";
          Statement stm2 = conn.createStatement();
          ResultSet res2 = stm2.executeQuery(verifidate);
          int e = 0;
          while (res2.next()) {
              String Nom = res.getString("Nom");
              String mail = res.getString("mail");
              System.out.println(Nom + " "+ mail+ "\n");

             // String Prenom = res.getString("Prenom");
             // String Numéro_étudiant = res.getString("Numéro étudiant");
             // String nbr_livres_réservés = res.getString("nbr livres réservés");
              columnNames2[e] = Nom;
              columnNames2[e] = mail ;
            e++;
          }
    } */
	
	/**
	 * Afficher la table Livre en utilisant une méthode 
	 * générique prenant en entrée un objet ResultSet.
	 * @param rset
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void AfficheTableLivre( ResultSet rset ) throws SQLException, IOException{
		 int i;
		 ResultSetMetaData rsetMeta = rset.getMetaData();// lecture des métaData
		 int Nbre = rsetMeta.getColumnCount(); // le nombre de colonne
		 for ( i =1; i <= Nbre; i++) { // Afficher les entêtes de colonnes
		 if ( i > 1 ) System.out.println(", ");
		 	System.out.println(" le nom de la colonne est : " + rsetMeta.getColumnLabel(i));
		 }
		 while (rset.next()) {	
			 for ( i =1; i <= Nbre; i++){
				 if ( i > 1 ) System.out.println(", ");
				 System.out.println(rset.getString(i));
			 }
			 System.out.println("");
		 }
	 }
	
	/**
	 * Afficher le nom et adresses mail des étudiants qui ont  
	 * empruntés un livre depuis plus de jour. 
	 * @param rset
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void AfficheTableEtudiant( ResultSet rset ) throws SQLException, IOException{
		 int i;
		 ResultSetMetaData rsetMeta = rset.getMetaData();// lecture des métaData
		 int Nbre = rsetMeta.getColumnCount(); // le nombre de colonne
		 for ( i =1; i <= Nbre; i++) { // Afficher les entêtes de colonnes
		 if ( i > 1 ) System.out.println(", ");
		 	System.out.println(" le nom de la colonne est : " + rsetMeta.getColumnLabel(i));
		 }
		 while (rset.next()) {	
			 for ( i =1; i <= Nbre; i++){
				 if ( i > 1 ) System.out.println(", ");
				 System.out.println(rset.getString(i));
			 }
			 System.out.println("");
		 }
	 }

	 static public void main(String args[]) throws SQLException, IOException{
		 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		 Connection AcceBD = DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
                 "ahassan", "azerty");
		 Statement stmt = AcceBD.createStatement();
		 ResultSet rset = stmt.executeQuery("select * from livre");
		 AfficheTableLivre(rset);
		 
		 
		 Statement stmt2 = AcceBD.createStatement();	
		 String Auteur = getChampAuteur();
		 String Livre =  getChampTitre();
		 String query ="Select distinct auteur,titre from LIVRE where auteur = '"+Auteur+"', titre = '"+Livre+"')" ;
		 stmt.executeUpdate(query);
		 ResultSet res = stmt2.executeQuery(query);
		 if(res != null) {
			 ResultSet resultat = stmt.executeQuery("select auteur, titre from livre");
			 rechercheLivre(resultat);
         }
		
		 String verifidate = "Select DATE_RES from RESERV where DATE_RES + 15 > (SELECT SYSDATE FROM DUAL);";
         Statement stm3 = AcceBD.createStatement();
         ResultSet res2 = stm3.executeQuery(verifidate); 
         if(res2 != null) {
			 ResultSet rset2 = stmt.executeQuery("select Nom, mail from etudiant");
			 AfficheTableEtudiant(rset2);
         }
         
		 AcceBD.close();
		 stmt.close();
		 rset.close();
	 }
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==boutons [0]) {
			gestionnaireDeCartes.first (panelEast);
		}
		
		if (e.getSource()==boutons [1]) {
			gestionnaireDeCartes.next (panelEast);
		}
		
		if (e.getSource() == boutonCreer) {
			try {
				createEtu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource()== boutonSupprimer) {
			try {
				deleteEtu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		if (e.getSource()== boutonModifier) {
			try {
				updateEtu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
				
		if (e.getSource() == boutonAjouter) {
			try {
				addLivre();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
		if (e.getSource() == boutonPreter) {
			try {
				empruntLivre();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
		if (e.getSource() == boutonRecup) {
			try {
				recupLivre();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
		
		/*if (e.getSource() == boutonTableLivre || e.getSource() == boutonTableEtudiant ) {
            try {
                AfficheLivre();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }*/
		
	}
}
		
	