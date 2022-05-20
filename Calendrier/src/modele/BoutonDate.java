package modele;

import javax.swing.JButton;
/**
 * BoutonDate est la classe qui associe une date ou bien un jour à un bouton.
 * 
 * @author Raffaele GIANNICO
 * @version 1.0
 */
public class BoutonDate extends JButton {
	/**
	 * date représente la date à mettre sur le bouton
	 */
	private Date date;
	
	/**
	 * jour représente le jour à mettre sur le bouton
	 */
	private String jour;
	/**
	 * Le Constructeur BoutonDate associe une date à un bouton.
	 * @param parDate une Date à associé à un bouton
	 */
	public BoutonDate(Date parDate) {
		super (Integer.toString (parDate.getJour()));
		date = parDate;
	}
	
	/**
	 * Le Constructeur BoutonDate associe un jour à un bouton.
	 * @param parJour une chaine de caractère à associé à un bouton
	 */
	public BoutonDate(String parJour) {
		super (parJour);
	}
	
	/**
	 * La methode setDate permet de modifier la Date du bouton.
	 * @param date correspond à la nouvelle Date a donné au bouton.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * La methode getDate retourne la date associé au bouton.
	 * @return la date associé au bouton
	 */
	public Date getDate() {
		return date;
	}
}