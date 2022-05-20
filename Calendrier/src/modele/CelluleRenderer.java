package modele;

import java.awt.Component;
import java.awt.Font;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * CelluleRenderer est la classe qui embellit la table des évenements.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class CelluleRenderer extends JLabel implements TableCellRenderer{
	/**
	 * La constructeur CelluleRenderer permet d'ajouter des couleurs à la table
	 * 
	 */
	public CelluleRenderer(){
		super();
		setOpaque (true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(new java.awt.Color(49, 140, 231));
	}
	@Override
	/**
	 * La methode getTableCellRendererComponent permet d'ajouter des couleurs, modifier la police aux cellules, les infos-bulles...
	 * @param parTable une JTable a modifier
	 * @param cellValue un objet, la cellule a modifier
	 * @param isSelected boolean cellule selectionner ?
	 * @param hasFocus boolean, cellule a le focus ?
	 * @param row, un entier représentant la ligne
	 * @param col, un entier représentant la colonne
	 * @return this.
	 */
	public Component getTableCellRendererComponent(JTable parTable, Object cellValue, boolean isSelected, boolean hasFocus, int row,int col) {
		// TODO Auto-generated method stub
		String texte;
		String toolTipTxt;
		String temp = null;
		if(cellValue == null){
			texte = null;
			toolTipTxt = null;
			setToolTipText(null);
		}
		else{
			texte = cellValue.toString();
			toolTipTxt = cellValue.toString();

			StringTokenizer titre = new StringTokenizer(texte);
			if (titre.hasMoreTokens()){  
				texte= titre.nextToken().toString();  
				//System.out.println(texte);
			} 
		
			StringTokenizer titre2 = new StringTokenizer(toolTipTxt);
			for(int i =0;i<=10;i++){
				if (titre2.hasMoreTokens()){  
					toolTipTxt= titre2.nextToken().toString();  
					//System.out.println(toolTipTxt);
				} 
			}
			temp= titre2.nextToken().toString();
			temp= titre2.nextToken().toString();
			setToolTipText(texte+" a "+toolTipTxt+": "+ temp);
		}
		setText(texte);
		setFont (new Font ("Calibri", Font.BOLD, 14));
		
		//System.out.println(row);
		//System.out.println(col);
		return this; 

	}
	

}