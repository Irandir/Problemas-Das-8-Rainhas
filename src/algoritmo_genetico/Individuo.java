package algoritmo_genetico;

import java.util.Arrays;

public class Individuo {
	private char[][] genes;
	private byte numeroDeConflitos;
	//private boolean[] conflitos;
	private double fitnnes;
	private double probabilidade;
	public Individuo(char[][] genes) {
		super();
		this.genes = genes;
	}
	
	public char[][] getGenes() {
		return genes;
	}
	public void setGenes(char[][] genes) {
		this.genes = genes;
	}
	public byte getNumeroDeConflitos() {
		return numeroDeConflitos;
	}
	public void setNumeroDeConflitos(byte numeroDeConflitos) {
		this.numeroDeConflitos = numeroDeConflitos;
	}


	public double getFitnnes() {
		return fitnnes;
	}

	public void setFitnnes(double fitnnes) {
		this.fitnnes = fitnnes;
	}

	
	public double getProbabilidade() {
		return probabilidade;
	}

	public void setProbabilidade(double probabilidade) {
		this.probabilidade = probabilidade;
	}

	@Override
	public String toString() {
		return "Individuo [" + " numeroDeConflitos=" + numeroDeConflitos + ", fitnnes="
				+ fitnnes + ", probabilidade=" + probabilidade + "]";
	}

	

	

	

	
	
}
