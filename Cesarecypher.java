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
	String cA, cB, cACypher, cBCypher;
	String msgAlterada = "";
	for (int i = 0; i < normal.length(); i++){
		System.out.println("\nAgora estamos falando de: " 
			+ normal.charAt(i) );
		for (int v = 0; v < 26; v++){
			cA = Character.toString( casoAlto[v] );
			cACypher = Character.toString( casoAlto[ ( v+keyindex ) % 					26 ] );
			cB = Character.toString( casoBaixo[v] );
			cBCypher = Character.toString( casoBaixo[ (v+keyindex) % 					26 ] );
			
			if (normal.charAt(i) == cA.charAt(0) ){
				msgAlterada = msgAlterada.concat(cACypher);
				System.out.printf("%s -> %s \n", cA , cACypher);
				System.out.println(msgAlterada);
				break;
			}	
			else if (normal.charAt(i) == cB.charAt(0) ) {
				msgAlterada = msgAlterada.concat(cBCypher);
				System.out.printf("%s -> %s \n", cB , cBCypher);
				System.out.println(msgAlterada);
				break;
				
			} 
			else if ( !(normal.charAt(i) == cA.charAt(0)) &&
				!(normal.charAt(i) == cB.charAt(0)) )
			{
				msgAlterada = msgAlterada.concat
					(String.valueOf( normal.charAt(i) ) );
				break;
			}
		
		}
	}
	return msgAlterada ;
}

public static String uncypher (String normal, char[] uppercase, 
char[] lowercase, int keyindex)
{

	final char[] casoAlto = uppercase;
	final char[] casoBaixo = lowercase;
	String cA, cB, cAUncypher, cBUncypher; 
	String msgAlterada = "";
	for (int i = 0; i < normal.length(); i++){
		System.out.println("\nAgora estamos falando de: " 
			+ normal.charAt(i) );
		for (int v = 0; v < 26; v++){
			cA = Character.toString( casoAlto[v] );
			cAUncypher = Character.toString( casoAlto[ ( v + 
				( 26 - keyindex) ) % 26 ] );
			cB = Character.toString( casoBaixo[v] );
			cBUncypher = Character.toString( casoAlto[ ( v + 
				( 26 - keyindex) ) % 26 ] );
			
			if ( normal.charAt(i) == cA.charAt(0) ){
				msgAlterada = msgAlterada.concat(cAUncypher);
				System.out.printf("%s -> %s \n", cA , cAUncypher);
				System.out.println(msgAlterada);
				break;
			}	
			else if (normal.charAt(i) == cB.charAt(0) ) {
				msgAlterada = msgAlterada.concat(cBUncypher);
				System.out.printf("%s -> %s \n", cB , cBUncypher);
				System.out.println(msgAlterada);
				break;
			} else if ( !(normal.charAt(i) == cB.charAt(0)) &&
				!(normal.charAt(i) == cA.charAt(0)) )
			{
				msgAlterada = msgAlterada.concat
					(String.valueOf( normal.charAt(i) ) );
				break;
			}
		}
	}
	return msgAlterada ;
}

}
