package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import models.InsiaPoint;

@SuppressWarnings("serial")
public class PantallaInsia extends JPanel{
	
	private Image image, redPoint, upmIcon;
	private Toolkit toolkit;
	private InsiaPoint imgPoint;
	private Dimension size;
	
	public PantallaInsia(String imageURL){
		this.toolkit = Toolkit.getDefaultToolkit();
		this.image = toolkit.getImage(imageURL);
		this.redPoint = toolkit.getImage("assets/red_point.jpg");
		this.upmIcon = toolkit.getImage("assets/upm.jpg");
		this.size = toolkit.getScreenSize();
		this.setVisible(true);
		this.setBounds(0, 0, size.width, size.height);
		this.setBackground(new Color(0.972f,0.972f,0.972f));
		this.setLayout(null);
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		int widthAdapt = (size.width / 2) - (image.getWidth(this) / 2);
		int heightAdapt = (size.height / 2) - (image.getHeight(this) / 2);
		graphics.drawImage(image, widthAdapt, heightAdapt, this);
		graphics.drawImage(upmIcon, 0, 0, this);

		if (imgPoint != null) {
			graphics.drawImage(redPoint, imgPoint.getXimg() + widthAdapt - (redPoint.getWidth(this)/2),
					imgPoint.getYimg() + heightAdapt - (redPoint.getHeight(this)/2), this);
		}
	}

	public void setImgPoint(InsiaPoint imgPoint) {
		this.imgPoint = imgPoint;
	}
}
