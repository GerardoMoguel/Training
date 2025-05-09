
package compare_permu_dt;

/**
 *
 * @author andre
 */
public class Compare_Permu_DT {
    static boolean IMPRIME;
    //primera forma de ejecutar permutaciones, por medio de generacion.
 

    static void permut(int x[], int d[], int k, int n, int cuantas[]) {
        if (k > n) {
                cuantas[0]++;
                if (IMPRIME) {
                    System.out.print(cuantas[0] + "...");
                    for (int i = 1; i <= n; i++) {
                        System.out.print(x[i] + " ");
                    }
                    System.out.println();
                }
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (d[j] == 1) {
                x[k] = j;
                d[j] = 0;
                permut(x, d, k + 1, n, cuantas);
                d[j] = 1;
            }
        }
    }

    // segunda forma de ejecutar permutaciones por medio de intercambio.
    static void intercambia(int x[], int k, int j) {
        int t = x[k];
        x[k] = x[j];
        x[j] = t;
    }
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
        
        int N = 3;
        if( args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        if(args.length>1){
            IMPRIME=false;
        }
        else{
            IMPRIME=true;
        }
        
        long t10, t11,t20,t21;
        double deltaT_mSeg,deltaT_inter_mSeg;
       
        int c[] = new int[1];
        c[0]=0;
        
        int x[] = new int[N + 1];
        int d[] = new int[N + 1]; // disponibles   
        for (int i = 1; i <= N; i++) {
            x[i] = i;
            d[i]=1;
        }        
        t10 = System.nanoTime();
        permut(x,d,1,N,c);
        t11 = System.nanoTime();
        c[0]=0;
        deltaT_mSeg=1.0E-6*(t11 - t10);
        t20 = System.nanoTime();
        perm_inter(x,1,N,c);
        t21 = System.nanoTime();
        deltaT_inter_mSeg=1.0E-6*(t21 - t20);
        
        if(IMPRIME){
            System.out.println("N:" + N + " deltaT_mSeg:" + deltaT_mSeg);
        }
        else{
            System.out.println(N +","+deltaT_mSeg+","+N+","+deltaT_inter_mSeg);
        }
    }
}
