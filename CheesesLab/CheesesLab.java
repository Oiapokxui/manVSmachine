import java.util.Arraylist;
class ChessesLab {
	static int[][] matriz = new int[6][11];	
	static int posX = -1;
	static int posY = -1;

	public static void main(String[] args){
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

	static void mover (char direcao){
		if (char == 'u') posY++;
		if (char == 'd') posY--;
		if (char == 'l') posX--;
		if (char == 'r') posX++;
	}

	// Este verifica quantas ramificacoes a casa possui,
	// retorna uma ArrayList com as ramificacoes 
	// ( 'u' = ramo acima, 'b' = ramo abaixo
	// 'r' = ramo à esquerda, 'l' = ramo à direita)

	static ArrayList<Character> bifurcacao (){ 
		ArrayList<Character> bifurca = new ArrayList<Character>(); 
		boolean leftColumn = posX == 0 ;
		boolean rightColumn = posX == 10 ;
		boolean downColumn = posY == 5 ;
		boolean upColumn = posY == 0 ;	
		if (!leftColumn) bifurca.add('l');
		if (!rightColumn) bifurca.add('r');
		if (!downColumn) bifurca.add('d');
		if (!upColumn) bifurca.add('u');
		return bifurca;
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
	
	static int[] queue (int xProx, int yProx){
		int[] queue = { xProx , yProx};
		return queue;	
	}

}
