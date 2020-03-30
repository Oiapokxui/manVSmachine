/*********************************************************************/
/**   Aproximacao do valor de cosseno de x                          **/
/**                                                                 **/
/**   Oiapokxui           enrique.emanuel@usp.br                    **/
/**                                                                 **/
/**   30/03/2020                                                    **/
/*********************************************************************/

/*
	Atraves da serie de Taylor, aproxima cos(x), x e os n
	primeiros termos dados
*/

import java.util.Scanner;
class Cosaprox {

	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		System.out.println( "Digite primeiro o número (radianos) cujo cosseno gostaria de aproximar" );
		System.out.println( "depois o número de passos de aproximação" );

		// Esta variavel armazena o valor real para aproximar seu cosseno
		double real = ( input.nextDouble() % (Math.PI*2) );		
		// Devido a natureza par do cosseno, cos(-x) = cos(x)
		if (real < 0) real *= (-1);
		// Esta armazena o numero de termos da serie a ser calculada
 
		int natural = (input.nextInt() - 1);
		System.out.println( cosseno(real, natural) );
		
	}

	/*	
  		Funcao fatorial de um numero x

		Parametros: 
			  'num' - numero cujo fatorial sera calculado
	*/

	static long fatorial(int num){
		long fator = 1;
		for (int i = 1; i<= num ; i++){
			fator *= i;
		}
		return fator;
	}

	/*	
  		O seguinte metodo calcula para determinado valor real seu cosseno de
		acordo com uma aproximacao da serie de Taylor com base nos n primeiros
		termos fornecidos. Como o divisor eh um fatorial de um numero que segue
		razao aritmetica 2, após 10 termos dá overflow no tipo long, portanto
		assim que o divisor se torna negativo, o calculo eh interrompido

		Parametros: 
			  'radiano' - numero cujo cosseno sera aproximado
			  'termos' -  quantidade de termos da aproximação
	*/

	static double cosseno(double radiano, int termos){
		double cos = 0;
		int passos = 0;
		while (passos <= termos){
			double a =  Math.pow( (-1), passos);
			double b = Math.pow(radiano , (2 * passos) );
			long c = fatorial(2 * passos);
			if (c < 0) break;
			cos += ( a * b ) / c;
			//System.out.printf("%f %f %d\n", a, b, c);
			passos++;
		}
		return cos;
	}
}
