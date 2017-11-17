package algoritmo_genetico;

import java.util.Random;

public class Multacao {
	
	private Random random = new Random();
	private char[][]genes;
	public Multacao(Individuo individuo) {
		genes = new char[individuo.getGenes().length][individuo.getGenes()[0].length];
		for (int i = 0; i < genes.length; i++) {
			for (int j = 0; j < genes.length; j++) {
				genes[i][j] = individuo.getGenes()[i][j];
			}
			
		}
		
	}
	
	public Individuo multando1() {
		
		int indice = random.nextInt(genes.length);
		
		char gene[] = genes[indice];
		int indice1= 0;
		for (int i = 0; i < gene.length; i++) {
			if (gene[i] == '1') {
				indice1 = i;
				break;
			}
		}
		int indece2 = random.nextInt(gene.length);
		while(indece2 == indice1){
			indece2 = random.nextInt(gene.length);
		}
		
		char aux = gene[indice1];
		
		gene[indice1] = gene[indece2];
		gene[indece2] = aux;
		
		
		genes[indice] = gene;
		
		return new Individuo(genes);
	}

}
