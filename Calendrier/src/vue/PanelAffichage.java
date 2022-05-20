package vue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.Agenda;
import modele.CelluleRenderer;
import modele.Date;
import modele.Evenement;
import modele.ModeleTable;

/**
 * PanelAffichage est la classe qui créer la JTable et permet aussi de gérer son fonctionnement et ses caractéristiques.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class PanelAffichage extends JPanel{
	/**
	 * L'agenda qui contient les évènements à ajouter à la JTable.
	 * @see Agenda
	 */
	 private Agenda agenda;
	
	/**
	 * La JTable qui recense les évènements de l'agenda
	 * @see JTable
	 */
	 private JTable tableSemaine2;
	
	/**
	 * Le ModelTable pour définir les caractéristiques de la JTable.
	 * @see ModeleTable
	 */
	 private ModeleTable modele;
	
	/**
	 * Le CelluleRenderer pour donner des couleurs à la table.
	 * @see CelluleRenderer
	 */
	 private CelluleRenderer rendererCells;
	
	/**
	 * rowIndex désigne la ligne d'une JTable
	 * @see JTable
	 */
	 private int rowIndex ;
	
	/**
	 * colIndex désigne la colonne d'une JTable
	 * @see JTable
	 */
	 private int colIndex ;
	
	/**
	 * Le Constructeur PanelAffichage permet de construire une table ainsi que de gérer son fonctionnement.
	 * @param parAgenda l'agenda qui contient les évènements
	 */
	public PanelAffichage(Agenda parAgenda){
		GregorianCalendar date = new GregorianCalendar();
		int numJour = date.get(date.DAY_OF_MONTH);
		int mois = date.get(date.MONTH)+1;
		int annee = date.get(date.YEAR);
		
		/**
		 * today de type date, utile pour afficher la JTable par défault à la date courante.
		 */
		Date today = new Date(numJour,mois,annee);
		agenda = parAgenda;
		modele = new ModeleTable(today, parAgenda);
		JTable tableSemaine = new JTable(modele);
		tableSemaine2 = tableSemaine;
		/**
		 *  le scrollPane de la Jtable apparait seulement si besoin.
		 */
		JScrollPane scrollPane = new JScrollPane(tableSemaine, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
		
		tableSemaine.getTableHeader().setBackground (new java.awt.Color(220, 220, 220) );
		tableSemaine.getTableHeader().setFont (new java.awt.Font("Arial",java.awt.Font.BOLD,15) );
		tableSemaine.getTableHeader().setForeground (new java.awt.Color(100, 100, 100) );
		// pour empêcher les redimensionnements
		tableSemaine.getTableHeader().setResizingAllowed(false);
		// pour empêcher les réordonnancements
		tableSemaine.getTableHeader().setReorderingAllowed(false);
		// la hauteur des lignes
		tableSemaine.setRowHeight(24);
	//	tableSemaine.getToolTipText(MouseEvent );
		
		/**
		 * Pour afficher un message de dialogue quand on clique sur une cellule.
		 */
		tableSemaine.addMouseListener ( new MouseAdapter ( ) {
			// la méthode mouseClicked est surchargée
			public void mouseClicked (MouseEvent evt) {
			JTable table = (JTable) evt.getSource();
			ModeleTable model =(ModeleTable) table.getModel();
			Point point = evt.getPoint();
			rowIndex = table.rowAtPoint (point );
			colIndex = table.columnAtPoint (point );
			// La liste des déserts est affichée dans une fenêtre de dialogue
			JOptionPane.showMessageDialog ( table,
			model.getValueAt (rowIndex,colIndex) );
			}} ) ;
		
		rendererCells = new CelluleRenderer();
		rendererCells.getTableCellRendererComponent(tableSemaine, tableSemaine.getValueAt (rowIndex,colIndex),true, true, rowIndex, colIndex);
		tableSemaine.setDefaultRenderer(Evenement.class, rendererCells);
	}
	/**
	 * La methode setSemaine modifie la semaine de la table
	 * @param parDate la nouvelle date pour la nouvelle semaine 
	 */
	public void setSemaine(Date parDate){
		modele = new ModeleTable (parDate, agenda);
		tableSemaine2.setModel(modele);
		
		rendererCells = new CelluleRenderer();
		rendererCells.getTableCellRendererComponent(tableSemaine2, tableSemaine2.getValueAt (rowIndex,colIndex),false, true, rowIndex, colIndex);
		tableSemaine2.setDefaultRenderer(Evenement.class, rendererCells);
		//	System.out.println("tableSemaine");
	}
	/**
	 * La methode getModel retourne le modele d'une table
	 * @return ModeleTable le modele de la table
	 */
	public ModeleTable getModel() {
		// TODO Auto-generated method stub
		return modele;
	}
	
	 
	
}