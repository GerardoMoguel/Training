package permporinter_00;

/**
 *
 * @author andre
 */
public class PermPorInter_00 {

    static void intercambia(int x[], int k, int j) {
        int t = x[k];
        x[k] = x[j];
        x[j] = t;
    }
    static boolean IMPRIME;
    static void perm_inter(int x[], int k, int n, int c[]) {
        if (k > n) {
            if (IMPRIME) {
                c[0]++;
                System.out.print(c[0] + " ... ");
                for (int i = 1; i <= n; i++) {
                    System.out.print(x[i] + " ");
                }
            System.out.println();
            }
        } else {
            for (int j = k; j <= n; j++) {
                intercambia(x, k, j);
                perm_inter(x, k + 1, n, c);
                intercambia(x, k, j);
            }
        }
    }

    public static void main(String[] args) {
        
        int N = 6;
        if( args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        if(args.length>1){
            IMPRIME=false;
        }
        else{
            IMPRIME=true;
        }
        
        long t0, t1;
        double deltaT_mSeg;
        
        if (args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        int c[] = new int[1];
        int x[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            x[i] = i;
        }
        
        t0 = System.nanoTime();
        perm_inter(x, 1, N, c);
        t1 = System.nanoTime();
        deltaT_mSeg=1.0E-6*(t1 - t0);

        if(IMPRIME){
            System.out.println("N:" + N + " deltaT_mSeg:" + deltaT_mSeg);
        }
        else{
            System.out.println(N +","+deltaT_mSeg);
        }
    }
}
