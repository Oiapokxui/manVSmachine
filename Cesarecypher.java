import java.util.Scanner;
class Cesarecypher{
public static void main(String[] args){
	Scanner scan = new Scanner(System.in);

	String output;
	System.out.println("Digite a mensagem: ");
	String msg = scan.nextLine();
	System.out.print("Digite a chave e 'c' para cifrar ");
	System.out.println("ou 'u' para decifrar");
	int key = scan.nextInt() % 26;
	String cORu = scan.next() ;
	final char[] uppercase = new char[26];
	final char[] lowercase = new char[26];
	int upc = 65;
	int lwc = 97;
		
	for (int i = 0; i <= 25 ; i++){
		uppercase[i] =(char) upc;
		upc++;
		lowercase[i] =(char) lwc;
		lwc++;
	}

	switch (cORu){
		case "u" :
			output = uncypher(msg, uppercase, lowercase, key);
			System.out.println("Sua mensagem decifrada eh:\n" + output);
			break;
		case "c" :
			output = cypher(msg, uppercase, lowercase, key);
			System.out.println("Sua mensagem cifrada eh:\n" + output);
			break;
	}	
}

public static String cypher ( String normal, char[] uppercase, 
char[] lowercase, int keyindex) 
{
	final char[] casoAlto = uppercase;
	final char[] casoBaixo = lowercase;
	// cA será uma letra maiúscula a ser trocada por cACypher, letra com a cifra aplicada
	// cB será uma letra minúscula a ser trocada por cBCypher, letra com a cifra aplicada
	String cA, cB, cACypher, cBCypher;
	int len = normal.length();
	for (int i = 0; i < len; i++){
		for (int v = 0; v < 26; v++){
			cA = Character.toString( casoAlto[v] );
			cACypher = Character.toString( casoAlto[ ( v+keyindex ) % 					26 ] );
			cB = Character.toString( casoBaixo[v] );
			cBCypher = Character.toString( casoBaixo[ (v+keyindex) % 					26 ] );
			
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

public static String uncypher (String normal, char[] uppercase, 
char[] lowercase, int keyindex)
{

	final char[] casoAlto = uppercase;
	final char[] casoBaixo = lowercase;
	// cA será uma letra maiúscula a ser trocada por cAUncypher, com a cifra revertida
	// cB será uma letra minúscula a ser trocada por cBUncypher, com a cifra revertida
	String cA, cB, cAUncypher, cBUncypher; 
	int len = normal.length();
	for (int i = 0; i < normal.length(); i++){
		for (int v = 0; v < 26; v++){
			cA = Character.toString( casoAlto[v] );
			cAUncypher = Character.toString( casoAlto[ ( v + 
				( 26 - keyindex) ) % 26 ] );
			cB = Character.toString( casoBaixo[v] );
			cBUncypher = Character.toString( casoBaixo[ ( v + 
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
