package algoritmo_genetico;

import java.util.Random;

public class Crossover {
	private Individuo novo1;
	private Individuo novo2;
	private Individuo pai1;
	private Individuo pai2;
	private static Random random = new Random();

	public Crossover(Individuo pai1, Individuo pai2) {

		this.pai1 = pai1;
		this.pai2 = pai2;
		gerando();
	}

	private void gerando() {
		int pontoDeCrossover = (int)pai1.getGenes().length/2;
		
		char [][] filho1Genes = new char[pai1.getGenes().length][pai1.getGenes()[0].length]; 
		char [][] filho2Genes = new char[pai1.getGenes().length][pai1.getGenes()[0].length]; 
		
		int i = 0;
		for (i = 0; i < pontoDeCrossover; i++) {
			filho1Genes[i] = pai1.getGenes()[i]; 
		}
		for (; i < filho1Genes.length; i++) {
			filho1Genes[i] = pai2.getGenes()[i]; 
		}
		
		i = 0;
		for (i = 0; i < pontoDeCrossover; i++) {
			filho2Genes[i] = pai2.getGenes()[i]; 
		}
		for (; i < filho2Genes.length; i++) {
			filho2Genes[i] = pai1.getGenes()[i]; 
		}
		
		novo1 = new Individuo(filho1Genes);
		novo2 = new Individuo(filho2Genes);
	}

	public Individuo getNovo1() {
		return novo1;
	}

	public void setNovo1(Individuo novo1) {
		this.novo1 = novo1;
	}

	public Individuo getNovo2() {
		return novo2;
	}

	public void setNovo2(Individuo novo2) {
		this.novo2 = novo2;
	}

	public Individuo getPai1() {
		return pai1;
	}

	public void setPai1(Individuo pai1) {
		this.pai1 = pai1;
	}

	public Individuo getPai2() {
		return pai2;
	}

	public void setPai2(Individuo pai2) {
		this.pai2 = pai2;
	}

	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		Crossover.random = random;
	}

	@Override
	public String toString() {
		return "Crossover [novo1=" + novo1 + ", novo2=" + novo2 + ", pai1=" + pai1 + ", pai2=" + pai2 + "]";
	}
	
}
