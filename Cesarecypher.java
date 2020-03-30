/*********************************************************************/
/**   Cifra de Cesar                                                **/
/**                                                                 **/
/**   Oiapokxui           enrique.emanuel@usp.br                    **/
/**                                                                 **/
/**   30/03/2020                                                    **/
/*********************************************************************/

/*
	Codigo para cifrar e decifrar uma mensagem pelo metodo
	da cifra de cesar
*/

import java.util.Scanner;
class Cesarecypher{

// Aqui estão declaradas as arrays que armazenarao todas as
// letras do alfabeto. Uppercase = Maiúscula, Lowercase = Minúscula
static final char[] uppercase = new char[26];
static final char[] lowercase = new char[26];

public static void main(String[] args){
	Scanner scan = new Scanner(System.in);
	
	// Armazena a mensagem final a ser impressa ao usuário
	String output;

	System.out.println("Digite a mensagem: ");
	String msg = scan.nextLine();
	
	System.out.print("Digite a chave e 'c' para cifrar ");
	System.out.println("ou 'u' para decifrar");
	int key = scan.nextInt() % 26;
	String cORu = scan.next() ;
	
	/* Essas duas aqui indicam o valor ASCII da letra 'a' maiuscula
	   e minuscula, respectivamente. Sao usadas pra criar as arrays de chars acima
	*/
	int upc = 65;
	int lwc = 97;
	
	//Armazena as letras do alfabeto nas arrays
	for (int i = 0; i <= 25 ; i++){
		uppercase[i] =(char) upc;
		upc++;
		lowercase[i] =(char) lwc;
		lwc++;
	}
	
	
	switch (cORu){
		case "u" :
			output = uncypher(msg, key);
			System.out.println("Sua mensagem decifrada eh:\n" + output);
			break;
		case "c" :
			output = cypher(msg, key);
			System.out.println("Sua mensagem cifrada eh:\n" + output);
			break;
	}	
}

	/*	
  		Para cada letra dentro da mensagem, compara a igualdade
		essa letra com cada letra do alfabeto (Maiusc ou minusc).
		Caso seja verdade a igualdade, substitui a letra comparada
		por uma letra alterada segundo a cifra.
		Retorna na mesma variavel da string recebida a mensagem final.

		Parametros: 
			  'normal' - Mensagem recebido
			  'keyindex' -  Quantas casas do alfabeto a se mover
	*/


public static String cypher ( String normal, int keyindex) 
{

	// cA será uma letra maiúscula a ser trocada por cACypher, letra com a cifra aplicada
	// cB será uma letra minúscula a ser trocada por cBCypher, letra com a cifra aplicada
	String cA, cB, cACypher, cBCypher;
	int len = normal.length();
	for (int i = 0; i < len; i++){
		for (int v = 0; v < 26; v++){
			cA = Character.toString( uppercase[v] );
			cACypher = Character.toString( uppercase[ ( v+keyindex ) % 					26 ] );
			cB = Character.toString( lowercase[v] );
			cBCypher = Character.toString( lowercase[ (v+keyindex) % 					26 ] );
			
			if (normal.charAt(i) == cA.charAt(0) ){
				if (i == 0) normal = cACypher + normal.substring(1);
				else if (i == (len-1)) normal = normal.substring(0, len - 1 ) + cACypher;
				else normal = normal.substring(0, i) + cACypher + normal.substring(i+1);
				break;
			}	
			else if (normal.charAt(i) == cB.charAt(0) ) {
				if (i == 0) normal = cBCypher + normal.substring(1);
				else if (i == (len-1)) normal = normal.substring(0, len - 1 ) + cBCypher;
				else normal = normal.substring(0, i) + cBCypher + normal.substring(i+1);
				break;
				
			} 
		
		}
	}
	return normal;
}

/*
	Funciona da mesma maneira que o metodo cypher(), com os mesmo parametros.
	
*/

public static String uncypher (String normal, int keyindex)
{

	// cA será uma letra maiúscula a ser trocada por cAUncypher, com a cifra revertida
	// cB será uma letra minúscula a ser trocada por cBUncypher, com a cifra revertida
	String cA, cB, cAUncypher, cBUncypher; 
	int len = normal.length();	
	for (int i = 0; i < normal.length(); i++){
		for (int v = 0; v < 26; v++){
			cA = Character.toString( uppercase[v] );
		/*	
			Aqui, ( v + 26 - keyindex ) % 26 impede que, caso
			v seja menor que a chave da cifra,
			o indice da letra a se trocar seja menor que 0.
			Ou seja, ao invés de voltar casas para achar a letra
			original, avanca uma volta completa menos o numero da cifra
		*/ 
			cAUncypher = Character.toString( uppercase[ ( v + 
				( 26 - keyindex) ) % 26 ] );
			cB = Character.toString( lowercase[v] );
			cBUncypher = Character.toString( lowercase[ ( v + 
				( 26 - keyindex) ) % 26 ] );
			
			if ( normal.charAt(i) == cA.charAt(0) ){
				if (i == 0) normal = cAUncypher + normal.substring(1);
				else if (i == (len-1)) normal = normal.substring(0, len - 1) + cAUncypher;
				else normal = normal.substring(0, i) + cAUncypher + normal.substring(i+1);
				break;
			}	
			else if (normal.charAt(i) == cB.charAt(0) ) {
				if (i == 0) normal = cBUncypher + normal.substring(1);
				else if (i == (len-1)) normal = normal.substring(0, len - 1 ) + cBUncypher;
				else normal = normal.substring(0, i) + cBUncypher + normal.substring(i+1);
				break;
			}
		}
	}
	return normal ;
}

}
