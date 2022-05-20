import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;


/**
 * Interface pour l'étudiant
 * @author aniss
 *
 */
public class PanelEtu extends JPanel implements ActionListener {
	
	// Pour l'etudiant
	JLabel livre = new JLabel();
	JLabel auteur = new JLabel();
	JButton boutonReserver = new JButton("reservation");
	JPanel panelWest = new JPanel();
	JPanel panelEast = new JPanel();
	
	// Les champs textfield
	JTextField champTitre = new JTextField (20);
	JTextField champAuteur = new JTextField (20);
	//JTextField champEdition = new JTextField (20);
	JTextField champIdEtu = new JTextField (20);

	
	// Pour la table avec livre
	int nbLignes = 30;
	JTable tableLivre;
	String[] columnNames = {"Livre", "Auteur", "Etat"};
	private Statement stmt;
	
	


	/**
	 * Constructeur sans paramètre
	 */
	public PanelEtu(){
		
		this.setLayout(new BorderLayout(20,20));
		
		/***** PANELWEST ***************************************************************/
		// Les labels pour les champs + interface sur laquelle on se trouve
		JLabel labelTitre = new JLabel("Titre", JLabel.LEFT);
		JLabel labelAuteur = new JLabel("Auteur", JLabel.LEFT);
		//JLabel labelEdition = new JLabel("Edition", JLabel.LEFT);
		JLabel labelIdEtu = new JLabel("ID Etudiant", JLabel.LEFT);
		JLabel labelIdentifiant = new JLabel("Identifiez-vous,", JLabel.LEFT);
		JLabel labelReservez = new JLabel("et réservez un livre !", JLabel.LEFT);



		
		// Le gestionnaire de répartition
		panelWest.setLayout (new GridBagLayout ());	
		GridBagConstraints contraintes = new GridBagConstraints ();
	  	contraintes.insets = new Insets (6,6,6,6); 
		contraintes.anchor = GridBagConstraints.WEST;
		
		// labelIdentifiant
		contraintes.gridy=0; contraintes.gridwidth = 1;	contraintes.gridx=0;
		labelIdentifiant.setFont(new Font("Serif", Font.ITALIC, 40));
		panelWest.add(labelIdentifiant, contraintes);
		
		// labelReservez
		contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
		labelReservez.setFont(new Font("Serif", Font.ITALIC, 40));
		panelWest.add(labelReservez, contraintes);
				
						
		// labelIdEtu et champIdEtu
		contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
		panelWest.add(labelIdEtu, contraintes);
		contraintes.gridx++; contraintes.gridwidth =4 ; 
		contraintes.fill = GridBagConstraints.HORIZONTAL;		
		panelWest.add(champIdEtu, contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		
		// labelTitre et champTitre
		contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
		panelWest.add(labelTitre, contraintes);
		contraintes.gridx++; contraintes.gridwidth =4 ; 
		contraintes.fill = GridBagConstraints.HORIZONTAL;		
		panelWest.add(champTitre, contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		
		
		// labelAuteur et champAuteur
		contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
		panelWest.add(labelAuteur, contraintes);
		contraintes.gridx++; contraintes.gridwidth =4 ; 
		contraintes.fill = GridBagConstraints.HORIZONTAL;		
		panelWest.add(champAuteur, contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		/*
		// labelEdition et champEdition
		contraintes.gridy++; contraintes.gridwidth = 1;	contraintes.gridx=0;
		panelWest.add(labelEdition, contraintes);
		contraintes.gridx++; contraintes.gridwidth =4 ; 
		contraintes.fill = GridBagConstraints.HORIZONTAL;		
		panelWest.add(champEdition, contraintes);
		contraintes.fill = GridBagConstraints.NONE;*/
		
		// boutonReserver
		contraintes.gridwidth = 1;	contraintes.gridx=6;
		panelWest.add(boutonReserver, contraintes);
		boutonReserver.addActionListener(this);
		
		
		
		this.add(panelWest, BorderLayout.WEST);
		
		/***********************************************************************************/
		
		/***** PANELEAST ***************************************************************/
		
		// GESTION TABLE LIVRE
		tableLivre = new JTable(new Object[nbLignes][columnNames.length], columnNames);		
		tableLivre.setPreferredScrollableViewportSize(new Dimension(700,500));
		tableLivre.setFillsViewportHeight(true);
		
		// Pour agrandir la largeur des cellules
		for(int i=0; i<3; i++) {
			TableColumn col = tableLivre.getColumnModel().getColumn(i);
	        col.setPreferredWidth(200);
		}

		// Pour agrandir la hauteur des cellules
        tableLivre.setRowHeight(30);
        
        // On met le tableau dans un JScrollPAne
		JScrollPane scrollPane = new JScrollPane(tableLivre);
		panelEast.add(scrollPane);
		
		this.add(panelEast, BorderLayout.EAST);

		

		
	}

	/**
	 * Permet de récuperer le nom du livre
	 * @return un String correspondant au nom du livre
	 */
	public String getChampTitre() {
		return champTitre.getText();
	}
	
	/**
	 * Permet de récuperer l'auteur du livre
	 * @return un String correspondant à l'auteur du livre
	 */
	public String getChampAuteur() {
		return champAuteur.getText();
	}
	
	/**
	 * Permet de récuperer l'identifiant de l'etudiant
	 * @return un String correspondant à l'auteur du livre
	 */
	public String getChampIdEtu() {
		return champIdEtu.getText();
	}
	
	
	/**
	 * Méthode permettant la réservation d'un livre par un étudiant
	 * en ne dépassant pas les 5 livres 
	 * @throws SQLException 
	 */
	public void reservLivre() throws SQLException{
		
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
		String ID_EX = getChampTitre();
		int result = 0;
		Date DATEJ = new Date();
		Date DATEF = new Date();
		
		int i;
		for(i=0 ; i<5 ; i++) {
		DATEF = DATEF.dateDuLendemain(); 
		}
		
		System.out.println(DATEF.toString());
		String dateDebut = DATEJ.toString();
		String dateFin = DATEF.toString();
		
		int IDI_EX = Integer.parseInt(ID_EX);
		int IDI = Integer.parseInt(ID);
		
		String query = "Select count(*) from emprunt where id_et = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
	    ps.setString(1, ID);
		ResultSet res = ps.executeQuery();
		
		while(res.next()) {
			result = res.getInt("count(*)");
		}
	    
		
		if(result == 5) {
			JOptionPane.showMessageDialog(null, "Vous ne pouvez pas reserver (+5 reservation) ", "impossible de reserver un livre", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			
			String insert =  "insert into RESERV (ID_ET, ID_LIVRE, date_res, date_fin_res ) VALUES ("+IDI+","+IDI_EX+",to_date('"+dateDebut+"', 'DD/MM/YYYY'),to_date('"+dateFin+"' , 'DD/MM/YYYY'))";
			System.out.println(insert);
			PreparedStatement ps2 = conn.prepareStatement(insert);
			ps2.executeQuery();
			JOptionPane.showMessageDialog(null, "Vous avez reservé votre livre !", "Reservation d'un livre", JOptionPane.INFORMATION_MESSAGE);
		}				

	}
	
	/**
	 * Cette méthode permet de rechercher et sélectionner le livre
	 * taper par l'étudiant grâce à la récupération de l'auteur et du titre
	 * @throws SQLException
	 */
/*	public void rechercheLivre()throws SQLException{
		/********** Connexion au serveur SQL ******************
		//Chargement du driver JDBC Oracle
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Connexion
	
		Connection conn =
		  DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "ahassan", "azerty");
		//Traitement
	
		Statement stmt = conn.createStatement ();
		String Auteur = getChampAuteur();
		String Livre =  getChampTitre();					
		
		String query ="Select distinct auteur,titre from LIVRE where auteur = ?, titre = ?)" ;
		stmt.executeUpdate(query);
		ResultSet res = stmt.executeQuery(query);
		int i = 0;
	      while (res.next()) {
	    	  columnNames[i] = Livre;
		      columnNames[i] = Auteur ;
		    
	        i++;
	      }		
	}*/
	
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
	static public void main(String args[]) throws SQLException, IOException{
		 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		 Connection AcceBD = DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
                "ahassan", "azerty");
		 Statement stmt = AcceBD.createStatement();
		 ResultSet rset = stmt.executeQuery("select * from livre");
		 AfficheTableLivre(rset);
    
		 AcceBD.close();
		 stmt.close();
		 rset.close();
	 }
	/**
	 * Permet de générer les évènements produit par les boutons
	 * @param e Correspond à un clic de bouton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == boutonReserver) {
			try {
				reservLivre();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
	
	}

}