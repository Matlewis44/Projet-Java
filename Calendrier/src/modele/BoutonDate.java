package modele;

import javax.swing.JButton;
/**
 * BoutonDate est la classe qui associe une date ou bien un jour � un bouton.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class BoutonDate extends JButton {
	/**
	 * date repr�sente la date � mettre sur le bouton
	 */
	private Date date;
	
	/**
	 * jour repr�sente le jour � mettre sur le bouton
	 */
	private String jour;
	/**
	 * Le Constructeur BoutonDate associe une date � un bouton.
	 * @param parDate une Date � associ� � un bouton
	 */
	public BoutonDate(Date parDate) {
		super (Integer.toString (parDate.getJour()));
		date = parDate;
	}
	
	/**
	 * Le Constructeur BoutonDate associe un jour � un bouton.
	 * @param parJour une chaine de caract�re � associ� � un bouton
	 */
	public BoutonDate(String parJour) {
		super (parJour);
	}
	
	/**
	 * La methode setDate permet de modifier la Date du bouton.
	 * @param date correspond � la nouvelle Date a donn� au bouton.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * La methode getDate retourne la date associ� au bouton.
	 * @return la date associ� au bouton
	 */
	public Date getDate() {
		return date;
	}
}