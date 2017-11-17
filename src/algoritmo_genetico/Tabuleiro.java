package algoritmo_genetico;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algoritmo_genetico.Individuo;

public class Tabuleiro extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public Tabuleiro(Individuo individuo) {
		setTitle("Xadrez");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		int x = 10;
		int y = 11;
		boolean altere = false;
		JButton button;
		String nomeDaImagem;
		char[] individuoGenesChar;
		for (int i = 0; i < 8; i++) {
			x = 10;
			individuoGenesChar = new char[individuo.getGenes()[i].length];
			individuoGenesChar = individuo.getGenes()[i];
			for (int j = 0; j < 8; j++) {
				button = new JButton("");
				if (individuoGenesChar[j] == '0') {
					if (altere == false) {
						nomeDaImagem = "preto";
						altere = true;
					} else {
						nomeDaImagem = "branco";
						altere = false;
					}
				} else {

					if (altere == false) {
						nomeDaImagem = "pretoR";
						altere = true;
					} else {
						nomeDaImagem = "brancoR";
						altere = false;
					}

				}

				button.setIcon(new ImageIcon(Tabuleiro.class.getResource("/imagens/" + nomeDaImagem + ".png")));
				button.setBounds(x, y, 61, 44);
				contentPane.add(button);
				x += 60;
			}
			if (altere == false) {
				nomeDaImagem = "preto";
				altere = true;
			} else {
				nomeDaImagem = "branco";
				altere = false;
			}
			y += 44;
		}

	}
}
