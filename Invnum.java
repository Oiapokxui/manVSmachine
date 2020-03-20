public class Invnum {
     static int a;
    
    static int inv(int n){
        int nQuebrado[];
        int nInv = 0;
        String num = Integer.toString(n);
        int len = num.length();
        int divisor = Math.pow(10, (n.length() - i));
        for (i = 0, i < len ; i++) {
            if (i == 0) {
                nQuebrado[i] = n / divisor ;
            }
            if (i >0){
                int nInter = n - ( (n/divisor) * divisor );
                nQuebrado[i] = nInter / divisor ;
            }
            nInv = nInv + (nQuebrado[i] * Math.pow(10, i)) ;
        }
        return nInv ;
    }
    
    public static void main (String[] args){
        a = 332;
        System.out.println(inv(a));
        
    }
}
