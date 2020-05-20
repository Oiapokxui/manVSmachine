package tictactoe;
import java.util.*;
/*
	Jogo da Velha - programa para verificar o status de um jogo.
	
	Lista de Status calculado:
	-1 - Impossible
	 0 - Game not finished
	 1 - X wins
	 2 - O wins
	 3 - Draw
*/
public class Main {
	static final char pecaX = 'X';
	static final char pecaO = 'O';
	static final char espacoVazio = '_';
	// Conta as aparicoes da peca O no tabuleiro
	static int bola = 0;
	// Conta as aparicoes da peca X no tabuleiro
	static int xis = 0;
	// Conta todas as sequencias ganhadoras no jogo.
	static int conta;
	// Conta todos os espacos vazios do tabuleiro.
	static int contagemVazios;
	static char[][] tabuleiro;
	
	public static void main(String[] args) {
	    	String jogo = "_________"; 
		// Arranjo para armazenar a partida.
	    	tabuleiro = stringToCharArr(jogo);	
		montaJogo();
		 
		while (resultado() == 0){
			turnoJogador(pecaX); 
			montaJogo();
			if (resultado() != 0) break;
			turnoJogador(pecaO); 
			montaJogo();
		} 
		System.out.println( verificaStatus() ) ;
	}
	 
	/*
		Coordena a impressao de um tabuleiro, recebe um input
		de coordenadas do usuario, coloca um dos tipos de peca
		no tabuleiro de acordo com as coordenadas fornecidas, se
		possivel, e imprime de novo o tabuleiro atualizado.
			
		Entrada:
			nao ha.		
		Saída:
			nao ha.
	*/
	static void turnoJogador(char peca){
	    	Scanner scan = new Scanner(System.in);
		boolean rangeX, rangeY;
		boolean valido = false;
		String xOy;
		int coordX = 0;
		int coordY = 0;
		do { 
			try{
				valido = true;
				System.out.println("Enter the coordinates: ");
				// as proximas duas linhas sao necessarias
				// para transformar o input da coordenada X,
				// que cresce de baixo para cima, em um X
				// equivalente dentro da matriz.
				xOy = scan.nextLine();
				String[] coords = xOy.split(" ");
				coordX = Integer.parseInt(coords[0]) - 1;
				coordY = Integer.parseInt(coords[1]) - 3;
				coordY = (coordY >= 0)? coordY : coordY * -1; 
				rangeX = coordX >= 0 && coordX <= 2;
				rangeY = coordY >= 0 && coordY <= 2;
				 
				if (!rangeX || !rangeY){
					valido = false;
					System.out.println("Coordinates should be from 1 to 3!");
				} else { 
					if (tabuleiro[coordY][coordX] != '_'){
						valido = false;
						System.out.println("This cell is occupied! Choose another one!");
					}
				}
			} catch (NumberFormatException e){
				System.out.println("You should enter numbers!"); 
				valido = false;
			}
		} while (!valido) ;
		 
		tabuleiro[coordY][coordX] = peca;
	}
	 
	/*
		Transforma uma string de 9 caracteres em uma matriz  
		3x3 de chars.
			
		Entrada:
			`jogo` - string a ser utilizada.
		Saída:
			`tabuleiro` - matriz de chars que representa o
				tabuleiro.
	*/
	static char[][] stringToCharArr (String jogo){
	    	char[][] tabuleiro = new char[3][3];
		 	
		// Transforma o input em uma matriz 3x3
		// que representa o jogo atual		
		int h = 0;
		for(int v = 0; v<3 ; v++){
			for(int i = 0; i < 3; i++){
	    			tabuleiro[v][i] = jogo.charAt(h);
				h++;
			}
		}	 
		return tabuleiro;
	}
	 
	/*
		Recebe uma matriz 3x3 e imprime ela no terminal do usuario
			
		Entrada:
			nao ha.
		Saída:
			nao ha.
	*/
	static void montaJogo(){
	    	System.out.println("---------");
	    	for (int v = 0; v<3; v++){
			System.out.print("| ");
			for(int i = 0 ; i< 3 ; i++){
				System.out.print(tabuleiro[v][i] + " ");
			}
			System.out.println("|");
	    	}
	    	System.out.println("---------");
	}
	/*
	   	Transforma o inteiro recebido de resultado() em uma string
		na qual está escrito a interpretacao daquele inteiro de acordo
		com a lista no cabecalho do codigo.
		
		Entrada:
			mesa - matriz 3x3 de caracteres representando uma 
			partida valida ou nao de Jogo da Velha
			
		Saída:
			Uma string a ser impressa, que diz o status da
			partida.
	*/
	 
	static String verificaStatus() {
		String status = "";
		int resultado = resultado();
		switch (resultado) {
			case (-1):
				status += "Impossible";		
				break;
			case 0:
				status += "Game not finished";
				break;
			case 1:
				status += "X wins";
				break;
			case 2:
				status += "O wins";
				break;
			case 3:
				status += "Draw";
				break;
		}
		return status;
	}
	 
	/*
		Determina qual o resultado da partida
		
		Entrada:
			nao ha.	
		Saída:
			Um inteiro que indica se a peca venceu ou nao o jogo. 
	*/
	static int resultado(){
		// Armazena um inteiro que representa o resultado daquele jogo.
		int resultado = 0;
		// Diz se apenas uma peça venceu.
		boolean[] vence = vence();
		contagemVazios = contagem(); 
		 
		// Para que uma partida tenha um vencedor, eh preciso que
		// pelo menos 5 jogadas sejam feitas, ou seja, que sobrem
		// no máximo 4 espacos vazios.
		// ------------------------------------------------------
		// Para que a partida seja válida, deve haver apenas um
		// vencedor OU a diferenca da quantidade de cada peca
		// no tabuleiro nao deve ser maior que 1
		if (conta < 2 && contagemVazios <= 4){
			if (vence[0]){
				resultado = 1;
			}
			else if(vence[1]){
				resultado = 2;
			} 
			else if (contagemVazios == 0) {
				resultado = 3;
			}
		}
		if ( (bola >= xis + 2) || (xis >= bola + 2) || (conta >= 2) ){
			resultado = -1;
		}
	
		return resultado;
	}
	 
	/*
		Determina se uma determinada peca venceu o jogo.
		
		Entrada:
			nao ha.
		Saída:
			um arranjo de boolean que indica quem venceu ou nao o jogo.
			vence[0] indica se `X` venceu
			vence[1] indica se `O` venceu
	*/
	static boolean[] vence (){
		// Em todos os arranjos abaixo, dentro da primeira dimensão
		// os itens arr[0] se referem a peca `X` e os itens arr[1]
		// a peca `O`
		 
		// 'linhas', 'colunas', 'diagonais' sao arranjos responsaveis
		// por armazenar quantas vezes a peca foi jogada
		// na 'i' coluna, linha ou diagonal do tabuleiro. 
		// 'i' sendo a coluna da matriz.  
		// A primeira linha de cada matriz se refere
		// às peças X, a segunda às peças O. 
		// As linhas são numeradas de cima para baixo, as colunas da
		// esquerda para a direita.
		int[][] linhas = { {0,0,0}, {0,0,0} };
		int[][] colunas = { {0,0,0}, {0,0,0} };
		int[][] diagonais = { {0,0}, {0,0} };
		 
		// Os 3 arranjos abaixo armazenam 
		// se há apenas uma sequencia vencedora
		// de uma peca nas linhas, colunas ou diagonais.
		boolean[] venceLinha = {false, false};
		boolean[] venceColuna = {false, false};
		boolean[] venceDiagonal = {false, false};
		// Diz se apenas uma peça venceu.
		boolean[] vence = {false, false};
		 	
	       	bola = xis = 0;	
		conta = 0;
		 	
		// Este daqui armazena quantas
		// vezes a peca X ou O fez uma
		// sequencia ganhadora.
		int[] contaXOuY = new int [2];
		 
		for (int i = 0; i< 3; i++){
			for (int v = 0; v < 3; v++){
				if (tabuleiro[i][v] == pecaX) {
					xis++;
					if ( i == v ) diagonais[0][0]++;
					if ( v + i == 2 ) diagonais[0][1]++;
					linhas[0][i]++;
				    colunas[0][v]++;
				} 
				else if (tabuleiro[i][v] == pecaO) {
					bola++;
					if ( i == v ) diagonais[1][0]++;
					if ( v + i == 2 ) diagonais[1][1]++;
					linhas[1][i]++;
				    colunas[1][v]++;
				}
			}
			

		} 
		
		for (int i = 0; i < 2 ; i++){ 
			venceLinha[i] = linhas[i][0] == 3;
			venceColuna[i] = colunas[i][0] == 3;
			
			for (int v = 0; v < 3; v++){
				if (linhas[i][v] == 3) contaXOuY[i]++;
				if (colunas[i][v] == 3) contaXOuY[i]++;
				if (v < 2){
					if (diagonais[i][v] == 3) contaXOuY[i]++;
				}
				if ( v > 0 ){
					venceLinha[i] = venceLinha[i] ^ linhas[i][v] == 3 ;
					venceColuna[i] = venceColuna[i] ^ colunas[i][v] == 3 ;
					venceDiagonal[i] = venceDiagonal[i] ^ diagonais[i][v - 1] == 3;
					vence[i] = venceLinha[i] || venceColuna[i] || venceDiagonal[i];
				}
			}
		}
		 
		// Como o jogo só é impossível quando X e O ganham
		// ao mesmo tempo, `conta` só receberá o valor 2
		// se existirem duas sequencias ganhadoras das
		// duas pecas ao mesmo tempo.
		conta = (contaXOuY[0] > 0 && contaXOuY[1] > 0)? 2 : 1;
		return vence;
	}

	/*
		Conta todos os espacos nao ocupados por alguma peca no tabuleiro	
		
		Entrada:
			nao ha.	
		Saída:
			um inteiro contendo a quantidade de espacos nao ocupados em um determinado tabuleiro
	*/
	static int contagem (){
		int cont = 0;
		for(int i = 0; i<tabuleiro.length; i++){
			for (int v = 0; v < tabuleiro[0].length ; v++){
				if (tabuleiro[i][v] == espacoVazio) {
					cont ++;
				}
			}
		}
		return cont;
	}

}
