import java.util.ArrayList;
class CheesesLab {
	static int[][] matriz = new int[11][6];	
	static int posX ;
	static int posY ;
	static ArrayList<int[]> queue = new ArrayList<int[]>();	

	public static void main(String[] args){
		matriz();
		int[] cheese = queijo (10, 5);
		int[] rato = {0 , 0};
		posAtual (cheese);
		char[] vizinhos = bifurcacao();

		for (char i : vizinhos){
			addQueue(cheese, i);
		}
		
		fillMatrix();
		
		int passosTotais = matriz[ rato[0] ][ rato[1] ]; 
		if ( passosTotais > 0 ) {
			System.out.printf("O rato andou %d casas para chegar" + 
					" ao queijo!\n",passosTotais);
		} else {
			System.out.println("Nao é possivel chegar ao queijo. :c");
		}
	}

	static void matriz() {
		/*		for (int i = 0; i<5; i++){
					for (int v = 0; v <10 ; v++){
						matriz[i][v] = 0;
					}
				}
		*/
		matriz[2][0] = -1;
		matriz[3][0] = -1;
		matriz[1][1] = -1;
		matriz[4][1] = -1;
		matriz[6][1] = -1;
		matriz[8][1] = -1;
		matriz[9][1] = -1;
		matriz[2][2] = -1;
		matriz[4][2] = -1;
		matriz[6][2] = -1;
		matriz[10][2] = -1;
		matriz[1][3] = -1;
		matriz[3][3] = -1;
		matriz[7][3] = -1;
		matriz[8][3] = -1;
		matriz[3][4] = -1;
		matriz[5][4] = -1;
		matriz[9][4] = -1;
		matriz[1][5] = -1;
		matriz[8][5] = -1;
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
		

		if (!leftColumn){
			int esquerda = matriz[posX - 1][posY];
		       	if (esquerda  == 0 ) bifurca.add('l');
		}
		if (!rightColumn) {
			int direita = matriz[posX + 1][posY];
			if (direita == 0 ) bifurca.add('r');
		}
		if (!downColumn) {
			int abaixo = matriz[posX][posY + 1];
			if (abaixo == 0 ) bifurca.add('d');
		}
		if (!upColumn) {
			int acima = matriz[posX][posY - 1];
			if (acima == 0 ) bifurca.add('u');
		}

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
		queue.remove(1);
		queue.remove(0);
	}
	
	static void fillMatrix(){
		int k ;
		while ( !queue.isEmpty() ){            	
        		int[] origem = queue.get(0);
        		int[] destino = queue.get(1);
        		posAtual(origem);
        		k = matriz[posX][posY];
        		posAtual(destino);
        		matriz[posX][posY] = k + 1;
        		char[] vizinhos = bifurcacao();
        		for (char i : vizinhos){
                        	addQueue(destino, i);
        		}
			removeQueue();
        	}
	}

}
