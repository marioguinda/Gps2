package app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import gui.PantallaCampus;
import gui.PantallaInsia;
import gui.PantallaPrincipal;

public class App implements ActionListener {

	private JFrame frame;
	private PantallaCampus pantallaCampus;
	private PantallaInsia pantallaInsia;
	private PantallaPrincipal indexPanel;
	private JComboBox<String> combo;
	private JButton close;
	private Dimension size;
	boolean insia;

	public void startGUI() throws InterruptedException {
		size = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(size);
		frame.setUndecorated(true);
		frame.setVisible(true);

		close = new JButton("Salir");
		close.addActionListener(this);
		close.setBounds(size.width - 155, 30, 150, 30);

		String[] mapas = { "Mapa del Campus", "Mapa del Insia" };
		combo = new JComboBox<String>(mapas);
		combo.addActionListener(this);
		combo.setBounds((size.width / 2) - 75, size.height / 2, 150, 30);

		pantallaCampus = new PantallaCampus("assets/Recorte.jpg");
		pantallaInsia = new PantallaInsia("assets/RecorteInsia.jpg");
		indexPanel = new PantallaPrincipal("assets/upm.jpg");

		indexPanel.add(combo);
		indexPanel.add(close);

		frame.setContentPane(indexPanel);

		GPS.start();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == combo) {
			String selected = (String) combo.getSelectedItem();
			indexPanel.removeAll();
			frame.getContentPane().removeAll();
			combo.setBounds((size.width / 2) - 75, 30, 150, 30);
			if (selected.compareTo("Mapa del Campus") == 0) {
				insia = false;
				pantallaCampus.add(close);
				pantallaCampus.add(combo);
				frame.setContentPane(pantallaCampus);
				pantallaCampus.repaint();
			} else {
				insia = true;
				pantallaInsia.add(close);
				pantallaInsia.add(combo);
				frame.setContentPane(pantallaInsia);
				pantallaInsia.repaint();
			}
		} else if (e.getSource() == close) {
			System.exit(0);
		}
	}

}
