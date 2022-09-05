package Proiect;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Mechanics extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	String pozPiese[][] = new String[8][8];
	int[][] mutariPosibile = new int[8][8];
	private int click_x = 0;
	private int click_y = 0;

	private int afisare_mutari = 0;
	private int mutam_piesa = 0;
	private int rand_jucator = 0;
	
	private int verificare_sah = 0;
	private int sah_mat = 0;
	private int game_over = 0;
	
	private int L_click_x = 0;
	private int L_click_y = 0;

	public void InitializeMap() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				mutariPosibile[i][j] = 0;
				pozPiese[i][j] = " ";
			}
		for (int i = 0; i < 8; i++) {
			pozPiese[1][i] = "\u2659";
			mutariPosibile[1][i] = 1;
			pozPiese[6][i] = "\u265F";
			mutariPosibile[6][i] = -1;
		}

		// alb

		pozPiese[0][0] = "\u2656";
		pozPiese[0][7] = "\u2656";
		mutariPosibile[0][0] = 2;
		mutariPosibile[0][7] = 2;

		pozPiese[0][1] = "\u2658";
		pozPiese[0][6] = "\u2658";
		mutariPosibile[0][1] = 3;
		mutariPosibile[0][6] = 3;

		pozPiese[0][2] = "\u2657";
		pozPiese[0][5] = "\u2657";
		mutariPosibile[0][2] = 4;
		mutariPosibile[0][5] = 4;

		pozPiese[0][3] = "\u2655";
		pozPiese[0][4] = "\u2654";
		mutariPosibile[0][3] = 6;
		mutariPosibile[0][4] = 5;

		// negru

		pozPiese[7][0] = "\u265C";
		pozPiese[7][7] = "\u265C";
		mutariPosibile[7][0] = -2;
		mutariPosibile[7][7] = -2;

		pozPiese[7][1] = "\u265E";
		pozPiese[7][6] = "\u265E";
		mutariPosibile[7][1] = -3;
		mutariPosibile[7][6] = -3;

		pozPiese[7][2] = "\u265D";
		pozPiese[7][5] = "\u265D";
		mutariPosibile[7][2] = -4;
		mutariPosibile[7][5] = -4;

		pozPiese[7][3] = "\u265B";
		pozPiese[7][4] = "\u265A";
		mutariPosibile[7][3] = -6;
		mutariPosibile[7][4] = -5;
	}

	public Mechanics() {
		this.addMouseListener(this); // listen to mouse events
		InitializeMap();
	}

	private void paintPiese(Graphics g) {
		int lung = 96;
		int lat = 93;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (pozPiese[i][j] != " ") {
					if (pozPiese[i][j].compareTo("\u265A") >= 0)
						g.setColor(Color.BLACK);
					else
						g.setColor(Color.WHITE);
					g.setFont(new Font("serif", Font.BOLD, 50));
					g.drawString(pozPiese[i][j], lung * (i) + 25, lat * (j) + 70);
				}
	}

	private void paintTabla(Graphics g) {
		int lung = 96;
		int lat = 93;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					g.setColor(Color.LIGHT_GRAY);
				} else {
					g.setColor(Color.DARK_GRAY);
				}
				g.fillRect(i * lung + 8, j * lat + 8, lung, lat);
			}
	}

	public void paint(Graphics g) {
		// background

		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 800, 900);

		// margini

		g.setColor(Color.RED);
		g.fillRect(0, 0, 800, 3); // sus
		g.fillRect(0, 758, 800, 10); // jos
		g.fillRect(0, 858, 800, 10); // jos
		g.fillRect(0, 0, 3, 900); // stanga
		g.fillRect(781, 0, 3, 900); // dreapta

		paintTabla(g);

		paintPiese(g);
		
		if(rand_jucator == 0)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Jucator Alb", 280, 820);
		}
		else
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Jucator Negru",  280, 820);
		}
		if(verificare_sah == 1)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Sah",  120, 820);
		}
		
		if(sah_mat == 1)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.BOLD, 40));
			if(rand_jucator == 0)
				g.drawString("a Castigat !", 500, 820);
			else
				g.drawString("a Castigat !", 540, 820);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	private int verificam_sah_rege()
	{
		return 0;
	}
	
	private int mutare_permisa() {
		int ok = 0;
		if(mutariPosibile[L_click_x][L_click_y] < 0 && rand_jucator == 0)
			return 0;
		if(mutariPosibile[L_click_x][L_click_y] > 0 && rand_jucator == 1)
			return 0;
		switch (mutariPosibile[L_click_x][L_click_y]) {
		case 1:
			ok = mutare_pion_alb();
			break;
		case 2:
			ok = mutare_tura_alb();
			break;
		case 3:
			ok = mutare_cal_alb();
			break;
		case 4:
			ok = mutare_nebun_alb();
			break;
		case 5:
			ok = mutare_rege_alb();
			break;
		case 6:
			ok = mutare_regina_alba();
			break;
		case -1:
			ok = mutare_pion_negru();
			break;
		case -2:
			ok = mutare_tura_negru();
			break;
		case -3:
			ok = mutare_cal_negru();
			break;
		case -4:
			ok = mutare_nebun_negru();
			break;
		case -5:
			ok = mutare_rege_negru();
			break;
		case -6:
			ok = mutare_regina_negru();
			break;
		}
		if(ok == 1)
		{
			rand_jucator = (rand_jucator+1)%2;
		}
		return ok;
	}

	private int mutare_regina_negru() {
		// TODO Auto-generated method stub
		System.out.println("Regina Alba --> Mutare");
		if (mutariPosibile[click_x][click_y] < 0)
			return 0;
		if (click_x == L_click_x || click_y == L_click_y) {
			if (click_x == L_click_x)
			{
				if (click_y > L_click_y) {
					int i = click_y - 1;
					while (i > L_click_y) {
						if (mutariPosibile[click_x][i] != 0)
							return 0;
						i--;
					}
				}
				if (click_y < L_click_y) {
					int i = click_y + 1;
					while (i < L_click_y) {
						if (mutariPosibile[click_x][i] != 0)
							return 0;
						i++;
					}
				}
			}
			if (click_y == L_click_y) {

				if (click_x > L_click_x) {
					int i = click_x - 1;
					while (i > L_click_x) {
						if (mutariPosibile[i][click_y] != 0)
							return 0;
						i--;
					}
				}
				if (click_x < L_click_x) {
					int i = click_x + 1;
					while (i < L_click_x) {
						if (mutariPosibile[i][click_y] != 0)
							return 0;
						i++;
					}
				}
			}
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = -6;
			return 1;
		}
		int ok = 0;
		if (click_x < L_click_x && click_y < L_click_y) {
			int i = click_x + 1;
			int j = click_y + 1;
			while (i < L_click_x && j < L_click_y && i < 8 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y > L_click_y) {
			int i = click_x - 1;
			int j = click_y - 1;
			while (i > L_click_x && j > L_click_y && i >= 0 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x < L_click_x && click_y > L_click_y) {
			int i = click_x + 1;
			int j = click_y - 1;
			while (i < L_click_x && j > L_click_y && i >= 0 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y < L_click_y) {
			int i = click_x - 1;
			int j = click_y + 1;
			while (i > L_click_x && j < L_click_y && i < 8 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (ok == 1) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = -6;
			return 1;
		}
		return 0;
	}

	private int mutare_rege_negru() {
		// TODO Auto-generated method stub
		System.out.println("Rege Negru --> mutare");
		if (mutariPosibile[click_x][click_y] < 0) {
			return 0;
		}
		if ((click_x == L_click_x && click_y == L_click_y + 1) || (click_x == L_click_x && click_y == L_click_y - 1)
				|| (click_x == L_click_x + 1 && click_y == L_click_y)
				|| (click_x == L_click_x - 1 && click_y == L_click_y)) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = -5;
			return 1;
		}
		if ((click_x == L_click_x + 1 && click_y == L_click_y + 1)
				|| (click_x == L_click_x + 1 && click_y == L_click_y - 1)
				|| (click_x == L_click_x - 1 && click_y == L_click_y + 1)
				|| (click_x == L_click_x - 1 && click_y == L_click_y - 1)) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = -5;
			return 1;
		}
		return 0;
	}

	private int mutare_nebun_negru() {
		// TODO Auto-generated method stub
		System.out.println("Nebun Negru --> mutare");
		if (mutariPosibile[click_x][click_y] < 0) {
			return 0;
		}
		if (((click_x + click_y) % 2) != ((L_click_x + L_click_y) % 2)) {
			return 0;
		}
		int ok = 0;
		if (click_x < L_click_x && click_y < L_click_y) {
			int i = click_x + 1;
			int j = click_y + 1;
			while (i < L_click_x && j < L_click_y && i < 8 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y > L_click_y) {
			int i = click_x - 1;
			int j = click_y - 1;
			while (i > L_click_x && j > L_click_y && i >= 0 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x < L_click_x && click_y > L_click_y) {
			int i = click_x + 1;
			int j = click_y - 1;
			while (i < L_click_x && j > L_click_y && i >= 0 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y < L_click_y) {
			int i = click_x - 1;
			int j = click_y + 1;
			while (i > L_click_x && j < L_click_y && i < 8 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (ok == 1) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = -4;
			return 1;
		}
		return 0;
	}

	private int mutare_cal_negru() {
		// TODO Auto-generated method stub
		System.out.println("Se face");
		if (mutariPosibile[click_x][click_y] < 0) {
			return 0;
		}
		if ((click_x - 2 == L_click_x && click_y - 1 == L_click_y)
				|| (click_x - 2 == L_click_x && click_y + 1 == L_click_y)
				|| (click_x - 1 == L_click_x && click_y - 2 == L_click_y)
				|| (click_x - 1 == L_click_x && click_y + 2 == L_click_y)
				|| (click_x + 2 == L_click_x && click_y - 1 == L_click_y)
				|| (click_x + 2 == L_click_x && click_y + 1 == L_click_y)
				|| (click_x + 1 == L_click_x && click_y - 2 == L_click_y)
				|| (click_x + 1 == L_click_x && click_y + 2 == L_click_y)) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = -3;
			return 1;
		}
		return 0;
	}

	private int mutare_tura_negru() {
		// TODO Auto-generated method stub
		System.out.println("Se face");
		System.out.println(click_x + " " + click_y + " , " + L_click_x + " " + L_click_y + " "
				+ mutariPosibile[click_x][click_y] + " ");
		if (mutariPosibile[click_x][click_y] < 0)
			return 0;
		if (click_x != L_click_x && click_y != L_click_y)
			return 0;
		if (click_x == L_click_x) {
			if (click_y > L_click_y) {
				int i = click_y - 1;
				while (i > L_click_y) {
					if (mutariPosibile[click_x][i] != 0)
						return 0;
					i--;
				}
			}
			if (click_y < L_click_y) {
				int i = click_y + 1;
				while (i < L_click_y) {
					if (mutariPosibile[click_x][i] != 0)
						return 0;
					i++;
				}
			}
		}
		if (click_y == L_click_y) {
			if (click_x > L_click_x) {
				int i = click_x - 1;
				while (i > L_click_x) {
					if (mutariPosibile[i][click_y] != 0)
						return 0;
					i--;
				}
			}
			if (click_x < L_click_x) {
				int i = click_x + 1;
				while (i < L_click_x) {
					if (mutariPosibile[i][click_y] != 0)
						return 0;
					i++;
				}
			}
		}
		mutariPosibile[L_click_x][L_click_y] = 0;
		mutariPosibile[click_x][click_y] = -2;
		return 1;
	}

	private int mutare_pion_negru() {
		// TODO Auto-generated method stub
		System.out.println(click_x + " " + click_y + " : " + mutariPosibile[click_x][click_y]);
		System.out.println(L_click_x + " " + L_click_y + " : " + mutariPosibile[L_click_x][L_click_y] + " , ");
		// System.out.println(mutariPosibile[L_click_x+1][L_click_y] + " , " +
		// mutariPosibile[L_click_x+2][L_click_y] + " , " +
		// mutariPosibile[L_click_x+1][L_click_y+1] + " , " +
		// mutariPosibile[L_click_x+1][L_click_y-1] + " , ");
		// TODO Auto-generated method stub
		int ok = 0;
		if (click_x + 1 < 8)
			if (mutariPosibile[click_x + 1][click_y] == -1 && mutariPosibile[click_x][click_y] == 0
					&& click_x + 1 == L_click_x && click_y == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = -1;
				ok = 1;
			}
		if (click_x + 2 < 8)
			if (mutariPosibile[click_x + 2][click_y] == -1 && mutariPosibile[click_x][click_y] == 0 && L_click_x == 6
					&& click_x + 2 == L_click_x && click_y == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = -1;
				ok = 1;
			}
		if (click_x + 1 < 8 && click_y + 1 < 8)
			if (mutariPosibile[click_x + 1][click_y + 1] == -1 && mutariPosibile[click_x][click_y] > 0
					&& click_x + 1 == L_click_x && click_y + 1 == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = -1;
				ok = 1;
			}
		if (click_x + 1 < 8 && click_y - 1 >= 0)
			if (mutariPosibile[click_x + 1][click_y - 1] == -1 && mutariPosibile[click_x][click_y] > 0
					&& click_x + 1 == L_click_x && click_y - 1 == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = -1;
				ok = 1;
			}
		return ok;
	}

	private int mutare_regina_alba() {
		// TODO Auto-generated method stub
		System.out.println("Regina Alba --> Mutare");
		if (mutariPosibile[click_x][click_y] > 0)
			return 0;
		if (click_x == L_click_x || click_y == L_click_y) {
			if (click_x == L_click_x)
			{
				if (click_y > L_click_y) {
					int i = click_y - 1;
					while (i > L_click_y) {
						if (mutariPosibile[click_x][i] != 0)
							return 0;
						i--;
					}
				}
				if (click_y < L_click_y) {
					int i = click_y + 1;
					while (i < L_click_y) {
						if (mutariPosibile[click_x][i] != 0)
							return 0;
						i++;
					}
				}
			}
			if (click_y == L_click_y) {

				if (click_x > L_click_x) {
					int i = click_x - 1;
					while (i > L_click_x) {
						if (mutariPosibile[i][click_y] != 0)
							return 0;
						i--;
					}
				}
				if (click_x < L_click_x) {
					int i = click_x + 1;
					while (i < L_click_x) {
						if (mutariPosibile[i][click_y] != 0)
							return 0;
						i++;
					}
				}
			}
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = 6;
			return 1;
		}
		int ok = 0;
		if (click_x < L_click_x && click_y < L_click_y) {
			int i = click_x + 1;
			int j = click_y + 1;
			while (i < L_click_x && j < L_click_y && i < 8 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y > L_click_y) {
			int i = click_x - 1;
			int j = click_y - 1;
			while (i > L_click_x && j > L_click_y && i >= 0 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x < L_click_x && click_y > L_click_y) {
			int i = click_x + 1;
			int j = click_y - 1;
			while (i < L_click_x && j > L_click_y && i >= 0 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y < L_click_y) {
			int i = click_x - 1;
			int j = click_y + 1;
			while (i > L_click_x && j < L_click_y && i < 8 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (ok == 1) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = 6;
			return 1;
		}
		return 0;
	}

	private int mutare_rege_alb() {
		// TODO Auto-generated method stub
		System.out.println("Rege Alba --> mutare");
		if (mutariPosibile[click_x][click_y] > 0) {
			return 0;
		}
		if ((click_x == L_click_x && click_y == L_click_y + 1) || (click_x == L_click_x && click_y == L_click_y - 1)
				|| (click_x == L_click_x + 1 && click_y == L_click_y)
				|| (click_x == L_click_x - 1 && click_y == L_click_y)) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = 5;
			return 1;
		}
		if ((click_x == L_click_x + 1 && click_y == L_click_y + 1)
				|| (click_x == L_click_x + 1 && click_y == L_click_y - 1)
				|| (click_x == L_click_x - 1 && click_y == L_click_y + 1)
				|| (click_x == L_click_x - 1 && click_y == L_click_y - 1)) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = 5;
			return 1;
		}
		return 0;
	}

	private int mutare_nebun_alb() {
		// TODO Auto-generated method stub
		System.out.println("Nebun Alb --> mutare");
		if (mutariPosibile[click_x][click_y] > 0) {
			return 0;
		}
		if (((click_x + click_y) % 2) != ((L_click_x + L_click_y) % 2)) {
			return 0;
		}
		int ok = 0;
		if (click_x < L_click_x && click_y < L_click_y) {
			int i = click_x + 1;
			int j = click_y + 1;
			while (i < L_click_x && j < L_click_y && i < 8 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y > L_click_y) {
			int i = click_x - 1;
			int j = click_y - 1;
			while (i > L_click_x && j > L_click_y && i >= 0 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x < L_click_x && click_y > L_click_y) {
			int i = click_x + 1;
			int j = click_y - 1;
			while (i < L_click_x && j > L_click_y && i >= 0 && j < 8) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i++;
				j--;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (click_x > L_click_x && click_y < L_click_y) {
			int i = click_x - 1;
			int j = click_y + 1;
			while (i > L_click_x && j < L_click_y && i < 8 && j >= 0) {
				if (mutariPosibile[i][j] != 0)
					return 0;
				i--;
				j++;
			}
			if (i == L_click_x && j == L_click_y)
				ok = 1;
		}
		if (ok == 1) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = 4;
			return 1;
		}
		return 0;
	}

	private int mutare_cal_alb() {
		// TODO Auto-generated method stub
		System.out.println("Cal Alb --> mutare");
		if (mutariPosibile[click_x][click_y] > 0) {
			return 0;
		}
		if ((click_x - 2 == L_click_x && click_y - 1 == L_click_y)
				|| (click_x - 2 == L_click_x && click_y + 1 == L_click_y)
				|| (click_x - 1 == L_click_x && click_y - 2 == L_click_y)
				|| (click_x - 1 == L_click_x && click_y + 2 == L_click_y)
				|| (click_x + 2 == L_click_x && click_y - 1 == L_click_y)
				|| (click_x + 2 == L_click_x && click_y + 1 == L_click_y)
				|| (click_x + 1 == L_click_x && click_y - 2 == L_click_y)
				|| (click_x + 1 == L_click_x && click_y + 2 == L_click_y)) {
			mutariPosibile[L_click_x][L_click_y] = 0;
			mutariPosibile[click_x][click_y] = 3;
			return 1;
		}
		return 0;
	}

	private int mutare_tura_alb() {
		// TODO Auto-generated method stub
		System.out.println("Tura Alb --> Mutare");
		System.out.println(click_x + " " + click_y + " , " + L_click_x + " " + L_click_y + " "
				+ mutariPosibile[click_x][click_y] + " ");
		if (mutariPosibile[click_x][click_y] > 0)
			return 0;
		if (click_x != L_click_x && click_y != L_click_y)
			return 0;
		if (click_x == L_click_x) {

			if (click_y > L_click_y) {
				int i = click_y - 1;
				while (i > L_click_y) {
					if (mutariPosibile[click_x][i] != 0)
						return 0;
					i--;
				}
			}
			if (click_y < L_click_y) {
				int i = click_y + 1;
				while (i < L_click_y) {
					if (mutariPosibile[click_x][i] != 0)
						return 0;
					i++;
				}
			}
		}
		if (click_y == L_click_y) {

			if (click_x > L_click_x) {
				int i = click_x - 1;
				while (i > L_click_x) {
					if (mutariPosibile[i][click_y] != 0)
						return 0;
					i--;
				}
			}
			if (click_x < L_click_x) {
				int i = click_x + 1;
				while (i < L_click_x) {
					if (mutariPosibile[i][click_y] != 0)
						return 0;
					i++;
				}
			}
		}
		mutariPosibile[L_click_x][L_click_y] = 0;
		mutariPosibile[click_x][click_y] = 2;
		return 1;
	}

	private int mutare_pion_alb() {
		System.out.println(click_x + " " + click_y + " : " + mutariPosibile[click_x][click_y]);
		System.out.println(L_click_x + " " + L_click_y + " : " + mutariPosibile[L_click_x][L_click_y] + " , ");
		// System.out.println(mutariPosibile[L_click_x+1][L_click_y] + " , " +
		// mutariPosibile[L_click_x+2][L_click_y] + " , " +
		// mutariPosibile[L_click_x+1][L_click_y+1] + " , " +
		// mutariPosibile[L_click_x+1][L_click_y-1] + " , ");
		// TODO Auto-generated method stub
		int ok = 0;
		if (click_x - 1 >= 0)
			if (mutariPosibile[click_x - 1][click_y] == 1 && mutariPosibile[click_x][click_y] == 0
					&& click_x - 1 == L_click_x && click_y == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = 1;
				ok = 1;
			}
		if (click_x - 2 >= 0)
			if (mutariPosibile[click_x - 2][click_y] == 1 && mutariPosibile[click_x][click_y] == 0 && L_click_x == 1
					&& click_x - 2 == L_click_x && click_y == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = 1;
				ok = 1;
			}
		if (click_x - 1 >= 0 && click_y - 1 >= 0)
			if (mutariPosibile[click_x - 1][click_y - 1] == 1 && mutariPosibile[click_x][click_y] < 0
					&& click_x - 1 == L_click_x && click_y - 1 == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = 1;
				ok = 1;
			}
		if (click_x - 1 >= 0 && click_y + 1 < 8)
			if (mutariPosibile[click_x - 1][click_y + 1] == 1 && mutariPosibile[click_x][click_y] < 0
					&& click_x - 1 == L_click_x && click_y + 1 == L_click_y) {
				mutariPosibile[L_click_x][L_click_y] = 0;
				mutariPosibile[click_x][click_y] = 1;
				ok = 1;
			}
		return ok;
	}

	private void prelucrare_click() {
		int lung = 96;
		int lat = 93;

		int i;
		for (i = 7; i >= 0; i--) {
			if (click_x >= i * lung) {
				click_x = i;
				break;
			}
		}

		int j;
		for (j = 7; j >= 0; j--) {
			if (click_y >= j * lat) {
				click_y = j;
				break;
			}
		}

		if (mutam_piesa == 0) {
			if (pozPiese[click_x][click_y] != " ") {
				mutam_piesa = 1;
			}
		} else {
			if (mutare_permisa() == 1) {
				pozPiese[click_x][click_y] = pozPiese[L_click_x][L_click_y];
				pozPiese[L_click_x][L_click_y] = " ";
			}
			mutam_piesa = 0;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		L_click_x = click_x;
		L_click_y = click_y;

		click_x = e.getX();
		click_y = e.getY();

		prelucrare_click();

		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
