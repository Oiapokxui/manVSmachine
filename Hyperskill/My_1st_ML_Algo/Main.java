package recognition;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		// redeNeural é um arranjo de vetores.
		// Dá um atalho para os atalhos dos arranjos
		// dos neuronios i da rede. Onde i eh 
		// o indice da linha.
		int[][][] redeNeural = new int[2][15][1];
		int[][] neuronA0 = new int[15][1];
		redeNeural[0] = neuronA0;
		// Estes sao os valores de bias para os neuronios
		// respectivos aos dígitos
		// 1, 2, 3, 4, 5, 6, 7, 8, 9, 0. 
		int[][] bias = {
				{6},
				{1},
				{0},
				{2},
				{0},
				{-1},
				{3},
				{-2},
				{-1},
				{-1}
			};
		 
		Scanner scan = new Scanner(System.in);
		System.out.println("Input grid: ");
		String[] linha = new String[5];
		int h = 0; 
		for (int i = h; i < 5; i++) {
			linha[i] = scan.nextLine();
			for (int v = 0; v < 3; v++){ 
				neuronA0[h][0] = (linha[i].charAt(v) == 'X')?
					1 : -1;
				h++;
			}
		}
		 	
		// Cada linha dessa matriz representa os pesos
		// do n-esimo neuronio da camada 1.
		// pesos é um int[10][15] e estão distribuidos
		// da seguinte maneira, de acordo com a linha:
		// 1, 2, 3, 4, 5, 6, 7, 8, 9, 0
		int[][] pesos = {
			{-1,1,-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,1,-1},
			{1,1,1,-1,-1,1,1,1,1,1,-1,-1,1,1,1},
			{1,1,1,-1,-1,1,1,1,1,-1,-1,1,1,1,1},
			{1,-1,1,1,-1,1,1,1,1,-1,-1,1,-1,-1,1},
			{1,1,1,1,-1,-1,1,1,1,-1,-1,1,1,1,1},
			{1,1,1,1,-1,-1,1,1,1,1,-1,1,1,1,1},
			{1,1,1,-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,1},
			{1,1,1,1,-1,1,1,1,1,-1,-1,1,1,1,1},
			{1,1,1,1,-1,1,1,1,1,-1,-1,1,1,1,1},
			{1,1,1,1,-1,1,1,-1,1,1,-1,1,1,1,1}
		} ;
		int[][] neuronA1 = multiMatriz (pesos, neuronA0);	 
		somaMatriz (neuronA1, bias);
		 
		System.out.println();
		System.out.println("This number is: " + chosenNeuron(neuronA1));
	}
	 
	static int[][] multiMatriz(int[][] m1, int[][] m2){
		int[][] Resultante = new int[m1.length][m2[0].length];
		if (m1[0].length != m2.length){
			return null;
		}
		int linhasR = m1.length;
		int colunasR = m2[0].length;
		int colunasM1 = m1[0].length;
		for (int i = 0; i < linhasR ; i++){
			for (int v = 0; v < colunasR ; v++){
				Resultante[i][v] = 0;
				for(int w = 0; w < colunasM1; w++){
					Resultante[i][v] += m1[i][w] * m2[w][v];
				} 
			}
		}
		return Resultante;
	}

	static void somaMatriz(int[][] m1, int[][] m2){
		for (int i = 0; i < m1.length; i++){
			for (int v = 0; v < m1[0].length ; v++){
				m1[i][v] += m2[i][v];
			}
		}
	}
	 
	static int chosenNeuron (int[][] n){
		int maxInd = 0;
		for (int i = 0; i < n.length ; i++){
			for (int v = 0; v < n[0].length ; v++){
				System.out.printf("Numero: %d", (i+1)%10);
				System.out.printf(" Ativacao: %d\n", n[i][0]);
				if (n[maxInd][0] < n[i][0]) maxInd = i;
			}
		}
		return (maxInd + 1)% 10;
	}
	
}
