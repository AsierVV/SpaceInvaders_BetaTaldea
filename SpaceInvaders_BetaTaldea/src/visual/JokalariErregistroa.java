package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modeloa.JokoKudeatzailea;

public class JokalariErregistroa extends JPanel{

	private JFrame erregistroFrame;
	private JPanel erregistroPanel;
	
	private static JokalariErregistroa nireEMA = null;
	private String jokalariIzena;
	
	private JokalariErregistroa() {
		erregistroFrame = new JFrame("JOKALARI ERREGISTROA");
        erregistroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        erregistroPanel = new JPanel();
	    erregistroPanel.setLayout(new GridLayout(3,1,0,15));
	    erregistroPanel.setPreferredSize(new Dimension(400, 250));
	    erregistroPanel.setBackground(Color.BLACK);
	    erregistroPanel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

	    // Label-a
	    JLabel info = new JLabel("Sartu zure izena:");
	    info.setHorizontalAlignment(JLabel.CENTER);
	    info.setFont(new Font("Arial", Font.BOLD, 24));
	    info.setForeground(Color.WHITE);		// Hizkien kolorea
	    erregistroPanel.add(info);
	    
	    // Idazteko testua
	    JTextField izena = new JTextField();
	    izena.setHorizontalAlignment(JTextField.CENTER);
	    izena.setFont(new Font("Arial", Font.PLAIN, 22));
		izena.setBackground(Color.DARK_GRAY);	// Atzeko kolorea
		izena.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));	// Field-eko bordea
	    izena.setForeground(Color.WHITE);		// Hizkien kolorea
		izena.setCaretColor(Color.WHITE);		// Kurtsorearen kolorea
	    erregistroPanel.add(izena);
	    
	    // Partida hasteko botoia
	    JButton hasiBotoia = new JButton("PARTIDA HASI");
	    hasiBotoia.setFont(new Font("Arial", Font.BOLD, 20));
	    hasiBotoia.setBackground(Color.GREEN);	// Atzeko kolorea
	    hasiBotoia.setForeground(Color.BLACK);	// Hizkien kolorea
	    erregistroPanel.add(hasiBotoia);
	    
	    // Botoia pultsatu
	    hasiBotoia.addActionListener(e -> {
	    	jokalariIzena = izena.getText().trim(); // Honekin JTextFiled-ean idatzi dena hartzen dugu String batean
	    	
	    	if (jokalariIzena.isEmpty()) {	// True ematen du String-aren luzera 0 bada
	    		JOptionPane.showMessageDialog(erregistroFrame, "Izena sartu behar duzu.");	// Mezu bat duen leiho txiki bat erakusten du; eta jarraitzeko "Aceptar" eman behar da
	    	} else {
				erregistroFrame.dispose();
				HasierakoPantaila.getEMA().setJokalariIzena(jokalariIzena);
	    	}
	    });
		
	    erregistroFrame.getContentPane().add(erregistroPanel);
        erregistroFrame.pack();
        erregistroFrame.setLocationRelativeTo(null);
        erregistroFrame.setVisible(true);
	}
	
	public static JokalariErregistroa getEMA() {
		if (nireEMA == null) {
			nireEMA = new JokalariErregistroa();
		}
		return nireEMA;
	}
	
	public String getJokalariIzena() {
		return jokalariIzena;
	}
}
