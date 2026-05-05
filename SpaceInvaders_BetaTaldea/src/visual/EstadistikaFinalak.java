package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modeloa.JokoKudeatzailea;

public class EstadistikaFinalak extends JPanel implements Observer{

	private JFrame estadisFrame;
	private JPanel panelOsoa;
	private JPanel botoiakPanel;
	private JPanel estadisPanel;
	
	private String jokalaria;
	private String data;
	private String emaitza;
	private String zailtasuna;
	private String puntuazioa;
	private String denb;
	private String hegazkinMota;
	private String etsaiMota;
	
	private static EstadistikaFinalak nireEMA = null;

	private EstadistikaFinalak() {
		estadisFrame = new JFrame("PARTIDAREN ESTADISTIKAK");
		estadisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelOsoa = new JPanel();
		panelOsoa.setLayout(new BorderLayout(0, 20));
		panelOsoa.setPreferredSize(new Dimension(1400, 450));
		panelOsoa.setBackground(Color.BLACK);
		panelOsoa.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
		
		estadistikakEzarri();
		botoiakGehitu();
		
		estadisFrame.getContentPane().add(panelOsoa);
		estadisFrame.pack();
		estadisFrame.setLocationRelativeTo(null);
		estadisFrame.setVisible(false);
		
		JokoKudeatzailea.getEMA().addObserver(this);
	}
	
	public static EstadistikaFinalak getEMA() {
		if (nireEMA == null) nireEMA = new EstadistikaFinalak();
		return nireEMA;
	}
	
	private void estadistikakEzarri() {
		estadisPanel = new JPanel();
		estadisPanel.setLayout(new GridLayout(1,7,0,15));
		estadisPanel.setPreferredSize(new Dimension(840, 300));
		estadisPanel.setBackground(Color.BLACK);
		estadisPanel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
		
		azkenPartidaIrakurri();
		
		// JOKALARIA
		JPanel jokalariPanel = new JPanel();
		jokalariPanel.setLayout(new GridLayout(2,1,0,15));
		jokalariPanel.setPreferredSize(new Dimension(120, 300));
	    jokalariPanel.setBackground(Color.BLACK);
	    
	    JLabel jokalariLabel = new JLabel("Jokalaria:");
	    jokalariLabel.setHorizontalAlignment(JLabel.CENTER);
	    jokalariLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    jokalariLabel.setForeground(Color.WHITE);
	    jokalariPanel.add(jokalariLabel);
	    
	    JLabel jokalariInfo = new JLabel(jokalaria);
	    jokalariInfo.setHorizontalAlignment(JLabel.CENTER);
	    jokalariInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    jokalariInfo.setForeground(Color.RED);
	    jokalariPanel.add(jokalariInfo);
	    
	    estadisPanel.add(jokalariPanel);

	    
	    // DATA
	    JPanel dataPanel = new JPanel();
	    dataPanel.setLayout(new GridLayout(2,1,0,15));
	    dataPanel.setPreferredSize(new Dimension(120, 300));
	    dataPanel.setBackground(Color.BLACK);
	    
	    JLabel dataLabel = new JLabel("Data:");
	    dataLabel.setHorizontalAlignment(JLabel.CENTER);
	    dataLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    dataLabel.setForeground(Color.WHITE);
	    dataPanel.add(dataLabel);
	    
	    JLabel dataInfo = new JLabel(data);
	    dataInfo.setHorizontalAlignment(JLabel.CENTER);
	    dataInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    dataInfo.setForeground(Color.RED);
	    dataPanel.add(dataInfo);
	    
	    estadisPanel.add(dataPanel);
	    
	    
	    // EMAITZA
	    JPanel emaitzaPanel = new JPanel();
	    emaitzaPanel.setLayout(new GridLayout(2,1,0,15));
	    emaitzaPanel.setPreferredSize(new Dimension(120, 300));
	    emaitzaPanel.setBackground(Color.BLACK);
	    
	    JLabel emaitzaLabel = new JLabel("Emaitza:");
	    emaitzaLabel.setHorizontalAlignment(JLabel.CENTER);
	    emaitzaLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    emaitzaLabel.setForeground(Color.WHITE);
	    emaitzaPanel.add(emaitzaLabel);
	    
	    JLabel emaitzaInfo = new JLabel(emaitza);
	    emaitzaInfo.setHorizontalAlignment(JLabel.CENTER);
	    emaitzaInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    emaitzaInfo.setForeground(Color.RED);
	    emaitzaPanel.add(emaitzaInfo);
	    
	    estadisPanel.add(emaitzaPanel);
	    
	    
	    // ZAILTASUNA
	    JPanel zailtasunaPanel = new JPanel();
	    zailtasunaPanel.setLayout(new GridLayout(2,1,0,15));
	    zailtasunaPanel.setPreferredSize(new Dimension(120, 300));
	    zailtasunaPanel.setBackground(Color.BLACK);
	    
	    JLabel zailtasunaLabel = new JLabel("Zailtasuna:");
	    zailtasunaLabel.setHorizontalAlignment(JLabel.CENTER);
	    zailtasunaLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    zailtasunaLabel.setForeground(Color.WHITE);
	    zailtasunaPanel.add(zailtasunaLabel);
	    
	    JLabel zailtasunaInfo = new JLabel(zailtasuna);
	    zailtasunaInfo.setHorizontalAlignment(JLabel.CENTER);
	    zailtasunaInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    zailtasunaInfo.setForeground(Color.RED);
	    zailtasunaPanel.add(zailtasunaInfo);
	    
	    estadisPanel.add(zailtasunaPanel);
	    
	    
	    // PUNTUAZIOA
	    JPanel puntuazioaPanel = new JPanel();
	    puntuazioaPanel.setLayout(new GridLayout(2,1,0,15));
	    puntuazioaPanel.setPreferredSize(new Dimension(160, 300));
	    puntuazioaPanel.setBackground(Color.BLACK);
	    
	    JLabel puntuazioaLabel = new JLabel("Puntuazioa:");
	    puntuazioaLabel.setHorizontalAlignment(JLabel.CENTER);
	    puntuazioaLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    puntuazioaLabel.setForeground(Color.WHITE);
	    puntuazioaPanel.add(puntuazioaLabel);
	    
	    JLabel puntuazioaInfo = new JLabel(puntuazioa);
	    puntuazioaInfo.setHorizontalAlignment(JLabel.CENTER);
	    puntuazioaInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    puntuazioaInfo.setForeground(Color.RED);
	    puntuazioaPanel.add(puntuazioaInfo);
	    
	    estadisPanel.add(puntuazioaPanel);
	    
	    
	    // PARTIDA DENBORA
	    JPanel denboraPanel = new JPanel();
	    denboraPanel.setLayout(new GridLayout(2,1,0,15));
	    denboraPanel.setBackground(Color.BLACK);
	    
	    JLabel denboraLabel = new JLabel("Denbora:");
	    denboraLabel.setHorizontalAlignment(JLabel.CENTER);
	    denboraLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    denboraLabel.setForeground(Color.WHITE);
	    denboraPanel.add(denboraLabel);
	    
	    JLabel denboraInfo = new JLabel(denb);
	    denboraInfo.setHorizontalAlignment(JLabel.CENTER);
	    denboraInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    denboraInfo.setForeground(Color.RED);
	    denboraPanel.add(denboraInfo);
	    
	    estadisPanel.add(denboraPanel);
	    
	    
	    // HEGAZKIN ETA ETSAI MOTAK
	    JPanel motakPanel = new JPanel();
	    motakPanel.setLayout(new GridLayout(2,1,0,15));
	    motakPanel.setBackground(Color.BLACK);
	    
	    JLabel motakLabel = new JLabel("<html><center>Hegazkina:<br>-----<br>Etsaia:</center></html>");
	    motakLabel.setHorizontalAlignment(JLabel.CENTER);
	    motakLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    motakLabel.setForeground(Color.WHITE);
	    motakPanel.add(motakLabel);
	    
	    JLabel motakInfo = new JLabel("<html><center>" + hegazkinMota + "<br>-----<br>" + etsaiMota + "</center></html>");
	    motakInfo.setHorizontalAlignment(JLabel.CENTER);
	    motakInfo.setFont(new Font("Arial", Font.BOLD, 20));
	    motakInfo.setForeground(Color.RED);
	    motakPanel.add(motakInfo);
	    
	    estadisPanel.add(motakPanel);
	    
	    
	    panelOsoa.add(estadisPanel, BorderLayout.CENTER);
	}
	
	private void botoiakGehitu() {
		botoiakPanel = new JPanel();
		botoiakPanel.setLayout(new GridLayout(1,2,0,15));
		botoiakPanel.setPreferredSize(new Dimension(840, 150));
		botoiakPanel.setBackground(Color.BLACK);
		
		// ITZULI
		JButton itzuli = new JButton("ITZULI");
		itzuli.setFont(new Font("Arial", Font.BOLD, 22));
		itzuli.setForeground(Color.BLACK);	// Hizkien kolorea
	    botoiakPanel.add(itzuli);
	    
	    itzuli.addActionListener(e -> estadisFrame.setVisible(false));	// Botoia pultsatu		
		
		// PARTIDA GUZTIETAKO ESTADISTIKAK IKUSI
	    JButton estadistikak = new JButton("<html><center>PARTIDA GUZTIEN<br>ESTADISTIKAK IKUSI</center></html>");	// html erabiltzen dugu testua bi lerrotan jartzeko botoian
	    estadistikak.setFont(new Font("Arial", Font.BOLD, 22));
	    estadistikak.setForeground(Color.BLACK);	// Hizkien kolorea
	    botoiakPanel.add(estadistikak);
	    
	    estadistikak.addActionListener(e -> estadisFrame.setVisible(false));	// Botoia pultsatu
	    
	    
	    panelOsoa.add(botoiakPanel, BorderLayout.SOUTH);
	}
	
	private void azkenPartidaIrakurri() {
		List<String> partidak = JokoKudeatzailea.getEMA().getEstadistikak();
		
		if (partidak.isEmpty()) {
			// HUTSIK
		} else {
			String azkenLerroa = partidak.get(partidak.size() - 1);	// Fitxategiaren azken lerroa hartzeko; hau da, jokatu den azken partida
			String[] zatiak = azkenLerroa.split("#");
			
			jokalaria 		= zatiak[0];
			data 			= zatiak[1];
			emaitza			= zatiak[2];
			zailtasuna		= zatiak[3];
			puntuazioa		= zatiak[4];
			denb 			= zatiak[5];
			hegazkinMota 	= zatiak[6]; 
			etsaiMota 		= zatiak[7];
		}

	}
	
	public void bistaratu() {
		estadisFrame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if ("RESET".equals(arg)) {
			o.deleteObserver(this);	// "o" notifyObservers() mezua bidali duen Observable-a da, kasu hontan JokoKudeatzailea
			estadisFrame.dispose();
			nireEMA = null;
		}
	}
}
