import java.util.ArrayList;
class CheesesLab {
	static int[][] matriz = new int[11][6];	
	static int posX ;
	static int posY ;
	static ArrayList<int[]> queue = new ArrayList<int[]>();	

	public static void main(String[] args){
		int[] cheese = queijo (10, 5);
		int[] rato = {0 , 0};
		posAtual (cheese);
		char[] vizinhos = bifurcacao();

		for (char i : vizinhos){
			addQueue(cheese, i);
		}
		
		fillMatrix();

		int passosTotais = menorCaminho(rato);
		System.out.printf("O rato andou %d casas para chegar ao queijo!", 
				passosTotais);
	}

	static void matriz() {
		for (int i = 0; i<5; i++){
			for (int v = 0; v <10 ; v++){
				matriz[i][v] = 0;
			}
		}
		matriz[0][2] = -1;
		matriz[0][3] = -1;
		matriz[1][1] = -1;
		matriz[1][4] = -1;
		matriz[1][6] = -1;
		matriz[1][8] = -1;
		matriz[1][9] = -1;
		matriz[2][2] = -1;
		matriz[2][4] = -1;
		matriz[2][6] = -1;
		matriz[2][10] = -1;
		matriz[3][1] = -1;
		matriz[3][3] = -1;
		matriz[3][7] = -1;
		matriz[3][8] = -1;
		matriz[4][3] = -1;
		matriz[4][5] = -1;
		matriz[4][9] = -1;
		matriz[5][1] = -1;
		matriz[5][8] = -1;
	}

	static void posAtual(int[] coord){
		posX = coord[0];
		posY = coord[1];
	}

	static int[]  mover (char direcao){
		int[] coordenada = new int [2];
		int x = posX;
		int y = posY;
		if (direcao == 'd') y++;
		if (direcao == 'u') y--;
		if (direcao == 'l') x--;
		if (direcao == 'r') x++;
		coordenada[0] = x; 
		coordenada[1] = y;               
		return coordenada;
	}

	// Este verifica quantas ramificacoes a casa possui,
	// retorna uma ArrayList com as ramificacoes 
	// ( 'u' = ramo acima, 'b' = ramo abaixo
	// 'r' = ramo à esquerda, 'l' = ramo à direita)

	static char[] bifurcacao (){ 
		ArrayList<Character> bifurca = new ArrayList<Character>(); 

		boolean leftColumn = posX == 0 ;
		boolean rightColumn = posX == 10 ;
		boolean downColumn = posY == 5 ;
		boolean upColumn = posY == 0 ;	

		if (!leftColumn) bifurca.add('l');
		if (!rightColumn) bifurca.add('r');
		if (!downColumn) bifurca.add('d');
		if (!upColumn) bifurca.add('u');

		return converte(bifurca);
	}
		

	static char[] converte (ArrayList<Character> lista){
		char[] arr = new char [lista.size()];
		for (int i = 0; i < lista.size() ; i++){
			arr[i] = lista.get(i);
		}
		return arr;
	}

	static int[] queijo (int xQueijo, int yBacon){
		matriz[xQueijo][yBacon] = 1;
		int[] queijo = new int[2];
		queijo[0] = xQueijo;
		queijo[1] = yBacon;	
		return queijo;
	}
	
	static void addQueue (int[] origem, char direcao){
		queue.add(origem);
		queue.add( mover(direcao) );
	}

	static void removeQueue() {
        	queue.remove(0);
        	queue.remove(1);
	}
	
	static void fillMatrix(){
		int k ;
		while ( !queue.isEmpty() ){            	
        		int[] origem = queue.get(0);
        		int[] destino = queue.get(1);
        		posAtual(origem);
        		k = matriz[posX][posY];
        		posAtual(destino);
//			System.out.printf("x: %d y: %d\n", posX, posY);
        		matriz[posX][posY] = k + 1;
        		char[] vizinhos = bifurcacao();
        		for (char i : vizinhos){
                        	addQueue(destino, i);
        		}
        		removeQueue();
        	}
	}

	static int menorCaminho(int[] coordRato){
		int menorK;
		int passos = 0;
		posAtual( coordRato );
		menorK = matriz[posX][posY];
		while (menorK != 1){
			char[] caminhos = bifurcacao(); 
			char proxCasa = melhorCaminho(caminhos);
			posAtual( mover(proxCasa) );
			passos++;
			menorK--;
		}

		return passos;
	}

	static char melhorCaminho(char[] possiveis){
		char menorDir = 'q';
		int menor = Integer.MAX_VALUE;
		int[] kPossiveis = new int [possiveis.length];
		if (possiveis.length > 0)  {
			for (int i = 0; i < possiveis.length; i++){
				int[] xOy = mover(possiveis[i]);
				int atual = matriz[ xOy[0] ][ xOy[1] ];
				if (menor > atual) {
					menor = atual;
					menorDir = possiveis[i];
				}
				
			}
			return menorDir;
		} else {
			return 'e';
		}
	}

}
