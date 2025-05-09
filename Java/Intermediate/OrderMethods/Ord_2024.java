/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenadores_2024;

import java.util.Arrays;

/**
 *
 * @author rafael
 */
public class Ord_2024 {

    
    public static void estructura_heap(double a[], int nodo, int fin) {
          int izq = 2*nodo+1;
        int der = izq+1;
        int may;
        if(izq>fin) return;
        if(der>fin) may=izq;
        else may= a[izq]>a[der]?izq:der;
        if(a[nodo] < a[may]) {
            double tmp = a[nodo];
            a[nodo] = a[may];
            a[may]  = tmp;
            estructura_heap(a, may, fin);
        }
    }

    public static void ordenaPorHeapSort(double a[]) {
        final int N = a.length;
        for(int nodo = N/2; nodo>=0; nodo--) estructura_heap(a, nodo, N-1);
        for(int nodo = N-1; nodo>=0; nodo--) {
            double tmp = a[0];
            a[0]    = a[nodo];
            a[nodo] = tmp;
            estructura_heap(a, 0, nodo-1);
        }
    }

    private static void qs(double a[], int izq, int der) {
        double t;
        double x = (a[izq] + a[der]) / 2;
        int i = izq;
        int j = der;
        do {
            while (a[i] < x) {
                i++;
            }
            while (a[j] > x) {
                j--;
            }
            if (i <= j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j) {
            qs(a, izq, j);
        }
        if (i < der) {
            qs(a, i, der);
        }
    }

    public static void ordQuickSort(double arr[]) {
        qs(arr, 0, arr.length - 1);
    }

    public static void ordenaPorStoneSort(double a[]) {

    }

    public static void ordenaPorSeleccionDta(double a[]) {

    }

    public static void ordenaPorInsersionDta(double a[]) {

    }

    public void bricksort(double a[]) // modalidad del bubblesort
    {

    }

    // Shaker Sort (Bubble y Brick intercalados)
    public static void shakersort(double a[]) // modalidad del bubblesort
    {

    }

    // ==========================================================================
    //                          Utilerías
    // ========================================================================== 
    public static void rellena(double a[], double xMin, double xMax, java.util.Random rnd) {
        int i, n = a.length;
        for (i = 0; i < n; i++) {
            a[i] = (float) (xMin + (xMax - xMin) * rnd.nextDouble());
        }
    }

    public static boolean verificaOrdenAscendente(double a[]) {
        boolean blnRes = true;
        int i, n = a.length - 1;
        for (i = 0; (i < n) && blnRes; i++) {
            if (a[i] > a[i + 1]) {
                System.out.println("LLaves desordenadas en a[" + i + "]:" + a[i]
                        + ",a[" + (i + 1) + "]:" + a[i + 1]);
                blnRes = false;
            }
        }
        /*
           else if( a[i] == a[i+1] )
           {
               System.out.println("LLaves repetidas en a[" + i + "]:" + a[i] +
                                  ",a[" + (i+1) + "]:" + a[i+1]);
               //blnRes = false;
           }
         */
        return blnRes;
    }

    public static void esc_arr(double a[]) {
        int i, n = a.length;
        for (i = 0; i < n; i++) {
            System.out.println("a[" + i + "]:" + a[i]);
        }
    }

    public static double[] copia_doubles(double a[]) {
        return Arrays.copyOf(a, a.length); //copia el arreglo en modo interno
    }

    public static int distintos(double a[]) {
        int i, n = a.length, cuantos = 0;
        for (i = 1; i < n; i++) {
            if (a[i - 1] != a[i]) {
                cuantos++;
            }
        }

        return cuantos;
    }

    public static boolean verif_ord(double arr[]) {
        int n = arr.length;
        int i = 0;
        boolean ban = true;
        while (ban & i < n - 1) {
            ban = arr[i] < arr[i + 1];

            if (!ban) {
                System.out.println("arr[" + i + "]:" + arr[i] + " ... arr[" + (i + 1) + "]:" + arr[i + 1]);
                System.exit(1);
            }
            i++;
        }
        return ban;
    }

    public static double[] genera_dbls(int n) {
        double x[] = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = 1.0e6 * Math.random();
        }
        return x;
    }
}
