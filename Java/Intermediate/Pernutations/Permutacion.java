/*
 * Gerardo Moguel Rovelo
 */
package permutacion;

public class Permutacion {

    static boolean IMPRIME;

    static boolean valida(int x[], int n) {
        boolean bandera = true;
        int i, j;
        i = 1;
        while(bandera && i < n) {
            j = i + 1;
            while(bandera && j <= n) {
                bandera = j - i != Math.abs(x[i] - x[j]);
                j++;
            }
            i++;
        }
        return bandera;
    }
    
    static void permut(int x[], int d[], int k, int n, int cuantas[]) {
        
        if(k > n) {
            if(valida(x,n)) {
                cuantas[0]=cuantas[0]+1;
                System.out.print(cuantas[0] + "...");
                for(int i = 1; i <= n; i++) {
                    System.out.print(x[i] + " ");
                }
                System.out.println();   
            }
            return;
        }
        
        for(int j = 1; j<= n; j++) {
            if(d[j] == 1) {
                x[k] = j;
                d[j] = 0;
                permut(x,d,k+1,n,cuantas);
                d[j]=1;
            }
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int N = 5;
        if (args.length > 0) {
            N = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            IMPRIME = false;
        } else {
            IMPRIME = true;
        }
        long t0, t1;
        double deltaT_mSeg;
        int x[] = new int[N + 1];
        int d[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = 1;
        }
        int cuantas[] = new int[1];
        cuantas[0] = 0;
        t0 = System.nanoTime();
        permut(x, d, 1, N, cuantas);
        t1 = System.nanoTime();
        deltaT_mSeg = 1.0E-6 * (t1 - t0);
        if (IMPRIME) {
            System.out.println("N:" + N + " deltaT_mSeg:" + deltaT_mSeg);
        } else {
            System.out.println(N + "," + deltaT_mSeg);
        }
    }
}
