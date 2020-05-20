package recognition;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		// neuron é um vetor unidimensional que
		// representa os 9 neuronios da rede.
		int[][] neuron = new int[1][9];
		int bias = -5;
		Scanner scan = new Scanner(System.in);
		System.out.println("Input grid: ");
		String linha1 = scan.nextLine();
		String linha2 = scan.nextLine();
		String linha3 = scan.nextLine();
		for (int i = 0; i < 9; i++){
			if (i < 3){
			       	neuron[0][i] = (linha1.charAt(i) == 'X')?
					1 : 0;
			}
			else if (i > 2 && i < 6){
			       	neuron[0][i] = (linha2.charAt(i%3) == 'X')?
					1 : 0;
			}
			else if (i > 5 && i < 9){
			       	neuron[0][i] = (linha3.charAt(i%3) == 'X')?
					1 : 0;
			}
		}
		// Por enquanto, todos os pesos foram fornecidos
		int[][] pesos = {{2},{1},{2},{4},{-4},{4},{2},{-1},{2}}; 
		int[][] a10 = multiMatriz(neuron, pesos);
		a10[0][0] += bias;
		 
		System.out.print("This number is: ");
		if (a10[0][0] >= 0){
			System.out.println("0");
		} else {
			System.out.println("1");
		}
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
}
