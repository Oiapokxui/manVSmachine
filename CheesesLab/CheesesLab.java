import java.util.ArrayList;
class CheesesLab {
	static int[][] matriz = new int[11][6];	
	static int posX ;
	static int posY ;
	static ArrayList<int[]> queue = new ArrayList<int[]>();	

	public static void main(String[] args){
		int[] cheese = queijo (10, 5);
		posAtual (cheese);
		char[] vizinhos = bifurcacao();

		for (char i : vizinhos){
			int[] prox = mover(i);
			addQueue(cheese, prox);
		}

		while ( !queue.isEmpty() ){
				
		}
	}

	public void matriz() {
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
		x = posX;
		y = posY;
		if (char == 'u') y++;
		if (char == 'd') y--;
		if (char == 'l') x--;
		if (char == 'r') x++;
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
		char[] b = bifurca.toArray();

		return b;

	}
		
	static int[] queijo (int xQueijo, int yBacon){
		matriz[xQueijo][yBacon] = 1;
		int[] queijo = new int[2];
		queijo[0] = xQueijo;
		queijo[1] = yQueijo;	
		return queijo;
	}
	
	static int[] anterior (int xAnt, int yAnt){

		int[] anterior = { xAnt ; yAnt};
		return anterior;	

	}
	
	static void addQueue (int[] origem, char direcao){
		queue.add(origem);
		queue.add( mover(direcao) );
	}

	static void removeQueue() {
        	queue.remove(0);
        	queue.remove(1);
	}

}
