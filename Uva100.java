import java.util.Scanner;
import java.util.HashMap;
public class Uva100 {
public static void main (String[] args){
	HashMap<Integer, Integer> numLen = new HashMap <Integer, Integer>();
	Scanner scan = new Scanner(System.in);
	while(scan.hasNext()){
		int n1 = scan.nextInt();
		int n2 = scan.nextInt();
		int maxlen = 0;
		if (n1 >= n2){
			for (int i = n2; i<=n1; i++){
				int num = i;
				int len = 1;
				while (num != 1){
					if (num%2 == 0){
						num = num / 2;
						if (numLen.containsKey(num)){
							len = len + numLen.get(num);
							break;
						} else { len++; }
					} else {
						num = 3*num + 1;
						if (numLen.containsKey(num)){
							len = len + numLen.get(num);
							break;
						} else { len++; }
					}
				}
				numLen.put(i, len);
				if (len > maxlen) { maxlen = len; } 
			}
		}
		if (n2 > n1){
			for (int i = n1; i<=n2; i++){
				int num = i;
				int len = 1;
				while (num != 1){
					if (num%2 == 0){
						num = num / 2;
						if (numLen.containsKey(num)){
							len = len + numLen.get(num);
							break;
						} else { len++; }
					} else {
						num = 3*num + 1;
						if (numLen.containsKey(num)){
							len = len + numLen.get(num);
							break;
						} else { len++; }
					}
				}
				numLen.put(i, len);
				if (len > maxlen) { maxlen = len; } 
			}
		}
		System.out.printf("%d %d %d\n",n1,n2, maxlen);
	}
}
}
