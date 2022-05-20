import java.awt.Color;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class FenetreMere extends JFrame {
	
	public FenetreMere (String parTitre) {
		super (parTitre); // appel du constructeur de la classe mère
		PanelDiapo contentPane = new PanelDiapo () ;
		JMenuBar menuBar = new JMenuBar ();
		String itemMenu[] = {"Fermer"};
		char mnemos[]={'C','V','S','R'};
		char raccourci[]={'Q'};
		
		for(int i = 0; i< itemMenu.length;i++){
			JMenuItem item = new JMenuItem(itemMenu[i], mnemos[i]);
			item.addActionListener(contentPane);
			item.setActionCommand(itemMenu[i]);
			menuBar.add(item);
			item.setAccelerator (KeyStroke.getKeyStroke (raccourci[i], java.awt.Event.CTRL_MASK));
		}
		
		setJMenuBar(menuBar);
		setContentPane (contentPane);
		contentPane.setBackground (Color.GRAY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,400); setVisible(true); contentPane.requestFocus();setLocation(200,300);
		setBackground (new Color (100,67,98));
		
		
	} // FenetreMere ()

	public static void main (String [] args) {
		new FenetreMere ("Diaporama");
	} // main ()

	public Insets getInsets () {
		return new Insets (40,15,15,15); 
		}
	
} // FenetreMere