package app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hra.HraciPlocha;

public class FlappyPtak extends JFrame{
	
	static final long serialVersionUID = 1L;
	private HraciPlocha hp;
	
	public FlappyPtak() {
		hp = new HraciPlocha();
		getContentPane().add(hp, "Center");
		
		pack();
	}
	public void initGUI() {
		setSize(HraciPlocha.SIRKA, HraciPlocha.VYSKA);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("FlappyPtak");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void spust(){
		hp = new HraciPlocha();
		getContentPane().add(hp, "Center");
		hp.setVisible(true);
		this.revalidate();
		hp.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FlappyPtak app = new FlappyPtak();
				app.initGUI();
				app.spust();
			}
		});
	}

}
