package recognition;
import java.util.*;

/*
 *				RECOGNITION.
 *	Projeto do curso Hyperskill.
 *	Visa projetar uma rede neural do tipo perceptron
 *	a fim de identificar digitos em uma grid 3x3
 *	 
 *	São recebidas, por input do usuario, 5 linhas que 
 *	contem alguma permutacao de 3 'X' ou '_', essas 5 
 *	linhas, representam o desenh de um digito,
 *	onde o carac. 'X' representaria um pixel
 *	aceso e o caract '_' um pixel apagado. 
 *
 *	Apos o processamento, espera-se que a rede saiba identificar
 *	qual o digito desenhado, imprimindo no terminal o inteiro que
 *	corresponde ao dígito.
 *
 */
 
public class Main {
	public static void main(String[] args) {
		int[] neuronA0 = new int[15];
		// Estes sao os valores de bias para os neuronios
		// respectivos aos dígitos  do 1 ao 9, e 0
		int[] bias = {
				 6,	// 1
				 1,	// 2
				 0,	// 3
				 2,	// 4
				 0,	// 5
				-1,	// 6
				 3,	// 7
				-2,	// 8
				-1,	// 9
				-1	// 0
			};
		 
		System.out.println("Input grid: ");
		 	
		// Cada linha dessa matriz representa os pesos
		// do n-esimo neuronio da camada 1.
		// pesos é um int[10][15] onde cada linha
		// representa os 15 pesos para um digito ideal
		int[][] pesos = 
		{
				{	// Um
					 -1,+1,-1,
					 -1,+1,-1,
					 -1,+1,-1,
					 -1,+1,-1,
					 -1,+1,-1},
				{	// Dois
					 +1,+1,+1,
					 -1,-1,+1,
					 +1,+1,+1,
					 +1,-1,-1,
					 +1,+1,+1},
				{	// Tres
					 +1,+1,+1,
					 -1,-1,+1,	
					 +1,+1,+1,
					 -1,-1,+1,
					 +1,+1,+1},
				{	// Quatro
					 +1,-1,+1,
					 +1,-1,+1,
					 +1,+1,+1,
					 -1,-1,+1,
					 -1,-1,+1},
				{ 	// Cinco
					 +1,+1,+1,
					 +1,-1,-1,
					 +1,+1,+1,
					 -1,-1,+1,
					 +1,+1,+1},
				{	// Seis
					 +1,+1,+1,
					 +1,-1,-1,
					 +1,+1,+1,
					 +1,-1,+1,
					 +1,+1,+1},
				{	// Sete
					 +1,+1,+1,
					 -1,-1,+1,
					 -1,-1,+1,
					 -1,-1,+1,
					 -1,-1,+1},
				{	// Oito
					 +1,+1,+1,
					 +1,-1,+1,	
					 +1,+1,+1,
					 +1,-1,+1,
					 +1,+1,+1},
				{ 	// Nove
					 +1,+1,+1,
					 +1,-1,+1,
					 +1,+1,+1,
					 -1,-1,+1,
					 +1,+1,+1},
				{	// Zero
					 +1,+1,+1,
					 +1,-1,+1,
					 +1,-1,+1,
					 +1,-1,+1,
					 +1,+1,+1}
		} ;

		neuronA0 = preencheNeuronA0 (neuronA0);
		int[] neuronA1 = novaCamada (pesos, neuronA0, bias);	 
		 
		System.out.println("This number is: " + chosenNeuron(neuronA1));
	}
	 
	/*
	 *	Recebe o uma camada de neuronios e preenche ela
	 *	de acordo com inputs a serem digitados pelo
	 *	usuario.
	 *	
	 *	Entradas:
	 *		- neuronA0 : Um arranjo de inteiros que
	 *		representa uma camada de neuronios.
	 *	Saidas:
	 *		- neuronA0 : O mesmo arranjo só que
	 *		preenchido.
	 */
	static int[] preencheNeuronA0 (int[] neuronA0){
		Scanner scan = new Scanner(System.in);
		String[] linha = new String[5];
		int h = 0; 
		for (int i = h; i < 5; i++) {
			linha[i] = scan.nextLine();
			for (int v = 0; v < 3; v++){ 
				neuronA0[h] = (linha[i].charAt(v) == 'X')?
					1 : 0;
				h++;
			}
		}
		return neuronA0;
	}
	 
	/*
	 *	Realiza operacoes necessarias para se obter uma
	 *	nova camada de neuronios, a partir de uma matriz
	 *	de pesos, do vetor da camada anterior de neuronios
	 *	e do vetor dos pesos.
	 *
	 *	Entrada:
	 *		- pesos : Matriz que representa todos os pesos
	 *		para o calculo.
	 *		- neuron : Arranjo que representa a camada anterior
	 *		de neuronios a ser transformada.
	 *		- bias : Arranjo que armazena o valor de todos os vies
	 *		necessarios.
	 *	Saida:
	 *		- resultante : Uma nova camada de neuronios.
	 */
	static int[] novaCamada (int[][] pesos, int[] neuron, int[] bias) {
		// MULTIPLICAO ENTRE PESOS E NEURON
		int[] resultante = new int[pesos.length];
		if (pesos[0].length != neuron.length){
			return null;
		}
		int linhasR = pesos.length;
		int colunasR = 1;
		int colunasM1 = pesos[0].length;
		for (int i = 0; i < linhasR ; i++){
			for (int v = 0; v < colunasR ; v++){
				resultante[i] = 0;
				for(int w = 0; w < colunasM1; w++){
					resultante[i] += pesos[i][w] * neuron[w];
				} 
			}
		}
		 
		// ADICAO DOS VIESES.
		somaNeuronioPeso (resultante, bias);
		return resultante;
	}
	
	/*
	 *	Adiciona os vies a uma camada existente de neuronios
	 *
	 *	Entrada:
	 *		- vetor : Arranjo que representa a 
	 *		camada de neuronio existente
	 *		- bias : O arranjo de vieses.
	 *	Saida:
	 *		nao ha.
	 */
	static void somaNeuronioPeso (int[] vetor, int[] bias) {
		for (int i = 0; i < vetor.length; i++){
			vetor[i] += bias[i];
		}
	}
	 
	/*
	 *	Dentro da camada, escolhe o neuronio de valor maximo
	 *	
	 *	Entrada:
	 *		- neuron : Arranjo de inteiros que representa a
	 *		camada de neuronios.
	 *	Saida
	 *		nao ha.
	 */
	static int chosenNeuron (int[] neuron) {
		int maxInd = 0;
		for (int i = 0; i < neuron.length ; i++){
			if (neuron[maxInd] < neuron[i]) maxInd = i;
		}
		return (maxInd + 1)% 10;
	}
	
}
