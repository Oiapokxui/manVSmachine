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
	
	static ArrayList<Character> bifurcacao (){ 
		ArrayList<Character> bifurca = new ArrayList<Character>(); 
		boolean up_left = posX == 0 && posY == 0; 
		boolean up_right = posX == 10 && posY == 0; 
		boolean down_left = posX == 0 && posY == 5;
		boolean down_right = posX == 10 && posY == 5;
		boolean meioX = posX > 0 && posX < 10;
		boolean meioY = posY > 0 && posY < 5;
		boolean leftColumn = posX == 0 && meioY;
		boolean rightColumn = posX == 10 && meioY;
		boolean downColumn = posY == 5 && meioX;
		boolean upColumn = posY == 0 && meioX;	
		if (meioY && meioX) {
			if ( matriz[posX+1][posY] != -1 ) bifurca.add('r');
			if ( matriz[posX-1][posY] != -1 ) bifurca.add('l');
			if ( matriz[posX][posY+1] != -1 ) bifurca.add('u');
			if ( matriz[posX][posY-1] != -1 ) bifurca.add('d');	
		}
		if (up_right) {
			if ( matriz[posX-1][posY] != -1 ) bifurca.add('l');
			if ( matriz[posX][posY-1] != -1 ) bifurca.add('d');	
		}
		if (up_left) {
			if ( matriz[posX+1][posY] != -1 ) bifurca.add('r');
			if ( matriz[posX][posY-1] != -1 ) bifurca.add('d');	
		}
		if (down_right) {
			if ( matriz[posX-1][posY] != -1 ) bifurca.add('l');
			if ( matriz[posX][posY+1] != -1 ) bifurca.add('u');	
		}
		if (down_left) {
			if ( matriz[posX+1][posY] != -1 ) bifurca.add('r');
			if ( matriz[posX][posY+1] != -1 ) bifurca.add('u');
		}
		if (rightColumn) {
			if ( matriz[posX-1][posY] != -1 ) bifurca.add('l');
			if ( matriz[posX][posY+1] != -1 ) bifurca.add('u');     			if ( matriz[posX][posY-1] != -1 ) bifurca.add('d');	
		}
		if (leftColumn) {
			if ( matriz[posX+1][posY] != -1 ) bifurca.add('r');
			if ( matriz[posX][posY+1] != -1 ) bifurca.add('u');
			if ( matriz[posX][posY-1] != -1 ) bifurca.add('d');	
		}
		if (downColumn) {
			if ( matriz[posX+1][posY] != -1 ) bifurca.add('r');
			if ( matriz[posX][posY+1] != -1 ) bifurca.add('u');
			if ( matriz[posX-1][posY] != -1 ) bifurca.add('l');
		}
		if (rightColumn) {
			if ( matriz[posX+1][posY] != -1 ) bifurca.add('r');
			if ( matriz[posX][posY-1] != -1 ) bifurca.add('d');
			if ( matriz[posX-1][posY] != -1 ) bifurca.add('l');
		}
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
