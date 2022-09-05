package Proiect;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame fundal = new JFrame();
		Mechanics game = new Mechanics();
		fundal.setSize(800, 900);
		fundal.setTitle("Sah");
		fundal.setResizable(false);
		fundal.add(game);
		fundal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fundal.setVisible(true);
		
	}

}
