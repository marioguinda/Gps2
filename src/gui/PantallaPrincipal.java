package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PantallaPrincipal extends JPanel{
	private Toolkit toolkit;
	private Dimension size;
	
	public PantallaPrincipal(String imageURL){
		this.toolkit = Toolkit.getDefaultToolkit();
		this.size = toolkit.getScreenSize();
		this.setVisible(true);
		this.setBounds(0, 0, size.width, size.height);
		this.setBackground(new Color(0.999f,0.999f,0.999f));
		this.setLayout(null);
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		int widthAdapt = (size.width / 2) - (title.getWidth(this) / 2);
		int heightAdapt = (size.height / 4) - (title.getHeight(this) / 2);
		graphics.drawImage(upmIcon, 0, 0, this);
		graphics.drawImage(title, widthAdapt, heightAdapt, this);
	}	
}
