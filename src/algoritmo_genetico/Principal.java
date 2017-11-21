package algoritmo_genetico;

import java.util.Random;
import java.util.Scanner;


public class Principal {
	private static final int NUMERO_DE_RAINHAS = 8;

	private static Random random = new Random();

	public static void main(String[] args) {

		Individuo individuos[] = new Individuo[5];
		int indice=0;
		int indice2;
		
		//int delete=0;
		for (int i = 0; i < individuos.length; i++) {
			individuos[i] = new Individuo(gerandoElementos());
			individuos[i].setNumeroDeConflitos(conflitos(individuos[i]));
			individuos[i].setFitnnes(funcaoFitness(individuos[i]));
		}
		

		Tabuleiro frame = new Tabuleiro(individuos[selecaoDoMelhor(individuos)]);
		frame.setVisible(true);	
		
		
		tudo:while (true) {
			for (int i = 0; i < individuos.length; i++) {
				if (individuos[i].getNumeroDeConflitos() == 0) {
					indice = i;
					break tudo;
				}
			}
			calculandoProbabilidade(individuos);

			
			Individuo[] novosIndividuo = new Individuo[5];

			Crossover c;
			for (int i = 0; i < novosIndividuo.length; ) {
				
				
				if(i==0){
					indice = selecaoDoMelhor(individuos);
					novosIndividuo[0] = new Individuo(individuos[indice].getGenes());
					novosIndividuo[0].setNumeroDeConflitos(conflitos(novosIndividuo[0]));
					novosIndividuo[0].setFitnnes(funcaoFitness(novosIndividuo[0]));
					i++;
					continue;
				}
				indice = selecao(individuos);
				indice2 = selecao(individuos);
				while(indice == indice2){
					indice2 = selecao(individuos);
				}
				c = new Crossover(individuos[indice], individuos[indice2]);
				novosIndividuo[i] = c.getNovo1();
				novosIndividuo[i].setNumeroDeConflitos(conflitos(novosIndividuo[i]));
				novosIndividuo[i].setFitnnes(funcaoFitness(novosIndividuo[i]));
				novosIndividuo[i + 1] = c.getNovo2();
				novosIndividuo[i + 1].setNumeroDeConflitos(conflitos(novosIndividuo[i + 1]));
				novosIndividuo[i + 1].setFitnnes(funcaoFitness(novosIndividuo[i + 1]));
				i += 2;
			}
			calculandoProbabilidade(novosIndividuo);
			// Multação
			double rand = random.nextDouble();
			if (rand <= 0.2) {
				System.out.println("Multou");
				indice = random.nextInt(novosIndividuo.length);
				novosIndividuo[indice] = new Multacao(novosIndividuo[indice]).multando1();
				novosIndividuo[indice].setNumeroDeConflitos(conflitos(novosIndividuo[indice]));
				novosIndividuo[indice].setFitnnes(funcaoFitness(novosIndividuo[indice]));
				//new Scanner(System.in).nextLine();
			}
			
			individuos = novosIndividuo;
			
			calculandoProbabilidade(individuos);
			
			for (int i = 0; i < individuos.length; i++) {
				System.out.println(individuos[i].toString());
			}
			System.out.println("_______________________");
			
			
		}
		System.out.println("____________________Resposta____________________");
		for (int i = 0; i < individuos[indice].getGenes().length; i++) {
			for (int j = 0; j < individuos[indice].getGenes()[0].length; j++) {
				System.out.print(individuos[indice].getGenes()[i][j]+" ");
			}
			System.out.println();
		}
		Tabuleiro frame2 = new Tabuleiro(individuos[indice]);
		frame2.setVisible(true);	
	}

	public static char[][] gerandoElementos() {
		char[] linha;
		int i = 0;
		int j = 0;
		int indiceDoUM;
		char[][] genes = new char[NUMERO_DE_RAINHAS][NUMERO_DE_RAINHAS];
		for (i = 0; i < NUMERO_DE_RAINHAS; i++) {
			linha = new char[NUMERO_DE_RAINHAS];
			indiceDoUM = random.nextInt(NUMERO_DE_RAINHAS);
			linha[indiceDoUM] = '1';
			for (j = 0; j < linha.length; j++) {
				if (linha[j] == '1') {
					continue;
				} else {
					linha[j] = '0';
				}
			}
			genes[i] = linha;
		}

		return genes;
	}

	public static byte conflitos(Individuo ind) {

		// teste vertical
		char[] linhaAtual;
		char[] linhaAux;
		String aux1;
		String aux2;
		int anterior = 0;
		byte contadorDeConflitos = 0;
		for (int i = 1; i < ind.getGenes().length; i++) {
			linhaAtual = ind.getGenes()[i];
			anterior = i - 1;
			while (anterior >= 0) {
				linhaAux = ind.getGenes()[anterior];

				aux1 = new String(linhaAtual);
				aux2 = new String(linhaAux);
				if (aux1.equals(aux2)) {
					contadorDeConflitos++;
				}
				anterior--;
			}

		}

		int posicaoDaRainha = 0;
		int aux;
		boolean continuaParaEsqueda;
		boolean continuaParaDireita;
		int p1;
		int p2;

		// comparação de diagonal
		for (int i = 1; i < ind.getGenes().length; i++) {

			aux = 1;
			anterior = i - 1;
			continuaParaEsqueda = true;
			continuaParaDireita = true;
			for (int j2 = 0; j2 < ind.getGenes()[0].length; j2++) {
				if (ind.getGenes()[i][j2] == '1') {
					posicaoDaRainha = j2;
				}
			}

			loop2: while (anterior >= 0) {

				p1 = posicaoDaRainha - aux;
				p2 = posicaoDaRainha + aux;

				if (p1 < 0) {
					continuaParaEsqueda = false;
				}
				if (p2 > ind.getGenes()[anterior].length - 1) {
					continuaParaDireita = false;
				}
				if (continuaParaDireita == false && continuaParaEsqueda == false) {
					break loop2;
				}
				if (continuaParaDireita == false) {
					if (ind.getGenes()[anterior][p1] == '1') {
						contadorDeConflitos++;
					}
				} else if (continuaParaEsqueda == false) {
					if (ind.getGenes()[anterior][p2] == '1') {
						contadorDeConflitos++;
					}
				} else if (ind.getGenes()[anterior][p1] == '1' || ind.getGenes()[anterior][p2] == '1') {
					contadorDeConflitos++;
				}
				aux++;
				anterior--;
			}
		}

		return contadorDeConflitos;
	}

	public static double funcaoFitness(Individuo ind) {

		return (double) 1 / (ind.getNumeroDeConflitos() + 1);
	}

	public static void calculandoProbabilidade(Individuo[] inds) {

		double somaDosFitness = 0;
		for (int i = 0; i < inds.length; i++) {
			somaDosFitness += inds[i].getFitnnes();
		}

		for (int i = 0; i < inds.length; i++) {
			inds[i].setProbabilidade((double) inds[i].getFitnnes() / somaDosFitness);
		}

	}

	// gerando o indice
	public static int selecao(Individuo[] inds) {
		// int indice[] = new int[NUMERO_DE_IND_SELECIONADOS];
		double[] somaDasProbabilidades = new double[inds.length];
		int j = 0;
		double aux = 0;
		for (int i = 0; i < somaDasProbabilidades.length; i++) {
			somaDasProbabilidades[i] = inds[i].getProbabilidade() + aux;
			aux += inds[i].getProbabilidade();
		}

		// selecionando o indice
		double rand = random.nextDouble();

		for (j = 0; j < somaDasProbabilidades.length; j++) {

			if (rand <= somaDasProbabilidades[j]) {
				break;

			}
		}

		return j;
	}

	public static int selecaoDoMelhor(Individuo[] inds) {
		int indice = 0;
		double probAux=0;
		for (int i = 0; i < inds.length; i++) {
			if(i==0){
				probAux = inds[0].getProbabilidade();
				continue;
			}
			if(probAux < inds[i].getProbabilidade()){
				probAux = inds[i].getProbabilidade();
				indice = i;
			}
		}
		return indice;
	}

}
