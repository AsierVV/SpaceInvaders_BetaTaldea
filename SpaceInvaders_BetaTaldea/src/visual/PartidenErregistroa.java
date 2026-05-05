package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modeloa.JokoKudeatzailea;

public class PartidenErregistroa extends JPanel{

	private JFrame estadisFrame;
	private JPanel panelOsoa;
	private JTable taula;
	private DefaultTableModel modeloTaula;
	private JScrollPane scrollPane;
	
	private static PartidenErregistroa nireEMA = null;
	
	private PartidenErregistroa() {
		estadisFrame = new JFrame("PARTIDEN ERREGISTROA ESTADISTIKAK");

		panelOsoa = new JPanel();
		panelOsoa.setLayout(new BorderLayout(0, 10));
		panelOsoa.setPreferredSize(new Dimension(1400, 600));
		panelOsoa.setBackground(Color.BLACK);
		panelOsoa.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		String[] zutabeakTaula = {"Partida","Jokalaria","Data","Emaitza","Zailtasuna","Puntuazioa","Denbora","Hegazkina","Etsaia"};
		modeloTaula = new DefaultTableModel(zutabeakTaula, 0) {// Lerroak eta informazioa gordetzeko
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;	// Taula editatu ezin izateko
			}
		};
		
		taula = new JTable(modeloTaula);	// modeloTaula dituen datuak bistaratzeko
		taula.setFont(new Font("Arial", Font.PLAIN, 16));
		taula.setRowHeight(28);	// Lerro bakoitzaren altuera
		taula.setBackground(Color.BLACK);
		taula.setForeground(Color.WHITE);
		taula.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		taula.getTableHeader().setReorderingAllowed(false);	// Taulako zutabeak mugitu ezin izateko
		
		scrollPane = new JScrollPane(taula);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelOsoa.add(scrollPane, BorderLayout.CENTER);
		
		JPanel botoiPanel = new JPanel();
		botoiPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		botoiPanel.setPreferredSize(new Dimension(1400, 50));
		botoiPanel.setBackground(Color.BLACK);
		botoiPanel.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		
		JButton itzuli = new JButton("ITZULI");
		itzuli.setPreferredSize(new Dimension(400,40));
		itzuli.setFont(new Font("Arial", Font.BOLD, 22));
		itzuli.setForeground(Color.BLACK);	// Hizkien kolorea
	    botoiPanel.add(itzuli);
	    
	    itzuli.addActionListener(e -> estadisFrame.setVisible(false));	// Botoia pultsatu		

	    panelOsoa.add(botoiPanel, BorderLayout.SOUTH);
		
		estadisFrame.getContentPane().add(panelOsoa);
		estadisFrame.pack();
		estadisFrame.setLocationRelativeTo(null);
		estadisFrame.setVisible(false);
	}
	
	public static PartidenErregistroa getEMA() {
		if (nireEMA == null) nireEMA = new PartidenErregistroa();
		return nireEMA;
	}

	private void taulaEguneratu() {
		modeloTaula.setRowCount(0);	// Lerro guztiak borratzeko taula eguneratu aurretik

		List<String> partidak = JokoKudeatzailea.getEMA().getEstadistikak();

		int i = 1;

		for (String lerroa : partidak) {
			String[] zatiak = lerroa.split("#");

			if (zatiak.length >= 8) {
				Object[] fila = {
					i,
					zatiak[0],
					zatiak[1],
					zatiak[2],
					zatiak[3],
					zatiak[4],
					zatiak[5],
					zatiak[6],
					zatiak[7]
				};

				modeloTaula.addRow(fila);	// Lerro berria gehitzen du taulan
				i++;
			}
		}
	}
	
	public void bistaratu() {
		taulaEguneratu();
		estadisFrame.setVisible(true);
	}
	
}