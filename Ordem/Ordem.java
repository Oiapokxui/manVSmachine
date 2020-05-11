import java.io.*;
class Ordem{
	public static void main(String[] args) throws IOException{
		File naoOrdenado = new File("lista1.txt");
		FileReader leia = new FileReader(naoOrdenado);
		long len = naoOrdenado.length();
		char[] arquivoEmChar = new char[(int)len];
		leia.read(arquivoEmChar);
		 
		int numLista = 1;
		int quebra = 1;
		for (char i : arquivoEmChar){
			if ( !(i >= 48 && i <= 57) ){
			      if (quebra == 2) {
				      numLista ++;
				      quebra = 0;
			      }
			      quebra++;
			}
		}
		 
		StringBuilder[] listaEmStr = new StringBuilder[numLista];
		int[] lista = new int[numLista];
		int v = 0;
		listaEmStr[v] = new StringBuilder("");
		 
		/*
		// O seguinte laço esta preparado para ler datasets
		// separados por virgulas
		for (char i : arquivoEmChar){
			if (i == ',') {
				v++;
				listaEmStr[v] = new StringBuilder("");
			} else if (i != ' '&& i != 10) {
				listaEmStr[v].append(i);
			}
		}
		*/	
		 
		// novo
		for (char i : arquivoEmChar){
			if ( !(i >= 48 && i <= 57) ){
				if (quebra == 2) {
					numLista ++;
				      	quebra = 0;
					v++;
					listaEmStr[v] = new StringBuilder("");
				}
				quebra++;
			} else {
				listaEmStr[v].append(i);
			}
		}
		 
		for (int i = 0; i < listaEmStr.length; i++){
			String no = listaEmStr[i].toString();
			lista[i] = Integer.valueOf(no);
		}
		 
		int procura = 355; 
		Binary binary = new Binary(lista, procura);
		int linha = binary.search() + 1;
		// A busca binaria nao eh tao apropriada para conjuntos com elementos
		// repetidos, por isso não retornara a primeira aparicao de um numero
		// no conjunto, mas sim a primeira vez que o numero apareceu na busca.
		System.out.printf("Uma das aparicoes de %d foi na linha %d\n", procura, linha);
		 
		Selection select = new Selection(lista);
		Bubble bubble = new Bubble(lista);
		bubble.sort();
		// select.sort();
		 
		StringBuilder sb = new StringBuilder("");
		for (int i : bubble.arr){
			String no = Integer.toString(i);
			sb.append(no);
			sb.append("\n");
		}
		String fim = sb.toString();
		 
		File ordenado = new File("ordenado");
		ordenado.createNewFile();
		FileWriter escreva = new FileWriter(ordenado, false);
		escreva.write(fim);
		escreva.flush();
		escreva.close();

	}
}

class Binary{
	int[] arr;
	int num;
	Binary (int[] arr, int num){
		this.arr = arr;
		this.num = num;
	}

	int search(){
		boolean elementFound = false;
		int min = 0;
		int max = arr.length - 1;
		int meio = max/ 2 ;
		while (!elementFound) {
			if (arr[meio] == num) elementFound = true;
			else if (meio == 0 || meio == arr.length){ 
				meio = -1;
			       	break;
			}
			else if (arr[meio] > num) {
				max = meio - 1;
				meio /= 2;
			} else {
				min = meio + 1;
				meio = (min + max) /2;
			}
		} 
		return meio;
	}
}

class Selection{
	int[] arr;
	Selection (int[] arr){
		this.arr = arr;
	}
	 
	void sort(){
		int indexMin = 0;
		int altMin = 0;
		int max = arr.length - 1;
		boolean sorted = false;
		while (!sorted){
			if (indexMin == max){
			       	sorted = true;
			} else {
				for (int i = indexMin + 1; i <= max ; i++){
					if (arr[altMin] > arr[i]) altMin = i;
				}
				if (arr[indexMin] > arr[altMin] ){
					arr[altMin] = arr[indexMin] - arr[altMin];
					arr[indexMin] -= arr[altMin];
					arr[altMin] += arr[indexMin];
				}
				if (indexMin != max) indexMin++;
				altMin = indexMin;
			}
		}
	}
}

class Bubble{
	int[] arr;
	Bubble (int[] arr){
		this.arr = arr;
	}
	 		
	void sort(){
		boolean sorted = false;
		int max = arr.length - 1;
		while (!sorted){
			sorted = true;
			for (int i = 0; i < max ; i++){
				if (arr[i] > arr[i+1] ){
					arr[i] = arr[i+1] - arr[i];
					arr[i+1] -= arr[i];
					arr[i] += arr[i+1];
					sorted = false;
				}
			}
			max--;
		}
	}
}
