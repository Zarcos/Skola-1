package hra;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import obrazek.Obrazek;
import obrazek.ZdrojObrazkuSoubor;

public class HraciPlocha extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int VYSKA = 800;
	public static final int SIRKA = 600;
	public static final int RYCHLOST = -2;
	
	private BufferedImage imgPozadi;
	private Timer casovacAnimace;
	private boolean pauza = false;
	private boolean hraBezi = false;
	private int posunPozadi = 0;
	
	public HraciPlocha() {
		ZdrojObrazkuSoubor z = new ZdrojObrazkuSoubor();
		z.naplnMapu();
		z.setZdroj(Obrazek.POZADI.getKlic());
		
		try {
			imgPozadi = z.getObrazek();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		//prvni obrazek
		g.drawImage(imgPozadi, posunPozadi, 0, null);
		//druhy obrazek posunuty
		g.drawImage(imgPozadi, posunPozadi+imgPozadi.getWidth(), 0, null);
	}
	
	public void posun() {
		if(!pauza && hraBezi) {
			posunPozadi = posunPozadi + HraciPlocha.RYCHLOST;
			
			if(posunPozadi == imgPozadi.getWidth()) {
				posunPozadi = 0;
			}
		}
	}
	private void spustHru() {
		casovacAnimace = new Timer(20, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				posun();
			}
		});
		hraBezi = true;
		casovacAnimace.start();
	}
	
	public void pripravHraciPlochu() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON1){
					//TODO skok hrace
				}
				//pauza
				if(e.getButton()==MouseEvent.BUTTON3){
					if (hraBezi) {
						if(pauza){
							pauza = false;
						}
						else {
							pauza = true;
						}
					} else {
						pripravNovouHru();
						spustHru();
					}
				}
			}
			
		});
		setSize(SIRKA, VYSKA);
	}

	protected void pripravNovouHru() {
		
	}
}
